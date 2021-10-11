package afleveringfravaer;

public class AfproevFravaersSystem {

    public static void main(String[] args) {
        int[][] fravaer21S = {
                { 2, 0, 0, 0, 3, 1, 0, 2, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 2, 0, 0, 0, 3, 1, 0, 2, 0, 0, 0, 0 },
                { 1, 2, 1, 2, 1, 2, 0, 2, 0, 0, 4, 0 },
                { 5, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0 }
        };

        FravaersSystem fravaersSystem = new FravaersSystem();
        fravaersSystem.udskrivFravaer(fravaer21S);
        System.out.println();

        // opgave 7
        System.out.printf("Det samlede antal fraværsdage elev 1 har er %d           | Forventet 8%n", fravaersSystem.samletFravaer(fravaer21S, 0));
        System.out.printf("Det gennemsnitte månedsfravær elev 1 har er %f    | Forventet 0.667%n", fravaersSystem.gennemsnit(fravaer21S, 0));
        System.out.printf("Det samlede antal elever uden fravær hele året er %d     | Forventet 1%n", fravaersSystem.antalUdenFravaer(fravaer21S));
        System.out.printf("Den elev med mest fravær er elev %d                      | Forventet 4%n", fravaersSystem.mestFravaer(fravaer21S) + 1);
        System.out.println();
        fravaersSystem.nulstil(fravaer21S, 3);
        fravaersSystem.udskrivFravaer(fravaer21S);
        System.out.println();
        System.out.printf("Det samlede antal elever uden fravær hele året er %d     | Forventet 2%n", fravaersSystem.antalUdenFravaer(fravaer21S));
        System.out.printf("Den elev med mest fravær er elev %d                      | Forventet 5%n", fravaersSystem.mestFravaer(fravaer21S) + 1);
    }
}
