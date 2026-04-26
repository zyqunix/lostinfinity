package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
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
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.projectile.entity.EntityCarrierProjectile;
import xol.lostinfinity.projectile.entity.EntityComet;
public class ItemCometCannon extends ItemCooldown implements ISwitchModels, IModeSelect, ICustomHoldPose {
    private int cooldown;
    public ItemCometCannon(String regName) {
        super(regName);
        this.cooldown = 5000;
        func_77637_a(TabsInit.TAB_INFINITYWEP);
        setModelSwitch("firemode", this, 2);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74768_a("firemode_data", 0);
        }
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            int mode = getFireMode(stack);
            if (mode == 1) {
                if (!worldIn.field_72995_K) {
                    EntityCarrierProjectile shot = new EntityCarrierProjectile(worldIn, playerIn);
                    shot.setThrower(playerIn);
                    shot.setForm((byte) 1);
                    if (!playerIn.func_70093_af()) {
                        shot.setRemainingLife((byte) 40);
                    } else {
                        shot.setRemainingLife((byte) 15);
                    }
                    shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.5f, 1.0f);
                    worldIn.func_72838_d(shot);
                }
                setCooldown(5000);
            } else {
                if (!worldIn.field_72995_K) {
                    EntityComet comet = new EntityComet(worldIn, playerIn);
                    comet.setThrower(playerIn);
                    comet.setCount(2000);
                    comet.func_189654_d(true);
                    comet.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 3.0f, 0.0f);
                    worldIn.func_72838_d(comet);
                }
                setCooldown(500);
            }
            if (!worldIn.field_72995_K) {
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.ITEM_STARSTORM, SoundCategory.MASTER, 1.0f, 1.0f);
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return this.cooldown;
    }
    private void setCooldown(int newC) {
        this.cooldown = newC;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "Can switch between firing comet showers and comets forwards.");
        tooltip.add(TextFmt.Yellow + "Shift Fire for fast detonation.");
        tooltip.add(TextFmt.Aqua + "Comets deal:");
        tooltip.add(TextFmt.Red + "50% Max Health to creatures");
        tooltip.add(TextFmt.Red + "75% Max Health to players");
    }
    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        int attack_style = stack.func_77978_p().func_74762_e("firemode_data");
        if (attack_style == 0) {
            stack.func_77978_p().func_74768_a("firemode_data", 1);
        } else {
            stack.func_77978_p().func_74768_a("firemode_data", 0);
        }
    }
    private int getFireMode(ItemStack stack) {
        if (stack.func_77942_o()) {
            return stack.func_77978_p().func_74762_e("firemode_data");
        }
        return 0;
    }
}
