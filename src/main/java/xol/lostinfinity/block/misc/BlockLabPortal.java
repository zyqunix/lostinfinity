package xol.lostinfinity.block.misc;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicGlass;
import xol.lostinfinity.dimension.util.DimensionActivator;
import xol.lostinfinity.init.DimensionInit;
public class BlockLabPortal extends BlockBasicGlass {
    private int portalLocation;
    public BlockLabPortal(String name, int portal) {
        super(name);
        this.portalLocation = 0;
        this.portalLocation = portal;
        func_149715_a(1.0f);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        double yPlace;
        if (!worldIn.field_72995_K) {
            int roomRand_X = worldIn.field_73012_v.nextInt(1000) - (1000 / 2);
            int roomRand_Z = worldIn.field_73012_v.nextInt(1000) - (1000 / 2);
            if (roomRand_X == roomRand_Z) {
                yPlace = 16.0d;
            } else {
                yPlace = 25.0d;
            }
            if (this.portalLocation == 0) {
                DimensionActivator.transferEntityWithCoords(playerIn, DimensionInit.cartographerRealmMid, 15 + (160 * roomRand_X), yPlace, 15 + (160 * roomRand_Z));
                return true;
            }
            DimensionActivator.transferEntityWithCoords(playerIn, DimensionInit.cartographerRealmBot, 15 + (160 * roomRand_X), yPlace, 15 + (160 * roomRand_Z));
            return true;
        }
        return true;
    }
}
