package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

//客製 關閉頁籤 的按鈕內容
class CloseButton extends JButton implements MouseListener {
 String[] imgPath = null;
 
 public CloseButton(String[] imgPath) {
     this.imgPath = imgPath;
     int size = 17;
     setPreferredSize(new Dimension(size, size));
     setToolTipText("關閉頁籤");
     setUI(new BasicButtonUI());
     setContentAreaFilled(false);
     setFocusable(false);
     setBorder(BorderFactory.createEtchedBorder());
     setBorderPainted(false);
     addMouseListener(this);
     setRolloverEnabled(true);
 }

 protected void paintComponent(Graphics g) {
     super.paintComponent(g);
     
     // 如無圖片，則利用繪圖的方式繪製出來
     Graphics2D g2 = (Graphics2D) g.create();
     if(imgPath == null) {
         if (getModel().isPressed()) {
             g2.translate(1, 1);
         }
         g2.setStroke(new BasicStroke(3));
         g2.setColor(Color.BLACK);
         if (getModel().isRollover()) {
             g2.setColor(Color.RED);
         }
         g2.drawLine(5, 5, 12, 12);
         g2.drawLine(12, 6, 6, 12);
         g2.dispose();
     }
     else {
         ImageIcon img, imgEntered, imgPressed;
         int size = imgPath.length;
         if(size == 3) {
             img = new ImageIcon(this.getClass().getResource(imgPath[0]));
             imgEntered = new ImageIcon(this.getClass().getResource(imgPath[1]));
             imgPressed = new ImageIcon(this.getClass().getResource(imgPath[2]));
         }
         else if(size == 2) {
             img = new ImageIcon(this.getClass().getResource(imgPath[0]));
             imgEntered = new ImageIcon(this.getClass().getResource(imgPath[1]));
             imgPressed = imgEntered;
         }
         else {
             img = new ImageIcon(this.getClass().getResource(imgPath[0]));
             imgEntered = img;
             imgPressed = img;
         }
         
         if (getModel().isRollover()) {
              g2.drawImage(imgEntered.getImage(), 1, 2, null, this);
         }
         else if(getModel().isPressed()) {
             g2.drawImage(imgPressed.getImage(), 1, 2, null, this);
         }
         else {
             g2.drawImage(img.getImage(), 1, 1, null, this);
         }
         g2.dispose();
     }
 }

 @Override
 public void mouseClicked(MouseEvent e) {}

 @Override
 public void mousePressed(MouseEvent e) {}

 @Override
 public void mouseReleased(MouseEvent e) {}

 @Override
 public void mouseEntered(MouseEvent e) {
     Component component = e.getComponent();
     if (component instanceof AbstractButton) {
         AbstractButton button = (AbstractButton) component;
         button.setBorderPainted(true);
     }
 }

 @Override
 public void mouseExited(MouseEvent e) {
     Component component = e.getComponent();
     if (component instanceof AbstractButton) {
         AbstractButton button = (AbstractButton) component;
         button.setBorderPainted(false);
     }
 }
}