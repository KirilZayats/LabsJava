package labjava.by.main;

import by.labjava.dataconteiner.CarDataContainer;
import by.labjava.datastorage.dataexport.DataExport;
import by.labjava.datastorage.dataimport.TextFileDataSource;
import by.labjava.datastorage.services.ConsoleExportService;
import by.labjava.datastorage.services.FileExportService;
import by.lavjava.processor.DataStatistic;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("Set the path to the data file as a command line parameter!");
            System.exit(0);
        }

        ArrayList<CarDataContainer> data = new ArrayList<>();


        TextFileDataSource dataSource = new TextFileDataSource();
        System.out.println("Data file: '" + Path.of(args[0]) + "'");
        System.out.println("Reading data:");
        dataSource.LoadData(args[0], data);
        System.out.println("Statistics:"+ StringUtils.LF);
        Collection<String> colorInfo = new DataStatistic().carsByColor(data);
        Collection<String> modelInfo = new  DataStatistic().amountByModel(data);
        FileExportService fileExportService = new FileExportService();
        fileExportService.pushOnFile(colorInfo);
        fileExportService.pushOnFile(modelInfo);
        System.out.println(StringUtils.LF);
        ConsoleExportService consoleExportService = new ConsoleExportService();
        consoleExportService.outToConsole(colorInfo);
        System.out.println(StringUtils.LF);
        consoleExportService.outToConsole(modelInfo);
        System.out.println(StringUtils.LF);
        System.out.println("Services....");
        System.out.println(DataExport.findServices().toString());

    }
}
