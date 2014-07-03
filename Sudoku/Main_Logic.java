/*
ICS3U1
Sudoku - ISU Project
Written by : Shalin Upadhyay, Kalan Dowrich
Written for : Ms.Ganesan
Due : January 21, 2013
Submitted : January 21, 2013

This program is composed of several methods and will create the actual solvable puzzle.

Variable Dictionary :

Total_Blocks - A 2D Array which consists of 9 x 9 array of numbers ranging from (1-9) in varying orders
Row_F - Used to decide where numbers will be placed in rows
Row_S - Used to decide where numbers will be placed in rows
Col_F - Used to decide where numbers will be placed in columns
Col_S - Used to decide where numbers will be placed in columns
Grid_F - Changes the location of numbers depending on values of random numbers
Grid_S - Changes the location of numbers depending on values of random numbers
num - If num is a certain value then the entire puzzle is saved in save()
hold - Changes the value of numbers at certain locations
Hori_Block - Used to save the completed puzzle, and have it accessed by GUI_Main
Colu_Block - Used to save the completed puzzle, and have it accessed by GUI_Main
max - Creates a max value for main for loop to go up to, depending on the random value generated
Num_F - Used to decide the value of numbers
Num_S - Used to decide the value of numbers 
max2 - The max value of a for loop based on certain numbers
*/

import java.util.*;

public class Main_Logic
{
    Random Num = new Random ();         // Creates random numbers for both the rows and columns
    Random Random_Grid = new Random (); // Creates random numbers for the grid, later used by other variables
    Random Random_3Num = new Random (); // Creates random numbers for the placement of the numbers, lated used by the other variables
    Random Hori_RNum = new Random ();   // Creates random numbers for the placement of the numbers, lated used by the other variables

    int Total_Blocks[] [] = {{4, 3, 5, 8, 7, 6, 1, 2, 9}, {8, 7, 6, 2, 1, 9, 3, 4, 5}, {2, 1, 9, 4, 3, 5, 7, 8, 6}, {5, 2, 3, 6, 4, 7, 8, 9, 1}, {9, 8, 1, 5, 2, 3, 4, 6, 7}, {6, 4, 7, 9, 8, 1, 2, 5, 3}, {7, 5, 4, 1, 6, 8, 9, 3, 2}, {3, 9, 2, 7, 5, 4, 6, 1, 8}, {1, 6, 8, 3, 9, 2, 5, 7, 4}};
    int Row_F;
    int Row_S;
    int Col_F;
    int Col_S;
    int Grid_F;
    int Grid_S;
    int num = 0;
    int hold[] = new int [9];
    int Hori_Block[] [] = new int [9] [9];
    int Colu_Block[] [] = new int [9] [9];

