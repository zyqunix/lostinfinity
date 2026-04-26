package xol.lostinfinity.common.events;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.DimensionType;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xol.lostinfinity.block.basic.BlockStarforgeOre;
import xol.lostinfinity.block.basic.ISpecialHarvest;
import xol.lostinfinity.block.misc.BlockPowerCrystal;
import xol.lostinfinity.dimension.util.DimensionActivator;
import xol.lostinfinity.dimension.util.DimensionNoBuild;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
public class EventsDimensional {
    @SubscribeEvent
    public void celestialArenaNoBuild(BlockEvent.EntityPlaceEvent event) {
        if ((event.getWorld().func_180494_b(event.getPos()) instanceof DimensionNoBuild) && (event.getEntity() instanceof EntityPlayer)) {
            EntityPlayer pl = event.getEntity();
            if (!pl.func_184812_l_() && !(event.getPlacedBlock().func_177230_c() instanceof BlockPowerCrystal) && !event.getPlacedBlock().func_177230_c().equals(Blocks.field_150350_a)) {
                event.setCanceled(true);
            }
        }
    }
    @SubscribeEvent
    public void celestialArenaProt(BlockEvent.BreakEvent event) {
        if (event.getWorld().func_180494_b(event.getPos()).equals(DimensionInit.biomeNonexistence) && event.getState().func_177230_c().equals(Blocks.field_189877_df) && event.getPlayer() != null) {
            ItemStack held = event.getPlayer().func_184586_b(event.getPlayer().func_184600_cs());
            if (!held.func_190926_b() && held.func_77973_b().equals(ItemInit.forgeFirePickaxe)) {
                DimensionActivator.transferEntity(event.getPlayer(), DimensionType.OVERWORLD);
                event.setCanceled(true);
            }
        }
        if (event.getWorld().func_180494_b(event.getPos()) instanceof DimensionNoBuild) {
            if (event.getState().func_177230_c() instanceof BlockStarforgeOre) {
                BlockStarforgeOre block = (BlockStarforgeOre) event.getState().func_177230_c();
                boolean schedule = false;
                if (!block.isDepletedType) {
                    if (event.getPlayer() != null) {
                        ItemStack held2 = event.getPlayer().func_184586_b(event.getPlayer().func_184600_cs());
                        if (!held2.func_190926_b() && held2.func_77973_b().equals(ItemInit.forgeFirePickaxe)) {
                            int fortune = EnchantmentHelper.func_77506_a(Enchantments.field_185308_t, event.getPlayer().func_184614_ca());
                            Item drop = event.getState().func_177230_c().func_180660_a(event.getState(), event.getWorld().field_73012_v, fortune);
                            if (!held2.func_77942_o()) {
                                held2.func_77982_d(new NBTTagCompound());
                            } else {
                                String[] split = drop.getRegistryName().func_110623_a().split("_");
                                int chargesLeft = held2.func_77978_p().func_74762_e(split[split.length - 2]);
                                if (chargesLeft > 0) {
                                    event.getState().func_177230_c().func_176226_b(event.getWorld(), event.getPlayer().func_180425_c().func_177982_a(0, 2, 0), event.getState(), fortune);
                                    event.getWorld().func_175656_a(event.getPos(), BlockInit.oreDepleted.func_176223_P());
                                    schedule = true;
                                    held2.func_77978_p().func_74768_a(split[split.length - 2], chargesLeft - 1);
                                } else {
                                    event.setCanceled(true);
                                }
                            }
                        }
                    }
                } else {
                    schedule = !event.getWorld().func_184145_b(event.getPos(), block);
                }
                if (schedule) {
                    int delay = event.getWorld().field_73012_v.nextInt(31 - 10) + 10;
                    event.getWorld().func_175684_a(event.getPos(), block, delay * 20);
                }
            } else if (event.getState().func_177230_c() instanceof ISpecialHarvest) {
                ISpecialHarvest harvest_block = event.getState().func_177230_c();
                if (harvest_block.isHarvestable(event.getWorld(), event.getPos(), event.getPlayer())) {
                    if (event.getPlayer().func_184614_ca().func_77973_b() == harvest_block.getToolNeeded()) {
                        if (!event.getWorld().field_72995_K) {
                            EntityItem crystal = new EntityItem(event.getWorld(), event.getPos().func_177958_n(), event.getPos().func_177956_o(), event.getPos().func_177952_p(), new ItemStack(harvest_block.getHarvestResult(event.getWorld(), event.getPos())));
                            crystal.field_70159_w = 0.0d;
                            crystal.field_70181_x = 0.0d;
                            crystal.field_70179_y = 0.0d;
                            event.getWorld().func_72838_d(crystal);
                        }
                        harvest_block.worldHarvestEffect(event.getWorld(), event.getPos(), event.getPlayer());
                    }
                } else {
                    harvest_block.failedHarvest(event.getWorld(), event.getPos(), event.getPlayer());
                }
            }
            event.setCanceled(!event.getPlayer().func_184812_l_());
        }
    }
}
