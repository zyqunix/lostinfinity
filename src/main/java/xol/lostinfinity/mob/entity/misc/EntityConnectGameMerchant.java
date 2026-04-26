package xol.lostinfinity.mob.entity.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityConnectGameMerchant.class */
public class EntityConnectGameMerchant extends EntityLiving {
    private boolean game;
    private ArrayList<BlockPos> buttons;
    private boolean win;
    private Vec3i dir;
    private int top;
    private BlockPos startPos;
    private boolean lose;
    private boolean playerPlaced;
    private int numConnect;
    private int lastMove;
    private boolean horizontal;
    private int up;

    private int getLastMove() {
        return this.lastMove;
    }

    private void setLastMove(int lastMove) {
        this.lastMove = lastMove;
    }

    public EntityConnectGameMerchant(World worldIn) {
        super(worldIn);
        this.game = false;
        this.buttons = new ArrayList<>();
        this.win = false;
        this.dir = new Vec3i(0, 0, 1);
        this.lose = false;
        this.playerPlaced = false;
        this.numConnect = 4;
        this.lastMove = 0;
        this.horizontal = true;
        this.up = 1;
    }

    public void setDir(Vec3i dir) {
        this.dir = dir;
    }

    protected void func_70088_a() {
        super.func_70088_a();
    }

    public void startGame() {
        this.game = true;
    }

    public BlockPos nearestButton(BlockPos pos, ArrayList<BlockPos> visited) {
        BlockPos a = pos.func_177982_a(1, 0, 1);
        BlockPos b = pos.func_177982_a(-1, 0, 1);
        BlockPos c = pos.func_177982_a(1, 0, 1);
        BlockPos d = pos.func_177982_a(-1, 0, -1);
        BlockPos e = pos.func_177982_a(1, 0, 0);
        BlockPos f = pos.func_177982_a(-1, 0, 0);
        BlockPos g = pos.func_177982_a(0, 0, 1);
        BlockPos h = pos.func_177982_a(0, 0, -1);
        BlockPos i = pos.func_177982_a(1, 0, -1);
        this.field_70170_p.func_180495_p(i);
        if (this.field_70170_p.func_180495_p(a) == BlockInit.connectButton.func_176223_P() && !visited.contains(a)) {
            return a;
        }
        if (this.field_70170_p.func_180495_p(b) == BlockInit.connectButton.func_176223_P() && !visited.contains(b)) {
            return b;
        }
        if (this.field_70170_p.func_180495_p(c) == BlockInit.connectButton.func_176223_P() && !visited.contains(c)) {
            return c;
        }
        if (this.field_70170_p.func_180495_p(d) == BlockInit.connectButton.func_176223_P() && !visited.contains(d)) {
            return d;
        }
        if (this.field_70170_p.func_180495_p(e) == BlockInit.connectButton.func_176223_P() && !visited.contains(e)) {
            return e;
        }
        if (this.field_70170_p.func_180495_p(f) == BlockInit.connectButton.func_176223_P() && !visited.contains(f)) {
            return f;
        }
        if (this.field_70170_p.func_180495_p(g) == BlockInit.connectButton.func_176223_P() && !visited.contains(g)) {
            return g;
        }
        if (this.field_70170_p.func_180495_p(h) == BlockInit.connectButton.func_176223_P() && !visited.contains(h)) {
            return h;
        }
        if (this.field_70170_p.func_180495_p(i) == BlockInit.connectButton.func_176223_P() && !visited.contains(i)) {
            return i;
        }
        return null;
    }

