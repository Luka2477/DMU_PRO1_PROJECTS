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
        System.out.printf("Det samlede antal fraværsdage elev 1 har er %d | Forventet 8%n", fravaersSystem.samletFravaer(fravaer21S, 0));
        System.out.printf("Det gennemsnitte månedsfravær elev 1 har er %f | Forventet 0.667%n", fravaersSystem.gennemsnit(fravaer21S, 0));
        System.out.printf("Det samlede antal elever uden fravær hele året er %d | Forventet 1", fravaersSystem.antalUdenFravaer(fravaer21S));

        //TODO opgave 7

    }
}
