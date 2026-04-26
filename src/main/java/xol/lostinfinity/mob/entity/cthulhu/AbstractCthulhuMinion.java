package xol.lostinfinity.mob.entity.cthulhu;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
public abstract class AbstractCthulhuMinion extends EntityMultipleLives implements ICthulhuMinion {
    protected EntityCthulhu owner;
    public AbstractCthulhuMinion(World worldIn) {
        super(worldIn);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }
    @Override // xol.lostinfinity.mob.entity.cthulhu.ICthulhuMinion
    public void setOwner(EntityCthulhu owner) {
        this.owner = owner;
    }
    @Override // xol.lostinfinity.mob.entity.cthulhu.ICthulhuMinion
    public EntityCthulhu getOwner() {
        return this.owner;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        write(tag);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        read(tag, this);
    }
    protected boolean func_70692_ba() {
        return false;
    }
}
