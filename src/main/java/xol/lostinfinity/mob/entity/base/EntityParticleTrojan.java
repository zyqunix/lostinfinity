package xol.lostinfinity.mob.entity.base;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import xol.lostinfinity.block.misc.BlockCthulhuSpawner;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.item.weapon.ItemHeadCollector;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/base/EntityParticleTrojan.class */
public class EntityParticleTrojan extends EntityImmaterial {
    private static final DataParameter<Integer> PARTICLE_FX = EntityDataManager.func_187226_a(EntityParticleTrojan.class, DataSerializers.field_187192_b);
    private static final DataParameter<Integer> EXTRA_PARTICLE_DATA = EntityDataManager.func_187226_a(EntityParticleTrojan.class, DataSerializers.field_187192_b);

    public EntityParticleTrojan(World worldIn) {
        super(worldIn);
        func_70105_a(0.001f, 0.001f);
        func_184224_h(true);
        func_82142_c(true);
        func_189654_d(true);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(PARTICLE_FX, 0);
        this.field_70180_af.func_187214_a(EXTRA_PARTICLE_DATA, 0);
    }

    public int getFX() {
        return ((Integer) this.field_70180_af.func_187225_a(PARTICLE_FX)).intValue();
    }

    public void setFX(int fx) {
        this.field_70180_af.func_187227_b(PARTICLE_FX, Integer.valueOf(fx));
    }

    public int getFXData() {
        return ((Integer) this.field_70180_af.func_187225_a(EXTRA_PARTICLE_DATA)).intValue();
    }

