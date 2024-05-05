package cabbypatty.cab302_assignment.model;

import cabbypatty.cab302_assignment.store.IJournalDAO;

import java.util.ArrayList;
import java.util.Date;

public class MockJournalDAO implements IJournalDAO {
    private ArrayList<Journal> journals = new ArrayList<>();

    @Override
    public Journal getJournal(int id) {
        return this.journals.stream().filter(j -> j.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void createJournal(String body, MoodLevel mood, Integer author_id) {
        Journal journal = new Journal(this.journals.size() + 1, body, new Date(), author_id, mood);
        this.journals.add(journal);
    }

    @Override
    public Journal[] getJournals(Integer author_id) {
        return this.journals.stream().filter(j -> j.getAuthorID().equals(author_id)).toArray(Journal[]::new);
    }

    @Override
    public void deleteJournal(int id) {
        this.journals.removeIf(j -> j.getId() == id);
    }

    @Override
    public void updateJournal(int id, String body) {
        this.journals.stream().filter(j -> j.getId() == id).findFirst().ifPresent(journal -> journal.setBody(body));
    }
}