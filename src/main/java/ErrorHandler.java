public class ErrorHandler {

    // Non numbers after "mark"
    public static void printInvalidMarkIndex() {
        System.out.println("\tInvalid command format!");
        System.out.println("\tCorrect format: mark [index of task].");
        TaskManager.printList();
        System.out.println("\tYou may try again!");
    }

    // Given index out of bounds of wordArray
    // Given index out of bounds of taskList
    public static void printOutOfBoundsMarkIndex(ArrayIndexOutOfBoundsException e) {
        System.out.println("\tInvalid command format!");
        System.out.println("\t" + e.getMessage());
        System.out.println("\tCorrect format: mark [index of task].");
        TaskManager.printList();
        System.out.println("\tYou may try again!");
    }
}
