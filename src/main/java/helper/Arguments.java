package helper;

import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Arguments {

	String skuUnitPriceFile;
	String activePromotionsFile;
	String scenariosFile;


	public Arguments(String [] args) {
		Stack<String> params = IntStream.rangeClosed(1, args.length).mapToObj(
				i->args[args.length-1]).collect(Collectors.toCollection(Stack::new));
		while(!params.empty()) {
			String param = params.pop();
			if(params.equals("-skuUnitPriceFile"))
				skuUnitPriceFile = new String(params.pop());
			else if(params.equals("-activePromotionsFile"))
				activePromotionsFile = new String(params.pop());
			if(params.equals("-scenariosFile"))
				scenariosFile = new String(params.pop());
			
		}
	}

	public String getSkuUnitPriceFile() {
		return skuUnitPriceFile;
	}

	public void setSkuUnitPriceFile(String skuUnitPriceFile) {
		this.skuUnitPriceFile = skuUnitPriceFile;
	}

	public String getActivePromotionsFile() {
		return activePromotionsFile;
	}

	public void setActivePromotionsFile(String activePromotionsFile) {
		this.activePromotionsFile = activePromotionsFile;
	}

	public String getScenariosFile() {
		return scenariosFile;
	}

	public void setScenariosFile(String scenariosFile) {
		this.scenariosFile = scenariosFile;
	}

}
