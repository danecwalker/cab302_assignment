import com.example.cab302_personal.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EntryTest {
    private Entry entry;


    @BeforeEach
    void setUp() {
        entry = new Entry(LocalDateTime.now(), "Test Title", "Test Description",
                "Test Content");
    }

    @Test
    void testConstructor() {
        assertEquals(1, entry.getId());
        assertNotNull(entry.getDate());
        assertEquals("Test Title", entry.getTitle());
        assertEquals("Test Description", entry.getDescription());
        assertEquals("Test Content", entry.getContent());
    }

    @Test
    void testNegativeId() {
        int negativeId = -1;
        assertThrows(IllegalArgumentException.class, () -> entry.setId(negativeId),
                "ID must be greater than zero");
    }

    @Test
    void testIdEqualsZero() {
        int zeroId = 0;
        assertThrows(IllegalArgumentException.class, () -> entry.setId(zeroId),
                "ID must be greater than zero");
    }

    @Test
    void testPositiveId() {
        int validId = 5;
        assertDoesNotThrow(() -> entry.setId(validId),
                "Valid ID");
    }

    @Test
    void testNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> entry.setTitle(null), "Title cannot be null");
    }

    @Test
    void testEmptyTitle() {
        assertThrows(IllegalArgumentException.class, () -> entry.setTitle(""), "Title cannot be empty");
    }

    @Test
    void testBlankTitle() {
        assertThrows(IllegalArgumentException.class, () -> entry.setTitle("  "), "Title cannot be blank");
    }

    @Test
    void testNullDescription() {
        assertThrows(IllegalArgumentException.class, () -> entry.setDescription(null),
                "Description cannot be null");
    }

    @Test
    void testEmptyDescription() {
        assertThrows(IllegalArgumentException.class, () -> entry.setDescription(""),
                "Description cannot be empty");
    }

    @Test
    void testBlankDescription() {
        assertThrows(IllegalArgumentException.class, () -> entry.setDescription("  "),
                "Description cannot be blank");
    }

    @Test
    void testNullContent() {
        assertThrows(IllegalArgumentException.class, () -> entry.setContent(null),
                "Content cannot be null");
    }

    @Test
    void testEmptyContent() {
        assertThrows(IllegalArgumentException.class, () -> entry.setContent(""),
                "Content cannot be empty");
    }

    @Test
    void testBlankContent() {
        assertThrows(IllegalArgumentException.class, () -> entry.setContent(" "),
                "Content cannot be blank");
    }

}

