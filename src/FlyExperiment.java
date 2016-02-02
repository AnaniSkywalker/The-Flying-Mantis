/*Anani Assoutovi
 * February 1st, 2016
 * */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlyExperiment extends JDialog implements ActionListener 
{
    public static final GridLayout GRID_LAYOUT = new GridLayout(0, 1, 2, 2);

    public FlyExperiment(FlyWindow container, String title) 
    {
        super(container.getWindow(), title, true);

        setPreferredSize(new Dimension(400,400));
        //setBorder(BorderFactory.createEmptyBorder(70, 70, 70, 70));

        center(container.getWindow(), this);

        buildSettingGui(this);
        buildStartGuiButton(this);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) 
    {
        setVisible(false);
        dispose();
    }

    static FlyExperiment buildStartGuiButton(final FlyExperiment experiment) 
    {
        JPanel startPane = new JPanel();
        JButton startButton = new JButton("Start Simulation");
        startButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                experiment.setVisible(false);
                experiment.dispose();
            }
        });

        startPane.add(startButton);
        experiment.getContentPane().add(startPane, BorderLayout.SOUTH);
        return experiment;
    }

    static FlyExperiment buildSettingGui(final FlyExperiment experiment) 
    {
        GridLayout layout = new GridLayout(0, 2, 5, 5);
        JPanel settingPanel = new JPanel();
        settingPanel.setLayout(layout);

        Box radiusLabelBox = Box.createHorizontalBox();
        radiusLabelBox.add(Box.createHorizontalGlue());
        radiusLabelBox.add(new JLabel("Radius"));
        radiusLabelBox.add(Box.createHorizontalGlue());

        Box radiusFieldBox = Box.createHorizontalBox();
        radiusFieldBox.add(Box.createHorizontalGlue());
        radiusFieldBox.add(new JTextField("50"));

        Box lengthLabelBox = Box.createHorizontalBox();
        lengthLabelBox.add(Box.createHorizontalGlue());
        lengthLabelBox.add(new JLabel("Length"));
        lengthLabelBox.add(Box.createHorizontalGlue());

        Box widthLabelBox = Box.createHorizontalBox();
        widthLabelBox.add(Box.createHorizontalGlue());
        widthLabelBox.add(new JLabel("Width"));
        widthLabelBox.add(Box.createHorizontalGlue());

        settingPanel.add(add(new JPanel(GRID_LAYOUT), new JLabel("Environment"), new JCheckBox("Circle", true)));
        settingPanel.add(add(new JPanel(GRID_LAYOUT), new JLabel("Simulation Name"), new JTextField("")));
        settingPanel.add(add(new JPanel(GRID_LAYOUT), radiusLabelBox, radiusFieldBox));
        settingPanel.add(add(new JPanel(GRID_LAYOUT), new JLabel("No. of iterations"), new JTextField("")));
        settingPanel.add(add(new JPanel(GRID_LAYOUT), new JLabel(""), new JCheckBox("Rectangle")));
        settingPanel.add(add(new JPanel(GRID_LAYOUT), new JLabel("Random Turn Angle"), new JTextField("10")));
        settingPanel.add(add(new JPanel(GRID_LAYOUT), lengthLabelBox, new JTextField("")));
        settingPanel.add(add(new JPanel(GRID_LAYOUT), new JLabel("Visual Field"), new JTextField("60")));
        settingPanel.add(add(new JPanel(GRID_LAYOUT), widthLabelBox, new JTextField("")));
        settingPanel.add(add(new JPanel(GRID_LAYOUT), new JLabel("Sight Distance"), new JTextField("10")));

        experiment.getContentPane().add(settingPanel, BorderLayout.CENTER);

        return experiment;
    }

    static void center(JFrame frame, FlyExperiment experiment) 
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // calculate the new location of the window
        int w = frame.getSize().width;
        int h = frame.getSize().height;

        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        experiment.setLocation(x, y);
    }

    static JComponent add(JComponent container, JComponent ... childrens) 
    {
        if((null != container) && (null !=  childrens && (0 < childrens.length))) 
        {
            for(JComponent child : childrens) 
            {
                if(null != child) container.add(child);
            }
        }
        return container;
    }

    static JComponent add(JComponent container, String align, JComponent children) 
    {
        if(null != container && null !=  children) 
        {
            container.add(children, align);
        }
        return container;
    }
}