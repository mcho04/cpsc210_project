package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Adapted from JsonSerializationDemo-Workroom
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents list of candidates
public class Candidates implements Writable {

    private ArrayList<Candidate> candidates;

    // EFFECTS: constructors empty list of candidates
    public Candidates() {
        candidates = new ArrayList<Candidate>();
    }

    // REQUIRES: age > 0
    // MODIFIES: this
    // EFFECTS: Creates a new candidate and adds that candidate to candidates (list of candidate). Adds logEvent.
    public void addCandidate(String name, String party) {
        Candidate candidate = new Candidate(name, party);
        candidates.add(candidate);
        EventLog.getInstance().logEvent(new Event("Candidate added"));
    }

    // MODIFIES: this
    // EFFECTS: input number - 1 (to easily work with index), if that index is less than 0, returns false.
    //          If it is bigger than 0 and is less than the size of candidates,
    //          removes candidate in that index and returns true. Adds logEvent.
    public boolean removeCandidate(int index) {
        index = index - 1;
        if (index < 0 || index >= candidates.size()) {
            return false;
        } else {
            candidates.remove(index);
            EventLog.getInstance().logEvent(new Event("Candidate removed"));
            return true;
        }
    }

    // EFFECTS: returns index of candidate that has the most votes if there is a tie, returns -1
    public int getWinnerIndex() {
        int winnerIndex = 0;
        for (int i = 1; i < candidates.size(); i++) {
            int votes = candidates.get(i).getVotes();
            int winningVotes = candidates.get(winnerIndex).getVotes();
            if (votes > winningVotes) {
                winnerIndex = i;
            } else if (votes == winningVotes && largestVotesWithTie(winnerIndex)) {
                winnerIndex = -1;
                break;
            }
        }
        return winnerIndex;
    }

    // EFFECTS: helper function for getWInnerIndex(). If all the received votes are equal or smaller than
    //          the votes received at winnerIndex, returns true, otherwise returns false.
    public boolean largestVotesWithTie(int winnerIndex) {
        boolean largest = false;
        int largestVotes = candidates.get(winnerIndex).getVotes();
        for (Candidate c : candidates) {
            largest = largestVotes >= c.getVotes();
        }
        return largest;
    }

    // EFFECTS: clears all stored votes
    public void clearVotes() {
        for (Candidate c : candidates) {
            c.setVotes(0);
        }
    }

    // EFFECTS: returns total votes stored in candidates
    public int getTotalVotes() {
        int totalVotes = 0;
        for (Candidate c : candidates) {
            totalVotes += c.getVotes();
        }
        return totalVotes;
    }

    // EFFECTS: returns true if candidates is empty. False otherwise.
    public boolean isEmpty() {
        return candidates.isEmpty();
    }

    // EFFECTS: returns the element stored in index i of candidates
    public Candidate get(int i) {
        Candidate candidate = candidates.get(i);
        return candidate;
    }

    // EFFECTS: returns the size of the candidates
    public int size() {
        int size = candidates.size();
        return size;
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("candidates", candidatesToJson());
        return json;
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // EFFECTS: returns things in this candidates as a JSON array
    private JSONArray candidatesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Candidate t : candidates) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
