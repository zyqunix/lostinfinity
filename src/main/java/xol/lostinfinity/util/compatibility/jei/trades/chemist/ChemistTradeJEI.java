package xol.lostinfinity.util.compatibility.jei.trades.chemist;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Map;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ItemInit;
public class ChemistTradeJEI {
    private static final ChemistTradeJEI INSTANCE = new ChemistTradeJEI();
    private final Table<ItemStack, ItemStack, ItemStack> chemistTradeList = HashBasedTable.create();
    public ChemistTradeJEI() {
        ItemStack goldToken = new ItemStack(ItemInit.goldToken);
        ItemStack blueCompound = new ItemStack(ItemInit.cureSampleBlue);
        blueCompound.func_190920_e(16);
        addChemistTradeRecipe(goldToken, ItemStack.field_190927_a, blueCompound);
        ItemStack greenCompound = new ItemStack(ItemInit.cureSampleGreen);
        greenCompound.func_190920_e(16);
        addChemistTradeRecipe(goldToken, ItemStack.field_190927_a, greenCompound);
        ItemStack orangeCompound = new ItemStack(ItemInit.cureSampleOrange);
        orangeCompound.func_190920_e(16);
        addChemistTradeRecipe(goldToken, ItemStack.field_190927_a, orangeCompound);
        ItemStack pinkCompound = new ItemStack(ItemInit.cureSamplePink);
        pinkCompound.func_190920_e(16);
        addChemistTradeRecipe(goldToken, ItemStack.field_190927_a, pinkCompound);
        ItemStack yellowCompound = new ItemStack(ItemInit.cureSampleYellow);
        yellowCompound.func_190920_e(16);
        addChemistTradeRecipe(goldToken, ItemStack.field_190927_a, yellowCompound);
        ItemStack amazonToken = new ItemStack(ItemInit.amazoniteToken);
        amazonToken.func_190920_e(10);
        ItemStack magicBiopowder = new ItemStack(ItemInit.magicBiopowder);
        magicBiopowder.func_190920_e(16);
        addChemistTradeRecipe(amazonToken, ItemStack.field_190927_a, magicBiopowder);
        ItemStack corruptedRoot = new ItemStack(ItemInit.corruptedRoot);
        corruptedRoot.func_190920_e(60);
        addChemistTradeRecipe(corruptedRoot, new ItemStack(ItemInit.containerOfCollectionFull), new ItemStack(ItemInit.colixiumCatenationPouch));
        ItemStack ghostlyHusk = new ItemStack(ItemInit.ghostlyHusk);
        ghostlyHusk.func_190920_e(60);
        addChemistTradeRecipe(ghostlyHusk, new ItemStack(ItemInit.containerOfCollectionFull), new ItemStack(ItemInit.phoroxiumCatenationPouch));
        ItemStack luminecentCubes = new ItemStack(ItemInit.luminescentCubes);
        luminecentCubes.func_190920_e(60);
        addChemistTradeRecipe(luminecentCubes, new ItemStack(ItemInit.containerOfCollectionFull), new ItemStack(ItemInit.laraxiumCatenationPouch));
    }
    public static ChemistTradeJEI getInstance() {
        return INSTANCE;
    }
    public void addChemistTradeRecipe(ItemStack input1, ItemStack input2, ItemStack result) {
        if (getChemistTradeResult(input1, input2) != ItemStack.field_190927_a) {
            return;
        }
        this.chemistTradeList.put(input1, input2, result);
    }
    public ItemStack getChemistTradeResult(ItemStack input1, ItemStack input2) {
        for (Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.chemistTradeList.columnMap().entrySet()) {
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
    public Table<ItemStack, ItemStack, ItemStack> getChemistTradeList() {
        return this.chemistTradeList;
    }
}
