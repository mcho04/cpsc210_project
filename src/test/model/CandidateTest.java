package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CandidateTest {

    private Candidate testCandidate;

    @BeforeEach
    void setUp() {
        testCandidate = new Candidate("Justin Trudeau", "Liberal");
    }

    @Test
    void testConstructor() {
        assertEquals("Justin Trudeau", testCandidate.getName());
        assertEquals("Liberal",testCandidate.getParty());
    }

    @Test
    void testGetStatistics() {
        testCandidate.setVotes(50000);
        double votesDouble = testCandidate.getVotes();
        double expected = (votesDouble / 100000) * 100 ;

        assertEquals(expected, testCandidate.getStatistics(100000));
    }

    @Test
    void testGetSeats() {
        testCandidate.setVotes(7000000);
        int expected = 7000000 / Candidate.ELECTORAL_QUOTIENT;

        assertEquals(expected, testCandidate.getSeats());
    }

}
