package xol.lostinfinity.util.compatibility.jei.trades.archeologist;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import java.util.List;
import java.util.Map;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
public class ArcheologistTradeWrapper implements IRecipeWrapper {
    private final List<ItemStack> inputs;
    private final ItemStack output;
    public ArcheologistTradeWrapper(List<ItemStack> inputs, ItemStack output) {
        this.inputs = inputs;
        this.output = output;
    }
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, this.inputs);
        ingredients.setOutput(VanillaTypes.ITEM, this.output);
    }
    public static List<ArcheologistTradeWrapper> getRecipes(IJeiHelpers helpers) {
        helpers.getStackHelper();
        ArcheologistTradeJEI instance = ArcheologistTradeJEI.getInstance();
        Table<ItemStack, ItemStack, ItemStack> recipes = instance.getArcheologistTradeList();
        List<ArcheologistTradeWrapper> jeiRecipes = Lists.newArrayList();
        for (Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : recipes.columnMap().entrySet()) {
            for (Map.Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
                ItemStack input1 = entry.getKey();
                ItemStack input2 = ent.getKey();
                ItemStack output = ent.getValue();
                List<ItemStack> inputs = Lists.newArrayList(new ItemStack[]{input1, input2});
                ArcheologistTradeWrapper recipe = new ArcheologistTradeWrapper(inputs, output);
                jeiRecipes.add(recipe);
            }
        }
        return jeiRecipes;
    }
}
