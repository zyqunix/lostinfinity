package xol.lostinfinity.recipes;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ArmorInit;
import xol.lostinfinity.init.ItemInit;
public class ModulatorRecipes {
    private static ArrayList<Recipe> recipes = new ArrayList<>();
    private static void init() {
        Recipe vampHelmetUpgrade = new Recipe(ArmorInit.vampyreonPrimeSet.helmet, new ItemStack(ItemInit.moduleRange), new ItemStack(ItemInit.modulePower), new ItemStack(ArmorInit.vampyreonSet.helmet), new ItemStack(ItemInit.maliciumCondensed), new ItemStack(ItemInit.maliciumCondensed), new ItemStack(ItemInit.maliciumCondensed));
        Recipe vampLeggingsUpgrade = new Recipe(ArmorInit.vampyreonPrimeSet.leggings, new ItemStack(ItemInit.moduleRange), new ItemStack(ItemInit.moduleRestoration), new ItemStack(ArmorInit.vampyreonSet.leggings), new ItemStack(ItemInit.maliciumCondensed), new ItemStack(ItemInit.maliciumCondensed), new ItemStack(ItemInit.maliciumCondensed));
        Recipe vampBootUpgrade = new Recipe(ArmorInit.vampyreonPrimeSet.boots, new ItemStack(ItemInit.moduleRange), new ItemStack(ItemInit.moduleAcceleration), new ItemStack(ArmorInit.vampyreonSet.boots), new ItemStack(ItemInit.maliciumCondensed), new ItemStack(ItemInit.maliciumCondensed), new ItemStack(ItemInit.maliciumCondensed));
        Recipe vampChestplateUpgrade = new Recipe(ArmorInit.vampyreonPrimeSet.chestplate, new ItemStack(ItemInit.moduleRange), new ItemStack(ItemInit.moduleConversion), new ItemStack(ArmorInit.vampyreonSet.chestplate), new ItemStack(ItemInit.maliciumCondensed), new ItemStack(ItemInit.maliciumCondensed), new ItemStack(ItemInit.maliciumCondensed));
        recipes.add(vampHelmetUpgrade);
        recipes.add(vampLeggingsUpgrade);
        recipes.add(vampBootUpgrade);
        recipes.add(vampChestplateUpgrade);
        Recipe spectrosHelmetUpgrade = new Recipe(ArmorInit.spectrosPrimeSet.helmet, new ItemStack(ItemInit.moduleHexing), new ItemStack(ItemInit.moduleWeight), new ItemStack(ArmorInit.spectrosSet.helmet), new ItemStack(ItemInit.etheriumCondensed), new ItemStack(ItemInit.etheriumCondensed), new ItemStack(ItemInit.etheriumCondensed));
        Recipe spectrosLeggingsUpgrade = new Recipe(ArmorInit.spectrosPrimeSet.leggings, new ItemStack(ItemInit.moduleHexing), new ItemStack(ItemInit.moduleRestoration), new ItemStack(ArmorInit.spectrosSet.leggings), new ItemStack(ItemInit.etheriumCondensed), new ItemStack(ItemInit.etheriumCondensed), new ItemStack(ItemInit.etheriumCondensed));
        Recipe spectrosBootUpgrade = new Recipe(ArmorInit.spectrosPrimeSet.boots, new ItemStack(ItemInit.moduleHexing), new ItemStack(ItemInit.moduleTransmitting), new ItemStack(ArmorInit.spectrosSet.boots), new ItemStack(ItemInit.etheriumCondensed), new ItemStack(ItemInit.etheriumCondensed), new ItemStack(ItemInit.etheriumCondensed));
        Recipe spectrosChestplateUpgrade = new Recipe(ArmorInit.spectrosPrimeSet.chestplate, new ItemStack(ItemInit.moduleHexing), new ItemStack(ItemInit.moduleDurability), new ItemStack(ArmorInit.spectrosSet.chestplate), new ItemStack(ItemInit.etheriumCondensed), new ItemStack(ItemInit.etheriumCondensed), new ItemStack(ItemInit.etheriumCondensed));
        recipes.add(spectrosHelmetUpgrade);
        recipes.add(spectrosLeggingsUpgrade);
        recipes.add(spectrosBootUpgrade);
        recipes.add(spectrosChestplateUpgrade);
        Recipe vitralitonHelmetUpgrade = new Recipe(ArmorInit.vitralitonPrimeSet.helmet, new ItemStack(ItemInit.moduleRestoration), new ItemStack(ItemInit.modulePower), new ItemStack(ArmorInit.vitralitonSet.helmet), new ItemStack(ItemInit.polariumCondensed), new ItemStack(ItemInit.polariumCondensed), new ItemStack(ItemInit.polariumCondensed));
        Recipe vitralitonLeggingsUpgrade = new Recipe(ArmorInit.vitralitonPrimeSet.leggings, new ItemStack(ItemInit.moduleRestoration), new ItemStack(ItemInit.moduleDurability), new ItemStack(ArmorInit.vitralitonSet.leggings), new ItemStack(ItemInit.polariumCondensed), new ItemStack(ItemInit.polariumCondensed), new ItemStack(ItemInit.polariumCondensed));
        Recipe vitralitonBootUpgrade = new Recipe(ArmorInit.vitralitonPrimeSet.boots, new ItemStack(ItemInit.moduleRestoration), new ItemStack(ItemInit.moduleConversion), new ItemStack(ArmorInit.vitralitonSet.boots), new ItemStack(ItemInit.polariumCondensed), new ItemStack(ItemInit.polariumCondensed), new ItemStack(ItemInit.polariumCondensed));
        Recipe vitralitonChestplateUpgrade = new Recipe(ArmorInit.vitralitonPrimeSet.chestplate, new ItemStack(ItemInit.moduleRestoration), new ItemStack(ItemInit.moduleAcceleration), new ItemStack(ArmorInit.vitralitonSet.chestplate), new ItemStack(ItemInit.polariumCondensed), new ItemStack(ItemInit.polariumCondensed), new ItemStack(ItemInit.polariumCondensed));
        recipes.add(vitralitonHelmetUpgrade);
        recipes.add(vitralitonLeggingsUpgrade);
        recipes.add(vitralitonBootUpgrade);
        recipes.add(vitralitonChestplateUpgrade);
        Recipe blightcystHelmetUpgrade = new Recipe(ArmorInit.blightcystPrimeSet.helmet, new ItemStack(ItemInit.moduleBiocalibration), new ItemStack(ItemInit.modulePower), new ItemStack(ArmorInit.blightcystSet.helmet), new ItemStack(ItemInit.kyvoriumCondensed), new ItemStack(ItemInit.kyvoriumCondensed), new ItemStack(ItemInit.kyvoriumCondensed));
        Recipe blightcystLeggingsUpgrade = new Recipe(ArmorInit.blightcystPrimeSet.leggings, new ItemStack(ItemInit.moduleBiocalibration), new ItemStack(ItemInit.moduleDurability), new ItemStack(ArmorInit.blightcystSet.leggings), new ItemStack(ItemInit.kyvoriumCondensed), new ItemStack(ItemInit.kyvoriumCondensed), new ItemStack(ItemInit.kyvoriumCondensed));
        Recipe blightcystBootUpgrade = new Recipe(ArmorInit.blightcystPrimeSet.boots, new ItemStack(ItemInit.moduleBiocalibration), new ItemStack(ItemInit.moduleTransmitting), new ItemStack(ArmorInit.blightcystSet.boots), new ItemStack(ItemInit.kyvoriumCondensed), new ItemStack(ItemInit.kyvoriumCondensed), new ItemStack(ItemInit.kyvoriumCondensed));
        Recipe blightcystChestplateUpgrade = new Recipe(ArmorInit.blightcystPrimeSet.chestplate, new ItemStack(ItemInit.moduleBiocalibration), new ItemStack(ItemInit.moduleConversion), new ItemStack(ArmorInit.blightcystSet.chestplate), new ItemStack(ItemInit.kyvoriumCondensed), new ItemStack(ItemInit.kyvoriumCondensed), new ItemStack(ItemInit.kyvoriumCondensed));
        recipes.add(blightcystHelmetUpgrade);
        recipes.add(blightcystLeggingsUpgrade);
        recipes.add(blightcystBootUpgrade);
        recipes.add(blightcystChestplateUpgrade);
    }
    public static ItemStack getResult(ItemStack... inputs) {
        if (recipes.isEmpty()) {
            init();
        }
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            if (inputs.length == 6 && ((inputs[0].func_77969_a(recipe.values[0]) && inputs[1].func_77969_a(recipe.values[1])) || (inputs[0].func_77969_a(recipe.values[1]) && inputs[1].func_77969_a(recipe.values[0])))) {
                boolean match = true;
                if (!inputs[2].func_77969_a(recipe.values[2])) {
                    match = false;
                }
                ArrayList<ItemStack> foundIngots = new ArrayList<>();
                ArrayList<ItemStack> ingots = new ArrayList<>();
                ingots.add(recipe.values[3]);
                ingots.add(recipe.values[4]);
                ingots.add(recipe.values[5]);
                for (int k = 3; k < 6; k++) {
                    for (ItemStack stack : ingots) {
                        if (stack.func_77969_a(inputs[k])) {
                            foundIngots.add(stack);
                        }
                    }
                }
                if (!foundIngots.containsAll(ingots)) {
                    match = false;
                }
                if (match) {
                    return new ItemStack(recipe.result);
                }
            }
        }
        return null;
    }
}
