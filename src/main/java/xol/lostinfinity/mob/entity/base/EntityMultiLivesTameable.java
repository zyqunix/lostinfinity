package xol.lostinfinity.mob.entity.base;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
public class EntityMultiLivesTameable extends EntityTameable {
    private static final DataParameter<Integer> LIFECOUNT_TAMEABLE = EntityDataManager.func_187226_a(EntityMultiLivesTameable.class, DataSerializers.field_187192_b);
    private boolean performedDeathAction;
    public EntityMultiLivesTameable(World worldIn) {
        super(worldIn);
        this.performedDeathAction = false;
    }
    public boolean didDeathAction() {
        return this.performedDeathAction;
    }
    public void deathActionComplete() {
        this.performedDeathAction = true;
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(LIFECOUNT_TAMEABLE, 0);
    }
    public int getLivesCount() {
        return ((Integer) this.field_70180_af.func_187225_a(LIFECOUNT_TAMEABLE)).intValue();
    }
    public void setLivesCount(int f) {
        this.field_70180_af.func_187227_b(LIFECOUNT_TAMEABLE, Integer.valueOf(f));
    }
    public void takewayLife() {
        this.field_70180_af.func_187227_b(LIFECOUNT_TAMEABLE, Integer.valueOf(getLivesCount() + 1));
        updateLifeAction();
    }
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74768_a("LivesUsed", getLivesCount());
    }
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setLivesCount(tag.func_74762_e("LivesUsed"));
    }
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }
    public boolean onFinalLife() {
        return getLivesCount() == numberOfLives();
    }
    protected int numberOfLives() {
        return 10;
    }
    protected void updateLifeAction() {
    }
    public void trueDeathAction() {
    }
    public EntityAgeable func_90011_a(EntityAgeable ageable) {
        return null;
    }
    public void func_70645_a(DamageSource cause) {
        if (onFinalLife()) {
            super.func_70645_a(cause);
        }
    }
}
