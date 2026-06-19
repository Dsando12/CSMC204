import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserAccessManagerTest {

    private UserAccessManager manager;

    @BeforeEach
    public void setup() {
        manager = new UserAccessManager();
    }

    // ---------- ADD USER TESTS ----------

    @Test
    public void testAddUserSuccess() throws Exception {
        manager.addUser("alice", "hash");
        assertTrue(true);
    }

    @Test
    public void testAddDuplicateUserThrowsException() throws Exception {
        manager.addUser("bob", "hash");

        assertThrows(
                DuplicateUserException.class,
                () -> manager.addUser("bob", "hash2")
        );
    }

    @Test
    public void testAddUserEmptyUsernameThrowsException() {
        assertThrows(
                InvalidCommandException.class,
                () -> manager.addUser("", "hash")
        );
    }


    @Test
    public void testVerifyAccessSuccess() throws Exception {
        manager.addUser("charlie", "hash");

        assertTrue(manager.verifyAccess("charlie", "hash"));
    }

    @Test
    public void testVerifyWrongPasswordThrowsException() throws Exception {
        manager.addUser("dave", "correct");

        assertThrows(
                PasswordIncorrectException.class,
                () -> manager.verifyAccess("dave", "wrong")
        );
    }

    @Test
    public void testVerifyNonexistentUserThrowsException() {
        assertThrows(
                UserNotFoundException.class,
                () -> manager.verifyAccess("ghost", "hash")
        );
    }

    @Test
    public void testVerifyEmptyUsernameThrowsException() {
        assertThrows(
                InvalidCommandException.class,
                () -> manager.verifyAccess("", "hash")
        );
    }


    @Test
    public void testAccountLocksAfterThreeFailedAttempts() throws Exception {
        manager.addUser("eve", "correct");

        for (int i = 0; i < 3; i++) {
            try {
                manager.verifyAccess("eve", "wrong");
            } catch (PasswordIncorrectException ignored) {}
        }

        assertThrows(
                AccountLockedException.class,
                () -> manager.verifyAccess("eve", "correct")
        );
    }


    @Test
    public void testRemoveUserSuccess() throws Exception {
        manager.addUser("frank", "hash");
        manager.removeUser("frank");

        assertThrows(
                UserNotFoundException.class,
                () -> manager.verifyAccess("frank", "hash")
        );
    }

    @Test
    public void testRemoveNonexistentUserThrowsException() {
        assertThrows(
                UserNotFoundException.class,
                () -> manager.removeUser("nobody")
        );
    }

    @Test
    public void testRemoveEmptyUsernameThrowsException() {
        assertThrows(
                InvalidCommandException.class,
                () -> manager.removeUser("")
        );
    }
}
