package xol.lostinfinity.mob.entity.galaxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/galaxy/EntityGalaxyBeast.class */
public class EntityGalaxyBeast extends EntityMob implements IMaxAttack, IBasicAI {
    private static final DataParameter<Byte> COLOR = EntityDataManager.func_187226_a(EntityGalaxyBeast.class, DataSerializers.field_187191_a);

    public EntityGalaxyBeast(World worldIn) {
        super(worldIn);
        func_70105_a(3.5f, 3.0f);
    }

    protected void func_184651_r() {
        initBasicTasks(this);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(COLOR, (byte) 0);
    }

    public byte getColor() {
        return ((Byte) this.field_70180_af.func_187225_a(COLOR)).byteValue();
    }

    public void setColor(byte f) {
        this.field_70180_af.func_187227_b(COLOR, Byte.valueOf(f));
    }

    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74774_a("CreatureType", getColor());
    }

    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setColor(tag.func_74771_c("CreatureType"));
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0d);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 5);
            return true;
        }
        return false;
    }

    public boolean func_70814_o() {
        return true;
    }

    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa == 1 && getColor() == 0) {
            setColor((byte) (this.field_70146_Z.nextInt(4) + 1));
        }
        if (this.field_70173_aa % 20 < 10) {
            this.field_70181_x += 0.1d;
        }
        this.field_70143_R = -1.0f;
        if (this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_175688_a(EnumParticleTypes.SPELL_MOB_AMBIENT, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
        }
        if (func_70638_az() != null) {
            if (func_70638_az().field_70163_u > this.field_70163_u) {
                this.field_70181_x += 0.1d;
                this.field_70133_I = true;
            }
            if (this.field_70159_w > -0.07000000029802322d && this.field_70159_w < 0.07000000029802322d) {
                func_70024_g((func_70638_az().field_70165_t - this.field_70165_t) * 0.1d, 0.0d, 0.0d);
                this.field_70133_I = true;
            }
            if (this.field_70179_y > -0.07000000029802322d && this.field_70179_y < 0.07000000029802322d) {
                func_70024_g(0.0d, 0.0d, (func_70638_az().field_70161_v - this.field_70161_v) * 0.1d);
                this.field_70133_I = true;
            }
        }
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.GALAXYBEAST_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.GALAXYBEAST_HIT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.GALAXYBEAST_AMBIENT;
    }

    protected ResourceLocation func_184647_J() {
        ResourceLocation result = null;
        switch (getColor()) {
            case 1:
                result = LootTableRegistry.ENTITIES_GALAXYBEAST_BLUE;
                break;
            case 2:
                result = LootTableRegistry.ENTITIES_GALAXYBEAST_GREEN;
                break;
            case 3:
                result = LootTableRegistry.ENTITIES_GALAXYBEAST_YELLOW;
                break;
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                result = LootTableRegistry.ENTITIES_GALAXYBEAST_PURPLE;
                break;
        }
        return result;
    }
}
