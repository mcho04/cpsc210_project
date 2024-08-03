package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Adapted from JsonSerializationDemo-Workroom
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents list of provinces
public class Provinces implements Writable {

    public static final int ELECTORAL_QUOTIENT = 111166;

    private ArrayList<Province> provinces;

    // EFFECTS: constructs empty list of provinces
    public Provinces() {
        provinces = new ArrayList<Province>();
    }

    // REQUIRES: population >= 0
    // MODIFIES: this
    // EFFECTS: Creates a new province and adds that province to provinces (list of province). Adds logEvent.
    public void addProvince(String name, int population) {
        Province province = new Province(name, population);
        provinces.add(province);
        EventLog.getInstance().logEvent(new Event("Province added"));
    }

    // MODIFIES: this
    // EFFECTS: index - 1 (to easily work with index), if that index is less than 0, returns false.
    //          If it is bigger than 0 and is less than the size of provinces,
    //          removes province in that index and returns true. Adds logEvent
    public boolean removeProvince(int index) {
        index = index - 1;
        if (index < 0 || index >= provinces.size()) {
            return false;
        } else {
            provinces.remove(index);
            EventLog.getInstance().logEvent(new Event("Province removed"));
            return true;
        }
    }

    // EFFECTS: returns total population of stored provinces
    public int getTotalPopulation() {
        int totalPopulation = 0;
        for (Province p : provinces) {
            totalPopulation = totalPopulation + p.getPopulation();
        }
        return totalPopulation;
    }

    // EFFECTS: returns total ridings of the country based on provinces' population
    public int calculateTotalRidings() {
        int totalRidings = 0;
        for (Province p : provinces) {
            totalRidings = totalRidings + (p.getPopulation() / ELECTORAL_QUOTIENT);
        }
        return totalRidings;
    }

    // EFFECTS: returns true if provinces is empty. False otherwise.
    public boolean isEmpty() {
        return provinces.isEmpty();
    }

    // EFFECTS: returns the element stored in index i of provinces
    public Province get(int i) {
        Province province = provinces.get(i);
        return province;
    }

    // EFFECTS: returns the size of provinces
    public int size() {
        int size = provinces.size();
        return size;
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("provinces", provincesToJson());
        return json;
    }

    // This method was adapted from CPSC 210 WorkRoomApp
    // EFFECTS: returns things in this provinces as a JSON array
    private JSONArray provincesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Province t : provinces) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

}
