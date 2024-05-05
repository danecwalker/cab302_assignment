package cabbypatty.cab302_assignment.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    private MockUserDAO userDAO;

    @BeforeEach
    void setUp() {
        userDAO = new MockUserDAO();
    }

    @Test
    void testCreateUser() {
        User user = userDAO.createUser("John Doe", "john@example.com", "Password1234@",
                new Date(2000 - 1900, Calendar.JANUARY, 1), "Male");
        assertNotNull(user);
        assertEquals("John Doe", user.getName());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("Password1234@", user.getPassword());
        assertEquals(new Date(2000 - 1900, Calendar.JANUARY, 1), user.getDob());
        assertEquals("Male", user.getGender());
    }

    @Test
    void testGetUser() {

        User newUser = userDAO.createUser("John Doe", "john12@example.com", "Password1234@",
                new Date(2000 - 1900, Calendar.JANUARY, 1), "Male");
        assertNotNull(newUser, "User should be created");

        // Attempt to retrieve the user immediately after creation
        User retrievedUser = userDAO.getUser("john12@example.com");
        assertNotNull(retrievedUser, "Retrieved user should not be null");

        // Verify details to ensure the retrieved user matches the created user
        assertEquals("John Doe", retrievedUser.getName());
        assertEquals("john12@example.com", retrievedUser.getEmail());
        assertEquals("Password1234@", retrievedUser.getPassword());
        assertEquals(new Date(2000 - 1900, Calendar.JANUARY, 1), retrievedUser.getDob());
        assertEquals("Male", retrievedUser.getGender());
    }


    @Test
    void testGetUserNotFound() {
        assertNull(userDAO.getUser("nonexistent@example.com"));
    }
}