    public void setButtonPositions(BlockPos reference, int stretch, int yRange, int numConnect) {
        this.numConnect = numConnect;
        this.top = stretch * 2;
        BlockPos pos = reference.func_177982_a(0, -1, 0);
        ArrayList<BlockPos> visited = new ArrayList<>();
        ArrayList<BlockPos> tempbuttons = new ArrayList<>();
        while (nearestButton(pos, visited) != null && this.field_70170_p.func_180495_p(nearestButton(pos, visited)) == BlockInit.connectButton.func_176223_P()) {
            pos = nearestButton(pos, visited);
            tempbuttons.add(pos);
            visited.add(pos);
        }
        reference.func_177958_n();
        double dFunc_177956_o = reference.func_177956_o() - 1;
        reference.func_177952_p();
        ArrayList<Double> values = new ArrayList<>();
        double yValue = tempbuttons.get(0).func_177956_o();
        double xValue = tempbuttons.get(0).func_177958_n();
        double zValue = tempbuttons.get(0).func_177952_p();
        boolean operatingX = tempbuttons.get(0).func_177958_n() != tempbuttons.get(1).func_177958_n();
        if (!tempbuttons.isEmpty()) {
            for (BlockPos tempPos : tempbuttons) {
                values.add(Double.valueOf(operatingX ? tempPos.func_177958_n() : tempPos.func_177952_p()));
            }
            Collections.sort(values);
            for (Double tempValue : values) {
                this.buttons.add(new BlockPos(operatingX ? tempValue.doubleValue() : xValue, yValue, operatingX ? zValue : tempValue.doubleValue()));
            }
        }
        BlockPos b1 = this.buttons.get(0);
        BlockPos b2 = this.buttons.get(1);
        BlockPos b3 = b1.func_177982_a(1, 0, 0);
        this.field_70170_p.func_180495_p(b3);
        if (b1.func_177952_p() != b2.func_177952_p()) {
            this.horizontal = false;
            if (this.field_70170_p.func_180495_p(b1.func_177982_a(1, 0, 0)).func_177230_c() == BlockInit.connectBlock || this.field_70170_p.func_180495_p(b1.func_177982_a(1, 0, 0)).func_177230_c() == BlockInit.connectBlockYellow || this.field_70170_p.func_180495_p(b1.func_177982_a(1, 0, 0)).func_177230_c() == BlockInit.connectBlockRed) {
                this.up = 1;
                this.startPos = b1.func_177982_a(1, 0, 0);
                return;
            } else {
                this.up = -1;
                this.startPos = b1.func_177982_a(-1, 0, 0);
                return;
            }
        }
        this.horizontal = true;
        if (this.field_70170_p.func_180495_p(b1.func_177982_a(0, 0, 1)).func_177230_c() == BlockInit.connectBlock || this.field_70170_p.func_180495_p(b1.func_177982_a(0, 0, 1)).func_177230_c() == BlockInit.connectBlockYellow || this.field_70170_p.func_180495_p(b1.func_177982_a(0, 0, 1)).func_177230_c() == BlockInit.connectBlockRed) {
            this.up = 1;
            this.startPos = b1.func_177982_a(0, 0, 1);
        } else {
            this.up = -1;
            this.startPos = b1.func_177982_a(0, 0, 1);
        }
    }

    public boolean playerPlaced() {
        return this.playerPlaced;
    }

