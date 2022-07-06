package ru.alxstn.data;

import java.util.SortedMap;
import java.util.TreeMap;

public class PointsStats {
    private Points points;
    private SortedMap<Integer, String> usage = new TreeMap<>();

    public PointsStats(Points points) {
        this.points = points;

        if (points.getJavaPoints() > 0)
            add(points.getJavaPoints(), "Java");

        if (points.getDsaPoints() > 0)
            add(points.getDsaPoints(), "DSA");

        if (points.getSpringPoints() > 0)
            add(points.getSpringPoints(), "Spring");

        if (points.getDatabasesPoints() > 0)
            add(points.getDatabasesPoints(), "Databases");
    }

    private void add(Integer count, String name) {
        if (usage.containsKey(count)) {
            String s = usage.get(count) + " " + name;
            usage.replace(count, s);
        }
        else usage.put(count, name);
    }

    // ToDo: Optional<String>, "n/a"
    public String getGreatest() {
        if (usage.size() < 1)
            return "n/a";

        String value = usage.get(usage.lastKey());
        if (value.isEmpty() || value.isBlank())
            return "n/a";
        else return value;
    }

    // ToDo: Optional<String>, "n/a"
    public String getSmallest() {

        if (usage.size() <= 1){
            return "n/a";
        }

        String value = usage.get(usage.firstKey());
        if (value.isEmpty() || value.isBlank())
            return "n/a";
        else return value;
    }
}
