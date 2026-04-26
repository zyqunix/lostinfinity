package xol.lostinfinity.util.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.item.weapon.ItemBranchOfLife;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/command/CommandSetDeviant.class */
public class CommandSetDeviant extends CommandBase {
    public String func_71517_b() {
        return "setdeviant";
    }

    public String func_71518_a(ICommandSender sender) {
        return "setdeviant <NameOfStoredDeviant>";
    }

    public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 0 && (sender instanceof EntityPlayer)) {
            int flag = 0;
            EntityPlayer player = (EntityPlayer) sender;
            Item held = player.func_184614_ca().func_77973_b();
            if (held instanceof ItemBranchOfLife) {
                ItemStack stack = player.func_184614_ca();
                if (stack.func_77942_o()) {
                    if (stack.func_77978_p().func_74764_b(args[0])) {
                        stack.func_77978_p().func_74778_a("currentdeviant", args[0]);
                        if (!player.field_70170_p.field_72995_K) {
                            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundEvents.field_187556_aj, SoundCategory.MASTER, 1.0f, 1.0f);
                        }
                    } else {
                        flag = 2;
                    }
                } else {
                    flag = 1;
                }
            } else {
                flag = 3;
            }
            if (flag > 0 && !player.field_70170_p.field_72995_K) {
                switch (flag) {
                    case 1:
                        player.func_145747_a(new TextComponentString(TextFmt.Red + "No deviants saved."));
                        break;
                    case 2:
                        player.func_145747_a(new TextComponentString(TextFmt.Red + "That deviant is not saved."));
                        break;
                    case 3:
                        player.func_145747_a(new TextComponentString(TextFmt.Red + "Not holding a compatible item."));
                        break;
                }
            }
        }
    }
}
