package xol.lostinfinity.item.tool;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemBasic;
import xol.lostinfinity.item.classify.IHeldTick;
import xol.lostinfinity.util.Reference;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/tool/ItemCloakingDevice.class */
public class ItemCloakingDevice extends ItemBasic implements IHeldTick {
    public ItemCloakingDevice(String regName) {
        super(regName, TabsInit.TAB_AUXWEP);
        func_185043_a(new ResourceLocation(Reference.MODID, "cloaking"), new IItemPropertyGetter() { // from class: xol.lostinfinity.item.tool.ItemCloakingDevice.1
            public float func_185085_a(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return ItemCloakingDevice.getCloakingProperty(stack, entityIn);
            }
        });
    }

    public static float getCloakingProperty(ItemStack stack, @Nullable EntityLivingBase entityIn) {
        if (entityIn != null && !stack.func_190926_b() && (stack.func_77973_b() instanceof ItemCloakingDevice) && stack.func_77942_o() && stack.func_77978_p().func_74767_n("Cloaking")) {
            return 0.1f;
        }
        return 0.0f;
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74757_a("Cloaking", true);
        } else {
            stack.func_77978_p().func_74757_a("Cloaking", !stack.func_77978_p().func_74767_n("Cloaking"));
        }
        if (!worldIn.field_72995_K) {
            if (stack.func_77978_p().func_74767_n("Cloaking")) {
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.ITEM_GHOSTHUNTER, SoundCategory.MASTER, 2.0f, 1.0f);
            } else {
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_191248_br, SoundCategory.MASTER, 2.0f, 1.0f);
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Toggle a cloaking mode that avoids radar detection.");
        if (stack.func_77942_o()) {
            if (stack.func_77978_p().func_74767_n("Cloaking")) {
                tooltip.add(TextFmt.Dark_Purple + "CLOAKING");
            } else {
                tooltip.add(TextFmt.Red + "Cloak not activated.");
            }
        }
    }

    @Override // xol.lostinfinity.item.classify.IHeldTick
    public void heldTick(EntityPlayer player, EnumHand hand, ItemStack stack) {
        if (player.field_70173_aa % 10 == 0 && stack.func_77942_o() && stack.func_77978_p().func_74767_n("Cloaking")) {
            player.func_70690_d(new PotionEffect(MobEffects.field_76441_p, 12));
        }
    }
}
