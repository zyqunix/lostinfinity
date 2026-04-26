package xol.lostinfinity.mob.render.minion;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.minion.EntityLuminousGuardian;
import xol.lostinfinity.mob.model.starforge.ModelWisp;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/minion/RenderLuminousGuardian.class */
public class RenderLuminousGuardian extends RenderMinion<EntityLuminousGuardian> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/luminous_guardian.png");

    public RenderLuminousGuardian(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelWisp(), 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityLuminousGuardian entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(0.3f, 0.3f, 0.3f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityLuminousGuardian entity) {
        return TEXTURES;
    }
}
