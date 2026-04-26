package xol.lostinfinity.mob.entity.boss;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.stone.EntityInfinityStone;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityRikarus extends EntityMob implements IMaxAttack, IBasicAI {
    private static final DataParameter<Byte> FORM = EntityDataManager.func_187226_a(EntityRikarus.class, DataSerializers.field_187191_a);
    private static final DataParameter<Boolean> SPINNING = EntityDataManager.func_187226_a(EntityRikarus.class, DataSerializers.field_187198_h);
    public EntityRikarus(World worldIn) {
        super(worldIn);
        func_70105_a(3.5f, 5.5f);
    }
    protected void func_184651_r() {
        initBasicTasks(this);
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(FORM, (byte) 0);
        this.field_70180_af.func_187214_a(SPINNING, false);
    }
    public byte getForm() {
        return ((Byte) this.field_70180_af.func_187225_a(FORM)).byteValue();
    }
    public void setForm(byte f) {
        this.field_70180_af.func_187227_b(FORM, Byte.valueOf(f));
    }
    public boolean isSpinning() {
        return ((Boolean) this.field_70180_af.func_187225_a(SPINNING)).booleanValue();
    }
    public void setSpinning(boolean f) {
        this.field_70180_af.func_187227_b(SPINNING, Boolean.valueOf(f));
    }
    public void gainFormBack() {
        if (getForm() > 0) {
            setForm((byte) (getForm() - 1));
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
                sendHealthMSG(near_pl, 100);
            }
        }
    }
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74774_a("BossForm", getForm());
        tag.func_74757_a("SpinningAttack", isSpinning());
    }
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setForm(tag.func_74771_c("BossForm"));
        setSpinning(tag.func_74767_n("SpinningAttack"));
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5000.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 4);
            return true;
        }
        return false;
    }
    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -145.0d), new BlockPos(52.0d, 85.0d, -40.0d));
    }
    private void sendHealthMSG(EntityPlayer play, int topnum) {
        play.func_145747_a(new TextComponentString(TextFmt.Light_Purple + "Rikarus is at " + (topnum - (10 * getForm())) + "% health"));
    }
    public void func_70645_a(DamageSource cause) {
        if (this.field_70170_p.field_73011_w.func_186058_p() == DimensionInit.celestialVoid) {
            int stage = getForm();
            if (stage < 9) {
                if (!this.field_70170_p.field_72995_K) {
                    for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
                        sendHealthMSG(near_pl, 90);
                    }
                    EntityRikarus newRikarus = new EntityRikarus(this.field_70170_p);
                    boolean inAir = false;
                    int x_pos = 0;
                    int z_pos = 0;
                    while (!inAir) {
                        x_pos = this.field_70146_Z.nextInt(30);
                        z_pos = this.field_70146_Z.nextInt(80);
                        if (this.field_70170_p.func_175623_d(new BlockPos(0 + x_pos, 63, (-140) + z_pos))) {
                            inAir = true;
                        }
                    }
                    newRikarus.func_70107_b(0 + x_pos, 63.0d, (-140) + z_pos);
                    newRikarus.setForm((byte) (getForm() + 1));
                    this.field_70170_p.func_72838_d(newRikarus);
                    return;
                }
                return;
            }
            super.func_70645_a(cause);
            if (!this.field_70170_p.field_72995_K) {
                EntityInfinityStone misdirStone = new EntityInfinityStone(this.field_70170_p);
                misdirStone.setStoneNum((byte) 4);
                misdirStone.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                this.field_70170_p.func_72838_d(misdirStone);
                func_145779_a(ItemInit.arenaCard, 1);
            }
        }
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (this.field_70170_p.field_72995_K && (func_110143_aJ() <= 0.0f || this.field_70128_L)) {
            for (int i = 0; i < 24; i++) {
                this.field_70170_p.func_175688_a(EnumParticleTypes.DRAGON_BREATH, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
            }
        }
        if (getForm() != 0 && !this.field_70170_p.field_72995_K && (this.field_70173_aa + 320) % 400 == 0) {
            func_184185_a(SoundInit.RIKARUS_CRYSTAL, 3.0f, 1.0f);
            int crystal = 0;
            while (true) {
                if (crystal >= (getForm() < 5 ? 1 : 3)) {
                    break;
                }
                EntityRestorationCrystal crystalheal = new EntityRestorationCrystal(this.field_70170_p);
                boolean inAir = false;
                int x_pos = 0;
                int z_pos = 0;
                while (!inAir) {
                    x_pos = this.field_70146_Z.nextInt(30);
                    z_pos = this.field_70146_Z.nextInt(80);
                    if (this.field_70170_p.func_175623_d(new BlockPos(0 + x_pos, 63, (-140) + z_pos))) {
                        inAir = true;
                    }
                }
                crystalheal.func_70107_b(0 + x_pos, 63.0d, (-140) + z_pos);
                this.field_70170_p.func_72838_d(crystalheal);
                crystal++;
            }
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
                String crystalMessage = "";
                switch (this.field_70146_Z.nextInt(3)) {
                    case 0:
                        crystalMessage = "Rise my crystals! Heal me.";
                        break;
                    case 1:
                        crystalMessage = "With my crystals I am unkillable!";
                        break;
                    case 2:
                        crystalMessage = "Crystals, give me life!";
                        break;
                }
                near_pl.func_145747_a(new TextComponentString(TextFmt.Dark_Purple + "Rikarus: " + crystalMessage));
            }
        }
        func_70024_g(0.0d, this.field_70173_aa % 60 < 30 ? 0.10000000149011612d : 0.0d, 0.0d);
        if (!this.field_70170_p.field_72995_K) {
            if (func_70638_az() == null) {
                AxisAlignedBB aabb = getArenaAABB();
                Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, aabb).iterator();
                if (it.hasNext()) {
                    EntityPlayer near_pl2 = (EntityPlayer) it.next();
                    if (this.field_70159_w > -0.699999988079071d && this.field_70159_w < 0.699999988079071d && this.field_70179_y > -0.699999988079071d && this.field_70179_y < 0.699999988079071d) {
                        func_70024_g((near_pl2.field_70165_t - this.field_70165_t) * 0.03d, (near_pl2.field_70163_u - this.field_70163_u) * 0.02d, (near_pl2.field_70161_v - this.field_70161_v) * 0.03d);
                        this.field_70133_I = true;
                    }
                    setSpinning(true);
                    return;
                }
                return;
            }
            setSpinning(false);
        }
    }
    protected SoundEvent func_184615_bR() {
        if (getForm() == 9) {
            return SoundInit.RIKARUS_DEATH;
        }
        return SoundInit.RIKARUS_HURT;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.RIKARUS_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.RIKARUS_AMBIENT;
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
}
