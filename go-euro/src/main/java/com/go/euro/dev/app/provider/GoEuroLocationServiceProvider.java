
package com.go.euro.dev.app.provider;

import com.go.euro.dev.model.Location;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * A concrete implementation of the {@link LocationServiceProvider} where the
 * services are provided by the GoEuro API.
 * </p>
 * @author Sabry <Muhammad.Sabry.Ali@gmail.com>
 */
public class GoEuroLocationServiceProvider implements LocationServiceProvider {

    //Should be externally configurable outside the class to allow adhoc changes
    private static final String URL = "http://api.goeuro.com/api/v2/position/suggest/en/";
    
    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isValidQuery(final String query) {
        return StringUtils.isNotBlank(query) && StringUtils.isAlphanumericSpace(query);
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public List<Location> search(final String query) {
        
        final String resourceURL = getResourceURL(query);
        
        final Client client = Client.create();
        final WebResource webResource = client.resource(resourceURL);
        webResource.accept(MediaType.APPLICATION_JSON_TYPE);
        
        final ClientResponse response
                = webResource.get(ClientResponse.class);
        
        if (response.getStatus() != 200) {
            throw new RuntimeException("Search request failed : " + response.getStatus());
        }
        
        final String output
                = response.getEntity(String.class);
        
        if (StringUtils.isEmpty(output)) {
            return Collections.EMPTY_LIST;
        }
        
        return parseResponseFromJson(output);
    }
    
    /**
     * <p>
     * Builds the query resource URL.
     * </p>
     */
    private String getResourceURL(final String query) {
        return URL + query;
    }
    
    /**
     * <p>
     * Parses the given JSON string into the corresponding list of
     * {@link Location} objects.
     * </p>
     */
    private List<Location> parseResponseFromJson(final String jsonResponse) {
        final Gson gson = new Gson();
        
        final Type listType = new TypeToken<List<Location>>(){}.getType();
        final List<Location> locations
                = (List<Location>) gson.fromJson(jsonResponse, listType);
        
        return locations;
    }
}
