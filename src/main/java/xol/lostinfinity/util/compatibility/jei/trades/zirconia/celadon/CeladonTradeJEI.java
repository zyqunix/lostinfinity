package xol.lostinfinity.util.compatibility.jei.trades.zirconia.celadon;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Map;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ItemInit;
public class CeladonTradeJEI {
    private static final CeladonTradeJEI INSTANCE = new CeladonTradeJEI();
    private final Table<ItemStack, ItemStack, ItemStack> zirconiaTradeList = HashBasedTable.create();
    public CeladonTradeJEI() {
        ItemStack celestialDiamond10 = new ItemStack(ItemInit.celestialDiamond);
        celestialDiamond10.func_190920_e(10);
        addCeladonTradeRecipe(celestialDiamond10, ItemStack.field_190927_a, new ItemStack(ItemInit.contenderPass));
        ItemStack celestialDiamond15 = new ItemStack(ItemInit.celestialDiamond);
        celestialDiamond15.func_190920_e(15);
        addCeladonTradeRecipe(celestialDiamond15, ItemStack.field_190927_a, new ItemStack(ItemInit.mysteryBox));
        ItemStack zirconia20 = new ItemStack(ItemInit.zirconiaCeladon);
        zirconia20.func_190920_e(20);
        addCeladonTradeRecipe(zirconia20, ItemStack.field_190927_a, new ItemStack(ItemInit.superInductor));
        ItemStack zirconia50 = new ItemStack(ItemInit.zirconiaCeladon);
        zirconia50.func_190920_e(50);
        addCeladonTradeRecipe(zirconia50, zirconia50, new ItemStack(ItemInit.advancedRelay));
    }
    public static CeladonTradeJEI getInstance() {
        return INSTANCE;
    }
    public void addCeladonTradeRecipe(ItemStack input1, ItemStack input2, ItemStack result) {
        if (getCeladonTradeResult(input1, input2) != ItemStack.field_190927_a) {
            return;
        }
        this.zirconiaTradeList.put(input1, input2, result);
    }
    public ItemStack getCeladonTradeResult(ItemStack input1, ItemStack input2) {
        for (Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.zirconiaTradeList.columnMap().entrySet()) {
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
    public Table<ItemStack, ItemStack, ItemStack> getZirconiaTradeList() {
        return this.zirconiaTradeList;
    }
}
