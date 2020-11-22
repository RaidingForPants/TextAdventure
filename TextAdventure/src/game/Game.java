package game;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Game {
	
	GameViewInterface GVInter;
	GameWindow window;
	JMenuBar menuBar;
	ArrayList<JMenu> menus;
	ArrayList<JMenuItem> menuItems;
	
	public Game(){
		
	}
	
	public void init(){
		window = new GameWindow();
		//init objects
		menus = new ArrayList<JMenu>();
		menuItems = new ArrayList<JMenuItem>();
		GVInter = new GameViewInterface();
		menuBar = new JMenuBar();
		
		//create the menu bar
		JMenu commands = new JMenu("Menu");
		JMenuItem temp = new JMenuItem("Objects");
		commands.add(temp);
		temp.addActionListener(GVInter);
		temp = new JMenuItem("Actions");
		commands.add(temp);
		temp.addActionListener(GVInter);
		temp = new JMenuItem("Inventory");
		commands.add(temp);
		temp.addActionListener(GVInter);
		temp = new JMenuItem("Quit");
		commands.add(temp);
		temp.addActionListener(GVInter);
		menuBar.add(commands);
		
		window.setJMenuBar(menuBar);
		window.setContent(GVInter.getView());
		window.show();
	}
}


/*

static JFrame frame;
	static GameViewInterface gvinter;
	static JMenuBar menuBar;
	static ArrayList<JMenu> menus;
	static ArrayList<JMenuItem> menuItems;
	
	public static void main(String[] args){
		//init objects
		frame = new JFrame();
		menus = new ArrayList<JMenu>();
		menuItems = new ArrayList<JMenuItem>();
		gvinter = new GameViewInterface();
		menuBar = new JMenuBar();
		
		//create the menu bar
		JMenu commands = new JMenu("Menu");
		JMenuItem temp = new JMenuItem("Objects");
		commands.add(temp);
		temp.addActionListener(gvinter);
		temp = new JMenuItem("Actions");
		commands.add(temp);
		temp.addActionListener(gvinter);
		temp = new JMenuItem("Inventory");
		commands.add(temp);
		temp.addActionListener(gvinter);
		temp = new JMenuItem("Quit");
		commands.add(temp);
		temp.addActionListener(gvinter);
		menuBar.add(commands);
		
		//put everything in the frame and show it
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(gvinter.getView());
		//frame.setSize(screenSize);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//frame.setUndecorated(true);
		frame.setJMenuBar(menuBar);
		frame.pack();
		frame.setVisible(true);
		
	}

*/