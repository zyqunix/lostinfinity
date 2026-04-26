package xol.lostinfinity.mob.entity.deviant.titan;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityStray;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityLlama;
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
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.item.weapon.ItemHeadCollector;
import xol.lostinfinity.mob.entity.base.EntityDeviantMob;
import xol.lostinfinity.mob.entity.base.EntityDeviantTitan;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantBlaze;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantCreeper;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantEnderman;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantLlama;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantMagmacube;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantPiglin;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantShulker;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantSkeleton;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantSpider;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantStray;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantVex;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantZombie;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityTrialObserver extends EntityMob implements IMaxAttack {
    private static final DataParameter<Byte> TYPE = EntityDataManager.func_187226_a(EntityTrialObserver.class, DataSerializers.field_187191_a);
    private static final DataParameter<Byte> STAGE = EntityDataManager.func_187226_a(EntityTrialObserver.class, DataSerializers.field_187191_a);
    public EntityTrialObserver(World worldIn) {
        super(worldIn);
        func_70105_a(5.0f, 4.0f);
        func_184224_h(true);
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(TYPE, (byte) 0);
        this.field_70180_af.func_187214_a(STAGE, (byte) 0);
    }
    public byte getEventType() {
        return ((Byte) this.field_70180_af.func_187225_a(TYPE)).byteValue();
    }
    public void setEventType(byte f) {
        this.field_70180_af.func_187227_b(TYPE, Byte.valueOf(f));
    }
    public byte getStage() {
        return ((Byte) this.field_70180_af.func_187225_a(STAGE)).byteValue();
    }
    public void setStage(byte f) {
        this.field_70180_af.func_187227_b(STAGE, Byte.valueOf(f));
    }
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74774_a("EventType", getEventType());
        tag.func_74774_a("EventStage", getStage());
    }
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setEventType(tag.func_74771_c("EventType"));
        setStage(tag.func_74771_c("EventStage"));
    }
    protected SoundEvent func_184639_G() {
        return null;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return null;
    }
    protected SoundEvent func_184615_bR() {
        return null;
    }
    private void messagePlayers(String str) {
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            near_pl.func_145747_a(new TextComponentString(str));
        }
    }
    protected AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(-493.0d, 60.0d, 407.0d), new BlockPos(548.0d, 85.0d, 460.0d));
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        func_70634_a(522.0d, 75.0d, 438.0d);
        if (this.field_70170_p.func_175659_aa() == EnumDifficulty.PEACEFUL) {
            func_70106_y();
        }
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 15 == 0) {
            for (EntityPlayer player : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
                if (player.field_71075_bZ.field_75100_b && !player.func_184812_l_()) {
                    IMaxAttack.dealMaxHealth((Entity) this, (EntityLivingBase) player, 4, 3.0f);
                    player.func_145747_a(new TextComponentString(TextFmt.Red + "Flying is prohibited in the trial. Cheaters will be punished!"));
                }
            }
        }
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 40 == 0) {
            boolean empty = true;
            for (EntityLiving entity : this.field_70170_p.func_72872_a(EntityLiving.class, getArenaAABB())) {
                if (!entity.func_110124_au().equals(func_110124_au())) {
                    empty = false;
                }
            }
            if (empty) {
                if (getStage() < 7) {
                    setStage((byte) (getStage() + 1));
                    if (!this.field_70170_p.field_72995_K) {
                        summonWave(this.field_70170_p);
                        return;
                    }
                    return;
                }
                if (!this.field_70170_p.field_72995_K) {
                    messagePlayers("The trial has been completed.");
                    func_70106_y();
                }
            }
        }
    }
    private void summonWave(World worldIn) {
        switch (getStage()) {
            case 1:
                messagePlayers("The first wave has arrived.");
                for (int repeats = 0; repeats < 10; repeats++) {
                    summonBasic(worldIn);
                }
                break;
            case 2:
                messagePlayers("The deviant wave has arrived.");
                for (int repeats2 = 0; repeats2 < 6; repeats2++) {
                    summonDeviant(worldIn);
                }
                break;
            case 3:
                messagePlayers("The super mutated wave has arrived.");
                for (int repeats3 = 0; repeats3 < 2; repeats3++) {
                    summonSuperMutant(worldIn);
                }
                break;
            case TileEntityFusionTable.BOARD_ROWS :
                messagePlayers("The second deviant wave has arrived.");
                for (int repeats4 = 0; repeats4 < 10; repeats4++) {
                    summonDeviant(worldIn);
                }
                break;
            case 5:
                messagePlayers("The second super mutated wave has arrived.");
                for (int repeats5 = 0; repeats5 < 5; repeats5++) {
                    summonSuperMutant(worldIn);
                }
                break;
            case TileEntityFusionTable.BOARD_COLUMNS :
                messagePlayers("The mixed wave has arrived.");
                for (int repeats6 = 0; repeats6 < 5; repeats6++) {
                    summonDeviant(worldIn);
                    summonSuperMutant(worldIn);
                }
                break;
            case 7:
                messagePlayers("The final wave has arrived.");
                for (int repeats7 = 0; repeats7 < 2; repeats7++) {
                    summonBasic(worldIn);
                    summonDeviant(worldIn);
                    summonSuperMutant(worldIn);
                }
                EntityDeviantTitan titan = getTitanCreature(worldIn);
                titan.func_70107_b(522.0d, 65.0d, 438.0d);
                worldIn.func_72838_d(titan);
                break;
        }
    }
    private void summonBasic(World worldIn) {
        EntityLiving mob = getBasicCreature(worldIn);
        BlockPos spawnLoc = validSpawnPos(worldIn);
        mob.func_70107_b(spawnLoc.func_177958_n(), spawnLoc.func_177956_o(), spawnLoc.func_177952_p());
        worldIn.func_72838_d(mob);
    }
    private void summonDeviant(World worldIn) {
        EntityDeviantMob mob = getDeviantCreature(worldIn);
        BlockPos spawnLoc = validSpawnPos(worldIn);
        mob.func_70107_b(spawnLoc.func_177958_n(), spawnLoc.func_177956_o(), spawnLoc.func_177952_p());
        worldIn.func_72838_d(mob);
    }
    private void summonSuperMutant(World worldIn) {
        EntityDeviantMob mob = getDeviantCreature(worldIn);
        mob.setMutation(1);
        BlockPos spawnLoc = validSpawnPos(worldIn);
        mob.func_70107_b(spawnLoc.func_177958_n(), spawnLoc.func_177956_o(), spawnLoc.func_177952_p());
        worldIn.func_72838_d(mob);
    }
    private BlockPos validSpawnPos(World worldIn) {
        boolean inAir = false;
        BlockPos pos = null;
        while (!inAir) {
            int x_pos = this.field_70146_Z.nextInt(44);
            int z_pos = this.field_70146_Z.nextInt(34);
            pos = new BlockPos(500 + x_pos, 63, 420 + z_pos);
            if (this.field_70170_p.func_175623_d(pos)) {
                inAir = true;
            }
        }
        return pos;
    }
    private EntityLiving getBasicCreature(World worldin) {
        switch (getEventType()) {
            case 0:
                return new EntityBlaze(this.field_70170_p);
            case 1:
                return new EntitySkeleton(this.field_70170_p);
            case 2:
                return new EntitySpider(this.field_70170_p);
            case 3:
                return new EntityCreeper(this.field_70170_p);
            case TileEntityFusionTable.BOARD_ROWS :
                return new EntityEnderman(this.field_70170_p);
            case 5:
                return new EntityLlama(this.field_70170_p);
            case TileEntityFusionTable.BOARD_COLUMNS :
                return new EntityMagmaCube(this.field_70170_p);
            case 7:
                return new EntityPigZombie(this.field_70170_p);
            case 8:
                return new EntityStray(this.field_70170_p);
            case 9:
                return new EntityVex(this.field_70170_p);
            case ItemHeadCollector.CHARGE_LIMIT :
                return new EntityZombie(this.field_70170_p);
            case 11:
                return new EntityShulker(this.field_70170_p);
            default:
                return new EntityBlaze(this.field_70170_p);
        }
    }
    private EntityDeviantMob getDeviantCreature(World worldin) {
        switch (getEventType()) {
            case 0:
                return new EntityDeviantBlaze(this.field_70170_p);
            case 1:
                return new EntityDeviantSkeleton(this.field_70170_p);
            case 2:
                return new EntityDeviantSpider(this.field_70170_p);
            case 3:
                return new EntityDeviantCreeper(this.field_70170_p);
            case TileEntityFusionTable.BOARD_ROWS :
                return new EntityDeviantEnderman(this.field_70170_p);
            case 5:
                return new EntityDeviantLlama(this.field_70170_p);
            case TileEntityFusionTable.BOARD_COLUMNS :
                return new EntityDeviantMagmacube(this.field_70170_p);
            case 7:
                return new EntityDeviantPiglin(this.field_70170_p);
            case 8:
                return new EntityDeviantStray(this.field_70170_p);
            case 9:
                return new EntityDeviantVex(this.field_70170_p);
            case ItemHeadCollector.CHARGE_LIMIT :
                return new EntityDeviantZombie(this.field_70170_p);
            case 11:
                return new EntityDeviantShulker(this.field_70170_p);
            default:
                return new EntityDeviantBlaze(this.field_70170_p);
        }
    }
    private EntityDeviantTitan getTitanCreature(World worldin) {
        switch (getEventType()) {
            case 0:
                return new EntityTitanBlaze(this.field_70170_p);
            case 1:
                return new EntityTitanSkeleton(this.field_70170_p);
            case 2:
                return new EntityTitanSpider(this.field_70170_p);
            case 3:
                return new EntityTitanCreeper(this.field_70170_p);
            case TileEntityFusionTable.BOARD_ROWS :
                return new EntityTitanEnderman(this.field_70170_p);
            case 5:
                return new EntityTitanLlama(this.field_70170_p);
            case TileEntityFusionTable.BOARD_COLUMNS :
                return new EntityTitanMagmacube(this.field_70170_p);
            case 7:
                return new EntityTitanPiglin(this.field_70170_p);
            case 8:
                return new EntityTitanStray(this.field_70170_p);
            case 9:
                return new EntityTitanVex(this.field_70170_p);
            case ItemHeadCollector.CHARGE_LIMIT :
                return new EntityTitanZombie(this.field_70170_p);
            case 11:
                return new EntityTitanShulker(this.field_70170_p);
            default:
                return new EntityTitanBlaze(this.field_70170_p);
        }
    }
}
