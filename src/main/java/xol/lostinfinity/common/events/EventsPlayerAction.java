package xol.lostinfinity.common.events;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandKill;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.server.FMLServerHandler;
import xol.lostinfinity.block.basic.BlockBasicFluid;
import xol.lostinfinity.block.basic.ITetherable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.util.DimensionActivator;
import xol.lostinfinity.dimension.util.DimensionEffectRegistry;
import xol.lostinfinity.dimension.util.DimensionNoBuild;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.activate.ItemRiftWalker;
import xol.lostinfinity.item.armor.ItemLostArmor;
import xol.lostinfinity.item.classify.IChargeItem;
import xol.lostinfinity.item.classify.IDimensionSwitch;
import xol.lostinfinity.item.classify.IHeldTick;
import xol.lostinfinity.item.classify.IHotbarTick;
import xol.lostinfinity.item.classify.IMoveTick;
import xol.lostinfinity.item.classify.IPotionReactive;
import xol.lostinfinity.item.classify.ItemSoulbound;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.mob.entity.misc.EntitySkybooster;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;
import xol.lostinfinity.util.damagesource.DeathMessage;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.player.PlayerManager;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/events/EventsPlayerAction.class */
public class EventsPlayerAction implements IMaxAttack {
    private final Map<EntityPlayer, ItemStack> clientMainhandItem = new ConcurrentHashMap();
    private final Map<EntityPlayer, ItemStack> serverMainhandItem = new ConcurrentHashMap();
    private final Map<EntityPlayer, ItemStack> clientOffhandItem = new ConcurrentHashMap();
    private final Map<EntityPlayer, ItemStack> serverOffhandItem = new ConcurrentHashMap();

    private EntityPlayer getPlayerByUUID(World world, UUID uuid) {
        if (uuid == null) {
            return null;
        }
        return world.field_72995_K ? FMLServerHandler.instance().getServer().func_184103_al().func_177451_a(uuid) : world.func_73046_m().func_184103_al().func_177451_a(uuid);
    }

    @SubscribeEvent
    public void onDimensionTransfer(PlayerEvent.PlayerChangedDimensionEvent event) {
        ItemSoulbound.removeSoulBound(event.player);
        if ((event.fromDim == DimensionInit.celestialVoid.func_186068_a() || event.fromDim == DimensionInit.grandmasterOutpost.func_186068_a()) && !event.player.field_70170_p.field_72995_K) {
            event.player.func_184589_d(Potion.func_188412_a(23));
        }
        ItemStack stack = event.player.func_184614_ca();
        IDimensionSwitch iDimensionSwitchFunc_77973_b = stack.func_77973_b();
        if (iDimensionSwitchFunc_77973_b instanceof IDimensionSwitch) {
            iDimensionSwitchFunc_77973_b.onDimensionSwitch(event.player, stack);
        }
    }

    @SubscribeEvent
    public void onPlayerCraft(PlayerEvent.ItemCraftedEvent event) {
        EntityPlayer crafter = event.player;
        if (!crafter.field_70170_p.field_72995_K && event.crafting.func_77973_b() == ItemInit.puzzleKey) {
            crafter.func_145747_a(new TextComponentString(TextFmt.Dark_Aqua + "The Echo: I see everywhere " + crafter.func_70005_c_() + ". Are you looking to play" + TextFmt.Reset + TextFmt.Gold + " one last game?"));
        }
    }

