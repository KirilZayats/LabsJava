package co.by.food;

import co.by.pathAndFiles;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class UtilsClass {
    /**
     * A method responsible for eating food with preprocessing of the food array according to the passed parameters:
     * -sort - sorting the food array according to the selected option:
     * 1.By name length in direct oder;
     * 2.By calories in reverse oder;
     * 3.By quantity of additional parameters in reverse oder.
     * -calories - counting the total number of calories in foods contained in a food array
     * If there are no special parameters, then the use of food without pretreatment
     *
     * @param breakfast  - the array of food that should be consumed
     * @param parameters - list of special settings that need to be applied to the array of food
     */
    public static void processingOfSpecialParam(Food[] breakfast, Set<String> parameters) {

        if (parameters.contains("-sort"))
            breakfast = UiSort(breakfast);
        consumer(breakfast);
        if (parameters.contains("-calories")) {
            System.out.println("In the breakfast were " + countNutritious(breakfast) + " calories");
        }
    }

    public static Food[] UiSort(Food[] breakfast) {
        Scanner cin = new Scanner(System.in);
        System.out.println("""
                Choose the type of sort...
                1.By name length in direct oder;
                2.By calories in reverse oder;
                3.By quantity of additional parameters in reverse oder.""");
        if (!cin.hasNextInt()) {
            System.err.println("This is not integer!");
        }
        int typeOfSort = cin.nextInt();
        return sortFood(breakfast, typeOfSort);
    }

    public static void consumer(Food[] breakfast) {
        for (Food item : breakfast) {
            if (item == null) break;
            item.consume();
        }
    }

    /**
     * The method compares the 'food' object by internal fields with
     * the elements of the "breakfast" array and returns the number of equal elements
     *
     * @param food      - the product to compare the products from the "breakfast" array with
     * @param breakfast - an array of products to search for a product in,
     *                  with the same internal fields as the product 'food'
     * @return quantity of equal food
     */
    public static int countOfEquals(Food food, Food[] breakfast) {
        int equals = 0;
        for (Food item : breakfast) {
            if (food.equals(item)) equals++;
        }
        return equals;
    }


    /**
     * Calculates the total number of calories in the "breakfast" array
     * using the methods of the inheriting classes of the food class
     *
     * @param breakfast - аn array of foods to count the total number of calories in
     * @return total number of calories
     */
    public static int countNutritious(Food[] breakfast) {
        int collieries = 0;
        for (Food item : breakfast) {
            if (item != null)
                collieries += item.calculateCalories();
        }
        return collieries;
    }

    /**
     * Sorting the food array according to the selected option:
     * 1.By name length in direct oder;
     * 2.By calories in reverse oder;
     * 3.By quantity of additional parameters in reverse oder.
     *
     * @param breakfast  - array to sort
     * @param typeOfSort - sorting option number
     * @return sorted array "breakfast"
     */
    public static Food[] sortFood(Food[] breakfast, int typeOfSort) {
        switch (typeOfSort) {
            case 1 -> {
                Arrays.sort(breakfast, (o1, o2) -> {
                    if (o1 == null) return 1;
                    if (o2 == null) return -1;
                    return Integer.compare(o1.getName().length(), o2.getName().length());
                });
            }
            case 2 -> {
                Comparator<Food> c = (o1, o2) -> {
                    if (o1 == null) return 1;
                    if (o2 == null) return -1;
                    return -Integer.compare(o1.calculateCalories(), o2.calculateCalories());
                };
                Arrays.sort(breakfast,c);
            }
            case 3 -> {
                Arrays.sort(breakfast, (o1, o2) -> {
                    if (o1 == null) return 1;
                    if (o2 == null) return -1;
                    return -Integer.compare(o1.getClass().getFields().length, o2.getClass().getFields().length);
                });
            }
            default -> {
                System.out.println("There is no such type of sort!");
            }
        }
        return breakfast;
    }

    /**
     * The method reads class entities from the specified package,reads class names, and writes them to a file "fileName"
     *
     * @param fileName - the name of the file in which the names of the existing classes in the package will be written
     */
    public static void createListOfExistClasses(String fileName) {
        List<Class> classes = new Reflections(pathAndFiles.WAY_TO_FOOD.getPath(),
                new SubTypesScanner(false))
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
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));) {
            for (Class timely : classes)
                bufferedWriter.write(timely.getName() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method reads commands for creating objects of a certain classes of inheritors from the specified file
     *
     * @return array of commands for creating objects of certain classes of inheritors
     */
    public static String[] getBreakfastList() {
        try ( BufferedReader bufferedReader = new BufferedReader(new FileReader("Commands.txt"));){
            String[] list = new String[20];
            String str;
            int iter = 0;
            while ((str = bufferedReader.readLine()) != null) {
                list[iter++] = str;
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }
}
