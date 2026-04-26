package xol.lostinfinity.util.compatibility.jei.modulator;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
public class ModulatorRecipeWrapper implements IRecipeWrapper {
    private final List<ItemStack> inputs;
    private final ItemStack output;
    public ModulatorRecipeWrapper(List<ItemStack> inputs, ItemStack output) {
        this.inputs = inputs;
        this.output = output;
    }
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, this.inputs);
        ingredients.setOutput(VanillaTypes.ITEM, this.output);
    }
    public static List<ModulatorRecipeWrapper> getRecipes(IJeiHelpers helpers) {
        helpers.getStackHelper();
        ModulatorRecipeJEI instance = ModulatorRecipeJEI.getInstance();
        Map<List<ItemStack>, ItemStack> recipes = instance.getModulatorList();
        List<ModulatorRecipeWrapper> jeiRecipes = Lists.newArrayList();
        for (Map.Entry<List<ItemStack>, ItemStack> entry : recipes.entrySet()) {
            List<ItemStack> inputs = entry.getKey();
            ItemStack output = entry.getValue();
            ModulatorRecipeWrapper recipe = new ModulatorRecipeWrapper(inputs, output);
            jeiRecipes.add(recipe);
        }
        return jeiRecipes;
    }
}
