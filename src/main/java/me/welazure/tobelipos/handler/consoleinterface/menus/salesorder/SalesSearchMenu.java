package me.welazure.tobelipos.handler.consoleinterface.menus.salesorder;

import me.welazure.tobelipos.handler.catalog.Catalog;
import me.welazure.tobelipos.handler.catalog.Item;
import me.welazure.tobelipos.handler.consoleinterface.menus.Menu;
import me.welazure.tobelipos.handler.consoleinterface.menus.SubMenu;
import me.welazure.tobelipos.handler.consoleinterface.menus.catalog.CatalogMenu;
import me.welazure.tobelipos.handler.salesorder.Order;
import me.welazure.tobelipos.handler.salesorder.SalesOrder;
import me.welazure.tobelipos.utils.Reader;

import java.util.ArrayList;
import java.util.List;

public class SalesSearchMenu extends SubMenu {
    public SalesSearchMenu(Menu parent) {
        super(parent);
    }
    @Override
    public void show() {
        SalesOrder orders = getHandler().getDelegator().getSalesOrder();
        Reader rd = getHandler().getDelegator().getReader();
        SalesMenu parentMenu = (SalesMenu) getParent();
        while (true) {
            getHandler().clearConsole();
            parentMenu.printToShow();

            List<Order> ordersList = filterItems(orders, parentMenu, rd);
            if (ordersList == null) {
                break;
            }
            parentMenu.setToShow(ordersList);
        }
        getParent().show();
    }

    public List<Order> filterItems(SalesOrder orders, SalesMenu parentMenu, Reader rd) {
        int option = getHandler().getOptions(false, "Select parameter to search from...\n" +
                "[1]: Order ID\n" +
                "[2]: Customer Name\n" +
                "[3]: Item ID\n" +
                "[4]: Order ID & Customer Name" +
                "[5]: Order ID & Item ID\n" +
                "[6]: Customer Name & Item ID\n" +
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
        boolean byItemID = false;
        switch (option) {
            case 1:
                byID = true;
                break;
            case 2:
                byName = true;
                break;
            case 3:
                byItemID = true;
                break;
            case 4:
                byID = true;
                byName = true;
                break;
            case 5:
                byID = true;
                byItemID = true;
            case 6:
                byName = true;
                byItemID = true;
                break;
            case 7:
                byID = true;
                byItemID = true;
                byName = true;
                break;
            default:

        }
        List<Order> ordersList = new ArrayList<>();
        if (byID) {
            orders.searchOrderByID(input).stream().forEach(x -> {
                if (!ordersList.contains(x))
                    ordersList.add(x);
            });
        }
        if (byName) {
            orders.searchOrderByUserName(input).stream().forEach(x -> {
                if (!ordersList.contains(x))
                    ordersList.add(x);
            });
        }
        if (byItemID) {
            orders.searchOrderByItemID(input).stream().forEach(x -> {
                if (!ordersList.contains(x))
                    ordersList.add(x);
            });
        }
        return ordersList;
    }
}
