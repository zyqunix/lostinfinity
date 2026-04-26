package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.projectile.entity.EntityGalaxyBomb;
public class ItemGalaxyBomb extends Item {
    private String wep;
    public ItemGalaxyBomb(String regName) {
        this.wep = "";
        func_77637_a(TabsInit.TAB_GALAXY);
        setRegistryName(regName);
        func_77655_b(regName);
        this.wep = regName;
        ItemInit.ITEMS.add(this);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        EntityGalaxyBomb shot;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74768_a("Bombs", 400);
        }
        stack.func_77978_p().func_74768_a("Bombs", stack.func_77978_p().func_74762_e("Bombs") - 1);
        if (!worldIn.field_72995_K) {
            shot = new EntityGalaxyBomb(worldIn, playerIn);
            shot.setThrower(playerIn);
            shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 0.7f, 0.0f);
            switch (this.wep) {
                case "moonglow_bomb":
                    shot.setForm((byte) 0);
                    break;
                case "novacron_bomb":
                    shot.setForm((byte) 1);
                    break;
                case "aurorus_bomb":
                    shot.setForm((byte) 2);
                    break;
                case "starfire_bomb":
                    shot.setForm((byte) 3);
                    break;
            }
            worldIn.func_72838_d(shot);
        }
        if (stack.func_77978_p().func_74762_e("Bombs") == 0) {
            stack.func_190918_g(1);
        }
        playerIn.func_184185_a(SoundEvents.field_187578_au, 1.0f, 1.0f);
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Red + "In an area on impact:");
        switch (this.wep) {
            case "moonglow_bomb":
                tooltip.add(TextFmt.Gold + "While Above 80% Life:");
                tooltip.add(TextFmt.Aqua + "Deal 15% Max Health Damage");
                break;
            case "novacron_bomb":
                tooltip.add(TextFmt.Gold + "To Targets Below 50% Health:");
                tooltip.add(TextFmt.Light_Purple + "Deal 10% Max Health Damage");
                break;
            case "aurorus_bomb":
                tooltip.add(TextFmt.Gold + "To Targets Above 50% Health:");
                tooltip.add(TextFmt.Green + "Deal 10% Max Health Damage");
                break;
            case "starfire_bomb":
                tooltip.add(TextFmt.Gold + "While Below 20% Life:");
                tooltip.add(TextFmt.Yellow + "Deal 20% Max Health Damage");
                break;
        }
        if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("Bombs")) {
            tooltip.add(TextFmt.Red + "Bombs Remaining: " + stack.func_77978_p().func_74762_e("Bombs"));
        }
    }
}
