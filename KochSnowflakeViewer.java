import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class KochSnowflakeViewer implements ActionListener
{
    private final int WIDTH=700;
    private final int HEIGHT=700;
    private final int MIN=1,MAX=25;
    private JButton increase,decrease;
    private JLabel titleLabel,orderLabel;
    private JSlider offset1,offset2;
    private KochPanel drawing;
    private JPanel panel,tools;
    private JFrame frame;
    public static void main(String[] args)
    {
        KochSnowflakeViewer viewer=new KochSnowflakeViewer();
    }
    public KochSnowflakeViewer()
    {
        tools = new JPanel();
        tools.setLayout(new BoxLayout(tools,BoxLayout.X_AXIS));
        tools.setOpaque(true);
        titleLabel = new JLabel("          ");
        titleLabel.setForeground(Color.black);

        increase = new JButton(new ImageIcon("increase.gif"));
        increase.setPressedIcon(new ImageIcon("increasePressed.gif"));
        increase.setMargin(new Insets(0,0,0,0));
        increase.addActionListener(this);
        decrease = new JButton(new ImageIcon("decrease.gif"));
        decrease.setPressedIcon(new ImageIcon("decreasePressed.gif"));
        decrease.setMargin(new Insets(0,0,0,0));
        decrease.addActionListener(this);
        
        offset1 = new JSlider(JSlider.HORIZONTAL,1,12,6);
        offset1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                JSlider offset1 = (JSlider) evt.getSource();
                if (!offset1.getValueIsAdjusting()) {
                    int value = offset1.getValue();
                    drawing.setComp(value); frame.repaint();
                }}});

        orderLabel=new JLabel("Order: 1");
        orderLabel.setForeground(Color.black);
        tools.add(orderLabel);
        tools.add(Box.createHorizontalStrut(20));
        tools.add(decrease);tools.add(increase);
        tools.add(Box.createHorizontalStrut(20));
        tools.add(titleLabel);tools.add(offset1);
        drawing=new KochPanel(1,6);
        panel = new JPanel();
        panel.add(tools);
        panel.add(drawing);
        frame = new JFrame();
        frame.setTitle("Fractal Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(panel);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent event)
    {
        int order=drawing.getOrder();
        if(event.getSource()==increase)
            order++;
        else
            order--;
        if(order>=MIN&&order<=MAX)
        {
            orderLabel.setText("Order: "+order);
            drawing.setOrder(order);
            frame.repaint();
        }
    }
}
