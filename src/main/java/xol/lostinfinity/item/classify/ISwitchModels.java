package xol.lostinfinity.item.classify;

import javax.annotation.Nullable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import xol.lostinfinity.util.Reference;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/classify/ISwitchModels.class */
public interface ISwitchModels {
    default void setModelSwitch(final String overrideName, Item item, final int totalValues) {
        item.func_185043_a(new ResourceLocation(Reference.MODID, overrideName), new IItemPropertyGetter() { // from class: xol.lostinfinity.item.classify.ISwitchModels.1
            public float func_185085_a(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return ISwitchModels.this.getProperty(stack, entityIn, overrideName + "_data", totalValues - 1);
            }
        });
    }

    default float getProperty(ItemStack stack, @Nullable EntityLivingBase entityIn, String name, int divisor) {
        if (entityIn != null && !stack.func_190926_b() && (stack.func_77973_b() instanceof ISwitchModels) && stack.func_77942_o()) {
            float initial_data = stack.func_77978_p().func_74762_e(name) / divisor;
            int temp = (int) (((double) initial_data) * 100.0d);
            float char_data = (float) (((double) temp) / 100.0d);
            return char_data;
        }
        return 0.0f;
    }
}
