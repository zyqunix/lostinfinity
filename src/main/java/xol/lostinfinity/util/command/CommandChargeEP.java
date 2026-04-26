package xol.lostinfinity.util.command;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import xol.lostinfinity.item.weapon.ItemEssencePossessor;
public class CommandChargeEP extends CommandBase {
    public String func_71517_b() {
        return "chargeep";
    }
    public String func_71518_a(ICommandSender sender) {
        return "chargeep";
    }
    public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
        return true;
    }
    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) sender;
            chargeItem(player.func_184614_ca());
            chargeItem(player.func_184592_cb());
        }
    }
    private void chargeItem(ItemStack stack) {
        if (stack.func_77973_b() instanceof ItemEssencePossessor) {
            NBTTagCompound compound = stack.func_77978_p();
            if (compound == null) {
                compound = new NBTTagCompound();
                stack.func_77982_d(compound);
            }
            compound.func_74768_a("essence", 10);
        }
    }
}
