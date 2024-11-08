package View;

import java.util.Scanner;

/**
 * The CommonView class provides utility methods for common UI tasks, such as printing formatted
 * separators and handling user input.
 * It is used across different parts of the application to maintain consistency in UI formatting.
 */
public class CommonView {

	// Static scanner object for user input
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Prints a separator line to indicate a new page or section in the UI.
     * This is useful for improving readability and separating different sections of the UI.
     */
	public static void newPage() {
		System.out.println("\n**********************************************************************************************************************\n");
	}

}
