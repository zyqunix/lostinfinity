package xol.lostinfinity.mob.render.sea;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.sea.EntityGlowfish;
import xol.lostinfinity.mob.model.sea.ModelGlowfish;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/sea/RenderGlowfish.class */
public class RenderGlowfish extends RenderLiving<EntityGlowfish> {
    public RenderGlowfish(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelGlowfish(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityGlowfish entity) {
        int variant = entity.getVisualStyle();
        switch (variant) {
            case 0:
                return new ResourceLocation("lostinfinity:textures/entity/sea/glowfish1.png");
            case 1:
                return new ResourceLocation("lostinfinity:textures/entity/sea/glowfish2.png");
            case 2:
                return new ResourceLocation("lostinfinity:textures/entity/sea/glowfish3.png");
            case 3:
                return new ResourceLocation("lostinfinity:textures/entity/sea/glowfish4.png");
            default:
                return new ResourceLocation("lostinfinity:textures/entity/sea/glowfish1.png");
        }
    }
}
