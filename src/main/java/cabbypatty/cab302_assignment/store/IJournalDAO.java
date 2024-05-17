package cabbypatty.cab302_assignment.store;

import cabbypatty.cab302_assignment.model.Journal;
import cabbypatty.cab302_assignment.model.MoodLevel;

/**
 * Interface for handling journal-related data access operations.
 */
public interface IJournalDAO {

    /**
     * Retrieves a journal entry based on the journal ID.
     *
     * @param id the ID of the journal entry to retrieve
     * @return a Journal object representing the journal entry
     */
    Journal getJournal(int id);

    /**
     * Creates a new journal entry.
     *
     * @param body the content of the journal entry
     * @param mood the mood level associated with the journal entry
     * @param author_id the ID of the author creating the journal entry
     */
    void createJournal(String body, MoodLevel mood, Integer author_id);

    /**
     * Retrieves all journal entries associated with a specific author.
     *
     * @param author_id the ID of the author whose journal entries are to be retrieved
     * @return an array of Journal objects representing the journal entries
     */
    Journal[] getJournals(Integer author_id);

    /**
     * Deletes a specific journal entry based on the journal ID.
     *
     * @param id the ID of the journal entry to delete
     */
    void deleteJournal(int id);

    /**
     * Updates the content of a specific journal entry.
     *
     * @param id the ID of the journal entry to update
     * @param body the new content for the journal entry
     */
    void updateJournal(int id, String body);
}
