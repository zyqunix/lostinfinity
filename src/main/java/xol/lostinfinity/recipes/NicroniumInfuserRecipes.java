package xol.lostinfinity.recipes;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ItemInit;
public class NicroniumInfuserRecipes {
    private static ArrayList<Recipe> recipes = new ArrayList<>();
    private static void init() {
        ItemStack nicro = new ItemStack(ItemInit.nicronium);
        Recipe infusedNicro = new Recipe(ItemInit.infusedNicronium, nicro);
        recipes.add(infusedNicro);
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
