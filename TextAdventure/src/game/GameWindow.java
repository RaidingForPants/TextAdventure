package game;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class GameWindow {
	JFrame frame;
	
	public GameWindow(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void setContent(Container c){
		frame.setContentPane(c);
		frame.pack();
	}
	
	public void setJMenuBar(JMenuBar bar){
		frame.setJMenuBar(bar);
	}
	
	public void show(){
		frame.setVisible(true);
	}
	
	public void hide(){
		frame.setVisible(false);
	}
}
