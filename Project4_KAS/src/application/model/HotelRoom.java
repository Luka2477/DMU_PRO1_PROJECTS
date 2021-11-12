package application.model;

import java.util.ArrayList;

public class HotelRoom {

    private int nr;
    private int price;
    private boolean single;

    private ArrayList<AddOn> addOns = new ArrayList<>();

    public HotelRoom (int nr, int price, boolean single) {
        this.nr = nr;
        this.price = price;
        this.single = single;
    }

    // -------------------------------------------------------------------------------

    public int getNr () {
        return this.nr;
    }

    public void setNr (int nr) {
        this.nr = nr;
    }

    public int getPrice () {
        return this.price;
    }

    public void setPrice (int price) {
        this.price = price;
    }

    public boolean isSingle () {
        return this.single;
    }

    public void setSingle (boolean single) {
        this.single = single;
    }

    // -------------------------------------------------------------------------------

    public AddOn createAddOn (String name, int price) {
        AddOn addOn = new AddOn(name, price);
        this.addOns.add(addOn);
        return addOn;
    }

    public void removeAddOn (AddOn addOn) {
        this.addOns.remove(addOn);
    }

    public ArrayList<AddOn> getAddOns () {
        return new ArrayList<>(this.addOns);
    }
}
