package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import pojos.SKU;

public class SKUFileReader {

	public static List<SKU> populateInventory(String skuUnitPriceFile) throws Exception {
		List<SKU> skus = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(new File(skuUnitPriceFile)));
		String line;
		SKU sku;
		while((line=br.readLine())!=null) {
			String []arr = line.split(",");
			sku = new SKU();
			sku.setSkuID(arr[0].charAt(0));
			sku.setUnitPrice(Double.parseDouble(arr[1]));
			skus.add(sku);
		}
		return skus;
	}

}
