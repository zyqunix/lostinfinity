package xol.lostinfinity.mob.entity.labyrinth;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityVectosect extends EntityMultipleLives implements IMaxAttack {
    private static final DataParameter<Float> GMOVE_X = EntityDataManager.func_187226_a(EntityVectosect.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> GMOVE_Y = EntityDataManager.func_187226_a(EntityVectosect.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> GMOVE_Z = EntityDataManager.func_187226_a(EntityVectosect.class, DataSerializers.field_187193_c);
    private int collideGrace;
    public EntityVectosect(World worldIn) {
        super(worldIn);
        this.collideGrace = 0;
        func_70105_a(1.0f, 1.5f);
        func_189654_d(true);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(GMOVE_X, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(GMOVE_Y, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(GMOVE_Z, Float.valueOf(0.0f));
    }
    public float getXMovement() {
        return ((Float) this.field_70180_af.func_187225_a(GMOVE_X)).floatValue();
    }
    public float getYMovement() {
        return ((Float) this.field_70180_af.func_187225_a(GMOVE_Y)).floatValue();
    }
    public float getZMovement() {
        return ((Float) this.field_70180_af.func_187225_a(GMOVE_Z)).floatValue();
    }
    private void randomizeMovement() {
        float xrand = ((-1.0f) + (2.0f * this.field_70146_Z.nextFloat())) * 0.5f;
        float yrand = ((-1.0f) + (2.0f * this.field_70146_Z.nextFloat())) * 0.5f;
        float zrand = ((-1.0f) + (2.0f * this.field_70146_Z.nextFloat())) * 0.5f;
        this.field_70180_af.func_187227_b(GMOVE_X, Float.valueOf(xrand));
        this.field_70180_af.func_187227_b(GMOVE_Y, Float.valueOf(yrand));
        this.field_70180_af.func_187227_b(GMOVE_Z, Float.valueOf(zrand));
    }
    protected void func_82167_n(Entity entityIn) {
        if ((entityIn instanceof EntityPlayer) && this.collideGrace == 0) {
            EntityPlayer play = (EntityPlayer) entityIn;
            if (!play.func_184812_l_()) {
                this.collideGrace = 10;
                IMaxAttack.dealMaxHealth(this, play, 3);
            }
        }
        entityIn.func_70108_f(this);
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            if (this.collideGrace > 0) {
                this.collideGrace--;
            }
            if (this.field_70173_aa % 80 == 3) {
                randomizeMovement();
            }
        }
        this.field_70159_w = getXMovement();
        this.field_70181_x = getYMovement();
        this.field_70179_y = getZMovement();
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.VECTOSECT_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.VECTOSECT_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.VECTOSECT_AMBIENT;
    }
    protected boolean func_70692_ba() {
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 3;
    }
    protected ResourceLocation func_184647_J() {
        if (this.field_70146_Z.nextInt(7) == 0) {
            return LootTableRegistry.LABYRINTH_MIDDLE;
        }
        return null;
    }
}
