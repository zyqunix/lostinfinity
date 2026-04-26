package xol.lostinfinity.mob.render.minion;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.minion.EntityAbyssalCrabulon;
import xol.lostinfinity.mob.model.sea.ModelCrabulon;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/minion/RenderAbyssalCrabulon.class */
public class RenderAbyssalCrabulon extends RenderMinion<EntityAbyssalCrabulon> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/abyssal_crabulon.png");

    public RenderAbyssalCrabulon(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelCrabulon(), 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityAbyssalCrabulon entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(0.3f, 0.3f, 0.3f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityAbyssalCrabulon entity) {
        return TEXTURES;
    }
}
