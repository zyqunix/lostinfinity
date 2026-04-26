package xol.lostinfinity.client.screen;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/client/screen/HeadHunterVision.class */
public class HeadHunterVision {
    public static final Map<UUID, Vec3d> oorPlayer = new ConcurrentHashMap();
    private static final ResourceLocation BULLSEYE = new ResourceLocation("lostinfinity:textures/gui/bullseye.png");
    private static final int DISTANCE = 160000;
    private final Minecraft mc = Minecraft.func_71410_x();

    @SubscribeEvent
    public void onPostRender(RenderWorldLastEvent event) {
        if (this.mc.field_71439_g.func_184586_b(EnumHand.MAIN_HAND).func_77973_b() == ItemInit.headHunter || this.mc.field_71439_g.func_184586_b(EnumHand.OFF_HAND).func_77973_b() == ItemInit.headHunter) {
            Set<UUID> rendered = new HashSet<>();
            for (EntityPlayerSP entityPlayerSP : this.mc.field_71441_e.field_73010_i) {
                if (entityPlayerSP != this.mc.field_71439_g) {
                    double dist = entityPlayerSP.func_174791_d().func_72436_e(this.mc.field_71439_g.func_174791_d());
                    if (dist <= 160000.0d) {
                        onTickRender(entityPlayerSP, dist);
                        rendered.add(entityPlayerSP.func_110124_au());
                    }
                }
            }
            if (this.mc.func_147114_u() == null) {
                return;
            }
            for (UUID uuid : oorPlayer.keySet()) {
                if (!rendered.contains(uuid)) {
                    NetworkPlayerInfo info = this.mc.func_147114_u().func_175102_a(uuid);
                    if (info == null) {
                        oorPlayer.remove(uuid);
                    } else {
                        Vec3d pos = oorPlayer.get(uuid);
                        double dist2 = this.mc.field_71439_g.func_174791_d().func_72436_e(pos);
                        if (dist2 <= 160000.0d) {
                            onTickRender(info.func_178837_g(), pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, dist2);
                        }
                    }
                }
            }
            return;
        }
        oorPlayer.clear();
    }

    private void onTickRender(EntityPlayer other, double dist) {
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        double partialTicks = this.mc.func_184121_ak();
        double dX = MathHelper.func_151238_b(other.field_70142_S, other.field_70165_t, partialTicks);
        double dY = MathHelper.func_151238_b(other.field_70137_T, other.field_70163_u, partialTicks);
        double dZ = MathHelper.func_151238_b(other.field_70136_U, other.field_70161_v, partialTicks);
        onTickRender(((AbstractClientPlayer) other).func_110306_p(), dX, dY, dZ, dist);
    }

    private void onTickRender(ResourceLocation skin, double dX, double dY, double dZ, double dist) {
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        double partialTicks = this.mc.func_184121_ak();
        EntityPlayerSP entityPlayerSP = this.mc.field_71439_g;
        double cX = MathHelper.func_151238_b(((EntityPlayer) entityPlayerSP).field_70142_S, ((EntityPlayer) entityPlayerSP).field_70165_t, partialTicks);
        double cY = MathHelper.func_151238_b(((EntityPlayer) entityPlayerSP).field_70137_T, ((EntityPlayer) entityPlayerSP).field_70163_u, partialTicks);
        double cZ = MathHelper.func_151238_b(((EntityPlayer) entityPlayerSP).field_70136_U, ((EntityPlayer) entityPlayerSP).field_70161_v, partialTicks);
        double yaw = LMath.degreeLerp(((EntityPlayer) entityPlayerSP).field_70758_at, ((EntityPlayer) entityPlayerSP).field_70759_as, partialTicks);
        double pitch = LMath.degreeLerp(((EntityPlayer) entityPlayerSP).field_70127_C, ((EntityPlayer) entityPlayerSP).field_70125_A, partialTicks);
        Vec3d normalized = LMath.fastNormalize(new Vec3d(dX - cX, dY - cY, dZ - cZ)).func_186678_a(5.0d);
        double r = MathHelper.func_151238_b(0.5d, 1.0d, 1.0d - (dist / 160000.0d));
        initialize(normalized.field_72450_a, normalized.field_72448_b + ((double) entityPlayerSP.func_70047_e()), normalized.field_72449_c, (float) yaw, (float) pitch);
        if (skin != null) {
            drawPlayerFace(skin, 0.5d * r, 0.5d * r);
        }
        drawBullseye(0.5d * r, 0.5d * r, r);
        cleanup();
    }

