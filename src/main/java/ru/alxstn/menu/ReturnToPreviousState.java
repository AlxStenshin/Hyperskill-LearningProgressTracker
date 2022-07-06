package ru.alxstn.menu;

import java.util.Scanner;

public abstract class ReturnToPreviousState implements State {
    private final Scanner scanner = new Scanner(System.in);
    private final State returnToState;

    public ReturnToPreviousState(State returnToState) {
        this.returnToState = returnToState;
    }

    protected abstract void welcomeAction();
    protected abstract void menuStateAction(String input);
    protected abstract void finalizeAction();

    @Override
    public State runState() {
        welcomeAction();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("back")) {
                finalizeAction();
                return returnToState;
            } else {
                menuStateAction(input);
            }
        }
    }
}
