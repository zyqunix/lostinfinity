package xol.lostinfinity.util.compatibility.jei.compressiontable;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/compatibility/jei/compressiontable/CompressionTableRecipeWrapper.class */
public class CompressionTableRecipeWrapper implements IRecipeWrapper {
    private final ItemStack input;
    private final ItemStack output;

    public CompressionTableRecipeWrapper(ItemStack input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.ITEM, this.input);
        ingredients.setOutput(VanillaTypes.ITEM, this.output);
    }

    public static List<CompressionTableRecipeWrapper> getRecipes(IJeiHelpers helpers) {
        helpers.getStackHelper();
        CompressionTableRecipeJEI instance = CompressionTableRecipeJEI.getInstance();
        Map<ItemStack, ItemStack> recipes = instance.getCompressionTableList();
        List<CompressionTableRecipeWrapper> jeiRecipes = Lists.newArrayList();
        for (Map.Entry<ItemStack, ItemStack> entry : recipes.entrySet()) {
            ItemStack input = entry.getKey();
            ItemStack output = entry.getValue();
            CompressionTableRecipeWrapper recipe = new CompressionTableRecipeWrapper(input, output);
            jeiRecipes.add(recipe);
        }
        return jeiRecipes;
    }
}
