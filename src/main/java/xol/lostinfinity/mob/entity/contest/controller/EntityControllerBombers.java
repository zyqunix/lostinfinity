package xol.lostinfinity.mob.entity.contest.controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.activate.ItemBombDeployer;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
public class EntityControllerBombers extends EntityControllerBase {
    private int stageTimer;
    private List<BlockPos> powerupLocations;
    public EntityControllerBombers(World worldIn) {
        super(worldIn);
        this.stageTimer = 400;
        this.powerupLocations = new ArrayList();
        func_70105_a(3.0f, 6.0f);
    }
    public void addPowerupLocation(BlockPos pos) {
        this.powerupLocations.add(pos);
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase, xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.field_70170_p.field_73011_w.func_186058_p() == DimensionInit.grandmasterOutpost) {
            if (this.stageTimer == 0) {
                createPowerups();
                this.stageTimer = 400;
                this.stage++;
                return;
            }
            this.stageTimer--;
        }
    }
    private void createPowerups() {
        int max = this.powerupLocations.size();
        if (max >= 6) {
            Collections.shuffle(this.powerupLocations);
            for (int i = 0; i < 6; i++) {
                this.field_70170_p.func_175656_a(this.powerupLocations.get(i), randomPowerupBlock().func_176223_P());
            }
            messageContendersWithSound(this.stage % 2 == 0 ? TextFmt.Gold : TextFmt.Green, randomPowerupMessage(), SoundInit.MINIGAME_ANNOUNCEMENT);
        }
    }
    private Block randomPowerupBlock() {
        switch (this.field_70146_Z.nextInt(4)) {
            case 0:
                return BlockInit.bombersPowerupSpeed;
            case 1:
                return BlockInit.bombersPowerupSize;
            case 2:
                return BlockInit.bombersPowerupCooldown;
            case 3:
                return BlockInit.bombersPowerupShield;
            default:
                return BlockInit.bombersPowerupSize;
        }
    }
    private String randomPowerupMessage() {
        switch (this.field_70146_Z.nextInt(8)) {
            case 0:
                return "Powerups have been opened in the arena! Claim them before your foes do.";
            case 1:
                return "Contenders, I've enabled the powerups. Grab them quickly.";
            case 2:
                return "Somebody blow up already! I've enabled powerups to help you do that.";
            case 3:
                return "Why does it take so long to see somebody explode? Here I'll lend you a hand... powerups are now open.";
            case TileEntityFusionTable.BOARD_ROWS :
                return "Quick contenders, grab the powerups before you get eliminated!";
            case 5:
                return "I've opened powerups. Hopefully one of you explodes trying to grab one.";
            case TileEntityFusionTable.BOARD_COLUMNS :
                return "Powerups are open, grab them quickly contenders.";
            case 7:
                return "I've opened powerups so that one of you can get eliminated already.";
            default:
                return "";
        }
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.bombersArenaAABB();
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected BlockPos getSnapPos() {
        return ContestCoordinates.bombersControllerPos();
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void rewardPlayer(EntityPlayer player, int placement) {
        BlockPos teleTo = ContestCoordinates.bombersLobbyPos();
        player.func_70634_a(teleTo.func_177958_n(), teleTo.func_177956_o(), teleTo.func_177952_p());
        int reward_count = Math.min(1 + (4 * placement) + (placement == this.contenderCount - 1 ? this.contenderCount * 2 : 0), 50);
        removeBombBag(player);
        player.func_191521_c(new ItemStack(ItemInit.zirconiaCitrine, reward_count));
    }
    private void removeBombBag(EntityPlayer player) {
        for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
            if (player.field_71071_by.func_70301_a(i).func_77973_b() instanceof ItemBombDeployer) {
                player.field_71071_by.func_70299_a(i, ItemStack.field_190927_a);
            }
        }
    }
}
