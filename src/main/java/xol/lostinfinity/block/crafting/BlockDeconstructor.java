package xol.lostinfinity.block.crafting;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicGui;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.gui.GuiHandler;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/crafting/BlockDeconstructor.class */
public class BlockDeconstructor extends BlockBasicGui {
    public BlockDeconstructor(String name, Material material, CreativeTabs tab) {
        super(name, material, tab);
    }

    @Override // xol.lostinfinity.block.basic.BlockBasicGui
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            playerIn.openGui(lostinfinity.instance, GuiHandler.RegisteredGuis.DECONSTRUCTOR.getId(), worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
        }
        return super.func_180639_a(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
