package xol.lostinfinity.dimension.data;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class LightSwitchGameGenerator {
    private List<LightSwitchNode> switches = new ArrayList();
    private List<SwitchableLightNode> lights = new ArrayList();
    public LightSwitchGameGenerator(int numSwitches, int numLights) {
        Random rand = new Random();
        for (int i = 0; i < numLights; i++) {
            SwitchableLightNode light = new SwitchableLightNode(2, 0, i, true);
            addLight(light);
        }
        for (int j = 0; j < numSwitches; j++) {
            boolean[] toToggle = new boolean[numLights];
            for (int f = 0; f < toToggle.length; f++) {
                toToggle[f] = false;
            }
            for (int k = 0; k < numLights - 1; k++) {
                int randLight = rand.nextInt(numLights);
                toToggle[randLight] = true;
                LightSwitchNode node = new LightSwitchNode(0, 0, j);
                node.setLights(toToggle);
                addSwitch(node);
            }
        }
        for (int p = 0; p < numSwitches; p++) {
            LightSwitchNode switchNode = getSwitch(p);
            boolean pressSwitch = rand.nextBoolean();
            if (pressSwitch) {
                boolean[] lightsToSwitch = switchNode.getLights();
                for (int c = 0; c < lightsToSwitch.length; c++) {
                    if (lightsToSwitch[c]) {
                        getLight(c).toggle();
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        LightSwitchGameGenerator switchGen = new LightSwitchGameGenerator(5, 5);
        for (int i = 0; i < 5; i++) {
            LightSwitchNode node = switchGen.getSwitch(i);
            for (int k = 0; k < node.getLights().length; k++) {
                System.out.print(String.format("Switch %d %b ", Integer.valueOf(i), Boolean.valueOf(node.getLights()[k])));
            }
            System.out.println("");
        }
        for (int j = 0; j < 5; j++) {
            System.out.println(String.format("Light %d %b", Integer.valueOf(j), Boolean.valueOf(switchGen.getLight(j).isLit())));
        }
    }
    private void addLight(SwitchableLightNode light) {
        this.lights.add(light);
    }
    private void addSwitch(LightSwitchNode lightSwitch) {
        this.switches.add(lightSwitch);
    }
    public LightSwitchNode getSwitch(int i) {
        return this.switches.get(i);
    }
    public SwitchableLightNode getLight(int i) {
        return this.lights.get(i);
    }
}
