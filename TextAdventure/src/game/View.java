package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/*
 * Contains all the GUI elements. Sends text out from the textField to the ActionListener
 * 
 */
public class View extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textField;
	private JScrollPane scroller;
	private JScrollPane errorScroller;
	private JTextPane textPane;
	private SimpleAttributeSet attributes;
	private JTextArea errorText;
	private JPanel errorPanel;
	private JButton errorClose;
	private ActionListener out;
	boolean errorWindowAdded;
	
	public View(ActionListener textOut){
		setLayout(new BorderLayout());
		out = textOut;
		textPane = new JTextPane();
		textPane.setMinimumSize(new Dimension(800, 600));
		textPane.setEditable(false);
		attributes = new SimpleAttributeSet();
		textPane.setCharacterAttributes(attributes, true);
		textField = new JTextField();
		textField.addActionListener(textOut);
		Font newFont = new Font("Arial", Font.PLAIN, 16);
		textField.setFont(newFont);
		textPane.setFont(newFont);
		scroller = new JScrollPane(textPane);
		scroller.setPreferredSize(new Dimension(800, 600));
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setVerticalScrollBar(new JScrollBar());
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		errorClose = new JButton("Close");
		errorPanel = new JPanel();
		errorText = new JTextArea();
		errorScroller = new JScrollPane(errorText);
		errorScroller.setPreferredSize(new Dimension(800, 600));
		errorScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		errorScroller.setVerticalScrollBar(new JScrollBar());
		errorScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		errorClose.addActionListener(textOut);
		errorPanel.setLayout(new BorderLayout());
		errorPanel.add(errorScroller, BorderLayout.CENTER);
		errorPanel.add(errorClose, BorderLayout.SOUTH);
		errorText.setForeground(Color.RED);
		errorWindowAdded = false;
		add(scroller, BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
	}
	
	//adds a new line of text with the given color
	public void addLine(String line, Color c) throws BadLocationException{
		SimpleAttributeSet tempSet = new SimpleAttributeSet();
		
		//change the text color attribute
		StyleConstants.setForeground(tempSet, c);
		
		//get the offset of where we start coloring the text
		int length = textPane.getText().length();
		
		addLine(line);
		
		//set the document to have the new attributes for the new line added
		textPane.getStyledDocument().setCharacterAttributes(length, line.length(), tempSet, false);
	}
	
	//adds a new line of text in the default color (black)
	public void addLine(String line) throws BadLocationException{
		//insert text into the document
		textPane.getStyledDocument().insertString(textPane.getText().length(), line+"\n", null);
		
		//scroll to the bottom of the text pane
		textPane.setCaretPosition(textPane.getDocument().getLength());
	}
	
	public void addErrorWindow(){
		if(!errorWindowAdded){
			remove(scroller);
			errorText.setText("");
			add(errorPanel, BorderLayout.CENTER);
			revalidate();
			errorWindowAdded = true;
			textField.removeActionListener(out);
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
			add(scroller, BorderLayout.CENTER);
			repaint();
			errorWindowAdded = false;
			textField.addActionListener(out);
		}
	}
	
	public void disableTextEntry(){
		textField.removeActionListener(out);
	}
	
	public void enableTextEntry(){
		textField.addActionListener(out);
	}
	
	public String getInputText(){
		return textField.getText();
	}
	
	public void clearInputText(){
		textField.setText("");
	}
	
	public void clearText(){
		textPane.setText("");
	}
	
	public void setText(String s){
		textPane.setText(s);
	}

}
