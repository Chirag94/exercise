package entry;

import java.util.ArrayList;
import java.util.List;

import businesslogic.ApplyPromotions;
import helper.Arguments;
import pojos.PromotionRuleOne;
import pojos.PromotionRuleTwo;
import pojos.SKU;
import pojos.Scenario;
import service.PromotionFileReader;
import service.SKUFileReader;
import service.ScenarioReader;

public class MainClass {

	public static void main(String[] args) {
		
		try {
			List<PromotionRuleOne> promotionRuleOnes = new ArrayList<PromotionRuleOne>();
			List<PromotionRuleTwo> PromotionRuleTwos = new ArrayList<PromotionRuleTwo>();
			List<SKU> skus;
			Scenario scenario;
			Double totalResult;
			
			
			// Reading input
			Arguments arguments = new Arguments(args);
			
			// Populating sku inventory since we want sku and promotion rule to be tightle coupled and sku be reusable
			skus = SKUFileReader.populateInventory(arguments.getSkuUnitPriceFile());
			PromotionFileReader.populatePromotions(promotionRuleOnes,PromotionRuleTwos,arguments.getActivePromotionsFile(),skus);
			scenario = ScenarioReader.populateScenario(arguments.getScenariosFile(),skus);
			
			// Apply the logic to calculate total price
			totalResult = ApplyPromotions.apply(scenario,skus,promotionRuleOnes,PromotionRuleTwos);
			
			System.out.println("Total: \t " + totalResult);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
