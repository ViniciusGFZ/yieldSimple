package yield.simple;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.HashSet;

public class YldSimpleCore implements Updatable {

    private HashSet<YldScript> scripts = new HashSet<>();

    public YldSimpleCore() {

    }

    @Override
    public void update() {
        int n = -1, maxLayer = 1;

        try {
            while (n <= maxLayer) {
                for (YldScript script : scripts) {
                    if (maxLayer < script.getLayer())
                        maxLayer = script.getLayer();
                    boolean call = false;
                    if (n == script.getLayer()) {
                        try {
                            if (script.getRoom().hashCode() == YldScript.getActRoom().hashCode())
                                call = true;
                            else
                                call = false;
                            if (script.getRoom().hashCode() == "null".hashCode())
                                call = true;
                        } catch (Exception e) {
                            call = true;
                        }
                    }

                    if (call) {
                        script.tick();
                    }
                }
                n++;
            }

        } catch (Exception ignore) {
        }


    }

    public void render(Graphics g) {
        int n = -1, maxLayer = 1;

        Graphics2D g2 = (Graphics2D) g;

        try {
            while (n <= maxLayer) {
                for (YldScript script : scripts) {
                    AffineTransform at = g2.getTransform();
                    if (maxLayer < script.getLayer())
                        maxLayer = script.getLayer();
                    boolean call = false;
                    if (n == script.getLayer()) {
                        try {
                            if (script.getRoom().hashCode() == YldScript.getActRoom().hashCode())
                                call = true;
                            else
                                call = false;
                            if (script.getRoom().hashCode() == "null".hashCode())
                                call = true;
                        } catch (Exception e) {
                            call = true;
                        }
                    }

                    if (call) {
                        script.render(g);
                    }
                    g2.transform(at);
                }
                n++;
            }

        } catch (Exception ignore) {
        }

        if (scripts.size() <= 0) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            String s = "SimpleYield";
            g.setFont(new Font("arial", 0, 40));
            int sp = 10;
            int x = SimpleYield.getWidth() / 2 - g.getFontMetrics().stringWidth(s) / 2
                    - SimpleYield.getSimpleYieldLogo().getWidth(null) / 2 - sp / 2,
                    y = SimpleYield.getHeight() / 2 - SimpleYield.getSimpleYieldLogo().getHeight(null);
            g.drawImage(SimpleYield.getSimpleYieldLogo(), x, y, null);
            x += SimpleYield.getSimpleYieldLogo().getWidth(null) + sp;
            y += SimpleYield.getSimpleYieldLogo().getHeight(null) / 2 + (g.getFont().getSize() / 3);
            g.setColor(Color.white);
            g.drawString(s, x, y);
        }
    }

    public HashSet<YldScript> getScripts() {
        return scripts;
    }

    public void setScripts(HashSet<YldScript> scripts) {
        this.scripts = scripts;
    }

}
