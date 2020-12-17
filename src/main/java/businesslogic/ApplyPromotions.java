package businesslogic;

import java.util.List;
import java.util.Optional;

import pojos.LineItem;
import pojos.PromotionRuleOne;
import pojos.PromotionRuleTwo;
import pojos.SKU;
import pojos.Scenario;

public class ApplyPromotions {

	public static Double apply(Scenario scenario, List<SKU> skus, List<PromotionRuleOne> promotionRuleOnes,
			List<PromotionRuleTwo> promotionRuleTwos) {
		Double result = 0D;
		for (LineItem lineItem : scenario.getLineItems()) {
			if (lineItem.getQuantity() == 1) {
				PromotionRuleTwo promotionRuleTwo = checkPromotionRule2Exists(lineItem.getSku(),
						scenario.getLineItems(), promotionRuleTwos);
				if (promotionRuleTwo != null) {
					result += applyPromotionRule2(lineItem, scenario.getLineItems(), promotionRuleTwo);
				} else {
					result += lineItem.getSku().getUnitPrice();
					lineItem.setQuantity(0);
				}
			} else {
				result += applyPromotionRule1(lineItem, promotionRuleOnes);
			}
		}
		return result;
	}

	private static Double applyPromotionRule1(LineItem lineItem, List<PromotionRuleOne> promotionRuleOnes) {

		Double price = 0D;
		PromotionRuleOne promotionRuleOne = checkPromotionRule1Exists(lineItem.getSku(), promotionRuleOnes);
		if (promotionRuleOne != null) {
			if (lineItem.getQuantity() < promotionRuleOne.getQuantity()) {
				price += lineItem.getSku().getUnitPrice() * lineItem.getQuantity();
			} else {
				price += promotionRuleOne.getPrice() * (lineItem.getQuantity() / promotionRuleOne.getQuantity());
				lineItem.setQuantity(lineItem.getQuantity() % promotionRuleOne.getQuantity());
				price += lineItem.getSku().getUnitPrice() * lineItem.getQuantity();
			}
		}
		return price;
	}

	private static PromotionRuleOne checkPromotionRule1Exists(SKU sku, List<PromotionRuleOne> promotionRuleOnes) {
		Optional<PromotionRuleOne> opt = promotionRuleOnes.stream()
				.filter(x -> x.getSku().getSkuID().equals(sku.getSkuID())).findAny();
		return opt.isPresent() ? opt.get() : null;

	}

	private static Double applyPromotionRule2(LineItem lineItem, List<LineItem> lineItems,
			PromotionRuleTwo promotionRuleTwo) {
		Double price = promotionRuleTwo.getPrice();
		for (LineItem lineItem1 : lineItems) {
			if (lineItem1.getSku().getSkuID().equals(promotionRuleTwo.getSku1().getSkuID())
					|| lineItem1.getSku().getSkuID().equals(promotionRuleTwo.getSku2().getSkuID())) {
				lineItem1.setQuantity(0);
			}
		}
		return price;
	}

	private static PromotionRuleTwo checkPromotionRule2Exists(SKU sku, List<LineItem> lineItems,
			List<PromotionRuleTwo> promotionRuleTwos) {
		for (PromotionRuleTwo promotionRuleTwo : promotionRuleTwos) {
			if (promotionRuleTwo.getSku1().getSkuID().equals(sku.getSkuID())
					|| promotionRuleTwo.getSku2().getSkuID().equals(sku.getSkuID())) {
				if (promotionRuleTwo.getSku1().getSkuID().equals(sku.getSkuID())) {
					if (checkLineItems(promotionRuleTwo.getSku2(), lineItems))
						return promotionRuleTwo;
				} else if (promotionRuleTwo.getSku2().getSkuID().equals(sku.getSkuID())) {
					if (checkLineItems(promotionRuleTwo.getSku1(), lineItems))
						return promotionRuleTwo;
				}
			}
		}
		return null;
	}

	private static boolean checkLineItems(SKU sku2, List<LineItem> lineItems) {
		return lineItems.stream().anyMatch(x -> x.getSku().getSkuID().equals(sku2.getSkuID()) && x.getQuantity() == 1)
				? true
				: false;
	}

}
