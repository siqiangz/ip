import java.util.Scanner;

public class Pookie {
    private static boolean onOffSwitch = true;

    public static void greeting() {
        String logo = "\t__________              __   .__        \n"
                + "\t\\______   \\____   ____ |  | _|__| ____  \n"
                + "\t |     ___/  _ \\ /  _ \\|  |/ /  |/ __ \\ \n"
                + "\t |    |  (  <_> |  <_> )    <|  \\  ___/ \n"
                + "\t |____|   \\____/ \\____/|__|_ \\__|\\___>\n";
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

    public static void doEcho(String sentence) {
        System.out.println("\t" + sentence);
        doLineBreak();
    }

    public static void doLineBreak() {
        System.out.println("\t_________________________________________");
    }

    public static void commandDictionary(String lineInput) {
        switch (lineInput) {
        case "bye":
            goodbye();
            onOffSwitch = false;
            break;
        default:
            doEcho(lineInput);
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
