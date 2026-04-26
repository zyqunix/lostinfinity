package xol.lostinfinity.mob.entity.base;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/base/EntityImmaterial.class */
public class EntityImmaterial extends EntityLiving {
    public EntityImmaterial(World worldIn) {
        super(worldIn);
        func_184224_h(true);
        func_189654_d(true);
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
            func_70674_bp();
            func_70606_j(Float.MAX_VALUE);
        }
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10000.0d);
    }

    public boolean func_70067_L() {
        return false;
    }

    public boolean func_70104_M() {
        return false;
    }

    public boolean func_70601_bi() {
        return true;
    }

    protected boolean func_70692_ba() {
        return false;
    }
}
