package xol.lostinfinity.item.classify;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
public interface ICooldown {
    default void startCooldown(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
    }
    default void setComplexCooldown(ItemStack stack, int cooldown) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.func_77978_p().func_74768_a("ComplexCooldown", cooldown);
    }
}
