package xol.lostinfinity.item.weapon;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemChanneling;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemSongOfInvigoration extends ItemChanneling {
    public ItemSongOfInvigoration(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    @Override // xol.lostinfinity.item.basics.ItemChanneling
    public void chargeTick(World worldIn, EntityPlayer player, EnumHand hand, ItemStack stack, int chargeTime) {
        if (worldIn.field_72995_K) {
        }
        if (chargeTime % 7 == 0) {
            int chosenSound = worldIn.field_73012_v.nextInt(3) + 1;
            switch (chosenSound) {
                case 1:
                    worldIn.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.NOTE_TYPE_1, SoundCategory.PLAYERS, 1.0f, 0.5f + worldIn.field_73012_v.nextFloat());
                    break;
                case 2:
                    worldIn.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.NOTE_TYPE_3, SoundCategory.PLAYERS, 1.0f, 0.5f + worldIn.field_73012_v.nextFloat());
                    break;
                case 3:
                    worldIn.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.NOTE_TYPE_4, SoundCategory.PLAYERS, 1.0f, 0.5f + worldIn.field_73012_v.nextFloat());
                    break;
            }
        }
        if (chargeTime % 20 == 0) {
            List<EntityTameable> nearEntities = worldIn.func_72872_a(EntityTameable.class, player.func_174813_aQ().func_186662_g(30.0d));
            List<EntityTameable> toRemove = new ArrayList<>();
            for (EntityTameable entity : nearEntities) {
                if (!entity.func_70909_n() || entity.func_70902_q() != player) {
                    toRemove.add(entity);
                }
            }
            nearEntities.removeAll(toRemove);
            if (nearEntities.size() == 0) {
                return;
            }
            int chosenIndex = worldIn.field_73012_v.nextInt(nearEntities.size());
            EntityLiving chosenEntity = (EntityLiving) nearEntities.get(chosenIndex);
            CustomParticleConfig config = new CustomParticleConfig();
            config.createInstance().setParticle(ParticleInit.CRYSTAL_MAGIC).setCount(10).setSpread(0.5d, 0.5d, 0.5d);
            IParticleSpawner.spawnParticle(worldIn, config, chosenEntity.func_180425_c().func_177958_n(), chosenEntity.func_180425_c().func_177956_o(), chosenEntity.func_180425_c().func_177952_p());
            int chosenEffect = worldIn.field_73012_v.nextInt(4);
            switch (chosenEffect) {
                case 0:
                    if (chosenEntity.func_70644_a(PotionInit.ADRENALINE)) {
                        int newDuration = chosenEntity.func_70660_b(PotionInit.ADRENALINE).func_76459_b() + 100;
                        int newAmplifier = Math.min(chosenEntity.func_70660_b(PotionInit.ADRENALINE).func_76458_c() + 1, 9);
                        chosenEntity.func_70690_d(new PotionEffect(PotionInit.ADRENALINE, newDuration, newAmplifier));
                    } else {
                        chosenEntity.func_70690_d(new PotionEffect(PotionInit.ADRENALINE, 100, 0));
                    }
                    break;
                case 1:
                    if (chosenEntity.func_70644_a(PotionInit.PLANESPLIT)) {
                        int newDuration2 = chosenEntity.func_70660_b(PotionInit.PLANESPLIT).func_76459_b() + 100;
                        int newAmplifier2 = Math.min(chosenEntity.func_70660_b(PotionInit.PLANESPLIT).func_76458_c() + 1, 9);
                        chosenEntity.func_70690_d(new PotionEffect(PotionInit.PLANESPLIT, newDuration2, newAmplifier2));
                    } else {
                        chosenEntity.func_70690_d(new PotionEffect(PotionInit.PLANESPLIT, 100, 0));
                    }
                    break;
                case 2:
                    if (chosenEntity.func_70644_a(PotionInit.IRONHEART)) {
                        int newDuration3 = chosenEntity.func_70660_b(PotionInit.IRONHEART).func_76459_b() + 100;
                        chosenEntity.func_70690_d(new PotionEffect(PotionInit.IRONHEART, newDuration3, 0));
                    } else {
                        chosenEntity.func_70690_d(new PotionEffect(PotionInit.IRONHEART, 100, 0));
                    }
                    break;
                case TileEntityFusionTable.BOARD_ROWS :
                    if (chosenEntity.func_70644_a(PotionInit.TRANSFUSION)) {
                        int newDuration4 = chosenEntity.func_70660_b(PotionInit.TRANSFUSION).func_76459_b() + 100;
                        int newAmplifier3 = Math.min(chosenEntity.func_70660_b(PotionInit.TRANSFUSION).func_76458_c() + 1, 9);
                        chosenEntity.func_70690_d(new PotionEffect(PotionInit.TRANSFUSION, newDuration4, newAmplifier3));
                    } else {
                        chosenEntity.func_70690_d(new PotionEffect(PotionInit.TRANSFUSION, 100, 0));
                    }
                    break;
            }
        }
    }
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Empower your nearby tames through with a battle song.");
        tooltip.add(TextFmt.Green + "Your tames will periodically gain buffs as you play.");
        tooltip.add(TextFmt.Red + "Buffs they already have, are extended and made stronger.");
        tooltip.add(TextFmt.Italic + "Buffs are Adrenaline, Planesplit, Ironheart & Transfusion");
    }
}
