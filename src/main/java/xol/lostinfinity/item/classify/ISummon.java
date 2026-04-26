package xol.lostinfinity.item.classify;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayer;
import xol.lostinfinity.common.special.CommonMinionHandler;
import xol.lostinfinity.mob.entity.minion.EntityMinion;
public interface ISummon {
    default Set<EntityMinion> getCurrentMinion(EntityPlayer player) {
        return CommonMinionHandler.getMinions(player.func_110124_au());
    }
    default void despawnPrevious(EntityPlayer player) {
        CommonMinionHandler.unregisterAll(player.func_110124_au());
    }
}
