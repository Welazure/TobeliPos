package me.welazure.tobelipos.handler.consoleinterface.menus.catalog;

import me.welazure.tobelipos.handler.catalog.Catalog;
import me.welazure.tobelipos.handler.catalog.Item;
import me.welazure.tobelipos.handler.consoleinterface.menus.Menu;
import me.welazure.tobelipos.handler.consoleinterface.menus.SubMenu;
import me.welazure.tobelipos.utils.Reader;

public class CatalogDeleteMenu extends SubMenu {
    public CatalogDeleteMenu(Menu parent) {
        super(parent);
    }

    @Override
    public void show() {
        Catalog catalog = getHandler().getDelegator().getCatalog();
        Reader rd = getHandler().getDelegator().getReader();
        getHandler().clearConsole();
        ((CatalogMenu) getParent()).printToShow();

        System.out.println("Removing Item... ");
        Item item = null;
        while (true) {
            System.out.print("Input ID (Press enter to cancel!): ");
            String id = rd.readLine();
            if (id.isEmpty()) {
                getParent().show();
            }
            item = catalog.getItemById(id);
            if (item != null)
                break;
            System.out.println("Item with id " + id + " Not found!");
        }
        System.out.println("\nDeleting " + item);
        System.out.print("Confirm [y/N]?");
        String confirm = rd.readLine();
        if (confirm.isEmpty() || confirm.equalsIgnoreCase("no") || confirm.equalsIgnoreCase("n")) {
            System.out.print("Cancelled deleting item!\nPress enter to return... ");
            rd.readLine();
            getParent().show();
        }
        if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
            catalog.removeItem(item);
            System.out.println("Removed " + item);
            System.out.print("Press enter to return... ");
            rd.readLine();
        }

        getParent().show();
    }
}
