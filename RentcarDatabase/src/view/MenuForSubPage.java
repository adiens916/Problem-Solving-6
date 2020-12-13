package view;

import javax.swing.*;
import java.awt.*;

public class MenuForSubPage extends JMenu {
    private final JMenuItem[] menuItems = new JMenuItem[3];

    public JMenuItem[] getMenuItems() {
        return menuItems;
    }

    public MenuForSubPage(String text) {
        setText(text);
        setIconTextGap(55);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);

        menuItems[0] = new MenuItemForSubPage("등록");
        menuItems[1] = new MenuItemForSubPage("수정");
        menuItems[2] = new MenuItemForSubPage("삭제");

        for (JMenuItem menuItem : menuItems) {
            add(menuItem);
        }
    }
}

class MenuItemForSubPage extends JMenuItem {
    public MenuItemForSubPage(String text) {
        super(text);
        setPreferredSize(new Dimension(150, 30));
        setMargin(new Insets(0, 30, 0, 0));
        setHorizontalTextPosition(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
