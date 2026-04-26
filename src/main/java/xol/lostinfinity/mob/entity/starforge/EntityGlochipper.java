package xol.lostinfinity.mob.entity.starforge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityGlochipper.class */
public class EntityGlochipper extends EntityMob implements IMaxAttack, IBasicAI {
    public EntityGlochipper(World worldIn) {
        super(worldIn);
        func_70105_a(0.75f, 0.75f);
    }

    protected void func_184651_r() {
        initBasicTasks(this);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 3);
            return true;
        }
        return false;
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        boolean condition = false;
        if (this.field_70173_aa % 200 < 100) {
            condition = true;
        }
        if (func_70638_az() != null && this.field_70163_u > func_70638_az().field_70163_u) {
            condition = false;
        }
        if (condition) {
            func_189654_d(true);
            this.field_70181_x = 0.05d;
        } else {
            func_189654_d(false);
        }
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.GLOCHIPPER_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.GLOCHIPPER_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.GLOCHIPPER_AMBIENT;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public boolean func_70814_o() {
        return true;
    }

    public int func_70641_bl() {
        return 1;
    }

    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }

    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_GLOCHIPPER;
    }
}
