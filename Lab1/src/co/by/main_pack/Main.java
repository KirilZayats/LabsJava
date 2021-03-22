package co.by.main_pack;

import co.by.food.Dessert;
import co.by.food.Food;
import co.by.food.UtilsClass;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import co.by.pathAndFiles;

import static co.by.exceptionHendlers.ClassNotFoundExceptionsHandler.isSuchClassExist;

/**
 * Student Kiril Zayats
 * group 4
 */

public class Main {

    public static void main(String[] args) {
        args = UtilsClass.getBreakfastList();
        Food[] breakfast = new Food[20];
        UtilsClass.createListOfExistClasses(pathAndFiles.CLASSES_NAME_FILE.getPath());
        Set<String> specialParam = new HashSet<>();
        for (int argsIndex = 0, itemIndex = 0; argsIndex < args.length; argsIndex++) {
            if (args[argsIndex] == null) {
                continue;
            }
            if (args[argsIndex].charAt(0) == '-') {
                specialParam.add(args[argsIndex]);
                continue;
            }
            String[] parts = args[argsIndex].split("/");

            //Checks and handles exceptions "ClassNotFoundException" and "NoSuchMethodException"
            //  if (!isSuchClassExist(parts, pathAndFiles.CLASSES_NAME_FILE.getPath())) {
            //      continue;
            //  }
            //creating and pushing objects of the class inheritors of the 'Food' class
            try {
                Class myClass = Class.forName(pathAndFiles.WAY_TO_FOOD.getPath() + parts[0]);
                if (parts.length == 1) //if quantity of parameters is 0
                    breakfast[itemIndex++] = (Food) myClass.getConstructor()
                            .newInstance();
                if (parts.length == 2) //if quantity of parameters is 1
                    breakfast[itemIndex++] = (Food) myClass
                            .getConstructor(String.class)
                            .newInstance(parts[1]);
                if (parts.length == 3) //if quantity of parameters is 2
                    breakfast[itemIndex++] = (Food) myClass
                            .getConstructor(String.class, String.class)
                            .newInstance(parts[1], parts[2]);
            } catch (Exception e) {
                System.err.println("this product '" + parts[0] + "' can't be added!");
            }
        }

        //output with the execution of special parameters
        UtilsClass.processingOfSpecialParam(breakfast, specialParam);
        Map<String, Integer> map = new HashMap<>();
        System.out.println("\nQuantity of  equal products :");
       for (Food food : breakfast) {
           if (food==null || map.containsKey(food.toString())) continue;
           map.put(food.toString(), UtilsClass.countOfEquals(food, breakfast));
           System.out.println(food.toString() +": "+map.get(food.toString()));
       }
        System.out.println("\nGood luck!");
    }
}
