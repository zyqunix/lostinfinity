package xol.lostinfinity.mob.entity.boss;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.projectile.entity.EntityPuzzleMasterArrow;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/boss/EntityPuzzleMaster.class */
public class EntityPuzzleMaster extends EntityMultipleLives {
    private boolean shockwave;
    private ArrayList<BlockPos> waveBlocks;
    private BlockPos wavePos;
    private Vec3d waveDir;
    private int curWaveDist;
    private int floorHeight;
    private int phase;
    private int phaseTimer;
    private int phaseLength;
    private ArrayList<ArrayList<BlockPos>> quadrants;
    private int curQuadrant;
    private ArrayList<BlockPos> wallTurrets;
    private ArrayList<Integer> inActiveTurrets;
    private ArrayList<BlockPos> platforms;
    private ArrayList<BlockPos> lavaPositions;
    private static int waveLength = 17;
    private static final IBlockState quadrantSafeState = BlockInit.puzzleArenaFloor.func_176203_a(1);
    private static final IBlockState quadrantBadState = BlockInit.puzzleArenaFloor.func_176203_a(2);
    private static final IBlockState shockwaveState = BlockInit.puzzleArenaFloor.func_176203_a(0);
    private static final IBlockState platformState = BlockInit.labyrinthMetalBlack.func_176223_P();
    private static final IBlockState turretActive = BlockInit.puzzleMasterTurret.func_176203_a(1);
    private static final IBlockState turretInactive = BlockInit.puzzleMasterTurret.func_176203_a(0);

