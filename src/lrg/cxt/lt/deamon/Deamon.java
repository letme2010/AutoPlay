package lrg.cxt.lt.deamon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Deamon {

	private static final String BUTTON_START_TEXT = "Start";
	private static final String BUTTON_STOP_TEXT = "Stop";
	
	public static void main(String[] args) throws InterruptedException {

		System.out.println("start");

		
		JFrame frame = new JFrame();
		
		frame.setSize(300, 200);
		
		frame.setVisible(true);
		
		JButton button = new JButton();
		button.setText("Start");
		button.setSize(100, 60);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("...");
			}
		});
		
		
		frame.add(button);
		
//		do {
//			Thread.sleep(1000);
//		} while (true);

		
	}

}
