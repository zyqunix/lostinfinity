package xol.lostinfinity.item.weapon;

import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.mob.entity.mount.EntityXScreacher;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemXScreacher.class */
public class ItemXScreacher extends ItemCooldown {
    private static final String MOUNT_ID = "mount_id";

    public ItemXScreacher(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        Entity prevMount;
        ItemStack stack = playerIn.func_184586_b(handIn);
        Entity mount = playerIn.func_184208_bv();
        if (!showDurabilityBar(stack)) {
            if (mount instanceof EntityXScreacher) {
                if (!worldIn.field_72995_K) {
                    useAttack(playerIn, (EntityXScreacher) mount);
                }
            } else {
                if (!worldIn.field_72995_K) {
                    UUID prev = getSpawnedUUID(stack);
                    if (prev != null && (prevMount = worldIn.func_73046_m().func_175576_a(prev)) != null) {
                        prevMount.func_70106_y();
                    }
                    EntityXScreacher screacher = new EntityXScreacher(worldIn);
                    screacher.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v);
                    screacher.setOwner(playerIn);
                    worldIn.func_72838_d(screacher);
                    playerIn.func_184205_a(screacher, true);
                    setSpawnedUUID(stack, screacher.func_110124_au());
                }
                startCooldown(stack);
            }
        } else if (!worldIn.field_72995_K && (mount instanceof EntityXScreacher)) {
            useAttack(playerIn, (EntityXScreacher) mount);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    private void useAttack(EntityPlayer player, EntityXScreacher screacher) {
        screacher.fireAttack(player);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 120000;
    }

    private void setSpawnedUUID(ItemStack stack, UUID uuid) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.func_77978_p().func_186854_a(MOUNT_ID, uuid);
    }

    private UUID getSpawnedUUID(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            return null;
        }
        return stack.func_77978_p().func_186857_a(MOUNT_ID);
    }

    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Summons a powerful X-Screacher creature for you to ride.");
        tooltip.add(TextFmt.Aqua + "The X-Screacher will grant you increased regeneration and can fire terrain piercing attacks.");
    }
}
