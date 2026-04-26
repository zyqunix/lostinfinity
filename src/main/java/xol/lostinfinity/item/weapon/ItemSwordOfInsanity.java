package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.mob.entity.misc.EntityPlayerLimb;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemSwordOfInsanity extends ItemSword implements IModeSelect, IMaxAttack {
    public ItemSwordOfInsanity(String regName) {
        super(Item.ToolMaterial.DIAMOND);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        func_77625_d(1);
        ItemInit.ITEMS.add(this);
    }
    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        boolean mode = getMode(stack);
        if (mode) {
            amputationAttack(stack, target, attacker);
            target.field_70170_p.func_184133_a((EntityPlayer) null, target.func_180425_c(), SoundInit.SWING_HIT, SoundCategory.PLAYERS, 1.0f, 0.6f + (target.field_70170_p.field_73012_v.nextFloat() * 0.4f));
            return true;
        }
        destructionAttack(stack, target, attacker);
        target.field_70170_p.func_184133_a((EntityPlayer) null, target.func_180425_c(), SoundInit.MISSILE_EXPLOSION, SoundCategory.PLAYERS, 1.0f, 0.6f + (target.field_70170_p.field_73012_v.nextFloat() * 0.4f));
        return true;
    }
    private void amputationAttack(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (!IMaxAttack.dealTrueDamage(attacker, target, target.func_110138_aP(), null).wasTargetKilled() && !IMaxAttack.dealTrueDamage(attacker, target, target.func_110138_aP(), null).wasTargetKilled()) {
            IMaxAttack.dealTrueDamage(attacker, target, target.func_110138_aP(), null);
        }
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        EntityPlayerLimb entityLimb = new EntityPlayerLimb(target.field_70170_p, target.func_110124_au());
        Vec3d targetPos = target.func_174791_d();
        Vec3d targetLook = target.func_70040_Z();
        Vec3d targetLookScaled = targetLook.func_186678_a(0.5d);
        Vec3d targetPosBehind = targetPos.func_178788_d(targetLookScaled);
        entityLimb.func_70107_b(targetPosBehind.field_72450_a, targetPosBehind.field_72448_b, targetPosBehind.field_72449_c);
        Vec3d lookVec = attacker.func_70040_Z();
        entityLimb.field_70159_w = lookVec.field_72450_a * 0.7d;
        entityLimb.field_70181_x = (lookVec.field_72448_b * 0.7d) + 0.6d;
        entityLimb.field_70179_y = lookVec.field_72449_c * 0.7d;
        if (!target.field_70170_p.field_72995_K) {
            attacker.field_70170_p.func_72838_d(entityLimb);
        }
    }
    private void destructionAttack(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        IMaxAttack.dealTrueDamage(attacker, target, target.func_110138_aP(), null);
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        Iterable<BlockPos> blocks = BlockPos.func_177980_a(target.func_180425_c().func_177982_a(-3, -3, -3), target.func_180425_c().func_177982_a(3, 3, 3));
        for (BlockPos pos : blocks) {
            if (pos.func_177951_i(target.func_180425_c()) < 9.0d && target.field_70170_p.func_180495_p(pos).func_185887_b(target.field_70170_p, pos) != -1.0f) {
                target.field_70170_p.func_175655_b(pos, true);
            }
        }
        CustomParticleConfig config = new CustomParticleConfig();
        config.createInstance().setCount(50).setParticle(EnumParticleTypes.DRAGON_BREATH).setSpread(3.0d, 2.0d, 3.0d).setSpeed(new Vec3d(0.0d, 0.10000000149011612d, 0.0d));
        IParticleSpawner.spawnParticle(target.field_70170_p, config, target.field_70165_t, target.field_70163_u, target.field_70161_v);
    }
    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (stack.func_77978_p() == null) {
            stack.func_77982_d(new NBTTagCompound());
        }
        boolean mode = !getMode(stack);
        setMode(stack, mode);
        announceUpdate(player, mode);
    }
    private void announceUpdate(EntityPlayer player, boolean mode) {
        if (mode) {
            player.func_146105_b(new TextComponentString("Amputation Mode"), true);
        } else {
            player.func_146105_b(new TextComponentString("Destruction Mode"), true);
        }
    }
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Amputation Mode: Slices the limbs off of enemies (100% Health True Damage 3 Times)");
        tooltip.add(TextFmt.Light_Purple + "Destruction Mode: Destroys blocks around enemy players (100% Health True Damage)");
    }
    private void setMode(ItemStack stack, boolean mode) {
        if (stack.func_77978_p() == null) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.func_77978_p().func_74757_a("mode", mode);
    }
    private boolean getMode(ItemStack stack) {
        if (stack.func_77978_p() == null) {
            stack.func_77982_d(new NBTTagCompound());
        }
        return stack.func_77978_p().func_74767_n("mode");
    }
}
