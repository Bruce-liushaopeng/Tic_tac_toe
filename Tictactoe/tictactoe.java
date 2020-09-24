import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
/**
 * Write a description of class tictactoe here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class tictactoe extends MouseAdapter implements ActionListener
{
    // instance variables - replace the example below with your own
    private JTextField counterDisplay;//display the current state of the game
    Boolean yesorno; //yes or no there's a winner
    
    private JButton b1;//all the buttons we need
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    private ArrayList<JButton> allButtons;
    private JButton nextround; //start next round, whitout reset score
    private JButton restart;   //reset the score and restart the game
    private int xscore;     //the x score
    private int oscore;     //the o score
    private JMenuItem reStart;//same as restart button
    private JMenuItem quitItem;     //quit the game JmnuItem
    private JTextArea history;      //JtextArea of the history 
    private String currentplayer;   //the currentplay "x" or "o"
    private String xp;          //the "x"
    private String op;          //the "o"
    private int numFreeSquares;
    private String winner;
    Boolean istie;              //yes if the game is tie
    /**
     * Constructor for objects of class tictactoe
     */
    public tictactoe()
    {
        // initialise instance variables
      xp="X";       
      op="O";
      istie=false;
      xscore=0;
      oscore=0;
      winner="tie"; 
      yesorno=false;
     
      numFreeSquares=8;
      
      
      currentplayer=xp;
      JFrame frame = new JFrame("Tictactoe");
      Container contentPane = frame.getContentPane(); 
      contentPane.setLayout(new BorderLayout()); // use border layout (default)
      
      JMenuBar menubar = new JMenuBar();
      frame.setJMenuBar(menubar); // add menu bar to our frame

      JMenu fileMenu = new JMenu("Options"); // create a menu
      menubar.add(fileMenu); // and add to our menu bar
      // we are going to listen to mouse actions on the fileMenue
      fileMenu.addMouseListener(this);

      reStart = new JMenuItem("New"); // create a menu item called "Reset"
      fileMenu.add(reStart); // and add to our menu

      quitItem = new JMenuItem("Quit"); // create a menu item called "Quit"
      fileMenu.add(quitItem); // and add to our menu
      
      // this allows us to use shortcuts (e.g. Ctrl-R and Ctrl-Q)
      final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(); // to save typing
      reStart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, SHORTCUT_MASK));
      quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
      reStart.addActionListener(this); 
      quitItem.addActionListener(new ActionListener() // create an anonymous inner class
        { // start of anonymous subclass of ActionListener
          // this allows us to put the code for this action here  
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0); // quit
            }
        } // end of anonymous subclass
      );
      
      //JLabel label = new JLabel("current player: ");
      //label.setHorizontalAlignment(JLabel.RIGHT); // right justified
      //contentPane.add(label,BorderLayout.WEST); 
      
       
      
      counterDisplay = new JTextField(20); // text field is 5 characters wide
      counterDisplay.setEditable(false); // we cannot edit this field
      counterDisplay.setFont(new Font(null, Font.BOLD, 15)); // bold 18pt font
      counterDisplay.setHorizontalAlignment(JTextField.CENTER); // right justified
      contentPane.add(counterDisplay, BorderLayout.CENTER);
      counterDisplay.setText("game in progress "+currentplayer+ " turn ");
      
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridLayout(4, 3));
      
      b1=new JButton(" ");
      
      b2=new JButton(" ");
      b3=new JButton(" ");
      b4=new JButton(" ");
      b5=new JButton(" ");
      b6=new JButton(" ");
      b7=new JButton(" ");
      b8=new JButton(" ");
      b9=new JButton(" ");
      nextround=new JButton("next round");
      restart= new JButton("restart");
      
      
      buttonPanel.add(b1);
      buttonPanel.add(b2);
      buttonPanel.add(b3);
      buttonPanel.add(b4);
      buttonPanel.add(b5);
      buttonPanel.add(b6);
      buttonPanel.add(b7);
      buttonPanel.add(b8);
      buttonPanel.add(b9);
      buttonPanel.add(nextround);
      buttonPanel.add(restart);
     
      contentPane.add(buttonPanel, BorderLayout.NORTH);
      
      b1.addActionListener(this); 
      b2.addActionListener(this); 
      b3.addActionListener(this); 
      b4.addActionListener(this); 
      b5.addActionListener(this); 
      b6.addActionListener(this); 
      b7.addActionListener(this); 
      b8.addActionListener(this); 
      b9.addActionListener(this); 
      nextround.addActionListener(this); 
      restart.addActionListener(this); 
      
      history = new JTextArea(10,15);
      JScrollPane pane = new JScrollPane(history); // put text area in a scroll pane
      contentPane.add(pane,BorderLayout.SOUTH); // south side
      history.append("welcome to tictactoe\n");
      
      history.setCaretPosition(history.getDocument().getLength());
      
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // exit when we hit the "X"
      frame.pack(); // pack everthing into our frame
      frame.setResizable(true); // we can resize it
      frame.setVisible(true);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void actionPerformed(ActionEvent e)
    {   Boolean functionalbutton;
        functionalbutton=false;
        Object o = e.getSource(); // get the action 
        
        // see if it's a JButton
        if (o instanceof JButton) {
            JButton button = (JButton)o;
            if(button == b1) {
                
                b1.setText(currentplayer);
                b1.setEnabled(false);
                
            }
            if(button == b2) {
                b2.setText(currentplayer);
                b2.setEnabled(false);
                
            }
            if(button == b3) {
                b3.setText(currentplayer);
                b3.setEnabled(false);
            }
            if(button == b4) {
                b4.setText(currentplayer);
                b4.setEnabled(false);
            }
            if(button == b5) {
                b5.setText(currentplayer);
                b5.setEnabled(false);
            }
            if(button == b6) {
                b6.setText(currentplayer);
                b6.setEnabled(false);
                
            }if(button == b7) {
                b7.setText(currentplayer);
                b7.setEnabled(false);
            }
            if(button == b8) {
                b8.setText(currentplayer);
                b8.setEnabled(false);
            }
            if(button == b9) {
                b9.setText(currentplayer);
                b9.setEnabled(false);
            }
            
            if(button==nextround)
            {
                history.append("new round started\n");
                
                this.nextR();
                currentplayer=op;
                    }
            if(button==restart)
            {
                history.setText("new game start\nScore has been reset\n");
                xscore=0;
                oscore=0;
                this.nextR();
                currentplayer=op;
                    }
                
            
            
            numFreeSquares--;
            if(winner.equals("tie"))
            {
                yesorno=this.haveWinner();
            }
        
            if(yesorno==true){
                if(winner==xp) xscore++;
                if(winner==op) oscore++;
                history.append(this.displayscore()+"\n");
                b1.setEnabled(false);
                b2.setEnabled(false);
                b3.setEnabled(false);
                b4.setEnabled(false);
                b5.setEnabled(false);
                b6.setEnabled(false);
                b7.setEnabled(false);
                b8.setEnabled(false);
                b9.setEnabled(false);
                counterDisplay.setText("The winner is  "+currentplayer+ "");
                return;
            }
            if(currentplayer.equals(op))
            {currentplayer= xp ;
            }
            else {
                currentplayer=op;
            }
            if(!istie)counterDisplay.setText("game in progress "+currentplayer+ " turn ");
        
            
            
            
        }
        else { // it's a JMenuItem
            
            JMenuItem item = (JMenuItem)o;
            
            if (item == reStart) { // reset
                history.setText("new game start\nScore has been reset\n");
                xscore=0;
                oscore=0;
                this.nextR();
                numFreeSquares=8;
                
            } //else if (item == quitItem) { // quit
               // System.exit(0);
           // }
               
        }
    }
    
    private boolean haveWinner() 
   {
       // unless at least 5 squares have been filled, we don't need to go any further
       // (the earliest we can have a winner is after player X's 3rd move).
       
       if (numFreeSquares>4) return false;
       //history.append("we are here\n");
       // Note: We don't need to check all rows, columns, and diagonals, only those
       // that contain the latest filled square.  We know that we have a winner 
       // if all 3 squares are the same, as they can't all be blank (as the latest
       // filled square is one of them).
       
       // check row "row"
       if ( b1.getText()!=" "&&b1.getText()==(b2.getText())&&b2.getText()==(b3.getText()) ) {
           history.append("\nThe winner is "+currentplayer+" for this round\n");
           winner=currentplayer;
           return true;
        }
        if (b4.getText()!=" "&& b4.getText().equals(b5.getText())&&b5.getText().equals(b6.getText()) ) {
           history.append("\nThe winner is "+currentplayer+" for this round\n");
            winner=currentplayer;
           return true;
        }
        if (b7.getText()!=" "&& b7.getText().equals(b8.getText())&&b8.getText().equals(b9.getText()) ) {
           history.append("\nThe winner is "+currentplayer+" for this round\n");
            winner=currentplayer;
           return true;
        }
        if ( b1.getText()!=" "&&b1.getText().equals(b4.getText())&&b4.getText().equals(b7.getText()) ) {
          history.append("\nThe winner is "+currentplayer+" for this round\n");
            winner=currentplayer;
           return true;
        }
        if ( b2.getText()!=" "&&b2.getText().equals(b5.getText())&&b5.getText().equals(b8.getText()) ) {
          history.append("\nThe winner is "+currentplayer+" for this round\n");
            winner=currentplayer;
           return true;
        }
        if (b3.getText()!=" "&& b3.getText().equals(b6.getText())&&b6.getText().equals(b9.getText()) ) {
           history.append("\nThe winner is "+currentplayer+" for this round\n");
            winner=currentplayer;
           return true;
        }
        if (b1.getText()!=" "&& b1.getText().equals(b5.getText())&&b5.getText().equals(b9.getText()) ) {
           history.append("\nThe winner is "+currentplayer+" for this round\n");
            winner=currentplayer;
           return true;
        }
        if ( b3.getText()!=" "&&b3.getText().equals(b5.getText())&&b5.getText().equals(b7.getText()) ) {
           history.append("\nThe winner is "+currentplayer+" for this round\n");
           winner=currentplayer;
           return true;
        }
        if(numFreeSquares<0)
        {
            counterDisplay.setText(" TIE no winner for this round ");
            
            history.append(this.displayscore()+"\n");
            istie=true;
       

       // no winner yet
       return false;
    }
    return false;
}
   
   private String displayscore()
   {
       return("Score  \n    x player: "+xscore+"   |   O player:  "+oscore+"\n");
    }
    private void nextR(){
        b1.setEnabled(true); b1.setText(" ");
                 b2.setEnabled(true); b2.setText(" ");
                  b3.setEnabled(true); b3.setText(" ");
                   b4.setEnabled(true); b4.setText(" ");
                    b5.setEnabled(true); b5.setText(" ");
                     b6.setEnabled(true); b6.setText(" ");
                      b7.setEnabled(true); b7.setText(" ");
                       b8.setEnabled(true); b8.setText(" ");
                        b9.setEnabled(true); b9.setText(" ");
                        
                        currentplayer=xp;
                        counterDisplay.setText("game in progress "+currentplayer+ " turn ");
                        numFreeSquares=9;
                        
                        winner="tie";
                        yesorno=false;
                        istie=false;
        
}
}
