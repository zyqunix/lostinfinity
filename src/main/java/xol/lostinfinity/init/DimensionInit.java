package xol.lostinfinity.init;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import xol.lostinfinity.dimension.cartographerrealm.BiomeCartographerRealm;
import xol.lostinfinity.dimension.cartographerrealm.bot.WorldProviderCartographerBot;
import xol.lostinfinity.dimension.cartographerrealm.mid.WorldProviderCartographerMid;
import xol.lostinfinity.dimension.cartographerrealm.top.WorldProviderCartographerTop;
import xol.lostinfinity.dimension.celestialvoid.BiomeCelestialVoid;
import xol.lostinfinity.dimension.celestialvoid.WorldProviderCelestialVoid;
import xol.lostinfinity.dimension.grandmasteroutpost.BiomeGrandmasterOutpost;
import xol.lostinfinity.dimension.grandmasteroutpost.WorldProviderGrandmasterOutpost;
import xol.lostinfinity.dimension.murk.BiomeInfiniteMurk;
import xol.lostinfinity.dimension.murk.WorldProviderInfiniteMurk;
import xol.lostinfinity.dimension.nonexistence.BiomeNonexistence;
import xol.lostinfinity.dimension.nonexistence.WorldProviderNonexistence;
import xol.lostinfinity.dimension.shadowsea.BiomeMoltenSea;
import xol.lostinfinity.dimension.shadowsea.BiomeShadowSea;
import xol.lostinfinity.dimension.shadowsea.WorldProviderShadowSea;
import xol.lostinfinity.dimension.util.DimensionEffectRegistry;
import xol.lostinfinity.util.ConfigurationHandler;
@Mod.EventBusSubscriber
public class DimensionInit {
    public static DimensionType cartographerRealmTop;
    public static DimensionType cartographerRealmMid;
    public static DimensionType cartographerRealmBot;
    public static DimensionType celestialVoid;
    public static DimensionType grandmasterOutpost;
    public static DimensionType nonexistence;
    public static DimensionType infiniteMurk;
    public static DimensionType shadowSea;
    public static final Biome biomeCelestialArena = new BiomeCelestialVoid();
    public static final Biome biomeGrandmasterOutpost = new BiomeGrandmasterOutpost();
    public static final Biome biomeNonexistence = new BiomeNonexistence();
    public static final Biome biomeCartographerRealm = new BiomeCartographerRealm();
    public static final Biome biomeInfiniteMurk = new BiomeInfiniteMurk();
    public static final Biome biomeShadowSea = new BiomeShadowSea();
    public static final Biome biomeMoltenSea = new BiomeMoltenSea();
    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        IForgeRegistry<Biome> registry = event.getRegistry();
        registry.registerAll(new Biome[]{biomeCelestialArena, biomeGrandmasterOutpost, biomeCartographerRealm, biomeNonexistence, biomeShadowSea, biomeMoltenSea, biomeInfiniteMurk});
    }
    public static void dimensionInit() {
        infiniteMurk = DimensionType.register("lostinfinity:infinitemurk", "_infinitemurk", ConfigurationHandler.infinite_murk_id, WorldProviderInfiniteMurk.class, false);
        DimensionManager.registerDimension(ConfigurationHandler.infinite_murk_id, infiniteMurk);
        celestialVoid = DimensionType.register("lostinfinity:celestialvoid", "_celestialvoid", ConfigurationHandler.celestial_void_id, WorldProviderCelestialVoid.class, false);
        DimensionManager.registerDimension(ConfigurationHandler.celestial_void_id, celestialVoid);
        cartographerRealmTop = DimensionType.register("lostinfinity:cartographerrealmtop", "_cartographerrealmtop", ConfigurationHandler.cartographer_realm_top_id, WorldProviderCartographerTop.class, false);
        DimensionManager.registerDimension(ConfigurationHandler.cartographer_realm_top_id, cartographerRealmTop);
        cartographerRealmMid = DimensionType.register("lostinfinity:cartographerrealmmid", "_cartographerrealmmid", ConfigurationHandler.cartographer_realm_mid_id, WorldProviderCartographerMid.class, false);
        DimensionManager.registerDimension(ConfigurationHandler.cartographer_realm_mid_id, cartographerRealmMid);
        cartographerRealmBot = DimensionType.register("lostinfinity:cartographerrealmbot", "_cartographerrealmbot", ConfigurationHandler.cartographer_realm_bot_id, WorldProviderCartographerBot.class, false);
        DimensionManager.registerDimension(ConfigurationHandler.cartographer_realm_bot_id, cartographerRealmBot);
        grandmasterOutpost = DimensionType.register("lostinfinity:grandmasteroutpost", "_grandmasteroutpost", ConfigurationHandler.grandmaster_outpost_id, WorldProviderGrandmasterOutpost.class, false);
        DimensionManager.registerDimension(ConfigurationHandler.grandmaster_outpost_id, grandmasterOutpost);
        nonexistence = DimensionType.register("lostinfinity:nonexistence", "_nonexistence", ConfigurationHandler.nonexistence_id, WorldProviderNonexistence.class, false);
        DimensionManager.registerDimension(ConfigurationHandler.nonexistence_id, nonexistence);
        shadowSea = DimensionType.register("lostinfinity:shadowsea", "_shadowsea", ConfigurationHandler.shadow_sea_id, WorldProviderShadowSea.class, false);
        DimensionManager.registerDimension(ConfigurationHandler.shadow_sea_id, shadowSea);
        DimensionEffectRegistry.registerDimensionEffects();
    }
}
