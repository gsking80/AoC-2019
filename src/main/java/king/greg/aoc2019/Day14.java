package king.greg.aoc2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Day14 {
	
	final Map<String, Recipe> recipes = new HashMap<>();
	
	final Map<String,Long> ingredients = new HashMap<>();
	final Map<String,Long> leftovers = new HashMap<>();

	public Day14(FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);

			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					final String[] formula = lineJustFetched.split(" => ");
					final String[] inputs = formula[0].split(", ");
					final String[] output = formula[1].split(" ");
					final Recipe recipe = new Recipe(Long.valueOf(output[0]));
					for(final String input: inputs) {
						final String[] parts = input.split(" ");
						recipe.ingredients.put(parts[1], Long.valueOf(parts[0]));
					}
					recipes.put(output[1], recipe);
				}
			}
		} catch (IOException ioe) {
			throw new RuntimeException();
		}
	}
	
	class Recipe {
		final long yield;
		final Map <String, Long> ingredients;
		
		Recipe(final long yield) {
			this.yield = yield;
			ingredients = new HashMap<>();
		}
	}

	public long necessaryOre(final long fuelAmount) {
		ingredients.put("FUEL", fuelAmount);
		
		while(!ingredients.containsKey("ORE") || ingredients.size() > 1) {
			processIngredients();
		}
		
		return ingredients.get("ORE");
	}
	
	private void processIngredients() {
		final Map<String, Long> ingredientsToProcess = new HashMap<>();
		ingredientsToProcess.putAll(ingredients);
		ingredients.clear();
		
		for (final Entry<String,Long> entry : ingredientsToProcess.entrySet()) {
			if (entry.getKey().equals("ORE")) {
				long required = ingredients.getOrDefault(entry.getKey(), 0L) + entry.getValue();
				ingredients.put(entry.getKey(), required);
			} else {
				long qtyToMake = quantityToMake(entry.getKey(), entry.getValue());
				if (qtyToMake == 0) {
					continue;
				}
				final Recipe recipe = recipes.get(entry.getKey());
				long multiple = findMultiple(qtyToMake, recipe.yield);
				for (final Entry<String,Long> ingredient: recipe.ingredients.entrySet()) {
					long qty = quantityToMake(ingredient.getKey(), multiple * ingredient.getValue());
					if (qty > 0) {
						long required = ingredients.getOrDefault(ingredient.getKey(), 0L) + qty;
						ingredients.put(ingredient.getKey(), required);
					}
				}
				long leftoverQty = (multiple * recipe.yield) - qtyToMake;
				if (leftoverQty > 0) {
					leftovers.put(entry.getKey(), leftoverQty);
				}
			}

		}
	}

    private long quantityToMake(final String key, final long value) {
		long qtyToMake = value;
    	if (leftovers.containsKey(key)) {
			long leftoverQty = leftovers.get(key);
			if (leftoverQty > qtyToMake) { 
				leftovers.put(key, leftoverQty - qtyToMake);
				qtyToMake = 0;
			} else {
				leftovers.remove(key);
				qtyToMake -= leftoverQty;
			}
		}
		return qtyToMake;
	}

	static long findMultiple(final long qtyNeeded, final long yield) 
    { 
        long remainder = (qtyNeeded + yield) % yield; 
      
        if (remainder == 0) 
            return (qtyNeeded / yield); 
        else
            return (qtyNeeded + yield - remainder) / yield; 
    } 
	
	public long fuelFrom(long oreQty) {
		long startingGuess = 3750000L;
		long necessaryOre = necessaryOre(startingGuess);
		int extra = 0;
		while (necessaryOre < oreQty) {
			necessaryOre = necessaryOre(1L);
			extra++;
		}
		
		return startingGuess + extra - 1;
	}
	
}
