package xol.lostinfinity.item.weapon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.projectile.entity.EntityAvenger;
import xol.lostinfinity.util.PotionBasic;
import xol.lostinfinity.util.data.CustomDamageResult;
import xol.lostinfinity.util.data.IMaxAttack;
public class ItemAvenger extends ItemSword implements ISwitchModels, IMaxAttack {
    public ItemAvenger(String regName) {
        super(Item.ToolMaterial.DIAMOND);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        func_77625_d(1);
        setModelSwitch("empty", this, 2);
        ItemInit.ITEMS.add(this);
    }
    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        CustomDamageResult dr = IMaxAttack.dealTrueDamage(attacker, target, target.func_110138_aP() * 0.5f, Arrays.asList("Aquatic"));
        float damageDealt = dr.getDamageDealt();
        if (dr.wasTargetKilled()) {
            killReward(attacker);
        } else {
            attacker.func_70691_i(damageDealt);
        }
        attacker.field_70170_p.func_184133_a((EntityPlayer) null, attacker.func_180425_c(), SoundInit.SWING_HIT, SoundCategory.PLAYERS, 1.5f, 0.6f + (attacker.field_70170_p.field_73012_v.nextFloat() * 0.4f));
        return true;
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        int mode = stack.func_77978_p().func_74762_e("empty_data");
        if (mode == 0) {
            if (!worldIn.field_72995_K) {
                EntityAvenger shot = new EntityAvenger(worldIn, playerIn);
                shot.setThrower(playerIn);
                shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 2.0f, 0.0f);
                worldIn.func_72838_d(shot);
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187737_v, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            stack.func_77978_p().func_74768_a("empty_data", 1);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    private void killReward(EntityLivingBase player) {
        List<PotionEffect> potionsToAdd = new ArrayList<>();
        List<Potion> potionList = (List) player.func_70651_bq().stream().map((v0) -> {
            return v0.func_188419_a();
        }).collect(Collectors.toList());
        for (Potion potion : potionList) {
            if (potion instanceof PotionBasic) {
                PotionBasic lost_potion = (PotionBasic) potion;
                PotionEffect scaledEffect = scalePotionEffect(player.func_70660_b(potion), lost_potion.negativeLostEffect());
                if (scaledEffect != null) {
                    potionsToAdd.add(scaledEffect);
                }
            } else {
                PotionEffect scaledEffect2 = scalePotionEffect(player.func_70660_b(potion), potion.func_76398_f());
                if (scaledEffect2 != null) {
                    potionsToAdd.add(scaledEffect2);
                }
            }
        }
        player.func_70674_bp();
        for (PotionEffect pe : potionsToAdd) {
            player.func_70690_d(pe);
        }
        player.func_70691_i(player.func_110138_aP());
    }
    private PotionEffect scalePotionEffect(PotionEffect effect, boolean bad) {
        if (!bad) {
            return effect;
        }
        int level = effect.func_76458_c() - 1;
        int duration = effect.func_76459_b();
        if (level >= 0) {
            return new PotionEffect(effect.func_188419_a(), duration, level);
        }
        return null;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Deals 50% Health True Damage");
        tooltip.add(TextFmt.Green + "Lifesteals for 100% of Damage Dealt");
        tooltip.add(TextFmt.Italic + "Right Click to throw the hammer.");
        tooltip.add(TextFmt.Gold + "Killing a target heals you to full and reduces the level of negative effects.");
        tooltip.add(TextFmt.Aqua + "Aquatic");
    }
}
