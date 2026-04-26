package xol.lostinfinity.mob.entity.galaxy;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.projectile.entity.EntityGalaxyLaser;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/galaxy/EntityLaserSpire.class */
public class EntityLaserSpire extends EntityMultipleLives implements IMaxAttack {
    private int gameStyle;
    private boolean fastFire;
    private int fireOffset;

    public EntityLaserSpire(World worldIn) {
        super(worldIn);
        this.gameStyle = 0;
        this.fastFire = false;
        this.fireOffset = this.field_70146_Z.nextInt(6) * 5;
        func_70105_a(2.0f, 3.5f);
        func_184224_h(true);
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10000.0d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }

    public void setGameStyle(int style) {
        this.gameStyle = style;
    }

    public int getGameStyle() {
        return this.gameStyle;
    }

    public void setFastFire() {
        this.fastFire = true;
    }

    protected SoundEvent func_184639_G() {
        return null;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return null;
    }

    protected SoundEvent func_184615_bR() {
        return null;
    }

    public boolean func_70104_M() {
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74768_a("GameStyle", this.gameStyle);
        tag.func_74757_a("FastFire", this.fastFire);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        this.gameStyle = tag.func_74762_e("GameStyle");
        this.fastFire = tag.func_74767_n("FastFire");
    }

    private AxisAlignedBB getMyAABB() {
        switch (this.gameStyle) {
            case 1:
                return GalaxyCoordinates.getBlueAABB();
            case 2:
                return GalaxyCoordinates.getGreenAABB();
            case 3:
                return GalaxyCoordinates.getPinkAABB();
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                return GalaxyCoordinates.getYellowAABB();
            case 5:
                return GalaxyCoordinates.getSwordAABB();
            case TileEntityFusionTable.BOARD_COLUMNS /* 6 */:
                return GalaxyCoordinates.getBombAABB();
            case 7:
                return GalaxyCoordinates.getKnifeAABB();
            default:
                return GalaxyCoordinates.getBlueAABB();
        }
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (func_110143_aJ() > 0.0f) {
            func_70606_j(func_110138_aP());
        }
        this.field_70143_R = -1.0f;
        boolean valid_tick = (this.field_70173_aa + this.fireOffset) % (this.fastFire ? 30 : 50) == 0;
        if (!this.field_70170_p.field_72995_K && valid_tick) {
            boolean fired = false;
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getMyAABB())) {
                if (!near_pl.func_184812_l_() && func_70032_d(near_pl) > 3.0f) {
                    fired = true;
                    func_70676_i(1.0f);
                    double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
                    double makeY = this.field_70163_u + ((double) (this.field_70131_O / 2.0f));
                    double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
                    double d2 = near_pl.field_70165_t - makeX;
                    double d3 = ((near_pl.func_174813_aQ().field_72338_b + near_pl.func_174813_aQ().field_72337_e) / 2.0d) - makeY;
                    double d4 = near_pl.field_70161_v - makeZ;
                    EntityGalaxyLaser shot = new EntityGalaxyLaser(this.field_70170_p, makeX, makeY, makeZ);
                    shot.setThrower(this);
                    shot.func_70186_c(d2, d3, d4, 1.0f, 0.0f);
                    this.field_70170_p.func_72838_d(shot);
                }
            }
            if (fired) {
                func_184185_a(SoundInit.LASER_WEAPON_2, 2.0f, 1.0f);
            }
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 200;
    }

    protected boolean func_70692_ba() {
        return false;
    }
}
