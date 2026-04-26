package xol.lostinfinity.projectile.render;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import xol.lostinfinity.projectile.entity.EntityLuminousGuardianLaser;
public class RenderLuminousGuardianLaser<T extends Entity> extends Render<T> {
    public static final ResourceLocation TEXTURE_LASER_BEAM = new ResourceLocation("lostinfinity:textures/particles/luminous_beam.png");
    public static final ResourceLocation TEXTURE_LASER_END = new ResourceLocation("lostinfinity:textures/particles/luminous_beam_end.png");
    public RenderLuminousGuardianLaser(RenderManager renderManager) {
        super(renderManager);
    }
    public void func_76986_a(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        EntityLuminousGuardianLaser attackEntity = (EntityLuminousGuardianLaser) entity;
        Vec3d stopPos = attackEntity.getTargetPos();
        if (stopPos == null) {
            return;
        }
        float segmentHeight = (0.004f + ((0.0f * attackEntity.counter) / 2.0f)) * attackEntity.counter;
        if (attackEntity.counter > 7) {
            segmentHeight = ((0.004f + (0.003f * (attackEntity.counter / 2.0f))) * (attackEntity.counter - 7)) + 0.08f;
        }
        if (segmentHeight > 1.6f) {
            segmentHeight = 1.6f;
        }
        if (attackEntity.counter > 2) {
            if (attackEntity.counter > 7 && attackEntity.counter < 21) {
                func_110776_a(TEXTURE_LASER_END);
            } else {
                func_110776_a(TEXTURE_LASER_BEAM);
            }
            Tessellator tessellator = Tessellator.func_178181_a();
            BufferBuilder bufferbuilder = tessellator.func_178180_c();
            GlStateManager.func_179129_p();
            GlStateManager.func_179141_d();
            GlStateManager.func_179140_f();
            GlStateManager.func_179147_l();
            GlStateManager.func_179092_a(516, 0.15f);
            GlStateManager.func_179112_b(770, 771);
            float alpha = 1.0f;
            if (attackEntity.counter > 10) {
                alpha = 1.0f - (0.125f * (attackEntity.counter - 10));
            }
            GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, alpha);
            bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            int id = attackEntity.getOwnerID();
            Entity owner = attackEntity.field_70170_p.func_73045_a(id);
            Vec3d attackPos = attackEntity.func_174791_d();
            Vec3d diff = new Vec3d(0.0d, 0.0d, 0.0d);
            if (owner != null) {
                attackPos = owner.func_174791_d();
                diff = attackPos.func_178788_d(attackEntity.func_174791_d());
            }
            float dist = (float) attackEntity.getDist();
            Vec3d dir = stopPos.func_178788_d(attackPos).func_72432_b();
            for (int i = 0; i < ((int) Math.floor(dist / 1.0f)); i++) {
                double xPos = x + diff.field_72450_a + (dir.field_72450_a * ((double) i) * ((double) 1.0f));
                double yPos = ((y + diff.field_72448_b) + ((dir.field_72448_b * ((double) i)) * ((double) 1.0f))) - ((double) (segmentHeight / 2.0f));
                double zPos = z + diff.field_72449_c + (dir.field_72449_c * ((double) i) * ((double) 1.0f));
                bufferbuilder.func_181662_b(xPos, yPos + ((double) segmentHeight), zPos).func_187315_a(0.0d, 1.0d).func_181675_d();
                bufferbuilder.func_181662_b(xPos, yPos, zPos).func_187315_a(0.0d, 0.0d).func_181675_d();
                bufferbuilder.func_181662_b(xPos + (((double) 1.0f) * dir.field_72450_a), yPos + (((double) 1.0f) * dir.field_72448_b), zPos + (((double) 1.0f) * dir.field_72449_c)).func_187315_a(1.0d, 0.0d).func_181675_d();
                bufferbuilder.func_181662_b(xPos + (((double) 1.0f) * dir.field_72450_a), yPos + ((double) segmentHeight) + (((double) 1.0f) * dir.field_72448_b), zPos + (((double) 1.0f) * dir.field_72449_c)).func_187315_a(1.0d, 1.0d).func_181675_d();
            }
            tessellator.func_78381_a();
            GlStateManager.func_179118_c();
            GlStateManager.func_179145_e();
            GlStateManager.func_179089_o();
            GlStateManager.func_179084_k();
        }
    }
    protected ResourceLocation func_110775_a(T entity) {
        return null;
    }
    public boolean func_177071_a(T livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return true;
    }
}
