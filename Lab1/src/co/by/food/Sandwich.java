package co.by.food;


public class Sandwich extends Food  {

    private String filling1;
    private String filling2;

    protected Sandwich(String filling1, String filling2) {
        super("Sandwich");
        this.filling1 = filling1;
        this.filling2 = filling2;
    }

    public String getFilling1() {
        return filling1;
    }

    public void setFilling1(String filling1) {
        this.filling1 = filling1;
    }

    public String getFilling2() {
        return filling2;
    }

    public void setFilling2(String filling2) {
        this.filling2 = filling2;
    }

    @Override
    public void consume() {
        System.out.println("The " + this + "was eaten");
    }

    @Override
    public String toString() {
        return super.toString() + "with the first filling '" + filling1.toUpperCase() +
                "' and with the second filling '" + filling2.toUpperCase() + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        if (!(o instanceof Sandwich)) return false;
        return filling1.equals(((Sandwich) o).filling1) || filling2.equals(((Sandwich) o).filling2);
    }

    @Override
    public int calculateCalories() {
        return (int)filling1.toLowerCase().charAt(0)*10+(int)filling2.toLowerCase().charAt(filling2.length()-1)*10;
    }
}
