package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityRainDrop.class */
public class EntityRainDrop extends EntityBaseThrowable {
    public EntityRainDrop(World par1World) {
        super(par1World);
        func_70105_a(0.95f, 0.95f);
    }

    public EntityRainDrop(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        func_70105_a(0.95f, 0.95f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && (result.field_72308_g instanceof EntityPlayer)) {
                ItemStack stack = result.field_72308_g.func_184614_ca();
                Item item = stack.func_77973_b();
                this.field_70170_p.func_184133_a((EntityPlayer) null, result.field_72308_g.func_180425_c(), SoundInit.WATER_DROP, SoundCategory.PLAYERS, 1.0f, 0.8f + (this.field_70146_Z.nextFloat() * 0.4f));
                if (item.equals(ItemInit.rainfallCollector)) {
                    if (!stack.func_77942_o()) {
                        stack.func_77982_d(new NBTTagCompound());
                    }
                    int progress = stack.func_77978_p().func_74762_e("Progress");
                    stack.func_77978_p().func_74768_a("Progress", progress + 1);
                    result.field_72308_g.func_145747_a(new TextComponentString(TextFmt.Dark_Aqua + "You catch a raindrop."));
                    return;
                }
            }
            func_70106_y();
        }
    }

    protected float func_70185_h() {
        return 0.02f;
    }
}
