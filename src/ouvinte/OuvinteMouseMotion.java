package ouvinte;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class OuvinteMouseMotion implements MouseMotionListener {
	@Override
	public void mouseDragged(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			JPanel component = (JPanel) e.getComponent().getParent();
			Point pt = new Point(SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), component));
			e.getComponent().setLocation((int) pt.getX(), (int) pt.getY());
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

}
