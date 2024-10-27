package me.welazure.tobelipos.handler.consoleinterface.menus;

import me.welazure.tobelipos.handler.consoleinterface.MenuHandler;

import java.util.Map;
import java.util.Scanner;

public abstract class Menu {
    private final MenuHandler handler;
    private Map<String, SubMenu> subMenus;

    public Menu(MenuHandler handler) {
        this.handler = handler;
    }

    public void show() {
        System.out.println("menu printed!");
        Scanner sc = new Scanner(System.in);
        System.out.println(sc.nextLine() + " Inputted!");
    }
    public MenuHandler getHandler() {
        return handler;
    }
    public Map<String, SubMenu> getSubMenus() {
        return subMenus;
    }
}
