import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarqueeEvent extends JFrame {
    private final JLabel marqueeLabel;
    private int xPosition = 600; // Start position of the text

    public MarqueeEvent() {
        setTitle("Marquee Example");
        setSize(800, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Create a JLabel for the marquee
        marqueeLabel = new JLabel("This is a simple marquee example!");
        marqueeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        marqueeLabel.setSize(600, 50);
        marqueeLabel.setForeground(Color.BLUE);

        // Set initial position
        marqueeLabel.setLocation(xPosition, 25);
        add(marqueeLabel);

        // Timer to update the position of the label
        // Move left
        // Reset if out of view
        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xPosition -= 2; // Move left
                if (xPosition < -600) { // Reset if out of view
                    xPosition = getWidth();
                }
                marqueeLabel.setLocation(xPosition, 25);
            }
        });
        timer.start();
    }

    public JLabel getMarquee() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
        return marqueeLabel;
    }
}
