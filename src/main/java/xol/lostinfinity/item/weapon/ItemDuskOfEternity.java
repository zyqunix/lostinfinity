package xol.lostinfinity.item.weapon;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.IHitReactive;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.item.classify.IPotionReactive;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.util.PotionBasic;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemDuskOfEternity.class */
public class ItemDuskOfEternity extends ItemSword implements IMaxAttack, IHitReactive, IPotionReactive, ISwitchModels, IModeSelect {
    private static final String MODE_DATA = "mode_data";
    private static final int HEX_MODE = 0;
    private static final float HEX_HIT_DAMAGE = 0.5f;
    private static final int SEN_MODE = 1;
    private static final float SEN_HIT_DAMAGE = 0.75f;
    private static final float SEN_SWEEP_ANGLE = 120.0f;
    private static final float SEN_SWEEP_RANGE = 10.0f;
    private static final float SEN_SWEEP_VELOCITY = 5.0f;
    private static final float SEN_SWEEP_DAMAGE = 0.33f;

    public ItemDuskOfEternity(String regName) {
        super(Item.ToolMaterial.DIAMOND);
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
        setModelSwitch("mode", this, 2);
    }

    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (!isPaired(attacker, EnumHand.OFF_HAND)) {
            return true;
        }
        switch (getMode(stack)) {
            case HEX_MODE /* 0 */:
                IMaxAttack.dealTrueDamage(attacker, target, target.func_110138_aP() * HEX_HIT_DAMAGE);
                for (PotionEffect effect : attacker.func_70651_bq()) {
                    Potion potion = effect.func_188419_a();
                    if (isBadPotion(potion)) {
                        target.func_70690_d(new PotionEffect(potion, effect.func_76459_b(), effect.func_76458_c(), effect.func_82720_e(), effect.func_188418_e()));
                    }
                }
                break;
            case SEN_MODE /* 1 */:
                IMaxAttack.dealTrueDamage(attacker, target, target.func_110138_aP() * SEN_HIT_DAMAGE);
                float yaw = attacker.field_70177_z;
                Vec3d attackerPos = attacker.func_174791_d();
                for (EntityLivingBase entityLivingBase : attacker.field_70170_p.func_72872_a(Entity.class, attacker.func_174813_aQ().func_72314_b(10.0d, 5.0d, 10.0d))) {
                    if (entityLivingBase != attacker && entityLivingBase != target && (entityLivingBase instanceof EntityLivingBase) && !(entityLivingBase instanceof EntityImmaterial)) {
                        Vec3d dir = entityLivingBase.func_174791_d().func_178788_d(attackerPos);
                        if (dir.func_189985_c() <= 100.0d) {
                            float angle = (float) (MathHelper.func_181159_b(-dir.field_72450_a, dir.field_72449_c) * 57.295780181884766d);
                            float dif = MathHelper.func_76135_e(LMath.degreeDifference(yaw, angle));
                            if (dif <= 60.0f) {
                                Vec3d loc = entityLivingBase.func_174791_d();
                                CustomParticleConfig config = new CustomParticleConfig();
                                config.createInstance().setParticle(ParticleInit.GRAVITY_RING).setSpread(1.5d, 1.5d, 1.5d).setIgnoreRange(true);
                                IParticleSpawner.spawnParticle(((Entity) entityLivingBase).field_70170_p, config, loc);
                                IMaxAttack.dealTrueDamage(attacker, entityLivingBase, entityLivingBase.func_110138_aP() * SEN_SWEEP_DAMAGE);
                                Vec3d dir2 = LMath.fastNormalize(dir).func_186678_a(5.0d);
                                entityLivingBase.func_70024_g(dir2.field_72450_a, dir2.field_72448_b, dir2.field_72449_c);
                                ((Entity) entityLivingBase).field_70133_I = true;
                            }
                        }
                    }
                }
                break;
        }
        ItemStack other = attacker.func_184592_cb();
        attacker.func_184611_a(EnumHand.MAIN_HAND, other);
        attacker.func_184611_a(EnumHand.OFF_HAND, stack);
        return true;
    }

    @Override // xol.lostinfinity.item.classify.IHitReactive
    public void hitReaction(EntityPlayer player, Entity attacker, float damage, ItemStack stack) {
        if (!player.field_70170_p.field_72995_K && player.func_184586_b(EnumHand.OFF_HAND) == stack && getMode(stack) == SEN_MODE && isPaired(player, EnumHand.MAIN_HAND)) {
            for (Entity entity : player.field_70170_p.func_72872_a(Entity.class, player.func_174813_aQ().func_72314_b(10.0d, 5.0d, 10.0d))) {
                if (isProjectile(entity) && entity != attacker) {
                    entity.func_70106_y();
                    Vec3d loc = entity.func_174791_d();
                    CustomParticleConfig config = new CustomParticleConfig();
                    config.createInstance().setParticle(ParticleInit.GRAVITY_RING).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(entity.field_70170_p, config, loc);
                }
            }
        }
    }

    @Override // xol.lostinfinity.item.classify.IPotionReactive
    public void potionAddReaction(EntityPlayer player, ItemStack stack, EnumHand hand, PotionEffect newEffect, PotionEffect prevEffect) {
        if (hand == EnumHand.OFF_HAND && getMode(stack) == 0 && isPaired(player, EnumHand.MAIN_HAND)) {
            Potion newPotion = newEffect.func_188419_a();
            if (!isBadPotion(newPotion)) {
                return;
            }
            List<Potion> removed = new ArrayList<>();
            for (PotionEffect effect : player.func_70651_bq()) {
                Potion potion = effect.func_188419_a();
                if (isBadPotion(potion)) {
                    removed.add(potion);
                }
            }
            if (!removed.isEmpty()) {
                player.func_184589_d(removed.get(player.field_70170_p.field_73012_v.nextInt(removed.size())));
            }
        }
    }

    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        toggleMode(stack);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Twin sword of the " + TextFmt.Light_Purple + "Dawn of Eternity" + TextFmt.Reset + ".");
        EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
        if (entityPlayerSP != null) {
            if ((entityPlayerSP.func_184586_b(EnumHand.MAIN_HAND) == stack && isPaired(entityPlayerSP, EnumHand.OFF_HAND)) || (entityPlayerSP.func_184586_b(EnumHand.OFF_HAND) == stack && isPaired(entityPlayerSP, EnumHand.MAIN_HAND))) {
                tooltip.add(TextFmt.getFormatting(TextFmt.Dark_Purple, TextFmt.Bold) + "You can feel their power resonating!");
            } else {
                tooltip.add(TextFmt.Dark_Gray + "Without its twins, the sword feels strangely heavier...");
            }
        }
        switch (getMode(stack)) {
            case HEX_MODE /* 0 */:
                tooltip.add(TextFmt.Light_Purple + "Current mode: Hexcorum");
                tooltip.add(TextFmt.Red + "Main hand: Copy all negative effects to your target.");
                tooltip.add(TextFmt.Red + "50% Health True Damage");
                tooltip.add(TextFmt.Blue + "Off hand: When receiving a negative effect, cleanse a random negative effect you have.");
                break;
            case SEN_MODE /* 1 */:
                tooltip.add(TextFmt.Gold + "Current mode: Sentinum");
                tooltip.add(TextFmt.Red + "Main hand: Send a shockwave behind your target and blast anything away.");
                tooltip.add(TextFmt.Red + "75% Health True Damage To Target, 33% Health True Damage Shockwave");
                tooltip.add(TextFmt.Blue + "Off hand: When damaged, destroy all nearby projectiles.");
                break;
        }
    }

    private void toggleMode(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        NBTTagCompound compound = stack.func_77978_p();
        compound.func_74768_a(MODE_DATA, (compound.func_74762_e(MODE_DATA) + SEN_MODE) % 2);
    }

    private int getMode(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        return stack.func_77978_p().func_74762_e(MODE_DATA);
    }

    private boolean isBadPotion(Potion potion) {
        return potion.func_76398_f() || ((potion instanceof PotionBasic) && ((PotionBasic) potion).negativeLostEffect());
    }

    private boolean isProjectile(Entity entity) {
        return (entity instanceof EntityThrowable) || (entity instanceof EntityArrow) || (entity instanceof EntityFireball) || (entity instanceof EntityShulkerBullet);
    }

    private boolean isPaired(EntityLivingBase player, EnumHand other) {
        return player.func_184586_b(other).func_77973_b() instanceof ItemDawnOfEternity;
    }
}
