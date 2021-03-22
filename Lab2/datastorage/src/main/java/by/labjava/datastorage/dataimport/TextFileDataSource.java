package by.labjava.datastorage.dataimport;

import by.labjava.dataconteiner.CarDataContainer;
import by.labjava.dataconteiner.IncorrectCarDataException;

import java.io.*;
import java.util.List;

public class TextFileDataSource {
    public void LoadData(String filename, List<CarDataContainer> data) {
        try (BufferedReader src = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = src.readLine()) != null) {
                try {
                    data.add(new CarDataContainer(line));
                    System.out.println(" '" + data.get(data.size() - 1) + "' -> done");
                } catch (IncorrectCarDataException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File '" + filename + "' was not found.");
        } catch (IOException e) {
            System.out.println("Error with file work '" + filename + "'.");
        }
    }
}


