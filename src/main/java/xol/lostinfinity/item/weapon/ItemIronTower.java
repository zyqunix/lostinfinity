package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.common.packets.LostInfinityPacketHandler;
import xol.lostinfinity.common.packets.serverbound.PacketNBTSyncLong;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.ICooldown;
import xol.lostinfinity.item.classify.IHitReactive;
import xol.lostinfinity.item.classify.IMaxReducible;
public class ItemIronTower extends Item implements IMaxReducible, IHitReactive {
    public ItemIronTower(String regName) {
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "When held, reduces max health damage taken by 30%.");
        tooltip.add(TextFmt.Aqua + "When reducing damage, advance ALL cooldowns in your hotbar by 30%.");
    }
    @Override // xol.lostinfinity.item.classify.IMaxReducible
    public float reduceMaxDamage(EntityPlayer player, boolean isMainHand, float damage, float reductionMultiplier, ItemStack stack) {
        return reductionMultiplier - 0.3f;
    }
    @Override // xol.lostinfinity.item.classify.IHitReactive
    public void hitReaction(EntityPlayer player, Entity attacker, float damage, ItemStack stack) {
        boolean didSound = false;
        if (damage > 0.0f) {
            for (int i = 0; i <= 8; i++) {
                ItemStack invyStack = player.field_71071_by.func_70301_a(i);
                if (invyStack.func_77973_b() instanceof ICooldown) {
                    Item hotbarItem = invyStack.func_77973_b();
                    if (hotbarItem.showDurabilityBar(invyStack)) {
                        long storedTime = invyStack.func_77978_p().func_74763_f("lastUse");
                        long currentTime = System.currentTimeMillis();
                        double barProgress = 1.0d - hotbarItem.getDurabilityForDisplay(invyStack);
                        long passedTime = currentTime - storedTime;
                        int originalCD = MathHelper.func_76128_c(passedTime / barProgress);
                        int cooldownReduction = MathHelper.func_76128_c(((double) originalCD) * 0.3d);
                        long replaceLong = storedTime - ((long) cooldownReduction);
                        invyStack.func_77978_p().func_74772_a("lastUse", replaceLong);
                        LostInfinityPacketHandler.INSTANCE.sendTo(new PacketNBTSyncLong("lastUse", replaceLong, invyStack), (EntityPlayerMP) player);
                        didSound = true;
                    }
                }
            }
        }
        if (didSound) {
            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.SHIELD_BLOCK, SoundCategory.PLAYERS, 1.0f, 0.8f + (player.field_70170_p.field_73012_v.nextFloat() * 0.4f));
            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.MAGIC_WEAPON_19, SoundCategory.PLAYERS, 1.0f, 0.8f + (player.field_70170_p.field_73012_v.nextFloat() * 0.4f));
        }
    }
}
