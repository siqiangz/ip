import java.util.Scanner;

public class Pookie {
    private static boolean onOffSwitch = true;
    private static int listSize = 0;
    private static final int MAX_LIST_SIZE = 100;
    private static Task[] taskList = new Task[MAX_LIST_SIZE];

    public static void greeting() {
        String logo = "\t__________              __   .__        \n"
                + "\t\\______   \\____   ____ |  | _|__| ____  \n"
                + "\t |     ___/  _ \\ /  _ \\|  |/ /  |/ __ \\ \n"
                + "\t |    |  (  <_> |  <_> )    <|  \\  ___/ \n"
                + "\t |____|   \\____/ \\____/|__|_ \\__|\\___>";
        System.out.println("\t** ** ** ** ** Hello from ** ** ** ** **\n" + logo);
        doLineBreak();
        System.out.println("\tHello! I'm Pookie, your task tracker!");
        System.out.println("\tWhat can I do for you?");
        doLineBreak();
    }

    public static void goodbye() {
        System.out.println("\tBye bye, hope to see you again soon!");
        doLineBreak();
    }

    public static void doLineBreak() {
        System.out.println("\t_________________________________________");
    }

    public static void addTaskToList(String task) {
        if (listSize >= 100) {
            System.out.println("\tTask list is already full!");
        } else {
            Task t = new Task(task);
            taskList[listSize] = t;
            listSize++;
            System.out.println("\tadded: " + task);
            System.out.println("\t\t[" + listSize + "/100 tasks added]");
        }
        doLineBreak();
    }

    public static void doPrintList() {
        if (listSize == 0) {
            System.out.println("\tYou do not have any tasks!");
            System.out.println("\tType something to add to the task list.");
        } else {
            System.out.println("\tHere are all your tasks:");
            for (int i = 0; i < listSize; i++) {
                String taskStatus = taskList[i].getStatusIcon();
                String taskDescription = taskList[i].getTaskDescription();
                System.out.println("\t" + (i + 1) + ".[" + taskStatus + "] " + taskDescription);
            }
            System.out.println("\t\t[" + listSize + "/100 tasks added]");
        }
        doLineBreak();
    }

    public static String[] separateInput(String sentence) {
        sentence = sentence.trim();
        return sentence.split(" ", 2);
    }

    public static void markTask(String[] wordArray, String lineInput, String mark) {
        try {
            if (wordArray.length <= 1) {
                throw new ArrayIndexOutOfBoundsException("Please specify task index.");
            }
            int taskListIndex = Integer.parseInt(wordArray[1]);
            if (taskListIndex < 1 || taskListIndex > listSize) {
                throw new ArrayIndexOutOfBoundsException("Index provided is out of bounds.");
            }
            Task t = taskList[taskListIndex - 1];
            if (mark.equals("mark")) {
                t.setIsDone(true);
                System.out.println("\tGreat! I've marked this task as done:");
                System.out.println("\t\t[" + t.getStatusIcon() + "] " + t.getTaskDescription());
            } else {
                t.setIsDone(false);
                System.out.println("\tOK, I've marked this task as not done:");
                System.out.println("\t\t[" + t.getStatusIcon() + "] " + t.getTaskDescription());
            }
            doLineBreak();
        } catch (NumberFormatException e){
            // Non numbers after "mark"
            System.out.println("\tInvalid command format!");
            System.out.println("\tCorrect format: mark [index of task].");
            doPrintList();
            System.out.println("\tYou may try again!");
        } catch (ArrayIndexOutOfBoundsException e) {
            // Given index out of bounds of wordArray
            // Given index out of bounds of taskList
            System.out.println("\tInvalid command format!");
            System.out.println("\t" + e.getMessage());
            System.out.println("\tCorrect format: mark [index of task].");
            doPrintList();
            System.out.println("\tYou may try again!");
        }
        doLineBreak();
    }

    public static void commandDictionary(String lineInput) {
        // Separate lineInput into individual words in an array
        String[] wordArray = separateInput(lineInput);

        switch (wordArray[0]) {
        case "":
            System.out.println("\tI don't understand, please type something.");
            doLineBreak();
            break;
        case "mark":
            markTask(wordArray, lineInput, "mark");
            break;
        case "unmark":
            markTask(wordArray, lineInput, "unmark");
            break;
        case "bye":
            goodbye();
            onOffSwitch = false;
            break;
        case "list":
            doPrintList();
            break;
        default:
            addTaskToList(lineInput);
            break;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String lineInput;
        greeting();
        while (onOffSwitch) {
            lineInput = in.nextLine();
            doLineBreak();
            commandDictionary(lineInput);
        }
    }


}
