package xol.lostinfinity.item.capability;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import xol.lostinfinity.item.misc.ItemShipmentBox;
public class ShipmentBoxProvider implements ICapabilityProvider, ICapabilitySerializable<NBTBase> {
    private ItemStackHandler handler = new ItemStackHandler(28);
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return false;
    }
    @Nullable
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing enumFacing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) this.handler;
        }
        return null;
    }
    public NBTBase serializeNBT() {
        return this.handler.serializeNBT();
    }
    public void deserializeNBT(NBTBase nbt) {
        this.handler.deserializeNBT((NBTTagCompound) nbt);
    }
    public static int getWeight(ItemStackHandler handler) {
        int weight = 0;
        if (handler == null) {
            return 0;
        }
        for (int i = 0; i < handler.getSlots(); i++) {
            if (!handler.getStackInSlot(i).func_190926_b()) {
                Item item = handler.getStackInSlot(i).func_77973_b();
                weight += ItemShipmentBox.getItemWeight(item);
            }
        }
        return weight;
    }
    public static boolean transferItems(ItemStackHandler handler, NonNullList<ItemStack> invTo) {
        if (handler == null || invTo == null) {
            return false;
        }
        for (int i = 0; i < handler.getSlots(); i++) {
            if (!handler.getStackInSlot(i).func_190926_b()) {
                int nextSlot = -1;
                int j = 0;
                while (true) {
                    if (j >= invTo.size()) {
                        break;
                    }
                    if (!((ItemStack) invTo.get(j)).func_190926_b()) {
                        j++;
                    } else {
                        nextSlot = j;
                        break;
                    }
                }
                if (nextSlot != -1) {
                    invTo.set(nextSlot, handler.getStackInSlot(i));
                    handler.setStackInSlot(i, ItemStack.field_190927_a);
                } else {
                    return false;
                }
            }
        }
        for (int i2 = 0; i2 < handler.getSlots(); i2++) {
            if (!handler.getStackInSlot(i2).func_190926_b()) {
                return false;
            }
        }
        mergeStacks(invTo);
        return true;
    }
    private static void mergeStacks(NonNullList<ItemStack> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) != ItemStack.field_190927_a) {
                for (int j = 0; j < inventory.size(); j++) {
                    if (inventory.get(j) != ItemStack.field_190927_a && ((ItemStack) inventory.get(i)).func_77973_b() == ((ItemStack) inventory.get(j)).func_77973_b() && i != j) {
                        ((ItemStack) inventory.get(i)).func_190917_f(((ItemStack) inventory.get(j)).func_190916_E());
                        inventory.set(j, ItemStack.field_190927_a);
                    }
                }
            }
        }
    }
}
