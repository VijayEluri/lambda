package loan.traditional;

import loan.primitives.Amount;
import loan.primitives.Rate;
import loan.domain.Country;
import loan.domain.FinancialInstrument;

import static loan.atomic.FinancialCalculator.monthlyLoanPayment;

public class TranditionalLoanCalculation {
    public static void calculate(FinancialInstrument financialInstrument) {

       if (financialInstrument.getCountry() == Country.GD) {
           financialInstrument.setMonthPayment(
               Amount.valueOf(
                       monthlyLoanPayment(financialInstrument.getRate().doubleValue(),
                               financialInstrument.getTerm()*12,
                               financialInstrument.getPrincipal().doubleValue())
               )
           );
           financialInstrument.setStampDuty(
                financialInstrument.getMonthPayment().multiply(Rate.valueOf(0.02))
           );
           financialInstrument.setFirstMonthPayment(
                financialInstrument.getMonthPayment().plus(
                        financialInstrument.getStampDuty()
                )

        );
       } else if (financialInstrument.getCountry() == Country.NR) {
           if (financialInstrument.isFinanceApplicationFee()) {
               financialInstrument.setPrincipal(financialInstrument.getPrincipal().plus(financialInstrument.getApplicationFee()));
           }
           financialInstrument.setMonthPayment(
               Amount.valueOf(
                   monthlyLoanPayment(financialInstrument.getRate().doubleValue(),
                           financialInstrument.getTerm()*12,
                           financialInstrument.getPrincipal().doubleValue())
                   )
           );
           if (!financialInstrument.isFirstTimeBuyer()) {
               financialInstrument.setStampDuty(
                    financialInstrument.getMonthPayment().multiply(Rate.valueOf(0.03))
               );
           }
           financialInstrument.setFirstMonthPayment(
                financialInstrument.getMonthPayment().plus(
                    financialInstrument.getStampDuty()
                 )
           );
           if (!financialInstrument.isFinanceApplicationFee()) {
               financialInstrument.setFirstMonthPayment(financialInstrument.getFirstMonthPayment().plus(financialInstrument.getApplicationFee()));
           }

       } else if (financialInstrument.getCountry() == Country.WL) {
           financialInstrument.setMonthPayment(
               Amount.valueOf(
                       monthlyLoanPayment(financialInstrument.getRate().doubleValue(),
                               financialInstrument.getTerm()*12,
                               financialInstrument.getPrincipal().doubleValue())
                       )
           );
           financialInstrument.setStampDuty(
                financialInstrument.getPrincipal().multiply(Rate.valueOf(0.03))
           );
           financialInstrument.setFirstMonthPayment(
                financialInstrument.getMonthPayment().plus(
                        financialInstrument.getStampDuty()
                )

           );
       }

    }
}
