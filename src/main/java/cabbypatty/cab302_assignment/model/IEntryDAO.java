package cabbypatty.cab302_assignment.model;

import java.util.List;

public interface IEntryDAO {
    /**
     * Adds a new entry to the database.
     * @param entry The entry to add.
     */
    public void addEntry(Entry entry);
    /**
     * Updates an existing entry in the database.
     * @param entry The entry to update.
     */
    public void updateEntry(Entry entry);
    /**
     * Deletes an entry from the database.
     * @param entry The entry to delete.
     */
    public void deleteEntry(Entry entry);
    /**
     Retrieves an entry from the database.
     @param id The id of the entry to retrieve.
     @return The entry with the given id, or null if not found.
     */
    public Entry getEntry(int id);

    /**
     * Gets all entries from the database.
     * @return a list of all entries in the database
     */
    public List<Entry> getAllEntries();
}
