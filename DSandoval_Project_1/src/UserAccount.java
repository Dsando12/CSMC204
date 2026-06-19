public class UserAccount {

    private String username;
    private String encryptedPassword;
    private int failureCount;
    private boolean locked;

    public UserAccount(String username, String encryptedPassword) {
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.failureCount = 0;
        this.locked = false;
    }

    public String getUsername() {
        return username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public boolean isLocked() {
        return locked;
    }

    public void incrementFailureCount() {
        failureCount++;
        if (failureCount >= 3) {
            locked = true;
        }
    }

    public void resetFailureCount() {
        failureCount = 0;
    }

    public boolean checkPassword(String encryptedInput)
            throws AccountLockedException, PasswordIncorrectException {

        if (locked) {
            throw new AccountLockedException(
                    "User '" + username + "' account is locked."
            );
        }

        if (!encryptedPassword.equals(encryptedInput)) {
            incrementFailureCount();
            throw new PasswordIncorrectException("Incorrect Password");
        }

        resetFailureCount();
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserAccount)) return false;
        UserAccount other = (UserAccount) obj;
        return username.equals(other.username);
    }

    @Override
    public String toString() {
        return username;
    }
}
