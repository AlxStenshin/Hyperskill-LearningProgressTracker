package ru.alxstn;

import ru.alxstn.menu.*;
import ru.alxstn.menu.states.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Learning Progress Tracker");

        var mainMenuState = new ConfigurableMenuState();
        var backState = new BackState(mainMenuState);
        var authState = new AuthState(mainMenuState);
        var listState = new ListState(mainMenuState);
        var findState = new FindState(mainMenuState);
        var statsState = new StatisticsState(mainMenuState);
        var addPointsState = new AddPointsState(mainMenuState);
        var notificationState = new NotificationState(mainMenuState);

        int id = 0;
        mainMenuState.addMenuItem(++id, new MenuItem("exit"), menu -> null);
        mainMenuState.addMenuItem(++id, new MenuItem("back"), menu -> backState);
        mainMenuState.addMenuItem(++id, new MenuItem("list"), menu -> listState);
        mainMenuState.addMenuItem(++id, new MenuItem("find"), menu -> findState);
        mainMenuState.addMenuItem(++id, new MenuItem("add points"), menu -> addPointsState);
        mainMenuState.addMenuItem(++id, new MenuItem("add students"), menu -> authState);
        mainMenuState.addMenuItem(++id, new MenuItem("statistics"), menu -> statsState);
        mainMenuState.addMenuItem(++id, new MenuItem("notify"), menu -> notificationState);

        State starterState = mainMenuState;

        while (starterState != null)
            starterState = starterState.runState();
        System.out.println("Bye!");
    }
}
