package xol.lostinfinity.item.basics;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import xol.lostinfinity.item.classify.IHeldTick;
import xol.lostinfinity.util.Reference;
public class ItemChanneling extends ItemCooldown implements IHeldTick {
    protected static final String CHARGE_STATE = "charging";
    protected static final String CHARGE = "charge";
    protected float maxChargeTime;
    public ItemChanneling(String regName) {
        super(regName);
        this.maxChargeTime = 20.0f;
        func_185043_a(new ResourceLocation(Reference.MODID, CHARGE_STATE), this::getChargeState);
        func_185043_a(new ResourceLocation(Reference.MODID, CHARGE), this::getChargeRatio);
    }
    public void func_77615_a(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        chargeStop(stack, worldIn, entityLiving, func_77626_a(stack) - timeLeft);
    }
    public int func_77626_a(ItemStack stack) {
        return 72000;
    }
    public EnumAction func_77661_b(ItemStack stack) {
        return EnumAction.BOW;
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            playerIn.func_184598_c(handIn);
            return chargeStart(worldIn, playerIn, handIn, stack);
        }
        return new ActionResult<>(EnumActionResult.FAIL, stack);
    }
    @Override // xol.lostinfinity.item.classify.IHeldTick
    public void heldTick(EntityPlayer player, EnumHand hand, ItemStack stack) {
        if (player.func_184607_cu() != stack) {
            return;
        }
        chargeTick(player.field_70170_p, player, hand, stack, player.func_184612_cw());
    }
    public float getMaxChargeTime() {
        return this.maxChargeTime;
    }
    public void setMaxChargeTime(int tick) {
        this.maxChargeTime = tick;
    }
    public ActionResult<ItemStack> chargeStart(World worldIn, EntityPlayer player, EnumHand handIn, ItemStack stack) {
        return new ActionResult<>(EnumActionResult.PASS, stack);
    }
    public void chargeTick(World worldIn, EntityPlayer player, EnumHand hand, ItemStack stack, int chargeTime) {
    }
    public void chargeStop(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int chargeTime) {
        if (chargeTime >= this.maxChargeTime) {
            startCooldown(stack);
        }
    }
    public float updateFOV(EntityPlayer player, ItemStack stack, float original) {
        float chargeRatio;
        float chargeRatio2 = player.func_184612_cw() / 20.0f;
        if (chargeRatio2 > 1.0f) {
            chargeRatio = 1.0f;
        } else {
            chargeRatio = chargeRatio2 * chargeRatio2;
        }
        return original * (1.0f - (chargeRatio * 0.15f));
    }
    protected float getChargeState(ItemStack stack, World world, EntityLivingBase entityLivingBase) {
        return isChanneling(entityLivingBase, stack) ? 1.0f : 0.0f;
    }
    protected float getChargeRatio(ItemStack stack, World world, EntityLivingBase entityLivingBase) {
        if (entityLivingBase == null || stack.func_190926_b() || entityLivingBase.func_184607_cu().func_77973_b() != this) {
            return 0.0f;
        }
        return entityLivingBase.func_184612_cw() / this.maxChargeTime;
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 0;
    }
    public static boolean isChanneling(EntityLivingBase entityLivingBase, ItemStack stack) {
        return entityLivingBase != null && !stack.func_190926_b() && stack.func_77942_o() && entityLivingBase.func_184587_cr() && stack.func_77969_a(entityLivingBase.func_184607_cu());
    }
}
