import java.awt.*;
import java.awt.event.*;

public class AwtImageDisplay extends Frame {

  Image image;

  public AwtImageDisplay() {
    super("Image Display - AWT");
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    // Replace "path/to/your/image.jpg" with your actual image path
    image = Toolkit.getDefaultToolkit().getImage("source/board.png ");
    if (image != null) {
      g.drawImage(image, 0, 80, this);
    }
  }

  public static void getPlaceCoords(int coords[]) {
    System.out.println("COORDS y,x " + coords[0] + " " + coords[1]);
  }

  public static void main(String[] args) {
    AwtImageDisplay app = new AwtImageDisplay();
    app.setSize(780, 620); // Set preferred size
    app.setVisible(true);
  }
}