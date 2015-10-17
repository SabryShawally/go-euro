
package com.go.euro.dev.app;

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
 * This class consumes the web service, and searches for a given location
 * string.
 * </p>
 * @author Sabry <Muhammad.Sabry.Ali@gmail.com>
 */
public class LocationServiceConsumer {
    
    //Should be externally configurable outside the class
    private static final String URL = "http://api.goeuro.com/api/v2/position/suggest/en/";
    
    /**
     * <p>
     * Returns the matching locations returned form the web service for the
     * given search query. This method will throw an IllegalArgumentException
     * given that the search query is blank. An empty list will be returned if
     * no results are found.
     * </p>
     */
    public List<Location> search(final String query) {
        
        if (StringUtils.isEmpty(query)) {
            throw new IllegalArgumentException("Please provide a search query");
        }
        
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
