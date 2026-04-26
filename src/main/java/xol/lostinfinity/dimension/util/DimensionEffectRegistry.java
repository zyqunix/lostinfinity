package xol.lostinfinity.dimension.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.BiomeDictionary;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ArmorInit;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.util.player.PlayerManager;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/util/DimensionEffectRegistry.class */
public class DimensionEffectRegistry {
    private static final Map<DimensionType, DimensionEffect> DIMENSION_EFFECTS = new HashMap();

    public static void dimensionEffect(DimensionType type, DimensionEffect effect) {
        DIMENSION_EFFECTS.put(type, effect);
    }

    public static void tickPlayerDimensionEffects(EntityPlayer player) {
        DimensionType type = player.field_70170_p.field_73011_w.func_186058_p();
        DimensionEffect effect = DIMENSION_EFFECTS.getOrDefault(type, null);
        if (effect != null) {
            effect.tickPlayerInDimension(player);
        }
    }

    public static double randomCoordinate(Random rand) {
        return ((-0.5d) + rand.nextDouble()) * 10000.0d;
    }

    public static void registerDimensionEffects() {
        dimensionEffect(DimensionType.OVERWORLD, new DimensionEffect() { // from class: xol.lostinfinity.dimension.util.DimensionEffectRegistry.1
            @Override // xol.lostinfinity.dimension.util.DimensionEffect
            public void tickPlayerInDimension(EntityPlayer player) {
                if (player.field_70170_p.func_180495_p(player.func_180425_c().func_177982_a(0, 2, 0)).func_177230_c().equals(Blocks.field_150353_l)) {
                    ItemStack held = player.func_184614_ca();
                    if (held.func_77973_b().equals(ItemInit.forgeFirePickaxe)) {
                        DimensionActivator.transferEntityWithCoords(player, DimensionInit.nonexistence, 24.0d, 110.0d, 17.0d);
                        return;
                    }
                    return;
                }
                if (player.field_70170_p.func_180495_p(player.func_180425_c().func_177982_a(0, 2, 0)).func_177230_c().equals(Blocks.field_150355_j)) {
                    ItemStack held2 = player.func_184614_ca();
                    if (held2.func_77973_b().equals(ItemInit.seaboundCompass) && player.field_70181_x <= -1.0d && BiomeDictionary.getBiomes(BiomeDictionary.Type.OCEAN).contains(player.field_70170_p.func_180494_b(player.func_180425_c()))) {
                        DimensionActivator.transferEntityWithCoords(player, DimensionInit.shadowSea, DimensionEffectRegistry.randomCoordinate(player.field_70170_p.field_73012_v), 160.0d, DimensionEffectRegistry.randomCoordinate(player.field_70170_p.field_73012_v));
                    }
                }
            }
        });
        dimensionEffect(DimensionInit.celestialVoid, new DimensionEffect() { // from class: xol.lostinfinity.dimension.util.DimensionEffectRegistry.2
            @Override // xol.lostinfinity.dimension.util.DimensionEffect
            public void tickPlayerInDimension(EntityPlayer player) {
                if (player.field_70165_t < 700.0d && player.field_70165_t > -50.0d) {
                    ItemStack helm = player.field_71071_by.func_70301_a(39);
                    if (helm.func_77973_b() != ArmorInit.celestialHeadguard && !PlayerManager.isWearingAnySet(player) && !player.field_70170_p.field_72995_K) {
                        DimensionActivator.transferEntity(player, DimensionType.OVERWORLD);
                        player.func_145747_a(new TextComponentString(TextFmt.Aqua + "Challengers must wear a Celestial Headguard."));
                    }
                }
                if (!player.field_70170_p.field_72995_K && !player.func_70644_a(Potion.func_188412_a(23))) {
                    player.func_70690_d(new PotionEffect(Potion.func_188412_a(23), 601, 0, true, false));
                }
                if (!player.field_70170_p.field_72995_K && !player.func_184812_l_() && player.field_71075_bZ.field_75100_b) {
                    player.func_70606_j(0.5f);
                    if (player.field_70173_aa % 40 == 0) {
                        player.func_145747_a(new TextComponentString(TextFmt.Dark_Purple + "Flight damages your lungs due to the lack of atmosphere."));
                    }
                }
            }
        });
        dimensionEffect(DimensionInit.nonexistence, new DimensionEffect() { // from class: xol.lostinfinity.dimension.util.DimensionEffectRegistry.3
            @Override // xol.lostinfinity.dimension.util.DimensionEffect
            public void tickPlayerInDimension(EntityPlayer player) {
                if (player.func_70644_a(MobEffects.field_76439_r) && !player.func_184812_l_()) {
                    if (player.func_110143_aJ() > 0.0f) {
                        player.func_70606_j(0.5f);
                    }
                    if (player.field_70173_aa % 30 == 0 && !player.field_70170_p.field_72995_K) {
                        player.func_145747_a(new TextComponentString(TextFmt.Red + "Nightvision causes your eyes to burn from fumes in the air. You feel severely weakened."));
                    }
                }
                boolean hasLostArmor = PlayerManager.isWearingAnySet(player);
                ItemStack helm = player.field_71071_by.func_70301_a(39);
                if (!helm.func_77973_b().equals(ArmorInit.filtrationMask) && !hasLostArmor && !player.field_70170_p.field_72995_K && !player.func_184812_l_() && player.func_110143_aJ() > 0.0f) {
                    player.func_70606_j(0.0f);
                    player.func_145747_a(new TextComponentString(TextFmt.Yellow + "Toxic fumes quickly fill your airways."));
                }
            }
        });
        dimensionEffect(DimensionInit.cartographerRealmMid, new DimensionEffect() { // from class: xol.lostinfinity.dimension.util.DimensionEffectRegistry.4
            @Override // xol.lostinfinity.dimension.util.DimensionEffect
            public void tickPlayerInDimension(EntityPlayer player) {
                if (!player.field_70170_p.field_72995_K && player.field_70173_aa % 80 == 0 && !player.func_184812_l_() && player.func_184614_ca().func_77973_b() != ItemInit.synchronizer && player.func_184614_ca().func_77973_b() != ItemInit.synchronizer && player.func_184614_ca().func_77973_b() != ItemInit.advancedSynchronizer && player.func_184614_ca().func_77973_b() != ItemInit.advancedSynchronizer) {
                    DimensionEffectRegistry.dimensionalTear(player);
                }
            }
        });
        dimensionEffect(DimensionInit.cartographerRealmBot, new DimensionEffect() { // from class: xol.lostinfinity.dimension.util.DimensionEffectRegistry.5
            @Override // xol.lostinfinity.dimension.util.DimensionEffect
            public void tickPlayerInDimension(EntityPlayer player) {
                if (!player.field_70170_p.field_72995_K && player.field_70173_aa % 40 == 0 && !player.func_184812_l_() && player.func_184614_ca().func_77973_b() != ItemInit.advancedSynchronizer && player.func_184614_ca().func_77973_b() != ItemInit.advancedSynchronizer) {
                    DimensionEffectRegistry.dimensionalTear(player);
                }
            }
        });
        dimensionEffect(DimensionInit.grandmasterOutpost, new DimensionEffect() { // from class: xol.lostinfinity.dimension.util.DimensionEffectRegistry.6
            @Override // xol.lostinfinity.dimension.util.DimensionEffect
            public void tickPlayerInDimension(EntityPlayer player) {
                if (player.field_70173_aa % 20 == 0) {
                    player.func_71024_bL().func_75122_a(20, 20.0f);
                }
            }
        });
        dimensionEffect(DimensionInit.infiniteMurk, new DimensionEffect() { // from class: xol.lostinfinity.dimension.util.DimensionEffectRegistry.7
            @Override // xol.lostinfinity.dimension.util.DimensionEffect
            public void tickPlayerInDimension(EntityPlayer player) {
                if (player.field_70170_p.field_72995_K && player == Minecraft.func_71410_x().field_71439_g) {
                    for (int i = 0; i < 5; i++) {
                        Random random = player.field_70170_p.field_73012_v;
                        double xpos = (player.field_70165_t - 75.0d) + (random.nextDouble() * 150.0d);
                        double zpos = (player.field_70161_v - 75.0d) + (random.nextDouble() * 150.0d);
                        double ypos = ((double) (player.field_70170_p.func_189649_b(MathHelper.func_76128_c(xpos), MathHelper.func_76128_c(zpos)) - 2)) + (random.nextDouble() * 8.0d);
                        player.field_70170_p.func_175688_a(ParticleInit.MURK, xpos, ypos, zpos, 0.0d, 0.0d, 0.0d, new int[0]);
                    }
                }
            }
        });
        dimensionEffect(DimensionInit.shadowSea, new DimensionEffect() { // from class: xol.lostinfinity.dimension.util.DimensionEffectRegistry.8
            @Override // xol.lostinfinity.dimension.util.DimensionEffect
            public void tickPlayerInDimension(EntityPlayer player) {
                if (player.field_70170_p.field_72995_K && player == Minecraft.func_71410_x().field_71439_g) {
                    for (int i = 0; i < 5; i++) {
                        Random random = player.field_70170_p.field_73012_v;
                        double xpos = (player.field_70165_t - 75.0d) + (random.nextDouble() * 150.0d);
                        double zpos = (player.field_70161_v - 75.0d) + (random.nextDouble() * 150.0d);
                        double ypos = (player.field_70163_u - 6.0d) + (random.nextDouble() * 12.0d);
                        player.field_70170_p.func_175688_a(random.nextBoolean() ? ParticleInit.LARGE_BUBBLE : ParticleInit.LARGE_BUBBLE_PURPLE, xpos, ypos, zpos, 0.0d, 0.0d, 0.0d, new int[0]);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dimensionalTear(EntityPlayer player) {
        int level = 0;
        if (player.func_70644_a(PotionInit.DIMENSIONAL_TEAR)) {
            level = player.func_70660_b(PotionInit.DIMENSIONAL_TEAR).func_76458_c() + 1;
            player.func_184589_d(PotionInit.DIMENSIONAL_TEAR);
        }
        player.func_70690_d(new PotionEffect(PotionInit.DIMENSIONAL_TEAR, 100, level));
    }
}
