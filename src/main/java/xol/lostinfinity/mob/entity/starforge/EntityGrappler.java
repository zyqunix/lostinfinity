package xol.lostinfinity.mob.entity.starforge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityGrappler.class */
public class EntityGrappler extends EntityMob implements IMaxAttack {
    private static final DataParameter<Integer> MOVE_STYLE = EntityDataManager.func_187226_a(EntityGrappler.class, DataSerializers.field_187192_b);

    public EntityGrappler(World worldIn) {
        super(worldIn);
        func_70105_a(1.7f, 2.2f);
        func_189654_d(true);
        this.field_70178_ae = true;
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(MOVE_STYLE, 0);
    }

    public int getMoveStyle() {
        return ((Integer) this.field_70180_af.func_187225_a(MOVE_STYLE)).intValue();
    }

    public void setMoveStyle(int f) {
        this.field_70180_af.func_187227_b(MOVE_STYLE, Integer.valueOf(f));
    }

    private void randomizeMovement() {
        int style = getMoveStyle();
        boolean run = true;
        int new_style = 0;
        while (run) {
            new_style = this.field_70146_Z.nextInt(6);
            if (new_style != style) {
                run = false;
            }
        }
        setMoveStyle(new_style);
    }

    protected void func_184651_r() {
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1200.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    protected void func_82167_n(Entity entityIn) {
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer play = (EntityPlayer) entityIn;
            if (!play.func_184812_l_()) {
                IMaxAttack.dealMaxHealth((Entity) this, (EntityLivingBase) play, 3, 2.0f);
            }
        }
        entityIn.func_70108_f(this);
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 120 == 0) {
            randomizeMovement();
            func_184185_a(SoundInit.STARFORGE_GRAPPLER_AMBIENT, 2.0f, 1.0f);
        }
        switch (getMoveStyle()) {
            case 0:
                this.field_70181_x = 0.25d;
                this.field_70159_w *= 0.95d;
                this.field_70179_y *= 0.95d;
                break;
            case 1:
                this.field_70181_x = -0.25d;
                this.field_70159_w *= 0.95d;
                this.field_70179_y *= 0.95d;
                break;
            case 2:
                this.field_70159_w = 0.25d;
                this.field_70181_x *= 0.95d;
                this.field_70179_y *= 0.95d;
                break;
            case 3:
                this.field_70159_w = -0.25d;
                this.field_70181_x *= 0.95d;
                this.field_70179_y *= 0.95d;
                break;
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                this.field_70179_y = 0.25d;
                this.field_70181_x *= 0.95d;
                this.field_70159_w *= 0.95d;
                break;
            case 5:
                this.field_70179_y = -0.25d;
                this.field_70181_x *= 0.95d;
                this.field_70159_w *= 0.95d;
                break;
        }
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.STARFORGE_GRAPPLER_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.STARFORGE_GRAPPLER_HURT;
    }

    protected SoundEvent func_184639_G() {
        return null;
    }

    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_STARFORGE_GRAPPLER;
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
}
