package ui;

import model.Provinces;
import model.Candidates;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

// Adapted from JsonSerializationDemo-Workroom
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// voting application
public class VotingApplication {
    private static final String JSON_STORE_C = "./data/candidates.json";
    private static final String JSON_STORE_P = "./data/provinces.json";
    private Scanner input;
    private Provinces provinces;
    private Candidates candidates;
    private JsonWriter jsonWriterP;
    private JsonReader jsonReaderP;

    private JsonWriter jsonWriterC;
    private JsonReader jsonReaderC;

    // EFFECTS: runs the voting application
    public VotingApplication() {
        runVotingApplication();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runVotingApplication() {
        boolean keepGoing = true;
        int command;

        init();

        while (keepGoing) {
            displayMenu();
            command = Integer.parseInt(input.nextLine());

            if (command == 4) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Thank you for using Paul's voting application!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(int command) {
        if (command == 1) {
            doFirstPastThePost();
        } else if (command == 2) {
            doSaveProvincesAndCandidates();
        } else if (command == 3) {
            doLoadProvinceAndCandidate();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: conducts a first past the post voting application (who gets the most votes, wins)
    private void doFirstPastThePost() {
        boolean keepGoing = true;

        while (keepGoing) {
            displayFptpMenu();
            int command = Integer.parseInt(input.nextLine());

            if (command == 4) {
                keepGoing = false;
            } else if (command == 1) {
                doAddOrRemoveProvince();
            } else if (command == 2) {
                doAddOrRemoveCandidate();
            } else if (command == 3) {
                doVote();
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: User can add or remove province
    private void doAddOrRemoveProvince() {
        boolean keepGoing = true;

        while (keepGoing) {
            displayAddOrRemoveProvince();
            int command = Integer.parseInt(input.nextLine());

            if (command == 3) {
                keepGoing = false;
            } else {
                processProvinceCommand(command);
            }
        }
        if (!provinces.isEmpty()) {
            System.out.println("Added provinces: ");
            printProvinces();
            System.out.println(provinces.calculateTotalRidings() + " seats are present in House of Common");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command for add or remove province
    private void processProvinceCommand(int command) {
        if (command == 1) {
            System.out.println("Enter the name of the province: ");
            String name = input.nextLine();
            System.out.println("Enter the population of the province: ");
            int population = Integer.parseInt(input.nextLine());
            provinces.addProvince(name, population);
            System.out.println("province " + name + " with population of " + population + " has been added");
        } else if (command == 2) {
            if (provinces.isEmpty()) {
                System.out.println("Remove after adding at least one province");
            } else {
                printProvinces();
                System.out.println("Which province do you want to remove? Please enter a numerical value");
                int provinceNumber = Integer.parseInt(input.nextLine());
                provinces.removeProvince(provinceNumber);
            }
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: User can add or remove candidate
    private void doAddOrRemoveCandidate() {
        boolean keepGoing = true;

        while (keepGoing) {
            displayAddOrRemoveCandidate();
            int command = Integer.parseInt(input.nextLine());
            if (command == 3) {
                keepGoing = false;
            } else {
                processCandidateCommand(command);
            }
        }
        if (!candidates.isEmpty()) {
            System.out.println("Added candidates: ");
            printCandidates();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command for add or remove candidate
    private void processCandidateCommand(int command) {
        if (command == 1) {
            System.out.println("Enter the name of the candidate you wish to add: ");
            String name = input.nextLine();
            System.out.println("Enter the party of the candidate: ");
            String party = input.nextLine();
            candidates.addCandidate(name, party);
            System.out.println("Candidate " + name + " (" + party + " party) has been added");
        } else if (command == 2) {
            if (candidates.isEmpty()) {
                System.out.println("Remove after adding at least one candidate");
            } else {
                printCandidates();
                System.out.println("which candidate do you want to remove? Please enter a numerical value");
                int candidateNumber = Integer.parseInt(input.nextLine());
                candidates.removeCandidate(candidateNumber);
            }
        } else {
            System.out.println("Selection not valid");
        }
    }

    // EFFECTS: conducts a vote. If neither province nor candidate has been added, fails to conduct
    private void doVote() {
        int totalPop = provinces.getTotalPopulation();
        if (provinces.isEmpty() || candidates.isEmpty()) {
            System.out.println("Make sure you've added at least one province or one candidate");
        } else {
            printCandidates();
            for (int i = 0; i < candidates.size(); i++) {
                System.out.print("\nHow many people would vote for " + candidates.get(i).getParty() + " party");
                System.out.println("(" + candidates.get(i).getName() + ")?\n Ballots remaining: " + totalPop);
                int votes = validVotes(totalPop);
                totalPop = totalPop - votes;
                candidates.get(i).setVotes(votes);
            }
            int winnerIndex = candidates.getWinnerIndex();
            if (winnerIndex == -1) {
                System.out.println("It is a tie, please try again");
            } else {
                System.out.println("\n" + candidates.get(winnerIndex).getParty() + " party got the most votes!");
                System.out.println(candidates.get(winnerIndex).getName() + " is the new prime minister!");
                getStatistics();
            }
        }
        candidates.clearVotes();
    }

    // REQUIRES: totalPop >= 0
    // EFFECTS: Helper method for doVote(). Checks if user input exceeds remaining total population.
    //          If user input is invalid, asks user to type valid input.
    private int validVotes(int totalPop) {
        boolean keepGoing = true;
        int votes = 0;
        while (keepGoing) {
            votes = Integer.parseInt(input.nextLine());

            if (votes > totalPop) {
                System.out.println("Enter votes within the remaining ballots");
            } else {
                keepGoing = false;
            }
        }
        return votes;
    }

    // EFFECTS: prints statistical result of the vote
    private void getStatistics() {
        double noVotes = provinces.getTotalPopulation() - candidates.getTotalVotes();
        System.out.println("\nStatistical distribution: ");
        for (int i = 0; i < candidates.size(); i++) {
            String party = candidates.get(i).getParty();
            double percent = candidates.get(i).getStatistics(provinces.getTotalPopulation());
            System.out.println(party + " " + percentRoundUp(percent) + "%");
        }
        if (noVotes != 0) {
            double noneVotersPercent = (noVotes / provinces.getTotalPopulation()) * 100;
            System.out.println("non-voters " + percentRoundUp(noneVotersPercent) + "%");
        }
        getHouseOfCommonSeats();
    }

    // REQUIRES: percent >= 0.0
    // EFFECTS: Rounds up percent into three sig figs
    private double percentRoundUp(double percent) {
        BigDecimal bd = new BigDecimal(percent);
        bd = bd.round(new MathContext(3));
        double rounded = bd.doubleValue();

        return rounded;
    }

    // EFFECTS: prints seats(ridings) earned per candidate
    private void getHouseOfCommonSeats() {
        System.out.println("\nHouse of commons seats distribution: ");
        for (int i = 0; i < candidates.size(); i++) {
            String party = candidates.get(i).getParty();
            String name = candidates.get(i).getName();
            int seats = candidates.get(i).getSeats();

            System.out.println(party + " party (" + name + ") earned " + seats + " seats");
        }
    }

        // EFFECTS: prints existing provinces
    private void printProvinces() {
        String initialName = provinces.get(0).getName();
        int initialPop = provinces.get(0).getPopulation();
        String printProvinces = "\n1. " + initialName + " (" + initialPop + " people)";
        for (int i = 1; i < provinces.size(); i++) {
            String name = provinces.get(i).getName();
            int pop = provinces.get(i).getPopulation();
            printProvinces = printProvinces + "\n" + (i + 1) + ". " + name + " (" + pop + " people)";
        }
        System.out.println(printProvinces);
    }

    // EFFECTS: prints existing candidates
    private void printCandidates() {
        String initialName = candidates.get(0).getName();
        String initialParty = candidates.get(0).getParty();
        String printCandidates = "\n1. " + initialName + " (" + initialParty + " party)";
        for (int i = 1; i < candidates.size(); i++) {
            String name = candidates.get(i).getName();
            String party = candidates.get(i).getParty();
            printCandidates = printCandidates + "\n" + (i + 1) + ". " + name + " (" + party + " party)";
        }
        System.out.println(printCandidates);
    }



    // EFFECTS: displays menu of options for adding or removing candidate
    private void displayAddOrRemoveCandidate() {
        System.out.println("\nselect from: ");
        System.out.println("\t1. add candidate");
        System.out.println("\t2. remove candidate");
        System.out.println("\t3. quit");
    }

    // EFFECTS: displays menu of options for adding or removing province
    private static void displayAddOrRemoveProvince() {
        System.out.println("\nselect from: ");
        System.out.println("\t1. add province");
        System.out.println("\t2. remove province");
        System.out.println("\t3. quit");
    }

    // EFFECTS: displays menu of options for first past the post
    private void displayFptpMenu() {
        System.out.println("\nSelect from: ");
        System.out.println("\t1 -> add/remove province");
        System.out.println("\t2 -> add/remove candidate");
        System.out.println("\t3 -> vote");
        System.out.println("\t4 -> quit");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nHello welcome to Paul's voting system, how can I help you today?");
        System.out.println("\t1. First Past the Post");
        System.out.println("\t2. Save provinces/candidates");
        System.out.println("\t3. load provinces/candidates");
        System.out.println("\t4. quit");
    }

    // MODIFIES: this
    // EFFECTS: initializes provinces and candidates
    private void init() {
        input = new Scanner(System.in);
        provinces = new Provinces();
        candidates = new Candidates();
        jsonWriterP = new JsonWriter(JSON_STORE_P);
        jsonReaderP = new JsonReader(JSON_STORE_P);

        jsonWriterC = new JsonWriter(JSON_STORE_C);
        jsonReaderC = new JsonReader(JSON_STORE_C);
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // EFFECTS: saves the provinces and candidates into seperate file
    private void doSaveProvincesAndCandidates() {
        try {
            jsonWriterP.open();
            jsonWriterP.write(provinces);
            jsonWriterP.close();
            System.out.print("Saved ");
            printProvinces();
            System.out.println(" to " + JSON_STORE_P);
            System.out.println();

            jsonWriterC.open();
            jsonWriterC.writeCandidate(candidates);
            jsonWriterC.close();
            System.out.print("Saved ");
            printCandidates();
            System.out.println(" to " + JSON_STORE_C);

        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_C);
        }
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // MODIFIES: this
    // EFFECTS: loads provinces and candidates from seperate file
    private void doLoadProvinceAndCandidate() {
        try {
            provinces = jsonReaderP.read();
            System.out.print("Loaded ");
            printProvinces();
            System.out.println(" from " + JSON_STORE_P);
            System.out.println();

            candidates = jsonReaderC.readCandidate();
            System.out.print("Loaded");
            printCandidates();
            System.out.println(" from " + JSON_STORE_C);

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_C);
        }
    }
}
