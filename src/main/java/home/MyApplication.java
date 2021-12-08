package home;

import home.service.AsyncSearchDataService;
import home.utils.ArgsReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Map;

@Configuration
@EnableAsync
@EnableAutoConfiguration
@ComponentScan
public class MyApplication implements CommandLineRunner {

    @Autowired
    private AsyncSearchDataService asyncSearchDataService;

    @Autowired
    ArgsReader reader;

    @Override
    public void run(String... args) throws Exception {
        Map<String, String> argsMap = reader.readArgs(args);
        asyncSearchDataService.execute(argsMap);
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}