package xol.lostinfinity.common.events;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xol.lostinfinity.block.basic.ITetherable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.classify.IHealReactive;
import xol.lostinfinity.item.classify.ITransfusionEffect;
import xol.lostinfinity.mob.entity.base.EntityDeviantMob;
import xol.lostinfinity.mob.entity.base.EntityFloatingDeviant;
import xol.lostinfinity.mob.entity.base.EntityMultiLivesTameable;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhu;
import xol.lostinfinity.mob.entity.misc.EntityGhostCopy;
import xol.lostinfinity.mob.entity.misc.EntityStarfiend;
import xol.lostinfinity.projectile.entity.EntityEnergyBurst;
import xol.lostinfinity.util.PotionBasic;
import xol.lostinfinity.util.damagesource.DeathMessage;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EventsCreatureAction implements IMaxAttack {
    @SubscribeEvent
    public void deviantDropEvent(LivingDropsEvent event) {
        if (((event.getEntityLiving() instanceof EntityDeviantMob) || (event.getEntityLiving() instanceof EntityFloatingDeviant)) && event.getEntityLiving().field_70170_p.field_73011_w.func_186058_p() == DimensionInit.celestialVoid) {
            event.setCanceled(true);
        }
    }
    @SubscribeEvent
    public void onEntityHeal(LivingHealEvent event) {
        EntityPlayer entityLiving = event.getEntityLiving();
        if (!((EntityLivingBase) entityLiving).field_70170_p.field_72995_K) {
            if (entityLiving instanceof EntityPlayer) {
                EntityPlayer player = entityLiving;
                ItemStack main = player.func_184614_ca();
                ItemStack off = player.func_184592_cb();
                ItemStack reactive = null;
                if (main.func_77973_b() instanceof IHealReactive) {
                    reactive = main;
                } else if (off.func_77973_b() instanceof IHealReactive) {
                    reactive = off;
                }
                if (reactive != null) {
                    float newHeal = reactive.func_77973_b().itemHealReaction(player, event.getAmount(), reactive);
                    event.setAmount(newHeal);
                }
            }
            if (entityLiving.func_70644_a(PotionInit.BLOOD_TOXIN)) {
                if (entityLiving.func_110143_aJ() > 0.0f && !((EntityLivingBase) entityLiving).field_70128_L) {
                    int level = entityLiving.func_70660_b(PotionInit.BLOOD_TOXIN).func_76458_c();
                    float changedAmount = event.getAmount() * (0.0f + (0.2f * level));
                    IMaxAttack.dealPotionDamage(entityLiving, changedAmount);
                    if (entityLiving.func_110143_aJ() <= 0.0f && (entityLiving instanceof EntityPlayer)) {
                        DeathMessage.broadcastDeathMessage(entityLiving.func_184102_h(), TextFmt.Red + entityLiving.func_70005_c_() + " died of toxins.");
                    }
                }
                event.setCanceled(true);
            }
            if (entityLiving.func_70644_a(PotionInit.PLANESPLIT)) {
                float entityHP = entityLiving.func_110143_aJ();
                int level2 = entityLiving.func_70660_b(PotionInit.PLANESPLIT).func_76458_c();
                float hpCap = entityLiving.func_110138_aP() * (0.9f - (0.1f * level2));
                if (entityHP + event.getAmount() > hpCap) {
                    event.setAmount(hpCap - entityHP);
                }
            }
        }
    }
    @SubscribeEvent
    public void onTickEvent(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase target;
        int level;
        EntityPlayer entityLiving = event.getEntityLiving();
        if (entityLiving.func_70644_a(PotionInit.LAST_BREATH) && !((EntityLivingBase) entityLiving).field_70170_p.field_72995_K && ((EntityLivingBase) entityLiving).field_70173_aa % 10 == 0) {
            int level2 = entityLiving.func_70660_b(PotionInit.LAST_BREATH).func_76458_c();
            float entityMaxHp = entityLiving.func_110138_aP();
            IMaxAttack.dealPotionDamage(entityLiving, entityMaxHp / (20 - (2 * level2)));
            if (entityLiving.func_110143_aJ() <= 0.0f && (entityLiving instanceof EntityPlayer)) {
                DeathMessage.broadcastDeathMessage(entityLiving.func_184102_h(), TextFmt.Red + entityLiving.func_70005_c_() + " took their final breath.");
            }
        }
        if (entityLiving.func_70644_a(PotionInit.TRANSFUSION) && !((EntityLivingBase) entityLiving).field_70170_p.field_72995_K) {
            int amplifier = entityLiving.func_70660_b(PotionInit.TRANSFUSION).func_76458_c();
            for (EntityLivingBase nearPlayer : ((EntityLivingBase) entityLiving).field_70170_p.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(entityLiving.func_180425_c()).func_186662_g(2 + (amplifier * 3)))) {
                if (!nearPlayer.func_110124_au().equals(entityLiving.func_110124_au()) && !nearPlayer.func_70644_a(PotionInit.TRANSFUSION) && nearPlayer.func_70651_bq().size() > 0) {
                    ArrayList<Potion> toRemove = new ArrayList<>();
                    for (PotionEffect effect : nearPlayer.func_70651_bq()) {
                        Potion potion = effect.func_188419_a();
                        if (((potion instanceof PotionBasic) && !((PotionBasic) potion).negativeLostEffect()) || (!(potion instanceof PotionBasic) && !potion.func_76398_f())) {
                            toRemove.add(effect.func_188419_a());
                            entityLiving.func_70690_d(effect);
                        }
                    }
                    Iterator<Potion> it = toRemove.iterator();
                    while (it.hasNext()) {
                        nearPlayer.func_184589_d(it.next());
                    }
                    if (entityLiving instanceof EntityPlayer) {
                        ItemStack mainHand = entityLiving.func_184614_ca();
                        ItemStack offhand = entityLiving.func_184592_cb();
                        if (mainHand.func_77973_b() instanceof ITransfusionEffect) {
                            ITransfusionEffect transfusible = mainHand.func_77973_b();
                            transfusible.transfuse(entityLiving, nearPlayer, EnumHand.MAIN_HAND, mainHand, toRemove);
                        }
                        if (offhand.func_77973_b() instanceof ITransfusionEffect) {
                            ITransfusionEffect transfusible2 = offhand.func_77973_b();
                            transfusible2.transfuse(entityLiving, nearPlayer, EnumHand.OFF_HAND, offhand, toRemove);
                        }
                    }
                }
            }
        }
        if (entityLiving.func_70644_a(PotionInit.NITROUS) && ((EntityLivingBase) entityLiving).field_70173_aa % 20 == 0 && !((EntityLivingBase) entityLiving).field_70170_p.field_72995_K) {
            int level3 = entityLiving.func_70660_b(PotionInit.NITROUS).func_76458_c();
            for (EntityLivingBase near_entity : ((EntityLivingBase) entityLiving).field_70170_p.func_72872_a(EntityLivingBase.class, entityLiving.func_174813_aQ().func_186662_g(7.0d))) {
                if (!near_entity.func_110124_au().equals(entityLiving.func_110124_au())) {
                    boolean canDealTrue = level3 >= 1;
                    if (canDealTrue && near_entity.func_70032_d(entityLiving) < 3.0f) {
                        IMaxAttack.dealTrueDamage(entityLiving, near_entity, near_entity.func_110138_aP() * 0.75f);
                    } else {
                        IMaxAttack.dealMaxHealth((Entity) entityLiving, near_entity, 4, 3.0f);
                    }
                }
            }
            ((EntityLivingBase) entityLiving).field_70170_p.func_184133_a((EntityPlayer) null, entityLiving.func_180425_c(), SoundInit.GENERIC_WEAPON_11, SoundCategory.PLAYERS, 0.5f, 0.7f + (0.6f * ((EntityLivingBase) entityLiving).field_70170_p.field_73012_v.nextFloat()));
            float f = 0.0f;
            while (true) {
                float angle = f;
                if (angle > 6.283185307179586d) {
                    break;
                }
                double velocity_x = 10.0d * Math.cos(angle);
                double velocity_z = 10.0d * Math.sin(angle);
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.FLAME_LARGE).setSpread(1.0d, 0.0d, 1.0d).setSpeed(0.3d, 0.0d, 0.3d).setVelSpread(1.0d, 0.0d, 1.0d).setCount(9).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(((EntityLivingBase) entityLiving).field_70170_p, config1, ((EntityLivingBase) entityLiving).field_70165_t + (velocity_x / 2.0d), ((EntityLivingBase) entityLiving).field_70163_u + 1.0d, ((EntityLivingBase) entityLiving).field_70161_v + (velocity_z / 2.0d));
                f = (float) (((double) angle) + 0.39269908169872414d);
            }
        }
        if (entityLiving.func_70644_a(PotionInit.PLANESPLIT) && !((EntityLivingBase) entityLiving).field_70170_p.field_72995_K) {
            float hpCap = entityLiving.func_110138_aP() * (0.9f - (0.1f * entityLiving.func_70660_b(PotionInit.PLANESPLIT).func_76458_c()));
            if (entityLiving.func_110143_aJ() > hpCap + 0.1f) {
                entityLiving.func_70606_j(hpCap);
            }
            if (((EntityLivingBase) entityLiving).field_70173_aa % 3 == 0 && (entityLiving instanceof EntityPlayer)) {
                EntityPlayer player = entityLiving;
                Random rand = player.field_70170_p.field_73012_v;
                float rYaw = player.field_70177_z * 0.017453292f;
                float x = -MathHelper.func_76126_a(rYaw);
                float z = MathHelper.func_76134_b(rYaw);
                Vec3d look = player.func_174791_d().func_178788_d(new Vec3d(((double) x) * 2.5d, 0.0d, ((double) z) * 2.5d).func_178785_b((-0.7f) + (1.4f * rand.nextFloat())));
                EntityGhostCopy ghost = new EntityGhostCopy(player.field_70170_p);
                ghost.setCopiedPlay(player);
                ghost.func_70107_b(look.field_72450_a, player.field_70163_u + 0.5d, look.field_72449_c);
                player.field_70170_p.func_72838_d(ghost);
                if (((EntityLivingBase) entityLiving).field_70173_aa % 18 == 0) {
                    switch (rand.nextInt(4)) {
                        case 0:
                            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.APPARITION_1, SoundCategory.PLAYERS, 0.5f, 0.9f + (rand.nextFloat() * 0.2f));
                            break;
                        case 1:
                            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.APPARITION_2, SoundCategory.PLAYERS, 0.5f, 0.9f + (rand.nextFloat() * 0.2f));
                            break;
                        case 2:
                            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.APPARITION_3, SoundCategory.PLAYERS, 0.5f, 0.9f + (rand.nextFloat() * 0.2f));
                            break;
                        case 3:
                            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.APPARITION_4, SoundCategory.PLAYERS, 0.5f, 0.9f + (rand.nextFloat() * 0.2f));
                            break;
                    }
                }
            }
        }
        if (entityLiving.func_70644_a(PotionInit.INTANGIBLE) && !((EntityLivingBase) entityLiving).field_70170_p.field_72995_K) {
            ItemStack offhand2 = entityLiving.func_184592_cb();
            if (!offhand2.func_190926_b()) {
                ItemStack droppedCopy = offhand2.func_77946_l();
                EntityItem stolenItem = new EntityItem(((EntityLivingBase) entityLiving).field_70170_p, ((EntityLivingBase) entityLiving).field_70165_t, ((EntityLivingBase) entityLiving).field_70163_u + 1.0d, ((EntityLivingBase) entityLiving).field_70161_v, droppedCopy);
                stolenItem.field_70159_w = 0.0d;
                stolenItem.field_70181_x = 0.0d;
                stolenItem.field_70179_y = 0.0d;
                ((EntityLivingBase) entityLiving).field_70170_p.func_72838_d(stolenItem);
                entityLiving.func_184611_a(EnumHand.OFF_HAND, ItemStack.field_190927_a);
            }
        }
        if (entityLiving.func_70644_a(PotionInit.GRAVITATIONAL)) {
            int level4 = entityLiving.func_70660_b(PotionInit.GRAVITATIONAL).func_76458_c();
            double force = 0.05d + (0.05d * ((double) level4));
            int size = 3 + (level4 * 5);
            for (EntityLivingBase near_entity2 : ((EntityLivingBase) entityLiving).field_70170_p.func_72872_a(EntityLivingBase.class, entityLiving.func_174813_aQ().func_72314_b(size, size, size))) {
                if (!near_entity2.func_110124_au().equals(entityLiving.func_110124_au())) {
                    near_entity2.func_70024_g(Math.signum(((EntityLivingBase) entityLiving).field_70165_t - near_entity2.field_70165_t) * force, Math.signum(((EntityLivingBase) entityLiving).field_70163_u - near_entity2.field_70163_u) * force, Math.signum(((EntityLivingBase) entityLiving).field_70161_v - near_entity2.field_70161_v) * force);
                    near_entity2.field_70133_I = true;
                }
            }
        }
        if (entityLiving.func_70644_a(PotionInit.ULTRAHEAVY)) {
            int level5 = entityLiving.func_70660_b(PotionInit.ULTRAHEAVY).func_76458_c();
            if (((EntityLivingBase) entityLiving).field_70181_x > 0.699999988079071d) {
                entityLiving.func_70024_g(0.0d, -(0.5d + (0.2d * ((double) level5))), 0.0d);
            }
        }
        if (entityLiving.func_70644_a(PotionInit.UNLEASHING)) {
            int level6 = entityLiving.func_70660_b(PotionInit.UNLEASHING).func_76458_c();
            int duration = entityLiving.func_70660_b(PotionInit.UNLEASHING).func_76459_b();
            if (!((EntityLivingBase) entityLiving).field_70170_p.field_72995_K) {
                if (((EntityLivingBase) entityLiving).field_70173_aa % 60 == 0) {
                    entityLiving.func_70690_d(new PotionEffect(PotionInit.UNLEASHING, duration, Math.min(level6 + 1, 9)));
                }
                if (((EntityLivingBase) entityLiving).field_70173_aa % (21 - (level6 * 2)) == 0) {
                    EntityEnergyBurst shot = new EntityEnergyBurst(((EntityLivingBase) entityLiving).field_70170_p, entityLiving);
                    shot.setThrower(entityLiving);
                    shot.shootNoVel(entityLiving, ((EntityLivingBase) entityLiving).field_70125_A, ((EntityLivingBase) entityLiving).field_70177_z, 0.0f, 2.0f, 0.0f);
                    ((EntityLivingBase) entityLiving).field_70170_p.func_72838_d(shot);
                    ((EntityLivingBase) entityLiving).field_70170_p.func_184133_a((EntityPlayer) null, entityLiving.func_180425_c(), SoundInit.LASER_WEAPON_7, SoundCategory.PLAYERS, 0.4f, 0.8f + (((EntityLivingBase) entityLiving).field_70170_p.field_73012_v.nextFloat() * 0.4f));
                }
            }
        }
        if (entityLiving.func_70644_a(PotionInit.CONTAGIOUS) && !((EntityLivingBase) entityLiving).field_70170_p.field_72995_K && entityLiving.func_70644_a(PotionInit.PLAGUE) && (level = entityLiving.func_70660_b(PotionInit.PLAGUE).func_76458_c()) < 4 && ((EntityLivingBase) entityLiving).field_70173_aa % 100 == 0) {
            int duration2 = entityLiving.func_70660_b(PotionInit.PLAGUE).func_76459_b();
            entityLiving.func_70690_d(new PotionEffect(PotionInit.PLAGUE, duration2, level + 1));
        }
        if (entityLiving.func_70644_a(PotionInit.RACING_HEART) && !((EntityLivingBase) entityLiving).field_70170_p.field_72995_K) {
            int duration3 = entityLiving.func_70660_b(PotionInit.RACING_HEART).func_76459_b();
            if (duration3 <= 1) {
                if (entityLiving.func_110143_aJ() < entityLiving.func_110138_aP() / 2.0f && !((EntityLivingBase) entityLiving).field_70128_L) {
                    entityLiving.func_70106_y();
                    if (entityLiving instanceof EntityPlayer) {
                        DeathMessage.broadcastDeathMessage(entityLiving.func_184102_h(), TextFmt.Red + entityLiving.func_70005_c_() + " had a heart attack.");
                    }
                }
            } else if (((EntityLivingBase) entityLiving).field_70173_aa % 5 == 0) {
                entityLiving.func_70691_i(entityLiving.func_110138_aP() * 0.1f);
            }
        }
        if (entityLiving.func_70644_a(PotionInit.SUPERCHARGED) && !((EntityLivingBase) entityLiving).field_70170_p.field_72995_K && ((EntityLivingBase) entityLiving).field_70173_aa % 5 == 0) {
            entityLiving.func_70691_i(entityLiving.func_110138_aP() * 0.1f);
        }
        if (entityLiving.func_70644_a(PotionInit.TERRIFIED) && !((EntityLivingBase) entityLiving).field_70170_p.field_72995_K && (entityLiving instanceof EntityLiving)) {
            for (EntityLivingBase near : ((EntityLivingBase) entityLiving).field_70170_p.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(entityLiving.func_180425_c()).func_186662_g(8.0d))) {
                if (near.func_70644_a(PotionInit.FEARED)) {
                    Vec3d dir = entityLiving.func_174791_d().func_178788_d(near.func_174791_d()).func_72432_b();
                    entityLiving.func_70024_g(dir.field_72450_a / 10.0d, (dir.field_72448_b / 10.0d) + 0.1d, dir.field_72449_c / 10.0d);
                    ((EntityLivingBase) entityLiving).field_70133_I = true;
                }
            }
        }
        if (entityLiving.func_70644_a(PotionInit.TETHERED) && !((EntityLivingBase) entityLiving).field_70170_p.field_72995_K) {
            tether(event);
        }
        if (entityLiving.func_70644_a(PotionInit.PLAGUE) && !((EntityLivingBase) entityLiving).field_70170_p.field_72995_K) {
            int level7 = entityLiving.func_70660_b(PotionInit.PLAGUE).func_76458_c();
            float entityMaxHp2 = entityLiving.func_110138_aP();
            int tickDiv = Math.max(2, 50 - (5 * level7));
            if (((EntityLivingBase) entityLiving).field_70173_aa % tickDiv == 0) {
                IMaxAttack.dealPotionDamage(entityLiving, entityMaxHp2 / 3.0f);
                if (entityLiving.func_110143_aJ() <= 0.0f) {
                    doContagion(entityLiving, level7);
                    if (entityLiving instanceof EntityPlayer) {
                        DeathMessage.broadcastDeathMessage(entityLiving.func_184102_h(), TextFmt.Dark_Green + entityLiving.func_70005_c_() + " has succumbed to the plague.");
                    }
                }
            }
        }
        if (entityLiving.func_70644_a(PotionInit.MIASMA) && !((EntityLivingBase) entityLiving).field_70170_p.field_72995_K) {
            double radius = 5 + (entityLiving.func_70660_b(PotionInit.MIASMA).func_76458_c() * 3);
            for (EntityMob nearEntity : ((EntityLivingBase) entityLiving).field_70170_p.func_72872_a(EntityMob.class, new AxisAlignedBB(entityLiving.func_180425_c()).func_186662_g(radius))) {
                if (!nearEntity.func_110124_au().equals(entityLiving.func_110124_au()) && (target = nearEntity.func_70638_az()) != null && target.func_110124_au().equals(entityLiving.func_110124_au())) {
                    EntityLivingBase nearest = null;
                    double minDist = 99.0d;
                    for (EntityLivingBase near2 : ((EntityLivingBase) entityLiving).field_70170_p.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(nearEntity.func_180425_c()).func_186662_g(radius))) {
                        if (!near2.func_110124_au().equals(entityLiving.func_110124_au()) && !near2.func_110124_au().equals(nearEntity.func_110124_au())) {
                            double dist = near2.func_70032_d(nearEntity);
                            if (dist < minDist) {
                                minDist = dist;
                                nearest = near2;
                            }
                        }
                    }
                    nearEntity.func_70624_b(nearest);
                    CustomParticleConfig config12 = new CustomParticleConfig();
                    config12.createInstance().setParticle(ParticleInit.MIASMA).setSpread(4.0d, 1.0d, 4.0d).setCount(5).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(((EntityLivingBase) entityLiving).field_70170_p, config12, nearEntity.field_70165_t, nearEntity.field_70163_u + ((double) (nearEntity.field_70131_O / 2.0f)), nearEntity.field_70161_v);
                    ((EntityLivingBase) entityLiving).field_70170_p.func_184133_a((EntityPlayer) null, nearEntity.func_180425_c(), SoundInit.MIND_CONTROL_POTION, SoundCategory.PLAYERS, 1.5f, 0.7f + (((EntityLivingBase) entityLiving).field_70170_p.field_73012_v.nextFloat() * 0.6f));
                }
            }
        }
    }
    private void tether(LivingEvent.LivingUpdateEvent event) {
        BlockPos logPos = getNearestLog(event, 7);
        if (logPos != null) {
            double x = event.getEntity().field_70165_t;
            double y = event.getEntity().field_70163_u;
            double z = event.getEntity().field_70161_v;
            double diffX = ((double) logPos.func_177958_n()) - x;
            double diffY = ((double) logPos.func_177956_o()) - y;
            double diffZ = ((double) logPos.func_177952_p()) - z;
            Vec3d dir = new Vec3d(diffX, diffY, diffZ);
            dir.func_72432_b();
            event.getEntity().field_70159_w = dir.field_72450_a * 0.4d;
            event.getEntity().field_70181_x = dir.field_72448_b * 0.4d;
            event.getEntity().field_70179_y = dir.field_72449_c * 0.4d;
            event.getEntity().field_70133_I = true;
        }
    }
    private BlockPos getNearestLog(LivingEvent.LivingUpdateEvent event, int radius) {
        double minDist = 99.0d;
        BlockPos nearest = null;
        BlockPos entityPos = event.getEntity().func_180425_c();
        for (int x = entityPos.func_177958_n() - radius; x <= entityPos.func_177958_n() + radius; x++) {
            for (int y = entityPos.func_177956_o() - radius; y <= entityPos.func_177956_o() + radius; y++) {
                for (int z = entityPos.func_177952_p() - radius; z <= entityPos.func_177952_p() + radius; z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    IBlockState state = event.getEntity().field_70170_p.func_180495_p(pos);
                    if (state.func_177230_c() instanceof ITetherable) {
                        double dist = pos.func_185332_f(entityPos.func_177958_n(), entityPos.func_177956_o(), entityPos.func_177952_p());
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
    private void doContagion(EntityLivingBase entity, int level) {
        if (entity.func_70644_a(PotionInit.CONTAGIOUS)) {
            for (EntityLivingBase near_pl : entity.field_70170_p.func_72872_a(EntityLivingBase.class, entity.func_174813_aQ().func_72314_b(10.0d, 4.0d, 10.0d))) {
                near_pl.func_70690_d(new PotionEffect(PotionInit.PLAGUE, 300, level));
                near_pl.func_70690_d(new PotionEffect(PotionInit.CONTAGIOUS, 300, level));
            }
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.GLOOM_BURST).setSpread(1.0d, 1.0d, 1.0d).setCount(3).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(entity.field_70170_p, config1, entity.field_70165_t, entity.field_70163_u + ((double) (entity.field_70131_O / 2.0f)), entity.field_70161_v);
            entity.field_70170_p.func_184133_a((EntityPlayer) null, entity.func_180425_c(), SoundInit.MAGIC_WEAPON_7, SoundCategory.PLAYERS, 1.0f, 1.0f);
            entity.func_70674_bp();
        }
    }
    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof EntityPlayer)) {
            if (event.getEntity() instanceof EntityStarfiend) {
                EntityStarfiend fiend = event.getEntity();
                if (fiend.getScaleType() != 9) {
                    fiend.setScaleType((byte) (fiend.getScaleType() + 1));
                    fiend.func_70606_j(Float.MAX_VALUE);
                    event.setCanceled(true);
                    return;
                }
                return;
            }
            if (!(event.getEntity() instanceof EntityMultipleLives)) {
                if (event.getEntity() instanceof EntityMultiLivesTameable) {
                    EntityMultiLivesTameable creature = event.getEntity();
                    if (!creature.onFinalLife()) {
                        creature.takewayLife();
                        creature.func_70606_j(Float.MAX_VALUE);
                        event.setCanceled(true);
                        return;
                    } else {
                        if (!creature.didDeathAction()) {
                            creature.trueDeathAction();
                            creature.deathActionComplete();
                            return;
                        }
                        return;
                    }
                }
                return;
            }
            EntityMultipleLives creature2 = event.getEntity();
            if (!creature2.onFinalLife()) {
                creature2.takewayLife();
                creature2.func_70606_j(Float.MAX_VALUE);
                event.setCanceled(true);
                return;
            }
            boolean flag = true;
            if (creature2 instanceof EntityDeviantMob) {
                EntityDeviantMob deviant = (EntityDeviantMob) creature2;
                if (deviant.getMutation() != 0 && !deviant.atMaxMutation()) {
                    flag = false;
                    deviant.updateSupermutationAI();
                    deviant.func_70606_j(Float.MAX_VALUE);
                    deviant.increaseMutation();
                    deviant.setLivesCount(0);
                    event.setCanceled(true);
                }
            }
            if (creature2 instanceof EntityCthulhu) {
                EntityCthulhu cthulhu = (EntityCthulhu) creature2;
                if (!cthulhu.isEssenceCharged()) {
                    flag = false;
                    cthulhu.func_70606_j(Float.MAX_VALUE);
                    cthulhu.setLivesCount(cthulhu.numberOfLives());
                    event.setCanceled(true);
                }
            }
            if (flag && !creature2.didDeathAction()) {
                creature2.trueDeathAction();
                creature2.deathActionComplete();
            }
        }
    }
}
