package loan.composite;


import algocraft.function.CompositeFunction;
import loan.atomic.*;
import loan.domain.FinancialInstrument;
import loan.domain.Loan;

import static algocraft.function.Functions.conditional;
import static algocraft.function.Functions.compose;
import static com.google.common.base.Predicates.not;
import static loan.domain.Rate.valueOf;

public final class NarniaCalculation extends CompositeFunction<Loan, Loan> {

	public NarniaCalculation() {
        super(
              conditional(BorrowLoanApplicationFeePredicate.INSTANCE, new AddApplicationFeeToPrincipalCalculation()),
              MonthlyLoanPaymentCalculation.INSTANCE,
              conditional(not(new FirstTimeBuyerPredicate()), new StampDutyOnMonthlyPaymentCalculation(valueOf(0.03))),
              new FirstMonthPaymentCalculation(),
              conditional(not(BorrowLoanApplicationFeePredicate.INSTANCE), new AddApplicationFeeToFirstMonthPaymentCalculation())
        );
	}
}
