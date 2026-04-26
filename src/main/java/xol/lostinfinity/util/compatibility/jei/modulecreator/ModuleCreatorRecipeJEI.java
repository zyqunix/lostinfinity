package xol.lostinfinity.util.compatibility.jei.modulecreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/compatibility/jei/modulecreator/ModuleCreatorRecipeJEI.class */
public class ModuleCreatorRecipeJEI {
    private static final ModuleCreatorRecipeJEI INSTANCE = new ModuleCreatorRecipeJEI();
    private final Map<List<ItemStack>, ItemStack> moduleCreatorList = new HashMap();

    public ModuleCreatorRecipeJEI() {
        ItemStack moduleEmpty = new ItemStack(ItemInit.moduleContainer);
        List<ItemStack> acceleration = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.supermutatedBatwing), new ItemStack(ItemInit.supermutatedWing), new ItemStack(ItemInit.superStimulant), moduleEmpty));
        addModuleCreatorRecipe(acceleration, new ItemStack(ItemInit.moduleAcceleration));
        List<ItemStack> power = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.powerControlDisc), new ItemStack(ItemInit.overchargedCell), new ItemStack(ItemInit.powerClamp), moduleEmpty));
        addModuleCreatorRecipe(power, new ItemStack(ItemInit.modulePower));
        List<ItemStack> restoration = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.lifeboundEmerald), new ItemStack(ItemInit.jarOfSyrup), new ItemStack(ItemInit.lifestrainEnigma), moduleEmpty));
        addModuleCreatorRecipe(restoration, new ItemStack(ItemInit.moduleRestoration));
        List<ItemStack> range = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.advancedSignalReceiver), new ItemStack(ItemInit.geocoordinatedOrb), new ItemStack(ItemInit.nanofluoricAcid), moduleEmpty));
        addModuleCreatorRecipe(range, new ItemStack(ItemInit.moduleRange));
        List<ItemStack> conversion = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.atomicCell), new ItemStack(ItemInit.fracturedMultiversite), new ItemStack(ItemInit.volatileGloop), moduleEmpty));
        addModuleCreatorRecipe(conversion, new ItemStack(ItemInit.moduleConversion));
        List<ItemStack> durability = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.durableHusk), new ItemStack(ItemInit.crystallizedAlloy), new ItemStack(ItemInit.supermutatedPelt), moduleEmpty));
        addModuleCreatorRecipe(durability, new ItemStack(ItemInit.moduleDurability));
        List<ItemStack> hexing = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.crystalOfCurses), new ItemStack(ItemInit.ghostlyHusk), new ItemStack(ItemInit.cursedEmerald), moduleEmpty));
        addModuleCreatorRecipe(hexing, new ItemStack(ItemInit.moduleHexing));
        List<ItemStack> weight = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.ultralightDust), new ItemStack(ItemInit.weightlessGem), new ItemStack(ItemInit.gravityCore), moduleEmpty));
        addModuleCreatorRecipe(weight, new ItemStack(ItemInit.moduleWeight));
        List<ItemStack> transmitting = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.murkyClay), new ItemStack(ItemInit.reconfiguredMatter), new ItemStack(ItemInit.advancedSignalReceiver), moduleEmpty));
        addModuleCreatorRecipe(transmitting, new ItemStack(ItemInit.moduleTransmitting));
        List<ItemStack> biocalibration = new ArrayList<>(Arrays.asList(new ItemStack(ItemInit.astralOrgan), new ItemStack(ItemInit.organicShadowMatter), new ItemStack(ItemInit.biosyncedClock), moduleEmpty));
        addModuleCreatorRecipe(biocalibration, new ItemStack(ItemInit.moduleBiocalibration));
    }

    public static ModuleCreatorRecipeJEI getInstance() {
        return INSTANCE;
    }

    public void addModuleCreatorRecipe(List<ItemStack> inputs, ItemStack result) {
        if (getModuleCreatorResult(inputs) != ItemStack.field_190927_a) {
            return;
        }
        this.moduleCreatorList.put(inputs, result);
    }

    public ItemStack getModuleCreatorResult(List<ItemStack> inputs) {
        for (Map.Entry<List<ItemStack>, ItemStack> entry : this.moduleCreatorList.entrySet()) {
            if (Objects.equals(inputs, entry.getKey())) {
                return entry.getValue();
            }
        }
        return ItemStack.field_190927_a;
    }

    public Map<List<ItemStack>, ItemStack> getModuleCreatorList() {
        return this.moduleCreatorList;
    }
}
