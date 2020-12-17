package businesslogic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import pojos.LineItem;
import pojos.PromotionRuleOne;
import pojos.PromotionRuleTwo;
import pojos.SKU;
import pojos.Scenario;

public class ApplyPromotionsTest {

	
	
	
	@Test
	public void testScenarioB() {
		Scenario scenario;
		List<SKU> skus;
		List<PromotionRuleOne> rule1s;
		List<PromotionRuleTwo> rule2s;
		SKU sku1 = new SKU();
		sku1.setSkuID('A');
		sku1.setUnitPrice(50.0);
		SKU sku2 = new SKU();
		sku2.setSkuID('B');
		sku2.setUnitPrice(30.0);
		SKU sku3 = new SKU();
		sku3.setSkuID('C');
		sku3.setUnitPrice(20.0);
		SKU sku4 = new SKU();
		sku4.setSkuID('D');
		sku4.setUnitPrice(15.0);
		skus = new ArrayList<SKU>();
		skus.add(sku1);
		skus.add(sku2);
		skus.add(sku3);
		skus.add(sku4);
		
		PromotionRuleOne rule1 = new PromotionRuleOne();
		rule1.setSku(sku1);
		rule1.setPrice(130.0);
		rule1.setQuantity(3);
		
		PromotionRuleOne rule2 = new PromotionRuleOne();
		rule2.setSku(sku2);
		rule2.setPrice(45.0);
		rule2.setQuantity(2);
		
		PromotionRuleTwo rule3 = new PromotionRuleTwo();
		rule3.setPrice(20.0);
		rule3.setSku1(sku3);
		rule3.setSku2(sku4);
		
		rule1s= new ArrayList<>();
		rule1s.add(rule1);
		rule1s.add(rule2);
		rule2s= new ArrayList<>();
		rule2s.add(rule3);
		scenario = new Scenario();
		LineItem l1 = new LineItem();
		l1.setQuantity(5);
		l1.setSku(skus.get(0));
		LineItem l2 = new LineItem();
		l2.setQuantity(5);
		l2.setSku(skus.get(1));
		LineItem l3 = new LineItem();
		l3.setQuantity(1);
		l3.setSku(skus.get(2));
		List<LineItem> linetems = new ArrayList<>();
		linetems.add(l1);
		linetems.add(l2);
		linetems.add(l3);
		scenario.setLineItems(linetems);
		
		Double actual = ApplyPromotions.apply(scenario, skus, rule1s, rule2s);
		Double expected = new Double(370D);
		assertEquals(expected, actual);
		
		
	}
}
