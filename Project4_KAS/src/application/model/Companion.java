package application.model;

import javax.swing.*;
import java.util.ArrayList;

public class Companion extends Person {

    private ArrayList<Excursion> excursions = new ArrayList<>();

    public Companion (String name, int age, String telephone) {
        super(name, age, telephone);
    }

    // ------------------------------------------------------------------------------

    public void addExcursion (Excursion excursion) {
        if (!this.excursions.contains(excursion)) {
            this.excursions.add(excursion);
        }
    }

    public void removeExcursion (Excursion excursion) {
        this.excursions.remove(excursion);
    }

    public ArrayList<Excursion> getExcursions () {
        return new ArrayList<>(this.excursions);
    }
}
