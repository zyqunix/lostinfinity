package xol.lostinfinity.mob.entity.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityRocketStrappedExplosive.class */
public class EntityRocketStrappedExplosive extends Entity {
    private EntityPlayer owner;
    private int timer;
    private double speed;

    public EntityRocketStrappedExplosive(World worldIn) {
        super(worldIn);
        this.owner = null;
        this.timer = 140;
        this.speed = 0.01d;
        func_184224_h(true);
    }

    public void setOwner(EntityPlayer play) {
        this.owner = play;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            if (this.owner == null || this.owner.field_70128_L) {
                func_70106_y();
                return;
            }
            if (this.timer % 10 == 0) {
                func_184185_a(SoundEvents.field_191244_bn, 2.0f, 0.5f + this.field_70146_Z.nextFloat());
            }
            this.speed *= 1.04d;
            this.owner.func_189654_d(true);
            this.owner.field_70181_x = this.speed;
            this.owner.field_70133_I = true;
            func_70634_a(this.owner.field_70165_t, this.owner.field_70163_u + 1.5d, this.owner.field_70161_v);
            this.timer--;
            if (this.timer == 0) {
                this.owner.func_70606_j(0.0f);
                this.owner.func_189654_d(false);
                func_145779_a(ItemInit.velocitizedFemur, 1);
                func_70106_y();
            }
        }
    }

    protected void func_70088_a() {
    }

    protected void func_70037_a(NBTTagCompound compound) {
    }

    protected void func_70014_b(NBTTagCompound compound) {
    }
}
