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
import xol.lostinfinity.block.activator.BlockRhythmButton;
import xol.lostinfinity.block.activator.BlockRhythmTile;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
public class EntityRhythmGameMerchant extends EntityLiving {
    private boolean game;
    private boolean win;
    private BlockPos startPos;
    private BlockPos buttonStartPos;
    private boolean lose;
    int roundsBeforeSpeed;
    private int roundTimer;
    private int columns;
    private int time;
    private int rows;
    private int numButtons;
    private Vec3i dirUp;
    private Vec3i dirLeft;
    private boolean[][] tileMap;
    private boolean[] buttons;
    private boolean sentWinMessage;
    public EntityRhythmGameMerchant(World worldIn) {
        super(worldIn);
        this.game = false;
        this.startPos = null;
        this.buttonStartPos = null;
        this.lose = false;
        this.roundsBeforeSpeed = 4;
        this.roundTimer = 21;
        this.columns = 0;
        this.time = 0;
        this.rows = 0;
        this.numButtons = 0;
        this.dirUp = new Vec3i(0, 0, 0);
        this.dirLeft = new Vec3i(0, 0, 0);
        this.sentWinMessage = false;
    }
    protected void func_70088_a() {
        super.func_70088_a();
    }
    public void startGame() {
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
            near_pl.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "Match the rhythm with the buttons to stay in the game!"));
            this.game = false;
        }
        this.game = true;
    }
    public void genRhythmGame(BlockPos ref) {
        setRhythmPositions(ref);
        this.roundTimer = 21;
        this.time = 0;
        this.roundsBeforeSpeed = 10;
        if (this.columns > 0 && this.rows > 0 && this.numButtons > 0 && this.numButtons == this.columns) {
            this.tileMap = new boolean[this.columns][this.rows];
            this.buttons = new boolean[this.numButtons];
            updateStates();
            return;
        }
        func_70106_y();
    }
    private void updateStates() {
        for (int i = 0; i < this.numButtons; i++) {
            boolean buttonActive = false;
            boolean buttonData = getButtonAtLocation(i);
            BlockPos buttonPos = getButtonPosFromIndex(i);
            IBlockState state = this.field_70170_p.func_180495_p(buttonPos);
            Block block = state.func_177230_c();
            if ((block instanceof BlockRhythmButton) && state.equals(((BlockRhythmButton) block).getActiveState())) {
                buttonActive = true;
            }
            if (buttonActive != buttonData) {
                if (buttonData) {
                    this.field_70170_p.func_175656_a(buttonPos, ((BlockRhythmButton) BlockInit.rhythmButton).getActiveState());
                } else {
                    this.field_70170_p.func_175656_a(buttonPos, ((BlockRhythmButton) BlockInit.rhythmButton).getInactiveState());
                }
            }
        }
        for (int c = 0; c < this.columns; c++) {
            for (int r = 0; r < this.rows; r++) {
                boolean tileActive = false;
                boolean tileData = getTileAtLocation(c, r);
                BlockPos tilePos = getPosFromGrid(c, r);
                IBlockState state2 = this.field_70170_p.func_180495_p(tilePos);
                Block block2 = state2.func_177230_c();
                if ((block2 instanceof BlockRhythmTile) && state2.equals(((BlockRhythmTile) block2).getActiveState())) {
                    tileActive = true;
                }
                if (tileActive != tileData) {
                    if (tileData) {
                        this.field_70170_p.func_175656_a(tilePos, ((BlockRhythmTile) BlockInit.rhythmTile).getActiveState());
                    } else {
                        this.field_70170_p.func_175656_a(tilePos, ((BlockRhythmTile) BlockInit.rhythmTile).getInactiveState());
                    }
                }
            }
        }
    }
    public void setRhythmPositions(BlockPos ref) {
        this.buttonStartPos = nearestButton(ref);
        this.startPos = nearestTile(this.buttonStartPos);
        if (this.buttonStartPos.func_177958_n() == this.startPos.func_177958_n()) {
            boolean zdir = this.startPos.func_177952_p() > this.buttonStartPos.func_177952_p();
            this.dirUp = new Vec3i(0, 0, zdir ? 1 : -1);
            this.dirLeft = new Vec3i(zdir ? 1 : -1, 0, 0);
        } else {
            boolean xdir = this.startPos.func_177958_n() > this.buttonStartPos.func_177958_n();
            this.dirUp = new Vec3i(xdir ? 1 : -1, 0, 0);
            this.dirLeft = new Vec3i(0, 0, xdir ? -1 : 1);
        }
        boolean buttons = true;
        int b = 0;
        boolean rows = true;
        boolean columns = true;
        int r = 0;
        int c = 0;
        while (buttons) {
            if (getBlockAtPos(getButtonPosFromIndex(b)) instanceof BlockRhythmButton) {
                b++;
            } else {
                buttons = false;
            }
        }
        while (rows) {
            if (getBlockAtPos(getPosFromGrid(0, r)) instanceof BlockRhythmTile) {
                r++;
            } else {
                rows = false;
            }
        }
        while (columns) {
            if (getBlockAtPos(getPosFromGrid(c, 0)) instanceof BlockRhythmTile) {
                c++;
            } else {
                columns = false;
            }
        }
        this.numButtons = b;
        this.rows = r;
        this.columns = c;
    }
    private Block getBlockAtPos(BlockPos pos) {
        return this.field_70170_p.func_180495_p(pos).func_177230_c();
    }
    private BlockPos getButtonPosFromIndex(int i) {
        return this.buttonStartPos.func_177982_a(this.dirLeft.func_177958_n() * i, 0, this.dirLeft.func_177952_p() * i);
    }
    private int getButtonIndexFromPos(BlockPos pos) {
        int i = Math.abs(pos.func_177958_n() - this.buttonStartPos.func_177958_n()) + Math.abs(pos.func_177952_p() - this.buttonStartPos.func_177952_p());
        if (i < this.numButtons) {
            return i;
        }
        return 0;
    }
    private BlockPos getPosFromGrid(int c, int r) {
        return this.startPos.func_177982_a((this.dirUp.func_177958_n() * r) + (this.dirLeft.func_177958_n() * c), 0, (this.dirUp.func_177952_p() * r) + (this.dirLeft.func_177952_p() * c));
    }
    public BlockPos nearestTile(BlockPos pos) {
        ArrayList<BlockPos> positions = new ArrayList<>();
        positions.add(pos.func_177982_a(1, 0, 0));
        positions.add(pos.func_177982_a(0, 0, -1));
        positions.add(pos.func_177982_a(-1, 0, 0));
        positions.add(pos.func_177982_a(0, 0, 1));
        for (BlockPos position : positions) {
            Block block = this.field_70170_p.func_180495_p(position).func_177230_c();
            if (block instanceof BlockRhythmTile) {
                return position;
            }
        }
        return null;
    }
    public BlockPos nearestButton(BlockPos pos) {
        ArrayList<BlockPos> positions = new ArrayList<>();
        positions.add(pos.func_177982_a(1, 0, 1));
        positions.add(pos.func_177982_a(1, 0, -1));
        positions.add(pos.func_177982_a(-1, 0, 1));
        positions.add(pos.func_177982_a(-1, 0, -1));
        for (BlockPos position : positions) {
            Block block = this.field_70170_p.func_180495_p(position).func_177230_c();
            if (block instanceof BlockRhythmButton) {
                return position;
            }
        }
        return null;
    }
    private boolean getButtonAtLocation(int i) {
        if (i >= 0 && i < this.buttons.length) {
            return this.buttons[i];
        }
        return false;
    }
    private boolean getTileAtLocation(int c, int r) {
        if (c >= 0 && c < this.tileMap.length && r >= 0 && r < this.tileMap[c].length) {
            return this.tileMap[c][r];
        }
        return false;
    }
    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        if (!this.field_70170_p.field_72995_K && this.win) {
            func_145779_a(ItemInit.spacetimeTrigger, 1);
            this.win = false;
            deathEffect();
            return true;
        }
        return true;
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            this.time++;
            if (this.field_70173_aa % 5 == 0 && this.startPos == null) {
                deathEffect();
            }
            if (this.lose) {
                for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(25.0d, 32.0d, 25.0d))) {
                    near_pl.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "You have failed to match the rhythm!"));
                }
                deathEffect();
            }
            if (this.time == this.roundTimer && this.game) {
                func_184185_a(SoundInit.DASH, 0.5f, 1.0f);
                progress();
                if (this.roundsBeforeSpeed == 0) {
                    this.roundsBeforeSpeed = 10;
                    for (EntityPlayer near_pl2 : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(25.0d, 32.0d, 25.0d))) {
                        near_pl2.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "Speed Up!"));
                    }
                    this.roundTimer--;
                } else {
                    this.roundsBeforeSpeed--;
                }
            }
            if (this.roundTimer < 18 && !this.sentWinMessage) {
                this.win = true;
                this.game = false;
                for (EntityPlayer near_pl3 : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(25.0d, 32.0d, 25.0d))) {
                    near_pl3.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "You have matched the whole rhythm! Come receive your reward!"));
                }
                this.sentWinMessage = true;
            }
        }
    }
    private void progress() {
        boolean goalRowHasTile = false;
        for (int c = 0; c < this.tileMap.length; c++) {
            if (getTileAtLocation(c, 0)) {
                goalRowHasTile = true;
            }
        }
        if (goalRowHasTile) {
            boolean match = false;
            for (int c2 = 0; c2 < this.tileMap.length; c2++) {
                boolean tile = getTileAtLocation(c2, 0);
                if (tile && tile == getButtonAtLocation(c2)) {
                    match = true;
                }
            }
            if (!match) {
                this.lose = true;
                this.game = false;
                return;
            }
        }
        int randColumn = this.field_70170_p.field_73012_v.nextInt(this.buttons.length);
        boolean[][] newMap = new boolean[this.columns][this.rows];
        for (int r = this.rows - 1; r > 0; r--) {
            if (r == this.rows - 1) {
                newMap[randColumn][r] = true;
            }
            for (int c3 = 0; c3 < this.columns; c3++) {
                newMap[c3][r - 1] = this.tileMap[c3][r];
            }
        }
        this.tileMap = newMap;
        this.time = 0;
        updateStates();
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
    public void pressButton(BlockPos pos) {
        int i = getButtonIndexFromPos(pos);
        for (int j = 0; j < this.buttons.length; j++) {
            if (j == i) {
                this.buttons[j] = true;
            } else {
                this.buttons[j] = false;
            }
        }
        updateStates();
    }
}
