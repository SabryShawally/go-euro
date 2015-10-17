
package com.go.euro.dev.model;

import com.google.gson.annotations.SerializedName;

/**
 * <p>
 * A simple POJO representation of the Location data returned by the web
 * service. A Location maps an actual city/airport with a set of data about that
 * location such as the corresponding IATA code, and its geographical position.
 * </p>
 * @author Sabry <Muhammad.Sabry.Ali@gmail.com>
 */
public class Location {
    
    @SerializedName("_id")
    private Long id;
    
    private String name;
    private String type; //Usually should be an enum
    
    @SerializedName("geo_position")
    private GeoPosition geoPosition;

    public Location() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(final GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }
}
