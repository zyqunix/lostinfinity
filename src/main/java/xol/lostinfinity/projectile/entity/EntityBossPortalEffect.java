package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.boss.EntityTempTentacle;
import xol.lostinfinity.mob.entity.starforge.EntityTentaclon;
public class EntityBossPortalEffect extends Entity {
    private float growth;
    private float rotation;
    float angle;
    private EntityTempTentacle creator;
    public float getGrowth() {
        return this.growth;
    }
    public float getRotation() {
        return this.rotation;
    }
    public void setCreator(EntityTempTentacle tentacle) {
        this.creator = tentacle;
    }
    public EntityBossPortalEffect(World worldIn) {
        super(worldIn);
        this.growth = 0.0f;
        this.rotation = 0.0f;
        this.angle = 0.0f;
        this.creator = null;
        func_70105_a(0.5f, 0.5f);
    }
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70173_aa < 70) {
            this.growth += 0.1f;
        }
        this.rotation += 0.2f;
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa >= 200) {
                func_70106_y();
            }
            if (this.field_70173_aa >= 50 && this.field_70173_aa <= 150 && this.field_70173_aa % 50 == 0 && this.creator != null) {
                EntityTentaclon tenta = new EntityTentaclon(this.field_70170_p);
                tenta.func_70107_b((this.field_70165_t - 0.5d) + this.field_70146_Z.nextDouble(), this.field_70163_u, (this.field_70161_v - 0.5d) + this.field_70146_Z.nextDouble());
                tenta.func_70024_g(0.0d, -1.0d, 0.0d);
                tenta.field_70133_I = true;
                this.field_70170_p.func_72838_d(tenta);
                func_184185_a(SoundInit.WIND_GUST, 1.5f, 0.7f + (0.6f * this.field_70146_Z.nextFloat()));
                return;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            float growthDist = this.growth * 1.06f;
            this.angle += 0.15f;
            double velocity_x = ((double) growthDist) * Math.cos(this.angle);
            double velocity_z = ((double) growthDist) * Math.sin(this.angle);
            this.field_70170_p.func_175688_a(ParticleInit.DARK_FIZZLE, this.field_70165_t + (velocity_x / 2.0d), this.field_70163_u, this.field_70161_v + (velocity_z / 2.0d), 0.0d, 0.0d, 0.0d, new int[0]);
        }
    }
    protected void func_70088_a() {
    }
    protected void func_70037_a(NBTTagCompound compound) {
    }
    protected void func_70014_b(NBTTagCompound compound) {
    }
}
