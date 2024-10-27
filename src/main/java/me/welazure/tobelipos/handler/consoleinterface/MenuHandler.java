package me.welazure.tobelipos.handler.consoleinterface;


import me.welazure.tobelipos.handler.Delegator;
import me.welazure.tobelipos.handler.consoleinterface.menus.AuthMenu;
import me.welazure.tobelipos.handler.consoleinterface.menus.MainMenu;
import me.welazure.tobelipos.handler.consoleinterface.menus.Menu;

import me.welazure.tobelipos.handler.consoleinterface.menus.catalog.CatalogMenu;
import me.welazure.tobelipos.handler.consoleinterface.menus.salesorder.SalesMenu;
import me.welazure.tobelipos.utils.Reader;

import java.util.HashMap;
import java.util.Map;

public class MenuHandler {
    private Map<String, Menu> menus;
    private final Delegator dlg;

    public MenuHandler(Delegator dlg) {
        this.dlg = dlg;
        initMenus();
    }

    private void initMenus() {
        menus = new HashMap<>();
        menus.put("auth", new AuthMenu(this));
        menus.put("main", new MainMenu(this));
        menus.put("catalog", new CatalogMenu(this));
        menus.put("sales", new SalesMenu(this));
    }

    public Delegator getDelegator() {
        return dlg;
    }

    public Map<String, Menu> getMenus() {
        return menus;
    }


    public void mainMenu() {
        this.menus.get("main").show();
    }

    public void clearConsole() {
        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getOptions(boolean clear, String message, int lower, int upper) {
        int input;
        Reader rd = dlg.getReader();
        if(clear) clearConsole();
        System.out.print(message);

        while (true) {
            input = rd.getInt();
            if (input >= lower && input <= upper) {
                return input;
            }
            if(clear) clearConsole();
            System.out.print(message);
        }
    }
}
