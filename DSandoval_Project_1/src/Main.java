import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserAccessManager manager = new UserAccessManager();

        System.out.println("User access manager ready.");

        while (true) {
            System.out.print("User Access Manager> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            String[] tokens = input.split("\\s+", 2);
            String command = tokens[0];
            String argument = tokens.length > 1 ? tokens[1].trim() : "";

            try {
                switch (command) {
                    case "add":
                        if (argument.isEmpty()) throw new InvalidCommandException("Username can't be empty.");
                        System.out.print("Enter password: ");
                        String addPassword = scanner.nextLine();
                        if (addPassword.isEmpty()) throw new InvalidCommandException("Password can't be empty.");
                        manager.addUser(argument, Utilities.encryptPassword(addPassword));
                        System.out.println("User '" + argument + "' has been added successfully.");
                        break;

                    case "remove":
                        if (argument.isEmpty()) throw new InvalidCommandException("Username can't be empty.");
                        manager.removeUser(argument);
                        System.out.println("User '" + argument + "'  was removed successfully.");
                        break;

                    case "verify":
                        if (argument.isEmpty()) throw new InvalidCommandException("Username cannot be empty.");
                        System.out.print("Enter password: ");
                        String verifyPassword = scanner.nextLine();
                        manager.verifyAccess(argument, Utilities.encryptPassword(verifyPassword));
                        System.out.println("Access granted to be verified for '" + argument + "'.");
                        break;

                    case "load":
                        if (argument.isEmpty()) throw new InvalidCommandException("Filename can't be empty.");
                        Utilities.readAccountFile(argument, manager);
                        break;

                    case "exit":
                        scanner.close();
                        return;

                    default:
                        throw new InvalidCommandException("Command unknown: " + command);
                }

            } catch (DuplicateUserException e) {
                System.out.println("User '" + argument + "' already exists.");
            } catch (UserNotFoundException e) {
                System.out.println("User '" + argument + "' not found.");
            } catch (AccountLockedException e) {
                System.out.println("User '" + argument + "' account is now locked.");
            } catch (PasswordIncorrectException e) {
                System.out.println("ERROR Try a new Password");
            } catch (InvalidCommandException e) {
                System.out.println("ERROR Try again: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error! Choose another: " + e.getMessage());
            }
        }
    }
}
