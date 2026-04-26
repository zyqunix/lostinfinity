package xol.lostinfinity.init;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantShulker;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantSkyworm;
import xol.lostinfinity.mob.entity.misc.EntityDimensionalMerchant;
import xol.lostinfinity.mob.entity.misc.EntitySlimeStrider;
import xol.lostinfinity.mob.entity.murk.EntityCaveTerror;
import xol.lostinfinity.mob.entity.murk.EntityScorpwing;
import xol.lostinfinity.mob.entity.murk.EntitySkyre;
import xol.lostinfinity.mob.entity.murk.EntityTorpedon;
import xol.lostinfinity.mob.entity.murk.EntityWhisper;
import xol.lostinfinity.mob.entity.sea.EntityCrabulon;
import xol.lostinfinity.mob.entity.sea.EntityDoublerang;
import xol.lostinfinity.mob.entity.sea.EntityEelShark;
import xol.lostinfinity.mob.entity.sea.EntityGlowfish;
import xol.lostinfinity.mob.entity.sea.EntityLongfin;
import xol.lostinfinity.mob.entity.sea.EntityOctobrella;
import xol.lostinfinity.mob.entity.sea.EntityRayfish;
import xol.lostinfinity.mob.entity.sea.EntityRibshark;
import xol.lostinfinity.mob.entity.sea.EntityUnderfin;
import xol.lostinfinity.mob.entity.sea.seaserpent.EntitySeaSerpentController;
public class SpawnInit {
    public static void init() {
        addSpawns(doVanillaSpawns());
        addSpawns(doModdedSpawns());
    }
    public static HashSet<SpawnEntry> doVanillaSpawns() {
        HashSet<SpawnEntry> spawns = new HashSet<>();
        Biome[] ocean = setToArray(BiomeDictionary.getBiomes(BiomeDictionary.Type.OCEAN));
        Biome[] mountain = setToArray(BiomeDictionary.getBiomes(BiomeDictionary.Type.MOUNTAIN));
        Biome[] end = setToArray(BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
        spawns.add(new SpawnEntry(EntityDeviantSkyworm.class, 2, 1, 1, EnumCreatureType.MONSTER, mountain));
        spawns.add(new SpawnEntry(EntitySlimeStrider.class, 20, 1, 1, EnumCreatureType.MONSTER, ocean));
        spawns.add(new SpawnEntry(EntityDimensionalMerchant.class, 2, 1, 1, EnumCreatureType.CREATURE, end));
        spawns.add(new SpawnEntry(EntityDeviantShulker.class, 3, 1, 1, EnumCreatureType.MONSTER, end));
        return spawns;
    }
    public static HashSet<SpawnEntry> doModdedSpawns() {
        HashSet<SpawnEntry> spawns = new HashSet<>();
        spawns.add(new SpawnEntry(EntityTorpedon.class, 10, 1, 1, EnumCreatureType.MONSTER, new Biome[]{DimensionInit.biomeInfiniteMurk}));
        spawns.add(new SpawnEntry(EntityScorpwing.class, 10, 1, 1, EnumCreatureType.MONSTER, new Biome[]{DimensionInit.biomeInfiniteMurk}));
        spawns.add(new SpawnEntry(EntityWhisper.class, 10, 1, 1, EnumCreatureType.MONSTER, new Biome[]{DimensionInit.biomeInfiniteMurk}));
        spawns.add(new SpawnEntry(EntityCaveTerror.class, 15, 1, 1, EnumCreatureType.MONSTER, new Biome[]{DimensionInit.biomeInfiniteMurk}));
        spawns.add(new SpawnEntry(EntitySkyre.class, 15, 1, 1, EnumCreatureType.MONSTER, new Biome[]{DimensionInit.biomeInfiniteMurk}));
        spawns.add(new SpawnEntry(EntityDimensionalMerchant.class, 2, 1, 1, EnumCreatureType.CREATURE, new Biome[]{DimensionInit.biomeInfiniteMurk}));
        spawns.add(new SpawnEntry(EntityLongfin.class, 15, 1, 1, EnumCreatureType.MONSTER, new Biome[]{DimensionInit.biomeShadowSea}));
        spawns.add(new SpawnEntry(EntityUnderfin.class, 15, 1, 1, EnumCreatureType.MONSTER, new Biome[]{DimensionInit.biomeShadowSea}));
        spawns.add(new SpawnEntry(EntityRayfish.class, 15, 1, 1, EnumCreatureType.MONSTER, new Biome[]{DimensionInit.biomeShadowSea}));
        spawns.add(new SpawnEntry(EntityEelShark.class, 15, 1, 1, EnumCreatureType.MONSTER, new Biome[]{DimensionInit.biomeShadowSea}));
        spawns.add(new SpawnEntry(EntityCrabulon.class, 1, 1, 1, EnumCreatureType.MONSTER, new Biome[]{DimensionInit.biomeShadowSea}));
        spawns.add(new SpawnEntry(EntityOctobrella.class, 25, 1, 1, EnumCreatureType.MONSTER, new Biome[]{DimensionInit.biomeMoltenSea}));
        spawns.add(new SpawnEntry(EntityGlowfish.class, 25, 1, 1, EnumCreatureType.MONSTER, new Biome[]{DimensionInit.biomeMoltenSea}));
        spawns.add(new SpawnEntry(EntityRibshark.class, 25, 1, 1, EnumCreatureType.MONSTER, new Biome[]{DimensionInit.biomeMoltenSea}));
        spawns.add(new SpawnEntry(EntityDoublerang.class, 25, 1, 1, EnumCreatureType.MONSTER, new Biome[]{DimensionInit.biomeMoltenSea}));
        spawns.add(new SpawnEntry(EntitySeaSerpentController.class, 1, 1, 1, EnumCreatureType.MONSTER, new Biome[]{DimensionInit.biomeMoltenSea}));
        return spawns;
    }
    private static Biome[] setToArray(Set<Biome> biomes) {
        Biome[] biomeArray = new Biome[biomes.size()];
        int i = 0;
        for (Biome b : biomes) {
            int i2 = i;
            i++;
            biomeArray[i2] = b;
        }
        return biomeArray;
    }
    public static void addSpawns(Collection<SpawnEntry> spawns) {
        spawns.forEach(entry -> {
            EntityRegistry.addSpawn(entry.entityClass, entry.weight, entry.minGroupSize, entry.maxGroupSize, entry.creatureType, entry.biomes);
        });
    }
    public static void removeSpawns(Collection<SpawnEntry> spawns) {
        spawns.forEach(entry -> {
            EntityRegistry.removeSpawn(entry.entityClass, entry.creatureType, entry.biomes);
        });
    }
    public static class SpawnEntry {
        public final Class<? extends EntityLiving> entityClass;
        public final int weight;
        public final int minGroupSize;
        public final int maxGroupSize;
        public final EnumCreatureType creatureType;
        public final Biome[] biomes;
        private SpawnEntry(Class<? extends EntityLiving> entityClass, int weight, int minGroupSize, int maxGroupSize, EnumCreatureType creatureType, Biome... biomes) {
            this.entityClass = entityClass;
            this.weight = weight;
            this.minGroupSize = minGroupSize;
            this.maxGroupSize = maxGroupSize;
            this.creatureType = creatureType;
            this.biomes = biomes;
        }
    }
}
