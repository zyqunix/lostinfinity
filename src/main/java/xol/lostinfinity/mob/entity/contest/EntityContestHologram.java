package xol.lostinfinity.mob.entity.contest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
public class EntityContestHologram extends EntityImmaterial {
    public EntityContestHologram(World worldIn) {
        super(worldIn);
        func_184224_h(true);
    }
    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0f, 1.0f));
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 5 == 0 && this.field_70170_p.field_73011_w.func_186058_p() == DimensionInit.grandmasterOutpost) {
            BlockPos teleTo = ContestCoordinates.lobbyHologramPos();
            func_70634_a(teleTo.func_177958_n(), teleTo.func_177956_o(), teleTo.func_177952_p());
        }
    }
}
