package ru.alxstn.menu.states;

import ru.alxstn.data.NotificationService;
import ru.alxstn.menu.State;


public class NotificationState implements State {
    private State returnToState;

    public NotificationState(State returnToState) {
        this.returnToState = returnToState;
    }

    @Override
    public State runState() {
        NotificationService service = new NotificationService();
        service.buildNotifications();
        System.out.println("Total " + service.getRecipientsCount() + " students have been notified.");
        return returnToState;
    }
}
