package xol.lostinfinity.mob.entity.starforge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityCrusher.class */
public class EntityCrusher extends EntityMultipleLives implements IMaxAttack {
    private float speed;
    private boolean movingUpwards;
    private int timer;

    public EntityCrusher(World worldIn) {
        super(worldIn);
        this.speed = 0.0f;
        this.movingUpwards = true;
        this.timer = 100;
        func_70105_a(1.2f, 2.5f);
        func_189654_d(true);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }

    protected void func_82167_n(Entity entityIn) {
        if ((entityIn instanceof EntityPlayer) && this.field_70173_aa % 5 == 0) {
            EntityPlayer play = (EntityPlayer) entityIn;
            if (!play.func_184812_l_()) {
                IMaxAttack.dealMaxHealth(this, play, 3);
            }
        }
        entityIn.func_70108_f(this);
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa % this.timer == 0) {
                reverseDirection();
            }
            if (this.speed < 0.5f) {
                this.speed += 0.02f;
            }
            if (this.movingUpwards) {
                this.field_70181_x = this.speed;
            } else {
                this.field_70181_x = -this.speed;
            }
            this.field_70133_I = true;
        }
    }

    private void reverseDirection() {
        this.movingUpwards = !this.movingUpwards;
        this.speed = 0.0f;
        this.timer = 80 + this.field_70146_Z.nextInt(60);
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.CRUSHER_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.CRUSHER_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.CRUSHER_DEATH;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 3;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }

    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_CRUSHER;
    }
}
