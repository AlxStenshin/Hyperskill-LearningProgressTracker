package ru.alxstn.menu;

public class MenuEntry<T, U> {
    private T id;
    private U value;

    public MenuEntry(T id, U value) {
        this.id = id;
        this.value = value;
    }

    public T getId() {
        return id;
    }

    public U getValue() {
        return value;
    }

}

