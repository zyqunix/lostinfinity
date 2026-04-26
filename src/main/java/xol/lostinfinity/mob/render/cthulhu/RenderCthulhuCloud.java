package xol.lostinfinity.mob.render.cthulhu;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuCloud;
import xol.lostinfinity.mob.model.cthulhu.ModelCthulhuCloud;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/cthulhu/RenderCthulhuCloud.class */
public class RenderCthulhuCloud extends RenderLiving<EntityCthulhuCloud> {
    public RenderCthulhuCloud(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelCthulhuCloud(), 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityCthulhuCloud entity) {
        return null;
    }
}
