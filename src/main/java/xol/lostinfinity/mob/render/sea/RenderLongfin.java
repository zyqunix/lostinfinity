package xol.lostinfinity.mob.render.sea;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.sea.EntityLongfin;
import xol.lostinfinity.mob.model.sea.ModelLongfin;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/sea/RenderLongfin.class */
public class RenderLongfin extends RenderLiving<EntityLongfin> {
    public RenderLongfin(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelLongfin(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityLongfin entity) {
        int variant = entity.getVisualStyle();
        switch (variant) {
            case 0:
                return new ResourceLocation("lostinfinity:textures/entity/sea/longfin1.png");
            case 1:
                return new ResourceLocation("lostinfinity:textures/entity/sea/longfin2.png");
            case 2:
                return new ResourceLocation("lostinfinity:textures/entity/sea/longfin3.png");
            case 3:
                return new ResourceLocation("lostinfinity:textures/entity/sea/longfin4.png");
            default:
                return new ResourceLocation("lostinfinity:textures/entity/sea/longfin1.png");
        }
    }
}