    public void clearTokens() {
        BlockPos ref = this.startPos;
        if (ref != null) {
            for (int i = -1; i < this.buttons.size() + 1; i++) {
                int i2 = 0;
                while (true) {
                    int j = i2;
                    if (j >= this.top || j <= (-this.top)) {
                        break;
                    }
                    BlockPos temp = ref.func_177982_a(this.horizontal ? i : j, 0, this.horizontal ? j : i);
                    IBlockState state = this.field_70170_p.func_180495_p(temp);
                    if (state == BlockInit.connectBlockRed.func_176223_P() || state == BlockInit.connectBlockYellow.func_176223_P() || state == BlockInit.connectBlock.func_176223_P()) {
                        this.field_70170_p.func_175656_a(temp, BlockInit.connectBlock.func_176223_P());
                    }
                    i2 = j + this.up;
                }
            }
        }
    }

    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        if (this.field_70170_p.field_72995_K || !this.win) {
            if (!this.field_70170_p.field_72995_K && !this.game) {
                clearTokens();
                startGame();
                player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "Start!"));
                return true;
            }
            if (!this.field_70170_p.field_72995_K && this.lose) {
                player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "Sorry, you didn't win this time!"));
                this.game = false;
                return true;
            }
            return true;
        }
        player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "I have left you a reward!"));
        ItemStack mapstack = new ItemStack(ItemInit.resolveMap);
        mapstack.func_77982_d(new NBTTagCompound());
        mapstack.func_77978_p().func_74768_a("MapEntityType", this.field_70170_p.field_73012_v.nextInt(8));
        mapstack.func_77978_p().func_74768_a("MapEntityNum", 2 + this.field_70170_p.field_73012_v.nextInt(4));
        EntityItem geoloc = new EntityItem(this.field_70170_p, player.field_70165_t, player.field_70163_u + 1.0d, player.field_70161_v, mapstack);
        geoloc.field_70159_w = 0.0d;
        geoloc.field_70181_x = 0.0d;
        geoloc.field_70179_y = 0.0d;
        this.field_70170_p.func_72838_d(geoloc);
        this.win = false;
        deathEffect();
        return true;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa > 5 && this.buttons.isEmpty()) {
                deathEffect();
            }
            if (this.win && this.game) {
                for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
                    near_pl.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "You have won! Come get your reward"));
                    this.game = false;
                }
            }
            if (this.lose && this.field_70173_aa % 140 == 5) {
                for (EntityPlayer near_pl2 : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
                    near_pl2.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "Sorry, you didn't win this time!"));
                    this.game = false;
                }
                deathEffect();
            }
            if (!this.lose && this.game && this.playerPlaced && this.field_70173_aa % 20 == 0) {
                opponentPlace();
                if (isComplete()) {
                    this.lose = true;
                }
            }
        }
    }

    public boolean isLost() {
        return this.lose;
    }

    private void opponentPlace() {
        int ability;
        BlockPos playerWinPos = findPlayerWin();
        if (playerWinPos != null) {
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
                near_pl.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "I won't allow you to win that easy!"));
            }
            this.playerPlaced = false;
            put(playerWinPos, false);
        }
        Random rand = new Random();
        int iNextInt = rand.nextInt(4);
        while (true) {
            ability = iNextInt;
            if (ability != getLastMove()) {
                break;
            } else {
                iNextInt = rand.nextInt(4);
            }
        }
        setLastMove(ability);
        switch (ability) {
            case 0:
                replacePlayerPiece();
                for (EntityPlayer near_pl2 : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
                    near_pl2.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "I'll take one of yours!"));
                }
                break;
            case 1:
                randomPlace();
                randomPlace();
                for (EntityPlayer near_pl3 : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
                    near_pl3.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "Double Up!"));
                }
                break;
            case 2:
                placeOnTop();
                for (EntityPlayer near_pl4 : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
                    near_pl4.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "I'll pile up my pieces until you surrender"));
                }
                break;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x007e, code lost:
    
        r7 = r7 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void replacePlayerPiece() {
        /*
            r5 = this;
            r0 = r5
            net.minecraft.util.math.BlockPos r0 = r0.startPos
            r6 = r0
            r0 = r6
            if (r0 == 0) goto L84
            r0 = 0
            r7 = r0
        Lb:
            r0 = r7
            r1 = r5
            java.util.ArrayList<net.minecraft.util.math.BlockPos> r1 = r1.buttons
            int r1 = r1.size()
            if (r0 >= r1) goto L84
            r0 = 0
            r8 = r0
        L18:
            r0 = r8
            r1 = r5
            int r1 = r1.top
            if (r0 >= r1) goto L7e
            r0 = r8
            r1 = r5
            int r1 = r1.top
            int r1 = -r1
            if (r0 <= r1) goto L7e
            r0 = r6
            r1 = r5
            boolean r1 = r1.horizontal
            if (r1 == 0) goto L35
            r1 = r7
            goto L36
        L35:
            r1 = r8
        L36:
            r2 = 0
            r3 = r5
            boolean r3 = r3.horizontal
            if (r3 == 0) goto L42
            r3 = r8
            goto L43
        L42:
            r3 = r7
        L43:
            net.minecraft.util.math.BlockPos r0 = r0.func_177982_a(r1, r2, r3)
            r9 = r0
            r0 = r5
            net.minecraft.world.World r0 = r0.field_70170_p
            r1 = r9
            net.minecraft.block.state.IBlockState r0 = r0.func_180495_p(r1)
            r10 = r0
            r0 = r10
            net.minecraft.block.Block r1 = xol.lostinfinity.init.BlockInit.connectBlockYellow
            net.minecraft.block.state.IBlockState r1 = r1.func_176223_P()
            if (r0 != r1) goto L74
            r0 = r5
            net.minecraft.world.World r0 = r0.field_70170_p
            r1 = r9
            net.minecraft.block.Block r2 = xol.lostinfinity.init.BlockInit.connectBlockRed
            net.minecraft.block.state.IBlockState r2 = r2.func_176223_P()
            boolean r0 = r0.func_175656_a(r1, r2)
            r0 = r5
            r1 = 0
            r0.playerPlaced = r1
            return
        L74:
            r0 = r8
            r1 = r5
            int r1 = r1.up
            int r0 = r0 + r1
            r8 = r0
            goto L18
        L7e:
            int r7 = r7 + 1
            goto Lb
        L84:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: xol.lostinfinity.mob.entity.misc.EntityConnectGameMerchant.replacePlayerPiece():void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x008a, code lost:
    
        r7 = r7 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void placeOnTop() {
        /*
            r5 = this;
            r0 = r5
            net.minecraft.util.math.BlockPos r0 = r0.startPos
            r6 = r0
            r0 = r6
            if (r0 == 0) goto L90
            r0 = 0
            r7 = r0
        Lb:
            r0 = r7
            r1 = r5
            java.util.ArrayList<net.minecraft.util.math.BlockPos> r1 = r1.buttons
            int r1 = r1.size()
            if (r0 >= r1) goto L90
            r0 = 0
            r8 = r0
        L18:
            r0 = r8
            r1 = r5
            int r1 = r1.top
            if (r0 >= r1) goto L8a
            r0 = r8
            r1 = r5
            int r1 = r1.top
            int r1 = -r1
            if (r0 <= r1) goto L8a
            r0 = r6
            r1 = r5
            boolean r1 = r1.horizontal
            if (r1 == 0) goto L35
            r1 = r7
            goto L36
        L35:
            r1 = r8
        L36:
            r2 = 0
            r3 = r5
            boolean r3 = r3.horizontal
            if (r3 == 0) goto L42
            r3 = r8
            goto L43
        L42:
            r3 = r7
        L43:
            net.minecraft.util.math.BlockPos r0 = r0.func_177982_a(r1, r2, r3)
            r9 = r0
            r0 = r5
            net.minecraft.world.World r0 = r0.field_70170_p
            r1 = r9
            net.minecraft.block.state.IBlockState r0 = r0.func_180495_p(r1)
            r10 = r0
            r0 = r10
            net.minecraft.block.Block r1 = xol.lostinfinity.init.BlockInit.connectBlockYellow
            net.minecraft.block.state.IBlockState r1 = r1.func_176223_P()
            if (r0 == r1) goto L69
            r0 = r10
            net.minecraft.block.Block r1 = xol.lostinfinity.init.BlockInit.connectBlockRed
            net.minecraft.block.state.IBlockState r1 = r1.func_176223_P()
            if (r0 != r1) goto L80
        L69:
            r0 = r5
            r1 = 0
            r0.playerPlaced = r1
            r0 = r5
            r1 = r5
            java.util.ArrayList<net.minecraft.util.math.BlockPos> r1 = r1.buttons
            r2 = r7
            java.lang.Object r1 = r1.get(r2)
            net.minecraft.util.math.BlockPos r1 = (net.minecraft.util.math.BlockPos) r1
            r2 = 0
            boolean r0 = r0.put(r1, r2)
            return
        L80:
            r0 = r8
            r1 = r5
            int r1 = r1.up
            int r0 = r0 + r1
            r8 = r0
            goto L18
        L8a:
            int r7 = r7 + 1
            goto Lb
        L90:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: xol.lostinfinity.mob.entity.misc.EntityConnectGameMerchant.placeOnTop():void");
    }

    private BlockPos findPlayerWin() {
        BlockPos ref = this.startPos;
        if (ref != null) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < this.buttons.size() && i2 > this.buttons.size()) {
                    int i3 = 0;
                    while (true) {
                        int j = i3;
                        if (j >= this.top || j <= (-this.top)) {
                            break;
                        }
                        BlockPos temp = ref.func_177982_a(this.horizontal ? i2 : j, 0, this.horizontal ? j : i2);
                        IBlockState state = this.field_70170_p.func_180495_p(temp);
                        if (state == BlockInit.connectBlockYellow.func_176223_P()) {
                            boolean connectLeft = true;
                            boolean connectRight = true;
                            boolean connectUp = true;
                            boolean connectUpLeft = true;
                            boolean connectUpRight = true;
                            for (int k = 0; k < this.numConnect - 1; k++) {
                                IBlockState stateLeft = this.field_70170_p.func_180495_p(temp.func_177982_a(this.horizontal ? k : 0, 0, this.horizontal ? 0 : k));
                                IBlockState stateRight = this.field_70170_p.func_180495_p(temp.func_177982_a(this.horizontal ? -k : 0, 0, this.horizontal ? 0 : -k));
                                IBlockState stateUp = this.field_70170_p.func_180495_p(temp.func_177982_a(this.horizontal ? 0 : k * this.up, 0, this.horizontal ? k * this.up : 0));
                                IBlockState stateUpLeft = this.field_70170_p.func_180495_p(temp.func_177982_a(this.horizontal ? k : k * this.up, 0, this.horizontal ? k * this.up : k));
                                IBlockState stateUpRight = this.field_70170_p.func_180495_p(temp.func_177982_a(this.horizontal ? -k : k * this.up, 0, this.horizontal ? k * this.up : -k));
                                if (stateLeft != BlockInit.connectBlockYellow.func_176223_P()) {
                                    connectLeft = false;
                                }
                                if (stateRight != BlockInit.connectBlockYellow.func_176223_P()) {
                                    connectRight = false;
                                }
                                if (stateUp != BlockInit.connectBlockYellow.func_176223_P()) {
                                    connectUp = false;
                                }
                                if (stateUpLeft != BlockInit.connectBlockYellow.func_176223_P()) {
                                    connectUpLeft = false;
                                }
                                if (stateUpRight != BlockInit.connectBlockYellow.func_176223_P()) {
                                    connectUpRight = false;
                                }
                            }
                            if (connectLeft) {
                                IBlockState hereState = this.field_70170_p.func_180495_p(temp.func_177982_a(this.horizontal ? this.numConnect - 1 : 0, 0, this.horizontal ? 0 : this.numConnect - 1));
                                IBlockState belowState = this.field_70170_p.func_180495_p(temp.func_177982_a(this.horizontal ? this.numConnect - 1 : 0, 0, this.horizontal ? 0 : this.numConnect - 1).func_177982_a(this.horizontal ? 0 : (-1) * this.up, 0, this.horizontal ? (-1) * this.up : 0));
                                if (belowState != BlockInit.connectBlock.func_176223_P() && hereState == BlockInit.connectBlock.func_176223_P()) {
                                    return this.buttons.get((i2 + this.numConnect) - 1);
                                }
                            }
                            if (connectRight) {
                                IBlockState hereState2 = this.field_70170_p.func_180495_p(temp.func_177982_a(this.horizontal ? -(this.numConnect - 1) : 0, 0, this.horizontal ? 0 : -(this.numConnect - 1)));
                                IBlockState belowState2 = this.field_70170_p.func_180495_p(temp.func_177982_a(this.horizontal ? -(this.numConnect - 1) : 0, 0, this.horizontal ? 0 : -(this.numConnect - 1)).func_177982_a(this.horizontal ? 0 : (-1) * this.up, 0, this.horizontal ? (-1) * this.up : 0));
                                if (belowState2 != BlockInit.connectBlock.func_176223_P() && hereState2 == BlockInit.connectBlock.func_176223_P()) {
                                    return this.buttons.get((i2 - this.numConnect) + 1);
                                }
                            }
                            if (connectUp) {
                                IBlockState belowState3 = this.field_70170_p.func_180495_p(temp.func_177982_a(this.horizontal ? 0 : this.numConnect * this.up, 0, this.horizontal ? this.numConnect * this.up : 0).func_177982_a(this.horizontal ? 0 : (-1) * this.up, 0, this.horizontal ? (-1) * this.up : 0));
                                IBlockState hereState3 = this.field_70170_p.func_180495_p(temp.func_177982_a(this.horizontal ? 0 : this.numConnect * this.up, 0, this.horizontal ? this.numConnect * this.up : 0));
                                if (hereState3 == BlockInit.connectBlock.func_176223_P() && belowState3 != BlockInit.connectBlockRed.func_176223_P()) {
                                    return this.buttons.get(i2);
                                }
                            }
                            if (connectUpRight) {
                                IBlockState hereState4 = this.field_70170_p.func_180495_p(temp.func_177982_a(this.horizontal ? -(this.numConnect - 1) : this.numConnect * this.up, 0, this.horizontal ? this.numConnect * this.up : -(this.numConnect - 1)));
                                IBlockState belowState4 = this.field_70170_p.func_180495_p(temp.func_177982_a(this.horizontal ? -(this.numConnect - 1) : this.numConnect * this.up, 0, this.horizontal ? this.numConnect * this.up : -(this.numConnect - 1)).func_177982_a(this.horizontal ? 0 : (-1) * this.up, 0, this.horizontal ? (-1) * this.up : 0));
                                if (belowState4 != BlockInit.connectBlock.func_176223_P() && hereState4 == BlockInit.connectBlock.func_176223_P()) {
                                    return this.buttons.get((i2 - this.numConnect) + 1);
                                }
                            }
                            if (connectUpLeft) {
                                IBlockState hereState5 = this.field_70170_p.func_180495_p(temp.func_177982_a(this.horizontal ? this.numConnect - 1 : this.numConnect * this.up, 0, this.horizontal ? this.numConnect * this.up : this.numConnect - 1));
                                IBlockState belowState5 = this.field_70170_p.func_180495_p(temp.func_177982_a(this.horizontal ? this.numConnect - 1 : this.numConnect * this.up, 0, this.horizontal ? this.numConnect * this.up : this.numConnect - 1).func_177982_a(this.horizontal ? 0 : (-1) * this.up, 0, this.horizontal ? (-1) * this.up : 0));
                                if (belowState5 != BlockInit.connectBlock.func_176223_P() && hereState5 == BlockInit.connectBlock.func_176223_P()) {
                                    return this.buttons.get((i2 + this.numConnect) - 1);
                                }
                            } else {
                                continue;
                            }
                        }
                        i3 = j + this.up;
                    }
                } else {
                    return null;
                }
                i = i2 - this.up;
            }
        } else {
            return null;
        }
    }

    private void randomPlace() {
        Random rand = new Random();
        int randButton = rand.nextInt(this.buttons.size());
        if (put(this.buttons.get(randButton), false)) {
            this.playerPlaced = false;
        }
    }

    private void deathEffect() {
        if (!this.buttons.isEmpty()) {
            clearTokens();
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

    public boolean put(BlockPos pos, boolean isPlayer) {
        if (isComplete()) {
            if (isPlayer) {
                this.win = true;
            } else {
                this.lose = true;
            }
        }
        this.game = true;
        BlockPos ref = null;
        boolean placed = false;
        Iterator<BlockPos> it = this.buttons.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            BlockPos button = it.next();
            if (button.func_177958_n() == pos.func_177958_n() && button.func_177956_o() == pos.func_177956_o() && button.func_177952_p() == pos.func_177952_p()) {
                ref = button;
                break;
            }
        }
        if (ref != null) {
            BlockPos check = ref.func_177982_a(this.horizontal ? 0 : 1 * this.up, 0, this.horizontal ? 1 * this.up : 0);
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.top || i2 <= (-this.top)) {
                    break;
                }
                IBlockState checkState = this.field_70170_p.func_180495_p(check);
                if (checkState.func_177230_c() == BlockInit.connectBlockYellow || checkState.func_177230_c() == BlockInit.connectBlockRed) {
                    check = check.func_177982_a(this.horizontal ? 0 : 1 * this.up, 0, this.horizontal ? 1 * this.up : 0);
                } else {
                    if (checkState.func_177230_c() == BlockInit.connectBlock) {
                        break;
                    }
                    if (i2 == 0) {
                        return false;
                    }
                }
                i = i2 + this.up;
            }
            if (isPlayer) {
                if (this.field_70170_p.func_180495_p(check) == BlockInit.connectBlock.func_176223_P()) {
                    this.field_70170_p.func_175656_a(check, BlockInit.connectBlockYellow.func_176223_P());
                    this.playerPlaced = true;
                    placed = true;
                }
            } else if (this.field_70170_p.func_180495_p(check) == BlockInit.connectBlock.func_176223_P()) {
                this.field_70170_p.func_175656_a(check, BlockInit.connectBlockRed.func_176223_P());
                placed = true;
            }
            if (isComplete()) {
                if (isPlayer) {
                    this.win = true;
                } else {
                    this.lose = true;
                }
            }
        }
        return placed;
    }

    /* JADX WARN: Code restructure failed: missing block: B:168:0x0360, code lost:
    
        r9 = r9 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean isComplete() {
        /*
            Method dump skipped, instruction units count: 872
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: xol.lostinfinity.mob.entity.misc.EntityConnectGameMerchant.isComplete():boolean");
    }
}
