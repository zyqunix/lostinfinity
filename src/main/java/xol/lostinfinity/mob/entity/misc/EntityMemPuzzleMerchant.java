package xol.lostinfinity.mob.entity.misc;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
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
public class EntityMemPuzzleMerchant extends EntityLiving {
    private static final DataParameter<Boolean> HARD_MODE = EntityDataManager.func_187226_a(EntityMemPuzzleMerchant.class, DataSerializers.field_187198_h);
    private int puzzleStage;
    private int puzzleTimer;
    private int currentLight;
    private ArrayList<BlockPos> lightPositions;
    public EntityMemPuzzleMerchant(World worldIn) {
        super(worldIn);
        this.puzzleStage = 0;
        this.puzzleTimer = 0;
        this.currentLight = 0;
        this.lightPositions = new ArrayList<>();
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(HARD_MODE, false);
    }
    public boolean isHardmode() {
        return ((Boolean) this.field_70180_af.func_187225_a(HARD_MODE)).booleanValue();
    }
    public void setHardMode(boolean b) {
        this.field_70180_af.func_187227_b(HARD_MODE, Boolean.valueOf(b));
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
                    if (checkState.equals(BlockInit.labyrinthChargerUnpowered.func_176223_P())) {
                        this.lightPositions.add(checkPos);
                    } else if (checkState.equals(BlockInit.labyrinthChargerLit.func_176223_P())) {
                        this.lightPositions.add(checkPos);
                        this.field_70170_p.func_175656_a(checkPos, BlockInit.labyrinthChargerUnpowered.func_176223_P());
                    }
                }
            }
        }
    }
    private void randomizeLightOrder() {
        Collections.shuffle(this.lightPositions);
    }
    private void lightBlock(int list_index) {
        if (list_index > 0 && list_index <= this.lightPositions.size()) {
            BlockPos selected_pos = this.lightPositions.get(list_index - 1);
            this.field_70170_p.func_175656_a(selected_pos, BlockInit.labyrinthChargerLit.func_176223_P());
        }
    }
    private void darkenBlock(int list_index) {
        if (list_index > 0 && list_index <= this.lightPositions.size()) {
            BlockPos selected_pos = this.lightPositions.get(list_index - 1);
            this.field_70170_p.func_175656_a(selected_pos, BlockInit.labyrinthChargerUnpowered.func_176223_P());
        }
    }
    private boolean litCorrectly() {
        BlockPos selected_pos = this.lightPositions.get(this.currentLight);
        if (this.field_70170_p.func_180495_p(selected_pos).equals(BlockInit.labyrinthChargerUnpowered.func_176223_P())) {
            return false;
        }
        if (this.currentLight < this.lightPositions.size() - 1) {
            for (int check = this.currentLight + 1; check < this.lightPositions.size(); check++) {
                BlockPos check_pos = this.lightPositions.get(check);
                if (this.field_70170_p.func_180495_p(check_pos).equals(BlockInit.labyrinthChargerLit.func_176223_P())) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }
    private void darkenAllLights() {
        for (BlockPos pos : this.lightPositions) {
            this.field_70170_p.func_175656_a(pos, BlockInit.labyrinthChargerUnpowered.func_176223_P());
        }
    }
    private boolean validInput(Item item) {
        if (isHardmode()) {
            return item.equals(ItemInit.unpoweredEmberstar);
        }
        return item.equals(ItemInit.unpoweredStarcrystal);
    }
    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        if (this.puzzleStage == 0) {
            if (validInput(player.func_184586_b(hand).func_77973_b())) {
                player.func_184586_b(hand).func_190918_g(1);
                this.puzzleStage = 1;
                if (!this.field_70170_p.field_72995_K) {
                    if (this.lightPositions.isEmpty()) {
                        if (isHardmode()) {
                            func_145779_a(ItemInit.unpoweredStarcrystal, 1);
                        } else {
                            func_145779_a(ItemInit.unpoweredEmberstar, 1);
                        }
                        player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Underline, TextFmt.Red) + "Merchant: Error detecting puzzle. Call a new merchant."));
                        deathEffect();
                        return true;
                    }
                    func_184185_a(SoundEvents.field_187620_cL, 1.0f, 1.0f);
                    func_184185_a(SoundEvents.field_187915_go, 1.0f, 1.0f);
                    randomizeLightOrder();
                    player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Gold) + "Merchant: I will show you a sequence of lights. You will have to memorize the sequence and power the chargers with Electrified Quartz in that order."));
                    player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Underline, TextFmt.Red) + "Merchant: After I show you, you have to interact with me after EACH charger you power within a few seconds."));
                    player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "Merchant: Interact with me again to begin."));
                    return true;
                }
                return true;
            }
            return true;
        }
        if (!this.field_70170_p.field_72995_K) {
            if (this.puzzleStage == 1) {
                this.puzzleStage = 2;
                this.puzzleTimer = 30;
                this.currentLight = 0;
                player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Gold) + "Merchant: Starting sequence..."));
                func_184185_a(SoundEvents.field_187914_gn, 1.0f, 1.0f);
                return true;
            }
            if (this.puzzleStage == 3) {
                if (litCorrectly()) {
                    this.currentLight++;
                    if (this.currentLight == this.lightPositions.size()) {
                        player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "Merchant: The core was successfully charged! I have left you a reward!"));
                        if (isHardmode()) {
                            func_145779_a(ItemInit.eternalEmberstarGenerator, 1);
                        } else {
                            func_145779_a(ItemInit.starcrystalCapacitor, 1);
                        }
                        deathEffect();
                        return true;
                    }
                    this.puzzleTimer = 140;
                    player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "Merchant: Correct! Charge the next one!"));
                    func_184185_a(SoundEvents.field_187915_go, 1.0f, 1.0f);
                    return true;
                }
                player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Red) + "Merchant: Incorrect Order! The core has lost power."));
                func_184185_a(SoundEvents.field_187913_gm, 1.0f, 1.0f);
                deathEffect();
                return true;
            }
            return true;
        }
        return true;
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa > 5 && this.lightPositions.isEmpty()) {
                func_70106_y();
            }
            if (this.puzzleTimer > 0) {
                this.puzzleTimer--;
                if (this.puzzleTimer == 0) {
                    if (this.puzzleStage == 2) {
                        if (this.currentLight < this.lightPositions.size()) {
                            this.puzzleTimer = 25;
                            darkenBlock(this.currentLight);
                            this.currentLight++;
                            lightBlock(this.currentLight);
                            func_184185_a(SoundEvents.field_187649_bu, 2.0f, 1.0f);
                            return;
                        }
                        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
                            near_pl.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "Merchant: BEGIN!"));
                        }
                        this.puzzleTimer = 200;
                        darkenBlock(this.currentLight);
                        this.currentLight = 0;
                        this.puzzleStage = 3;
                        return;
                    }
                    for (EntityPlayer near_pl2 : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
                        near_pl2.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Red) + "Merchant: Times up. The core has lost power!"));
                    }
                    deathEffect();
                }
            }
        }
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
