import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        String[][] board = new String[6][7];

        //initialize array
        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[0].length; col++)
            {
                board[row][col] = "-";
            }
        }

        int turn = 1;
        String player = "X";
        boolean winner = false;

        //play a turn
        while (winner == false && turn <= 42)
        {
            boolean checker;
            int play=0;
            do {
                try{
                    printBoard(board);
                    System.out.print("Player " + player + ", choose a column: ");
                    play = scan.nextInt();
                }catch (Exception e) {
                    scan.next();
                    System.out.println("That is an invalid input! Please try again:");
                    scan.reset();
                }
                play = play-1;

                //check answer
                checker = check(play,board);

            }while (checker == false);

            //drop the checker
            for (int row = board.length-1; row >= 0; row--)
            {
                if(board[row][play].equals("-")){
                    board[row][play] = player;
                    break;
                }
            }

            //check if there is a winner
            winner = isWinner(player,board);
            if (player.equals("X")){
                player = "O";
            }else{
                player = "X";
            }

            turn++;
        }
        printBoard(board);

        if (winner)
        {
            if (player.equals("X")){
                System.out.println("O's won");
            }
            else{
                System.out.println("X's won!!! Congratulations Genius!");
            }
        }
        else
        {
            System.out.println("Tie game :(");
        }

    }

    public static void printBoard(String[][] board)
    {
        System.out.println("Current Board: ");
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                System.out.printf(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean check(int column, String[][] grid)
    {
        //valid column?
        if (column < 0 || column > grid[0].length)
        {
            return false;
        }

        //full column?
        if (!grid[0][column].equals("-"))
        {
            return false;
        }



        return true;
    }

    public static boolean isWinner(String player, String[][] board)
    {
        //check for 4 across
        for(int row = 0; row<board.length; row++)
        {
            for (int col = 0;col < board[0].length - 3;col++){

                if (board[row][col].equals(player)   &&
                        board[row][col+1].equals(player) &&
                        board[row][col+2].equals(player) &&
                        board[row][col+3].equals(player))
                {
                    return true;
                }
            }
        }
        //check for 4 up and down
        for(int row = 0; row < board.length - 3; row++)
        {
            for(int col = 0; col < board[0].length; col++)
            {
                if (board[row][col].equals(player)   &&
                        board[row+1][col].equals(player) &&
                        board[row+2][col].equals(player) &&
                        board[row+3][col].equals(player))
                {
                    return true;
                }
            }
        }
        //check upward diagonal
        for(int row = 3; row < board.length; row++)
        {
            for(int col = 0; col < board[0].length - 3; col++)
            {
                if (board[row][col].equals(player)   &&
                        board[row-1][col+1].equals(player) &&
                        board[row-2][col+2].equals(player) &&
                        board[row-3][col+3].equals(player))
                {
                    return true;
                }
            }
        }
        //check downward diagonal
        for(int row = 0; row < board.length - 3; row++)
        {
            for(int col = 0; col < board[0].length - 3; col++)
            {
                if (board[row][col].equals(player)   &&
                        board[row+1][col+1].equals(player) &&
                        board[row+2][col+2].equals(player) &&
                        board[row+3][col+3].equals(player))
                {
                    return true;
                }
            }
        }
        return false;
    }
}