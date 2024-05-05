import cabbypatty.cab302_assignment.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MoodTest {
    private Mood mood;

    @BeforeEach
    void setUp() {
        mood = new Mood(LocalDateTime.now(), MoodLevel.NEUTRAL);
    }

    @Test
    void testNegativeId() {
        int negativeId = -1;
        assertThrows(IllegalArgumentException.class, () -> mood.setId(negativeId),
                "ID must be greater than zero");
    }

    @Test
    void testIdEqualsZero() {
        int zeroId = 0;
        assertThrows(IllegalArgumentException.class, () -> mood.setId(zeroId),
                "ID must be greater than zero");
    }

    @Test
    void testPositiveId() {
        int validId = 5;
        assertDoesNotThrow(() -> mood.setId(validId));
    }

    @Test
    public void testDateTimeNullable() {
        mood.setDateTime(null);
        assertNull(mood.getDateTime());
    }

    @Test
    public void testMoodLevelNotNull() {
        assertThrows(IllegalArgumentException.class, () -> mood.setMoodLevel(null),
                "MoodLevel must not null");
    }
}
