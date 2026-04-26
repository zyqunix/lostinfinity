package xol.lostinfinity.mob.entity.base;

import com.google.common.base.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.ai.EntityAILookAround;
import xol.lostinfinity.mob.ai.EntityAIRandomFly;
import xol.lostinfinity.mob.ai.FloatMoveHelper;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/base/EntityFloatingBase.class */
public abstract class EntityFloatingBase extends EntityFlyingCustom implements IMob {
    protected int attackCooldown;
    protected int attackGracePeriod;
    private static DataParameter<Optional<UUID>> TargetData = EntityDataManager.func_187226_a(EntityFloatingBase.class, DataSerializers.field_187203_m);

    @Nullable
    protected abstract EntityAIFloatAttack createShootAI();

    public EntityFloatingBase(World worldIn) {
        super(worldIn);
        this.attackCooldown = 0;
        this.attackGracePeriod = 15;
        this.field_70765_h = createMoveHelper();
    }

    public boolean func_145773_az() {
        return true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(64.0d);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        func_184212_Q().func_187214_a(TargetData, Optional.absent());
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(5, new EntityAIRandomFly(this));
        this.field_70714_bg.func_75776_a(7, new EntityAILookAround(this));
        this.field_70715_bh.func_75776_a(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
        EntityAIFloatAttack attack = createShootAI();
        if (attack != null) {
            this.field_70714_bg.func_75776_a(7, attack);
        }
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.attackCooldown > 0) {
            this.attackCooldown--;
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 1;
    }

    public void func_70100_b_(EntityPlayer par1EntityPlayer) {
        if (this.attackCooldown == 0) {
            func_70652_k(par1EntityPlayer);
            this.attackCooldown = this.attackGracePeriod;
        }
    }

    protected void func_82167_n(Entity entityIn) {
        if (entityIn.equals(func_70638_az()) && this.attackCooldown == 0) {
            func_70652_k(entityIn);
            this.attackCooldown = this.attackGracePeriod;
        }
    }

    protected EntityMoveHelper createMoveHelper() {
        return new FloatMoveHelper(this);
    }
}
