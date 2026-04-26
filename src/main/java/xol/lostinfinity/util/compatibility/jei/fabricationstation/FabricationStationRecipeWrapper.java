package xol.lostinfinity.util.compatibility.jei.fabricationstation;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
public class FabricationStationRecipeWrapper implements IRecipeWrapper {
    private final List<ItemStack> inputs;
    private final ItemStack output;
    public FabricationStationRecipeWrapper(List<ItemStack> inputs, ItemStack output) {
        this.inputs = inputs;
        this.output = output;
    }
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, this.inputs);
        ingredients.setOutput(VanillaTypes.ITEM, this.output);
    }
    public static List<FabricationStationRecipeWrapper> getRecipes(IJeiHelpers helpers) {
        helpers.getStackHelper();
        FabricationStationRecipeJEI instance = FabricationStationRecipeJEI.getInstance();
        Map<List<ItemStack>, ItemStack> recipes = instance.getFabricationStationList();
        List<FabricationStationRecipeWrapper> jeiRecipes = Lists.newArrayList();
        for (Map.Entry<List<ItemStack>, ItemStack> entry : recipes.entrySet()) {
            List<ItemStack> inputs = entry.getKey();
            ItemStack output = entry.getValue();
            FabricationStationRecipeWrapper recipe = new FabricationStationRecipeWrapper(inputs, output);
            jeiRecipes.add(recipe);
        }
        return jeiRecipes;
    }
}
