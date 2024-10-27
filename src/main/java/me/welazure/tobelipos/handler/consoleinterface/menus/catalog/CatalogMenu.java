package me.welazure.tobelipos.handler.consoleinterface.menus.catalog;

import me.welazure.tobelipos.handler.catalog.Catalog;
import me.welazure.tobelipos.handler.catalog.CatalogHandler;
import me.welazure.tobelipos.handler.catalog.Item;
import me.welazure.tobelipos.handler.consoleinterface.MenuHandler;
import me.welazure.tobelipos.handler.consoleinterface.menus.Menu;
import me.welazure.tobelipos.utils.Reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CatalogMenu extends Menu {
    private Map<String, Menu> subMenus;
    private Catalog catalog;
    private List<Item> toShow;
//    private int page = 1;
//    private static final int ITEM_PER_PAGE = 5;


    public CatalogMenu(MenuHandler handler) {
        super(handler);

        subMenus = new HashMap<>();
        catalog = getHandler().getDelegator().getCatalog();
        toShow = new ArrayList<>();

        subMenus.put("add", new CatalogAddMenu(this));
        subMenus.put("update", new CatalogUpdateMenu(this));
        subMenus.put("delete", new CatalogDeleteMenu(this));
    }

    //
    public void printToShow() {
        if(toShow.isEmpty()) return;
        printToShowUnconditionally();
    }

    public void printToShowUnconditionally() {
        System.out.println("Items list: ");
        for (Item item : toShow) {
            System.out.println(item.toString());
        }
        System.out.println("\n");
    }
    public List<Item> getToShow() {
        return toShow;
    }

    public void setToShow(List<Item> toShow) {
        this.toShow = toShow;
    }

    public List<Item> getToShow(int start, int end) {
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
        while (true) {
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
                    search(getHandler().getDelegator().getReader());
                    break;
                case 5:
                    setToShow(catalog.getList());
                    break;
                default:
                    break;
            }
        }
    }

    public void search(Reader rd) {
        getHandler().clearConsole();
        printToShow();

        System.out.println("Searching... ");
        String input;
        while(true){
            System.out.print("Input keyword: ");
            input = rd.readLine();
            if(!input.isEmpty())
                break;
            System.out.println("Input cannot be empty!");
        }

        String finalInput = input;
        setToShow(catalog.getList().stream().filter(x ->
            x.getID().contains(finalInput) || x.getName().contains(finalInput) || x.getDescription().contains(finalInput))
                .collect(Collectors.toList()));
    }


    public Map<String, Menu> getSubMenus() {
        return subMenus;
    }

}
