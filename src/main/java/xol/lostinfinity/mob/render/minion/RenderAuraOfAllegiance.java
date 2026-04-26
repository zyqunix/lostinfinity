package xol.lostinfinity.mob.render.minion;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.minion.EntityAuraOfAllegiance;
import xol.lostinfinity.mob.model.minion.ModelAuraOfAllegiance;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/minion/RenderAuraOfAllegiance.class */
public class RenderAuraOfAllegiance extends RenderMinion<EntityAuraOfAllegiance> {
    public static final ResourceLocation[] STONES = {new ResourceLocation("lostinfinity:textures/stone/cube_resolve.png"), new ResourceLocation("lostinfinity:textures/stone/cube_dread.png"), new ResourceLocation("lostinfinity:textures/stone/cube_ingenuity.png"), new ResourceLocation("lostinfinity:textures/stone/cube_aspiration.png"), new ResourceLocation("lostinfinity:textures/stone/cube_misdirection.png"), new ResourceLocation("lostinfinity:textures/stone/cube_ambition.png")};

    public RenderAuraOfAllegiance(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelAuraOfAllegiance(), 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityAuraOfAllegiance entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(0.25f, 0.25f, 0.25f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityAuraOfAllegiance entity) {
        return STONES[0];
    }
}
