package ru.alxstn.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

abstract class MenuState implements State {

    protected Map<Integer, MenuItem> menuEntries = new HashMap<>();
    private boolean showMenuNumbers = true;
    private final Scanner scan = new Scanner(System.in);

    public void setShowMenuNumbers(boolean showMenuNumbers) {
        this.showMenuNumbers = showMenuNumbers;
    }

    private void showMenu() {
        for (var entry : menuEntries.entrySet()) {
            System.out.println(showMenuNumbers ? (entry.getKey() + " ") : ""
                    + entry.getValue());
        }
    }

   MenuEntry<Integer, MenuItem> readMenuEntry() {
        //showMenu();

        String str = scan.nextLine();
        if (str.isEmpty() || str.isBlank()) {
            System.out.println("No input.");
            readMenuEntry();
        }
        try {
            int tryInt = Integer.parseInt(str);
            return readMenuEntryByNumber(tryInt);
        } catch (NumberFormatException nfe) {
            return readMenuEntryByCommand(str);
        }
    }

    private MenuEntry<Integer, MenuItem> readMenuEntryByNumber(int selection) {
        if (!menuEntries.containsKey(selection)) {
            System.out.println("Error: unknown command!");
            readMenuEntry();
        }
        return new MenuEntry(selection, menuEntries.get(selection));
    }

    private MenuEntry<Integer, MenuItem> readMenuEntryByCommand(String selection) {
        if (!menuEntries.containsValue(new MenuItem(selection))) {
            System.out.println("Error: unknown command!");
            readMenuEntry();
        } else {
            for (var entry : menuEntries.entrySet()) {
                if (entry.getValue().getDescription().equals(selection)) {
                    return new MenuEntry(entry.getKey(), menuEntries.get(entry.getKey()));
                } // else System.out.println(entry.getValue().getDescription() + " != " + selection);
            }
        }
        return null;
    }

    @Override
    public State runState() {
        var option = readMenuEntry();
        return nextState((MenuEntry) option);
    }

    abstract State nextState(MenuEntry menuEntry);
}
