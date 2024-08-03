package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CandidatesTest {

    private Candidates testCandidates;

    @BeforeEach
    void setUp() {
        testCandidates = new Candidates();
    }

    @Test
    void testConstructor() {
        assertTrue(testCandidates.isEmpty());
        assertEquals(0, testCandidates.size());
    }

    @Test
    void testAddCandidate() {
        testCandidates.addCandidate("Justin Trudeau", "Liberal");
        assertEquals("Justin Trudeau", testCandidates.get(0).getName());
        assertEquals("Liberal", testCandidates.get(0).getParty());

        testCandidates.addCandidate("Pierre Poilievre", "Conservative");
        assertEquals("Pierre Poilievre", testCandidates.get(1).getName());
        assertEquals("Conservative", testCandidates.get(1).getParty());
    }

    @Test
    void testRemoveCandidate() {
        testCandidates.addCandidate("Justin Trudeau", "Liberal");
        testCandidates.addCandidate("Pierre Poilievre", "Conservative");

        assertTrue(testCandidates.removeCandidate(1));
        assertEquals("Pierre Poilievre", testCandidates.get(0).getName());
        assertEquals("Conservative", testCandidates.get(0).getParty());

    }

    @Test
    void testRemoveCandidateNotSuccessful() {
        testCandidates.addCandidate("Justin Trudeau", "Liberal");
        testCandidates.addCandidate("Pierre Poilievre", "Conservative");

        assertFalse(testCandidates.removeCandidate(3));
        assertFalse(testCandidates.removeCandidate(-1));
    }

    @Test
    void testGetWinnerIndexNoTie() {
        testCandidates.addCandidate("Justin Trudeau", "Liberal");
        testCandidates.addCandidate("Pierre Poilievre", "Conservative");

        testCandidates.get(0).setVotes(70000);
        testCandidates.get(1).setVotes(60000);

        assertEquals(0, testCandidates.getWinnerIndex());

        testCandidates.get(0).setVotes(79999);
        testCandidates.get(1).setVotes(80000);

        assertEquals(1, testCandidates.getWinnerIndex());

    }

    @Test
    void testGetWinnerIndexTie() {
        testCandidates.addCandidate("Justin Trudeau", "Liberal");
        testCandidates.addCandidate("Pierre Poilievre", "Conservative");

        testCandidates.get(0).setVotes(50000);
        testCandidates.get(1).setVotes(50000);

        assertEquals(-1, testCandidates.getWinnerIndex());

        testCandidates.addCandidate("Paul", "NDP");
        testCandidates.get(2).setVotes(2000);

        assertEquals(-1, testCandidates.getWinnerIndex());
    }

    @Test
    void testGetWinnerIndexNoTieOnlyOneAdded() {
        testCandidates.addCandidate("Justin Trudeau", "Liberal");

        testCandidates.get(0).setVotes(50000);

        assertEquals(0, testCandidates.getWinnerIndex());

    }

    @Test
    void testGetWinnerIndexTieButBiggerNumPresent() {
        testCandidates.addCandidate("Justin Trudeau", "Liberal");
        testCandidates.addCandidate("Pierre Poilievre", "Conservative");

        testCandidates.get(0).setVotes(99999);
        testCandidates.get(1).setVotes(99999);

        testCandidates.addCandidate("Paul", "NDP");
        testCandidates.get(2).setVotes(100000);

        assertEquals(2, testCandidates.getWinnerIndex());

    }

    @Test
    void testLargestVotesWithTie() {
        testCandidates.addCandidate("Justin Trudeau", "Liberal");
        testCandidates.addCandidate("Pierre Poilievre", "Conservative");
        testCandidates.addCandidate("Paul", "NDP");

        testCandidates.get(0).setVotes(150000);
        testCandidates.get(1).setVotes(150000);
        testCandidates.get(2).setVotes(70000);

        assertTrue(testCandidates.largestVotesWithTie(0));
        assertTrue(testCandidates.largestVotesWithTie(1));
    }



    @Test
    void testLargestVotesWithNoTie() {
        testCandidates.addCandidate("Justin Trudeau", "Liberal");
        testCandidates.addCandidate("Pierre Poilievre", "Conservative");
        testCandidates.addCandidate("Paul", "NDP");

        testCandidates.get(0).setVotes(150000);
        testCandidates.get(1).setVotes(150000);
        testCandidates.get(2).setVotes(300000);

        assertFalse(testCandidates.largestVotesWithTie(0));
    }



    @Test
    void testGetTotalVotes() {
        testCandidates.addCandidate("Justin Trudeau", "Liberal");
        testCandidates.addCandidate("Pierre Poilievre","Conservative");

        testCandidates.get(0).setVotes(70000);
        testCandidates.get(1).setVotes(60000);

        assertEquals(130000, testCandidates.getTotalVotes());

    }

    @Test
    void testClearVotes() {
        testCandidates.addCandidate("Justin Trudeau", "Liberal");
        testCandidates.addCandidate("Pierre Poilievre", "Conservative");

        testCandidates.get(0).setVotes(9845984);
        testCandidates.get(1).setVotes(4857589);

        testCandidates.clearVotes();

        assertEquals(0, testCandidates.get(0).getVotes());
        assertEquals(0, testCandidates.get(1).getVotes());


    }
}