package xol.lostinfinity.projectile.render;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import xol.lostinfinity.mob.entity.sea.leviathan.EntityLeviathanSegment;
import xol.lostinfinity.projectile.entity.EntityLeviathanBreath;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/render/RenderLeviathanBreath.class */
public class RenderLeviathanBreath extends Render<EntityLeviathanBreath> {
    public static final ResourceLocation TEXTURE_LASER_BEAM = new ResourceLocation("lostinfinity:textures/particles/laser_beam_bright.png");

    public RenderLeviathanBreath(RenderManager renderManager) {
        super(renderManager);
    }

    /* JADX INFO: renamed from: doRender, reason: merged with bridge method [inline-methods] */
    public void func_76986_a(EntityLeviathanBreath entity, double x, double y, double z, float entityYaw, float partialTicks) {
        Vec3d dir = entity.getDirection(partialTicks);
        if (dir == null) {
            return;
        }
        func_110776_a(TEXTURE_LASER_BEAM);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179129_p();
        GlStateManager.func_179141_d();
        GlStateManager.func_179140_f();
        GlStateManager.func_179147_l();
        GlStateManager.func_179092_a(516, 0.15f);
        GlStateManager.func_179112_b(1, 1);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        float segment = entity.getOwnerSize() * 0.75f * 0.5f;
        EntityLeviathanSegment head = entity.getOwner().segments[0];
        float pitch = head.field_70125_A;
        float yaw = head.field_70177_z;
        Vec3d normDir = LMath.fastNormalize(dir);
        Vec3d rotate = new Vec3d(0.0d, segment, 0.0d).func_178789_a(pitch * 0.017453292f).func_178785_b(yaw * 0.017453292f);
        bufferbuilder.func_181662_b((x - normDir.field_72450_a) + rotate.field_72450_a, (y - normDir.field_72448_b) + rotate.field_72448_b, (z - normDir.field_72449_c) + rotate.field_72449_c).func_187315_a(0.0d, 1.0d).func_181675_d();
        bufferbuilder.func_181662_b((x - normDir.field_72450_a) - rotate.field_72450_a, (y - normDir.field_72448_b) - rotate.field_72448_b, (z - normDir.field_72449_c) - rotate.field_72449_c).func_187315_a(0.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b((x + dir.field_72450_a) - rotate.field_72450_a, (y + dir.field_72448_b) - rotate.field_72448_b, (z + dir.field_72449_c) - rotate.field_72449_c).func_187315_a(1.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b(x + dir.field_72450_a + rotate.field_72450_a, y + dir.field_72448_b + rotate.field_72448_b, z + dir.field_72449_c + rotate.field_72449_c).func_187315_a(1.0d, 1.0d).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179118_c();
        GlStateManager.func_179145_e();
        GlStateManager.func_179089_o();
        GlStateManager.func_179084_k();
    }

    /* JADX INFO: renamed from: shouldRender, reason: merged with bridge method [inline-methods] */
    public boolean func_177071_a(EntityLeviathanBreath livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityLeviathanBreath entity) {
        return TEXTURE_LASER_BEAM;
    }
}
