package xol.lostinfinity.block.tileentity;

import java.util.List;
import java.util.Random;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.nebula.EntityNebulaGiant;
import xol.lostinfinity.mob.entity.nebula.EntityNebulaGrunt;
import xol.lostinfinity.mob.entity.nebula.EntityNebulaWizard;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/TileEntityNebulousBeacon.class */
public class TileEntityNebulousBeacon extends TileEntity implements IInventory, ITickable {
    private static final int RANGE = 3;
    public static final int HEALTH_MAX = 100;
    public static final int DURATION_MAX = 4800;
    private Random rand = new Random();
    private int health = 100;
    private int duration = 0;

    public void func_73660_a() {
        if (!this.field_145850_b.field_72995_K) {
            if (this.health <= 0) {
                this.field_145850_b.func_175655_b(this.field_174879_c, false);
                this.field_145850_b.func_175713_t(this.field_174879_c);
                this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundEvents.field_187929_hc, SoundCategory.BLOCKS, 1.0f, 1.0f);
                return;
            }
            this.duration++;
            trySpawnMob();
            checkIfDamaged();
            if (this.duration % 160 == 0) {
                this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundInit.NEBULOUS_BEACON, SoundCategory.BLOCKS, 1.5f, 0.8f + (this.rand.nextFloat() * 0.4f));
            }
            if (this.duration >= 4800) {
                this.duration = 0;
                this.health = 100;
                this.field_145850_b.func_175713_t(this.field_174879_c);
                this.field_145850_b.func_175698_g(this.field_174879_c);
                EntityItem generator = new EntityItem(this.field_145850_b, this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p(), new ItemStack(ItemInit.astralGenerator));
                this.field_145850_b.func_72838_d(generator);
                this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundInit.SPACE_VICTORY, SoundCategory.BLOCKS, 1.5f, 1.0f);
                IParticleSpawner.spawnParticle(this.field_145850_b, 61, 16, this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p());
            }
        }
    }

    private void checkIfDamaged() {
        if (this.duration % 40 == 0) {
            List<EntityNebulaGrunt> nearGrunts = this.field_145850_b.func_72872_a(EntityNebulaGrunt.class, new AxisAlignedBB(this.field_174879_c).func_186662_g(3.0d));
            List<EntityNebulaGiant> nearGiants = this.field_145850_b.func_72872_a(EntityNebulaGiant.class, new AxisAlignedBB(this.field_174879_c).func_186662_g(3.0d));
            List<EntityNebulaWizard> nearWizards = this.field_145850_b.func_72872_a(EntityNebulaWizard.class, new AxisAlignedBB(this.field_174879_c).func_186662_g(3.0d));
            int damage = nearGrunts.size() + nearGiants.size() + nearWizards.size();
            this.health -= damage;
            if (damage > 0) {
                this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundEvents.field_187927_ha, SoundCategory.PLAYERS, 1.0f, 1.5f - this.field_145850_b.field_73012_v.nextFloat());
            }
        }
    }

    private void trySpawnMob() {
        BlockPos spawnPos;
        if (this.duration % 40 == 0 && this.field_145850_b.field_73012_v.nextBoolean()) {
            double x = this.field_145850_b.field_73012_v.nextBoolean() ? this.field_174879_c.func_177958_n() - (this.field_145850_b.field_73012_v.nextInt(15) + 25) : this.field_174879_c.func_177958_n() + this.field_145850_b.field_73012_v.nextInt(15) + 25;
            double y = this.field_174879_c.func_177956_o();
            double z = this.field_145850_b.field_73012_v.nextBoolean() ? this.field_174879_c.func_177952_p() - (this.field_145850_b.field_73012_v.nextInt(15) + 25) : this.field_174879_c.func_177952_p() + this.field_145850_b.field_73012_v.nextInt(15) + 25;
            BlockPos blockPos = new BlockPos(x, y, z);
            while (true) {
                spawnPos = blockPos;
                if (this.field_145850_b.func_175623_d(spawnPos.func_177982_a(0, -1, 0))) {
                    break;
                }
                y += 1.0d;
                blockPos = new BlockPos(x, y, z);
            }
            BlockPos targetPos = this.field_174879_c.func_177963_a(0.0d, 1.0d, 0.0d);
            if (this.duration / 4800.0f < 0.25d) {
                EntityNebulaGrunt grunt = new EntityNebulaGrunt(this.field_145850_b, targetPos);
                grunt.func_70107_b(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
                this.field_145850_b.func_72838_d(grunt);
                return;
            }
            if (this.duration / 4800.0f < 0.5d) {
                EntityNebulaWizard wizard = new EntityNebulaWizard(this.field_145850_b, targetPos);
                wizard.func_70107_b(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
                this.field_145850_b.func_72838_d(wizard);
                return;
            }
            if (this.duration / 4800.0f < 0.75d) {
                EntityNebulaGiant giant = new EntityNebulaGiant(this.field_145850_b, targetPos);
                giant.func_70107_b(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
                this.field_145850_b.func_72838_d(giant);
                return;
            }
            switch (this.field_145850_b.field_73012_v.nextInt(RANGE)) {
                case 0:
                    EntityNebulaGrunt grunt2 = new EntityNebulaGrunt(this.field_145850_b, targetPos);
                    grunt2.func_70107_b(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
                    this.field_145850_b.func_72838_d(grunt2);
                    break;
                case 1:
                    EntityNebulaWizard wizard2 = new EntityNebulaWizard(this.field_145850_b, targetPos);
                    wizard2.func_70107_b(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
                    this.field_145850_b.func_72838_d(wizard2);
                    break;
                case 2:
                    EntityNebulaGiant giant2 = new EntityNebulaGiant(this.field_145850_b, targetPos);
                    giant2.func_70107_b(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
                    this.field_145850_b.func_72838_d(giant2);
                    break;
            }
        }
    }

    public int func_70302_i_() {
        return 0;
    }

    public boolean func_191420_l() {
        return false;
    }

    public ItemStack func_70301_a(int index) {
        return null;
    }

    public ItemStack func_70298_a(int index, int count) {
        return null;
    }

    public ItemStack func_70304_b(int index) {
        return null;
    }

    public void func_70299_a(int index, ItemStack stack) {
    }

    public int func_70297_j_() {
        return 0;
    }

    public boolean func_70300_a(EntityPlayer player) {
        return true;
    }

    public void func_174889_b(EntityPlayer player) {
    }

    public void func_174886_c(EntityPlayer player) {
    }

    public boolean func_94041_b(int index, ItemStack stack) {
        return false;
    }

    public int func_174887_a_(int id) {
        switch (id) {
            case 0:
                return this.health;
            case 1:
                return this.duration;
            default:
                return 0;
        }
    }

    public void func_174885_b(int id, int value) {
        switch (id) {
            case 0:
                this.health = value;
                break;
            case 1:
                this.duration = value;
                break;
        }
    }

    public int func_174890_g() {
        return 2;
    }

    public void func_174888_l() {
    }

    public String func_70005_c_() {
        return "tile.nebulous_beacon";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int getCurrentHealth() {
        return func_174887_a_(0);
    }

    public int getCurrentDuration() {
        return func_174887_a_(1);
    }

    public NBTTagCompound func_189515_b(NBTTagCompound compound) {
        super.func_189515_b(compound);
        compound.func_74768_a("health", this.health);
        compound.func_74768_a("duration", this.duration);
        return compound;
    }

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.health = compound.func_74762_e("health");
        this.duration = compound.func_74762_e("duration");
    }
}
