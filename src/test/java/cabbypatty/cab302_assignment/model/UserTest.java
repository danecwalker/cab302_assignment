package cabbypatty.cab302_assignment.model;

import cabbypatty.cab302_assignment.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User(1, "john.doe@example.com", "John Paul", "Password#1234",
                new Date(103, Calendar.JUNE, 1), "Male");
    }

    @Test
    public void testGetName() {
        assertEquals("John Paul", user.getName());
    }

    @Test
    public void testSetName() {
        user.setName("Jane Paul");
        assertEquals("Jane Paul", user.getName());
    }

    @Test
    public void testGetDob() {
        assertEquals(new Date(103, Calendar.JUNE, 1), user.getDob());
    }

    @Test
    public void testSetValidDob() {
        user.setDob(new Date(102, Calendar.MAY, 5));
        assertEquals(new Date(102, Calendar.MAY, 5), user.getDob());
    }
    @Test
    public void testSetInValidDob() {
        // Under 13
        assertThrows(IllegalArgumentException.class, () -> user.setDob(new Date(119, Calendar.JUNE, 2)));
    }
    @Test
    public void testGetEmail() {
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    public void testSetValidEmail() {
        user.setEmail("logan@example.com");
        assertEquals("logan@example.com", user.getEmail());
    }

    @Test
    public void testSetInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> user.setEmail("jane.smithexample.com"));
        assertThrows(IllegalArgumentException.class, () -> user.setEmail("jane.smith@exam@ple"));
        assertThrows(IllegalArgumentException.class, () -> user.setEmail("jane"));
        assertThrows(IllegalArgumentException.class, () -> user.setEmail("123@exam@ple.com"));
        assertThrows(IllegalArgumentException.class, () -> user.setEmail("jane.smith@exam@@@@"));
    }

    @Test
    public void testGetGender() {
        assertEquals("Male", user.getGender());
    }

    @Test
    public void testSetValidGender() {
        assertDoesNotThrow(() -> user.setGender("Male"));
        assertEquals("Male", user.getGender());

        assertDoesNotThrow(() -> user.setGender("Female"));
        assertEquals("Female", user.getGender());

        assertDoesNotThrow(() -> user.setGender("Others"));
        assertEquals("Others", user.getGender());
    }

    @Test
    public void testSetInValidGender() {
        assertThrows(IllegalArgumentException.class, () -> user.setGender("Malee"));
        assertThrows(IllegalArgumentException.class, () -> user.setGender("Femalee"));
        assertThrows(IllegalArgumentException.class, () -> user.setGender("Other"));
    }

    @Test
    public void testGetPassword() {
        assertEquals("Password#1234", user.getPassword());
    }

    @Test
    public void testSetValidPassword() {
        user.setPassword("NewPassword#5678");
        assertEquals("NewPassword#5678", user.getPassword());
    }

    @Test
    public void testSetInValidPassword() {
        assertThrows(IllegalArgumentException.class, () -> user.setPassword("short"));
        assertThrows(IllegalArgumentException.class, () -> user.setPassword("nocapitalletter"));
        assertThrows(IllegalArgumentException.class, () -> user.setPassword("ALLLOWERCASE"));
        assertThrows(IllegalArgumentException.class, () -> user.setPassword("NoNumberOrSymbol"));
    }
}
