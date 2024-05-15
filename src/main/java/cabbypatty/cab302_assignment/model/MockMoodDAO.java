package cabbypatty.cab302_assignment.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A model class which is responsible for handling the CRUD operations with the SQLite database for mood inputs.
 */
public class MockMoodDAO implements IMoodDAO{
    public static final ArrayList<Mood> moods = new ArrayList<>();
    private int autoIncrementedId = 1;

    /**
     * A method that executes a SQL query to add the moods table if it does not already exist.
     */
    @Override
    public void addMood(Mood mood) {
        mood.setId(autoIncrementedId);
        autoIncrementedId++;
        moods.add(mood);
    }

    /**
     * A method that returns a single mood based on the provided id.
     * @param id The id of the user
     * @return a single mood or null
     */
    @Override
    public Mood getMood(int id) {
        for (Mood mood : moods) {
            if (mood.getId() == id) {
                return mood;
            }
        }
        return null;
    }

    /**
     * A method that executes a SQL query to create the users table if it does not already exist.
     * @return The new array of the mood
     */
    @Override
    public List<Mood> getAllMoods() {
        return new ArrayList<>(moods);
    }
}
