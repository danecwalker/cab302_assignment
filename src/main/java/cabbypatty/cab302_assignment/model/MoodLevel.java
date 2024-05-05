package cabbypatty.cab302_assignment.model;

public enum MoodLevel {
    // Define mood levels with corresponding integer values
    VERY_BAD(0),
    BAD(1),
    NEUTRAL(2),
    GOOD(3),
    VERY_GOOD(4);

    // Instance variable to hold the integer value of the mood level
    private final int level;

    // Constructor to initialize the mood level with an integer value
    MoodLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level + 1;
    }

//    public MoodLevel fromLevel(int level) {
//        this.level = MoodLevel.values()[level-1];
//        return .this.level;
////        throw new IllegalArgumentException("Invalid mood level: " + level);
//    }

    public String toString(){
        return switch (level) {
            case 0 -> "Very bad";
            case 1 -> "Bad";
            case 2 -> "Neutral";
            case 3 -> "Good";
            case 4 -> "Very Good";
            default -> "";
        };
    }
}
