
package com.go.euro.dev.app.provider;

import com.go.euro.dev.model.Location;
import java.util.List;

/**
 * <p>
 * A simple interface to be implemented by the different location data providers.
 * </p>
 * @author Sabry <Muhammad.Sabry.Ali@gmail.com>
 */
public interface LocationServiceProvider {
    
    /**
     * <p>
     * This method allows each provider to has its own set of validation rules
     * on the search query.
     * </p>
     */
    public boolean isValidQuery(final String query);
    
    /**
     * <p>
     * The entry point for interaction with a location provider, where a list of
     * matching results is to be returned if any. Each provider is responsible
     * for ensuring that the contract does not break, and that their data is
     * mapped correctly to the Location model.
     * </p>
     */
    public List<Location> search(final String query);
}
