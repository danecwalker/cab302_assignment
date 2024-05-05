package cabbypatty.cab302_assignment.model;

import java.util.ArrayList;
import java.util.List;

public class MockMoodDAO implements IMoodDAO{
    public static final ArrayList<Mood> moods = new ArrayList<>();
    private int autoIncrementedId = 1;
    @Override
    public void addMood(Mood mood) {
        mood.setId(autoIncrementedId);
        autoIncrementedId++;
        moods.add(mood);
    }

    @Override
    public Mood getMood(int id) {
        for (Mood mood : moods) {
            if (mood.getId() == id) {
                return mood;
            }
        }
        return null;
    }

    @Override
    public List<Mood> getAllMoods() {
        return new ArrayList<>(moods);
    }
}
