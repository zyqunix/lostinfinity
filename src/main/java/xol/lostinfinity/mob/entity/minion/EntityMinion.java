package xol.lostinfinity.mob.entity.minion;

import com.google.common.base.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.common.special.CommonMinionHandler;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/minion/EntityMinion.class */
public class EntityMinion extends EntityImmaterial implements IEntityOwnable {
    private static final DataParameter<Optional<UUID>> OWNER_UUID = EntityDataManager.func_187226_a(EntityMinion.class, DataSerializers.field_187203_m);
    private static final DataParameter<Boolean> ACTIVE = EntityDataManager.func_187226_a(EntityMinion.class, DataSerializers.field_187198_h);
    protected EntityPlayer owner;
    protected EnumHand hand;
    protected ItemStack trackedItemStack;
    protected int lastSeenSlot;

    public EntityMinion(World worldIn) {
        super(worldIn);
        this.field_70145_X = true;
    }

    public void setLastSlot(int slot) {
        this.lastSeenSlot = slot;
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(OWNER_UUID, Optional.absent());
        this.field_70180_af.func_187214_a(ACTIVE, false);
    }

    public void func_70106_y() {
        super.func_70106_y();
        onDeath();
        Optional<UUID> uuid = (Optional) this.field_70180_af.func_187225_a(OWNER_UUID);
        if (uuid.isPresent()) {
            CommonMinionHandler.unregisterMinion((UUID) uuid.get(), this);
        }
    }

    public void setDeadNoTrigger() {
        super.func_70106_y();
        onDeath();
    }

    protected void onDeath() {
    }

    protected void func_184231_a(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
    }

    public void func_180430_e(float distance, float damageMultiplier) {
    }

    public boolean func_70112_a(double distance) {
        return true;
    }

    public void setOwner(EntityPlayer player) {
        this.owner = player;
        this.field_70180_af.func_187227_b(OWNER_UUID, Optional.of(this.owner.func_110124_au()));
        CommonMinionHandler.registerMinion(player.func_110124_au(), this);
    }

    public void setHand(EnumHand hand) {
        this.hand = hand;
    }

    @Nullable
    public UUID func_184753_b() {
        return (UUID) ((Optional) this.field_70180_af.func_187225_a(OWNER_UUID)).orNull();
    }

    @Nullable
    /* JADX INFO: renamed from: getOwner, reason: merged with bridge method [inline-methods] */
    public EntityPlayer func_70902_q() {
        if (this.owner == null) {
            Optional<UUID> ownerId = (Optional) this.field_70180_af.func_187225_a(OWNER_UUID);
            if (ownerId.isPresent()) {
                this.owner = this.field_70170_p.func_152378_a((UUID) ownerId.get());
            }
        }
        return this.owner;
    }

    public void setActive(boolean flag) {
        this.field_70180_af.func_187227_b(ACTIVE, Boolean.valueOf(flag));
    }

    public boolean isActive() {
        return ((Boolean) this.field_70180_af.func_187225_a(ACTIVE)).booleanValue();
    }

    public void setTrackedItemStack(ItemStack stack) {
        this.trackedItemStack = stack;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        if (!this.field_70170_p.field_72995_K && (func_70902_q() == null || this.owner.field_70170_p != this.field_70170_p || this.owner.field_70128_L || !isStillHoldingItem())) {
            func_70106_y();
        } else {
            super.func_70636_d();
            livingUpdate();
        }
    }

    protected void livingUpdate() {
    }

    public boolean func_70075_an() {
        return false;
    }

    public void func_180426_a(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        super.func_180426_a(x, y, z, yaw, pitch, 1, teleport);
    }

    public boolean func_190631_cK() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldRender() {
        return (!isActive() && func_70902_q() == Minecraft.func_71410_x().field_71439_g && Minecraft.func_71410_x().field_71474_y.field_74320_O == 0) ? false : true;
    }

    protected boolean isStillHoldingItem() {
        if (this.owner == null) {
            return false;
        }
        ItemStack stack = this.owner.field_71071_by.func_70301_a(this.lastSeenSlot);
        if (this.trackedItemStack.func_77969_a(stack)) {
            this.trackedItemStack = stack;
            return true;
        }
        this.lastSeenSlot = getSlotFor(this.owner.field_71071_by, this.trackedItemStack);
        if (this.lastSeenSlot != -1) {
            this.trackedItemStack = this.owner.field_71071_by.func_70301_a(this.lastSeenSlot);
            return true;
        }
        return false;
    }

    protected double getRandomDouble(double mul) {
        return ((-0.5d) + ((double) this.field_70146_Z.nextFloat())) * mul;
    }

    protected boolean validateTarget(Entity input) {
        if (!(input instanceof EntityLivingBase) || (input instanceof EntityImmaterial) || input == func_70902_q() || input.field_70128_L || ((EntityLivingBase) input).func_110143_aJ() <= 0.0f) {
            return false;
        }
        return input instanceof EntityPlayer ? (((EntityPlayer) input).func_184812_l_() || ((EntityPlayer) input).func_175149_v()) ? false : true : ((input instanceof IEntityOwnable) && ((IEntityOwnable) input).func_70902_q() == func_70902_q()) ? false : true;
    }

    private int getSlotFor(InventoryPlayer inventoryPlayer, ItemStack stack) {
        for (int i = 0; i < inventoryPlayer.field_70462_a.size(); i++) {
            if (!((ItemStack) inventoryPlayer.field_70462_a.get(i)).func_190926_b() && stack.equals(inventoryPlayer.field_70462_a.get(i))) {
                return i;
            }
        }
        return -1;
    }
}
