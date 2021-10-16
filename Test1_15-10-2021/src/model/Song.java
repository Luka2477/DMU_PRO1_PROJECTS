package model;

public class Song {
    private final String title;
    private final String artist;
    private int bpm;

    public Song(String title, String artist, int bpm) {
        this.title = title;
        this.artist = artist;
        this.bpm = bpm;
    }

    public String getTitle() { return this.title; }
    public String getArtist() { return this.artist; }
    public int getBpm() { return this.bpm; }

    public void setBpm(int bpm) { this.bpm = bpm; }

    public String tilpas(int bpm) { return (this.bpm < bpm) ? "langsom" : (this.bpm == bpm) ? "perfekt" : "hurtig"; }

    public void printSong() {
        System.out.println("*********************************");
        System.out.printf("Title\t\t: %s%n", this.title);
        System.out.printf("Kunstner\t: %s%n", this.artist);
        System.out.printf("Hastighed\t: %d (Bpm)%n", this.bpm);
        System.out.println("*********************************");
    }
}
