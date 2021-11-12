package application.model;

import java.util.ArrayList;

public class HotelRoom {

    private int nr;
    private int price;
    private boolean isSingle;

    private ArrayList<AddOn> addOns = new ArrayList<>();

    public HotelRoom (int nr, int price, boolean isSingle) {
        this.nr = nr;
        this.price = price;
        this.isSingle = isSingle;
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
        return this.isSingle;
    }

    public void setSingle (boolean single) {
        this.isSingle = single;
    }

    // -------------------------------------------------------------------------------

    public void addAddOn (AddOn addOn) {
        if (!this.addOns.contains(addOn)) {
            this.addOns.add(addOn);
        }
    }

    public void removeAddOn (AddOn addOn) {
        this.addOns.remove(addOn);
    }

    public ArrayList<AddOn> getAddOns () {
        return new ArrayList<>(this.addOns);
    }
}