    @SubscribeEvent
    public void onPlayerHurt(LivingHurtEvent event) {
        if (!event.getEntity().func_130014_f_().field_72995_K && (event.getEntity() instanceof EntityPlayer)) {
            if (event.getSource() == DamageSource.field_76380_i && event.getEntity().field_70170_p.field_73011_w.func_186058_p() == DimensionInit.nonexistence) {
                BlockPos teleport = GalaxyCoordinates.galaxyDungeonEntry();
                event.getEntity().func_70634_a(teleport.func_177958_n(), teleport.func_177956_o(), teleport.func_177952_p());
                event.getEntity().field_70143_R = -1.0f;
                event.setCanceled(true);
            }
            EntityPlayer owner = event.getEntity();
            if (PlayerManager.isWearingAnySet(owner)) {
                event.setCanceled(true);
                return;
            }
            ItemStack vessel = null;
            int i = 0;
            while (true) {
                if (i > 8) {
                    break;
                }
                ItemStack stack = owner.field_71071_by.func_70301_a(i);
                if (!stack.func_77973_b().equals(ItemInit.lifeVessel) || !stack.func_77942_o() || getPlayerByUUID(event.getEntity().field_70170_p, stack.func_77978_p().func_186857_a("target")) == null) {
                    i++;
                } else {
                    vessel = stack;
                    break;
                }
            }
            if (vessel != null) {
                EntityPlayerMP entityPlayerMPFunc_177451_a = owner.field_70170_p.func_73046_m().func_184103_al().func_177451_a(vessel.func_77978_p().func_186857_a("target"));
                if (entityPlayerMPFunc_177451_a != null) {
                    if (!((EntityPlayer) entityPlayerMPFunc_177451_a).field_70128_L) {
                        entityPlayerMPFunc_177451_a.func_130011_c(owner);
                        entityPlayerMPFunc_177451_a.func_184185_a(SoundEvents.field_187718_dS, 2.0f, 3.0f);
                        entityPlayerMPFunc_177451_a.func_70606_j(entityPlayerMPFunc_177451_a.func_110143_aJ() - event.getAmount());
                        owner.func_184185_a(SoundEvents.field_187604_bf, 1.0f, 1.0f);
                        if (event.getSource().func_76347_k()) {
                            entityPlayerMPFunc_177451_a.func_70015_d(5);
                        }
                        if (entityPlayerMPFunc_177451_a.func_110143_aJ() <= 0.0f) {
                            vessel.func_77978_p().func_82580_o("targetMost");
                            vessel.func_77978_p().func_82580_o("targetLeast");
                            vessel.func_77978_p().func_82580_o("targetName");
                            owner.func_184185_a(SoundEvents.field_187561_bM, 1.0f, 1.0f);
                            entityPlayerMPFunc_177451_a.func_70066_B();
                            entityPlayerMPFunc_177451_a.func_70645_a(event.getSource());
                            entityPlayerMPFunc_177451_a.func_70106_y();
                        }
                        event.setCanceled(true);
                        return;
                    }
                    return;
                }
                vessel.func_77978_p().func_82580_o("targetMost");
                vessel.func_77978_p().func_82580_o("targetLeast");
            }
        }
    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        EntityPlayer owner;
        EntityPlayerMP entityPlayerMPFunc_177451_a;
        if ((event.getEntity() instanceof EntityPlayer) && (owner = event.getEntity()) != null) {
            ItemStack vessel = null;
            int i = 0;
            while (true) {
                if (i > 8) {
                    break;
                }
                ItemStack stack = owner.field_71071_by.func_70301_a(i);
                if (!stack.func_77973_b().equals(ItemInit.lifeVessel) || !stack.func_77942_o() || owner.field_70170_p.func_73046_m().func_184103_al().func_177451_a(stack.func_77978_p().func_186857_a("target")) == null) {
                    i++;
                } else {
                    vessel = stack;
                    break;
                }
            }
            if (vessel != null && (entityPlayerMPFunc_177451_a = owner.field_70170_p.func_73046_m().func_184103_al().func_177451_a(vessel.func_77978_p().func_186857_a("target"))) != null && !((EntityPlayer) entityPlayerMPFunc_177451_a).field_70128_L) {
                vessel.func_77978_p().func_82580_o("targetMost");
                vessel.func_77978_p().func_82580_o("targetLeast");
                vessel.func_77978_p().func_82580_o("targetName");
                owner.func_184185_a(SoundEvents.field_187561_bM, 1.0f, 1.0f);
                IMaxAttack.dealMaxHealth(owner, entityPlayerMPFunc_177451_a, 1);
                owner.func_70606_j(owner.func_110138_aP());
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event) {
        if (event.getTarget() != null && event.getTarget().field_72313_a == RayTraceResult.Type.BLOCK) {
            IBlockState block = event.getWorld().func_180495_p(event.getTarget().func_178782_a());
            if (block.func_177230_c() instanceof BlockBasicFluid) {
                block.func_177230_c().handleFill(event, block);
            }
        }
    }

    @SubscribeEvent
    public void rightClickBlock(PlayerInteractEvent event) {
        ItemStack stack = event.getItemStack();
        if (event.getEntityPlayer().func_70644_a(PotionInit.SPECTRAL) && event.isCancelable() && !stack.func_77973_b().equals(ItemInit.riftWalker)) {
            event.setCanceled(true);
        }
        if (event.getEntityPlayer().func_70644_a(PotionInit.PHASED) && event.isCancelable()) {
            event.setCanceled(true);
        }
        if ((((stack.func_77973_b() instanceof ItemBucket) && !stack.func_77973_b().equals(Items.field_151133_ar)) || (stack.func_77973_b() instanceof UniversalBucket)) && (event.getWorld().func_180494_b(event.getPos()) instanceof DimensionNoBuild)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void soulboundClear(LivingDeathEvent event) {
        if (!event.isCanceled() && (event.getEntity() instanceof EntityPlayer)) {
            ItemSoulbound.removeSoulBound(event.getEntity());
        }
    }

    @SubscribeEvent
    public void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            World world = event.getEntity().field_70170_p;
            EntityPlayer player = event.getEntity();
            if (!world.field_72995_K) {
                if (world.func_180495_p(player.func_180425_c().func_177977_b()).func_177230_c().equals(BlockInit.launchPad)) {
                    if (world.field_73011_w.func_186058_p() == DimensionType.OVERWORLD) {
                        player.field_70181_x = 0.01d;
                        player.field_70133_I = true;
                        EntitySkybooster boost = new EntitySkybooster(world);
                        boost.func_70107_b(player.field_70165_t, player.field_70163_u, player.field_70161_v);
                        boost.setOwner(player);
                        world.func_72838_d(boost);
                        return;
                    }
                    DimensionActivator.transferEntity(player, DimensionType.OVERWORLD);
                    return;
                }
                if (world.func_180495_p(player.func_180425_c().func_177977_b()).func_177230_c().equals(BlockInit.fabricReformulator) || world.func_180495_p(player.func_180425_c().func_177977_b().func_177977_b()).func_177230_c().equals(BlockInit.fabricReformulator)) {
                    if (world.field_73011_w.func_186058_p() == DimensionType.OVERWORLD) {
                        DimensionActivator.transferEntityWithCoords(player, DimensionInit.infiniteMurk, player.field_70165_t, player.field_70163_u, player.field_70161_v);
                    } else {
                        DimensionActivator.transferEntityWithCoords(player, DimensionType.OVERWORLD, player.field_70165_t, player.field_70163_u, player.field_70161_v);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onPotionExpire(PotionEvent.PotionExpiryEvent event) {
        if (event.getEntity() != null && (event.getEntity() instanceof EntityPlayer)) {
            EntityPlayer player = event.getEntity();
            if (event.getPotionEffect().func_188419_a().equals(PotionInit.SPECTRAL)) {
                ItemRiftWalker.endEffect(player);
            }
        }
        if (event.getPotionEffect().func_188419_a().equals(PotionInit.SPONTANEOUS_COMBUSTION) && !event.getEntity().field_70170_p.field_72995_K) {
            int level = event.getEntityLiving().func_70660_b(PotionInit.SPONTANEOUS_COMBUSTION).func_76458_c();
            IMaxAttack.dealPotionDamage(event.getEntityLiving(), event.getEntityLiving().func_110138_aP() * (0.3f + (0.15f * level)));
            if (event.getEntityLiving() instanceof EntityMultipleLives) {
                EntityMultipleLives multiEntity = event.getEntityLiving();
                multiEntity.takeawayNumLives(1 + MathHelper.func_76141_d(level / 2));
            }
            EntityLivingBase living = event.getEntityLiving();
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.EXPLOSION).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(living.field_70170_p, config1, living.field_70165_t, living.field_70163_u, living.field_70161_v);
            event.getEntityLiving().field_70170_p.func_184133_a((EntityPlayer) null, event.getEntityLiving().func_180425_c(), SoundInit.GENERIC_EXPLOSION, SoundCategory.PLAYERS, 1.0f, 1.0f);
        }
    }

    @SubscribeEvent
    public void onCommandEvent(CommandEvent event) {
        EntityPlayerMP entityPlayerMPFunc_177451_a;
        if (event.getCommand() instanceof CommandKill) {
            String sender = "";
            if (event.getParameters().length == 0) {
                sender = event.getSender().func_70005_c_();
            } else if (event.getParameters().length == 1) {
                sender = event.getParameters()[0];
            }
            EntityPlayerMP entityPlayerMPFunc_152612_a = event.getSender().func_130014_f_().func_73046_m().func_184103_al().func_152612_a(sender);
            if (entityPlayerMPFunc_152612_a != null) {
                ItemStack vessel = null;
                int i = 0;
                while (true) {
                    if (i > 8) {
                        break;
                    }
                    ItemStack stack = ((EntityPlayer) entityPlayerMPFunc_152612_a).field_71071_by.func_70301_a(i);
                    if (!stack.func_77973_b().equals(ItemInit.lifeVessel) || !stack.func_77942_o() || getPlayerByUUID(event.getSender().func_130014_f_(), stack.func_77978_p().func_186857_a("target")) == null) {
                        i++;
                    } else {
                        vessel = stack;
                        break;
                    }
                }
                if (vessel != null && (entityPlayerMPFunc_177451_a = event.getSender().func_130014_f_().func_73046_m().func_184103_al().func_177451_a(vessel.func_77978_p().func_186857_a("target"))) != null && !((EntityPlayer) entityPlayerMPFunc_177451_a).field_70128_L) {
                    vessel.func_77978_p().func_82580_o("targetMost");
                    vessel.func_77978_p().func_82580_o("targetLeast");
                    vessel.func_77978_p().func_82580_o("targetName");
                    entityPlayerMPFunc_152612_a.func_184185_a(SoundEvents.field_187561_bM, 1.0f, 1.0f);
                    IMaxAttack.dealMaxHealth(entityPlayerMPFunc_152612_a, entityPlayerMPFunc_177451_a, 1);
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public void onHarvestBlock(BlockEvent.HarvestDropsEvent event) {
        Item held;
        Item held2;
        Item held3;
        Item held4;
        Item held5;
        if (event.getState().func_177230_c() == Blocks.field_150482_ag) {
            if (event.getHarvester() != null && (held5 = event.getHarvester().func_184586_b(event.getHarvester().func_184600_cs()).func_77973_b()) != null && held5 == ItemInit.celestialMinersPickaxe && event.getWorld().field_73012_v.nextInt(25) == 1) {
                event.getDrops().add(new ItemStack(ItemInit.celestialDiamond));
                return;
            }
            return;
        }
        if (event.getState().func_177230_c() == Blocks.field_150412_bA) {
            if (event.getHarvester() != null && (held4 = event.getHarvester().func_184586_b(event.getHarvester().func_184600_cs()).func_77973_b()) != null && held4 == ItemInit.celestialMinersPickaxe && event.getWorld().field_73012_v.nextInt(15) == 1) {
                event.getDrops().add(new ItemStack(ItemInit.celestialEmerald));
                return;
            }
            return;
        }
        if (event.getState().func_177230_c() == Blocks.field_150450_ax || event.getState().func_177230_c() == Blocks.field_150439_ay) {
            if (event.getHarvester() != null && (held = event.getHarvester().func_184586_b(event.getHarvester().func_184600_cs()).func_77973_b()) != null && held == ItemInit.celestialMinersPickaxe && event.getWorld().field_73012_v.nextInt(35) == 1) {
                event.getDrops().add(new ItemStack(ItemInit.celestialRedstone));
                return;
            }
            return;
        }
        if (event.getState().func_177230_c() == Blocks.field_150449_bY) {
            if (event.getHarvester() != null && (held3 = event.getHarvester().func_184586_b(event.getHarvester().func_184600_cs()).func_77973_b()) != null && held3 == ItemInit.celestialMinersPickaxe && event.getWorld().field_73012_v.nextInt(35) == 1) {
                event.getDrops().add(new ItemStack(ItemInit.celestialQuartz));
                return;
            }
            return;
        }
        if (event.getState().func_177230_c() == Blocks.field_150366_p && event.getHarvester() != null && (held2 = event.getHarvester().func_184586_b(event.getHarvester().func_184600_cs()).func_77973_b()) != null && held2 == ItemInit.celestialMinersPickaxe && event.getWorld().field_73012_v.nextInt(25) == 1) {
            event.getDrops().add(new ItemStack(ItemInit.celestialIron));
        }
    }

    @SubscribeEvent
    public void onPlayerPotionAdd(PotionEvent.PotionAddedEvent event) {
        if (!(event.getEntityLiving() instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = event.getEntityLiving();
        if (player.field_70170_p.field_72995_K) {
            return;
        }
        ItemStack mainItem = player.func_184586_b(EnumHand.MAIN_HAND);
        if (mainItem.func_77973_b() instanceof IPotionReactive) {
            mainItem.func_77973_b().potionAddReaction(player, mainItem, EnumHand.MAIN_HAND, event.getPotionEffect(), event.getOldPotionEffect());
        }
        ItemStack offItem = player.func_184586_b(EnumHand.OFF_HAND);
        if (offItem.func_77973_b() instanceof IPotionReactive) {
            offItem.func_77973_b().potionAddReaction(player, offItem, EnumHand.OFF_HAND, event.getPotionEffect(), event.getOldPotionEffect());
        }
    }

    @SubscribeEvent
    public void onPlayerPotionRemove(PotionEvent.PotionRemoveEvent event) {
        if (!(event.getEntityLiving() instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = event.getEntityLiving();
        if (player.field_70170_p.field_72995_K) {
            return;
        }
        ItemStack mainItem = player.func_184586_b(EnumHand.MAIN_HAND);
        if (mainItem.func_77973_b() instanceof IPotionReactive) {
            mainItem.func_77973_b().potionRemoveReaction(player, mainItem, EnumHand.MAIN_HAND, event.getPotion(), event.getPotionEffect());
        }
        ItemStack offItem = player.func_184586_b(EnumHand.OFF_HAND);
        if (offItem.func_77973_b() instanceof IPotionReactive) {
            offItem.func_77973_b().potionRemoveReaction(player, offItem, EnumHand.OFF_HAND, event.getPotion(), event.getPotionEffect());
        }
    }

    @SubscribeEvent
    public void onTickEvent(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            boolean enableArmor = PlayerManager.isWearingAnySet(event.player) && !event.player.func_70644_a(PotionInit.NULLIFIED);
            ItemLostArmor.handleStandardArmorBonus(event.player, enableArmor);
            ItemStack mainHand = event.player.func_184614_ca();
            ItemStack offhand = event.player.func_184592_cb();
            processIHeldTick(event.player, EnumHand.MAIN_HAND, mainHand);
            processIHeldTick(event.player, EnumHand.OFF_HAND, offhand);
            for (int i = 0; i <= 8; i++) {
                ItemStack stack = event.player.field_71071_by.func_70301_a(i);
                if (stack.func_77973_b() instanceof IHotbarTick) {
                    IHotbarTick hotbarItem = stack.func_77973_b();
                    hotbarItem.hotbarTick(event.player, i, stack);
                }
            }
            if (!event.player.field_70170_p.field_72995_K && mainHand.func_77942_o() && mainHand.func_77978_p().func_74764_b("InfinityMiraged")) {
                int mirageTime = mainHand.func_77978_p().func_74762_e("InfinityMiraged");
                int ticksExisted = event.player.field_70173_aa;
                if (Math.abs(mirageTime - ticksExisted) > 300) {
                    event.player.func_184611_a(EnumHand.MAIN_HAND, ItemStack.field_190927_a);
                    event.player.func_145747_a(new TextComponentString(TextFmt.Aqua + "The miraged item vanishes into thin air..."));
                    event.player.field_70170_p.func_184133_a((EntityPlayer) null, event.player.func_180425_c(), SoundInit.MAGIC_WEAPON_13, SoundCategory.PLAYERS, 1.0f, 0.8f + (event.player.field_70170_p.field_73012_v.nextFloat() * 0.4f));
                }
            }
            if (!event.player.field_70170_p.field_72995_K && offhand.func_77942_o() && offhand.func_77978_p().func_74764_b("InfinityMiraged")) {
                int mirageTime2 = offhand.func_77978_p().func_74762_e("InfinityMiraged");
                int ticksExisted2 = event.player.field_70173_aa;
                if (Math.abs(mirageTime2 - ticksExisted2) > 300) {
                    event.player.func_184611_a(EnumHand.OFF_HAND, ItemStack.field_190927_a);
                    event.player.func_145747_a(new TextComponentString(TextFmt.Aqua + "The miraged item vanishes into thin air..."));
                    event.player.field_70170_p.func_184133_a((EntityPlayer) null, event.player.func_180425_c(), SoundInit.MAGIC_WEAPON_13, SoundCategory.PLAYERS, 1.0f, 0.8f + (event.player.field_70170_p.field_73012_v.nextFloat() * 0.4f));
                }
            }
            DimensionEffectRegistry.tickPlayerDimensionEffects(event.player);
            if (event.player.func_70644_a(PotionInit.DIMENSIONAL_TEAR) && !event.player.field_70170_p.field_72995_K && event.player.field_70173_aa % 30 == 0) {
                if (event.player.func_184614_ca().func_77973_b() == ItemInit.synchronizer || event.player.func_184592_cb().func_77973_b() == ItemInit.synchronizer || event.player.func_184614_ca().func_77973_b() == ItemInit.advancedSynchronizer || event.player.func_184592_cb().func_77973_b() == ItemInit.advancedSynchronizer) {
                    event.player.func_184589_d(PotionInit.DIMENSIONAL_TEAR);
                } else {
                    int level = event.player.func_70660_b(PotionInit.DIMENSIONAL_TEAR).func_76458_c() + 1;
                    float damage = (level * event.player.func_110138_aP()) / 20.0f;
                    IMaxAttack.dealPotionDamage(event.player, damage);
                    if (event.player.func_110143_aJ() <= 0.0f) {
                        DeathMessage.broadcastDeathMessage(event.player.func_184102_h(), TextFmt.Red + event.player.func_70005_c_() + " was split interdimensionally.");
                    } else {
                        float speedMulti = level / 2;
                        event.player.func_70024_g(getRandomFloat(event.player, speedMulti), getRandomFloat(event.player, speedMulti), getRandomFloat(event.player, speedMulti));
                        event.player.field_70133_I = true;
                    }
                }
            }
            if (event.player.func_70644_a(PotionInit.SUPERSONIC) && !event.player.field_70170_p.field_72995_K) {
                boolean found = false;
                if (mainHand.func_77973_b() instanceof IMoveTick) {
                    IMoveTick tickable = mainHand.func_77973_b();
                    tickable.moveTick(event.player, EnumHand.MAIN_HAND, mainHand);
                    found = true;
                }
                if (offhand.func_77973_b() instanceof IMoveTick) {
                    IMoveTick tickable2 = offhand.func_77973_b();
                    tickable2.moveTick(event.player, EnumHand.OFF_HAND, mainHand);
                    found = true;
                }
                if (!found) {
                    event.player.func_184596_c(PotionInit.SUPERSONIC);
                }
            }
            if (event.player.func_70644_a(PotionInit.RAMPAGING) && !event.player.field_70170_p.field_72995_K && event.player.field_70173_aa % 10 == 0) {
                int level2 = event.player.func_70660_b(PotionInit.RAMPAGING).func_76458_c();
                int particleLevel = 16 + Math.floorDiv(level2, 4);
                int extra = level2 % 4;
                CustomParticleConfig config1 = new CustomParticleConfig();
                CustomParticleConfig.Instance instance = config1.createInstance();
                switch (Math.min(18, particleLevel)) {
                    case 16:
                        instance.setParticle(ParticleInit.FLAME_SMALL).setCount(1 + extra);
                        break;
                    case 17:
                        instance.setParticle(ParticleInit.FLAME_MEDIUM).setCount(1 + (extra * 2));
                        break;
                    case 18:
                        instance.setParticle(ParticleInit.FLAME_LARGE).setCount(1 + (extra * 4));
                        break;
                }
                instance.setSpread(1.0d, 0.0d, 1.0d).setSpeed(0.3d, 0.0d, 0.3d).setVelSpread(1.0d, 0.0d, 1.0d).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(event.player.field_70170_p, config1, event.player.field_70165_t, event.player.field_70163_u, event.player.field_70161_v);
            }
            if (event.player.func_70644_a(PotionInit.SPECTRAL) && !event.player.field_70170_p.field_72995_K && event.player.field_70173_aa % 10 == 0) {
                CustomParticleConfig config12 = new CustomParticleConfig();
                config12.createInstance().setParticle(ParticleInit.SPECTRAL).setSpread(1.0d, 1.0d, 1.0d).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(event.player.field_70170_p, config12, event.player.field_70165_t, event.player.field_70163_u, event.player.field_70161_v);
            }
            if (event.player.func_70644_a(PotionInit.TERRIFIED) && !event.player.field_70170_p.field_72995_K) {
                for (EntityLivingBase near : event.player.field_70170_p.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(event.player.func_180425_c()).func_186662_g(8.0d))) {
                    if (near.func_70644_a(PotionInit.FEARED)) {
                        Vec3d dir = event.player.func_174791_d().func_178788_d(near.func_174791_d()).func_72432_b();
                        event.player.func_70024_g(dir.field_72450_a / 10.0d, (dir.field_72448_b / 10.0d) + 0.1d, dir.field_72449_c / 10.0d);
                        event.player.field_70133_I = true;
                    }
                }
            }
            if (event.player.func_70644_a(PotionInit.CHARGING) && !event.player.field_70170_p.field_72995_K) {
                int duration = event.player.func_70660_b(PotionInit.CHARGING).func_76459_b();
                if (duration <= 1) {
                    ItemStack held = event.player.func_184614_ca();
                    if (held.func_77973_b() instanceof IChargeItem) {
                        IChargeItem charger = held.func_77973_b();
                        charger.endChargeEffect(held, event.player);
                        event.player.func_184589_d(PotionInit.CHARGING);
                    }
                }
            }
            if (event.player.func_70644_a(PotionInit.TETHERED) && !event.player.field_70170_p.field_72995_K) {
                tether(event);
            }
        }
    }

    private void tether(TickEvent.PlayerTickEvent event) {
        BlockPos logPos = getNearestLog(event, 7);
        if (logPos != null) {
            double x = event.player.field_70165_t;
            double y = event.player.field_70163_u;
            double z = event.player.field_70161_v;
            double distToLog = logPos.func_177954_c(x, y, z);
            if (distToLog > 0.0d) {
                double diffX = ((double) logPos.func_177958_n()) - x;
                double diffY = ((double) logPos.func_177956_o()) - y;
                double diffZ = ((double) logPos.func_177952_p()) - z;
                Vec3d dir = new Vec3d(diffX, diffY, diffZ);
                dir.func_72432_b();
                event.player.field_70159_w = dir.field_72450_a * 0.4d;
                event.player.field_70181_x = dir.field_72448_b * 0.4d;
                event.player.field_70179_y = dir.field_72449_c * 0.4d;
                event.player.field_70133_I = true;
            }
        }
    }

    private BlockPos getNearestLog(TickEvent.PlayerTickEvent event, int radius) {
        double minDist = 99.0d;
        BlockPos nearest = null;
        BlockPos playerPos = event.player.func_180425_c();
        for (int x = playerPos.func_177958_n() - radius; x <= playerPos.func_177958_n() + radius; x++) {
            for (int y = playerPos.func_177956_o() - radius; y <= playerPos.func_177956_o() + radius; y++) {
                for (int z = playerPos.func_177952_p() - radius; z <= playerPos.func_177952_p() + radius; z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    IBlockState state = event.player.field_70170_p.func_180495_p(pos);
                    if (state.func_177230_c() instanceof ITetherable) {
                        double dist = pos.func_185332_f((int) event.player.field_70165_t, (int) event.player.field_70163_u, (int) event.player.field_70161_v);
                        if (dist <= minDist) {
                            minDist = dist;
                            nearest = new BlockPos(pos);
                        }
                    }
                }
            }
        }
        return nearest;
    }

    private void processIHeldTick(EntityPlayer player, EnumHand hand, ItemStack curr) {
        Map<EntityPlayer, ItemStack> map = hand == EnumHand.MAIN_HAND ? player.field_70170_p.field_72995_K ? this.clientMainhandItem : this.serverMainhandItem : player.field_70170_p.field_72995_K ? this.clientOffhandItem : this.serverOffhandItem;
        ItemStack prev = map.get(player);
        if (!(curr.func_77973_b() instanceof IHeldTick)) {
            if (prev != null && (prev.func_77973_b() instanceof IHeldTick)) {
                IHeldTick oldTickable = prev.func_77973_b();
                oldTickable.stopHolding(player, hand, prev);
                map.remove(player);
                return;
            }
            return;
        }
        IHeldTick tickable = curr.func_77973_b();
        if (curr != prev) {
            tickable.startHolding(player, hand, curr);
            if (prev != null && (prev.func_77973_b() instanceof IHeldTick)) {
                IHeldTick oldTickable2 = prev.func_77973_b();
                oldTickable2.stopHolding(player, hand, prev);
            }
            map.put(player, curr);
        }
        tickable.heldTick(player, hand, curr);
    }

    private float getRandomFloat(EntityPlayer player, float mutli) {
        return ((-0.5f) + player.field_70170_p.field_73012_v.nextFloat()) * mutli;
    }
}
