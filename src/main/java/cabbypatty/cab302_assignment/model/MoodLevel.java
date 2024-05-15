package cabbypatty.cab302_assignment.model;

public enum MoodLevel {
    VERY_BAD(0),
    BAD(1),
    NEUTRAL(2),
    GOOD(3),
    VERY_GOOD(4);

    private final int level;

    /**
     * A simple model class representing a mood with level.
     * @param level The level of mood
     */
    MoodLevel(int level) {
        this.level = level;
    }

    /**
     * Returns the level of the Mood class represented by this Mood object as an int.
     * @return level The level of mood
     */
    public int getLevel() {
        return level;
    }

    /**
     * A method that provides a convenient way to convert an integer mood level to the corresponding MoodLevel enum constant, ensuring type safety and preventing invalid inputs.
     * @param level The level of mood
     * @return The mood level
     */
    public static MoodLevel fromLevel(int level) {
        for (MoodLevel mood : MoodLevel.values()) {
            if (mood.getLevel() == level) {
                return mood;
            }
        }
        throw new IllegalArgumentException("Invalid mood level: " + level);
    }
}