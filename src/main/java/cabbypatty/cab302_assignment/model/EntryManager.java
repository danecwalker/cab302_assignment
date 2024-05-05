package cabbypatty.cab302_assignment.model;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class EntryManager {
    private IEntryDAO entryDAO;

    public EntryManager(IEntryDAO entryDAO) {
        this.entryDAO = entryDAO;
    }

    public List<Entry> searchEntries(String query){
        return entryDAO.getAllEntries()
                .stream()
                .filter(entry -> isEntryMatched(entry, query))
                .toList();
    }
    private boolean isEntryMatched(Entry entry, String query) {
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
    public void addEntry(Entry entry) {
        entryDAO.addEntry(entry);
    }

    public void deleteEntry(Entry entry) {
        entryDAO.deleteEntry(entry);
    }

    public void updateEntry(Entry entry) {
        entryDAO.updateEntry(entry);
    }

    public List<Entry> getAllEntries() {
        return entryDAO.getAllEntries();
    }

    public void deleteAllEntry() {
        for (Entry entry : entryDAO.getAllEntries()) {
            entryDAO.deleteEntry(entry);
        }
    }
}
