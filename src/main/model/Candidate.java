package model;

import org.json.JSONObject;
import persistence.Writable;

// Adapted from JsonSerializationDemo-Workroom
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a candidate with name, age, party, and received votes
public class Candidate implements Writable {

    public static final int ELECTORAL_QUOTIENT = 111166;

    private String name;
    private String party;
    private int votes;

    // REQUIRES: age > 0
    // EFFECTS: Constructs a candidate with given name, age, and party
    public Candidate(String name, String party) {
        this.name = name;
        this.party = party;
        this.votes = 0;
    }


    // REQUIRES: totalPopulation >= 0
    // EFFECTS: returns percentage of votes received out of whole (100%)
    public double getStatistics(int totalPopulation) {
        double votesDouble = this.votes;

        return (votesDouble / totalPopulation) * 100;
    }

    // EFFECTS: returns seats(ridings) a candidate earned based on their received votes
    public int getSeats() {
        return (this.votes / ELECTORAL_QUOTIENT);
    }

    public String getName() {
        return name;
    }


    public String getParty() {
        return party;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("party", party);
        json.put("votes", votes);
        return json;
    }
}
