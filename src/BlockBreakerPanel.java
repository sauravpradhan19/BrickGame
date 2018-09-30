import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel; 

public class BlockBreakerPanel extends JPanel implements KeyListener {
	 
	ArrayList<Block> blocks ;
	Block ball ;
	Block paddle ;
	Thread thread;
	JFrame mainFrame,startScreen;
	
	void reset() {
		blocks =new ArrayList<Block>();
		ball = new Block(237,435,35,25,"ball.png");
		paddle = new Block(175,480,150,25,"paddle.png");
		for(int  i =0;i< 8 ;i++) 
			blocks.add(new Block((i*60),0,60,25,"green2.png"));
		for(int  i =0;i< 8 ;i++) 
			blocks.add(new Block((i*60),25,60,25,"red.png"));
		for(int  i =0;i< 8 ;i++) 
			blocks.add(new Block((i*60),50,60,25,"yellow.png"));
		for(int  i =0;i< 8 ;i++)
			blocks.add(new Block((i*60),75,60,25,"green.png"));
		
		addKeyListener(this);
		setFocusable(true);
	}
	BlockBreakerPanel(JFrame frame, JFrame startScreen){
		this.mainFrame = frame;
		this.startScreen = startScreen;
		
		reset();
	}
	public void paintComponent(Graphics g) {
		paddle.draw(g,this); 
		blocks.forEach(block->{block.draw(g,this); });
		ball.draw(g, this);
	}
	public void update() {
		// TODO Auto-generated method stub
		ball.x +=ball.movX;
		ball.y += ball.movY;
		
		if(ball.x > (getWidth() - 25) || ball.x < 0)
			ball.movX*=-1;
		if(ball.y < 0 || ball.intersects(paddle))
			ball.movY*=-1;
		if(ball.y > getHeight()) { 
			thread  = null;
			mainFrame.setVisible(false);
			startScreen.setVisible(true);
			reset();
		}
		blocks.forEach(block ->{
			if(ball.intersects(block) && !block.destroyed) {
				block.destroyed=true;
				ball.movY *= -1;
			}
		});
		repaint();
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyPressed(KeyEvent e) {
		 if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			 thread = new Thread(()-> {
				 while(true) {
					 update();
					 System.out.println("Running");
					 try {
						 Thread.sleep(10);
					 }catch(InterruptedException err) {
						err.printStackTrace(); 
					 };
				 }
			 });
			 thread.start();
		 }  
		if(e.getKeyCode()== KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)) {
			paddle.x += 15;
		}
		if(e.getKeyCode()== KeyEvent.VK_LEFT  && paddle.x > 0 ) {
			paddle.x -= 15;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
