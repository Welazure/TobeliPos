package me.welazure.tobelipos.handler.consoleinterface.menus;

import me.welazure.tobelipos.handler.consoleinterface.MenuHandler;
import java.util.Scanner;

public abstract class Menu {
    private MenuHandler handler;

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
}
