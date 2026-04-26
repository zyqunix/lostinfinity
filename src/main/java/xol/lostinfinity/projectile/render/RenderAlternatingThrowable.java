package xol.lostinfinity.projectile.render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.ResourceLocation;
public class RenderAlternatingThrowable extends RenderProjectileBase<EntityThrowable> {
    private ResourceLocation texture;
    private ResourceLocation texture2;
    private int switch_timer;
    public RenderAlternatingThrowable(RenderManager renderManager, ResourceLocation texture, ResourceLocation texture2) {
        super(renderManager, 1.0f);
        this.switch_timer = 5;
        this.texture = texture;
        this.texture2 = texture2;
    }
    public RenderAlternatingThrowable(RenderManager renderManager, float scaleFactor, ResourceLocation texture, ResourceLocation texture2) {
        super(renderManager, scaleFactor);
        this.switch_timer = 5;
        this.texture = texture;
        this.texture2 = texture2;
    }
    public RenderAlternatingThrowable(RenderManager renderManager, float scaleFactor, ResourceLocation texture, ResourceLocation texture2, int timer) {
        super(renderManager, scaleFactor);
        this.switch_timer = 5;
        this.texture = texture;
        this.texture2 = texture2;
        this.switch_timer = timer;
    }
    public ResourceLocation func_110775_a(EntityThrowable entity) {
        if ((entity.field_70173_aa % this.switch_timer) * 2 < this.switch_timer) {
            return this.texture;
        }
        return this.texture2;
    }
}
