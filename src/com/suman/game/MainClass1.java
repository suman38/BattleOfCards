package com.suman.game;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainClass1 {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame frm = new JFrame("Battle of Cards");
				Game game = new Game();
				frm.add(game);
				frm.pack();
				frm.setLocationRelativeTo(null);
				frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frm.setResizable(false);
				frm.setVisible(true);
				game.startGame();
			}
		});
	}
}
