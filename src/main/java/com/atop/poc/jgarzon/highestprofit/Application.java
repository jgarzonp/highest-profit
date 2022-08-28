package com.atop.poc.jgarzon.highestprofit;

import com.atop.poc.jgarzon.highestprofit.model.bo.ResultBO;
import com.atop.poc.jgarzon.highestprofit.service.IHighestProfitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Main start point for Spring Boot Application.
 */
@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner init(IHighestProfitService highestProfitService) {
        return args -> {
            if (ArrayUtils.isEmpty(args)) {
                log.error("You have to type file path as program argument to process!");
            } else {
                ResultBO result = highestProfitService.processDataFile(args[0]);
                if (result.isValid()) {
                    log.info("Processing result: \n"
                                    + "\tTotal records: {},\n"
                                    + "\tInvalid records: {},\n"
                                    + "\tTop 20 highest profit: {}",
                            result.getTotalRecords(),
                            result.getInvalidRecords(),
                            result.getTop20()
                    );
                }
            }
        };
    }

}
