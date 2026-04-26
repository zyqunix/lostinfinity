package xol.lostinfinity.mob.entity.misc;

import java.util.ArrayList;
import java.util.Random;
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
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.activator.BlockCellContainer;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityCellGameMerchant.class */
public class EntityCellGameMerchant extends EntityLiving {
    private boolean game;
    private boolean win;
    private boolean lose;
    private int cell1;
    private int cell2;
    private static int numSteps = 30;
    private boolean sentWinMessage;
    private ArrayList<BlockPos> cells;
    private ArrayList<Integer> amounts;
    private ArrayList<Integer> winningAmounts;
    private ArrayList<Integer> capacities;

    public void setCellPositions(BlockPos ref, int radius, World world, EntityPlayer playerIn) {
        this.cells = new ArrayList<>();
        this.amounts = new ArrayList<>();
        this.capacities = new ArrayList<>();
        this.winningAmounts = new ArrayList<>();
        for (int i = -radius; i <= radius; i++) {
            for (int k = -radius; k <= radius; k++) {
                if (world.func_180495_p(ref.func_177982_a(i, 1, k)).func_177230_c() instanceof BlockCellContainer) {
                    this.cells.add(ref.func_177982_a(i, 1, k));
                    IBlockState state = getRandomCell();
                    this.field_70170_p.func_175656_a(ref.func_177982_a(i, 1, k), state);
                    Block cell = state.func_177230_c();
                    int capacity = getBlockCapacity(cell);
                    int randAmount = world.field_73012_v.nextInt(capacity + 1);
                    this.capacities.add(Integer.valueOf(capacity));
                    this.amounts.add(Integer.valueOf(randAmount));
                    setCellState(this.cells.size() - 1, randAmount, world);
                }
            }
        }
        setGoalAmounts(world, playerIn);
        startGame();
    }

    public IBlockState getRandomCell() {
        Random rand = new Random();
        int randCell = rand.nextInt(4);
        switch (randCell) {
            case 0:
                return BlockInit.cellContainer3.func_176223_P();
            case 1:
                return BlockInit.cellContainer5.func_176223_P();
            case 2:
                return BlockInit.cellContainer7.func_176223_P();
            case 3:
                return BlockInit.cellContainer9.func_176223_P();
            default:
                return BlockInit.cellContainer3.func_176223_P();
        }
    }

    private int getBlockCapacity(Block block) {
        if (block.equals(BlockInit.cellContainer3)) {
            return 3;
        }
        if (block.equals(BlockInit.cellContainer5)) {
            return 5;
        }
        if (block.equals(BlockInit.cellContainer7)) {
            return 7;
        }
        if (block.equals(BlockInit.cellContainer9)) {
            return 9;
        }
        return 0;
    }

    private void setGoalAmounts(World world, EntityPlayer playerIn) {
        int second;
        ArrayList<Integer> tempAmounts = new ArrayList<>();
        tempAmounts.addAll(this.amounts);
        for (int i = 0; i < numSteps; i++) {
            int first = world.field_73012_v.nextInt(this.cells.size());
            int iNextInt = world.field_73012_v.nextInt(this.cells.size());
            while (true) {
                second = iNextInt;
                if (second != first) {
                    break;
                } else {
                    iNextInt = world.field_73012_v.nextInt(this.cells.size());
                }
            }
            int amount1 = tempAmounts.get(first).intValue();
            int amount2 = tempAmounts.get(second).intValue();
            int capacity = this.capacities.get(second).intValue();
            int space = capacity - amount2;
            if (amount1 <= space) {
                amount2 += amount1;
                amount1 = 0;
            } else if (amount1 > space) {
                amount1 -= space;
                amount2 += space;
            }
            tempAmounts.set(first, Integer.valueOf(amount1));
            tempAmounts.set(second, Integer.valueOf(amount2));
        }
        this.winningAmounts.addAll(tempAmounts);
    }

    public EntityCellGameMerchant(World worldIn) {
        super(worldIn);
        this.game = false;
        this.win = false;
        this.lose = false;
        this.cell1 = -1;
        this.cell2 = -1;
        this.sentWinMessage = false;
        this.cells = null;
        this.amounts = null;
        this.winningAmounts = null;
        this.capacities = null;
    }

