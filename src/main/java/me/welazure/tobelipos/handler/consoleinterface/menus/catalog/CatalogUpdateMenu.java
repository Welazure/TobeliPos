package me.welazure.tobelipos.handler.consoleinterface.menus.catalog;

import me.welazure.tobelipos.handler.catalog.Catalog;
import me.welazure.tobelipos.handler.catalog.Item;
import me.welazure.tobelipos.handler.consoleinterface.MenuHandler;
import me.welazure.tobelipos.handler.consoleinterface.menus.Menu;
import me.welazure.tobelipos.handler.consoleinterface.menus.SubMenu;
import me.welazure.tobelipos.utils.Reader;

import java.util.List;

public class CatalogUpdateMenu extends SubMenu {
    private Item selected = null;

    public CatalogUpdateMenu(Menu parent) {
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
        CatalogMenu parentMenu = (CatalogMenu) getParent();


        if (selected == null) {
            getHandler().clearConsole();
            showItemSelectMenu(parentMenu, rd);
        } else {
            while (true) {
                getHandler().clearConsole();
                System.out.println("Item being updated: \n" + selected);
                System.out.println("\n");


                int input = getHandler().getOptions(false,
                        "Choose field to update: \n" +
                                "[1]: Name\n" +
                                "[2]: ID\n" +
                                "[3]: Description\n" +
                                "[4]: Price\n" +
                                "[5]: Stock\n" +
                                "[6]: Quantity unit\n" +
                                "[9]: Unselect item\n" +
                                "[0]: Return\n" +
                                "Input: ", 0, 9);
                switch (input) {
                    case 1:
                        showNameUpdateMenu(parentMenu, rd);
                        break;
                    case 2:
                        showIDUpdateMenu(parentMenu, rd);
                        break;
                    case 3:
                        showDescriptionUpdateMenu(parentMenu, rd);
                        break;
                    case 4:
                        showPriceUpdateMenu(parentMenu, rd);
                        break;
                    case 5:
                        showStockUpdateMenu(parentMenu, rd);
                        break;
                    case 6:
                        showUnitUpdateMenu(parentMenu, rd);
                        break;
                    case 0:
                        getParent().show();
                        break;
                    case 9:
                        showClearSelected(true);
                        break;
                    default:
                        showClearSelected(false);
                        break;
                }
            }
        }
    }

    private void showItemSelectMenu(CatalogMenu catalogMenu, Reader rd) {
        if (selected != null) show();
        catalogMenu.printToShow();
        System.out.println("Select item to update... ");
        Item item = null;
        while (true) {
            System.out.print("Input ID (Press enter to cancel!): ");
            String id = rd.readLine();
            if (id.isEmpty()) {
                getParent().show();
            }
            item = getHandler().getDelegator().getCatalog().getItemById(id);
            if (item != null)
                break;
            System.out.println("Item with id " + id + " Not found!");
        }
        selected = item;
        showClearSelected(false);
    }

    private void showNameUpdateMenu(CatalogMenu catalogMenu, Reader rd) {
        getHandler().clearConsole();
        if (selected == null)
            return;
        System.out.println("Item being updated: \n" + selected);
        System.out.println("\n");
        String newName;
        while (true) {
            System.out.print("Insert new name...");
            newName = rd.readLine();
            if (!newName.isEmpty()) break;
            System.out.println("Name cannot be empty!");
        }
        selected.setName(newName);
    }

    private void showIDUpdateMenu(CatalogMenu catalogMenu, Reader rd) {
        getHandler().clearConsole();
        if (selected == null)
            return;
        System.out.println("Item being updated: \n" + selected);
        System.out.println("\n");
        String newID;
        while (true) {
            System.out.print("Insert new ID... ");
            newID = rd.readLine();
            if (newID.isEmpty()) {
                System.out.println("ID cannot be empty!");
                continue;
            }
            Catalog catalog = getHandler().getDelegator().getCatalog();
            if (catalog.getItemById(newID) != null) {
                System.out.println("Item with id " + newID + " already exists!");
                continue;
            }
            break;
        }
        selected.setID(newID);
    }

    public void showDescriptionUpdateMenu(CatalogMenu catalogMenu, Reader rd) {
        getHandler().clearConsole();
        if (selected == null)
            return;
        System.out.println("Item being updated: \n" + selected);
        System.out.println("\n");
        System.out.print("Insert new description... ");
        String newDescription = rd.readLine();
        selected.setDescription(newDescription);
    }

    public void showPriceUpdateMenu(CatalogMenu catalogMenu, Reader rd) {
        getHandler().clearConsole();
        if(selected == null)
            return;
        System.out.println("Item being updated: \n" + selected);
        System.out.println("\n");
        double price;
        while (true) {
            try {
                System.out.print("Input new price... ");
                price = rd.getDouble();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
        selected.setPrice(price);
    }
    public void showStockUpdateMenu(CatalogMenu catalogMenu, Reader rd) {
        getHandler().clearConsole();
        if(selected == null)
            return;
        System.out.println("Item being updated: \n" + selected);
        System.out.println("\n");
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
        selected.setQuantity(quantity);
    }
    public void showUnitUpdateMenu(CatalogMenu catalogMenu, Reader rd) {
        getHandler().clearConsole();
        getHandler().clearConsole();
        if (selected == null)
            return;
        System.out.println("Item being updated: \n" + selected);
        System.out.println("\n");
        String newUnit;
        while (true) {
            System.out.print("Insert new quantity unit...");
            newUnit = rd.readLine();
            if (!newUnit.isEmpty()) break;
            System.out.println("Quantity unit cannot be empty!");
        }
        selected.setUnit(newUnit);
    }
}
