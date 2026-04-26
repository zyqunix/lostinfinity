package xol.lostinfinity.mob.entity.sea;
import java.util.Arrays;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAILookAround;
import xol.lostinfinity.mob.ai.EntityAIRandomFly;
import xol.lostinfinity.projectile.entity.EntityCrabulonProjectile;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityCrabulon extends EntitySeaCreature implements IRangedAttackMob {
    public EntityCrabulon(World worldIn) {
        super(worldIn);
        func_70105_a(7.0f, 7.0f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(5, new EntityAIRandomFly(this));
        this.field_70714_bg.func_75776_a(7, new EntityAILookAround(this));
        this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0f, 1.0f));
        this.field_70714_bg.func_75776_a(10, new EntityAIAttackMelee(this, 1.0d, true));
        this.field_70714_bg.func_75776_a(11, new EntityAIAttackRanged(this, 1.0d, 20, 60, 15.0f));
        this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealTrueDamage(this, func_70638_az(), func_70638_az().func_110138_aP() * 0.75f, Arrays.asList("Aquatic"));
            return true;
        }
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && func_70638_az() != null) {
            EntityLivingBase target = func_70638_az();
            func_70605_aq().func_75642_a(target.field_70165_t, target.field_70163_u, target.field_70161_v, 1.0d);
            if (this.field_70173_aa % 60 == 0 && func_70032_d(target) > 4.0f) {
                func_82196_d(target, 1.0f);
            }
        }
    }
    public void func_82196_d(EntityLivingBase target, float distanceFactor) {
        EntityCrabulonProjectile projectile = new EntityCrabulonProjectile(this.field_70170_p, func_180425_c().func_177958_n(), ((double) func_180425_c().func_177956_o()) + 1.5d, func_180425_c().func_177952_p());
        projectile.setThrower(this);
        projectile.func_70186_c(func_70040_Z().field_72450_a, func_70040_Z().field_72448_b, func_70040_Z().field_72449_c, 1.5f, 0.0f);
        this.field_70170_p.func_72838_d(projectile);
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_CRABULON;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 25;
    }
    public void func_184724_a(boolean swingingArms) {
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.CRABULON_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.CRABULON_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.CRABULON_AMBIENT;
    }
}
