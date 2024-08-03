package persistence;


import model.Candidate;
import model.Candidates;
import model.Province;
import model.Provinces;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Adapted from JsonSerializationDemo-Workroom
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonWriterTest extends JsonTest {

    // This test case was adapted from CPSC 210 WorkRoomApp
    @Test
    void testWriterInvalidFileProvince() {
        try {
            Provinces provinces = new Provinces();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    // This test case was adapted from CPSC 210 WorkRoomApp
    @Test
    void testWriterEmptyProvince() {
        try {
            Provinces provinces = new Provinces();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyProvinces.json");
            writer.open();
            writer.write(provinces);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyProvinces.json");
            provinces = reader.read();
            assertEquals(0, provinces.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // This test case was adapted from CPSC 210 WorkRoomApp
    @Test
    void testWriterGeneralProvinces() {
        try {
            Provinces provinces = new Provinces();
            provinces.addProvince("BC", 350000);
            provinces.addProvince("Ontario", 888888);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralProvinces.json");
            writer.open();
            writer.write(provinces);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralProvinces.json");
            provinces = reader.read();

            List<Province> listOfProvince = new ArrayList<>();
            for (int i = 0; i < provinces.size(); i++) {
                listOfProvince.add(provinces.get(i));
            }

            assertEquals(2, listOfProvince.size());
            checkProvince("BC", 350000, listOfProvince.get(0));
            checkProvince("Ontario", 888888, listOfProvince.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // This test case was adapted from CPSC 210 WorkRoomApp
    @Test
    void testWriterInvalidFileCandidate() {
        try {
            Candidates candidates = new Candidates();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    // This test case was adapted from CPSC 210 WorkRoomApp
    @Test
    void testWriterEmptyCandidate() {
        try {
            Candidates candidates = new Candidates();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCandidates.json");
            writer.open();
            writer.writeCandidate(candidates);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCandidates.json");
            candidates = reader.readCandidate();
            assertEquals(0, candidates.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // This test case was adapted from CPSC 210 WorkRoomApp
    @Test
    void testWriterGeneralWorkroom() {
        try {
            Candidates candidates = new Candidates();
            candidates.addCandidate("Paul", "Conservative");
            candidates.addCandidate("Matina", "Liberal");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCandidates.json");
            writer.open();
            writer.writeCandidate(candidates);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCandidates.json");
            candidates = reader.readCandidate();

            List<Candidate> listOfCandidate = new ArrayList<>();
            for (int i = 0; i < candidates.size(); i++) {
                listOfCandidate.add(candidates.get(i));
            }

            assertEquals(2, listOfCandidate.size());
            checkCandidate("Paul", "Conservative", listOfCandidate.get(0));
            checkCandidate("Matina", "Liberal", listOfCandidate.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}