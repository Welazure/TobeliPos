package me.welazure.tobelipos.handler.consoleinterface.menus.salesorder;

import me.welazure.tobelipos.handler.consoleinterface.MenuHandler;
import me.welazure.tobelipos.handler.consoleinterface.menus.Menu;
import me.welazure.tobelipos.handler.consoleinterface.menus.SubMenu;
import me.welazure.tobelipos.handler.consoleinterface.menus.catalog.CatalogAddMenu;
import me.welazure.tobelipos.handler.consoleinterface.menus.catalog.CatalogDeleteMenu;
import me.welazure.tobelipos.handler.consoleinterface.menus.catalog.CatalogSearchMenu;
import me.welazure.tobelipos.handler.consoleinterface.menus.catalog.CatalogUpdateMenu;
import me.welazure.tobelipos.handler.salesorder.Order;
import me.welazure.tobelipos.handler.salesorder.SalesOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesMenu extends Menu {
    private final Map<String, SubMenu> subMenus;
    private final SalesOrder salesOrder;
    private List<Order> toShow;
//    private int page = 1;
//    private static final int ITEM_PER_PAGE = 5;


    public SalesMenu(MenuHandler handler) {
        super(handler);

        subMenus = new HashMap<>();
        salesOrder = getHandler().getDelegator().getSalesOrder();
        toShow = new ArrayList<>();

        subMenus.put("add", new SalesAddMenu(this));
        subMenus.put("update", new SalesUpdateMenu(this));
        subMenus.put("delete", new SalesDeleteMenu(this));
        subMenus.put("search", new SalesSearchMenu(this));
    }

    //
    public void printToShow() {
        if (toShow.isEmpty()) return;
        printToShowUnconditionally();
    }

    public void printToShowUnconditionally() {
        System.out.println("Orders list: \n");
        for (Order order : toShow) {
            System.out.println(order.toString());
        }
        System.out.println("\n");
    }

    public List<Order> getToShow() {
        return toShow;
    }

    public void setToShow(List<Order> toShow) {
        this.toShow = toShow;
    }

    public List<Order> getToShow(int start, int end) {
        int size = toShow.size();
        if (start < 0 || end < 0 || end > size || start >= end) {
            return new ArrayList<>();
        }
        return toShow.subList(start, end);
    }
//    public List<Item> getCurrentPage() {
//        int start = (page - 1) * ITEM_PER_PAGE;
//        return getToShow(start, start + ITEM_PER_PAGE);
//    }
//    public void incrementPage() {
//        page++;
//    }
//    public void decrementPage() {
//        page--;
//    }

    @Override
    public void show() {
        getHandler().clearConsole();
        printToShow();

        int input = getHandler().getOptions(false,
                "[1]: Add\n" +
                        "[2]: Update\n" +
                        "[3]: Delete\n" +
                        "[4]: Search\n" +
                        "[5]: List All Items\n" +
                        "[0]: Back to Main Menu\n" +
                        "Input: ", 0, 5);
        switch (input) {
            case 0:
                getHandler().mainMenu();
                break;
            case 1:
                getSubMenus().get("add").show();
                break;
            case 2:
                getSubMenus().get("update").show();
                break;
            case 3:
                getSubMenus().get("delete").show();
                break;
            case 4:
                getSubMenus().get("search").show();
                break;
            case 5:
                setToShow(salesOrder.getList());
                show();
                break;
            default:
                show();
        }
    }


    @Override
    public Map<String, SubMenu> getSubMenus() {
        return subMenus;
    }

}
