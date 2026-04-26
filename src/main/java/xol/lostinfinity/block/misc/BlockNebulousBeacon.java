package xol.lostinfinity.block.misc;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.basic.BlockBasicGui;
import xol.lostinfinity.block.tileentity.TileEntityNebulousBeacon;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.gui.GuiHandler;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockNebulousBeacon.class */
public class BlockNebulousBeacon extends BlockBasicGui implements ITileEntityProvider {
    private TileEntityNebulousBeacon tileEntity;

    public BlockNebulousBeacon(String name) {
        super(name, Material.field_151576_e, TabsInit.TAB_BLOCKS);
    }

    public BlockNebulousBeacon(String name, Material material, CreativeTabs tab) {
        super(name, material, tab);
    }

    public TileEntity createTileEntity(World worldIn, IBlockState state) {
        this.tileEntity = new TileEntityNebulousBeacon();
        return this.tileEntity;
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
            playerIn.openGui(lostinfinity.instance, GuiHandler.RegisteredGuis.NEBULOUS_BEACON.getId(), worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            return true;
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void func_190948_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "When placed, this beacon will charge up an astral generator.");
        tooltip.add(TextFmt.Dark_Purple + "Nebulous Enemies will detect the beacon and attack it.");
        tooltip.add(TextFmt.Gray + "Protect the beacon until it is fully charged to receive the generator.");
    }

    public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
        if (hasTileEntity(state)) {
            TileEntity brokenTileEntity = worldIn.func_175625_s(pos);
            if (brokenTileEntity instanceof TileEntityNebulousBeacon) {
                worldIn.func_175713_t(pos);
            }
        }
    }

    public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (worldIn.field_72995_K) {
            placer.func_145747_a(new TextComponentString(TextFmt.Light_Purple + "The beacon has been placed. Prepare your defenses."));
        } else {
            worldIn.func_184148_a((EntityPlayer) null, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), SoundInit.IMPENDING_DOOM, SoundCategory.PLAYERS, 3.0f, 1.0f);
        }
    }
}
