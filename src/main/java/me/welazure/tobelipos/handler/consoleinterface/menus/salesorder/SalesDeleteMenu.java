package me.welazure.tobelipos.handler.consoleinterface.menus.salesorder;

import me.welazure.tobelipos.handler.catalog.Catalog;
import me.welazure.tobelipos.handler.catalog.Item;
import me.welazure.tobelipos.handler.consoleinterface.menus.Menu;
import me.welazure.tobelipos.handler.consoleinterface.menus.SubMenu;
import me.welazure.tobelipos.handler.consoleinterface.menus.catalog.CatalogMenu;
import me.welazure.tobelipos.handler.salesorder.Order;
import me.welazure.tobelipos.handler.salesorder.SalesOrder;
import me.welazure.tobelipos.utils.Reader;

public class SalesDeleteMenu extends SubMenu {
    public SalesDeleteMenu(Menu parent) {
        super(parent);
    }
    @Override
    public void show() {
        SalesOrder orders = getHandler().getDelegator().getSalesOrder();
        Reader rd = getHandler().getDelegator().getReader();
        getHandler().clearConsole();
        ((SalesMenu) getParent()).printToShow();

        System.out.println("Removing order... ");
        Order order;
        while (true) {
            System.out.print("Input ID (Press enter to cancel!): ");
            String id = rd.readLine();
            if (id.isEmpty()) {
                getParent().show();
            }
            order = orders.getOrderByID(id);
            if (order != null)
                break;
            System.out.println("Order with id " + id + " Not found!");
        }
        System.out.println("\nDeleting " + order);
        System.out.print("Confirm [y/N]?");
        String confirm = rd.readLine();
        if (confirm.isEmpty() || confirm.equalsIgnoreCase("no") || confirm.equalsIgnoreCase("n")) {
            System.out.print("Cancelled deleting order!\nPress enter to return... ");
            rd.readLine();
            getParent().show();
        }
        if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
            orders.removeOrder(order);
            System.out.println("Removed " + order);
            System.out.print("Press enter to return... ");
            rd.readLine();
        }

        getParent().show();
    }
}
