import java.util.Locale;
import java.util.Scanner;

public class Sal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();

        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();

        System.out.print("Enter HRA percentage (in %): ");
        double hraPercent = scanner.nextDouble();

        System.out.print("Enter DA percentage (in %): ");
        double daPercent = scanner.nextDouble();

        System.out.print("Enter PF percentage (in %): ");
        double pfPercent = scanner.nextDouble();

        System.out.print("Select locale (1-IN, 2-US, 3-CA): ");
        int localeChoice = scanner.nextInt();

        Locale locale = getLocale(localeChoice);

        double hra = salary * hraPercent / 100;
        double da = salary * daPercent / 100;
        double pf = salary * pfPercent / 100;
        double netSalary = salary + hra + da - pf;

        printSalarySlip(name, salary, hra, da, pf, netSalary, locale);
    }

    public static Locale getLocale(int choice) {
        switch (choice) {
            case 1:
                return new Locale("en", "IN");
            case 2:
                return new Locale("en", "US");
            case 3:
                return new Locale("fr", "CA");
            default:
                return new Locale("en", "IN");
        }
    }

    public static void printSalarySlip(String name, double salary, double hra, double da, double pf, double netSalary, Locale locale) {
        String currencySymbol = "";
        String language = "";

        switch (locale.getLanguage() + "_" + locale.getCountry()) {
            case "en_IN":
                currencySymbol = "₹";
                language = "English (India)";
                break;
            case "en_US":
                currencySymbol = "$";
                language = "English (United States)";
                break;
            case "fr_CA":
                currencySymbol = "CAD ";
                language = "Français (Canada)";
                break;
            default:
                currencySymbol = "";
                language = "Unknown";
        }

        System.out.println("Salary Slip (" + language + ")");
        System.out.println("-----------");
        System.out.printf(locale, "Name: %s\n", name);
        System.out.printf(locale, "Salary: %s%.2f\n", currencySymbol, salary);
        System.out.printf(locale, "HRA: %s%.2f\n", currencySymbol, hra);
        System.out.printf(locale, "DA: %s%.2f\n", currencySymbol, da);
        System.out.printf(locale, "PF: %s%.2f\n", currencySymbol, pf);
        System.out.printf(locale, "Net Salary: %s%.2f\n", currencySymbol, netSalary);
    }
}