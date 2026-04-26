package xol.lostinfinity.mob.entity.cthulhu;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xol.lostinfinity.util.animation.client.blueprint.LoopMode;
public class EntityCthulhuTentaclePersist extends EntityCthulhuTentacle {
    public EntityCthulhuTentaclePersist(World worldIn) {
        super(worldIn);
        setInverted(false);
        func_189654_d(false);
        setSize(9.25f);
    }
    @Override // xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuTentacle
    protected void livingUpdate() {
        if (!this.field_70170_p.field_72995_K && this.owner != null) {
            if (this.field_70173_aa % 600 == 580) {
                playAnimation("death", LoopMode.NONE, true, 0.5f);
            } else if (this.field_70173_aa % 600 == 0) {
                playAnimation("spawn", LoopMode.NONE, false, 0.5f);
                teleportRandom();
            }
        }
    }
    @Override // xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuTentacle, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void doDamageTint() {
        damageTint();
    }
    public void cast() {
        playAnimation("cast", 1.0f);
    }
    public void teleportRandom() {
        this.field_70177_z = this.field_70170_p.field_73012_v.nextFloat() * 360.0f;
        this.field_70759_as = this.field_70177_z;
        double x = ((double) this.field_70170_p.field_73012_v.nextFloat()) - 0.5d;
        double z = ((double) this.field_70170_p.field_73012_v.nextFloat()) - 0.5d;
        double s = MathHelper.func_181161_i((x * x) + (z * z)) * 64.0d;
        double x2 = x * s;
        double z2 = z * s;
        double x3 = x2 + this.owner.field_70165_t;
        double z3 = z2 + this.owner.field_70161_v;
        double y = this.field_70170_p.func_189649_b((int) x3, (int) z3);
        func_70107_b(x3, y, z3);
    }
}
