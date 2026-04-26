package xol.lostinfinity.block.generator;

import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/generator/BlockShockwaveGenerator.class */
public class BlockShockwaveGenerator extends BlockGenerator implements IMaxAttack {
    public BlockShockwaveGenerator(String name) {
        super(name, 3.0f, Material.field_151576_e, TabsInit.TAB_BLOCKS);
    }

    @Override // xol.lostinfinity.block.generator.BlockGenerator
    public void activateGenerator(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, int power_upgrade, int efficiency_upgrade, int range_upgrade, int depth_upgrade, UUID placer_uuid) {
        double zboost;
        for (EntityLivingBase detected_player : worldIn.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(pos).func_72314_b(20.0d + ((double) (range_upgrade * 8)), 10.0d + ((double) (depth_upgrade * 4)), 20.0d + ((double) (range_upgrade * 8))))) {
            if (!detected_player.func_110124_au().equals(placer_uuid) && is_detectable(detected_player)) {
                IMaxAttack.dealMaxHealth((Entity) playerIn, detected_player, 12 - (power_upgrade * 2), 3.0f);
                if (detected_player.field_70159_w <= 7.0d && detected_player.field_70159_w >= -7.0d && detected_player.field_70179_y <= 7.0d && detected_player.field_70179_y >= -7.0d) {
                    double xboost = Math.signum(playerIn.field_70165_t - detected_player.field_70165_t) * (-5.0d);
                    double dSignum = Math.signum(playerIn.field_70161_v - detected_player.field_70161_v);
                    double d = -5.0d;
                    while (true) {
                        zboost = dSignum * d;
                        if (xboost <= 7.0d && zboost <= 7.0d && xboost >= -7.0d && zboost >= -7.0d) {
                            break;
                        }
                        xboost *= 0.98d;
                        dSignum = zboost;
                        d = 0.98d;
                    }
                    detected_player.func_70024_g(xboost, 1.0d, zboost);
                    detected_player.field_70133_I = true;
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_190948_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Italic + "Unleashes an extremely powerful shockwave when given a Celestial Redstone.");
        tooltip.add(TextFmt.Italic + "Per Module Upgrades:");
        tooltip.add(TextFmt.Red + "NW: Power Modules | 22% Damage");
        tooltip.add(TextFmt.Aqua + "NE: Efficiency Modules | 16% Reduced Consumption");
        tooltip.add(TextFmt.Light_Purple + "SE: Depth Modules | 4 Blocks Up/Down");
        tooltip.add(TextFmt.Yellow + "SW: Range Modules | 8 Block Radius");
    }
}
