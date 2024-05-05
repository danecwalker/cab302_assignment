package cabbypatty.cab302_assignment.model;

import cabbypatty.cab302_assignment.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoodLevelTest {

    @Test
    void testOrdinalOfLevel() {
        assertEquals(0, Mood.VERY_BAD.ordinal());
        assertEquals(1, Mood.BAD.ordinal());
        assertEquals(2, Mood.NEUTRAL.ordinal());
        assertEquals(3, Mood.GOOD.ordinal());
        assertEquals(4, Mood.VERY_GOOD.ordinal());
    }
}