package xol.lostinfinity.projectile.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/render/RenderForbiddenBrand.class */
public class RenderForbiddenBrand<T extends Entity> extends Render<T> {
    public RenderForbiddenBrand(RenderManager renderManager) {
        super(renderManager);
    }

    public void func_76986_a(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
    }

    protected ResourceLocation func_110775_a(T entity) {
        return null;
    }
}
