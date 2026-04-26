package xol.lostinfinity.item.weapon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.item.classify.IHotbarDeath;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.mob.entity.minion.EntityMinion;
import xol.lostinfinity.mob.entity.misc.EntityTNTZombie;
import xol.lostinfinity.projectile.entity.EntitySoundwaveBullet;
import xol.lostinfinity.util.PotionBasic;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemReplicaInfinityGauntlet.class */
public class ItemReplicaInfinityGauntlet extends ItemCooldown implements IMaxAttack, IModeSelect, ICustomHoldPose, IHotbarDeath {
    public ItemReplicaInfinityGauntlet(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (showDurabilityBar(stack)) {
            return super.func_77659_a(worldIn, playerIn, handIn);
        }
        if (!worldIn.field_72995_K) {
            CustomParticleConfig config = new CustomParticleConfig();
            switch (getMode(stack)) {
                case 0:
                    gravityGrasp(playerIn);
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MAGIC_WEAPON_5, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    config.createInstance().setCount(10).setParticle(ParticleInit.GRAVITY_RING).setSpread(3.0d, 2.0d, 3.0d).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(worldIn, config, playerIn.func_174791_d().func_72441_c(0.0d, playerIn.func_70047_e(), 0.0d));
                    break;
                case 1:
                    thunderSkin(playerIn);
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MAGIC_POWDER, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    config.createInstance().setCount(10).setParticle(ParticleInit.LIGHT_FLASH).setSpread(1.5d, 2.0d, 1.5d).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(worldIn, config, playerIn.func_174791_d().func_72441_c(0.0d, playerIn.func_70047_e(), 0.0d));
                    break;
                case 2:
                    embuedEmbers(playerIn);
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.DRAGON_FIRE_BALL, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    break;
                case 3:
                    riteOfRise(playerIn);
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.DROID_SUMMON, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    break;
                case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                    echoBlast(playerIn);
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.SOUND_GUN, SoundCategory.PLAYERS, 1.0f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
                    break;
                case 5:
                    realityCleanse(playerIn);
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.LIGHT_MAGIC, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    config.createInstance().setCount(100).setParticle(ParticleInit.GOLDEN_MAGIC).setSpread(2.0d, 2.0d, 2.0d).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(worldIn, config, playerIn.func_174791_d().func_72441_c(0.0d, playerIn.func_70047_e(), 0.0d));
                    break;
            }
        }
        startCooldown(stack);
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    private void gravityGrasp(EntityPlayer playerIn) {
        List<EntityLivingBase> entities = playerIn.field_70170_p.func_72872_a(EntityLivingBase.class, playerIn.func_174813_aQ().func_186662_g(50.0d));
        for (EntityLivingBase entityLivingBase : entities) {
            if (entityLivingBase != playerIn && (!(entityLivingBase instanceof IEntityOwnable) || ((IEntityOwnable) entityLivingBase).func_184753_b() != playerIn.func_110124_au())) {
                if (!(entityLivingBase instanceof EntityMinion) || ((EntityMinion) entityLivingBase).func_70902_q() != playerIn) {
                    if (!(entityLivingBase instanceof EntityPlayer) || (!((EntityPlayer) entityLivingBase).func_184812_l_() && !((EntityPlayer) entityLivingBase).func_175149_v())) {
                        entityLivingBase.field_70159_w = (playerIn.field_70165_t - entityLivingBase.field_70165_t) / 5.0d;
                        entityLivingBase.field_70181_x = ((playerIn.field_70163_u - entityLivingBase.field_70163_u) / 5.0d) + 0.25d;
                        entityLivingBase.field_70179_y = (playerIn.field_70161_v - entityLivingBase.field_70161_v) / 5.0d;
                        entityLivingBase.field_70133_I = true;
                        IMaxAttack.dealTrueDamage(playerIn, entityLivingBase, entityLivingBase.func_110138_aP() * 0.5f);
                    }
                }
            }
        }
    }

    private void thunderSkin(EntityPlayer playerIn) {
        playerIn.func_70690_d(new PotionEffect(PotionInit.POTION_AFFINITY, 200, 4));
        playerIn.func_70690_d(new PotionEffect(PotionInit.ADRENALINE, 200, 4));
    }

    private void embuedEmbers(EntityPlayer playerIn) {
        playerIn.func_70690_d(new PotionEffect(PotionInit.UNLEASHING, 400, 5));
        playerIn.func_70690_d(new PotionEffect(PotionInit.NITROUS, 400, 3));
    }

    private void riteOfRise(EntityPlayer playerIn) {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            EntityTNTZombie zombie = new EntityTNTZombie(playerIn.field_70170_p);
            zombie.func_184754_b(playerIn.func_110124_au());
            zombie.func_70107_b((playerIn.field_70165_t + ((double) rand.nextInt(10))) - 5.0d, playerIn.field_70163_u, (playerIn.field_70161_v + ((double) rand.nextInt(10))) - 5.0d);
            playerIn.field_70170_p.func_72838_d(zombie);
        }
    }

