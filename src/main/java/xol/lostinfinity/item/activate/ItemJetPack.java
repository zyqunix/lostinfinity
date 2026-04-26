package xol.lostinfinity.item.activate;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemBasic;
import xol.lostinfinity.mob.entity.mount.EntityJetMount;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/activate/ItemJetPack.class */
public class ItemJetPack extends ItemBasic {
    private static final String MOUNT_ID = "mount_id";

    public ItemJetPack(String regName) {
        super(regName, TabsInit.TAB_AUXMATS);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        Entity prevMount;
        ItemStack stack = playerIn.func_184586_b(handIn);
        Entity mount = playerIn.func_184208_bv();
        if (!worldIn.field_72995_K) {
            if (mount instanceof EntityJetMount) {
                mount.func_70106_y();
            } else {
                UUID prev = getSpawnedUUID(stack);
                if (prev != null && (prevMount = worldIn.func_73046_m().func_175576_a(prev)) != null) {
                    prevMount.func_70106_y();
                }
                EntityJetMount jetPack = new EntityJetMount(worldIn);
                jetPack.setOwner(playerIn);
                jetPack.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v);
                worldIn.func_72838_d(jetPack);
                if (playerIn.func_184205_a(jetPack, true)) {
                    setSpawnedUUID(stack, jetPack.func_110124_au());
                    if (worldIn.field_73011_w.func_186058_p() == DimensionInit.infiniteMurk) {
                        jetPack.startCourse(playerIn);
                    }
                } else {
                    jetPack.func_70106_y();
                    return super.func_77659_a(worldIn, playerIn, handIn);
                }
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
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
}
