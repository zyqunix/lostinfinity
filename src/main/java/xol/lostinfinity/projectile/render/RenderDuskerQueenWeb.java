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
import xol.lostinfinity.projectile.entity.EntityDuskerQueenWeb;
public class RenderDuskerQueenWeb<T extends Entity> extends Render<T> {
    public static final ResourceLocation TEXTURE_QUEEN_WEB = new ResourceLocation("lostinfinity:textures/particles/queen_web.png");
    public RenderDuskerQueenWeb(RenderManager renderManager) {
        super(renderManager);
    }
    public void func_76986_a(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        EntityDuskerQueenWeb attackEntity = (EntityDuskerQueenWeb) entity;
        Vec3d targetPos = attackEntity.getPlayerPos();
        Vec3d stopPos = attackEntity.getStopPos();
        float growth = attackEntity.getGrowth();
        if (targetPos != null && ((Entity) entity).field_70173_aa > 2) {
            func_110776_a(TEXTURE_QUEEN_WEB);
            Tessellator tessellator = Tessellator.func_178181_a();
            BufferBuilder bufferbuilder = tessellator.func_178180_c();
            GlStateManager.func_179129_p();
            GlStateManager.func_179141_d();
            GlStateManager.func_179140_f();
            GlStateManager.func_179147_l();
            GlStateManager.func_179092_a(516, 0.4f);
            GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
            bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            if (stopPos != null) {
                float dist = (float) attackEntity.func_174791_d().func_72438_d(stopPos);
                Vec3d dir = stopPos.func_178788_d(attackEntity.func_174791_d()).func_72432_b();
                for (int i = 0; i < ((int) Math.floor(dist / 1.0f)); i++) {
                    double xPos = x + (dir.field_72450_a * ((double) i) * ((double) 1.0f));
                    double yPos = y + (dir.field_72448_b * ((double) i) * ((double) 1.0f));
                    double zPos = z + (dir.field_72449_c * ((double) i) * ((double) 1.0f));
                    bufferbuilder.func_181662_b(xPos, yPos + ((double) 1.5f), zPos).func_187315_a(0.0d, 1.0d).func_181675_d();
                    bufferbuilder.func_181662_b(xPos, yPos, zPos).func_187315_a(0.0d, 0.0d).func_181675_d();
                    bufferbuilder.func_181662_b(xPos + (((double) 1.0f) * dir.field_72450_a), yPos + (((double) 1.0f) * dir.field_72448_b), zPos + (((double) 1.0f) * dir.field_72449_c)).func_187315_a(1.0d, 0.0d).func_181675_d();
                    bufferbuilder.func_181662_b(xPos + (((double) 1.0f) * dir.field_72450_a), yPos + ((double) 1.5f) + (((double) 1.0f) * dir.field_72448_b), zPos + (((double) 1.0f) * dir.field_72449_c)).func_187315_a(1.0d, 1.0d).func_181675_d();
                }
            } else {
                float dist2 = (float) attackEntity.func_174791_d().func_72438_d(targetPos);
                Vec3d dir2 = targetPos.func_178788_d(attackEntity.func_174791_d()).func_72432_b();
                for (int i2 = 0; i2 < ((int) Math.floor((dist2 * growth) / 1.0f)); i2++) {
                    double xPos2 = x + (dir2.field_72450_a * ((double) i2) * ((double) 1.0f));
                    double yPos2 = y + (dir2.field_72448_b * ((double) i2) * ((double) 1.0f));
                    double zPos2 = z + (dir2.field_72449_c * ((double) i2) * ((double) 1.0f));
                    bufferbuilder.func_181662_b(xPos2, yPos2 + ((double) 1.5f), zPos2).func_187315_a(0.0d, 1.0d).func_181675_d();
                    bufferbuilder.func_181662_b(xPos2, yPos2, zPos2).func_187315_a(0.0d, 0.0d).func_181675_d();
                    bufferbuilder.func_181662_b(xPos2 + (((double) 1.0f) * dir2.field_72450_a), yPos2 + (((double) 1.0f) * dir2.field_72448_b), zPos2 + (((double) 1.0f) * dir2.field_72449_c)).func_187315_a(1.0d, 0.0d).func_181675_d();
                    bufferbuilder.func_181662_b(xPos2 + (((double) 1.0f) * dir2.field_72450_a), yPos2 + ((double) 1.5f) + (((double) 1.0f) * dir2.field_72448_b), zPos2 + (((double) 1.0f) * dir2.field_72449_c)).func_187315_a(1.0d, 1.0d).func_181675_d();
                }
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
}
