package com.atop.poc.jgarzon.highestprofit.model.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * @author <a href="mailto:jgarzon@gptech.com.co">Jonathan Garz&oacute;n</a>
 * @version 1.0.0
 * @since highest-profit-1.0.0
 */
@Getter
@Builder
@AllArgsConstructor
public final class ResultBO {

    @Builder.Default
    private final boolean valid = Boolean.FALSE;

    private final int totalRecords;

    private final int invalidRecords;

    private final List<ProfitBO> top20;

}
