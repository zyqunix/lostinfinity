package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.packets.clientbound.PacketHeadHunterUpdate;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.item.classify.IHeldTick;
import xol.lostinfinity.util.data.IMaxAttack;
public class ItemHeadHunter extends ItemCooldown implements ICustomHoldPose, IHeldTick {
    public ItemHeadHunter(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                Vec3d eye = playerIn.func_174824_e(1.0f);
                Vec3d dir = playerIn.func_70040_Z();
                for (EntityPlayer player : worldIn.func_73046_m().func_184103_al().func_181057_v()) {
                    if (player != playerIn) {
                        Vec3d delta = eye.func_178788_d(player.func_174824_e(1.0f));
                        double lSqr = delta.func_189985_c();
                        if (lSqr <= 250000.0d) {
                            AxisAlignedBB closerBB = player.func_174813_aQ();
                            double distance = lSqr * MathHelper.func_181161_i(lSqr);
                            if (distance > 5.0d) {
                                Vec3d closer = delta.func_186678_a((distance - 5.0d) / distance);
                                closerBB = closerBB.func_191194_a(closer);
                                distance = 5.0d;
                            }
                            Vec3d tracePoint = eye.func_178787_e(dir.func_186678_a(distance));
                            if (closerBB.func_186668_a(tracePoint.field_72450_a - 0.3d, tracePoint.field_72448_b - 0.3d, tracePoint.field_72449_c - 0.3d, tracePoint.field_72450_a + 0.3d, tracePoint.field_72448_b + 0.3d, tracePoint.field_72449_c + 0.3d)) {
                                IMaxAttack.dealTrueDamage(playerIn, player, player.func_110138_aP() * 2.0f);
                            }
                        }
                    }
                }
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.GENERIC_WEAPON_9, SoundCategory.PLAYERS, 1.0f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
            }
            startCooldown(stack);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.classify.IHeldTick
    public void heldTick(EntityPlayer player, EnumHand hand, ItemStack stack) {
        if (player.field_70170_p.field_72995_K) {
            return;
        }
        for (EntityPlayer other : player.field_70170_p.func_73046_m().func_184103_al().func_181057_v()) {
            if (other != player) {
                lostinfinity.instance.packetHandler.sendToPlayer((EntityPlayerMP) player, new PacketHeadHunterUpdate(other));
            }
        }
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 1000;
    }
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Red + "Shoot a bullet that travels in the 4th dimension.");
        tooltip.add(TextFmt.Gold + "While holding this gun, you can sense the location of other players.");
        tooltip.add(TextFmt.Red + "Dimensional bullets deal 200% Health True Damage");
    }
}
