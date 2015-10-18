
package com.go.euro.dev.app;

import com.go.euro.dev.app.provider.GoEuroLocationServiceProvider;
import com.go.euro.dev.model.Location;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * This class represents the entry point for the location search operation.
 * </p>
 * @author Sabry <Muhammad.Sabry.Ali@gmail.com>
 */
public class LocationSearchRunner {
    
    private final static Logger log = Logger.getLogger(LocationSearchRunner.class.getName());
    
    public static void main(final String[] args) {
        
        if (args == null
                || args.length == 0
                || args[0] == null) {
            throw new IllegalArgumentException("Please provide your search query");
        }
        
        final String query = args[0];
        
        final LocationServiceConsumer searchServiceConsumer
                = new LocationServiceConsumer(new GoEuroLocationServiceProvider());
        
        final List<Location> locations
                = searchServiceConsumer.search(query);
        
        try {
            final LocationCsvWriter csvWriter = new LocationCsvWriter();
            
            final String userHome = System.getProperty("user.home");
            final String fileName = userHome + File.separator + "locations.csv";
            
            System.out.println(fileName);
            
            csvWriter.write(fileName, locations);
        } catch (final Exception ex) {
            log.log(Level.SEVERE, "Exception occured writing CSV file", ex);
        }
        
        for (final Location location : locations) {
            System.out.println("Location name = " + location.getName()
                    + " type = " + location.getType()
                    + " id = " + location.getId() 
                    + " GeoPosition = " + location.getGeoPosition()
                    + " Longitude = " + location.getGeoPosition().getLongitude()
                    + " Latitude = " + location.getGeoPosition().getLatitude());
        }
    }
}
