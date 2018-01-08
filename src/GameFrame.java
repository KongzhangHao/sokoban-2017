import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sun.javafx.collections.MappingChange.Map;

/**
 * @brief The starting frame of the game. Handles the displayment of all elements.
 * @author hao
 * @file GameFrame.java
 * @date 28/12/2017 hao: Created GameFrame.java.
 * 						 Set the display methods for the game.
 */
public class GameFrame extends JFrame {

	private GameEngine game; /**< the game engine */
	private Object[] possibleValues = new Object[50]; /**< possible values for selecting a level*/
	JPanel jp;

	/**
	 * @brief Constructor, displays the frame of the game when the project is run
	 */
	public GameFrame() {
		
		/** Set the title, bounds and basic configurations of the game frame */
		this.setTitle("SOKOBAN2017");
		this.setBounds(200, 100, 800, 620);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		/** fill in the level names which are choosable by the user */
		for (int i = 0; i < 15; i++) {
			possibleValues[i] = "Level " + (i + 1);
		}
		
		/** display the background image from the image file */
		jp = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img=new ImageIcon(GameFrame.class.getClassLoader().getResource("imgs/dye_or_die.png"));
				g.drawImage(img.getImage(), 0, 0, null);
			}
		};
		this.add(jp);
		jp.setLayout(null);
		
		/** display the four buttons onto the starting page */
		/** button 1 Single Player Mode */
		JButton jb1 = new JButton("");
		jp.add(jb1);
		jb1.setBorderPainted(false);
		jb1.setFocusPainted(false);
		jb1.setContentAreaFilled(false);
		jb1.setBounds(21, 529, 198, 48);
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game = new GameEngine();
				startGame();
				requestFocus();
			}
		});
		
		/** button 2 Multi Player Mode */
		JButton jb4 = new JButton("");
		jp.add(jb4);
		jb4.setBorderPainted(false);
		jb4.setFocusPainted(false);
		jb4.setContentAreaFilled(false);
		jb4.setBounds(232, 529, 197, 48);
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//game = new MultiPlayerGameEngine();
				game = new MonsterGameEngineWithSkill();
				startGame();
				requestFocus();
			}
		});

		/** button 3 Game Instruction */
		/** Show instruction of the game */
		JButton jb2 = new JButton("");
		jp.add(jb2);
		jb2.setBorderPainted(false);
		jb2.setFocusPainted(false);
		jb2.setContentAreaFilled(false);
		jb2.setBounds(453, 529, 151, 48);
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				/** Instruction Message */
				JOptionPane.showMessageDialog(null, "The goal is to dye all slimes."
						+ "\r\nYou can control hero or heroine to complete the task."
						+ "\r\nYou should move bottles and put each of them on one slime"
						+ "\r\nto make them red.Then they will become friendly and show"
						+ "\r\nyou the stairs."
						+ "\r\nIf you are powerful enough, you can also use your skill."
                        + "\r\nEach skill can be used for 3 times."
						+ "\r\n"
						+ "\r\nHero:"
						+ "\r\nBerserker"
						+ "\r\nuses UP, DOWN, LEFT and RIGHT arrow keys to move"
						+ "\r\nskills:"
						+ "\r\n		1.Chop: uses his sword to kill a slime"
						+ "\r\n		2.Pulverize: smash the wall before him"
						+ "\r\n		3.Seismic Crash: uses dark power destroys "
						+ "\r\n		everything around him"
						+ "\r\nHeroine:"
						+ "\r\nElemental Bomber"
						+ "\r\nuses W, A, S and D to move"
						+ "\r\nskills:"
						+ "\r\n		1.Flame Circle: uses fire, burns 3 items in the"
						+ "\r\n		first line in front of her "
						+ "\r\n		2.Water Cannon: uses water, pushes 3 items in the "
						+ "\r\n		second line in front of her away"
						+ "\r\n		3.Darkness Mantle: uses the dark power, destroys 3"
						+ "\r\n		items in the third line in front of her way"
						, "Game Instruction",
						JOptionPane.INFORMATION_MESSAGE);
				
				requestFocus();
			}
		});

		/** button 4 Exit the game and close the frame */
		JButton jb3 = new JButton("");
		jp.add(jb3);
		jb3.setBorderPainted(false);
		jb3.setFocusPainted(false);
		jb3.setContentAreaFilled(false);
		jb3.setBounds(630, 529, 157, 48);
		jb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				setVisible(false);
				requestFocus();
			}
		});
		this.requestFocus();

		this.addKeyListener(new MyKeyListener());
	
	}
	
	/**
	 * @brief Display the game scene after the player has entered the game
	 */
	public void startGame() {
		
		repaint();
		this.remove(jp);
		this.add(game);
		//add background image 
		JPanel jp1 = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img = new ImageIcon(GameFrame.class.getClassLoader().getResource("imgs/bar.png"));
				g.drawImage(img.getImage(), 0, 0, null);
			}
		};
		jp1.setBounds(600, 0, 200, 600);
		jp1.setLayout(null);
		this.add(jp1);
		
		//add information of hero, including icon, skills images and the keys for skills
		//icon
		ImageIcon img1=new ImageIcon(GameFrame.class.getClassLoader().getResource("imgs/player1.gif"));
		JLabel picLabel = new JLabel(img1);
		picLabel.setBounds(10,10,110,110);
		jp1.add(picLabel);
		//skill images
		img1=new ImageIcon(GameFrame.class.getClassLoader().getResource("imgs/skill1.gif"));
		picLabel = new JLabel(img1);
		picLabel.setBounds(140,10,30,30);
		jp1.add(picLabel);
		img1=new ImageIcon(GameFrame.class.getClassLoader().getResource("imgs/skill2.gif"));
		picLabel = new JLabel(img1);
		picLabel.setBounds(140,50,30,30);
		jp1.add(picLabel);
		img1=new ImageIcon(GameFrame.class.getClassLoader().getResource("imgs/skill3.gif"));
		picLabel = new JLabel(img1);
		picLabel.setBounds(140,90,30,30);
		jp1.add(picLabel);
		//instruction of keys
		JLabel lab=new JLabel("J");
		lab.setFont(new Font("DOG", Font.BOLD, 15));
		lab.setForeground(Color.orange);
		lab.setBounds(180,10,20,30);
		jp1.add(lab);
		lab=new JLabel("K");
		lab.setFont(new Font("DOG", Font.BOLD, 15));
		lab.setForeground(Color.green);
		lab.setBounds(180,50,20,30);
		jp1.add(lab);
		lab=new JLabel("L");
		lab.setFont(new Font("DOG", Font.BOLD, 15));
		lab.setForeground(Color.magenta);
		lab.setBounds(180,90,20,30);
		jp1.add(lab);
		/*
		//if heroine exists, also show the information like hero
		if(game.isWomanExist()==true) {
			//icon
			img1=new ImageIcon(GameFrame.class.getClassLoader().getResource("imgs/player2.gif"));
			picLabel = new JLabel(img1);
			picLabel.setBounds(10,150,110,110);
			jp1.add(picLabel);
			//skill images
			img1=new ImageIcon(GameFrame.class.getClassLoader().getResource("imgs/skill4.gif"));
			picLabel = new JLabel(img1);
			picLabel.setBounds(140,150,30,30);
			jp1.add(picLabel);
			img1=new ImageIcon(GameFrame.class.getClassLoader().getResource("imgs/skill5.gif"));
			picLabel = new JLabel(img1);
			picLabel.setBounds(140,190,30,30);
			jp1.add(picLabel);
			img1=new ImageIcon(GameFrame.class.getClassLoader().getResource("imgs/skill6.gif"));
			picLabel = new JLabel(img1);
			picLabel.setBounds(140,230,30,30);
			jp1.add(picLabel);
			//instruction of keys
			lab=new JLabel("Z");
			lab.setFont(new Font("DOG", Font.BOLD, 15));
			lab.setForeground(Color.red);
			lab.setBounds(180,150,20,30);
			jp1.add(lab);
			lab=new JLabel("X");
			lab.setFont(new Font("DOG", Font.BOLD, 15));
			lab.setForeground(Color.cyan);
			lab.setBounds(180,190,20,30);
			jp1.add(lab);
			lab=new JLabel("C");
			lab.setFont(new Font("DOG", Font.BOLD, 15));
			lab.setForeground(Color.yellow);
			lab.setBounds(180,230,20,30);
			jp1.add(lab);
		}*/
		//set order buttons, including change levels, restart and exit
		//last step button
		JButton but1=new JButton("");
		but1.setBorderPainted(false);
		but1.setFocusPainted(false);
		but1.setContentAreaFilled(false);
		jp1.add(but1);
		but1.setBounds(46, 295, 113, 25);
		but1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.getStatusBar().back();
				requestFocus();
			}
		});
		//previous level button
		JButton but2=new JButton("");
		but2.setBorderPainted(false);
		but2.setFocusPainted(false);
		but2.setContentAreaFilled(false);
		jp1.add(but2);
		but2.setBounds(46, 343, 113, 25);
		but2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.getStatusBar().goBack();
				requestFocus();
			}
		});
		//next level button
		JButton but3=new JButton("");
		but3.setBorderPainted(false);
		but3.setFocusPainted(false);
		but3.setContentAreaFilled(false);
		jp1.add(but3);
		but3.setBounds(46, 392, 113, 25);
		but3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.getStatusBar().goNext();
				requestFocus();
			}
		});
		//choose level button
		//show a small page for choosing levels 1~15
		JButton but4=new JButton("");
		but4.setBorderPainted(false);
		but4.setFocusPainted(false);
		but4.setContentAreaFilled(false);
		jp1.add(but4);
		but4.setBounds(46, 438, 113, 25);
		but4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object selectedValue = JOptionPane.showInputDialog(null,
						"Level Choose", "Jump to the Level", JOptionPane.INFORMATION_MESSAGE, null,
						possibleValues, possibleValues[0]);
				if(selectedValue != null) {
					for (int i = 0; i < 15; i++) {
						if (selectedValue.toString().equals(possibleValues[i].toString())) {
							game.getStatusBar().choice(i + 1);
							break;
						}
					}
				}
				requestFocus();
			}
		});
		//restart button
		JButton but5=new JButton("");
		but5.setBorderPainted(false);
		but5.setFocusPainted(false);
		but5.setContentAreaFilled(false);
		jp1.add(but5);
		but5.setBounds(46, 486, 113, 25);
		but5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.getStatusBar().reStart();
				requestFocus();
			}
		});
		//exit button
		JButton but6=new JButton("");
		but6.setBorderPainted(false);
		but6.setFocusPainted(false);
		but6.setContentAreaFilled(false);
		jp1.add(but6);
		but6.setBounds(46, 531, 113, 25);
		but6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				setVisible(false);
				requestFocus();
			}
		});
	}

	/**
	 * @brief this method is a auto listener which connect the key press and the action method together
	 */
	class MyKeyListener extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			game.keyReleased(e);
		}
	}

	/**
	 * @brief main method, create game frame
	 */
	public static void main(String[] args) {
		new GameFrame();
	}

}
