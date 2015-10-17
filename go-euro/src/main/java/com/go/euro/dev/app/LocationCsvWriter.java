
package com.go.euro.dev.app;

import com.go.euro.dev.model.GeoPosition;
import com.go.euro.dev.model.Location;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * This class is responsible for persisting a set of {@link Location} objects 
 * into CSV files.
 * </p>
 * @author Sabry <Muhammad.Sabry.Ali@gmail.com>
 */
public class LocationCsvWriter {
    
    public void write(
            final String outputLocation, 
            final List<Location> locations) throws IOException {
        
        FileWriter fileWriter = null;
        CSVPrinter csvPrinter = null;
                
        try {
            
            final CSVFormat csvFormat = CSVFormat.RFC4180;
            fileWriter = new FileWriter(outputLocation);

            csvPrinter = new CSVPrinter(fileWriter, csvFormat);
            csvPrinter.printRecord("Id, name, type, longitude, latitude");

            if (locations == null
                    || locations.isEmpty()) {
                return;
            }
            
            for (final Location location : locations) {
                
                final List<String> data = new ArrayList<>();
                data.add(location.getId() + "");
                data.add(location.getName());
                data.add(location.getType());
                
                final GeoPosition geoPosition = location.getGeoPosition();
                
                if (geoPosition != null) {
                    data.add(geoPosition.getLatitude() + "");
                    data.add(geoPosition.getLatitude() + "");
                } else {
                    data.add("");
                    data.add("");
                }
                
                csvPrinter.printRecord(StringUtils.join(data, ","));
            }
            
        } finally {//No catch, because want to still inform the caller of the IOException possibility
            /**
             * Better to use IOUtils, than nested try catch for each resource 
             * individually, each resource on its own could fail to close, thus
             * locking the other if in the same try/catch block.
             */
            IOUtils.closeQuietly(csvPrinter);
            IOUtils.closeQuietly(fileWriter);
        }
    }
}
