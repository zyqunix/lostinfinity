package xol.lostinfinity.util.compatibility.jei.fossilcombiner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/compatibility/jei/fossilcombiner/FossilCombinerRecipeJEI.class */
public class FossilCombinerRecipeJEI {
    private static final FossilCombinerRecipeJEI INSTANCE = new FossilCombinerRecipeJEI();
    private final Map<List<ItemStack>, ItemStack> fossilCombinerList = new HashMap();

    public FossilCombinerRecipeJEI() {
        List<ItemStack> pelicanRecipe = new ArrayList<>(Arrays.asList(ItemStack.field_190927_a, ItemStack.field_190927_a, ItemStack.field_190927_a, ItemStack.field_190927_a, ItemStack.field_190927_a, new ItemStack(ItemInit.fossilRibbedTail), new ItemStack(ItemInit.fossilSmallRibs), new ItemStack(ItemInit.fossilSmallRibs), new ItemStack(ItemInit.fossilSmallRibs), new ItemStack(ItemInit.fossilEelTopjaw), new ItemStack(ItemInit.fossilRibbedTail), ItemStack.field_190927_a, ItemStack.field_190927_a, ItemStack.field_190927_a, new ItemStack(ItemInit.fossilEelBotjaw)));
        addFossilCombinerRecipe(pelicanRecipe, new ItemStack(ItemInit.remainsPelicanEel));
    }

    public static FossilCombinerRecipeJEI getInstance() {
        return INSTANCE;
    }

    public void addFossilCombinerRecipe(List<ItemStack> inputs, ItemStack result) {
        if (getFossilCombinerResult(inputs) != ItemStack.field_190927_a) {
            return;
        }
        this.fossilCombinerList.put(inputs, result);
    }

    public ItemStack getFossilCombinerResult(List<ItemStack> inputs) {
        for (Map.Entry<List<ItemStack>, ItemStack> entry : this.fossilCombinerList.entrySet()) {
            if (Objects.equals(inputs, entry.getKey())) {
                return entry.getValue();
            }
        }
        return ItemStack.field_190927_a;
    }

    public Map<List<ItemStack>, ItemStack> getFossilCombinerList() {
        return this.fossilCombinerList;
    }
}
