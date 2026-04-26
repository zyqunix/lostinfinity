package xol.lostinfinity.mob.entity.deviant.prime;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityDeviantPrime;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/deviant/prime/EntityAzross.class */
public class EntityAzross extends EntityDeviantPrime implements IMaxAttack {
    private EntityLivingBase marked;
    private int markedTimer;

    public EntityAzross(World worldIn) {
        super(worldIn);
        this.marked = null;
        this.markedTimer = 0;
        func_70105_a(1.75f, 2.9f);
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2000.0d);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            boolean killed = IMaxAttack.dealMaxHealth(this, func_70638_az(), 3).wasTargetKilled();
            Vec3d lookMulti = func_70040_Z();
            func_70638_az().func_70024_g(lookMulti.field_72450_a * 5.0d, 0.25d, lookMulti.field_72449_c * 5.0d);
            func_70638_az().field_70133_I = true;
            if (killed && (func_70638_az() instanceof EntityPlayer)) {
                func_70106_y();
                return true;
            }
            return true;
        }
        return false;
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            EntityLivingBase target = func_70638_az();
            if (target != null && this.field_70173_aa % 60 == 0) {
                func_70024_g((target.field_70165_t - this.field_70165_t) * 0.145d, (target.field_70163_u - this.field_70163_u) * 0.145d, (target.field_70161_v - this.field_70161_v) * 0.145d);
                this.field_70133_I = true;
                this.marked = target;
            }
            if (this.marked != null) {
                if (this.markedTimer == 15) {
                    func_70634_a(this.marked.field_70165_t, this.marked.field_70163_u, this.marked.field_70161_v);
                    return;
                } else {
                    this.markedTimer++;
                    return;
                }
            }
            this.markedTimer = 0;
        }
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.GENERIC_STYLE1_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.GENERIC_STYLE1_HURT;
    }

    protected SoundEvent func_184639_G() {
        return null;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantPrime
    protected String primeName() {
        return "Azross";
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantPrime
    protected Item primeDrop() {
        return ItemInit.deviantFragmentTR;
    }
}
