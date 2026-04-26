package xol.lostinfinity.recipes;

import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/recipes/FossilCombinerRecipes.class */
public class FossilCombinerRecipes {
    private static ArrayList<Recipe> recipes = new ArrayList<>();

    private static void init() {
        Recipe testRec = new Recipe(ItemInit.remainsPelicanEel, ItemStack.field_190927_a, ItemStack.field_190927_a, ItemStack.field_190927_a, ItemStack.field_190927_a, ItemStack.field_190927_a, new ItemStack(ItemInit.fossilRibbedTail), new ItemStack(ItemInit.fossilSmallRibs), new ItemStack(ItemInit.fossilSmallRibs), new ItemStack(ItemInit.fossilSmallRibs), new ItemStack(ItemInit.fossilEelTopjaw), new ItemStack(ItemInit.fossilRibbedTail), ItemStack.field_190927_a, ItemStack.field_190927_a, ItemStack.field_190927_a, new ItemStack(ItemInit.fossilEelBotjaw));
        recipes.add(testRec);
    }

    public static ItemStack getResult(ItemStack... inputs) {
        if (recipes.isEmpty()) {
            init();
        }
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            ArrayList<ItemStack> foundInputs = new ArrayList<>();
            ArrayList<ItemStack> neededInputs = new ArrayList<>();
            if (recipe.values.length == 15) {
                for (int j = 0; j < 15; j++) {
                    neededInputs.add(recipe.values[j]);
                }
                for (int k = 0; k < 15; k++) {
                    ItemStack stack = neededInputs.get(k);
                    if (stack.func_77969_a(inputs[k]) || (inputs[k].func_190926_b() && stack.func_190926_b())) {
                        foundInputs.add(stack);
                    }
                }
                if (foundInputs.containsAll(neededInputs)) {
                    return new ItemStack(recipe.result);
                }
            }
        }
        return null;
    }
}
