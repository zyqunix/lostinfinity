package xol.lostinfinity.item.weapon;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.mob.entity.misc.EntityNitroExplosion;
import xol.lostinfinity.projectile.entity.EntityDryadsGripAttack;
import xol.lostinfinity.util.data.CustomRayTraceResult;
public class ItemDryadsGrip extends ItemCooldown implements IModeSelect, ICustomRaytrace, ISwitchModels {
    public ItemDryadsGrip(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
        setModelSwitch("usetype", this, 2);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                int attack_style = stack.func_77978_p().func_74762_e("usetype_data");
                if (attack_style == 0) {
                    EntityDryadsGripAttack shot = new EntityDryadsGripAttack(worldIn, playerIn);
                    shot.setThrower(playerIn);
                    shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 2.0f, 0.0f);
                    worldIn.func_72838_d(shot);
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.LASER_WEAPON_5, SoundCategory.PLAYERS, 1.0f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
                } else {
                    CustomRayTraceResult trace_result = simpleBlockTrace(worldIn, playerIn, 60);
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.LASER_WEAPON_4, SoundCategory.PLAYERS, 1.0f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
                    if (trace_result != null) {
                        BlockPos pos = trace_result.getResultPos();
                        for (int i = -2; i <= 2; i++) {
                            for (int j = -2; j <= 2; j++) {
                                for (int k = -2; k <= 2; k++) {
                                    if (i != 0 || j != 0 || k != 0) {
                                        Block result_block = worldIn.func_180495_p(pos.func_177982_a(i, j, k)).func_177230_c();
                                        if (result_block.equals(BlockInit.leavesNitro) || result_block.equals(BlockInit.logsNitro)) {
                                            int numBlocks = 0;
                                            ArrayList<BlockPos> toExplode = propogateExplosions(worldIn, pos.func_177982_a(i, j, k), null);
                                            for (BlockPos explodePos : toExplode) {
                                                Block block = worldIn.func_180495_p(explodePos).func_177230_c();
                                                if (block.equals(BlockInit.leavesNitro)) {
                                                    numBlocks++;
                                                }
                                                if (block.equals(BlockInit.logsNitro)) {
                                                    numBlocks++;
                                                }
                                                worldIn.func_175698_g(explodePos);
                                            }
                                            createExplosion(worldIn, pos.func_177982_a(i, j, k), numBlocks, playerIn);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    private ArrayList<BlockPos> propogateExplosions(World worldIn, BlockPos pos, ArrayList<BlockPos> visited) {
        if (visited == null) {
            visited = new ArrayList<>();
        }
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    BlockPos check = pos.func_177982_a(i, j, k);
                    Block result_block = worldIn.func_180495_p(check).func_177230_c();
                    if (!visited.contains(check) && (result_block.equals(BlockInit.leavesNitro) || result_block.equals(BlockInit.logsNitro))) {
                        visited.add(check);
                        ArrayList<BlockPos> neighbours = propogateExplosions(worldIn, check, visited);
                        for (BlockPos n : neighbours) {
                            if (!visited.contains(n)) {
                                visited.add(n);
                            }
                        }
                    }
                }
            }
        }
        return visited;
    }
    public void createExplosion(World worldIn, BlockPos pos, int numBlocks, EntityPlayer player) {
        EntityNitroExplosion bomb = new EntityNitroExplosion(worldIn);
        bomb.setNumBlocks(numBlocks);
        bomb.setThrower(player);
        bomb.func_70107_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
        worldIn.func_72838_d(bomb);
        worldIn.func_175698_g(pos);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 100;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Green + "Summons a tree and tethers nearby entities to them.");
        tooltip.add(TextFmt.Gold + "Alt Fire: Detonates trees, dealing 5% Health True Damage per Block");
    }
    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        int attack_style = stack.func_77978_p().func_74762_e("usetype_data");
        if (attack_style == 0) {
            stack.func_77978_p().func_74768_a("usetype_data", 1);
        } else {
            stack.func_77978_p().func_74768_a("usetype_data", 0);
        }
    }
}
