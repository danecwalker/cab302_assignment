import com.example.cab302_personal.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntryManagerTest {
    private EntryManager entryManager;

    private Entry[] entries = {
            new Entry(LocalDateTime.of(2021,1,4, 1,11,11),
                    "Title 1", "Description 1", "Content 1"),
            new Entry(LocalDateTime.of(2022,2,4, 2,22,22),
                    "Title 2,", "Description 2,", "Content 2"),
            new Entry(LocalDateTime.of(2023,3,4, 3,33,33),
                    "Title 3", "Description 3", "Content 3"),
            new Entry(LocalDateTime.of(2024,4,4, 4,44,44),
                    "Day 1", "Description 1", "Content 4"),
            new Entry(LocalDateTime.of(2024,5,5, 4,55,44),
                    "Day 2", "Night 2", "Nah"),
            new Entry(LocalDateTime.of(2024,6,5, 4,55,55),
                    "Day 3", "Night 4", "Brah")
    };
    @BeforeEach
    public void setUp() {
        entryManager = new EntryManager(new MockEntryDAO());
        entryManager.deleteAllEntry();
    }
    @Test
    public void testSearchInOneEntry() {
        entryManager.addEntry(entries[0]);
        assertEquals(1, entryManager.getAllEntries().size());
        List<Entry> entries = entryManager.searchEntries("Title");
        assertEquals(1, entries.size());
        assertEquals(this.entries[0], entries.getFirst());
    }

    @Test
    public void testSearchInMultipleEntries() {
        for (Entry entry : entries) {
            entryManager.addEntry(entry);
        }
        List<Entry> entries = entryManager.searchEntries("Description");
        assertEquals(4, entries.size());
        for (Entry entry : entries) {
            assertTrue(entry.getDescription().contains("Description"));
        }
    }

    @Test
    public void testSearchNoResults() {
        for (Entry entry : entries) {
            entryManager.addEntry(entry);
        }
        List<Entry> entries = entryManager.searchEntries("Messi");
        assertEquals(0, entries.size());
    }

    @Test
    public void testSearchEmptyQuery() {
        for (Entry entry : entries) {
            entryManager.addEntry(entry);
        }
        List<Entry> entries = entryManager.searchEntries("");
        assertEquals(6, entries.size());
    }

    @Test
    public void testSearchNullQuery() {
        for (Entry entry : entries) {
            entryManager.addEntry(entry);
        }
        List<Entry> entries = entryManager.searchEntries(null);
        assertEquals(6, entries.size());
    }

    @Test
    public void testSearchCaseInsensitive() {
        for (Entry entry : entries) {
            entryManager.addEntry(entry);
        }
        List<Entry> entries = entryManager.searchEntries("day");
        assertEquals(3, entries.size());
        assertEquals("Day 1", entries.get(0).getTitle());
        assertEquals("Day 2", entries.get(1).getTitle());
        assertEquals("Day 3", entries.get(2).getTitle());
    }

    @Test
    public void testSearchPartialQuery() {
        for (Entry entry : entries) {
            entryManager.addEntry(entry);
        }
        List<Entry> entries = entryManager.searchEntries("ah");
        assertEquals(2, entries.size());
        assertEquals("Nah", entries.get(0).getContent());
        assertEquals("Brah", entries.get(1).getContent());
    }

    @Test
    public void testSearchEmptyEntries() {
        List<Entry> entries = entryManager.searchEntries("Description");
        assertEquals(0, entries.size());
    }
    @Test
    public void testSearchByDate() {
        for (Entry entry : entries) {
            entryManager.addEntry(entry);
        }
        List<Entry> entries = entryManager.searchEntries("2024");
        assertEquals(3, entries.size());
    }
    @Test
    public void testSearchByTime() {
        for (Entry entry : entries) {
            entryManager.addEntry(entry);
        }
        List<Entry> entries = entryManager.searchEntries("55");
        assertEquals(2, entries.size());
    }
}
