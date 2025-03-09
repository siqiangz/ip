package pookie;

import java.util.Scanner;

import static pookie.customs.ColorAndStyles.PURPLE;
import static pookie.customs.ColorAndStyles.RED;
import static pookie.customs.ColorAndStyles.RESET;

import pookie.files.FileManager;

/**
 * Pookie is a task tracker class where tasks can be added, deleted and marked.
 * Tasks data is stored in a text file, and will be read from next time Pookie
 * runs.
 */
public class Pookie {
    private static boolean isPookieAwake = true;

    private static void greeting() {
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

    /**
     * Prints a goodbye message and ends Pookie.
     */
    public static void goodbye() {
        System.out.println(PURPLE + "\tBye bye, hope to see you again soon!" + RESET);
        System.out.println("\t" + RED + ",d88b.d88b," + RESET);
        System.out.println("\t" + RED + "88888888888" + RESET);
        System.out.println("\t" + RED + "`Y8888888Y'" + RESET);
        System.out.println("\t  " + RED + "`Y888Y'" + RESET);
        System.out.println("\t    " + RED + "`Y'" + RESET);
        isPookieAwake = false;
        doLineBreak();
    }

    public static void doLineBreak() {
        System.out.println("\t_________________________________________");
    }

    public static void main(String[] args) {
        FileManager.getSaveFileOnStartup();
        final Scanner in = new Scanner(System.in);
        String lineInput;
        greeting();
        while (isPookieAwake) {
            lineInput = in.nextLine();
            Parser.interpretLineInput(lineInput);
        }
    }
}