    public EntityPuzzleMaster(World worldIn) {
        super(worldIn);
        this.shockwave = false;
        this.waveBlocks = new ArrayList<>();
        this.wavePos = null;
        this.waveDir = new Vec3d(1.0d, 0.0d, 1.0d);
        this.curWaveDist = 0;
        this.floorHeight = 30;
        this.phase = 0;
        this.phaseTimer = 0;
        this.phaseLength = 400;
        this.quadrants = new ArrayList<>();
        this.curQuadrant = 0;
        this.wallTurrets = new ArrayList<>();
        this.inActiveTurrets = new ArrayList<>();
        this.platforms = new ArrayList<>();
        this.lavaPositions = new ArrayList<>();
        func_70105_a(2.0f, 6.0f);
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            for (EntityPlayer nearPlayer : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
                if (nearPlayer.field_71075_bZ.field_75100_b) {
                    IMaxAttack.dealTrueDamage(this, nearPlayer, nearPlayer.func_110138_aP() / 10.0f);
                }
            }
            if (this.phaseTimer < this.phaseLength) {
                this.phaseTimer++;
            } else {
                phaseUp();
            }
            if (this.phase != 2) {
                if (this.quadrants.isEmpty()) {
                    initQuadrants();
                    updateQuadrants();
                } else {
                    if (this.field_70173_aa % 99 == 0) {
                        this.curQuadrant = this.field_70146_Z.nextInt(4);
                        updateQuadrants();
                    }
                    if (this.field_70173_aa % 10 == 0) {
                        for (EntityPlayer nearPlayer2 : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
                            if (this.field_70170_p.func_180495_p(nearPlayer2.func_180425_c().func_177977_b()) == quadrantBadState) {
                                IMaxAttack.dealTrueDamage(this, nearPlayer2, nearPlayer2.func_110138_aP() / 6.0f);
                            }
                        }
                    }
                }
            }
            if (this.phase == 1) {
                if (this.wallTurrets.isEmpty()) {
                    initTurrets();
                    return;
                } else {
                    if (this.field_70173_aa % 50 == 0) {
                        fireTurrets();
                        updateTurrets();
                        return;
                    }
                    return;
                }
            }
            if (this.phase == 0) {
                if (!this.shockwave && this.field_70173_aa % 60 == 0) {
                    startShockwave();
                    return;
                } else {
                    if (this.shockwave) {
                        progressWave();
                        if (this.field_70173_aa % 100 == 99) {
                            endShockwave();
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
            if (this.phase == 2) {
                if (this.platforms.isEmpty()) {
                    initPlatforms();
                } else if (this.phaseTimer > 100 && this.lavaPositions.isEmpty()) {
                    initLava();
                }
            }
        }
    }

    private void initPlatforms() {
        messagePlayers("YOU BETTER HIDE SOMEWHERE HIGHER UP!");
        soundPlayers(SoundInit.CINEMATIC_WARNING, 1.25f);
        AxisAlignedBB arena = getArenaAABB();
        int xMin = ((int) arena.field_72340_a) + 1;
        int zMin = ((int) arena.field_72339_c) + 1;
        int y = this.floorHeight + 1;
        int xMax = ((int) arena.field_72336_d) - 1;
        int zMax = ((int) arena.field_72334_f) - 1;
        int xQuart = Math.abs(xMax - xMin) / 4;
        int zQuart = Math.abs(zMax - zMin) / 4;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                BlockPos platformPos1 = new BlockPos(xMin + xQuart + i, y, zMin + zQuart + j);
                BlockPos platformPos2 = new BlockPos(xMin + xQuart + i, y, (zMax - zQuart) + j);
                BlockPos platformPos3 = new BlockPos((xMax - xQuart) + i, y, zMin + zQuart + j);
                BlockPos platformPos4 = new BlockPos((xMax - xQuart) + i, y, (zMax - zQuart) + j);
                this.platforms.add(platformPos4);
                this.platforms.add(platformPos3);
                this.platforms.add(platformPos2);
                this.platforms.add(platformPos1);
            }
        }
        for (BlockPos platformPos : this.platforms) {
            this.field_70170_p.func_175656_a(platformPos, platformState);
        }
    }

    private void initLava() {
        soundPlayers(SoundInit.WATER_REVEAL, 1.25f);
        AxisAlignedBB arena = getArenaAABB();
        int xMin = ((int) arena.field_72340_a) + 2;
        int zMin = ((int) arena.field_72339_c) + 2;
        int y = this.floorHeight + 1;
        int xMax = ((int) arena.field_72336_d) - 2;
        int zMax = ((int) arena.field_72334_f) - 2;
        for (int i = xMin; i <= xMax; i++) {
            for (int j = zMin; j <= zMax; j++) {
                BlockPos lavaPos = new BlockPos(i, y, j);
                if (!this.platforms.contains(lavaPos)) {
                    this.lavaPositions.add(lavaPos);
                    this.field_70170_p.func_175656_a(lavaPos, BlockInit.puzzleLava.func_176223_P());
                }
            }
        }
    }

    private void endLava() {
        for (BlockPos platformPos : this.platforms) {
            this.field_70170_p.func_175698_g(platformPos);
        }
        for (BlockPos lavaPos : this.lavaPositions) {
            this.field_70170_p.func_175698_g(lavaPos);
        }
        this.lavaPositions.clear();
        this.platforms.clear();
    }

    private void updateTurrets() {
        for (BlockPos turretPos : this.wallTurrets) {
            int index = this.wallTurrets.indexOf(turretPos);
            if (this.inActiveTurrets.contains(Integer.valueOf(index))) {
                this.field_70170_p.func_175656_a(turretPos, turretActive);
            }
        }
        for (int i = 0; i < this.inActiveTurrets.size(); i++) {
            int curIndex = this.inActiveTurrets.get(i).intValue();
            if (curIndex < this.wallTurrets.size() - 1) {
                this.inActiveTurrets.set(i, Integer.valueOf(curIndex + 1));
            } else {
                this.inActiveTurrets.set(i, 0);
            }
        }
        for (BlockPos turretPos2 : this.wallTurrets) {
            int index2 = this.wallTurrets.indexOf(turretPos2);
            if (this.inActiveTurrets.contains(Integer.valueOf(index2))) {
                this.field_70170_p.func_175656_a(turretPos2, turretInactive);
            }
        }
    }

    private void fireTurrets() {
        soundPlayers(SoundInit.GENERIC_WEAPON_14, 1.25f);
        AxisAlignedBB arena = getArenaAABB();
        int i = ((int) arena.field_72340_a) + 1;
        int i2 = ((int) arena.field_72339_c) + 1;
        int i3 = ((int) arena.field_72334_f) - 1;
        for (BlockPos turretPos : this.wallTurrets) {
            int index = this.wallTurrets.indexOf(turretPos);
            if (!this.inActiveTurrets.contains(Integer.valueOf(index))) {
                Vec3d dir = new Vec3d(-1.0d, 0.0d, 0.0d);
                EntityPuzzleMasterArrow arrow = new EntityPuzzleMasterArrow(this.field_70170_p, this);
                arrow.func_70634_a(((double) turretPos.func_177958_n()) + dir.field_72450_a + 0.5d, ((double) turretPos.func_177956_o()) + dir.field_72448_b + 0.5d, ((double) turretPos.func_177952_p()) + dir.field_72449_c + 0.5d);
                arrow.func_70186_c(dir.field_72450_a, dir.field_72448_b, dir.field_72449_c, 0.3f, 0.0f);
                this.field_70170_p.func_72838_d(arrow);
            }
        }
    }

    private void initTurrets() {
        messagePlayers("DODGE THIS!");
        AxisAlignedBB arena = getArenaAABB();
        int i = ((int) arena.field_72340_a) + 1;
        int zMin = ((int) arena.field_72339_c) + 1;
        int y = this.floorHeight + 1;
        int xMax = ((int) arena.field_72336_d) - 1;
        int zMax = ((int) arena.field_72334_f) - 1;
        for (int i2 = zMin; i2 <= zMax; i2++) {
            BlockPos botPos = new BlockPos(xMax, y, i2);
            this.wallTurrets.add(botPos);
            if (this.field_70146_Z.nextInt(2) == 0) {
                this.inActiveTurrets.add(Integer.valueOf(i2));
            } else {
                this.field_70170_p.func_175656_a(botPos, turretActive);
            }
        }
    }

    private void initQuadrants() {
        AxisAlignedBB arena = getArenaAABB();
        int xMin = ((int) arena.field_72340_a) + 1;
        int zMin = ((int) arena.field_72339_c) + 1;
        int y = this.floorHeight;
        int xMax = ((int) arena.field_72336_d) - 1;
        int zMax = ((int) arena.field_72334_f) - 1;
        int xMid = (xMax + xMin) / 2;
        int zMid = (zMax + zMin) / 2;
        ArrayList<BlockPos> quadrant1 = new ArrayList<>();
        for (int i = xMin; i < xMid; i++) {
            for (int k = zMin; k < zMid; k++) {
                BlockPos floorPos = new BlockPos(i, y, k);
                quadrant1.add(floorPos);
            }
        }
        this.quadrants.add(quadrant1);
        ArrayList<BlockPos> quadrant2 = new ArrayList<>();
        for (int i2 = xMin; i2 < xMid; i2++) {
            for (int k2 = zMid; k2 <= zMax; k2++) {
                BlockPos floorPos2 = new BlockPos(i2, y, k2);
                quadrant2.add(floorPos2);
            }
        }
        this.quadrants.add(quadrant2);
        ArrayList<BlockPos> quadrant3 = new ArrayList<>();
        for (int i3 = xMid; i3 <= xMax; i3++) {
            for (int k3 = zMid; k3 <= zMax; k3++) {
                BlockPos floorPos3 = new BlockPos(i3, y, k3);
                quadrant3.add(floorPos3);
            }
        }
        this.quadrants.add(quadrant3);
        ArrayList<BlockPos> quadrant4 = new ArrayList<>();
        for (int i4 = xMid; i4 < xMax; i4++) {
            for (int k4 = zMin; k4 <= zMid; k4++) {
                BlockPos floorPos4 = new BlockPos(i4, y, k4);
                quadrant4.add(floorPos4);
            }
        }
        this.quadrants.add(quadrant4);
        this.curQuadrant = this.field_70146_Z.nextInt(4);
    }

    private void startShockwave() {
        messagePlayers("SHOCKWAVE!");
        soundPlayers(SoundInit.ENERGY_PULSE, 1.25f);
        EntityPlayer player = null;
        Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB()).iterator();
        if (it.hasNext()) {
            EntityPlayer nearPlayer = (EntityPlayer) it.next();
            player = nearPlayer;
        }
        if (player != null) {
            Vec3d dir = player.func_174791_d().func_178788_d(func_174791_d());
            this.waveDir = new Vec3d(dir.field_72450_a, 0.0d, dir.field_72449_c).func_72432_b();
            this.shockwave = true;
        }
    }

