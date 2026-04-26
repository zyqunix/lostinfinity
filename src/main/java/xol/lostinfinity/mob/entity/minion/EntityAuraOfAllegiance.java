package xol.lostinfinity.mob.entity.minion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.classify.IOwnerReactive;
import xol.lostinfinity.util.data.CustomDamageResult;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/minion/EntityAuraOfAllegiance.class */
public class EntityAuraOfAllegiance extends EntityMinion implements IOwnerReactive {
    private static final DataParameter<Integer> LIVES = EntityDataManager.func_187226_a(EntityAuraOfAllegiance.class, DataSerializers.field_187192_b);
    private static final int MAX_LIVES = 6;

    public EntityAuraOfAllegiance(World worldIn) {
        super(worldIn);
        func_70105_a(0.25f, 0.25f);
    }

    @Override // xol.lostinfinity.mob.entity.minion.EntityMinion
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(LIVES, 1);
    }

    @Override // xol.lostinfinity.mob.entity.minion.EntityMinion
    protected void livingUpdate() {
        EntityPlayer owner = func_70902_q();
        if (owner == null) {
            return;
        }
        updatePosition();
    }

    public void setLives(int lives) {
        this.field_70180_af.func_187227_b(LIVES, Integer.valueOf(MathHelper.func_76125_a(lives, 0, 6)));
    }

    public int getLives() {
        return ((Integer) this.field_70180_af.func_187225_a(LIVES)).intValue();
    }

    public void addLives(int lives) {
        setLives(getLives() + lives);
    }

    public void removeLives(int lives) {
        addLives(-lives);
    }

    private void updatePosition() {
        func_70080_a(this.owner.field_70165_t, this.owner.field_70163_u + 1.0d, this.owner.field_70161_v, 0.0f, 0.0f);
        this.field_70759_as = 0.0f;
        this.field_70761_aq = 0.0f;
    }

    @Override // xol.lostinfinity.mob.entity.classify.IOwnerReactive
    public void trueDamageEffect(Entity attacker, CustomDamageResult result) {
        result.setHitMissed();
        removeLives(1);
        if (getLives() <= 0) {
            func_70106_y();
        }
    }
}
