package afleveringfravaer;

import java.util.ArrayList;
import java.util.Arrays;

public class FravaersSystem {
	/**
	 * Fraværstallene udskrives på skærmen
	 *
	 * @param fravaer
	 */
	public void udskrivFravaer(int[][] fravaer) {
		// opgave 1

		System.out.print(" Måned :");
		for(int i=1; i<=fravaer[0].length; i++)
			System.out.printf("  %s", (i > 9) ? i : " " + i);

		System.out.printf("%n%s%s%n", " ".repeat(8), "-".repeat(fravaer[0].length * 4));

		for(int i=0; i<fravaer.length; i++) {
			System.out.printf("Elev %d :", i);

			for(int fravaersdage : fravaer[i])
				System.out.printf("   %d", fravaersdage);

			System.out.println();
		}
	}

	/**
	 * Returnerer det samlede antal fravaerdage over 12 måneder for eleven med
	 * elevnr.
	 *
	 * @param fravaer
	 * @param elevNr
	 * @return
	 */
	public int samletFravaer(int[][] fravaer, int elevNr) {
		// opgave 2
		return Arrays.stream(fravaer[elevNr]).sum();
	}

	/**
	 * Returnerer det gennemsnitlige antal fraværsdage pr måned for eleven med
	 * elevNr.
	 *
	 * @param fravaer
	 * @param elevNr
	 * @return
	 */
	public double gennemsnit(int[][] fravaer, int elevNr) {
		// opgave 3
		return (double) this.samletFravaer(fravaer, elevNr) / fravaer[elevNr].length;
	}


	/**
	 * Returnerer om alle tal i integer arrayen list er 0.
	 *
	 * @param list integer array som checkes for 0er.
	 * @return en boolean der besvarer om alle tal i arrayen er 0 eller ej
	 */
	private boolean allFalse(int[] list) {
		for (int number : list) if(number != 0) return false;
		return true;
	}

	/**
	 * Returnerer antallet af elever der ikke har haft fravær i de 12 måneder.
	 *
	 * @param fravaer
	 * @return
	 */
	public int antalUdenFravaer(int[][] fravaer) {
		// opgave 4
		ArrayList<Integer> tempArray = new ArrayList<>();

		for (int[] fravaersdage : fravaer) tempArray.add(!this.allFalse(fravaersdage) ? 0 : 1);

		return tempArray.stream().mapToInt(Integer::intValue).sum();
	}

	/**
	 * Returnerer elevNr for den elev der har haft mest fravær igennem de 12
	 * måneder. Hvis flere elever har haft højst fravær, returneres elevnummer
	 * for en af disse.
	 *
	 * @param fravaer
	 * @return
	 */
	public int mestFravaer(int[][] fravaer) {
		// TODO opgave 5
		return -1;
	}

	/**
	 * Registrerer at elenven med elevNr ikke har haft fravær i nogen af de 12
	 * måneder.
	 *
	 * @param fravaer
	 * @param elevNr
	 */
	public void nulstil(int[][] fravaer, int elevNr) {
		// TODO opgave 6
	}
}
