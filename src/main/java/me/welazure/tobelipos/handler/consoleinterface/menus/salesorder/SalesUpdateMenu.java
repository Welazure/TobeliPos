package me.welazure.tobelipos.handler.consoleinterface.menus.salesorder;

import me.welazure.tobelipos.handler.catalog.Catalog;
import me.welazure.tobelipos.handler.catalog.Item;
import me.welazure.tobelipos.handler.consoleinterface.menus.Menu;
import me.welazure.tobelipos.handler.consoleinterface.menus.SubMenu;
import me.welazure.tobelipos.handler.consoleinterface.menus.catalog.CatalogMenu;
import me.welazure.tobelipos.handler.salesorder.Order;
import me.welazure.tobelipos.utils.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SalesUpdateMenu extends SubMenu {
    private Order selected;

    public SalesUpdateMenu(Menu parent) {
        super(parent);
    }
    @Override
    public void show() {
        showClearSelected(true);
    }
    public void showClearSelected(boolean clear) {
        if (clear)
            selected = null;
        Catalog catalog = getHandler().getDelegator().getCatalog();
        Reader rd = getHandler().getDelegator().getReader();
        SalesMenu parentMenu = (SalesMenu) getParent();


        if (selected == null) {
            getHandler().clearConsole();
            showItemSelectMenu(parentMenu, rd);
        } else {
            getHandler().clearConsole();
            System.out.println("Item being updated: \n" + selected);
            System.out.println("\n");


            int input = getHandler().getOptions(false,
                    "Choose field to update: \n" +
                            "[1]: ID\n" +
                            "[2]: Customer Name\n" +
                            "[3]: Status\n" +
                            "[4]: Items\n" +
                            "[5]: Unselect order\n" +
                            "[0]: Return\n" +
                            "Input: ", 0, 5);
            switch (input) {
                case 1:
                    showIDUpdateMenu(parentMenu, rd);
                    break;
                case 2:
                    showCustomerNameUpdateMenu(parentMenu, rd);
                    break;
                case 3:
                    showStatusUpdateMenu(parentMenu, rd);
                    break;
                case 4:
                    showItemsUpdateMenu(parentMenu, rd);
                    break;
                case 5:
                    showClearSelected(true);
                    break;
                case 0:
                    getParent().show();
                    break;
                default:
                    showClearSelected(false);
                    break;
            }
        }
        getParent().show();
    }

    private void showItemSelectMenu(SalesMenu salesMenu, Reader rd) {
        if (selected != null) show();
        salesMenu.printToShow();
        System.out.println("Select item to update... ");
        Order order;
        while (true) {
            System.out.print("Input ID (Press enter to cancel!): ");
            String id = rd.readLine();
            if (id.isEmpty()) {
                getParent().show();
            }
            order = getHandler().getDelegator().getSalesOrder().getOrderByID(id);
            if (order != null)
                break;
            System.out.println("Order with id " + id + " Not found!");
        }
        selected = order;
        showClearSelected(false);
    }

    private void showIDUpdateMenu(SalesMenu salesMenu, Reader rd) {
        getHandler().clearConsole();
        if (selected == null)
            return;
        System.out.println("Order being updated: \n" + selected);
        System.out.println("\n");
        String newName;
        while (true) {
            System.out.print("Insert new ID...");
            newName = rd.readLine();
            if (!newName.isEmpty()) break;
            System.out.println("ID cannot be empty!");
        }
        selected.setID(newName);
        showClearSelected(false);
    }
    private void showCustomerNameUpdateMenu(SalesMenu salesMenu, Reader rd) {
        getHandler().clearConsole();
        if (selected == null)
            return;
        System.out.println("Order being updated: \n" + selected);
        System.out.println("\n");
        String newName;
        while (true) {
            System.out.print("Insert new Customer Name...");
            newName = rd.readLine();
            if (!newName.isEmpty()) break;
            System.out.println("Customer Name cannot be empty!");
        }
        selected.setCustomer(newName);
        showClearSelected(false);
    }
    private void showStatusUpdateMenu(SalesMenu salesMenu, Reader rd) {
        getHandler().clearConsole();
        if (selected == null)
            return;
        System.out.println("Order being updated: \n" + selected);
        System.out.println("\n");
        String newName;
        while (true) {
            System.out.print("Insert new status...");
            newName = rd.readLine();
            if (!newName.isEmpty()) break;
            System.out.println("Status cannot be empty!");
        }
        selected.setStatus(newName);
        showClearSelected(false);
    }

    private void showItemsUpdateMenu(SalesMenu salesMenu, Reader rd) {
        getHandler().clearConsole();
        if(selected == null)
            return;
        System.out.println("Order being updated: \n" + selected);
        System.out.println("\n");
        Map<Item, Integer> items = selected.getItems();
        List<Map.Entry<Item, Integer>> itemSet = new ArrayList<>(items.entrySet());
        StringBuilder builder = new StringBuilder();
        builder.append("Select Item ID to update: \n");
        for(int i = 0; i < itemSet.size(); i++) {
            Map.Entry<Item, Integer> item = itemSet.get(i);
            builder.append(String.format("[%s]: %s\n", i + 1 , item.getKey().toString(item.getValue())));
        }
        builder.append("[0]: Cancel\n");
        builder.append("Input: ");
        int input = getHandler().getOptions(false, builder.toString(), 0, itemSet.size());
        if(input == 0) {
            showClearSelected(false);
            return;
        }
        Item item = itemSet.get(input-1).getKey();
        System.out.println("\nUpdating item: \n" + item.toString(itemSet.get(input-1).getValue()));
        input = getHandler().getOptions(false, "[1]: Change quantity\n" +
                "[2]: Delete Item\n" +
                "[0]: Select another item\n" +
                "Input: ", 0, 2);
        switch(input) {
            case 0:
                showItemsUpdateMenu(salesMenu, rd);
                break;
            case 1:
                int quantity;
                while (true) {
                    try {
                        System.out.print("Input quantity...");
                        quantity = rd.getInt();
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid input!");
                    }
                }
                selected.getItems().put(item, quantity);
                showClearSelected(false);
                break;
            case 2:
                System.out.print("Confirm [y/N]?");
                String confirm = rd.readLine();
                if (confirm.isEmpty() || confirm.equalsIgnoreCase("no") || confirm.equalsIgnoreCase("n")) {
                    showClearSelected(false);
                    break;
                }
                if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
                    selected.getItems().remove(item);
                }
                showClearSelected(false);
                break;
            default:
                showClearSelected(false);
        }
    }
}
