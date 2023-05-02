package cwk4;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Provide a GUI interface for the game
 *
 * @author Team 23
 * @version April 2023
 */
public class GameGUI
{
    private WIN gp = new SpaceWars("Horatio");
    private JFrame myFrame = new JFrame("Game GUI");

    private JTextArea listing = new JTextArea();
    private JLabel codeLabel = new JLabel ();
    private JButton fightBtn = new JButton("Fight");
    private JButton viewStateBtn = new JButton("View State");
    private JButton clearBtn = new JButton("Clear");

    private JPanel eastPanel = new JPanel();


    public GameGUI()
    {
        makeFrame();
        makeMenuBar(myFrame);
    }

    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        // create the Forces menu
        JMenu forcesMenu = new JMenu("Forces");
        menubar.add(forcesMenu);

        // create the Battles menu
        JMenu battlesMenu = new JMenu("Battles");
        menubar.add(battlesMenu);

        JMenuItem listForcesItem = new JMenuItem("List All Forces ");
        listForcesItem.addActionListener(new ListForcesHandler());
        forcesMenu.add(listForcesItem);

        JMenuItem listASFleetItem = new JMenuItem("List ASFleet ");
        listASFleetItem.addActionListener(new ListASFleetHandler());
        forcesMenu.add(listASFleetItem);

        // Add a menu item to activate a force
        JMenuItem activateForceItem = new JMenuItem("Activate Force");
        activateForceItem.addActionListener(new ActivateForceHandler());
        forcesMenu.add(activateForceItem);

        JMenuItem listBattlesItem = new JMenuItem("List All Battles ");
        listBattlesItem.addActionListener(new ListBattlesHandler());
        battlesMenu.add(listBattlesItem);
    }

    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {
        myFrame.setLayout(new BorderLayout());
        myFrame.add(listing,BorderLayout.CENTER);
        listing.setVisible(false);

        // set panel layout and add components
        eastPanel.setLayout(new GridLayout(4,1));
        fightBtn.addActionListener(new FightBtnHandler());
        eastPanel.add(fightBtn);

        viewStateBtn.addActionListener(new ViewStateBtnHandler());
        eastPanel.add(viewStateBtn);

        clearBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                listing.setText("");
            }
        });
        eastPanel.add(clearBtn);

        // show the buttons
        fightBtn.setVisible(true);
        viewStateBtn.setVisible(true);
        clearBtn.setVisible(true);

        // create the east panel and add buttons
        myFrame.add(eastPanel, BorderLayout.EAST);

        // building is done - arrange the components and show
        myFrame.pack();
        myFrame.setVisible(true);
    }

    private String fighting(int code)
    {
        switch (code)
        {
            case 0:return "Fight won";
            case 1:return "Fight lost as no suitable force available";
            case 2:return "Fight lost on battle strength, force destroyed";
            case 3:return "fight is lost and admiral completely defeated ";
        }
        return " no such fight ";
    }

    private class ListForcesHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String xx = gp.getAllForces();
            listing.setText(xx);

        }
    }

    private class ListASFleetHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String xx = gp.getASFleet();
            listing.setText(xx);

        }
    }

    private String activating(int code)
    {
        switch (code)
        {
            case 0:return "Activated";
            case 1:return "Not in the UFF dock or destroyed";
            case 2:return "Not enough money";
        }
        return " no such force ";
    }

    private class ActivateForceHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int result = -1;
            String inputValue = JOptionPane.showInputDialog("Force reference ?: ");
            result = gp.activateForce(inputValue);
            JOptionPane.showMessageDialog(myFrame,activating(result));
        }
    }


    private class ListBattlesHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String xx = gp.getAllBattles();
            listing.setText(xx);

        }
    }

    private class FightBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int result = -1;
            String inputValue = JOptionPane.showInputDialog("Fight number ?: ");
            int num = Integer.parseInt(inputValue);
            result = gp.doBattle(num);
            JOptionPane.showMessageDialog(myFrame,fighting(result));
        }
    }

    private class ViewStateBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String xx = gp.toString();
            listing.setText(xx);
        }
    }


    public static void main(String[] args)
    {
        new GameGUI();
    }
}
   
