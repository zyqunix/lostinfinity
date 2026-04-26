package xol.lostinfinity.mob.entity.sea;
import net.minecraft.world.World;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingBase;
public class EntitySeaCreature extends EntityFloatingBase {
    private int visualStyle;
    public EntitySeaCreature(World worldIn) {
        super(worldIn);
        this.visualStyle = 0;
        this.visualStyle = this.field_70146_Z.nextInt(4);
    }
    public int getVisualStyle() {
        return this.visualStyle;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    protected EntityAIFloatAttack createShootAI() {
        return null;
    }
}
