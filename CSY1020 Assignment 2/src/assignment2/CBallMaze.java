package assignment2;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.TargetDataLine;
import javax.swing.*;

import java.awt.event.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class CBallMaze extends JFrame implements ActionListener{

		//Panels
		private JPanel jPMain, jPBottom, jPRight;
		//Right panels
		private JPanel jPRight1, jPRight2, jPRight3, jPRight4, jPRight5;
		
		//Options buttons
		private JButton jBOption1, jBOption2, jBOption3, jBExit;
		
		//Actions buttons
		private JButton jBAct, jBRun, jBReset;
		
		//Moving buttons
		private JButton jBForward, jBBackwards, jBUp, jBDown, blank1, blank2, blank3, blank4, blank5;
		
		//Timer
		private Timer timer;
		private int seconds = 0, minutes = 0, hours = 0;
		//Timers Labels
		private JLabel jLTimer, jLTimer1, jLTimer2; //jLTimer1 and 2 for ':'
		//Timers TextFields
		private JTextField jTHours, jTMinutes, jTSeconds;
		
		//Labels
		private JLabel jLOption, jLSquare, jLDirection;
		//TextFields
		private JTextField jTOption, jTSquare, jTDirection;
		
		//Images
		private ImageIcon jRImage, jIAct, jIRun, jIReset;
		private JLabel img;
		private ImageIcon sand = new ImageIcon(this.getClass().getResource("/sand.jpg"));
		private ImageIcon sandstone = new ImageIcon(this.getClass().getResource("/sandstone.jpg"));
		private ImageIcon ballSand = new ImageIcon(this.getClass().getResource("/sand40x40.jpg"));
		private ImageIcon win = new ImageIcon(this.getClass().getResource("/win.jpg"));
		
		//Slider
		private JSlider slider;
		
		//Main Panel Images
		private JButton[] blocks = new JButton[208];
		private int ballPosition = 15;   

		public static void main(String[] args) {
			//Menu
			JMenuBar menuBar = new JMenuBar();
			JMenu jMScenario, jMEdit, jMControls, jMHelp;
			jMScenario = new JMenu("Scenario");
			menuBar.add(jMScenario);
			jMEdit = new JMenu("Edit");
			menuBar.add(jMEdit);
			jMControls = new JMenu("Controls");
			menuBar.add(jMControls);
			jMHelp = new JMenu("Help");
			menuBar.add(jMHelp);
			
			CBallMaze frame = new CBallMaze();
			frame.setSize(775, 650);
			frame.setJMenuBar(menuBar);
			frame.setTitle("CBallMaze - Ball Maze Application");
			frame.setResizable(false);
			ImageIcon frameIcon = new ImageIcon("/greenfoot.png");
			frame.setIconImage(frameIcon.getImage());
			frame.createGUI();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
		}
		
		private void createGUI(){
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			Container window = getContentPane();
			window.setLayout(new FlowLayout());
			
			//Main Panel
			jPMain = new JPanel();
			jPMain.setLayout(new GridLayout(13,16));
			jPMain.setPreferredSize(new Dimension(560,530));
			window.add(jPMain);
			
			//Right panel
			jPRight = new JPanel();
			jPRight.setPreferredSize(new Dimension(170,530));
			
			window.add(jPRight);
			
			//Bottom panel
			jPBottom = new JPanel();
			jPBottom.setPreferredSize(new Dimension(775,51));
			//jPBottom.setBackground(Color.gray);
			window.add(jPBottom);
			
			//Right panels
			jPRight1 = new JPanel();
			jPRight1.setLayout(new GridLayout(3,2));
			jPRight1.setPreferredSize(new Dimension(170,110));
			//jPRight1.setBackground(Color.red);
			jPRight.add(jPRight1);
			
			jPRight2 = new JPanel();
			jPRight2.setPreferredSize(new Dimension(170,70));
			//jPRight2.setBackground(Color.red);
			jPRight.add(jPRight2);
			
			jPRight3 = new JPanel();
			jPRight3.setLayout(new GridLayout(3,3));
			jPRight3.setPreferredSize(new Dimension(170,120));
			//jPRight3.setBackground(Color.red);
			jPRight.add(jPRight3);
			
			jPRight4 = new JPanel();
			jPRight4.setLayout(new GridLayout(2,2));
			jPRight4.setPreferredSize(new Dimension(170,110));
			//jPRight4.setBackground(Color.red);
			jPRight.add(jPRight4);
			
			jPRight5 = new JPanel();
			jPRight5.setPreferredSize(new Dimension(170,110));
			//jPRight5.setBackground(Color.red);
			jPRight.add(jPRight5);
			
			//Right Panel 1 Labels and TextFields
			jLOption = new JLabel(" Option:");
			jPRight1.add(jLOption);
			jTOption = new JTextField("1");
			jTOption.setHorizontalAlignment(jTOption.CENTER);
			jTOption.setBackground(Color.white);
			jTOption.setEditable(false);
			jPRight1.add(jTOption);
			
			jLSquare = new JLabel(" Square:");
			jPRight1.add(jLSquare);
			jTSquare = new JTextField("15");
			jTSquare.setHorizontalAlignment(jTSquare.CENTER);
			jTSquare.setBackground(Color.white);
			jTSquare.setEditable(false);
			jPRight1.add(jTSquare);
			
			jLDirection = new JLabel(" Direction:");
			jPRight1.add(jLDirection);
			jTDirection = new JTextField("W");
			jTDirection.setHorizontalAlignment(jTDirection.CENTER);
			jTDirection.setBackground(Color.white);
			jTDirection.setEditable(false);
			jPRight1.add(jTDirection);
			
			
			//Right Panel 2 - Timer
			jLTimer = new JLabel("          DIGITAL TIMER           ");
			jPRight2.add(jLTimer);
			
			jTHours = new JTextField("00",2);
			jTHours.setEditable(false);
			jTHours.setBackground(Color.gray);
			jPRight2.add(jTHours);
			
			jLTimer1 = new JLabel(":");
			jPRight2.add(jLTimer1);
			
			jTMinutes = new JTextField("00",2);
			jTMinutes.setEditable(false);
			jTMinutes.setBackground(Color.gray);
			jPRight2.add(jTMinutes);
			
			jLTimer2 = new JLabel(":");
			jPRight2.add(jLTimer2);
			
			jTSeconds = new JTextField("00",2);
			jTSeconds.setEditable(false);
			jTSeconds.setBackground(Color.gray);
			jPRight2.add(jTSeconds);
			
			
			//Right Panel 3 - Move buttons
			blank1 = new JButton();
			blank1.setBorder(BorderFactory.createLoweredBevelBorder());
			blank1.setEnabled(false);
			jPRight3.add(blank1);
			jBUp = new JButton("^");
			jBUp.addActionListener(this);
			jPRight3.add(jBUp);
			blank2 = new JButton();
			blank2.setBorder(BorderFactory.createLoweredBevelBorder());
			blank2.setEnabled(false);
			jPRight3.add(blank2);
			
			jBForward = new JButton("<");
			jBForward.addActionListener(this);
			jPRight3.add(jBForward);
			blank3 = new JButton();
			blank3.setBorder(BorderFactory.createLoweredBevelBorder());
			blank3.setEnabled(false);
			jPRight3.add(blank3);
			jBBackwards = new JButton(">");
			jBBackwards.addActionListener(this);
			jPRight3.add(jBBackwards);
			
			blank4 = new JButton();
			blank4.setBorder(BorderFactory.createLoweredBevelBorder());
			blank4.setEnabled(false);
			jPRight3.add(blank4);
			jBDown = new JButton("v");
			jBDown.addActionListener(this);
			jPRight3.add(jBDown);
			blank5 = new JButton();
			blank5.setBorder(BorderFactory.createLoweredBevelBorder());
			blank5.setEnabled(false);
			jPRight3.add(blank5);
			
			
			//Right Panel 4 - Options buttons
			jBOption1 = new JButton("Option 1");
			jBOption1.addActionListener(this);
			jPRight4.add(jBOption1);
			
			jBOption2 = new JButton("Option 2");
			jBOption2.addActionListener(this);
			jPRight4.add(jBOption2);
			
			jBOption3 = new JButton("Option 3");
			jBOption3.addActionListener(this);
			jPRight4.add(jBOption3);
			
			jBExit = new JButton("Exit");
			jBExit.addActionListener(this);
			jPRight4.add(jBExit);
			
			
			//Right Panel 5 - Direction Image
			jRImage = new ImageIcon(this.getClass().getResource("/west.jpg"));
			img = new JLabel(jRImage);
			jPRight5.add(img);
			
			
			//Bottom Panel - Action Buttons
			jIAct = new ImageIcon(this.getClass().getResource("/step.png"));
			jBAct = new JButton("Act");
			jBAct.setIcon(jIAct);
			jBAct.addActionListener(this);
			jPBottom.add(jBAct);
			
			jIRun = new ImageIcon(this.getClass().getResource("/run.png"));
			jBRun = new JButton("Run");
			jBRun.setIcon(jIRun);
			jBRun.addActionListener(this);
			jPBottom.add(jBRun);
			
			jIReset = new ImageIcon(this.getClass().getResource("/reset.png"));
			jBReset = new JButton("Reset");
			jBReset.setIcon(jIReset);
			jBReset.addActionListener(this);
			jPBottom.add(jBReset);
			
			//Bottom Panel - Slider
			JLabel speed = new JLabel("                                                     Speed:");
			jPBottom.add(speed);
			
			slider = new JSlider(JSlider.HORIZONTAL,0, 1000, 500);
			slider.setMajorTickSpacing(250);
			slider.setPaintTicks(true);
			jPBottom.add(slider);
			
			//Main Panel
			for(int position = 0; position < 208; position++) {
				blocks[position] = new JButton();
				jPMain.add(blocks[position]);
				//all the sand blocks
				if(position >= 0 && position < 16 || position >= 48 && position < 64 || position >= 96 && position <112 || position >= 144 && position < 160 || position >=193 && position < 208 || position == 17 || position == 21 || position == 25 || position == 33 || position == 37 || position == 41 || position == 66 || position == 70 || position == 75 || position == 82 || position == 86 || position == 91 || position == 113 || position == 117 || position == 124 || position == 129 || position == 133 || position == 140 || position == 162 || position == 166 || position == 178 || position == 182) {
					blocks[position].setIcon(sand);
					Color brown = new Color(210,180,140);
					blocks[position].setBorder(BorderFactory.createLineBorder(brown));
				}
				else
					if(position == 192){ //win block
						blocks[position].setIcon(sandstone);
						blocks[position].setBorder(null);
					}
					else //white blocks
					{
						blocks[position].setIcon(new ImageIcon(this.getClass().getResource("/white32x32.jpg")));
						blocks[position].setBorder(BorderFactory.createLineBorder(Color.white,10));
					}
			}
						
			blocks[15].setIcon(ballSand);			
		}


		public void actionPerformed(ActionEvent event) {
			Object jBAction = event.getSource();
					
			//On click changes
			if(jBAction == jBForward) {
				move(jBForward);
			}
			else
			if(jBAction == jBBackwards) {
				move(jBBackwards);
			}
			else
			if(jBAction == jBUp) {
				move(jBUp);
			}
			else
			if(jBAction == jBDown) {
				move(jBDown);
			}
			if(jBAction == jBOption1) {
				jTOption.setText("1");
			}
			else
			if(jBAction == jBOption2) {
				jTOption.setText("2");
			}
			else
			if(jBAction == jBOption3) {
				jTOption.setText("3");
			}
			else
			if(jBAction == jBExit) {
				System.exit(0);
			}
			else
			if(jBAction == jBAct) {
				moveBall();
			}
			else
			if(jBAction == jBRun) {
				//jBAct.setEnabled(false);
				blocks[ballPosition].setIcon(sand);
				ballPosition = 15;
				jTSquare.setText(""+ballPosition);
				blocks[ballPosition].setIcon(ballSand);
				int speed = slider.getValue();
				timer = new Timer(speed, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						jTSeconds.setText(seconds+"");
						jTMinutes.setText(minutes+"");
						jTHours.setText(hours+"");
						
						seconds++;
						if(seconds == 60) {
							minutes++;
							seconds=0;
						}
						else
							if(minutes == 60) {
								hours++;
								minutes=0;
							}
						
						if(ballPosition == 192) {
							timer.stop();
						}
						
						jBAct.doClick();
						
					}
				});
				timer.start();
				
				
				
			}
			else
			if(jBAction == jBReset) {
				if(timer != null) {
					//Right Panel reset
					jTOption.setText("1");
					jTSquare.setText("15");
					jTDirection.setText("W");
					
					timer.stop();
					seconds =0;
					minutes=0;
					hours=0;
					jTSeconds.setText("00");
					jTMinutes.setText("00");
					jTHours.setText("00");
					
					jRImage = new ImageIcon(this.getClass().getResource("/west.jpg"));
					img.setIcon(jRImage);
					
					//Ball position reset
					blocks[ballPosition].setIcon(sand);
					ballPosition = 15;
					jTSquare.setText(""+ballPosition);
					blocks[ballPosition].setIcon(ballSand);
					
					jBForward.setEnabled(true);
					jBBackwards.setEnabled(true);
					jBUp.setEnabled(true);
					jBDown.setEnabled(true);
					jBAct.setEnabled(true);
				}
				else {
					jRImage = new ImageIcon(this.getClass().getResource("/west.jpg"));
					img.setIcon(jRImage);
					
					blocks[ballPosition].setIcon(sand);
					ballPosition = 15;
					jTSquare.setText(""+ballPosition);
					blocks[ballPosition].setIcon(ballSand);
				}
				
			}
			
		}
		
		public void moveBall() {
			move(jBDown);
			move(jBForward);
			
				//move(jBUp);				
				//move(jBBackwards);				
				
		}
		
		public void move(JButton moveButton) {
			int moveLeft = ballPosition-1;
			int moveRight = ballPosition+1;
			int moveUp = ballPosition-16;
			int moveDown = ballPosition+16;
			
			if(moveButton == jBForward) {
				if(blocks[moveLeft].getIcon() == sand || moveLeft == 192) {
					jTDirection.setText("W");
					jRImage = new ImageIcon(this.getClass().getResource("/west.jpg"));
					img.setIcon(jRImage);
						
					blocks[ballPosition].setIcon(sand);
					ballPosition = moveLeft;
					jTSquare.setText(""+ballPosition);
					blocks[ballPosition].setIcon(ballSand);
					
					if(moveLeft == 192) {
						jTDirection.setText("W");
						jRImage = new ImageIcon(this.getClass().getResource("/west.jpg"));
						img.setIcon(jRImage);
							
						blocks[ballPosition].setIcon(sand);
						ballPosition = 192;
						jTSquare.setText(""+ballPosition);
						blocks[ballPosition].setIcon(win);
						jBForward.setEnabled(false);
						jBBackwards.setEnabled(false);
						jBUp.setEnabled(false);
						jBDown.setEnabled(false);
					}
				}
			}
			if(moveButton == jBBackwards) {
				if(blocks[moveRight].getIcon() == sand) {
					jTDirection.setText("E");
					jRImage = new ImageIcon(this.getClass().getResource("/east.jpg"));
					img.setIcon(jRImage);
					
					blocks[ballPosition].setIcon(sand);
					ballPosition = moveRight;
					jTSquare.setText(""+ballPosition);
					blocks[ballPosition].setIcon(ballSand);
					
				}
			}
			if(moveButton == jBUp) {
				if(blocks[moveUp].getIcon() == sand) {
					jTDirection.setText("N");
					jRImage = new ImageIcon(this.getClass().getResource("/north.jpg"));
					img.setIcon(jRImage);
					
					blocks[ballPosition].setIcon(sand);
					ballPosition = moveUp;
					jTSquare.setText(""+ballPosition);
					blocks[ballPosition].setIcon(ballSand);
				}
			}
			
			if(moveButton == jBDown) {
				if(blocks[moveDown].getIcon() == sand) {
					jTDirection.setText("S");
					jRImage = new ImageIcon(this.getClass().getResource("/south.jpg"));
					img.setIcon(jRImage);
					
					blocks[ballPosition].setIcon(sand);
					ballPosition = moveDown;
					jTSquare.setText(""+ballPosition);
					blocks[ballPosition].setIcon(ballSand);
				}
			}
			
			while(blocks[moveDown].getIcon() == sand){
				jTDirection.setText("S");
				jRImage = new ImageIcon(this.getClass().getResource("/south.jpg"));
				img.setIcon(jRImage);
				
				//File fallSound
				
				blocks[ballPosition].setIcon(sand);
				ballPosition = moveDown;
				jTSquare.setText(""+ballPosition);
				blocks[ballPosition].setIcon(ballSand);
			}
			
		}

}


