package model;

import org.json.JSONObject;
import persistence.Writable;

// Adapted from JsonSerializationDemo-Workroom
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a province having province name and population
public class Province implements Writable {

    private String name;
    private int population;

    // REQUIRES: population >= 0
    // EFFECTS: Constructs a province with given name and population
    public Province(String name, int population) {
        this.name = name;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("population", population);

        return json;
    }
}
