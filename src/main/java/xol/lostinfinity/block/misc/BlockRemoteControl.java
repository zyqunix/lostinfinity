package xol.lostinfinity.block.misc;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.tileentity.TileEntityRemoteControl;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.item.basics.ItemRemoteControl;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockRemoteControl.class */
public class BlockRemoteControl extends BlockBasic {
    public BlockRemoteControl(String name) {
        super(name);
    }

    public TileEntity createTileEntity(World world, IBlockState state) {
        TileEntityRemoteControl TE = new TileEntityRemoteControl();
        return TE;
    }

    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            ItemStack held = playerIn.func_184586_b(hand);
            if (held.func_77973_b() instanceof ItemRemoteControl) {
                ItemRemoteControl control = (ItemRemoteControl) held.func_77973_b();
                if (control.getControlBlock() == worldIn.func_180495_p(pos).func_177230_c()) {
                    if (!held.func_77942_o()) {
                        held.func_77982_d(new NBTTagCompound());
                    }
                    TileEntity te = worldIn.func_175625_s(pos);
                    if (te == null || !(te instanceof TileEntityRemoteControl)) {
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "Improper Tile Entity found here. Replace block."));
                        return true;
                    }
                    TileEntityRemoteControl remTe = (TileEntityRemoteControl) te;
                    if (remTe.getPlacerID() == null) {
                        remTe.setPlacer(playerIn);
                    }
                    if (remTe.getPlacerID().equals(playerIn.func_110124_au())) {
                        held.func_77978_p().func_74780_a("BlockStoredX", pos.func_177958_n());
                        held.func_77978_p().func_74780_a("BlockStoredY", pos.func_177956_o());
                        held.func_77978_p().func_74780_a("BlockStoredZ", pos.func_177952_p());
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Gold + "Remote bound to " + pos.func_177958_n() + "," + pos.func_177956_o() + "," + pos.func_177952_p()));
                        return true;
                    }
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }
}
