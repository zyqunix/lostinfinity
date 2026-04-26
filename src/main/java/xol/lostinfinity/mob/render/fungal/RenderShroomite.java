package xol.lostinfinity.mob.render.fungal;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.fungal.EntityShroomite;
import xol.lostinfinity.mob.model.fungal.ModelShroomite;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/fungal/RenderShroomite.class */
public class RenderShroomite extends RenderLiving<EntityShroomite> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/fungal/shroomite.png");

    public RenderShroomite(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelShroomite(), 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityShroomite entity) {
        return TEXTURES;
    }
}
