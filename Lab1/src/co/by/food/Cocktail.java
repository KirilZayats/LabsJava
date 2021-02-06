package co.by.food;


public class Cocktail extends Food  {
    private String drink;
    private String fruit;

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }


    public Cocktail(String drink, String fruit) {
        super("Cocktail");
        this.drink = drink;
        this.fruit = fruit;
    }

    @Override
    public void consume() {
        System.out.println("The " + this + "was drunk");
    }

    @Override
    public String toString() {
        return super.toString() + "with drink '" + drink.toUpperCase() + "' and fruit '" + fruit.toUpperCase() + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        if (!(o instanceof Cocktail)) return false;
        return drink.equals(((Cocktail) o).drink) || fruit.equals(((Cocktail) o).fruit);
    }

    @Override
    public int calculateCalories() {
        return (int)drink.toUpperCase().charAt(0)*10+(int)fruit.toUpperCase().charAt(fruit.length()-1)*10;
    }
}
