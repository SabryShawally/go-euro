
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
        
        final String userHome = System.getProperty("user.home");
        final String fileName = userHome + File.separator + "locations.csv";
            
        try {
            final LocationCsvWriter csvWriter = new LocationCsvWriter();
            csvWriter.write(fileName, locations);
        } catch (final Exception ex) {
            log.log(Level.SEVERE, "Exception occured writing CSV file", ex);
        }
        
        System.out.println(
                String.format(
                        "[%d] Locations found, results stored in [%s]", 
                        locations.size(), 
                        fileName));
    }
}
