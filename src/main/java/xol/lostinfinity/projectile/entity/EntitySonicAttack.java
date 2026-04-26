package xol.lostinfinity.projectile.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntitySonicAttack.class */
public class EntitySonicAttack extends EntityBaseThrowable {
    public EntitySonicAttack(World worldIn) {
        super(worldIn);
        func_70105_a(1.0f, 1.0f);
    }

    public EntitySonicAttack(World worldIn, EntityLivingBase entityIn) {
        super(worldIn, entityIn);
        func_70105_a(1.0f, 1.0f);
    }

    public EntitySonicAttack(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        func_70105_a(1.0f, 1.0f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72313_a == RayTraceResult.Type.BLOCK) {
                Block hitBlock = this.field_70170_p.func_180495_p(result.func_178782_a()).func_177230_c();
                if (hitBlock.equals(BlockInit.mutantBloom)) {
                    harvestFungus(result.func_178782_a());
                }
            }
            if (result.field_72308_g != null && func_85052_h() != null && result.field_72308_g != func_85052_h() && (result.field_72308_g instanceof EntityLivingBase) && (result.field_72308_g instanceof EntityPlayer)) {
                EntityPlayer play = result.field_72308_g;
                if (play.func_184614_ca().func_77973_b().equals(ItemInit.sonicShield) || play.func_184614_ca().func_77973_b().equals(ItemInit.gigacron) || play.func_184614_ca().func_77973_b().equals(ItemInit.hypercron)) {
                    IMaxAttack.dealMaxHealth(this, result.field_72308_g, 4);
                } else if (play.func_184614_ca().func_77973_b().equals(ItemInit.swordOfReverberance)) {
                    IMaxAttack.dealMaxHealth(this, result.field_72308_g, 4);
                    ItemStack stack = play.func_184614_ca();
                    if (stack.func_77942_o()) {
                        int newcharge = stack.func_77978_p().func_74762_e("sword_charge") * 2;
                        if (newcharge > 250) {
                            newcharge = 250;
                        }
                        stack.func_77978_p().func_74768_a("sword_charge", newcharge);
                    }
                } else {
                    IMaxAttack.dealMaxHealth((Entity) this, result.field_72308_g, 1, 5.0f);
                }
            }
            func_70106_y();
        }
    }

    private void harvestFungus(BlockPos pos) {
        this.field_70170_p.func_175656_a(pos, BlockInit.forgebloom.func_176223_P());
        AxisAlignedBB checkBox = new AxisAlignedBB(pos.func_177982_a(-3, -3, -3), pos.func_177982_a(3, 3, 3));
        for (EntityPlayer player : this.field_70170_p.func_72872_a(EntityPlayer.class, checkBox)) {
            player.func_70690_d(new PotionEffect(PotionInit.PLAGUE, 200));
            player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "You have been afflicted by mutant spores!"));
        }
        func_145779_a(ItemInit.mutantFungus, 1);
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
