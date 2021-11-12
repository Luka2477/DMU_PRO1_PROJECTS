package application.model;

import java.util.ArrayList;

public class Hotel {

    private String name;
    private String address;

    private final ArrayList<Conference> conferences = new ArrayList<>();
    private final ArrayList<HotelRoom> hotelRooms;
    private final ArrayList<AddOn> addOns = new ArrayList<>();

    public Hotel (String name, String address, ArrayList<HotelRoom> hotelRooms) {
        this.name = name;
        this.address = address;

        this.hotelRooms = hotelRooms;
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

    public HotelRoom createHotelRoom (int nr, int price, boolean isSingle) {
        HotelRoom hotelRoom = new HotelRoom(nr, price, isSingle);
        this.hotelRooms.add(hotelRoom);
        return hotelRoom;
    }

    public void removeHotelRoom (HotelRoom hotelRoom) {
        this.hotelRooms.remove(hotelRoom);
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
}
