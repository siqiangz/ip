import java.util.Scanner;

public class Pookie {
    private static boolean onOffSwitch = true;
    private static int listSize = 0;
    private static final int MAX_LIST_SIZE = 100;
    private static String[] taskList = new String[MAX_LIST_SIZE];

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
        taskList[listSize] = task;
        listSize++;
        System.out.println("\tadded: " + task);
        doLineBreak();
    }

    public static void doPrintList() {
        if (listSize == 0) {
            System.out.println("\tYou do not have any tasks!");
            System.out.println("\tType something to add to the task list.");
        } else {
            for (int i = 0; i < listSize; i++) {
                System.out.println("\t" + (i + 1) + ". " + taskList[i]);
            }
        }
        doLineBreak();
    }

    public static void commandDictionary(String lineInput) {
        switch (lineInput) {
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