    int[] [] Num_Creator ()
        // Creates the entire puzzle according to the random numbers that have been made
        // This method is saved into save() method which is then accessed by GUI_Main
    {
        int max = 10 + Num.nextInt (10); //.nextInt(10) sets the max value of the random number less than 10 (0-9)

        for (int i = 0 ; i < max ; i++)
        {
            for (int j = 0 ; j < 3 ; j++)
            {
                if (j == 0)
                {
                    Row_F = Num.nextInt (3);
                    Row_S = Num.nextInt (3);
                }

                else if (j == 1)
                {
                    Row_F = 3 + Num.nextInt (3);
                    Row_S = 3 + Num.nextInt (3);
                }

                else if (j == 2)
                {
                    Row_F = 6 + Num.nextInt (3);
                    Row_S = 6 + Num.nextInt (3);
                }

                for (int count3 = 0 ; count3 < 9 ; count3++)
                {
                    hold [count3] = Total_Blocks [Row_F] [count3];
                    Total_Blocks [Row_F] [count3] = Total_Blocks [Row_S] [count3];
                    Total_Blocks [Row_S] [count3] = hold [count3];
                }
            }

            for (int j = 0 ; j < 3 ; j++)
            {
                if (j == 0)
                {
                    Col_F = Num.nextInt (3);
                    Col_S = Num.nextInt (3);
                }

                else if (j == 1)
                {
                    Col_F = 3 + Num.nextInt (3);
                    Col_S = 3 + Num.nextInt (3);
                }

                else if (j == 2)
                {
                    Col_F = 6 + Num.nextInt (3);
                    Col_S = 6 + Num.nextInt (3);
                }

                for (int k = 0 ; k < 9 ; k++)
                {
                    hold [k] = Total_Blocks [k] [Col_F];
                    Total_Blocks [k] [Col_F] = Total_Blocks [k] [Col_S];
                    Total_Blocks [k] [Col_S] = hold [k];
                }
            }
        }

        Grid_F = 1 + Random_Grid.nextInt (3);
        Grid_S = 1 + Random_Grid.nextInt (3);

        if ((Grid_F == 1 && Grid_S == 2) || (Grid_F == 2 && Grid_S == 1))
        {
            for (int i = 0 ; i < 3 ; i++)
            {
                for (int j = 0 ; j < 9 ; j++)
                {
                    hold [j] = Total_Blocks [i] [j];
                    Total_Blocks [i] [j] = Total_Blocks [i + 3] [j];
                    Total_Blocks [i + 3] [j] = hold [j];
                }
            }
        }
        else if ((Grid_F == 2 && Grid_S == 3) || (Grid_F == 3 && Grid_S == 2))
        {
            for (int i = 3 ; i < 6 ; i++)
            {
                for (int j = 0 ; j < 9 ; j++)
                {
                    hold [j] = Total_Blocks [i] [j];
                    Total_Blocks [i] [j] = Total_Blocks [i + 3] [j];
                    Total_Blocks [i + 3] [j] = hold [j];
                }
            }
        }
        else if ((Grid_F == 1 && Grid_S == 3) || (Grid_F == 3 && Grid_S == 1))
        {
            for (int i = 0 ; i < 3 ; i++)
            {
                for (int j = 0 ; j < 9 ; j++)
                {
                    hold [j] = Total_Blocks [i] [j];
                    Total_Blocks [i] [j] = Total_Blocks [i + 6] [j];
                    Total_Blocks [i + 6] [j] = hold [j];
                }
            }
        }

        int Num_F, Num_S, max2;

        max2 = 3 + Num.nextInt (6);

        for (int count5 = 0 ; count5 < max2 ; count5++)
        {
            Num_F = 1 + Random_3Num.nextInt (9);
            Num_S = 1 + Random_3Num.nextInt (9);

            for (int i = 0 ; i < 9 ; i++)
            {
                for (int j = 0 ; j < 9 ; j++)
                {
                    if (Total_Blocks [i] [j] == Num_F)
                    {
                        Total_Blocks [i] [j] = Num_S;
                        continue;
                    }

                    if (Total_Blocks [i] [j] == Num_S)
                        Total_Blocks [i] [j] = Num_F;
                }
            }
        }
        return Total_Blocks;
    }


    int[] [] save ()
        // Saves the new grid created, which is playable by the user
        // It is then extended to by GUI_Main
    {
        if (num == 0)
        {
            Colu_Block = Num_Creator ();
        }

        num = 1;

        return Colu_Block;
    }


    int[] [] hide ()
        // Makes only certain numbers visible by the user
        // The others will be the ones the user has to input
    {
        for (int i = 0 ; i < 9 ; i++)
        {
            for (int j = 0 ; j < 9 ; j++)
            {
                Hori_Block [i] [j] = Colu_Block [i] [j];
            }
        }

        int row, column, hidingnum;

        hidingnum = 50 + Num.nextInt (10);

        for (int i = 0 ; i < hidingnum ; i++)
        {
            row = Hori_RNum.nextInt (9);
            column = Hori_RNum.nextInt (9);
            Hori_Block [row] [column] = 0;
        }
        return Hori_Block;
    }
}
