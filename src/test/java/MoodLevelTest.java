import cabbypatty.cab302_assignment.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoodLevelTest {

    @Test
    void testEnumValues() {
        assertEquals(0, MoodLevel.VERY_BAD.getLevel());
        assertEquals(1, MoodLevel.BAD.getLevel());
        assertEquals(2, MoodLevel.NEUTRAL.getLevel());
        assertEquals(3, MoodLevel.GOOD.getLevel());
        assertEquals(4, MoodLevel.VERY_GOOD.getLevel());
    }

    @Test
    void testOrdinalOfLevel() {
        assertEquals(0, MoodLevel.VERY_BAD.ordinal());
        assertEquals(1, MoodLevel.BAD.ordinal());
        assertEquals(2, MoodLevel.NEUTRAL.ordinal());
        assertEquals(3, MoodLevel.GOOD.ordinal());
        assertEquals(4, MoodLevel.VERY_GOOD.ordinal());
    }

    @Test
    void fromLevelShouldReturnCorrectEnumConstant() {
        assertSame(MoodLevel.VERY_BAD, MoodLevel.fromLevel(0));
        assertSame(MoodLevel.BAD, MoodLevel.fromLevel(1));
        assertSame(MoodLevel.NEUTRAL, MoodLevel.fromLevel(2));
        assertSame(MoodLevel.GOOD, MoodLevel.fromLevel(3));
        assertSame(MoodLevel.VERY_GOOD, MoodLevel.fromLevel(4));
    }

    @Test
    void fromLevelShouldThrowExceptionForInvalidLevel() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            MoodLevel.fromLevel(-1);
        });
        assertTrue(exception.getMessage().contains("Invalid mood level: -1"));

        exception = assertThrows(IllegalArgumentException.class, () -> {
            MoodLevel.fromLevel(5);
        });
        assertTrue(exception.getMessage().contains("Invalid mood level: 5"));
    }
}