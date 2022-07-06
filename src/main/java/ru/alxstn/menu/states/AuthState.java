package ru.alxstn.menu.states;

import ru.alxstn.data.Student;
import ru.alxstn.data.StudentStorage;
import ru.alxstn.menu.ReturnToPreviousState;
import ru.alxstn.menu.State;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthState extends ReturnToPreviousState {

    public AuthState(State returnToState) {
        super(returnToState);
    }

    // Using modified RegEx from
    // https://regex101.com/r/5oGxV9/1
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Za-z0-9а-яА-Я\\._-]+@([A-Za-z0-9а-яА-Я]{1,2}|[A-Za-z0-9а-яА-Я]((?!(\\.\\.))[A-Za-z0-9а-яА-Я.-])+[A-Za-z0-9а-яА-Я])\\.[A-Za-zа-яА-Я0-9]{1,}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_NAME_REGEX =
            Pattern.compile("^[A-Z]+(?:[ '-]?[A-Z]+)+$", Pattern.CASE_INSENSITIVE);


    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validateName(String name) {
        Matcher matcher = VALID_NAME_REGEX.matcher(name);
        return matcher.find();
    }

    protected static boolean checkEmailExists(String email) {
       return StudentStorage.checkEmailExists(email);
    }

    @Override
    protected void welcomeAction() {
        System.out.println("Enter student credentials or 'back' to return:");
    }

    @Override
    protected void finalizeAction() {
        System.out.println("Total " + StudentStorage.getStudentsListSize() + " students have been added.");
    }

    @Override
    protected void menuStateAction(String input) {
        if (input.isBlank() || input.isEmpty() || input.split(" ").length < 3) {
            System.out.println("Incorrect credentials.");
        } else {
            try {
                String firstname = input.substring(0, input.indexOf(' ')).stripLeading().stripTrailing();
                String email = input.substring(input.lastIndexOf(' ')).stripLeading().stripTrailing();
                String lastname = input.substring(input.indexOf(' '), input.lastIndexOf(' ')).stripLeading().stripTrailing();

                if (validateName(firstname)) {
                    if (validateName(lastname)) {
                        if (validateEmail(email)) {
                            if (!checkEmailExists(email)) {
                                Student newStudent = new Student(firstname, lastname, email);
                                StudentStorage.addNewStudent(newStudent);
                                System.out.println("The student has been added.");
                            } else
                                System.out.println("This email is already taken.");
                        } else
                            System.out.println("Incorrect email.");
                    } else
                        System.out.println("Incorrect last name.");
                } else
                    System.out.println("Incorrect first name.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Incorrect credentials.");
            }
        }
    }
}
