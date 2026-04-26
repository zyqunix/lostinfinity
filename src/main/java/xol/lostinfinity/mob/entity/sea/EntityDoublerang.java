package xol.lostinfinity.mob.entity.sea;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
public class EntityDoublerang extends EntityFish {
    public EntityDoublerang(World worldIn) {
        super(worldIn);
        func_70105_a(1.0f, 2.5f);
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.DOUBLERANG_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.DOUBLERANG_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.DOUBLERANG_AMBIENT;
    }
}
