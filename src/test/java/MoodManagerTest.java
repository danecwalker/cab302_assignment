//import com.example.cab302_personal.model.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.*;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//public class MoodManagerTest {
//    private MoodManager moodManager;
//    private Mood[] moods = {
//            new Mood(LocalDateTime.of(2021, 1, 4, 1, 11, 11),
//                    MoodLevel.NEUTRAL),
//            new Mood(LocalDateTime.of(2022, 1, 4, 2, 2, 0),
//                    MoodLevel.GOOD),
//            new Mood(LocalDateTime.of(2023, 1, 4, 3, 3, 0),
//                    MoodLevel.VERY_GOOD),
//            new Mood(LocalDateTime.of(2024, 1, 4, 4, 4, 0),
//                    MoodLevel.VERY_BAD),
//            new Mood(LocalDateTime.of(2024, 1, 4, 6, 6, 0),
//                    MoodLevel.GOOD),
//            new Mood(LocalDateTime.of(2024, 6, 5, 7, 55, 55),
//                    MoodLevel.VERY_GOOD)
//    };
//
//    @BeforeEach
//    public void setUp() {
//        moodManager = new MoodManager(new MockMoodDAO());
////        moodManager.deleteAllMoods();
//    }
//    @Test
//    public void testFilterMoodByDate() {
//        for (Mood mood : moods) {
//            moodManager.addMood(mood);
//        }
//        Mood mood;
//        List<Mood> filteredMoods = moodManager.filterMoodByDate(LocalDate.of(12,2,4)
//                .atStartOfDay().toLocalDate());
//        assertEquals(5, filteredMoods.size(), "Should filter five moods on 2024-01-04");
//    }
//
//    @Test
//    public void testFilterMoodByMonth() {
//        Month month = Month.JANUARY;
//        List<Mood> filteredMoods = moodManager.filterMoodByMonth(month);
//        assertEquals(5, filteredMoods.size(), "Should filter five moods in January across all years");
//    }
//
//    @Test
//    public void testFilterMoodByYear() {
//        Year year = Year.of(2024);
//        List<Mood> filteredMoods = moodManager.filterMoodByYear(year);
//        assertEquals(3, filteredMoods.size(), "Should filter three moods from 2024");
//    }
//
//    @Test
//    public void testFilterMoodByLevel() {
//        MoodLevel level = MoodLevel.GOOD;
//        List<Mood> filteredMoods = moodManager.filterMoodByLevel(level);
//        assertEquals(2, filteredMoods.size(), "Should filter two moods with GOOD level");
//    }
//}
