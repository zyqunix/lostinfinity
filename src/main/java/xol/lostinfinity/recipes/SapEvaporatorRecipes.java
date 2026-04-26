package xol.lostinfinity.recipes;

import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/recipes/SapEvaporatorRecipes.class */
public class SapEvaporatorRecipes {
    private static ArrayList<Recipe> recipes = new ArrayList<>();

    private static void init() {
        ItemStack sap = new ItemStack(ItemInit.jarOfSap);
        Recipe sunderSyrup = new Recipe(ItemInit.jarOfSyrup, sap);
        recipes.add(sunderSyrup);
    }

    public static ItemStack getResult(ItemStack input) {
        if (recipes.isEmpty()) {
            init();
        }
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            if (recipe.values[0].func_77969_a(input)) {
                return new ItemStack(recipe.result);
            }
        }
        return null;
    }
}
