package xol.lostinfinity.block.activator;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.packets.serverbound.PacketTextTitle;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.weapon.ItemHeadCollector;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
public class BlockContestGame extends Block {
    private int game;
    public BlockContestGame(String name, int screen_type) {
        super(Material.field_151573_f);
        func_149663_c(name);
        setRegistryName(name);
        func_149711_c(3.0f);
        func_149647_a(TabsInit.TAB_BLOCKS);
        func_149672_a(SoundType.field_185851_d);
        func_149715_a(1.0f);
        this.game = screen_type;
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (playerIn.func_70093_af() || worldIn.field_73011_w.func_186058_p() != DimensionInit.grandmasterOutpost) {
            return true;
        }
        if (playerIn.field_70165_t <= 6.0d) {
            if (!worldIn.field_72995_K) {
                BlockPos teleport_to = null;
                switch (this.game) {
                    case -4:
                        teleport_to = ContestCoordinates.chemistPos();
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Aqua + "The Grandmaster: " + TextFmt.Gold + "Looking to dabble in illegal chemical mixing? You didn't see us, we don't see you..."));
                        break;
                    case -3:
                        teleport_to = ContestCoordinates.archeologistPos();
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Aqua + "The Grandmaster: " + TextFmt.Gold + "So you've found some rare fossils? We always offer a good price for them... if of course it is not a fossil of someone who owed us money."));
                        break;
                    case -2:
                        teleport_to = ContestCoordinates.grandEntryPos();
                        break;
                    case -1:
                        teleport_to = ContestCoordinates.blackMarketPos();
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Aqua + "The Grandmaster: " + TextFmt.Gold + "So you wanted to visit the black market hmm? Okay just don't tell the authorities and we'll have no issue."));
                        break;
                    case 1:
                        teleport_to = ContestCoordinates.holodeckTelportPos();
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Aqua + "The Grandmaster: " + TextFmt.Gold + "The Holodeck, yes yes.. good choice. You will definitely " + TextFmt.getFormatting(TextFmt.Dark_Aqua, TextFmt.Italic) + "fall" + TextFmt.Reset + TextFmt.Gold + " in love with this one!"));
                        break;
                    case 2:
                        teleport_to = ContestCoordinates.huntersLobbyPos();
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Aqua + "The Grandmaster: " + TextFmt.Gold + "Ah the thrill of the hunt is what gets you going hmm? Well there's a little twist as you'll be the one getting hunted. Hope you don't have a scent!"));
                        break;
                    case 3:
                        teleport_to = ContestCoordinates.redlightLobbyPos();
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Aqua + "The Grandmaster: " + TextFmt.Gold + "Yes good choice adventurer! Our take on a classic children's game, just with a little added " + TextFmt.getFormatting(TextFmt.Red, TextFmt.Italic) + "death."));
                        break;
                    case TileEntityFusionTable.BOARD_ROWS :
                        teleport_to = ContestCoordinates.bombersLobbyPos();
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Aqua + "The Grandmaster: " + TextFmt.Gold + "Nothing like being trapped in a small space with a load of explosives. This one is a real " + TextFmt.getFormatting(TextFmt.Yellow, TextFmt.Italic) + "blast" + TextFmt.Reset + TextFmt.Gold + " to watch."));
                        break;
                    case 5:
                        teleport_to = ContestCoordinates.duelLobbyPos();
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Aqua + "The Grandmaster: " + TextFmt.Gold + "So someone has something you want and they won't just give it to you? Well, at the Duel Arena you can take it from them!"));
                        break;
                    case TileEntityFusionTable.BOARD_COLUMNS :
                        teleport_to = ContestCoordinates.lightBridgeLobbyPos();
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Aqua + "The Grandmaster: " + TextFmt.Gold + "Good choice. This game is a game to " + TextFmt.getFormatting(TextFmt.Green, TextFmt.Italic) + "remember" + TextFmt.Reset + TextFmt.Gold + ". You can work together on this one, just don't work together too well."));
                        break;
                    case 7:
                        teleport_to = ContestCoordinates.targetsLobbyPos();
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Aqua + "The Grandmaster: " + TextFmt.Gold + "So you want to show off your skills? Well then, let's see if you can " + TextFmt.getFormatting(TextFmt.Green, TextFmt.Italic) + "shoot" + TextFmt.Reset + TextFmt.Gold + " to the top of our contenders."));
                        break;
                    case 8:
                        teleport_to = ContestCoordinates.battleSnakesLobbyPos();
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Aqua + "The Grandmaster: " + TextFmt.Gold + "So you've slithered your way into this game. Well, time to see whether you are a predator or prey."));
                        break;
                    case 9:
                        teleport_to = ContestCoordinates.laserTagLobbyPos();
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Aqua + "The Grandmaster: " + TextFmt.Gold + "You fancy a game of laser tag hmm? Well these lasers won't disintegrate eachother like many of the toys we have around eachother. Not to say, we couldn't make them do that though if you are up for it..."));
                        break;
                    case ItemHeadCollector.CHARGE_LIMIT :
                        teleport_to = ContestCoordinates.parkourLobbyPos();
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Aqua + "The Grandmaster: " + TextFmt.Gold + "You're really " + TextFmt.getFormatting(TextFmt.Aqua, TextFmt.Italic) + "jumping" + TextFmt.Reset + TextFmt.Gold + " for the opportunity to compete in this game. Let's get your legs to work contender!"));
                        break;
                    case 11:
                        teleport_to = ContestCoordinates.dodgeballLobbyPos();
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Aqua + "The Grandmaster: " + TextFmt.Gold + "This game always makes our audience " + TextFmt.getFormatting(TextFmt.Aqua, TextFmt.Italic) + "bounce" + TextFmt.Reset + TextFmt.Gold + " off the walls."));
                        break;
                    case 12:
                        teleport_to = ContestCoordinates.inkBattleLobbyPos();
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Aqua + "The Grandmaster: " + TextFmt.Gold + "So you want to make your territory? This is the perfect game to " + TextFmt.getFormatting(TextFmt.Aqua, TextFmt.Italic) + "paint" + TextFmt.Reset + TextFmt.Gold + " who you are."));
                        break;
                    case 13:
                        teleport_to = ContestCoordinates.treadmillLobbyPos();
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Aqua + "The Grandmaster: " + TextFmt.Gold + "A game that will test your agility... as well as your skills in " + TextFmt.getFormatting(TextFmt.Aqua, TextFmt.Italic) + "pushing" + TextFmt.Reset + TextFmt.Gold + " people to their death.. I mean limits."));
                        break;
                }
                if (teleport_to != null) {
                    playerIn.func_70634_a(teleport_to.func_177958_n(), teleport_to.func_177956_o(), teleport_to.func_177952_p());
                    worldIn.func_184133_a((EntityPlayer) null, teleport_to, SoundInit.MINIGAME_TELEPORT, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    return true;
                }
                return true;
            }
            switch (this.game) {
                case -4:
                    lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(true, false, 80, 30, 30, "Chemistry"), playerIn);
                    break;
                case -3:
                    lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(true, false, 80, 30, 30, "Archeology"), playerIn);
                    break;
                case -1:
                    lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(true, false, 80, 30, 30, "The Black Market"), playerIn);
                    break;
                case 1:
                    lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(true, false, 80, 30, 30, "The Holodeck"), playerIn);
                    break;
                case 2:
                    lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(true, false, 80, 30, 30, "Hunters"), playerIn);
                    break;
                case 3:
                    lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(true, false, 80, 30, 30, "Redlight Greenbridge"), playerIn);
                    break;
                case TileEntityFusionTable.BOARD_ROWS :
                    lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(true, false, 80, 30, 30, "Bombers"), playerIn);
                    break;
                case 5:
                    lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(true, false, 80, 30, 30, "The Duel Arena"), playerIn);
                    break;
                case TileEntityFusionTable.BOARD_COLUMNS :
                    lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(true, false, 80, 30, 30, "The Light Bridge"), playerIn);
                    break;
                case 7:
                    lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(true, false, 80, 30, 30, "Target Time"), playerIn);
                    break;
                case 8:
                    lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(true, false, 80, 30, 30, "Battle Snakes"), playerIn);
                    break;
                case 9:
                    lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(true, false, 80, 30, 30, "Laser Warz"), playerIn);
                    break;
                case ItemHeadCollector.CHARGE_LIMIT :
                    lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(true, false, 80, 30, 30, "Parkour Trials"), playerIn);
                    break;
                case 11:
                    lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(true, false, 80, 30, 30, "Trampoline Dodgeball"), playerIn);
                    break;
                case 12:
                    lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(true, false, 80, 30, 30, "Ink Wars"), playerIn);
                    break;
                case 13:
                    lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(true, false, 80, 30, 30, "Obstacle Alley"), playerIn);
                    break;
            }
            return true;
        }
        if (!worldIn.field_72995_K) {
            BlockPos teleport_to2 = ContestCoordinates.gamesLobbyPos();
            playerIn.func_70634_a(teleport_to2.func_177958_n(), teleport_to2.func_177956_o(), teleport_to2.func_177952_p());
            worldIn.func_184133_a((EntityPlayer) null, teleport_to2, SoundInit.MINIGAME_TELEPORT, SoundCategory.PLAYERS, 1.0f, 1.0f);
            return true;
        }
        return true;
    }
}
