package xol.lostinfinity.mob.entity.murk;
import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityWhisper extends EntityMultipleLives implements IMaxAttack, IBasicAI {
    private EntityLivingBase followingTarget;
    private static final DataParameter<Boolean> SACRIFICED = EntityDataManager.func_187226_a(EntityWhisper.class, DataSerializers.field_187198_h);
    private static final DataParameter<Boolean> RITUAL_LEADER = EntityDataManager.func_187226_a(EntityWhisper.class, DataSerializers.field_187198_h);
    private int timer;
    private float pitch;
    public EntityWhisper(World worldIn) {
        super(worldIn);
        this.followingTarget = null;
        this.timer = 200;
        this.pitch = 0.0f;
        func_70105_a(4.0f, 5.0f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        func_184212_Q().func_187214_a(SACRIFICED, false);
        func_184212_Q().func_187214_a(RITUAL_LEADER, false);
    }
    public boolean func_70652_k(Entity entity) {
        if (func_70638_az() != null) {
            if (this.followingTarget != null) {
                if (func_70638_az().equals(this.followingTarget)) {
                    func_70624_b(null);
                    return false;
                }
                return false;
            }
            IMaxAttack.dealMaxHealth((Entity) this, func_70638_az(), 2, (List<String>) Arrays.asList("Darkborn"));
            return true;
        }
        return false;
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(3000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.WHISPER_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.WHISPER_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.WHISPER_AMBIENT;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return super.func_70601_bi() && nothingInRadius(45);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 15;
    }
    public boolean isSacrificed() {
        return ((Boolean) this.field_70180_af.func_187225_a(SACRIFICED)).booleanValue();
    }
    public void setSacrificed(boolean sacrificed) {
        this.field_70180_af.func_187227_b(SACRIFICED, Boolean.valueOf(sacrificed));
        this.followingTarget = null;
    }
    public boolean isLeader() {
        return ((Boolean) this.field_70180_af.func_187225_a(RITUAL_LEADER)).booleanValue();
    }
    public void setLeader(boolean leader) {
        this.field_70180_af.func_187227_b(RITUAL_LEADER, Boolean.valueOf(leader));
    }
    public void setFollowingTarget(EntityLivingBase t) {
        this.followingTarget = t;
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (isLeader()) {
                if (this.timer == 0) {
                    List<EntityWhisper> sacrifices = this.field_70170_p.func_72872_a(EntityWhisper.class, new AxisAlignedBB(func_180425_c()).func_186662_g(50.0d));
                    for (EntityWhisper sacrifice : sacrifices) {
                        if (!sacrifice.func_110124_au().equals(func_110124_au())) {
                            sacrifice.func_70106_y();
                        }
                    }
                    EntityScreamer screamer = new EntityScreamer(this.field_70170_p);
                    screamer.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    screamer.setInOcean(false);
                    this.field_70170_p.func_72838_d(screamer);
                    func_70106_y();
                } else {
                    this.timer--;
                    if (this.field_70173_aa % 10 == 0) {
                        this.pitch += 0.1f;
                        this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.BIOENERGIZE, SoundCategory.HOSTILE, 1.0f, this.pitch);
                    }
                }
            }
            if (this.followingTarget != null) {
                func_70624_b(null);
                func_70671_ap().func_75650_a(this.followingTarget.field_70165_t, this.followingTarget.field_70163_u + 2.0d, this.followingTarget.field_70161_v, 10.0f, 10.0f);
                if (this.followingTarget.field_70128_L) {
                    this.followingTarget = null;
                    return;
                }
                if (func_70068_e(this.followingTarget) > 1750.0d) {
                    this.followingTarget = null;
                    return;
                }
                if (this.followingTarget.field_70163_u > this.field_70163_u && ((this.field_70159_w == 0.0d || this.field_70179_y == 0.0d) && !isSacrificed())) {
                    this.field_70181_x = 0.5d;
                }
                if (!isSacrificed()) {
                    func_70605_aq().func_75642_a(this.followingTarget.field_70165_t, this.followingTarget.field_70163_u, this.followingTarget.field_70161_v, 0.75d);
                } else {
                    func_70605_aq().func_75642_a(this.followingTarget.field_70165_t, this.followingTarget.field_70163_u, this.followingTarget.field_70161_v, 0.5d);
                }
            }
            if (isSacrificed() && this.field_70146_Z.nextInt(15) == 0) {
                this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), randomWhisper(this.field_70146_Z.nextInt(5)), SoundCategory.PLAYERS, 1.25f, 0.7f + (this.field_70146_Z.nextFloat() * 0.6f));
                return;
            }
            return;
        }
        if (isLeader() && this.field_70173_aa % 4 == 0) {
            List<EntityWhisper> sacrifices2 = this.field_70170_p.func_72872_a(EntityWhisper.class, new AxisAlignedBB(func_180425_c()).func_186662_g(50.0d));
            for (EntityWhisper sacrifice2 : sacrifices2) {
                if (!sacrifice2.func_110124_au().equals(func_110124_au())) {
                    Vec3d dir = sacrifice2.func_174791_d().func_178788_d(func_174791_d()).func_72432_b();
                    double dist = sacrifice2.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    double d = 0.0d;
                    while (true) {
                        double i = d;
                        if (i < dist) {
                            if (this.field_70146_Z.nextBoolean()) {
                                this.field_70170_p.func_175688_a(ParticleInit.GENERIC_DOT_PURPLE, this.field_70165_t + (dir.field_72450_a * i), this.field_70163_u + (dir.field_72448_b * i) + (((double) this.field_70131_O) / 1.8d), this.field_70161_v + (dir.field_72449_c * i), 0.0d, 0.0d, 0.0d, new int[0]);
                            }
                            d = i + 0.9d;
                        }
                    }
                }
            }
        }
    }
    private SoundEvent randomWhisper(int i) {
        switch (i) {
            case 0:
                return SoundInit.WHISPER_1;
            case 1:
                return SoundInit.WHISPER_2;
            case 2:
                return SoundInit.WHISPER_3;
            case 3:
                return SoundInit.WHISPER_4;
            case TileEntityFusionTable.BOARD_ROWS :
                return SoundInit.WHISPER_5;
            default:
                return SoundInit.WHISPER_5;
        }
    }
}
