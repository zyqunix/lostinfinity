package xol.lostinfinity.dimension.data;
import java.util.function.Function;
import net.minecraft.util.math.BlockPos;
public class FlightCurve {
    public static double a = 1.0d;
    public static double b = 1.0d;
    public static double c = 1.0d;
    public static double p = 2.0d;
    public static double q = 3.0d;
    private double moveSpeed;
    private Function<Double, Double> xFunction;
    private Function<Double, Double> yFunction;
    private Function<Double, Double> zFunction;
    private Function<Double[], Double> slopeFunction;
    private Function<Double, Double> xslopeFunction;
    private Function<Double, Double> yslopeFunction;
    private Function<Double, Double> zslopeFunction;
    public static void main(String[] args) {
        FlightCurve f = new FlightCurve(new BlockPos(3, 4, 10), new BlockPos(0, 0, 4), 5.0d);
        double d = 0.0d;
        while (true) {
            double t = d;
            if (t < 1.0d) {
                System.out.println(String.format("x:%f y:%f z%f", Double.valueOf(f.getXVelocity(t)), Double.valueOf(f.getYVelocity(t)), Double.valueOf(f.getZVelocity(t))));
                d = t + 0.1d;
            } else {
                return;
            }
        }
    }
    public FlightCurve(BlockPos start, BlockPos finish, double moveSpeed) {
        this.moveSpeed = moveSpeed;
        final double x0 = start.func_177958_n();
        final double x1 = finish.func_177958_n();
        final double y0 = start.func_177956_o();
        final double y1 = finish.func_177956_o();
        final double z0 = start.func_177952_p();
        final double z1 = finish.func_177952_p();
        this.slopeFunction = new Function<Double[], Double>() { // from class: xol.lostinfinity.dimension.data.FlightCurve.1
            @Override // java.util.function.Function
            public Double apply(Double[] values) {
                double f0 = values[0].doubleValue();
                double f1 = values[1].doubleValue();
                double t0 = values[2].doubleValue();
                double t1 = values[3].doubleValue();
                double t = values[4].doubleValue();
                double a2 = (f1 - f0) / (t1 - t0);
                double b2 = f0 - (a2 * t0);
                return Double.valueOf((a2 * t) + b2);
            }
        };
        this.xslopeFunction = new Function<Double, Double>() { // from class: xol.lostinfinity.dimension.data.FlightCurve.2
            @Override // java.util.function.Function
            public Double apply(Double t) {
                Double[] Values = {Double.valueOf(x0), Double.valueOf(x1), Double.valueOf(0.0d), Double.valueOf(1.0d), t};
                return (Double) FlightCurve.this.slopeFunction.apply(Values);
            }
        };
        this.yslopeFunction = new Function<Double, Double>() { // from class: xol.lostinfinity.dimension.data.FlightCurve.3
            @Override // java.util.function.Function
            public Double apply(Double t) {
                Double[] Values = {Double.valueOf(y0), Double.valueOf(y1), Double.valueOf(0.0d), Double.valueOf(1.0d), t};
                return (Double) FlightCurve.this.slopeFunction.apply(Values);
            }
        };
        this.zslopeFunction = new Function<Double, Double>() { // from class: xol.lostinfinity.dimension.data.FlightCurve.4
            @Override // java.util.function.Function
            public Double apply(Double t) {
                Double[] Values = {Double.valueOf(z0), Double.valueOf(z1), Double.valueOf(0.0d), Double.valueOf(1.0d), t};
                return (Double) FlightCurve.this.slopeFunction.apply(Values);
            }
        };
        this.xFunction = new Function<Double, Double>() { // from class: xol.lostinfinity.dimension.data.FlightCurve.5
            @Override // java.util.function.Function
            public Double apply(Double t) {
                return (Double) FlightCurve.this.xslopeFunction.apply(t);
            }
        };
        this.yFunction = new Function<Double, Double>() { // from class: xol.lostinfinity.dimension.data.FlightCurve.6
            @Override // java.util.function.Function
            public Double apply(Double t) {
                return Double.valueOf(((3.6105d * Math.pow(t.doubleValue(), 3.0d)) - (5.9498d * Math.pow(t.doubleValue(), 2.0d))) + (2.3644d * t.doubleValue()) + 0.0187d);
            }
        };
        this.zFunction = new Function<Double, Double>() { // from class: xol.lostinfinity.dimension.data.FlightCurve.7
            @Override // java.util.function.Function
            public Double apply(Double t) {
                return (Double) FlightCurve.this.zslopeFunction.apply(t);
            }
        };
    }
    public double getMoveSpeed() {
        return this.moveSpeed;
    }
    public void setMoveSpeed(double speed) {
        this.moveSpeed = speed;
    }
    public double getXVelocity(double t) {
        return (-(this.xFunction.apply(Double.valueOf(t)).doubleValue() - this.xFunction.apply(Double.valueOf(t + 0.01d)).doubleValue())) / 0.01d;
    }
    public double getYVelocity(double t) {
        return (-(this.yFunction.apply(Double.valueOf(t)).doubleValue() - this.yFunction.apply(Double.valueOf(t + 0.01d)).doubleValue())) / 0.01d;
    }
    public double getZVelocity(double t) {
        return (-(this.zFunction.apply(Double.valueOf(t)).doubleValue() - this.zFunction.apply(Double.valueOf(t + 0.01d)).doubleValue())) / 0.01d;
    }
}
