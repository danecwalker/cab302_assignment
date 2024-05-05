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
        this.user = new User(1, "john.doe@example.com", "John Paul", "Password#1234",
                new Date(103, Calendar.JUNE, 1), "Male");
    }

    @Test
    public void testGetName() {
        assertEquals("John Paul", this.user.getName());
    }

    @Test
    public void testSetName() {
        this.user.setName("Jane Paul");
        assertEquals("Jane Paul", this.user.getName());
    }

    @Test
    public void testGetDob() {
        assertEquals(new Date(103, Calendar.JUNE, 1), this.user.getDob());
    }

    @Test
    public void testSetValidDob() {
        this.user.setDob(new Date(102, Calendar.MAY, 5));
        assertEquals(new Date(102, Calendar.MAY, 5), this.user.getDob());
    }
    @Test
    public void testSetInValidDob() {
        // Under 13
        assertThrows(IllegalArgumentException.class, () -> this.user.setDob(new Date(119, Calendar.JUNE, 2)));
    }
    @Test
    public void testGetEmail() {
        assertEquals("john.doe@example.com", this.user.getEmail());
    }

    @Test
    public void testSetValidEmail() {
        this.user.setEmail("logan@example.com");
        assertEquals("logan@example.com", this.user.getEmail());
    }

    @Test
    public void testSetInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> this.user.setEmail("jane.smithexample.com"));
        assertThrows(IllegalArgumentException.class, () -> this.user.setEmail("jane.smith@exam@ple"));
        assertThrows(IllegalArgumentException.class, () -> this.user.setEmail("jane"));
        assertThrows(IllegalArgumentException.class, () -> this.user.setEmail("123@exam@ple.com"));
        assertThrows(IllegalArgumentException.class, () -> this.user.setEmail("jane.smith@exam@@@@"));
    }

    @Test
    public void testGetGender() {
        assertEquals("Male", this.user.getGender());
    }

    @Test
    public void testSetValidGender() {
        assertDoesNotThrow(() -> this.user.setGender("Male"));
        assertEquals("Male", this.user.getGender());

        assertDoesNotThrow(() -> this.user.setGender("Female"));
        assertEquals("Female", this.user.getGender());

        assertDoesNotThrow(() -> this.user.setGender("Others"));
        assertEquals("Others", this.user.getGender());
    }

    @Test
    public void testSetInValidGender() {
        assertThrows(IllegalArgumentException.class, () -> this.user.setGender("Malee"));
        assertThrows(IllegalArgumentException.class, () -> this.user.setGender("Femalee"));
        assertThrows(IllegalArgumentException.class, () -> this.user.setGender("Other"));
    }

    @Test
    public void testGetPassword() {
        assertEquals("Password#1234", this.user.getPassword());
    }

    @Test
    public void testSetValidPassword() {
        this.user.setPassword("NewPassword#5678");
        assertEquals("NewPassword#5678", this.user.getPassword());
    }

    @Test
    public void testSetInValidPassword() {
        assertThrows(IllegalArgumentException.class, () -> this.user.setPassword("short"));
        assertThrows(IllegalArgumentException.class, () -> this.user.setPassword("nocapitalletter"));
        assertThrows(IllegalArgumentException.class, () -> this.user.setPassword("ALLLOWERCASE"));
        assertThrows(IllegalArgumentException.class, () -> this.user.setPassword("NoNumberOrSymbol"));
    }
}
