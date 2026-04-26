package xol.lostinfinity.util.compatibility.jei.trades.zirconia.cerulean;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import java.util.List;
import java.util.Map;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/compatibility/jei/trades/zirconia/cerulean/CeruleanTradeWrapper.class */
public class CeruleanTradeWrapper implements IRecipeWrapper {
    private final List<ItemStack> inputs;
    private final ItemStack output;

    public CeruleanTradeWrapper(List<ItemStack> inputs, ItemStack output) {
        this.inputs = inputs;
        this.output = output;
    }

    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, this.inputs);
        ingredients.setOutput(VanillaTypes.ITEM, this.output);
    }

    public static List<CeruleanTradeWrapper> getRecipes(IJeiHelpers helpers) {
        helpers.getStackHelper();
        CeruleanTradeJEI instance = CeruleanTradeJEI.getInstance();
        Table<ItemStack, ItemStack, ItemStack> recipes = instance.getCeruleanTradeList();
        List<CeruleanTradeWrapper> jeiRecipes = Lists.newArrayList();
        for (Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : recipes.columnMap().entrySet()) {
            for (Map.Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
                ItemStack input1 = entry.getKey();
                ItemStack input2 = ent.getKey();
                ItemStack output = ent.getValue();
                List<ItemStack> inputs = Lists.newArrayList(new ItemStack[]{input1, input2});
                CeruleanTradeWrapper recipe = new CeruleanTradeWrapper(inputs, output);
                jeiRecipes.add(recipe);
            }
        }
        return jeiRecipes;
    }
}
