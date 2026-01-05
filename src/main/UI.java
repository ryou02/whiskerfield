package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class UI {
    Gamepanel gp;
    Font arial_40;
    Font arial_20;

    public UI(Gamepanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.BOLD, 40);
        arial_20 = new Font("Arial", Font.PLAIN, 20);
    }

    public void draw(Graphics2D g2) {
        // Always draw inventory
        drawInventory(g2);
        
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen(g2);
        }
    }

    public void drawInventory(Graphics2D g2) {
        int slotSize = gp.tileSize;
        int startX = gp.screenWidth / 2 - (slotSize * gp.player.maxInventorySize) / 2;
        int startY = gp.screenHeight - slotSize - 10;
        
        for (int i = 0; i < gp.player.maxInventorySize; i++) {
            int x = startX + (i * slotSize);
            int y = startY;
            
            // Draw slot background
            g2.setColor(new Color(50, 50, 50, 200));
            g2.fillRect(x, y, slotSize, slotSize);
            
            // Draw slot border
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(2));
            g2.drawRect(x, y, slotSize, slotSize);
            
            // Draw item if present
            if (gp.player.inventory[i] != null) {
                g2.drawImage(gp.player.inventory[i].image, x + 4, y + 4, slotSize - 8, slotSize - 8, null);
            }
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
