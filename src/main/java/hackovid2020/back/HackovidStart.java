package hackovid2020.back;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.google.common.base.Joiner;

@SpringBootApplication
public class HackovidStart {
	
	private static final Logger LOG = LoggerFactory.getLogger(HackovidStart.class);
	
	@PostConstruct
	public void initApplication() {
		LOG.info("Hackovid backend started");
	}

	@PreDestroy
	public void destroyApplication() {
		LOG.info("Hackovid backend stopping");
	
	}
	
    public static void main(String[] args) {
    	SpringApplication app = new SpringApplication(HackovidStart.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("Application is running, profile(s): {}",
                Joiner.on(", ").join(env.getActiveProfiles()));
    }
    
}