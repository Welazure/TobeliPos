package me.welazure.tobelipos.handler.consoleinterface.menus;

public abstract class SubMenu extends Menu {
    private Menu parent;

    public SubMenu(Menu parent) {
        super(parent.getHandler());
        this.parent = parent;
    }

    public Menu getParent() {
        return parent;
    }
}
