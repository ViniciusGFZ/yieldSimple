package yield.simple;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import yield.simple.ThreadType.Update;

public class SimpleYield {

    public static final String SimpleYieldVersion = "1";

    private static JFrame frame;
    private static YldPanel mainPanel;
    private static int width = 1280, height = 720;
    private static Update updateMain, updatePanel;
    private static Image simpleYieldLogo;
    private static YldSimpleCore core;

    public static void init() {
        frame = new JFrame();
        ThreadType.startCoroutine(new Action() {
            @Override
            public void onAction() {
                try {
                    simpleYieldLogo = ImageIO.read(new File(SimpleYield.class.getResource("/yield/simple/SimpleYield.png").getFile()))
                            .getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    frame.setIconImage(simpleYieldLogo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        frame.setName("SimpleYield Game");
        frame.setTitle("SimpleYield");
        mainPanel = new YldPanel(width, height);
        frame.add(mainPanel);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        updatePanel = new Update(mainPanel);
        updatePanel.startThread();
        core = new YldSimpleCore();
        mainPanel.setCore(core);
        updateMain = new Update(core);
        updateMain.startThread();
        frame.addKeyListener(new YldInput());
    }

    public static void setResolution(int width, int height) {
        SimpleYield.width = width;
        SimpleYield.height = height;
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void setFrame(JFrame frame) {
        SimpleYield.frame = frame;
    }

    public static YldPanel getMainPanel() {
        return mainPanel;
    }

    public static void setMainPanel(YldPanel mainPanel) {
        SimpleYield.mainPanel = mainPanel;
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        SimpleYield.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        SimpleYield.height = height;
    }

    public static Update getUpdateMain() {
        return updateMain;
    }

    public static void setUpdateMain(Update updateMain) {
        SimpleYield.updateMain = updateMain;
    }

    public static Update getUpdatePanel() {
        return updatePanel;
    }

    public static void setUpdatePanel(Update updatePanel) {
        SimpleYield.updatePanel = updatePanel;
    }

    public static Image getSimpleYieldLogo() {
        return simpleYieldLogo;
    }

    public static void setSimpleYieldLogo(Image simpleYieldLogo) {
        SimpleYield.simpleYieldLogo = simpleYieldLogo;
    }

    public static String getSimpleyieldversion() {
        return SimpleYieldVersion;
    }

    public static YldSimpleCore getCore() {
        return core;
    }

    public static void setCore(YldSimpleCore core) {
        SimpleYield.core = core;
    }

}
