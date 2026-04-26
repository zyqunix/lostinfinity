package xol.lostinfinity.common.events;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.util.DimensionNoBuild;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.item.basics.ItemChanneling;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhu;
import xol.lostinfinity.mob.entity.galaxy.EntityGalaxyDragon;
import xol.lostinfinity.mob.entity.minion.EntityBombDrone;
import xol.lostinfinity.mob.entity.minion.andromeda.EntityAndromedaSegment;
@Mod.EventBusSubscriber({Side.CLIENT})
public class EventsClientRender {
    private static final Map<Integer, Entity> renderLast = new HashMap();
    private static final Map<Integer, Entity> rendered = new HashMap();
    public static final Map<Integer, Entity> renderForce = new HashMap();
    private static boolean lock = false;
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onFogEvent(EntityViewRenderEvent.FogColors event) {
        if (event.getEntity().field_70170_p.field_73011_w.func_186058_p() == DimensionInit.nonexistence) {
            double height = event.getEntity().field_70163_u - 30.0d;
            if (height < 0.0d) {
                height = 0.0d;
            }
            float var_clr = ((float) height) / 400.0f;
            if (var_clr > 0.5f) {
                var_clr = 0.5f;
            }
            event.setRed(var_clr);
            event.setGreen(var_clr / 2.0f);
            event.setBlue(0.0f);
            return;
        }
        if (event.getEntity().field_70170_p.field_73011_w.func_186058_p() == DimensionInit.infiniteMurk) {
            event.setRed(0.0f);
            event.setBlue(0.1f);
            event.setGreen(0.05f);
        } else if (event.getEntity().field_70170_p.func_180494_b(event.getEntity().func_180425_c()) instanceof DimensionNoBuild) {
            event.setRed(0.0f);
            event.setBlue(0.0f);
            event.setGreen(0.0f);
        }
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onFogDensity(EntityViewRenderEvent.FogDensity event) {
        if (event.getEntity().field_70170_p.field_73011_w.func_186058_p() == DimensionInit.nonexistence) {
            event.setDensity(0.03f);
            event.setCanceled(true);
        }
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(receiveCanceled = true)
    public void itemToolTip(ItemTooltipEvent event) {
        if (event.getItemStack().func_77942_o() && event.getItemStack().func_77978_p().func_74764_b("InfinityMiraged")) {
            event.getToolTip().add(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Aqua) + "MIRAGED");
        }
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onRenderHand(RenderHandEvent event) {
        if (Minecraft.func_71410_x().field_71439_g.func_82150_aj()) {
            event.setCanceled(true);
        }
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onRenderPlayer(RenderLivingEvent.Pre<EntityPlayer> event) {
        if (!(event.getEntity() instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = event.getEntity();
        if (player.func_82150_aj()) {
            event.setCanceled(true);
        }
        if (player.func_184208_bv() instanceof EntityBombDrone) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179152_a(0.0f, 0.0f, 0.0f);
        }
        EnumHand right = EnumHand.MAIN_HAND;
        EnumHand left = EnumHand.OFF_HAND;
        if ((player instanceof EntityPlayerSP) && player.func_184591_cq() == EnumHandSide.LEFT) {
            right = EnumHand.OFF_HAND;
            left = EnumHand.MAIN_HAND;
        }
        if (event.getRenderer().func_177087_b() instanceof ModelBiped) {
            if (isHoldingCustomPose(player, right)) {
                event.getRenderer().func_177087_b().field_187076_m = ModelBiped.ArmPose.BOW_AND_ARROW;
            } else if (isHoldingCustomPose(player, left)) {
                event.getRenderer().func_177087_b().field_187075_l = ModelBiped.ArmPose.BOW_AND_ARROW;
            }
        }
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onPostRenderPlayer(RenderLivingEvent.Post<EntityPlayer> event) {
        if (!(event.getEntity() instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = event.getEntity();
        if (player.func_82150_aj()) {
            event.setCanceled(true);
        }
        if (player.func_184208_bv() instanceof EntityBombDrone) {
            GlStateManager.func_179121_F();
        }
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onFOVChange(FOVUpdateEvent event) {
        EntityPlayer player = event.getEntity();
        ItemStack stack = player.func_184607_cu();
        if (player.func_184587_cr() && (stack.func_77973_b() instanceof ItemChanneling)) {
            ItemChanneling channeling = (ItemChanneling) stack.func_77973_b();
            float oldFOV = event.getFov();
            event.setNewfov(channeling.updateFOV(player, stack, oldFOV));
        }
    }
    private static boolean isHoldingCustomPose(EntityPlayer player, EnumHand hand) {
        return player.func_184586_b(hand).func_77973_b() instanceof ICustomHoldPose;
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onRenderGalaxyDragon(RenderLivingEvent.Pre<EntityGalaxyDragon> event) {
        if (lock || !(event.getEntity() instanceof EntityGalaxyDragon)) {
            return;
        }
        Entity entity = (EntityGalaxyDragon) event.getEntity();
        if (Minecraft.func_71410_x().field_71474_y.field_74320_O == 0 && Minecraft.func_71410_x().field_71439_g.func_184208_bv() == entity) {
            event.setCanceled(true);
            renderLast.put(Integer.valueOf(entity.func_145782_y()), entity);
        }
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onRenderAndromeda(RenderLivingEvent.Pre<EntityAndromedaSegment> event) {
        if (lock || !(event.getEntity() instanceof EntityAndromedaSegment)) {
            return;
        }
        Entity entity = (EntityAndromedaSegment) event.getEntity();
        EntityPlayerSP owner = entity.getController().func_70902_q();
        if (owner != null && owner == Minecraft.func_71410_x().field_71439_g && entity.func_70068_e(owner) <= 900.0d) {
            event.setCanceled(true);
            renderLast.put(Integer.valueOf(entity.func_145782_y()), entity);
        } else {
            rendered.put(Integer.valueOf(entity.func_145782_y()), entity);
        }
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onRenderCthulhu(RenderLivingEvent.Pre<EntityCthulhu> event) {
        if (lock || !(event.getEntity() instanceof EntityCthulhu)) {
            return;
        }
        Entity entity = (EntityCthulhu) event.getEntity();
        rendered.put(Integer.valueOf(entity.func_145782_y()), entity);
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onRenderLast(RenderWorldLastEvent event) {
        if (renderLast.isEmpty() && renderForce.isEmpty()) {
            rendered.clear();
            return;
        }
        lock = true;
        GlStateManager.func_179127_m();
        RenderHelper.func_74519_b();
        Minecraft.func_71410_x().field_71460_t.func_180436_i();
        Iterator<Integer> i = renderForce.keySet().iterator();
        while (i.hasNext()) {
            int id = i.next().intValue();
            Entity entity = renderForce.get(Integer.valueOf(id));
            if (entity == null || entity.field_70128_L) {
                i.remove();
            } else if (!rendered.containsKey(Integer.valueOf(id)) && !renderLast.containsKey(Integer.valueOf(id))) {
                Minecraft.func_71410_x().func_175598_ae().func_188388_a(entity, event.getPartialTicks(), false);
            }
        }
        for (Entity entity2 : renderLast.values()) {
            if (entity2 != null && !entity2.field_70128_L) {
                Minecraft.func_71410_x().func_175598_ae().func_188388_a(entity2, event.getPartialTicks(), false);
            }
        }
        Minecraft.func_71410_x().field_71460_t.func_175072_h();
        RenderHelper.func_74518_a();
        GlStateManager.func_179106_n();
        lock = false;
        renderLast.clear();
        rendered.clear();
    }
    @SubscribeEvent
    public void onClientDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        renderLast.clear();
        renderForce.clear();
        rendered.clear();
    }
    @SubscribeEvent
    public void onPlayerChangeWorld(WorldEvent.Unload event) {
        renderLast.clear();
        renderForce.clear();
        rendered.clear();
    }
}
