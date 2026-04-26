package xol.lostinfinity.mob.entity.misc;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.activator.BlockPipe;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.data.MazeMap;
import xol.lostinfinity.dimension.data.MazeNode;
import xol.lostinfinity.dimension.data.PipeNode;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityPipeGameMerchant.class */
public class EntityPipeGameMerchant extends EntityLiving {
    private boolean game;
    private boolean win;
    private BlockPos ref;
    private BlockPos startPos;
    private boolean lose;
    private int columns;
    private int rows;
    private Vec3i dir;
    private PipeNode[][] pipeMap;
    private boolean sentWinMessage;

    public void setGridSize(int c, int r) {
        this.columns = c;
        this.rows = r;
    }

    public EntityPipeGameMerchant(World worldIn) {
        super(worldIn);
        this.game = false;
        this.win = false;
        this.lose = false;
        this.dir = new Vec3i(0, 0, 0);
        this.sentWinMessage = false;
    }

    protected void func_70088_a() {
        super.func_70088_a();
    }

    public void startGame() {
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
            near_pl.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "Not often I get called to Nonexistence... Quickly connect the pipe to the top right to fill the vial."));
            this.game = false;
        }
        this.game = true;
    }

    public void genPipeGame(World worldIn, BlockPos ref) {
        if (!worldIn.field_72995_K) {
            int col = this.columns;
            int row = this.rows;
            this.pipeMap = new PipeNode[col][row];
            MazeMap maze = new MazeMap(col, row);
            for (int c = 0; c < col; c++) {
                for (int r = 0; r < row; r++) {
                    PipeNode node = null;
                    int rot = 0;
                    boolean lit = false;
                    if (c == 0 && r == 0) {
                        rot = 0;
                        lit = true;
                    } else if (c == col - 1 && r == row - 1) {
                        rot = 0;
                    } else {
                        MazeNode mazeNode = maze.getNodeAtLocation(c, r);
                        node = ((mazeNode.getType().equals("path") || mazeNode.getType().equals("trigger")) && mazeNode.isVisited()) ? getPipeNodeFromMazeNode(mazeNode, maze, c, r) : new PipeNode(c, r);
                    }
                    if (node == null) {
                        node = new PipeNode("cross", rot, c, r);
                    }
                    node.setLit(lit);
                    this.pipeMap[c][r] = node;
                }
            }
            setPipePositions(ref);
            updateLit();
        }
    }

    private PipeNode getPipeNodeFromMazeNode(MazeNode mazeNode, MazeMap maze, int c, int r) {
        boolean[] cross = {true, true, true, true};
        boolean[] elbow = {true, true, false, false};
        boolean[] t = {true, true, true, false};
        boolean[] l = {true, false, true, false};
        boolean[] connected = getConnectedMazeNeighbours(mazeNode, maze);
        if (matchNeighbours(connected, cross)) {
            return new PipeNode("cross", 0, c, r);
        }
        if (matchNeighbours(connected, elbow)) {
            return new PipeNode("elbow", 0, c, r);
        }
        if (matchNeighbours(connected, t)) {
            return new PipeNode("t", 0, c, r);
        }
        if (matchNeighbours(connected, l)) {
            return new PipeNode("l", 0, c, r);
        }
        for (int i = 1; i <= 3; i++) {
            connected = getRotatedMazeNeighbours(connected);
            if (matchNeighbours(connected, cross)) {
                return new PipeNode("cross", 4 - i, c, r);
            }
            if (matchNeighbours(connected, elbow)) {
                return new PipeNode("elbow", 4 - i, c, r);
            }
            if (matchNeighbours(connected, t)) {
                return new PipeNode("t", 4 - i, c, r);
            }
            if (matchNeighbours(connected, l)) {
                return new PipeNode("l", 4 - i, c, r);
            }
        }
        return new PipeNode("l", 0, c, r);
    }

    public static boolean matchNeighbours(boolean[] neighbours1, boolean[] neighbours2) {
        for (int i = 0; i < neighbours1.length; i++) {
            if (neighbours1[i] != neighbours2[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean[] getConnectedMazeNeighbours(MazeNode mazeNode, MazeMap maze) {
        boolean[] connected = {false, false, false, false};
        int x = mazeNode.getX();
        int z = mazeNode.getZ();
        if (maze.getNodeAtLocation(x, z + 1) != null) {
            MazeNode node = maze.getNodeAtLocation(x, z + 1);
            if ((node.getType().equals("path") || node.getType().equals("trigger")) && node.isVisited()) {
                connected[0] = true;
            }
        }
        if (maze.getNodeAtLocation(x + 1, z) != null) {
            MazeNode node2 = maze.getNodeAtLocation(x + 1, z);
            if ((node2.getType().equals("path") || node2.getType().equals("trigger")) && node2.isVisited()) {
                connected[1] = true;
            }
        }
        if (maze.getNodeAtLocation(x, z - 1) != null) {
            MazeNode node3 = maze.getNodeAtLocation(x, z - 1);
            if ((node3.getType().equals("path") || node3.getType().equals("trigger")) && node3.isVisited()) {
                connected[2] = true;
            }
        }
        if (maze.getNodeAtLocation(x - 1, z) != null) {
            MazeNode node4 = maze.getNodeAtLocation(x - 1, z);
            if ((node4.getType().equals("path") || node4.getType().equals("trigger")) && node4.isVisited()) {
                connected[3] = true;
            }
        }
        return connected;
    }

    private boolean[] getRotatedMazeNeighbours(boolean[] neighbours) {
        boolean[] newNeighbours = {false, false, false, false};
        if (neighbours[0]) {
            newNeighbours[1] = true;
        }
        if (neighbours[1]) {
            newNeighbours[2] = true;
        }
        if (neighbours[2]) {
            newNeighbours[3] = true;
        }
        if (neighbours[3]) {
            newNeighbours[0] = true;
        }
        return newNeighbours;
    }

    private void setBlock(PipeNode node) {
        BlockPos pos = node.getBlockPos();
        if (pos != null) {
            node.updateState();
            IBlockState state = node.getState();
            this.field_70170_p.func_175656_a(pos, state);
        }
    }

    public BlockPos nearestPipe(BlockPos pos) {
        ArrayList<BlockPos> positions = new ArrayList<>();
        positions.add(pos.func_177982_a(1, 0, 1));
        positions.add(pos.func_177982_a(1, 0, -1));
        positions.add(pos.func_177982_a(-1, 0, 1));
        positions.add(pos.func_177982_a(-1, 0, -1));
        positions.add(pos.func_177982_a(1, 0, 0));
        positions.add(pos.func_177982_a(-1, 0, 0));
        positions.add(pos.func_177982_a(0, 0, 1));
        positions.add(pos.func_177982_a(0, 0, -1));
        for (BlockPos position : positions) {
            Block block = this.field_70170_p.func_180495_p(position).func_177230_c();
            if (block instanceof BlockPipe) {
                return position;
            }
        }
        return null;
    }

    public void setPipePositions(BlockPos reference) {
        BlockPos pipe2;
        this.ref = reference;
        this.startPos = nearestPipe(this.ref);
        if (this.startPos != null && (pipe2 = nearestPipe(this.startPos)) != null) {
            if (pipe2.func_177958_n() == this.startPos.func_177958_n()) {
                boolean zdir = pipe2.func_177952_p() > this.startPos.func_177952_p();
                this.dir = new Vec3i(0, 0, zdir ? 1 : -1);
            } else {
                boolean xdir = pipe2.func_177958_n() > this.startPos.func_177958_n();
                this.dir = new Vec3i(xdir ? 1 : -1, 0, 0);
            }
        }
        for (int i = 0; i < this.columns; i++) {
            for (int j = 0; j < this.rows; j++) {
                BlockPos temp = this.startPos.func_177982_a(this.dir.func_177958_n() * i, j, this.dir.func_177952_p() * i);
                if (this.field_70170_p.func_180495_p(temp).func_177230_c() instanceof BlockPipe) {
                    PipeNode node = getNodeAtLocation(i, j);
                    node.setBlockPos(temp);
                    int numRot = this.field_70170_p.field_73012_v.nextInt(4);
                    for (int rot = 0; rot < numRot; rot++) {
                        node.rotate();
                    }
                    setBlock(node);
                }
            }
        }
    }

    public void rotate(BlockPos pos) {
        if (this.startPos != null) {
            int gridX = Math.abs(pos.func_177958_n() - this.startPos.func_177958_n()) + Math.abs(pos.func_177952_p() - this.startPos.func_177952_p());
            int gridY = Math.abs(pos.func_177956_o() - this.startPos.func_177956_o());
            PipeNode node = getNodeAtLocation(gridX, gridY);
            node.rotate();
            setBlock(node);
            updateLit();
        }
    }

    private void updateLit() {
        for (int i = 0; i < this.columns; i++) {
            for (int j = 0; j < this.rows; j++) {
                PipeNode node = getNodeAtLocation(i, j);
                node.setLit(false);
                setBlock(node);
            }
        }
        PipeNode startNode = getNodeAtLocation(0, 0);
        startNode.propogateLit(this.pipeMap, new ArrayList<>());
        for (int i2 = 0; i2 < this.columns; i2++) {
            for (int j2 = 0; j2 < this.rows; j2++) {
                PipeNode node2 = getNodeAtLocation(i2, j2);
                setBlock(node2);
                if (node2.isLit() && i2 == this.columns - 1 && j2 == this.columns - 1) {
                    this.win = true;
                }
            }
        }
    }

    private PipeNode getNodeAtLocation(int c, int r) {
        if (this.pipeMap[c] != null && this.pipeMap[c][r] != null) {
            return this.pipeMap[c][r];
        }
        return null;
    }

    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        if (!this.field_70170_p.field_72995_K && this.win) {
            func_145779_a(ItemInit.nanofluoricAcid, 1);
            this.win = false;
            deathEffect();
            return true;
        }
        return true;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa % 5 == 0 && this.startPos == null) {
                deathEffect();
            }
            if (this.field_70173_aa % 1400 == 0 && this.startPos != null && !this.win) {
                for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(25.0d, 35.0d, 25.0d))) {
                    near_pl.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Red) + "Oh no! The liquid ran out before the pipes were connected."));
                    this.game = false;
                }
                deathEffect();
            }
            if (this.win && !this.sentWinMessage) {
                for (EntityPlayer near_pl2 : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(25.0d, 32.0d, 25.0d))) {
                    near_pl2.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "The vial is full! Come get it."));
                }
                this.sentWinMessage = true;
            }
        }
    }

    private void deathEffect() {
        this.field_70170_p.func_175739_a(EnumParticleTypes.PORTAL, this.field_70165_t, this.field_70163_u, this.field_70161_v, 12, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
        func_70106_y();
    }

    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187910_gj;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187912_gl;
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187911_gk;
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0d);
    }
}
