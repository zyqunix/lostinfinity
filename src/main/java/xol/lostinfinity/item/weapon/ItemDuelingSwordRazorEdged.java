package xol.lostinfinity.item.weapon;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.math.AxisAlignedBB;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerDuelArena;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
public class ItemDuelingSwordRazorEdged extends ItemSword {
    private static final int numHits = 5;
    public ItemDuelingSwordRazorEdged(String regName) {
        super(Item.ToolMaterial.DIAMOND);
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }
    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.func_77964_b(0);
        if ((target instanceof EntityPlayer) && (attacker instanceof EntityPlayer)) {
            AxisAlignedBB arena = ContestCoordinates.duelArenaAABB();
            List<EntityPlayer> inAABB = attacker.field_70170_p.func_72872_a(EntityPlayer.class, arena);
            if (inAABB.contains((EntityPlayer) attacker) && inAABB.contains((EntityPlayer) target)) {
                for (EntityControllerDuelArena controller : attacker.field_70170_p.func_72872_a(EntityControllerDuelArena.class, arena)) {
                    controller.hitPlayer((EntityPlayer) target, numHits);
                }
                return true;
            }
            return true;
        }
        return true;
    }
}
