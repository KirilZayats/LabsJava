package co.by.exceptionHendlers;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ClassNotFoundExceptionsHandler {
    /**
     *Compares the passed class name with the names of existing classes,if there is a match by name,
     *  then compares the number of passed parameters with the number of parameters in the matched class name
     * @param parts - array containing the class name and the values of its fields
     * @param fileName - the name of the file that stores the full names of existing classes
     * @return  the value "false" if the class with the passed name or fields is not in the
     * desired package or such a class does not exist, "true"-the class with the specified name and fields
     * is in the specified package
     */
    public static boolean isSuchClassExist(String[] parts,String fileName) {
        List<String> lisOfClasses =new LinkedList<>();
        listOfExistClasses(lisOfClasses,fileName);
        for (String clss:lisOfClasses){
            if(parts[0].equals(clss.split("\\.")[clss.split("\\.").length - 1])){
                try {
                    Class cl=Class.forName(clss);
                    int quantity=cl.getDeclaredFields().length;
                    if(parts.length-1==quantity){
                        return true;
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Class '"+ Arrays.toString(parts) +"' doesn't exists!");
        return false;
    }

    /**
     *Reads the names of existing classes from a file named "fileName" and puts them in the passed list "classes".
     * If, after reading, the list is empty, then the program completely terminates with an error
     * @param classes a List of Strings that will contain the full names of the classes
     *               that are currently present in the package
     * @param fileName the name of the file that stores the full class names
     *                 that are currently present in the package
     */
    public static void listOfExistClasses(List<String> classes,String fileName ) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            String string;
            while ((string = fileReader.readLine()) != null) {
                classes.add(string);
            }
            if(classes.isEmpty()){
                System.err.println("There are no classes!");
                System.exit(-1);
            }
            fileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