    public void setFX(int fx, int extraData) {
        this.field_70180_af.func_187227_b(PARTICLE_FX, Integer.valueOf(fx));
        this.field_70180_af.func_187227_b(EXTRA_PARTICLE_DATA, Integer.valueOf(extraData));
    }

    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        setFX(compound.func_74762_e("PartFX"), compound.func_74762_e("PartData"));
    }

    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        compound.func_74768_a("PartFX", getFX());
        compound.func_74768_a("PartData", getFXData());
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70173_aa == 1) {
            if (this.field_70170_p.field_72995_K) {
                switch (getFX()) {
                    case 0:
                        for (int i = 0; i < 10; i++) {
                            this.field_70170_p.func_175682_a(ParticleInit.VENOM, true, this.field_70165_t + getROD(4), this.field_70163_u + getROD(1), this.field_70161_v + getROD(4), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        for (int i2 = 0; i2 < 5; i2++) {
                            this.field_70170_p.func_175682_a(ParticleInit.VENOM_RING, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 1:
                        for (int i3 = 0; i3 < 4; i3++) {
                            this.field_70170_p.func_175682_a(ParticleInit.PLASMA_EXPLOSION, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[]{2});
                        }
                        break;
                    case 2:
                        for (int i4 = 0; i4 < 5 + getFXData(); i4++) {
                            this.field_70170_p.func_175682_a(EnumParticleTypes.DRAGON_BREATH, true, this.field_70165_t + getROD(1), this.field_70163_u + 0.75d, this.field_70161_v + getROD(1), getROD(1), Math.abs(getROD(1)), getROD(1), new int[0]);
                        }
                        if (getFXData() != 0) {
                            this.field_70170_p.func_175682_a(EnumParticleTypes.EXPLOSION_HUGE, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 3:
                        for (int i5 = 0; i5 < 10; i5++) {
                            this.field_70170_p.func_175682_a(ParticleInit.QUANTUM_MARK, true, this.field_70165_t + getROD(2), this.field_70163_u + getROD(1), this.field_70161_v + getROD(2), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                        for (int i6 = 0; i6 < 3; i6++) {
                            this.field_70170_p.func_175682_a(ParticleInit.ATTRACT_FIELD, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 5:
                        for (int i7 = 0; i7 < 3; i7++) {
                            this.field_70170_p.func_175682_a(ParticleInit.REPEL_FIELD, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case TileEntityFusionTable.BOARD_COLUMNS /* 6 */:
                        for (int i8 = 0; i8 < 2; i8++) {
                            this.field_70170_p.func_175682_a(ParticleInit.SLAM, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 7:
                        this.field_70170_p.func_175682_a(ParticleInit.WARP, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                        break;
                    case 8:
                        for (int i9 = 0; i9 < 5; i9++) {
                            this.field_70170_p.func_175682_a(ParticleInit.GOO_RING, true, this.field_70165_t + getROD(2), this.field_70163_u + getROD(1), this.field_70161_v + getROD(2), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 9:
                        this.field_70170_p.func_175682_a(ParticleInit.EXPLOSION, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                        break;
                    case ItemHeadCollector.CHARGE_LIMIT /* 10 */:
                        for (int i10 = 0; i10 < 3; i10++) {
                            this.field_70170_p.func_175682_a(ParticleInit.EXPLOSION_RING, true, this.field_70165_t + getROD(2), this.field_70163_u + getROD(1), this.field_70161_v + getROD(2), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 11:
                        for (int i11 = 0; i11 < 5; i11++) {
                            this.field_70170_p.func_175682_a(ParticleInit.ZAP, true, this.field_70165_t + getROD(3), this.field_70163_u + getROD(1), this.field_70161_v + getROD(3), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 12:
                        this.field_70170_p.func_175682_a(ParticleInit.DARK_MAGIC, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                        this.field_70170_p.func_175682_a(ParticleInit.PLAGUE, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                        break;
                    case 13:
                        for (int i12 = 0; i12 < 3; i12++) {
                            this.field_70170_p.func_175682_a(ParticleInit.GLOOM_BURST, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 14:
                        for (int i13 = 0; i13 < 10; i13++) {
                            this.field_70170_p.func_175682_a(ParticleInit.LIGHT_FLASH, true, this.field_70165_t + getROD(4), this.field_70163_u + getROD(1), this.field_70161_v + getROD(4), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 15:
                        for (int i14 = 0; i14 < 10; i14++) {
                            this.field_70170_p.func_175682_a(ParticleInit.DARK_FLASH, true, this.field_70165_t + getROD(4), this.field_70163_u + getROD(1), this.field_70161_v + getROD(4), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 16:
                        for (int i15 = 0; i15 < 1 + getFXData(); i15++) {
                            this.field_70170_p.func_175682_a(ParticleInit.FLAME_SMALL, true, this.field_70165_t + getROD(1), this.field_70163_u, this.field_70161_v + getROD(1), 0.30000001192092896d * getROD(1), 0.0d, 0.30000001192092896d * getROD(1), new int[0]);
                        }
                        break;
                    case 17:
                        for (int i16 = 0; i16 < 1 + (2 * getFXData()); i16++) {
                            this.field_70170_p.func_175682_a(ParticleInit.FLAME_MEDIUM, true, this.field_70165_t + getROD(1), this.field_70163_u, this.field_70161_v + getROD(1), 0.30000001192092896d * getROD(1), 0.0d, 0.30000001192092896d * getROD(1), new int[0]);
                        }
                        break;
                    case 18:
                        for (int i17 = 0; i17 < 1 + (4 * getFXData()); i17++) {
                            this.field_70170_p.func_175682_a(ParticleInit.FLAME_LARGE, true, this.field_70165_t + getROD(1), this.field_70163_u, this.field_70161_v + getROD(1), 0.30000001192092896d * getROD(1), 0.0d, 0.30000001192092896d * getROD(1), new int[0]);
                        }
                        break;
                    case 19:
                        for (int i18 = 0; i18 < 8; i18++) {
                            this.field_70170_p.func_175688_a(ParticleInit.FIREGOO, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), getROD(1), getROD(1), getROD(1), new int[0]);
                        }
                        break;
                    case 20:
                        this.field_70170_p.func_175682_a(ParticleInit.BOMBER_EXPLOSION, true, this.field_70165_t + 0.5d, this.field_70163_u, this.field_70161_v + 0.5d, 0.0d, 0.0d, 0.0d, new int[0]);
                        break;
                    case 21:
                        this.field_70170_p.func_175682_a(ParticleInit.GLOMITE_WARP, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                        break;
                    case 22:
                        for (int i19 = 0; i19 < 8; i19++) {
                            this.field_70170_p.func_175682_a(ParticleInit.SPACE_MAGIC, true, this.field_70165_t + getROD(4), this.field_70163_u + getROD(1), this.field_70161_v + getROD(4), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 23:
                        this.field_70170_p.func_175682_a(ParticleInit.POWER_FIELD, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                        break;
                    case TileEntityFusionTable.BOARD_SIZE /* 24 */:
                        this.field_70170_p.func_175682_a(ParticleInit.POWER_LOSS, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                        break;
                    case 25:
                        for (int i20 = 0; i20 < 8; i20++) {
                            this.field_70170_p.func_175682_a(ParticleInit.PLAGUE, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 26:
                        for (int i21 = 0; i21 < 14; i21++) {
                            this.field_70170_p.func_175682_a(ParticleInit.ANCIENT_SPELL, true, this.field_70165_t + getROD(1), this.field_70163_u, this.field_70161_v + getROD(1), 0.30000001192092896d * getROD(1), 0.0d, 0.30000001192092896d * getROD(1), new int[0]);
                        }
                        break;
                    case 27:
                        for (int i22 = 0; i22 < 8; i22++) {
                            this.field_70170_p.func_175682_a(ParticleInit.CLAW_MARKS, true, this.field_70165_t + getROD(4), this.field_70163_u + getROD(1), this.field_70161_v + getROD(4), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 28:
                        for (int i23 = 0; i23 < 3; i23++) {
                            this.field_70170_p.func_175682_a(this.field_70146_Z.nextBoolean() ? ParticleInit.ELECTRIC_EXPLOSION_BLUE : ParticleInit.ELECTRIC_EXPLOSION_YELLOW, true, this.field_70165_t + getROD(2), this.field_70163_u + getROD(1), this.field_70161_v + getROD(2), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 29:
                        for (int i24 = 0; i24 < 10; i24++) {
                            this.field_70170_p.func_175682_a(ParticleInit.CORRUPTION_MAGIC, true, this.field_70165_t + getROD(4), this.field_70163_u + getROD(1), this.field_70161_v + getROD(4), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case BlockCthulhuSpawner.BLOCK_DISTANCE /* 30 */:
                        for (int i25 = 0; i25 < 3; i25++) {
                            this.field_70170_p.func_175682_a(ParticleInit.NATURE_RING, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 31:
                        for (int i26 = 0; i26 < 10; i26++) {
                            this.field_70170_p.func_175682_a(ParticleInit.NATURE_MAGIC, true, this.field_70165_t + getROD(4), this.field_70163_u + getROD(1), this.field_70161_v + getROD(4), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 32:
                        for (int i27 = 0; i27 < 10; i27++) {
                            this.field_70170_p.func_175682_a(ParticleInit.CRYSTAL_MAGIC, true, this.field_70165_t + getROD(4), this.field_70163_u + getROD(1), this.field_70161_v + getROD(4), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 33:
                        for (int i28 = 0; i28 < 2; i28++) {
                            this.field_70170_p.func_175682_a(ParticleInit.VENOM, true, this.field_70165_t + getROD(4), this.field_70163_u + getROD(1), this.field_70161_v + getROD(4), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        this.field_70170_p.func_175682_a(ParticleInit.VENOM_RING, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
                        break;
                    case 34:
                        this.field_70170_p.func_175682_a(ParticleInit.SHADOW_BLAST, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
                        break;
                    case 35:
                        for (int i29 = 0; i29 < 10; i29++) {
                            this.field_70170_p.func_175682_a(ParticleInit.POISON_BUBBLE, true, this.field_70165_t + getROD(4), this.field_70163_u + getROD(1), this.field_70161_v + getROD(4), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 36:
                        for (int i30 = 0; i30 < 12; i30++) {
                            this.field_70170_p.func_175682_a(ParticleInit.PURPLE_SKULL, true, this.field_70165_t + getROD(4), this.field_70163_u + getROD(1), this.field_70161_v + getROD(4), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 37:
                        for (int i31 = 0; i31 < 4; i31++) {
                            this.field_70170_p.func_175682_a(ParticleInit.BLIGHT_SPELL_GREEN, true, this.field_70165_t + getROD(4), this.field_70163_u + getROD(1), this.field_70161_v + getROD(4), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 38:
                        for (int i32 = 0; i32 < 5; i32++) {
                            this.field_70170_p.func_175682_a(ParticleInit.DARK_MAGIC, true, this.field_70165_t + getROD(4), this.field_70163_u + getROD(1), this.field_70161_v + getROD(4), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 39:
                        this.field_70170_p.func_175682_a(ParticleInit.BLUE_SKULL, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
                        break;
                    case 40:
                        for (int i33 = 0; i33 < 5; i33++) {
                            this.field_70170_p.func_175682_a(ParticleInit.ION_BLAST, true, this.field_70165_t + getROD(6), this.field_70163_u + getROD(4), this.field_70161_v + getROD(6), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 41:
                        for (int i34 = 0; i34 < 5; i34++) {
                            this.field_70170_p.func_175682_a(ParticleInit.NUCLEAR_BLAST, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 42:
                        for (int i35 = 0; i35 < 10; i35++) {
                            this.field_70170_p.func_175682_a(ParticleInit.EXPLOSION, true, this.field_70165_t + getROD(8), this.field_70163_u + getROD(2), this.field_70161_v + getROD(8), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 43:
                        for (int i36 = 0; i36 < 8; i36++) {
                            this.field_70170_p.func_175682_a(ParticleInit.PLASMA_RIFT, true, this.field_70165_t + getROD(3), this.field_70163_u + getROD(1), this.field_70161_v + getROD(3), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 44:
                        for (int i37 = 0; i37 < 20; i37++) {
                            this.field_70170_p.func_175682_a(ParticleInit.MURK, true, this.field_70165_t + getROD(5), this.field_70163_u + getROD(2), this.field_70161_v + getROD(5), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 45:
                        this.field_70170_p.func_175682_a(ParticleInit.SPECTRAL, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
                        break;
                    case 46:
                        for (int i38 = 0; i38 < 5; i38++) {
                            switch (this.field_70146_Z.nextInt(3)) {
                                case 0:
                                    this.field_70170_p.func_175682_a(ParticleInit.PRISMATIC_EXPLOSION_TYPE1, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
                                    break;
                                case 1:
                                    this.field_70170_p.func_175682_a(ParticleInit.PRISMATIC_EXPLOSION_TYPE2, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
                                    break;
                                case 2:
                                    this.field_70170_p.func_175682_a(ParticleInit.PRISMATIC_EXPLOSION_TYPE3, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
                                    break;
                            }
                        }
                        break;
                    case 47:
                        for (int i39 = 0; i39 < 5; i39++) {
                            this.field_70170_p.func_175682_a(ParticleInit.PRISMATIC_EXPLOSION_TYPE1, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 48:
                        for (int i40 = 0; i40 < 5; i40++) {
                            this.field_70170_p.func_175682_a(ParticleInit.PRISMATIC_EXPLOSION_TYPE2, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 49:
                        for (int i41 = 0; i41 < 5; i41++) {
                            this.field_70170_p.func_175682_a(ParticleInit.PRISMATIC_EXPLOSION_TYPE3, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 50:
                        for (int i42 = 0; i42 < 5; i42++) {
                            this.field_70170_p.func_175682_a(ParticleInit.EXPLOSION_RING_DARK, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 51:
                        for (int i43 = 0; i43 < 3; i43++) {
                            if (this.field_70146_Z.nextBoolean()) {
                                this.field_70170_p.func_175682_a(ParticleInit.EXPLOSION_BLUE, true, this.field_70165_t + getROD(5), this.field_70163_u + getROD(1), this.field_70161_v + getROD(5), 0.0d, 0.0d, 0.0d, new int[0]);
                            } else {
                                this.field_70170_p.func_175682_a(ParticleInit.EXPLOSION_TEAL, true, this.field_70165_t + getROD(5), this.field_70163_u + getROD(1), this.field_70161_v + getROD(5), 0.0d, 0.0d, 0.0d, new int[0]);
                            }
                        }
                        for (int i44 = 0; i44 < 7; i44++) {
                            this.field_70170_p.func_175682_a(ParticleInit.MURK, true, this.field_70165_t + getROD(10), this.field_70163_u + getROD(2), this.field_70161_v + getROD(10), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 52:
                        for (int i45 = 0; i45 < 2; i45++) {
                            this.field_70170_p.func_175682_a(ParticleInit.CLAW_MARKS, true, this.field_70165_t + getROD(2), this.field_70163_u + getROD(1), this.field_70161_v + getROD(2), 0.0d, 0.0d, 0.0d, new int[0]);
                            this.field_70170_p.func_175682_a(ParticleInit.BLOOD_DROP, true, this.field_70165_t + getROD(2), this.field_70163_u + getROD(1), this.field_70161_v + getROD(2), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 53:
                        for (int i46 = 0; i46 < 10; i46++) {
                            this.field_70170_p.func_175682_a(ParticleInit.ION_BLAST, true, this.field_70165_t + getROD(6), this.field_70163_u + getROD(4), this.field_70161_v + getROD(6), 0.0d, 0.0d, 0.0d, new int[0]);
                            this.field_70170_p.func_175682_a(ParticleInit.EXPLOSION_LAVENDER, true, this.field_70165_t + getROD(5), this.field_70163_u + getROD(1), this.field_70161_v + getROD(5), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 54:
                        for (int i47 = 0; i47 < 15; i47++) {
                            this.field_70170_p.func_175682_a(ParticleInit.POISON_EXPLOSION, true, this.field_70165_t + getROD(15), this.field_70163_u + getROD(2), this.field_70161_v + getROD(15), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                    case 55:
                        switch (this.field_70146_Z.nextInt(4)) {
                            case 0:
                                this.field_70170_p.func_175682_a(ParticleInit.COSMIC_EXPLOSION_TYPE1, true, this.field_70165_t + getROD(5), this.field_70163_u + getROD(1), this.field_70161_v + getROD(5), 0.0d, 0.0d, 0.0d, new int[0]);
                                break;
                            case 1:
                                this.field_70170_p.func_175682_a(ParticleInit.COSMIC_EXPLOSION_TYPE2, true, this.field_70165_t + getROD(5), this.field_70163_u + getROD(1), this.field_70161_v + getROD(5), 0.0d, 0.0d, 0.0d, new int[0]);
                                break;
                            case 2:
                                this.field_70170_p.func_175682_a(ParticleInit.COSMIC_EXPLOSION_TYPE3, true, this.field_70165_t + getROD(5), this.field_70163_u + getROD(1), this.field_70161_v + getROD(5), 0.0d, 0.0d, 0.0d, new int[0]);
                                break;
                            case 3:
                                this.field_70170_p.func_175682_a(ParticleInit.COSMIC_EXPLOSION_TYPE4, true, this.field_70165_t + getROD(5), this.field_70163_u + getROD(1), this.field_70161_v + getROD(5), 0.0d, 0.0d, 0.0d, new int[0]);
                                break;
                        }
                        for (int i48 = 0; i48 < 4; i48++) {
                            switch (this.field_70146_Z.nextInt(4)) {
                                case 0:
                                    this.field_70170_p.func_175682_a(ParticleInit.BASIC_STAR_TYPE1, true, this.field_70165_t + getROD(5), this.field_70163_u + getROD(3), this.field_70161_v + getROD(5), 0.0d, 0.0d, 0.0d, new int[0]);
                                    break;
                                case 1:
                                    this.field_70170_p.func_175682_a(ParticleInit.BASIC_STAR_TYPE2, true, this.field_70165_t + getROD(5), this.field_70163_u + getROD(3), this.field_70161_v + getROD(5), 0.0d, 0.0d, 0.0d, new int[0]);
                                    break;
                                case 2:
                                    this.field_70170_p.func_175682_a(ParticleInit.BASIC_STAR_TYPE3, true, this.field_70165_t + getROD(5), this.field_70163_u + getROD(3), this.field_70161_v + getROD(5), 0.0d, 0.0d, 0.0d, new int[0]);
                                    break;
                                case 3:
                                    this.field_70170_p.func_175682_a(ParticleInit.BASIC_STAR_TYPE4, true, this.field_70165_t + getROD(5), this.field_70163_u + getROD(3), this.field_70161_v + getROD(5), 0.0d, 0.0d, 0.0d, new int[0]);
                                    break;
                            }
                        }
                        break;
                    case 56:
                        for (int i49 = 0; i49 < 4; i49++) {
                            this.field_70170_p.func_175682_a(ParticleInit.VENOM_RING, true, this.field_70165_t + getROD(15), this.field_70163_u + getROD(2), this.field_70161_v + getROD(15), 0.0d, 0.0d, 0.0d, new int[0]);
                            this.field_70170_p.func_175682_a(ParticleInit.EXPLOSION_RING_DARK, true, this.field_70165_t + getROD(15), this.field_70163_u + getROD(2), this.field_70161_v + getROD(15), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                    case 57:
                        for (int i50 = 0; i50 < 5; i50++) {
                            this.field_70170_p.func_175682_a(ParticleInit.MIASMA, true, this.field_70165_t + getROD(4), this.field_70163_u + getROD(1), this.field_70161_v + getROD(4), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                    case 58:
                        if (this.field_70146_Z.nextBoolean()) {
                            for (int i51 = 0; i51 < 2; i51++) {
                                this.field_70170_p.func_175682_a(ParticleInit.SUPERSONIC_BLUE, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                            }
                        } else {
                            for (int i52 = 0; i52 < 2; i52++) {
                                this.field_70170_p.func_175682_a(ParticleInit.SUPERSONIC_RED, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                            }
                        }
                        break;
                    case 59:
                        for (int i53 = 0; i53 < 15; i53++) {
                            if (this.field_70146_Z.nextBoolean()) {
                                this.field_70170_p.func_175682_a(ParticleInit.EXPLOSION_RED, true, this.field_70165_t + getROD(25), this.field_70163_u + getROD(4), this.field_70161_v + getROD(25), 0.0d, 0.0d, 0.0d, new int[0]);
                            } else {
                                this.field_70170_p.func_175682_a(ParticleInit.EXPLOSION_ORANGE, true, this.field_70165_t + getROD(25), this.field_70163_u + getROD(4), this.field_70161_v + getROD(25), 0.0d, 0.0d, 0.0d, new int[0]);
                            }
                        }
                        break;
                    case 60:
                        this.field_70170_p.func_175682_a(ParticleInit.POWER_FIELD, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
                        for (int i54 = 0; i54 < 12; i54++) {
                            this.field_70170_p.func_175682_a(this.field_70146_Z.nextBoolean() ? ParticleInit.EXPLOSION_BLUE : ParticleInit.EXPLOSION_LAVENDER, true, this.field_70165_t + getROD(18), this.field_70163_u + getROD(2), this.field_70161_v + getROD(18), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                        break;
                }
            }
            func_70106_y();
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public boolean func_70067_L() {
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public boolean func_70104_M() {
        return false;
    }

    private double getROD(int multi) {
        return ((-0.5d) + this.field_70146_Z.nextDouble()) * ((double) multi);
    }
}
