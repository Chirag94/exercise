package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import pojos.PromotionRuleOne;
import pojos.PromotionRuleTwo;
import pojos.SKU;

public class PromotionFileReader {

	public static void populatePromotions(List<PromotionRuleOne> promotionRuleOnes,
			List<PromotionRuleTwo> promotionRuleTwos, String activePromotionsFile, List<SKU> skus) throws Exception{
		
		BufferedReader br = new BufferedReader(new FileReader(new File(activePromotionsFile)));
		String line;
		while((line = br.readLine())!=null) {
			String arr[] = line.split(",");
			if(arr.length==2) {
				SKU skuone = findSku(skus,arr[0].charAt(0));
				SKU skutwo = findSku(skus,arr[0].charAt(1));
				PromotionRuleTwo promotionRuleTwo = new PromotionRuleTwo();
				promotionRuleTwo.setSku1(skuone);
				promotionRuleTwo.setSku2(skutwo);
				promotionRuleTwo.setPrice(Double.parseDouble(arr[1]));
				promotionRuleTwos.add(promotionRuleTwo);
			}else {
				PromotionRuleOne promotionRuleOne = new PromotionRuleOne();
				promotionRuleOne.setPrice(Double.parseDouble(arr[2]));
				promotionRuleOne.setSku(findSku(skus,arr[1].charAt(0)));
				promotionRuleOne.setQuantity(Integer.parseInt(arr[0]));
				promotionRuleOnes.add(promotionRuleOne);
			}
		}
		
	}

	public static SKU findSku(List<SKU> skus, char charAt) {
		Optional<SKU> opt = skus.stream().filter(x->x.getSkuID().equals(charAt)).findAny();
		return opt.isPresent()?opt.get():null;
	}

}
