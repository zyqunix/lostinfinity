package xol.lostinfinity.projectile.entity;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomDamageResult;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityForbiddenBrand.class */
public class EntityForbiddenBrand extends Entity implements IMaxAttack {
    private EntityPlayer creator;
    private EntityLivingBase target;
    private float rotation;
    private UUID targetID;

    public EntityForbiddenBrand(World worldIn) {
        super(worldIn);
        this.creator = null;
        this.target = null;
        this.rotation = 0.0f;
        this.targetID = null;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            if (this.targetID != null) {
                this.target = this.field_70170_p.func_152378_a(this.targetID);
                this.targetID = null;
            }
            if (this.creator == null || this.target == null) {
                if (this.field_70173_aa > 5) {
                    func_70106_y();
                }
            } else {
                if (this.field_70173_aa % 3 == 0) {
                    func_70634_a(this.target.field_70165_t, this.target.field_70163_u + ((double) (this.target.field_70131_O / 2.0f)), this.target.field_70161_v);
                }
                if (this.field_70173_aa % 10 == 0) {
                    CustomDamageResult dr = IMaxAttack.dealMaxHealth(this.creator, this.target, 3);
                    if (dr.wasTargetKilled() && (this.target instanceof EntityPlayer)) {
                        this.targetID = this.target.func_110124_au();
                    }
                }
                if (this.field_70173_aa % 20 == 0) {
                    func_184185_a(SoundInit.GENERIC_WEAPON_12, 1.0f, 0.8f + (this.field_70146_Z.nextFloat() * 0.4f));
                }
            }
            if (this.field_70173_aa >= 800) {
                func_70106_y();
                return;
            }
            return;
        }
        this.rotation += 0.15f;
        double velocity_x = ((double) 8.0f) * Math.cos(this.rotation);
        double velocity_z = ((double) 8.0f) * Math.sin(this.rotation);
        for (int i = 0; i < 3; i++) {
            if (this.field_70173_aa % 80 < 40) {
                this.field_70170_p.func_175688_a(ParticleInit.BLIGHT_SPELL_PINK, this.field_70165_t + (velocity_x / 2.0d), this.field_70163_u, this.field_70161_v + (velocity_z / 2.0d), 0.0d, 0.0d, 0.0d, new int[0]);
            } else {
                this.field_70170_p.func_175688_a(ParticleInit.BLIGHT_SPELL_GREEN, this.field_70165_t + (velocity_x / 2.0d), this.field_70163_u, this.field_70161_v + (velocity_z / 2.0d), 0.0d, 0.0d, 0.0d, new int[0]);
            }
        }
    }

    public void setTarget(EntityLivingBase target) {
        this.target = target;
    }

    public void setCreator(EntityPlayer creator) {
        this.creator = creator;
    }

    protected void func_70037_a(NBTTagCompound compound) {
    }

    protected void func_70014_b(NBTTagCompound compound) {
    }

    protected void func_70088_a() {
    }
}
