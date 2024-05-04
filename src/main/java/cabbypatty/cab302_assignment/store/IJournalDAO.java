package cabbypatty.cab302_assignment.store;

import cabbypatty.cab302_assignment.model.Journal;

public interface IJournalDAO {
    public Journal getJournal(int id);
    public Journal createJournal(String body, Integer author_id);
    public Journal[] getJournals(Integer author_id);
    public void deleteJournal(int id);
    public void updateJournal(int id, String body);
}
