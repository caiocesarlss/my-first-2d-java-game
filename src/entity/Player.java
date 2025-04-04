package entity;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	GamePanel gamePanel;
	KeyHandler keyHandler;
	
	public Player(GamePanel gamePanel, KeyHandler keyHandler) {
		this.gamePanel = gamePanel;
		this.keyHandler = keyHandler;
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
		direction = "DOWN";
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if (isAnyKeyPressed()) {
			if (keyHandler.upPressed == true) {
				direction = "UP";
				y -= speed;
			} 
			else if (keyHandler.downPressed == true) {
				direction = "DOWN";
				y += speed;
			} 
			else if (keyHandler.leftPressed == true) {
				direction = "LEFT";
				x -= speed;
			} 
			else if (keyHandler.rightPressed == true) {
				direction = "RIGHT";
				x += speed;
			}
			
			spriteCounter++;
			
			if (spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else {
					spriteNum = 1;
				}
				
				spriteCounter = 0;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		//g2.setColor(Color.WHITE);
		//g2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
		
		BufferedImage image = null;
		
		switch (direction) {
		case "UP":
			if (spriteNum == 1) {
				image = up1;
			} else {
				image = up2;
			}
			break;
		case "DOWN":
			if (spriteNum == 1) {
				image = down1;
			} else {
				image = down2;
			}
			break;
		case "LEFT":
			if (spriteNum == 1) {
				image = left1;
			} else {
				image = left2;
			}
			break;
		case "RIGHT":
			if (spriteNum == 1) {
				image = right1;
			} else {
				image = right2;
			}
			break;
		}
		
		g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
	}
	
	public boolean isAnyKeyPressed() {
		if (keyHandler.upPressed == true || keyHandler.downPressed == true ||
				keyHandler.leftPressed == true || keyHandler.rightPressed == true) {
			return true;
		}
		
		return false;
	}

}
