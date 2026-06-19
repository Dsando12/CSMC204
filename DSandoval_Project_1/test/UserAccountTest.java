import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UserAccountTest {

    @Test
    public void testEqualsSameUsername() {
        UserAccount a1 = new UserAccount("alice", "hash1");
        UserAccount a2 = new UserAccount("alice", "hash2");

        assertEquals(a1, a2, "Accounts with same username should be equal");
    }

    @Test
    public void testEqualsDifferentUsername() {
        UserAccount a1 = new UserAccount("alice", "hash");
        UserAccount a2 = new UserAccount("bob", "hash");

        assertNotEquals(a1, a2);
    }

    @Test
    public void testToStringReturnsUsername() {
        UserAccount user = new UserAccount("charlie", "hash");
        assertEquals("charlie", user.toString());
    }

    @Test
    public void testIncorrectPasswordThrowsException() {
        UserAccount user = new UserAccount("dave", "correctHash");

        assertThrows(
                PasswordIncorrectException.class,
                () -> user.checkPassword("wrongHash")
        );
    }

    @Test
    public void testAccountLocksAfterThreeFailures() {
        UserAccount user = new UserAccount("eve", "hash");

        user.incrementFailureCount();
        user.incrementFailureCount();
        user.incrementFailureCount();

        assertTrue(user.isLocked(), "Account should be locked after 3 failures");
    }

    @Test
    public void testLockedAccountThrowsAccountLockedException() {
        UserAccount user = new UserAccount("frank", "hash");

        user.incrementFailureCount();
        user.incrementFailureCount();
        user.incrementFailureCount();

        assertThrows(
                AccountLockedException.class,
                () -> user.checkPassword("hash")
        );
    }
}
