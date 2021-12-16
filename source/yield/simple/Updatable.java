package yield.simple;

public interface Updatable {

    public void update();
    public default void updateStart() {

    }
    
}
