package ru.alxstn.menu.states;

import ru.alxstn.data.Statistics;
import ru.alxstn.menu.ReturnToPreviousState;
import ru.alxstn.menu.State;

import java.util.Locale;


public class StatisticsState extends ReturnToPreviousState {

    public StatisticsState(State returnToState) {
        super(returnToState);
    }

    private static String makePrintable(String input) {
        return input.toLowerCase(Locale.ROOT).substring(0, 1).toUpperCase() + input.substring(1);
    }

    protected static boolean validateInputString(String input) {
        if (input.isBlank() || input.isEmpty())
            return false;
        else {
            String lowerInput = input.toLowerCase(Locale.ROOT);
            if ((lowerInput.equals("java")) ||  (lowerInput.equals("dsa")) ||
                    (lowerInput.equals("databases")) ||(lowerInput.equals("spring"))) {
                return true;
            }
            else return false;
        }
    }

    @Override
    protected void welcomeAction() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        System.out.println(Statistics.getStats());
    }

    @Override
    protected void menuStateAction(String input) {
        if (validateInputString(input)) {
            Statistics.printTable(makePrintable(input));
        } else {
            System.out.println("Unknown course.");
        }
    }

    @Override
    protected void finalizeAction() {

    }

}
