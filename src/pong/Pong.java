package pong;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Pong implements ActionListener, KeyListener {

	public static Pong pong;
	public final int WIDTH = 900, HEIGHT = 600;
	public Renderer renderer;
	public Paddle player1;
	public Paddle player2;
	public Paddle player3;
	public Paddle player4;
	public boolean w, s, up, down, f, g, j, k;
	public JFrame jframe;
	public Ball ball;
	
	
	public static void main(String[] args) {
		pong = new Pong();
	}

	public Pong() {
		
		Timer timer = new Timer(20, this);
		jframe = new JFrame("Pong");
		
		renderer = new Renderer();
		
		jframe.setSize(WIDTH + 6, HEIGHT + 28);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setResizable(false);
		jframe.add(renderer);
		jframe.setVisible(true);
		jframe.addKeyListener(this);
		
		start();
		
		timer.start();		
		
	}

	public void start() {
		player1 = new Paddle(1, this);
		player2 = new Paddle(2, this);
		player3 = new Paddle(3, this);
		player4 = new Paddle(4, this);
		ball = new Ball(this);
	}
	
	public void update() {
		if(w) {
			player1.move(true);
		}
		if(s) {
			player1.move(false);
		}
		if(up) {
			player2.move(true);
		}
		if(down) {
			player2.move(false);
		}
		if(f) {
			player3.move(true);
		}
		if(g) {
			player3.move(false);
		}
		if(j) {
			player4.move(true);
		}
		if(k) {
			player4.move(false);
		}
			
		ball.update(player1, player2, player3, player4);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		update();
		renderer.repaint();
	}

	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		player1.render(g);
		player2.render(g);
		player3.render(g);
		player4.render(g);
		ball.render(g);
		
		g.setColor(Color.WHITE.darker().darker().darker().darker());
		g.fillRect(0, 0, player1.x + player1.width, player3.y + player3.height);
		g.fillRect(0, HEIGHT - 2 * player4.height, player1.x + player1.width, player3.y + player3.height);
		g.fillRect(WIDTH - 2 * player2.width, 0, player1.x + player1.width, player3.y + player3.height);
		g.fillRect(WIDTH - 2 * player2.width, HEIGHT - 2 * player4.height, player1.x + player1.width, player3.y + player3.height);
		
		g.setColor(Color.WHITE);
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g.setStroke(dashed);
        g.drawLine(player1.x + player1.width, player3.y + player3.height, WIDTH - 2 * player2.width, HEIGHT - 2 * player4.height);
        g.drawLine(player1.x + player1.width, HEIGHT - 2 * player4.height, WIDTH - 2 * player2.width, player3.y + player3.height);
		
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", 1, 25));
		g.drawString(Integer.toString(player1.score), player1.x + 1, player1.y + player1.height / 2 + 4);
		g.drawString(Integer.toString(player2.score), player2.x + 1, player2.y + player2.height / 2 + 4);
		g.drawString(Integer.toString(player3.score), player3.x + player3.width / 2 - 15, player3.y + player3.height - 4);
		g.drawString(Integer.toString(player4.score), player4.x + player4.width / 2 - 15, player4.y + player4.height - 4);
		
	}

	public void keyPressed(KeyEvent e) {
		int id = e.getKeyCode();
		
		if(id == KeyEvent.VK_W) {
			w = true;
		}
		else if(id == KeyEvent.VK_S) {
			s = true;
		}
		else if(id == KeyEvent.VK_UP) {
			up = true;
		}
		else if(id == KeyEvent.VK_DOWN) {
			down = true;
		}
		else if(id == KeyEvent.VK_F) {
			f = true;
		}
		else if(id == KeyEvent.VK_G) {
			g = true;
		}
		else if(id == KeyEvent.VK_J) {
			j = true;
		}
		else if(id == KeyEvent.VK_K) {
			k = true;
		}
	
	}

	public void keyReleased(KeyEvent e) {
		int id = e.getKeyCode();

		if (id == KeyEvent.VK_W) {
			w = false;
		}
		else if(id == KeyEvent.VK_S) {
			s = false;
		}
		else if(id == KeyEvent.VK_UP) {
			up = false;
		}
		else if(id == KeyEvent.VK_DOWN) {
			down = false;
		}
		else if(id == KeyEvent.VK_F) {
			f = false;
		}
		else if(id == KeyEvent.VK_G) {
			g = false;
		}
		else if(id == KeyEvent.VK_J) {
			j = false;
		}
		else if(id == KeyEvent.VK_K) {
			k = false;
		}
	}

	public void keyTyped(KeyEvent e) {}
	
}
