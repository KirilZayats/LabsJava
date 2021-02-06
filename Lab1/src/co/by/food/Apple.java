package co.by.food;

public class Apple extends Food {
    private String size;

    public Apple(String size) {
        super("Apple");
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public void consume() {
        System.out.println("The " + this + " was eaten");
    }

    @Override
    public String toString() {
        return super.toString() + " of '" + size.toUpperCase() + "' size";
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        if (!(o instanceof Apple)) return false;
        return size.equals(((Apple) o).size);
    }

    @Override
    public int calculateCalories() {
        return (int)size.toUpperCase().charAt(0)*(int)getName().toUpperCase().charAt(0);
    }
}
