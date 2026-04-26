package xol.lostinfinity.block.crafting;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicGui;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.gui.GuiHandler;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.TabsInit;
public class BlockFabricationTable extends BlockBasicGui {
    public BlockFabricationTable(String name) {
        this(name, TabsInit.TAB_BLOCKS);
    }
    public BlockFabricationTable(String name, CreativeTabs tab) {
        super(name, GuiHandler.RegisteredGuis.FABRICATION_STATION.getId());
        func_149647_a(tab);
        func_149672_a(SoundType.field_185852_e);
        func_149715_a(1.0f);
    }
    @Override // xol.lostinfinity.block.basic.BlockBasicGui
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            int poweredCount = 0;
            Block poweredBlock = BlockInit.fabricationBattery;
            BlockPos[] positions = {pos.func_177982_a(0, -1, 1), pos.func_177982_a(-1, -1, 0), pos.func_177982_a(1, -1, 0), pos.func_177982_a(0, -1, -1)};
            for (BlockPos position : positions) {
                if (worldIn.func_180495_p(position).func_177230_c().equals(poweredBlock)) {
                    poweredCount++;
                }
            }
            if (poweredCount == 4) {
                playerIn.openGui(lostinfinity.instance, GuiHandler.RegisteredGuis.FABRICATION_STATION.getId(), worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
                return true;
            }
            if (!worldIn.field_72995_K) {
                playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "The fabrication table is unpowered."));
                return true;
            }
            return true;
        }
        return true;
    }
}
