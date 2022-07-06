package ru.alxstn.menu.states;

import ru.alxstn.data.Points;
import ru.alxstn.data.StudentStorage;
import ru.alxstn.menu.ReturnToPreviousState;
import ru.alxstn.menu.State;

public class AddPointsState extends ReturnToPreviousState {

    public AddPointsState(State returnToState) {
        super(returnToState);
    }

    protected boolean validatePointsInputString(String input) {
        if (input.isBlank() || input.isEmpty() || input.split(" ").length != 5)
            return false;
        return true;
    }

    protected Points getPointsFromString(String input) throws NumberFormatException {
        String[] params = input.split(" ");
        Points p = new Points(Integer.parseInt(params[1]),
                Integer.parseInt(params[2]),
                Integer.parseInt(params[3]),
                Integer.parseInt(params[4]));
        return p;
    }

    protected String getIdFromString(String input) {
        String[] params = input.split(" ");
        return params[0];
    }

    protected boolean validatePoints(Points points) throws NumberFormatException {
        if (points.getJavaPoints() < 0 || points.getDsaPoints() < 0 ||
                points.getDatabasesPoints() < 0 || points.getSpringPoints() < 0) {
            throw new NumberFormatException();
        } else return true;
    }

    @Override
    protected void welcomeAction() {
        System.out.println("Enter an id and points or 'back' to return:");
    }

    @Override
    protected void menuStateAction(String input) {
        if (validatePointsInputString(input)) {
            try {
                String studentId = getIdFromString(input);
                Points newPoints = getPointsFromString(input);
                if (validatePoints(newPoints)) {
                    StudentStorage.updatePoints(studentId, newPoints);
                }
            } catch (NumberFormatException e) {
                System.out.println("Incorrect points format.");
            }
        } else {
            System.out.println("Incorrect points format.");
        }
    }

    @Override
    protected void finalizeAction() {

    }
}
