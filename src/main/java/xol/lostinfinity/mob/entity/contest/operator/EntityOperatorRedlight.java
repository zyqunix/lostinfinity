package xol.lostinfinity.mob.entity.contest.operator;

import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.data.ChunkPairing;
import xol.lostinfinity.dimension.util.WorldGenStructure;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerRedlight;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/operator/EntityOperatorRedlight.class */
public class EntityOperatorRedlight extends EntityOperatorBase {
    public EntityOperatorRedlight(World worldIn) {
        super(worldIn);
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void startGame() {
        EntityControllerRedlight gamehologram = new EntityControllerRedlight(this.field_70170_p);
        BlockPos holopos = ContestCoordinates.redlightControllerPos();
        gamehologram.func_70107_b(holopos.func_177958_n(), holopos.func_177956_o(), holopos.func_177952_p());
        gamehologram.setPlayerCount(this.contenders.size());
        gamehologram.setLights(BlockInit.championSignalGreen);
        gamehologram.darkenPillars();
        for (UUID pl_id : this.contenders) {
            EntityPlayer pl = this.field_70170_p.func_152378_a(pl_id);
            if (pl != null) {
                gamehologram.addPlayerToList(pl);
                pl.field_71071_by.func_70436_m();
                ChunkPairing chunk = ContestCoordinates.redlightGenLoc();
                int chunkX = chunk.chunkX();
                int chunkZ = chunk.chunkZ();
                double spawnX = startingOffset() + 31.0d + ((double) (chunkX * 16));
                double spawnZ = startingOffset() + 31.0d + ((double) (chunkZ * 16));
                pl.func_70634_a(spawnX, 25.0d, spawnZ);
                pl.func_145747_a(new TextComponentString(TextFmt.Aqua + "Grandmaster: Okay contenders, don't let me catch you moving during a red light!"));
            }
        }
        this.field_70170_p.func_72838_d(gamehologram);
        this.contenders.clear();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.redlightArenaAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getLobbyAABB() {
        return ContestCoordinates.redlightLobbyAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void generateArena() {
        ChunkPairing chunk = ContestCoordinates.redlightGenLoc();
        int chunkX = chunk.chunkX();
        int chunkZ = chunk.chunkZ();
        int posX = chunkX * 16;
        int posZ = chunkZ * 16;
        new WorldGenStructure(randomRedlightGame()).func_180709_b(this.field_70170_p, this.field_70146_Z, new BlockPos(posX, 24, posZ));
        new WorldGenStructure(randomRedlightGame()).generateWithRotation(this.field_70170_p, this.field_70146_Z, new BlockPos(posX + 32, 24, posZ), Rotation.CLOCKWISE_90);
        new WorldGenStructure(randomRedlightGame()).generateWithRotation(this.field_70170_p, this.field_70146_Z, new BlockPos(posX + 32, 24, posZ + 32), Rotation.CLOCKWISE_180);
        new WorldGenStructure(randomRedlightGame()).generateWithRotation(this.field_70170_p, this.field_70146_Z, new BlockPos(posX, 24, posZ + 32), Rotation.COUNTERCLOCKWISE_90);
    }

    private double startingOffset() {
        return this.field_70146_Z.nextBoolean() ? -1 : 2;
    }

    private String randomRedlightGame() {
        int game_pick = this.field_70146_Z.nextInt(12);
        return "contest/contest_redlight_arena_" + (game_pick + 1);
    }
}
