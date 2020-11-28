package dev.latvian.kubejs.mekanism;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.kubejs.item.ingredient.IngredientStackJS;
import dev.latvian.kubejs.recipe.RecipeJS;
import dev.latvian.kubejs.util.MapJS;
import mekanism.api.recipes.inputs.chemical.GasStackIngredient;

import javax.annotation.Nullable;

/**
 * @author LatvianModder
 */
public abstract class MekanismRecipeJS extends RecipeJS
{
	@Override
	public JsonElement serializeIngredientStack(IngredientStackJS in)
	{
		JsonObject json = new JsonObject();
		json.add("ingredient", in.ingredient.toJson());

		if (in.getCount() > 1)
		{
			json.addProperty("amount", in.getCount());
		}

		return json;
	}

	public GasStackIngredient parseGas(@Nullable Object o)
	{
		if (o instanceof JsonElement)
		{
			return GasStackIngredient.deserialize((JsonElement) o);
		}
		else if (o instanceof CharSequence)
		{
			JsonObject json = new JsonObject();
			json.addProperty("gas", o.toString());
			json.addProperty("amount", 1000);
			return GasStackIngredient.deserialize(json);
		}

		JsonObject json = MapJS.of(o).toJson();

		if (!json.has("amount"))
		{
			json.addProperty("amount", 1000);
		}

		return GasStackIngredient.deserialize(json);
	}
}
