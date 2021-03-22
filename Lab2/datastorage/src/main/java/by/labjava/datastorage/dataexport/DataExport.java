package by.labjava.datastorage.dataexport;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface DataExport {
    Map<String,Collection<String>> services = new HashMap<>();
    void identificationService(Collection<String> data);
    static  Map<String,Collection<String>>findServices() {
        return  services;
    }
}
