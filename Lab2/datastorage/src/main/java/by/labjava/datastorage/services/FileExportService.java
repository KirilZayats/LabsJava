package by.labjava.datastorage.services;

import by.labjava.datastorage.dataexport.DataExport;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class FileExportService implements DataExport {
    @Override
    public void identificationService(Collection<String> data) {
        services.put(this.getClass().getName(),data);
    }

    public void pushOnFile(Collection<String> data) {
        identificationService(data);
        System.out.print("export to file...");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.getClass().getName()+".txt",true))){
            for (String inf:data) {
                bufferedWriter.write(inf+ StringUtils.LF);
            }
            System.out.println("-> done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
