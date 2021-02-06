package co.by.main_pack;

import co.by.food.Food;
import co.by.food.UtilsClass;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static co.by.exceptionHendlers.ClassNotFoundExceptionsHandler.isSuchClassExist;

public class Main {
    public static final String CLASSES_NAME_FILE = "Classes.txt";

    public static void main(String[] args) {
       // args=UtilsClass.getBreakfastList();
        Food[] breakfast = new Food[20];
        UtilsClass.createListOfExistClasses(CLASSES_NAME_FILE);
        Set<String> specialParam = new HashSet<>();
        for (int argsIndex = 0, itemIndex = 0; argsIndex < args.length; argsIndex++) {
            if (args[argsIndex].charAt(0) == '-') {
                specialParam.add(args[argsIndex]);
                continue;
            }
            String[] parts = args[argsIndex].split("/");
            if (!isSuchClassExist(parts,CLASSES_NAME_FILE)) {
                continue;
            }
            try {
                Class myClass = Class.forName("co.by.food." + parts[0]);
                if (parts.length == 1)
                    breakfast[itemIndex++] = (Food) myClass.getConstructor()
                            .newInstance();
                if (parts.length == 2)
                    breakfast[itemIndex++] = (Food) myClass
                            .getConstructor(String.class)
                            .newInstance(parts[1]);
                if (parts.length == 3)
                    breakfast[itemIndex++] = (Food) myClass
                            .getConstructor(String.class, String.class)
                            .newInstance(parts[1], parts[2]);
            } catch (Exception e) {
                System.err.println("this product '" + parts[0] + "' can't be added!");
            }
        }
        UtilsClass.processingOfSpecialParam(breakfast, specialParam);
        System.out.println("Good luck!");
    }
}
