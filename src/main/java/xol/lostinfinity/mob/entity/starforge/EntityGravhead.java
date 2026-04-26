package xol.lostinfinity.mob.entity.starforge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityGravhead extends EntityMob implements IMaxAttack, IBasicAI {
    public EntityGravhead(World worldIn) {
        super(worldIn);
        func_70105_a(1.0f, 1.8f);
    }
    protected void func_184651_r() {
        initBasicTasks(this);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 4);
            return true;
        }
        return false;
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70173_aa % 100 < 15) {
            if (this.field_70173_aa % 100 == 0) {
                func_184185_a(SoundInit.STARFORGE_GRAVHEAD_AMBIENT, 2.0f, 1.0f);
            }
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(12.0d, 12.0d, 12.0d))) {
                near_pl.func_70024_g(Math.signum(this.field_70165_t - near_pl.field_70165_t) * 0.3d, Math.signum(this.field_70163_u - near_pl.field_70163_u) * 0.5d, Math.signum(this.field_70161_v - near_pl.field_70161_v) * 0.3d);
                near_pl.field_70133_I = true;
            }
        }
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.22d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.STARFORGE_GRAVHEAD_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.STARFORGE_GRAVHEAD_HURT;
    }
    protected SoundEvent func_184639_G() {
        return null;
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
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_STARFORGE_GRAVHEAD;
    }
}
