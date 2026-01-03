package object;

public class OBJ_hoe extends SuperObject {

    public OBJ_hoe() {
        name = "Hoe";

        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/tools/hoe.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}