package cabbypatty.cab302_assignment.model;



public class MoodLevel {
    // Instance variable to hold the integer value of the mood level
    private final Mood mood;


    /**
     * Constructor to initialize the mood level with an integer value
     * @param mood The mood of MoodLevel
     */
    MoodLevel(Mood mood) {
        this.mood = mood;
    }

    /**
     * Returns the level of the Mood class represented by this Mood object as an int.
     * @return level The level of mood
     */
    public int getLevel() {
        return this.mood.ordinal() + 1;
    }

    /**
     * A method that provides a convenient way to convert an integer mood level to the corresponding MoodLevel enum constant, ensuring type safety and preventing invalid inputs.
     * @param level The level of mood
     * @return The mood level
     */
    public static MoodLevel fromLevel(int level) {
        return new MoodLevel(Mood.values()[level-1]);
    }

    /**
     * @return integer of selected
     */
    public String toString(){
        return switch (this.mood) {
            case VERY_BAD -> "Very bad";
            case BAD -> "Bad";
            case GOOD -> "Good";
            case VERY_GOOD -> "Very Good";
            default -> "Neutral";
        };
    }

    /**
     * @return image of the selected mood
     */
    public String getImage() {
        return switch (this.mood) {
            case VERY_BAD -> "/cabbypatty/cab302_assignment/image/very_bad.png";
            case BAD -> "/cabbypatty/cab302_assignment/image/bad.png";
            case GOOD -> "/cabbypatty/cab302_assignment/image/good.png";
            case VERY_GOOD -> "/cabbypatty/cab302_assignment/image/very_good.png";
            default -> "/cabbypatty/cab302_assignment/image/neutral.png";
        };
    }
}
