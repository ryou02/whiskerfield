package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
    Gamepanel gp;
    Font arial_40;

    public UI(Gamepanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.BOLD, 40);
    }

    public void draw(Graphics2D g2) {
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen(g2);
        }
    }

    public void drawPauseScreen(Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        
        String text = "PAUSED";
        
        // Center the text
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        int y = gp.screenHeight / 2;
        
        g2.drawString(text, x, y);
    }
}
