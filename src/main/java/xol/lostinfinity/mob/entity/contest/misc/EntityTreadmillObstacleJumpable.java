package xol.lostinfinity.mob.entity.contest.misc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/misc/EntityTreadmillObstacleJumpable.class */
public class EntityTreadmillObstacleJumpable extends EntityTreadmillObstacle {
    public EntityTreadmillObstacleJumpable(World worldIn) {
        super(worldIn);
        func_70105_a(0.1f, 1.0f);
    }

    @Override // xol.lostinfinity.mob.entity.contest.misc.EntityTreadmillObstacle
    public void func_70100_b_(EntityPlayer entityIn) {
        if (Math.abs(this.field_70163_u - entityIn.field_70163_u) < 0.001d && this.field_70165_t > entityIn.field_70165_t) {
            super.func_70100_b_(entityIn);
        }
    }
}
