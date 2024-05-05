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
        MoodLevel level = new MoodLevel(Mood.GOOD);
        this.journal = new Journal(1, "Test Content", new Date(103, Calendar.JUNE, 1), 1, level);
    }

    @Test
    void getId() {
        assertEquals(1, this.journal.getId());
    }

    @Test
    void getTitle() {
        assertEquals("01 June 2003 - 12:00AM - Mood: Good", this.journal.getTitle());
    }

    @Test
    void getBody() {
        assertEquals("Test Content", this.journal.getBody());
    }

    @Test
    void setBody() {
        assertDoesNotThrow(() -> this.journal.setBody("Updated Content"));
        assertEquals("Updated Content", this.journal.getBody());
    }

    @Test
    void setBody_Null() {
        assertThrows(IllegalArgumentException.class, () -> this.journal.setBody(null));
    }

    @Test
    void setBody_Empty() {
        assertThrows(IllegalArgumentException.class, () -> this.journal.setBody(""));
    }

    @Test
    void getDate() {
        assertEquals(new Date(103, Calendar.JUNE, 1), this.journal.getDate());
    }

    @Test
    void getAuthorID() {
        assertEquals(1, this.journal.getAuthorID());
    }

    @Test
    void getMood() {
        assertEquals(new MoodLevel(Mood.GOOD).toString(), this.journal.getMood().toString());
    }

    @Test
    void setMood() {
        assertDoesNotThrow(() -> this.journal.setMood(new MoodLevel(Mood.BAD)));
        assertEquals(new MoodLevel(Mood.BAD).toString(), this.journal.getMood().toString());
    }

    @Test
    void setMood_Null() {
        assertThrows(IllegalArgumentException.class, () -> this.journal.setMood(null));
    }
}