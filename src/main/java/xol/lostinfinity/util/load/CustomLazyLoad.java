package xol.lostinfinity.util.load;

import java.util.Objects;
import java.util.function.Supplier;
import net.minecraft.util.LazyLoadBase;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/load/CustomLazyLoad.class */
public class CustomLazyLoad<T> extends LazyLoadBase<T> {
    private Supplier<T> func;

    public CustomLazyLoad(Supplier<T> func) {
        this.func = func;
        Objects.requireNonNull(func);
    }

    protected T func_179280_b() {
        return this.func.get();
    }
}
