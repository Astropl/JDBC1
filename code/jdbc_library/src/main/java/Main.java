
import config.Config;
import service.JDBCService;
import service.PostgreSQLService;

import java.util.Scanner;

public class Main {

    private static Menu menu = new Menu();

    public static void main(String[] args) {
        Config config = new Config("library", "jdbc:mysql://localhost/library", "root", "@nXAweCS9pb");
        JDBCService service = new PostgreSQLService(config);
        while (true) {
            menu.showMenu();
            int menuOption = menu.chooseMenuOption();
            System.out.println("Menu option: " + menuOption);
            switch (menuOption) {
                case 1:
                    System.out.println("Option 1");
                    service.connect();
                    break;
                case 2:
                    System.out.println("Option 2");
                    service.disconnect();
                    break;
                case 5:
                    System.out.println("Closing app!");
                    System.exit(0);
                    break;
                default:
            }
        }
    }

    private static class Menu {

        void showMenu() {
            System.out.println("Choose option");
            System.out.println("1. Establish connection: ");
            System.out.println("2. Close connection");
            System.out.println("3. Create Book table");
            System.out.println("4. Create Author table");
            System.out.println("5. Close");
            //add other necessary menu options
        }

        int chooseMenuOption() {
            Scanner reader = new Scanner(System.in);
            System.out.println("Enter a menu option: ");
            return reader.nextInt();
        }
    }
}
