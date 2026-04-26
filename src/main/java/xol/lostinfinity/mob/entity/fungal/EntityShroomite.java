package xol.lostinfinity.mob.entity.fungal;

import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/fungal/EntityShroomite.class */
public class EntityShroomite extends EntityMultipleLives {
    private static final DataParameter<Boolean> BURROWED = EntityDataManager.func_187226_a(EntityShroomite.class, DataSerializers.field_187198_h);

    public EntityShroomite(World worldIn) {
        super(worldIn);
        func_70105_a(0.8f, 1.5f);
    }

    @Nullable
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        func_189654_d(true);
        int i = 0;
        while (true) {
            if (i >= 256) {
                break;
            }
            if (!this.field_70170_p.func_180495_p(func_180425_c().func_177979_c(i)).func_185913_b()) {
                i++;
            } else {
                func_70107_b(func_180425_c().func_177958_n(), ((double) (func_180425_c().func_177956_o() - i)) + 0.25d, func_180425_c().func_177952_p());
                break;
            }
        }
        return super.func_180482_a(difficulty, livingdata);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(BURROWED, true);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 8;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        super.func_184651_r();
        this.field_70714_bg.func_75776_a(1, new EntityAIShroomiteBurrow(this));
        this.field_70714_bg.func_75776_a(2, new EntityAIShroomiteUnburrow(this));
        this.field_70714_bg.func_75776_a(3, new EntityAIAttackMelee(this, 1.0d, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3300000041723251d);
    }

    public boolean isBurrowed() {
        return ((Boolean) this.field_70180_af.func_187225_a(BURROWED)).booleanValue();
    }

    public void setBurrowed(boolean burrowed) {
        this.field_70180_af.func_187227_b(BURROWED, Boolean.valueOf(burrowed));
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/fungal/EntityShroomite$EntityAIShroomiteBurrow.class */
    private static class EntityAIShroomiteBurrow extends EntityAIBase {
        private final EntityShroomite shroomite;

        public EntityAIShroomiteBurrow(EntityShroomite shroomite) {
            this.shroomite = shroomite;
        }

        public boolean func_75250_a() {
            if ((this.shroomite.func_70638_az() == null || this.shroomite.func_70638_az().field_70128_L || this.shroomite.func_70638_az().func_70032_d(this.shroomite) > 30.0f) && !this.shroomite.isBurrowed()) {
                return true;
            }
            return false;
        }

        public boolean func_75253_b() {
            return !this.shroomite.isBurrowed();
        }

        public void func_75246_d() {
            super.func_75246_d();
            if (this.shroomite.field_70181_x == -0.0784000015258789d) {
                this.shroomite.func_189654_d(true);
                this.shroomite.func_70634_a(this.shroomite.field_70165_t, this.shroomite.field_70163_u - 0.25d, this.shroomite.field_70161_v);
                this.shroomite.setBurrowed(true);
            }
        }
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/fungal/EntityShroomite$EntityAIShroomiteUnburrow.class */
    private static class EntityAIShroomiteUnburrow extends EntityAIBase {
        private final EntityShroomite shroomite;

        public EntityAIShroomiteUnburrow(EntityShroomite shroomite) {
            this.shroomite = shroomite;
        }

        public boolean func_75250_a() {
            if (this.shroomite.func_70638_az() != null && this.shroomite.func_70638_az().func_70032_d(this.shroomite) < 12.0f) {
                return true;
            }
            return false;
        }

        public boolean func_75253_b() {
            return this.shroomite.isBurrowed();
        }

        public void func_75246_d() {
            super.func_75246_d();
            if (this.shroomite.func_189652_ae()) {
                this.shroomite.func_189654_d(false);
                this.shroomite.field_70181_x += 0.65d;
                this.shroomite.setBurrowed(false);
            }
        }
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealTrueDamage(this, func_70638_az(), func_70638_az().func_110138_aP() * 0.5f);
            return true;
        }
        return false;
    }

    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_SHROOMITE;
    }
}
