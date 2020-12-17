package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import pojos.LineItem;
import pojos.SKU;
import pojos.Scenario;

public class ScenarioReader {

	public static Scenario populateScenario(String scenariosFile, List<SKU> skus) throws Exception {
		Scenario scenario = new Scenario();
		BufferedReader br = new BufferedReader(new FileReader(new File(scenariosFile)));
		String line;
		LineItem lineItem;
		List<LineItem> lineItems = new ArrayList<LineItem>();
		while ((line = br.readLine()) != null) {
			String[] arr = line.split(",");
			lineItem = new LineItem();
			lineItem.setQuantity(Integer.parseInt(arr[0]));
			lineItem.setSku(PromotionFileReader.findSku(skus, arr[1].charAt(0)));
			lineItems.add(lineItem);
		}
		scenario.setLineItems(lineItems);
		return scenario;
	}

}
