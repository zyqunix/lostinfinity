package xol.lostinfinity.util.compatibility.jei.modulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ArmorInit;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/compatibility/jei/modulator/ModulatorRecipeJEI.class */
public class ModulatorRecipeJEI {
    private static final ModulatorRecipeJEI INSTANCE = new ModulatorRecipeJEI();
    private final Map<List<ItemStack>, ItemStack> modulatorList = new HashMap();

    public ModulatorRecipeJEI() {
        List<ItemStack> vampHelmetInputs = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.moduleRange), new ItemStack(ItemInit.modulePower), new ItemStack(ArmorInit.vampyreonSet.helmet), new ItemStack(ItemInit.maliciumCondensed), new ItemStack(ItemInit.maliciumCondensed), new ItemStack(ItemInit.maliciumCondensed)));
        addModulatorRecipe(vampHelmetInputs, new ItemStack(ArmorInit.vampyreonPrimeSet.helmet));
        List<ItemStack> vampLeggingsInputs = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.moduleRange), new ItemStack(ItemInit.moduleRestoration), new ItemStack(ArmorInit.vampyreonSet.leggings), new ItemStack(ItemInit.maliciumCondensed), new ItemStack(ItemInit.maliciumCondensed), new ItemStack(ItemInit.maliciumCondensed)));
        addModulatorRecipe(vampLeggingsInputs, new ItemStack(ArmorInit.vampyreonPrimeSet.leggings));
        List<ItemStack> vampBootsInputs = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.moduleRange), new ItemStack(ItemInit.moduleAcceleration), new ItemStack(ArmorInit.vampyreonSet.boots), new ItemStack(ItemInit.maliciumCondensed), new ItemStack(ItemInit.maliciumCondensed), new ItemStack(ItemInit.maliciumCondensed)));
        addModulatorRecipe(vampBootsInputs, new ItemStack(ArmorInit.vampyreonPrimeSet.boots));
        List<ItemStack> vampChestplateInputs = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.moduleRange), new ItemStack(ItemInit.moduleConversion), new ItemStack(ArmorInit.vampyreonSet.chestplate), new ItemStack(ItemInit.maliciumCondensed), new ItemStack(ItemInit.maliciumCondensed), new ItemStack(ItemInit.maliciumCondensed)));
        addModulatorRecipe(vampChestplateInputs, new ItemStack(ArmorInit.vampyreonPrimeSet.chestplate));
        List<ItemStack> spectrosHelmetInputs = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.moduleHexing), new ItemStack(ItemInit.moduleWeight), new ItemStack(ArmorInit.spectrosSet.helmet), new ItemStack(ItemInit.etheriumCondensed), new ItemStack(ItemInit.etheriumCondensed), new ItemStack(ItemInit.etheriumCondensed)));
        addModulatorRecipe(spectrosHelmetInputs, new ItemStack(ArmorInit.spectrosPrimeSet.helmet));
        List<ItemStack> spectrosLeggingsInputs = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.moduleHexing), new ItemStack(ItemInit.moduleRestoration), new ItemStack(ArmorInit.spectrosSet.leggings), new ItemStack(ItemInit.etheriumCondensed), new ItemStack(ItemInit.etheriumCondensed), new ItemStack(ItemInit.etheriumCondensed)));
        addModulatorRecipe(spectrosLeggingsInputs, new ItemStack(ArmorInit.spectrosPrimeSet.leggings));
        List<ItemStack> spectrosBootsInputs = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.moduleHexing), new ItemStack(ItemInit.moduleTransmitting), new ItemStack(ArmorInit.spectrosSet.boots), new ItemStack(ItemInit.etheriumCondensed), new ItemStack(ItemInit.etheriumCondensed), new ItemStack(ItemInit.etheriumCondensed)));
        addModulatorRecipe(spectrosBootsInputs, new ItemStack(ArmorInit.spectrosPrimeSet.boots));
        List<ItemStack> spectrosChestplateInputs = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.moduleHexing), new ItemStack(ItemInit.moduleDurability), new ItemStack(ArmorInit.spectrosSet.chestplate), new ItemStack(ItemInit.etheriumCondensed), new ItemStack(ItemInit.etheriumCondensed), new ItemStack(ItemInit.etheriumCondensed)));
        addModulatorRecipe(spectrosChestplateInputs, new ItemStack(ArmorInit.spectrosPrimeSet.chestplate));
        List<ItemStack> vitralitonHelmetInputs = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.moduleRestoration), new ItemStack(ItemInit.modulePower), new ItemStack(ArmorInit.vitralitonSet.helmet), new ItemStack(ItemInit.polariumCondensed), new ItemStack(ItemInit.polariumCondensed), new ItemStack(ItemInit.polariumCondensed)));
        addModulatorRecipe(vitralitonHelmetInputs, new ItemStack(ArmorInit.vitralitonPrimeSet.helmet));
        List<ItemStack> vitralitonLeggingsInputs = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.moduleRestoration), new ItemStack(ItemInit.moduleDurability), new ItemStack(ArmorInit.vitralitonSet.leggings), new ItemStack(ItemInit.polariumCondensed), new ItemStack(ItemInit.polariumCondensed), new ItemStack(ItemInit.polariumCondensed)));
        addModulatorRecipe(vitralitonLeggingsInputs, new ItemStack(ArmorInit.vitralitonPrimeSet.leggings));
        List<ItemStack> vitralitonBootsInputs = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.moduleRestoration), new ItemStack(ItemInit.moduleConversion), new ItemStack(ArmorInit.vitralitonSet.boots), new ItemStack(ItemInit.polariumCondensed), new ItemStack(ItemInit.polariumCondensed), new ItemStack(ItemInit.polariumCondensed)));
        addModulatorRecipe(vitralitonBootsInputs, new ItemStack(ArmorInit.vitralitonPrimeSet.boots));
        List<ItemStack> vitralitonChestplateInputs = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.moduleRestoration), new ItemStack(ItemInit.moduleAcceleration), new ItemStack(ArmorInit.vitralitonSet.chestplate), new ItemStack(ItemInit.polariumCondensed), new ItemStack(ItemInit.polariumCondensed), new ItemStack(ItemInit.polariumCondensed)));
        addModulatorRecipe(vitralitonChestplateInputs, new ItemStack(ArmorInit.vitralitonPrimeSet.chestplate));
        List<ItemStack> blightcystHelmetInputs = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.moduleBiocalibration), new ItemStack(ItemInit.modulePower), new ItemStack(ArmorInit.blightcystSet.helmet), new ItemStack(ItemInit.kyvoriumCondensed), new ItemStack(ItemInit.kyvoriumCondensed), new ItemStack(ItemInit.kyvoriumCondensed)));
        addModulatorRecipe(blightcystHelmetInputs, new ItemStack(ArmorInit.blightcystPrimeSet.helmet));
        List<ItemStack> blightcystLeggingsInputs = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.moduleBiocalibration), new ItemStack(ItemInit.moduleDurability), new ItemStack(ArmorInit.blightcystSet.leggings), new ItemStack(ItemInit.kyvoriumCondensed), new ItemStack(ItemInit.kyvoriumCondensed), new ItemStack(ItemInit.kyvoriumCondensed)));
        addModulatorRecipe(blightcystLeggingsInputs, new ItemStack(ArmorInit.blightcystPrimeSet.leggings));
        List<ItemStack> blightcystBootsInputs = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.moduleBiocalibration), new ItemStack(ItemInit.moduleTransmitting), new ItemStack(ArmorInit.blightcystSet.boots), new ItemStack(ItemInit.kyvoriumCondensed), new ItemStack(ItemInit.kyvoriumCondensed), new ItemStack(ItemInit.kyvoriumCondensed)));
        addModulatorRecipe(blightcystBootsInputs, new ItemStack(ArmorInit.blightcystPrimeSet.boots));
        List<ItemStack> blightcystChestplateInputs = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.moduleBiocalibration), new ItemStack(ItemInit.moduleConversion), new ItemStack(ArmorInit.blightcystSet.chestplate), new ItemStack(ItemInit.kyvoriumCondensed), new ItemStack(ItemInit.kyvoriumCondensed), new ItemStack(ItemInit.kyvoriumCondensed)));
        addModulatorRecipe(blightcystChestplateInputs, new ItemStack(ArmorInit.blightcystPrimeSet.chestplate));
    }

    public static ModulatorRecipeJEI getInstance() {
        return INSTANCE;
    }

    public void addModulatorRecipe(List<ItemStack> inputs, ItemStack result) {
        if (getModulatorResult(inputs) != ItemStack.field_190927_a) {
            return;
        }
        this.modulatorList.put(inputs, result);
    }

    public ItemStack getModulatorResult(List<ItemStack> inputs) {
        for (Map.Entry<List<ItemStack>, ItemStack> entry : this.modulatorList.entrySet()) {
            if (Objects.equals(inputs, entry.getKey())) {
                return entry.getValue();
            }
        }
        return ItemStack.field_190927_a;
    }

    public Map<List<ItemStack>, ItemStack> getModulatorList() {
        return this.modulatorList;
    }
}
