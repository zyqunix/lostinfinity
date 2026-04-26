package xol.lostinfinity.mob.entity.starforge;
import java.util.Iterator;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityWisp extends EntityMob implements IMaxAttack {
    private static final DataParameter<Float> GMOVE_X = EntityDataManager.func_187226_a(EntityWisp.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> GMOVE_Y = EntityDataManager.func_187226_a(EntityWisp.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> GMOVE_Z = EntityDataManager.func_187226_a(EntityWisp.class, DataSerializers.field_187193_c);
    public EntityWisp(World worldIn) {
        super(worldIn);
        func_70105_a(0.75f, 0.75f);
        func_189654_d(true);
    }
    protected void func_184651_r() {
    }
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
        float xrand = ((-1.0f) + (2.0f * this.field_70146_Z.nextFloat())) * 0.04f;
        float yrand = ((-1.0f) + (2.0f * this.field_70146_Z.nextFloat())) * 0.04f;
        float zrand = ((-1.0f) + (2.0f * this.field_70146_Z.nextFloat())) * 0.04f;
        this.field_70180_af.func_187227_b(GMOVE_X, Float.valueOf(xrand));
        this.field_70180_af.func_187227_b(GMOVE_Y, Float.valueOf(yrand));
        this.field_70180_af.func_187227_b(GMOVE_Z, Float.valueOf(zrand));
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 60 == 3) {
            boolean found = false;
            Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d)).iterator();
            if (it.hasNext()) {
                EntityPlayer near_pl = (EntityPlayer) it.next();
                if (near_pl.func_184614_ca().func_77973_b() == ItemInit.beaconOfLight) {
                    found = true;
                    float xmove = (float) ((near_pl.field_70165_t - this.field_70165_t) * 0.019999999552965164d);
                    float ymove = (float) ((near_pl.field_70163_u - this.field_70163_u) * 0.019999999552965164d);
                    float zmove = (float) ((near_pl.field_70161_v - this.field_70161_v) * 0.019999999552965164d);
                    this.field_70180_af.func_187227_b(GMOVE_X, Float.valueOf(xmove));
                    this.field_70180_af.func_187227_b(GMOVE_Y, Float.valueOf(ymove));
                    this.field_70180_af.func_187227_b(GMOVE_Z, Float.valueOf(zmove));
                }
            }
            if (!found) {
                randomizeMovement();
            }
        }
        this.field_70159_w = getXMovement();
        this.field_70181_x = getYMovement();
        this.field_70179_y = getZMovement();
    }
    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        if (!this.field_70170_p.field_72995_K) {
            boolean foundLeaves = false;
            Iterable<BlockPos> nearblocks = BlockPos.func_177980_a(func_180425_c().func_177982_a(-6, -6, -6), func_180425_c().func_177982_a(6, 6, 6));
            Iterator<BlockPos> it = nearblocks.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BlockPos pos = it.next();
                if (this.field_70170_p.func_180495_p(pos).func_177230_c() == BlockInit.leavesRebirth) {
                    foundLeaves = true;
                    break;
                }
            }
            if (foundLeaves) {
                EntityItem heart = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, new ItemStack(ItemInit.branchOfEnergy));
                heart.field_70159_w = 0.0d;
                heart.field_70181_x = 0.0d;
                heart.field_70179_y = 0.0d;
                this.field_70170_p.func_72838_d(heart);
                func_70106_y();
                func_184185_a(SoundInit.LIGHT_MAGIC, 1.0f, 1.0f + (this.field_70146_Z.nextFloat() * 0.3f));
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.CRYSTAL_MAGIC).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u + ((double) (this.field_70131_O / 2.0f)), this.field_70161_v);
                return true;
            }
            return true;
        }
        return true;
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
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
    protected SoundEvent func_184615_bR() {
        return SoundInit.WISP_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.WISP_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.WISP_AMBIENT;
    }
}
