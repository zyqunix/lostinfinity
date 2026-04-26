package xol.lostinfinity.mob.entity.cthulhu;

import net.minecraft.world.World;
import xol.lostinfinity.common.events.EventsClientRender;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/cthulhu/EntityCthulhuDeathFX.class */
public class EntityCthulhuDeathFX extends EntityImmaterial {
    public EntityCthulhuDeathFX(World worldIn) {
        super(worldIn);
    }

    public void onAddedToWorld() {
        super.onAddedToWorld();
        if (this.field_70170_p.field_72995_K) {
            EventsClientRender.renderForce.put(Integer.valueOf(func_145782_y()), this);
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70173_aa > 340) {
            func_70106_y();
        }
    }
}
