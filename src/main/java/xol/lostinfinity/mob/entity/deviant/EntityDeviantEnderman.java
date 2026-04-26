package xol.lostinfinity.mob.entity.deviant;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.base.EntityDeviantMob;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/deviant/EntityDeviantEnderman.class */
public class EntityDeviantEnderman extends EntityDeviantMob {
    public EntityDeviantEnderman(World worldIn) {
        super(worldIn);
        func_70105_a(2.5f, 8.0f);
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.378d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0d);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 8 - (2 * getMutation()));
            return true;
        }
        return false;
    }

    protected Entity findPlayerToAttack() {
        EntityPlayer entityPlayer = this.field_70170_p.func_72890_a(this, 16.0d);
        if (entityPlayer == null || !func_70685_l(entityPlayer)) {
            return null;
        }
        return entityPlayer;
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187530_aT;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187531_aU;
    }

    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187529_aS;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item playerInput() {
        return ItemInit.cursedEmerald;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item mutantOutput() {
        return ItemInit.lifeboundEmerald;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70173_aa % (120 + (20 * getMutation())) == 0) {
            if (this.field_70170_p.field_72995_K) {
                for (int i = 0; i < 14; i++) {
                    this.field_70170_p.func_175688_a(EnumParticleTypes.SPELL_WITCH, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
                }
            }
            this.field_70170_p.func_184134_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, SoundEvents.field_187534_aX, SoundCategory.HOSTILE, 1.5f, 0.5f + this.field_70170_p.field_73012_v.nextFloat(), true);
            func_70690_d(new PotionEffect(MobEffects.field_76441_p, 40 + (20 * getMutation())));
            BlockPos pos = func_180425_c().func_177977_b();
            IBlockState block = this.field_70170_p.func_180495_p(pos);
            if (!this.field_70170_p.field_72995_K && (block.func_177230_c().equals(Blocks.field_150450_ax) || block.func_177230_c().equals(Blocks.field_150439_ay))) {
                this.field_70170_p.func_72838_d(new EntityItem(this.field_70170_p, pos.func_177958_n(), pos.func_177956_o() + 1, pos.func_177952_p(), new ItemStack(ItemInit.ghostlyRedstone)));
                this.field_70170_p.func_175698_g(pos);
            }
        }
        if (func_82150_aj() && this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_175688_a(EnumParticleTypes.SPELL, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N) * 3.0d), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N) * 3.0d), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation deviantDrop() {
        return LootTableRegistry.ENTITIES_DEVIANTENDERMAN;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation superMutatedDrop() {
        return LootTableRegistry.ENTITIES_SUPERMUTANT_ENDERMAN;
    }
}
