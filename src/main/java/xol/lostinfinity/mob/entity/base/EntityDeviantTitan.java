package xol.lostinfinity.mob.entity.base;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityDeviantTitan extends EntityMultipleLives implements IMaxAttack {
    public EntityDeviantTitan(World worldIn) {
        super(worldIn);
    }
    protected AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(-493.0d, 60.0d, 407.0d), new BlockPos(548.0d, 85.0d, 460.0d));
    }
    protected boolean func_70692_ba() {
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 25;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K) {
            func_145779_a(ItemInit.arenaCard, 1);
        }
    }
}
