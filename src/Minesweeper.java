// Minesweeper
// Brandon Joffe
// JFFBRA001
// 07/09/2013
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;

//class implements ActionListener and MouseListener interface and extends JFrame class
public class Minesweeper extends JFrame implements ActionListener, MouseListener
{

    //instances
    //////////////////////////
    private int width;
    private int length;
    private String values[][];
    //Other variable/objects
    ////////////////////////////////////
    JFrame frame = new JFrame("Minesweeper");
    JPanel grid = new JPanel();
    JPanel north = new JPanel();
    JLabel flagnum = new JLabel();
    JLabel smiley = new JLabel(new ImageIcon("smiley.PNG"));
    JButton[][] Buttons;
    int[][] numOfClicks;
    GridValues X = new GridValues(width, length);
    javax.swing.Timer timer;
    boolean first = true;
    int flags;
    int minesCovered = 0;
    int timeCounter = 0;
    //menu
    ////////////////////////////////
    JMenuBar menubar = new JMenuBar();
    JMenu file = new JMenu("Options");
    JMenuItem restart = new JMenuItem("Restart");
    JMenu diff = new JMenu("Difficulty");
    JMenuItem easy = new JMenuItem("Easy");
    JMenuItem med = new JMenuItem("Medium");
    JMenuItem hard = new JMenuItem("Hard");
    //timer
    JLabel Time = new JLabel("Time: 000");

    //constructer
    public Minesweeper(int length, int width)
    {
        //layout
        super();
        this.width = width;
        this.length = length;
        X = new GridValues(width, length);
        values = X.getGrid();


        setSize(width * 50, length * 50);
        frame.setBounds(0, 0, width * 70, length * 40);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //creates gridlayout for frame
        GridLayout layout = new GridLayout(length, width);

        flags = width;

        frame.setLayout(new BorderLayout());
        grid.setLayout(layout);
        north.setLayout(new BorderLayout());
        Buttons = new JButton[width][length];

        numOfClicks = new int[width][length];

        makeButtons();

        frame.add(grid, BorderLayout.CENTER);
        frame.add(north, BorderLayout.NORTH);
        flagnum = new JLabel("Flags: " + flags);
        //calls makeButtons method

        frame.setVisible(true);

        north.setBackground(Color.LIGHT_GRAY);
        north.add(flagnum, BorderLayout.EAST);
        //menu setup
        /////////////////      
        this.add(menubar);

        menubar.add(file);

        file.add(restart);
        file.add(diff);
        diff.add(easy);
        diff.add(med);
        diff.add(hard);

        file.addActionListener(this);
      
        restart.addActionListener(this);
        easy.addActionListener(this);
        med.addActionListener(this);
        hard.addActionListener(this);

        frame.setJMenuBar(menubar);

        //Timer
        north.add(Time, BorderLayout.WEST);
        Time.setForeground(Color.white);
        Time.setFont(new Font("DialogInput", Font.BOLD, 18));
        flagnum.setForeground(Color.white);
        flagnum.setFont(new Font("DialogInput", Font.BOLD, 18));

        // timer object to measure time

        timer = new javax.swing.Timer(1000, this);



    }

