package cabbypatty.cab302_assignment.model;

import java.util.List;

public interface IMoodDAO {
    /**
     * Adds a new mood to the database.
     * @param mood The mood to add.
     */
    public void addMood(Mood mood);
    /**
     Retrieves a mood from the database.
     @param id The id of the mood to retrieve.
     @return The mood with the given id, or null if not found.
     */
    public Mood getMood(int id);
    /**
     * Gets all moods from the database.
     * @return a list of all moods in the database
     */
    public List<Mood> getAllMoods();
}
