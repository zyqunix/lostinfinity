package xol.lostinfinity.mob.entity.murk;
import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityCaveTerror extends EntityMultipleLives implements IMaxAttack, IBasicAI {
    public EntityCaveTerror(World worldIn) {
        super(worldIn);
        func_70105_a(2.25f, 4.0f);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth((Entity) this, func_70638_az(), 1, (List<String>) Arrays.asList("Darkborn"));
            return true;
        }
        return false;
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(3000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.CAVE_TERROR_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.CAVE_TERROR_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.CAVE_TERROR_AMBIENT;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 15;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return super.func_70601_bi() && this.field_70163_u < 40.0d;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_CAVETERROR;
    }
}
