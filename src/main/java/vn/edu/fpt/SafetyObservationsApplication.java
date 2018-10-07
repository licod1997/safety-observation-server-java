package vn.edu.fpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import vn.edu.fpt.configuration.FileStorageProperties;

@EnableConfigurationProperties({
        FileStorageProperties.class
})
@SpringBootApplication
public class SafetyObservationsApplication {

    public static void main( String[] args ) {
        SpringApplication.run( SafetyObservationsApplication.class, args );
    }
}
