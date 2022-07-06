package ru.alxstn.menu.states;

import ru.alxstn.menu.State;

import java.util.Scanner;

public class BackState implements State {
    private static Scanner scanner = new Scanner(System.in);
    private State returnToState;

    public BackState(State returnToState) {
        this.returnToState = returnToState;
    }

    @Override
    public State runState() {
        System.out.println("Enter 'exit' to exit the program.");
        String input = scanner.nextLine();
        if (input.equals("exit")) {
            return null;
        }
        else return this;
    }
}
