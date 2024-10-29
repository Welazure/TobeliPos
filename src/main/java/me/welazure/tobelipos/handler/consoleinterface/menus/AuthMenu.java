package me.welazure.tobelipos.handler.consoleinterface.menus;

import me.welazure.tobelipos.handler.auth.Authenticator;
import me.welazure.tobelipos.handler.auth.Users;
import me.welazure.tobelipos.handler.auth.user.User;
import me.welazure.tobelipos.handler.consoleinterface.MenuHandler;
import me.welazure.tobelipos.utils.Reader;

public class AuthMenu extends Menu {
    public AuthMenu(MenuHandler handler) {
        super(handler);
    }

    @Override
    public void show() {
        getHandler().clearConsole();

        Reader rd = getHandler().getDelegator().getReader();
        System.out.println("Welcome to the Auth Menu");
        System.out.print("Menu:\n" +
                "[1]: Login\n" +
             //   "[2]: Register\n" +
                "[0]: Back to Main Menu\n" +
                "Input: ");
        int input = rd.getInt();
        switch (input) {
        case 1:
            this.login();
            break;
//        case 2:  //Uncomment to enable register
//            this.register();
//            break;
        case 0:
            getHandler().mainMenu();
            break;
        default:
            show();
        }


    }

    public void login() {
        getHandler().clearConsole();
        Reader rd = getHandler().getDelegator().getReader();
        System.out.println("Welcome to the Login Menu");
        System.out.print("Please enter your username: ");
        String name = rd.readLine();
        System.out.print("Please enter your password: ");
        String password = rd.readLine();

        Users users = getHandler().getDelegator().getUsers();
        Authenticator auth = getHandler().getDelegator().getAuthenticator();

        User user = users.getUser(name);
        if (user == null) {
            invalidLogin();
        } else {
            boolean succeeded = auth.authenticate(user, name, password);
            if (succeeded) {
                getHandler().getDelegator().setCurrentUser(user);
                System.out.println("Successfully logged in!");
                System.out.print("Press enter to return to main menu..");
                rd.readLine();
                getHandler().mainMenu();
            } else {
                invalidLogin();
            }
        }
    }

    public void invalidLogin() {
        int input = getHandler().getOptions(true, "Username or password incorrect! Please try again.\n" +
                "Menu:\n" +
                "[1]: Try again\n" +
                "[2]: Register\n" +
                "[0]: Back to Main Menu\n" +
                "Input: ", 0, 2);
        switch (input) {
        case 1:
            this.login();
            break;
        case 2:
            this.register();
            break;
        case 0:
            getHandler().mainMenu();
            break;
        }
    }

    public void register() {
        getHandler().clearConsole();
        int input;
        Reader rd = getHandler().getDelegator().getReader();
        System.out.println("Welcome to the Register Menu");
        System.out.print("Input username: ");
        String name = rd.readLine();
        System.out.print("Input password: ");
        String password = rd.readLine();
        do {
            System.out.print("Are you an admin? [0/1]: ");
            input = rd.getInt();
        } while (input < 0 || input > 1);
        boolean success = getHandler().getDelegator().getUsers().createUser(input == 1, name, password);
        if (success) {
            input = getHandler().getOptions(true, "Successfully registered!\n" +
                    "Menu:\n" +
                    "[1]: Login\n" +
                    "[0]: Back to Main Menu\n" +
                    "Input: ", 0, 1);

            switch (input) {
            case 1:
                login();
                break;
            case 0:
                getHandler().mainMenu();
                break;
            }
        } else {
            input = getHandler().getOptions(true, "Username already in use! Please try again.\n" + "Menu:\n" +
                    "[1]: Try again\n" +
                    "[2]: Login\n" +
                    "[0]: Back to Main Menu\n" +
                    "Input: ", 0, 2);


            switch (input) {
            case 1:
                this.register();
                break;
            case 2:
                this.login();
                break;
            case 0:
                getHandler().mainMenu();
                break;
            }
        }
    }
}
