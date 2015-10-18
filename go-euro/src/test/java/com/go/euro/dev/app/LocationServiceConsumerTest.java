
package com.go.euro.dev.app;

import com.go.euro.dev.app.provider.TestLocationServiceProvider;
import com.go.euro.dev.model.Location;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * A simple test suite to exercise {@link LocationServiceConsumer}
 * </p>
 * @author Sabry <Muhammad.Sabry.Ali@gmail.com>
 */
public class LocationServiceConsumerTest {
    
    @Test
    public void testSearch() {
        final LocationServiceConsumer consumer
                = new LocationServiceConsumer(new TestLocationServiceProvider());
        
        final List<Location> locations = consumer.search("Berlin");
        
        Assert.assertNotNull(locations);
        Assert.assertTrue(!locations.isEmpty());
        Assert.assertEquals(1, locations.size());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSearchValidation() {
        final LocationServiceConsumer consumer
                = new LocationServiceConsumer(new TestLocationServiceProvider());
        
        //Fails validation
        consumer.search(null);
    }
}
