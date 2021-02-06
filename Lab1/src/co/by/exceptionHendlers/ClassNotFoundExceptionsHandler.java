package co.by.exceptionHendlers;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ClassNotFoundExceptionsHandler {
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
