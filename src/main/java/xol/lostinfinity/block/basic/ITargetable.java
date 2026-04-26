package xol.lostinfinity.block.basic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
public interface ITargetable {
    void targetedResult(World world, EntityPlayer entityPlayer, BlockPos blockPos);
}
