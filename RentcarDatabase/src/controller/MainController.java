package controller;

import model.DatabaseInitializer;
import view.AdminView;
import view.MainView;
import view.UserView;

import java.awt.*;

public class MainController {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				DatabaseInitializer.getInstance().init();
				MainController.getInstance().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public static MainController getInstance() {
		return MainControllerHolder.instance;
	}

	private static class MainControllerHolder {
		private static final MainController instance = new MainController();
	}

	private MainController() {
		addListenerToGoToAdminMenu();
		addListenerToGoToUserMenu();
	}

	public void setVisible(boolean value) {
		MainView.getInstance().setVisible(value);
	}

	private void addListenerToGoToAdminMenu() {
		MainView.getInstance().goToAdminButton.addActionListener(e -> {
			setVisible(false);
			AdminController.getInstance().setVisible(true);
		});
	}

	private void addListenerToGoToUserMenu() {
		MainView.getInstance().goToUserButton.addActionListener(e -> {
			setVisible(false);
			UserController.getInstance().setVisible(true);
		});
	}
}
