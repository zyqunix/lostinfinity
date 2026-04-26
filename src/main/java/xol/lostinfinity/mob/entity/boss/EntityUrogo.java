package xol.lostinfinity.mob.entity.boss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.stone.EntityInfinityStone;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityUrogo extends EntityMob implements IMaxAttack {
    private static final DataParameter<Byte> FORM = EntityDataManager.func_187226_a(EntityUrogo.class, DataSerializers.field_187191_a);
    public EntityUrogo(World worldIn) {
        super(worldIn);
        func_70105_a(2.0f, 3.2f);
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(FORM, (byte) 0);
    }
    public byte getForm() {
        return ((Byte) this.field_70180_af.func_187225_a(FORM)).byteValue();
    }
    public void setForm(byte f) {
        this.field_70180_af.func_187227_b(FORM, Byte.valueOf(f));
    }
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74774_a("BossForm", getForm());
    }
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setForm(tag.func_74771_c("BossForm"));
    }
    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(2, new EntityAIAttackMelee(this, 1.0d, true));
        this.field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsRestriction(this, 1.0d));
        this.field_70714_bg.func_75776_a(7, new EntityAIWanderAvoidWater(this, 1.0d));
        this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
        func_175456_n();
    }
    protected void func_175456_n() {
        this.field_70714_bg.func_75776_a(6, new EntityAIMoveThroughVillage(this, 1.0d, false));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityPigZombie.class}));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5000.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 14);
            func_70638_az().func_70024_g(this.field_70159_w * 7.5d, this.field_70181_x * 0.5d, this.field_70179_y * 7.0d);
            return true;
        }
        return false;
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (((getForm() == 0 && this.field_70173_aa % 120 == 0) || (getForm() == 1 && this.field_70173_aa % 50 == 0)) && !this.field_70170_p.field_72995_K && func_70638_az() != null) {
            int pick = this.field_70146_Z.nextInt(3);
            switch (pick) {
                case 0:
                    func_70634_a(func_70638_az().field_70165_t, func_70638_az().field_70163_u, func_70638_az().field_70161_v);
                    func_70652_k(func_70638_az());
                    func_70638_az().func_145747_a(new TextComponentString(TextFmt.Gold + "Urogo: Surprise!"));
                    break;
                case 1:
                    func_70638_az().func_70634_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    func_70652_k(func_70638_az());
                    func_70638_az().func_145747_a(new TextComponentString(TextFmt.Gold + "Urogo: You can't run."));
                    break;
                case 2:
                    func_70634_a(25.5d, 62.2d, -92.0d);
                    func_70638_az().func_70634_a(25.5d, 62.2d, -88.0d);
                    func_70638_az().func_145747_a(new TextComponentString(TextFmt.Gold + "Urogo: Stay, and fight!"));
                    break;
            }
        }
    }
    public void func_70645_a(DamageSource cause) {
        if (this.field_70170_p.field_73011_w.func_186058_p() == DimensionInit.celestialVoid && !this.field_70170_p.field_72995_K) {
            switch (getForm()) {
                case 0:
                    EntityArenaEvent event = new EntityArenaEvent(this.field_70170_p);
                    event.func_70107_b(25.5d, 62.2d, -90.0d);
                    event.setEventType((byte) 0);
                    this.field_70170_p.func_72838_d(event);
                    AxisAlignedBB aabb = new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -145.0d), new BlockPos(52.0d, 85.0d, -40.0d));
                    for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, aabb)) {
                        near_pl.func_145747_a(new TextComponentString(TextFmt.Dark_Red + "Urogo: I'm not done with you yet."));
                    }
                    break;
                case 1:
                    EntityInfinityStone badlandsStone = new EntityInfinityStone(this.field_70170_p);
                    badlandsStone.setStoneNum((byte) 2);
                    badlandsStone.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    this.field_70170_p.func_72838_d(badlandsStone);
                    func_145779_a(ItemInit.arenaCard, 1);
                    break;
            }
        }
        super.func_70645_a(cause);
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.UROGO_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.UROGO_HIT;
    }
    protected SoundEvent func_184639_G() {
        return null;
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
}
