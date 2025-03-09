package pookie;

import pookie.files.FileManager;
import pookie.tasks.TaskManager;

/**
 * Parser class breaks down line inputs from the user and
 * accesses necessary methods of other classes to
 * execute commands.
 */
public class Parser {

    /**
     * Stores user's input into a String array.
     * Calls necessary methods in other classes.
     * @param lineInput String copy of user's input
     */
    public static void interpretLineInput(String lineInput) {
        String[] wordArray = separateInput(lineInput);

        switch (wordArray[0]) {
        case "mark":
        case "unmark":
            TaskManager.markTask(wordArray);
            break;
        case "bye":
        case "exit":
            FileManager.updateSaveFileOnExit();
            Pookie.goodbye();
            break;
        case "list":
            TaskManager.printList();
            break;
        case "todo":
        case "deadline":
        case "event":
            TaskManager.addNewTask(wordArray);
            break;
        case "delete":
            TaskManager.deleteTask(wordArray);
            break;
        case "find":
            TaskManager.findTask(wordArray);
            break;
        default:
            System.out.println("\tI don't understand, please start with a command <3.");
            Pookie.doLineBreak();
            break;
        }
    }

    private static String[] separateInput(String sentence) {
        sentence = sentence.trim();
        return sentence.split(" +");
    }
}
