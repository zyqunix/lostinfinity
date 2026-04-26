package xol.lostinfinity.block.activator;
import java.util.List;
import java.util.Random;
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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.basic.BlockBasicGui;
import xol.lostinfinity.block.tileentity.TileEntityRainfallGenerator;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.gui.GuiHandler;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.TabsInit;
public class BlockRainfallGenerator extends BlockBasicGui implements ITileEntityProvider {
    public static final PropertyBool BURNING = PropertyBool.func_177716_a("burning");
    private final boolean isBurning;
    public BlockRainfallGenerator(String name) {
        this(name, Material.field_151576_e, false);
    }
    public BlockRainfallGenerator(String name, Material material, boolean isBurning) {
        this(name, material, TabsInit.TAB_BLOCKS, isBurning);
    }
    public BlockRainfallGenerator(String name, Material material, CreativeTabs tab, boolean isBurning) {
        super(name, material, tab);
        func_149647_a(tab);
        func_149672_a(SoundType.field_185852_e);
        func_149715_a(1.0f);
        func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(BURNING, false));
        this.isBurning = isBurning;
    }
    public Item func_180660_a(IBlockState state, Random rand, int fortune) {
        return Item.func_150898_a(BlockInit.rainfallGenerator);
    }
    public ItemStack func_185473_a(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(BlockInit.rainfallGenerator);
    }
    public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (!worldIn.field_72995_K && this.field_149758_A) {
            TileEntity tileentity = worldIn.func_175625_s(pos);
            if (tileentity instanceof TileEntityRainfallGenerator) {
                if (placer instanceof EntityPlayer) {
                }
            }
        }
        super.func_180633_a(worldIn, pos, state, placer, stack);
    }
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityRainfallGenerator();
    }
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
    private TileEntityRainfallGenerator getTE(World world, BlockPos pos) {
        return (TileEntityRainfallGenerator) world.func_175625_s(pos);
    }
    @Override // xol.lostinfinity.block.basic.BlockBasicGui
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            playerIn.openGui(lostinfinity.instance, GuiHandler.RegisteredGuis.RAINFALL_GENERATOR.getId(), worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            return true;
        }
        return true;
    }
    public static void setState(boolean active, World worldIn, BlockPos pos) {
        worldIn.func_180495_p(pos);
        TileEntity tileentity = worldIn.func_175625_s(pos);
        if (active) {
            worldIn.func_180501_a(pos, BlockInit.rainfallGenerator.func_176223_P().func_177226_a(BURNING, true), 3);
        } else {
            worldIn.func_180501_a(pos, BlockInit.rainfallGenerator.func_176223_P().func_177226_a(BURNING, false), 3);
        }
        if (tileentity != null) {
            tileentity.func_145829_t();
            worldIn.func_175690_a(pos, tileentity);
        }
    }
    public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
        TileEntityRainfallGenerator tileentity = (TileEntityRainfallGenerator) worldIn.func_175625_s(pos);
        InventoryHelper.func_180175_a(worldIn, pos, tileentity);
        super.func_180663_b(worldIn, pos, state);
    }
    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer(this, new IProperty[]{BURNING});
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
    public TileEntity func_149915_a(World worldIn, int meta) {
        return null;
    }
    @SideOnly(Side.CLIENT)
    public void func_190948_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Italic + "Advanced weather control system. Fuel it to generate rainfall.");
    }
}
