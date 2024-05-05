package cabbypatty.cab302_assignment.model;

import cabbypatty.cab302_assignment.store.IJournalDAO;

import java.util.ArrayList;
import java.util.Date;

public class MockJournalDAO implements IJournalDAO {
    private ArrayList<Journal> journals = new ArrayList<>();

    @Override
    public Journal getJournal(int id) {
        return journals.stream().filter(j -> j.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void createJournal(String body, MoodLevel mood, Integer author_id) {
        Journal journal = new Journal(journals.size() + 1, body, new Date(), author_id, mood);
        journals.add(journal);
    }

    @Override
    public Journal[] getJournals(Integer author_id) {
        return journals.stream().filter(j -> j.getAuthorID().equals(author_id)).toArray(Journal[]::new);
    }

    @Override
    public void deleteJournal(int id) {
        journals.removeIf(j -> j.getId() == id);
    }

    @Override
    public void updateJournal(int id, String body) {
        journals.stream().filter(j -> j.getId() == id).findFirst().ifPresent(journal -> journal.setBody(body));
    }
}