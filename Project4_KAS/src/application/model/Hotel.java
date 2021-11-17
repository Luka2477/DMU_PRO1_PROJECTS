package application.model;

import java.util.ArrayList;

public class Hotel {

    private static int NR = 0;

    private String name;
    private String address;
    private int singlePrice;
    private int doublePrice;

    private final ArrayList<Conference> conferences = new ArrayList<>();
    private final ArrayList<HotelRoom> hotelRooms = new ArrayList<>();
    private final ArrayList<AddOn> addOns = new ArrayList<>();

    public Hotel (String name, String address, int singlePrice, int doublePrice) {
        this.name = name;
        this.address = address;
        this.singlePrice = singlePrice;
        this.doublePrice = doublePrice;
    }

    // ----------------------------------------------------------------------------

    public String getName () {
        return this.name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getAddress () {
        return this.address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public int getSinglePrice() {
        return this.singlePrice;
    }

    public void setSinglePrice(int singlePrice) {
        this.singlePrice = singlePrice;
    }

    public int getDoublePrice() {
        return this.doublePrice;
    }

    public void setDoublePrice(int doublePrice) {
        this.doublePrice = doublePrice;
    }

    // ----------------------------------------------------------------------------

    public void addConference (Conference conference) {
        if (!this.conferences.contains(conference)) {
            this.conferences.add(conference);
            conference.addHotel(this);
        }
    }

    public void removeConference (Conference conference) {
        if (this.conferences.contains(conference)) {
            this.conferences.remove(conference);
            conference.removeHotel(this);
        }
    }

    public ArrayList<Conference> getConferences () {
        return new ArrayList<>(this.conferences);
    }

    // ----------------------------------------------------------------------------

    public HotelRoom createHotelRoom (int price, boolean isSingle) {
        HotelRoom hotelRoom = new HotelRoom(Hotel.NR, price, isSingle, this);
        Hotel.NR++;
        this.hotelRooms.add(hotelRoom);
        return hotelRoom;
    }

    public void removeHotelRoom (HotelRoom hotelRoom) {
        this.hotelRooms.remove(hotelRoom);
        hotelRoom.removeHotel();
    }

    public ArrayList<HotelRoom> getHotelRooms () {
        return new ArrayList<>(this.hotelRooms);
    }

    // ----------------------------------------------------------------------------

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

    // ----------------------------------------------------------------------------

    @Override
    public String toString () {
        return String.format("%s i %s%nEnkeltværselsespris: %d | Doubleværelsespris: %d",
                this.name, this.address, this.singlePrice, this.doublePrice);
    }
}
