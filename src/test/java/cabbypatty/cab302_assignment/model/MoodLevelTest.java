package cabbypatty.cab302_assignment.model;

import cabbypatty.cab302_assignment.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoodLevelTest {

    @Test
    void testOrdinalOfLevel() {
        assertEquals(0, MoodLevel.VERY_BAD.ordinal());
        assertEquals(1, MoodLevel.BAD.ordinal());
        assertEquals(2, MoodLevel.NEUTRAL.ordinal());
        assertEquals(3, MoodLevel.GOOD.ordinal());
        assertEquals(4, MoodLevel.VERY_GOOD.ordinal());
    }

//    @Test
//    void testOutOfBoundLevel() {
//        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
//            MoodLevel.fromLevel(0);
//        });
//        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
//            MoodLevel.fromLevel(6);
//        });
//    }
}