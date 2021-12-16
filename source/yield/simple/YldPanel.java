package yield.simple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class YldPanel extends JPanel implements Updatable {

    public int width, height;

    private Graphics graphics, paintGraphics;

    private YldSimpleCore core;

    public YldPanel(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void update() {
        tick();
    }

    @Override
    public void updateStart() {
        tick();
    }

    BufferedImage image;

    private void tick() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics pg) {
        super.paintComponent(pg);
        pg.setColor(Color.black);
        pg.fillRect(0, 0, getWidth(), getHeight());
        if (image == null) {
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        }
        Graphics g = image.getGraphics();
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        graphics = g;
        if(core != null) {
            core.render(g);
        }
        g.dispose();
        image.flush();
        //System.out.println(Yield.getFrame().getInsets());
        pg.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        paintGraphics = pg;
        pg.dispose();
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public Graphics getPaintGraphics() {
        return paintGraphics;
    }

    public void setPaintGraphics(Graphics paintGraphics) {
        this.paintGraphics = paintGraphics;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public YldSimpleCore getCore() {
        return core;
    }

    public void setCore(YldSimpleCore core) {
        this.core = core;
    }

}
