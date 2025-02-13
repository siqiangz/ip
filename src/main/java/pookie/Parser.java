package pookie;

import pookie.tasks.TaskManager;

public class Parser {

    public static void interpretLineInput(String lineInput) {
        String[] wordArray = separateInput(lineInput);

        switch (wordArray[0]) {
        case "mark":
            TaskManager.markTask(wordArray, lineInput, "mark");
            break;
        case "unmark":
            TaskManager.markTask(wordArray, lineInput, "unmark");
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
