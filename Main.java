package budget;

import java.util.*;

public class Main {

    public static Scanner scan = new Scanner(System.in);
    static boolean exit;
    static BudgetManager budgetManager = new BudgetManager();
    static FileManager fileManager = new FileManager();

    public static void main(String[] args) {
        java.util.Locale.setDefault(Locale.US);
        while (!exit) {
            menu();
            chooseAction();
        }
    }

    public static void chooseAction() {
        int action = Integer.parseInt(scan.nextLine());
        System.out.println();

        switch (action) {
            case 1:
                addIncome();
                break;
            case 2:
                addPurchase();
                break;
            case 3:
                getPurchases();
                System.out.println();
                break;
            case 4:
                budgetManager.showBalance();
                break;
            case 5:
                fileManager.saveFile(budgetManager);
                break;
            case 6:
                fileManager.loadFile(budgetManager);
                break;
            case 7:
                chooseSort();
                break;
            case 0:
                exit();
        }
    }

    public static void menu() {
        System.out.println("Choose your action: \n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "5) Save \n" +
                "6) Load \n" +
                "7) Analyze (Sort)\n" +
                "0) Exit");
    }

    public static void showMenu() {
        System.out.println("Choose the type of purchases\n" +
                "1) Food \n" +
                "2) Clothes \n" +
                "3) Entertainment \n" +
                "4) Other \n" +
                "5) All \n" +
                "6) Back");
    }

    public static void typeMenu() {
        System.out.println("Choose the type of purchase \n" +
                "1) Food\n" +
                "2) Clothes \n" +
                "3) Entertainment \n" +
                "4) Other \n" +
                "5) Back");
    }

    public static void addIncome() {
        double income;
        System.out.println("Enter income: ");
        if (scan.hasNextInt() || scan.hasNextDouble()) {
            income = Double.parseDouble(scan.nextLine());
            budgetManager.addToBalance(income);
        }
    }


    public static void addPurchase() {
        while (true) {
            String type;
            String item;
            String price;
            typeMenu();
            type = scan.nextLine();
            System.out.println();
            if (Integer.parseInt(type) >= 5) {
                System.out.println();
                break;
            }
            System.out.println("Enter purchase name:");
            item = scan.nextLine();
            System.out.println("Enter its price:");
            price = scan.nextLine();

            try {
                double dPrice = Double.parseDouble(price);
                int iType = Integer.parseInt(type);
                budgetManager.addItem(item, dPrice, iType);
                System.out.println("Purchase was added!");
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void getPurchases() {
        while (true) {
            showMenu();
            int type = Integer.parseInt(scan.nextLine());
            if (type == 6 || type < 1 || type > 6) {
                System.out.println();
                return;
            } else {
                System.out.println();
                switch (type) {
                    case 1:
                        budgetManager.showPurchaseList("Food");
                        break;
                    case 2:
                        budgetManager.showPurchaseList("Clothes");
                        break;
                    case 3:
                        budgetManager.showPurchaseList("Entertainment");
                        break;
                    case 4:
                        budgetManager.showPurchaseList("Other");
                        break;
                    case 5:
                        budgetManager.showPurchaseList();
                        break;
                }
            }
            System.out.println();
        }
    }

    private static void chooseSort() {
        while (true) {
            System.out.println("How do you want to sort?");
            analyzeMenu();
            int method = Integer.parseInt(scan.nextLine());
            if (method == 4 || method < 1 || method > 4) {
                System.out.println();
                return;
            } else {
                System.out.println();
                switch (method) {
                    case 1:
                        budgetManager.sortAllPurchases();
                        break;
                    case 2:
                        budgetManager.sortAllTypes();
                        break;
                    case 3:
                        System.out.println("\nChoose the type of purchase\n" +
                                "1) Food\n" +
                                "2) Clothes\n" +
                                "3) Entertainment\n" +
                                "4) Other");
                        int type = Integer.parseInt(scan.nextLine());
                        budgetManager.sortType(type);
                        break;
                }
            }
        }
    }

    public static void exit() {
        System.out.println("Bye!");
        exit = true;
    }

    public static void analyzeMenu() {
        System.out.println("1) Sort all purchases \n" +
                "2) Sort by type \n" +
                "3) Sort certain type \n" +
                "4) Back");
    }
}