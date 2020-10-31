package model;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class CustomJTabbedPaneUI extends JTabbedPane {
    String[] imgPath = null;
    
    public CustomJTabbedPaneUI() {}
    
    public CustomJTabbedPaneUI(String[] imgPath) {
        this.imgPath = imgPath;
    }
    
    @Override 
    public void addTab(String title, final Component content) {
        JPanel tab = new JPanel(new BorderLayout());
        JLabel label = new JLabel(title);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 4));
        JButton button = new CloseButton(imgPath);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                removeTabAt(indexOfComponent(content));
            }
        });
        tab.add(label, BorderLayout.WEST);
        tab.add(button, BorderLayout.EAST);
        tab.setOpaque(false);
        tab.setBorder(BorderFactory.createEmptyBorder(2, 1, 1, 1));
        super.addTab(title, content);
        setTabComponentAt(getTabCount() - 1, tab);
    }
}
