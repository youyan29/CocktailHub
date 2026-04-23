package Manage;

public class Cocktail {
    private String ingredient;
    private String price;
    private String name;
    private String flavor;

    public Cocktail(String ingredient, String price, String name, String flavor) {
        this.ingredient = ingredient;
        this.price = price;
        this.name = name;
        this.flavor = flavor;
    }

    @Override
    public String toString() {
        return ingredient + " - " + price + " " + name + " (" + flavor + ")";
    }
}
