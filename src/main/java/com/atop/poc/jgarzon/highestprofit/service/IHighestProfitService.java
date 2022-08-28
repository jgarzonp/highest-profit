package com.atop.poc.jgarzon.highestprofit.service;

import com.atop.poc.jgarzon.highestprofit.model.bo.ResultBO;

/**
 * Service interface definition of challenge implementation.
 *
 * @author <a href="mailto:jgarzon@gptech.com.co">Jonathan Garz&oacute;n</a>
 * @version 1.0.0
 * @since highest-profit-1.0.0
 */
public interface IHighestProfitService {

    /**
     * <p>
     * Processes the given data file in text format, as CSV file delimited by commas, and read record by record,
     * extracting data into file and then it counts the total of data records into file, counts failed mapped records,
     * and gets the Top 20 highest profit of valid records.
     * </p>
     * <p>
     * The sorted result by highest profit of all valid records will be written to JSON file into same directory of
     * original file.
     * </p>
     *
     * @param filePath the data file path to process.
     * @return Result of processing data file.
     */
    ResultBO processDataFile(String filePath);

}
