package xol.lostinfinity.mob.entity.deviant;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.base.EntityDeviantMob;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityDeviantCreeper extends EntityDeviantMob {
    private boolean animationState;
    private boolean cageVisible;
    public EntityDeviantCreeper(World worldIn) {
        super(worldIn);
        this.animationState = false;
        this.cageVisible = false;
        func_70105_a(2.5f, 4.0f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    public boolean func_180427_aV() {
        return true;
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(700.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 8);
            return true;
        }
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item playerInput() {
        return ItemInit.mechanicalPowerCell;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item mutantOutput() {
        return ItemInit.superchargedCell;
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (this.animationState) {
            if (this.field_70173_aa % (3 - Math.floorDiv(getMutation(), 2)) == 0) {
                this.cageVisible = !this.cageVisible;
            }
            if (this.field_70173_aa % (50 - (getMutation() * 10)) == 0) {
                this.cageVisible = false;
                this.animationState = false;
                if (!this.field_70170_p.field_72995_K) {
                    this.field_70170_p.func_72876_a((Entity) null, this.field_70165_t, this.field_70163_u, this.field_70161_v, 7.0f + (8 * getMutation()), false);
                    for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(4.0d + ((double) (4 * getMutation())), 4.0d + ((double) (4 * getMutation())), 4.0d + ((double) (4 * getMutation()))))) {
                        IMaxAttack.dealMaxHealth(this, near_pl, 3);
                    }
                }
                BlockPos pos = func_180425_c().func_177977_b();
                IBlockState block = this.field_70170_p.func_180495_p(pos);
                if (!this.field_70170_p.field_72995_K && block.func_177230_c().equals(Blocks.field_150449_bY)) {
                    this.field_70170_p.func_72838_d(new EntityItem(this.field_70170_p, pos.func_177958_n(), pos.func_177956_o() + 1, pos.func_177952_p(), new ItemStack(ItemInit.electrifiedQuartz)));
                    this.field_70170_p.func_175698_g(pos);
                    return;
                }
                return;
            }
            return;
        }
        if (this.field_70173_aa % (100 - (30 * getMutation())) == 0) {
            this.animationState = true;
            func_184185_a(SoundEvents.field_187572_ar, 2.0f, 1.0f);
        }
    }
    public boolean getCageVisible() {
        return this.cageVisible;
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187568_ap;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187570_aq;
    }
    protected SoundEvent func_184639_G() {
        return null;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation deviantDrop() {
        return LootTableRegistry.ENTITIES_DEVIANTCREEPER;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation superMutatedDrop() {
        return LootTableRegistry.ENTITIES_SUPERMUTANT_CREEPER;
    }
}
