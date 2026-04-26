package xol.lostinfinity.mob.entity.cthulhu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityCthulhuHealingOrb extends AbstractCthulhuMinion {
    private final int healDelayOffset;
    private int orbitAngle;
    public EntityCthulhuHealingOrb(World worldIn) {
        this(worldIn, null);
    }
    public EntityCthulhuHealingOrb(World worldIn, EntityCthulhu owner) {
        super(worldIn);
        this.owner = owner;
        this.healDelayOffset = this.field_70146_Z.nextInt(100) + 1;
        func_189654_d(true);
        func_94061_f(true);
        this.orbitAngle = this.field_70146_Z.nextInt(360) + 1;
        func_70105_a(3.0f, 3.0f);
    }
    @Override // xol.lostinfinity.mob.entity.cthulhu.AbstractCthulhuMinion, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 3;
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (this.owner == null) {
            return;
        }
        updatePos();
        if (this.field_70173_aa >= 400 + this.healDelayOffset) {
            if (healOwner()) {
                func_70106_y();
            } else {
                this.field_70173_aa = 0;
            }
        }
    }
    private boolean healOwner() {
        int phase = this.owner.getPhase();
        float maxHealth = this.owner.func_110138_aP();
        float currentHealth = this.owner.func_110143_aJ();
        this.owner.func_70691_i(maxHealth - currentHealth);
        int healRange = this.owner.numberOfLives() / 50;
        switch (phase) {
            case 1:
                if (this.owner.remainingLives() < this.owner.numberOfLives()) {
                    int livesToHeal = Math.min(healRange, this.owner.numberOfLives() - this.owner.remainingLives());
                    this.owner.setLivesCount(this.owner.getLivesCount() - livesToHeal);
                } else {
                    return false;
                }
                break;
            case 2:
                if (this.owner.remainingLives() / this.owner.numberOfLives() < 0.6666d) {
                    int livesToHeal2 = (int) Math.min(healRange, (((double) this.owner.numberOfLives()) * 0.6666d) - ((double) this.owner.remainingLives()));
                    this.owner.setLivesCount(this.owner.getLivesCount() - livesToHeal2);
                } else {
                    return false;
                }
                break;
            case 3:
                if (this.owner.remainingLives() / this.owner.numberOfLives() < 0.3333d) {
                    int livesToHeal3 = (int) Math.min(healRange, (((double) this.owner.numberOfLives()) * 0.3333d) - ((double) this.owner.remainingLives()));
                    this.owner.setLivesCount(this.owner.getLivesCount() - livesToHeal3);
                } else {
                    return false;
                }
                break;
        }
        CustomParticleConfig config = new CustomParticleConfig();
        config.createInstance().setParticle(EnumParticleTypes.HEART).setCount(5).setSpread(0.5d, 0.5d, 0.5d).setIgnoreRange(true);
        for (int i = 0; i < 20; i++) {
            double x = this.field_70165_t + ((this.owner.field_70165_t - this.field_70165_t) * (((double) i) / 20.0d));
            double y = this.field_70163_u + (((this.owner.field_70163_u + ((double) (this.owner.func_70047_e() / 2.0f))) - this.field_70163_u) * (((double) i) / 20.0d));
            double z = this.field_70161_v + ((this.owner.field_70161_v - this.field_70161_v) * (((double) i) / 20.0d));
            IParticleSpawner.spawnParticle(this.field_70170_p, config, x, y, z);
        }
        this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.LIFEVESSEL_HEAL, SoundCategory.PLAYERS, 1.0f, 1.0f);
        return true;
    }
    public void updatePos() {
        double x = this.owner.field_70165_t + ((double) (MathHelper.func_76134_b(this.orbitAngle * 0.017453292f) * 36.0f));
        double y = this.owner.field_70163_u + ((double) this.owner.func_70047_e());
        double z = this.owner.field_70161_v + ((double) (MathHelper.func_76126_a(this.orbitAngle * 0.017453292f) * 36.0f));
        func_70634_a(x, y, z);
        this.field_70759_as = 0.0f;
        this.field_70761_aq = 0.0f;
        this.orbitAngle = (this.orbitAngle + 1) % 360;
    }
    public int getOrbitAngle() {
        return this.orbitAngle;
    }
    @Override // xol.lostinfinity.mob.entity.cthulhu.AbstractCthulhuMinion, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74768_a("orbitAngle", this.orbitAngle);
        write(tag);
    }
    @Override // xol.lostinfinity.mob.entity.cthulhu.AbstractCthulhuMinion, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        this.orbitAngle = tag.func_74762_e("orbitAngle");
        read(tag, this);
    }
}
