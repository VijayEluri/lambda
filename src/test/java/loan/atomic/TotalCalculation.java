package loan.atomic;

import com.google.common.base.Function;
import loan.domain.Worksheet;

import java.math.BigDecimal;

public class TotalCalculation implements Function<Worksheet, Worksheet> {
    @Override
    public Worksheet apply(Worksheet worksheet) {
        worksheet.setPrincipal(worksheet.getPrincipal().add(new BigDecimal(100)));
        return worksheet;
    }
}