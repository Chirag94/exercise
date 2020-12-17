package businesslogic;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import pojos.LineItem;
import pojos.PromotionRuleOne;
import pojos.PromotionRuleTwo;
import pojos.SKU;
import pojos.Scenario;

public class ApplyPromotionsTest {
	Scenario scenario;
	List<SKU> skus;
	List<PromotionRuleOne> rule1s;
	List<PromotionRuleTwo> rule2s;

	@BeforeEach
	public void init() {

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
		rule3.setPrice(30.0);
		rule3.setSku1(sku3);
		rule3.setSku2(sku4);

		rule1s = new ArrayList<>();
		rule1s.add(rule1);
		rule1s.add(rule2);
		rule2s = new ArrayList<>();
		rule2s.add(rule3);

	}

	@org.junit.jupiter.api.Test
	public void testScenarioA() {

		scenario = new Scenario();
		LineItem l1 = new LineItem();
		l1.setQuantity(1);
		l1.setSku(skus.get(0));
		LineItem l2 = new LineItem();
		l2.setQuantity(1);
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
		Double expected = new Double(100D);
		assertEquals(expected.doubleValue(), actual.doubleValue());

	}

	@org.junit.jupiter.api.Test

	public void testScenarioB() {

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
		assertEquals(expected.doubleValue(), actual.doubleValue());

	}
	@org.junit.jupiter.api.Test

	public void testScenarioC() {
		
		scenario = new Scenario();
		LineItem l1 = new LineItem();
		l1.setQuantity(3);
		l1.setSku(skus.get(0));
		LineItem l2 = new LineItem();
		l2.setQuantity(5);
		l2.setSku(skus.get(1));
		LineItem l3 = new LineItem();
		l3.setQuantity(1);
		l3.setSku(skus.get(2));
		LineItem l4 = new LineItem();
		l4.setQuantity(1);
		l4.setSku(skus.get(3));
		
		
		List<LineItem> linetems = new ArrayList<>();
		linetems.add(l1);
		linetems.add(l2);
		linetems.add(l3);
		linetems.add(l4);
		
		scenario.setLineItems(linetems);
		
		Double actual = ApplyPromotions.apply(scenario, skus, rule1s, rule2s);
		Double expected = new Double(280D);
		assertEquals(expected.doubleValue(), actual.doubleValue());
		
		
	}
}
