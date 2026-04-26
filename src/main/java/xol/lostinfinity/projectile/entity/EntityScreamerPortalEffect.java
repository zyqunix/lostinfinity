package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityScreamerPortalEffect.class */
public class EntityScreamerPortalEffect extends Entity {
    private float growth;
    private float rotation;
    float angle;

    public float getGrowth() {
        return this.growth;
    }

    public float getRotation() {
        return this.rotation;
    }

    public EntityScreamerPortalEffect(World worldIn) {
        super(worldIn);
        this.growth = 0.0f;
        this.rotation = 0.0f;
        this.angle = 0.0f;
        func_70105_a(0.5f, 0.5f);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70173_aa < 70) {
            this.growth += 0.2f;
        }
        this.rotation += 0.2f;
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa >= 200) {
                func_70106_y();
                return;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            float growthDist = this.growth * 1.06f;
            this.angle += 0.15f;
            double velocity_x = ((double) growthDist) * Math.cos(this.angle);
            double velocity_z = ((double) growthDist) * Math.sin(this.angle);
            this.field_70170_p.func_175688_a(ParticleInit.GLOOM_SPELL, this.field_70165_t + (velocity_x / 2.0d), this.field_70163_u, this.field_70161_v + (velocity_z / 2.0d), 0.0d, 0.0d, 0.0d, new int[0]);
        }
    }

    protected void func_70088_a() {
    }

    protected void func_70037_a(NBTTagCompound compound) {
    }

    protected void func_70014_b(NBTTagCompound compound) {
    }
}
