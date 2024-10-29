package me.welazure.tobelipos.handler.consoleinterface.menus.catalog;

import me.welazure.tobelipos.handler.catalog.Catalog;
import me.welazure.tobelipos.handler.catalog.Item;
import me.welazure.tobelipos.handler.consoleinterface.menus.Menu;
import me.welazure.tobelipos.handler.consoleinterface.menus.SubMenu;
import me.welazure.tobelipos.utils.Reader;

import java.util.ArrayList;
import java.util.List;

public class CatalogSearchMenu extends SubMenu {

    public CatalogSearchMenu(Menu parent) {
        super(parent);
    }

    @Override
    public void show() {
        Catalog catalog = getHandler().getDelegator().getCatalog();
        Reader rd = getHandler().getDelegator().getReader();
        CatalogMenu parentMenu = (CatalogMenu) getParent();
        while (true) {
            getHandler().clearConsole();
            parentMenu.printToShow();

            List<Item> items = filterItems(catalog, parentMenu, rd);
            if (items == null) {
                break;
            }
            parentMenu.setToShow(items);
        }
        getParent().show();
    }

    public List<Item> filterItems(Catalog catalog, CatalogMenu parentMenu, Reader rd) {
        int option = getHandler().getOptions(false, "Select parameter to search from...\n" +
                "[1]: Item ID\n" +
                "[2]: Item Name\n" +
                "[3]: Item Description\n" +
                "[4]: Item ID & Name\n" +
                "[5]: Item ID & Description\n" +
                "[6]: Item Name & Description\n" +
                "[7]: All parameter\n" +
                "[0]: Return\n" +
                "Input: ", 0, 7);
        if (option == 0) {
            return null;
        }

        System.out.println("Searching... ");
        String input;
        while (true) {
            System.out.print("Input keyword: ");
            input = rd.readLine();
            if (!input.isEmpty())
                break;
            System.out.println("Input cannot be empty!");
        }
        boolean byID = false;
        boolean byName = false;
        boolean byDescription = false;
        switch (option) {
            case 1:
                byID = true;
                break;
            case 2:
                byName = true;
                break;
            case 3:
                byDescription = true;
                break;
            case 4:
                byID = true;
                byName = true;
                break;
            case 5:
                byID = true;
                byDescription = true;
            case 6:
                byName = true;
                byDescription = true;
                break;
            case 7:
                byID = true;
                byDescription = true;
                byName = true;
                break;
            default:

        }
        List<Item> items = new ArrayList<>();
        if (byID) {
            catalog.searchItemByID(input).stream().forEach(x -> {
                if (!items.contains(x))
                    items.add(x);
            });
        }
        if (byName) {
            catalog.searchItemByName(input).stream().forEach(x -> {
                if (!items.contains(x))
                    items.add(x);
            });
        }
        if (byDescription) {
            catalog.searchItemByDescription(input).stream().forEach(x -> {
                if (!items.contains(x))
                    items.add(x);
            });
        }
        return items;
    }
}
