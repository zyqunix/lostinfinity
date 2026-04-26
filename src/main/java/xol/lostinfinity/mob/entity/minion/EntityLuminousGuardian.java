package xol.lostinfinity.mob.entity.minion;

import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.projectile.entity.EntityLuminousGuardianLaser;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/minion/EntityLuminousGuardian.class */
public class EntityLuminousGuardian extends EntityMinion {
    public EntityLuminousGuardian(World worldIn) {
        super(worldIn);
        func_70105_a(0.5f, 0.5f);
    }

    @Override // xol.lostinfinity.mob.entity.minion.EntityMinion
    protected void livingUpdate() {
        EntityPlayer owner = func_70902_q();
        if (owner == null) {
            return;
        }
        updatePosition();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 20 == 0) {
            ArrayList<EntityLivingBase> nearEntities = new ArrayList<>();
            for (EntityLivingBase entity : this.field_70170_p.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(func_180425_c()).func_186662_g(15.0d))) {
                if (!entity.func_110124_au().equals(owner.func_110124_au()) && !entity.func_110124_au().equals(func_110124_au()) && !(entity instanceof EntityImmaterial)) {
                    nearEntities.add(entity);
                }
            }
            if (!nearEntities.isEmpty()) {
                Collections.shuffle(nearEntities);
                EntityLivingBase target = nearEntities.get(0);
                EntityLuminousGuardianLaser laser = new EntityLuminousGuardianLaser(this.field_70170_p);
                laser.setOwnerID(func_145782_y());
                if (this.owner != null) {
                    laser.setPlayerOwner(this.owner);
                }
                laser.setOwner(this);
                laser.setTargetPos(target.func_174791_d());
                laser.func_70634_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                this.field_70170_p.func_72838_d(laser);
            }
        }
    }

    private void updatePosition() {
        float x = MathHelper.func_76126_a(this.field_70173_aa * 0.01f);
        float y = MathHelper.func_76126_a(this.field_70173_aa * 0.05f) * 0.5f;
        float z = MathHelper.func_76134_b(this.field_70173_aa * 0.01f);
        func_70080_a(this.owner.field_70165_t + ((double) x), this.owner.field_70163_u + 2.0d + ((double) y), this.owner.field_70161_v + ((double) z), 0.0f, 0.0f);
        this.field_70759_as = 0.0f;
        this.field_70761_aq = 0.0f;
    }
}
