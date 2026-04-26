package xol.lostinfinity.util.compatibility.jei.pickchargingtable;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Map;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/compatibility/jei/pickchargingtable/PickChargingTableRecipeJEI.class */
public class PickChargingTableRecipeJEI {
    private static final PickChargingTableRecipeJEI INSTANCE = new PickChargingTableRecipeJEI();
    private final Table<ItemStack, ItemStack, ItemStack> pickChargingTableList = HashBasedTable.create();

    public PickChargingTableRecipeJEI() {
        ItemStack pickDefault = new ItemStack(ItemInit.forgeFirePickaxe);
        ItemStack pickEmberium = new ItemStack(ItemInit.forgeFirePickaxe);
        pickEmberium.func_77982_d(new NBTTagCompound());
        pickEmberium.func_77978_p().func_74768_a("emberium", 250);
        ItemStack reactiveEmberium = new ItemStack(ItemInit.reactiveCrystalEmberium);
        reactiveEmberium.func_190920_e(10);
        addPickChargingTableRecipe(reactiveEmberium, pickDefault, pickEmberium);
        ItemStack pickHextorium = new ItemStack(ItemInit.forgeFirePickaxe);
        pickHextorium.func_77982_d(new NBTTagCompound());
        pickHextorium.func_77978_p().func_74768_a("hextorium", 250);
        ItemStack reactiveHextorium = new ItemStack(ItemInit.reactiveCrystalHextorium);
        reactiveHextorium.func_190920_e(10);
        addPickChargingTableRecipe(reactiveHextorium, pickDefault, pickHextorium);
        ItemStack pickCrystonium = new ItemStack(ItemInit.forgeFirePickaxe);
        pickCrystonium.func_77982_d(new NBTTagCompound());
        pickCrystonium.func_77978_p().func_74768_a("crystonium", 250);
        ItemStack reactiveCrystonium = new ItemStack(ItemInit.reactiveCrystalCrystonium);
        reactiveCrystonium.func_190920_e(10);
        addPickChargingTableRecipe(reactiveCrystonium, pickDefault, pickCrystonium);
        ItemStack pickAstrallium = new ItemStack(ItemInit.forgeFirePickaxe);
        pickAstrallium.func_77982_d(new NBTTagCompound());
        pickAstrallium.func_77978_p().func_74768_a("astrallium", 250);
        ItemStack reactiveAstrallium = new ItemStack(ItemInit.reactiveCrystalAstrallium);
        reactiveAstrallium.func_190920_e(10);
        addPickChargingTableRecipe(reactiveAstrallium, pickDefault, pickAstrallium);
        ItemStack pickKylaxium = new ItemStack(ItemInit.forgeFirePickaxe);
        pickKylaxium.func_77982_d(new NBTTagCompound());
        pickKylaxium.func_77978_p().func_74768_a("kylaxium", 250);
        ItemStack reactiveKylaxium = new ItemStack(ItemInit.reactiveCrystalKylaxium);
        reactiveKylaxium.func_190920_e(10);
        addPickChargingTableRecipe(reactiveKylaxium, pickDefault, pickKylaxium);
        ItemStack pickVellorium = new ItemStack(ItemInit.forgeFirePickaxe);
        pickVellorium.func_77982_d(new NBTTagCompound());
        pickVellorium.func_77978_p().func_74768_a("vellorium", 250);
        ItemStack reactiveVellorium = new ItemStack(ItemInit.reactiveCrystalVellorium);
        reactiveVellorium.func_190920_e(10);
        addPickChargingTableRecipe(reactiveVellorium, pickDefault, pickVellorium);
        ItemStack pickIncadium = new ItemStack(ItemInit.forgeFirePickaxe);
        pickIncadium.func_77982_d(new NBTTagCompound());
        pickIncadium.func_77978_p().func_74768_a("incadium", 250);
        ItemStack reactiveIncadium = new ItemStack(ItemInit.reactiveCrystalIncadium);
        reactiveIncadium.func_190920_e(10);
        addPickChargingTableRecipe(reactiveIncadium, pickDefault, pickIncadium);
        ItemStack pickNoxerium = new ItemStack(ItemInit.forgeFirePickaxe);
        pickNoxerium.func_77982_d(new NBTTagCompound());
        pickNoxerium.func_77978_p().func_74768_a("noxerium", 250);
        ItemStack reactiveNoxerium = new ItemStack(ItemInit.reactiveCrystalNoxerium);
        reactiveNoxerium.func_190920_e(10);
        addPickChargingTableRecipe(reactiveNoxerium, pickDefault, pickNoxerium);
        ItemStack pickOlysium = new ItemStack(ItemInit.forgeFirePickaxe);
        pickOlysium.func_77982_d(new NBTTagCompound());
        pickOlysium.func_77978_p().func_74768_a("olysium", 250);
        ItemStack reactiveOlysium = new ItemStack(ItemInit.reactiveCrystalOlysium);
        reactiveOlysium.func_190920_e(10);
        addPickChargingTableRecipe(reactiveOlysium, pickDefault, pickOlysium);
        ItemStack pickDetherium = new ItemStack(ItemInit.forgeFirePickaxe);
        pickDetherium.func_77982_d(new NBTTagCompound());
        pickDetherium.func_77978_p().func_74768_a("detherium", 250);
        ItemStack reactiveDetherium = new ItemStack(ItemInit.reactiveCrystalDetherium);
        reactiveDetherium.func_190920_e(10);
        addPickChargingTableRecipe(reactiveDetherium, pickDefault, pickDetherium);
        ItemStack pickPhytrosium = new ItemStack(ItemInit.forgeFirePickaxe);
        pickPhytrosium.func_77982_d(new NBTTagCompound());
        pickPhytrosium.func_77978_p().func_74768_a("phytrosium", 250);
        ItemStack reactivePhytrosium = new ItemStack(ItemInit.reactiveCrystalPhytrosium);
        reactivePhytrosium.func_190920_e(10);
        addPickChargingTableRecipe(reactivePhytrosium, pickDefault, pickPhytrosium);
        ItemStack pickXerovium = new ItemStack(ItemInit.forgeFirePickaxe);
        pickXerovium.func_77982_d(new NBTTagCompound());
        pickXerovium.func_77978_p().func_74768_a("xerovium", 250);
        ItemStack reactiveXerovium = new ItemStack(ItemInit.reactiveCrystalXerovium);
        reactiveXerovium.func_190920_e(10);
        addPickChargingTableRecipe(reactiveXerovium, pickDefault, pickXerovium);
    }

    public static PickChargingTableRecipeJEI getInstance() {
        return INSTANCE;
    }

    public void addPickChargingTableRecipe(ItemStack input1, ItemStack input2, ItemStack result) {
        if (getPickChargingTableResult(input1, input2) != ItemStack.field_190927_a) {
            return;
        }
        this.pickChargingTableList.put(input1, input2, result);
    }

    public ItemStack getPickChargingTableResult(ItemStack input1, ItemStack input2) {
        for (Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.pickChargingTableList.columnMap().entrySet()) {
            if (compareItemStacks(input1, entry.getKey())) {
                for (Map.Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
                    if (compareItemStacks(input2, ent.getKey())) {
                        return ent.getValue();
                    }
                }
            }
        }
        return ItemStack.field_190927_a;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
        return stack1.func_77973_b() == stack2.func_77973_b() && stack1.func_77960_j() == stack2.func_77960_j();
    }

    public Table<ItemStack, ItemStack, ItemStack> getPickChargingTableList() {
        return this.pickChargingTableList;
    }
}
