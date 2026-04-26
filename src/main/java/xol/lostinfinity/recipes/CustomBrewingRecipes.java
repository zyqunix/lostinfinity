package xol.lostinfinity.recipes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xol.lostinfinity.init.ItemInit;
public class CustomBrewingRecipes {
    public static void init() {
        BrewingRecipeRegistry.addRecipe(new ItemStack(Items.field_151068_bn), new ItemStack(ItemInit.explosiveSack), new ItemStack(ItemInit.firebloodSolution));
        BrewingRecipeRegistry.addRecipe(new ItemStack(Items.field_151068_bn), new ItemStack(ItemInit.acidicTongue), new ItemStack(ItemInit.acidbloodSolution));
        BrewingRecipeRegistry.addRecipe(new ItemStack(Items.field_151068_bn), new ItemStack(ItemInit.duskerEggs), new ItemStack(ItemInit.polychargeSolution));
        BrewingRecipeRegistry.addRecipe(new ItemStack(Items.field_151068_bn), new ItemStack(ItemInit.supermutatedFlesh), new ItemStack(ItemInit.unstableSolution));
        BrewingRecipeRegistry.addRecipe(new ItemStack(Items.field_151068_bn), new ItemStack(ItemInit.deviantFlesh), new ItemStack(ItemInit.corruptedSolution));
        BrewingRecipeRegistry.addRecipe(new ItemStack(Items.field_151068_bn), new ItemStack(ItemInit.volatileBlood), new ItemStack(ItemInit.volatilitySolution));
        BrewingRecipeRegistry.addRecipe(new ItemStack(Items.field_151068_bn), new ItemStack(ItemInit.nightmarePowder), new ItemStack(ItemInit.nightmareSolution));
        BrewingRecipeRegistry.addRecipe(new ItemStack(Items.field_151068_bn), new ItemStack(ItemInit.glowingSac), new ItemStack(ItemInit.glowbloodSolution));
        BrewingRecipeRegistry.addRecipe(new ItemStack(Items.field_151068_bn), new ItemStack(ItemInit.emberSac), new ItemStack(ItemInit.quickflameSolution));
        BrewingRecipeRegistry.addRecipe(new ItemStack(Items.field_151068_bn), new ItemStack(ItemInit.atomicCell), new ItemStack(ItemInit.rapidGrowthSolution));
        BrewingRecipeRegistry.addRecipe(new ItemStack(Items.field_151068_bn), new ItemStack(ItemInit.vileSac), new ItemStack(ItemInit.bioreactiveSolution));
        GameRegistry.addSmelting(ItemInit.honeyJar, new ItemStack(ItemInit.amber), 0.7f);
        GameRegistry.addSmelting(ItemInit.impureExothermite, new ItemStack(ItemInit.moltenExothermite), 0.7f);
        GameRegistry.addSmelting(ItemInit.sunderLog, new ItemStack(ItemInit.sunderAsh), 0.7f);
        GameRegistry.addSmelting(ItemInit.murkyLog, new ItemStack(ItemInit.murkyAsh), 0.7f);
    }
}
