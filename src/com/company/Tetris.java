package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import static com.company.Tetrominoes.*;

public class Tetris extends JFrame implements KeyListener {
    private static final int ROW = 20;
    private static final int COL = 10;
    private static final int SQUARE_SIZE = 20;
    private static int SCORE = 0;
    private static boolean GAME_OVER = false;
    private JPanel panel = new JPanel();
    private Piece p;
    private Timer timer = new Timer(500, e -> p.moveDown());
    private static final Color EMPTY = Color.WHITE;
    private static Color[][] board = new Color[ROW][COL];

    private Piece[] PIECES = {
            new Piece(new Tetrominoes(Z), Color.RED),
            new Piece(new Tetrominoes(S), Color.GREEN),
            new Piece(new Tetrominoes(T), Color.YELLOW),
            new Piece(new Tetrominoes(O), Color.BLUE),
            new Piece(new Tetrominoes(L), Color.PINK),
            new Piece(new Tetrominoes(I), Color.CYAN),
            new Piece(new Tetrominoes(J), Color.ORANGE),
    };

    private class Piece {
        Tetrominoes tetromino;
        Color color;
        int tetromino_index, x, y;
        int[][] currTetromino;

        private Piece(Tetrominoes tetromino, Color color) {
            this.tetromino = tetromino;
            this.color = color;

            this.tetromino_index = 0;
            this.currTetromino = this.tetromino.coords[tetromino_index];

            x = 3;
            y = -2;
        }

        void fill(Color color) {
            for (int row = 0; row < currTetromino.length ; row++)
                for (int col = 0; col < currTetromino.length ; col++)
                    if (currTetromino[row][col] == 1)
                        drawSquare(x + col, y + row, color);
        }

        void draw() {
            fill(this.color);
        }

        void unDraw() {
            fill(EMPTY);
        }

        boolean collision(int x, int y, int[][] piece) {
            for(int r = 0 ; r < piece.length ; r++)
                for(int c = 0 ; c < piece.length ; c++){
                    if(piece[r][c] == 0)
                        continue;

                    int newX = this.x + c + x;
                    int newY = this.y + r + y;

                    if(newX < 1 || newX >= COL || newY >= ROW)
                        return true;

                    if(newY < 0)
                        continue;

                    if(board[newY][newX] != EMPTY)
                        return true;
                }

            return false;
        }

        void lock() {
            for(int r = 0; r < currTetromino.length ; r++)
                for(int c = 0; c < currTetromino.length ; c++){
                    if(this.currTetromino[r][c] == 0)
                        continue;

                    if(this.y + r == 1){
                        GAME_OVER = true;
                        break;
                    }

                    board[this.y + r][this.x + c] = this.color;
                }

            for(int r = 0 ; r < ROW ; r++){
                boolean isRowFull = true;

                for(int c = 1 ; c < COL ; c++)
                    isRowFull = isRowFull && (board[r][c] != EMPTY);

                if(isRowFull){
                    for(int j = r ; j > 1 ; j--)
                        for(int c = 0 ; c < COL ; c++)
                            board[j][c] = board[j-1][c];

                    for(int c = 0 ; c < COL ; c++)
                        board[0][c] = EMPTY;

                    SCORE += 10;
                }
            }

            drawBoard();
        }

        void moveDown() {
            if (!collision(0, 1, currTetromino)) {
                unDraw();
                this.y++;
                draw();
            }

            else {
                lock();
                p = randomPiece();
            }
        }

        void moveRight() {
            if(!collision(1,0,this.currTetromino)){
                this.unDraw();
                this.x++;
                this.draw();
            }
        }

        void moveLeft() {
            if(!this.collision(-1,0,this.currTetromino)){
                this.unDraw();
                this.x--;
                this.draw();
            }
        }

        void rotate() {
            int[][] next = this.tetromino.coords[(tetromino_index + 1) % this.tetromino.coords.length];
            int shift = 0;

            if(this.collision(0, 0, next)){
                if (this.x > COL / 2) shift = -1;
                else shift = 1;
            }

            if(!this.collision(shift, 0, next)){
                this.unDraw();
                this.x += shift;
                this.tetromino_index = (this.tetromino_index + 1) % this.tetromino.coords.length;
                this.currTetromino = this.tetromino.coords[this.tetromino_index];
                this.draw();
            }

        }
    }

    private Tetris() {
        setTitle("TETRIS GAME");
        setSize(234, 458);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        panel.setBackground(EMPTY);
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);

        for(int i = 0 ; i < ROW ; i++)
            for(int j = 0 ; j < COL ; j++)
                board[i][j] = EMPTY;

        drawBoard();
        getContentPane().setBackground(EMPTY);
        p = randomPiece();
        timer.start();
        drop();
    }

    private void drawSquare(int x, int y, Color color) {
        Graphics g = panel.getGraphics();
        if (x >= 1 && y >= 1) {
            Graphics2D g2 = (Graphics2D) g;
            float thickness = 3f;
            Stroke oldStroke = g2.getStroke();
            g2.setStroke(new BasicStroke(thickness));
            g2.drawRect((x) * SQUARE_SIZE, (y) * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            g2.setStroke(oldStroke);
            g.setColor(color);
            g.fillRect((x) * SQUARE_SIZE, (y) * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }
    }

    private void drawBoard() {
        if (GAME_OVER) {
            timer.stop();
            String s = "Game Over...\nScore: " + SCORE;
            JOptionPane.showMessageDialog(this, s);
            System.exit(0);
        }

        for (int i = 0 ; i < ROW ; i++)
            for (int j = 0 ; j < COL ; j++)
                drawSquare(j, i, board[i][j]);
    }

    private Piece randomPiece() {
        Random random = new Random();
        int r = random.nextInt(PIECES.length); // 0 -> 6
        return new Piece(PIECES[r].tetromino, PIECES[r].color);
    }

    private void drop() {
        drawBoard();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
            p.moveLeft();

        else if (key == KeyEvent.VK_UP)
            p.rotate();

        else if (key == KeyEvent.VK_RIGHT)
            p.moveRight();

        else if (key == KeyEvent.VK_DOWN)
            p.moveDown();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        new Tetris();
    }
}
