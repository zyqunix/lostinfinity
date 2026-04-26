package xol.lostinfinity.block.tileentity;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
public class TileEntityCircuitCalibrator extends TileEntity implements ITickable {
    private boolean game = false;
    private int cur = 0;
    private int time = 0;
    private int switchTime = 0;
    private int baseTime = 25;
    private int score = 0;
    private EntityPlayer player = null;
    private boolean pressed = false;
    BlockPos monPos = null;
    private static int timeRange = 10;
    private static Random rand = new Random();
    public void func_73660_a() {
        int meta;
        if (!this.field_145850_b.field_72995_K && this.game) {
            if (this.score >= 15) {
                this.game = false;
                if (this.player != null) {
                    this.player.func_191521_c(new ItemStack(ItemInit.biosyncedClock));
                    this.player.func_145747_a(new TextComponentString(TextFmt.Green + "You have managed to fully calibrate the circuit!"));
                    if (this.monPos != null) {
                        this.field_145850_b.func_175656_a(this.monPos, BlockInit.circuitMonitor.func_176203_a(0));
                        return;
                    }
                    return;
                }
                return;
            }
            if (this.score < 0) {
                this.game = false;
                if (this.player != null) {
                    this.player.func_145747_a(new TextComponentString(TextFmt.Dark_Red + "You have failed to calibrate the circuit."));
                    return;
                }
                return;
            }
            if (this.time >= this.switchTime) {
                this.time = 0;
                if (this.baseTime > 12) {
                    this.baseTime -= 3;
                }
                this.switchTime = rand.nextInt(timeRange) + this.baseTime;
                if (this.monPos != null) {
                    try {
                        meta = BlockInit.circuitMonitor.func_176201_c(this.field_145850_b.func_180495_p(this.monPos));
                    } catch (Exception e) {
                        meta = 0;
                    }
                    int newMeta = 0;
                    switch (meta) {
                        case 0:
                            newMeta = rand.nextBoolean() ? 1 : 2;
                            break;
                        case 1:
                            newMeta = 0;
                            if (this.pressed) {
                                this.score++;
                                this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundInit.MINIGAME_SCORE, SoundCategory.BLOCKS, 1.5f, 0.8f + (rand.nextFloat() * 0.4f));
                                this.pressed = false;
                            } else {
                                this.score -= 3;
                                this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundInit.GAME_BUZZER, SoundCategory.BLOCKS, 1.5f, 0.8f + (rand.nextFloat() * 0.4f));
                            }
                            if (this.player != null) {
                                this.player.func_145747_a(new TextComponentString(TextFmt.Green + "Score: " + this.score));
                            }
                            break;
                        case 2:
                            newMeta = 0;
                            if (this.pressed) {
                                this.score -= 3;
                                this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundInit.GAME_BUZZER, SoundCategory.BLOCKS, 1.5f, 0.8f + (rand.nextFloat() * 0.4f));
                                if (this.player != null) {
                                    this.player.func_145747_a(new TextComponentString(TextFmt.Green + "Score: " + this.score));
                                }
                                this.pressed = false;
                            }
                            break;
                    }
                    this.field_145850_b.func_175656_a(this.monPos, BlockInit.circuitMonitor.func_176203_a(newMeta));
                    if (newMeta != 0) {
                        this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundInit.BLOCK_DING, SoundCategory.BLOCKS, 1.5f, 0.8f + (rand.nextFloat() * 0.4f));
                        return;
                    }
                    return;
                }
                return;
            }
            this.time++;
        }
    }
    public void startGame() {
        this.pressed = false;
        this.game = true;
        this.cur = 0;
        this.time = 0;
        this.baseTime = 32;
        this.score = 0;
        this.switchTime = rand.nextInt(timeRange) + this.baseTime;
        Vec3i offset = new Vec3i(0, 0, 0);
        for (int i = -7; i <= 7; i++) {
            for (int k = -7; k <= 7; k++) {
                BlockPos check = func_174877_v().func_177971_a(offset).func_177982_a(i, 0, k);
                IBlockState state = this.field_145850_b.func_180495_p(check);
                if (state.func_177230_c() == BlockInit.circuitMonitor) {
                    this.monPos = check;
                    this.field_145850_b.func_175656_a(this.monPos, BlockInit.circuitMonitor.func_176203_a(0));
                    return;
                }
            }
        }
    }
    public void press(int meta, BlockPos pos, EntityPlayer playerIn) {
        this.monPos = pos;
        this.player = playerIn;
        if (meta == 2 || meta == 1) {
            this.pressed = true;
            this.time = this.switchTime;
        }
    }
}
