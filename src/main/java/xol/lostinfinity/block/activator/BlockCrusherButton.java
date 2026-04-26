package xol.lostinfinity.block.activator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;
import xol.lostinfinity.util.damagesource.DeathMessage;
public class BlockCrusherButton extends BlockBasic {
    public BlockCrusherButton(String name) {
        super(name);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            AxisAlignedBB crushBox = GalaxyCoordinates.crusherAABB();
            boolean crush = false;
            for (int i = (int) crushBox.field_72340_a; i <= ((int) crushBox.field_72336_d); i++) {
                for (int j = (int) crushBox.field_72338_b; j <= ((int) crushBox.field_72337_e); j++) {
                    for (int k = (int) crushBox.field_72339_c; k <= ((int) crushBox.field_72334_f); k++) {
                        BlockPos check = new BlockPos(i, j, k);
                        if (worldIn.func_175623_d(check) || crush) {
                            worldIn.func_175656_a(check, BlockInit.neosteelBeam.func_176223_P());
                            crush = true;
                        } else {
                            worldIn.func_175698_g(check);
                        }
                    }
                }
            }
            if (crush) {
                worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187689_f, SoundCategory.MASTER, 1.0f, 0.9f);
                for (EntityLivingBase mob : worldIn.func_72872_a(EntityLivingBase.class, crushBox)) {
                    EntityItem bones = new EntityItem(worldIn, mob.field_70165_t, mob.field_70163_u + 0.4d, mob.field_70161_v, new ItemStack(ItemInit.crushedBones));
                    bones.field_70159_w = 0.0d;
                    bones.field_70181_x = 0.0d;
                    bones.field_70179_y = 0.0d;
                    worldIn.func_72838_d(bones);
                    mob.func_70606_j(0.0f);
                    if (mob instanceof EntityPlayer) {
                        DeathMessage.broadcastDeathMessage(mob.func_184102_h(), TextFmt.Red + mob.func_70005_c_() + " was brutally crushed.");
                    }
                }
                return true;
            }
            return true;
        }
        return true;
    }
}
