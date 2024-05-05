package cabbypatty.cab302_assignment.model;

import java.time.LocalDateTime;

public class Mood {
    private int id;
    private LocalDateTime dateTime;
    private MoodLevel moodLevel;

    // Constructor
    public Mood(LocalDateTime dateTime, MoodLevel moodLevel) {
        this.dateTime = dateTime;
        this.moodLevel = moodLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public MoodLevel getMoodLevel() {
        return moodLevel;
    }

    public void setMoodLevel(MoodLevel moodLevel) {
        if (moodLevel == null) {
            throw new IllegalArgumentException("MoodLevel cannot be null");
        }
        this.moodLevel = moodLevel;
    }
}
