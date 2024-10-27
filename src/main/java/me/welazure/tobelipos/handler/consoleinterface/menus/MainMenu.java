package me.welazure.tobelipos.handler.consoleinterface.menus;


import me.welazure.tobelipos.handler.auth.user.Admin;
import me.welazure.tobelipos.handler.auth.user.User;
import me.welazure.tobelipos.handler.consoleinterface.MenuHandler;

public class MainMenu extends Menu {

    public MainMenu(MenuHandler handler) {
        super(handler);
    }

    @Override
    public void show() {
        User user = getHandler().getDelegator().getCurrentUser();
        if(user == null)
            user = new Admin(10000, "root", "asdasda");
//        if (user == null) {
//            int input = getHandler().getOptions(true, "You are not logged in!\n" +
//                    "[1]: Authenticate!\n" +
//                    "[0]: Exit!\n" +
//                    "Input: ", 0, 1);
//            switch (input) {
//            case 1:
//                getHandler().getMenus().get("auth").show();
//                break;
//            case 0:
//                return;
//            default:
//                show();
//                break;
//            }
//        } else if (user.isAdmin()) {
        System.out.println("You are an admin!...");
        int input = getHandler().getOptions(true, String.format(
                "Welcome %s, you are an admin...\n" +
                        "[1]: Catalog\n" +
                        "[2]: Sales Order\n" +
//                            "[9]: Logout!\n" +
                        "[0]: Exit!\n" +
                        "Input: ", user.getName().split(" ")[0]), 0, 9);
        switch (input) {
            case 1:
                getHandler().getMenus().get("catalog").show();
                break;
            case 2:
                getHandler().getMenus().get("sales").show();
                break;
//            case 9:
//                Delegator dlg = getHandler().getDelegator();
//                dlg.getAuthenticator().deAuthenticate(dlg.getCurrentUser());
//                dlg.setCurrentUser(null);
//
//                getHandler().clearConsole();
//                System.out.println("Successfully logged out! Press enter to return to main menu...");
//                getHandler().getDelegator().getReader().readLine();
//
//                show();
//                break;
            case 0:
                getHandler().getDelegator().exit();
            default:
                show();
        }
//        }

    }

}
