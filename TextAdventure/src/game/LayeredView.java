package game;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import support.PanelRemover;

public class LayeredView extends JLayeredPane{

	View v;
	
	public static final int UPPER_LAYER = 3;
	public static final int LOWER_LAYER = 1;
	
	private JTextArea errorText;
	private JPanel errorPanel;
	private JButton closeError;
	private ActionListener out;
	boolean errorWindowAdded;
	public LayeredView(ActionListener l){
		v = new View(l);
		add(v, LOWER_LAYER);
		errorPanel = new JPanel();
		errorText = new JTextArea();
		errorText.setEditable(false);
		errorPanel.setLayout(new BorderLayout());
		errorPanel.setPreferredSize(new Dimension(800, 600));
		errorPanel.add(errorText, BorderLayout.CENTER);
		closeError = new JButton("Close");
		closeError.addActionListener(new PanelRemover(errorPanel));
		errorPanel.add(closeError, BorderLayout.SOUTH);
	}
	
	public View getView(){
		return v;
	}
	
	public Component add(Component c){
		add(c, UPPER_LAYER);
		return null;
	}
	
	public void addErrorWindow(){
		if(errorPanel.getParent() != null){
			errorText.setText("");
			add(errorPanel, UPPER_LAYER);
			revalidate();
			repaint();
			errorWindowAdded = true;
			//v.disableTextEntry();
		}
	}
	
	public void addErrorText(String s){
		if(errorText.getText().equals("")){
			errorText.setText(s);
		}else{
			errorText.append(s);
		}
	}
	
	public void removeErrorWindow(){
		if(errorWindowAdded){
			remove(errorPanel);
			repaint();
			errorWindowAdded = false;
			v.enableTextEntry();
		}
	}
}
