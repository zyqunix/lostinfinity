package xol.lostinfinity.mob.render.minion;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.minion.EntityLostBlade;
import xol.lostinfinity.mob.model.minion.ModelLostBlade;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/minion/RenderLostBlade.class */
public class RenderLostBlade extends RenderMinion<EntityLostBlade> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/lost_blade.png");

    public RenderLostBlade(RenderManager renderManager) {
        super(renderManager, new ModelLostBlade(), 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityLostBlade entity) {
        return TEXTURES;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityLostBlade entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179139_a(0.25d, 0.25d, 0.25d);
    }
}
