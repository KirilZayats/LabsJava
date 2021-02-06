package co.by.food;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class UtilsClass {
    public static void processingOfSpecialParam(Food[] breakfast, Set<String> parameters) {
        if (parameters.size() == 0) {
            for (Food item : breakfast) {
                if (item == null) break;
                item.consume();
            }
        }
        if (parameters.size() == 1) {
            if (parameters.contains("-sort")) {
                Scanner cin = new Scanner(System.in);
                System.out.println("""
                        Choose the type of sort...
                        1.By name length in direct oder;
                        2.By calories in reverse oder;
                        3.By quantity of additional parameters in reverse oder.""");
                if(!cin.hasNextInt()){
                    System.err.println("This is not integer!");
                }
                int typeOfSort = cin.nextInt();
                Food[] timely = sortFood(breakfast,typeOfSort);
                for (Food item : timely) {
                    if (item == null) break;
                    item.consume();
                }
            } else {
                if (parameters.contains("-calories")) {
                    for (Food item : breakfast) {
                        if (item == null) break;
                        item.consume();
                    }
                    System.out.println("In the breakfast were " + countNutritious(breakfast) + " calories");
                } else {
                    System.out.println("There is no such parameter...");
                    for (Food item : breakfast) {
                        if (item == null) break;
                        item.consume();
                    }
                }
            }
        }
    }
    public static int countOfEquals(Food food, Food[] breakfast) {
        int equals = 0;
        for (Food item : breakfast) {
            if (food.equals(item)) equals++;
        }
        return equals;
    }

    public static int countNutritious(Food[] breakfast) {
        int collieries = 0;
        for (Food item : breakfast) {
            collieries += item.calculateCalories();
        }
        return collieries;
    }

    public static Food[] sortFood(Food[] breakfast, int typeOfSort) {
        switch (typeOfSort) {
            case 1 -> {
                Arrays.sort(breakfast, (o1, o2) -> {
                    if (o1 == null) return 1;
                    if (o2 == null) return -1;
                    return o1.getName().compareTo(o2.getName());
                });
            }
            case 2 -> {
                Arrays.sort(breakfast, (o1, o2) -> {
                    if (o1 == null) return -1;
                    if (o2 == null) return 1;
                    return -Integer.compare(o1.calculateCalories(), o2.calculateCalories());
                });
            }
            case 3 -> {
                Arrays.sort(breakfast, (o1, o2) -> {
                    if (o1 == null) return -1;
                    if (o2 == null) return 1;
                    return -Integer.compare(o1.getClass().getFields().length, o2.getClass().getFields().length);
                });
            }
            default -> {
                System.out.println("There is no such type of sort!");
            }
        }
        return breakfast;
    }

    public static void createListOfExistClasses(String fileName){
        List<Class> classes=new Reflections("co.by.food.", new SubTypesScanner(false))
                .getAllTypes()
                .stream()
                .map(name -> {
                    try {
                        return Class.forName(name);
                    } catch (ClassNotFoundException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
                for(Class timely:classes)
                    bufferedWriter.write(timely.getName()+"\n");
                bufferedWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public static String[] getBreakfastList() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Commands.txt"));
            String[] list = new String[20];
            String str;
            int iter =0;
            while ((str=bufferedReader.readLine())!=null){
                list[iter++]=str;
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }
}
