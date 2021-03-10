package co.by.food;

public class Dessert extends Food  {
    private String component1;
    private String component2;

    public Dessert(String component1, String component2) {
        super("Dessert");
        this.component1 = component1;
        this.component2 = component2;
    }

    public String getComponent1() {
        return component1;
    }

    public void setComponent1(String component1) {
        this.component1 = component1;
    }

    public String getComponent2() {
        return component2;
    }

    public void setComponent2(String component2) {
        this.component2 = component2;
    }

    @Override
    public void consume() {
        System.out.println("The " + this + " was eaten");
    }

    @Override
    public String toString() {
        return super.toString() + " with the first component '" + component1.toUpperCase() +
                "' and the second component '" + component2.toUpperCase() + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        if (!(o instanceof Dessert)) return false;
        return component1.equals(((Dessert) o).component1) && component2.equals(((Dessert) o).component2);
    }

    @Override
    public int calculateCalories() {
        return (int) component1.toLowerCase().charAt(0) * 10 +
                (int) component2.toLowerCase().charAt(component2.length() - 1);
    }

    @Override
    public String getName() {
        return (super.getName()+this.getComponent1()+this.getComponent2());
    }
}
