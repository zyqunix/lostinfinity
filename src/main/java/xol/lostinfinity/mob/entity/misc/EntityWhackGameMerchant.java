package xol.lostinfinity.mob.entity.misc;

import java.util.ArrayList;
import java.util.Random;
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
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityWhackGameMerchant.class */
public class EntityWhackGameMerchant extends EntityLiving {
    private int puzzleTimer;
    private boolean game;
    private ArrayList<BlockPos> lightPositions;
    private int round;
    private boolean win;
    private int finalRound;

    public EntityWhackGameMerchant(World worldIn) {
        super(worldIn);
        this.puzzleTimer = 6;
        this.game = false;
        this.lightPositions = new ArrayList<>();
        this.round = 0;
        this.win = false;
        this.finalRound = 13;
    }

    protected void func_70088_a() {
        super.func_70088_a();
    }

    public void startGame() {
        this.game = true;
    }

    public void setLightPositions(BlockPos reference, int stretch, int yRange) {
        double xref = reference.func_177958_n();
        double yref = reference.func_177956_o() + 1;
        double zref = reference.func_177952_p();
        for (int y_offset = 0; y_offset <= yRange; y_offset++) {
            for (int z_offset = -stretch; z_offset <= stretch; z_offset++) {
                for (int x_offset = -stretch; x_offset <= stretch; x_offset++) {
                    BlockPos checkPos = new BlockPos(xref + ((double) x_offset), yref + ((double) y_offset), zref + ((double) z_offset));
                    IBlockState checkState = this.field_70170_p.func_180495_p(checkPos);
                    if (checkState.equals(BlockInit.whackBlockUnpowered.func_176223_P())) {
                        this.lightPositions.add(checkPos);
                    } else if (checkState.equals(BlockInit.whackBlockLit.func_176223_P())) {
                        this.lightPositions.add(checkPos);
                        this.field_70170_p.func_175656_a(checkPos, BlockInit.whackBlockUnpowered.func_176223_P());
                    }
                }
            }
        }
    }

    private void lightBlock(int list_index) {
        if (list_index > 0 && list_index <= this.lightPositions.size()) {
            BlockPos selected_pos = this.lightPositions.get(list_index - 1);
            this.field_70170_p.func_175656_a(selected_pos, BlockInit.whackBlockLit.func_176223_P());
        }
    }

    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        if (!this.field_70170_p.field_72995_K && this.win) {
            player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "I have left you your new ion cell!"));
            func_145779_a(ItemInit.rewiredIonCell, 1);
            this.win = false;
            deathEffect();
            return true;
        }
        if (!this.field_70170_p.field_72995_K && !this.game) {
            this.game = true;
            this.round = 0;
            player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Gold) + "Get Ready!"));
            return true;
        }
        return true;
    }

    private void darkenAllLights() {
        for (BlockPos pos : this.lightPositions) {
            this.field_70170_p.func_175656_a(pos, BlockInit.whackBlockUnpowered.func_176223_P());
        }
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa > 5 && this.lightPositions.isEmpty()) {
                func_70106_y();
            }
            if (this.game && this.field_70173_aa % (20 * this.puzzleTimer) == 0) {
                if (!allUnLit()) {
                    this.game = false;
                    this.round = 0;
                    for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
                        near_pl.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Red) + "The generator overheated. The ion cell was destroyed."));
                    }
                    deathEffect();
                    return;
                }
                if (this.round >= this.finalRound) {
                    this.game = false;
                    darkenAllLights();
                    this.round = 0;
                    this.win = true;
                    for (EntityPlayer near_pl2 : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
                        near_pl2.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "You successfully kept the generator from overheating. Come get your ion cell!"));
                    }
                    return;
                }
                this.round++;
                for (EntityPlayer near_pl3 : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
                    near_pl3.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Gold) + String.format("Progress: " + this.round + "/" + this.finalRound + " - Put out the ion batteries quick!", new Object[0])));
                }
                int numToLight = this.round + 2;
                Random rand = new Random();
                int randLight = 0;
                for (int i = 0; i < numToLight; i++) {
                    while (0 == 0) {
                        randLight = rand.nextInt(this.lightPositions.size());
                        if (this.field_70170_p.func_180495_p(this.lightPositions.get(randLight)).equals(BlockInit.whackBlockUnpowered.func_176223_P())) {
                            break;
                        }
                    }
                    lightBlock(randLight);
                }
            }
        }
    }

    private boolean allUnLit() {
        boolean unlit = true;
        for (BlockPos pos : this.lightPositions) {
            if (this.field_70170_p.func_180495_p(pos).equals(BlockInit.whackBlockLit.func_176223_P())) {
                unlit = false;
            }
        }
        return unlit;
    }

    private void deathEffect() {
        if (!this.lightPositions.isEmpty()) {
            darkenAllLights();
        }
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
