package cabbypatty.cab302_assignment.model;

import java.time.LocalDateTime;

/**
 * A simple model class representing a mood with an id, dateTime, and moodLevel.
 */
public class Mood {
    private int id;
    private LocalDateTime dateTime;
    private MoodLevel moodLevel;


    /**
     * Constructs a new mood input data with the specified dateTime and moodLevel.
     * @param dateTime The date and time of the mood input
     * @param moodLevel The mood level and time of the mood input
     */
    public Mood(LocalDateTime dateTime, MoodLevel moodLevel) {
        this.dateTime = dateTime;
        this.moodLevel = moodLevel;
    }

    /**
     * Returns the ID of the mood class represented by this Class object as an int.
     * @return id The id of the mood
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the mood class on an object
     * @param id The id of the mood
     */
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        this.id = id;
    }

    /**
     * Returns the dateTime of the mood class represented by this Class object as a LocalDateTime.
     * @return id The dateTime of the mood
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets the dateTime of the mood class on an object
     * @param dateTime The dateTime of the mood
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns the moodLevel of the mood class represented by this Class object as a MoodLevel.
     * @return moodLevel The moodLevel of the mood
     */
    public MoodLevel getMoodLevel() {
        return moodLevel;
    }

    /**
     * Sets the moodLevel of the mood class on an object
     * @param moodLevel The moodLevel of the mood
     */
    public void setMoodLevel(MoodLevel moodLevel) {
        if (moodLevel == null) {
            throw new IllegalArgumentException("MoodLevel cannot be null");
        }
        this.moodLevel = moodLevel;
    }
}
