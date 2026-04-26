package xol.lostinfinity.mob.entity.boss;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/boss/EntityRestorationCrystal.class */
public class EntityRestorationCrystal extends EntityMob implements IMaxAttack {
    public EntityRestorationCrystal(World worldIn) {
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
        if ((this.field_70173_aa + 150) % 300 == 0 && !this.field_70170_p.field_72995_K) {
            AxisAlignedBB aabb = getArenaAABB();
            boolean healed = false;
            for (EntityRikarus rikarus : this.field_70170_p.func_72872_a(EntityRikarus.class, aabb)) {
                healed = true;
                rikarus.gainFormBack();
                func_184185_a(SoundInit.RIKARUS_HEAL, 3.0f, 1.0f);
            }
            if (!healed) {
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
