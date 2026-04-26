package xol.lostinfinity.mob.model.cthulhu;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.model.ModelBase;
import xol.lostinfinity.util.animation.client.blueprint.AnimationBlueprint;
import xol.lostinfinity.util.animation.model.IXolModel;
import xol.lostinfinity.util.animation.model.ModelRenderer;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/model/cthulhu/AnimatedModelBase.class */
public abstract class AnimatedModelBase extends ModelBase implements IXolModel {
    protected AnimationBlueprint blueprint;
    protected final Map<String, ModelRenderer> index = new HashMap();

    @Override // xol.lostinfinity.util.animation.model.IXolModel
    public void setBlueprint(AnimationBlueprint blueprint) {
        this.blueprint = blueprint;
    }

    @Override // xol.lostinfinity.util.animation.model.IXolModel
    public AnimationBlueprint getBlueprint() {
        return this.blueprint;
    }

    @Override // xol.lostinfinity.util.animation.model.IXolModel
    public Map<String, ModelRenderer> getIndex() {
        return this.index;
    }
}
