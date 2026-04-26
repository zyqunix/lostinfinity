package xol.lostinfinity.common;

import net.minecraft.item.Item;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import xol.lostinfinity.common.special.CommonMindControlHandler;
import xol.lostinfinity.common.special.CommonMinionHandler;
import xol.lostinfinity.gui.GuiHandler;
import xol.lostinfinity.init.SpawnInit;
import xol.lostinfinity.item.classify.IMovingSoundSource;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/CommonProxy.class */
@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(lostinfinity.instance, GuiHandler.getInstance());
    }

    public void init() {
    }

    public void postInit(FMLPostInitializationEvent event) {
        SpawnInit.init();
        CommonMinionHandler.init();
        MinecraftForge.EVENT_BUS.register(new CommonMindControlHandler());
    }

    public void registerItemRenderer(Item item, int meta, String id) {
    }

    public void playMovingSound(SoundEvent soundIn, SoundCategory categoryIn, IMovingSoundSource.Special special, float volume, float pitch) {
    }
}
