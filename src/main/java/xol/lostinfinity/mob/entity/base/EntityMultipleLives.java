package xol.lostinfinity.mob.entity.base;
import java.util.Iterator;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketAnimation;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.mob.ai.IBasicAI;
public class EntityMultipleLives extends EntityMob implements IBasicAI {
    private static final DataParameter<Integer> LIFECOUNT = EntityDataManager.func_187226_a(EntityMultipleLives.class, DataSerializers.field_187192_b);
    private boolean performedDeathAction;
    public EntityMultipleLives(World worldIn) {
        super(worldIn);
        this.performedDeathAction = false;
    }
    protected void func_184651_r() {
        initBasicTasks(this);
    }
    public boolean didDeathAction() {
        return this.performedDeathAction;
    }
    public void deathActionComplete() {
        this.performedDeathAction = true;
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(LIFECOUNT, 0);
    }
    public int getLivesCount() {
        return ((Integer) this.field_70180_af.func_187225_a(LIFECOUNT)).intValue();
    }
    public void setLivesCount(int f) {
        this.field_70180_af.func_187227_b(LIFECOUNT, Integer.valueOf(f));
    }
    public void takewayLife() {
        this.field_70180_af.func_187227_b(LIFECOUNT, Integer.valueOf(getLivesCount() + 1));
        updateLifeAction();
        doDamageTint();
    }
    public void takeawayNumLives(int lives) {
        for (int i = 0; i < lives && !didDeathAction(); i++) {
            func_70606_j(0.0f);
            func_70645_a(DamageSource.field_76376_m);
        }
    }
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74768_a("LivesUsed", getLivesCount());
    }
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setLivesCount(tag.func_74762_e("LivesUsed"));
    }
    public boolean func_70814_o() {
        return true;
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
    public int remainingLives() {
        return numberOfLives() - getLivesCount();
    }
    protected void updateLifeAction() {
    }
    public void trueDeathAction() {
    }
    protected boolean nothingInRadius(int i) {
        Iterator it = this.field_70170_p.func_72872_a(EntityMultipleLives.class, func_174813_aQ().func_186662_g(i)).iterator();
        if (it.hasNext()) {
            return false;
        }
        return true;
    }
    protected void doDamageTint() {
        if (!this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_73039_n().func_151247_a(this, new SPacketAnimation(this, 1));
        } else {
            func_70057_ab();
        }
    }
}
