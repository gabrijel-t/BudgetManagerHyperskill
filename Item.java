package budget;

public class Item{

    private String name;
    private double price;
    private PurchaseCategory category;

    public Item(String name, double price, int cat){
        this.name = name;
        this.price = price;

        switch(cat){
            case 1:
                this.category = PurchaseCategory.FOOD;
                break;
            case 2:
                this.category = PurchaseCategory.CLOTHES;
                break;
            case 3:
                this.category = PurchaseCategory.ENTERTAINMENT;
                break;
            case 4:
                this.category = PurchaseCategory.OTHER;
                break;
        }
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public PurchaseCategory getCategory(){
        return category;
    }
}