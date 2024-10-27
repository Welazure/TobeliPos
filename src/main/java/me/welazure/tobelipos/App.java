package me.welazure.tobelipos;

import me.welazure.tobelipos.handler.Delegator;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        Delegator dlg = Delegator.getInstance();
        dlg.getMenuHandler().mainMenu();
    }
}
