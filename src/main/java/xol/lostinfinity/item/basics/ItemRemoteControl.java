package xol.lostinfinity.item.basics;

import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.misc.BlockRemoteControl;
import xol.lostinfinity.block.tileentity.TileEntityRemoteControl;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.classify.ICustomRaytrace;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/basics/ItemRemoteControl.class */
public abstract class ItemRemoteControl extends ItemCooldown implements ICustomRaytrace {
    @Nullable
    public abstract BlockRemoteControl getControlBlock();

    public abstract void tickEffect(TileEntityRemoteControl tileEntityRemoteControl, World world, BlockPos blockPos, EntityPlayer entityPlayer);

    public abstract void toggleEffect(TileEntityRemoteControl tileEntityRemoteControl, World world, BlockPos blockPos, EntityPlayer entityPlayer, boolean z);

    public ItemRemoteControl(String regName) {
        super(regName);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack held = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(held)) {
            if (!worldIn.field_72995_K) {
                if (!held.func_77942_o()) {
                    held.func_77982_d(new NBTTagCompound());
                }
                double blockX = held.func_77978_p().func_74769_h("BlockStoredX");
                double blockY = held.func_77978_p().func_74769_h("BlockStoredY");
                double blockZ = held.func_77978_p().func_74769_h("BlockStoredZ");
                BlockPos checkpos = new BlockPos(blockX, blockY, blockZ);
                if (worldIn.func_180495_p(checkpos).func_177230_c() == getControlBlock()) {
                    TileEntity te = worldIn.func_175625_s(checkpos);
                    if (te != null && (te instanceof TileEntityRemoteControl)) {
                        TileEntityRemoteControl remTe = (TileEntityRemoteControl) te;
                        if (remTe.getController() == null) {
                            remTe.setController(this);
                        }
                        if (remTe.getPlacerID() != null && remTe.getPlacerID().equals(playerIn.func_110124_au())) {
                            boolean nowActive = !remTe.getActive();
                            remTe.setActive(nowActive);
                            toggleEffect(remTe, worldIn, checkpos, playerIn, nowActive);
                            playerIn.func_145747_a(new TextComponentString((nowActive ? TextFmt.Green : TextFmt.Red) + "Remote block was " + (nowActive ? "activated." : "deactivated.")));
                            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.GENERIC_WEAPON_3, SoundCategory.MASTER, 1.5f, 0.9f + (worldIn.field_73012_v.nextFloat() * 0.2f));
                        }
                    }
                } else {
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Red + "No compatible remote block found at " + blockX + "," + blockY + "," + blockZ));
                }
            }
            held.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 500;
    }
}
