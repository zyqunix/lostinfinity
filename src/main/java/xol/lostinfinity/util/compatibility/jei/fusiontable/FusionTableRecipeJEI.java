package xol.lostinfinity.util.compatibility.jei.fusiontable;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Map;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/compatibility/jei/fusiontable/FusionTableRecipeJEI.class */
public class FusionTableRecipeJEI {
    private static final FusionTableRecipeJEI INSTANCE = new FusionTableRecipeJEI();
    private final Table<ItemStack, ItemStack, ItemStack> FusionTableList = HashBasedTable.create();

    public FusionTableRecipeJEI() {
        addFusionTableRecipe(new ItemStack(ItemInit.ioniteBar), new ItemStack(ItemInit.inverseMagnecronite), new ItemStack(ItemInit.polyionite));
        addFusionTableRecipe(new ItemStack(ItemInit.inverseMagnecronite), new ItemStack(ItemInit.ioniteBar), new ItemStack(ItemInit.polyionite));
    }

    public static FusionTableRecipeJEI getInstance() {
        return INSTANCE;
    }

    public void addFusionTableRecipe(ItemStack input1, ItemStack input2, ItemStack result) {
        if (getFusionTableResult(input1, input2) != ItemStack.field_190927_a) {
            return;
        }
        this.FusionTableList.put(input1, input2, result);
    }

    public ItemStack getFusionTableResult(ItemStack input1, ItemStack input2) {
        for (Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.FusionTableList.columnMap().entrySet()) {
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

    public Table<ItemStack, ItemStack, ItemStack> getFusionTableList() {
        return this.FusionTableList;
    }
}
