package xol.lostinfinity.projectile.render;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import xol.lostinfinity.projectile.entity.EntityCryoBeamEffect;
public class RenderCryoBeamEffect<T extends Entity> extends Render<T> {
    public static final ResourceLocation TEXTURE_CRYO_BEAM = new ResourceLocation("lostinfinity:textures/particles/cryo_beam.png");
    public static final ResourceLocation TEXTURE_ICE_BLAST = new ResourceLocation("lostinfinity:textures/particles/ice_blast.png");
    public RenderCryoBeamEffect(RenderManager renderManager) {
        super(renderManager);
    }
    public void func_76986_a(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        EntityCryoBeamEffect attackEntity = (EntityCryoBeamEffect) entity;
        if (attackEntity.isIceBlast()) {
            Vec3d target = attackEntity.func_174791_d();
            if (target != null && ((Entity) entity).field_70173_aa > 2) {
                func_110776_a(TEXTURE_ICE_BLAST);
                Tessellator tessellator = Tessellator.func_178181_a();
                BufferBuilder bufferbuilder = tessellator.func_178180_c();
                GlStateManager.func_179140_f();
                GlStateManager.func_179129_p();
                GlStateManager.func_179141_d();
                float growth = attackEntity.getGrowth();
                GlStateManager.func_179147_l();
                GlStateManager.func_179092_a(516, 0.4f);
                float alpha = attackEntity.getAlpha();
                if (alpha <= 0.0f) {
                    alpha = 0.0f;
                }
                GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, alpha);
                bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
                double xPos = x + 0.5d;
                double yPos = y + 0.10000000149011612d;
                double zPos = z + 0.5d;
                bufferbuilder.func_181662_b(((double) ((-0.5f) * growth)) + xPos, 1.0d + yPos, ((double) (0.5f * growth)) + zPos).func_187315_a(0.0d, 1.0d).func_181675_d();
                bufferbuilder.func_181662_b(((double) ((-0.5f) * growth)) + xPos, 1.0d + yPos, ((double) ((-0.5f) * growth)) + zPos).func_187315_a(0.0d, 0.0d).func_181675_d();
                bufferbuilder.func_181662_b(((double) (0.5f * growth)) + xPos, 1.0d + yPos, ((double) ((-0.5f) * growth)) + zPos).func_187315_a(1.0d, 0.0d).func_181675_d();
                bufferbuilder.func_181662_b(((double) (0.5f * growth)) + xPos, 1.0d + yPos, ((double) (0.5f * growth)) + zPos).func_187315_a(1.0d, 1.0d).func_181675_d();
                tessellator.func_78381_a();
                GlStateManager.func_179118_c();
                GlStateManager.func_179145_e();
                GlStateManager.func_179089_o();
                GlStateManager.func_179084_k();
                return;
            }
            return;
        }
        Vec3d targetPos = attackEntity.getPlayerPos();
        Vec3d stopPos = attackEntity.getStopPos();
        if (targetPos != null && ((Entity) entity).field_70173_aa > 2) {
            func_110776_a(TEXTURE_CRYO_BEAM);
            Tessellator tessellator2 = Tessellator.func_178181_a();
            BufferBuilder bufferbuilder2 = tessellator2.func_178180_c();
            GlStateManager.func_179129_p();
            GlStateManager.func_179141_d();
            GlStateManager.func_179140_f();
            GlStateManager.func_179147_l();
            GlStateManager.func_179092_a(516, 0.4f);
            GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
            bufferbuilder2.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            if (stopPos != null) {
                float dist = (float) attackEntity.func_174791_d().func_72438_d(stopPos);
                Vec3d dir = stopPos.func_178788_d(attackEntity.func_174791_d()).func_72432_b();
                for (int i = 0; i < ((int) Math.floor(dist / 1.0f)); i++) {
                    double xPos2 = x + (dir.field_72450_a * ((double) i) * ((double) 1.0f));
                    double yPos2 = y + (dir.field_72448_b * ((double) i) * ((double) 1.0f));
                    double zPos2 = z + (dir.field_72449_c * ((double) i) * ((double) 1.0f));
                    bufferbuilder2.func_181662_b(xPos2, yPos2 + ((double) 2.0f), zPos2).func_187315_a(0.0d, 1.0d).func_181675_d();
                    bufferbuilder2.func_181662_b(xPos2, yPos2, zPos2).func_187315_a(0.0d, 0.0d).func_181675_d();
                    bufferbuilder2.func_181662_b(xPos2 + (((double) 1.0f) * dir.field_72450_a), yPos2 + (((double) 1.0f) * dir.field_72448_b), zPos2 + (((double) 1.0f) * dir.field_72449_c)).func_187315_a(1.0d, 0.0d).func_181675_d();
                    bufferbuilder2.func_181662_b(xPos2 + (((double) 1.0f) * dir.field_72450_a), yPos2 + ((double) 2.0f) + (((double) 1.0f) * dir.field_72448_b), zPos2 + (((double) 1.0f) * dir.field_72449_c)).func_187315_a(1.0d, 1.0d).func_181675_d();
                }
            } else {
                float dist2 = (float) attackEntity.func_174791_d().func_72438_d(targetPos);
                Vec3d dir2 = targetPos.func_178788_d(attackEntity.func_174791_d()).func_72432_b();
                for (int i2 = 0; i2 < ((int) Math.floor(dist2 / 1.0f)); i2++) {
                    double xPos3 = x + (dir2.field_72450_a * ((double) i2) * ((double) 1.0f));
                    double yPos3 = y + (dir2.field_72448_b * ((double) i2) * ((double) 1.0f));
                    double zPos3 = z + (dir2.field_72449_c * ((double) i2) * ((double) 1.0f));
                    bufferbuilder2.func_181662_b(xPos3, yPos3 + ((double) 2.0f), zPos3).func_187315_a(0.0d, 1.0d).func_181675_d();
                    bufferbuilder2.func_181662_b(xPos3, yPos3, zPos3).func_187315_a(0.0d, 0.0d).func_181675_d();
                    bufferbuilder2.func_181662_b(xPos3 + (((double) 1.0f) * dir2.field_72450_a), yPos3 + (((double) 1.0f) * dir2.field_72448_b), zPos3 + (((double) 1.0f) * dir2.field_72449_c)).func_187315_a(1.0d, 0.0d).func_181675_d();
                    bufferbuilder2.func_181662_b(xPos3 + (((double) 1.0f) * dir2.field_72450_a), yPos3 + ((double) 2.0f) + (((double) 1.0f) * dir2.field_72448_b), zPos3 + (((double) 1.0f) * dir2.field_72449_c)).func_187315_a(1.0d, 1.0d).func_181675_d();
                }
            }
            tessellator2.func_78381_a();
            GlStateManager.func_179118_c();
            GlStateManager.func_179145_e();
            GlStateManager.func_179089_o();
            GlStateManager.func_179084_k();
        }
    }
    protected ResourceLocation func_110775_a(T entity) {
        return null;
    }
}
