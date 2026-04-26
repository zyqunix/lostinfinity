package xol.lostinfinity.mob.entity.starforge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.projectile.entity.EntitySunstoneRock;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityGiantRockslug extends EntityMultipleLives implements IMaxAttack, IBasicAI {
    private float chargerDist;
    public EntityGiantRockslug(World worldIn) {
        super(worldIn);
        this.chargerDist = 0.0f;
        func_70105_a(2.0f, 4.0f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        initBasicTasks(this);
    }
    public float getChargerOffset() {
        return this.chargerDist;
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 3);
            return true;
        }
        return false;
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa % 60 == 0) {
                fireRock();
            }
        } else if (this.field_70173_aa % 60 > 20) {
            this.chargerDist += 0.005f;
        } else if (this.chargerDist > 0.0f) {
            this.chargerDist -= 0.1f;
            if (this.chargerDist < 0.0f) {
                this.chargerDist = 0.0f;
            }
        }
    }
    private void fireRock() {
        EntitySunstoneRock shot = new EntitySunstoneRock(this.field_70170_p);
        shot.setThrower(this);
        shot.func_70107_b(this.field_70165_t, this.field_70163_u + ((double) (this.field_70131_O / 2.0f)), this.field_70161_v);
        if (func_70638_az() != null) {
            Vec3d vec = func_70638_az().func_174791_d().func_178788_d(func_174791_d()).func_72432_b();
            shot.func_70186_c(vec.field_72450_a, vec.field_72448_b, vec.field_72449_c, 1.0f, 0.0f);
            this.field_70170_p.func_72838_d(shot);
            func_184185_a(SoundInit.ROCKSLUG_HURL, 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
        }
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.ROCKSLUG_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.ROCKSLUG_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.ROCKSLUG_AMBIENT;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 40;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_ROCKSLUG;
    }
}
