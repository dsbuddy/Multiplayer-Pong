package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {

	public int x, y, width = 25, height = 25;
	public int motionX, motionY;
	public Random random;
	public int amountOfHits;
	public Pong pong;
	
	
	public Ball(Pong pong) {
		this.random = new Random();
		this.pong = pong;
		spawn();
	}
	
	public void spawn() {
		this.amountOfHits = 0;
		this.x = pong.WIDTH / 2 - this.width / 2;
		this.y = pong.HEIGHT / 2 - this.height / 2;
		
		this.motionY = -2 + random.nextInt(4);
		if(motionY == 0) {
			motionY = 1;
		}
		
		if(random.nextBoolean()) {
			motionX = 1;
		}
		else {
			motionX = -1;
		}
	}
	
	public void update(Paddle paddle1, Paddle paddle2, Paddle paddle3, Paddle paddle4) {
		int speed = 5;
		
		this.x += motionX * speed;
		this.y += motionY * speed;
		
//		if(this.y < 0 || this.y + height > pong.HEIGHT) {
//			motionY *= -1;
//		}
		
		if(checkCollision(paddle1) == 1) {
			this.motionX = 1 + (amountOfHits / 5);
			this.motionY = -2 + random.nextInt(4);
			if(motionY == 0) motionY = 1;
			amountOfHits++;
		}
		else if(checkCollision(paddle2) == 1) {
			this.motionX = -1 - (amountOfHits / 5);
			this.motionY = -2 + random.nextInt(4);
			if(motionY == 0) motionY = 1;
			amountOfHits++;
		}
		else if(checkCollision(paddle3) == 1) {
			this.motionY = 1 + (amountOfHits / 5);
			this.motionX = -2 + random.nextInt(4);
			if(motionX == 0) motionX = 1;
			amountOfHits++;
		}
		else if(checkCollision(paddle4) == 1) {
			this.motionY = -1 - (amountOfHits / 5);
			this.motionX = -2 + random.nextInt(4);
			if(motionX == 0) motionX = 1;
			amountOfHits++;
		}
		
		if(checkCollision(paddle1) == 2) {
			paddle2.score++;
			paddle3.score++;
			paddle4.score++;
			spawn();
		} else if(checkCollision(paddle2) == 2) {
			paddle1.score++;
			paddle3.score++;
			paddle4.score++;
			spawn();
		} else if (checkCollision(paddle3) == 2) {
			paddle1.score++;
			paddle2.score++;
			paddle4.score++;
			spawn();
		} else if (checkCollision(paddle4) == 2) {
			paddle1.score++;
			paddle2.score++;
			paddle3.score++;
			spawn();
		}
	}
	
	public int checkCollision(Paddle paddle) {
		
		if(this.x < paddle.x + paddle.width && this.x + width > paddle.x && this.y < paddle.y + paddle.height && this.y + height > paddle.y) {
			return 1; //bounce
		}
		else if (this.y < paddle.y + paddle.height && this.y + height > paddle.y && this.x < paddle.x + paddle.width && this.x + width > paddle.x) {
			return 1;
		}
		if ((this.x + this.width < 0 && paddle.paddleNumber == 1) || (this.x > pong.WIDTH && paddle.paddleNumber == 2)) {
			return 2; //score
		} else if ((this.y + this.height < 0 && paddle.paddleNumber == 3) || (this.y > pong.HEIGHT && paddle.paddleNumber == 4)) {
			return 2;
		}
		
		return 0;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, width, height);
	}
	
}
