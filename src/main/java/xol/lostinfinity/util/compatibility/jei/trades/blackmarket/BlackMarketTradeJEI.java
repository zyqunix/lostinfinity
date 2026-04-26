package xol.lostinfinity.util.compatibility.jei.trades.blackmarket;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Map;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ItemInit;
public class BlackMarketTradeJEI {
    private static final BlackMarketTradeJEI INSTANCE = new BlackMarketTradeJEI();
    private final Table<ItemStack, ItemStack, ItemStack> blackMarketTradeList = HashBasedTable.create();
    public BlackMarketTradeJEI() {
        ItemStack masterAlloy = new ItemStack(ItemInit.masterCraftedAlloy);
        masterAlloy.func_190920_e(15);
        addBlackMarketTradeRecipe(masterAlloy, new ItemStack(ItemInit.containerOfCollectionFull), new ItemStack(ItemInit.dualWeaponConvertor));
        ItemStack crystalAlloy = new ItemStack(ItemInit.crystallizedAlloy);
        crystalAlloy.func_190920_e(15);
        addBlackMarketTradeRecipe(crystalAlloy, new ItemStack(ItemInit.containerOfCollectionFull), new ItemStack(ItemInit.directWeaponConvertor));
        ItemStack starCrystal = new ItemStack(ItemInit.starcrystalCapacitor);
        starCrystal.func_190920_e(10);
        addBlackMarketTradeRecipe(starCrystal, new ItemStack(ItemInit.containerOfCollectionFull), new ItemStack(ItemInit.ultrapoweredCapacitor));
        ItemStack gigaCharge = new ItemStack(ItemInit.gigachargeSolutions);
        gigaCharge.func_190920_e(10);
        addBlackMarketTradeRecipe(gigaCharge, new ItemStack(ItemInit.containerOfCollectionFull), new ItemStack(ItemInit.minorMultiversalDevice));
        ItemStack unstableIngot = new ItemStack(ItemInit.unstableIngot);
        unstableIngot.func_190920_e(20);
        addBlackMarketTradeRecipe(unstableIngot, new ItemStack(ItemInit.containerOfCollectionFull), new ItemStack(ItemInit.radioactiveIsotopes));
        addBlackMarketTradeRecipe(new ItemStack(ItemInit.deviantFragmentBL), ItemStack.field_190927_a, new ItemStack(ItemInit.broachOfDeviancy));
        addBlackMarketTradeRecipe(new ItemStack(ItemInit.deviantFragmentBR), ItemStack.field_190927_a, new ItemStack(ItemInit.broachOfDeviancy));
        addBlackMarketTradeRecipe(new ItemStack(ItemInit.deviantFragmentTR), ItemStack.field_190927_a, new ItemStack(ItemInit.broachOfDeviancy));
        addBlackMarketTradeRecipe(new ItemStack(ItemInit.deviantFragmentTL), ItemStack.field_190927_a, new ItemStack(ItemInit.broachOfDeviancy));
        ItemStack silverToken = new ItemStack(ItemInit.silverToken);
        silverToken.func_190920_e(10);
        addBlackMarketTradeRecipe(silverToken, ItemStack.field_190927_a, new ItemStack(ItemInit.hypersonicDriveChamber));
        ItemStack amazonToken = new ItemStack(ItemInit.amazoniteToken);
        amazonToken.func_190920_e(4);
        ItemStack superCell = new ItemStack(ItemInit.superCell);
        superCell.func_190920_e(2);
        addBlackMarketTradeRecipe(amazonToken, new ItemStack(ItemInit.reactiveMushroom), superCell);
        ItemStack ghostlyHusk = new ItemStack(ItemInit.ghostlyHusk);
        ghostlyHusk.func_190920_e(60);
        addBlackMarketTradeRecipe(amazonToken, ghostlyHusk, new ItemStack(ItemInit.etherstockSeeds));
        ItemStack goldToken = new ItemStack(ItemInit.goldToken);
        addBlackMarketTradeRecipe(new ItemStack(ItemInit.boxOfLife), ItemStack.field_190927_a, goldToken);
    }
    public static BlackMarketTradeJEI getInstance() {
        return INSTANCE;
    }
    public void addBlackMarketTradeRecipe(ItemStack input1, ItemStack input2, ItemStack result) {
        if (getBlackMarketTradeResult(input1, input2) != ItemStack.field_190927_a) {
            return;
        }
        this.blackMarketTradeList.put(input1, input2, result);
    }
    public ItemStack getBlackMarketTradeResult(ItemStack input1, ItemStack input2) {
        for (Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.blackMarketTradeList.columnMap().entrySet()) {
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
    public Table<ItemStack, ItemStack, ItemStack> getBlackMarketTradeList() {
        return this.blackMarketTradeList;
    }
}
