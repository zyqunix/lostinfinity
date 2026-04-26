package xol.lostinfinity.item.basics;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import xol.lostinfinity.init.ItemInit;
public class ItemBasic extends Item {
    public ItemBasic(String regName, CreativeTabs tab) {
        setRegistryName(regName);
        func_77655_b(regName);
        func_77637_a(tab);
        ItemInit.ITEMS.add(this);
    }
}
