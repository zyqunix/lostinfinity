package xol.lostinfinity.util.compatibility.jei.chemistrynegametric;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/compatibility/jei/chemistrynegametric/ChemistryNegametricRecipeWrapper.class */
public class ChemistryNegametricRecipeWrapper implements IRecipeWrapper {
    private final List<ItemStack> inputs;
    private final ItemStack output;

    public ChemistryNegametricRecipeWrapper(List<ItemStack> inputs, ItemStack output, IGuiHelper helper) {
        this.inputs = inputs;
        this.output = output;
    }

    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, this.inputs);
        ingredients.setOutput(VanillaTypes.ITEM, this.output);
    }

    public static List<ChemistryNegametricRecipeWrapper> getRecipes(IJeiHelpers helpers) {
        helpers.getStackHelper();
        ChemistryNegametricRecipeJEI instance = ChemistryNegametricRecipeJEI.getInstance();
        Map<List<ItemStack>, ItemStack> recipes = instance.getChemistryTableList();
        List<ChemistryNegametricRecipeWrapper> jeiRecipes = Lists.newArrayList();
        for (Map.Entry<List<ItemStack>, ItemStack> entry : recipes.entrySet()) {
            List<ItemStack> inputs = entry.getKey();
            ItemStack output = entry.getValue();
            ChemistryNegametricRecipeWrapper recipe = new ChemistryNegametricRecipeWrapper(inputs, output, helpers.getGuiHelper());
            jeiRecipes.add(recipe);
        }
        return jeiRecipes;
    }
}
