import javax.swing.JButton;
import javax.swing.JFrame;

public class main {
	public static void main(String args[]) {
		JFrame frame  = new JFrame("Trillo");
		
		JButton start  = new JButton("Start");
		JFrame startScreen =  new JFrame();
		
		BlockBreakerPanel panel = new BlockBreakerPanel(frame,startScreen);
		
		start.addActionListener(listener->{
		 startScreen.setVisible(false);
		 frame.setVisible(true) ;
		});
		
		
		frame.getContentPane().add(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(false);
		
		frame.setSize( 490, 600);
		
		startScreen.setResizable(false);
		
		startScreen.getContentPane().add(start);
		
		startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		startScreen.setVisible(true);
		
		startScreen.setSize( 490, 600);
		
		startScreen.setResizable(false);
		
	
	}
}
