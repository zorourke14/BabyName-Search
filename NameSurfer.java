/*
 * Logan May, Zack O'Rourke
 * CSC 220
 * Project 4: Name Surfer
 * December 9th
 * Uses name databases to search through and prompts user to give a name or year
 * Uses the databases to come out with accurate results about the users chosen name 
 */

//imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class NameSurfer {

    // this method gets reprinted everytime the loop goes, prints the menu
    public static void printmenu() {
        System.out.println("1 - Find best year for a female name ");
        System.out.println("2 - Find best rank for a female name ");
        System.out.println("3 - Find a female name's rank in a specified year ");
        System.out.println("4 - Find best year for a male name ");
        System.out.println("5 - Find best rank for a male name ");
        System.out.println("6 - Find a male name's rank in a specified year ");
        System.out.println("7 - Quit  ");
        System.out.println("Enter your selection.");
    }

    public static void main(String[] args) throws FileNotFoundException {

        // two arraylists for Intergers and Strings from the files
        ArrayList<NameRecord> mList = new ArrayList<NameRecord>();
        ArrayList<NameRecord> fList = new ArrayList<NameRecord>();

        // scanner creation
        Scanner scnr = new Scanner(System.in);

        for (int i = NameRecord.START; i <= NameRecord.END; i++) {

            // make a tostring for each file number
            Scanner fscnr = new Scanner(new File("names/yob" + i + ".txt"));
            int maleRank = 1;
            int femaleRank = 1;

            // reads scanner
            while (fscnr.hasNextLine()) {

                // turning it into a string array to find gender
                String[] result = fscnr.nextLine().split(",");

                // making the text docs into seperate arrays based on name, gender, and rank
                String name = result[0];
                char gender = result[1].charAt(0);
                boolean test = false;

                // puts Males in 'M' and puts Females in 'F'
                // male list
                if (gender == 'M') {
                    if (mList.size() == 0) {
                        mList.add(new NameRecord(name));
                    }

                    // sorts out information & rank
                    for (int j = 0; j < mList.size(); j++) {
                        if (mList.get(j).getName().equalsIgnoreCase(name)) {
                            test = true;
                            mList.get(j).setRank(i - NameRecord.START, maleRank);
                            maleRank++;
                            break;
                        }
                    }

                    // if failed, adds a new NameRecord instance and rank
                    if (test == false) {
                        NameRecord nr = new NameRecord(name.toLowerCase());
                        nr.setRank(i - NameRecord.START, maleRank);
                        mList.add(nr);
                        maleRank++;
                    }
                } else {

                    // female list
                    if (fList.size() == 0) {
                        fList.add(new NameRecord(name));
                    }
                    // sorts out information & rank
                    for (int j = 0; j < fList.size(); j++) {
                        if (fList.get(j).getName().equalsIgnoreCase(name)) {
                            test = true;
                            fList.get(j).setRank(i - NameRecord.START, femaleRank);
                            femaleRank++;
                            break;
                        }
                    }

                    // if failed, adds a new NameRecord instance and rank
                    if (test == false) {
                        NameRecord nr = new NameRecord(name);
                        nr.setRank(i - NameRecord.START, femaleRank);
                        fList.add(nr);
                        femaleRank++;
                    }
                }
            }
        }

        // while loop
        while (true) {

            //prints menu
            printmenu();

            int choice = scnr.nextInt();

            switch (choice) {

                // returns user with the best year for female name
                case 1: // prompt user for a name
                    boolean found = false;
                    System.out.println("Please enter a name:");
                    String fName1 = scnr.next();

                    for (int i = 0; i < fList.size(); i++) {
                        if (fList.get(i).getName().equalsIgnoreCase(fName1.toLowerCase())) {
                            found = true;
                            System.out.println("\n" + fName1 + " was most popular in: " + fList.get(i).bestYear() + "\n");
                            break;

                        }
                    }
                    if (!(found)) {
                        System.out.println("Could not find: " + fName1);
                    }
                    break;

                // returns user with the best rank for a female name
                case 2: // prompt user for a name
                    System.out.println("Please enter a name:");
                    String fName2 = scnr.next();

                    boolean findName2 = false;
                    int bestRank2;
                    for (int i = 0; i < fList.size(); i++) {
                        if (fList.get(i).getName().equalsIgnoreCase(fName2)) {
                            findName2 = true;
                            bestRank2 = fList.get(i).getRank(fList.get(i).bestYear()-NameRecord.START);

                            System.out.println("\n" + fName2 + "'s highest rank was: " + bestRank2 + "\n");
                        } 
                    }

                    if (!(findName2)) {
                        System.out.println("Could not find: " + fName2);
                    }

                    break;

                // returns user with a specific rank for a year for a female
                case 3: // prompt user for a name and year
                    System.out.println("Please enter a name and year:");
                    String fName3 = scnr.next();
                    int fYear = scnr.nextInt();
                    boolean findName3 = false;

                    for (int i = 0; i < fList.size(); i++) {
                        if (fList.get(i).getName().equalsIgnoreCase(fName3)) {
                            findName3 = true;
                            System.out.println("\n" + fName3 + "'s Highest Rank for " + fYear + " was: " + fList.get(i).getRank(fYear - NameRecord.START)+ "\n");
                        }
                    }

                    if (!(findName3)) {
                        System.out.println("Could not find: " + fName3 + " in the year " + fYear + "\n");
                    }

                    break;

                // returns user with the best year for a female name
                case 4: // prompt user for a name
                    boolean findName4 = false;
                    System.out.println("Please enter a name:");
                    String mName1 = scnr.next();

                    for (int i = 0; i < mList.size(); i++) {
                        if (mList.get(i).getName().equalsIgnoreCase(mName1.toLowerCase())) {
                            findName4 = true;
                            System.out.println("\n" + mName1 + " Was most popular in: " + mList.get(i).bestYear() + "\n");
                            break;

                        }
                    }
                    if (!(findName4)) {
                        System.out.println("Could not find: " + mName1);
                    }
                    break;

                // returns user with the best rank for a female name
                case 5: // prompt user for a name
                    System.out.println("Please enter a name:");
                    String mName2 = scnr.next();
                    boolean findName5 = false;
                    int bestRank1;

                    for (int i = 0; i < mList.size(); i++) {
                        if (mList.get(i).getName().equalsIgnoreCase(mName2)) {
                            findName5 = true;
                            bestRank1 = mList.get(i).getRank(mList.get(i).bestYear() - NameRecord.START);

                            System.out.println("\n" + mName2 + "'s Highest Rank was: " + bestRank1 + "\n");
                        }
                    }
                    if (!(findName5)) {
                        System.out.println("Could not find: " + mName2);
                    }

                    break;

                // returns user with a specific rank for a year for a man
                case 6: // prompt user for a name and year
                    System.out.println("Please enter a name and year:");
                    String mName3 = scnr.next();
                    int fYear2 = scnr.nextInt();
                    boolean findName6 = false;

                    for (int i = 0; i < mList.size(); i++) {
                        if (mList.get(i).getName().equalsIgnoreCase(mName3)) {
                            findName6 = true;
                            System.out.println("\n" + mName3 + "'s Highest Rank for " + fYear2 + " was: " + mList.get(i).getRank(fYear2 - NameRecord.START) + "\n");
                        }
                    }

                    if (!(findName6)) {
                        System.out.println("Could not find: " + mName3 + " in the year " + fYear2 + "\n");
                    }

                    break;

                // exits the program, stopping the loop
                case 7:
                    System.out.println("Thank you.");
                    System.exit(0);

                    // default case
                default:
                    System.out.println("\nInvalid. Please try again.\n");
                    break;
            }
        }
    }
}