package view;

import controller.AbstractController;

import javax.swing.*;
import java.awt.*;

public class CRUDMenu<C extends AbstractController> extends JMenu {

    C controller;
    AdminView adminView;
    JMenuItem[] menuItem = new JMenuItem[3];

    public CRUDMenu(String text, C controller, AdminView adminView) {
        this.controller = controller;
        this.adminView = adminView;

        setText(text);
        setIconTextGap(55);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);

        menuItem[0] = addMenuItem("등록");
        menuItem[1] = addMenuItem("수정");
        menuItem[2] = addMenuItem("삭제");
    }

    private JMenuItem addMenuItem(String text) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.setText(text);
        menuItem.setPreferredSize(new Dimension(150, 30));
        menuItem.setMargin(new Insets(0, 30, 0, 0));
        menuItem.setHorizontalTextPosition(SwingConstants.CENTER);
        menuItem.setHorizontalAlignment(SwingConstants.CENTER);

        menuItem.addActionListener(e -> {
            adminView.setVisible(false);
            controller.setVisible(true);
        });

        controller.quit.addActionListener(e -> {
            controller.setVisible(false);
            adminView.setVisible(true);
        });

        add(menuItem);
        return menuItem;
    }
}
