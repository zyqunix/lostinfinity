package xol.lostinfinity.item.weapon;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.projectile.entity.EntityChainOfVenomsAttack;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemChainOfVenoms extends ItemCooldownSword implements IMaxAttack, ICustomRaytrace {
    private static final int chainCount = 4;
    private static final int range = 10;
    public ItemChainOfVenoms(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    private ArrayList<EntityLivingBase> findChainEnemies(EntityLivingBase target, EntityPlayer playerIn, World worldIn) {
        ArrayList<EntityLivingBase> entitiesHit = new ArrayList<>();
        ArrayList<EntityLivingBase> entitiesVisited = new ArrayList<>();
        entitiesVisited.add(playerIn);
        entitiesVisited.add(target);
        entitiesHit.add(target);
        for (int count = 0; count < 4; count++) {
            EntityLivingBase chainFrom = entitiesHit.get(entitiesHit.size() - 1);
            BlockPos pos = chainFrom.func_180425_c();
            AxisAlignedBB checkBox = new AxisAlignedBB(pos.func_177982_a(-10, -10, -10), pos.func_177982_a(10, 10, 10));
            EntityLivingBase closest = null;
            double minDist = 1000.0d;
            for (EntityLivingBase entity : worldIn.func_72872_a(EntityLivingBase.class, checkBox)) {
                if (!entitiesVisited.contains(entity)) {
                    double dist = entity.func_70032_d(chainFrom);
                    if (dist <= minDist) {
                        minDist = dist;
                        closest = entity;
                    }
                }
            }
            if (closest != null) {
                entitiesVisited.add(closest);
                entitiesHit.add(closest);
            }
        }
        return entitiesHit;
    }
    private void dealChainDamage(EntityPlayer playerIn, ArrayList<EntityLivingBase> entitiesHit) {
        for (EntityLivingBase target : entitiesHit) {
            int potion_count = target.func_70651_bq().size();
            if (potion_count > 0) {
                IMaxAttack.dealMaxHealth((Entity) playerIn, target, 10, potion_count);
            }
        }
    }
    private ArrayList<EntityChainOfVenomsAttack> createChainRender(World worldIn, EntityPlayer player, ArrayList<EntityLivingBase> entitiesHit) {
        ArrayList<EntityChainOfVenomsAttack> attackEntities = new ArrayList<>();
        if (entitiesHit != null && entitiesHit.size() > 1) {
            for (int i = 0; i < entitiesHit.size() - 1; i++) {
                EntityLivingBase origin = entitiesHit.get(i);
                EntityLivingBase target = entitiesHit.get(i + 1);
                EntityChainOfVenomsAttack attackEntity = new EntityChainOfVenomsAttack(worldIn);
                attackEntity.setOrigin(origin.func_145782_y());
                attackEntity.setTarget(target.func_145782_y());
                attackEntity.setCaster(player);
                attackEntity.func_70107_b(origin.field_70165_t, origin.field_70163_u, origin.field_70161_v);
                attackEntities.add(attackEntity);
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.VENOM).setSpread(4.0d, 1.0d, 4.0d).setCount(2).setIgnoreRange(true);
                CustomParticleConfig config2 = new CustomParticleConfig();
                config2.createInstance().setParticle(ParticleInit.VENOM_RING).setSpread(1.0d, 1.0d, 1.0d).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(worldIn, config1, target.field_70165_t, target.field_70163_u + ((double) (target.field_70131_O / 2.0f)), target.field_70161_v);
                IParticleSpawner.spawnParticle(worldIn, config2, target.field_70165_t, target.field_70163_u + ((double) (target.field_70131_O / 2.0f)), target.field_70161_v);
            }
        }
        return attackEntities;
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult trace_result;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack) && !playerIn.func_70093_af()) {
            if (!worldIn.field_72995_K && (trace_result = standardFXTrace(worldIn, playerIn, 45, EnumParticleTypes.SMOKE_NORMAL, EntityLivingBase.class)) != null && trace_result.getResultEntity() != null) {
                EntityLivingBase target = (EntityLivingBase) trace_result.getResultEntity();
                ArrayList<EntityLivingBase> entitiesHit = findChainEnemies(target, playerIn, worldIn);
                ArrayList<EntityChainOfVenomsAttack> attackEntities = createChainRender(worldIn, playerIn, entitiesHit);
                renderChains(worldIn, attackEntities);
                dealChainDamage(playerIn, entitiesHit);
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.VENOM).setSpread(4.0d, 1.0d, 4.0d).setCount(2).setIgnoreRange(true);
                CustomParticleConfig config2 = new CustomParticleConfig();
                config2.createInstance().setParticle(ParticleInit.VENOM_RING).setSpread(1.0d, 1.0d, 1.0d).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(worldIn, config1, target.field_70165_t, target.field_70163_u + ((double) (target.field_70131_O / 2.0f)), target.field_70161_v);
                IParticleSpawner.spawnParticle(worldIn, config2, target.field_70165_t, target.field_70163_u + ((double) (target.field_70131_O / 2.0f)), target.field_70161_v);
            }
            playerIn.func_184185_a(SoundInit.LASER_WEAPON_6, 1.0f, 1.0f);
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    private void renderChains(World worldIn, ArrayList<EntityChainOfVenomsAttack> attackEntities) {
        if (attackEntities != null) {
            for (EntityChainOfVenomsAttack attackEntity : attackEntities) {
                worldIn.func_72838_d(attackEntity);
            }
        }
    }
    @Override // xol.lostinfinity.item.weapon.ItemCooldownSword
    protected int getCooldown() {
        return 300;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Fires a projectile that chains between nearby enemies, dealing damage based on potion effects.");
        tooltip.add(TextFmt.Light_Purple + "Per Potion Effect on Target:");
        tooltip.add(TextFmt.Gold + "Deals 10% Max Health Damage");
    }
}
