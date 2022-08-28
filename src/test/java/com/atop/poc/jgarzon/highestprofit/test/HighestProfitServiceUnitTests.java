package com.atop.poc.jgarzon.highestprofit.test;

import com.atop.poc.jgarzon.highestprofit.model.bo.ResultBO;
import com.atop.poc.jgarzon.highestprofit.service.IHighestProfitService;
import com.atop.poc.jgarzon.highestprofit.service.impl.HighestProfitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for HighestProfitService implementation.
 *
 * @author <a href="mailto:jgarzon@gptech.com.co">Jonathan Garz&oacute;n</a>
 * @version 1.0.0
 * @since highest-profit-1.0.0
 */
class HighestProfitServiceUnitTests {

    /**
     * Subject test.
     */
    private IHighestProfitService service;

    @BeforeEach
    void init() {
        this.service = new HighestProfitService();
    }

    @Test
    void testProcessDataFile_ShouldSuccess() {

        URL resourceUrl = HighestProfitServiceUnitTests.class.getClassLoader().getResource("data.csv");
        assertNotNull(resourceUrl);

        File file = new File(resourceUrl.getPath());

        ResultBO result = service.processDataFile(resourceUrl.getPath());
        assertNotNull(result);
        assertTrue(result.isValid());
        assertNotNull(result.getTop20());
        assertEquals(25500, result.getTotalRecords());
        assertEquals(426, result.getInvalidRecords());
        assertFalse(result.getTop20().isEmpty());

        File resultFile = new File(file.getParent(), "data2.json");
        assertTrue(resultFile.exists());

        boolean deleteResult = resultFile.delete();
        assertTrue(deleteResult);
    }

}
