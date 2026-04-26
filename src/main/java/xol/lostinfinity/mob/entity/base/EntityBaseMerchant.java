package xol.lostinfinity.mob.entity.base;

import javax.annotation.Nullable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/base/EntityBaseMerchant.class */
public class EntityBaseMerchant extends EntityCreature implements INpc, IMerchant {
    private EntityPlayer buyingPlayer;
    private MerchantRecipeList buyingList;

    public EntityBaseMerchant(World worldIn) {
        super(worldIn);
    }

    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0f, 1.0f));
    }

    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187910_gj;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187912_gl;
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187911_gk;
    }

    public void func_70636_d() {
        super.func_70636_d();
    }

    public boolean func_70104_M() {
        return false;
    }

    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        if (func_70089_S() && !isTrading() && !player.func_70093_af()) {
            if (this.buyingList == null) {
                this.buyingList = getRecipeList();
            }
            if (!this.field_70170_p.field_72995_K && !this.buyingList.isEmpty()) {
                func_70932_a_(player);
                player.func_180472_a(this);
                return true;
            }
            if (this.buyingList.isEmpty()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0d);
        func_184224_h(true);
    }

    public MerchantRecipeList getRecipeList() {
        MerchantRecipeList list = new MerchantRecipeList();
        return list;
    }

    public int func_70641_bl() {
        return 1;
    }

    public boolean func_70692_ba() {
        return false;
    }

    public boolean func_70601_bi() {
        return false;
    }

    public void func_70932_a_(@Nullable EntityPlayer player) {
        this.buyingPlayer = player;
    }

    @Nullable
    public EntityPlayer func_70931_l_() {
        return this.buyingPlayer;
    }

    public boolean isTrading() {
        return this.buyingPlayer != null;
    }

    @SideOnly(Side.CLIENT)
    public void func_70930_a(@Nullable MerchantRecipeList recipeList) {
    }

    public void func_70933_a(MerchantRecipe recipe) {
    }

    public void func_110297_a_(ItemStack var1) {
    }

    @Nullable
    public MerchantRecipeList func_70934_b(EntityPlayer player) {
        if (this.buyingList == null) {
            this.buyingList = getRecipeList();
        }
        return this.buyingList;
    }

    public World func_190670_t_() {
        return this.field_70170_p;
    }

    public BlockPos func_190671_u_() {
        return func_180425_c();
    }
}
