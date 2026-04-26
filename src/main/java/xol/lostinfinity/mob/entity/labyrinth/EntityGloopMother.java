package xol.lostinfinity.mob.entity.labyrinth;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityGloopMother extends EntityMultipleLives {
    private int spawnTimer;
    public EntityGloopMother(World worldIn) {
        super(worldIn);
        this.spawnTimer = 0;
        func_70105_a(1.0f, 1.0f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2500.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (this.spawnTimer == 0) {
            if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 20 == 0) {
                EntityPlayer foundPlayer = null;
                for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(12.0d, 12.0d, 12.0d))) {
                    foundPlayer = near_pl;
                }
                if (foundPlayer != null) {
                    this.spawnTimer = 100;
                    return;
                }
                return;
            }
            return;
        }
        if (this.spawnTimer % 20 == 0) {
            int count = 0;
            for (EntityGloop entityGloop : this.field_70170_p.func_72872_a(EntityGloop.class, func_174813_aQ().func_72314_b(18.0d, 18.0d, 18.0d))) {
                count++;
            }
            if (count < 8) {
                EntityGloop gloop = new EntityGloop(this.field_70170_p);
                gloop.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                this.field_70170_p.func_72838_d(gloop);
            }
        }
        this.spawnTimer--;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 7;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_GLOOPMOTHER;
    }
}
