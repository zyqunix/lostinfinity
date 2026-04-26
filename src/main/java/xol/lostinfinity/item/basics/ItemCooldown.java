package xol.lostinfinity.item.basics;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.item.classify.ICooldown;
public class ItemCooldown extends Item implements ICooldown {
    public ItemCooldown(String regName) {
        setRegistryName(regName);
        func_77655_b(regName);
        func_77625_d(1);
        ItemInit.ITEMS.add(this);
    }
    public boolean showDurabilityBar(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74772_a("lastUse", 0L);
        }
        long lastUse = stack.func_77978_p().func_74763_f("lastUse");
        long maxDelay = hasSimpleCooldown() ? getCooldown() : stack.func_77978_p().func_74762_e("ComplexCooldown");
        return System.currentTimeMillis() - lastUse <= maxDelay;
    }
    protected int getCooldown() {
        return 5000;
    }
    protected boolean hasSimpleCooldown() {
        return true;
    }
    public double getDurabilityForDisplay(ItemStack stack) {
        if (showDurabilityBar(stack)) {
            double result = System.currentTimeMillis() - stack.func_77978_p().func_74763_f("lastUse");
            int denominator = hasSimpleCooldown() ? getCooldown() : stack.func_77978_p().func_74762_e("ComplexCooldown");
            double fin = result / ((double) denominator);
            return 1.0d - Math.pow(fin, 1.0d);
        }
        return 1.0d;
    }
}
