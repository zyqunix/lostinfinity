package xol.lostinfinity.block.activator;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.mob.entity.misc.EntityLightGame;
public class BlockLightSwitchGame extends BlockBasic {
    public BlockLightSwitchGame(String name) {
        super(name);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            AxisAlignedBB checkBox = new AxisAlignedBB(pos.func_177982_a(-10, -10, -10), pos.func_177982_a(10, 10, 10));
            for (EntityLightGame merch : worldIn.func_72872_a(EntityLightGame.class, checkBox)) {
                ((WorldServer) worldIn).func_175739_a(EnumParticleTypes.PORTAL, merch.field_70165_t, merch.field_70163_u, merch.field_70161_v, 5, ((-0.5d) + worldIn.field_73012_v.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + worldIn.field_73012_v.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
                merch.func_70106_y();
            }
            BlockPos refPos = pos.func_177982_a(0, 1, 0);
            List<BlockPos> switches = new ArrayList<>();
            List<BlockPos> lights = new ArrayList<>();
            for (int i = -8; i <= 8; i++) {
                for (int j = -8; j <= 8; j++) {
                    Block block = worldIn.func_180495_p(refPos.func_177982_a(i, 0, j)).func_177230_c();
                    if (block == BlockInit.switchableLightOff || block == BlockInit.switchableLightOn) {
                        lights.add(refPos.func_177982_a(i, 0, j));
                    } else if (block == BlockInit.lightSwitchOff || block == BlockInit.lightSwitchOn) {
                        switches.add(refPos.func_177982_a(i, 0, j));
                    }
                }
            }
            List<EntityLightGame> gameEntities = worldIn.func_72872_a(EntityLightGame.class, new AxisAlignedBB(-8.0d, 0.0d, -8.0d, 8.0d, 8.0d, 8.0d));
            for (EntityLightGame entity : gameEntities) {
                entity.func_70106_y();
            }
            EntityLightGame lightGame = new EntityLightGame(worldIn, lights, switches);
            BlockPos entityPos = pos.func_177982_a(-1, 1, -1);
            lightGame.func_70107_b(entityPos.func_177958_n(), entityPos.func_177956_o(), entityPos.func_177952_p());
            worldIn.func_72838_d(lightGame);
            return true;
        }
        return true;
    }
}
