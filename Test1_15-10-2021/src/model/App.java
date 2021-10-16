package model;

import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("Pax", "Fido", "Molly", "Pluto", "Juno"));
        System.out.println(arrayList);

        arrayList.set(2, "King");
        System.out.println(arrayList);

        arrayList.remove(1);
        System.out.println(arrayList);

        arrayList.add(3, "Trine");
        System.out.println(arrayList);

        arrayList.add(0, "Bella");
        System.out.println(arrayList);

        System.out.println(arrayList.size());

        int fiveBogstaver = 0;
        for (String name : arrayList)
            if (name.length() == 5)
                fiveBogstaver++;
        System.out.println(fiveBogstaver);

        for (int i=arrayList.size()-1; i>=0; i--)
            System.out.println(arrayList.get(i));

        for (int i=0; i<arrayList.size(); i+=2)
            System.out.println(arrayList.get(i));
    }
}