    private void endShockwave() {
        if (!this.waveBlocks.isEmpty()) {
            for (BlockPos wavePos : this.waveBlocks) {
                this.field_70170_p.func_175698_g(wavePos);
            }
            this.waveBlocks.clear();
        }
        this.curWaveDist = 0;
        this.shockwave = false;
        this.wavePos = null;
    }

    private void progressWave() {
        BlockPos tempPos;
        if (this.wavePos == null) {
            BlockPos blockPosFunc_180425_c = func_180425_c();
            while (true) {
                tempPos = blockPosFunc_180425_c;
                if (!this.field_70170_p.func_175623_d(tempPos)) {
                    break;
                } else {
                    blockPosFunc_180425_c = tempPos.func_177977_b();
                }
            }
            this.wavePos = tempPos.func_177963_a(((-this.waveDir.field_72450_a) * ((double) waveLength)) / 2.0d, 0.0d, ((-this.waveDir.field_72449_c) * ((double) waveLength)) / 2.0d);
        }
        if (this.waveDir != null && this.field_70173_aa % 1 == 0) {
            if (!this.waveBlocks.isEmpty()) {
                for (BlockPos wavePos : this.waveBlocks) {
                    this.field_70170_p.func_175698_g(wavePos);
                }
                this.waveBlocks.clear();
            }
            AxisAlignedBB arena = getArenaAABB();
            int xMin = ((int) arena.field_72340_a) + 1;
            int zMin = ((int) arena.field_72339_c) + 1;
            int xMax = ((int) arena.field_72336_d) - 1;
            int zMax = ((int) arena.field_72334_f) - 1;
            for (int i = 0; i < waveLength; i++) {
                double height = (2.0d * Math.cos(((3.141592653589793d * ((double) i)) / 16.0d) + 3.141592653589793d)) + 3.0d;
                for (int j = 1; j <= height; j++) {
                    BlockPos raisePos = this.wavePos.func_177963_a(this.waveDir.field_72450_a * ((double) (i + this.curWaveDist)), j, this.waveDir.field_72449_c * ((double) (i + this.curWaveDist)));
                    if (raisePos.func_177958_n() >= xMax || raisePos.func_177952_p() >= zMax || raisePos.func_177958_n() <= xMin || raisePos.func_177952_p() <= zMin) {
                        break;
                    }
                    for (EntityPlayer nearPlayer : this.field_70170_p.func_72872_a(EntityPlayer.class, new AxisAlignedBB(raisePos))) {
                        IMaxAttack.dealTrueDamage(this, nearPlayer, nearPlayer.func_110138_aP() * 0.75f);
                        nearPlayer.func_70024_g(0.0d, 1.0d, 0.0d);
                        nearPlayer.field_70133_I = true;
                    }
                    this.waveBlocks.add(raisePos);
                    this.field_70170_p.func_175656_a(raisePos, shockwaveState);
                }
            }
            this.curWaveDist++;
        }
    }

