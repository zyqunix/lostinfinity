package xol.lostinfinity.mob.ai;
import java.util.Random;
import java.util.function.BiFunction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
public class EntityAIFloatAttack extends EntityAIBase {
    private final EntityLiving parentEntity;
    private final BiFunction<EntityLivingBase, Entity, Entity> createFireballFunc;
    public int attackTimer;
    private int attackDelay;
    private final int maxDistance;
    private final SoundEvent beforeShoot;
    private final SoundEvent onShoot;
    public EntityAIFloatAttack(EntityLiving parentEntity, BiFunction<EntityLivingBase, Entity, Entity> createFireballFunc, int attackDelay, int maxDistance, SoundEvent beforeShoot, SoundEvent onShoot) {
        this.parentEntity = parentEntity;
        this.createFireballFunc = createFireballFunc;
        this.attackDelay = attackDelay;
        this.maxDistance = maxDistance;
        this.beforeShoot = beforeShoot;
        this.onShoot = onShoot;
    }
    public EntityAIFloatAttack(EntityLiving ghast, IFireballAttack func) {
        this(ghast, func::createFireball, 20, 64, SoundEvents.field_187559_bL, SoundEvents.field_187557_bK);
        func.getClass();
    }
    public EntityAIFloatAttack(EntityLiving ghast, IFireballAttack createFireball, SoundEvent onShoot) {
        this(ghast, createFireball::createFireball, 20, 64, null, onShoot);
        createFireball.getClass();
    }
    public EntityAIFloatAttack(EntityLiving ghast, IFireballAttack createFireball, SoundEvent onShoot, int delay) {
        this(ghast, createFireball::createFireball, delay, 64, null, onShoot);
        createFireball.getClass();
    }
    public EntityAIFloatAttack(EntityLiving ghast, IThrowableAttack createThrowable, SoundEvent onShoot) {
        this(ghast, createThrowable::createFireball, 20, 64, null, onShoot);
        createThrowable.getClass();
    }
    public boolean func_75250_a() {
        return this.parentEntity.func_70638_az() != null;
    }
    public void func_75249_e() {
        this.attackTimer = 0;
    }
    public void updateDelay(int newDelay) {
        this.attackDelay = newDelay;
    }
    public void func_75251_c() {
        this.parentEntity.func_70624_b((EntityLivingBase) null);
    }
    public void func_75246_d() {
        Entity entityFunc_70638_az = this.parentEntity.func_70638_az();
        if (Math.sqrt(entityFunc_70638_az.func_70068_e(this.parentEntity)) < this.maxDistance && this.parentEntity.func_70685_l(entityFunc_70638_az)) {
            World world = this.parentEntity.field_70170_p;
            this.attackTimer++;
            if (this.attackTimer == this.attackDelay - 10) {
                playSound(this.beforeShoot);
            }
            if (this.attackTimer == this.attackDelay) {
                playSound(this.onShoot);
                world.func_72838_d(this.createFireballFunc.apply(this.parentEntity, entityFunc_70638_az));
                this.attackTimer = this.attackDelay * (-2);
            }
        } else if (this.attackTimer > 0) {
            this.attackTimer--;
        }
        if (this.attackTimer <= 10) {
        }
    }
    private void playSound(SoundEvent event) {
        if (this.parentEntity == null || this.parentEntity.field_70170_p == null || event == null) {
            return;
        }
        Random rand = this.parentEntity.field_70170_p.field_73012_v;
        this.parentEntity.field_70170_p.func_184133_a((EntityPlayer) null, this.parentEntity.func_180425_c(), event, SoundCategory.HOSTILE, 10.0f, ((rand.nextFloat() - rand.nextFloat()) * 0.2f) + 1.0f);
    }
}
