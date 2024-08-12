 package abc;

import java.util.Stack;

//Command interface
interface Command { 
	
 void execute();
 void undo();
}

//Receiver class
class TextEditor {
 private StringBuilder text = new StringBuilder();

 public void append(String textToAppend) {
     text.append(textToAppend);
 }

 public void delete(int length) {
     int start = text.length() - length;
     if (start < 0) start = 0;
     text.delete(start, text.length());
 }

 public String getText() {
     return text.toString();
 }
}

//Command to append text
class AppendCommand implements Command {
 private TextEditor editor;
 private String textToAppend;

 public AppendCommand(TextEditor editor, String textToAppend) {
     this.editor = editor;
     this.textToAppend = textToAppend;
 }

 @Override
 public void execute() {
     editor.append(textToAppend);
 }

 @Override
 public void undo() {
     editor.delete(textToAppend.length());
 }
}

//Command to delete text
class DeleteCommand implements Command {
 private TextEditor editor;
 private String deletedText;

 public DeleteCommand(TextEditor editor, int length) {
     this.editor = editor;
     this.deletedText = editor.getText().substring(Math.max(0, editor.getText().length() - length));
 }

 @Override
 public void execute() {
     editor.delete(deletedText.length());
 }

 @Override
 public void undo() {
     editor.append(deletedText);
 }
}

//Invoker class
class TextEditorInvoker {
 private Stack<Command> undoStack = new Stack<>();
 private Stack<Command> redoStack = new Stack<>();

 public void executeCommand(Command command) {
     command.execute();
     undoStack.push(command);
     redoStack.clear(); // Clear redo stack on new command
 }

 public void undo() {
     if (!undoStack.isEmpty()) {
         Command command = undoStack.pop();
         command.undo();
         redoStack.push(command);
     }
 }

 public void redo() {
     if (!redoStack.isEmpty()) {
         Command command = redoStack.pop();
         command.execute();
         undoStack.push(command);
     }
 }
}

//Main application class
public class Main {
 public static void main(String[] args) {
     TextEditor editor = new TextEditor();
     TextEditorInvoker invoker = new TextEditorInvoker();

     // Perform some operations
     Command appendHello = new AppendCommand(editor, "Hello ");
     Command appendWorld = new AppendCommand(editor, "World!");
     Command deleteWorld = new DeleteCommand(editor, 6);

     invoker.executeCommand(appendHello);
     invoker.executeCommand(appendWorld);
     System.out.println("Current text: " + editor.getText());

     invoker.undo();
     System.out.println("After undo: " + editor.getText());

     invoker.redo();
     System.out.println("After redo: " + editor.getText());

     invoker.executeCommand(deleteWorld);
     System.out.println("After delete: " + editor.getText());

     invoker.undo();
     System.out.println("After undo delete: " + editor.getText());

     invoker.redo();
     System.out.println("After redo delete: " + editor.getText());
 }
}
