package xol.lostinfinity.mob.entity.fungal;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/fungal/EntityMushmerra.class */
public class EntityMushmerra extends EntityMultipleLives {
    private static final int SPAWN_FREQUENCY_TICKS = 100;

    public EntityMushmerra(World worldIn) {
        super(worldIn);
        func_70105_a(1.9f, 2.2f);
    }

    public void func_70071_h_() {
        BlockPos safeSpawn;
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa > 120 && this.field_70173_aa % 100 == 0 && new Random().nextBoolean() && (safeSpawn = getSafeLocationNearby()) != null) {
            EntityMushmerraClone clone = new EntityMushmerraClone(this.field_70170_p);
            clone.func_70107_b(safeSpawn.func_177958_n(), safeSpawn.func_177956_o(), safeSpawn.func_177952_p());
            this.field_70170_p.func_72838_d(clone);
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 14;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        super.func_184651_r();
        this.field_70714_bg.func_75776_a(0, new EntityAIWanderAvoidWater(this, 1.0d));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23000000417232513d);
    }

    private BlockPos getSafeLocationNearby() {
        BlockPos pos = func_180425_c();
        Vec3d vec = new Vec3d(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
        for (int i = 0; i < 30; i++) {
            vec = vec.func_72441_c(this.field_70170_p.field_73012_v.nextInt(5) - 2, this.field_70170_p.field_73012_v.nextInt(5) - 2, this.field_70170_p.field_73012_v.nextInt(5) - 2);
            BlockPos newPos = new BlockPos(vec);
            if (this.field_70170_p.func_175623_d(newPos) && this.field_70170_p.func_175623_d(newPos.func_177974_f()) && this.field_70170_p.func_175623_d(newPos.func_177976_e()) && this.field_70170_p.func_175623_d(newPos.func_177978_c()) && this.field_70170_p.func_175623_d(newPos.func_177968_d()) && this.field_70170_p.func_175623_d(newPos.func_177984_a()) && this.field_70170_p.func_175623_d(newPos.func_177984_a().func_177974_f()) && this.field_70170_p.func_175623_d(newPos.func_177984_a().func_177976_e()) && this.field_70170_p.func_175623_d(newPos.func_177984_a().func_177978_c()) && this.field_70170_p.func_175623_d(newPos.func_177984_a().func_177968_d()) && this.field_70170_p.func_175623_d(newPos.func_177984_a().func_177984_a()) && this.field_70170_p.func_175623_d(newPos.func_177984_a().func_177984_a().func_177974_f()) && this.field_70170_p.func_175623_d(newPos.func_177984_a().func_177984_a().func_177976_e()) && this.field_70170_p.func_175623_d(newPos.func_177984_a().func_177984_a().func_177978_c()) && this.field_70170_p.func_175623_d(newPos.func_177984_a().func_177984_a().func_177968_d()) && !this.field_70170_p.func_175623_d(newPos.func_177977_b()) && !this.field_70170_p.func_180495_p(newPos.func_177977_b()).func_185904_a().func_76224_d()) {
                return new BlockPos(vec);
            }
        }
        return null;
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealTrueDamage(this, func_70638_az(), func_70638_az().func_110138_aP() * 0.7f);
            return true;
        }
        return false;
    }

    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_MUSHMERRA;
    }
}
