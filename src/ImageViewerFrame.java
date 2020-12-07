import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;


@SuppressWarnings("serial")

// Creates Frame with Label
class ImageViewerFrame extends JFrame implements MouseMotionListener {
    JLabel label;
    JFileChooser choose;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem open;


    // Constructor
    public ImageViewerFrame() {
        
        JFrame frame = this;
        
        
        setSize(500, 500);

        // Uses label to display the image
        label = new JLabel();
        add(label);

        // Setup Choose File Function
        choose = new JFileChooser();
        choose.setCurrentDirectory(new File("."));

        // Set up the menubar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menu = new JMenu("File");
        menuBar.add(menu);

        open = new JMenuItem("Open");
        menu.add(open);

        open.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                // Creates File Open Menu
                int result = choose.showOpenDialog(null);

                // Used when selecting a picture on PC
                if (result == JFileChooser.APPROVE_OPTION) {
                    String name = choose.getSelectedFile().getPath();
                    label.setIcon(new ImageIcon(name));
                }
            }
        });


        ArrayList<Float> coordsX = new ArrayList<Float>();
        ArrayList<Float> coordsY = new ArrayList<Float>();

        addMouseMotionListener(this);

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                coordsX.add((float) e.getX()); //Storing coordinate X
                coordsY.add((float) e.getY()); //Storing coordinate Y

                //Add this code to draw a circle each time you click.
                int r = 6; //Radius of the circle/point.
                int x = e.getX() - (r / 2); //Position X (mouse will be in the center of the point)
                int y = e.getY() - (r / 2); //Position Y (mouse will be in the center of the point)

                Graphics g = getGraphics(); //Getting the Graphic object
                g.setColor(Color.red); //Setting color to red
                g.fillOval(x, y, r, r); //Drawing the circle/point
                g.dispose();

                JDialog dialog = new JDialog(frame, "Test Frame");

                JLabel dialogText = new JLabel("Annotation");
                JTextField textField = new JTextField(20);
                JButton submit = new JButton("Submit");
                
                dialog.setLayout(new FlowLayout());
                dialog.setSize(400, 100);
                
                submit.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        String text = textField.getText();
                        System.out.println(text + "\nx: " + x + "\ny: " + y);
                        dialog.dispose();
                    }
                });
            
                dialog.add(dialogText);
                dialog.add(textField);
                dialog.add(submit);

                dialog.setModal(true);
                dialog.setVisible(true);
                    

            }


            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
