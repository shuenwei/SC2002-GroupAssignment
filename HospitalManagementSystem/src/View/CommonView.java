package View;

import java.util.Scanner;

public class CommonView {

    private static final Scanner sc = new Scanner(System.in);

	private CommonView() {
	};

	public static void pressEnterToContinue() {
		System.out.println("Press Enter key to continue...");
		sc.nextLine();
	}
}
