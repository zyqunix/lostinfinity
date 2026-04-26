package xol.lostinfinity.mob.entity.base;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import xol.lostinfinity.common.packets.LostInfinityPacketHandler;
import xol.lostinfinity.common.packets.clientbound.PacketSyncParts;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.mob.entity.classify.ILostMultiPart;
import xol.lostinfinity.mob.entity.classify.IRelay;
public class EntityMultipleLivesRelay<T extends EntityMultipleLives> extends EntityMultipleLives implements IRelay<T> {
    protected T relay;
    protected int id;
    protected boolean awaitSync;
    protected double syncX;
    protected double syncY;
    protected double syncZ;
    protected float syncYaw;
    protected float syncPitch;
    public EntityMultipleLivesRelay(World worldIn) {
        super(worldIn);
    }
    public EntityMultipleLivesRelay(T relay, float width, float height) {
        super(relay.func_130014_f_());
        this.relay = relay;
        func_70105_a(width, height);
        this.field_70145_X = true;
        func_189654_d(true);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(relay.func_110138_aP());
    }
    @Override // xol.lostinfinity.mob.entity.classify.IRelay
    public T mo307getRelay() {
        return this.relay;
    }
    @Override // xol.lostinfinity.mob.entity.classify.IRelay
    public void setId(int id) {
        this.id = id;
    }
    @Override // xol.lostinfinity.mob.entity.classify.IRelay
    public int getId() {
        return this.id;
    }
    @Override // xol.lostinfinity.mob.entity.classify.IRelay
    public void setPos(double x, double y, double z, float yaw, float pitch) {
        this.syncX = x;
        this.syncY = y;
        this.syncZ = z;
        this.syncYaw = yaw;
        this.syncPitch = pitch;
        this.awaitSync = true;
    }
    @Override // xol.lostinfinity.mob.entity.classify.IRelay
    public double getX() {
        return this.field_70165_t;
    }
    @Override // xol.lostinfinity.mob.entity.classify.IRelay
    public double getY() {
        return this.field_70163_u;
    }
    @Override // xol.lostinfinity.mob.entity.classify.IRelay
    public double getZ() {
        return this.field_70161_v;
    }
    @Override // xol.lostinfinity.mob.entity.classify.IRelay
    public float getYaw() {
        return this.field_70177_z;
    }
    @Override // xol.lostinfinity.mob.entity.classify.IRelay
    public float getPitch() {
        return this.field_70125_A;
    }
    public void func_70071_h_() {
        super.func_70071_h_();
        this.field_70133_I = false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }
    public void func_70030_z() {
        super.func_70030_z();
        if (this.relay.onFinalLife() && this.relay.func_110143_aJ() <= 0.0f) {
            func_70609_aI();
        }
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 20 == 0) {
            PacketSyncParts syncParts = new PacketSyncParts(this);
            LostInfinityPacketHandler.INSTANCE.sendToAllTracking(syncParts, this.relay);
        } else {
            this.field_70737_aN = ((EntityMultipleLives) this.relay).field_70737_aN;
            this.field_70738_aO = ((EntityMultipleLives) this.relay).field_70738_aO;
        }
    }
    public boolean func_70097_a(DamageSource source, float amount) {
        if (func_180431_b(source)) {
            return false;
        }
        if (this.relay instanceof ILostMultiPart) {
            return ((ILostMultiPart) this.relay).attackEntityFromPart(this, source, amount);
        }
        return this.relay.func_70097_a(source, amount);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean didDeathAction() {
        return this.relay.didDeathAction();
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void deathActionComplete() {
        this.relay.deathActionComplete();
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public int getLivesCount() {
        return this.relay.getLivesCount();
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void setLivesCount(int f) {
        this.relay.setLivesCount(f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void takewayLife() {
        this.relay.takewayLife();
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void takeawayNumLives(int lives) {
        this.relay.takeawayNumLives(lives);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70014_b(NBTTagCompound tag) {
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70037_a(NBTTagCompound tag) {
    }
    protected boolean func_70692_ba() {
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70814_o() {
        return this.relay.func_70814_o();
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return this.relay.func_70601_bi();
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean onFinalLife() {
        return this.relay.onFinalLife();
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return this.relay.numberOfLives();
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public int remainingLives() {
        return this.relay.remainingLives();
    }
}
