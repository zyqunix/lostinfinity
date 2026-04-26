package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
public class EntityExothermite extends EntityBaseThrowable {
    private ItemStack stack;
    public EntityExothermite(World par1World) {
        super(par1World);
        this.stack = null;
    }
    public EntityExothermite(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.stack = null;
    }
    public EntityExothermite(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.stack = null;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K && this.stack != null) {
            boolean flag = false;
            if (result.field_72308_g != null && (result.field_72308_g instanceof EntityPlayer)) {
                EntityPlayer target = result.field_72308_g;
                if (this.stack != null) {
                    target.func_191521_c(this.stack.func_77946_l());
                    flag = true;
                }
            }
            if (result.field_72313_a == RayTraceResult.Type.BLOCK && BiomeDictionary.getBiomes(BiomeDictionary.Type.SNOWY).contains(this.field_70170_p.func_180494_b(func_180425_c())) && this.field_70170_p.func_180495_p(result.func_178782_a()).func_177230_c().equals(Blocks.field_150432_aD)) {
                if (this.stack.func_77942_o() && this.stack.func_77978_p().func_74764_b("progress")) {
                    long progress = this.stack.func_77978_p().func_74763_f("progress");
                    this.stack.func_77978_p().func_74772_a("progress", progress + 100);
                }
                this.field_70170_p.func_175656_a(result.func_178782_a(), Blocks.field_150355_j.func_176223_P());
            }
            if (!flag) {
                EntityItem exo = new EntityItem(this.field_70170_p, result.func_178782_a().func_177958_n(), result.func_178782_a().func_177956_o(), result.func_178782_a().func_177952_p(), this.stack);
                this.field_70170_p.func_72838_d(exo);
            }
            func_70106_y();
        }
    }
    public void setStack(ItemStack stack) {
        this.stack = stack.func_77946_l();
    }
    protected float func_70185_h() {
        return 0.05f;
    }
}
