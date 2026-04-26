package xol.lostinfinity.dimension.nonexistence;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.dimension.data.GridNode;
import xol.lostinfinity.dimension.data.GridParse;
import xol.lostinfinity.dimension.util.WorldGenStructure;
import xol.lostinfinity.item.weapon.ItemHeadCollector;
public class ChunkGeneratorNonexistence implements IChunkGenerator {
    private final Random rand;
    private final World world;
    private Biome[] biomesForGeneration;
    private GridParse dungeon_grid = new GridParse("starforge_dungeon.csv");
    public ChunkGeneratorNonexistence(World world, long seed) {
        this.world = world;
        this.rand = new Random(seed);
    }
    public void func_185931_b(int chunkX, int chunkZ) {
        GridNode node = this.dungeon_grid.getNodeAtLocation(chunkX, chunkZ, -14, 52);
        if (node != null) {
            int posX = (chunkX * 16) + 8;
            int posZ = (chunkZ * 16) + 8;
            String struct = typeToStructureName(node.getPieceType());
            if (!struct.contains(",")) {
                new WorldGenStructure(struct).generateWithRotation(this.world, this.rand, new BlockPos(posX, 80 + node.getHeightDiff(), posZ), node.getRotation());
                return;
            }
            String[] structure_data = struct.split(",");
            int base_yOffset = node.getHeightDiff();
            new WorldGenStructure(structure_data[0]).generateWithRotation(this.world, this.rand, new BlockPos(posX, 80 + base_yOffset, posZ), node.getRotation());
            String[] second_structure = extraStructureDetail(structure_data[1]).split(",");
            int extra_yOffset = Integer.parseInt(second_structure[1]);
            int xOffsetCap = Integer.parseInt(second_structure[2]);
            int zOffsetCap = Integer.parseInt(second_structure[3]);
            Rotation rotValue = rotationFromInt(Integer.parseInt(second_structure[4]));
            new WorldGenStructure(second_structure[0]).generateWithRotation(this.world, this.rand, new BlockPos(posX + this.rand.nextInt(xOffsetCap), 80 + base_yOffset + extra_yOffset, posZ + this.rand.nextInt(zOffsetCap)), rotValue);
        }
    }
    private static int getAreaNumIdx(String name) {
        int i = 0;
        while (i < name.length() && Character.isDigit(name.charAt(i))) {
            i++;
        }
        return i;
    }
    private String typeToStructureName(String name) {
        int areaNum;
        int areaNumIdx = getAreaNumIdx(name);
        if (areaNumIdx != 0) {
            areaNum = Integer.parseInt(name.substring(0, areaNumIdx));
        } else {
            areaNum = -1;
        }
        String pieceType = name.substring(areaNumIdx);
        switch (areaNum) {
            case 0:
                return mineShaftsToStructName(pieceType);
            case 1:
                return mineShaftPoisToStructName(pieceType);
            case 2:
                return starforgeMinesToStructName(pieceType);
            case 3:
                return galaxyDungeonToStructName(pieceType);
            case TileEntityFusionTable.BOARD_ROWS :
                return twistedTunnelsToStructName(pieceType);
            case 5:
                return overgrownPassagesToStructName(pieceType);
            case TileEntityFusionTable.BOARD_COLUMNS :
                return crystalCreviceToStructName(pieceType);
            case 7:
                return luminescentCavernToStructName(pieceType);
            case 8:
                return archluminescentCavernToStructName(pieceType);
            case 9:
                return blightedLuminescenceToStructName(pieceType);
            case ItemHeadCollector.CHARGE_LIMIT :
                return moltenTwistsToStructName(pieceType);
            case 11:
                return cavityOfTheDeadToStructName(pieceType);
            case 12:
                return manufacturingOutpostToStructName(pieceType);
            case 13:
                return fungalCavernToStructName(pieceType);
            default:
                return "";
        }
    }
    private String fungalCavernToStructName(String pieceType) {
        switch (pieceType) {
            case "fungtun":
                return randomPiece("fungal", "fungalcavern_tunnel", 6);
            case "fungat":
                return randomPiece("fungal", "fungalcavern_tunnel", 72);
            case "funblock":
                return "fungal/fungalcavern_blockage";
            case "fungrot1":
                return "fungal/fungalcavern_turn_1";
            case "fungrot2":
                return "fungal/fungalcavern_turn_2";
            case "fungrot3":
                return "fungal/fungalcavern_turn_3";
            case "fungrot4":
                return "fungal/fungalcavern_turn_4";
            case "fungluc1":
                return "fungal/fungalcavern_lucient1";
            case "fungluc2":
                return "fungal/fungalcavern_lucient2";
            case "fungluc3":
                return "fungal/fungalcavern_lucient3";
            case "fungluc4":
                return "fungal/fungalcavern_lucient4";
            case "fungluc5":
                return "fungal/fungalcavern_lucient5";
            case "fungluc6":
                return "fungal/fungalcavern_lucient6";
            default:
                return "";
        }
    }
    private String manufacturingOutpostToStructName(String pieceType) {
        switch (pieceType) {
            case "manent1":
                return "twistedtunnels/twisted_manufact_entry1";
            case "manent2":
                return "twistedtunnels/twisted_manufact_entry2";
            case "manent3":
                return "twistedtunnels/twisted_manufact_entry3";
            case "manup1":
                return "manufacturing/manufacturing_outpost_p1";
            case "manup2":
                return "manufacturing/manufacturing_outpost_p2";
            case "manup3":
                return "manufacturing/manufacturing_outpost_p3";
            case "manup4":
                return "manufacturing/manufacturing_outpost_p4";
            case "serp1":
                return "manufacturing/serpentine_cave1";
            case "serp2":
                return "manufacturing/serpentine_cave2";
            case "serp3":
                return "manufacturing/serpentine_cave3";
            case "serp4":
                return "manufacturing/serpentine_cave4";
            case "serp5":
                return "manufacturing/serpentine_cave5";
            case "serp6":
                return "manufacturing/serpentine_cave6";
            case "exo1":
                return "manufacturing/exothermite_cave1";
            case "exo2":
                return "manufacturing/exothermite_cave2";
            case "sun1":
                return "manufacturing/sunstone_mine1";
            case "sun2":
                return "manufacturing/sunstone_mine2";
            default:
                return "";
        }
    }
    private String cavityOfTheDeadToStructName(String pieceType) {
        pieceType.hashCode();
        switch (-1) {
        }
        return "";
    }
    private String moltenTwistsToStructName(String pieceType) {
        pieceType.hashCode();
        switch (-1) {
        }
        return "";
    }
    private String luminescentCavernToStructName(String pieceType) {
        switch (pieceType) {
            case "lumentry":
                return "lumcavern/lumcavern_entry";
            case "lumessent":
                return "lumcavern/lumcavern_ess_entry";
            case "lumboss":
                return "lumcavern/lumcavern_boss_entry";
            case "augment":
                return "lumcavern/lumcavern_boss";
            case "lumtree":
                return "lumcavern/lumcavern_lumtree";
            case "lumgate":
                return "lumcavern/lumcavern_archlum_gate";
            case "lumprism1":
                return "lumcavern/lumcavern_prism1";
            case "lumprism2":
                return "lumcavern/lumcavern_prism2";
            case "lumprism3":
                return "lumcavern/lumcavern_prism3";
            case "lumportal":
                return "lumcavern/lumcavern_portal";
            case "lumgen":
                return "lumcavern/lumcavern_powergen";
            case "lumshaft":
                return randomLuminTunnel();
            case "lumturn":
                return randomLuminTurn();
            case "lumtsect":
                return randomLuminTSect();
            case "lumess":
                return randomLuminEss();
            case "lumups":
                return randomLuminUpS();
            case "lumupl":
                return randomLuminUpL();
            case "lumcabove":
                return randomLuminTunnel() + ",lumabove";
            case "lumcbelow":
                return randomLuminTunnel() + ",lumbelow";
            default:
                return "";
        }
    }
    private String crystalCreviceToStructName(String pieceType) {
        switch (pieceType) {
            case "crysent":
                return "crystal/crystal_crevice_entry";
            case "fabricator":
                return "crystal/crystal_crevice_fabricator";
            case "synch1":
                return "crystal/crystal_crevice_synchro1";
            case "synch2":
                return "crystal/crystal_crevice_synchro2";
            case "cryblight":
                return "crystal/crystal_crevice_blight";
            case "crystal":
                return randomCrystalCrevice();
            default:
                return "";
        }
    }
    private String overgrownPassagesToStructName(String pieceType) {
        switch (pieceType) {
            case "ogentry":
                return "overgrown/overgrown_entry";
            case "ogstairs":
                return randomOvergrownStairs();
            case "corcavern":
                return randomCorrosiveCavern();
            case "ogtsect":
                return randomOvergrownTSect();
            case "ogturn":
                return randomOvergrownTurn();
            case "ogfyre":
                return "overgrown/overgrown_giantfyreweed";
            case "corent":
                return "overgrown/corrosive_cavern_entry";
            case "oggen":
                return "overgrown/overgrown_powergen";
            case "ogaura1":
                return "overgrown/overgrown_auradine1";
            case "ogaura2":
                return "overgrown/overgrown_auradine2";
            case "ogaura3":
                return "overgrown/overgrown_auradine3";
            case "ogaura4":
                return "overgrown/overgrown_auradine4";
            default:
                return "";
        }
    }
    private String twistedTunnelsToStructName(String pieceType) {
        switch (pieceType) {
            case "twistx":
                return randomTwistedXTunnel();
            case "twistz":
                return randomTwistedZTunnel();
            case "twistturn1":
                return randomTwistTurnStyle1();
            case "twistturn2":
                return randomTwistTurnStyle2();
            case "twisttxin":
                return "twistedtunnels/twistedtunnel_tsect_xin";
            case "twisttxde":
                return "twistedtunnels/twistedtunnel_tsect_xde";
            case "twisttzin":
                return "twistedtunnels/twistedtunnel_tsect_zin";
            case "twisttzde":
                return "twistedtunnels/twistedtunnel_tsect_zde";
            case "twistslug":
                return randomSlug();
            case "compression":
                return "twistedtunnels/twistedtunnel_compression";
            case "twistgen":
                return "twistedtunnels/twistedtunnel_powergen";
            case "twistdk1":
                return "twistedtunnels/twistedtunnel_dusker1";
            case "twistdk2":
                return "twistedtunnels/twistedtunnel_dusker2";
            case "twistdk3":
                return "twistedtunnels/twistedtunnel_dusker3";
            case "twistphot1":
                return "twistedtunnels/twistedtunnel_phototenzyte1";
            case "twistphot2":
                return "twistedtunnels/twistedtunnel_phototenzyte2";
            case "twistphot3":
                return "twistedtunnels/twistedtunnel_phototenzyte3";
            case "twistphot4":
                return "twistedtunnels/twistedtunnel_phototenzyte4";
            case "twistphot5":
                return "twistedtunnels/twistedtunnel_phototenzyte5";
            case "twistphot6":
                return "twistedtunnels/twistedtunnel_phototenzyte6";
            default:
                return "";
        }
    }
    private String galaxyDungeonToStructName(String pieceType) {
        switch (pieceType) {
            case "galaxy1":
                return "galaxy/galaxy_dungeon_lower1,galaxy1";
            case "galaxy2":
                return "galaxy/galaxy_dungeon_lower2,galaxy2";
            case "galaxy3":
                return "galaxy/galaxy_dungeon_lower3,galaxy3";
            case "galaxy4":
                return "galaxy/galaxy_dungeon_lower4,galaxy4";
            case "galblue":
                return "galaxy/galaxy_basic_blue";
            case "galgreen":
                return "galaxy/galaxy_basic_green";
            case "galyellow":
                return "galaxy/galaxy_basic_yellow";
            case "galpink":
                return "galaxy/galaxy_basic_pink";
            case "galsword":
                return "galaxy/galaxy_elite_sword";
            case "galbomb":
                return "galaxy/galaxy_elite_bomb";
            case "galknife":
                return "galaxy/galaxy_elite_knife";
            case "gallight1":
                return "galaxy/galaxy_lightmaze1";
            case "gallight2":
                return "galaxy/galaxy_lightmaze2";
            case "gallight3":
                return "galaxy/galaxy_lightmaze3";
            case "gallight4":
                return "galaxy/galaxy_lightmaze4";
            case "galterror1":
                return "galaxy/galaxy_shockarena1";
            case "galterror2":
                return "galaxy/galaxy_shockarena2";
            case "galterror3":
                return "galaxy/galaxy_shockarena3";
            case "galterror4":
                return "galaxy/galaxy_shockarena4";
            default:
                return "";
        }
    }
    private String starforgeMinesToStructName(String pieceType) {
        switch (pieceType) {
            case "minescr":
                return "forgemines/forge_mines_corner";
            case "minesgw":
                return "forgemines/forge_mines_door";
            case "minesgwo":
                return "forgemines/forge_mines_dooropen";
            case "minegen":
                return "forgemines/forge_mines_powergen";
            case "mines":
                return randomMines() + ",orepillar";
            case "minesed":
                return randomMinesEdgewall();
            case "mycave1":
                return "forgemines/forgemines_myrite_cave1";
            case "mycave2":
                return "forgemines/forgemines_myrite_cave2";
            case "mycave3":
                return "forgemines/forgemines_myrite_cave3";
            case "mycave4":
                return "forgemines/forgemines_myrite_cave4";
            case "mypass":
                return "forgemines/forgemines_myrite_passage";
            case "mywall":
                return "forgemines/forgemines_myrite_wall";
            case "myentry":
                return "forgemines/forgemines_myrite_entry";
            default:
                return "";
        }
    }
    private String mineShaftPoisToStructName(String pieceType) {
        switch (pieceType) {
            case "starforge":
                return "starforge/starforge_forge";
            case "entry":
                return "forgemines/forgemines_entry";
            case "dark":
                return "twistedtunnels/twistedtunnel_entrytop,twistedtunnel_entrybot";
            case "sunderbr":
                return "starforge/sundertree2";
            case "sundertr":
                return "starforge/sundertree4";
            case "sunderbl":
                return "starforge/sundertree1";
            case "sundertl":
                return "starforge/sundertree3,hiveent";
            case "hive1":
                return "starforge/sunder_hive_bottom_1,hive1";
            case "hive2":
                return "starforge/sunder_hive_bottom_2,hive2";
            case "hive3":
                return "starforge/sunder_hive_bottom_3,hive3";
            case "hive4":
                return "starforge/sunder_hive_bottom_4,hive4";
            case "clovm1":
                return "starforge/clovinite_mine1";
            case "clovm2":
                return "starforge/clovinite_mine2";
            case "clovm3":
                return "starforge/clovinite_mine3";
            case "clovm4":
                return "starforge/clovinite_mine4";
            case "cata1":
                return "starforge/catatonite_mine1";
            case "cata2":
                return "starforge/catatonite_mine2";
            case "cata3":
                return "starforge/catatonite_mine3";
            case "cata4":
                return "starforge/catatonite_mine4";
            default:
                return "";
        }
    }
    private String mineShaftsToStructName(String pieceType) {
        switch (pieceType) {
            case "tsect":
                return randomMinesTSect();
            case "end":
                return randomMinesEnd();
            case "turn":
                return randomMinesTurn();
            case "shaft":
                return randomMinesShaft();
            case "stairs":
                return randomMinesStairs();
            case "secur1":
                return "starforge/forgeclearance1";
            case "secur3":
                return "starforge/forgeclearance3";
            case "forgegen":
                return "starforge/forgemines_powergen";
            case "forgeterm":
                return "starforge/forgemines_powerterminal";
            case "rworm":
                return randomPiece("forgemines", "forgemines_rockworm", 2);
            default:
                return "";
        }
    }
    private String archluminescentCavernToStructName(String pieceType) {
        switch (pieceType) {
            case "archgate":
                return "lumcavern/lumcavern_archlum_top";
            case "archentry":
                return "archcavern/archcavern_entry";
            case "archshaft":
                return randomPiece("archcavern", "archcavern_tunnel", 24);
            case "archthin":
                return randomPiece("archcavern", "archcavern_thintunnel", 18);
            case "archblock":
                return randomPiece("archcavern", "archcavern_archblock", 4);
            case "archabs":
                return randomPiece("archcavern", "archcavern_tunnel", 24) + ",archaboves";
            case "archbls":
                return randomPiece("archcavern", "archcavern_tunnel", 24) + ",archbelows";
            case "archabt":
                return randomPiece("archcavern", "archcavern_thintunnel", 18) + ",archabovet";
            case "archblt":
                return randomPiece("archcavern", "archcavern_thintunnel", 18) + ",archbelowt";
            case "archwisp1":
                return "archcavern/archcavern_wisp1";
            case "archwisp2":
                return "archcavern/archcavern_wisp2";
            case "archwisp3":
                return "archcavern/archcavern_wisp3";
            case "archwisp4":
                return "archcavern/archcavern_wisp4";
            case "archbirth":
                return "archcavern/archcavern_rebirth";
            case "archport":
                return "archcavern/archcavern_portal";
            case "archpipe":
                return "archcavern/archcavern_pipe";
            case "archinc1":
                return "archcavern/archcavern_incandescite_lower1,archinc1";
            case "archinc2":
                return "archcavern/archcavern_incandescite_lower2,archinc2";
            case "archinc3":
                return "archcavern/archcavern_incandescite_lower3,archinc3";
            case "archinc4":
                return "archcavern/archcavern_incandescite_lower4,archinc4";
            case "archinc5":
                return "archcavern/archcavern_incandescite_lower5,archinc5";
            case "archinc6":
                return "archcavern/archcavern_incandescite_lower6,archinc6";
            case "archinc7":
                return "archcavern/archcavern_incandescite_lower7,archinc7";
            case "archinc8":
                return "archcavern/archcavern_incandescite_lower8,archinc8";
            case "archinc9":
                return "archcavern/archcavern_incandescite_lower9,archinc9";
            case "archsu":
                return randomPiece("archcavern", "archcavern_smallup", 9) + ",archsu";
            case "archsr":
                return randomPiece("archcavern", "archcavern_smallup", 9) + ",archsr";
            case "archsl":
                return randomPiece("archcavern", "archcavern_smallup", 9) + ",archsl";
            case "archsd":
                return randomPiece("archcavern", "archcavern_smallup", 9) + ",archsd";
            case "archlu":
                return randomPiece("archcavern", "archcavern_largeup", 9) + ",archlu";
            case "archll":
                return randomPiece("archcavern", "archcavern_largeup", 9) + ",archll";
            case "archld":
                return randomPiece("archcavern", "archcavern_largeup", 9) + ",archld";
            case "archlr":
                return randomPiece("archcavern", "archcavern_largeup", 9) + ",archlr";
            case "archmu":
                if (this.rand.nextBoolean()) {
                    return randomPiece("archcavern", "archcavern_smallup", 9) + ",archmlu";
                }
                return randomPiece("archcavern", "archcavern_largeup", 9) + ",archmsu";
            case "archml":
                if (this.rand.nextBoolean()) {
                    return randomPiece("archcavern", "archcavern_smallup", 9) + ",archmll";
                }
                return randomPiece("archcavern", "archcavern_largeup", 9) + ",archmsl";
            case "archmd":
                if (this.rand.nextBoolean()) {
                    return randomPiece("archcavern", "archcavern_smallup", 9) + ",archmld";
                }
                return randomPiece("archcavern", "archcavern_largeup", 9) + ",archmsd";
            case "archmr":
                if (this.rand.nextBoolean()) {
                    return randomPiece("archcavern", "archcavern_smallup", 9) + ",archmlr";
                }
                return randomPiece("archcavern", "archcavern_largeup", 9) + ",archmsr";
            default:
                return "";
        }
    }
    private String blightedLuminescenceToStructName(String pieceType) {
        switch (pieceType) {
            case "bligentry":
                return "blightcavern/blightcavern_entry";
            case "bligdark":
                return "blightcavern/blightcavern_darktree";
            case "bligcrush":
                return "blightcavern/blightcavern_crusher";
            case "bligshaft":
                return randomPiece("blightcavern", "blightcavern_tunnel", 48);
            case "bligthin":
                return randomPiece("blightcavern", "blightcavern_thintunnel", 36);
            case "bligblock":
                return randomPiece("blightcavern", "blightcavern_blightblock", 4);
            case "bligabs":
                return randomPiece("blightcavern", "blightcavern_tunnel", 48) + ",bligaboves";
            case "bligbls":
                return randomPiece("blightcavern", "blightcavern_tunnel", 48) + ",bligbelows";
            case "bligabt":
                return randomPiece("blightcavern", "blightcavern_thintunnel", 36) + ",bligabovet";
            case "bligblt":
                return randomPiece("blightcavern", "blightcavern_thintunnel", 36) + ",bligbelowt";
            case "bligess":
                return randomPiece("blightcavern", "blightcavern_ess_seg", 12);
            case "bligessent":
                return "blightcavern/blightcavern_ess_entry";
            case "bligsap":
                return "blightcavern/blightcavern_sap";
            case "bligport":
                return "blightcavern/blightcavern_mouth";
            case "bligsec":
                return "blightcavern/blightcavern_secu3";
            case "bligsu":
                return randomPiece("blightcavern", "blightcavern_smallup", 18) + ",bligsu";
            case "bligsr":
                return randomPiece("blightcavern", "blightcavern_smallup", 18) + ",bligsr";
            case "bligsl":
                return randomPiece("blightcavern", "blightcavern_smallup", 18) + ",bligsl";
            case "bligsd":
                return randomPiece("blightcavern", "blightcavern_smallup", 18) + ",bligsd";
            case "bliglu":
                return randomPiece("blightcavern", "blightcavern_largeup", 18) + ",bliglu";
            case "bligll":
                return randomPiece("blightcavern", "blightcavern_largeup", 18) + ",bligll";
            case "bligld":
                return randomPiece("blightcavern", "blightcavern_largeup", 18) + ",bligld";
            case "bliglr":
                return randomPiece("blightcavern", "blightcavern_largeup", 18) + ",bliglr";
            case "bligmu":
                if (this.rand.nextBoolean()) {
                    return randomPiece("blightcavern", "blightcavern_smallup", 18) + ",bligmlu";
                }
                return randomPiece("blightcavern", "blightcavern_largeup", 18) + ",bligmsu";
            case "bligml":
                if (this.rand.nextBoolean()) {
                    return randomPiece("blightcavern", "blightcavern_smallup", 18) + ",bligmll";
                }
                return randomPiece("blightcavern", "blightcavern_largeup", 18) + ",bligmsl";
            case "bligmd":
                if (this.rand.nextBoolean()) {
                    return randomPiece("blightcavern", "blightcavern_smallup", 18) + ",bligmld";
                }
                return randomPiece("blightcavern", "blightcavern_largeup", 18) + ",bligmsd";
            case "bligmr":
                if (this.rand.nextBoolean()) {
                    return randomPiece("blightcavern", "blightcavern_smallup", 18) + ",bligmlr";
                }
                return randomPiece("blightcavern", "blightcavern_largeup", 18) + ",bligmsr";
            case "bligpow1":
                return "blightcavern/blightcavern_powercore_1";
            case "bligpow2":
                return "blightcavern/blightcavern_powercore_2";
            case "bligpow3":
                return "blightcavern/blightcavern_powercore_3";
            case "bligpow4":
                return "blightcavern/blightcavern_powercore_4";
            case "bligpow5":
                return "blightcavern/blightcavern_powercore_5";
            case "bligliblo":
                return "blightcavern/blightcavern_lightblockage";
            case "bligboss":
                return "blightcavern/blightcavern_bossgate";
            default:
                return "";
        }
    }
    private String extraStructureDetail(String name) {
        switch (name) {
            case "orepillar":
                return randomOrePillar() + ",0,5,5,9";
            case "twistedtunnel_entrybot":
                return "twistedtunnels/twistedtunnel_entrybot,-32,1,1,-1";
            case "lumabove":
                return randomLuminTunnel() + ",10,1,1,8";
            case "lumbelow":
                return randomLuminTunnel() + ",-10,1,1,8";
            case "galaxy1":
                return "galaxy/galaxy_dungeon_upper1,19,1,1,0";
            case "galaxy2":
                return "galaxy/galaxy_dungeon_upper2,19,1,1,0";
            case "galaxy3":
                return "galaxy/galaxy_dungeon_upper3,19,1,1,0";
            case "galaxy4":
                return "galaxy/galaxy_dungeon_upper4,19,1,1,0";
            case "hiveent":
                return "starforge/sunder_hive_entry,-13,1,1,0";
            case "hive1":
                return "starforge/sunder_hive_top_1,32,1,1,0";
            case "hive2":
                return "starforge/sunder_hive_top_2,32,1,1,0";
            case "hive3":
                return "starforge/sunder_hive_top_3,32,1,1,0";
            case "hive4":
                return "starforge/sunder_hive_top_4,32,1,1,0";
            case "archsu":
                return randomPiece("archcavern", "archcavern_smalldown", 9) + ",20,1,1,1";
            case "archsl":
                return randomPiece("archcavern", "archcavern_smalldown", 9) + ",20,1,1,0";
            case "archsr":
                return randomPiece("archcavern", "archcavern_smalldown", 9) + ",20,1,1,2";
            case "archsd":
                return randomPiece("archcavern", "archcavern_smalldown", 9) + ",20,1,1,-1";
            case "archlu":
                return randomPiece("archcavern", "archcavern_largedown", 9) + ",30,1,1,1";
            case "archll":
                return randomPiece("archcavern", "archcavern_largedown", 9) + ",30,1,1,0";
            case "archlr":
                return randomPiece("archcavern", "archcavern_largedown", 9) + ",30,1,1,2";
            case "archld":
                return randomPiece("archcavern", "archcavern_largedown", 9) + ",30,1,1,-1";
            case "archmsu":
                return randomPiece("archcavern", "archcavern_smalldown", 9) + ",30,1,1,1";
            case "archmsl":
                return randomPiece("archcavern", "archcavern_smalldown", 9) + ",30,1,1,0";
            case "archmsr":
                return randomPiece("archcavern", "archcavern_smalldown", 9) + ",30,1,1,2";
            case "archmsd":
                return randomPiece("archcavern", "archcavern_smalldown", 9) + ",30,1,1,-1";
            case "archmlu":
                return randomPiece("archcavern", "archcavern_largedown", 9) + ",20,1,1,1";
            case "archmll":
                return randomPiece("archcavern", "archcavern_largedown", 9) + ",20,1,1,0";
            case "archmlr":
                return randomPiece("archcavern", "archcavern_largedown", 9) + ",20,1,1,2";
            case "archmld":
                return randomPiece("archcavern", "archcavern_largedown", 9) + ",20,1,1,-1";
            case "archaboves":
                return randomPiece("archcavern", "archcavern_tunnel", 24) + ",20,1,1,0";
            case "archbelows":
                return randomPiece("archcavern", "archcavern_tunnel", 24) + ",-20,1,1,0";
            case "archabovet":
                return randomPiece("archcavern", "archcavern_thintunnel", 18) + ",20,1,1,0";
            case "archbelowt":
                return randomPiece("archcavern", "archcavern_thintunnel", 18) + ",-20,1,1,0";
            case "bligsu":
                return randomPiece("blightcavern", "blightcavern_smalldown", 18) + ",20,1,1,1";
            case "bligsl":
                return randomPiece("blightcavern", "blightcavern_smalldown", 18) + ",20,1,1,0";
            case "bligsr":
                return randomPiece("blightcavern", "blightcavern_smalldown", 18) + ",20,1,1,2";
            case "bligsd":
                return randomPiece("blightcavern", "blightcavern_smalldown", 18) + ",20,1,1,-1";
            case "bliglu":
                return randomPiece("blightcavern", "blightcavern_largedown", 18) + ",30,1,1,1";
            case "bligll":
                return randomPiece("blightcavern", "blightcavern_largedown", 18) + ",30,1,1,0";
            case "bliglr":
                return randomPiece("blightcavern", "blightcavern_largedown", 18) + ",30,1,1,2";
            case "bligld":
                return randomPiece("blightcavern", "blightcavern_largedown", 18) + ",30,1,1,-1";
            case "bligmsu":
                return randomPiece("blightcavern", "blightcavern_smalldown", 18) + ",30,1,1,1";
            case "bligmsl":
                return randomPiece("blightcavern", "blightcavern_smalldown", 18) + ",30,1,1,0";
            case "bligmsr":
                return randomPiece("blightcavern", "blightcavern_smalldown", 18) + ",30,1,1,2";
            case "bligmsd":
                return randomPiece("blightcavern", "blightcavern_smalldown", 18) + ",30,1,1,-1";
            case "bligmlu":
                return randomPiece("blightcavern", "blightcavern_largedown", 18) + ",20,1,1,1";
            case "bligmll":
                return randomPiece("blightcavern", "blightcavern_largedown", 18) + ",20,1,1,0";
            case "bligmlr":
                return randomPiece("blightcavern", "blightcavern_largedown", 18) + ",20,1,1,2";
            case "bligmld":
                return randomPiece("blightcavern", "blightcavern_largedown", 18) + ",20,1,1,-1";
            case "bligaboves":
                return randomPiece("blightcavern", "blightcavern_tunnel", 48) + ",20,1,1,0";
            case "bligbelows":
                return randomPiece("blightcavern", "blightcavern_tunnel", 48) + ",-20,1,1,0";
            case "bligabovet":
                return randomPiece("blightcavern", "blightcavern_thintunnel", 36) + ",20,1,1,0";
            case "bligbelowt":
                return randomPiece("blightcavern", "blightcavern_thintunnel", 36) + ",-20,1,1,0";
            case "archinc1":
                return "archcavern/archcavern_incandescite_upper1,30,1,1,0";
            case "archinc2":
                return "archcavern/archcavern_incandescite_upper2,30,1,1,0";
            case "archinc3":
                return "archcavern/archcavern_incandescite_upper3,30,1,1,0";
            case "archinc4":
                return "archcavern/archcavern_incandescite_upper4,30,1,1,0";
            case "archinc5":
                return "archcavern/archcavern_incandescite_upper5,30,1,1,0";
            case "archinc6":
                return "archcavern/archcavern_incandescite_upper6,30,1,1,0";
            case "archinc7":
                return "archcavern/archcavern_incandescite_upper7,30,1,1,0";
            case "archinc8":
                return "archcavern/archcavern_incandescite_upper8,30,1,1,0";
            case "archinc9":
                return "archcavern/archcavern_incandescite_upper9,30,1,1,0";
            default:
                return "";
        }
    }
    private String randomPiece(String folder, String name, int num) {
        int pick = this.rand.nextInt(num);
        return folder + "/" + name + (pick + 1);
    }
    private String randomSlug() {
        int game_pick = this.rand.nextInt(4);
        return "twistedtunnels/twisted_tunnel_rockslug" + (game_pick + 1);
    }
    private String randomLuminTunnel() {
        int game_pick = this.rand.nextInt(36);
        return "lumcavern/lumcavern_tunnel" + (game_pick + 1);
    }
    private String randomLuminTurn() {
        int game_pick = this.rand.nextInt(12);
        return "lumcavern/lumcavern_turn" + (game_pick + 1);
    }
    private String randomLuminTSect() {
        int game_pick = this.rand.nextInt(16);
        return "lumcavern/lumcavern_tsect" + (game_pick + 1);
    }
    private String randomLuminEss() {
        int game_pick = this.rand.nextInt(12);
        return "lumcavern/lumcavern_ess_seg" + (game_pick + 1);
    }
    private String randomLuminUpS() {
        int game_pick = this.rand.nextInt(10);
        return "lumcavern/lumcavern_up_small" + (game_pick + 1);
    }
    private String randomLuminUpL() {
        int game_pick = this.rand.nextInt(10);
        return "lumcavern/lumcavern_up_big" + (game_pick + 1);
    }
    private String randomOvergrownTurn() {
        int game_pick = this.rand.nextInt(2);
        return "overgrown/overgrown_turn_" + (game_pick + 1);
    }
    private String randomTwistTurnStyle1() {
        int game_pick = this.rand.nextInt(2);
        return "twistedtunnels/twistedtunnel_turn_1_" + (game_pick + 1);
    }
    private String randomTwistTurnStyle2() {
        int game_pick = this.rand.nextInt(2);
        return "twistedtunnels/twistedtunnel_turn_2_" + (game_pick + 1);
    }
    private String randomTwistedZTunnel() {
        int game_pick = this.rand.nextInt(4);
        return "twistedtunnels/twistedtunnel_zshaft" + (game_pick + 1);
    }
    private String randomTwistedXTunnel() {
        int game_pick = this.rand.nextInt(4);
        return "twistedtunnels/twistedtunnel_xshaft" + (game_pick + 1);
    }
    private String randomOvergrownTSect() {
        int game_pick = this.rand.nextInt(3);
        return "overgrown/overgrown_tsect_" + (game_pick + 1);
    }
    private String randomCrystalCrevice() {
        int game_pick = this.rand.nextInt(9);
        return "crystal/crystal_crevice_" + (game_pick + 1);
    }
    private String randomCorrosiveCavern() {
        int game_pick = this.rand.nextInt(4);
        return "overgrown/corrosive_cavern_" + (game_pick + 1);
    }
    private String randomOvergrownStairs() {
        int game_pick = this.rand.nextInt(9);
        return "overgrown/overgrown_stairs" + (game_pick + 1);
    }
    private String randomMinesStairs() {
        int game_pick = this.rand.nextInt(6);
        return "forgemines/forge_mines_stairs" + (game_pick + 1);
    }
    private String randomMinesEdgewall() {
        int game_pick = this.rand.nextInt(10);
        return "forgemines/forge_mines_wall" + (game_pick + 1);
    }
    private String randomOrePillar() {
        int game_pick = this.rand.nextInt(10);
        return "forgemines/forgemines_orepillar" + (game_pick + 1);
    }
    private String randomMines() {
        int game_pick = this.rand.nextInt(16);
        return "forgemines/forge_mines" + (game_pick + 1);
    }
    private String randomMinesTSect() {
        int game_pick = this.rand.nextInt(2);
        return "forgemines/forgeminestsect" + (game_pick + 1);
    }
    private String randomMinesEnd() {
        int game_pick = this.rand.nextInt(3);
        return "forgemines/forgeminesend" + (game_pick + 1);
    }
    private String randomMinesTurn() {
        int game_pick = this.rand.nextInt(3);
        return "forgemines/forgeminesturn" + (game_pick + 1);
    }
    private String randomMinesShaft() {
        int game_pick = this.rand.nextInt(8);
        return "forgemines/forgeminesshaft" + (game_pick + 1);
    }
    private Rotation rotationFromInt(int rotation) {
        switch (rotation) {
            case -1:
                return Rotation.COUNTERCLOCKWISE_90;
            case 0:
                return Rotation.NONE;
            case 1:
                return Rotation.CLOCKWISE_90;
            case 2:
                return Rotation.CLOCKWISE_180;
            case 3:
            case TileEntityFusionTable.BOARD_ROWS :
            case 5:
            case TileEntityFusionTable.BOARD_COLUMNS :
            default:
                return Rotation.NONE;
            case 7:
                return this.rand.nextBoolean() ? Rotation.CLOCKWISE_90 : Rotation.COUNTERCLOCKWISE_90;
            case 8:
                return this.rand.nextBoolean() ? Rotation.NONE : Rotation.CLOCKWISE_180;
            case 9:
                return rotationFromInt(this.rand.nextInt(4) - 1);
        }
    }
    public Chunk func_185932_a(int x, int z) {
        this.rand.setSeed((((long) x) * 341873128712L) + (((long) z) * 132897987541L));
        this.biomesForGeneration = this.world.func_72959_q().func_76933_b(this.biomesForGeneration, x * 16, z * 16, 16, 16);
        ChunkPrimer primer = new ChunkPrimer();
        Chunk chunk = new Chunk(this.world, primer, x, z);
        byte[] abyte = chunk.func_76605_m();
        for (int i = 0; i < abyte.length; i++) {
            abyte[i] = (byte) Biome.func_185362_a(this.biomesForGeneration[i]);
        }
        chunk.func_76603_b();
        return chunk;
    }
    public List<Biome.SpawnListEntry> func_177458_a(EnumCreatureType creatureType, BlockPos pos) {
        Biome biome = this.world.func_72959_q().func_180631_a(pos);
        if (biome != null) {
            return biome.func_76747_a(creatureType);
        }
        return null;
    }
    public boolean func_185933_a(Chunk chunkIn, int chunkX, int chunkZ) {
        return false;
    }
    public void func_180514_a(Chunk p_180514_1_, int x, int z) {
    }
    public boolean func_193414_a(World worldIn, String structureName, BlockPos pos) {
        return false;
    }
    public BlockPos func_180513_a(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }
}
