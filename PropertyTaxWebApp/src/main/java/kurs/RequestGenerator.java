package kurs;

import java.math.BigDecimal;
import java.util.List;

public final class RequestGenerator{
	private TaxAmount taxamount;
	
	public RequestGenerator(String cadastralValueText, String inventoryTaxText, String squareText, String portionText, String holdingPeriodRatioText, String childrenCountText, String exemptionText, double town, double property) {
		
		TaxAmount taxamount = new TaxAmount(
				cadastralValueText,
				inventoryTaxText,
				squareText,
				portionText,
				holdingPeriodRatioText,
				childrenCountText != null ? childrenCountText : "0",
						exemptionText != null ? exemptionText : "0",
								town,
								property
				);
	}

	public String check() {
		List<InputError> errors = taxamount.validate();
		String messages = "";
		
		if (errors.size() > 0) {
			
			for (int i = 0; i < errors.size(); i++) {
				messages += errors.get(i).getName() + ": " + errors.get(i).getMessage() + "\n";
			}
			
			return messages;
			
		} else {
			
			return null;
			
		}
	}
	
	
	public String count() {
		String stringResult = "";
		
		BigDecimal bigDecimalResult = taxamount.calculate();
		stringResult = bigDecimalResult.toString();
		
		return stringResult;
	}
	
	
	

	private String getTextFieldByName(String name) throws Exception {
		String field;

		switch (name) {
		case "Кадастровая стоимость": field = "kadastr";
		break;
		case "Инвентаризационный налог": field = "tax";
		break;
		case "Площадь": field = "square";
		break;
		case "Размер доли": field = "part";
		break;
		case "Период владения": field = "period";
		break;
		case "Количество детей": field = "childrens";
		break;
		case "Льгота": field = "benefit";
		break;
		default: throw new Exception("Недопустимое имя параметра");
		}

		return field;
	}
	
}
