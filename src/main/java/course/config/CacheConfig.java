package course.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Configuration;


@EnableCaching
@Configuration
public class CacheConfig {
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("bookings", "allBookings", "coworkingSpaces", "allCoworkingSpaces");
    }
}
