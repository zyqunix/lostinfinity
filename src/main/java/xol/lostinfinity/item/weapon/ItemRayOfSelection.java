package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.item.classify.IHeldTick;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.projectile.entity.EntitySelectionLaser;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemRayOfSelection.class */
public class ItemRayOfSelection extends ItemCooldown implements IMaxAttack, ISwitchModels, IHeldTick, IModeSelect, ICustomHoldPose {
    public ItemRayOfSelection(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
        setModelSwitch("characters", this, 4);
    }

    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                EntitySelectionLaser shot = new EntitySelectionLaser(worldIn, playerIn);
                shot.setThrower(playerIn);
                shot.setSelectionType(stack.func_77978_p().func_74762_e("characters_data"));
                shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.0f, 0.0f);
                worldIn.func_72838_d(shot);
            }
            playerIn.func_184185_a(SoundInit.LASER_WEAPON_4, 1.0f, 1.0f);
            stack.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 500;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Right Click: Toggle cycling through groups of characters.");
        tooltip.add(TextFmt.Red + "When shooting an entity who's name begins with a character in the group:");
        tooltip.add(TextFmt.Green + "Deal 150% maximum life damage to the entity.");
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
            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.GENERIC_WEAPON_3, SoundCategory.MASTER, 1.0f, 0.9f + (player.field_70170_p.field_73012_v.nextFloat() * 0.2f));
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
