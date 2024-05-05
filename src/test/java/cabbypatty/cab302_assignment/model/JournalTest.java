package cabbypatty.cab302_assignment.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JournalTest {
    private Journal journal;

    @BeforeEach
    void setUp() {
        MoodLevel level = MoodLevel.GOOD;
        journal = new Journal(1, "Test Content", new Date(103, Calendar.JUNE, 1), 1, level);
    }

    @Test
    void getId() {
        assertEquals(1, journal.getId());
    }

    @Test
    void getTitle() {
        assertEquals("01 June 2003 - 12:00am - Mood: GOOD/10", journal.getTitle());
    }

    @Test
    void getBody() {
        assertEquals("Test Content", journal.getBody());
    }

    @Test
    void setBody() {
        assertDoesNotThrow(() -> journal.setBody("Updated Content"));
        assertEquals("Updated Content", journal.getBody());
    }

    @Test
    void setBody_Null() {
        assertThrows(IllegalArgumentException.class, () -> journal.setBody(null));
    }

    @Test
    void setBody_Empty() {
        assertThrows(IllegalArgumentException.class, () -> journal.setBody(""));
    }

    @Test
    void getDate() {
        assertEquals(new Date(103, Calendar.JUNE, 1), journal.getDate());
    }

    @Test
    void getAuthorID() {
        assertEquals(1, journal.getAuthorID());
    }

    @Test
    void getMood() {
        assertEquals(MoodLevel.GOOD, journal.getMood());
    }

    @Test
    void setMood() {
        assertDoesNotThrow(() -> journal.setMood(MoodLevel.BAD));
        assertEquals(MoodLevel.BAD, journal.getMood());
    }

    @Test
    void setMood_Null() {
        assertThrows(IllegalArgumentException.class, () -> journal.setMood(null));
    }
}