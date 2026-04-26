package xol.lostinfinity.util.command;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
public class CommandClearFractures extends CommandBase {
    public String func_71517_b() {
        return "clearfractures";
    }
    public String func_71518_a(ICommandSender sender) {
        return "clearfractures <Range>";
    }
    public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
        return true;
    }
    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 0 && (sender instanceof EntityPlayer)) {
            EntityPlayer player = (EntityPlayer) sender;
            int rifts = 0;
            int range = 5;
            try {
                range = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                if (!player.field_70170_p.field_72995_K) {
                    player.func_145747_a(new TextComponentString(TextFmt.Red + "Invalid number format"));
                }
            }
            int radius = range;
            for (BlockPos fracturePos : BlockPos.func_177980_a(player.func_180425_c().func_177982_a(-radius, -radius, -radius), player.func_180425_c().func_177982_a(radius, radius, radius))) {
                if (player.field_70170_p.func_180495_p(fracturePos).func_177230_c() == BlockInit.cosmicFracture) {
                    player.field_70170_p.func_175698_g(fracturePos);
                    rifts++;
                }
            }
            if (!player.field_70170_p.field_72995_K) {
                player.func_145747_a(new TextComponentString(TextFmt.Gold + "Fractures cleared: " + rifts));
            }
        }
    }
}
