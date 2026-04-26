package xol.lostinfinity.mob.render.cthulhu;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Rotations;
import net.minecraft.util.math.Vec3d;
import xol.lostinfinity.mob.entity.cthulhu.EntityCelestialStatue;
import xol.lostinfinity.mob.model.cthulhu.ModelCelestialStatue;
import xol.lostinfinity.util.Reference;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/cthulhu/RenderCelestialStatue.class */
public class RenderCelestialStatue extends RenderLiving<EntityCelestialStatue> {
    private static final ResourceLocation TEXTURE_BEAM = new ResourceLocation(Reference.MODID, "textures/projectiles/cthulhu/celestial_beam.png");
    private static final ResourceLocation TEXTURE_PORTAL = new ResourceLocation(Reference.MODID, "textures/projectiles/cthulhu/celestial_beam_portal.png");
    private static final ResourceLocation CEL_BLUE = new ResourceLocation(Reference.MODID, "textures/entity/cthulhu/celestial_blue.png");
    private static final ResourceLocation CEL_PINK = new ResourceLocation(Reference.MODID, "textures/entity/cthulhu/celestial_pink.png");
    private static final ResourceLocation CEL_PURPLE = new ResourceLocation(Reference.MODID, "textures/entity/cthulhu/celestial_purple.png");
    private static final ResourceLocation CEL_RED = new ResourceLocation(Reference.MODID, "textures/entity/cthulhu/celestial_red.png");

    public RenderCelestialStatue(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelCelestialStatue(), 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityCelestialStatue entity) {
        switch (entity.getType()) {
            case 0:
                return CEL_RED;
            case 1:
                return CEL_PURPLE;
            case 2:
                return CEL_BLUE;
            case 3:
                return CEL_PINK;
            default:
                return CEL_RED;
        }
    }

    /* JADX INFO: renamed from: doRender, reason: merged with bridge method [inline-methods] */
    public void func_76986_a(EntityCelestialStatue entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
        if (entity.getOwner() == null) {
            return;
        }
        GlStateManager.func_179129_p();
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b(x, y, z);
        Vec3d offset = entity.getBeamOriginOffset();
        GlStateManager.func_179137_b(offset.field_72450_a, offset.field_72448_b, offset.field_72449_c);
        Vec3d offset2 = entity.func_174791_d().func_178787_e(offset).func_178788_d(entity.getOwner().func_174824_e(1.0f));
        Rotations rotations = LMath.toPitchYaw(offset2);
        GlStateManager.func_179114_b(rotations.func_179416_c() + 180.0f, 0.0f, -1.0f, 0.0f);
        GlStateManager.func_179114_b(-rotations.func_179415_b(), 1.0f, 0.0f, 0.0f);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        double distance = LMath.fastLength(offset2);
        func_110776_a(TEXTURE_BEAM);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        bufferbuilder.func_181662_b(0.0d, (-0.5d) * ((double) 5.0f), 0.0d).func_187315_a(1.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b(0.0d, (-0.5d) * ((double) 5.0f), distance).func_187315_a(0.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b(0.0d, 0.5d * ((double) 5.0f), distance).func_187315_a(0.0d, 1.0d).func_181675_d();
        bufferbuilder.func_181662_b(0.0d, 0.5d * ((double) 5.0f), 0.0d).func_187315_a(1.0d, 1.0d).func_181675_d();
        bufferbuilder.func_181662_b((-0.5d) * ((double) 5.0f), 0.0d, 0.0d).func_187315_a(1.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b((-0.5d) * ((double) 5.0f), 0.0d, distance).func_187315_a(0.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b(0.5d * ((double) 5.0f), 0.0d, distance).func_187315_a(0.0d, 1.0d).func_181675_d();
        bufferbuilder.func_181662_b(0.5d * ((double) 5.0f), 0.0d, 0.0d).func_187315_a(1.0d, 1.0d).func_181675_d();
        tessellator.func_78381_a();
        func_110776_a(TEXTURE_PORTAL);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        bufferbuilder.func_181662_b(1.5d * ((double) 5.0f), (-1.5d) * ((double) 5.0f), 0.0d).func_187315_a(1.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b((-1.5d) * ((double) 5.0f), (-1.5d) * ((double) 5.0f), 0.0d).func_187315_a(0.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b((-1.5d) * ((double) 5.0f), 1.5d * ((double) 5.0f), 0.0d).func_187315_a(0.0d, 1.0d).func_181675_d();
        bufferbuilder.func_181662_b(1.5d * ((double) 5.0f), 1.5d * ((double) 5.0f), 0.0d).func_187315_a(1.0d, 1.0d).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179121_F();
        GlStateManager.func_179089_o();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityCelestialStatue entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179139_a(2.75d, 2.75d, 2.75d);
    }
}
