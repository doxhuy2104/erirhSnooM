//package attack;
//
//import entity.Player;
//
//import java.awt.event.MouseMotionListener;
//import java.awt.event.MouseEvent;
//
//public class Attack extends Player implements MouseMotionListener {
//
//    private int mouseX, mouseY;
//
//    public ToaDo() {
//        addMouseMotionListener(this);
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.drawString("Mouse Coordinates: (" + mouseX + ", " + mouseY + ")", 10, 20);
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent e) {
//        mouseX = e.getX();
//        mouseY = e.getY();
//        repaint();
//    }
//
//    @Override
//    public void mouseDragged(MouseEvent e) {
//        // Không cần xử lý trong trường hợp này
//    }
//
//}