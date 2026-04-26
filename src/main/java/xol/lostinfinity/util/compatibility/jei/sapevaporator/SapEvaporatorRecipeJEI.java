package xol.lostinfinity.util.compatibility.jei.sapevaporator;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Map;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ItemInit;
public class SapEvaporatorRecipeJEI {
    private static final SapEvaporatorRecipeJEI INSTANCE = new SapEvaporatorRecipeJEI();
    private final Table<ItemStack, ItemStack, ItemStack> sapEvaporatorList = HashBasedTable.create();
    public SapEvaporatorRecipeJEI() {
        addSapEvaporatorRecipe(new ItemStack(ItemInit.pyreLog), new ItemStack(ItemInit.jarOfSap), new ItemStack(ItemInit.jarOfSyrup));
    }
    public static SapEvaporatorRecipeJEI getInstance() {
        return INSTANCE;
    }
    public void addSapEvaporatorRecipe(ItemStack input1, ItemStack input2, ItemStack result) {
        if (getSapEvaporatorResult(input1, input2) != ItemStack.field_190927_a) {
            return;
        }
        this.sapEvaporatorList.put(input1, input2, result);
    }
    public ItemStack getSapEvaporatorResult(ItemStack input1, ItemStack input2) {
        for (Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.sapEvaporatorList.columnMap().entrySet()) {
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
    public Table<ItemStack, ItemStack, ItemStack> getSapEvaporatorList() {
        return this.sapEvaporatorList;
    }
}
