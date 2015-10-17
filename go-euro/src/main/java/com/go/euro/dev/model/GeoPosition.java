
package com.go.euro.dev.model;

/**
 * <p>
 * Longitude, and Latitude information about a given {@link Location}.
 * <p>
 * @author Sabry <Muhammad.Sabry.Ali@gmail.com>
 */
public class GeoPosition {
    
    /**
     * Float vs float because information is actually meaningful, i.e. 0 vs null
     * for such info represents availability or not.
     */
    private Float longitude;
    private Float latitude;

    public GeoPosition() {
        
    }

    public GeoPosition(
            final Float longitude, 
            final Float latitude) {
        
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(final Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(final Float latitude) {
        this.latitude = latitude;
    }
}
