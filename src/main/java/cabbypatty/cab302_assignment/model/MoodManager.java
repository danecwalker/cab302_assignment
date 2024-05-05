package cabbypatty.cab302_assignment.model;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
public class MoodManager {
    private IMoodDAO moodDAO;

    public MoodManager(IMoodDAO moodDAO) {
        this.moodDAO = moodDAO;
    }

    public List<Mood> filterMoodByDate(LocalDate date) {
        return moodDAO.getAllMoods()
                .stream()
                .filter(mood -> mood.getDateTime().toLocalDate().equals(date))
                .toList();
    }

    public List<Mood> filterMoodByMonth(Month month) {
        return moodDAO.getAllMoods()
                .stream()
                .filter(mood -> Month.from(mood.getDateTime()).equals(month))
                .toList();
    }

    public List<Mood> filterMoodByYear(Year year) {
        return moodDAO.getAllMoods()
                .stream()
                .filter(mood -> Year.from(mood.getDateTime()).equals(year))
                .toList();
    }

    public List<Mood> filterMoodByLevel(MoodLevel level) {
        return moodDAO.getAllMoods()
                .stream()
                .filter(mood -> mood.getMoodLevel().equals(level))
                .toList();
    }


    // add mood
    public void addMood(Mood mood) {
        moodDAO.addMood(mood);
    }
    // get all mood
    public List<Mood> getAllMoods() {
        return moodDAO.getAllMoods();
    }

}
