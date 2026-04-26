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
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.projectile.entity.EntityElementiumAir;
import xol.lostinfinity.projectile.entity.EntityElementiumBlight;
import xol.lostinfinity.projectile.entity.EntityElementiumEarth;
import xol.lostinfinity.projectile.entity.EntityElementiumFire;
import xol.lostinfinity.projectile.entity.EntityElementiumPlague;
import xol.lostinfinity.projectile.entity.EntityElementiumShadow;
import xol.lostinfinity.projectile.entity.EntityElementiumWater;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemElementiumBow.class */
public class ItemElementiumBow extends ItemCooldown implements ICustomHoldPose, ISwitchModels, IModeSelect {
    private int elements_stored;

    public ItemElementiumBow(String regName, int numElements) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
        setModelSwitch("elements", this, numElements);
        this.elements_stored = numElements;
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74768_a("elements_data", 0);
            stack.func_77978_p().func_74768_a("elements_stored", this.elements_stored);
        }
        if (!worldIn.field_72995_K && !showDurabilityBar(playerIn.func_184586_b(handIn))) {
            switch (stack.func_77978_p().func_74762_e("elements_data")) {
                case 0:
                    EntityElementiumAir air = new EntityElementiumAir(worldIn, playerIn);
                    air.setThrower(playerIn);
                    air.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 4.0f, 0.0f);
                    worldIn.func_72838_d(air);
                    break;
                case 1:
                    for (int shot = 0; shot < 8; shot++) {
                        EntityElementiumWater water = new EntityElementiumWater(worldIn, playerIn);
                        water.setThrower(playerIn);
                        water.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 2.0f, 8.0f);
                        worldIn.func_72838_d(water);
                    }
                    break;
                case 2:
                    EntityElementiumEarth earth = new EntityElementiumEarth(worldIn, playerIn);
                    earth.setThrower(playerIn);
                    earth.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 2.5f, 0.0f);
                    worldIn.func_72838_d(earth);
                    break;
                case 3:
                    EntityElementiumFire fire = new EntityElementiumFire(worldIn, playerIn);
                    fire.setThrower(playerIn);
                    fire.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 2.5f, 0.0f);
                    worldIn.func_72838_d(fire);
                    break;
                case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                    EntityElementiumShadow shadow = new EntityElementiumShadow(worldIn, playerIn);
                    shadow.setThrower(playerIn);
                    shadow.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 2.5f, 0.0f);
                    worldIn.func_72838_d(shadow);
                    break;
                case 5:
                    EntityElementiumBlight blight = new EntityElementiumBlight(worldIn, playerIn);
                    blight.setThrower(playerIn);
                    blight.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 2.5f, 0.0f);
                    worldIn.func_72838_d(blight);
                    break;
                case TileEntityFusionTable.BOARD_COLUMNS /* 6 */:
                    EntityElementiumPlague plague = new EntityElementiumPlague(worldIn, playerIn);
                    plague.setThrower(playerIn);
                    plague.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 2.5f, 0.0f);
                    worldIn.func_72838_d(plague);
                    break;
            }
            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187866_fi, SoundCategory.MASTER, 1.0f, 1.0f);
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 500;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "A bow that can switch between elements.");
        if (stack.func_77942_o()) {
            tooltip.add(TextFmt.Italic + "Active Element: " + getElementName(stack.func_77978_p().func_74762_e("elements_data")));
            tooltip.add(TextFmt.Gold + "Currently has " + stack.func_77978_p().func_74762_e("elements_stored") + " elements stored.");
        }
    }

    private String getElementName(int element) {
        switch (element) {
            case 0:
                return "Wind";
            case 1:
                return "Waves";
            case 2:
                return "Nature";
            case 3:
                return "Flames";
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                return "Shadows";
            case 5:
                return "Blight";
            case TileEntityFusionTable.BOARD_COLUMNS /* 6 */:
                return "Plague";
            case 7:
                return "Magnetism";
            case 8:
                return "Electricity";
            default:
                return "";
        }
    }

    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74768_a("elements_data", 0);
            stack.func_77978_p().func_74768_a("elements_stored", this.elements_stored);
        }
        if (!stack.func_77978_p().func_74764_b("elements_stored")) {
            stack.func_77978_p().func_74768_a("elements_stored", this.elements_stored);
        }
        int new_element = stack.func_77978_p().func_74762_e("elements_data") + 1;
        if (new_element >= stack.func_77978_p().func_74762_e("elements_stored")) {
            new_element = 0;
        }
        stack.func_77978_p().func_74768_a("elements_data", new_element);
        player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundEvents.field_187556_aj, SoundCategory.MASTER, 1.0f, 1.0f);
    }
}
