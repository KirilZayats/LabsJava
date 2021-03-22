package co.by.food;

import co.by.main_pack.Consumable;


public abstract class Food implements Consumable,Nutritious {
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Food)) return false;
        if (name == null || ((Food)o).name==null) return false;
        Food food = (Food) o;
        return name.equals(((Food) o).name);
    }
    @Override
    public int calculateCalories(){
     return (int) name.toUpperCase().charAt(0)*100;
    }

    protected Food(String name) {
        this.name=name;
    }
}
