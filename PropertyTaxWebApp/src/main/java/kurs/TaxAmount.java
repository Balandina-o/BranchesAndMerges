package kurs;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


public final class TaxAmount{


	/** Объявление строковых переменных, хранящих введенные пользователем на старте программы значения */
	private static String cadastralValueText, inventoryTaxText, squareText, portionText, holdingPeriodRatioText, childrenCountText, exemptionText;

	/** Объявление числовых переменных, в которые записываются значения, прошедшие проверку на корректность*/
	private static double cadastralValue, inventoryTax, square, portion, holdingPeriodRatio, childrenCount, reductionFactor, deduction, exemption, evaporater;


	public TaxAmount(String cadastralValueText, String inventoryTaxText, String squareText, String portionText, String holdingPeriodRatioText, String childrenCountText, String exemptionText, double town, double property, double evapoarter) {
		TaxAmount.cadastralValueText = cadastralValueText;
		TaxAmount.inventoryTaxText = inventoryTaxText;
		TaxAmount.squareText = squareText;
		TaxAmount.portionText = portionText;
		TaxAmount.holdingPeriodRatioText = holdingPeriodRatioText;
		TaxAmount.childrenCountText = childrenCountText;
		TaxAmount.exemptionText = exemptionText;
		TaxAmount.reductionFactor = town;
		TaxAmount.deduction = property;

		TaxAmount.evaporater = evaporater;
	}
	
	


	public static final List<InputError> validate() {
		List<InputError> errors = new ArrayList<InputError>();
		InputNumber cadastralValue = new InputNumber("Кадастровая стоимость", TaxAmount.cadastralValueText);
		InputNumber inventoryTax = new InputNumber("Инвентаризационный налог", TaxAmount.inventoryTaxText);
		InputNumber square = new InputNumber("Площадь", TaxAmount.squareText);
		InputPortion portion = new InputPortion(TaxAmount.portionText);
		InputHoldingPeriodRatio holdingPeriodRatio = new InputHoldingPeriodRatio(TaxAmount.holdingPeriodRatioText);
		InputChildrenCount childrenCount = new InputChildrenCount(TaxAmount.childrenCountText);
		InputExemption exemption = new InputExemption(TaxAmount.exemptionText);

		try {
			TaxAmount.cadastralValue = cadastralValue.getValue();
		} catch (Exception error) {
			errors.add(new InputError(cadastralValue.getFieldName(), error.getMessage()));
		}

		try {
			TaxAmount.inventoryTax = inventoryTax.getValue();
		} catch (Exception error) {
			errors.add(new InputError(inventoryTax.getFieldName(), error.getMessage()));
		}

		try {
			TaxAmount.square = square.getValue();
		} catch (Exception error) {
			errors.add(new InputError(square.getFieldName(), error.getMessage()));
		}

		try {
			TaxAmount.portion = portion.getValue();
		} catch (Exception error) {
			errors.add(new InputError(portion.getFieldName(), error.getMessage()));
		}


		try {
			TaxAmount.holdingPeriodRatio = holdingPeriodRatio.getValue();
		} catch (Exception error) {
			errors.add(new InputError(holdingPeriodRatio.getFieldName(), error.getMessage()));
		}

		try {
			TaxAmount.childrenCount = childrenCount.getValue();
		} catch (Exception error) {
			errors.add(new InputError(childrenCount.getFieldName(), error.getMessage()));
		}

		try {
			TaxAmount.exemption = exemption.getValue();
		} catch (Exception error) {
			errors.add(new InputError(exemption.getFieldName(), error.getMessage()));
		}

		return errors;
	}
	/**
	 * Метод, отвечающий за непосредственно расчет суммы налога, необходимой к уплате
	 * по заданным в нем формулам
	 *
	 * @return the double - возвращаемая сумма налога, необходимая к уплате
	 */
	public static BigDecimal calculate() {
		//EnumSwitch enswitch = new EnumSwitch();
		double finalbid;
		double finalExemption=TaxAmount.exemption;

		BigDecimal finalbidbig;
		BigDecimal finalDeductionbig=BigDecimal.valueOf(TaxAmount.deduction);
		BigDecimal finalExemptionbig=BigDecimal.valueOf(TaxAmount.exemption);
		BigDecimal Deductionbig;
		BigDecimal childrenCountbig;
		BigDecimal evaporaterCountbig;


		//enswitch.setPropertyIndex(Integer.toString(combo_region.getSelectionModel().getSelectedIndex()));
		//enswitch.setRegionIndex(Integer.toString(combo_property.getSelectionModel().getSelectedIndex()));
		//enswitch.setCadastralValue(cadastralValue);
		//enswitch.enumuse();
		
		//finalbid= enswitch.getFinalbid();
		finalbid= 0.165;
		finalbidbig = BigDecimal.valueOf(finalbid);

		if (childrenCount >= 3) {
			Deductionbig=BigDecimal.valueOf(deduction);
			childrenCountbig=BigDecimal.valueOf(childrenCount);
			evaporaterCountbig=BigDecimal.valueOf(evaporater);
			finalDeductionbig= Deductionbig.add(childrenCountbig.multiply(evaporaterCountbig));
		}

		if (cadastralValue > 300000000) {
			finalExemption = 0;
			finalExemptionbig = BigDecimal.valueOf(finalExemption);
		}

		BigDecimal cadastralValuebig=BigDecimal.valueOf(cadastralValue);
		BigDecimal squarebig=BigDecimal.valueOf(square);
		BigDecimal inventoryTaxbig=BigDecimal.valueOf(inventoryTax);
		BigDecimal holdingPeriodRatiobig=BigDecimal.valueOf(holdingPeriodRatio);
		BigDecimal portionbig=BigDecimal.valueOf(portion);
		BigDecimal reductionFactorbig = BigDecimal.valueOf(reductionFactor);


		BigDecimal taxBasebig = cadastralValuebig.subtract((cadastralValuebig.divide(squarebig, 8,
				RoundingMode.HALF_UP)).multiply(finalDeductionbig));


		BigDecimal hundredbbig = BigDecimal.valueOf(100);
		BigDecimal twelvebig = BigDecimal.valueOf(12);
		BigDecimal onebig = BigDecimal.valueOf(1);


		BigDecimal sumWithoutExemptionbig1=((taxBasebig.divide(hundredbbig, 8, RoundingMode.HALF_UP).multiply(finalbidbig)
				.subtract(inventoryTaxbig)).multiply(reductionFactorbig).add(inventoryTaxbig)).multiply(holdingPeriodRatiobig.divide(twelvebig, 8, RoundingMode.HALF_UP))
				.multiply((onebig).divide(portionbig, 8, RoundingMode.HALF_UP));


		BigDecimal sumbig = sumWithoutExemptionbig1.subtract((sumWithoutExemptionbig1).multiply((finalExemptionbig).divide(hundredbbig, 8, RoundingMode.HALF_UP)));

		BigDecimal CheckNull=BigDecimal.valueOf(1);
		if(sumbig.compareTo(CheckNull)==-1){
			sumbig=BigDecimal.valueOf(0);
		}
		return sumbig.setScale(0, RoundingMode.HALF_UP);

	}
}