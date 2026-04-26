package xol.lostinfinity.item.weapon;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ISummon;
import xol.lostinfinity.mob.entity.minion.EntityAuraOfAllegiance;
import xol.lostinfinity.mob.entity.minion.EntityMinion;
public class ItemAuraOfAllegiance extends ItemCooldown implements ISummon {
    public ItemAuraOfAllegiance(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                boolean shouldDespawn = false;
                EntityAuraOfAllegiance aura = null;
                Set<EntityMinion> minionSet = getCurrentMinion(playerIn);
                for (EntityMinion minion : minionSet) {
                    if (minion instanceof EntityAuraOfAllegiance) {
                        aura = (EntityAuraOfAllegiance) minion;
                    } else {
                        shouldDespawn = true;
                    }
                    if (aura != null && shouldDespawn) {
                        break;
                    }
                }
                if (shouldDespawn) {
                    despawnPrevious(playerIn);
                }
                if (aura == null) {
                    EntityAuraOfAllegiance aura2 = new EntityAuraOfAllegiance(worldIn);
                    aura2.setOwner(playerIn);
                    aura2.setHand(handIn);
                    aura2.setLastSlot(playerIn.field_71071_by.field_70461_c);
                    aura2.setTrackedItemStack(stack);
                    aura2.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v);
                    aura2.setLives(2);
                    worldIn.func_72838_d(aura2);
                } else {
                    aura.addLives(2);
                }
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MAGIC_WEAPON_16, SoundCategory.PLAYERS, 1.5f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
            }
            startCooldown(stack);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 4000;
    }
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Summon the Lost Infinity Stones to protect you.");
        tooltip.add(TextFmt.Italic + "Each use creates 2 more stones, up to 6 maximum.");
        tooltip.add(TextFmt.Green + "While the stones are active, you take no Max Health and True damage, at the cost of losing 1 stone.");
    }
}
