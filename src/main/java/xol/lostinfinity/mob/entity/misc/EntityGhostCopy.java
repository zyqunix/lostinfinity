package xol.lostinfinity.mob.entity.misc;
import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
public class EntityGhostCopy extends EntityImmaterial {
    protected static final DataParameter<Optional<UUID>> COPIED_ID = EntityDataManager.func_187226_a(EntityGhostCopy.class, DataSerializers.field_187203_m);
    private float ghostScale;
    private float ghostAlpha;
    private float ghostColor;
    private List<Float> ghostAngles;
    private List<Integer> movementAdjustments;
    public EntityGhostCopy(World worldIn) {
        super(worldIn);
        this.ghostScale = 1.0f;
        this.ghostAlpha = 0.9f;
        this.ghostAngles = new ArrayList();
        this.movementAdjustments = new ArrayList();
        func_70105_a(0.1f, 0.1f);
        func_184224_h(true);
        this.ghostScale = 0.5f + this.field_70146_Z.nextFloat();
        for (int i = 0; i < 9; i++) {
            this.ghostAngles.add(Float.valueOf(this.field_70146_Z.nextFloat() - 0.5f));
            this.movementAdjustments.add(Integer.valueOf((-2) + this.field_70146_Z.nextInt(5)));
        }
        this.ghostColor = 0.25f + (0.5f * this.field_70146_Z.nextFloat());
    }
    public float getGhostScale() {
        return this.ghostScale;
    }
    public float getGhostAngle(int index) {
        return this.ghostAngles.get(index).floatValue();
    }
    public int getMoveAdjustment(int index) {
        return this.movementAdjustments.get(index).intValue();
    }
    public float getGhostAlpha() {
        return this.ghostAlpha;
    }
    public float getGhostColor() {
        return this.ghostColor;
    }
    @SideOnly(Side.CLIENT)
    public ResourceLocation getSkinForMyCopy() {
        AbstractClientPlayer copiedPlayer = getCopiedPlayer();
        if (copiedPlayer != null) {
            AbstractClientPlayer abPlayer = copiedPlayer;
            return abPlayer.func_110306_p();
        }
        return null;
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(COPIED_ID, Optional.absent());
    }
    public void setCopiedPlay(EntityPlayer player) {
        this.field_70180_af.func_187227_b(COPIED_ID, Optional.fromNullable(player.func_110124_au()));
    }
    private EntityPlayer getCopiedPlayer() {
        if (((Optional) this.field_70180_af.func_187225_a(COPIED_ID)).orNull() != null) {
            return this.field_70170_p.func_152378_a((UUID) ((Optional) this.field_70180_af.func_187225_a(COPIED_ID)).get());
        }
        return null;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa >= 12) {
                func_70106_y();
            }
        } else if (this.field_70173_aa % 3 == 2) {
            if (this.ghostAlpha != 0.0f) {
                this.ghostAlpha = 0.0f;
            } else {
                this.ghostAlpha = 0.2f + (this.field_70146_Z.nextFloat() * (0.6f - (MathHelper.func_76141_d(this.field_70173_aa / 2) * 0.1f)));
            }
        }
    }
    protected void func_82167_n(Entity entityIn) {
    }
}
