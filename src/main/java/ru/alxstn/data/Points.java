package ru.alxstn.data;

import java.util.Objects;

public class Points {

    private int javaPoints;
    private int dsaPoints;
    private int databasesPoints;
    private int springPoints;

    public Points() {
        this.javaPoints = 0;
        this.databasesPoints = 0;
        this.springPoints = 0;
        this.dsaPoints = 0;
    }

    public Points(int javaPoints, int dsaPoints, int databasesPoints, int springPoints) {
        this.javaPoints = javaPoints;
        this.dsaPoints = dsaPoints;
        this.databasesPoints = databasesPoints;
        this.springPoints = springPoints;
    }

    public Points getCompleted(){
        return new Points((javaPoints == 0) ? 0 : 1,
                (dsaPoints == 0) ? 0 : 1,
                (databasesPoints == 0) ? 0 : 1,
                (springPoints == 0) ? 0 : 1);
    }

    public Points add(Points p) {
        this.javaPoints += p.javaPoints;
        this.dsaPoints += p.dsaPoints;
        this.databasesPoints += p.databasesPoints;
        this.springPoints += p.springPoints;
        return this;
    }

    public int getJavaPoints() {
        return javaPoints;
    }

    public int getDsaPoints() {
        return dsaPoints;
    }

    public int getDatabasesPoints() {
        return databasesPoints;
    }

    public int getSpringPoints() {
        return springPoints;
    }

    public void setJavaPoints(int javaPoints) {
        this.javaPoints = javaPoints;
    }

    public void setDsaPoints(int dsaPoints) {
        this.dsaPoints = dsaPoints;
    }

    public void setDatabasesPoints(int databasesPoints) {
        this.databasesPoints = databasesPoints;
    }

    public void setSpringPoints(int springPoints) {
        this.springPoints = springPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Points points = (Points) o;
        return javaPoints == points.javaPoints && dsaPoints == points.dsaPoints && databasesPoints == points.databasesPoints && springPoints == points.springPoints;
    }

    @Override
    public int hashCode() {
        return Objects.hash(javaPoints, dsaPoints, databasesPoints, springPoints);
    }

    @Override
    public String toString() {
        return "Points{" +
                "javaPoints=" + javaPoints +
                ", dsaPoints=" + dsaPoints +
                ", databasesPoints=" + databasesPoints +
                ", springPoints=" + springPoints +
                '}';
    }
}
