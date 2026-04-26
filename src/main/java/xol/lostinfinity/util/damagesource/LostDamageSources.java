package xol.lostinfinity.util.damagesource;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'ACIDIC_GEL' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/damagesource/LostDamageSources.class */
public final class LostDamageSources {
    public static final LostDamageSources ACIDIC_GEL;
    public static final LostDamageSources CONCENTRATED_ACID;
    public DamageSource source;
    private static final /* synthetic */ LostDamageSources[] $VALUES;

    public static LostDamageSources[] values() {
        return (LostDamageSources[]) $VALUES.clone();
    }

    public static LostDamageSources valueOf(String name) {
        return (LostDamageSources) Enum.valueOf(LostDamageSources.class, name);
    }

    static {
        final String str = "acidic_gel";
        ACIDIC_GEL = new LostDamageSources("ACIDIC_GEL", 0, new DamageSource(str) { // from class: xol.lostinfinity.util.damagesource.AcidicGelSource
            {
                super("lostinfinity." + str);
            }

            public ITextComponent func_151519_b(EntityLivingBase entityLivingBaseIn) {
                EntityLivingBase entitylivingbase = entityLivingBaseIn.func_94060_bK();
                String baseMsg = "%l$s was melted by acid.".replace("%l$s", entityLivingBaseIn.func_145748_c_().func_150254_d());
                return entitylivingbase == null ? new TextComponentString(baseMsg) : new TextComponentString((baseMsg + ",  while running from %2$s").replace("%2$s", entitylivingbase.func_145748_c_().func_150254_d()));
            }
        });
        final String str2 = "concentrated_acid";
        CONCENTRATED_ACID = new LostDamageSources("CONCENTRATED_ACID", 1, new DamageSource(str2) { // from class: xol.lostinfinity.util.damagesource.ConcentratedAcidSource
            {
                super("lostinfinity." + str2);
            }

            public ITextComponent func_151519_b(EntityLivingBase entityLivingBaseIn) {
                EntityLivingBase entitylivingbase = entityLivingBaseIn.func_94060_bK();
                String baseMsg = "%l$s decided to take a bath, in acid".replace("%l$s", entityLivingBaseIn.func_145748_c_().func_150254_d());
                return entitylivingbase == null ? new TextComponentString(baseMsg) : new TextComponentString((baseMsg + ",  while running from %2$s").replace("%2$s", entitylivingbase.func_145748_c_().func_150254_d()));
            }
        });
        $VALUES = new LostDamageSources[]{ACIDIC_GEL, CONCENTRATED_ACID};
    }

    private LostDamageSources(String str, int i, Object source) {
        this.source = source instanceof DamageSource ? (DamageSource) source : DamageSource.field_76380_i;
    }
}
