package co.by.food;


public class Cheese extends Food  {

    public Cheese() {
        super("Cheese");
    }

    @Override
    public void consume() {
        System.out.println("The '" + this.toString().toUpperCase() + "' was eaten");
    }

    @Override
    public int calculateCalories() {
        return (int) getName().toLowerCase().charAt(0) * 10 +
                (int) getName().toLowerCase().charAt(getName().length() - 1) + 10;
    }
}
