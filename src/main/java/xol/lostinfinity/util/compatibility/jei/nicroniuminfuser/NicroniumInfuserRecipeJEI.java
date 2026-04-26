package xol.lostinfinity.util.compatibility.jei.nicroniuminfuser;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Map;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/compatibility/jei/nicroniuminfuser/NicroniumInfuserRecipeJEI.class */
public class NicroniumInfuserRecipeJEI {
    private static final NicroniumInfuserRecipeJEI INSTANCE = new NicroniumInfuserRecipeJEI();
    private final Table<ItemStack, ItemStack, ItemStack> nicroniumInfuserList = HashBasedTable.create();

    public NicroniumInfuserRecipeJEI() {
        addNicroniumInfuserRecipe(new ItemStack(ItemInit.powerfulPolarcronite), new ItemStack(ItemInit.nicronium), new ItemStack(ItemInit.infusedNicronium));
    }

    public static NicroniumInfuserRecipeJEI getInstance() {
        return INSTANCE;
    }

    public void addNicroniumInfuserRecipe(ItemStack input1, ItemStack input2, ItemStack result) {
        if (getNicroniumInfuserResult(input1, input2) != ItemStack.field_190927_a) {
            return;
        }
        this.nicroniumInfuserList.put(input1, input2, result);
    }

    public ItemStack getNicroniumInfuserResult(ItemStack input1, ItemStack input2) {
        for (Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.nicroniumInfuserList.columnMap().entrySet()) {
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

    public Table<ItemStack, ItemStack, ItemStack> getNicroniumInfuserList() {
        return this.nicroniumInfuserList;
    }
}
