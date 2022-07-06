package ru.alxstn.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public class ConfigurableMenuState extends MenuState {

    private final Map<Integer, MenuItem> menus = super.menuEntries;
    private final Map<Integer, UnaryOperator<State>> transitions = new HashMap<>();

    public void addMenuItem(int id, MenuItem item,  UnaryOperator<State> nextState) {
        menus.put(id, item);
        transitions.put(id, nextState);
    }

    @Override
    State nextState(MenuEntry menuEntry) {
        var op = transitions.get(menuEntry.getId());
        return op.apply(this);
    }
}
