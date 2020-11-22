package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import org.json.simple.parser.ParseException;

import support.Constants;
import support.GameOption;

/*
 * Provides a communication channel between the UI (View) and the input processing (GameRunner)
 * 
 */
public class GameViewInterface implements ActionListener{
	
	GameRunner runner;
	View view;
	boolean terminateOnError;
	
	public GameViewInterface(){
		view = new View(this);
		terminateOnError = false;
		try {
			runner = new GameRunner(this);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			sendError(e, GameOption.TERMINATE_AFTER_ERROR);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			sendError(e, GameOption.TERMINATE_AFTER_ERROR);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			sendError(e, GameOption.TERMINATE_AFTER_ERROR);
		}
		try{
			view.setText(runner.gsdata.getScene(Constants.introTextIndex).getDescription() + "\n");
			view.setText(runner.gsdata.getScene(Constants.introTextIndex).getDescription() + "\n");
		}catch(NullPointerException e){
			sendError(e, GameOption.CONTINUE_AFTER_ERROR);
		}
		
		//view.setText(runner.scene.getDescription()+"\n"); //intro text. I don't like how it's hard coded :P
	}
	

	//Used to return the view to be put into a frame
	public View getView(){
		return view;
	}

	//used by the GameRunner to deliver output text to the UI
	public void pushOutput(String text){
		try {
			view.addLine(text);
		} catch (BadLocationException e) {
			sendError(e, GameOption.CONTINUE_AFTER_ERROR);
		}
	}
	
	//adds the error window to the UI and writes the error stack trace to it
	public void sendError(Exception e, GameOption willTerminate){
		//create the string that is the stack trace
		String output;
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		output = sw.toString();
		
		//add the error window
		view.addErrorWindow();
		view.addErrorText(output);
		
		//set the program to terminate if necessary. Will not reset once set
		terminateOnError = (willTerminate == GameOption.TERMINATE_AFTER_ERROR) | terminateOnError;		
	}

	//used by the View to deliver input text to the GameRunner
	@Override
	public void actionPerformed(ActionEvent e) {
		//performs the menu actions
		if(e.getSource() instanceof JMenuItem){
			JMenuItem item = (JMenuItem) e.getSource();
			String command = item.getText().toLowerCase();
			if(runner.isCommand(command)){
				try {
					view.addLine("> "+command, Color.BLUE);
				} catch (BadLocationException e1) {
					sendError(e1, GameOption.CONTINUE_AFTER_ERROR);
				}
				runner.receiveInputText(command);
				view.clearInputText();
			}else{
				if(command.equals("font")){ //font options
					
				}
				
			}
		//delivers input text when "enter" is pressed
		}else if(e.getSource() instanceof JTextField){
			try {
				view.addLine("> "+view.getInputText(), Color.BLUE);
			} catch (BadLocationException e1) {
				sendError(e1, GameOption.CONTINUE_AFTER_ERROR);
			}
			runner.receiveInputText(view.getInputText());
			view.clearInputText();
		//closes the error window when the button is pressed
		}else if(e.getSource() instanceof JButton){
			//if we got the button in the error window, either continue with the game by removing the error window, 
			//or exit the game if we got an error that would make the game not work
			String s = e.getActionCommand().toLowerCase();
			if(s.equals("close")){
				if(terminateOnError){
					System.exit(0);
				}else{
					view.removeErrorWindow();
				}
			}
		}
		
	}
	
}