    private void echoBlast(EntityPlayer playerIn) {
        for (int i = 0; i < 8; i++) {
            EntitySoundwaveBullet bullet = new EntitySoundwaveBullet(playerIn.field_70170_p, playerIn);
            bullet.setThrower(playerIn);
            bullet.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.5f, 7.0f);
            playerIn.field_70170_p.func_72838_d(bullet);
        }
    }

    private void realityCleanse(EntityPlayer playerIn) {
        List<PotionEffect> effectsToRemove = new ArrayList<>();
        for (PotionEffect effect : playerIn.func_70651_bq()) {
            if (effect.func_188419_a() instanceof PotionBasic) {
                PotionBasic potion = (PotionBasic) effect.func_188419_a();
                if (potion.negativeLostEffect()) {
                    effectsToRemove.add(effect);
                }
            } else if (effect.func_188419_a().func_76398_f()) {
                effectsToRemove.add(effect);
            }
        }
        Iterator<PotionEffect> it = effectsToRemove.iterator();
        while (it.hasNext()) {
            playerIn.func_184589_d(it.next().func_188419_a());
        }
    }

    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        int mode = getMode(stack) == 5 ? 0 : getMode(stack) + 1;
        if (mode == 5) {
            setMode(stack, mode);
        } else {
            setMode(stack, mode);
        }
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        switch (mode) {
            case 0:
                stack.func_77978_p().func_74768_a("ComplexCooldown", 1000);
                break;
            case 1:
                stack.func_77978_p().func_74768_a("ComplexCooldown", 3000);
                break;
            case 2:
                stack.func_77978_p().func_74768_a("ComplexCooldown", 3000);
                break;
            case 3:
                stack.func_77978_p().func_74768_a("ComplexCooldown", 2000);
                break;
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                stack.func_77978_p().func_74768_a("ComplexCooldown", 300);
                break;
            case 5:
                stack.func_77978_p().func_74768_a("ComplexCooldown", 1000);
                break;
        }
        announceUpdate(player, mode);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected boolean hasSimpleCooldown() {
        return false;
    }

    private void announceUpdate(EntityPlayer player, int mode) {
        switch (mode) {
            case 0:
                player.func_146105_b(new TextComponentString("Gravity Grasp"), true);
                break;
            case 1:
                player.func_146105_b(new TextComponentString("Thunder Skin"), true);
                break;
            case 2:
                player.func_146105_b(new TextComponentString("Embued Embers"), true);
                break;
            case 3:
                player.func_146105_b(new TextComponentString("Rite of Rise"), true);
                break;
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                player.func_146105_b(new TextComponentString("Echo Blast"), true);
                break;
            case 5:
                player.func_146105_b(new TextComponentString("Reality Cleanse"), true);
                break;
        }
    }

    private void setMode(ItemStack stack, int mode) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.func_77978_p().func_74768_a("mode", mode);
    }

    private int getMode(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        return stack.func_77978_p().func_74762_e("mode");
    }

    @Override // xol.lostinfinity.item.classify.IHotbarDeath
    public boolean playedKilled(ItemStack stack, EntityPlayer player, Entity attacker, float damageDealt) {
        World world = player.field_70170_p;
        if (player.func_70644_a(PotionInit.LAST_BREATH)) {
            int level = player.func_70660_b(PotionInit.LAST_BREATH).func_76458_c();
            if (level >= 5) {
                return true;
            }
            if (!world.field_72995_K) {
                potionApplication(world, player, level + 1);
            }
        } else if (!world.field_72995_K) {
            potionApplication(world, player, 0);
        }
        player.func_70691_i(player.func_110138_aP());
        return false;
    }

    private void potionApplication(World world, EntityPlayer player, int amp) {
        player.func_70690_d(new PotionEffect(PotionInit.LAST_BREATH, 200, amp));
        for (EntityLivingBase near_creature : player.field_70170_p.func_72872_a(EntityLivingBase.class, player.func_174813_aQ().func_72314_b(20.0d, 10.0d, 20.0d))) {
            if (!near_creature.func_110124_au().equals(player.func_110124_au())) {
                player.func_70690_d(new PotionEffect(PotionInit.BLIGHTED, 200, amp));
            }
        }
        if (!world.field_72995_K) {
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.BLIGHT_SPELL_GREEN).setSpread(4.0d, 1.0d, 4.0d).setCount(4).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(world, config1, player.field_70165_t, player.field_70163_u + 1.0d, player.field_70161_v);
            CustomParticleConfig config2 = new CustomParticleConfig();
            config2.createInstance().setParticle(ParticleInit.CORRUPTION_MAGIC).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(world, config2, player.field_70165_t, player.field_70163_u + 1.0d, player.field_70161_v);
            world.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundEvents.field_187664_bz, SoundCategory.MASTER, 1.0f, 1.0f);
        }
    }
}
