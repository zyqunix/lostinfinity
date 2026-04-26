package xol.lostinfinity.mob.entity.misc;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntitySlimeStrider extends EntityMob implements IMaxAttack, IBasicAI {
    public EntitySlimeStrider(World worldIn) {
        super(worldIn);
        func_70105_a(1.5f, 0.5f);
    }
    protected void func_184651_r() {
        initBasicTasks(this);
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.378d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 5);
            return true;
        }
        return false;
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187874_fm;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187880_fp;
    }
    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187886_fs;
    }
    public boolean func_70814_o() {
        return true;
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -0.5f;
        float scl = 1.8f + (0.5f * MathHelper.func_76126_a(this.field_70173_aa * 0.05f));
        func_70105_a(1.1f + scl, 0.2f + scl);
        if (!this.field_70171_ac || func_70638_az() == null) {
            return;
        }
        if (func_70638_az().field_70163_u > this.field_70163_u) {
            this.field_70181_x = 0.25d;
            this.field_70133_I = true;
        }
        if (this.field_70159_w > -1.600000023841858d && this.field_70159_w < 1.600000023841858d) {
            this.field_70159_w *= 1.2999999523162842d;
            this.field_70133_I = true;
        }
        if (this.field_70179_y > -1.600000023841858d && this.field_70179_y < 1.600000023841858d) {
            this.field_70179_y *= 1.2999999523162842d;
            this.field_70133_I = true;
        }
    }
    public boolean func_70648_aU() {
        return true;
    }
    protected boolean func_70692_ba() {
        int time = (int) (this.field_70170_p.func_72820_D() % 24000);
        return time > 13000 && time < 18000;
    }
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL && this.field_70163_u > 45.0d && this.field_70163_u < ((double) this.field_70170_p.func_181545_F());
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_SLIMESTRIDER;
    }
}
