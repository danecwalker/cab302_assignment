package cabbypatty.cab302_assignment.model;



public class MoodLevel {
    // Instance variable to hold the integer value of the mood level
    private final Mood mood;

    // Constructor to initialize the mood level with an integer value
    MoodLevel(Mood mood) {
        this.mood = mood;
    }

    public int getLevel() {
        return this.mood.ordinal() + 1;
    }

    public static MoodLevel fromLevel(int level) {
        return new MoodLevel(Mood.values()[level-1]);
    }

    public String toString(){
        return switch (this.mood) {
            case VERY_BAD -> "Very bad";
            case BAD -> "Bad";
            case GOOD -> "Good";
            case VERY_GOOD -> "Very Good";
            default -> "Neutral";
        };
    }
}
