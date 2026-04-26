package xol.lostinfinity.mob.entity.starforge;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingBase;
import xol.lostinfinity.projectile.entity.EntityBeeAttack;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityFlutterbee extends EntityFloatingBase implements IMaxAttack {
    public EntityFlutterbee(World worldIn) {
        super(worldIn);
        func_70105_a(1.0f, 1.0f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1750.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.FLUTTERBEE_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.FLUTTERBEE_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.FLUTTERBEE_AMBIENT;
    }
    public void shootMiner(BlockPos pos) {
        func_184185_a(SoundInit.FLUTTERBEE_FIRE, 1.0f, 0.7f + (this.field_70146_Z.nextFloat() * 0.6f));
        EntityBeeAttack shot = new EntityBeeAttack(this.field_70170_p, this);
        double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
        double makeY = this.field_70163_u + ((double) (this.field_70131_O / 2.0f)) + 0.5d;
        double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
        double d2 = ((double) pos.func_177958_n()) - makeX;
        double d3 = ((double) pos.func_177956_o()) - makeY;
        double d4 = ((double) pos.func_177952_p()) - makeZ;
        shot.func_70186_c(d2, d3, d4, 0.8f, 0.0f);
        this.field_70170_p.func_72838_d(shot);
    }
    protected boolean func_70692_ba() {
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        super.func_70636_d();
    }
    public int func_70641_bl() {
        return 1;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    protected EntityAIFloatAttack createShootAI() {
        return null;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_FLUTTERBEE;
    }
}
