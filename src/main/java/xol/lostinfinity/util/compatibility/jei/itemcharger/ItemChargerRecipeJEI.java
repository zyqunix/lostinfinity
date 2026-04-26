package xol.lostinfinity.util.compatibility.jei.itemcharger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ItemInit;
public class ItemChargerRecipeJEI {
    private static final ItemChargerRecipeJEI INSTANCE = new ItemChargerRecipeJEI();
    private final Map<List<ItemStack>, ItemStack> itemChargerList = new HashMap();
    public ItemChargerRecipeJEI() {
        ItemStack powerDrive = new ItemStack(ItemInit.powerDrive);
        powerDrive.func_190920_e(10);
        List<ItemStack> headCollector = new ArrayList<>(Arrays.asList(powerDrive, powerDrive, powerDrive, powerDrive));
        addItemChargerRecipe(headCollector, new ItemStack(ItemInit.headCollector));
    }
    public static ItemChargerRecipeJEI getInstance() {
        return INSTANCE;
    }
    public void addItemChargerRecipe(List<ItemStack> inputs, ItemStack result) {
        if (getItemChargerResult(inputs) != ItemStack.field_190927_a) {
            return;
        }
        this.itemChargerList.put(inputs, result);
    }
    public ItemStack getItemChargerResult(List<ItemStack> inputs) {
        for (Map.Entry<List<ItemStack>, ItemStack> entry : this.itemChargerList.entrySet()) {
            if (Objects.equals(inputs, entry.getKey())) {
                return entry.getValue();
            }
        }
        return ItemStack.field_190927_a;
    }
    public Map<List<ItemStack>, ItemStack> getItemChargerList() {
        return this.itemChargerList;
    }
}
