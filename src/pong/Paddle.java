package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Paddle {

	public int paddleNumber;
	public int x, y, width = 25, height = 150;
	public int score;
	public Pong pong;
	
	public Paddle(int paddleNumber, Pong pong) {
		this.paddleNumber = paddleNumber;

		if (paddleNumber == 1 || paddleNumber == 2) {
			if (paddleNumber == 1) {
				x = width;
			}
			if (paddleNumber == 2) {
				x = pong.WIDTH - 2*width;
			}
			y = pong.HEIGHT / 2 - height / 2;
		} else {
			width = 150;
			height = 25;
			if (paddleNumber == 3){
				y = height;
			}
			if (paddleNumber == 4) {
				y = pong.HEIGHT - 2 * height;
			}
			x = pong.WIDTH / 2 - width / 2;
		}
	}
	

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}
	
	public void move(boolean up) {
		int speed = 15;
		
		if (paddleNumber == 1 || paddleNumber == 2) {
			if (up) {
				if (y - speed > Pong.pong.player3.y + Pong.pong.player3.height) {
					y -= speed;
				}
				else {
					y = Pong.pong.player3.y + Pong.pong.player3.height;
				}
			}
			else {
				if (y + height + speed < Pong.pong.player4.y) {
					y += speed;
				}
				else {
					y = Pong.pong.player4.y - height;
				}
			}
		} else {
			if (up) {
				if (x - speed > Pong.pong.player1.x + Pong.pong.player1.width) {
					x -= speed;
				}
				else {
					x = Pong.pong.player1.x + Pong.pong.player1.width;
				}
			}
			else {
				if (x + width + speed < Pong.pong.player2.x) {
					x += speed;
				}
				else {
					x = Pong.pong.player2.x - width;
				}
			}
		}
	}
	
	
	public int getPaddleNumber() {
		return paddleNumber;
	}
	
	public void setPaddleNumber(int paddleNumber) {
		this.paddleNumber = paddleNumber;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
