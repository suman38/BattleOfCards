package com.suman.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import com.suman.game.screens.GameScreen;
import com.suman.game.screens.HomeScreen;
import com.suman.game.screens.PauseScreen;
import com.suman.game.screens.Screen;
import com.suman.game.screens.ScreenType;
import com.suman.game.utils.GameUtils;

public class Game extends JPanel implements Runnable, MouseListener, MouseMotionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	public final int GameWidth = 800; // GameHeight = (9/12)*GameWidth; // 800x600
	public final int GameHeight = 600;

	private Map<RenderingHints.Key, Object> renderMap;

	private Thread gameThread;
	private boolean running;

	private final int fps = 60;
	private int delay = 1000 / fps;

	private Screen homeScreen, gameScreen, pauseScreen;

	private UIManager uiManager;

	public Game() {
		setBackground(Color.BLACK);

		setFocusable(true);
		setDoubleBuffered(true);

		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);

		GameUtils.init();

		renderMap = new HashMap<RenderingHints.Key, Object>();
		renderMap.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		renderMap.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		uiManager = new UIManager(this);

		homeScreen = new HomeScreen(this);
		gameScreen = new GameScreen(this);
		pauseScreen = new PauseScreen(this);

		changeScreen(ScreenType.Home);

		gameThread = new Thread(this);
	}

	public void changeScreen(ScreenType type) {

		Screen s = null;
		switch (type) {
		case Home:
			s = homeScreen;
			break;

		case Game:
			s = gameScreen;
			break;

		case Pause:
			s = pauseScreen;
			break;

		default:
			break;
		}
		Screen.setScreen(s);
		Screen.getScreen().loadButtons();
	}

	public void startGame() {
		running = true;
		gameThread.start();
	}

	public void stopGame() {
		running = false;
		System.exit(0);
	}

	@Override
	public void run() {
		while (running) {
			tick();
			repaint();

			try {
				Thread.sleep(delay);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private void tick() {
		if (Screen.getScreen() != null) {
			Screen.getScreen().tick();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHints(renderMap);

		if (Screen.getScreen() != null)
			Screen.getScreen().render(g2);

		g2.dispose();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(GameWidth, GameHeight);
	}

	public UIManager getUIManager() {
		return uiManager;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		Screen.getScreen().keyPressed(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Screen.getScreen().keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Screen.getScreen().keyReleased(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Screen.getScreen().mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Screen.getScreen().mouseMoved(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Screen.getScreen().mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Screen.getScreen().mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Screen.getScreen().mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Screen.getScreen().mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Screen.getScreen().mouseExited(e);
	}
}
