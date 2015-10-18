
package com.go.euro.dev.app;

import com.go.euro.dev.app.provider.LocationServiceProvider;
import com.go.euro.dev.model.Location;
import java.util.List;

/**
 * <p>
 * This class consumes the web service, and searches for a given location
 * string.
 * </p>
 * @author Sabry <Muhammad.Sabry.Ali@gmail.com>
 */
public class LocationServiceConsumer {
    
    private final LocationServiceProvider serviceProvider;

    /**
     * <p>
     * Constructs a new instance using the given {@code serviceProvider}. This
     * will throw an {@link IllegalStateException} if the given
     * {@code serviceProvider} is null.
     * </p>
     */
    public LocationServiceConsumer(final LocationServiceProvider serviceProvider) {
        if (serviceProvider == null) {
            throw new IllegalStateException("Cannot construct a consumer with no search provider.");
        }
        
        this.serviceProvider = serviceProvider;
    }
    /**
     * <p>
     * Uses the {@link LocationServiceProvider} validation methods to validate
     * the given search query, and throws an {@link IllegalArgumentException} if
     * validation fails, else will return a list of locations that did match the
     * search if any.
     * </p>
     */
    public List<Location> search(final String query) throws IllegalArgumentException {
        
        if (!serviceProvider.isValidQuery(query)) {//Should pass out the criteria back to the user
            throw new IllegalArgumentException("Given search query does not match criteria");
        }
        
        return serviceProvider.search(query);
    }
}
