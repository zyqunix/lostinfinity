package xol.lostinfinity.mob.entity.cthulhu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.mob.entity.misc.EntityBaseRift;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityCthulhuRift extends EntityBaseRift implements IMaxAttack, ICthulhuMinion {
    protected EntityCthulhu owner;
    public EntityCthulhuRift(World worldIn) {
        super(worldIn);
    }
    @Override // xol.lostinfinity.mob.entity.misc.EntityBaseRift, xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70159_w = 0.0d;
        this.field_70181_x = 0.0d;
        this.field_70179_y = 0.0d;
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa % 10 == 0) {
                for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_186662_g(20.0d))) {
                    if (!near_pl.func_184812_l_()) {
                        near_pl.func_70690_d(new PotionEffect(PotionInit.DIMENSIONAL_TEAR, 40, 2));
                    }
                }
                return;
            }
            return;
        }
        if (this.field_70146_Z.nextInt(2) == 0) {
            this.field_70170_p.func_175682_a(this.field_70146_Z.nextBoolean() ? ParticleInit.PLASMA : ParticleInit.GLOOM_SPELL, true, this.field_70165_t + getROD(8), this.field_70163_u + 2.0d, this.field_70161_v + getROD(8), 0.0d, 0.0d, 0.0d, new int[0]);
        }
    }
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        write(tag);
    }
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        read(tag, this);
    }
    @Override // xol.lostinfinity.mob.entity.cthulhu.ICthulhuMinion
    public void setOwner(EntityCthulhu owner) {
        this.owner = owner;
    }
    @Override // xol.lostinfinity.mob.entity.cthulhu.ICthulhuMinion
    public EntityCthulhu getOwner() {
        return this.owner;
    }
}
