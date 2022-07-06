package ru.alxstn.menu.states;

import ru.alxstn.data.StudentStorage;
import ru.alxstn.menu.State;

public class ListState implements State {
    private State returnToState;

    public ListState(State returnToState) {
        this.returnToState = returnToState;
    }

    @Override
    public State runState() {
        StudentStorage.printAll();
        return returnToState;
    }
}
