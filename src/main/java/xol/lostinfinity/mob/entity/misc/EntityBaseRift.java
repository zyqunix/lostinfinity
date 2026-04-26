package xol.lostinfinity.mob.entity.misc;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityBaseRift extends EntityImmaterial implements IMaxAttack {
    private float alpha;
    public EntityBaseRift(World worldIn) {
        super(worldIn);
        this.alpha = 0.0f;
        func_70105_a(0.2f, 0.2f);
        func_184224_h(true);
        func_82142_c(true);
        func_189654_d(true);
    }
    public float getAlpha() {
        return this.alpha;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70159_w = 0.0d;
        this.field_70181_x = 0.0d;
        this.field_70179_y = 0.0d;
        if (this.field_70170_p.field_72995_K && this.alpha < 0.85f) {
            this.alpha += 0.025f;
        }
    }
    protected double getROD(int multi) {
        return ((-0.5d) + this.field_70146_Z.nextDouble()) * ((double) multi);
    }
}
