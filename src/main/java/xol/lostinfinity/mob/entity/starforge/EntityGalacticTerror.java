package xol.lostinfinity.mob.entity.starforge;

import java.util.Iterator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.misc.BlockNeoshocker;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingBase;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityGalacticTerror.class */
public class EntityGalacticTerror extends EntityFloatingBase implements IMaxAttack {
    private static final DataParameter<Boolean> STEALTH = EntityDataManager.func_187226_a(EntityGalacticTerror.class, DataSerializers.field_187198_h);
    private int floorStyle;
    private float renderAlpha;

    public EntityGalacticTerror(World worldIn) {
        super(worldIn);
        this.floorStyle = 0;
        this.renderAlpha = 1.0f;
        func_70105_a(2.5f, 2.25f);
        this.rawFlySpeed = 0.97f;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(STEALTH, false);
    }

    public boolean isInStealth() {
        return ((Boolean) this.field_70180_af.func_187225_a(STEALTH)).booleanValue();
    }

    public void setStealtActive(boolean s) {
        this.field_70180_af.func_187227_b(STEALTH, Boolean.valueOf(s));
    }

    public float getRenderAlpha() {
        return this.renderAlpha;
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 2);
            return true;
        }
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa % 180 == 0) {
                floorIncrement();
            }
            if (this.field_70173_aa % 10 == 0) {
                for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
                    if (!near_pl.func_184812_l_() && near_pl.field_71075_bZ.field_75100_b) {
                        IMaxAttack.dealTrueDamage(this, near_pl, near_pl.func_110138_aP() * 0.3f);
                    }
                }
            }
            if (this.field_70173_aa % 200 == 0 && getLivesCount() >= numberOfLives() / 2) {
                if (isInStealth()) {
                    setStealtActive(false);
                } else {
                    setStealtActive(true);
                    soundPlayers(SoundInit.GALACTIC_TERROR_SCREACH, 1.0f);
                    messagePlayers(TextFmt.Red + "SCCCREEEAAACCCCHHHH!");
                }
            }
            if (func_70638_az() != null) {
                EntityLivingBase target = func_70638_az();
                func_70605_aq().func_75642_a(target.field_70165_t, target.field_70163_u, target.field_70161_v, 1.0d);
                if (this.field_70173_aa % 40 == 0 && func_70638_az().field_70163_u < this.field_70163_u) {
                    this.field_70181_x = -0.5d;
                    this.field_70133_I = true;
                    return;
                }
                return;
            }
            if (this.field_70173_aa % 40 == 0) {
                Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB()).iterator();
                if (it.hasNext()) {
                    func_70624_b((EntityPlayer) it.next());
                    return;
                }
                return;
            }
            return;
        }
        if (isInStealth()) {
            if (this.renderAlpha > 0.0f) {
                this.renderAlpha -= 0.05f;
            }
        } else if (this.renderAlpha < 1.0f) {
            this.renderAlpha += 0.05f;
        }
    }

    private void floorIncrement() {
        this.floorStyle++;
        if (this.floorStyle == 3) {
            this.floorStyle = 0;
        }
        AxisAlignedBB aabb = getArenaAABB();
        double blockY = aabb.field_72338_b;
        switch (this.floorStyle) {
            case 0:
                soundPlayers(SoundInit.GENERIC_POP, 1.0f);
                double d = aabb.field_72340_a;
                while (true) {
                    double blockX = d;
                    if (blockX < aabb.field_72336_d) {
                        double d2 = aabb.field_72339_c;
                        while (true) {
                            double blockZ = d2;
                            if (blockZ < aabb.field_72334_f) {
                                BlockPos checkPos = new BlockPos(blockX, blockY, blockZ);
                                IBlockState floorState = this.field_70170_p.func_180495_p(checkPos);
                                if (floorState.func_177230_c() == BlockInit.neoshocker) {
                                    this.field_70170_p.func_175656_a(checkPos, ((BlockNeoshocker) floorState.func_177230_c()).func_176203_a(0));
                                }
                                d2 = blockZ + 1.0d;
                            }
                        }
                        d = blockX + 1.0d;
                    }
                    break;
                }
                break;
            case 1:
                soundPlayers(SoundInit.GENERIC_POP, 1.0f);
                double d3 = aabb.field_72340_a;
                while (true) {
                    double blockX2 = d3;
                    if (blockX2 < aabb.field_72336_d) {
                        double d4 = aabb.field_72339_c;
                        while (true) {
                            double blockZ2 = d4;
                            if (blockZ2 < aabb.field_72334_f) {
                                BlockPos checkPos2 = new BlockPos(blockX2, blockY, blockZ2);
                                IBlockState floorState2 = this.field_70170_p.func_180495_p(checkPos2);
                                if (floorState2.func_177230_c() == BlockInit.neoshocker && this.field_70146_Z.nextInt(5) == 0) {
                                    this.field_70170_p.func_175656_a(checkPos2, ((BlockNeoshocker) floorState2.func_177230_c()).func_176203_a(1));
                                }
                                d4 = blockZ2 + 1.0d;
                            }
                        }
                        d3 = blockX2 + 1.0d;
                    }
                    break;
                }
                break;
            case 2:
                soundPlayers(SoundInit.ELECTRIC_SHOCK, 1.0f);
                double d5 = aabb.field_72340_a;
                while (true) {
                    double blockX3 = d5;
                    if (blockX3 < aabb.field_72336_d) {
                        double d6 = aabb.field_72339_c;
                        while (true) {
                            double blockZ3 = d6;
                            if (blockZ3 < aabb.field_72334_f) {
                                BlockPos checkPos3 = new BlockPos(blockX3, blockY, blockZ3);
                                IBlockState floorState3 = this.field_70170_p.func_180495_p(checkPos3);
                                if (floorState3.func_177230_c() == BlockInit.neoshocker) {
                                    BlockNeoshocker shock = (BlockNeoshocker) floorState3.func_177230_c();
                                    int meta = shock.func_176201_c(floorState3);
                                    if (meta == 1) {
                                        this.field_70170_p.func_175656_a(checkPos3, shock.func_176203_a(2));
                                    }
                                }
                                d6 = blockZ3 + 1.0d;
                            }
                        }
                        d5 = blockX3 + 1.0d;
                    }
                    break;
                }
                break;
        }
    }

    private AxisAlignedBB getArenaAABB() {
        return GalaxyCoordinates.getShockArenaAABB();
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        int lifePercent = Math.round((100 * (numberOfLives() - getLivesCount())) / numberOfLives());
        messagePlayers(TextFmt.Gold + "The Galactic Terror is at " + lifePercent + "% health.");
    }

    protected void messagePlayers(String message) {
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            contender.func_145747_a(new TextComponentString(message));
        }
    }

    protected void soundPlayers(SoundEvent sound, float vol) {
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            this.field_70170_p.func_184133_a((EntityPlayer) null, contender.func_180425_c(), sound, SoundCategory.MASTER, vol, 0.9f + (this.field_70146_Z.nextFloat() * 0.2f));
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.GALACTIC_TERROR_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.GALACTIC_TERROR_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.GALACTIC_TERROR_AMBIENT;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 250;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K) {
            func_145779_a(ItemInit.multigalacticPowerCrystal, 1);
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    protected EntityAIFloatAttack createShootAI() {
        return null;
    }
}
