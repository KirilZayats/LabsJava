package by.labjava.datastorage.services;

import by.labjava.datastorage.dataexport.DataExport;

import java.util.Collection;

public class ConsoleExportService implements DataExport {
    @Override
    public void identificationService(Collection<String> data) {
        services.put(this.getClass().getName(),data);
    }


    public void outToConsole(Collection<String> data){
        System.out.println("out to console...");
        identificationService(data);
        for (String inf:data) {
            System.out.println(inf);
        }
    }
}
