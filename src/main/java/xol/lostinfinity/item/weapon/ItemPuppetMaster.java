package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.packets.clientbound.PacketSetControlled;
import xol.lostinfinity.common.special.CommonMindControlHandler;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.item.classify.IHeldTick;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemPuppetMaster extends ItemCooldown implements IHeldTick, ICustomRaytrace {
    public ItemPuppetMaster(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult result;
        EntityPlayerMP target;
        EntityPlayerMP oldTarget;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K && CommonMindControlHandler.getControllerOfPlayer(playerIn) == null && (result = entityTrace(worldIn, playerIn, 60, EntityPlayer.class)) != null && (target = result.getResultEntity()) != (oldTarget = (EntityPlayerMP) CommonMindControlHandler.getTargetOfPlayer(playerIn))) {
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MIND_CONTROL, SoundCategory.PLAYERS, 0.7f, 1.0f);
                worldIn.func_184133_a((EntityPlayer) null, target.func_180425_c(), SoundInit.ENTITY_POSSESS, SoundCategory.PLAYERS, 1.5f, 1.0f);
                if (oldTarget != null) {
                    CommonMindControlHandler.unregisterPair(playerIn, oldTarget);
                    lostinfinity.instance.packetHandler.sendToPlayer(oldTarget, new PacketSetControlled());
                }
                CommonMindControlHandler.registerPair(playerIn, target);
                lostinfinity.instance.packetHandler.sendToPlayer(target, new PacketSetControlled(CommonMindControlHandler.State.CONTROLLED));
                lostinfinity.instance.packetHandler.sendToPlayer((EntityPlayerMP) playerIn, new PacketSetControlled(CommonMindControlHandler.State.CONTROLLING));
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.WATCHING_EYE).setSpread(0.0d, 0.0d, 0.0d).setCount(1).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(worldIn, config1, target.field_70165_t, target.field_70163_u + ((double) target.field_70131_O) + 0.5d, target.field_70161_v);
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.classify.IHeldTick
    public void stopHolding(EntityPlayer player, EnumHand hand, ItemStack stack) {
        EntityPlayerMP targetOfPlayer = CommonMindControlHandler.getTargetOfPlayer(player);
        if (targetOfPlayer != null) {
            CommonMindControlHandler.unregisterPair(player, targetOfPlayer);
            lostinfinity.instance.packetHandler.sendToPlayer(targetOfPlayer, new PacketSetControlled());
            lostinfinity.instance.packetHandler.sendToPlayer((EntityPlayerMP) player, new PacketSetControlled());
        }
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 50;
    }
    @Override // xol.lostinfinity.item.classify.IHeldTick
    public void heldTick(EntityPlayer player, EnumHand hand, ItemStack stack) {
        if (!player.field_70170_p.field_72995_K) {
            EntityPlayer target = CommonMindControlHandler.getTargetOfPlayer(player);
            float rotation = player.field_70173_aa / 10;
            double velocity_x = ((double) 3.0f) * Math.cos(rotation);
            double velocity_z = ((double) 3.0f) * Math.sin(rotation);
            if (target != null) {
                CustomParticleConfig config = new CustomParticleConfig();
                config.createInstance().setParticle(ParticleInit.BAD_MAGIC).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(player.field_70170_p, config, target.func_174791_d().func_72441_c(velocity_x / 2.0d, 0.0d, velocity_z / 2.0d));
            }
        }
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Takes control of a target player.");
        tooltip.add(TextFmt.Aqua + "While you are holding this item, you remain in control of their body.");
    }
}
