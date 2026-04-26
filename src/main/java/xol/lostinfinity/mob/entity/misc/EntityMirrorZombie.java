package xol.lostinfinity.mob.entity.misc;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityMirrorZombie extends EntityMob implements IMaxAttack, IBasicAI {
    private float lastHp;
    public EntityMirrorZombie(World worldIn) {
        super(worldIn);
        this.lastHp = -999.0f;
        func_70105_a(2.2f, 6.0f);
    }
    protected void func_184651_r() {
        initBasicTasks(this);
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 6);
            return true;
        }
        return false;
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(6.0d, 6.0d, 6.0d))) {
            near_pl.func_70024_g(Math.signum(this.field_70165_t - near_pl.field_70165_t) * (-2.5d), 0.5d, Math.signum(this.field_70161_v - near_pl.field_70161_v) * (-2.5d));
            near_pl.field_70133_I = true;
        }
        if (this.lastHp == -999.0f) {
            this.lastHp = func_110143_aJ();
        }
        if (this.lastHp != func_110143_aJ() && this.lastHp > func_110143_aJ()) {
            if (!this.field_70170_p.field_72995_K) {
                Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(20.0d, 20.0d, 20.0d)).iterator();
                while (it.hasNext()) {
                    IMaxAttack.dealMaxHealth(this, (EntityPlayer) it.next(), 3);
                }
                func_184185_a(SoundInit.MIRRORZOMBIE_REFLECT, 3.0f, 1.0f);
            } else {
                for (int i = 0; i < 35; i++) {
                    this.field_70170_p.func_175688_a(EnumParticleTypes.CLOUD, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, (-0.5d) + this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
                }
            }
        }
        this.lastHp = func_110143_aJ();
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187930_hd;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187934_hh;
    }
    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187899_gZ;
    }
    protected boolean func_70692_ba() {
        int time = (int) (this.field_70170_p.func_72820_D() % 24000);
        return time > 13000 && time < 18000;
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
        return LootTableRegistry.ENTITIES_MIRRORZOMBIE;
    }
}
