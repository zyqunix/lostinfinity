package xol.lostinfinity.mob.entity.boss;
import net.minecraft.entity.monster.EntityMob;
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
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
public class EntityArenaEvent extends EntityMob {
    private int eventtimer;
    private static final DataParameter<Byte> TYPE = EntityDataManager.func_187226_a(EntityArenaEvent.class, DataSerializers.field_187191_a);
    public EntityArenaEvent(World worldIn) {
        super(worldIn);
        this.eventtimer = 250;
        func_184224_h(true);
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(TYPE, (byte) 0);
    }
    public byte getEventType() {
        return ((Byte) this.field_70180_af.func_187225_a(TYPE)).byteValue();
    }
    public void setEventType(byte f) {
        this.field_70180_af.func_187227_b(TYPE, Byte.valueOf(f));
    }
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74774_a("EventType", getEventType());
    }
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setEventType(tag.func_74771_c("EventType"));
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
        AxisAlignedBB aabb = new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -145.0d), new BlockPos(52.0d, 85.0d, -40.0d));
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, aabb)) {
            near_pl.func_145747_a(new TextComponentString(str));
        }
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        this.field_70163_u = 64.0d;
        if (!this.field_70170_p.field_72995_K && getEventType() == 0) {
            if (this.eventtimer == 245) {
                func_184185_a(SoundInit.ARENA_EVENT, 2.0f, 1.0f);
                messagePlayers(TextFmt.Aqua + "Detecting another presence entering the arena...");
            }
            if (this.eventtimer == 160) {
                func_184185_a(SoundInit.ARENA_EVENT, 2.0f, 1.0f);
                messagePlayers(TextFmt.Red + "Familiar Voice: Oh how I've missed this world.");
            }
            if (this.eventtimer == 100) {
                func_184185_a(SoundInit.ARENA_EVENT, 2.0f, 1.0f);
                messagePlayers(TextFmt.Red + "Familiar Voice: I had strings but now I'm free.");
            }
            if (this.eventtimer == 50) {
                messagePlayers(TextFmt.Red + "Familiar Voice: There are no strings on me.");
            }
            if (this.eventtimer == 0) {
                func_184185_a(SoundInit.DROID_SUMMON, 2.0f, 1.0f);
                EntityDroidBoss droid = new EntityDroidBoss(this.field_70170_p);
                droid.func_70107_b(25.5d, 62.2d, -90.0d);
                droid.setForm((byte) 0);
                this.field_70170_p.func_72838_d(droid);
                func_70106_y();
            }
        }
        this.eventtimer--;
    }
}
