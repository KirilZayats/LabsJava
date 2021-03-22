package by.lavjava.processor;

import by.labjava.dataconteiner.CarDataContainer;

import java.util.*;


public class DataStatistic {
    public Collection<String> carsByColor(ArrayList<CarDataContainer> data) {
        HashSet<String> colors = new HashSet<>();
        ArrayList<String> collection = new ArrayList<>();
        for (CarDataContainer car : data) {
            colors.add(car.getColor());
        }
        for (String color : colors) {
            LinkedList<String> equalColorCar = new LinkedList<>();
            HashSet<String> nonRepeatCar = new HashSet<>();
            for (CarDataContainer car : data) {
                if (color.equals(car.getColor())) {
                    equalColorCar.add(car.getModel());
                    nonRepeatCar.add(car.getModel());
                }
            }
            String[] countArray = new String[nonRepeatCar.size()];
            int index = 0;
            for (String model : nonRepeatCar) {
                int count = 0;
                for (String carModel : equalColorCar) {
                    if (carModel.equals(model)) count++;
                }
                countArray[index++] = String.valueOf(count);
            }
            index = 0;
            color=color.concat(": ");
            for (String model : nonRepeatCar) {
               color= color.concat(String.join(" ", model, (String) countArray[index++])+",");
            }
            collection.add(color);
        }
        return collection;
    }

    public Collection<String> amountByModel(ArrayList<CarDataContainer> data) {
        SortedSet<String> cars = new TreeSet<>();
        ArrayList<String> collection = new ArrayList<>();
        for (CarDataContainer car : data) {
            cars.add(car.getModel());
        }

        for (String car : cars) {
            int count =0;
            for (CarDataContainer nonSortedCar : data) {
                if (car.equals(nonSortedCar.getModel())) count++;
            }
            collection.add(car+ ": " + count);
        }
      return collection;
    }
}
