package com.atop.poc.jgarzon.highestprofit.service.impl;

import com.atop.poc.jgarzon.highestprofit.model.bo.ProfitBO;
import com.atop.poc.jgarzon.highestprofit.model.bo.ResultBO;
import com.atop.poc.jgarzon.highestprofit.service.IHighestProfitService;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Service class implementation to contain all required logic to resolve requested challenge.
 *
 * @author <a href="mailto:jgarzon@gptech.com.co">Jonathan Garz&oacute;n</a>
 * @version 1.0.0
 * @since highest-profit-1.0.0
 */
@Slf4j
@Service
public class HighestProfitService implements IHighestProfitService {

    private static final String COMMA_DELIMITER = ",";

    @Override
    public ResultBO processDataFile(String filePath) {
        ResultBO result = ResultBO.builder().build();
        log.info("Start processing file: {}", filePath);
        int totalRecords = 0;
        int invalidRecords = 0;
        List<ProfitBO> profitRecords = new LinkedList<>();
        File dataFile = new File(filePath);
        try (Scanner scanner = new Scanner(dataFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (totalRecords > 0) {
                    ProfitBO profitRecord = mapFileLineToBO(line);
                    if (profitRecord != null) {
                        profitRecords.add(profitRecord);
                    } else {
                        invalidRecords++;
                    }
                }
                if (StringUtils.isNotBlank(line)) {
                    totalRecords++;
                }
            }
            totalRecords--;
            List<ProfitBO> sorted = profitRecords.stream().sorted().collect(Collectors.toList());
            result = ResultBO.builder()
                    .valid(Boolean.TRUE)
                    .totalRecords(totalRecords)
                    .invalidRecords(invalidRecords)
                    .top20(sorted.stream().limit(20).collect(Collectors.toList()))
                    .build();
            writeToJSON(sorted, dataFile.getParent());
        } catch (FileNotFoundException e) {
            log.error("Error reading file: {}", e.getMessage());
        }
        log.info("Processing file finished!");
        return result;
    }

    /**
     * Maps read line from file to {@link ProfitBO} object.
     *
     * @param line Read line from file.
     * @return A new {@link ProfitBO} instance if record has valid values. <code>null</code> if record has invalid values.
     */
    private ProfitBO mapFileLineToBO(String line) {
        try {
            ProfitBO profitRecord = null;
            String[] values = line.split(COMMA_DELIMITER);
            if (ArrayUtils.getLength(values) == 5) {
                profitRecord = ProfitBO.builder()
                        .year(Integer.valueOf(values[0]))
                        .rank(Integer.valueOf(values[1]))
                        .company(StringUtils.trim(values[2]))
                        .revenue(Double.parseDouble(values[3]))
                        .profit(Double.parseDouble(values[4]))
                        .build();
            }
            return profitRecord;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Writes valid records from CSV file to JSON file.
     *
     * @param profitRecords filtered and sorted records to be written to JSON file.
     * @param destination   destination directory of new JSON file named data2.json.
     */
    private void writeToJSON(List<ProfitBO> profitRecords, String destination) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(Paths.get(destination, "data2.json").toFile(), profitRecords);
        } catch (IOException e) {
            log.error("Error writing JSON file", e);
        }
    }
}
