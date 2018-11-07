package vn.edu.fpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import vn.edu.fpt.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class SafetyObservationsApplication {

    public static void main( String[] args ) {
        SpringApplication.run( SafetyObservationsApplication.class, args );
    }
}
