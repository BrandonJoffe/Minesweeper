// Minesweeper
// Brandon Joffe
// JFFBRA001
// 07/09/2013
import java.awt.Color;


public class GridValues 
{
    //instance variables  
    private int width;
    private int length;
    private String[][] grid;
  
    //constructer
    public GridValues(int w, int l)
    {
        width = w;
        length = l;
        grid = new String[w][l];
        populateMines();
    }

    public void populateMines()
    {
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < length; j++)
            {
                // gives blank values to the array
                grid[i][j] = "";

            }
        }

        for (int i = 0; i < width; i++)
        {
            // sets the mines up randomly
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * width);
            if (grid[x][y].equals("M"))
            {
                i--;
            }
            else
            {
                grid[x][y] = "M";
                
            }

        }
        //calls setNumbers method 
        setNumbers();
    }
    //returns string [][] of values
    public String[][] getGrid()
    {
        return grid;
    }
 
    // gets the colour for each number
    public Color getColor(int i, int j)
    {
        if (grid[i][j].equals("1"))
        {
            return Color.BLUE;
        }
        else if (grid[i][j].equals("2"))
        {
            return Color.GREEN;
        }
        else if (grid[i][j].equals("3"))
        {
            return Color.RED;
        }
        else if (grid[i][j].equals("4"))
        {
            return Color.ORANGE;
        }
        else if (grid[i][j].equals("5"))
        {
            return Color.PINK;
        }
        else if (grid[i][j].equals("6"))
        {
            return Color.yellow;
        }
        else if (grid[i][j].equals("7"))
        {
            return Color.MAGENTA;
        }
        else
        {
            return Color.CYAN;
        }
    }
    
    public void setNumbers()
    {

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < length; j++)
            {
                // counter for number of mines touching block
                int count = 0;
                // makes sure block isnt a mine
                if (grid[i][j].equals("M"))
                {
                    // if block is a mine start again from the top
                    continue;
                }
                //different num of blocks need to be checked on the sides 
                // of the [][] sides are( i = 0, i = width-1 && || j = 0,j = width-1)
                else
                {
                    if (i == 0)
                    {
                        if (j == 0)
                        {
                            if (grid[i][j + 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i + 1][j + 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i + 1][j].equals("M"))
                            {
                                count++;
                            }
                        }
                        else if (j == (width-1))
                        {
                            if (grid[i][j - 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i + 1][j].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i + 1][j - 1].equals("M"))
                            {
                                count++;
                            }
                        }
                        else
                        {
                            if (grid[i][j + 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i + 1][j + 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i][j - 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i + 1][j].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i + 1][j - 1].equals("M"))
                            {
                                count++;
                            }
                        }


                    }
                    else if (i == (width-1))
                    {
                        if (j == 0)
                        {
                            if (grid[i][j + 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i - 1][j + 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i - 1][j].equals("M"))
                            {
                                count++;
                            }
                        }
                        else if (j == (width-1))
                        {
                            if (grid[i][j - 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i - 1][j].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i - 1][j - 1].equals("M"))
                            {
                                count++;
                            }
                        }
                        else
                        {
                            if (grid[i][j + 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i - 1][j + 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i][j - 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i - 1][j].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i - 1][j - 1].equals("M"))
                            {
                                count++;
                            }
                        }

                    }
                    else
                    {
                        if (j == 0)
                        {
                            if (grid[i][j + 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i - 1][j + 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i - 1][j].equals("M"))
                            {
                                count++;
                            }
                        }
                        else if (j == (width-1))
                        {
                            if (grid[i][j - 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i - 1][j].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i - 1][j - 1].equals("M"))
                            {
                                count++;
                            }
                        }
                        // if block is not on any of the sides
                        else
                        {
                            if (grid[i][j + 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i - 1][j + 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i][j - 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i - 1][j].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i - 1][j - 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i + 1][j].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i + 1][j - 1].equals("M"))
                            {
                                count++;
                            }
                            if (grid[i + 1][j + 1].equals("M"))
                            {
                                count++;
                            }
                        }
                    }
                    grid[i][j] = count + "";
                
                    

                }
            }
        }
    }
   
}
