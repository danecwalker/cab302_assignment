package cabbypatty.cab302_assignment.store;

import cabbypatty.cab302_assignment.model.Journal;
import cabbypatty.cab302_assignment.model.MoodLevel;

public interface IJournalDAO {
    public Journal getJournal(int id);
    public void createJournal(String body, MoodLevel mood, Integer author_id);
    public Journal[] getJournals(Integer author_id);
    public void deleteJournal(int id);
    public void updateJournal(int id, String body);
}
