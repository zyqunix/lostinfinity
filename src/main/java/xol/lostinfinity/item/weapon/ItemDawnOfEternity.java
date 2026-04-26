package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.IMaxNullable;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.util.data.CustomDamageResult;
import xol.lostinfinity.util.data.IMaxAttack;
public class ItemDawnOfEternity extends ItemSword implements IMaxAttack, IMaxNullable, ISwitchModels, IModeSelect {
    private static final String MODE_DATA = "mode_data";
    private static final int ASC_MODE = 0;
    private static final float ASC_HIT_DAMAGE = 3.0f;
    private static final float ASC_AIR_DAMAGE = 1.5f;
    private static final int RES_MODE = 1;
    private static final float RES_HIT_DAMAGE = 0.5f;
    private static final float RES_OH_BLOCK_CHANCE = 0.5f;
    public ItemDawnOfEternity(String regName) {
        super(Item.ToolMaterial.DIAMOND);
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
        setModelSwitch("mode", this, 2);
    }
    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (!isPaired(attacker, EnumHand.OFF_HAND)) {
            return true;
        }
        switch (getMode(stack)) {
            case ASC_MODE :
                if (target.field_70122_E) {
                    IMaxAttack.dealMaxHealth((Entity) attacker, target, 2, ASC_HIT_DAMAGE);
                } else {
                    IMaxAttack.dealTrueDamage(attacker, target, target.func_110138_aP() * ASC_AIR_DAMAGE);
                }
                break;
            case RES_MODE :
                IMaxAttack.dealTrueDamage(attacker, target, target.func_110138_aP() * 0.5f);
                attacker.func_70691_i(attacker.func_110138_aP());
                break;
        }
        ItemStack other = attacker.func_184592_cb();
        attacker.func_184611_a(EnumHand.MAIN_HAND, other);
        attacker.func_184611_a(EnumHand.OFF_HAND, stack);
        return true;
    }
    @Override // xol.lostinfinity.item.classify.IMaxNullable
    public float nullableReaction(EntityPlayer player, boolean isMainHand, float originalDamage, float newDamage, ItemStack stack) {
        if (player.field_70170_p.field_72995_K || isMainHand || !isPaired(player, EnumHand.MAIN_HAND)) {
            return newDamage;
        }
        if (getMode(stack) == RES_MODE && player.field_70170_p.field_73012_v.nextFloat() > 0.5f) {
            player.func_70691_i(player.func_110138_aP());
            return 0.0f;
        }
        return newDamage;
    }
    @Override // xol.lostinfinity.item.classify.IMaxNullable
    public float trueNullableReaction(EntityPlayer player, boolean isMainHand, float originalDamage, float newDamage, ItemStack stack, CustomDamageResult result) {
        if (player.field_70170_p.field_72995_K || isMainHand || !isPaired(player, EnumHand.MAIN_HAND)) {
            return newDamage;
        }
        if (getMode(stack) == 0 && !result.getAttacker().field_70122_E && player.field_70170_p.field_73012_v.nextBoolean()) {
            result.setHitMissed();
            return 0.0f;
        }
        return newDamage;
    }
    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        toggleMode(stack);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Twin sword of the " + TextFmt.Light_Purple + "Dusk of Eternity" + TextFmt.Reset + ".");
        EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
        if (entityPlayerSP != null) {
            if ((entityPlayerSP.func_184586_b(EnumHand.MAIN_HAND) == stack && isPaired(entityPlayerSP, EnumHand.OFF_HAND)) || (entityPlayerSP.func_184586_b(EnumHand.OFF_HAND) == stack && isPaired(entityPlayerSP, EnumHand.MAIN_HAND))) {
                tooltip.add(TextFmt.getFormatting(TextFmt.Dark_Purple, TextFmt.Bold) + "You can feel their power resonating!");
            } else {
                tooltip.add(TextFmt.Dark_Gray + "Without its twins, the sword feels strangely heavier...");
            }
        }
        switch (getMode(stack)) {
            case ASC_MODE :
                tooltip.add(TextFmt.Aqua + "Current mode: Ascentrum");
                tooltip.add(TextFmt.Red + "Main hand: If your target is airborne. deals true damage.");
                tooltip.add(TextFmt.Red + "Deals 150% Health Damage");
                tooltip.add(TextFmt.Blue + "Off hand: 50% chance dodging (infinity) damage dealt by airborne enemies.");
                break;
            case RES_MODE :
                tooltip.add(TextFmt.Green + "Current mode: Restorum");
                tooltip.add(TextFmt.Red + "Main hand: Restore all your health on hit.");
                tooltip.add(TextFmt.Red + "Deal 50% Health True Damage");
                tooltip.add(TextFmt.Blue + "Off hand: 50% chance to block max health damage, and restore all your health.");
                break;
        }
    }
    private void toggleMode(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        NBTTagCompound compound = stack.func_77978_p();
        compound.func_74768_a(MODE_DATA, (compound.func_74762_e(MODE_DATA) + RES_MODE) % 2);
    }
    private int getMode(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        return stack.func_77978_p().func_74762_e(MODE_DATA);
    }
    private boolean isPaired(EntityLivingBase player, EnumHand other) {
        return player.func_184586_b(other).func_77973_b() instanceof ItemDuskOfEternity;
    }
}
