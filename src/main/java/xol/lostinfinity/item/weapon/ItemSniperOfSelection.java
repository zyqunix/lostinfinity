package xol.lostinfinity.item.weapon;

import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.item.classify.IHeldTick;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemSniperOfSelection.class */
public class ItemSniperOfSelection extends ItemCooldown implements IMaxAttack, ICustomRaytrace, ISwitchModels, IHeldTick, IModeSelect, ICustomHoldPose {
    static Pattern pattern_ah = Pattern.compile("[a-h]");
    static Pattern pattern_io = Pattern.compile("[i-o]");
    static Pattern pattern_pz = Pattern.compile("[p-z]");
    static Pattern pattern_09 = Pattern.compile("[0-9]");

    public ItemSniperOfSelection(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
        setModelSwitch("characters", this, 4);
    }

    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult trace_result;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K && (trace_result = standardFXTrace(worldIn, playerIn, 100, EnumParticleTypes.SMOKE_NORMAL, EntityLivingBase.class)) != null && trace_result.getResultEntity() != null) {
                EntityMultipleLives entityMultipleLives = (EntityLivingBase) trace_result.getResultEntity();
                String hit_name = entityMultipleLives.func_70005_c_().toLowerCase();
                if (!hit_name.isEmpty() && regexMatch(stack.func_77978_p().func_74762_e("characters_data"), hit_name.substring(0, 1))) {
                    IMaxAttack.dealMaxHealth((Entity) playerIn, (EntityLivingBase) entityMultipleLives, 2, 3.0f);
                    if (entityMultipleLives instanceof EntityMultipleLives) {
                        EntityMultipleLives multiTarget = entityMultipleLives;
                        multiTarget.takeawayNumLives(4);
                    }
                }
            }
            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.GENERIC_WEAPON_9, SoundCategory.PLAYERS, 1.0f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
            stack.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 2500;
    }

    private boolean regexMatch(int selection, String ch) {
        switch (selection) {
            case 0:
                return pattern_ah.matcher(ch).matches();
            case 1:
                return pattern_io.matcher(ch).matches();
            case 2:
                return pattern_pz.matcher(ch).matches();
            case 3:
                return pattern_09.matcher(ch).matches();
            default:
                return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Right Click: Toggle cycling through groups of characters.");
        tooltip.add(TextFmt.Red + "When shooting an entity who's name begins with a character in the group:");
        tooltip.add(TextFmt.Green + "Deal 150% maximum life damage to the entity.");
        tooltip.add(TextFmt.Gold + "Also removes 4 lives from creatures with multiple lives.");
    }

    @Override // xol.lostinfinity.item.classify.IHeldTick
    public void heldTick(EntityPlayer player, EnumHand hand, ItemStack stack) {
        int data;
        if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("cycling_style") && player.field_70173_aa % 5 == 0 && stack.func_77978_p().func_74767_n("cycling_style")) {
            int data2 = stack.func_77978_p().func_74762_e("characters_data");
            if (data2 < 3) {
                data = data2 + 1;
            } else {
                data = 0;
            }
            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.GENERIC_WEAPON_3, SoundCategory.MASTER, 1.5f, 0.9f + (player.field_70170_p.field_73012_v.nextFloat() * 0.2f));
            stack.func_77978_p().func_74768_a("characters_data", data);
        }
    }

    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74757_a("cycling_style", true);
            stack.func_77978_p().func_74768_a("characters_data", 0);
        } else {
            stack.func_77978_p().func_74757_a("cycling_style", !stack.func_77978_p().func_74767_n("cycling_style"));
        }
        if (!player.field_70170_p.field_72995_K) {
            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundEvents.field_187556_aj, SoundCategory.MASTER, 1.0f, 1.0f);
        }
    }
}
