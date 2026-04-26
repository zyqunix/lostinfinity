package xol.lostinfinity.item.basics;
import net.minecraft.item.Item;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
public class ItemArenaToken extends Item {
    public ItemArenaToken(String regName) {
        setRegistryName(regName);
        func_77655_b(regName);
        func_77637_a(TabsInit.TAB_CELESTIALARENA);
        ItemInit.ITEMS.add(this);
    }
}
