package xol.lostinfinity.util.compatibility.jei.trades.archeologist;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Map;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/compatibility/jei/trades/archeologist/ArcheologistTradeJEI.class */
public class ArcheologistTradeJEI {
    private static final ArcheologistTradeJEI INSTANCE = new ArcheologistTradeJEI();
    private final Table<ItemStack, ItemStack, ItemStack> archeologistTradeList = HashBasedTable.create();

    public ArcheologistTradeJEI() {
        ItemStack silverTokens = new ItemStack(ItemInit.silverToken);
        silverTokens.func_190920_e(16);
        addArcheologistTradeRecipe(new ItemStack(ItemInit.remainsPelicanEel), ItemStack.field_190927_a, silverTokens);
    }

    public static ArcheologistTradeJEI getInstance() {
        return INSTANCE;
    }

    public void addArcheologistTradeRecipe(ItemStack input1, ItemStack input2, ItemStack result) {
        if (getArcheologistTradeResult(input1, input2) != ItemStack.field_190927_a) {
            return;
        }
        this.archeologistTradeList.put(input1, input2, result);
    }

    public ItemStack getArcheologistTradeResult(ItemStack input1, ItemStack input2) {
        for (Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.archeologistTradeList.columnMap().entrySet()) {
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

    public Table<ItemStack, ItemStack, ItemStack> getArcheologistTradeList() {
        return this.archeologistTradeList;
    }
}
