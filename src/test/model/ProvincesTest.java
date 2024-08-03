package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProvincesTest {

    private Provinces testProvinces;

    @BeforeEach
    void setUp() {
        testProvinces = new Provinces();
    }

    @Test
    void testConstructor() {
        assertTrue(testProvinces.isEmpty());
        assertEquals(0, testProvinces.size());
    }

    @Test
    void testAddProvince() {
        testProvinces.addProvince("BC", 4573321);
        assertEquals("BC", testProvinces.get(0).getName());
        assertEquals(4573321, testProvinces.get(0).getPopulation());
    }

    @Test
    void testRemoveProvince() {
        testProvinces.addProvince("BC", 4573321);
        testProvinces.addProvince("Ontario", 13372996);

        assertTrue(testProvinces.removeProvince(1));

        assertEquals("Ontario", testProvinces.get(0).getName());
        assertEquals(13372996, testProvinces.get(0).getPopulation());
    }

    @Test
    void testRemoveProvinceNotSuccessful() {
        testProvinces.addProvince("BC", 4573321);
        testProvinces.addProvince("Ontario", 13372996);

        assertFalse(testProvinces.removeProvince(3));
        assertFalse(testProvinces.removeProvince(-1));
    }

    @Test
    void testGetTotalPopulation() {
        testProvinces.addProvince("BC", 4573321);
        testProvinces.addProvince("Ontario", 13372996);

        assertEquals(17946317, testProvinces.getTotalPopulation());

    }

    @Test
    void testCalculateTotalRidings() {
        testProvinces.addProvince("BC", 4573321);
        testProvinces.addProvince("Ontario", 13372996);

        int expectedTotalRidings = 0;
        for (int i = 0; i < testProvinces.size(); i++) {
            int pop = testProvinces.get(i).getPopulation();
            expectedTotalRidings = expectedTotalRidings + (pop / Provinces.ELECTORAL_QUOTIENT);
        }
        assertEquals(expectedTotalRidings, testProvinces.calculateTotalRidings());
    }


}
