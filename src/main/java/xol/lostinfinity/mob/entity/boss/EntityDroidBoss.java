package xol.lostinfinity.mob.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
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
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.item.weapon.ItemHeadCollector;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/boss/EntityDroidBoss.class */
public class EntityDroidBoss extends EntityMob implements IMaxAttack, IBasicAI {
    private static final DataParameter<Byte> FORM = EntityDataManager.func_187226_a(EntityUrogo.class, DataSerializers.field_187191_a);

    public EntityDroidBoss(World worldIn) {
        super(worldIn);
        func_70105_a(1.5f, 3.2f);
    }

    protected void func_184651_r() {
        initBasicTasks(this);
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

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0d);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 20);
            return true;
        }
        return false;
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187599_cE;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187602_cF;
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

    public void func_70645_a(DamageSource cause) {
        if (getForm() == 0) {
            if (!this.field_70170_p.field_72995_K) {
                for (int k = 0; k < 12; k++) {
                    EntityDroidBoss droid = new EntityDroidBoss(this.field_70170_p);
                    switch (k) {
                        case 0:
                            droid.func_70107_b(10.0d, 61.2d, -79.0d);
                            break;
                        case 1:
                            droid.func_70107_b(10.0d, 61.2d, -87.0d);
                            break;
                        case 2:
                            droid.func_70107_b(10.0d, 61.2d, -94.0d);
                            break;
                        case 3:
                            droid.func_70107_b(10.0d, 61.2d, -102.0d);
                            break;
                        case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                            droid.func_70107_b(41.0d, 61.2d, -79.0d);
                            break;
                        case 5:
                            droid.func_70107_b(41.0d, 61.2d, -87.0d);
                            break;
                        case TileEntityFusionTable.BOARD_COLUMNS /* 6 */:
                            droid.func_70107_b(41.0d, 61.2d, -94.0d);
                            break;
                        case 7:
                            droid.func_70107_b(41.0d, 61.2d, -102.0d);
                            break;
                        case 8:
                            droid.func_70107_b(34.0d, 61.2d, -79.0d);
                            break;
                        case 9:
                            droid.func_70107_b(34.0d, 61.2d, -102.0d);
                            break;
                        case ItemHeadCollector.CHARGE_LIMIT /* 10 */:
                            droid.func_70107_b(17.0d, 61.2d, -79.0d);
                            break;
                        case 11:
                            droid.func_70107_b(17.0d, 61.2d, -102.0d);
                            break;
                    }
                    droid.setForm((byte) 1);
                    this.field_70170_p.func_72838_d(droid);
                    if (func_70638_az() != null) {
                        droid.func_70624_b(func_70638_az());
                    }
                }
                AxisAlignedBB aabb = new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -145.0d), new BlockPos(52.0d, 85.0d, -40.0d));
                for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, aabb)) {
                    near_pl.func_145747_a(new TextComponentString(TextFmt.Red + "Familiar Voice: You didn't really think this would be that easy?"));
                }
            }
        } else if (!this.field_70170_p.field_72995_K) {
            int droidsleft = this.field_70170_p.func_72907_a(EntityDroidBoss.class);
            if (droidsleft <= 1) {
                EntityUrogo urogo = new EntityUrogo(this.field_70170_p);
                urogo.func_70107_b(25.5d, 62.2d, -90.0d);
                urogo.setForm((byte) 1);
                this.field_70170_p.func_72838_d(urogo);
                AxisAlignedBB aabb2 = new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -145.0d), new BlockPos(52.0d, 85.0d, -40.0d));
                for (EntityPlayer near_pl2 : this.field_70170_p.func_72872_a(EntityPlayer.class, aabb2)) {
                    near_pl2.func_145747_a(new TextComponentString(TextFmt.Gold + "Urogo: Enough of this! Just surrender."));
                }
            }
        }
        super.func_70645_a(cause);
    }
}
