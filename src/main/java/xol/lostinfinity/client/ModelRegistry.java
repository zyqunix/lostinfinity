package xol.lostinfinity.client;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.tileentity.TileEntityCthulhuSpawner;
import xol.lostinfinity.block.tileentity.TileEntityEternalBeacon;
import xol.lostinfinity.block.tileentity.TileEntityKillerVine;
import xol.lostinfinity.block.tileentity.TileEntityLightEmitter;
import xol.lostinfinity.block.tileentity.TileEntityNicroniumInfuser;
import xol.lostinfinity.block.tileentity.TileEntityPortalNexus;
import xol.lostinfinity.block.tileentity.TileEntityPortalNode;
import xol.lostinfinity.block.tileentity.TileEntityTeslaTower;
import xol.lostinfinity.block.tileentity.TileEntityVoidVacuum;
import xol.lostinfinity.block.tileentity.render.RenderCthulhuSpawner;
import xol.lostinfinity.block.tileentity.render.RenderEternalBeacon;
import xol.lostinfinity.block.tileentity.render.RenderKillerVine;
import xol.lostinfinity.block.tileentity.render.RenderNicroniumInfuserEffect;
import xol.lostinfinity.block.tileentity.render.RenderPortalNexus;
import xol.lostinfinity.block.tileentity.render.RenderPortalNodeEffect;
import xol.lostinfinity.block.tileentity.render.RenderTeslaTower;
import xol.lostinfinity.block.tileentity.render.RenderVoidVacuum;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.item.armor.ItemLostArmor;
import xol.lostinfinity.mob.entity.base.EntityParticleTrojan;
import xol.lostinfinity.mob.entity.boss.EntityAlestria;
import xol.lostinfinity.mob.entity.boss.EntityArash;
import xol.lostinfinity.mob.entity.boss.EntityArenaEvent;
import xol.lostinfinity.mob.entity.boss.EntityAtlasCrystal;
import xol.lostinfinity.mob.entity.boss.EntityAtlasSpire;
import xol.lostinfinity.mob.entity.boss.EntityBarul;
import xol.lostinfinity.mob.entity.boss.EntityCryonus;
import xol.lostinfinity.mob.entity.boss.EntityDarrio;
import xol.lostinfinity.mob.entity.boss.EntityDeviantCrystal;
import xol.lostinfinity.mob.entity.boss.EntityDeviantWither;
import xol.lostinfinity.mob.entity.boss.EntityDroidBoss;
import xol.lostinfinity.mob.entity.boss.EntityDuskerQueen;
import xol.lostinfinity.mob.entity.boss.EntityElara;
import xol.lostinfinity.mob.entity.boss.EntityNuxuro;
import xol.lostinfinity.mob.entity.boss.EntityOzor;
import xol.lostinfinity.mob.entity.boss.EntityOzorDecoy;
import xol.lostinfinity.mob.entity.boss.EntityPuzzleMaster;
import xol.lostinfinity.mob.entity.boss.EntityRestorationCrystal;
import xol.lostinfinity.mob.entity.boss.EntityRikarus;
import xol.lostinfinity.mob.entity.boss.EntitySentryCrystal;
import xol.lostinfinity.mob.entity.boss.EntityThundyron;
import xol.lostinfinity.mob.entity.boss.EntityUrogo;
import xol.lostinfinity.mob.entity.boss.EntityVelo;
import xol.lostinfinity.mob.entity.boss.EntityVycellia;
import xol.lostinfinity.mob.entity.boss.EntityWitherSkullling;
import xol.lostinfinity.mob.entity.contest.EntityBloodhunter;
import xol.lostinfinity.mob.entity.contest.EntityContestHologram;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerBattleSnakes;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerBombers;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerDuelArena;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerHolodeck;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerHunters;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerInkBattle;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerLaserTag;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerLightBridge;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerParkour;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerRedlight;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerTargets;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerTrampolineDodgeball;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerTreadmill;
import xol.lostinfinity.mob.entity.contest.misc.EntityTreadmillObstacle;
import xol.lostinfinity.mob.entity.contest.misc.EntityTreadmillObstacleJumpable;
import xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBattleSnakes;
import xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBombers;
import xol.lostinfinity.mob.entity.contest.operator.EntityOperatorDuelArena;
import xol.lostinfinity.mob.entity.contest.operator.EntityOperatorHolodeck;
import xol.lostinfinity.mob.entity.contest.operator.EntityOperatorHunters;
import xol.lostinfinity.mob.entity.contest.operator.EntityOperatorInkBattle;
import xol.lostinfinity.mob.entity.contest.operator.EntityOperatorLaserTag;
import xol.lostinfinity.mob.entity.contest.operator.EntityOperatorLightBridge;
import xol.lostinfinity.mob.entity.contest.operator.EntityOperatorParkour;
import xol.lostinfinity.mob.entity.contest.operator.EntityOperatorRedlight;
import xol.lostinfinity.mob.entity.contest.operator.EntityOperatorTargets;
import xol.lostinfinity.mob.entity.contest.operator.EntityOperatorTrampolineDodgeball;
import xol.lostinfinity.mob.entity.contest.operator.EntityOperatorTreadmill;
import xol.lostinfinity.mob.entity.contest.trader.EntityArcheologist;
import xol.lostinfinity.mob.entity.contest.trader.EntityChemist;
import xol.lostinfinity.mob.entity.contest.trader.EntityContraderBattleSnakes;
import xol.lostinfinity.mob.entity.contest.trader.EntityContraderBombers;
import xol.lostinfinity.mob.entity.contest.trader.EntityContraderHolodeck;
import xol.lostinfinity.mob.entity.contest.trader.EntityContraderHunters;
import xol.lostinfinity.mob.entity.contest.trader.EntityContraderInkBattle;
import xol.lostinfinity.mob.entity.contest.trader.EntityContraderLaserTag;
import xol.lostinfinity.mob.entity.contest.trader.EntityContraderLightbridge;
import xol.lostinfinity.mob.entity.contest.trader.EntityContraderMarket;
import xol.lostinfinity.mob.entity.contest.trader.EntityContraderParkour;
import xol.lostinfinity.mob.entity.contest.trader.EntityContraderRedlight;
import xol.lostinfinity.mob.entity.contest.trader.EntityContraderTargets;
import xol.lostinfinity.mob.entity.contest.trader.EntityContraderTrampolineDodgeball;
import xol.lostinfinity.mob.entity.contest.trader.EntityContraderTreadmill;
import xol.lostinfinity.mob.entity.cthulhu.EntityCelestialStatue;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhu;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuBlackHole;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuCloud;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuDeathFX;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuHealingOrb;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuPart;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuRift;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuSpear;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuTentacle;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuTurret;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantAmalgam;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantBat;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantBear;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantBlaze;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantCaveSpider;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantChicken;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantCow;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantCreeper;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantDimTrader;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantEnderman;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantEvoker;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantEvokerVex;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantGhast;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantGolem;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantGuardian;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantHorse;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantHusk;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantLlama;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantMagmacube;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantMooshroom;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantOcelote;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantPig;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantPiglin;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantSheep;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantShulker;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantSkeleton;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantSkyworm;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantSlime;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantSlimeStrider;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantSnowman;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantSpider;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantSquid;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantStray;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantVex;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantWitch;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantWitherSkeleton;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantWolf;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantZombie;
import xol.lostinfinity.mob.entity.deviant.EntityLostDeviant;
import xol.lostinfinity.mob.entity.deviant.prime.EntityAzross;
import xol.lostinfinity.mob.entity.deviant.prime.EntityKalikos;
import xol.lostinfinity.mob.entity.deviant.prime.EntityLivorax;
import xol.lostinfinity.mob.entity.deviant.prime.EntityZenon;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTitanBlaze;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTitanCreeper;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTitanEnderman;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTitanLlama;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTitanMagmacube;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTitanPiglin;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTitanShulker;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTitanSkeleton;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTitanSpider;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTitanStray;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTitanVex;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTitanZombie;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTrialObserver;
import xol.lostinfinity.mob.entity.fungal.EntityFungfly;
import xol.lostinfinity.mob.entity.fungal.EntityMushmerra;
import xol.lostinfinity.mob.entity.fungal.EntityMushmerraClone;
import xol.lostinfinity.mob.entity.fungal.EntityShroomite;
import xol.lostinfinity.mob.entity.galaxy.EntityGalaxyBeast;
import xol.lostinfinity.mob.entity.galaxy.EntityGalaxyDragon;
import xol.lostinfinity.mob.entity.galaxy.EntityGalaxyGladiator;
import xol.lostinfinity.mob.entity.galaxy.EntityGalaxyGulper;
import xol.lostinfinity.mob.entity.galaxy.EntityGalaxySorcerer;
import xol.lostinfinity.mob.entity.galaxy.EntityGalaxySpire;
import xol.lostinfinity.mob.entity.galaxy.EntityLaserSpire;
import xol.lostinfinity.mob.entity.labyrinth.EntityAspect;
import xol.lostinfinity.mob.entity.labyrinth.EntityClinger;
import xol.lostinfinity.mob.entity.labyrinth.EntityCyclos;
import xol.lostinfinity.mob.entity.labyrinth.EntityGloop;
import xol.lostinfinity.mob.entity.labyrinth.EntityGloopMother;
import xol.lostinfinity.mob.entity.labyrinth.EntityLabWarrior;
import xol.lostinfinity.mob.entity.labyrinth.EntityLabWizard;
import xol.lostinfinity.mob.entity.labyrinth.EntityNat;
import xol.lostinfinity.mob.entity.labyrinth.EntitySentry;
import xol.lostinfinity.mob.entity.labyrinth.EntityStickler;
import xol.lostinfinity.mob.entity.labyrinth.EntityVectosect;
import xol.lostinfinity.mob.entity.minion.EntityAbyssalCrabulon;
import xol.lostinfinity.mob.entity.minion.EntityAuraOfAllegiance;
import xol.lostinfinity.mob.entity.minion.EntityBombDrone;
import xol.lostinfinity.mob.entity.minion.EntityLostBlade;
import xol.lostinfinity.mob.entity.minion.EntityLuminousGuardian;
import xol.lostinfinity.mob.entity.minion.andromeda.EntityAndromedaChaser;
import xol.lostinfinity.mob.entity.minion.andromeda.EntityAndromedaController;
import xol.lostinfinity.mob.entity.minion.andromeda.EntityAndromedaSegment;
import xol.lostinfinity.mob.entity.misc.EntityBomberBomb;
import xol.lostinfinity.mob.entity.misc.EntityCellGameMerchant;
import xol.lostinfinity.mob.entity.misc.EntityClusterCannon;
import xol.lostinfinity.mob.entity.misc.EntityConnectGameMerchant;
import xol.lostinfinity.mob.entity.misc.EntityCourseRing;
import xol.lostinfinity.mob.entity.misc.EntityDimensionalMerchant;
import xol.lostinfinity.mob.entity.misc.EntityDroid;
import xol.lostinfinity.mob.entity.misc.EntityEffigyEffect;
import xol.lostinfinity.mob.entity.misc.EntityEidolonMist;
import xol.lostinfinity.mob.entity.misc.EntityFeralMerchant;
import xol.lostinfinity.mob.entity.misc.EntityGhostCopy;
import xol.lostinfinity.mob.entity.misc.EntityIonExplosion;
import xol.lostinfinity.mob.entity.misc.EntityLightGame;
import xol.lostinfinity.mob.entity.misc.EntityMarkOfInfiniteDespair;
import xol.lostinfinity.mob.entity.misc.EntityMemPuzzleMerchant;
import xol.lostinfinity.mob.entity.misc.EntityMirrorZombie;
import xol.lostinfinity.mob.entity.misc.EntityMortarCannon;
import xol.lostinfinity.mob.entity.misc.EntityMultiverseGhost;
import xol.lostinfinity.mob.entity.misc.EntityNuclearExplosion;
import xol.lostinfinity.mob.entity.misc.EntityPickleMan;
import xol.lostinfinity.mob.entity.misc.EntityPipeGameMerchant;
import xol.lostinfinity.mob.entity.misc.EntityPlasmaBomb;
import xol.lostinfinity.mob.entity.misc.EntityPlasmaExplosion;
import xol.lostinfinity.mob.entity.misc.EntityPlasmaSlicer;
import xol.lostinfinity.mob.entity.misc.EntityPlayerLimb;
import xol.lostinfinity.mob.entity.misc.EntityRhythmGameMerchant;
import xol.lostinfinity.mob.entity.misc.EntityRift;
import xol.lostinfinity.mob.entity.misc.EntityRisingPhantom;
import xol.lostinfinity.mob.entity.misc.EntityRocketStrappedExplosive;
import xol.lostinfinity.mob.entity.misc.EntitySandAttack;
import xol.lostinfinity.mob.entity.misc.EntitySkybooster;
import xol.lostinfinity.mob.entity.misc.EntitySkycrab;
import xol.lostinfinity.mob.entity.misc.EntitySlimeStrider;
import xol.lostinfinity.mob.entity.misc.EntitySpectre;
import xol.lostinfinity.mob.entity.misc.EntityStarfiend;
import xol.lostinfinity.mob.entity.misc.EntityStickyBomb;
import xol.lostinfinity.mob.entity.misc.EntityStormBomb;
import xol.lostinfinity.mob.entity.misc.EntitySupplyTrader;
import xol.lostinfinity.mob.entity.misc.EntityTNTZombie;
import xol.lostinfinity.mob.entity.misc.EntityTentacleTrap;
import xol.lostinfinity.mob.entity.misc.EntityThunderBomb;
import xol.lostinfinity.mob.entity.misc.EntityTornIndividual;
import xol.lostinfinity.mob.entity.misc.EntityTotemMoon;
import xol.lostinfinity.mob.entity.misc.EntityTotemPylon;
import xol.lostinfinity.mob.entity.misc.EntityTotemSplitter;
import xol.lostinfinity.mob.entity.misc.EntityUnstableMerchant;
import xol.lostinfinity.mob.entity.misc.EntityUnstableRift;
import xol.lostinfinity.mob.entity.misc.EntityWhackGameMerchant;
import xol.lostinfinity.mob.entity.misc.EntityWormholePortal;
import xol.lostinfinity.mob.entity.mount.EntityJetMount;
import xol.lostinfinity.mob.entity.mount.EntityXScreacher;
import xol.lostinfinity.mob.entity.murk.EntityCaveTerror;
import xol.lostinfinity.mob.entity.murk.EntityDoomsday;
import xol.lostinfinity.mob.entity.murk.EntityScorpwing;
import xol.lostinfinity.mob.entity.murk.EntityScreamer;
import xol.lostinfinity.mob.entity.murk.EntitySkyre;
import xol.lostinfinity.mob.entity.murk.EntityTorpedon;
import xol.lostinfinity.mob.entity.murk.EntityWhisper;
import xol.lostinfinity.mob.entity.nebula.EntityNebulaGiant;
import xol.lostinfinity.mob.entity.nebula.EntityNebulaGrunt;
import xol.lostinfinity.mob.entity.nebula.EntityNebulaWizard;
import xol.lostinfinity.mob.entity.sea.EntityCrabulon;
import xol.lostinfinity.mob.entity.sea.EntityDoublerang;
import xol.lostinfinity.mob.entity.sea.EntityEelShark;
import xol.lostinfinity.mob.entity.sea.EntityGlowfish;
import xol.lostinfinity.mob.entity.sea.EntityLongfin;
import xol.lostinfinity.mob.entity.sea.EntityOctobrella;
import xol.lostinfinity.mob.entity.sea.EntityPearlCollector;
import xol.lostinfinity.mob.entity.sea.EntityRayfish;
import xol.lostinfinity.mob.entity.sea.EntityRibshark;
import xol.lostinfinity.mob.entity.sea.EntityUnderfin;
import xol.lostinfinity.mob.entity.sea.leviathan.EntityLeviathanController;
import xol.lostinfinity.mob.entity.sea.leviathan.EntityLeviathanHead;
import xol.lostinfinity.mob.entity.sea.leviathan.EntityLeviathanSegment;
import xol.lostinfinity.mob.entity.sea.leviathan.EntityLeviathanTail;
import xol.lostinfinity.mob.entity.sea.seaserpent.EntitySeaSerpentController;
import xol.lostinfinity.mob.entity.sea.seaserpent.EntitySeaSerpentHead;
import xol.lostinfinity.mob.entity.sea.seaserpent.EntitySeaSerpentSegment;
import xol.lostinfinity.mob.entity.sea.seaserpent.EntitySeaSerpentTail;
import xol.lostinfinity.mob.entity.starforge.EntityAcidback;
import xol.lostinfinity.mob.entity.starforge.EntityAugmenticon;
import xol.lostinfinity.mob.entity.starforge.EntityBlisterweed;
import xol.lostinfinity.mob.entity.starforge.EntityChomper;
import xol.lostinfinity.mob.entity.starforge.EntityClusterweed;
import xol.lostinfinity.mob.entity.starforge.EntityClyster;
import xol.lostinfinity.mob.entity.starforge.EntityCrawker;
import xol.lostinfinity.mob.entity.starforge.EntityCrusher;
import xol.lostinfinity.mob.entity.starforge.EntityDoomDog;
import xol.lostinfinity.mob.entity.starforge.EntityDoublejaw;
import xol.lostinfinity.mob.entity.starforge.EntityDrippler;
import xol.lostinfinity.mob.entity.starforge.EntityDusker;
import xol.lostinfinity.mob.entity.starforge.EntityEssenceDweller;
import xol.lostinfinity.mob.entity.starforge.EntityEssenceIdol;
import xol.lostinfinity.mob.entity.starforge.EntityExplosect;
import xol.lostinfinity.mob.entity.starforge.EntityEyeSlug;
import xol.lostinfinity.mob.entity.starforge.EntityFlapper;
import xol.lostinfinity.mob.entity.starforge.EntityFlashfly;
import xol.lostinfinity.mob.entity.starforge.EntityFlurky;
import xol.lostinfinity.mob.entity.starforge.EntityFlutterbee;
import xol.lostinfinity.mob.entity.starforge.EntityFlutterfyre;
import xol.lostinfinity.mob.entity.starforge.EntityFyreweed;
import xol.lostinfinity.mob.entity.starforge.EntityGalacticTerror;
import xol.lostinfinity.mob.entity.starforge.EntityGiantFlapper;
import xol.lostinfinity.mob.entity.starforge.EntityGiantFyreweed;
import xol.lostinfinity.mob.entity.starforge.EntityGiantRockslug;
import xol.lostinfinity.mob.entity.starforge.EntityGlangler;
import xol.lostinfinity.mob.entity.starforge.EntityGloboon;
import xol.lostinfinity.mob.entity.starforge.EntityGlobro;
import xol.lostinfinity.mob.entity.starforge.EntityGlochipper;
import xol.lostinfinity.mob.entity.starforge.EntityGlomite;
import xol.lostinfinity.mob.entity.starforge.EntityGnawer;
import xol.lostinfinity.mob.entity.starforge.EntityGorger;
import xol.lostinfinity.mob.entity.starforge.EntityGrappler;
import xol.lostinfinity.mob.entity.starforge.EntityGravhead;
import xol.lostinfinity.mob.entity.starforge.EntityGrubber;
import xol.lostinfinity.mob.entity.starforge.EntityHanger;
import xol.lostinfinity.mob.entity.starforge.EntityHurler;
import xol.lostinfinity.mob.entity.starforge.EntityHypnosaur;
import xol.lostinfinity.mob.entity.starforge.EntityLeer;
import xol.lostinfinity.mob.entity.starforge.EntityLurcher;
import xol.lostinfinity.mob.entity.starforge.EntityMinimite;
import xol.lostinfinity.mob.entity.starforge.EntityNightshyre;
import xol.lostinfinity.mob.entity.starforge.EntityOrbiter;
import xol.lostinfinity.mob.entity.starforge.EntityPhaser;
import xol.lostinfinity.mob.entity.starforge.EntityRavager;
import xol.lostinfinity.mob.entity.starforge.EntityReflectal;
import xol.lostinfinity.mob.entity.starforge.EntityRibrex;
import xol.lostinfinity.mob.entity.starforge.EntityRockpest;
import xol.lostinfinity.mob.entity.starforge.EntityRockslug;
import xol.lostinfinity.mob.entity.starforge.EntityRockworm;
import xol.lostinfinity.mob.entity.starforge.EntityScreacher;
import xol.lostinfinity.mob.entity.starforge.EntityShimmer;
import xol.lostinfinity.mob.entity.starforge.EntitySightwalker;
import xol.lostinfinity.mob.entity.starforge.EntitySnapper;
import xol.lostinfinity.mob.entity.starforge.EntitySpinovern;
import xol.lostinfinity.mob.entity.starforge.EntitySpyker;
import xol.lostinfinity.mob.entity.starforge.EntityTentaclon;
import xol.lostinfinity.mob.entity.starforge.EntityTerrorFly;
import xol.lostinfinity.mob.entity.starforge.EntityTetherbug;
import xol.lostinfinity.mob.entity.starforge.EntityTitanopod;
import xol.lostinfinity.mob.entity.starforge.EntityVilebulb;
import xol.lostinfinity.mob.entity.starforge.EntityWeaver;
import xol.lostinfinity.mob.entity.starforge.EntityWisp;
import xol.lostinfinity.mob.render.arena.RenderAlestria;
import xol.lostinfinity.mob.render.arena.RenderArash;
import xol.lostinfinity.mob.render.arena.RenderArenaEvent;
import xol.lostinfinity.mob.render.arena.RenderAtlasCrystal;
import xol.lostinfinity.mob.render.arena.RenderAtlasSpire;
import xol.lostinfinity.mob.render.arena.RenderBarul;
import xol.lostinfinity.mob.render.arena.RenderCryonus;
import xol.lostinfinity.mob.render.arena.RenderDarrio;
import xol.lostinfinity.mob.render.arena.RenderDeviantWither;
import xol.lostinfinity.mob.render.arena.RenderDroidBoss;
import xol.lostinfinity.mob.render.arena.RenderDuskerQueen;
import xol.lostinfinity.mob.render.arena.RenderElara;
import xol.lostinfinity.mob.render.arena.RenderNuxuro;
import xol.lostinfinity.mob.render.arena.RenderOzor;
import xol.lostinfinity.mob.render.arena.RenderOzorDecoy;
import xol.lostinfinity.mob.render.arena.RenderPuzzleMaster;
import xol.lostinfinity.mob.render.arena.RenderRestorationCrystal;
import xol.lostinfinity.mob.render.arena.RenderRikarus;
import xol.lostinfinity.mob.render.arena.RenderSentryCrystal;
import xol.lostinfinity.mob.render.arena.RenderThundyron;
import xol.lostinfinity.mob.render.arena.RenderUrogo;
import xol.lostinfinity.mob.render.arena.RenderVelo;
import xol.lostinfinity.mob.render.arena.RenderVycellia;
import xol.lostinfinity.mob.render.arena.RenderWitherSkullling;
import xol.lostinfinity.mob.render.contest.RenderArcheologist;
import xol.lostinfinity.mob.render.contest.RenderBloodhunter;
import xol.lostinfinity.mob.render.contest.RenderChemist;
import xol.lostinfinity.mob.render.contest.RenderContestHologram;
import xol.lostinfinity.mob.render.contest.RenderContraderBattleSnakes;
import xol.lostinfinity.mob.render.contest.RenderContraderBombers;
import xol.lostinfinity.mob.render.contest.RenderContraderHolodeck;
import xol.lostinfinity.mob.render.contest.RenderContraderHunters;
import xol.lostinfinity.mob.render.contest.RenderContraderInkBattle;
import xol.lostinfinity.mob.render.contest.RenderContraderLaserTag;
import xol.lostinfinity.mob.render.contest.RenderContraderLightbridge;
import xol.lostinfinity.mob.render.contest.RenderContraderMarket;
import xol.lostinfinity.mob.render.contest.RenderContraderParkour;
import xol.lostinfinity.mob.render.contest.RenderContraderRedlight;
import xol.lostinfinity.mob.render.contest.RenderContraderTargets;
import xol.lostinfinity.mob.render.contest.RenderContraderTrampolineDodgeball;
import xol.lostinfinity.mob.render.contest.RenderContraderTreadmill;
import xol.lostinfinity.mob.render.contest.RenderControllerBattleSnakes;
import xol.lostinfinity.mob.render.contest.RenderControllerBombers;
import xol.lostinfinity.mob.render.contest.RenderControllerDuelArena;
import xol.lostinfinity.mob.render.contest.RenderControllerHolodeck;
import xol.lostinfinity.mob.render.contest.RenderControllerHunters;
import xol.lostinfinity.mob.render.contest.RenderControllerInkBattle;
import xol.lostinfinity.mob.render.contest.RenderControllerLaserTag;
import xol.lostinfinity.mob.render.contest.RenderControllerLightBridge;
import xol.lostinfinity.mob.render.contest.RenderControllerParkour;
import xol.lostinfinity.mob.render.contest.RenderControllerRedlight;
import xol.lostinfinity.mob.render.contest.RenderControllerTargets;
import xol.lostinfinity.mob.render.contest.RenderControllerTrampolineDodgeball;
import xol.lostinfinity.mob.render.contest.RenderControllerTreadmill;
import xol.lostinfinity.mob.render.contest.RenderOperatorBattleSnakes;
import xol.lostinfinity.mob.render.contest.RenderOperatorBombers;
import xol.lostinfinity.mob.render.contest.RenderOperatorDuelArena;
import xol.lostinfinity.mob.render.contest.RenderOperatorHolodeck;
import xol.lostinfinity.mob.render.contest.RenderOperatorHunters;
import xol.lostinfinity.mob.render.contest.RenderOperatorInkBattle;
import xol.lostinfinity.mob.render.contest.RenderOperatorLaserTag;
import xol.lostinfinity.mob.render.contest.RenderOperatorLightBridge;
import xol.lostinfinity.mob.render.contest.RenderOperatorParkour;
import xol.lostinfinity.mob.render.contest.RenderOperatorRedlight;
import xol.lostinfinity.mob.render.contest.RenderOperatorTargets;
import xol.lostinfinity.mob.render.contest.RenderOperatorTrampolineDodgeball;
import xol.lostinfinity.mob.render.contest.RenderOperatorTreadmill;
import xol.lostinfinity.mob.render.contest.RenderTreadmillObstacle;
import xol.lostinfinity.mob.render.contest.RenderTreadmillObstacleJumpable;
import xol.lostinfinity.mob.render.cthulhu.RenderCelestialStatue;
import xol.lostinfinity.mob.render.cthulhu.RenderCthulhu;
import xol.lostinfinity.mob.render.cthulhu.RenderCthulhuBlackHole;
import xol.lostinfinity.mob.render.cthulhu.RenderCthulhuCloud;
import xol.lostinfinity.mob.render.cthulhu.RenderCthulhuDeathFX;
import xol.lostinfinity.mob.render.cthulhu.RenderCthulhuHealingOrb;
import xol.lostinfinity.mob.render.cthulhu.RenderCthulhuPart;
import xol.lostinfinity.mob.render.cthulhu.RenderCthulhuRift;
import xol.lostinfinity.mob.render.cthulhu.RenderCthulhuSpear;
import xol.lostinfinity.mob.render.cthulhu.RenderCthulhuTentacle;
import xol.lostinfinity.mob.render.cthulhu.RenderCthulhuTurret;
import xol.lostinfinity.mob.render.deviant.RenderDeviantAmalgam;
import xol.lostinfinity.mob.render.deviant.RenderDeviantBat;
import xol.lostinfinity.mob.render.deviant.RenderDeviantBear;
import xol.lostinfinity.mob.render.deviant.RenderDeviantBlaze;
import xol.lostinfinity.mob.render.deviant.RenderDeviantCaveSpider;
import xol.lostinfinity.mob.render.deviant.RenderDeviantChicken;
import xol.lostinfinity.mob.render.deviant.RenderDeviantCow;
import xol.lostinfinity.mob.render.deviant.RenderDeviantCreeper;
import xol.lostinfinity.mob.render.deviant.RenderDeviantCrystal;
import xol.lostinfinity.mob.render.deviant.RenderDeviantDimTrader;
import xol.lostinfinity.mob.render.deviant.RenderDeviantEnderman;
import xol.lostinfinity.mob.render.deviant.RenderDeviantEvoker;
import xol.lostinfinity.mob.render.deviant.RenderDeviantEvokerVex;
import xol.lostinfinity.mob.render.deviant.RenderDeviantGhast;
import xol.lostinfinity.mob.render.deviant.RenderDeviantGolem;
import xol.lostinfinity.mob.render.deviant.RenderDeviantGuardian;
import xol.lostinfinity.mob.render.deviant.RenderDeviantHorse;
import xol.lostinfinity.mob.render.deviant.RenderDeviantHusk;
import xol.lostinfinity.mob.render.deviant.RenderDeviantLlama;
import xol.lostinfinity.mob.render.deviant.RenderDeviantMagmacube;
import xol.lostinfinity.mob.render.deviant.RenderDeviantMooshroom;
import xol.lostinfinity.mob.render.deviant.RenderDeviantOcelote;
import xol.lostinfinity.mob.render.deviant.RenderDeviantPig;
import xol.lostinfinity.mob.render.deviant.RenderDeviantPiglin;
import xol.lostinfinity.mob.render.deviant.RenderDeviantSheep;
import xol.lostinfinity.mob.render.deviant.RenderDeviantShulker;
import xol.lostinfinity.mob.render.deviant.RenderDeviantSkeleton;
import xol.lostinfinity.mob.render.deviant.RenderDeviantSkyworm;
import xol.lostinfinity.mob.render.deviant.RenderDeviantSlime;
import xol.lostinfinity.mob.render.deviant.RenderDeviantSlimeStrider;
import xol.lostinfinity.mob.render.deviant.RenderDeviantSnowman;
import xol.lostinfinity.mob.render.deviant.RenderDeviantSpider;
import xol.lostinfinity.mob.render.deviant.RenderDeviantSquid;
import xol.lostinfinity.mob.render.deviant.RenderDeviantStray;
import xol.lostinfinity.mob.render.deviant.RenderDeviantVex;
import xol.lostinfinity.mob.render.deviant.RenderDeviantWitch;
import xol.lostinfinity.mob.render.deviant.RenderDeviantWitherSkeleton;
import xol.lostinfinity.mob.render.deviant.RenderDeviantWolf;
import xol.lostinfinity.mob.render.deviant.RenderDeviantZombie;
import xol.lostinfinity.mob.render.deviant.RenderLostDeviant;
import xol.lostinfinity.mob.render.fungal.RenderFungfly;
import xol.lostinfinity.mob.render.fungal.RenderMushmerra;
import xol.lostinfinity.mob.render.fungal.RenderMushmerraClone;
import xol.lostinfinity.mob.render.fungal.RenderShroomite;
import xol.lostinfinity.mob.render.galaxy.RenderGalaxyBeast;
import xol.lostinfinity.mob.render.galaxy.RenderGalaxyDragon;
import xol.lostinfinity.mob.render.galaxy.RenderGalaxyGladiator;
import xol.lostinfinity.mob.render.galaxy.RenderGalaxyGulper;
import xol.lostinfinity.mob.render.galaxy.RenderGalaxySorcerer;
import xol.lostinfinity.mob.render.galaxy.RenderGalaxySpire;
import xol.lostinfinity.mob.render.galaxy.RenderLaserSpire;
import xol.lostinfinity.mob.render.labyrinth.RenderAspect;
import xol.lostinfinity.mob.render.labyrinth.RenderClinger;
import xol.lostinfinity.mob.render.labyrinth.RenderCyclos;
import xol.lostinfinity.mob.render.labyrinth.RenderGloop;
import xol.lostinfinity.mob.render.labyrinth.RenderGloopMother;
import xol.lostinfinity.mob.render.labyrinth.RenderLabWarrior;
import xol.lostinfinity.mob.render.labyrinth.RenderLabWizard;
import xol.lostinfinity.mob.render.labyrinth.RenderNat;
import xol.lostinfinity.mob.render.labyrinth.RenderSentry;
import xol.lostinfinity.mob.render.labyrinth.RenderStickler;
import xol.lostinfinity.mob.render.labyrinth.RenderVectosect;
import xol.lostinfinity.mob.render.minion.RenderAbyssalCrabulon;
import xol.lostinfinity.mob.render.minion.RenderAuraOfAllegiance;
import xol.lostinfinity.mob.render.minion.RenderLostBlade;
import xol.lostinfinity.mob.render.minion.RenderLuminousGuardian;
import xol.lostinfinity.mob.render.minion.andromeda.RenderAndromedaController;
import xol.lostinfinity.mob.render.minion.andromeda.RenderAndromedaSegment;
import xol.lostinfinity.mob.render.misc.RenderBomberBomb;
import xol.lostinfinity.mob.render.misc.RenderCellGameMerchant;
import xol.lostinfinity.mob.render.misc.RenderClusterCannon;
import xol.lostinfinity.mob.render.misc.RenderConnectGameMerchant;
import xol.lostinfinity.mob.render.misc.RenderCourseRing;
import xol.lostinfinity.mob.render.misc.RenderDimensionalMerchant;
import xol.lostinfinity.mob.render.misc.RenderDroid;
import xol.lostinfinity.mob.render.misc.RenderEffigyEffect;
import xol.lostinfinity.mob.render.misc.RenderEidolonMist;
import xol.lostinfinity.mob.render.misc.RenderFeralMerchant;
import xol.lostinfinity.mob.render.misc.RenderGhostCopy;
import xol.lostinfinity.mob.render.misc.RenderIonExplosion;
import xol.lostinfinity.mob.render.misc.RenderLightPuzzleMerchant;
import xol.lostinfinity.mob.render.misc.RenderMarkOfInfiniteDespair;
import xol.lostinfinity.mob.render.misc.RenderMemPuzzleMerchant;
import xol.lostinfinity.mob.render.misc.RenderMirrorZombie;
import xol.lostinfinity.mob.render.misc.RenderMortarCannon;
import xol.lostinfinity.mob.render.misc.RenderMultiverseGhost;
import xol.lostinfinity.mob.render.misc.RenderParticleTrojan;
import xol.lostinfinity.mob.render.misc.RenderPickleMan;
import xol.lostinfinity.mob.render.misc.RenderPipeGameMerchant;
import xol.lostinfinity.mob.render.misc.RenderPlasmaBomb;
import xol.lostinfinity.mob.render.misc.RenderPlasmaExplosion;
import xol.lostinfinity.mob.render.misc.RenderPlasmaSlicer;
import xol.lostinfinity.mob.render.misc.RenderPlayerLimb;
import xol.lostinfinity.mob.render.misc.RenderRhythmGameMerchant;
import xol.lostinfinity.mob.render.misc.RenderRift;
import xol.lostinfinity.mob.render.misc.RenderRisingPhantom;
import xol.lostinfinity.mob.render.misc.RenderRocketStrappedExplosive;
import xol.lostinfinity.mob.render.misc.RenderSandAttack;
import xol.lostinfinity.mob.render.misc.RenderSkybooster;
import xol.lostinfinity.mob.render.misc.RenderSkycrab;
import xol.lostinfinity.mob.render.misc.RenderSlimeStrider;
import xol.lostinfinity.mob.render.misc.RenderSpectre;
import xol.lostinfinity.mob.render.misc.RenderStarfiend;
import xol.lostinfinity.mob.render.misc.RenderStickyBomb;
import xol.lostinfinity.mob.render.misc.RenderStormBomb;
import xol.lostinfinity.mob.render.misc.RenderSupplyTrader;
import xol.lostinfinity.mob.render.misc.RenderTNTZombie;
import xol.lostinfinity.mob.render.misc.RenderTentacleTrap;
import xol.lostinfinity.mob.render.misc.RenderThunderBomb;
import xol.lostinfinity.mob.render.misc.RenderTornIndividual;
import xol.lostinfinity.mob.render.misc.RenderTotemMoon;
import xol.lostinfinity.mob.render.misc.RenderTotemPylon;
import xol.lostinfinity.mob.render.misc.RenderTotemSplitter;
import xol.lostinfinity.mob.render.misc.RenderUnstableMerchant;
import xol.lostinfinity.mob.render.misc.RenderUnstableRift;
import xol.lostinfinity.mob.render.misc.RenderWhackGameMerchant;
import xol.lostinfinity.mob.render.misc.RenderWormholePortal;
import xol.lostinfinity.mob.render.mount.RenderBombDrone;
import xol.lostinfinity.mob.render.mount.RenderJetMount;
import xol.lostinfinity.mob.render.mount.RenderXScreacher;
import xol.lostinfinity.mob.render.murk.RenderCaveTerror;
import xol.lostinfinity.mob.render.murk.RenderDoomsday;
import xol.lostinfinity.mob.render.murk.RenderScorpwing;
import xol.lostinfinity.mob.render.murk.RenderScreamer;
import xol.lostinfinity.mob.render.murk.RenderSkyre;
import xol.lostinfinity.mob.render.murk.RenderTorpedon;
import xol.lostinfinity.mob.render.murk.RenderWhisper;
import xol.lostinfinity.mob.render.nebula.RenderNebulaGiant;
import xol.lostinfinity.mob.render.nebula.RenderNebulaGrunt;
import xol.lostinfinity.mob.render.nebula.RenderNebulaWizard;
import xol.lostinfinity.mob.render.prime.RenderAzross;
import xol.lostinfinity.mob.render.prime.RenderKalikos;
import xol.lostinfinity.mob.render.prime.RenderLivorax;
import xol.lostinfinity.mob.render.prime.RenderZenon;
import xol.lostinfinity.mob.render.sea.RenderCrabulon;
import xol.lostinfinity.mob.render.sea.RenderDoublerang;
import xol.lostinfinity.mob.render.sea.RenderEelShark;
import xol.lostinfinity.mob.render.sea.RenderGlowfish;
import xol.lostinfinity.mob.render.sea.RenderLeviathanController;
import xol.lostinfinity.mob.render.sea.RenderLeviathanSegment;
import xol.lostinfinity.mob.render.sea.RenderLongfin;
import xol.lostinfinity.mob.render.sea.RenderOctobrella;
import xol.lostinfinity.mob.render.sea.RenderPearlCollector;
import xol.lostinfinity.mob.render.sea.RenderRayfish;
import xol.lostinfinity.mob.render.sea.RenderRibshark;
import xol.lostinfinity.mob.render.sea.RenderSeaSerpentController;
import xol.lostinfinity.mob.render.sea.RenderSeaSerpentSegment;
import xol.lostinfinity.mob.render.sea.RenderUnderfin;
import xol.lostinfinity.mob.render.starforge.RenderAcidback;
import xol.lostinfinity.mob.render.starforge.RenderAugmenticon;
import xol.lostinfinity.mob.render.starforge.RenderBlisterweed;
import xol.lostinfinity.mob.render.starforge.RenderChomper;
import xol.lostinfinity.mob.render.starforge.RenderClusterweed;
import xol.lostinfinity.mob.render.starforge.RenderClyster;
import xol.lostinfinity.mob.render.starforge.RenderCrawker;
import xol.lostinfinity.mob.render.starforge.RenderCrusher;
import xol.lostinfinity.mob.render.starforge.RenderDoomDog;
import xol.lostinfinity.mob.render.starforge.RenderDoublejaw;
import xol.lostinfinity.mob.render.starforge.RenderDrippler;
import xol.lostinfinity.mob.render.starforge.RenderDusker;
import xol.lostinfinity.mob.render.starforge.RenderEssenceDweller;
import xol.lostinfinity.mob.render.starforge.RenderEssenceIdol;
import xol.lostinfinity.mob.render.starforge.RenderExplosect;
import xol.lostinfinity.mob.render.starforge.RenderEyeSlug;
import xol.lostinfinity.mob.render.starforge.RenderFlapper;
import xol.lostinfinity.mob.render.starforge.RenderFlashfly;
import xol.lostinfinity.mob.render.starforge.RenderFlurky;
import xol.lostinfinity.mob.render.starforge.RenderFlutterbee;
import xol.lostinfinity.mob.render.starforge.RenderFlutterfyre;
import xol.lostinfinity.mob.render.starforge.RenderFyreweed;
import xol.lostinfinity.mob.render.starforge.RenderGalacticTerror;
import xol.lostinfinity.mob.render.starforge.RenderGiantFlapper;
import xol.lostinfinity.mob.render.starforge.RenderGiantFyreweed;
import xol.lostinfinity.mob.render.starforge.RenderGiantRockslug;
import xol.lostinfinity.mob.render.starforge.RenderGlangler;
import xol.lostinfinity.mob.render.starforge.RenderGloboon;
import xol.lostinfinity.mob.render.starforge.RenderGlobro;
import xol.lostinfinity.mob.render.starforge.RenderGlochipper;
import xol.lostinfinity.mob.render.starforge.RenderGlomite;
import xol.lostinfinity.mob.render.starforge.RenderGnawer;
import xol.lostinfinity.mob.render.starforge.RenderGorger;
import xol.lostinfinity.mob.render.starforge.RenderGrappler;
import xol.lostinfinity.mob.render.starforge.RenderGravhead;
import xol.lostinfinity.mob.render.starforge.RenderGrubber;
import xol.lostinfinity.mob.render.starforge.RenderHanger;
import xol.lostinfinity.mob.render.starforge.RenderHurler;
import xol.lostinfinity.mob.render.starforge.RenderHypnosaur;
import xol.lostinfinity.mob.render.starforge.RenderLeer;
import xol.lostinfinity.mob.render.starforge.RenderLurcher;
import xol.lostinfinity.mob.render.starforge.RenderMinimite;
import xol.lostinfinity.mob.render.starforge.RenderNightshyre;
import xol.lostinfinity.mob.render.starforge.RenderOrbiter;
import xol.lostinfinity.mob.render.starforge.RenderPhaser;
import xol.lostinfinity.mob.render.starforge.RenderRavager;
import xol.lostinfinity.mob.render.starforge.RenderReflectal;
import xol.lostinfinity.mob.render.starforge.RenderRibrex;
import xol.lostinfinity.mob.render.starforge.RenderRockpest;
import xol.lostinfinity.mob.render.starforge.RenderRockslug;
import xol.lostinfinity.mob.render.starforge.RenderRockworm;
import xol.lostinfinity.mob.render.starforge.RenderScreacher;
import xol.lostinfinity.mob.render.starforge.RenderShimmer;
import xol.lostinfinity.mob.render.starforge.RenderSightwalker;
import xol.lostinfinity.mob.render.starforge.RenderSnapper;
import xol.lostinfinity.mob.render.starforge.RenderSpinovern;
import xol.lostinfinity.mob.render.starforge.RenderSpyker;
import xol.lostinfinity.mob.render.starforge.RenderTentaclon;
import xol.lostinfinity.mob.render.starforge.RenderTerrorFly;
import xol.lostinfinity.mob.render.starforge.RenderTetherbug;
import xol.lostinfinity.mob.render.starforge.RenderTitanopod;
import xol.lostinfinity.mob.render.starforge.RenderVilebulb;
import xol.lostinfinity.mob.render.starforge.RenderWeaver;
import xol.lostinfinity.mob.render.starforge.RenderWisp;
import xol.lostinfinity.mob.render.titan.RenderTitanBlaze;
import xol.lostinfinity.mob.render.titan.RenderTitanCreeper;
import xol.lostinfinity.mob.render.titan.RenderTitanEnderman;
import xol.lostinfinity.mob.render.titan.RenderTitanLlama;
import xol.lostinfinity.mob.render.titan.RenderTitanMagmacube;
import xol.lostinfinity.mob.render.titan.RenderTitanPiglin;
import xol.lostinfinity.mob.render.titan.RenderTitanShulker;
import xol.lostinfinity.mob.render.titan.RenderTitanSkeleton;
import xol.lostinfinity.mob.render.titan.RenderTitanSpider;
import xol.lostinfinity.mob.render.titan.RenderTitanStray;
import xol.lostinfinity.mob.render.titan.RenderTitanVex;
import xol.lostinfinity.mob.render.titan.RenderTitanZombie;
import xol.lostinfinity.mob.render.titan.RenderTrialObserver;
import xol.lostinfinity.projectile.cthulhu.EntityCthulhuBeam;
import xol.lostinfinity.projectile.cthulhu.EntityCthulhuMeteor;
import xol.lostinfinity.projectile.cthulhu.EntityCthulhuMissile;
import xol.lostinfinity.projectile.cthulhu.EntityCthulhuTurretBullet;
import xol.lostinfinity.projectile.cthulhu.RenderCthulhuBeam;
import xol.lostinfinity.projectile.entity.EntityAcidRainDrop;
import xol.lostinfinity.projectile.entity.EntityAcidicConcoction;
import xol.lostinfinity.projectile.entity.EntityArcBlast;
import xol.lostinfinity.projectile.entity.EntityAsteroid;
import xol.lostinfinity.projectile.entity.EntityAstralShot;
import xol.lostinfinity.projectile.entity.EntityAtlasAttack;
import xol.lostinfinity.projectile.entity.EntityAvenger;
import xol.lostinfinity.projectile.entity.EntityAvengerLightning;
import xol.lostinfinity.projectile.entity.EntityBallOfContainedGluons;
import xol.lostinfinity.projectile.entity.EntityBallOfContainedQuarks;
import xol.lostinfinity.projectile.entity.EntityBarulChain;
import xol.lostinfinity.projectile.entity.EntityBeeAttack;
import xol.lostinfinity.projectile.entity.EntityBlightBomb;
import xol.lostinfinity.projectile.entity.EntityBlightPellet;
import xol.lostinfinity.projectile.entity.EntityBlightedComet;
import xol.lostinfinity.projectile.entity.EntityBloodhunterBlast;
import xol.lostinfinity.projectile.entity.EntityBossPortalEffect;
import xol.lostinfinity.projectile.entity.EntityCarrierProjectile;
import xol.lostinfinity.projectile.entity.EntityCelestialFire;
import xol.lostinfinity.projectile.entity.EntityCellularRock;
import xol.lostinfinity.projectile.entity.EntityChainOfVenomsAttack;
import xol.lostinfinity.projectile.entity.EntityChampionDodgeball;
import xol.lostinfinity.projectile.entity.EntityClusterBlast;
import xol.lostinfinity.projectile.entity.EntityComet;
import xol.lostinfinity.projectile.entity.EntityCrabulonProjectile;
import xol.lostinfinity.projectile.entity.EntityCreepingVineArrow;
import xol.lostinfinity.projectile.entity.EntityCreepingVinePod;
import xol.lostinfinity.projectile.entity.EntityCryoBeamEffect;
import xol.lostinfinity.projectile.entity.EntityCryoBolt;
import xol.lostinfinity.projectile.entity.EntityCrystalGellball;
import xol.lostinfinity.projectile.entity.EntityCrystalShard;
import xol.lostinfinity.projectile.entity.EntityDarkSpell;
import xol.lostinfinity.projectile.entity.EntityDeathShot;
import xol.lostinfinity.projectile.entity.EntityDebtCollectorEffect;
import xol.lostinfinity.projectile.entity.EntityDestabilizer;
import xol.lostinfinity.projectile.entity.EntityDeviantDeployer;
import xol.lostinfinity.projectile.entity.EntityDeviantEvokerBomb;
import xol.lostinfinity.projectile.entity.EntityDeviantEvokerFangs;
import xol.lostinfinity.projectile.entity.EntityDeviantEvokerShot;
import xol.lostinfinity.projectile.entity.EntityDeviantSnowball;
import xol.lostinfinity.projectile.entity.EntityDeviantSpit;
import xol.lostinfinity.projectile.entity.EntityDeviantSucker;
import xol.lostinfinity.projectile.entity.EntityDoomBlast;
import xol.lostinfinity.projectile.entity.EntityDoomShot;
import xol.lostinfinity.projectile.entity.EntityDroidBall;
import xol.lostinfinity.projectile.entity.EntityDroidLaser;
import xol.lostinfinity.projectile.entity.EntityDroidSucker;
import xol.lostinfinity.projectile.entity.EntityDroidZapper;
import xol.lostinfinity.projectile.entity.EntityDryadsGripAttack;
import xol.lostinfinity.projectile.entity.EntityDuskerQueenWeb;
import xol.lostinfinity.projectile.entity.EntityEchoBlast;
import xol.lostinfinity.projectile.entity.EntityEchoOfGravity;
import xol.lostinfinity.projectile.entity.EntityEffigyShot;
import xol.lostinfinity.projectile.entity.EntityElaraShot;
import xol.lostinfinity.projectile.entity.EntityElementiumAir;
import xol.lostinfinity.projectile.entity.EntityElementiumBlight;
import xol.lostinfinity.projectile.entity.EntityElementiumEarth;
import xol.lostinfinity.projectile.entity.EntityElementiumFire;
import xol.lostinfinity.projectile.entity.EntityElementiumPlague;
import xol.lostinfinity.projectile.entity.EntityElementiumPrime;
import xol.lostinfinity.projectile.entity.EntityElementiumShadow;
import xol.lostinfinity.projectile.entity.EntityElementiumWater;
import xol.lostinfinity.projectile.entity.EntityEmberShot;
import xol.lostinfinity.projectile.entity.EntityEnergyBurst;
import xol.lostinfinity.projectile.entity.EntityExothermite;
import xol.lostinfinity.projectile.entity.EntityExplosiveGoo;
import xol.lostinfinity.projectile.entity.EntityFallingStar;
import xol.lostinfinity.projectile.entity.EntityFearBomb;
import xol.lostinfinity.projectile.entity.EntityFirePellet;
import xol.lostinfinity.projectile.entity.EntityFluxBall;
import xol.lostinfinity.projectile.entity.EntityForbiddenBrand;
import xol.lostinfinity.projectile.entity.EntityFountainPellet;
import xol.lostinfinity.projectile.entity.EntityFractureBomb;
import xol.lostinfinity.projectile.entity.EntityGalaxyBlast;
import xol.lostinfinity.projectile.entity.EntityGalaxyBomb;
import xol.lostinfinity.projectile.entity.EntityGalaxyDragonFireball;
import xol.lostinfinity.projectile.entity.EntityGalaxyKnife;
import xol.lostinfinity.projectile.entity.EntityGalaxyLaser;
import xol.lostinfinity.projectile.entity.EntityGalaxySpell;
import xol.lostinfinity.projectile.entity.EntityGenericBomb;
import xol.lostinfinity.projectile.entity.EntityGloomSpell;
import xol.lostinfinity.projectile.entity.EntityGravityWarper;
import xol.lostinfinity.projectile.entity.EntityHomingBlight;
import xol.lostinfinity.projectile.entity.EntityInfinityTrident;
import xol.lostinfinity.projectile.entity.EntityInfinityTridentMK2;
import xol.lostinfinity.projectile.entity.EntityInkBomb;
import xol.lostinfinity.projectile.entity.EntityInkShot;
import xol.lostinfinity.projectile.entity.EntityIonicChakram;
import xol.lostinfinity.projectile.entity.EntityLaserBlast;
import xol.lostinfinity.projectile.entity.EntityLaserGunBeam;
import xol.lostinfinity.projectile.entity.EntityLaunchedKnife;
import xol.lostinfinity.projectile.entity.EntityLeviathanBreath;
import xol.lostinfinity.projectile.entity.EntityLeviathanTeslaOrb;
import xol.lostinfinity.projectile.entity.EntityLeviathanTracer;
import xol.lostinfinity.projectile.entity.EntityLuminousGuardianLaser;
import xol.lostinfinity.projectile.entity.EntityMeteor;
import xol.lostinfinity.projectile.entity.EntityMicroRocket;
import xol.lostinfinity.projectile.entity.EntityMiniChakram;
import xol.lostinfinity.projectile.entity.EntityMobComet;
import xol.lostinfinity.projectile.entity.EntityMortarShot;
import xol.lostinfinity.projectile.entity.EntityNebulaSpell;
import xol.lostinfinity.projectile.entity.EntityPicklePortal;
import xol.lostinfinity.projectile.entity.EntityPiercingShot;
import xol.lostinfinity.projectile.entity.EntityPlagueBlast;
import xol.lostinfinity.projectile.entity.EntityPoisonousBubble;
import xol.lostinfinity.projectile.entity.EntityPortalEffect;
import xol.lostinfinity.projectile.entity.EntityPowerPulse;
import xol.lostinfinity.projectile.entity.EntityProjectileKiller;
import xol.lostinfinity.projectile.entity.EntityPuzzleMasterArrow;
import xol.lostinfinity.projectile.entity.EntityRainDrop;
import xol.lostinfinity.projectile.entity.EntityRecursor;
import xol.lostinfinity.projectile.entity.EntityRockshot;
import xol.lostinfinity.projectile.entity.EntityScreamerBlast;
import xol.lostinfinity.projectile.entity.EntityScreamerPortalEffect;
import xol.lostinfinity.projectile.entity.EntitySelectionLaser;
import xol.lostinfinity.projectile.entity.EntitySelectorAttack;
import xol.lostinfinity.projectile.entity.EntitySkullShot;
import xol.lostinfinity.projectile.entity.EntitySkyStrike;
import xol.lostinfinity.projectile.entity.EntitySkycrabAttack;
import xol.lostinfinity.projectile.entity.EntitySonicAttack;
import xol.lostinfinity.projectile.entity.EntitySoundwaveBullet;
import xol.lostinfinity.projectile.entity.EntitySpiderBlast;
import xol.lostinfinity.projectile.entity.EntitySpikeBall;
import xol.lostinfinity.projectile.entity.EntityStarBlast;
import xol.lostinfinity.projectile.entity.EntityStickyProjectile;
import xol.lostinfinity.projectile.entity.EntityStrappedFirework;
import xol.lostinfinity.projectile.entity.EntityStunAttack;
import xol.lostinfinity.projectile.entity.EntitySunstoneRock;
import xol.lostinfinity.projectile.entity.EntityTNTStrapper;
import xol.lostinfinity.projectile.entity.EntityTekBullet;
import xol.lostinfinity.projectile.entity.EntityTentacleSeed;
import xol.lostinfinity.projectile.entity.EntityTetherBall;
import xol.lostinfinity.projectile.entity.EntityTitanRing;
import xol.lostinfinity.projectile.entity.EntityTormentorChain;
import xol.lostinfinity.projectile.entity.EntityUltraCrystalGel;
import xol.lostinfinity.projectile.entity.EntityUnstableBalloon;
import xol.lostinfinity.projectile.entity.EntityVeloMagic;
import xol.lostinfinity.projectile.entity.EntityVenomBlast;
import xol.lostinfinity.projectile.entity.EntityVoidLaser;
import xol.lostinfinity.projectile.entity.EntityVoltaicIonizerAttack;
import xol.lostinfinity.projectile.entity.EntityWandAttack;
import xol.lostinfinity.projectile.entity.EntityWhirlpool;
import xol.lostinfinity.projectile.entity.EntityWitchMagic;
import xol.lostinfinity.projectile.entity.EntityWitherBomb;
import xol.lostinfinity.projectile.entity.EntityWizardBlast;
import xol.lostinfinity.projectile.entity.EntityWormholeShot;
import xol.lostinfinity.projectile.entity.EntityXSonicAttack;
import xol.lostinfinity.projectile.entity.EntityZenonShot;
import xol.lostinfinity.projectile.render.RenderAlternatingArrow;
import xol.lostinfinity.projectile.render.RenderAlternatingThrowable;
import xol.lostinfinity.projectile.render.RenderAvenger;
import xol.lostinfinity.projectile.render.RenderAvengerLightning;
import xol.lostinfinity.projectile.render.RenderBarulChain;
import xol.lostinfinity.projectile.render.RenderBossPortalEffect;
import xol.lostinfinity.projectile.render.RenderChainOfVenomsAttack;
import xol.lostinfinity.projectile.render.RenderChampionDodgeball;
import xol.lostinfinity.projectile.render.RenderCryoBeamEffect;
import xol.lostinfinity.projectile.render.RenderDebtCollectorEffect;
import xol.lostinfinity.projectile.render.RenderDeviantEvokerFangs;
import xol.lostinfinity.projectile.render.RenderDuskerQueenWeb;
import xol.lostinfinity.projectile.render.RenderForbiddenBrand;
import xol.lostinfinity.projectile.render.RenderGalaxyDragonFireball;
import xol.lostinfinity.projectile.render.RenderInfinityThrowable;
import xol.lostinfinity.projectile.render.RenderInfinityTrident;
import xol.lostinfinity.projectile.render.RenderInfinityTridentMK2;
import xol.lostinfinity.projectile.render.RenderIonicChakram;
import xol.lostinfinity.projectile.render.RenderLaserGunBeam;
import xol.lostinfinity.projectile.render.RenderLeviathanBreath;
import xol.lostinfinity.projectile.render.RenderLightBeamEffect;
import xol.lostinfinity.projectile.render.RenderLuminousGuardianLaser;
import xol.lostinfinity.projectile.render.RenderMiniChakram;
import xol.lostinfinity.projectile.render.RenderNuclearExplosion;
import xol.lostinfinity.projectile.render.RenderPicklePortal;
import xol.lostinfinity.projectile.render.RenderPortalEffect;
import xol.lostinfinity.projectile.render.RenderScreamerPortalEffect;
import xol.lostinfinity.projectile.render.RenderSelectorAttack;
import xol.lostinfinity.projectile.render.RenderSpecialArrow;
import xol.lostinfinity.projectile.render.RenderTitanRing;
import xol.lostinfinity.projectile.render.RenderTormentorChain;
import xol.lostinfinity.projectile.render.RenderVoltaicIonizerAttack;
import xol.lostinfinity.projectile.render.RenderWandAttack;
import xol.lostinfinity.projectile.render.RenderWhirlpool;
import xol.lostinfinity.stone.EntityInfinityStone;
import xol.lostinfinity.stone.RenderInfinityStone;
import xol.lostinfinity.util.Reference;
@Mod.EventBusSubscriber
public class ModelRegistry {
    private static <T extends Entity> void registerRender(Class<T> entityClass, IRenderFactory<? super T> renderFactory) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
    }
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerAllModels(ModelRegistryEvent event) {
        for (Item item : ItemInit.ITEMS) {
            lostinfinity.proxy.registerItemRenderer(item, 0, "invetory");
        }
        Iterator<ItemLostArmor> it = ItemInit.ARMORS.iterator();
        while (it.hasNext()) {
            lostinfinity.proxy.registerItemRenderer((ItemLostArmor) it.next(), 0, "invetory");
        }
        registerRender(EntityTreadmillObstacle.class, manager -> {
            return new RenderTreadmillObstacle(manager);
        });
        registerRender(EntityTreadmillObstacleJumpable.class, manager2 -> {
            return new RenderTreadmillObstacleJumpable(manager2);
        });
        registerRender(EntityDimensionalMerchant.class, manager3 -> {
            return new RenderDimensionalMerchant(manager3);
        });
        registerRender(EntityDeviantCow.class, manager4 -> {
            return new RenderDeviantCow(manager4);
        });
        registerRender(EntityDeviantSheep.class, manager5 -> {
            return new RenderDeviantSheep(manager5);
        });
        registerRender(EntityDeviantPig.class, manager6 -> {
            return new RenderDeviantPig(manager6);
        });
        registerRender(EntityDeviantHorse.class, manager7 -> {
            return new RenderDeviantHorse(manager7);
        });
        registerRender(EntityDeviantEvoker.class, manager8 -> {
            return new RenderDeviantEvoker(manager8);
        });
        registerRender(EntityDeviantEvokerVex.class, manager9 -> {
            return new RenderDeviantEvokerVex(manager9);
        });
        registerRender(EntityDeviantBear.class, manager10 -> {
            return new RenderDeviantBear(manager10);
        });
        registerRender(EntityDeviantChicken.class, manager11 -> {
            return new RenderDeviantChicken(manager11);
        });
        registerRender(EntityDeviantShulker.class, manager12 -> {
            return new RenderDeviantShulker(manager12);
        });
        registerRender(EntityDeviantEnderman.class, manager13 -> {
            return new RenderDeviantEnderman(manager13);
        });
        registerRender(EntityDeviantGhast.class, manager14 -> {
            return new RenderDeviantGhast(manager14);
        });
        registerRender(EntityElara.class, manager15 -> {
            return new RenderElara(manager15);
        });
        registerRender(EntityDeviantSkyworm.class, manager16 -> {
            return new RenderDeviantSkyworm(manager16);
        });
        registerRender(EntityInfinityStone.class, manager17 -> {
            return new RenderInfinityStone(manager17);
        });
        registerRender(EntityUrogo.class, manager18 -> {
            return new RenderUrogo(manager18);
        });
        registerRender(EntityDeviantSkeleton.class, manager19 -> {
            return new RenderDeviantSkeleton(manager19);
        });
        registerRender(EntityDeviantCreeper.class, manager20 -> {
            return new RenderDeviantCreeper(manager20);
        });
        registerRender(EntityDroid.class, manager21 -> {
            return new RenderDroid(manager21);
        });
        registerRender(EntityDroidBoss.class, manager22 -> {
            return new RenderDroidBoss(manager22);
        });
        registerRender(EntityArenaEvent.class, manager23 -> {
            return new RenderArenaEvent(manager23);
        });
        registerRender(EntityLostDeviant.class, manager24 -> {
            return new RenderLostDeviant(manager24);
        });
        registerRender(EntityCourseRing.class, manager25 -> {
            return new RenderCourseRing(manager25);
        });
        registerRender(EntityLabWarrior.class, manager26 -> {
            return new RenderLabWarrior(manager26);
        });
        registerRender(EntityLabWizard.class, manager27 -> {
            return new RenderLabWizard(manager27);
        });
        registerRender(EntitySandAttack.class, manager28 -> {
            return new RenderSandAttack(manager28);
        });
        registerRender(EntityDeviantSpider.class, manager29 -> {
            return new RenderDeviantSpider(manager29);
        });
        registerRender(EntityAspect.class, manager30 -> {
            return new RenderAspect(manager30);
        });
        registerRender(EntitySlimeStrider.class, manager31 -> {
            return new RenderSlimeStrider(manager31);
        });
        registerRender(EntityVelo.class, manager32 -> {
            return new RenderVelo(manager32);
        });
        registerRender(EntityGalaxyBeast.class, manager33 -> {
            return new RenderGalaxyBeast(manager33);
        });
        registerRender(EntityGalaxyBeast.class, manager34 -> {
            return new RenderGalaxyBeast(manager34);
        });
        registerRender(EntityGalaxySorcerer.class, manager35 -> {
            return new RenderGalaxySorcerer(manager35);
        });
        registerRender(EntityGalaxyGladiator.class, manager36 -> {
            return new RenderGalaxyGladiator(manager36);
        });
        registerRender(EntityDeviantAmalgam.class, manager37 -> {
            return new RenderDeviantAmalgam(manager37);
        });
        registerRender(EntityGalaxySpire.class, manager38 -> {
            return new RenderGalaxySpire(manager38);
        });
        registerRender(EntityStarfiend.class, manager39 -> {
            return new RenderStarfiend(manager39);
        });
        registerRender(EntityBloodhunter.class, manager40 -> {
            return new RenderBloodhunter(manager40);
        });
        registerRender(EntityPuzzleMaster.class, manager41 -> {
            return new RenderPuzzleMaster(manager41);
        });
        registerRender(EntityRikarus.class, manager42 -> {
            return new RenderRikarus(manager42);
        });
        registerRender(EntityRestorationCrystal.class, manager43 -> {
            return new RenderRestorationCrystal(manager43);
        });
        registerRender(EntityDeviantWitch.class, manager44 -> {
            return new RenderDeviantWitch(manager44);
        });
        registerRender(EntityMirrorZombie.class, manager45 -> {
            return new RenderMirrorZombie(manager45);
        });
        registerRender(EntityDeviantSlimeStrider.class, manager46 -> {
            return new RenderDeviantSlimeStrider(manager46);
        });
        registerRender(EntityDeviantGuardian.class, manager47 -> {
            return new RenderDeviantGuardian(manager47);
        });
        registerRender(EntityDeviantBlaze.class, manager48 -> {
            return new RenderDeviantBlaze(manager48);
        });
        registerRender(EntityTitanBlaze.class, manager49 -> {
            return new RenderTitanBlaze(manager49);
        });
        registerRender(EntityTitanShulker.class, manager50 -> {
            return new RenderTitanShulker(manager50);
        });
        registerRender(EntityTitanSpider.class, manager51 -> {
            return new RenderTitanSpider(manager51);
        });
        registerRender(EntityTitanSkeleton.class, manager52 -> {
            return new RenderTitanSkeleton(manager52);
        });
        registerRender(EntityTrialObserver.class, manager53 -> {
            return new RenderTrialObserver(manager53);
        });
        registerRender(EntityHanger.class, manager54 -> {
            return new RenderHanger(manager54);
        });
        registerRender(EntityAcidback.class, manager55 -> {
            return new RenderAcidback(manager55);
        });
        registerRender(EntityReflectal.class, manager56 -> {
            return new RenderReflectal(manager56);
        });
        registerRender(EntityGravhead.class, manager57 -> {
            return new RenderGravhead(manager57);
        });
        registerRender(EntityRockpest.class, manager58 -> {
            return new RenderRockpest(manager58);
        });
        registerRender(EntitySpinovern.class, manager59 -> {
            return new RenderSpinovern(manager59);
        });
        registerRender(EntityHypnosaur.class, manager60 -> {
            return new RenderHypnosaur(manager60);
        });
        registerRender(EntityChomper.class, manager61 -> {
            return new RenderChomper(manager61);
        });
        registerRender(EntityRibrex.class, manager62 -> {
            return new RenderRibrex(manager62);
        });
        registerRender(EntityGnawer.class, manager63 -> {
            return new RenderGnawer(manager63);
        });
        registerRender(EntityScreacher.class, manager64 -> {
            return new RenderScreacher(manager64);
        });
        registerRender(EntityEyeSlug.class, manager65 -> {
            return new RenderEyeSlug(manager65);
        });
        registerRender(EntityFlutterfyre.class, manager66 -> {
            return new RenderFlutterfyre(manager66);
        });
        registerRender(EntityDeviantSnowman.class, manager67 -> {
            return new RenderDeviantSnowman(manager67);
        });
        registerRender(EntityNuxuro.class, manager68 -> {
            return new RenderNuxuro(manager68);
        });
        registerRender(EntityClyster.class, manager69 -> {
            return new RenderClyster(manager69);
        });
        registerRender(EntityCrawker.class, manager70 -> {
            return new RenderCrawker(manager70);
        });
        registerRender(EntityDusker.class, manager71 -> {
            return new RenderDusker(manager71);
        });
        registerRender(EntityExplosect.class, manager72 -> {
            return new RenderExplosect(manager72);
        });
        registerRender(EntityFlapper.class, manager73 -> {
            return new RenderFlapper(manager73);
        });
        registerRender(EntityFlurky.class, manager74 -> {
            return new RenderFlurky(manager74);
        });
        registerRender(EntityFyreweed.class, manager75 -> {
            return new RenderFyreweed(manager75);
        });
        registerRender(EntityGrappler.class, manager76 -> {
            return new RenderGrappler(manager76);
        });
        registerRender(EntityHurler.class, manager77 -> {
            return new RenderHurler(manager77);
        });
        registerRender(EntityPhaser.class, manager78 -> {
            return new RenderPhaser(manager78);
        });
        registerRender(EntityShimmer.class, manager79 -> {
            return new RenderShimmer(manager79);
        });
        registerRender(EntitySpyker.class, manager80 -> {
            return new RenderSpyker(manager80);
        });
        registerRender(EntityDeviantMooshroom.class, manager81 -> {
            return new RenderDeviantMooshroom(manager81);
        });
        registerRender(EntityDeviantZombie.class, manager82 -> {
            return new RenderDeviantZombie(manager82);
        });
        registerRender(EntityDeviantSlime.class, manager83 -> {
            return new RenderDeviantSlime(manager83);
        });
        registerRender(EntityDeviantPiglin.class, manager84 -> {
            return new RenderDeviantPiglin(manager84);
        });
        registerRender(EntityDeviantLlama.class, manager85 -> {
            return new RenderDeviantLlama(manager85);
        });
        registerRender(EntityDeviantStray.class, manager86 -> {
            return new RenderDeviantStray(manager86);
        });
        registerRender(EntityDeviantVex.class, manager87 -> {
            return new RenderDeviantVex(manager87);
        });
        registerRender(EntityDeviantMagmacube.class, manager88 -> {
            return new RenderDeviantMagmacube(manager88);
        });
        registerRender(EntityArash.class, manager89 -> {
            return new RenderArash(manager89);
        });
        registerRender(EntityAlestria.class, manager90 -> {
            return new RenderAlestria(manager90);
        });
        registerRender(EntityAtlasSpire.class, manager91 -> {
            return new RenderAtlasSpire(manager91);
        });
        registerRender(EntityDarrio.class, manager92 -> {
            return new RenderDarrio(manager92);
        });
        registerRender(EntityBarul.class, manager93 -> {
            return new RenderBarul(manager93);
        });
        registerRender(EntityAtlasCrystal.class, manager94 -> {
            return new RenderAtlasCrystal(manager94);
        });
        registerRender(EntitySentryCrystal.class, manager95 -> {
            return new RenderSentryCrystal(manager95);
        });
        registerRender(EntityDeviantCrystal.class, manager96 -> {
            return new RenderDeviantCrystal(manager96);
        });
        registerRender(EntityTitanStray.class, manager97 -> {
            return new RenderTitanStray(manager97);
        });
        registerRender(EntityTitanVex.class, manager98 -> {
            return new RenderTitanVex(manager98);
        });
        registerRender(EntityTitanCreeper.class, manager99 -> {
            return new RenderTitanCreeper(manager99);
        });
        registerRender(EntityTitanEnderman.class, manager100 -> {
            return new RenderTitanEnderman(manager100);
        });
        registerRender(EntityTitanPiglin.class, manager101 -> {
            return new RenderTitanPiglin(manager101);
        });
        registerRender(EntityTitanLlama.class, manager102 -> {
            return new RenderTitanLlama(manager102);
        });
        registerRender(EntityTitanZombie.class, manager103 -> {
            return new RenderTitanZombie(manager103);
        });
        registerRender(EntityTitanMagmacube.class, manager104 -> {
            return new RenderTitanMagmacube(manager104);
        });
        registerRender(EntityGloop.class, manager105 -> {
            return new RenderGloop(manager105);
        });
        registerRender(EntityGloopMother.class, manager106 -> {
            return new RenderGloopMother(manager106);
        });
        registerRender(EntityCyclos.class, manager107 -> {
            return new RenderCyclos(manager107);
        });
        registerRender(EntityClinger.class, manager108 -> {
            return new RenderClinger(manager108);
        });
        registerRender(EntityDeviantDimTrader.class, manager109 -> {
            return new RenderDeviantDimTrader(manager109);
        });
        registerRender(EntityMemPuzzleMerchant.class, manager110 -> {
            return new RenderMemPuzzleMerchant(manager110);
        });
        registerRender(EntitySkybooster.class, manager111 -> {
            return new RenderSkybooster(manager111);
        });
        registerRender(EntityRocketStrappedExplosive.class, manager112 -> {
            return new RenderRocketStrappedExplosive(manager112);
        });
        registerRender(EntityMarkOfInfiniteDespair.class, manager113 -> {
            return new RenderMarkOfInfiniteDespair(manager113);
        });
        registerRender(EntitySpectre.class, manager114 -> {
            return new RenderSpectre(manager114);
        });
        registerRender(EntityContraderMarket.class, manager115 -> {
            return new RenderContraderMarket(manager115);
        });
        registerRender(EntityContraderHolodeck.class, manager116 -> {
            return new RenderContraderHolodeck(manager116);
        });
        registerRender(EntityContraderHunters.class, manager117 -> {
            return new RenderContraderHunters(manager117);
        });
        registerRender(EntityContraderLightbridge.class, manager118 -> {
            return new RenderContraderLightbridge(manager118);
        });
        registerRender(EntityContraderTargets.class, manager119 -> {
            return new RenderContraderTargets(manager119);
        });
        registerRender(EntityContraderTrampolineDodgeball.class, manager120 -> {
            return new RenderContraderTrampolineDodgeball(manager120);
        });
        registerRender(EntityOperatorHolodeck.class, manager121 -> {
            return new RenderOperatorHolodeck(manager121);
        });
        registerRender(EntityOperatorHunters.class, manager122 -> {
            return new RenderOperatorHunters(manager122);
        });
        registerRender(EntityOperatorTargets.class, manager123 -> {
            return new RenderOperatorTargets(manager123);
        });
        registerRender(EntityOperatorTrampolineDodgeball.class, manager124 -> {
            return new RenderOperatorTrampolineDodgeball(manager124);
        });
        registerRender(EntityControllerHolodeck.class, manager125 -> {
            return new RenderControllerHolodeck(manager125);
        });
        registerRender(EntityControllerHunters.class, manager126 -> {
            return new RenderControllerHunters(manager126);
        });
        registerRender(EntityControllerTargets.class, manager127 -> {
            return new RenderControllerTargets(manager127);
        });
        registerRender(EntityControllerLightBridge.class, manager128 -> {
            return new RenderControllerLightBridge(manager128);
        });
        registerRender(EntityControllerDuelArena.class, manager129 -> {
            return new RenderControllerDuelArena(manager129);
        });
        registerRender(EntityControllerTrampolineDodgeball.class, manager130 -> {
            return new RenderControllerTrampolineDodgeball(manager130);
        });
        registerRender(EntityOperatorLightBridge.class, manager131 -> {
            return new RenderOperatorLightBridge(manager131);
        });
        registerRender(EntityOperatorDuelArena.class, manager132 -> {
            return new RenderOperatorDuelArena(manager132);
        });
        registerRender(EntityContestHologram.class, manager133 -> {
            return new RenderContestHologram(manager133);
        });
        registerRender(EntityParticleTrojan.class, manager134 -> {
            return new RenderParticleTrojan(manager134);
        });
        registerRender(EntityPlasmaExplosion.class, manager135 -> {
            return new RenderPlasmaExplosion(manager135);
        });
        registerRender(EntityAzross.class, manager136 -> {
            return new RenderAzross(manager136);
        });
        registerRender(EntityDuskerQueen.class, manager137 -> {
            return new RenderDuskerQueen(manager137);
        });
        registerRender(EntityOperatorRedlight.class, manager138 -> {
            return new RenderOperatorRedlight(manager138);
        });
        registerRender(EntityControllerRedlight.class, manager139 -> {
            return new RenderControllerRedlight(manager139);
        });
        registerRender(EntityContraderRedlight.class, manager140 -> {
            return new RenderContraderRedlight(manager140);
        });
        registerRender(EntityOperatorBattleSnakes.class, manager141 -> {
            return new RenderOperatorBattleSnakes(manager141);
        });
        registerRender(EntityControllerBattleSnakes.class, manager142 -> {
            return new RenderControllerBattleSnakes(manager142);
        });
        registerRender(EntityContraderBattleSnakes.class, manager143 -> {
            return new RenderContraderBattleSnakes(manager143);
        });
        registerRender(EntityOperatorLaserTag.class, manager144 -> {
            return new RenderOperatorLaserTag(manager144);
        });
        registerRender(EntityControllerLaserTag.class, manager145 -> {
            return new RenderControllerLaserTag(manager145);
        });
        registerRender(EntityContraderLaserTag.class, manager146 -> {
            return new RenderContraderLaserTag(manager146);
        });
        registerRender(EntityOperatorParkour.class, manager147 -> {
            return new RenderOperatorParkour(manager147);
        });
        registerRender(EntityControllerParkour.class, manager148 -> {
            return new RenderControllerParkour(manager148);
        });
        registerRender(EntityContraderParkour.class, manager149 -> {
            return new RenderContraderParkour(manager149);
        });
        registerRender(EntityOperatorInkBattle.class, manager150 -> {
            return new RenderOperatorInkBattle(manager150);
        });
        registerRender(EntityControllerInkBattle.class, manager151 -> {
            return new RenderControllerInkBattle(manager151);
        });
        registerRender(EntityContraderInkBattle.class, manager152 -> {
            return new RenderContraderInkBattle(manager152);
        });
        registerRender(EntityOperatorTreadmill.class, manager153 -> {
            return new RenderOperatorTreadmill(manager153);
        });
        registerRender(EntityControllerTreadmill.class, manager154 -> {
            return new RenderControllerTreadmill(manager154);
        });
        registerRender(EntityContraderTreadmill.class, manager155 -> {
            return new RenderContraderTreadmill(manager155);
        });
        registerRender(EntityDeviantWither.class, manager156 -> {
            return new RenderDeviantWither(manager156);
        });
        registerRender(EntityWitherSkullling.class, manager157 -> {
            return new RenderWitherSkullling(manager157);
        });
        registerRender(EntityArcheologist.class, manager158 -> {
            return new RenderArcheologist(manager158);
        });
        registerRender(EntityGlangler.class, manager159 -> {
            return new RenderGlangler(manager159);
        });
        registerRender(EntityGlochipper.class, manager160 -> {
            return new RenderGlochipper(manager160);
        });
        registerRender(EntityGloboon.class, manager161 -> {
            return new RenderGloboon(manager161);
        });
        registerRender(EntityEssenceDweller.class, manager162 -> {
            return new RenderEssenceDweller(manager162);
        });
        registerRender(EntityEssenceIdol.class, manager163 -> {
            return new RenderEssenceIdol(manager163);
        });
        registerRender(EntityGlobro.class, manager164 -> {
            return new RenderGlobro(manager164);
        });
        registerRender(EntityAugmenticon.class, manager165 -> {
            return new RenderAugmenticon(manager165);
        });
        registerRender(EntityGalaxyGulper.class, manager166 -> {
            return new RenderGalaxyGulper(manager166);
        });
        registerRender(EntityLaserSpire.class, manager167 -> {
            return new RenderLaserSpire(manager167);
        });
        registerRender(EntityIonExplosion.class, manager168 -> {
            return new RenderIonExplosion(manager168);
        });
        registerRender(EntityGiantFlapper.class, manager169 -> {
            return new RenderGiantFlapper(manager169);
        });
        registerRender(EntityDeviantGolem.class, manager170 -> {
            return new RenderDeviantGolem(manager170);
        });
        registerRender(EntityTornIndividual.class, manager171 -> {
            return new RenderTornIndividual(manager171);
        });
        registerRender(EntityDeviantCaveSpider.class, manager172 -> {
            return new RenderDeviantCaveSpider(manager172);
        });
        registerRender(EntityFeralMerchant.class, manager173 -> {
            return new RenderFeralMerchant(manager173);
        });
        registerRender(EntityUnstableMerchant.class, manager174 -> {
            return new RenderUnstableMerchant(manager174);
        });
        registerRender(EntityStickler.class, manager175 -> {
            return new RenderStickler(manager175);
        });
        registerRender(EntityVectosect.class, manager176 -> {
            return new RenderVectosect(manager176);
        });
        registerRender(EntityStickyBomb.class, manager177 -> {
            return new RenderStickyBomb(manager177);
        });
        registerRender(EntityRavager.class, manager178 -> {
            return new RenderRavager(manager178);
        });
        registerRender(EntityVycellia.class, manager179 -> {
            return new RenderVycellia(manager179);
        });
        registerRender(EntityNightshyre.class, manager180 -> {
            return new RenderNightshyre(manager180);
        });
        registerRender(EntityGiantFyreweed.class, manager181 -> {
            return new RenderGiantFyreweed(manager181);
        });
        registerRender(EntityBomberBomb.class, manager182 -> {
            return new RenderBomberBomb(manager182);
        });
        registerRender(EntitySkycrab.class, manager183 -> {
            return new RenderSkycrab(manager183);
        });
        registerRender(EntityGlomite.class, manager184 -> {
            return new RenderGlomite(manager184);
        });
        registerRender(EntityTNTZombie.class, manager185 -> {
            return new RenderTNTZombie(manager185);
        });
        registerRender(EntityDeviantBat.class, manager186 -> {
            return new RenderDeviantBat(manager186);
        });
        registerRender(EntityContraderBombers.class, manager187 -> {
            return new RenderContraderBombers(manager187);
        });
        registerRender(EntityControllerBombers.class, manager188 -> {
            return new RenderControllerBombers(manager188);
        });
        registerRender(EntityOperatorBombers.class, manager189 -> {
            return new RenderOperatorBombers(manager189);
        });
        registerRender(EntityRockslug.class, manager190 -> {
            return new RenderRockslug(manager190);
        });
        registerRender(EntityGiantRockslug.class, manager191 -> {
            return new RenderGiantRockslug(manager191);
        });
        registerRender(EntityLightGame.class, manager192 -> {
            return new RenderLightPuzzleMerchant(manager192);
        });
        registerRender(EntityWhackGameMerchant.class, manager193 -> {
            return new RenderWhackGameMerchant(manager193);
        });
        registerRender(EntityConnectGameMerchant.class, manager194 -> {
            return new RenderConnectGameMerchant(manager194);
        });
        registerRender(EntityRhythmGameMerchant.class, manager195 -> {
            return new RenderRhythmGameMerchant(manager195);
        });
        registerRender(EntityFlutterbee.class, manager196 -> {
            return new RenderFlutterbee(manager196);
        });
        registerRender(EntityOzor.class, manager197 -> {
            return new RenderOzor(manager197);
        });
        registerRender(EntityOzorDecoy.class, manager198 -> {
            return new RenderOzorDecoy(manager198);
        });
        registerRender(EntityTotemMoon.class, manager199 -> {
            return new RenderTotemMoon(manager199);
        });
        registerRender(EntityTotemPylon.class, manager200 -> {
            return new RenderTotemPylon(manager200);
        });
        registerRender(EntityThundyron.class, manager201 -> {
            return new RenderThundyron(manager201);
        });
        registerRender(EntityCryonus.class, manager202 -> {
            return new RenderCryonus(manager202);
        });
        registerRender(EntityThunderBomb.class, manager203 -> {
            return new RenderThunderBomb(manager203);
        });
        registerRender(EntityStormBomb.class, manager204 -> {
            return new RenderStormBomb(manager204);
        });
        registerRender(EntityPlasmaBomb.class, manager205 -> {
            return new RenderPlasmaBomb(manager205);
        });
        registerRender(EntityPipeGameMerchant.class, manager206 -> {
            return new RenderPipeGameMerchant(manager206);
        });
        registerRender(EntityPearlCollector.class, manager207 -> {
            return new RenderPearlCollector(manager207);
        });
        registerRender(EntityZenon.class, manager208 -> {
            return new RenderZenon(manager208);
        });
        registerRender(EntityCrusher.class, manager209 -> {
            return new RenderCrusher(manager209);
        });
        registerRender(EntityDrippler.class, manager210 -> {
            return new RenderDrippler(manager210);
        });
        registerRender(EntityGorger.class, manager211 -> {
            return new RenderGorger(manager211);
        });
        registerRender(EntityDoublejaw.class, manager212 -> {
            return new RenderDoublejaw(manager212);
        });
        registerRender(EntityMinimite.class, manager213 -> {
            return new RenderMinimite(manager213);
        });
        registerRender(EntityClusterweed.class, manager214 -> {
            return new RenderClusterweed(manager214);
        });
        registerRender(EntityWisp.class, manager215 -> {
            return new RenderWisp(manager215);
        });
        registerRender(EntityTetherbug.class, manager216 -> {
            return new RenderTetherbug(manager216);
        });
        registerRender(EntityCellGameMerchant.class, manager217 -> {
            return new RenderCellGameMerchant(manager217);
        });
        registerRender(EntityLuminousGuardian.class, manager218 -> {
            return new RenderLuminousGuardian(manager218);
        });
        registerRender(EntityVilebulb.class, manager219 -> {
            return new RenderVilebulb(manager219);
        });
        registerRender(EntitySightwalker.class, manager220 -> {
            return new RenderSightwalker(manager220);
        });
        registerRender(EntityWeaver.class, manager221 -> {
            return new RenderWeaver(manager221);
        });
        registerRender(EntityDoomDog.class, manager222 -> {
            return new RenderDoomDog(manager222);
        });
        registerRender(EntityBlisterweed.class, manager223 -> {
            return new RenderBlisterweed(manager223);
        });
        registerRender(EntityGrubber.class, manager224 -> {
            return new RenderGrubber(manager224);
        });
        registerRender(EntityLeer.class, manager225 -> {
            return new RenderLeer(manager225);
        });
        registerRender(EntityTitanopod.class, manager226 -> {
            return new RenderTitanopod(manager226);
        });
        registerRender(EntityFlashfly.class, manager227 -> {
            return new RenderFlashfly(manager227);
        });
        registerRender(EntitySnapper.class, manager228 -> {
            return new RenderSnapper(manager228);
        });
        registerRender(EntityTerrorFly.class, manager229 -> {
            return new RenderTerrorFly(manager229);
        });
        registerRender(EntityTentaclon.class, manager230 -> {
            return new RenderTentaclon(manager230);
        });
        registerRender(EntityGalacticTerror.class, manager231 -> {
            return new RenderGalacticTerror(manager231);
        });
        registerRender(EntityDeviantWitherSkeleton.class, manager232 -> {
            return new RenderDeviantWitherSkeleton(manager232);
        });
        registerRender(EntityPickleMan.class, manager233 -> {
            return new RenderPickleMan(manager233);
        });
        registerRender(EntityWhisper.class, manager234 -> {
            return new RenderWhisper(manager234);
        });
        registerRender(EntityTorpedon.class, manager235 -> {
            return new RenderTorpedon(manager235);
        });
        registerRender(EntityLurcher.class, manager236 -> {
            return new RenderLurcher(manager236);
        });
        registerRender(EntityMortarCannon.class, manager237 -> {
            return new RenderMortarCannon(manager237);
        });
        registerRender(EntityClusterCannon.class, manager238 -> {
            return new RenderClusterCannon(manager238);
        });
        registerRender(EntityDeviantOcelote.class, manager239 -> {
            return new RenderDeviantOcelote(manager239);
        });
        registerRender(EntityLivorax.class, manager240 -> {
            return new RenderLivorax(manager240);
        });
        registerRender(EntityCaveTerror.class, manager241 -> {
            return new RenderCaveTerror(manager241);
        });
        registerRender(EntityRift.class, manager242 -> {
            return new RenderRift(manager242);
        });
        registerRender(EntityUnstableRift.class, manager243 -> {
            return new RenderUnstableRift(manager243);
        });
        registerRender(EntityNat.class, manager244 -> {
            return new RenderNat(manager244);
        });
        registerRender(EntitySentry.class, manager245 -> {
            return new RenderSentry(manager245);
        });
        registerRender(EntityTotemSplitter.class, manager246 -> {
            return new RenderTotemSplitter(manager246);
        });
        registerRender(EntityTentacleTrap.class, manager247 -> {
            return new RenderTentacleTrap(manager247);
        });
        registerRender(EntityScorpwing.class, manager248 -> {
            return new RenderScorpwing(manager248);
        });
        registerRender(EntityOrbiter.class, manager249 -> {
            return new RenderOrbiter(manager249);
        });
        registerRender(EntitySkyre.class, manager250 -> {
            return new RenderSkyre(manager250);
        });
        registerRender(EntityDoomsday.class, manager251 -> {
            return new RenderDoomsday(manager251);
        });
        registerRender(EntityRockworm.class, manager252 -> {
            return new RenderRockworm(manager252);
        });
        registerRender(EntityKalikos.class, manager253 -> {
            return new RenderKalikos(manager253);
        });
        registerRender(EntityGhostCopy.class, manager254 -> {
            return new RenderGhostCopy(manager254);
        });
        registerRender(EntityDoublerang.class, manager255 -> {
            return new RenderDoublerang(manager255);
        });
        registerRender(EntityLongfin.class, manager256 -> {
            return new RenderLongfin(manager256);
        });
        registerRender(EntityGlowfish.class, manager257 -> {
            return new RenderGlowfish(manager257);
        });
        registerRender(EntityOctobrella.class, manager258 -> {
            return new RenderOctobrella(manager258);
        });
        registerRender(EntityRayfish.class, manager259 -> {
            return new RenderRayfish(manager259);
        });
        registerRender(EntityUnderfin.class, manager260 -> {
            return new RenderUnderfin(manager260);
        });
        registerRender(EntityEelShark.class, manager261 -> {
            return new RenderEelShark(manager261);
        });
        registerRender(EntityChemist.class, manager262 -> {
            return new RenderChemist(manager262);
        });
        registerRender(EntityDeviantSquid.class, manager263 -> {
            return new RenderDeviantSquid(manager263);
        });
        registerRender(EntityScreamer.class, manager264 -> {
            return new RenderScreamer(manager264);
        });
        registerRender(EntityNebulaGrunt.class, RenderNebulaGrunt::new);
        registerRender(EntityNebulaWizard.class, RenderNebulaWizard::new);
        registerRender(EntityNebulaGiant.class, RenderNebulaGiant::new);
        registerRender(EntityLeviathanController.class, RenderLeviathanController::new);
        registerRender(EntityLeviathanHead.class, RenderLeviathanSegment::new);
        registerRender(EntityLeviathanSegment.class, RenderLeviathanSegment::new);
        registerRender(EntityLeviathanTail.class, RenderLeviathanSegment::new);
        registerRender(EntitySeaSerpentController.class, RenderSeaSerpentController::new);
        registerRender(EntitySeaSerpentHead.class, RenderSeaSerpentSegment::new);
        registerRender(EntitySeaSerpentSegment.class, RenderSeaSerpentSegment::new);
        registerRender(EntitySeaSerpentTail.class, RenderSeaSerpentSegment::new);
        registerRender(EntityGalaxyDragon.class, RenderGalaxyDragon::new);
        registerRender(EntityXScreacher.class, RenderXScreacher::new);
        registerRender(EntityCrabulon.class, RenderCrabulon::new);
        registerRender(EntityRibshark.class, RenderRibshark::new);
        registerRender(EntityMushmerra.class, RenderMushmerra::new);
        registerRender(EntityMushmerraClone.class, RenderMushmerraClone::new);
        registerRender(EntityShroomite.class, RenderShroomite::new);
        registerRender(EntityDeviantWolf.class, RenderDeviantWolf::new);
        registerRender(EntityDeviantHusk.class, RenderDeviantHusk::new);
        registerRender(EntityAndromedaController.class, RenderAndromedaController::new);
        registerRender(EntityAndromedaSegment.class, RenderAndromedaSegment::new);
        registerRender(EntityFungfly.class, RenderFungfly::new);
        registerRender(EntityCthulhuCloud.class, RenderCthulhuCloud::new);
        registerRender(EntityDeviantEvokerFangs.class, RenderDeviantEvokerFangs::new);
        registerRender(EntityElaraShot.class, manager265 -> {
            return new RenderInfinityThrowable(manager265, new ResourceLocation(Reference.MODID, "textures/projectiles/elarashot.png"));
        });
        registerRender(EntityDroidBall.class, manager266 -> {
            return new RenderInfinityThrowable(manager266, new ResourceLocation(Reference.MODID, "textures/projectiles/droidball.png"));
        });
        registerRender(EntityDroidZapper.class, manager267 -> {
            return new RenderInfinityThrowable(manager267, new ResourceLocation(Reference.MODID, "textures/projectiles/droidzapper.png"));
        });
        registerRender(EntitySkullShot.class, manager268 -> {
            return new RenderInfinityThrowable(manager268, new ResourceLocation(Reference.MODID, "textures/projectiles/skullshot.png"));
        });
        registerRender(EntityDeviantEvokerShot.class, manager269 -> {
            return new RenderInfinityThrowable(manager269, new ResourceLocation(Reference.MODID, "textures/projectiles/deviantevokershot.png"));
        });
        registerRender(EntityDeviantEvokerBomb.class, manager270 -> {
            return new RenderInfinityThrowable(manager270, new ResourceLocation(Reference.MODID, "textures/projectiles/deviantevokerbomb.png"));
        });
        registerRender(EntityDoomShot.class, manager271 -> {
            return new RenderInfinityThrowable(manager271, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/doomshot.png"));
        });
        registerRender(EntityRainDrop.class, manager272 -> {
            return new RenderInfinityThrowable(manager272, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/rain_drop.png"));
        });
        registerRender(EntityAcidRainDrop.class, manager273 -> {
            return new RenderInfinityThrowable(manager273, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/acid_rain_drop.png"));
        });
        registerRender(EntityDroidSucker.class, manager274 -> {
            return new RenderInfinityThrowable(manager274, new ResourceLocation(Reference.MODID, "textures/projectiles/droidzapper.png"));
        });
        registerRender(EntityVeloMagic.class, manager275 -> {
            return new RenderInfinityThrowable(manager275, new ResourceLocation(Reference.MODID, "textures/projectiles/velomagic.png"));
        });
        registerRender(EntityWizardBlast.class, manager276 -> {
            return new RenderInfinityThrowable(manager276, new ResourceLocation(Reference.MODID, "textures/projectiles/wizardblast.png"));
        });
        registerRender(EntityFluxBall.class, manager277 -> {
            return new RenderInfinityThrowable(manager277, new ResourceLocation(Reference.MODID, "textures/projectiles/velomagic.png"));
        });
        registerRender(EntityInkShot.class, manager278 -> {
            return new RenderInfinityThrowable(manager278, new ResourceLocation(Reference.MODID, "textures/projectiles/ink_shot.png"));
        });
        registerRender(EntityInkBomb.class, manager279 -> {
            return new RenderInfinityThrowable(manager279, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/ink_bomb.png"));
        });
        registerRender(EntityGalaxyBlast.class, manager280 -> {
            return new RenderInfinityThrowable(manager280, new ResourceLocation(Reference.MODID, "textures/projectiles/galaxyblast.png"));
        });
        registerRender(EntityGalaxySpell.class, manager281 -> {
            return new RenderInfinityThrowable(manager281, new ResourceLocation(Reference.MODID, "textures/projectiles/galaxyspell.png"));
        });
        registerRender(EntityGalaxyBomb.class, manager282 -> {
            return new RenderInfinityThrowable(manager282, new ResourceLocation(Reference.MODID, "textures/projectiles/galaxybomb.png"));
        });
        registerRender(EntityGalaxyKnife.class, manager283 -> {
            return new RenderInfinityThrowable(manager283, new ResourceLocation(Reference.MODID, "textures/projectiles/galaxyknife.png"));
        });
        registerRender(EntityDeviantSucker.class, manager284 -> {
            return new RenderInfinityThrowable(manager284, new ResourceLocation(Reference.MODID, "textures/projectiles/droidzapper.png"));
        });
        registerRender(EntityDeviantDeployer.class, manager285 -> {
            return new RenderInfinityThrowable(manager285, new ResourceLocation(Reference.MODID, "textures/projectiles/deviantball.png"));
        });
        registerRender(EntityWitchMagic.class, manager286 -> {
            return new RenderInfinityThrowable(manager286, new ResourceLocation(Reference.MODID, "textures/projectiles/witchmagic.png"));
        });
        registerRender(EntityCrystalGellball.class, manager287 -> {
            return new RenderInfinityThrowable(manager287, new ResourceLocation(Reference.MODID, "textures/projectiles/crystalgel.png"));
        });
        registerRender(EntityCarrierProjectile.class, manager288 -> {
            return new RenderInfinityThrowable(manager288, new ResourceLocation(Reference.MODID, "textures/projectiles/carriershot.png"));
        });
        registerRender(EntityFallingStar.class, manager289 -> {
            return new RenderInfinityThrowable(manager289, new ResourceLocation(Reference.MODID, "textures/projectiles/fallingstar.png"));
        });
        registerRender(EntityRockshot.class, manager290 -> {
            return new RenderInfinityThrowable(manager290, new ResourceLocation(Reference.MODID, "textures/projectiles/rockshot.png"));
        });
        registerRender(EntitySonicAttack.class, manager291 -> {
            return new RenderAlternatingThrowable(manager291, 3.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/sonicattack_blue.png"), new ResourceLocation(Reference.MODID, "textures/projectiles/sonicattack_orange.png"));
        });
        registerRender(EntityXSonicAttack.class, manager292 -> {
            return new RenderAlternatingThrowable(manager292, 3.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/sonicattack_blue.png"), new ResourceLocation(Reference.MODID, "textures/projectiles/sonicattack_orange.png"));
        });
        registerRender(EntityWandAttack.class, manager293 -> {
            return new RenderWandAttack(manager293);
        });
        registerRender(EntityDeviantSnowball.class, manager294 -> {
            return new RenderInfinityThrowable(manager294, new ResourceLocation(Reference.MODID, "textures/projectiles/deviantsnowball.png"));
        });
        registerRender(EntityCelestialFire.class, manager295 -> {
            return new RenderAlternatingThrowable(manager295, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/celestialfire_red.png"), new ResourceLocation(Reference.MODID, "textures/projectiles/celestialfire_yellow.png"));
        });
        registerRender(EntityExplosiveGoo.class, manager296 -> {
            return new RenderAlternatingThrowable(manager296, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/explosivegoo_orange.png"), new ResourceLocation(Reference.MODID, "textures/projectiles/explosivegoo_yellow.png"), 3);
        });
        registerRender(EntityStunAttack.class, manager297 -> {
            return new RenderAlternatingThrowable(manager297, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/stunattack_1.png"), new ResourceLocation(Reference.MODID, "textures/projectiles/stunattack_2.png"), 4);
        });
        registerRender(EntityDarkSpell.class, manager298 -> {
            return new RenderInfinityThrowable(manager298, new ResourceLocation(Reference.MODID, "textures/projectiles/darkspell.png"));
        });
        registerRender(EntityCrystalShard.class, manager299 -> {
            return new RenderInfinityThrowable(manager299, new ResourceLocation(Reference.MODID, "textures/projectiles/crystalshard.png"));
        });
        registerRender(EntityDestabilizer.class, manager300 -> {
            return new RenderInfinityThrowable(manager300, new ResourceLocation(Reference.MODID, "textures/projectiles/destabilizer.png"));
        });
        registerRender(EntityComet.class, manager301 -> {
            return new RenderInfinityThrowable(manager301, 3.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/comet.png"));
        });
        registerRender(EntityMobComet.class, manager302 -> {
            return new RenderInfinityThrowable(manager302, 4.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/comet.png"));
        });
        registerRender(EntityBlightedComet.class, manager303 -> {
            return new RenderInfinityThrowable(manager303, 4.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/blighted_comet.png"));
        });
        registerRender(EntityTentacleSeed.class, manager304 -> {
            return new RenderInfinityThrowable(manager304, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/tentacle_seed.png"));
        });
        registerRender(EntityHomingBlight.class, manager305 -> {
            return new RenderInfinityThrowable(manager305, 4.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/homing_blight.png"));
        });
        registerRender(EntityAtlasAttack.class, manager306 -> {
            return new RenderInfinityThrowable(manager306, new ResourceLocation(Reference.MODID, "textures/projectiles/galaxyblast.png"));
        });
        registerRender(EntityDeviantSpit.class, manager307 -> {
            return new RenderInfinityThrowable(manager307, new ResourceLocation(Reference.MODID, "textures/projectiles/deviantspit.png"));
        });
        registerRender(EntityVoidLaser.class, manager308 -> {
            return new RenderInfinityThrowable(manager308, 1.5f, new ResourceLocation(Reference.MODID, "textures/projectiles/voidlaser.png"));
        });
        registerRender(EntitySkyStrike.class, manager309 -> {
            return new RenderAlternatingThrowable(manager309, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/charged_comet1.png"), new ResourceLocation(Reference.MODID, "textures/projectiles/charged_comet2.png"), 10);
        });
        registerRender(EntityProjectileKiller.class, manager310 -> {
            return new RenderInfinityThrowable(manager310, new ResourceLocation(Reference.MODID, "textures/projectiles/projectile_killer.png"));
        });
        registerRender(EntityVenomBlast.class, manager311 -> {
            return new RenderInfinityThrowable(manager311, new ResourceLocation(Reference.MODID, "textures/projectiles/venom_blast.png"));
        });
        registerRender(EntityBloodhunterBlast.class, manager312 -> {
            return new RenderInfinityThrowable(manager312, new ResourceLocation(Reference.MODID, "textures/projectiles/bloodhunter_blast.png"));
        });
        registerRender(EntityPowerPulse.class, manager313 -> {
            return new RenderInfinityThrowable(manager313, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/power_pulse.png"));
        });
        registerRender(EntityGravityWarper.class, manager314 -> {
            return new RenderInfinityThrowable(manager314, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/gravity_warper.png"));
        });
        registerRender(EntityWitherBomb.class, manager315 -> {
            return new RenderInfinityThrowable(manager315, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/wither_bomb.png"));
        });
        registerRender(EntityElementiumFire.class, manager316 -> {
            return new RenderSpecialArrow(manager316, new ResourceLocation(Reference.MODID, "textures/projectiles/elementium_fire.png"));
        });
        registerRender(EntityElementiumWater.class, manager317 -> {
            return new RenderSpecialArrow(manager317, new ResourceLocation(Reference.MODID, "textures/projectiles/elementium_water.png"));
        });
        registerRender(EntityElementiumEarth.class, manager318 -> {
            return new RenderSpecialArrow(manager318, new ResourceLocation(Reference.MODID, "textures/projectiles/elementium_earth.png"));
        });
        registerRender(EntityElementiumAir.class, manager319 -> {
            return new RenderSpecialArrow(manager319, new ResourceLocation(Reference.MODID, "textures/projectiles/elementium_air.png"));
        });
        registerRender(EntityDroidLaser.class, manager320 -> {
            return new RenderInfinityThrowable(manager320, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/laser_ring.png"));
        });
        registerRender(EntityGalaxyLaser.class, manager321 -> {
            return new RenderInfinityThrowable(manager321, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/laser_ball.png"));
        });
        registerRender(EntityStickyProjectile.class, manager322 -> {
            return new RenderInfinityThrowable(manager322, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/sticky_bomb.png"));
        });
        registerRender(EntityGenericBomb.class, manager323 -> {
            return new RenderInfinityThrowable(manager323, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/generic_bomb.png"));
        });
        registerRender(EntityPlagueBlast.class, manager324 -> {
            return new RenderInfinityThrowable(manager324, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/dark_skull.png"));
        });
        registerRender(EntityFractureBomb.class, manager325 -> {
            return new RenderInfinityThrowable(manager325, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/fracture_bomb.png"));
        });
        registerRender(EntityUltraCrystalGel.class, manager326 -> {
            return new RenderInfinityThrowable(manager326, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/ultra_crystalgel.png"));
        });
        registerRender(EntitySpiderBlast.class, manager327 -> {
            return new RenderInfinityThrowable(manager327, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/spider_blast.png"));
        });
        registerRender(EntityGloomSpell.class, manager328 -> {
            return new RenderInfinityThrowable(manager328, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/gloom_spell.png"));
        });
        registerRender(EntityEchoBlast.class, manager329 -> {
            return new RenderInfinityThrowable(manager329, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/echo.png"));
        });
        registerRender(EntityZenonShot.class, manager330 -> {
            return new RenderInfinityThrowable(manager330, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/zenon_shot.png"));
        });
        registerRender(EntityElementiumShadow.class, manager331 -> {
            return new RenderSpecialArrow(manager331, new ResourceLocation(Reference.MODID, "textures/projectiles/elementium_shadow.png"));
        });
        registerRender(EntityElementiumBlight.class, manager332 -> {
            return new RenderSpecialArrow(manager332, new ResourceLocation(Reference.MODID, "textures/projectiles/elementium_blight.png"));
        });
        registerRender(EntityElementiumPlague.class, manager333 -> {
            return new RenderSpecialArrow(manager333, new ResourceLocation(Reference.MODID, "textures/projectiles/elementium_plague.png"));
        });
        registerRender(EntityTNTStrapper.class, manager334 -> {
            return new RenderInfinityThrowable(manager334, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/carriershot.png"));
        });
        registerRender(EntityPuzzleMasterArrow.class, manager335 -> {
            return new RenderSpecialArrow(manager335, new ResourceLocation(Reference.MODID, "textures/projectiles/puzzle_master_arrow.png"));
        });
        registerRender(EntitySkycrabAttack.class, manager336 -> {
            return new RenderInfinityThrowable(manager336, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/skycrab_attack.png"));
        });
        registerRender(EntityCellularRock.class, manager337 -> {
            return new RenderInfinityThrowable(manager337, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/cellular_rock.png"));
        });
        registerRender(EntitySunstoneRock.class, manager338 -> {
            return new RenderInfinityThrowable(manager338, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/sunstone_rock.png"));
        });
        registerRender(EntityInfinityTrident.class, manager339 -> {
            return new RenderInfinityTrident(manager339);
        });
        registerRender(EntityInfinityTridentMK2.class, manager340 -> {
            return new RenderInfinityTridentMK2(manager340);
        });
        registerRender(EntityAvenger.class, manager341 -> {
            return new RenderAvenger(manager341);
        });
        registerRender(EntityTitanRing.class, manager342 -> {
            return new RenderTitanRing(manager342);
        });
        registerRender(EntityLuminousGuardianLaser.class, RenderLuminousGuardianLaser::new);
        registerRender(EntityLaserGunBeam.class, RenderLaserGunBeam::new);
        registerRender(EntityChampionDodgeball.class, manager343 -> {
            return new RenderChampionDodgeball(manager343, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/champion_dodgeball.png"));
        });
        registerRender(EntitySpikeBall.class, manager344 -> {
            return new RenderInfinityThrowable(manager344, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/spikeball.png"));
        });
        registerRender(EntityNebulaSpell.class, manager345 -> {
            return new RenderInfinityThrowable(manager345, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/nebula_spell.png"));
        });
        registerRender(EntityRecursor.class, manager346 -> {
            return new RenderInfinityThrowable(manager346, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/infinity_razor.png"));
        });
        registerRender(EntityIonicChakram.class, manager347 -> {
            return new RenderIonicChakram(manager347);
        });
        registerRender(EntityMiniChakram.class, manager348 -> {
            return new RenderMiniChakram(manager348);
        });
        registerRender(EntityExothermite.class, manager349 -> {
            return new RenderInfinityThrowable(manager349, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/exothermite.png"));
        });
        registerRender(EntityBallOfContainedQuarks.class, manager350 -> {
            return new RenderInfinityThrowable(manager350, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/ball_of_contained_quarks.png"));
        });
        registerRender(EntityBallOfContainedGluons.class, manager351 -> {
            return new RenderInfinityThrowable(manager351, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/ball_of_contained_gluons.png"));
        });
        registerRender(EntityAcidicConcoction.class, manager352 -> {
            return new RenderInfinityThrowable(manager352, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/acidic_concoction.png"));
        });
        registerRender(EntityPoisonousBubble.class, manager353 -> {
            return new RenderInfinityThrowable(manager353, 0.1f, new ResourceLocation(Reference.MODID, "textures/projectiles/poisonous_bubble.png"));
        });
        registerRender(EntityBeeAttack.class, manager354 -> {
            return new RenderInfinityThrowable(manager354, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/bee_attack.png"));
        });
        registerRender(EntityMortarShot.class, manager355 -> {
            return new RenderInfinityThrowable(manager355, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/mortar_shot.png"));
        });
        registerRender(EntityUnstableBalloon.class, manager356 -> {
            return new RenderInfinityThrowable(manager356, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/unstable_balloon.png"));
        });
        registerRender(EntityPiercingShot.class, manager357 -> {
            return new RenderSpecialArrow(manager357, new ResourceLocation(Reference.MODID, "textures/projectiles/crystal_arrow.png"));
        });
        registerRender(EntityLaserBlast.class, manager358 -> {
            return new RenderInfinityThrowable(manager358, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/laser_ball.png"));
        });
        registerRender(EntitySelectionLaser.class, manager359 -> {
            return new RenderInfinityThrowable(manager359, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/laser_ball.png"));
        });
        registerRender(EntityDryadsGripAttack.class, manager360 -> {
            return new RenderInfinityThrowable(manager360, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/nature_spell.png"));
        });
        registerRender(EntityTetherBall.class, manager361 -> {
            return new RenderInfinityThrowable(manager361, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/web_attack.png"));
        });
        registerRender(EntityFirePellet.class, manager362 -> {
            return new RenderInfinityThrowable(manager362, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/fire_pellet.png"));
        });
        registerRender(EntityFountainPellet.class, manager363 -> {
            return new RenderInfinityThrowable(manager363, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/fountain_pellet.png"));
        });
        registerRender(EntityClusterBlast.class, manager364 -> {
            return new RenderInfinityThrowable(manager364, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/fracture_bomb.png"));
        });
        registerRender(EntityWormholeShot.class, manager365 -> {
            return new RenderSpecialArrow(manager365, new ResourceLocation(Reference.MODID, "textures/projectiles/wormhole_shot.png"));
        });
        registerRender(EntityFearBomb.class, manager366 -> {
            return new RenderSpecialArrow(manager366, new ResourceLocation(Reference.MODID, "textures/projectiles/flurry_arrow.png"));
        });
        registerRender(EntityEnergyBurst.class, manager367 -> {
            return new RenderAlternatingArrow(manager367, new ResourceLocation(Reference.MODID, "textures/projectiles/energy_burst.png"), new ResourceLocation(Reference.MODID, "textures/projectiles/energy_burst_alternate.png"));
        });
        registerRender(EntityBlightPellet.class, manager368 -> {
            return new RenderInfinityThrowable(manager368, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/blight_pellet.png"));
        });
        registerRender(EntityEffigyShot.class, manager369 -> {
            return new RenderInfinityThrowable(manager369, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/effigy_doll.png"));
        });
        registerRender(EntityEchoOfGravity.class, manager370 -> {
            return new RenderInfinityThrowable(manager370, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/echo.png"));
        });
        registerRender(EntityStarBlast.class, manager371 -> {
            return new RenderInfinityThrowable(manager371, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/fallingstar.png"));
        });
        registerRender(EntityAsteroid.class, manager372 -> {
            return new RenderInfinityThrowable(manager372, 30.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/asteroid.png"), true);
        });
        registerRender(EntityMeteor.class, manager373 -> {
            return new RenderInfinityThrowable(manager373, 4.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/meteor.png"));
        });
        registerRender(EntityDoomBlast.class, manager374 -> {
            return new RenderInfinityThrowable(manager374, 2.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/doom_blast.png"));
        });
        registerRender(EntityAstralShot.class, manager375 -> {
            return new RenderSpecialArrow(manager375, new ResourceLocation(Reference.MODID, "textures/projectiles/astral_shot.png"));
        });
        registerRender(EntityLaunchedKnife.class, manager376 -> {
            return new RenderSpecialArrow(manager376, new ResourceLocation(Reference.MODID, "textures/projectiles/launched_knife.png"));
        });
        registerRender(EntityElementiumPrime.class, manager377 -> {
            return new RenderSpecialArrow(manager377, new ResourceLocation(Reference.MODID, "textures/projectiles/elementium_prime.png"));
        });
        registerRender(EntityArcBlast.class, manager378 -> {
            return new RenderInfinityThrowable(manager378, new ResourceLocation(Reference.MODID, "textures/projectiles/gravity_warper.png"));
        });
        registerRender(EntityBlightBomb.class, manager379 -> {
            return new RenderInfinityThrowable(manager379, new ResourceLocation(Reference.MODID, "textures/projectiles/venom_blast.png"));
        });
        registerRender(EntityTekBullet.class, manager380 -> {
            return new RenderInfinityThrowable(manager380, new ResourceLocation(Reference.MODID, "textures/projectiles/rockshot.png"));
        });
        registerRender(EntityStrappedFirework.class, manager381 -> {
            return new RenderInfinityThrowable(manager381, new ResourceLocation(Reference.MODID, "textures/projectiles/strapped_firework.png"));
        });
        registerRender(EntityCryoBolt.class, manager382 -> {
            return new RenderInfinityThrowable(manager382, new ResourceLocation(Reference.MODID, "textures/projectiles/cryo_bolt.png"));
        });
        registerRender(EntityScreamerBlast.class, manager383 -> {
            return new RenderAlternatingThrowable(manager383, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/screamer_blast.png"), new ResourceLocation(Reference.MODID, "textures/projectiles/screamer_blast_alternate.png"), 2);
        });
        registerRender(EntityEffigyEffect.class, manager384 -> {
            return new RenderEffigyEffect(manager384);
        });
        registerRender(EntityEidolonMist.class, manager385 -> {
            return new RenderEidolonMist(manager385);
        });
        registerRender(EntityWormholePortal.class, manager386 -> {
            return new RenderWormholePortal(manager386);
        });
        registerRender(EntityPlasmaSlicer.class, manager387 -> {
            return new RenderPlasmaSlicer(manager387);
        });
        registerRender(EntityAvengerLightning.class, manager388 -> {
            return new RenderAvengerLightning(manager388);
        });
        registerRender(EntityChainOfVenomsAttack.class, manager389 -> {
            return new RenderChainOfVenomsAttack(manager389);
        });
        registerRender(EntityVoltaicIonizerAttack.class, manager390 -> {
            return new RenderVoltaicIonizerAttack(manager390);
        });
        registerRender(EntitySelectorAttack.class, manager391 -> {
            return new RenderSelectorAttack(manager391);
        });
        registerRender(EntityBarulChain.class, manager392 -> {
            return new RenderBarulChain(manager392);
        });
        registerRender(EntityTormentorChain.class, manager393 -> {
            return new RenderTormentorChain(manager393);
        });
        registerRender(EntityDuskerQueenWeb.class, manager394 -> {
            return new RenderDuskerQueenWeb(manager394);
        });
        registerRender(EntityPortalEffect.class, manager395 -> {
            return new RenderPortalEffect(manager395);
        });
        registerRender(EntityScreamerPortalEffect.class, manager396 -> {
            return new RenderScreamerPortalEffect(manager396);
        });
        registerRender(EntityPicklePortal.class, manager397 -> {
            return new RenderPicklePortal(manager397);
        });
        registerRender(EntityBossPortalEffect.class, manager398 -> {
            return new RenderBossPortalEffect(manager398);
        });
        registerRender(EntityCryoBeamEffect.class, manager399 -> {
            return new RenderCryoBeamEffect(manager399);
        });
        registerRender(EntityDebtCollectorEffect.class, manager400 -> {
            return new RenderDebtCollectorEffect(manager400);
        });
        registerRender(EntityForbiddenBrand.class, manager401 -> {
            return new RenderForbiddenBrand(manager401);
        });
        registerRender(EntityMicroRocket.class, manager402 -> {
            return new RenderSpecialArrow(manager402, new ResourceLocation(Reference.MODID, "textures/projectiles/micro_rocket.png"));
        });
        registerRender(EntityDeathShot.class, manager403 -> {
            return new RenderSpecialArrow(manager403, new ResourceLocation(Reference.MODID, "textures/projectiles/death_shot.png"));
        });
        registerRender(EntityCrabulonProjectile.class, manager404 -> {
            return new RenderSpecialArrow(manager404, new ResourceLocation(Reference.MODID, "textures/projectiles/crabulon_projectile.png"));
        });
        registerRender(EntityMultiverseGhost.class, RenderMultiverseGhost::new);
        registerRender(EntityRisingPhantom.class, RenderRisingPhantom::new);
        registerRender(EntityLostBlade.class, RenderLostBlade::new);
        registerRender(EntityAbyssalCrabulon.class, RenderAbyssalCrabulon::new);
        registerRender(EntitySoundwaveBullet.class, manager405 -> {
            return new RenderAlternatingThrowable(manager405, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/echo_attack_white.png"), new ResourceLocation(Reference.MODID, "textures/projectiles/echo_attack_red.png"), 3);
        });
        registerRender(EntityLeviathanTracer.class, manager406 -> {
            return new RenderInfinityThrowable(manager406, new ResourceLocation(Reference.MODID, "textures/projectiles/leviathan_tracer.png"));
        });
        registerRender(EntityLeviathanTeslaOrb.class, manager407 -> {
            return new RenderInfinityThrowable(manager407, 3.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/leviathan_tesla.png"));
        });
        registerRender(EntityLeviathanBreath.class, RenderLeviathanBreath::new);
        registerRender(EntityGalaxyDragonFireball.class, RenderGalaxyDragonFireball::new);
        registerRender(EntityWhirlpool.class, manager408 -> {
            return new RenderWhirlpool(manager408);
        });
        registerRender(EntityNuclearExplosion.class, RenderNuclearExplosion::new);
        registerRender(EntityAuraOfAllegiance.class, RenderAuraOfAllegiance::new);
        registerRender(EntitySupplyTrader.class, RenderSupplyTrader::new);
        registerRender(EntityJetMount.class, RenderJetMount::new);
        registerRender(EntityEmberShot.class, manager409 -> {
            return new RenderAlternatingThrowable(manager409, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/embershot.png"), new ResourceLocation(Reference.MODID, "textures/projectiles/embershot_alternate.png"));
        });
        registerRender(EntityCreepingVineArrow.class, manager410 -> {
            return new RenderSpecialArrow(manager410, new ResourceLocation(Reference.MODID, "textures/projectiles/creeping_vine_arrow.png"));
        });
        registerRender(EntityCreepingVinePod.class, manager411 -> {
            return new RenderInfinityThrowable(manager411, 1.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/creeping_vine_pod.png"));
        });
        registerRender(EntityAndromedaChaser.class, manager412 -> {
            return new RenderInfinityThrowable(manager412, 7.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/andromeda_chaser.png"));
        });
        registerRender(EntityBombDrone.class, RenderBombDrone::new);
        registerRender(EntityPlayerLimb.class, RenderPlayerLimb::new);
        registerRender(EntityCthulhu.class, RenderCthulhu::new);
        registerRender(EntityCthulhuTurret.class, RenderCthulhuTurret::new);
        registerRender(EntityCthulhuTurretBullet.class, manager413 -> {
            return new RenderInfinityThrowable(manager413, new ResourceLocation(Reference.MODID, "textures/projectiles/cthulhu/turret_bullet.png"));
        });
        registerRender(EntityCthulhuMissile.class, manager414 -> {
            return new RenderSpecialArrow(manager414, new ResourceLocation(Reference.MODID, "textures/projectiles/cthulhu/missile.png"));
        });
        registerRender(EntityCthulhuMeteor.class, manager415 -> {
            return new RenderInfinityThrowable(manager415, 30.0f, new ResourceLocation(Reference.MODID, "textures/projectiles/cthulhu/meteor.png"), true);
        });
        registerRender(EntityCthulhuTentacle.class, RenderCthulhuTentacle::new);
        registerRender(EntityCthulhuBeam.class, RenderCthulhuBeam::new);
        registerRender(EntityCthulhuRift.class, RenderCthulhuRift::new);
        registerRender(EntityCthulhuBlackHole.class, RenderCthulhuBlackHole::new);
        registerRender(EntityCthulhuHealingOrb.class, RenderCthulhuHealingOrb::new);
        registerRender(EntityCelestialStatue.class, RenderCelestialStatue::new);
        registerRender(EntityCthulhuSpear.class, RenderCthulhuSpear::new);
        registerRender(EntityCthulhuPart.class, RenderCthulhuPart::new);
        registerRender(EntityCthulhuDeathFX.class, RenderCthulhuDeathFX::new);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLightEmitter.class, new RenderLightBeamEffect());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNicroniumInfuser.class, new RenderNicroniumInfuserEffect());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPortalNode.class, new RenderPortalNodeEffect());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPortalNexus.class, new RenderPortalNexus());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVoidVacuum.class, new RenderVoidVacuum());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTeslaTower.class, new RenderTeslaTower());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEternalBeacon.class, new RenderEternalBeacon());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCthulhuSpawner.class, new RenderCthulhuSpawner());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKillerVine.class, new RenderKillerVine());
    }
}
