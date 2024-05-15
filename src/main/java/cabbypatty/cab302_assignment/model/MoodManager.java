package cabbypatty.cab302_assignment.model;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

/**
 * A class that manage mood inputs.
 */
public class MoodManager {
    private IMoodDAO moodDAO;

    /**
     * @param moodDAO
     */
    public MoodManager(IMoodDAO moodDAO) {
        this.moodDAO = moodDAO;
    }

    /**
     * @param date
     * @return
     */
    public List<Mood> filterMoodByDate(LocalDate date) {
        return moodDAO.getAllMoods()
                .stream()
                .filter(mood -> mood.getDateTime().toLocalDate().equals(date))
                .toList();
    }

    /**
     * @param month
     * @return
     */
    public List<Mood> filterMoodByMonth(Month month) {
        return moodDAO.getAllMoods()
                .stream()
                .filter(mood -> Month.from(mood.getDateTime()).equals(month))
                .toList();
    }

    /**
     * @param year
     * @return
     */
    public List<Mood> filterMoodByYear(Year year) {
        return moodDAO.getAllMoods()
                .stream()
                .filter(mood -> Year.from(mood.getDateTime()).equals(year))
                .toList();
    }

    /**
     * @param level
     * @return
     */
    public List<Mood> filterMoodByLevel(MoodLevel level) {
        return moodDAO.getAllMoods()
                .stream()
                .filter(mood -> mood.getMoodLevel().equals(level))
                .toList();
    }


    /**
     * @param mood
     */
    // add mood
    public void addMood(Mood mood) {
        moodDAO.addMood(mood);
    }
    // get all mood

    /**
     * @return
     */
    public List<Mood> getAllMoods() {
        return moodDAO.getAllMoods();
    }

}
