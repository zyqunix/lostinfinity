package xol.lostinfinity.block.tileentity;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;
public class TileEntityDrillConsole extends TileEntity {
    private static final Vec3i forwardDir = new Vec3i(-1, 0, 0);
    private static final Vec3i rightDir = new Vec3i(0, 0, -1);
    private boolean active = false;
    private ArrayList<BlockPos> horizontalBeam = new ArrayList<>();
    private ArrayList<BlockPos> verticalBeam = new ArrayList<>();
    private BlockPos drillPos = null;
    private int drillHeight = 0;
    private boolean drilling = false;
    public void adjustDrill(boolean down) {
        Block drillBeamBlock = BlockInit.drillShaft;
        Block drillHeadBlock = BlockInit.drillHead;
        Block oreBlock = BlockInit.lucientOre;
        BlockPos drillHeadPos = this.drillPos.func_177982_a(0, -this.drillHeight, 0);
        boolean didMine = false;
        if (down) {
            if (this.field_145850_b.func_175623_d(drillHeadPos.func_177977_b())) {
                this.drillHeight++;
            } else {
                didMine = true;
                BlockPos checkPos = drillHeadPos.func_177977_b();
                IBlockState state = this.field_145850_b.func_180495_p(checkPos);
                if (state.func_177230_c() == oreBlock && oreBlock.func_176201_c(state) == 1) {
                    this.field_145850_b.func_175656_a(checkPos, oreBlock.func_176203_a(0));
                    this.drillHeight++;
                    BlockPos funnelPos = GalaxyCoordinates.lucientOreFunnelPos();
                    EntityItem item = new EntityItem(this.field_145850_b, funnelPos.func_177958_n(), funnelPos.func_177956_o() + 1, funnelPos.func_177952_p(), new ItemStack(ItemInit.lucient, 1));
                    item.field_70159_w = 0.0d;
                    item.field_70179_y = 0.0d;
                    item.field_70181_x = 0.0d;
                    this.field_145850_b.func_72838_d(item);
                    this.field_145850_b.func_184133_a((EntityPlayer) null, drillHeadPos, SoundInit.MACHINE_CRAFT, SoundCategory.MASTER, 1.0f, 1.0f);
                }
            }
        } else if (this.drillHeight > 0) {
            if (this.field_145850_b.func_180495_p(this.drillPos.func_177982_a(0, -this.drillHeight, 0)).func_177230_c() == drillHeadBlock) {
                this.field_145850_b.func_175698_g(this.drillPos.func_177982_a(0, -this.drillHeight, 0));
            }
            this.drillHeight--;
        }
        for (int i = 0; i <= this.drillHeight; i++) {
            BlockPos beamPos = this.drillPos.func_177982_a(0, -i, 0);
            if (i == this.drillHeight) {
                if (!didMine) {
                    this.field_145850_b.func_175656_a(beamPos, drillHeadBlock.func_176223_P());
                }
            } else {
                this.field_145850_b.func_175656_a(beamPos, drillBeamBlock.func_176223_P());
            }
        }
    }
    public void activateDrill(EntityPlayer player) {
        if (!this.field_145850_b.field_72995_K && !this.drilling) {
            Block beamBlock = BlockInit.drillBeam;
            IBlockState drillHeadState = BlockInit.drillHead.func_176223_P();
            this.active = true;
            player.func_145747_a(new TextComponentString(TextFmt.Green + "Maneuver the drill to mine the ores!"));
            AxisAlignedBB drillBB = GalaxyCoordinates.lucientDrillAABB();
            this.horizontalBeam.clear();
            this.verticalBeam.clear();
            for (int i = (int) drillBB.field_72340_a; i <= ((int) drillBB.field_72336_d); i++) {
                BlockPos checkPos = new BlockPos(i, drillBB.field_72337_e, drillBB.field_72339_c);
                if (this.field_145850_b.func_180495_p(checkPos).func_177230_c() == beamBlock) {
                    for (int k = (int) drillBB.field_72339_c; k <= ((int) drillBB.field_72334_f); k++) {
                        BlockPos beamPos = new BlockPos(i, drillBB.field_72337_e, k);
                        this.horizontalBeam.add(beamPos);
                    }
                }
            }
            for (int i2 = (int) drillBB.field_72339_c; i2 <= ((int) drillBB.field_72334_f); i2++) {
                BlockPos checkPos2 = new BlockPos(drillBB.field_72340_a, drillBB.field_72337_e, i2);
                if (this.field_145850_b.func_180495_p(checkPos2).func_177230_c() == beamBlock) {
                    for (int k2 = (int) drillBB.field_72340_a; k2 <= ((int) drillBB.field_72336_d); k2++) {
                        BlockPos beamPos2 = new BlockPos(k2, drillBB.field_72337_e, i2);
                        this.verticalBeam.add(beamPos2);
                    }
                }
            }
            for (BlockPos pos : this.horizontalBeam) {
                if (this.verticalBeam.contains(pos)) {
                    this.drillPos = pos.func_177977_b();
                    this.field_145850_b.func_175656_a(this.drillPos, drillHeadState);
                    return;
                }
            }
        }
    }
    public void moveDrill(int i) {
        Vec3i dir;
        BlockPos testPos;
        if (this.active) {
            if (i == 4) {
                adjustDrill(false);
                return;
            }
            if (i == 5) {
                adjustDrill(true);
                return;
            }
            if (this.drillHeight == 0 && this.horizontalBeam != null && !this.horizontalBeam.isEmpty() && this.verticalBeam != null && !this.verticalBeam.isEmpty()) {
                AxisAlignedBB drillBB = GalaxyCoordinates.lucientDrillAABB();
                boolean horizontal = false;
                switch (i) {
                    case 1:
                        dir = rightDir;
                        break;
                    case 2:
                        horizontal = true;
                        dir = new Vec3i(-forwardDir.func_177958_n(), 0, -forwardDir.func_177952_p());
                        break;
                    case 3:
                        dir = new Vec3i(-rightDir.func_177958_n(), 0, -rightDir.func_177952_p());
                        break;
                    default:
                        horizontal = true;
                        dir = forwardDir;
                        break;
                }
                if (horizontal) {
                    testPos = this.horizontalBeam.get(0);
                } else {
                    testPos = this.verticalBeam.get(0);
                }
                if (testPos.func_177958_n() + dir.func_177958_n() <= ((int) drillBB.field_72336_d) && testPos.func_177958_n() + dir.func_177958_n() >= ((int) drillBB.field_72340_a) && testPos.func_177952_p() + dir.func_177952_p() <= drillBB.field_72334_f && testPos.func_177952_p() + dir.func_177952_p() >= drillBB.field_72339_c) {
                    IBlockState beamState = BlockInit.drillBeam.func_176223_P();
                    IBlockState drillHeadState = BlockInit.drillHead.func_176223_P();
                    if (this.drillPos != null) {
                        this.field_145850_b.func_175698_g(this.drillPos);
                    }
                    ArrayList<BlockPos> newBeam = new ArrayList<>();
                    if (horizontal) {
                        for (BlockPos pos : this.horizontalBeam) {
                            if (!this.verticalBeam.contains(pos)) {
                                this.field_145850_b.func_175698_g(pos);
                            }
                            BlockPos newPos = pos.func_177971_a(dir);
                            newBeam.add(newPos);
                            this.field_145850_b.func_175656_a(newPos, beamState);
                        }
                        this.horizontalBeam.clear();
                        this.horizontalBeam.addAll(newBeam);
                    } else {
                        for (BlockPos pos2 : this.verticalBeam) {
                            if (!this.horizontalBeam.contains(pos2)) {
                                this.field_145850_b.func_175698_g(pos2);
                            }
                            BlockPos newPos2 = pos2.func_177971_a(dir);
                            newBeam.add(newPos2);
                            this.field_145850_b.func_175656_a(newPos2, beamState);
                        }
                        this.verticalBeam.clear();
                        this.verticalBeam.addAll(newBeam);
                    }
                    for (BlockPos pos3 : this.horizontalBeam) {
                        if (this.verticalBeam.contains(pos3)) {
                            this.drillPos = pos3.func_177977_b();
                            this.field_145850_b.func_175656_a(this.drillPos, drillHeadState);
                            return;
                        }
                    }
                }
            }
        }
    }
}
