package xol.lostinfinity.gui.containers;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.item.misc.ItemPortableBeacon;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/containers/ContainerPortableBeacon.class */
public class ContainerPortableBeacon extends Container {
    public final ItemStack beacon;
    private final World world;
    private final IInventory inputSlots = new InventoryBasic("Portable Beacon", true, 2) { // from class: xol.lostinfinity.gui.containers.ContainerPortableBeacon.1
        public void func_70296_d() {
            super.func_70296_d();
            ContainerPortableBeacon.this.func_75130_a(this);
        }
    };

    @Nullable
    private ItemPortableBeacon getBeacon() {
        if (this.beacon == null || !this.beacon.func_77973_b().equals(ItemInit.portableBeacon)) {
            return null;
        }
        return (ItemPortableBeacon) this.beacon.func_77973_b();
    }

    public boolean isValidInput(ItemStack input) {
        boolean result = false;
        if (getBeacon() != null && input.func_77973_b().equals(Items.field_151068_bn)) {
            PotionType pot = PotionUtils.func_185191_c(input);
            List<PotionType> disallowedTypes = Arrays.asList(PotionTypes.field_185229_a, PotionTypes.field_185230_b, PotionTypes.field_185233_e, PotionTypes.field_185252_x, PotionTypes.field_185250_v, PotionTypes.field_185251_w, PotionTypes.field_185231_c, PotionTypes.field_185232_d);
            ItemPortableBeacon bc = getBeacon();
            Collection<PotionEffect> effects = bc.getPotionEffects(this.beacon);
            int limit = bc.getLimit(this.beacon);
            if (limit > effects.size() && !disallowedTypes.contains(pot)) {
                Iterator it = pot.func_185170_a().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    PotionEffect ef = (PotionEffect) it.next();
                    if (!effects.contains(ef)) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public ContainerPortableBeacon(InventoryPlayer player, World worldIn, ItemStack beacon) {
        this.beacon = beacon;
        this.world = worldIn;
        func_75146_a(new Slot(this.inputSlots, 0, 65, 37) { // from class: xol.lostinfinity.gui.containers.ContainerPortableBeacon.2
            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public boolean func_75214_a(ItemStack stack) {
                return ContainerPortableBeacon.this.isValidInput(stack);
            }

            public boolean func_82869_a(EntityPlayer playerIn) {
                return func_75216_d();
            }
        });
        func_75146_a(new Slot(this.inputSlots, 1, 95, 37) { // from class: xol.lostinfinity.gui.containers.ContainerPortableBeacon.3
            public boolean func_75214_a(ItemStack stack) {
                return stack.func_77973_b().equals(Items.field_151117_aB) || stack.func_77973_b().equals(ItemInit.ultrapoweredCapacitor);
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public boolean func_82869_a(EntityPlayer playerIn) {
                return func_75216_d() && (func_75211_c().func_77973_b().equals(Items.field_151133_ar) || func_75211_c().func_77973_b().equals(ItemInit.ultrapoweredCapacitor));
            }
        });
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                func_75146_a(new Slot(player, j + (i * 9) + 9, 8 + (j * 18), 84 + (i * 18)));
            }
        }
        for (int k = 0; k < 9; k++) {
            func_75146_a(new Slot(player, k, 8 + (k * 18), 142));
        }
    }

    public void func_75130_a(IInventory inventoryIn) {
        super.func_75130_a(inventoryIn);
        if (inventoryIn == this.inputSlots) {
            updateBeaconGui();
        }
    }

    public void updateBeaconGui() {
        ItemPortableBeacon bc;
        ItemStack iteminput1 = this.inputSlots.func_70301_a(0).func_77946_l();
        ItemStack iteminput2 = this.inputSlots.func_70301_a(1).func_77946_l();
        if (!iteminput1.func_190926_b()) {
            ItemPortableBeacon bc2 = getBeacon();
            int limit = bc2.getLimit(this.beacon);
            PotionType pot = PotionUtils.func_185191_c(iteminput1);
            pot.func_185170_a().stream().filter(e -> {
                return !bc2.getPotionEffects(iteminput1).contains(e);
            }).forEach(e2 -> {
                if (limit > bc2.getPotionEffects(iteminput1).size()) {
                    bc2.updatePotionEffects(iteminput1, this.beacon, e2, false);
                    this.inputSlots.func_70299_a(0, ItemStack.field_190927_a);
                }
            });
        }
        if (iteminput2.func_77973_b().equals(Items.field_151117_aB)) {
            ItemPortableBeacon bc3 = getBeacon();
            if (bc3 != null) {
                bc3.clearEffects(this.beacon);
                this.inputSlots.func_70299_a(1, new ItemStack(Items.field_151133_ar, 1));
                return;
            }
            return;
        }
        if (iteminput2.func_77973_b().equals(ItemInit.ultrapoweredCapacitor) && (bc = getBeacon()) != null && bc.getLimit(this.beacon) == 5) {
            bc.setNewLimit(this.beacon, 10);
            this.inputSlots.func_70299_a(1, ItemStack.field_190927_a);
        }
    }

    public void func_75134_a(EntityPlayer playerIn) {
        super.func_75134_a(playerIn);
        if (!this.world.field_72995_K) {
            func_193327_a(playerIn, this.world, this.inputSlots);
        }
    }

    public boolean func_75145_c(EntityPlayer playerIn) {
        return true;
    }

    public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.field_190927_a;
        Slot slot = (Slot) this.field_75151_b.get(index);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (index == 1) {
                if (!func_75135_a(itemstack1, 2, 38, true)) {
                    return ItemStack.field_190927_a;
                }
                slot.func_75220_a(itemstack1, itemstack);
            } else if (index != 0) {
                if (index >= 2 && index < 37 && !func_75135_a(itemstack1, 0, 1, false)) {
                    return ItemStack.field_190927_a;
                }
            } else if (!func_75135_a(itemstack1, 2, 38, false)) {
                return ItemStack.field_190927_a;
            }
            if (itemstack1.func_190926_b()) {
                slot.func_75215_d(ItemStack.field_190927_a);
            } else {
                slot.func_75218_e();
            }
            if (itemstack1.func_190916_E() == itemstack.func_190916_E()) {
                return ItemStack.field_190927_a;
            }
            slot.func_190901_a(playerIn, itemstack1);
        }
        return itemstack;
    }
}
