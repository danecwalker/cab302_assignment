package cabbypatty.cab302_assignment.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JournalDAOTest {
    private MockJournalDAO journalDAO;

    @BeforeEach
    void setUp() {
        this.journalDAO = new MockJournalDAO();
    }

    @Test
    void testCreateJournal() {
        this.journalDAO.createJournal("Test Body", new MoodLevel(Mood.GOOD), 1);

        assertEquals(1, this.journalDAO.getJournals(1).length);
    }

    @Test
    void testGetJournal() {
        Journal journal = new Journal(1, "Test Body", new Date(2003 - 1900, Calendar.JUNE, 1), 1,
                new MoodLevel(Mood.GOOD));
        this.journalDAO.createJournal("Test Body", new MoodLevel(Mood.GOOD), 1);

        assertEquals(journal.getId(), this.journalDAO.getJournal(1).getId());
    }

    @Test
    void testGetJournals() {
        this.journalDAO.createJournal("Test Body 1", new MoodLevel(Mood.GOOD), 1);
        this.journalDAO.createJournal("Test Body 2", new MoodLevel(Mood.BAD), 1);

        assertEquals(2, this.journalDAO.getJournals(1).length);
    }

    @Test
    void testDeleteJournal() {
        this.journalDAO.createJournal("Test Body", new MoodLevel(Mood.GOOD), 1);
        this.journalDAO.deleteJournal(1);

        assertNull(this.journalDAO.getJournal(1));
    }

    @Test
    void testUpdateJournal() {
        this.journalDAO.createJournal("Test Body", new MoodLevel(Mood.GOOD), 1);
        this.journalDAO.updateJournal(1, "Updated Body");

        assertEquals("Updated Body", this.journalDAO.getJournal(1).getBody());
    }
}