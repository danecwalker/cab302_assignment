package cabbypatty.cab302_assignment.model;

import java.util.ArrayList;
import java.util.List;

public class MockEntryDAO implements IEntryDAO {
    public static final ArrayList<Entry> entries = new ArrayList<>();
    private int autoIncrementedId = 1;

    @Override
    public void addEntry(Entry entry) {
        entry.setId(autoIncrementedId);
        autoIncrementedId++;
        entries.add(entry);
    }

    @Override
    public void updateEntry(Entry entry) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getId() == entry.getId()) {
                entries.set(i, entry);
                break;
            }
        }
    }

    @Override
    public void deleteEntry(Entry entry) {
        entries.remove(entry);
    }

    @Override
    public Entry getEntry(int id) {
        for (Entry entry : entries) {
            if (entry.getId() == id) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public List<Entry> getAllEntries() {
        return new ArrayList<>(entries);
    }
}
