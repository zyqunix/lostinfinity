package xol.lostinfinity.mob.entity.sea;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
public class EntityLongfin extends EntityFish {
    public EntityLongfin(World worldIn) {
        super(worldIn);
        func_70105_a(1.0f, 1.0f);
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.LONGFIN_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.LONGFIN_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.LONGFIN_AMBIENT;
    }
}
