package xol.lostinfinity.block.activator;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
public class BlockLabTeleporter extends BlockBasic {
    public BlockLabTeleporter(String name) {
        super(name, Material.field_151576_e);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        double yPlace;
        if (!playerIn.func_70093_af() && !worldIn.field_72995_K) {
            int roomRand_X = worldIn.field_73012_v.nextInt(8);
            int roomRand_Z = worldIn.field_73012_v.nextInt(8);
            if (roomRand_X == roomRand_Z) {
                yPlace = 35.0d;
            } else {
                yPlace = 26.0d;
            }
            playerIn.func_70634_a(1295.0d + ((double) (160 * roomRand_X)), yPlace, 1295.0d + ((double) (160 * roomRand_Z)));
            return true;
        }
        return true;
    }
}
