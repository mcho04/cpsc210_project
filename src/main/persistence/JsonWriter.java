package persistence;

import model.Provinces;
import model.Candidates;
import org.json.JSONObject;

import java.io.*;

// Adapted from JsonSerializationDemo-Workroom
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a writer that writes JSON representation of provinces and candidates to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // This constructor was adapted from CPSC 210 WorkRoomApp
    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // MODIFIES: this
    // EFFECTS: writes JSON representation of provinces to file
    public void write(Provinces provinces) {
        JSONObject json = provinces.toJson();
        saveToFile(json.toString(TAB));
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // MODIFIES: this
    // EFFECTS: writes JSON representation of candidates to file
    public void writeCandidate(Candidates candidates) {
        JSONObject json = candidates.toJson();
        saveToFile(json.toString(TAB));
    }

}
