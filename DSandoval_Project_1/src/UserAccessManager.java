import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserAccessManager {

    private List<UserAccount> acc = new ArrayList<>();

    public void loadAccounts(String filename) throws FileNotFoundException {
        Utilities.readAccountFile(filename, this);
    }

    public void addUser(String username, String encryptedPassword)
            throws DuplicateUserException, InvalidCommandException {

        if (username == null || username.isEmpty()
                || encryptedPassword == null || encryptedPassword.isEmpty()) {
            throw new InvalidCommandException("ERROR Try again");
        }

        for (UserAccount ua : acc) {
            if (ua.getUsername().equals(username)) {
                throw new DuplicateUserException(
                        "User '" + username + "' account already exists."
                );
            }
        }

        acc.add(new UserAccount(username, encryptedPassword));
    }

    public void removeUser(String username)
            throws UserNotFoundException, InvalidCommandException {

        if (username == null || username.isEmpty()) {
            throw new InvalidCommandException("ERROR Try again");
        }

        Iterator<UserAccount> it = acc.iterator();
        while (it.hasNext()) {
            if (it.next().getUsername().equals(username)) {
                it.remove();
                return;
            }
        }

        throw new UserNotFoundException(
                "User '" + username + "' not found."
        );
    }

    public boolean verifyAccess(String username, String encryptedPassword)
            throws UserNotFoundException,
            AccountLockedException,
            InvalidCommandException,
            PasswordIncorrectException {

        if (username == null || username.isEmpty()) {
            throw new InvalidCommandException("ERROR Try again");
        }

        UserAccount user = null;
        for (UserAccount ua : acc) {
            if (ua.getUsername().equals(username)) {
                user = ua;
                break;
            }
        }

        if (user == null) {
            throw new UserNotFoundException(
                    "User '" + username + "' not found."
            );
        }

        user.checkPassword(encryptedPassword);
        return true;
    }


    public static void main(String[] args) {
        UserAccessManager manager = new UserAccessManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("User access manager ready.");

        while (true) {
            System.out.print("User Access Manager> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("ERROR Try again");
                continue;
            }

            String[] parts = input.split("\\s+", 2);
            String command = parts[0];

            try {
                switch (command) {

                    case "exit":
                        scanner.close();
                        return;

                    case "load":
                        if (parts.length < 2) {
                            throw new InvalidCommandException("ERROR Try again");
                        }
                        manager.loadAccounts(parts[1]);
                        break;

                    case "add":
                        if (parts.length < 2) {
                            throw new InvalidCommandException("ERROR Try again");
                        }
                        System.out.print("Enter password: ");
                        String addPass = scanner.nextLine();
                        manager.addUser(
                                parts[1],
                                Utilities.encryptPassword(addPass)
                        );
                        break;

                    case "remove":
                        if (parts.length < 2) {
                            throw new InvalidCommandException("ERROR Try again");
                        }
                        manager.removeUser(parts[1]);
                        break;

                    case "verify":
                        if (parts.length < 2) {
                            throw new InvalidCommandException("ERROR Try again");
                        }
                        System.out.print("Enter password: ");
                        String verifyPass = scanner.nextLine();
                        manager.verifyAccess(
                                parts[1],
                                Utilities.encryptPassword(verifyPass)
                        );
                        System.out.println("Access verified");
                        break;

                    default:
                        throw new InvalidCommandException("ERROR Try again");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
