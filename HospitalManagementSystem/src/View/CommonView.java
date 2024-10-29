package View;

import java.util.Scanner;

public class CommonView {

    private static final Scanner sc = new Scanner(System.in);

	public static void newPage() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	public static void pressEnterToContinue() {
		System.out.println();
		System.out.print("[Press Enter key to continue]");
		sc.nextLine();
	}
}
