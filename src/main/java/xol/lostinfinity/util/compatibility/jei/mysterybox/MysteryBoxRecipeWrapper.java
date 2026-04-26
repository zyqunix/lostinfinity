package xol.lostinfinity.util.compatibility.jei.mysterybox;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
public class MysteryBoxRecipeWrapper implements IRecipeWrapper {
    private final ItemStack input;
    private final List<ItemStack> outputs;
    public MysteryBoxRecipeWrapper(ItemStack input, List<ItemStack> outputs) {
        this.input = input;
        this.outputs = outputs;
    }
    public void getIngredients(IIngredients ingredients) {
        for (ItemStack items : this.outputs) {
            items.func_190920_e(2 + ((int) (Math.random() * 63.0d)));
        }
        ingredients.setInput(VanillaTypes.ITEM, this.input);
        ingredients.setOutputs(VanillaTypes.ITEM, this.outputs);
    }
    public static List<MysteryBoxRecipeWrapper> getRecipes(IJeiHelpers helpers) {
        helpers.getStackHelper();
        MysteryBoxRecipeJEI instance = MysteryBoxRecipeJEI.getInstance();
        Map<ItemStack, List<ItemStack>> recipes = instance.getMysteryBoxList();
        List<MysteryBoxRecipeWrapper> jeiRecipes = Lists.newArrayList();
        for (Map.Entry<ItemStack, List<ItemStack>> entry : recipes.entrySet()) {
            ItemStack input = entry.getKey();
            List<ItemStack> outputs = entry.getValue();
            MysteryBoxRecipeWrapper recipe = new MysteryBoxRecipeWrapper(input, outputs);
            jeiRecipes.add(recipe);
        }
        return jeiRecipes;
    }
}
