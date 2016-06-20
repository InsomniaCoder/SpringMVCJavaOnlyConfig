
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * Created by Tanat on 6/12/2016.
 */

@Configuration
@ComponentScan(basePackages = "beans")
//@Import({PersistenceContext.class})
public class ApplicationConfigure {

    @PostConstruct
    public void setUpMockData() {
        System.out.println("Setting up mock up data in database");
    }
}
