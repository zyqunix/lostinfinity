package xol.lostinfinity.item.weapon;
import com.mojang.authlib.GameProfile;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.gui.GuiHandler;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.util.data.IMaxAttack;
public class ItemHeadCollector extends ItemSword implements IMaxAttack {
    public static final int CHARGE_LIMIT = 10;
    public ItemHeadCollector(String regName) {
        super(Item.ToolMaterial.WOOD);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }
    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        boolean player = target instanceof EntityPlayer;
        boolean shouldDropHead = stack.func_77942_o() && stack.func_77978_p().func_74762_e("Charge") > 0;
        if (shouldDropHead && player) {
            ItemStack st = new ItemStack(Items.field_151144_bL, 1, 3);
            if (!st.func_77942_o()) {
                st.func_77982_d(new NBTTagCompound());
            }
            st.func_77978_p().func_74778_a("Owner", target.func_70005_c_());
            st.func_77978_p().func_74768_a("Immunity", 10);
            st.func_77978_p().func_74772_a("LastBlock", 0L);
            GameProfile gameprofile = new GameProfile(target.func_110124_au(), target.func_70005_c_());
            st.func_77978_p().func_74782_a("SkullOwner", NBTUtil.func_180708_a(new NBTTagCompound(), TileEntitySkull.func_174884_b(gameprofile)));
            if (!attacker.field_70170_p.field_72995_K) {
                EntityItem skull = new EntityItem(attacker.field_70170_p, attacker.field_70165_t, attacker.field_70163_u + 1.0d, attacker.field_70161_v, st);
                skull.field_70159_w = 0.0d;
                skull.field_70181_x = 0.0d;
                skull.field_70179_y = 0.0d;
                attacker.field_70170_p.func_72838_d(skull);
            }
            stack.func_77978_p().func_74768_a("Charge", stack.func_77978_p().func_74762_e("Charge") - 1);
        }
        IMaxAttack.dealMaxHealth(attacker, target, 10);
        return true;
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.openGui(lostinfinity.instance, GuiHandler.RegisteredGuis.ITEM_CHARGER.getId(), worldIn, 0, 0, 0);
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        tooltip.add(TextFmt.Gold + "Deals 10% Max Health Damage");
        tooltip.add(TextFmt.Green + "Current Charge: " + stack.func_77978_p().func_74762_e("Charge") + " / 10");
        tooltip.add(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Light_Purple) + "With charge:");
        tooltip.add(TextFmt.Red + "Collects the head of a player on kill, using a charge.");
        tooltip.add(TextFmt.Italic + "Wearing the collected head is honestly pretty morbid");
    }
}
