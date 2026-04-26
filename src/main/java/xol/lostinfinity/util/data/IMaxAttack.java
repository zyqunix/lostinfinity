package xol.lostinfinity.util.data;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.common.special.CommonMinionHandler;
import xol.lostinfinity.dimension.util.IDamageRestricted;
import xol.lostinfinity.init.ArmorInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.armor.ItemLostArmor;
import xol.lostinfinity.item.armor.ItemSpectrosArmor;
import xol.lostinfinity.item.armor.ItemSpectrosPrimeArmor;
import xol.lostinfinity.item.classify.IHitReactive;
import xol.lostinfinity.item.classify.IHotbarDeath;
import xol.lostinfinity.item.classify.IHotbarHit;
import xol.lostinfinity.item.classify.IMaxNullable;
import xol.lostinfinity.item.classify.IMaxReducible;
import xol.lostinfinity.item.classify.ItemSoulbound;
import xol.lostinfinity.item.weapon.ItemHeadCollector;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.mob.entity.base.EntityMultiLivesTameable;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.mob.entity.base.EntityMultipleLivesMount;
import xol.lostinfinity.mob.entity.base.EntityMultipleLivesRelay;
import xol.lostinfinity.mob.entity.base.IConditionalDamage;
import xol.lostinfinity.mob.entity.classify.IEntityReactive;
import xol.lostinfinity.mob.entity.classify.IKnockbackImmunity;
import xol.lostinfinity.mob.entity.classify.IOwnerReactive;
import xol.lostinfinity.mob.entity.minion.EntityMinion;
import xol.lostinfinity.mob.entity.misc.EntityPickleMan;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.player.PlayerManager;
public interface IMaxAttack {
    static CustomDamageResult dealMaxHealth(Entity attacker, EntityLivingBase entity, int de) {
        return dealMaxHealth(attacker, entity, de, 1.0f, null);
    }
    static CustomDamageResult dealMaxHealth(Entity attacker, EntityLivingBase entity, int de, float multiplier) {
        return dealMaxHealth(attacker, entity, de, multiplier, null);
    }
    static CustomDamageResult dealMaxHealth(Entity attacker, EntityLivingBase entity, int de, List<String> damageClass) {
        return dealMaxHealth(attacker, entity, de, 1.0f, damageClass);
    }
    static CustomDamageResult dealPotionDamage(EntityLivingBase target, float effective_damage) {
        return dealPotionDamage(target, effective_damage, null);
    }
    static CustomDamageResult dealTrueDamage(Entity attacker, EntityLivingBase target, float effective_damage) {
        return dealTrueDamage(attacker, target, effective_damage, null);
    }
    static CustomDamageResult dealTrueDamage(Entity attacker, EntityLivingBase target, float effective_damage, List<String> damageClass) {
        return dealTrueDamage(null, attacker, target, effective_damage, damageClass);
    }
    static CustomDamageResult dealMaxHealth(Entity attacker, EntityLivingBase targetedEntity, int de, float multiplier, List<String> damageClass) {
        float effectiveDamage = multiplier * (targetedEntity.func_110138_aP() / de);
        CustomDamageResult damageResult = new CustomDamageResult(attacker, targetedEntity);
        damageResult.addClassifications(damageClass);
        damageResult.setIntendedDamage(effectiveDamage);
        if (!targetedEntity.field_70170_p.field_72995_K) {
            boolean cannotDoDamage = false;
            if (targetedEntity instanceof EntityImmaterial) {
                cannotDoDamage = true;
                damageResult.setHitInvalid();
            }
            if (targetedEntity instanceof EntityPlayer) {
                EntityPlayer playerTarget = (EntityPlayer) targetedEntity;
                if (playerTarget.func_184812_l_()) {
                    cannotDoDamage = true;
                    damageResult.setHitInvalid();
                } else {
                    for (EntityLiving entityLiving : CommonMinionHandler.getMinions(targetedEntity.func_110124_au())) {
                        if (entityLiving instanceof IOwnerReactive) {
                            ((IOwnerReactive) entityLiving).maxHealthDamageEffect(attacker, damageResult);
                        }
                    }
                    cannotDoDamage = !damageResult.didSuccessfulHit();
                    int i = 0;
                    while (true) {
                        if (i >= playerTarget.field_71071_by.func_70302_i_()) {
                            break;
                        }
                        ItemStack stack = playerTarget.field_71071_by.func_70301_a(i);
                        if (stack.func_77973_b() == ItemInit.essencePossessor) {
                            int attackerId = attacker.func_145782_y();
                            NBTTagCompound compound = stack.func_77978_p();
                            boolean hit = true;
                            if (attackerId == compound.func_74762_e("id1")) {
                                if (System.currentTimeMillis() < compound.func_74763_f("time1") + 30000) {
                                    hit = false;
                                }
                            } else if (attackerId == compound.func_74762_e("id2")) {
                                if (System.currentTimeMillis() < compound.func_74763_f("time2") + 30000) {
                                    hit = false;
                                }
                            } else if (attackerId == compound.func_74762_e("id3") && System.currentTimeMillis() < compound.func_74763_f("time3") + 30000) {
                                hit = false;
                            }
                            if (!hit) {
                                cannotDoDamage = true;
                                damageResult.setHitInvalid();
                                break;
                            }
                        }
                        i++;
                    }
                }
            }
            if (targetedEntity.field_70128_L || targetedEntity.func_110143_aJ() <= 0.0f) {
                cannotDoDamage = true;
                damageResult.setHitInvalid();
            }
            IDamageRestricted iDamageRestrictedFunc_180494_b = attacker.field_70170_p.func_180494_b(attacker.func_180425_c());
            if (iDamageRestrictedFunc_180494_b instanceof IDamageRestricted) {
                IDamageRestricted restrictedBiome = iDamageRestrictedFunc_180494_b;
                if (damageClass == null || !damageClass.contains(restrictedBiome.allowedTypes())) {
                    cannotDoDamage = true;
                    damageResult.setHitMissed();
                }
            }
            if (targetedEntity.func_70644_a(PotionInit.OTHERWORLDLY) && (damageClass == null || !damageClass.contains("Darkborn"))) {
                cannotDoDamage = true;
                damageResult.setHitMissed();
            }
            if (!cannotDoDamage) {
                attacker.func_184185_a(SoundEvents.field_187718_dS, 2.0f, 3.0f);
                if (attacker instanceof EntityLivingBase) {
                    ((EntityLivingBase) attacker).func_130011_c(targetedEntity);
                }
                if (targetedEntity instanceof EntityPlayer) {
                    effectiveDamage = performDamageReduction(damageResult, (EntityPlayer) targetedEntity, effectiveDamage);
                }
                float effectiveDamage2 = performTargetPotionChecks(damageResult, attacker, targetedEntity, effectiveDamage);
                if (attacker instanceof EntityLivingBase) {
                    effectiveDamage2 = performAttackerPotionChecks(damageResult, (EntityLivingBase) attacker, effectiveDamage2);
                }
                if (effectiveDamage2 > 0.0f) {
                    if ((targetedEntity instanceof EntityPlayer) && hookPlayerHitEffect(damageResult, attacker, (EntityPlayer) targetedEntity, effectiveDamage2)) {
                        return damageResult;
                    }
                    if ((attacker instanceof EntityPlayer) && hookPlayerAttackEffect(damageResult, (EntityPlayer) attacker, targetedEntity, de, multiplier, effectiveDamage2)) {
                        return damageResult;
                    }
                    if ((targetedEntity instanceof EntityPlayer) && targetedEntity.func_110143_aJ() - effectiveDamage2 <= 0.0f) {
                        EntityPlayer playerTarget2 = (EntityPlayer) targetedEntity;
                        for (int i2 = 0; i2 <= 8; i2++) {
                            ItemStack stack2 = playerTarget2.field_71071_by.func_70301_a(i2);
                            if (stack2.func_77973_b() instanceof IHotbarDeath) {
                                IHotbarDeath hotbarItem = stack2.func_77973_b();
                                boolean wasKilled = hotbarItem.playedKilled(stack2, playerTarget2, attacker, effectiveDamage2);
                                if (!wasKilled) {
                                    return damageResult;
                                }
                            }
                        }
                    }
                    dealTrueDamage(damageResult, attacker, targetedEntity, effectiveDamage2, damageClass);
                }
            }
        }
        return damageResult;
    }
    static CustomDamageResult dealPotionDamage(EntityLivingBase target, float effective_damage, List<String> damageClass) {
        CustomDamageResult damageResult = new CustomDamageResult(null, target);
        damageResult.setIntendedDamage(effective_damage);
        damageResult.addClassifications(damageClass);
        float newDamage = effective_damage;
        if (target.func_70644_a(PotionInit.POTION_AFFINITY)) {
            int amp = target.func_70660_b(PotionInit.POTION_AFFINITY).func_76458_c();
            newDamage = Math.max(0.0f, 0.9f - (0.1f * amp));
            damageResult.addReduction(effective_damage - newDamage);
        }
        return dealTrueDamage(damageResult, null, target, newDamage, damageClass);
    }
    static CustomDamageResult dealTrueDamage(CustomDamageResult incomingResult, Entity attacker, EntityLivingBase target, float effective_damage, List<String> damageClass) {
        CustomDamageResult damageResult = incomingResult;
        if (damageResult == null) {
            damageResult = new CustomDamageResult(attacker, target);
            damageResult.setIntendedDamage(effective_damage);
            damageResult.addClassifications(damageClass);
        }
        if (!target.field_70170_p.field_72995_K) {
            float postReducedDamage = effective_damage;
            if (target.field_70128_L || target.func_110143_aJ() <= 0.0f) {
                damageResult.setHitInvalid();
                postReducedDamage = 0.0f;
            } else if (target.func_70644_a(PotionInit.PROTECTED)) {
                damageResult.setHitMissed();
                postReducedDamage = 0.0f;
            } else if (target instanceof EntityImmaterial) {
                damageResult.setHitInvalid();
                postReducedDamage = 0.0f;
            } else if (target instanceof IConditionalDamage) {
                if (attacker != null) {
                    IConditionalDamage condEntity = (IConditionalDamage) target;
                    if (!condEntity.canBeDamaged(attacker)) {
                        damageResult.setHitMissed();
                        postReducedDamage = 0.0f;
                    }
                }
            } else if ((target instanceof EntityPlayer) && ((EntityPlayer) target).func_184812_l_()) {
                damageResult.setHitInvalid();
                postReducedDamage = 0.0f;
            }
            if (target.func_70644_a(PotionInit.PLANESPLIT)) {
                int level = target.func_70660_b(PotionInit.PLANESPLIT).func_76458_c();
                int randDodge = target.field_70170_p.field_73012_v.nextInt(100);
                if (randDodge <= 10 + (10 * level)) {
                    damageResult.setHitMissed();
                    postReducedDamage = 0.0f;
                }
            }
            if (target.func_70644_a(PotionInit.SUPERCHARGED) && target.field_70170_p.field_73012_v.nextBoolean()) {
                damageResult.setHitMissed();
                postReducedDamage = 0.0f;
            }
            if (target.func_70644_a(PotionInit.FEARED) && (attacker instanceof EntityLivingBase) && ((EntityLivingBase) attacker).func_70644_a(PotionInit.TERRIFIED) && target.field_70170_p.field_73012_v.nextBoolean()) {
                damageResult.setHitMissed();
                postReducedDamage = 0.0f;
            }
            IDamageRestricted iDamageRestrictedFunc_180494_b = target.field_70170_p.func_180494_b(target.func_180425_c());
            if (iDamageRestrictedFunc_180494_b instanceof IDamageRestricted) {
                IDamageRestricted restrictedBiome = iDamageRestrictedFunc_180494_b;
                if (damageClass == null || !damageClass.contains(restrictedBiome.allowedTypes())) {
                    damageResult.setHitMissed();
                    postReducedDamage = 0.0f;
                }
            }
            if (target.func_70644_a(PotionInit.OTHERWORLDLY) && (damageClass == null || !damageClass.contains("Darkborn"))) {
                damageResult.setHitMissed();
                postReducedDamage = 0.0f;
            }
            if ((target instanceof IEntityReactive) && (attacker instanceof EntityLivingBase)) {
                postReducedDamage = ((IEntityReactive) target).hitEffect((EntityLivingBase) attacker, postReducedDamage);
                if (postReducedDamage == 0.0f) {
                    damageResult.setHitMissed();
                }
            }
            if (postReducedDamage > 0.0f && (target instanceof EntityPlayer)) {
                postReducedDamage = performTrueReduction(damageResult, (EntityPlayer) target, attacker, effective_damage);
                if (postReducedDamage == 0.0f) {
                    damageResult.setHitMissed();
                }
            }
            if (target instanceof EntityMultipleLivesRelay) {
                target = ((EntityMultipleLivesRelay) target).mo307getRelay();
            }
            if (target.func_184208_bv() instanceof EntityMultipleLivesMount) {
                target = (EntityLivingBase) target.func_184208_bv();
                ((EntityMultipleLivesMount) target).onDriverDamaged(target, damageResult);
            }
            if (target instanceof EntityPlayer) {
                Set<EntityMinion> minions = CommonMinionHandler.getMinions(target.func_110124_au());
                for (EntityLiving entityLiving : minions) {
                    if (entityLiving instanceof IOwnerReactive) {
                        ((IOwnerReactive) entityLiving).trueDamageEffect(attacker, damageResult);
                    }
                }
            }
            float newHp = target.func_110143_aJ() - postReducedDamage;
            damageResult.finishHitData(postReducedDamage, newHp);
            if (damageResult.didSuccessfulHit()) {
                if (damageResult.targetHealthChanged()) {
                    if (attacker != null) {
                        if (attacker instanceof EntityLivingBase) {
                            ((EntityLivingBase) attacker).func_130011_c(target);
                        }
                        double x = MathHelper.func_151237_a(attacker.field_70159_w * 0.5d, -0.1d, 0.1d);
                        double y = 0.1d;
                        double z = MathHelper.func_151237_a(attacker.field_70179_y * 0.5d, -0.1d, 0.1d);
                        if (target instanceof IKnockbackImmunity) {
                            float res = 1.0f - ((IKnockbackImmunity) target).getKnockbackResistance(damageResult);
                            x *= (double) res;
                            y = 0.1d * ((double) res);
                            z *= (double) res;
                        }
                        target.func_70024_g(x, y, z);
                        target.field_70133_I = true;
                    }
                    if (!(target instanceof EntityPlayer) && !damageResult.didTargetLoseLife()) {
                        target.func_70097_a(DamageSource.field_76376_m, 0.0f);
                    }
                }
                target.func_70606_j(newHp);
                if (target.func_110143_aJ() <= 0.0f) {
                    boolean didKill = false;
                    if (target instanceof EntityMultiLivesTameable) {
                        EntityMultiLivesTameable multiLifer = (EntityMultiLivesTameable) target;
                        if (multiLifer.onFinalLife()) {
                            didKill = true;
                        }
                    } else if (target instanceof EntityMultipleLives) {
                        EntityMultipleLives multiLifer2 = (EntityMultipleLives) target;
                        if (multiLifer2.onFinalLife()) {
                            didKill = true;
                        }
                    } else {
                        didKill = true;
                    }
                    if (attacker != null && (attacker instanceof EntityPlayer) && didKill) {
                        hookPlayerKillEffect((EntityPlayer) attacker, target);
                    }
                    if (target instanceof EntityPlayer) {
                        if (!target.field_70170_p.func_82736_K().func_82766_b("keepInventory")) {
                            EntityPlayer pl = (EntityPlayer) target;
                            ItemSoulbound.removeSoulBound(pl);
                            pl.field_71071_by.func_70436_m();
                        }
                        if (attacker != null) {
                            target.func_184102_h().func_184103_al().func_148539_a(new TextComponentString(TextFmt.Gray + target.func_70005_c_() + getRandomDeathMessage(target.field_70170_p) + attacker.func_70005_c_()));
                        }
                        target.func_70106_y();
                    } else {
                        target.func_70645_a(DamageSource.field_76376_m);
                    }
                }
            }
        }
        return damageResult;
    }
    static boolean hookPlayerAttackEffect(CustomDamageResult result, EntityPlayer attacker, EntityLivingBase targeted, int de, float multiplier, float effective_damage) {
        World world = attacker.field_70170_p;
        if (PlayerManager.isPlayerWearingFullSet(attacker, ArmorInit.spectrosSet)) {
            ItemStack stack = attacker.field_71071_by.func_70301_a(39);
            ItemSpectrosArmor spectros = stack.func_77973_b();
            spectros.spectreEffect(world, attacker, targeted, stack);
            return false;
        }
        if (PlayerManager.isPlayerWearingFullSet(attacker, ArmorInit.spectrosPrimeSet)) {
            ItemStack stack2 = attacker.field_71071_by.func_70301_a(39);
            ItemSpectrosPrimeArmor spectros2 = stack2.func_77973_b();
            spectros2.spectreEffect(world, attacker, targeted, stack2);
            return false;
        }
        if (PlayerManager.isPlayerWearingFullSet(attacker, ArmorInit.blightcystSet)) {
            if (!world.field_72995_K && !attacker.func_70644_a(PotionInit.NULLIFIED)) {
                ItemStack helm = (ItemStack) attacker.field_71071_by.field_70460_b.get(3);
                if (!helm.func_77942_o()) {
                    helm.func_77982_d(new NBTTagCompound());
                }
                float storedDamage = helm.func_77978_p().func_74760_g("DamageStored");
                if (storedDamage == 0.0f) {
                    dealMaxHealth((Entity) targeted, (EntityLivingBase) attacker, 10, 3.0f);
                    return false;
                }
                dealTrueDamage(attacker, targeted, storedDamage);
                helm.func_77978_p().func_74776_a("DamageStored", 0.0f);
                return false;
            }
            return false;
        }
        if (PlayerManager.isPlayerWearingFullSet(attacker, ArmorInit.blightcystPrimeSet) && !world.field_72995_K && !attacker.func_70644_a(PotionInit.NULLIFIED)) {
            ItemStack helm2 = (ItemStack) attacker.field_71071_by.field_70460_b.get(3);
            if (!helm2.func_77942_o()) {
                helm2.func_77982_d(new NBTTagCompound());
            }
            float storedDamage2 = helm2.func_77978_p().func_74760_g("DamageStored");
            if (storedDamage2 > 0.0f) {
                dealTrueDamage(attacker, targeted, storedDamage2);
                helm2.func_77978_p().func_74776_a("DamageStored", 0.0f);
                return false;
            }
            return false;
        }
        return false;
    }
    static float performTargetPotionChecks(CustomDamageResult result, Entity attacker, EntityLivingBase target, float damage) {
        float newDmg = damage;
        if (target.func_70644_a(PotionInit.VULNERABILITY)) {
            int level = target.func_70660_b(PotionInit.VULNERABILITY).func_76458_c();
            newDmg *= 1.2f + (0.2f * level);
        }
        if (target.func_70644_a(PotionInit.RAMPAGING)) {
            int level2 = 1 + target.func_70660_b(PotionInit.RAMPAGING).func_76458_c();
            float reduction = 1.0f - (0.06f * level2);
            newDmg *= reduction;
        }
        if (target.func_70644_a(PotionInit.IRONHEART)) {
            newDmg = 0.0f;
        }
        if (target.func_70644_a(PotionInit.SHATTERED)) {
            newDmg = 0.0f;
            int level3 = target.func_70660_b(PotionInit.SHATTERED).func_76458_c();
            target.func_184596_c(PotionInit.SHATTERED);
            float multi = 1.0f + (level3 * 0.2f);
            dealTrueDamage(attacker, target, damage * multi);
        }
        return newDmg;
    }
    static float performAttackerPotionChecks(CustomDamageResult result, EntityLivingBase attacker, float damage) {
        float newDmg = damage;
        if (attacker.func_70644_a(PotionInit.ADRENALINE)) {
            int level = attacker.func_70660_b(PotionInit.ADRENALINE).func_76458_c();
            newDmg *= 1.3f + (0.3f * level);
        }
        if (attacker.func_70644_a(PotionInit.BLIGHTED)) {
            int level2 = attacker.func_70660_b(PotionInit.BLIGHTED).func_76458_c();
            float reducer = Math.max(0.3f - (0.05f * level2), 0.0f);
            newDmg *= reducer;
        }
        if (attacker.func_70644_a(PotionInit.RAMPAGING)) {
            int level3 = 1 + attacker.func_70660_b(PotionInit.RAMPAGING).func_76458_c();
            float increase = 1.0f + (0.05f * level3);
            newDmg *= increase;
        }
        return newDmg;
    }
    static float performTrueReduction(CustomDamageResult result, EntityPlayer playerIn, Entity attacker, float damage) {
        float newDamage = damage;
        if (PlayerManager.isPlayerWearingFullSet(playerIn, ArmorInit.bionicveggitronSet)) {
            Iterator it = playerIn.field_70170_p.func_72872_a(EntityPickleMan.class, playerIn.func_174813_aQ().func_186662_g(18.0d)).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                EntityPickleMan near_pickle = (EntityPickleMan) it.next();
                if (near_pickle.func_184753_b().equals(playerIn.func_110124_au())) {
                    near_pickle.func_70106_y();
                    near_pickle.field_70170_p.func_184133_a((EntityPlayer) null, near_pickle.func_180425_c(), SoundInit.MAGIC_WEAPON_7, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    CustomParticleConfig config1 = new CustomParticleConfig();
                    config1.createInstance().setParticle(ParticleInit.GLOOM_BURST).setSpread(1.0d, 1.0d, 1.0d).setCount(3).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(near_pickle.field_70170_p, config1, near_pickle.field_70165_t, near_pickle.field_70163_u + ((double) (near_pickle.field_70131_O / 2.0f)), near_pickle.field_70161_v);
                    result.addReduction(newDamage);
                    newDamage = 0.0f;
                    break;
                }
            }
        } else if (PlayerManager.isPlayerWearingFullSet(playerIn, ArmorInit.vitralitonPrimeSet)) {
            if (playerIn.func_110143_aJ() == playerIn.func_110138_aP() && playerIn.field_70170_p.field_73012_v.nextInt(4) != 0) {
                newDamage = 0.0f;
                playerIn.field_70170_p.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.RAPID_DODGE, SoundCategory.PLAYERS, 0.5f, 0.7f + (playerIn.field_70170_p.field_73012_v.nextFloat() * 0.6f));
            }
        } else if (PlayerManager.isPlayerWearingFullSet(playerIn, ArmorInit.blightcystPrimeSet) && !playerIn.func_70644_a(PotionInit.NULLIFIED)) {
            ItemStack helm = (ItemStack) playerIn.field_71071_by.field_70460_b.get(3);
            if (playerIn.field_70170_p.field_73012_v.nextBoolean() && !helm.func_77942_o()) {
                helm.func_77982_d(new NBTTagCompound());
                float storedDamage = helm.func_77978_p().func_74760_g("DamageStored");
                helm.func_77978_p().func_74776_a("DamageStored", storedDamage + damage);
                newDamage = 0.0f;
            }
        }
        if (playerIn.func_70644_a(PotionInit.SUPERCHARGED)) {
            int amp = playerIn.func_70660_b(PotionInit.SUPERCHARGED).func_76458_c();
            if (damage / 2.0f > playerIn.func_110143_aJ() && amp > 0) {
                newDamage = 0.0f;
                playerIn.func_70606_j(playerIn.func_110138_aP());
                playerIn.func_184589_d(PotionInit.SUPERCHARGED);
                playerIn.func_70690_d(new PotionEffect(PotionInit.SUPERCHARGED, 72000, amp - 1));
                if (attacker instanceof EntityPlayer) {
                    attacker.func_145747_a(new TextComponentString("Super-Charged target has " + amp + " lives remaining"));
                }
            } else {
                newDamage = damage / 2.0f;
            }
        }
        float multiplier = 1.0f;
        int i = 0;
        while (i < 2) {
            ItemStack heldByTarget = i == 0 ? playerIn.func_184614_ca() : playerIn.func_184592_cb();
            if (heldByTarget.func_77973_b() instanceof IMaxReducible) {
                IMaxReducible reducible = heldByTarget.func_77973_b();
                multiplier = reducible.reduceMaxDamage(playerIn, i == 0, newDamage, multiplier, heldByTarget);
            }
            i++;
        }
        float return_damage = newDamage * Math.max(multiplier, 0.2f);
        int i2 = 0;
        while (i2 < 2) {
            ItemStack heldByTarget2 = i2 == 0 ? playerIn.func_184614_ca() : playerIn.func_184592_cb();
            if (heldByTarget2.func_77973_b() instanceof IMaxNullable) {
                IMaxNullable nullable = heldByTarget2.func_77973_b();
                return_damage = nullable.nullableReaction(playerIn, i2 == 0, damage, nullable.trueNullableReaction(playerIn, i2 == 0, damage, return_damage, heldByTarget2, result), heldByTarget2);
            }
            i2++;
        }
        return return_damage;
    }
    static float performDamageReduction(CustomDamageResult result, EntityPlayer playerIn, float damage) {
        float multiplier = 1.0f;
        boolean hasPrime = false;
        for (ItemStack stack : playerIn.func_184193_aE()) {
            if (stack.func_77973_b() instanceof ItemLostArmor) {
                multiplier -= 0.02f;
                if (stack.func_77973_b().isPrimeSet()) {
                    hasPrime = true;
                }
            }
        }
        if (PlayerManager.isWearingAnySet(playerIn)) {
            if (hasPrime) {
                multiplier -= 0.22f;
            } else {
                multiplier -= 0.07f;
            }
        }
        int i = 0;
        while (i < 2) {
            ItemStack heldByTarget = i == 0 ? playerIn.func_184614_ca() : playerIn.func_184592_cb();
            if (heldByTarget.func_77973_b() instanceof IMaxReducible) {
                IMaxReducible reducible = heldByTarget.func_77973_b();
                multiplier = reducible.reduceMaxDamage(playerIn, i == 0, damage, multiplier, heldByTarget);
            }
            i++;
        }
        float return_damage = damage * Math.max(multiplier, 0.2f);
        int i2 = 0;
        while (i2 < 2) {
            ItemStack heldByTarget2 = i2 == 0 ? playerIn.func_184614_ca() : playerIn.func_184592_cb();
            if (heldByTarget2.func_77973_b() instanceof IMaxNullable) {
                IMaxNullable nullable = heldByTarget2.func_77973_b();
                return_damage = nullable.nullableReaction(playerIn, i2 == 0, damage, return_damage, heldByTarget2);
            }
            i2++;
        }
        return return_damage;
    }
    static boolean hookPlayerHitEffect(CustomDamageResult result, Entity attacker, EntityPlayer targeted, float effective_damage) {
        if (PlayerManager.isPlayerWearingFullSet(targeted, ArmorInit.plasmythicSet) && (attacker instanceof EntityLivingBase) && !((EntityLivingBase) attacker).func_70644_a(PotionInit.NULLIFIED)) {
            dealTrueDamage(targeted, (EntityLivingBase) attacker, effective_damage * 0.35f);
        }
        if (PlayerManager.isPlayerWearingFullSet(targeted, ArmorInit.blightcystSet)) {
            if (!targeted.func_70644_a(PotionInit.NULLIFIED)) {
                ItemStack helm = (ItemStack) targeted.field_71071_by.field_70460_b.get(3);
                if (!helm.func_77942_o()) {
                    helm.func_77982_d(new NBTTagCompound());
                }
                float storedDamage = helm.func_77978_p().func_74760_g("DamageStored");
                helm.func_77978_p().func_74776_a("DamageStored", storedDamage + (effective_damage * 0.25f));
            }
        } else if (PlayerManager.isPlayerWearingFullSet(targeted, ArmorInit.blightcystPrimeSet) && !targeted.func_70644_a(PotionInit.NULLIFIED)) {
            ItemStack helm2 = (ItemStack) targeted.field_71071_by.field_70460_b.get(3);
            if (!helm2.func_77942_o()) {
                helm2.func_77982_d(new NBTTagCompound());
            }
            float storedDamage2 = helm2.func_77978_p().func_74760_g("DamageStored");
            helm2.func_77978_p().func_74776_a("DamageStored", storedDamage2 + effective_damage);
        }
        int i = 0;
        while (i < 2) {
            ItemStack heldByTarget = i == 0 ? targeted.func_184614_ca() : targeted.func_184592_cb();
            if (heldByTarget.func_77973_b() instanceof IHitReactive) {
                IHitReactive reactiveItem = heldByTarget.func_77973_b();
                reactiveItem.hitReaction(targeted, attacker, effective_damage, heldByTarget);
            }
            i++;
        }
        for (int i2 = 0; i2 <= 8; i2++) {
            ItemStack stack = targeted.field_71071_by.func_70301_a(i2);
            if (stack.func_77973_b() instanceof IHotbarHit) {
                IHotbarHit hotbarItem = stack.func_77973_b();
                hotbarItem.hitReaction(targeted, attacker, result, stack);
            }
        }
        if (attacker instanceof EntityLivingBase) {
            if (targeted.func_184592_cb().func_77973_b().equals(ItemInit.mirrorShield)) {
                dealTrueDamage(targeted, (EntityLivingBase) attacker, effective_damage);
            } else if (targeted.func_184592_cb().func_77973_b().equals(ItemInit.arcOfTheForbidden)) {
                dealTrueDamage(targeted, (EntityLivingBase) attacker, effective_damage * 0.7f);
            } else if (targeted.func_184592_cb().func_77973_b().equals(ItemInit.dualDefender)) {
                if (targeted.func_110143_aJ() == targeted.func_110138_aP()) {
                    dealTrueDamage(targeted, (EntityLivingBase) attacker, effective_damage * 0.25f);
                } else {
                    dealTrueDamage(targeted, (EntityLivingBase) attacker, effective_damage * 1.25f);
                }
            }
        }
        ItemStack vessel = null;
        int i3 = 0;
        while (true) {
            if (i3 > 8) {
                break;
            }
            ItemStack stack2 = targeted.field_71071_by.func_70301_a(i3);
            if (!stack2.func_77973_b().equals(ItemInit.lifeVessel)) {
                i3++;
            } else {
                vessel = stack2;
                break;
            }
        }
        if (vessel != null) {
            if (!vessel.func_77942_o()) {
                vessel.func_77982_d(new NBTTagCompound());
                vessel.func_77978_p().func_74776_a("charge", 0.0f);
            }
            float charge = vessel.func_77978_p().func_74760_g("charge");
            if (charge != 100.0f) {
                if (vessel.func_77978_p().func_186855_b("target")) {
                    EntityPlayerMP entityPlayerMPFunc_177451_a = targeted.field_70170_p.func_73046_m().func_184103_al().func_177451_a(vessel.func_77978_p().func_186857_a("target"));
                    if (entityPlayerMPFunc_177451_a != null) {
                        if (!((EntityPlayer) entityPlayerMPFunc_177451_a).field_70128_L) {
                            float newhp = entityPlayerMPFunc_177451_a.func_110143_aJ() - effective_damage;
                            if (newhp <= 0.0f) {
                                vessel.func_77978_p().func_82580_o("targetMost");
                                vessel.func_77978_p().func_82580_o("targetLeast");
                                targeted.field_70170_p.func_184133_a((EntityPlayer) null, new BlockPos(targeted.field_70165_t, targeted.field_70163_u, targeted.field_70161_v), SoundEvents.field_187561_bM, SoundCategory.MASTER, 1.0f, 1.0f);
                            }
                            dealTrueDamage(targeted, entityPlayerMPFunc_177451_a, effective_damage);
                            return true;
                        }
                        return false;
                    }
                    vessel.func_77978_p().func_82580_o("targetMost");
                    vessel.func_77978_p().func_82580_o("targetLeast");
                    return false;
                }
                if (!vessel.func_77978_p().func_186855_b("target")) {
                    float percentage = ((effective_damage / targeted.func_110138_aP()) * 50.0f) + charge;
                    if (percentage >= 100.0f) {
                        percentage = 100.0f;
                        targeted.field_70170_p.func_184133_a((EntityPlayer) null, new BlockPos(targeted.field_70165_t, targeted.field_70163_u, targeted.field_70161_v), SoundEvents.field_187754_de, SoundCategory.MASTER, 1.0f, 1.0f);
                    }
                    vessel.func_77978_p().func_74776_a("charge", percentage);
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }
    static void hookPlayerKillEffect(EntityPlayer attacker, EntityLivingBase killed) {
        SoundEvent sound;
        for (int i = 0; i <= 8; i++) {
            ItemStack stack = attacker.field_71071_by.func_70301_a(i);
            if (stack.func_77973_b().equals(ItemInit.containerOfCollectionEmpty)) {
                int addcharge = killed instanceof EntityPlayer ? 50 : 1;
                if (!stack.func_77942_o()) {
                    stack.func_77982_d(new NBTTagCompound());
                    stack.func_77978_p().func_74768_a("charge", addcharge);
                } else {
                    int newcharges = stack.func_77978_p().func_74762_e("charge") + addcharge;
                    if (newcharges >= 250) {
                        ItemStack full_container = new ItemStack(ItemInit.containerOfCollectionFull, 1);
                        attacker.field_71071_by.func_70299_a(i, full_container);
                    } else {
                        stack.func_77978_p().func_74768_a("charge", newcharges);
                    }
                }
            }
        }
        if (killed.func_70644_a(PotionInit.SUPERCHARGED)) {
            killed.func_145779_a(ItemInit.containedLifeforce, 1);
        }
        if (attacker.func_70644_a(PotionInit.RAMPAGING)) {
            PotionEffect activePot = attacker.func_70660_b(PotionInit.RAMPAGING);
            int level = activePot.func_76458_c();
            int duration = activePot.func_76459_b();
            if (level < 9) {
                level++;
            }
            if (level < 2) {
                sound = SoundInit.RAMPAGE_1;
            } else if (level < 4) {
                sound = SoundInit.RAMPAGE_2;
            } else if (level < 6) {
                sound = SoundInit.RAMPAGE_3;
            } else if (level < 8) {
                sound = SoundInit.RAMPAGE_4;
            } else {
                sound = SoundInit.RAMPAGE_5;
            }
            attacker.field_70170_p.func_184133_a((EntityPlayer) null, attacker.func_180425_c(), sound, SoundCategory.PLAYERS, 1.25f, 1.0f);
            int duration2 = duration + 300;
            if (duration2 > 1000) {
                duration2 = 1000;
            }
            attacker.func_70690_d(new PotionEffect(PotionInit.RAMPAGING, duration2, level));
        }
        if (PlayerManager.isPlayerWearingFullSet(attacker, ArmorInit.bionicveggitronSet)) {
            World world = attacker.field_70170_p;
            EntityPickleMan pickleman = new EntityPickleMan(world);
            pickleman.func_70107_b(killed.field_70165_t, killed.field_70163_u + 0.5d, killed.field_70161_v);
            pickleman.func_193101_c(attacker);
            world.func_72838_d(pickleman);
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.NATURE_MAGIC).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(world, config1, killed.field_70165_t, killed.field_70163_u + 0.5d, killed.field_70161_v);
        }
    }
    static String getRandomDeathMessage(World w) {
        switch (w.field_73012_v.nextInt(17)) {
            case 0:
                return " was brutally slain by ";
            case 1:
                return " had no chance fighting ";
            case 2:
                return " got completely dominated by ";
            case 3:
                return " was ripped apart by ";
            case TileEntityFusionTable.BOARD_ROWS :
                return " was humiliated by ";
            case 5:
                return " attempted to fight ";
            case TileEntityFusionTable.BOARD_COLUMNS :
                return " was far weaker than ";
            case 7:
                return " was no match for ";
            case 8:
                return " got wrecked by ";
            case 9:
                return " was shown who is the boss by ";
            case ItemHeadCollector.CHARGE_LIMIT :
                return " is a joke compared to ";
            case 11:
                return " wishes they had a chance against ";
            case 12:
                return " tried and tried but still failed against ";
            case 13:
                return " got rolled by ";
            case 14:
                return " was a fool for attempting to fight ";
            case 15:
                return " was decimated by ";
            case 16:
                return " was split in half by ";
            default:
                return "";
        }
    }
}
