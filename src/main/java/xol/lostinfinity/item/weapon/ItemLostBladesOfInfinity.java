package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.IHotbarHit;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.item.classify.ISummon;
import xol.lostinfinity.mob.entity.minion.EntityLostBlade;
import xol.lostinfinity.mob.entity.minion.EntityMinion;
import xol.lostinfinity.util.data.CustomDamageResult;
public class ItemLostBladesOfInfinity extends ItemCooldown implements IModeSelect, IHotbarHit, ISummon {
    public enum BladeMode {
        STANDBY,
        REACTIVE,
        TARGET,
        GENOCIDE
    }
    public ItemLostBladesOfInfinity(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.func_184586_b(hand);
        if (!showDurabilityBar(stack)) {
            if (!world.field_72995_K) {
                despawnPrevious(player);
                for (int i = 0; i < 6; i++) {
                    EntityLostBlade lostBlade = new EntityLostBlade(world);
                    lostBlade.setOwner(player);
                    lostBlade.setHand(hand);
                    lostBlade.setLastSlot(player.field_71071_by.field_70461_c);
                    lostBlade.setPose(i);
                    lostBlade.setTrackedItemStack(stack);
                    world.func_72838_d(lostBlade);
                }
            }
            startCooldown(stack);
        }
        return super.func_77659_a(world, player, hand);
    }
    @Override // xol.lostinfinity.item.classify.IHotbarHit
    public void hitReaction(EntityPlayer player, Entity attacker, CustomDamageResult result, ItemStack stack) {
        if (getMode(stack) != BladeMode.REACTIVE) {
            return;
        }
        if (attacker instanceof EntityThrowable) {
            attacker = ((EntityThrowable) attacker).func_85052_h();
        }
        if (attacker instanceof EntityArrow) {
            attacker = ((EntityArrow) attacker).field_70250_c;
        }
        if (!(attacker instanceof EntityLivingBase)) {
            return;
        }
        EntityLivingBase livingBase = (EntityLivingBase) attacker;
        for (EntityMinion minion : getCurrentMinion(player)) {
            if (minion instanceof EntityLostBlade) {
                ((EntityLostBlade) minion).setTarget(livingBase);
            }
        }
    }
    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        cycleMode(stack);
    }
    private void cycleMode(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.func_77978_p().func_74768_a("blade_mode", (stack.func_77978_p().func_74762_e("blade_mode") + 1) % BladeMode.values().length);
    }
    private BladeMode getMode(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            return BladeMode.STANDBY;
        }
        return BladeMode.values()[stack.func_77978_p().func_74762_e("blade_mode")];
    }
    static  class AnonymousClass1 {
        static final  int[] $SwitchMap$xol$lostinfinity$item$weapon$ItemLostBladesOfInfinity$BladeMode = new int[BladeMode.values().length];
        static {
            try {
                $SwitchMap$xol$lostinfinity$item$weapon$ItemLostBladesOfInfinity$BladeMode[BladeMode.STANDBY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$xol$lostinfinity$item$weapon$ItemLostBladesOfInfinity$BladeMode[BladeMode.REACTIVE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$xol$lostinfinity$item$weapon$ItemLostBladesOfInfinity$BladeMode[BladeMode.TARGET.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$xol$lostinfinity$item$weapon$ItemLostBladesOfInfinity$BladeMode[BladeMode.GENOCIDE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }
    public String getHighlightTip(ItemStack item, String displayName) {
        switch (AnonymousClass1.$SwitchMap$xol$lostinfinity$item$weapon$ItemLostBladesOfInfinity$BladeMode[getMode(item).ordinal()]) {
            case 1:
                return displayName + " - Standby";
            case 2:
                return displayName + " - Reactive";
            case 3:
                return displayName + " - Targeted";
            case TileEntityFusionTable.BOARD_ROWS :
                return displayName + " - Genocide";
            default:
                return displayName;
        }
    }
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Summons the Blades of Infinity to fight alongside you.");
        tooltip.add(TextFmt.Red + "Each blade deals 20% Max Health Damage every 0.4 seconds.");
        switch (AnonymousClass1.$SwitchMap$xol$lostinfinity$item$weapon$ItemLostBladesOfInfinity$BladeMode[getMode(stack).ordinal()]) {
            case 1:
                tooltip.add("");
                tooltip.add(TextFmt.Light_Purple + "Current Mode: Standby");
                tooltip.add(TextFmt.White + "Your blades would not attack anything.");
                break;
            case 2:
                tooltip.add("");
                tooltip.add(TextFmt.Light_Purple + "Current Mode: Reactive");
                tooltip.add(TextFmt.Green + "Your blades would attack anything that damaged you.");
                break;
            case 3:
                tooltip.add("");
                tooltip.add(TextFmt.Light_Purple + "Current Mode: Targeted");
                tooltip.add(TextFmt.Aqua + "Your blades would attack your target.");
                break;
            case TileEntityFusionTable.BOARD_ROWS :
                tooltip.add("");
                tooltip.add(TextFmt.Light_Purple + "Current Mode: Genocide");
                tooltip.add(TextFmt.Red + "Your blades would search and destroy anything within 24 blocks.");
                break;
        }
        tooltip.add(TextFmt.Aqua + "Aquatic");
    }
}
