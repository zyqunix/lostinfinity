package xol.lostinfinity.item.activate;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemBioEnergizer extends Item {
    public ItemBioEnergizer(String regName) {
        setRegistryName(regName);
        func_77655_b(regName);
        func_77637_a(TabsInit.TAB_AUXMATS);
        ItemInit.ITEMS.add(this);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.field_72995_K) {
            Iterable<BlockPos> nearblocks = BlockPos.func_177980_a(playerIn.func_180425_c().func_177982_a(-2, -2, -2), playerIn.func_180425_c().func_177982_a(2, 2, 2));
            for (BlockPos pos : nearblocks) {
                if (worldIn.func_180495_p(pos).func_177230_c() == BlockInit.lumcubes) {
                    EntityItem crystal = new EntityItem(worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), new ItemStack(ItemInit.florocite));
                    crystal.field_70159_w = 0.0d;
                    crystal.field_70181_x = 0.5d;
                    crystal.field_70179_y = 0.0d;
                    crystal.field_70133_I = true;
                    worldIn.func_72838_d(crystal);
                } else if (worldIn.func_180495_p(pos).func_177230_c() == BlockInit.blightcubes) {
                    EntityItem crystal2 = new EntityItem(worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), new ItemStack(ItemInit.anthocite));
                    crystal2.field_70159_w = 0.0d;
                    crystal2.field_70181_x = 0.5d;
                    crystal2.field_70179_y = 0.0d;
                    crystal2.field_70133_I = true;
                    worldIn.func_72838_d(crystal2);
                }
            }
            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.BIOENERGIZE, SoundCategory.PLAYERS, 1.0f, 1.0f);
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.NATURE_MAGIC).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(worldIn, config1, playerIn.field_70165_t, playerIn.field_70163_u + ((double) (playerIn.field_70131_O / 2.0f)), playerIn.field_70161_v);
            CustomParticleConfig config2 = new CustomParticleConfig();
            config2.createInstance().setParticle(ParticleInit.ZAP).setSpread(3.0d, 1.0d, 3.0d).setCount(5).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(worldIn, config2, playerIn.field_70165_t, playerIn.field_70163_u + ((double) (playerIn.field_70131_O / 2.0f)), playerIn.field_70161_v);
        }
        playerIn.func_184586_b(handIn).func_190918_g(1);
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Energizes plants that can react to electrical charge.");
    }
}
