package by.labjava.dataconteiner;


import lombok.Getter;

@Getter
public class CarDataContainer {
    private final String model;
    private final String color;

    public CarDataContainer(String fileString) throws IncorrectCarDataException {
        String[] carProperty = fileString.strip().split(":");
        if (carProperty[0].isEmpty()) throw new IncorrectCarDataException("model");
        if (carProperty.length==1) throw new IncorrectCarDataException("color");
        this.model = carProperty[0];
        this.color = carProperty[1];
    }


    @Override
    public String toString() {
        return  "model='" + model + '\'' +
                ", color='" + color + '\'' ;
    }


}
