package persistence;

import model.Candidates;
import model.Provinces;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Adapted from JsonSerializationDemo-Workroom
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads provinces and candidates from JSON data stored in file
public class JsonReader {
    private String source;

    // This constructor was adapted from CPSC 210 WorkRoomApp
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // EFFECTS: reads provinces from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Provinces read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseProvinces(jsonObject);
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // EFFECTS: parses provinces from JSON object and returns it
    private Provinces parseProvinces(JSONObject jsonObject) {
        Provinces provinces = new Provinces();
        addProvinces(provinces, jsonObject);
        return provinces;
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // MODIFIES: provinces
    // EFFECTS: parses thingies from JSON object and adds them to provinces
    private void addProvinces(Provinces provinces, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("provinces");
        for (Object json : jsonArray) {
            JSONObject nextProvince = (JSONObject) json;
            addProvince(provinces, nextProvince);
        }
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // MODIFIES: provinces
    // EFFECTS: parses thingy from JSON object and adds it to provinces
    private void addProvince(Provinces provinces, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int population = jsonObject.getInt("population");
        provinces.addProvince(name, population);
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // EFFECTS: reads candidates from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Candidates readCandidate() throws IOException {
        String jsonData = readFileCandidate(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCandidates(jsonObject);
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // EFFECTS: reads source file as string and returns it
    private String readFileCandidate(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // EFFECTS: parses candidates from JSON object and returns it
    private Candidates parseCandidates(JSONObject jsonObject) {
        Candidates candidates = new Candidates();
        addCandidates(candidates, jsonObject);
        return candidates;
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // MODIFIES: candidates
    // EFFECTS: parses thingies from JSON object and adds them to candidates
    private void addCandidates(Candidates candidates, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("candidates");
        for (Object json : jsonArray) {
            JSONObject nextCandidate = (JSONObject) json;
            addCandidate(candidates, nextCandidate);
        }
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // MODIFIES: candidates
    // EFFECTS: parses thingy from JSON object and adds it to candidates
    private void addCandidate(Candidates candidates, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String party = jsonObject.getString("party");
        candidates.addCandidate(name, party);
    }

}