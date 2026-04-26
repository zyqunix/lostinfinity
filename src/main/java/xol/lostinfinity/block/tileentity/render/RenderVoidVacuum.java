package xol.lostinfinity.block.tileentity.render;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import xol.lostinfinity.block.tileentity.TileEntityVoidVacuum;
import xol.lostinfinity.init.BlockInit;
public class RenderVoidVacuum extends TileEntitySpecialRenderer<TileEntityVoidVacuum> {
    public static final ResourceLocation TEXTURE_ELASTIC_STRING = new ResourceLocation("lostinfinity:textures/particles/elastic_thread.png");
    public void func_192841_a(TileEntityVoidVacuum te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        EntityLivingBase target;
        if (te.getActive()) {
            IBlockState state = te.func_145831_w().func_180495_p(te.func_174877_v());
            Block block = state.func_177230_c();
            if (!block.equals(BlockInit.voidVacuum) || (target = te.getTarget()) == null || target.field_70128_L) {
                return;
            }
            Vec3d pos = new Vec3d(te.func_174877_v().func_177958_n(), te.func_174877_v().func_177956_o(), te.func_174877_v().func_177952_p());
            Vec3d playerVec = target.func_174791_d();
            func_147499_a(TEXTURE_ELASTIC_STRING);
            Tessellator tessellator = Tessellator.func_178181_a();
            BufferBuilder bufferbuilder = tessellator.func_178180_c();
            GlStateManager.func_179129_p();
            GlStateManager.func_179141_d();
            GlStateManager.func_179140_f();
            int brightX = 240 % 65536;
            int brightY = 240 / 65536;
            OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, brightX, brightY);
            GlStateManager.func_179147_l();
            GlStateManager.func_179092_a(516, 0.3f);
            bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            double dist = pos.func_72438_d(playerVec);
            Vec3d dir = playerVec.func_178788_d(pos).func_72432_b();
            for (int i = 0; i < ((int) Math.floor(dist / ((double) 1.0f))); i++) {
                double xPos = x + 0.5d + (dir.field_72450_a * ((double) i) * ((double) 1.0f));
                double yPos = y + (dir.field_72448_b * ((double) i) * ((double) 1.0f));
                double zPos = z + 0.5d + (dir.field_72449_c * ((double) i) * ((double) 1.0f));
                bufferbuilder.func_181662_b(xPos, yPos + ((double) 2.0f), zPos).func_187315_a(0.0d, 1.0d).func_181675_d();
                bufferbuilder.func_181662_b(xPos, yPos, zPos).func_187315_a(0.0d, 0.0d).func_181675_d();
                bufferbuilder.func_181662_b(xPos + (((double) 1.0f) * dir.field_72450_a), yPos + (((double) 1.0f) * dir.field_72448_b), zPos + (((double) 1.0f) * dir.field_72449_c)).func_187315_a(1.0d, 0.0d).func_181675_d();
                bufferbuilder.func_181662_b(xPos + (((double) 1.0f) * dir.field_72450_a), yPos + ((double) 2.0f) + (((double) 1.0f) * dir.field_72448_b), zPos + (((double) 1.0f) * dir.field_72449_c)).func_187315_a(1.0d, 1.0d).func_181675_d();
            }
            tessellator.func_78381_a();
            GlStateManager.func_179145_e();
            GlStateManager.func_179089_o();
            GlStateManager.func_179084_k();
        }
    }
}
