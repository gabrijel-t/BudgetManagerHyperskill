package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    File file = new File("purchases.txt");

    public FileManager(){
        super();
        try{
            this.file.createNewFile();
        }catch(Exception e){
            System.out.println("Cannot create new file: " + file.getPath());
        }
    }

    public File getFile(){
        return file;
    }

    public void saveFile(BudgetManager budgetManager){
        double balance = budgetManager.getBalance();
        ArrayList<Item> items = budgetManager.getItemsList();
        try(PrintWriter printWriter = new PrintWriter(file)){
            printWriter.printf("Balance:%.2f\n", balance);
            for(Item item: items){
                int category = convertCategory(item.getCategory().toString());
                printWriter.printf("%s,%s,%.2f\n", category, item.getName(), item.getPrice());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("\nPurchases were saved!\n");
    }

    public void loadFile(BudgetManager budgetManager){
        try(Scanner scanner = new Scanner(file)){
            String balance = scanner.nextLine();
            if(balance.contains("Balance")){
                budgetManager.setBalance(Double.parseDouble(balance.split(":")[1]));
            }
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] data = line.split(",");
                int type = Integer.parseInt(data[0]);
                String item = data[1];
                double price = Double.parseDouble(data[2]);

                budgetManager.addItem(item, price, type);
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found!");
        }
        System.out.println("Purchases were loaded!\n");
    }

    private int convertCategory(String c){
        switch(c){
            case "FOOD":
                return 1;
            case "CLOTHES":
                return 2;
            case "ENTERTAINMENT":
                return 3;
            case "OTHER":
                return 4;
        }
        return 0;
    }
}
