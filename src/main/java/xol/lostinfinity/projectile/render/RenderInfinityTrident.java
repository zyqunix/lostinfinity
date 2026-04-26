package xol.lostinfinity.projectile.render;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import xol.lostinfinity.mob.model.item.ModelInfinityTrident;
import xol.lostinfinity.projectile.entity.EntityInfinityTrident;
@SideOnly(Side.CLIENT)
public class RenderInfinityTrident<T extends EntityInfinityTrident> extends Render<T> {
    public static final ResourceLocation NORMAL = new ResourceLocation("lostinfinity:textures/entity/infinity_trident.png");
    public static final ResourceLocation UPGRADED = new ResourceLocation("lostinfinity:textures/entity/infinity_trident_mk2.png");
    private ModelBase model;
    public RenderInfinityTrident(RenderManager renderManagerIn) {
        super(renderManagerIn);
        this.model = new ModelInfinityTrident();
    }
    public ResourceLocation func_110775_a(T entity) {
        return getCustomTexture(entity);
    }
    private ResourceLocation getCustomTexture(EntityInfinityTrident entity) {
        if (entity.getUpgraded()) {
            return UPGRADED;
        }
        return NORMAL;
    }
    public void func_76986_a(T entity, double x, double y, double z, float yaw, float partialTick) {
        GL11.glPushMatrix();
        if (entity.getUpgraded()) {
            func_110776_a(UPGRADED);
        } else {
            func_110776_a(NORMAL);
        }
        GL11.glTranslated(x, y - 0.75d, z);
        GL11.glRotated(yaw, 0.0d, 1.0d, 0.0d);
        this.model.func_78088_a(entity, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }
}
