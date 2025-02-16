package pookie;

import pookie.tasks.TaskManager;

public class Parser {

    public static void interpretLineInput(String lineInput) {
        String[] wordArray = separateInput(lineInput);

        switch (wordArray[0]) {
        case "mark":
        case "unmark":
            TaskManager.markTask(wordArray);
            break;
        case "bye":
        case "exit":
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
        default:
            System.out.println("\tI don't understand, please start with a command <3.");
            Pookie.doLineBreak();
            break;
        }
    }

    private static String[] separateInput(String sentence) {
        sentence = sentence.trim();
        return sentence.split(" ");
    }
}
