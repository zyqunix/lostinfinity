package xol.lostinfinity.item.misc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.IHotbarTick;
import xol.lostinfinity.item.classify.ISwitchModels;
public class ItemRainfallCollector extends Item implements IHotbarTick, ISwitchModels {
    public ItemRainfallCollector(String regName) {
        func_77637_a(TabsInit.TAB_AUXMATS);
        setRegistryName(regName);
        func_77655_b(regName);
        func_77625_d(1);
        ItemInit.ITEMS.add(this);
        setModelSwitch("collection", this, 2);
    }
    @Override // xol.lostinfinity.item.classify.IHotbarTick
    public void hotbarTick(EntityPlayer player, int itemSlot, ItemStack stack) {
        if (showDurabilityBar(stack)) {
            long progress = stack.func_77978_p().func_74763_f("Progress");
            if (!player.field_70170_p.field_72995_K && progress >= getMaxProgress()) {
                stack.func_190918_g(1);
                if (player != null) {
                    player.func_191521_c(new ItemStack(ItemInit.rainfallCollectorFull));
                }
            }
        }
    }
    public boolean showDurabilityBar(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74772_a("Progress", 0L);
            stack.func_77978_p().func_74768_a("collection_data", 0);
        }
        long progress = stack.func_77978_p().func_74763_f("Progress");
        return progress <= ((long) getMaxProgress());
    }
    protected static int getMaxProgress() {
        return 20;
    }
    public double getDurabilityForDisplay(ItemStack stack) {
        if (showDurabilityBar(stack)) {
            double progress = Math.min(stack.func_77978_p().func_74763_f("Progress"), getMaxProgress() - 1);
            double fin = progress / ((double) getMaxProgress());
            if (fin < 0.1d) {
                stack.func_77978_p().func_74768_a("collection_data", 0);
            } else if (fin < 0.3d) {
                stack.func_77978_p().func_74768_a("collection_data", 1);
            } else if (fin < 0.6d) {
                stack.func_77978_p().func_74768_a("collection_data", 2);
            } else if (fin < 0.8d) {
                stack.func_77978_p().func_74768_a("collection_data", 3);
            } else {
                stack.func_77978_p().func_74768_a("collection_data", 4);
            }
            return 1.0d - Math.pow(fin, 1.0d);
        }
        return 1.0d;
    }
}