    public void selectCell1(BlockPos pos, World world, EntityPlayer playerIn) {
        for (int i = 0; i < this.cells.size(); i++) {
            BlockPos cellPos = this.cells.get(i);
            if (pos.equals(cellPos)) {
                if (this.cell1 != i) {
                    this.cell1 = i;
                    playerIn.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + String.format("Container %d selected to transfer from", Integer.valueOf(i + 1))));
                }
                if (this.cell1 != -1 && this.cell2 != -1) {
                    pour(world, playerIn);
                    return;
                }
                return;
            }
        }
    }

    public void selectCell2(BlockPos pos, World world, EntityPlayer playerIn) {
        for (int i = 0; i < this.cells.size(); i++) {
            BlockPos cellPos = this.cells.get(i);
            if (pos.equals(cellPos)) {
                if (this.cell2 != i) {
                    this.cell2 = i;
                    playerIn.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + String.format("Container %d selected as receptacle", Integer.valueOf(i + 1))));
                }
                if (this.cell1 != -1 && this.cell2 != -1) {
                    pour(world, playerIn);
                    return;
                }
                return;
            }
        }
    }

    private void pour(World world, EntityPlayer playerIn) {
        if (this.cell1 != -1 && this.cell2 != -1 && this.cell1 != this.cell2) {
            int amount1 = this.amounts.get(this.cell1).intValue();
            int amount2 = this.amounts.get(this.cell2).intValue();
            int capacity = this.capacities.get(this.cell2).intValue();
            int space = capacity - amount2;
            if (amount1 <= space) {
                amount2 += amount1;
                amount1 = 0;
            } else if (amount1 > space) {
                amount1 -= space;
                amount2 += space;
            }
            this.amounts.set(this.cell1, Integer.valueOf(amount1));
            this.amounts.set(this.cell2, Integer.valueOf(amount2));
            setCellState(this.cell1, amount1, world);
            setCellState(this.cell2, amount2, world);
            playerIn.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + String.format("Transferred possible cells from container %d to container %d", Integer.valueOf(this.cell1 + 1), Integer.valueOf(this.cell2 + 1))));
            this.cell1 = -1;
            this.cell2 = -1;
            if (this.game) {
                checkWin(playerIn);
            }
        }
    }

    private void checkWin(EntityPlayer playerIn) {
        for (int i = 0; i < this.amounts.size(); i++) {
            if (this.amounts.get(i) != this.winningAmounts.get(i)) {
                this.win = false;
                return;
            }
        }
        this.win = true;
    }

    private void setCellState(int cell, int amount, World world) {
        Block block = world.func_180495_p(this.cells.get(cell)).func_177230_c();
        if (block instanceof BlockCellContainer) {
            this.field_70170_p.func_175656_a(this.cells.get(cell), ((BlockCellContainer) block).getStateWithAmount(amount));
        }
    }

    protected void func_70088_a() {
        super.func_70088_a();
    }

    public void startGame() {
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
            near_pl.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "Transfer cells between the containers to balance them to the correct levels, click with power analyzer to select which container to transfer from, click with hand to set the container receiving power."));
        }
        this.game = true;
    }

    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        if (!this.field_70170_p.field_72995_K && this.win) {
            if (func_110143_aJ() == 69.0f) {
                func_145779_a(ItemInit.powerBlade, 1);
            } else if (func_110143_aJ() == 420.0f) {
                func_145779_a(ItemInit.microstoragePowerCluster, 1);
            }
            this.win = false;
            deathEffect();
            return true;
        }
        return true;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa % 5 == 0 && this.cells == null) {
                deathEffect();
            }
            if (this.field_70173_aa % 140000 == 0 && this.cells != null && !this.win) {
                for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(25.0d, 35.0d, 25.0d))) {
                    near_pl.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Red) + "Oh no! The levels are still incorrect!"));
                    this.game = false;
                }
                deathEffect();
            }
            if (this.win && !this.sentWinMessage) {
                for (EntityPlayer near_pl2 : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(25.0d, 32.0d, 25.0d))) {
                    near_pl2.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "The levels are correct! Come receive your reward!"));
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

    public void readPowerLevel(BlockPos pos, World worldIn, EntityPlayer playerIn) {
        for (int i = 0; i < this.cells.size(); i++) {
            BlockPos cellPos = this.cells.get(i);
            if (pos.equals(cellPos)) {
                int goalAmount = this.winningAmounts.get(i).intValue();
                playerIn.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + String.format("This container needs %d cells", Integer.valueOf(goalAmount))));
                return;
            }
        }
    }
}
