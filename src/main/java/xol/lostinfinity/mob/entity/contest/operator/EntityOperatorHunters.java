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
import xol.lostinfinity.mob.entity.contest.EntityBloodhunter;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerHunters;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/operator/EntityOperatorHunters.class */
public class EntityOperatorHunters extends EntityOperatorBase {
    public EntityOperatorHunters(World worldIn) {
        super(worldIn);
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void arenaClear() {
        for (EntityBloodhunter hunter : this.field_70170_p.func_72872_a(EntityBloodhunter.class, getArenaAABB())) {
            hunter.func_70106_y();
        }
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void startGame() {
        EntityControllerHunters gamehologram = new EntityControllerHunters(this.field_70170_p);
        BlockPos holopos = ContestCoordinates.huntersControllerPos();
        gamehologram.func_70107_b(holopos.func_177958_n(), holopos.func_177956_o(), holopos.func_177952_p());
        gamehologram.setPlayerCount(this.contenders.size());
        for (UUID pl_id : this.contenders) {
            EntityPlayer pl = this.field_70170_p.func_152378_a(pl_id);
            if (pl != null) {
                gamehologram.addPlayerToList(pl);
                pl.field_71071_by.func_70436_m();
                BlockPos spawnPos = ContestCoordinates.huntersArenaCenterPos();
                pl.func_70634_a(((double) spawnPos.func_177958_n()) + startingOffset(), spawnPos.func_177956_o(), ((double) spawnPos.func_177952_p()) + startingOffset());
                pl.func_145747_a(new TextComponentString(TextFmt.Aqua + "Grandmaster: Okay contenders, scurry quickly! A bloodhunter will be released in 10 seconds."));
            }
        }
        this.field_70170_p.func_72838_d(gamehologram);
        this.contenders.clear();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.huntersArenaAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getLobbyAABB() {
        return ContestCoordinates.huntersLobbyAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void generateArena() {
        ChunkPairing chunk = ContestCoordinates.huntersGenLoc();
        int chunkX = chunk.chunkX();
        int chunkZ = chunk.chunkZ();
        int posX = chunkX * 16;
        int posZ = chunkZ * 16;
        new WorldGenStructure(randomHuntersGame()).func_180709_b(this.field_70170_p, this.field_70146_Z, new BlockPos(posX, 22, posZ));
        new WorldGenStructure(randomHuntersGame()).generateWithRotation(this.field_70170_p, this.field_70146_Z, new BlockPos(posX + 32, 22, posZ), Rotation.CLOCKWISE_90);
        new WorldGenStructure(randomHuntersGame()).generateWithRotation(this.field_70170_p, this.field_70146_Z, new BlockPos(posX + 32, 22, posZ + 32), Rotation.CLOCKWISE_180);
        new WorldGenStructure(randomHuntersGame()).generateWithRotation(this.field_70170_p, this.field_70146_Z, new BlockPos(posX, 22, posZ + 32), Rotation.COUNTERCLOCKWISE_90);
    }

    private double startingOffset() {
        return 2.0d * ((-0.5d) + this.field_70146_Z.nextDouble());
    }

    private String randomHuntersGame() {
        int game_pick = this.field_70146_Z.nextInt(7);
        return "contest/contest_hunters_game" + (game_pick + 1);
    }
}
