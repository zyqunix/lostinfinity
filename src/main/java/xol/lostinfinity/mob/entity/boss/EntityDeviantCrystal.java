package xol.lostinfinity.mob.entity.boss;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantBear;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityDeviantCrystal extends EntityMob implements IMaxAttack {
    private boolean superMutated;
    public EntityDeviantCrystal(World worldIn) {
        super(worldIn);
        this.superMutated = false;
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
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74757_a("SpawnForm", this.superMutated);
    }
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setSuperMutated(tag.func_74767_n("SpawnForm"));
    }
    public void setSuperMutated(boolean b) {
        this.superMutated = b;
    }
    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -145.0d), new BlockPos(52.0d, 85.0d, -40.0d));
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if ((this.field_70173_aa + 50) % 100 == 0 && !this.field_70170_p.field_72995_K) {
            boolean found_pl = false;
            for (EntityPlayer entityPlayer : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
                found_pl = true;
            }
            if (found_pl) {
                func_184185_a(SoundInit.DEVIATION, 2.0f, 0.5f + this.field_70146_Z.nextFloat());
                int count = 0;
                for (EntityDeviantBear entityDeviantBear : this.field_70170_p.func_72872_a(EntityDeviantBear.class, getArenaAABB())) {
                    count++;
                }
                if (count < 5) {
                    EntityDeviantBear spawnEntity = new EntityDeviantBear(this.field_70170_p);
                    spawnEntity.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    if (this.superMutated) {
                        spawnEntity.setMutation(1);
                    }
                    this.field_70170_p.func_72838_d(spawnEntity);
                    return;
                }
                return;
            }
            func_70106_y();
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
