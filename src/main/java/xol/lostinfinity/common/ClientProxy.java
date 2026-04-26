package xol.lostinfinity.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import xol.lostinfinity.client.ModelRegistry;
import xol.lostinfinity.client.audio.UniversalMovingSound;
import xol.lostinfinity.client.screen.CthulhuBossBar;
import xol.lostinfinity.client.screen.DistortionCover;
import xol.lostinfinity.client.screen.HeadHunterVision;
import xol.lostinfinity.client.screen.HealthValueGUI;
import xol.lostinfinity.client.special.ClientMindControlHandler;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.item.classify.IMovingSoundSource;
import xol.lostinfinity.util.compatibility.jer.JERCompatibility;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/ClientProxy.class */
public class ClientProxy extends CommonProxy {
    public static KeyBinding armorSetBonus = null;
    public static KeyBinding itemMode = null;
    public static KeyBinding dismount = null;

    @Override // xol.lostinfinity.common.CommonProxy
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }

    @Override // xol.lostinfinity.common.CommonProxy
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        EventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.register(new ParticleInit());
        forgeBus.register(new ModelRegistry());
    }

    @Override // xol.lostinfinity.common.CommonProxy
    public void init() {
        super.init();
        registerKeybinds();
        if (Loader.isModLoaded("jeresources")) {
            JERCompatibility.init();
        }
    }

    @Override // xol.lostinfinity.common.CommonProxy
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        MinecraftForge.EVENT_BUS.register(new HealthValueGUI());
        MinecraftForge.EVENT_BUS.register(new CthulhuBossBar());
        MinecraftForge.EVENT_BUS.register(new DistortionCover());
        MinecraftForge.EVENT_BUS.register(new HeadHunterVision());
        MinecraftForge.EVENT_BUS.register(new ClientMindControlHandler());
        ParticleInit.init();
    }

    @Override // xol.lostinfinity.common.CommonProxy
    public void playMovingSound(SoundEvent soundIn, SoundCategory categoryIn, IMovingSoundSource.Special special, float volume, float pitch) {
        Minecraft.func_71410_x().func_147118_V().func_147682_a(new UniversalMovingSound(soundIn, categoryIn, special, volume, pitch));
    }

    private void registerKeybinds() {
        armorSetBonus = new KeyBinding("key.lostinfinity.armor_set_bonus.desc", 47, "key.lostinfinity.category");
        ClientRegistry.registerKeyBinding(armorSetBonus);
        itemMode = new KeyBinding("key.lostinfinity.item_mode.desc", 44, "key.lostinfinity.category");
        ClientRegistry.registerKeyBinding(itemMode);
        dismount = new KeyBinding("key.lostinfinity.dismount.desc", 45, "key.lostinfinity.category");
        ClientRegistry.registerKeyBinding(dismount);
    }
}
