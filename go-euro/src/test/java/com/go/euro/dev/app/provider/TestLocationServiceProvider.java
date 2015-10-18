
package com.go.euro.dev.app.provider;

import com.go.euro.dev.model.GeoPosition;
import com.go.euro.dev.model.Location;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * A simple mock for a service provider for the search, since it's ideal to not
 * rely on external resources for test cases.
 * </p>
 * @author Sabry <Muhammad.Sabry.Ali@gmail.com>
 */
public class TestLocationServiceProvider implements LocationServiceProvider {

    @Override
    public boolean isValidQuery(final String query) {
        return StringUtils.isNotBlank(query) && StringUtils.isAlphanumericSpace(query);
    }

    @Override
    public List<Location> search(final String query) {
        //TODO: Should be more robust to allow for more queries
        final List<Location> locations = new ArrayList<>();
        
        final Location location1 = new Location();
        location1.setId(1234L);
        location1.setName("Location 1");
        location1.setType("Airport");
        
        final GeoPosition geoPosition1 = new GeoPosition();
        geoPosition1.setLatitude(4321F);
        geoPosition1.setLatitude(1234F);
        
        location1.setGeoPosition(geoPosition1);
        
        locations.add(location1);
        
        return locations;
    }
}
