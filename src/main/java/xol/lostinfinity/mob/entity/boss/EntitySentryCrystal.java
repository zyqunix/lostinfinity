package xol.lostinfinity.mob.entity.boss;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.projectile.entity.EntityDarkSpell;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntitySentryCrystal extends EntityMob implements IMaxAttack {
    public EntitySentryCrystal(World worldIn) {
        super(worldIn);
        func_70105_a(4.0f, 4.25f);
    }
    protected void func_184651_r() {
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0d);
    }
    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -145.0d), new BlockPos(52.0d, 85.0d, -40.0d));
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (this.field_70173_aa % 40 == 0) {
            boolean did_shot = false;
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
                if (!near_pl.func_184812_l_() && !this.field_70170_p.field_72995_K) {
                    func_70676_i(1.0f);
                    double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
                    double makeY = this.field_70163_u + ((double) (this.field_70131_O / 2.0f)) + 0.5d;
                    double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
                    double d2 = near_pl.field_70165_t - makeX;
                    double d3 = (near_pl.func_174813_aQ().field_72338_b + ((double) (near_pl.field_70131_O / 2.0f))) - makeY;
                    double d4 = near_pl.field_70161_v - makeZ;
                    EntityDarkSpell shot = new EntityDarkSpell(this.field_70170_p, this);
                    shot.func_70186_c(d2, d3, d4, 2.0f, 0.0f);
                    this.field_70170_p.func_72838_d(shot);
                    did_shot = true;
                }
            }
            if (did_shot) {
                func_184185_a(SoundInit.GALAXYFIRE, 2.0f, 0.5f + this.field_70146_Z.nextFloat());
            } else {
                func_70106_y();
            }
        }
    }
    protected SoundEvent func_184615_bR() {
        return null;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return null;
    }
    protected SoundEvent func_184639_G() {
        return null;
    }
    protected boolean func_70692_ba() {
        return false;
    }
    public boolean func_70814_o() {
        return true;
    }
    public int func_70641_bl() {
        return 1;
    }
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }
}
