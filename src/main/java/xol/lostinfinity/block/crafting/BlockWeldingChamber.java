package xol.lostinfinity.block.crafting;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.basic.BlockBasicGui;
import xol.lostinfinity.block.tileentity.TileEntityWeldingChamber;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.gui.GuiHandler;
import xol.lostinfinity.init.TabsInit;
public class BlockWeldingChamber extends BlockBasicGui implements ITileEntityProvider {
    public static final PropertyBool BURNING = PropertyBool.func_177716_a("burning");
    public BlockWeldingChamber(String name) {
        this(name, Material.field_151576_e, TabsInit.TAB_BLOCKS);
    }
    public BlockWeldingChamber(String name, Material material, CreativeTabs tab) {
        super(name, material, tab);
        func_149647_a(tab).func_149711_c(3.0f).func_149752_b(10.0f);
        func_149672_a(SoundType.field_185851_d);
        func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(BURNING, false));
    }
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityWeldingChamber();
    }
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
    public TileEntity func_149915_a(World worldIn, int meta) {
        return null;
    }
    @Override // xol.lostinfinity.block.basic.BlockBasicGui
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            playerIn.openGui(lostinfinity.instance, GuiHandler.RegisteredGuis.WELDING_CHAMBER.getId(), worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            return true;
        }
        return true;
    }
    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer(this, new IProperty[]{BURNING});
    }
    public static void setState(boolean active, World worldIn, BlockPos pos) {
        IBlockState state = worldIn.func_180495_p(pos);
        TileEntity tileEntity = worldIn.func_175625_s(pos);
        worldIn.func_180501_a(pos, state.func_177226_a(BURNING, Boolean.valueOf(active)), 3);
        if (tileEntity != null) {
            tileEntity.func_145829_t();
            worldIn.func_175690_a(pos, tileEntity);
        }
    }
    public IBlockState func_176203_a(int meta) {
        switch (meta) {
            case 0:
                return func_176223_P().func_177226_a(BURNING, false);
            case 1:
                return func_176223_P().func_177226_a(BURNING, true);
            default:
                return func_176223_P();
        }
    }
    public int func_176201_c(IBlockState state) {
        if (!state.equals(func_176223_P().func_177226_a(BURNING, false)) && state.equals(func_176223_P().func_177226_a(BURNING, true))) {
            return 1;
        }
        return 0;
    }
    @SideOnly(Side.CLIENT)
    public void func_190948_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gray + "This incredibly advanced machine is");
        tooltip.add(TextFmt.Gray + "used to fuse Atomite Fragments.");
        tooltip.add("");
        tooltip.add(TextFmt.Gray + "Requires " + TextFmt.Aqua + "5 Atomite Fragments" + TextFmt.Gray + ".");
    }
    public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
        if (hasTileEntity(state)) {
            TileEntity tileentity = worldIn.func_175625_s(pos);
            if (tileentity instanceof TileEntityWeldingChamber) {
                InventoryHelper.func_180175_a(worldIn, pos, (TileEntityWeldingChamber) tileentity);
                worldIn.func_175666_e(pos, this);
                worldIn.func_175713_t(pos);
            }
        }
    }
}
