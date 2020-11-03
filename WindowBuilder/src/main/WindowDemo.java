package main;

import java.awt.EventQueue;
import model.MainWindow;
import pattern.LinuxWindowImp;
import pattern.WindowImp;



public class WindowDemo {
	public static final WindowImp imp = new LinuxWindowImp();
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow(imp);
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}