package xol.lostinfinity.mob.entity.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DimensionType;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.mob.entity.deviant.prime.EntityAzross;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/base/EntityDeviantMob.class */
public class EntityDeviantMob extends EntityMultipleLives implements IMaxAttack, IBasicAI {
    private static final DataParameter<Integer> MUTATION = EntityDataManager.func_187226_a(EntityDeviantMob.class, DataSerializers.field_187192_b);

    public EntityDeviantMob(World worldIn) {
        super(worldIn);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        initBasicTasks(this);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(MUTATION, 0);
    }

    public int getMutation() {
        return ((Integer) this.field_70180_af.func_187225_a(MUTATION)).intValue();
    }

    public void setMutation(int f) {
        this.field_70180_af.func_187227_b(MUTATION, Integer.valueOf(f));
    }

    public boolean atMaxMutation() {
        return getMutation() == 3;
    }

    public void increaseMutation() {
        setMutation(getMutation() + 1);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74768_a("Mutation", getMutation());
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setMutation(tag.func_74762_e("Mutation"));
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return getMutation() == 0 ? 2 : 5;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70814_o() {
        return true;
    }

    public int func_70641_bl() {
        return 1;
    }

    public boolean func_180427_aV() {
        return true;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }

    protected boolean func_70692_ba() {
        int time;
        return this.field_70170_p.field_73011_w.func_186058_p() == DimensionType.OVERWORLD && (time = (int) (this.field_70170_p.func_72820_D() % 24000)) > 13000 && time < 18000;
    }

    public void updateSupermutationAI() {
    }

    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.func_184586_b(hand);
        if (playerInput() != null && atMaxMutation() && itemstack.func_77973_b().equals(playerInput())) {
            func_184185_a(SoundEvents.field_187626_cN, 2.0f, 1.0f);
            if (!this.field_70170_p.field_72995_K) {
                func_145779_a(mutantOutput(), 1);
                setMutation(1);
                updateSupermutationAI();
            }
            itemstack.func_190918_g(1);
            return true;
        }
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K && this.field_70146_Z.nextInt(20) == 10 && this.field_70170_p.field_73011_w.func_186058_p() == DimensionType.OVERWORLD) {
            EntityAzross azross = new EntityAzross(this.field_70170_p);
            azross.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.field_70170_p.func_72838_d(azross);
            this.field_70170_p.func_175739_a(EnumParticleTypes.DRAGON_BREATH, this.field_70165_t, this.field_70163_u + 1.0d, this.field_70161_v, 10, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(25.0d, 25.0d, 25.0d))) {
                near_pl.func_145747_a(new TextComponentString(TextFmt.Dark_Aqua + "Prime Deviant Azross: Stop killing my kind!"));
            }
        }
    }

    protected Item playerInput() {
        return null;
    }

    protected Item mutantOutput() {
        return null;
    }

    protected ResourceLocation deviantDrop() {
        return null;
    }

    protected ResourceLocation superMutatedDrop() {
        return null;
    }

    protected ResourceLocation func_184647_J() {
        return atMaxMutation() ? superMutatedDrop() : deviantDrop();
    }
}
