package mobilebg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class MobileBg {

    public static void main(final String[] args) {
        SpringApplication.run(MobileBg.class, args);
    }
}
