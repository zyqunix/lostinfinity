package xol.lostinfinity.mob.render.sea;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.sea.EntityDoublerang;
import xol.lostinfinity.mob.model.sea.ModelDoublerang;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/sea/RenderDoublerang.class */
public class RenderDoublerang extends RenderLiving<EntityDoublerang> {
    public RenderDoublerang(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelDoublerang(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityDoublerang entity) {
        int variant = entity.getVisualStyle();
        switch (variant) {
            case 0:
                return new ResourceLocation("lostinfinity:textures/entity/sea/doublerang1.png");
            case 1:
                return new ResourceLocation("lostinfinity:textures/entity/sea/doublerang2.png");
            case 2:
                return new ResourceLocation("lostinfinity:textures/entity/sea/doublerang3.png");
            case 3:
                return new ResourceLocation("lostinfinity:textures/entity/sea/doublerang4.png");
            default:
                return new ResourceLocation("lostinfinity:textures/entity/sea/doublerang1.png");
        }
    }
}
