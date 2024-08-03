package persistence;

import model.Candidates;
import model.Provinces;
import org.junit.jupiter.api.Test;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

// Adapted from JsonSerializationDemo-Workroom
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    // This test case was adapted from CPSC 210 WorkRoomApp
    @Test
    void testReaderNonExistentFileProvince() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Provinces provinces = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    // This test case was adapted from CPSC 210 WorkRoomApp
    @Test
    void testReaderEmptyProvinces() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyProvinces.json");
        try {
            Provinces provinces = reader.read();
            assertEquals(0, provinces.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // This test case was adapted from CPSC 210 WorkRoomApp
    @Test
    void testReaderGeneralProvinces() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralProvinces.json");
        try {
            Provinces provinces = reader.read();
            assertEquals(2, provinces.size());
            checkProvince("BC", 595865, provinces.get(0));
            checkProvince("Ontario", 4848449, provinces.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // This test case was adapted from CPSC 210 WorkRoomApp
    @Test
    void testReaderNonExistentFileCandidate() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Candidates candidates = reader.readCandidate();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    // This test case was adapted from CPSC 210 WorkRoomApp
    @Test
    void testReaderEmptyCandidates() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCandidates.json");
        try {
            Candidates candidates = reader.readCandidate();
            assertEquals(0, candidates.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // This test case was adapted from CPSC 210 WorkRoomApp
    @Test
    void testReaderGeneralCandidates() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCandidates.json");
        try {
            Candidates candidates = reader.readCandidate();
            assertEquals(2, candidates.size());
            checkCandidate("Daniel", "Green", candidates.get(0));
            checkCandidate("Sam", "NDP", candidates.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }




}