    private void initialize(double x, double y, double z, float cameraYaw, float cameraPitch) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b(x, y, z);
        GlStateManager.func_179114_b(-cameraYaw, 0.0f, 1.0f, 0.0f);
        GlStateManager.func_179114_b(cameraPitch, 1.0f, 0.0f, 0.0f);
        GlStateManager.func_179097_i();
        RenderHelper.func_74518_a();
    }

    private void cleanup() {
        RenderHelper.func_74519_b();
        GlStateManager.func_179126_j();
        GlStateManager.func_179121_F();
    }

    private void drawPlayerFace(ResourceLocation playerSkin, double width, double height) {
        double growX = 0.0625d * width;
        double growY = 0.0625d * height;
        this.mc.func_110434_K().func_110577_a(playerSkin);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        bufferbuilder.func_181662_b((-width) * 0.5d, height * 0.5d, 0.0d).func_187315_a(16.0f * 0.015625f, 8.0f * 0.015625f).func_181675_d();
        bufferbuilder.func_181662_b(width * 0.5d, height * 0.5d, 0.0d).func_187315_a(8.0f * 0.015625f, 8.0f * 0.015625f).func_181675_d();
        bufferbuilder.func_181662_b(width * 0.5d, (-height) * 0.5d, 0.0d).func_187315_a(8.0f * 0.015625f, 16.0f * 0.015625f).func_181675_d();
        bufferbuilder.func_181662_b((-width) * 0.5d, (-height) * 0.5d, 0.0d).func_187315_a(16.0f * 0.015625f, 16.0f * 0.015625f).func_181675_d();
        tessellator.func_78381_a();
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        bufferbuilder.func_181662_b(((-width) * 0.5d) - growX, (height * 0.5d) + growY, 0.0d).func_187315_a(48.0f * 0.015625f, 8.0f * 0.015625f).func_181675_d();
        bufferbuilder.func_181662_b((width * 0.5d) + growX, (height * 0.5d) + growY, 0.0d).func_187315_a(40.0f * 0.015625f, 8.0f * 0.015625f).func_181675_d();
        bufferbuilder.func_181662_b((width * 0.5d) + growX, ((-height) * 0.5d) - growY, 0.0d).func_187315_a(40.0f * 0.015625f, 16.0f * 0.015625f).func_181675_d();
        bufferbuilder.func_181662_b(((-width) * 0.5d) - growX, ((-height) * 0.5d) - growY, 0.0d).func_187315_a(48.0f * 0.015625f, 16.0f * 0.015625f).func_181675_d();
        tessellator.func_78381_a();
    }

    private void drawBullseye(double width, double height, double size) {
        this.mc.func_110434_K().func_110577_a(BULLSEYE);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        double growX = (0.0625d * width) + (0.4d * size);
        double growY = (0.0625d * height) + (0.4d * size);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        bufferbuilder.func_181662_b(((-width) * 0.5d) - growX, (height * 0.5d) + growY, 0.0d).func_187315_a(1.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b((width * 0.5d) + growX, (height * 0.5d) + growY, 0.0d).func_187315_a(0.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b((width * 0.5d) + growX, ((-height) * 0.5d) - growY, 0.0d).func_187315_a(0.0d, 1.0d).func_181675_d();
        bufferbuilder.func_181662_b(((-width) * 0.5d) - growX, ((-height) * 0.5d) - growY, 0.0d).func_187315_a(1.0d, 1.0d).func_181675_d();
        tessellator.func_78381_a();
    }
}
