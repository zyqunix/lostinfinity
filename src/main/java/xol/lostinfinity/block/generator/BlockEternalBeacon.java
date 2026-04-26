package xol.lostinfinity.block.generator;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.tileentity.TileEntityEternalBeacon;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.mob.entity.minion.EntityMinion;
public class BlockEternalBeacon extends BlockGenerator {
    public BlockEternalBeacon(String name) {
        super(name, 3.0f, Material.field_151576_e, TabsInit.TAB_BLOCKS);
    }
    @Override // xol.lostinfinity.block.generator.BlockGenerator
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityEternalBeacon();
    }
    @Override // xol.lostinfinity.block.generator.BlockGenerator
    public void activateGenerator(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, int power_upgrade, int efficiency_upgrade, int range_upgrade, int depth_upgrade, UUID placer_uuid) {
        int radius = 20 + (range_upgrade * 20);
        AxisAlignedBB bb = new AxisAlignedBB(pos).func_72314_b(radius, 20 + (depth_upgrade * 25), radius).func_72317_d(0.0d, -5.0d, 0.0d);
        for (EntityMinion entityMinion : worldIn.func_72872_a(EntityLivingBase.class, bb)) {
            if (entityMinion.func_174818_b(pos) <= radius * radius) {
                if (entityMinion.func_110124_au().equals(placer_uuid)) {
                    entityMinion.func_70690_d(new PotionEffect(PotionInit.PROTECTED, 60, 0));
                } else if ((entityMinion instanceof IEntityOwnable) && !placer_uuid.equals(entityMinion.func_184753_b())) {
                    entityMinion.func_70106_y();
                } else if ((entityMinion instanceof EntityMinion) && !placer_uuid.equals(entityMinion.func_184753_b())) {
                    entityMinion.func_70106_y();
                }
            }
        }
        TileEntity tile = worldIn.func_175625_s(pos);
        if (tile instanceof TileEntityEternalBeacon) {
            TileEntityEternalBeacon beacon = (TileEntityEternalBeacon) tile;
            beacon.setTickRemaining(60);
            beacon.setRadius(radius);
            beacon.doBlockUpdate();
        }
    }
    @SideOnly(Side.CLIENT)
    public void func_190948_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Makes the owner immune inside the forcefield (20 blocks).");
        tooltip.add(TextFmt.Gold + "Kills enemy tames inside the forcefield.");
        tooltip.add(TextFmt.Italic + "Per Module Upgrades:");
        tooltip.add(TextFmt.Red + "NW: Power Modules | NO EFFECT");
        tooltip.add(TextFmt.Aqua + "NE: Efficiency Modules | 16% Reduced Consumption");
        tooltip.add(TextFmt.Light_Purple + "SE: Depth Modules | +2.5 Blocks Down");
        tooltip.add(TextFmt.Yellow + "SW: Range Modules | +20 Block Radius");
    }
}
