package xol.lostinfinity.mob.entity.starforge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.projectile.entity.EntityClusterBlast;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityClusterweed extends EntityMob implements IMaxAttack {
    public EntityClusterweed(World worldIn) {
        super(worldIn);
        func_70105_a(1.0f, 4.0f);
    }
    public boolean func_180427_aV() {
        return true;
    }
    protected void func_184651_r() {
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth((Entity) this, func_70638_az(), 3, 2.0f);
            return true;
        }
        return false;
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(700.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70159_w = 0.0d;
        this.field_70179_y = 0.0d;
        if (this.field_70173_aa % 60 == 0 && !this.field_70170_p.field_72995_K) {
            func_70676_i(1.0f);
            double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
            double makeY = this.field_70163_u + ((double) this.field_70131_O);
            double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
            EntityClusterBlast shot = new EntityClusterBlast(this.field_70170_p, makeX, makeY, makeZ);
            shot.setThrower(this);
            shot.func_70186_c((-0.5f) + this.field_70146_Z.nextFloat(), 0.20000000298023224d, (-0.5f) + this.field_70146_Z.nextFloat(), 1.0f, 0.0f);
            this.field_70170_p.func_72838_d(shot);
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.CRYSTAL_MAGIC).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config1, makeX, makeY, makeZ);
            func_184185_a(SoundInit.CLUSTER_FIRE, 2.0f, 0.8f + (this.field_70146_Z.nextFloat() * 0.4f));
        }
    }
    protected SoundEvent func_184615_bR() {
        return null;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return null;
    }
    protected SoundEvent func_184639_G() {
        return null;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_CLUSTERWEED;
    }
    protected boolean func_70692_ba() {
        return false;
    }
    public boolean func_70814_o() {
        return true;
    }
    public int func_70641_bl() {
        return 1;
    }
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }
}
