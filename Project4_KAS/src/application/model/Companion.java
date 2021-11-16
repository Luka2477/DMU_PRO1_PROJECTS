package application.model;

import java.util.ArrayList;

public class Companion extends Person {

    private ArrayList<Excursion> excursions = new ArrayList<>();

    public Companion (String name) {
        super(name);
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

    // ------------------------------------------------------------------------------

    public double calculateExcursionsPrice () {
        double sum = 0.0;
        for (Excursion excursion : this.excursions) {
            sum += excursion.getPrice();
        }
        return sum;
    }
}
