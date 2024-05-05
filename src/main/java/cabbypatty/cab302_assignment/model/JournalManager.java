package cabbypatty.cab302_assignment.model;
import cabbypatty.cab302_assignment.store.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class JournalManager {
    private IJournalDAO journalDAO;

    public JournalManager(IJournalDAO journalDAO) {
        this.journalDAO = journalDAO;
    }

    public List<Journal> searchEntries(String query){
        return journalDAO.getAllJournals()
                .stream()
                .filter(journal -> isEntryMatched(journal, query))
                .toList();
    }
    private boolean isEntryMatched(Journal entry, String query) {
        if (query == null || query.trim().isEmpty()) return true;
        query = query.toLowerCase().trim();

        // A searchable string
        String dateString = entry.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String timeString = entry.getDate().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String searchableString = dateString + " " + timeString + " "
                + entry.getTitle() + " "
                + entry.getDescription() + " "
                + entry.getContent();

        return searchableString.toLowerCase().contains(query);
    }
    public void addJournal(Journal journal) {
        journalDAO.addJournal(journal);
    }

    public void deleteJournal(Journal journal) {
        journalDAO.deleteJournal(journal);
    }

    public void updateJournal(Journal journal) {
        journalDAO.updateJournal(journal);
    }
}
