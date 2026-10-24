package util;

import java.util.Scanner;

/** Reads and validates user input so the program never crashes on bad entries. */
public class InputHelper {
    private final Scanner scanner;

    public InputHelper(Scanner scanner) { this.scanner = scanner; }

    public String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) return line;
            System.out.println("  Input cannot be empty.");
        }
    }

    public int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) return value;
                System.out.println("  Enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("  Invalid number '" + line + "'.");
            }
        }
    }
}
