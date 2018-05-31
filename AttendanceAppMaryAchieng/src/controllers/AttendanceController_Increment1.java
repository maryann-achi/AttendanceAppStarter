package controllers;

import helpers.InputHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import model.Swipe;
import model.VisitorSwipe;
import repositories.Repository;

/**
 *
 * @author mga
 */
public class AttendanceController_Increment1 {
     private final Repository repository;
     List<Swipe>swipes;

    /**
     *
     */

    public AttendanceController_Increment1() {

        InputHelper inputHelper = new InputHelper();
        char a = inputHelper.readCharacter("Load an already existing Customers File (Y/N)?");
        if (a == 'Y' || a == 'y') {
            String fileName = inputHelper.readString("Enter filename");
            this.repository = new Repository(fileName);
        }
        else {
            this.repository = new Repository();
        }
    }

    /**
     *
     */
    public void run() {
        boolean finished = false;

        do {
            char choice = displayAttendanceMenu();
            switch (choice) {
                case 'A':
                    addSwipe();
                    break;
                case 'B':
                    listSwipes();
                    break;
                case 'C':
                    listSwipesByCardIdOrderedByDateTime(); //
                    break;
                case 'D':
                    listSwipeStatistics(); //
                    break;
                case 'Q':
                    finished = true;
            }
        } while (!finished);
    }

    private char displayAttendanceMenu() {
        InputHelper inputHelper = new InputHelper();
        System.out.print("\nA. Add Swipe");
        System.out.print("\tB. List Swipes");
        System.out.print("\tC. List Swipes In Date Time Order");
        System.out.print("\tD. List Swipes Which Match Card Id");
        System.out.print("\tQ. Quit\n");
        return inputHelper.readCharacter("Enter choice", "ABCDQ");
    }

    private void addSwipe() {
        System.out.format("\033[31m%s\033[0m%n", "Add Swipe");
        System.out.format("\033[31m%s\033[0m%n", "=========");


    }

    private void listSwipes() {
        System.out.format("\033[31m%s\033[0m%n", "Swipes");
        System.out.format("\033[31m%s\033[0m%n", "======");

        Iterator it = repository.getItems().listIterator();
        while (it.hasNext()) {
            Swipe s = (Swipe) it.next();
            System.out.println(s);
        }
    }


    private void listSwipesByCardIdOrderedByDateTime() {
        System.out.format("\033[31m%s\033[0m%n", "Swipes By Card Id");
        System.out.format("\033[31m%s\033[0m%n", "=================");

        }


    private void listSwipeStatistics() {
        System.out.format("\033[31m%s\033[0m%n", "Swipe Statistics for room");
        System.out.format("\033[31m%s\033[0m%n", "=========================");


    }
}
