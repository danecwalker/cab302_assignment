package cabbypatty.cab302_assignment.model;

public enum MoodLevel {
    VERY_BAD(0),
    BAD(1),
    NEUTRAL(2),
    GOOD(3),
    VERY_GOOD(4);

    private final int level;

    MoodLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static MoodLevel fromLevel(int level) {
        for (MoodLevel mood : MoodLevel.values()) {
            if (mood.getLevel() == level) {
                return mood;
            }
        }
        throw new IllegalArgumentException("Invalid mood level: " + level);
    }
}