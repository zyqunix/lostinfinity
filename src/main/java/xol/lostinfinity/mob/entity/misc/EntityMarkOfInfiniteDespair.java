package xol.lostinfinity.mob.entity.misc;
import com.google.common.base.Optional;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
public class EntityMarkOfInfiniteDespair extends Entity {
    protected static final DataParameter<Optional<UUID>> OWNER_ID = EntityDataManager.func_187226_a(EntityMarkOfInfiniteDespair.class, DataSerializers.field_187203_m);
    protected static final DataParameter<Optional<UUID>> TARGET_PLAYER_ID = EntityDataManager.func_187226_a(EntityMarkOfInfiniteDespair.class, DataSerializers.field_187203_m);
    private static final int pitRadius = 2;
    private int timer;
    private double speed;
    public EntityMarkOfInfiniteDespair(World worldIn) {
        super(worldIn);
        this.timer = 180;
        this.speed = -1.0d;
        func_184224_h(true);
    }
    public EntityPlayer getOwner() {
        if (((Optional) this.field_70180_af.func_187225_a(OWNER_ID)).orNull() != null) {
            return this.field_70170_p.func_152378_a((UUID) ((Optional) this.field_70180_af.func_187225_a(OWNER_ID)).get());
        }
        return null;
    }
    public void setOwner(EntityPlayer player) {
        this.field_70180_af.func_187227_b(OWNER_ID, Optional.fromNullable(player.func_110124_au()));
    }
    public EntityPlayer getPlayerTarget() {
        if (((Optional) this.field_70180_af.func_187225_a(TARGET_PLAYER_ID)).orNull() != null) {
            return this.field_70170_p.func_152378_a((UUID) ((Optional) this.field_70180_af.func_187225_a(TARGET_PLAYER_ID)).get());
        }
        return null;
    }
    public void setPlayerTarget(EntityPlayer player) {
        this.field_70180_af.func_187227_b(TARGET_PLAYER_ID, Optional.fromNullable(player.func_110124_au()));
    }
    public void func_70071_h_() {
        super.func_70071_h_();
        this.field_70143_R = -1.0f;
        EntityPlayer owner = getOwner();
        EntityPlayer target = getPlayerTarget();
        if (!this.field_70170_p.field_72995_K) {
            if (owner == null || owner.field_70128_L || target == null || target.field_70128_L) {
                func_70106_y();
                return;
            }
            if (this.timer % 10 == 0) {
                func_184185_a(SoundInit.ELECTRIC_WOOSH, 2.0f, 0.8f + (this.field_70146_Z.nextFloat() * 0.4f));
            }
            for (int i = -2; i <= pitRadius; i++) {
                for (int k = -2; k <= pitRadius; k++) {
                    for (int j = -8; j <= 0; j++) {
                        if ((i * i) + (k * k) <= 4) {
                            BlockPos clear = target.func_180425_c().func_177982_a(i, j, k);
                            if (!this.field_70170_p.func_175623_d(clear)) {
                                this.field_70170_p.func_175698_g(clear);
                            }
                        }
                    }
                }
            }
            target.func_189654_d(true);
            target.field_70181_x = this.speed;
            target.field_70133_I = true;
            func_70634_a(target.field_70165_t, target.field_70163_u + 1.5d, target.field_70161_v);
            this.timer--;
            if (this.timer == 0 || target.field_70163_u < 2.0d) {
                target.func_70606_j(0.0f);
                target.func_189654_d(false);
                owner.func_191521_c(new ItemStack(ItemInit.voidAlteredSpine, 1));
                func_70106_y();
            }
        }
    }
    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(OWNER_ID, Optional.absent());
        this.field_70180_af.func_187214_a(TARGET_PLAYER_ID, Optional.absent());
    }
    protected void func_70037_a(NBTTagCompound compound) {
    }
    protected void func_70014_b(NBTTagCompound compound) {
    }
}
