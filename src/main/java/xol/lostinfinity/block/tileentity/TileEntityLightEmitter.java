package xol.lostinfinity.block.tileentity;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.activator.BlockLightEmitter;
import xol.lostinfinity.block.activator.BlockLightReflector;
import xol.lostinfinity.block.misc.BlockLightReceiver;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.packets.serverbound.PacketLightReceiver;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/TileEntityLightEmitter.class */
public class TileEntityLightEmitter extends TileEntity implements ITickable {
    private Vec3i dirUp = new Vec3i(0, 0, 1);
    private Vec3i dirLeft = new Vec3i(1, 0, 0);
    private BlockPos finalBlock;
    private boolean complete;
    private ArrayList<Vec3d> dirs;
    private ArrayList<Vec3d> reflectors;
    private Vec3d startPos;
    private Vec3d stopPos;

    public void func_73660_a() {
        if (!this.field_145850_b.field_72995_K) {
            TileEntityLightEmitter tileEntity = (TileEntityLightEmitter) this.field_145850_b.func_175625_s(func_174877_v());
            tileEntity.getLightMazeAABB();
            this.finalBlock = GalaxyCoordinates.getLightReceiver();
            if (this.finalBlock != null) {
                for (int i = -20; i < 20; i++) {
                    for (int j = -20; j < 20; j++) {
                        for (int k = -20; k < 20; k++) {
                            BlockPos checkPos = new BlockPos(this.finalBlock.func_177958_n() + i, this.finalBlock.func_177956_o() + j, this.finalBlock.func_177952_p() + k);
                            Block checkBlock = this.field_145850_b.func_180495_p(checkPos).func_177230_c();
                            if (checkBlock.equals(BlockInit.lightReceiver)) {
                                if (isComplete()) {
                                    ((BlockLightReceiver) checkBlock).openGate(this.field_145850_b, checkPos);
                                    return;
                                } else {
                                    ((BlockLightReceiver) checkBlock).closeGate(this.field_145850_b, checkPos);
                                    return;
                                }
                            }
                        }
                    }
                }
                return;
            }
            return;
        }
        this.complete = false;
        IBlockState emitterState = this.field_145850_b.func_180495_p(func_174877_v());
        BlockLightEmitter emitterBlock = this.field_145850_b.func_180495_p(func_174877_v()).func_177230_c();
        TileEntityLightEmitter tileEntity2 = (TileEntityLightEmitter) this.field_145850_b.func_175625_s(func_174877_v());
        tileEntity2.getLightMazeAABB();
        this.finalBlock = GalaxyCoordinates.getLightReceiver();
        Vec3d emitterDir = emitterBlock.getBeamDir(emitterState);
        this.startPos = new Vec3d(func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p());
        this.reflectors = new ArrayList<>();
        this.reflectors.add(this.startPos);
        this.dirs = new ArrayList<>();
        this.dirs.add(emitterDir);
        boolean flag = true;
        int count = 0;
        while (true) {
            if (!flag) {
                break;
            }
            Vec3d dir = this.dirs.get(count);
            Vec3d nextPos = this.reflectors.get(count).func_178787_e(dir);
            for (int dist = 0; this.field_145850_b.func_175623_d(new BlockPos(nextPos.field_72450_a, nextPos.field_72448_b, nextPos.field_72449_c)) && dist < 30; dist++) {
                nextPos = nextPos.func_178787_e(dir);
            }
            IBlockState nextState = this.field_145850_b.func_180495_p(new BlockPos(nextPos.field_72450_a, nextPos.field_72448_b, nextPos.field_72449_c));
            BlockLightReflector blockLightReflectorFunc_177230_c = nextState.func_177230_c();
            if (blockLightReflectorFunc_177230_c instanceof BlockLightReflector) {
                ArrayList<Vec3d> beamDirs = blockLightReflectorFunc_177230_c.getBeamDirs(nextState);
                Vec3d dirReverse = new Vec3d((-1.0d) * dir.field_72450_a, (-1.0d) * dir.field_72448_b, (-1.0d) * dir.field_72449_c);
                if (beamDirs.contains(dirReverse)) {
                    boolean finalFlag = false;
                    IBlockState finalState = this.field_145850_b.func_180495_p(new BlockPos(nextPos.field_72450_a, nextPos.field_72448_b, nextPos.field_72449_c));
                    if (finalState.func_177230_c() == BlockInit.lightReflector) {
                        BlockLightReflector finalReflector = finalState.func_177230_c();
                        finalFlag = finalReflector.getStateWithFacing(EnumFacing.SOUTH) == finalState;
                    }
                    if (new BlockPos(nextPos.field_72450_a, nextPos.field_72448_b, nextPos.field_72449_c).equals(this.finalBlock) && finalFlag) {
                        this.complete = true;
                        this.reflectors.add(nextPos);
                        break;
                    } else {
                        this.reflectors.add(nextPos);
                        beamDirs.remove(dirReverse);
                        this.dirs.add(beamDirs.get(0));
                        count++;
                    }
                } else {
                    this.reflectors.add(this.stopPos);
                    this.stopPos = nextPos;
                    flag = false;
                }
            } else {
                if (this.reflectors.size() > 1) {
                    this.reflectors.add(this.stopPos);
                }
                this.stopPos = nextPos;
                flag = false;
            }
        }
        lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketLightReceiver(this.complete, func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p()));
    }

    public boolean shouldRenderInPass(int pass) {
        return true;
    }

    public AxisAlignedBB getRenderBoundingBox() {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        return bb;
    }

    @SideOnly(Side.CLIENT)
    public double func_145833_n() {
        return 65536.0d;
    }

    public AxisAlignedBB getLightMazeAABB() {
        int x = func_174877_v().func_177958_n();
        int y = func_174877_v().func_177956_o();
        int z = func_174877_v().func_177952_p();
        return new AxisAlignedBB(x, y, z, x + (this.dirUp.func_177958_n() * 10) + (this.dirLeft.func_177958_n() * 10), y, z + (this.dirUp.func_177952_p() * 10) + (this.dirLeft.func_177952_p() * 10));
    }

    private void generateLightMaze() {
    }

    public NBTTagCompound func_189515_b(NBTTagCompound compound) {
        super.func_189515_b(compound);
        if (this.finalBlock != null) {
            compound.func_74768_a("FinalX", (short) this.finalBlock.func_177958_n());
            compound.func_74768_a("FinalY", (short) this.finalBlock.func_177956_o());
            compound.func_74768_a("FinalZ", (short) this.finalBlock.func_177952_p());
        }
        compound.func_74757_a("Complete", this.complete);
        return compound;
    }

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        if (compound.func_74764_b("FinalX")) {
            this.finalBlock = new BlockPos(compound.func_74762_e("FinalX"), compound.func_74762_e("FinalY"), compound.func_74762_e("FinalZ"));
        }
        this.complete = compound.func_74767_n("Complete");
    }

    public Vec3d getStartPos() {
        return this.startPos;
    }

    public Vec3d getStopPos() {
        return this.stopPos;
    }

    public ArrayList<Vec3d> getReflectors() {
        if (this.reflectors != null) {
            return this.reflectors;
        }
        return new ArrayList<>();
    }

    public boolean isComplete() {
        return this.complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
