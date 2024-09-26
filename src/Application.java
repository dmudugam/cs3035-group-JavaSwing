import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application extends JFrame implements ActionListener {
    private JButton helloButton;
    private JButton ticTacToeButton;

    public Application() {
        setTitle("Main Menu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        helloButton = new JButton("Show Hello World");
        ticTacToeButton = new JButton("Play Tic Tac Toe");

        helloButton.addActionListener(this);
        ticTacToeButton.addActionListener(this);

        add(helloButton);
        add(ticTacToeButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == helloButton) {
            JOptionPane.showMessageDialog(this, "Hello World");
        } else if (e.getSource() == ticTacToeButton) {
            new TicTacToe();
        }
    }

    public static void main(String[] args) {
        new Application();
    }
}

class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private boolean playerX = true;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        initializeButtons();

        setVisible(true);
    }

    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (!buttonClicked.getText().equals("")) {
            return;
        }

        buttonClicked.setText(playerX ? "X" : "O");
        playerX = !playerX;

        if (checkForWin()) {
            JOptionPane.showMessageDialog(this, "Player " + (playerX ? "O" : "X") + " wins!");
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "The game is a draw!");
            resetBoard();
        }
    }

    private boolean checkForWin() {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(buttons[row][1].getText()) &&
                buttons[row][1].getText().equals(buttons[row][2].getText()) &&
                !buttons[row][0].getText().equals("")) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(buttons[1][col].getText()) &&
                buttons[1][col].getText().equals(buttons[2][col].getText()) &&
                !buttons[0][col].getText().equals("")) {
                return true;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][2].getText()) &&
            !buttons[0][0].getText().equals("")) {
            return true;
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][0].getText()) &&
            !buttons[0][2].getText().equals("")) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        playerX = true;
    }
}