package ru.alxstn.menu;

import java.util.Objects;

public class MenuItem {

    private String description;

    public String getDescription() {
        return description;
    }

    public MenuItem(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(description, menuItem.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
