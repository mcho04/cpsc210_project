package persistence;

import model.Province;
import model.Candidate;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Adapted from JsonSerializationDemo-Workroom
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {

    // This test case was adapted from CPSC 210 WorkRoomApp
    protected void checkProvince(String name, int population, Province province) {
        assertEquals(name, province.getName());
        assertEquals(population, province.getPopulation());
    }

    // This test case was adapted from CPSC 210 WorkRoomApp
    protected void checkCandidate(String name, String party, Candidate candidate) {
        assertEquals(name, candidate.getName());
        assertEquals(party, candidate.getParty());
    }
}
