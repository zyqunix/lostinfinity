package xol.lostinfinity.mob.entity.contest.controller;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.data.ChunkPairing;
import xol.lostinfinity.dimension.data.TileMap;
import xol.lostinfinity.dimension.data.TileNode;
import xol.lostinfinity.dimension.util.WorldGenStructure;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/controller/EntityControllerHolodeck.class */
public class EntityControllerHolodeck extends EntityControllerBase {
    private TileMap my_holodeck;
    private List<TileNode> removed_tiles;
    private int style;
    private int stageTimer;

    public EntityControllerHolodeck(World worldIn) {
        super(worldIn);
        this.my_holodeck = null;
        this.removed_tiles = new ArrayList();
        this.style = 0;
        this.stageTimer = 100;
        func_70105_a(5.0f, 12.0f);
    }

    public void setMyHolodeck(TileMap holo) {
        this.my_holodeck = holo;
    }

    public void setArenaStyle(int styleIn) {
        this.style = styleIn;
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.holodeckArenaAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected BlockPos getSnapPos() {
        return ContestCoordinates.holodeckControllerPos();
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase, xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.my_holodeck == null) {
                func_70106_y();
            } else if (this.field_70170_p.field_73011_w.func_186058_p() == DimensionInit.grandmasterOutpost) {
                if (this.stageTimer == 0) {
                    upStage();
                } else {
                    this.stageTimer--;
                }
            }
        }
    }

    private void upStage() {
        int removeTiles;
        EntitySkeleton entityHusk;
        this.stage++;
        int typeOfStage = this.stage % 3;
        switch (typeOfStage) {
            case 0:
                TileNode dropNode = this.my_holodeck.randomLivingNode();
                BlockPos spawnPos = nodeToBlockPos(dropNode).func_177963_a(2.5d, 1.0d, 2.5d);
                if (this.field_70146_Z.nextBoolean()) {
                    if (this.field_70146_Z.nextBoolean()) {
                        entityHusk = new EntitySkeleton(this.field_70170_p);
                        messageContenders(TextFmt.Italic, "Nothing like a skeleton to make things spookier!");
                    } else {
                        entityHusk = new EntityHusk(this.field_70170_p);
                        messageContenders(TextFmt.Italic, "Don't hug the husk contenders!");
                    }
                    entityHusk.func_70107_b(spawnPos.func_177958_n(), spawnPos.func_177956_o() + 1, spawnPos.func_177952_p());
                    this.field_70170_p.func_72838_d(entityHusk);
                } else if (this.field_70146_Z.nextBoolean()) {
                    EntityItem saber = new EntityItem(this.field_70170_p, spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p(), new ItemStack(ItemInit.holoSaber));
                    this.field_70170_p.func_72838_d(saber);
                    messageContenders(TextFmt.Aqua, "I've dropped a holo saber into the arena. Who will get it?");
                } else {
                    EntityItem pulse = new EntityItem(this.field_70170_p, spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p(), new ItemStack(ItemInit.powerPulse));
                    this.field_70170_p.func_72838_d(pulse);
                    messageContenders(TextFmt.Blue, "A power pulse has been placed in the arena.");
                }
                break;
            case 1:
                int remainingTiles = this.my_holodeck.remainingTiles();
                if (remainingTiles > 10) {
                    removeTiles = 4;
                } else if (remainingTiles > 5) {
                    removeTiles = 2;
                } else {
                    removeTiles = 1;
                }
                this.removed_tiles = this.my_holodeck.removeTiles(Math.min(remainingTiles - 1, removeTiles));
                for (TileNode node : this.removed_tiles) {
                    highlightTile(node);
                }
                messageContenders(TextFmt.Gold, randomRemoveTileMsg());
                func_184185_a(SoundInit.SPIRE_TARGET, 1.0f, 1.0f);
                break;
            case 2:
                for (TileNode node2 : this.removed_tiles) {
                    removeTile(node2);
                }
                this.removed_tiles.clear();
                func_184185_a(SoundInit.SPIRE_DIFFICULTY, 1.0f, 1.0f);
                break;
        }
        this.stageTimer = 120;
    }

    private BlockPos nodeToBlockPos(TileNode tile) {
        ChunkPairing chunk = ContestCoordinates.holodeckGenLoc();
        int chunkX = chunk.chunkX();
        int chunkZ = chunk.chunkZ();
        int holoX = 27 + (chunkX * 16);
        int holoZ = (-16) + (chunkZ * 16);
        int posX = tile.getX();
        int posY = tile.getY();
        int posZ = tile.getZ();
        return new BlockPos(holoX + (6 * posX), posY, holoZ + (6 * posZ));
    }

    private void highlightTile(TileNode tile) {
        new WorldGenStructure("contest/holodeckplatformh").func_180709_b(this.field_70170_p, this.field_70170_p.field_73012_v, nodeToBlockPos(tile));
    }

    private void removeTile(TileNode tile) {
        BlockPos start = nodeToBlockPos(tile);
        for (int xp = 0; xp < 5; xp++) {
            for (int zp = 0; zp < 5; zp++) {
                this.field_70170_p.func_175698_g(start.func_177982_a(xp, 0, zp));
            }
        }
    }

    private String randomRemoveTileMsg() {
        switch (this.field_70146_Z.nextInt(5)) {
            case 0:
                return "Some more tiles are getting ready to disappear!";
            case 1:
                return "Some tiles are ready to vanish beneath your feet.";
            case 2:
                return "Time to remove part of the arena!";
            case 3:
                return "Who's ready to fall into the void?";
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                return "I've marked more tiles for removal contenders!";
            default:
                return "Some more tiles are getting ready to disappear!";
        }
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void rewardPlayer(EntityPlayer player, int placement) {
        if (player.field_70165_t > 100.0d) {
            BlockPos teleTo = ContestCoordinates.holodeckLobbyPos();
            player.func_70634_a(teleTo.func_177958_n(), teleTo.func_177956_o(), teleTo.func_177952_p());
        }
        int reward_count = Math.min(1 + (placement * Math.floorDiv(this.stage, 4)) + (placement == this.contenderCount - 1 ? this.contenderCount * 2 : 0), 50);
        player.func_191521_c(new ItemStack(ItemInit.zirconiaCerulean, reward_count));
    }
}
