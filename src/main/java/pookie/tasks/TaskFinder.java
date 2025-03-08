package pookie.tasks;

import java.util.ArrayList;

import static pookie.customs.ColorAndStyles.BLUE;
import static pookie.customs.ColorAndStyles.GREEN;
import static pookie.customs.ColorAndStyles.RED;
import static pookie.customs.ColorAndStyles.RESET;

import pookie.Pookie;

public class TaskFinder {

    public static void findTasksWithKeywords(String[] wordsToFind) {
        ArrayList<Task> taskList = TaskManager.getTaskList();
        ArrayList<Task> tasksWithKeywords = new ArrayList<>();
        ArrayList<Integer> tasksWithKeywordsIndex = new ArrayList<>();
        int taskIndex = 1;
        for (Task task : taskList) {
            if (doesTaskContainKeywords(task, wordsToFind)) {
                tasksWithKeywords.add(task);
                tasksWithKeywordsIndex.add(taskIndex);
            }
            taskIndex++;
        }
        printTasksWithKeywords(tasksWithKeywords, tasksWithKeywordsIndex, wordsToFind);
        Pookie.doLineBreak();
    }

    private static void printTasksWithKeywords(ArrayList<Task> taskList, ArrayList<Integer> taskIndex, String[] wordsToFind) {
        if (taskList.isEmpty()) {
            printNoTasksWithKeywords(wordsToFind);
        } else {
            printPopulatedTaskList(taskList, taskIndex, wordsToFind);
        }
    }

    private static void printPopulatedTaskList(ArrayList<Task> taskList, ArrayList<Integer> taskIndex, String[] keywords) {
        System.out.println("\tYou have " + GREEN + taskIndex.size() + RESET + " tasks that contain some keywords:");
        for (String keyword : keywords) {
            System.out.println("\t\t" + BLUE + "> " + RESET + keyword);
        }
        Pookie.doLineBreak();
        for (int i = 0; i < taskIndex.size(); i++) {
            System.out.println("\t\t" + BLUE + taskIndex.get(i).toString() + ". " + RESET + taskList.get(i).getTaskInListFormat());
        }
    }

    private static boolean doesTaskContainKeywords(Task task, String[] wordsToFind) {
        String[] wordsInTask = task.getTaskDescription().split(" +");
        for (String keyword : wordsToFind) {
            for (String word : wordsInTask) {
                if (word.equals(keyword)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void printNoTasksWithKeywords(String[] keywords) {
        if (keywords.length == 0) {
            System.out.println("\tAdd a keyword to find!");
        } else {
            System.out.println("\tYou have " + RED + "0 " + RESET + "tasks that contain these keywords:");
            for (String keyword : keywords) {
                System.out.println("\t\t" + BLUE + "> " + RESET + keyword);
            }
        }
    }
}
