package xol.lostinfinity.item.weapon;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.mob.entity.minion.EntityBombDrone;
public class ItemBombDeliveryDrone extends ItemCooldown {
    public ItemBombDeliveryDrone(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        UUID uuid;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                if (!canSummon(worldIn, stack) && (uuid = getSpawnedUUID(stack)) != null) {
                    worldIn.func_73046_m().func_175576_a(uuid).func_70106_y();
                }
                EntityBombDrone drone = new EntityBombDrone(worldIn);
                drone.setOwner(playerIn);
                drone.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v);
                worldIn.func_72838_d(drone);
                setSpawnedUUID(stack, drone.func_110124_au());
                playerIn.func_184220_m(drone);
            }
            startCooldown(stack);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    private boolean canSummon(World world, ItemStack stack) {
        UUID uuid = getSpawnedUUID(stack);
        if (uuid == null) {
            return true;
        }
        Entity bound = world.func_73046_m().func_175576_a(uuid);
        return !(bound instanceof EntityBombDrone);
    }
    private void setSpawnedUUID(ItemStack stack, UUID uuid) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.func_77978_p().func_186854_a("summon", uuid);
    }
    private UUID getSpawnedUUID(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            return null;
        }
        return stack.func_77978_p().func_186857_a("summon");
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "Take control of a bomb drone.");
        tooltip.add(TextFmt.Gold + "Dismounting the drone causes it to explode.");
        tooltip.add(TextFmt.Red + "Deals 110% Health True Damage To Nearby Targets");
        tooltip.add(TextFmt.Italic + "Detonating returns you to your starting position.");
    }
}
