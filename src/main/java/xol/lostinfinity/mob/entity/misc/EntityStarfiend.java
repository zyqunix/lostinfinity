package xol.lostinfinity.mob.entity.misc;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityStarfiend extends EntityMob implements IMaxAttack, IBasicAI {
    private static final DataParameter<Byte> SCALETYPE = EntityDataManager.func_187226_a(EntityStarfiend.class, DataSerializers.field_187191_a);
    public EntityStarfiend(World worldIn) {
        super(worldIn);
        func_70105_a(2.2f, 3.7f);
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(SCALETYPE, (byte) 0);
    }
    public byte getScaleType() {
        return ((Byte) this.field_70180_af.func_187225_a(SCALETYPE)).byteValue();
    }
    public void setScaleType(byte f) {
        this.field_70180_af.func_187227_b(SCALETYPE, Byte.valueOf(f));
    }
    public float getPhysicalScale() {
        return 2.0f - (0.2f * getScaleType());
    }
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74774_a("ScaleType", getScaleType());
    }
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setScaleType(tag.func_74771_c("ScaleType"));
    }
    public void func_70636_d() {
        super.func_70636_d();
        func_70105_a(1.0f * getPhysicalScale(), 2.0f * getPhysicalScale());
        if (this.field_70173_aa % 60 == 0 && func_70638_az() != null && (func_70638_az() instanceof EntityPlayer)) {
            EntityPlayer pl = func_70638_az();
            func_70024_g((pl.field_70165_t - this.field_70165_t) * 0.145d, 1.0d, (pl.field_70161_v - this.field_70161_v) * 0.145d);
            this.field_70133_I = true;
        }
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 5);
            return true;
        }
        return false;
    }
    protected void func_184651_r() {
        initBasicTasks(this);
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1500.0d);
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.STARFIEND_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.STARFIEND_HIT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.STARFIEND_AMBIENT;
    }
    public boolean func_70814_o() {
        return true;
    }
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_STARFIEND;
    }
}
