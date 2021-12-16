package yield.simple;

import java.awt.Graphics;

public abstract class YldScript {

    protected int layer;
    protected String room = "default";
    protected static String actRoom = "default";

    public YldScript() {
        SimpleYield.getCore().getScripts().add(this);
    }

    public void tick() {

    }

    public void render(Graphics g) {

    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;

    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public static String getActRoom() {
        return actRoom;
    }

    public static void setActRoom(String actRoom) {
        YldScript.actRoom = actRoom;
    }
}