    public void makeButtons()
    {
        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < width; j++)
            {

                Buttons[i][j] = new JButton("");
                Buttons[i][j].addActionListener(this);
                Buttons[i][j].addMouseListener(this);
                //Buttons are added. numOfClicks is counter variable for each button
                numOfClicks[i][j] = 0;
                grid.add(Buttons[i][j]);
                Buttons[i][j].setFont(new Font("Arial", 1, 10));

            }
        }


    }

    public void emptyBlocks(int x, int y)
    {
        //makes sure x and y are within their boundries
        if ((x >= 0) && (x <= length - 1) && (y >= 0) && (y <= length - 1))
        {
            //sets the buttons to their corresponding numbers
            //gives the numbers a colour
            for (int z = 1; z < 9; z++)
            {
                if (values[x][y].equals(z + ""))
                {
                    Buttons[x][y].setText(z + "");
                    Buttons[x][y].setForeground(X.getColor(x, y));
                    //return; breaks from method
                    return;
                }
            }
            // if the button has a corresponding value of 0
            // and it is visible then continue.
            // sets the buttons visible to false and uses recursion to 
            // continue the process for all the buttons around.
            if (values[x][y].equals(0 + "") && Buttons[x][y].isVisible())
            {
                Buttons[x][y].setVisible(false);

                emptyBlocks(x - 1, y);
                emptyBlocks(x + 1, y);
                emptyBlocks(x, y - 1);
                emptyBlocks(x, y + 1);

            }
            else
            {
                return;
            }

        }

    }

    public void MineHit()
    {
        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if (values[i][j].equals("M"))
                {
                    if (numOfClicks[i][j] > 0)
                    {
                    }
                    else
                    {
                        Buttons[i][j].setIcon(new ImageIcon("mine.PNG"));
                    }


                }
                else
                {
                    Buttons[i][j].setEnabled(false);
                }
                Buttons[i][j].removeMouseListener(this);

            }
        }
        Time.setForeground(Color.red);
      
    }

    public void actionPerformed(ActionEvent e)
    {
        //checks for every button
        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < width; j++)
            {
                
                if (e.getSource() == Buttons[i][j])
                {
                    timer.start();
                    if (values[i][j].equals("0"))
                    {
                        
                        emptyBlocks(i, j);
                    }
                    else if (values[i][j].equals("M"))
                    {
                        MineHit();
                        timer.stop();
                    }
                    else
                    {
                       
                        Buttons[i][j].setForeground(X.getColor(i, j));
                        Buttons[i][j].setText(values[i][j]);
                    }

                   

                }
            }
        }
        if (e.getSource() == timer)
        {
            timeCounter++;

            if (timeCounter < 10)
            {
                Time.setText("Time: 00" + timeCounter);
            }
            else if (timeCounter < 100)
            {
                Time.setText("Time: 0" + timeCounter);
            }
            else
            {
                Time.setText("Time: " + timeCounter);
            }
        }
        if (e.getSource() == restart)
        {
            //closes frame but program continues to run
            // restarts game on same difficulty
            frame.dispose();
            new Minesweeper(width, length);
        }
        if (e.getSource() == easy)
        {
            frame.dispose();
            int w = 10;
            int l = 10;
            values = X.getGrid();
            new Minesweeper(w, l);
        }
        if (e.getSource() == med)
        {
            frame.dispose();
            int w = 15;
            int l = 15;
            // creates a bigger frame which is harder 
            new Minesweeper(w, l);
        }
        if (e.getSource() == hard)
        {
            frame.dispose();
            int w = 20;
            int l = 20;
            new Minesweeper(w, l);
        }

    }

    public void mouseClicked(MouseEvent ee)
    {
        int butt = ee.getButton();
        // each mouse button is related to a number. 3 is right click

        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if ((ee.getSource() == Buttons[i][j]) && (butt == 3))
                {
                    // if the button hasnt been pressed
                    if (numOfClicks[i][j] == 0)
                    {
                        // gives the button a flag, num of flags available decreases
                        // numOfClicks increases
                        // only if there are flags available
                        if (flags != 0)
                        {
                            Buttons[i][j].setIcon((new ImageIcon("flag.jpg")));
                            flags--;
                            numOfClicks[i][j]++;
                            flagnum.setText("Flags:  " + flags);
                        }
                        //if you flag a mine minesCovered increases
                        // if all mines are covered you win
                        if (values[i][j].equals("M"))
                        {
                            minesCovered++;
                            if (minesCovered == width)
                            {
                                timer.stop();
                                System.out.println("You Won");
                                //when player has won the flag Icons become stars
                                for (int k = 0; k < length; k++)
                                {
                                    for (int l = 0; l < width; l++)
                                    {
                                        if (values[k][l].equals("M"))
                                        {
                                            Buttons[k][l].setIcon((new ImageIcon("star.png")));
                                        }
                                    }

                                }
                            }
                        }
                    }
                    // if the button has a flag and is clicked again it gets a question mark
                    else if ((ee.getSource() == Buttons[i][j]) && (butt == 3) && (numOfClicks[i][j] == 1))
                    {
                        Buttons[i][j].setIcon(new ImageIcon("qm.jpg"));

                        numOfClicks[i][j]++;
                    }
                    // if button is clicked again give it no icon
                    // therefore num of flags decreases and if that
                    // flag was covering a mine, minesCovered decreases
                    else if ((ee.getSource() == Buttons[i][j]) && (butt == 3) && (numOfClicks[i][j] == 2))
                    {

                        Buttons[i][j].setIcon(null);
                        flags++;
                        numOfClicks[i][j] = 0;
                        flagnum.setText("Flags:  " + flags);
                        //starts from the beginning (No clicks)
                        if (values[i][j].equals("M"))
                        {
                            minesCovered--;
                        }
                    }
                    else
                    {
                        System.out.println("Error");

                    }
                }




            }
        }

    }
//Required methods from interface

    public void mousePressed(MouseEvent me)
    {
    }

    public void mouseReleased(MouseEvent me)
    {
    }

    public void mouseEntered(MouseEvent me)
    {
    }

    public void mouseExited(MouseEvent me)
    {
    }

    public static void main(String[] args)
    {
        int w = 10;
        int l = 10;
        new Minesweeper(w, l);
    }
}
