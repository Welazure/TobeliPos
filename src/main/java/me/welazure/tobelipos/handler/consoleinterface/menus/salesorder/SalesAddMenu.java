package me.welazure.tobelipos.handler.consoleinterface.menus.salesorder;

import me.welazure.tobelipos.handler.catalog.Catalog;
import me.welazure.tobelipos.handler.catalog.Item;
import me.welazure.tobelipos.handler.consoleinterface.menus.Menu;
import me.welazure.tobelipos.handler.consoleinterface.menus.SubMenu;
import me.welazure.tobelipos.handler.salesorder.Order;
import me.welazure.tobelipos.handler.salesorder.SalesOrder;
import me.welazure.tobelipos.utils.Reader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesAddMenu extends SubMenu {
    public SalesAddMenu(Menu parent) {
        super(parent);
    }

    @Override
    public void show() {
        SalesOrder salesOrder = getHandler().getDelegator().getSalesOrder();
        Catalog catalog = getHandler().getDelegator().getCatalog();
        Reader rd = getHandler().getDelegator().getReader();

        getHandler().clearConsole();
        ((SalesMenu) getParent()).printToShow();

        System.out.println("Adding Order... ");
        String id = "";
        String name = "";
        String status;
        Map<Item, Integer> items = new HashMap<>();

        while (true) {
            System.out.print("Input ID: ");
            id = rd.readLine();
            if (salesOrder.getOrderByID(id) != null) {
                System.out.println("ID Already in use!");
                continue;
            }
            if (id.isEmpty()) {
                System.out.println("ID cannot be empty!");
            }
            break;
        }

        while (true) {
            System.out.print("Input Customer name...");
            name = rd.readLine();
            if (!name.isEmpty()) break;
            System.out.println("Customer name cannot be empty!");
        }

        while (true) {
            System.out.print("Input status...");
            status = rd.readLine();
            if (!status.isEmpty()) break;
            System.out.println("Status cannot be empty!");
        }

        boolean run = true;
        while (run) {
            System.out.println("Adding item...");
            System.out.print("Continue? [Y/n]: ");
            String confirm = rd.readLine();
            if (confirm.equalsIgnoreCase("no") || confirm.equalsIgnoreCase("n")) {
                break;
            } else if (confirm.isEmpty() || confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
                run = true;
            } else {
                continue;
            }
            Item item = null;
            System.out.print("Insert Item ID: ");
            String itemId = rd.readLine();
            item = getHandler().getDelegator().getCatalog().getItemById(itemId);

            if (item == null) {
                System.out.println("Item not found!");
                continue;
            }

            System.out.println("Found item: " + item);
            System.out.print("Add Item? [Y/n]: ");
            confirm = rd.readLine();
            if (confirm.equalsIgnoreCase("no") || confirm.equalsIgnoreCase("n")) {
                continue;
            }
            if (confirm.isEmpty() || confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
                int amount = 0;
                while (true) {
                    try {
                        System.out.print("Input amount... ");
                        amount = Integer.parseInt(rd.readLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid input!");
                    }
                }
                System.out.printf("Adding %d %s of %s... \n Continue? [Y/n]: ", amount, item.getUnit(), item.getName());
                confirm = rd.readLine();
                if (confirm.equalsIgnoreCase("no") || confirm.equalsIgnoreCase("n")) {
                    continue;
                }
                if (confirm.isEmpty() || confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
                    items.put(item, amount);
                }
            }
        }

        Order order = new Order(id, name, status, LocalDateTime.now(), items);
        salesOrder.addOrder(order);

        getParent().show();
    }
}
