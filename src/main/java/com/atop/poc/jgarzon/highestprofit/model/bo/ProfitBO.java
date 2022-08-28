package com.atop.poc.jgarzon.highestprofit.model.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="mailto:jgarzon@gptech.com.co">Jonathan Garz&oacute;n</a>
 * @version 1.0.0
 * @since highest-profit-1.0.0
 */
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProfitBO implements Comparable<ProfitBO> {

    private Integer year;

    private Integer rank;

    private String company;

    private Double revenue;

    private Double profit;

    @Override
    public int compareTo(ProfitBO o) {
        return o.profit.compareTo(this.profit);
    }

    @Override
    public String toString() {
        return "\n\t\t" + StringUtils.joinWith(",", year, rank, company, revenue, profit);
    }
}
