package application.model;

public class AddOn {

    private String name;
    private int price;

    public AddOn (String name, int price) {
        this.name = name;
        this.price = price;
    }

    // ----------------------------------------------------------------------------

    public String getName () {
        return this.name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public int getPrice () {
        return this.price;
    }

    public void setPrice (int price) {
        this.price = price;
    }

    // ----------------------------------------------------------------------------

    @Override
    public String toString () {
        return (this.price == 0) ? "Alle med " + this.name : String.format("Tillæg kr. %d for %s", this.price, this.name);
    }
}
