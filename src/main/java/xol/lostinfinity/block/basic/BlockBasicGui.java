package xol.lostinfinity.block.basic;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.init.TabsInit;
public class BlockBasicGui extends BlockBasic {
    private int guiID;
    public BlockBasicGui(String name, int id) {
        this(name, Material.field_151573_f, TabsInit.TAB_STARFORGE);
        this.guiID = id;
    }
    public BlockBasicGui(String name, Material material, CreativeTabs tab) {
        super(name, material, tab);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (this.guiID > 0) {
            playerIn.openGui(lostinfinity.instance, this.guiID, worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            return true;
        }
        return true;
    }
}
