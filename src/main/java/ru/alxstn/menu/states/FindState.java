package ru.alxstn.menu.states;

import ru.alxstn.data.StudentStorage;
import ru.alxstn.menu.ReturnToPreviousState;
import ru.alxstn.menu.State;

public class FindState extends ReturnToPreviousState {

    public FindState(State returnToState) {
        super(returnToState);
    }

    protected static boolean validateInputString(String input) {
        return !input.isBlank() && !input.isEmpty();
    }

    @Override
    protected void welcomeAction() {
        System.out.println("Enter an id or 'back' to return:");
    }

    @Override
    protected void menuStateAction(String input) {
        if (validateInputString(input)) {
            try { System.out.println(StudentStorage.findById(input)); }
            catch (NumberFormatException e) { }
        }
    }

    @Override
    protected void finalizeAction() { }

}
