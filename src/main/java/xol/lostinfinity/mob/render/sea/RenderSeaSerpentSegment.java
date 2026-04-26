package xol.lostinfinity.mob.render.sea;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.sea.seaserpent.EntitySeaSerpentSegment;
import xol.lostinfinity.mob.model.sea.ModelSeaSerpent;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/sea/RenderSeaSerpentSegment.class */
public class RenderSeaSerpentSegment extends RenderLiving<EntitySeaSerpentSegment> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("lostinfinity:textures/entity/sea/sea_serpent.png");

    public RenderSeaSerpentSegment(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelSeaSerpent(), 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntitySeaSerpentSegment entity) {
        return TEXTURE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntitySeaSerpentSegment entitylivingbaseIn, float partialTickTime) {
        double scale = entitylivingbaseIn.func_70603_bj();
        GlStateManager.func_179139_a(scale, scale, scale);
    }
}
