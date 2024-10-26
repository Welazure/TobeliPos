package me.welazure.tobelipos.handler.consoleinterface.menus.catalog;

import me.welazure.tobelipos.handler.catalog.Catalog;
import me.welazure.tobelipos.handler.catalog.Item;
import me.welazure.tobelipos.handler.consoleinterface.MenuHandler;
import me.welazure.tobelipos.handler.consoleinterface.menus.Menu;
import me.welazure.tobelipos.handler.consoleinterface.menus.SubMenu;
import me.welazure.tobelipos.utils.Reader;

public class CatalogAddMenu extends SubMenu {
    public CatalogAddMenu(Menu menu) {
        super(menu);
    }

    @Override
    public void show() {
        Catalog catalog = getHandler().getDelegator().getCatalog();
        Reader rd = getHandler().getDelegator().getReader();
        while (true) {
            getHandler().clearConsole();

            System.out.println("Addding Item... ");
            String id = "";
            String name = "";
            String description = "";
            double price = 0;
            int quantity = 0;

            while (true) {
                System.out.print("Input ID: ");
                id = rd.readLine();
                if (catalog.getItemById(id) != null)
                    System.out.println("ID Already in use!");
                break;
            }

            System.out.print("Input name...");
            name = rd.readLine();
            System.out.print("Input description...");
            description = rd.readLine();
            while (true) {
                try {
                    System.out.print("Input price...");
                    price = Double.parseDouble(rd.readLine());
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input!");
                }
            }
            while (true) {
                try {
                    System.out.print("Input quantity...");
                    quantity = rd.getInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input!");
                }
            }

            Item item = new Item(id, name, description, price, quantity);
            catalog.addItem(item);

            getParent().show();
        }
    }
}
