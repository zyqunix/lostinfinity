package xol.lostinfinity.util.compatibility.jei.rainfallgenerator;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Map;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/compatibility/jei/rainfallgenerator/RainfallGeneratorRecipeJEI.class */
public class RainfallGeneratorRecipeJEI {
    private static final RainfallGeneratorRecipeJEI INSTANCE = new RainfallGeneratorRecipeJEI();
    private final Table<ItemStack, ItemStack, ItemStack> rainfallGeneratorList = HashBasedTable.create();

    public RainfallGeneratorRecipeJEI() {
        addRainfallGeneratorRecipe(new ItemStack(ItemInit.bagOfMagicCrystals), ItemStack.field_190927_a, ItemStack.field_190927_a);
    }

    public static RainfallGeneratorRecipeJEI getInstance() {
        return INSTANCE;
    }

    public void addRainfallGeneratorRecipe(ItemStack input1, ItemStack input2, ItemStack result) {
        if (getRainfallGeneratorResult(input1, input2) != ItemStack.field_190927_a) {
            return;
        }
        this.rainfallGeneratorList.put(input1, input2, result);
    }

    public ItemStack getRainfallGeneratorResult(ItemStack input1, ItemStack input2) {
        for (Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.rainfallGeneratorList.columnMap().entrySet()) {
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

    public Table<ItemStack, ItemStack, ItemStack> getRainfallGeneratorList() {
        return this.rainfallGeneratorList;
    }
}
