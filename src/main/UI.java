package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class UI {
    Gamepanel gp;
    Font arial_40;
    Font arial_20;
    public String currentDialogue = "";

    public UI(Gamepanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.BOLD, 40);
        arial_20 = new Font("Arial", Font.PLAIN, 20);
    }

    public void draw(Graphics2D g2) {
        // Always draw inventory
        drawInventory(g2);
        
        // Always draw money UI
        drawMoneyUI(g2);
        
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen(g2);
        }
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen(g2);
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
                
                // Draw stack count if stackable and count > 1
                if (gp.player.inventory[i].stackable && gp.player.inventory[i].stackCount > 1) {
                    g2.setFont(arial_20);
                    g2.setColor(Color.white);
                    String countText = String.valueOf(gp.player.inventory[i].stackCount);
                    g2.drawString(countText, x + slotSize - 15, y + slotSize - 5);
                }
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
    
    public void drawDialogueScreen(Graphics2D g2) {
        // Dialogue window position and size
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        
        // Draw semi-transparent black box
        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRoundRect(x, y, width, height, 35, 35);
        
        // Draw white border
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
        
        // Draw dialogue text centered
        g2.setFont(arial_20);
        int textWidth = (int) g2.getFontMetrics().getStringBounds(currentDialogue, g2).getWidth();
        int textX = x + (width - textWidth) / 2;
        int textY = y + height / 2;
        g2.drawString(currentDialogue, textX, textY);
    }
    
    public void drawMoneyUI(Graphics2D g2) {
        // Position at top left
        int x = 10;
        int y = 10;
        int width = gp.tileSize * 3;
        int height = gp.tileSize;
        
        // Draw semi-transparent rounded box
        g2.setColor(new Color(0, 0, 0, 180));
        g2.fillRoundRect(x, y, width, height, 20, 20);
        
        // Draw gold border
        g2.setColor(new Color(255, 215, 0));
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x + 2, y + 2, width - 4, height - 4, 18, 18);
        
        // Draw money icon ($)
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.setColor(new Color(255, 215, 0));
        g2.drawString("$", x + 12, y + height / 2 + 8);
        
        // Draw money amount
        g2.setFont(arial_20);
        g2.setColor(Color.white);
        g2.drawString(String.valueOf(gp.player.money), x + 35, y + height / 2 + 7);
    }
}
