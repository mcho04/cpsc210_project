package persistence;

import org.json.JSONObject;

// Adapted from JsonSerializationDemo-Workroom
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public interface Writable {

    // This method was adapted from CPSC 210 WorkRoomApp
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
