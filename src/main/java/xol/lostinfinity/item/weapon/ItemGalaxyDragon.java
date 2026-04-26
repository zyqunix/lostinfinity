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
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.mob.entity.galaxy.EntityGalaxyDragon;
public class ItemGalaxyDragon extends ItemCooldown implements IModeSelect {
    private static final String MODE = "attack_mode";
    private static final String MOUNT_ID = "mount_id";
    public ItemGalaxyDragon(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        Entity prevMount;
        ItemStack stack = playerIn.func_184586_b(handIn);
        Entity mount = playerIn.func_184208_bv();
        if (!showDurabilityBar(stack)) {
            if (mount instanceof EntityGalaxyDragon) {
                if (!worldIn.field_72995_K) {
                    useAttack(playerIn, stack, (EntityGalaxyDragon) mount);
                }
            } else {
                if (!worldIn.field_72995_K) {
                    UUID prev = getSpawnedUUID(stack);
                    if (prev != null && (prevMount = worldIn.func_73046_m().func_175576_a(prev)) != null) {
                        prevMount.func_70106_y();
                    }
                    EntityGalaxyDragon dragon = new EntityGalaxyDragon(worldIn);
                    dragon.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v);
                    dragon.setOwner(playerIn);
                    worldIn.func_72838_d(dragon);
                    playerIn.func_184205_a(dragon, true);
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.DRAGON_AMBIENT, SoundCategory.PLAYERS, 0.7f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
                    setSpawnedUUID(stack, dragon.func_110124_au());
                }
                startCooldown(stack);
            }
        } else if (!worldIn.field_72995_K && (mount instanceof EntityGalaxyDragon)) {
            useAttack(playerIn, stack, (EntityGalaxyDragon) mount);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    private void useAttack(EntityPlayer player, ItemStack stack, EntityGalaxyDragon dragon) {
        if (getAttackMode(stack)) {
            dragon.fireBreathAttack(player);
        } else {
            dragon.fireballAttack(player);
        }
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 120000;
    }
    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        toggleAttackMode(stack);
    }
    private void setSpawnedUUID(ItemStack stack, UUID uuid) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.func_77978_p().func_186854_a(MOUNT_ID, uuid);
    }
    private UUID getSpawnedUUID(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            return null;
        }
        return stack.func_77978_p().func_186857_a(MOUNT_ID);
    }
    private void toggleAttackMode(ItemStack stack) {
        setAttackMode(stack, !getAttackMode(stack));
    }
    private void setAttackMode(ItemStack stack, boolean flag) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.func_77978_p().func_74757_a(MODE, flag);
    }
    private boolean getAttackMode(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74757_a(MODE, false);
            return false;
        }
        return stack.func_77978_p().func_74767_n(MODE);
    }
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Attract a Galaxy Dragon to be your loyal steed.");
        tooltip.add(TextFmt.Aqua + "The dragon would risk its life to protect you from taking any damage.");
        if (getAttackMode(stack)) {
            tooltip.add(TextFmt.Light_Purple + "Current mode: Cosmic Inferno");
        } else {
            tooltip.add(TextFmt.Red + "Current mode: Fire Blast");
        }
    }
}
