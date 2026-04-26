package xol.lostinfinity.util.compatibility.jei.trades.zirconia.cerulean;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Map;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ItemInit;
public class CeruleanTradeJEI {
    private static final CeruleanTradeJEI INSTANCE = new CeruleanTradeJEI();
    private final Table<ItemStack, ItemStack, ItemStack> ceruleanTradeList = HashBasedTable.create();
    public CeruleanTradeJEI() {
        ItemStack celestialDiamond10 = new ItemStack(ItemInit.celestialDiamond);
        celestialDiamond10.func_190920_e(10);
        addCeruleanTradeRecipe(celestialDiamond10, ItemStack.field_190927_a, new ItemStack(ItemInit.contenderPass));
        ItemStack celestialDiamond15 = new ItemStack(ItemInit.celestialDiamond);
        celestialDiamond15.func_190920_e(15);
        addCeruleanTradeRecipe(celestialDiamond15, ItemStack.field_190927_a, new ItemStack(ItemInit.mysteryBox));
        ItemStack zirconia20 = new ItemStack(ItemInit.zirconiaCerulean);
        zirconia20.func_190920_e(20);
        addCeruleanTradeRecipe(zirconia20, ItemStack.field_190927_a, new ItemStack(ItemInit.powerPlug));
        ItemStack zirconia50 = new ItemStack(ItemInit.zirconiaCerulean);
        zirconia50.func_190920_e(50);
        addCeruleanTradeRecipe(zirconia50, zirconia50, new ItemStack(ItemInit.ceruleanLens));
    }
    public static CeruleanTradeJEI getInstance() {
        return INSTANCE;
    }
    public void addCeruleanTradeRecipe(ItemStack input1, ItemStack input2, ItemStack result) {
        if (getCeruleanTradeResult(input1, input2) != ItemStack.field_190927_a) {
            return;
        }
        this.ceruleanTradeList.put(input1, input2, result);
    }
    public ItemStack getCeruleanTradeResult(ItemStack input1, ItemStack input2) {
        for (Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.ceruleanTradeList.columnMap().entrySet()) {
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
    public Table<ItemStack, ItemStack, ItemStack> getCeruleanTradeList() {
        return this.ceruleanTradeList;
    }
}
