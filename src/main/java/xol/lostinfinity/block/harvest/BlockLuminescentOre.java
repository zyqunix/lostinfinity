package xol.lostinfinity.block.harvest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicLight;
import xol.lostinfinity.block.basic.ISpecialHarvest;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.starforge.EntityEssenceDweller;
import xol.lostinfinity.mob.entity.starforge.EntityGlochipper;
import xol.lostinfinity.util.data.IMaxAttack;
public class BlockLuminescentOre extends BlockBasicLight implements ISpecialHarvest, IMaxAttack {
    public BlockLuminescentOre(String name) {
        super(name);
        func_149711_c(2.0f);
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void failedHarvest(World world, BlockPos pos, EntityPlayer harvester) {
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getHarvestResult(World world, BlockPos pos) {
        return ItemInit.luminessence;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getToolNeeded() {
        return ItemInit.crystalPickaxe;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void worldHarvestEffect(World world, BlockPos pos, EntityPlayer harvester) {
        if (!world.field_72995_K) {
            world.func_175656_a(pos, BlockInit.lumioOreEmpty.func_176223_P());
            int rand_result = world.field_73012_v.nextInt(8);
            if (rand_result == 0) {
                world.func_72876_a((Entity) null, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 3.0f, false);
                for (EntityPlayer target : world.func_72872_a(EntityPlayer.class, new AxisAlignedBB(pos).func_186662_g(5.0d))) {
                    harvester.func_70606_j(harvester.func_110143_aJ() - (harvester.func_110138_aP() * 0.5f));
                    target.func_145747_a(new TextComponentString(TextFmt.Red + "A gas pocket in the ore explodes!"));
                }
                return;
            }
            if (rand_result == 2) {
                EntityEssenceDweller dwel = new EntityEssenceDweller(world);
                dwel.func_70107_b(harvester.field_70165_t, harvester.field_70163_u + 1.0d, harvester.field_70161_v);
                dwel.func_70624_b(harvester);
                world.func_72838_d(dwel);
                harvester.func_145747_a(new TextComponentString(TextFmt.Dark_Aqua + "An Essence Dweller emerged when you mined the Luminessence!"));
                return;
            }
            if (rand_result == 4) {
                for (int i = 0; i < 4; i++) {
                    EntityGlochipper glo = new EntityGlochipper(world);
                    glo.func_70107_b(harvester.field_70165_t, harvester.field_70163_u + 2.0d, harvester.field_70161_v);
                    glo.func_70624_b(harvester);
                    world.func_72838_d(glo);
                }
                harvester.func_145747_a(new TextComponentString(TextFmt.Yellow + "A swarm of glochippers notice the light disappear."));
            }
        }
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public boolean isHarvestable(World world, BlockPos pos, EntityPlayer harvester) {
        return true;
    }
}
