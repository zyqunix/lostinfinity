package xol.lostinfinity.gui;
import java.util.Arrays;
import java.util.Optional;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import xol.lostinfinity.block.basic.BlockBasicGui;
import xol.lostinfinity.block.tileentity.TileEntityChemistryTable;
import xol.lostinfinity.block.tileentity.TileEntityCombustionEngine;
import xol.lostinfinity.block.tileentity.TileEntityCthulhuSpawner;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.block.tileentity.TileEntityGearbox;
import xol.lostinfinity.block.tileentity.TileEntityGrinder;
import xol.lostinfinity.block.tileentity.TileEntityNebulousBeacon;
import xol.lostinfinity.block.tileentity.TileEntityNicroniumInfuser;
import xol.lostinfinity.block.tileentity.TileEntityRainfallGenerator;
import xol.lostinfinity.block.tileentity.TileEntitySapEvaporator;
import xol.lostinfinity.block.tileentity.TileEntityShipmentFiller;
import xol.lostinfinity.block.tileentity.TileEntityWeldingChamber;
import xol.lostinfinity.gui.containers.ContainerAugmentor;
import xol.lostinfinity.gui.containers.ContainerChemistryTable;
import xol.lostinfinity.gui.containers.ContainerCombustionEngine;
import xol.lostinfinity.gui.containers.ContainerCompressionTable;
import xol.lostinfinity.gui.containers.ContainerCthulhuSpawner;
import xol.lostinfinity.gui.containers.ContainerDeconstructor;
import xol.lostinfinity.gui.containers.ContainerFabricationStation;
import xol.lostinfinity.gui.containers.ContainerFossilCombiner;
import xol.lostinfinity.gui.containers.ContainerFusionTable;
import xol.lostinfinity.gui.containers.ContainerGearbox;
import xol.lostinfinity.gui.containers.ContainerGrinder;
import xol.lostinfinity.gui.containers.ContainerItemCharger;
import xol.lostinfinity.gui.containers.ContainerModulator;
import xol.lostinfinity.gui.containers.ContainerModuleCreator;
import xol.lostinfinity.gui.containers.ContainerNebulousBeacon;
import xol.lostinfinity.gui.containers.ContainerNicroniumInfuser;
import xol.lostinfinity.gui.containers.ContainerPickChargingTable;
import xol.lostinfinity.gui.containers.ContainerPortableBeacon;
import xol.lostinfinity.gui.containers.ContainerRainfallGenerator;
import xol.lostinfinity.gui.containers.ContainerSapEvaporator;
import xol.lostinfinity.gui.containers.ContainerShipmentFiller;
import xol.lostinfinity.gui.containers.ContainerSupplyDeposit;
import xol.lostinfinity.gui.containers.ContainerSupplyStore;
import xol.lostinfinity.gui.containers.ContainerWeldingChamber;
import xol.lostinfinity.gui.guis.GuiAugmentor;
import xol.lostinfinity.gui.guis.GuiChemistryTable;
import xol.lostinfinity.gui.guis.GuiCombustionEngine;
import xol.lostinfinity.gui.guis.GuiCompressionTable;
import xol.lostinfinity.gui.guis.GuiCthulhuSpawner;
import xol.lostinfinity.gui.guis.GuiDeconstructor;
import xol.lostinfinity.gui.guis.GuiFabricationStation;
import xol.lostinfinity.gui.guis.GuiFossilCombiner;
import xol.lostinfinity.gui.guis.GuiFusionTable;
import xol.lostinfinity.gui.guis.GuiGearbox;
import xol.lostinfinity.gui.guis.GuiGrinder;
import xol.lostinfinity.gui.guis.GuiItemCharger;
import xol.lostinfinity.gui.guis.GuiModulator;
import xol.lostinfinity.gui.guis.GuiModuleCreator;
import xol.lostinfinity.gui.guis.GuiNebulousBeacon;
import xol.lostinfinity.gui.guis.GuiNicroniumInfuser;
import xol.lostinfinity.gui.guis.GuiPickChargingTable;
import xol.lostinfinity.gui.guis.GuiPortableBeacon;
import xol.lostinfinity.gui.guis.GuiRainfallGenerator;
import xol.lostinfinity.gui.guis.GuiSapEvaporator;
import xol.lostinfinity.gui.guis.GuiShipmentFiller;
import xol.lostinfinity.gui.guis.GuiSupplyDeposit;
import xol.lostinfinity.gui.guis.GuiSupplyStore;
import xol.lostinfinity.gui.guis.GuiWeldingChamber;
import xol.lostinfinity.item.weapon.ItemHeadCollector;
public class GuiHandler implements IGuiHandler {
    private static final GuiHandler guiHandler = new GuiHandler();
    public static GuiHandler getInstance() {
        return guiHandler;
    }
    private Optional<RegisteredGuis> getGuiFor(int id) {
        return Arrays.stream(RegisteredGuis.values()).filter(it -> {
            return it.id == id;
        }).findFirst();
    }
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        Optional<RegisteredGuis> gui = getGuiFor(ID);
        Object result = null;
        if (gui.isPresent()) {
            RegisteredGuis g = gui.get();
            if (g.requiresBlock) {
                BlockPos xyz = new BlockPos(x, y, z);
                IBlockState state = world.func_180495_p(xyz);
                if (state.func_177230_c() instanceof BlockBasicGui) {
                    switch (AnonymousClass1.$SwitchMap$xol$lostinfinity$gui$GuiHandler$RegisteredGuis[g.ordinal()]) {
                        case 1:
                            result = new ContainerModulator(player.field_71071_by, world, xyz, state.func_177230_c());
                            break;
                        case 2:
                            result = new ContainerPickChargingTable(player.field_71071_by, world, xyz, state.func_177230_c());
                            break;
                        case 3:
                            result = new ContainerFabricationStation(player.field_71071_by, world, xyz, state.func_177230_c());
                            break;
                        case TileEntityFusionTable.BOARD_ROWS :
                            result = new ContainerCompressionTable(player.field_71071_by, world, xyz, state.func_177230_c(), world.func_175625_s(xyz));
                            break;
                        case 5:
                            result = new ContainerAugmentor(player.field_71071_by);
                            break;
                        case TileEntityFusionTable.BOARD_COLUMNS :
                            result = new ContainerSapEvaporator(player.field_71071_by, (TileEntitySapEvaporator) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 7:
                            result = new ContainerNicroniumInfuser(player.field_71071_by, (TileEntityNicroniumInfuser) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 8:
                            result = new ContainerModuleCreator(player.field_71071_by, world, xyz, state.func_177230_c());
                            break;
                        case 9:
                            result = new ContainerRainfallGenerator(player.field_71071_by, (TileEntityRainfallGenerator) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case ItemHeadCollector.CHARGE_LIMIT :
                            result = new ContainerWeldingChamber(player.field_71071_by, (TileEntityWeldingChamber) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 11:
                            result = new ContainerFusionTable(player.field_71071_by, (TileEntityFusionTable) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 12:
                            result = new ContainerFossilCombiner(player.field_71071_by, world, xyz, state.func_177230_c());
                            break;
                        case 13:
                            result = new ContainerNebulousBeacon(player.field_71071_by, (TileEntityNebulousBeacon) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 14:
                            result = new ContainerChemistryTable(player.field_71071_by, (TileEntityChemistryTable) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 15:
                            result = new ContainerShipmentFiller(player.field_71071_by, (TileEntityShipmentFiller) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 16:
                            result = new ContainerDeconstructor(player.field_71071_by);
                            break;
                        case 17:
                            result = new ContainerGearbox(player.field_71071_by, (TileEntityGearbox) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 18:
                            result = new ContainerGrinder(player.field_71071_by, (TileEntityGrinder) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 19:
                            result = new ContainerCombustionEngine(player.field_71071_by, (TileEntityCombustionEngine) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                    }
                } else {
                    switch (g) {
                        case CTHULHU_SPAWNER:
                            result = new ContainerCthulhuSpawner(player.field_71071_by, (TileEntityCthulhuSpawner) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                    }
                }
            } else {
                switch (AnonymousClass1.$SwitchMap$xol$lostinfinity$gui$GuiHandler$RegisteredGuis[g.ordinal()]) {
                    case 21:
                        result = new ContainerItemCharger(player.field_71071_by, world, player.field_71071_by.func_70448_g());
                        break;
                    case 22:
                        result = new ContainerPortableBeacon(player.field_71071_by, world, player.field_71071_by.func_70448_g());
                        break;
                    case 23:
                        result = new ContainerSupplyStore(player.field_71071_by);
                        break;
                    case TileEntityFusionTable.BOARD_SIZE :
                        result = new ContainerSupplyDeposit(player.field_71071_by);
                        break;
                }
            }
        }
        return result;
    }
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        Optional<RegisteredGuis> gui = getGuiFor(ID);
        Object result = null;
        if (gui.isPresent()) {
            RegisteredGuis g = gui.get();
            if (g.requiresBlock) {
                BlockPos xyz = new BlockPos(x, y, z);
                IBlockState state = world.func_180495_p(xyz);
                if (state.func_177230_c() instanceof BlockBasicGui) {
                    switch (AnonymousClass1.$SwitchMap$xol$lostinfinity$gui$GuiHandler$RegisteredGuis[g.ordinal()]) {
                        case 1:
                            result = new GuiModulator(player.field_71071_by, world, xyz, state.func_177230_c());
                            break;
                        case 2:
                            result = new GuiPickChargingTable(player.field_71071_by, world, xyz, state.func_177230_c());
                            break;
                        case 3:
                            result = new GuiFabricationStation(player.field_71071_by, world, xyz, state.func_177230_c());
                            break;
                        case TileEntityFusionTable.BOARD_ROWS :
                            result = new GuiCompressionTable(player.field_71071_by, world, xyz, state.func_177230_c(), world.func_175625_s(xyz));
                            break;
                        case 5:
                            result = new GuiAugmentor(player.field_71071_by);
                            break;
                        case TileEntityFusionTable.BOARD_COLUMNS :
                            result = new GuiSapEvaporator(player.field_71071_by, (TileEntitySapEvaporator) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 7:
                            result = new GuiNicroniumInfuser(player.field_71071_by, (TileEntityNicroniumInfuser) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 8:
                            result = new GuiModuleCreator(player.field_71071_by, world, xyz, state.func_177230_c());
                            break;
                        case 9:
                            result = new GuiRainfallGenerator(player.field_71071_by, (TileEntityRainfallGenerator) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case ItemHeadCollector.CHARGE_LIMIT :
                            result = new GuiWeldingChamber(player.field_71071_by, (TileEntityWeldingChamber) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 11:
                            result = new GuiFusionTable(player.field_71071_by, (TileEntityFusionTable) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 12:
                            result = new GuiFossilCombiner(player.field_71071_by, world, xyz, state.func_177230_c());
                            break;
                        case 13:
                            result = new GuiNebulousBeacon(player.field_71071_by, (TileEntityNebulousBeacon) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 14:
                            result = new GuiChemistryTable(player.field_71071_by, (TileEntityChemistryTable) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 15:
                            result = new GuiShipmentFiller(player.field_71071_by, (TileEntityShipmentFiller) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 16:
                            result = new GuiDeconstructor(player.field_71071_by);
                            break;
                        case 17:
                            result = new GuiGearbox(player.field_71071_by, (TileEntityGearbox) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 18:
                            result = new GuiGrinder(player.field_71071_by, (TileEntityGrinder) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 19:
                            result = new GuiCombustionEngine(player.field_71071_by, (TileEntityCombustionEngine) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                        case 20:
                            result = new GuiCthulhuSpawner(player.field_71071_by, (TileEntityCthulhuSpawner) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                    }
                } else {
                    switch (g) {
                        case CTHULHU_SPAWNER:
                            result = new GuiCthulhuSpawner(player.field_71071_by, (TileEntityCthulhuSpawner) world.func_175625_s(new BlockPos(x, y, z)));
                            break;
                    }
                }
            } else {
                switch (AnonymousClass1.$SwitchMap$xol$lostinfinity$gui$GuiHandler$RegisteredGuis[g.ordinal()]) {
                    case 21:
                        result = new GuiItemCharger(player.field_71071_by, world);
                        break;
                    case 22:
                        result = new GuiPortableBeacon(player.field_71071_by, world);
                        break;
                    case 23:
                        result = new GuiSupplyStore(player.field_71071_by);
                        break;
                    case TileEntityFusionTable.BOARD_SIZE :
                        result = new GuiSupplyDeposit(player.field_71071_by);
                        break;
                }
            }
        }
        return result;
    }
    public enum RegisteredGuis {
        MODULATOR(69, true),
        PICKAXE_CHARGETABLE(70, true),
        FABRICATION_STATION(71, true),
        COMPRESSION_TABLE(72, true),
        ITEM_CHARGER(73, false),
        PORTABLE_BEACON(74, false),
        AUGMENTOR(75, true),
        ESSENCE_CHAMBER(76, false),
        SAP_EVAPORATOR(77, true),
        NICRONIUM_INFUSER(78, true),
        MODULE_CREATOR(79, true),
        RAINFALL_GENERATOR(80, true),
        WELDING_CHAMBER(81, true),
        FUSION_TABLE(82, true),
        FOSSIL_COMBINER(83, true),
        NEBULOUS_BEACON(84, true),
        CHEMISTRY_TABLE(85, true),
        SHIPMENT_FILLER(86, true),
        SUPPLY_STORE(87, false),
        SUPPLY_DEPOSIT(88, false),
        DECONSTRUCTOR(89, true),
        CTHULHU_SPAWNER(90, true),
        COMBUSTION_ENGINE(91, true),
        GRINDER(92, true),
        GEARBOX(93, true);
        private final int id;
        final boolean requiresBlock;
        public int getId() {
            return this.id;
        }
        RegisteredGuis(int id, boolean requiresBlock) {
            this.id = id;
            this.requiresBlock = requiresBlock;
        }
    }
}
