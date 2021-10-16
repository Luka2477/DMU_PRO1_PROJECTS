package model;

import java.sql.SQLOutput;
import java.util.Scanner;

public class SongApp {
    public static void main(String[] args) {
        Song song1 = new Song("Hello", "Lukas Knudsen", 100);
        Song song2 = new Song("Lille Frække Frederik", "Bamse", 148);

        song1.printSong();
        song2.printSong();

        song1.setBpm(150);
        song1.printSong();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Vil du gerne indtaste en sang? ");
        String answer = scanner.nextLine();

        if(answer.equalsIgnoreCase("ja")) {
            System.out.print("\nVenligst indtaste sang titlen: ");
            String title = scanner.nextLine();

            System.out.print("Venligst indtaste sang artisten: ");
            String artist = scanner.nextLine();

            System.out.print("Venligst indtaste sang hastigheden (Bpm): ");
            int bpm = Integer.parseInt(scanner.nextLine());

            Song song3 = new Song(title, artist, bpm);
            System.out.println();
            song3.printSong();
        }

        scanner.close();
    }
}
