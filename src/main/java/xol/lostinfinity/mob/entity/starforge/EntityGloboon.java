package xol.lostinfinity.mob.entity.starforge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.entity.Entity;
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
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityGloboon.class */
public class EntityGloboon extends EntityMob implements IMaxAttack {
    private static final DataParameter<Float> GMOVE_X = EntityDataManager.func_187226_a(EntityGloboon.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> GMOVE_Y = EntityDataManager.func_187226_a(EntityGloboon.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> GMOVE_Z = EntityDataManager.func_187226_a(EntityGloboon.class, DataSerializers.field_187193_c);
    private List<Integer> tickOffsets;

    public EntityGloboon(World worldIn) {
        super(worldIn);
        this.tickOffsets = new ArrayList();
        func_70105_a(1.2f, 2.2f);
        populateOffsets();
        func_189654_d(true);
    }

    protected void func_184651_r() {
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(GMOVE_X, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(GMOVE_Y, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(GMOVE_Z, Float.valueOf(0.0f));
    }

    public float getXMovement() {
        return ((Float) this.field_70180_af.func_187225_a(GMOVE_X)).floatValue();
    }

    public float getYMovement() {
        return ((Float) this.field_70180_af.func_187225_a(GMOVE_Y)).floatValue();
    }

    public float getZMovement() {
        return ((Float) this.field_70180_af.func_187225_a(GMOVE_Z)).floatValue();
    }

    private void randomizeMovement() {
        float xrand = ((-1.0f) + (2.0f * this.field_70146_Z.nextFloat())) * 0.1f;
        float yrand = ((-1.0f) + (2.0f * this.field_70146_Z.nextFloat())) * 0.1f;
        float zrand = ((-1.0f) + (2.0f * this.field_70146_Z.nextFloat())) * 0.1f;
        this.field_70180_af.func_187227_b(GMOVE_X, Float.valueOf(xrand));
        this.field_70180_af.func_187227_b(GMOVE_Y, Float.valueOf(yrand));
        this.field_70180_af.func_187227_b(GMOVE_Z, Float.valueOf(zrand));
    }

    protected void func_82167_n(Entity entityIn) {
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer play = (EntityPlayer) entityIn;
            if (!play.func_184812_l_()) {
                IMaxAttack.dealMaxHealth(this, play, 1);
            }
        }
        entityIn.func_70108_f(this);
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 80 == 3) {
            randomizeMovement();
        }
        this.field_70159_w = getXMovement();
        this.field_70181_x = getYMovement();
        this.field_70179_y = getZMovement();
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    private void populateOffsets() {
        for (int i = 1; i <= 10; i++) {
            this.tickOffsets.add(Integer.valueOf(i));
        }
        Collections.shuffle(this.tickOffsets);
    }

    public int getLegOffset(int index) {
        try {
            return this.tickOffsets.get(index).intValue() * 3;
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return 0;
        }
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.GLOBOON_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.GLOBOON_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.GLOBOON_AMBIENT;
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
        return LootTableRegistry.ENTITIES_GLOBOON;
    }
}
