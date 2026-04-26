package xol.lostinfinity.mob.entity.cthulhu;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityCthulhuCloud extends AbstractCthulhuMinion {
    public EntityCthulhuCloud(World worldIn) {
        super(worldIn);
        func_189654_d(true);
        func_184224_h(true);
    }
    @Override // xol.lostinfinity.mob.entity.cthulhu.AbstractCthulhuMinion, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        func_70015_d(0);
        if (this.field_70173_aa % 4 == 1) {
            EnumParticleTypes type = ParticleInit.CLOUD_GREEN;
            if (this.field_70146_Z.nextBoolean()) {
                type = ParticleInit.CLOUD_PURPLE;
            }
            CustomParticleConfig config = new CustomParticleConfig();
            config.createInstance().setSpread(3.5d, 1.25d, 3.5d).setParticle(type).setCount(50).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config, this.field_70165_t, this.field_70163_u, this.field_70161_v);
        }
        if (this.field_70173_aa % 20 == 1) {
            for (EntityCthulhu entityCthulhu : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(3.0d))) {
                if (entityCthulhu != this.owner && !(entityCthulhu instanceof ICthulhuMinion)) {
                    IMaxAttack.dealTrueDamage(this, entityCthulhu, entityCthulhu.func_110138_aP() * 0.25f);
                    entityCthulhu.func_70690_d(new PotionEffect(PotionInit.PLAGUE, 100, 3));
                    entityCthulhu.func_70690_d(new PotionEffect(PotionInit.CONTAGIOUS, 100, 3));
                    this.field_70170_p.func_184133_a((EntityPlayer) null, entityCthulhu.func_180425_c(), randomWhisper(this.field_70146_Z.nextInt(5)), SoundCategory.PLAYERS, 1.5f, 0.6f + (0.4f * this.field_70146_Z.nextFloat()));
                }
            }
        }
        if (func_70638_az() != null && func_70638_az().field_70128_L) {
            func_70624_b(null);
        }
        if (func_70638_az() != null) {
            EntityLivingBase target = func_70638_az();
            this.field_70159_w = (target.field_70165_t - this.field_70165_t) / 25.0d;
            this.field_70181_x = (target.field_70163_u - this.field_70163_u) / 25.0d;
            this.field_70179_y = (target.field_70161_v - this.field_70161_v) / 25.0d;
            this.field_70133_I = true;
        } else if (this.field_70173_aa % 40 == 1 && this.field_70146_Z.nextBoolean()) {
            this.field_70159_w = (this.field_70146_Z.nextFloat() - 0.5f) * 0.75f;
            this.field_70181_x = (this.field_70146_Z.nextFloat() - 0.5f) * 0.75f;
            this.field_70179_y = (this.field_70146_Z.nextFloat() - 0.5f) * 0.75f;
            this.field_70133_I = true;
        }
        if (this.field_70173_aa == 300) {
            func_70106_y();
        }
    }
    private SoundEvent randomWhisper(int i) {
        switch (i) {
            case 0:
                return SoundInit.WHISPER_1;
            case 1:
                return SoundInit.WHISPER_2;
            case 2:
                return SoundInit.WHISPER_3;
            case 3:
                return SoundInit.WHISPER_4;
            case TileEntityFusionTable.BOARD_ROWS :
                return SoundInit.WHISPER_5;
            default:
                return SoundInit.WHISPER_5;
        }
    }
}