    private void updateQuadrants() {
        soundPlayers(SoundInit.LIGHT_MAGIC, 1.25f);
        for (int i = 0; i < this.quadrants.size(); i++) {
            ArrayList<BlockPos> quadrant = this.quadrants.get(i);
            if (i == this.curQuadrant) {
                for (BlockPos quadPos : quadrant) {
                    this.field_70170_p.func_175656_a(quadPos, quadrantSafeState);
                }
            } else {
                for (BlockPos quadPos2 : quadrant) {
                    this.field_70170_p.func_175656_a(quadPos2, quadrantBadState);
                }
            }
        }
    }

    private void phaseUp() {
        this.phaseTimer = 0;
        if (this.phase == 0) {
            endShockwave();
        } else if (this.phase == 1) {
            endTurrets();
        } else if (this.phase == 2) {
            endLava();
        }
        if (this.phase < 3) {
            this.phase++;
        } else {
            this.phase = 0;
        }
    }

    private void endTurrets() {
        this.inActiveTurrets.clear();
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth((Entity) this, func_70638_az(), 3, 2.0f);
            return true;
        }
        return false;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 300;
    }

    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(-9.0d, 29.0d, -9.0d), new BlockPos(24.0d, 40.0d, 24.0d));
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        int lifePercent = Math.round((100 * (numberOfLives() - getLivesCount())) / numberOfLives());
        messagePlayers(TextFmt.Gold + "The Puzzle Master is at " + lifePercent + "% health.");
    }

    protected void messagePlayers(String message) {
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            contender.func_145747_a(new TextComponentString(message));
        }
    }

    protected void soundPlayers(SoundEvent sound, float vol) {
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            this.field_70170_p.func_184133_a((EntityPlayer) null, contender.func_180425_c(), sound, SoundCategory.MASTER, vol, 0.9f + (this.field_70146_Z.nextFloat() * 0.2f));
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K) {
            messagePlayers(TextFmt.Dark_Aqua + "I never truly die. I am everywhere...");
            func_145779_a(ItemInit.interspaceTransmitter, 1);
        }
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(15000.0d);
    }
}
