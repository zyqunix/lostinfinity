package xol.lostinfinity.mob.entity.deviant;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.base.EntityDeviantMob;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityDeviantZombie extends EntityDeviantMob implements IMaxAttack {
    private boolean isSummoner;
    public EntityDeviantZombie(World worldIn) {
        super(worldIn);
        this.isSummoner = true;
        func_70105_a(2.0f, 3.0f);
    }
    protected void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.5d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.43200000000000005d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(400.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 7 - getMutation());
            return true;
        }
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item playerInput() {
        return Items.field_151055_y;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item mutantOutput() {
        return ItemInit.pluckedEye;
    }
    public void setNotSummoner() {
        this.isSummoner = false;
    }
    public boolean isSummoner() {
        return this.isSummoner;
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % (40 - (getMutation() * 6)) == 0 && this.isSummoner && func_70638_az() != null) {
            EntityDeviantZombie zombie = new EntityDeviantZombie(this.field_70170_p);
            zombie.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            zombie.field_70181_x = 0.3d;
            zombie.setNotSummoner();
            zombie.setMutation(getMutation());
            this.field_70170_p.func_72838_d(zombie);
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187930_hd;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187934_hh;
    }
    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187899_gZ;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation deviantDrop() {
        return LootTableRegistry.ENTITIES_DEVIANTZOMBIE;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation superMutatedDrop() {
        return LootTableRegistry.ENTITIES_SUPERMUTANT_ZOMBIE;
    }
}
