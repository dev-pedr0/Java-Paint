import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaintGui extends JFrame {
    public PaintGui() {
        super("Paint");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1500, 1000));
        pack();
        setLocationRelativeTo(null);

        addGuiComponents();
    }

    private void addGuiComponents() {
        JPanel canvasPanel = new JPanel();
        SpringLayout springLayout = new SpringLayout();
        canvasPanel.setLayout(springLayout);

        Canvas canvas = new Canvas(1500, 950);
        canvasPanel.add(canvas);
        springLayout.putConstraint(SpringLayout.NORTH, canvas, 50, SpringLayout.NORTH, canvasPanel);

        JButton chooseColorButton = new JButton("Choose Color");
        chooseColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(null, "Select a color", Color.BLACK);
                chooseColorButton.setBackground(c);
                canvas.setColor(c);
            }
        });
        canvasPanel.add(chooseColorButton);
        springLayout.putConstraint(SpringLayout.NORTH, chooseColorButton, 10, SpringLayout.NORTH, canvasPanel);
        springLayout.putConstraint(SpringLayout.WEST, chooseColorButton, 25, SpringLayout.WEST, canvasPanel);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               canvas.resetCanvas();
           }
        });
        canvasPanel.add(resetButton);
        springLayout.putConstraint(SpringLayout.NORTH, resetButton, 10, SpringLayout.NORTH, canvasPanel);
        springLayout.putConstraint(SpringLayout.WEST, resetButton, 150, SpringLayout.WEST, canvasPanel);

        JSlider strokeSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, canvas.getStrokeSize());
        strokeSlider.setMajorTickSpacing(10);
        strokeSlider.setMinorTickSpacing(1);
        strokeSlider.setPaintTicks(true);

        strokeSlider.addChangeListener(e -> {
            int newSize = strokeSlider.getValue();
            canvas.setStrokeSize(newSize);
        });
        canvasPanel.add(strokeSlider);
        springLayout.putConstraint(SpringLayout.NORTH, strokeSlider, 10, SpringLayout.NORTH, canvasPanel);
        springLayout.putConstraint(SpringLayout.WEST, strokeSlider, 250, SpringLayout.WEST, canvasPanel);

        this.getContentPane().add(canvasPanel);
    }
}
