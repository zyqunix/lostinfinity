package xol.lostinfinity.util.compatibility.jei.trades.zirconia.oyster;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Map;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ItemInit;
public class OysterTradeJEI {
    private static final OysterTradeJEI INSTANCE = new OysterTradeJEI();
    private final Table<ItemStack, ItemStack, ItemStack> zirconiaTradeList = HashBasedTable.create();
    public OysterTradeJEI() {
        ItemStack celestialDiamond10 = new ItemStack(ItemInit.celestialDiamond);
        celestialDiamond10.func_190920_e(10);
        addOysterTradeRecipe(celestialDiamond10, ItemStack.field_190927_a, new ItemStack(ItemInit.contenderPass));
        ItemStack celestialDiamond15 = new ItemStack(ItemInit.celestialDiamond);
        celestialDiamond15.func_190920_e(15);
        addOysterTradeRecipe(celestialDiamond15, ItemStack.field_190927_a, new ItemStack(ItemInit.mysteryBox));
        ItemStack zirconia20 = new ItemStack(ItemInit.zirconiaOyster);
        zirconia20.func_190920_e(20);
        addOysterTradeRecipe(zirconia20, ItemStack.field_190927_a, new ItemStack(ItemInit.ellipticalProjector));
        ItemStack zirconia50 = new ItemStack(ItemInit.zirconiaOyster);
        zirconia50.func_190920_e(50);
        addOysterTradeRecipe(zirconia50, zirconia50, new ItemStack(ItemInit.simpleMicrocontroller));
    }
    public static OysterTradeJEI getInstance() {
        return INSTANCE;
    }
    public void addOysterTradeRecipe(ItemStack input1, ItemStack input2, ItemStack result) {
        if (getOysterTradeResult(input1, input2) != ItemStack.field_190927_a) {
            return;
        }
        this.zirconiaTradeList.put(input1, input2, result);
    }
    public ItemStack getOysterTradeResult(ItemStack input1, ItemStack input2) {
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
