package xol.lostinfinity.mob.entity.labyrinth;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.projectile.entity.EntityDeviantFireball;
import xol.lostinfinity.projectile.entity.EntityWizardBlast;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityAspect extends EntityMultipleLives implements IMaxAttack {
    private static final DataParameter<Integer> STYLE = EntityDataManager.func_187226_a(EntityAspect.class, DataSerializers.field_187192_b);
    private Item map_drop;
    public EntityAspect(World worldIn) {
        super(worldIn);
        func_70105_a(1.0f, 2.5f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(STYLE, 0);
    }
    public int getStyle() {
        return ((Integer) this.field_70180_af.func_187225_a(STYLE)).intValue();
    }
    public void setStyle(int f) {
        this.field_70180_af.func_187227_b(STYLE, Integer.valueOf(f));
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74768_a("SpawnForm", getStyle());
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setStyle(tag.func_74762_e("SpawnForm"));
    }
    public void setMapDrop(Item item) {
        this.map_drop = item;
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0d);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K && this.map_drop != null) {
            func_145779_a(this.map_drop, 1);
        }
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 4);
            return true;
        }
        return false;
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.ASPECT_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.ASPECT_HIT;
    }
    protected SoundEvent func_184639_G() {
        return null;
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            EntityLivingBase target = func_70638_az();
            switch (getStyle()) {
                case 0:
                    if (this.field_70173_aa % 5 == 0 && target != null) {
                        EntityWizardBlast shot = new EntityWizardBlast(this.field_70170_p, this);
                        double d0 = target.field_70165_t - this.field_70165_t;
                        double d1 = (target.func_174813_aQ().field_72338_b + ((double) (target.field_70131_O / 5.0f))) - shot.field_70163_u;
                        double d2 = target.field_70161_v - this.field_70161_v;
                        double d3 = MathHelper.func_76133_a((d0 * d0) + (d2 * d2));
                        shot.func_70186_c(d0, d1 + (d3 * 0.20000000298023224d), d2, 2.0f, 0.0f);
                        this.field_70170_p.func_72838_d(shot);
                        func_184185_a(SoundEvents.field_187606_E, 1.0f, 1.0f);
                    }
                    if (this.field_70173_aa % 40 == 0 && !this.field_70170_p.field_72995_K) {
                        for (int snum = 0; snum < 5; snum++) {
                            EntityWizardBlast shot2 = new EntityWizardBlast(this.field_70170_p, this);
                            shot2.func_70186_c((-1.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 2.0d), 0.0d + ((double) (0.2f * Math.floorDiv(this.field_70173_aa % 90, 10))), (-1.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 2.0d), 1.0f, 0.0f);
                            this.field_70170_p.func_72838_d(shot2);
                        }
                        func_184185_a(SoundEvents.field_187606_E, 1.0f, 1.0f);
                    }
                    break;
                case 1:
                    if (this.field_70173_aa % 30 == 0) {
                        double x0 = this.field_70165_t;
                        double y0 = this.field_70163_u + 2.5d;
                        double z0 = this.field_70161_v;
                        func_184185_a(SoundEvents.field_187606_E, 1.0f, 1.0f);
                        float f = 0.0f;
                        while (true) {
                            float angle = f;
                            if (angle <= 6.283185307179586d) {
                                EntityWizardBlast shot3 = new EntityWizardBlast(this.field_70170_p, this);
                                shot3.func_70107_b(x0, y0, z0);
                                double velocity_x = 5.0d * Math.cos(angle);
                                double velocity_z = 5.0d * Math.sin(angle);
                                shot3.setThrower(this);
                                shot3.calculateVelocity(velocity_x, -0.5d, velocity_z);
                                shot3.func_184538_a(this, shot3.field_70125_A, shot3.field_70177_z, 0.0f, 1.5f, 0.0f);
                                this.field_70170_p.func_72838_d(shot3);
                                f = (float) (((double) angle) + 0.3141592653589793d);
                            }
                        }
                    }
                    break;
                case 2:
                    if (this.field_70173_aa % 50 <= 20 && this.field_70173_aa % 10 == 0) {
                        boolean did_shot = false;
                        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(18.0d, 18.0d, 18.0d))) {
                            if (!near_pl.func_184812_l_()) {
                                func_70676_i(1.0f);
                                double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
                                double makeY = this.field_70163_u + ((double) (this.field_70131_O / 2.0f)) + 0.5d;
                                double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
                                double d22 = near_pl.field_70165_t - makeX;
                                double d32 = (near_pl.func_174813_aQ().field_72338_b + ((double) (near_pl.field_70131_O / 2.0f))) - makeY;
                                double d4 = near_pl.field_70161_v - makeZ;
                                EntityDeviantFireball shot4 = new EntityDeviantFireball(this.field_70170_p, this, d22, d32, d4, 3, 0.5f);
                                shot4.field_70165_t = makeX;
                                shot4.field_70163_u = makeY;
                                shot4.field_70161_v = makeZ;
                                this.field_70170_p.func_72838_d(shot4);
                                did_shot = true;
                            }
                        }
                        if (did_shot) {
                            func_184185_a(SoundEvents.field_187606_E, 2.0f, 0.5f + this.field_70146_Z.nextFloat());
                        }
                    }
                    break;
            }
            if (this.field_70173_aa == 2400) {
                func_70106_y();
            }
        }
    }
    public boolean func_180427_aV() {
        return true;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 5;
    }
    protected boolean func_70692_ba() {
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70814_o() {
        return true;
    }
    public int func_70641_bl() {
        return 1;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }
}
