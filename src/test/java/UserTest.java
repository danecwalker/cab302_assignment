import com.example.cab302_personal.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("john.doe@example.com","John Paul", "2023-05-01", "Male", "Password#1234");
    }

//    @Test
//    public void testGetId() {
//        user.setId(1);
//        assertEquals(1, user.getId());
//    }

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
        assertEquals("2023-05-01", user.getDob());
    }
    @Test
    public void testSetValidDob() {
        user.setDob("2000-05-05");
        assertEquals("2000-05-05", user.getDob());
    }
    @Test
    public void testSetInValidDob() {
        // Testing invalid date formats
        assertThrows(DateTimeParseException.class, () -> user.setDob("InvalidDate"));
        assertThrows(DateTimeParseException.class, () -> user.setDob("2023-15-01")); // Invalid month
        assertThrows(DateTimeParseException.class, () -> user.setDob("2023-12-32")); // Invalid day
        assertThrows(IllegalArgumentException.class, () -> user.setDob("2023-02-30")); // February with 30 days
        assertThrows(DateTimeParseException.class, () -> user.setDob("2023-2-3")); // No 0s leading

        String thirteenYearsOld = LocalDate.now().minusYears(13).plusDays(1).toString();
        assertThrows(IllegalArgumentException.class, () -> user.setDob(thirteenYearsOld),
                "User must be at least 13 years old.");
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
        System.out.println("Current password: " + user.getPassword());
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