//NAME: Janny Ly
//DATE STARTED: Mar 13, 2023
//DATE FINISHED: Apr 7, 2023
//PURPOSE: Tic Tac Toe (Notakto Variation)

//IMPORTANT LIBRARIES
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
import java.net.*;
import java.applet.*;
import sun.audio.*;
import java.io.*;
import java.io.FileInputStream.*;


public class TicTacToe_Notakto extends Applet implements ActionListener
{
    //ALL GLOBAL VARIABLES------------------------------------------------------------------------------------------------------------------------------------------------------------

	//STORES THE TWO PLAYERS USERNAMES
    String name1 = JOptionPane.showInputDialog ("Player 1 FULL NAME:");
    String name2 = JOptionPane.showInputDialog ("Player 2 FULL NAME:");
    

	//STORES ALL THE WALLPAPER CHOICES THAT CAN CHANGE DEPENDING ON THE PLAYERS DECISIONS
    String wallchoice = "wallpaper.png";
    String wallchoice2 = "wallpaper2.png";
    String wallchoice3 = "wallpaper9.png";
    String wallchoice4 = "wallpaper10.png";
    String wallchoice5 = "wallpaper11.png";
    String wallchoice6 = "wallpaper12.png";
	//EMPTY, BUT HOLDS/STORES THE PLAYERS AVATARS IN A VARIETY OF SIZES WHICH IS THEN USED THROUGHOUT THE GAME
    String player1pic = "";
    String player2pic = "";
    String player1picbig = "";
    String player2picbig = "";
    String player1picmid = "";
    String player2picmid = "";
	//SOUNDS FILES
    AudioClip soundFile; 
    String musicpic = "";
	//ALL THE CHANGING PICTURES AND BUTTONS IN THE GAME
    JButton avatar1, avatar2, avatar3, avatar4, avatar5, avatar6, avatar7, avatar8, avatar9, avatar10, avatar11, avatar12, player1avatar, player2avatar, player1avatar2, player2avatar2, confirm, confirm1, confirm2, playerturnpic, playerturn, player1first, player2first, display;
     

    //ALL SCREENS---------------------------------------------------------------------------------------------------------------------------------------------------------------------

	//ALL THE PANELS THE GAME WILL MOVE BETWEEN
    Panel p_card;
    Panel card1, card2, card3, card4, card5, card6;
    CardLayout cdLayout = new CardLayout ();

	//SEPERATE GLOBAL JBUTTONS FOR THE GAMESCREEN
    JButton a, b, c, d, ee, f, g, h, i;
	//ARRAY THAT GIVES THE GRID FORMATION
    char board[] [] = {{'m', 'm', 'm'}, {'m', 'm', 'm'}, {'m', 'm', 'm'}};
    char turn = 'x';

	//FORMATTING
    Dimension square = new Dimension (120, 120);
    Color bgcolour = (new Color (215, 252, 212));
    Color bg2colour = (new Color (255, 236, 173));
    Color fgcolour = (new Color (191, 157, 115));
    Color bforward = (new Color (255, 194, 213));
    Color bback = (new Color (177, 230, 252));


	//WILL CREATE AND MAKE ALL THE PANELS VISIBLE
    public void init ()
    {
	//GETTING THE AUDIOCLIP FOR THE BACKGROUND MUSIC 
	JOptionPane.showMessageDialog (null, "Created by Janny Ly 2023", "Creator", JOptionPane.INFORMATION_MESSAGE);
	
	pickmusic();
	    
	//CALLING ALL THE METHODS FOR THE DIFFERENT SCREEN 
	p_card = new Panel ();
	p_card.setLayout (cdLayout);
	opening (wallchoice);
	optionscreen (wallchoice2);
	avatar (wallchoice3);
	avatar2 (wallchoice3);
	pickturn(wallchoice5, player1picmid, player2picmid);
	gamescreen (wallchoice4, player1pic, player2pic);
	resize (500, 700);
	setLayout (new BorderLayout ());
	add ("Center", p_card);
    }
    
    //METHOD THAT IS USED TO CREATE THE SOUND EFFECTS 
    public void soundEffect (String filepath)
    {
	AudioPlayer SEP = AudioPlayer.player;
	AudioStream SE;
	AudioData MA;
	AudioDataStream play = null;
	
	try 
	{
	    //IT FINDS THE FILE IN THE FOLDER AND GETS ITS DATA
	    SE = new AudioStream (new FileInputStream (filepath + ".wav"));
	    MA = SE.getData();
	    
	    play = new AudioDataStream(MA);
	}
	catch (IOException error)
	{
	    //IF IT CANT FIND THE FILE IT WILL OUTPUT THIS
	    System.out.println("Audio - File not found.");
	}
	//PLAYS THE SOUNDEFFECT
	SEP.start (play);
    }

    //METHOD TO CREATE A PULL DOWN OPTIONPANE FOR MUSIC OPTIONS
    public void pickmusic()
    {
	//CREATES AN ARRAY FOR THE SONG LIST 
	String [] songlist = {"a) Popping Music" , "b) Electronic Music" , "c) Light Electronic Music"};
	String song = (String) JOptionPane.showInputDialog (null, "What music would you like to listen to while playing Notakto?", "Music Choice", JOptionPane.INFORMATION_MESSAGE, null, songlist, songlist[0]);
	if (song.equals("a) Popping Music"))
	{
	    soundFile = getAudioClip(getDocumentBase(), "theme.wav");
	    soundFile.loop ();
	    //WILL CHANGE THE PICTURE THAT APPEARS ONTHE OPTIONPANE
	    musicpic = "music.png";
	}
	else if (song.equals("b) Electronic Music"))
	{
	    soundFile = getAudioClip(getDocumentBase(), "theme2.wav");
	    soundFile.loop ();
	    //WILL CHANGE THE PICTURE THAT APPEARS ONTHE OPTIONPANE
	    musicpic = "music2.png";
	}  
	else 
	{
	    soundFile = getAudioClip(getDocumentBase(), "theme3.wav");
	    soundFile.loop ();
	    //WILL CHANGE THE PICTURE THAT APPEARS ONTHE OPTIONPANE
	    musicpic = "music3.png";
	} 
    }
    
    
    //WILL CREATE A PANEL USING BOUNDARIES TO CREATE A BACKGROUND IMAGE
    private Image backgroundImage;

    class BackGroundPanel extends Panel
    {
	//AN IMAGE CLASS APART OF JAVA.AWT.* WHICH WILL MAKE A GRAPHICAL IMGE AS AN ARRAY OF PIXEL
	Image backGround;

	BackGroundPanel ()
	{
	    super ();
	}

	public void paint (Graphics g)
	{
	    g.drawImage (getBackGroundImage (), 0, 0,
		    //SETS THE BOUNDARIES OF THE PANEL THUS THE PICTURE WILL AUTOMATICALLY CONVERT TO THE CORRECT SIZE
		    (int) getBounds ().getWidth (), (int) getBounds ().getHeight (), this);
	}

	public void setBackGroundImage (Image backGround)
	{
	    this.backGround = backGround;
	}

	private Image getBackGroundImage ()
	{
	    return backGround;
	}
    }


    //FIRST SCREEN (OPENING/WELCOME SCREEN)--------------------------------------------------------------------------------------------------------

	//PARAMETER TO HAVE THE WALLCHOICE BE IMPLEMENTED BY THE USER BY CHOICE
    public void opening (String wallchoice)
    {
	card1 = new Panel ();
	    //THIS WILL MAKE THE BACKGROUND IMAGE USING THE METHOD CODED ON LINE 86
	Image backGround = getImage (getCodeBase (), wallchoice);
	BackGroundPanel card1 = new BackGroundPanel ();
	card1.setBackGroundImage (backGround);
	card1.setLayout (new FlowLayout ());
	setLayout (new BorderLayout ());

	    //CREATES THE TITLE OF THE SCREEN
	Panel p = new Panel ();
	JButton title = new JButton ("Saniro Pocket");
	title.setFont (new Font ("Arial", Font.BOLD, 50));
	title.setPreferredSize (new Dimension (400, 100));
	title.setBackground (bgcolour);
	title.setForeground (fgcolour);
	title.setBorder (null);
	p.add (title);

	    //INTRODUCES THE TYPE OF TICTACTOE GAME
	Panel p2 = new Panel ();
	JButton name = new JButton ("NOTAKTO");
	name.setFont (new Font ("Arial", Font.BOLD, 50));
	name.setPreferredSize (new Dimension (400, 60));
	name.setBackground (bg2colour);
	name.setForeground (fgcolour);
	name.setBorder (null);
	p2.add (name);

	    //DISPLAYS THE AUTOMATICALLY CREATED USERNAMES FOR THE PLAYERS USING METHODS
	Panel p3 = new Panel ();
	JButton welcome = new JButton ("Welcome Saniro: Villager " + username1 (name1) + " and Villager " + username2 (name2));
	welcome.setFont (new Font ("Arial", Font.BOLD, 18));
	welcome.setPreferredSize (new Dimension (450, 30));
	welcome.setBackground (bg2colour);
	welcome.setForeground (fgcolour);
	welcome.setBorder (null);
	p3.add (welcome);

	    //START BUTTON TO CONTINUE THROUGH OUT THE WHOLE GAME
	Panel p4 = new Panel ();
	JButton play = new JButton ("Start");
	play.setFont (new Font ("Arial", Font.BOLD, 40));
	play.setPreferredSize (new Dimension (200, 40));
	play.setBackground (bforward);
	play.setForeground (fgcolour);
	play.addActionListener (this);
	play.setActionCommand ("1");
	p4.add (play);

	    //BRINGS THE USER TO THE GAMESCREEN BACKGROUND SETTING SCREEN--HOWEVER THIS BACKGROUND ONLY AFFECTS THE GAMESCREEN AND CANNOT BE CHANGED ONCE IN GAME (KINDA WANT TO FIX SO THAT ITS MORE ACCESSIBLE)
	Panel p5 = new Panel ();
	JButton settings = new JButton ("Settings");
	settings.setFont (new Font ("Arial", Font.BOLD, 18));
	settings.setPreferredSize (new Dimension (150, 30));
	settings.setBackground (bback);
	settings.setForeground (fgcolour);
	settings.addActionListener (this);
	settings.setActionCommand ("2");
	p5.add (settings);

	    //BUTTON THAT OPENS UP THE INSTRUCTIONS OPTIONPANE
	JButton instru = new JButton ("Instructions");
	instru.setFont (new Font ("Arial", Font.BOLD, 18));
	instru.setPreferredSize (new Dimension (150, 30));
	instru.setBackground (bback);
	instru.setForeground (fgcolour);
	instru.addActionListener (this);
	instru.setActionCommand ("3");
	p5.add (instru);
	    
	    //BUTTON THAT OPENS UP THE MUSIC SETTINGS OPTIONPANE
	JButton songpopup = new JButton ("Music");
	songpopup.setFont (new Font ("Arial", Font.BOLD, 18));
	songpopup.setPreferredSize (new Dimension (150, 30));
	songpopup.setBackground (bback);
	songpopup.setForeground (fgcolour);
	songpopup.addActionListener (this);
	songpopup.setActionCommand ("20");
	p5.add (songpopup);

	card1.add (p);
	card1.add (p2);
	card1.add (p3);
	card1.add (p4);
	card1.add (p5);
	p_card.add ("1", card1);
    }


    //CREATE USERNAME FOR PLAYER 1 -----------------------------------------------------------------------------------------------------------------------------------------
    public String username1 (String name1)
    {
	String username1 = "";
	int space = 0;
	int space2 = 0;

	    //A FOR LOOP THAT FINDS THE SPACES IN THE PLAYERS FULL NAME (FIRST MIDDLE LAST)
	for (int i = 0 ; i < name1.length () ; i++)
	{
	    if (name1.charAt (i) == ' ' && space == 0)
	    {
		//HOLDS THE INDEX WHICH THE FIRST SPACE IS
		space = i;
	    }
	    if (name1.charAt (i) == ' ' && space != i)
	    {
		//HOLDS THE INDEX WHICH THE SECOND SPACE IS
		space2 = i;
	    }
	}
	    //WILL CREATE THE USERNAME FOR THE FIRST PLAYER BY TAKING THE FIRST LETTER OF THE FIRST NAME AND MIDDLE NAME, AND THEN THE FIRST AND LAST LETTERS OF THE LAST NAME
	username1 = ("" + name1.charAt (0) + name1.charAt (space + 1) + name1.charAt (space2 + 1) + name1.charAt (name1.length () - 1));
	return username1;
    }


    //CREATE USERNAME FOR PLAYER 2 ---------------------------------------------------------------------------------------------------------------------------------------------
    public String username2 (String name2)
    {
	String username2 = "";
	int space = 0;
	int space2 = 0;

	//A FOR LOOP THAT FIND THE SOACE IN THE PLAYERS FULL NAME (FIRST MIDDLE LAST)
	for (int i = 0 ; i < name2.length () ; i++)
	{
	    if (name2.charAt (i) == ' ' && space == 0)
	    {
		//HOLDS THE INDEX WHICH THE FIRST SPACE IS
		space = i;
	    }
	    if (name2.charAt (i) == ' ' && space != i)
	    {
		//HOLDS THE INDEX WHICH THE SECOND SPACE IS
		space2 = i;
	    }
	}
	//WILL CREATE THE USER NAME FOR THE SECOND PLAYER BY TAKING THE FIRST LETTER OF THE FIRST NAME NAD MIDDLE NAME, AND THEN THE FIRST AND LAST LETTERS OF THE LAST NAME
	username2 = ("" + name2.charAt (0) + name2.charAt (space + 1) + name2.charAt (space2 + 1) + name2.charAt (name2.length () - 1));
	return username2;
    }


    //SECOND SCREEN (SETTING/GAMESCREEN WALLPAPER CHOICES)----------------------------------------------------------------------------------------------------------------

    //PARAMETER TO HAVE THE WALLCHOICE BE IMPLEMENTED BY THE USER BY CHOICE
    public void optionscreen (String wallchoice2)
    {
	card2 = new Panel ();
	//THIS WILL MAKE THE BACKGROUND IMAGE USING THE METHOD CODED ON LINE 86
	Image backGround = getImage (getCodeBase (), wallchoice2);
	BackGroundPanel card2 = new BackGroundPanel ();
	card2.setBackGroundImage (backGround);
	card2.setLayout (new FlowLayout ());
	setLayout (new BorderLayout ());

	//TITLE OF THE SCREEN
	Panel p = new Panel ();
	JButton title = new JButton ("Settings");
	title.setFont (new Font ("Arial", Font.BOLD, 50));
	title.setPreferredSize (new Dimension (400, 100));
	title.setBackground (bgcolour);
	title.setForeground (fgcolour);
	title.setBorder (null);
	p.add (title);

	Panel p2 = new Panel ();
	//PURPOSE OF THE SCREEN IS TO BE ABLE TO CHANGE THE BACKGROUND IMAGE OF THE *GAMESCREEN*
	JButton name = new JButton ("GAMESCREEN BACKGROUND");
	name.setFont (new Font ("Arial", Font.BOLD, 25));
	name.setPreferredSize (new Dimension (400, 60));
	name.setBackground (bg2colour);
	name.setForeground (fgcolour);
	name.setBorder (null);
	p2.add (name);

	Panel p3 = new Panel ();
	//CREATES A SPACE SO THAT I CAN REPOSITION THE BUTTONS LOWER ONTO THE SCREEN
	JLabel space = new JLabel (createImageIcon ("space.png"));
	p3.add (space);

	//CREATES THE BUTTONS THAT WILL ALLOW THE USER TO PRESS ON THE WALLPAPER THEY WANT TO USE IN THE GAMESCREEN
	Panel p4 = new Panel ();
	JButton theme1 = new JButton ("1");
	theme1.setFont (new Font ("Arial", Font.BOLD, 18));
	theme1.setPreferredSize (new Dimension (150, 30));
	theme1.setBackground (bback);
	theme1.setForeground (fgcolour);
	theme1.addActionListener (this);
	theme1.setActionCommand ("4");
	p4.add (theme1);

	JButton theme2 = new JButton ("2");
	theme2.setFont (new Font ("Arial", Font.BOLD, 18));
	theme2.setPreferredSize (new Dimension (150, 30));
	theme2.setBackground (bback);
	theme2.setForeground (fgcolour);
	theme2.addActionListener (this);
	theme2.setActionCommand ("5");
	p4.add (theme2);

	JButton theme3 = new JButton ("3");
	theme3.setFont (new Font ("Arial", Font.BOLD, 18));
	theme3.setPreferredSize (new Dimension (150, 30));
	theme3.setBackground (bback);
	theme3.setForeground (fgcolour);
	theme3.addActionListener (this);
	theme3.setActionCommand ("6");
	p4.add (theme3);

	//SHOWS THE USER A MINI PREVIEW OF WHAT THE WALLPAPER LOOKS LIKE
	Panel p5 = new Panel ();
	JButton theme1pic = new JButton (createImageIcon ("wallpaper3.png"));
	theme1pic.setPreferredSize (new Dimension (150, 170));
	theme1pic.addActionListener (this);
	theme1pic.setActionCommand ("4");
	p5.add (theme1pic);

	JButton theme2pic = new JButton (createImageIcon ("wallpaper4.png"));
	theme2pic.setPreferredSize (new Dimension (150, 170));
	theme2pic.addActionListener (this);
	theme2pic.setActionCommand ("5");
	p5.add (theme2pic);

	JButton theme3pic = new JButton (createImageIcon ("wallpaper5.png"));
	theme3pic.setPreferredSize (new Dimension (150, 170));
	theme3pic.addActionListener (this);
	theme3pic.setActionCommand ("6");
	p5.add (theme3pic);

	//ALLOWS THE USER TO GO BACK TO THE STARTING SCREEN ONCE THEY HAVE FINISHED LOOKING AT THE SETTINGS SCREEN
	Panel p6 = new Panel ();
	JButton back = new JButton ("Back");
	back.setFont (new Font ("Arial", Font.BOLD, 18));
	back.setPreferredSize (new Dimension (150, 20));
	back.setBackground (bforward);
	back.setForeground (fgcolour);
	back.addActionListener (this);
	back.setActionCommand ("7");
	p6.add (back);

	card2.add (p);
	card2.add (p2);
	card2.add (p3);
	card2.add (p4);
	card2.add (p5);
	card2.add (p6);
	p_card.add ("2", card2);
    }


    //THIRD SCREEN(CHOOSING A PICTURE FOR THE FIRST PLAYERS TURN)-----------------------------------------------------------------------------------------------------------------

    //PARAMETER TO HAVE THE WALLCHOICE BE IMPLEMENTED BY THE USER BY CHOICE
    public void avatar (String wallchoice3)
    {
	card3 = new Panel ();
	//THIS WILL MAKE THE BACKGROUND IMAGE USING THE METHOD CODED ON LINE 86
	Image backGround = getImage (getCodeBase (), wallchoice3);
	BackGroundPanel card3 = new BackGroundPanel ();
	card3.setBackGroundImage (backGround);
	card3.setLayout (new FlowLayout ());
	setLayout (new BorderLayout ());

	Panel p = new Panel ();
	//CREATES THE TITLE OF THE SCREEN
	JButton title = new JButton ("Pick An Avatar");
	title.setFont (new Font ("Arial", Font.BOLD, 50));
	title.setPreferredSize (new Dimension (400, 100));
	title.setBackground (bgcolour);
	title.setForeground (fgcolour);
	title.setBorder (null);
	p.add (title);

	Panel p2 = new Panel ();
	//TELLS THE USER THAT PLAYER 1 SHOULD BE PICKING THE PICTURE RIGHT NOW
	JButton avatarpick = new JButton ("Player 1 Pick An Avatar");
	avatarpick.setFont (new Font ("Arial", Font.BOLD, 30));
	avatarpick.setPreferredSize (new Dimension (400, 60));
	avatarpick.setBackground (bg2colour);
	avatarpick.setForeground (fgcolour);
	avatarpick.setBorder (null);
	p2.add (avatarpick);

	//SHOWS ALL THE OPTIONS OF AVATARS THE USER CAN PICK FROM
	Panel p3 = new Panel ();
	avatar1 = new JButton (createImageIcon ("avatar1.png"));
	avatar1.setPreferredSize (new Dimension (100, 100));
	avatar1.addActionListener (this);
	avatar1.setActionCommand ("8");
	p3.add (avatar1);

	avatar2 = new JButton (createImageIcon ("avatar2.png"));
	avatar2.setPreferredSize (new Dimension (100, 100));
	avatar2.addActionListener (this);
	avatar2.setActionCommand ("9");
	p3.add (avatar2);

	avatar3 = new JButton (createImageIcon ("avatar3.png"));
	avatar3.setPreferredSize (new Dimension (100, 100));
	avatar3.addActionListener (this);
	avatar3.setActionCommand ("10");
	p3.add (avatar3);

	Panel p4 = new Panel ();
	avatar4 = new JButton (createImageIcon ("avatar4.png"));
	avatar4.setPreferredSize (new Dimension (100, 100));
	avatar4.addActionListener (this);
	avatar4.setActionCommand ("11");
	p4.add (avatar4);

	avatar5 = new JButton (createImageIcon ("avatar5.png"));
	avatar5.setPreferredSize (new Dimension (100, 100));
	avatar5.addActionListener (this);
	avatar5.setActionCommand ("12");
	p4.add (avatar5);

	avatar6 = new JButton (createImageIcon ("avatar6.png"));
	avatar6.setPreferredSize (new Dimension (100, 100));
	avatar6.addActionListener (this);
	avatar6.setActionCommand ("13");
	p4.add (avatar6);


	//THIS PORTION DISPLAYS WHAT PLAYER 1 HAS CHOSEN FOR THIER AVATAR
	Panel p5 = new Panel ();
	JButton player1pic = new JButton ("Player 1: " + username1 (name1));
	player1pic.setFont (new Font ("Arial", Font.BOLD, 18));
	player1pic.setPreferredSize (new Dimension (300, 30));
	player1pic.setBackground (bg2colour);
	player1pic.setForeground (fgcolour);
	player1pic.setBorder (null);
	p5.add (player1pic);

	Panel p6 = new Panel ();
	player1avatar = new JButton (createImageIcon ("blank.png"));
	player1avatar.setFont (new Font ("Arial", Font.BOLD, 18));
	player1avatar.setPreferredSize (new Dimension (200, 180));
	player1avatar.setBackground (bg2colour);
	player1avatar.setForeground (fgcolour);
	player1avatar.setBorder (null);
	p6.add (player1avatar);

	//ALLOWS PLAYER 1 TO GO BACK TO THE STARTING SCREEN BEFORE THEY CONFIRM THEIR AVATAR
	Panel p7 = new Panel ();
	JButton back = new JButton ("Back");
	back.setFont (new Font ("Arial", Font.BOLD, 18));
	back.setPreferredSize (new Dimension (240, 20));
	back.setBackground (bback);
	back.setForeground (fgcolour);
	back.setBorder (null);
	back.addActionListener (this);
	back.setActionCommand ("0");
	p7.add (back);

	//ALLOWS PLAYER 1 TO CONFIRM THEIR AVATAR
	confirm = new JButton ("Confirm");
	confirm.setFont (new Font ("Arial", Font.BOLD, 18));
	confirm.setPreferredSize (new Dimension (240, 20));
	confirm.setBackground (bback);
	confirm.setForeground (fgcolour);
	confirm.setBorder (null);
	confirm.addActionListener (this);
	confirm.setActionCommand ("100");
	confirm.setEnabled (false);
	p7.add (confirm);

	card3.add (p);
	card3.add (p2);
	card3.add (p3);
	card3.add (p4);
	card3.add (p5);
	card3.add (p6);
	card3.add (p7);
	p_card.add ("3", card3);
    }


    //FOURTH SCREEN (CHOOSING A PICTURE FOR THE SECOND PLAYERS TURN)-----------------------------------------------------------------------------------------------------------

    //PARAMETER TO HAVE THE WALLCHOICE BE IMPLEMENTED BY THE USER BY CHOICE
    public void avatar2 (String wallchoice3)
    {
	card4 = new Panel ();
	//THIS WILL MAKE THE BACKGROUND IMAGE USING THE METHOD CODED ON LINE 86
	Image backGround = getImage (getCodeBase (), wallchoice3);
	BackGroundPanel card4 = new BackGroundPanel ();
	card4.setBackGroundImage (backGround);
	card4.setLayout (new FlowLayout ());
	setLayout (new BorderLayout ());

	Panel p = new Panel ();
	//CREATES A TITLE FOR THE SCREEN
	JButton title = new JButton ("Pick An Avatar");
	title.setFont (new Font ("Arial", Font.BOLD, 50));
	title.setPreferredSize (new Dimension (400, 100));
	title.setBackground (bgcolour);
	title.setForeground (fgcolour);
	title.setBorder (null);
	p.add (title);

	Panel p2 = new Panel ();
	//TELLS THE USER THAT PLAYER TWO SHOULD BE PICKING THEIR AVATAR RIGHT NOW
	JButton avatarpick = new JButton ("Player 2 Pick An Avatar");
	avatarpick.setFont (new Font ("Arial", Font.BOLD, 30));
	avatarpick.setPreferredSize (new Dimension (400, 60));
	avatarpick.setBackground (bg2colour);
	avatarpick.setForeground (fgcolour);
	avatarpick.setBorder (null);
	p2.add (avatarpick);

	//SHOWS PLAYER TWO ALL THE AVATAR OPTIONS TO PICK FROM
	Panel p3 = new Panel ();
	avatar7 = new JButton (createImageIcon ("avatar1.png"));
	avatar7.setPreferredSize (new Dimension (100, 100));
	avatar7.addActionListener (this);
	avatar7.setActionCommand ("14");
	p3.add (avatar7);

	avatar8 = new JButton (createImageIcon ("avatar2.png"));
	avatar8.setPreferredSize (new Dimension (100, 100));
	avatar8.addActionListener (this);
	avatar8.setActionCommand ("15");
	p3.add (avatar8);

	avatar9 = new JButton (createImageIcon ("avatar3.png"));
	avatar9.setPreferredSize (new Dimension (100, 100));
	avatar9.addActionListener (this);
	avatar9.setActionCommand ("16");
	p3.add (avatar9);

	Panel p4 = new Panel ();
	avatar10 = new JButton (createImageIcon ("avatar4.png"));
	avatar10.setPreferredSize (new Dimension (100, 100));
	avatar10.addActionListener (this);
	avatar10.setActionCommand ("17");
	p4.add (avatar10);

	avatar11 = new JButton (createImageIcon ("avatar5.png"));
	avatar11.setPreferredSize (new Dimension (100, 100));
	avatar11.addActionListener (this);
	avatar11.setActionCommand ("18");
	p4.add (avatar11);

	avatar12 = new JButton (createImageIcon ("avatar6.png"));
	avatar12.setPreferredSize (new Dimension (100, 100));
	avatar12.addActionListener (this);
	avatar12.setActionCommand ("19");
	p4.add (avatar12);

	//THIS PORTION DISPLAYS WHAT AVATAR PLAYER TWO HAS CHOSEN
	Panel p5 = new Panel ();
	JButton player2pic = new JButton ("Player 2: " + username2 (name2));
	player2pic.setFont (new Font ("Arial", Font.BOLD, 18));
	player2pic.setPreferredSize (new Dimension (300, 30));
	player2pic.setBackground (bg2colour);
	player2pic.setForeground (fgcolour);
	player2pic.setBorder (null);
	p5.add (player2pic);

	Panel p6 = new Panel ();
	player2avatar = new JButton (createImageIcon ("blank.png"));
	player2avatar.setFont (new Font ("Arial", Font.BOLD, 18));
	player2avatar.setPreferredSize (new Dimension (200, 180));
	player2avatar.setBackground (bg2colour);
	player2avatar.setForeground (fgcolour);
	player2avatar.setBorder (null);
	p6.add (player2avatar);

	//THIS ALLOWS PLAYER TWO TO CONFIRM THEIR CHOICE AND MOVE ONTO THE GAMESCREEN
	Panel p7 = new Panel ();
	confirm1 = new JButton ("Confirm");
	confirm1.setFont (new Font ("Arial", Font.BOLD, 18));
	confirm1.setPreferredSize (new Dimension (300, 20));
	confirm1.setBackground (bback);
	confirm1.setForeground (fgcolour);
	confirm1.setBorder (null);
	confirm1.addActionListener (this);
	confirm1.setActionCommand ("101");
	confirm1.setEnabled (false);
	p7.add (confirm1);

	card4.add (p);
	card4.add (p2);
	card4.add (p3);
	card4.add (p4);
	card4.add (p5);
	card4.add (p6);
	card4.add (p7);
	p_card.add ("4", card4);
    }


    //FIFTH SCREEN (ALLOWS USER TO CHOOSE IF PLAYER ONE OR PLAYER TWO WILL GO FIRST)------------------------------------------------------------------------------------------------

    //PARAMETER TO HAVE THE WALLCHOICE AND THE PLAYERS AVATARS BE IMPLEMENTED INTO THE SCREEN
    public void pickturn (String wallchoice5, String player1mid, String player2mid)
    {
	card6 = new Panel ();
	//THIS WILL MAKE THE BACKGROUND IMAGE USING THE METHOD CODED ON LINE 86
	Image backGround = getImage (getCodeBase (), wallchoice5);
	BackGroundPanel card6 = new BackGroundPanel ();
	card6.setBackGroundImage (backGround);
	card6.setLayout (new FlowLayout ());
	setLayout (new BorderLayout ());

	//CREATES THE TITLE FOR THE SCREEN
	Panel p = new Panel ();
	JButton title = new JButton ("Who Will Go FIRST?");
	title.setFont (new Font ("Arial", Font.BOLD, 40));
	title.setPreferredSize (new Dimension (400, 100));
	title.setBackground (bforward);
	title.setForeground (fgcolour);
	title.setBorder (null);
	p.add (title);

	//DISPLAYS PLAYER 1'S NAME AND AVATAR
	Panel p2 = new Panel ();
	JButton player1 = new JButton ("Player 1: " + username1 (name1));
	player1.setFont (new Font ("Arial", Font.BOLD, 18));
	player1.setPreferredSize (new Dimension (300, 30));
	player1.setBackground (bg2colour);
	player1.setForeground (fgcolour);
	player1.setBorder (null);
	p2.add (player1);

	Panel p3 = new Panel ();
	player1avatar2 = new JButton (createImageIcon (player1picmid));
	player1avatar2.setFont (new Font ("Arial", Font.BOLD, 18));
	player1avatar2.setPreferredSize (new Dimension (200, 180));
	player1avatar2.setBackground (bg2colour);
	player1avatar2.setForeground (fgcolour);
	player1avatar2.setBorder (null);
	p3.add (player1avatar2);

	//ALLOWS THE USER TO PICK PLAYER 1 TO GO FIRST IN NOTAKTO
	player1first = new JButton (createImageIcon ("first.png"));
	player1first.setPreferredSize (new Dimension (50, 50));
	player1first.setBackground (bforward);
	player1first.setForeground (fgcolour);
	player1first.addActionListener (this);
	player1first.setActionCommand ("200");
	p3.add (player1first);

	//DISPLAYS PLAYER 2'S NAME NAD AVATAR
	Panel p4 = new Panel ();
	JButton player2 = new JButton ("Player 2: " + username2 (name2));
	player2.setFont (new Font ("Arial", Font.BOLD, 18));
	player2.setPreferredSize (new Dimension (300, 30));
	player2.setBackground (bg2colour);
	player2.setForeground (fgcolour);
	player2.setBorder (null);
	p4.add (player2);

	Panel p5 = new Panel ();
	player2avatar2 = new JButton (createImageIcon (player2picmid));
	player2avatar2.setFont (new Font ("Arial", Font.BOLD, 18));
	player2avatar2.setPreferredSize (new Dimension (200, 180));
	player2avatar2.setBackground (bg2colour);
	player2avatar2.setForeground (fgcolour);
	player2avatar2.setBorder (null);
	p5.add (player2avatar2);

	//ALLOWS THE USER TO PICK PLAYER 2 TO GO FIRST IN NOTAKTO
	player2first = new JButton (createImageIcon ("first.png"));
	player2first.setPreferredSize (new Dimension (50, 50));
	player2first.setBackground (bforward);
	player2first.setForeground (fgcolour);
	player2first.addActionListener (this);
	player2first.setActionCommand ("201");
	p5.add (player2first);

	//ALLOWS THE USER TO CONFIRM WHO WILL GO FIRST IN THE GAME
	Panel p6 = new Panel ();
	confirm2 = new JButton ("Confirm");
	confirm2.setFont (new Font ("Arial", Font.BOLD, 18));
	confirm2.setPreferredSize (new Dimension (300, 50));
	confirm2.setBackground (bback);
	confirm2.setForeground (fgcolour);
	confirm2.setBorder (null);
	confirm2.addActionListener (this);
	confirm2.setActionCommand ("102");
	confirm2.setEnabled (false);
	p6.add (confirm2);

	card6.add (p);
	card6.add (p2);
	card6.add (p3);
	card6.add (p4);
	card6.add (p5);
	card6.add (p6);
	p_card.add ("6", card6);
    }


    //METHOD TO CALL THE MUSIC IN AN OPTIONPANE
    public void music (String musicpick)
    {
	//CREATES AN ARRAY OF STRINGS THAT WILL BE IN THE ORDER OF THE BUTTONS ON THE OPTIONPANE 
	String[] options = new String[]{"Play_Again", "Stop_Music", "Exit"};

	//AN OPTIONPANE THAT HAS CUSTOM BUTTONS AND A PICTURE WHICH USE THE OPTIONS ARRAY AS THE BUTTONS
	int option = JOptionPane.showOptionDialog (null, createImageIcon (musicpick), "Music Settingss", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options [0]);

	//WILL LOOP THE SONG
	if (option == 0)
	{
	    soundFile.loop ();
	}
	//WILL STOP THE SONG 
	else if (option == 1)
	{
	    soundFile.stop();
	}
	//WILL EXIT OUT OF THE OPTIONPANE WITHOUT DOING ANYTHING 
	else
	{
	
	}


    }


    //SIXTH SCREEN (GAMESCREEN FOR THE TICTACTOW ADAPTION-NOTAKTO---------------------------------------------------------------------------------------------------

    //PARAMETER TO HAVE THE WALLCHOICE AND THE PLAYERS AVATARS BE IMPLEMENTED INTO THE SCREEN
    public void gamescreen (String wallchoice4, String player1pic, String player2pic)
    {
	card5 = new Panel ();
	//THIS WILL MAKE THE BACKGROUND IMAGE USING THE METHOD CODED ON LINE 86
	Image backGround = getImage (getCodeBase (), wallchoice4);
	BackGroundPanel card5 = new BackGroundPanel ();
	card5.setBackGroundImage (backGround);
	card5.setLayout (new FlowLayout ());
	setLayout (new BorderLayout ());

	Panel p = new Panel ();
	//CREATES THE TITLE OF THE GAMESCREEN WHICH WILL TELL THE USER WHAT TICTACTOW ADAPTION THIS IS
	JButton title = new JButton ("NOTAKTO");
	title.setFont (new Font ("Arial", Font.BOLD, 50));
	title.setPreferredSize (new Dimension (400, 100));
	title.setBackground (bforward);
	title.setForeground (fgcolour);
	title.setBorder (null);
	p.add (title);

	Panel p2 = new Panel ();
	//THIS WILL TELL THE USER WHO HAS THE CURRENT TURN TO PLACE THEIR PICTURE ON THE BOARD
	playerturn = new JButton ("Player " + username1 (name1) + "'s turn");
	playerturn.setFont (new Font ("Arial", Font.BOLD, 30));
	playerturn.setPreferredSize (new Dimension (300, 60));
	playerturn.setBackground (bgcolour);
	playerturn.setForeground (fgcolour);
	playerturn.setBorder (null);
	p2.add (playerturn);

	//THIS WILL DISPLAY THE AVATAR OF THE PLAYER WHO HAS THE CURRENT TURN FOR THE BOARD
	playerturnpic = new JButton (createImageIcon (player1pic));
	playerturnpic.setPreferredSize (new Dimension (60, 60));
	playerturnpic.setBorder (null);
	p2.add (playerturnpic);

	//NEW GRIDLAYOUT WILL MAKE A 3 BY 3 GRID FOR THE BOARD
	//EACH BUTTON IS INITALIZED AS THE SAME PICTURE
	//EACH BUTTON HAS A LETTER ASSOCIATED WITH THE BUTTON A-I
	Panel p3 = new Panel (new GridLayout (3, 3));
	a = new JButton (createImageIcon ("0.png"));
	a.setPreferredSize (new Dimension (square));
	a.setBorder (null);
	a.addActionListener (this);
	a.setActionCommand ("a");
	p3.add (a);

	b = new JButton (createImageIcon ("0.png"));
	b.setPreferredSize (new Dimension (square));
	b.setBorder (null);
	b.addActionListener (this);
	b.setActionCommand ("b");
	p3.add (b);

	c = new JButton (createImageIcon ("0.png"));
	c.setPreferredSize (new Dimension (square));
	c.setBorder (null);
	c.addActionListener (this);
	c.setActionCommand ("c");
	p3.add (c);

	d = new JButton (createImageIcon ("0.png"));
	d.setPreferredSize (new Dimension (square));
	d.setBorder (null);
	d.addActionListener (this);
	d.setActionCommand ("d");
	p3.add (d);

	ee = new JButton (createImageIcon ("0.png"));
	ee.setPreferredSize (new Dimension (square));
	ee.setBorder (null);
	ee.addActionListener (this);
	ee.setActionCommand ("e");
	p3.add (ee);

	f = new JButton (createImageIcon ("0.png"));
	f.setPreferredSize (new Dimension (square));
	f.setBorder (null);
	f.addActionListener (this);
	f.setActionCommand ("f");
	p3.add (f);

	g = new JButton (createImageIcon ("0.png"));
	g.setPreferredSize (new Dimension (square));
	g.setBorder (null);
	g.addActionListener (this);
	g.setActionCommand ("g");
	p3.add (g);

	h = new JButton (createImageIcon ("0.png"));
	h.setPreferredSize (new Dimension (square));
	h.setBorder (null);
	h.addActionListener (this);
	h.setActionCommand ("h");
	p3.add (h);

	i = new JButton (createImageIcon ("0.png"));
	i.setPreferredSize (new Dimension (square));
	i.setBorder (null);
	i.addActionListener (this);
	i.setActionCommand ("i");
	p3.add (i);

	//REVEAL WILL ALLOW THE USERS TO SEE WHERE EACH PICTURE WAS PLACED BY EACH PLAYER AFTER THE GAME IS FINISHED
	//PLANNED TO THAT IT WILL ONLY BE ENABLED TRUED AFTER THE WINNING CONDITIONS HAVE BEEN MET
	Panel p4 = new Panel ();
	display = new JButton ("Reveal");
	display.setFont (new Font ("Arial", Font.BOLD, 30));
	display.setPreferredSize (new Dimension (300, 60));
	display.setBackground (bg2colour);
	display.setForeground (fgcolour);
	display.setBorder (null);
	display.addActionListener (this);
	display.setActionCommand ("reveal");
	display.setEnabled(false);
	p4.add (display);

	//OPENS A OPTIONPANE THAT ALLOWS THE USER TO STOP THE MUSIC OR START IT AGAIN
	Panel p5 = new Panel ();
	JButton settings = new JButton ("Music");
	settings.setFont (new Font ("Arial", Font.BOLD, 18));
	settings.setPreferredSize (new Dimension (150, 30));
	settings.setBackground (bback);
	settings.setForeground (fgcolour);
	settings.addActionListener (this);
	settings.setActionCommand ("20");
	p5.add (settings);

	//OPENED A OPTIONPANE THAT ALLOWS THE USER TO SEE THE INSTRUCTIONS WHILE IN GAME
	JButton instru = new JButton ("Instructions");
	instru.setFont (new Font ("Arial", Font.BOLD, 18));
	instru.setPreferredSize (new Dimension (150, 30));
	instru.setBackground (bback);
	instru.setForeground (fgcolour);
	instru.addActionListener (this);
	instru.setActionCommand ("3");
	p5.add (instru);
	
	JButton reset = new JButton ("Again");
	reset.setFont (new Font ("Arial", Font.BOLD, 18));
	reset.setPreferredSize (new Dimension (150, 30));
	reset.setBackground (bgcolour);
	reset.setForeground (fgcolour);
	reset.addActionListener (this);
	reset.setActionCommand ("210");
	p5.add (reset);

	card5.add (p);
	card5.add (p2);
	card5.add (p3);
	card5.add (p4);
	card5.add (p5);
	p_card.add ("5", card5);
    }


    //METHOD THAT WILL CHANGE WHO HAS THE CURRENT TURN AFTER A PLAYER HAS PLACED THEIR PICTURE
    public void flipturn (String player1pic, String player2pic)
    {
	if (turn == 'x')
	{
	    //WILL FLIP THE TURN TO THE PLAYER O AND CHANGE THE LABEL AND PICTURE AS WELL
	    turn = 'o';
	    playerturnpic.setIcon (createImageIcon (player2pic));
	    playerturn.setText ("Player " + username2 (name2) + "'s turn");
	}
	else
	{
	    //WILL FLIP THE TURN TO THE PLAYER X AND CHANGE THE LABEL AND PICTURE AS WELL
	    turn = 'x';
	    playerturnpic.setIcon (createImageIcon (player1pic));
	    playerturn.setText ("Player " + username1 (name1) + "'s turn");
	}
    }



    //METHOD THAT USES THE GENERA; TERM X, Y COORDINATES AND SQUARE AS PARAMETERS
    //THESE PARAMETERS ARE TO HOLD THE VALUE WHICH WILL BE GIVEN TO THE METHOD THROUGH IT BEING CALLED IN THE ACTIONPREFORMED
    //CALLING THE METHOD IN ACTION PREFORM WILL GIVE X AND Y AND SQUARE THEIR VALUES MAKING THIS METHOD MORE EXTENSIVE
    public void upDateSquare (int x, int y, JButton square)
    {
	//CHECKS IF THE POSITION ON THE BOARD IS EMPTY/NOT BEEN PICKED YET
	if (board [x] [y] == 'm')
	{
	    //WILL ASSOCIATE THE BUTTON WITH THE VALUE OF THE CURRENT PLAYER
	    //DONE SO THAT IT REGISTERS WHICH PLAYER PICKED WHICH SQUARE ON THE BOARD
	    //WILL ULTIMATELY HELP WITH THE WINNING CONDITIONS BECAUSE THE GAME HAS THE SAME PICTURES
	    board [x] [y] = turn;
	    //WILL SET THE SQAURE THE USER PICKED INTO THE SAME IMAGE FOR BOTH PLAYERS
	    square.setIcon (createImageIcon (turn + ".png"));
	    //WILL MAKE SURE THE GIF IS DISPLAYED WELL
	    //NO GLITCHES WHILE IT RENDERS
	    square.setDoubleBuffered (true);
	    //WHEN THE SQUARE IS PICKED IT WILL CALL THE FLIPTURN METHOD
	    flipturn (player1pic, player2pic);
	    win(player1picmid, player2picmid);
	}
	else
	{
	    //INFORMS THE USER THEY HAVE PICKED A SQUARE THAT HAS ALREADY BEEN CHOSEN
	    JOptionPane.showMessageDialog (null, "Pick Again", "This Position Has Already Been Chosen", JOptionPane.ERROR_MESSAGE);
	}
    }

    //THE WIN (IN THIS CASE THE LOSE METHOD)--------------------------------------------------------------------------------------
    public void win (String player1picmid, String player2picmid)
    {
	//SETS THE WINNER AS N TO INDICATE THAT THERE IS NO WINNER YET
	char winner = 'n';
	
	//TOP ROW WIN CONDITION
	if (board[0][0] == board[0][1] && board [0][0] == board [0][2] && board [0][0] != 'm')
	{
	    //WILL MAKE THE WINNER THE VALUE OF THE SQUARE ON THE BOARD 
	    winner = board [0][0];
	    //HIGHLIGHTS THE BORDER OF THE WINNING ROW IN RED 
	    a.setBorder(BorderFactory.createLineBorder(Color.RED));
	    b.setBorder(BorderFactory.createLineBorder(Color.RED));
	    c.setBorder(BorderFactory.createLineBorder(Color.RED));
	    soundEffect("lose");
	}
	//MIDDLE ROW WIN CONDITION
	else if(board[1][0] == board[1][1] && board [1][0] == board [1][2] && board [1][0] != 'm')
	{
	    //WIL MAKE THE WINNER THE VALUE OF THE SQUARE ON THE BOARD
	    winner = board [1][0];
	    //HIGHLIGHTS THE BORDER OF THE WINNING ROW IN RED 
	    d.setBorder(BorderFactory.createLineBorder(Color.RED));
	    ee.setBorder(BorderFactory.createLineBorder(Color.RED));
	    f.setBorder(BorderFactory.createLineBorder(Color.RED));
	    soundEffect("lose");
	}
	//BOTTOM ROW WIN CONDITION
	else if(board[2][0] == board[2][1] && board [2][0] == board [2][2] && board [2][0] != 'm')
	{
	    //WILL MAKE THE WINNER THE VALUE OF THE SQUARE ON THE BOARD 
	    winner = board [2][0];
	    //HIGHLIGHTS THE BORDER OF THE WINNING ROW IN RED 
	    g.setBorder(BorderFactory.createLineBorder(Color.RED));
	    h.setBorder(BorderFactory.createLineBorder(Color.RED));
	    i.setBorder(BorderFactory.createLineBorder(Color.RED));
	    soundEffect("lose");
	}
	//FIRST COL WIN CONDITION
	else if(board[0][0] == board[1][0] && board [0][0] == board [2][0] && board [0][0] != 'm')
	{
	    //WILL MAKE THE WINNER THE VALUE OF THE SQUARE ON THE BOARD 
	    winner = board [0][0];
	    //HIGHLIGHTS THE BORDER OF THE WINNING COL IN RED 
	    a.setBorder(BorderFactory.createLineBorder(Color.RED));
	    d.setBorder(BorderFactory.createLineBorder(Color.RED));
	    g.setBorder(BorderFactory.createLineBorder(Color.RED));
	    soundEffect("lose");
	}
	//SECOND COL WIN CONDITION
	else if(board[0][1] == board[1][1] && board [0][1] == board [2][1] && board [0][1] != 'm')
	{
	    //WILL MAKE THE WINNER THE VALUE OF THE SQUARE ON THE BOARD 
	    winner = board [0][1];
	    //HIGHLIGHTS THE BORDER OF THE WINNING COL IN RED 
	    b.setBorder(BorderFactory.createLineBorder(Color.RED));
	    ee.setBorder(BorderFactory.createLineBorder(Color.RED));
	    h.setBorder(BorderFactory.createLineBorder(Color.RED));
	    soundEffect("lose");
	}
	//LAST COL WIN CONDITION
	else if(board[0][2] == board[1][2] && board [0][2] == board [2][2] && board [0][2] != 'm')
	{
	    //WILL MAKE THE WINNER THE VALUE OF THE SQUARE ON THE BOARD 
	    winner = board [0][2];
	    //HIGHLIGHTS THE BORDER OF THE WINNING COL IN RED 
	    c.setBorder(BorderFactory.createLineBorder(Color.RED));
	    f.setBorder(BorderFactory.createLineBorder(Color.RED));
	    i.setBorder(BorderFactory.createLineBorder(Color.RED));
	    soundEffect("lose");
	}
	//TOP LEFT TO BOT RIGHT DIAGONAL WIN CONDITION 
	else if(board[0][0] == board[1][1] && board [0][0] == board [2][2] && board [0][0] != 'm')
	{
	    //WILL MAKE THE WINNER THE VALUE OF THE SQUARE ON THE BOARD 
	    winner = board [0][0];
	    //HIGHLIGHTS THE BORDER OF THE WINNING COL IN RED 
	    a.setBorder(BorderFactory.createLineBorder(Color.RED));
	    ee.setBorder(BorderFactory.createLineBorder(Color.RED));
	    i.setBorder(BorderFactory.createLineBorder(Color.RED));
	    soundEffect("lose");
	}
	//TOP RIGHT TO BOT LEFT DIAGONAL WIN CONDITION 
	else if(board[0][2] == board[1][1] && board [0][2] == board [2][0] && board [0][2] != 'm')
	{
	    //WILL MAKE THE WINNER THE VALUE OF THE SQUARE ON THE BOARD 
	    winner = board [0][2];
	    //HIGHLUGHTS THE BORDER OF THE WINNING COL IN RED 
	    c.setBorder(BorderFactory.createLineBorder(Color.RED));
	    ee.setBorder(BorderFactory.createLineBorder(Color.RED));
	    g.setBorder(BorderFactory.createLineBorder(Color.RED));
	    soundEffect("lose");
	}
	//TIE WINNING CONDITIONS 
	else if (board [0][0] != 'm' && board [0][1] != 'm' && board [0][2] != 'm' &&
		 board [1][0] != 'm' && board [1][1] != 'm' && board [1][2] != 'm' &&
		 board [2][0] != 'm' && board [2][1] != 'm' && board [2][2] != 'm')
	{
	    winner = 't';
	    soundEffect("lose");
	}
	    
	if (winner == 'x')
	{
	    //OPENS OPTIONPANE THAT WILL TELL THE USER WHO WON 
	    ImageIcon xwin = new ImageIcon (player1picmid); 
	    JOptionPane.showMessageDialog (null, username1(name1)+ " loses :(", "LOSER!!!", JOptionPane.PLAIN_MESSAGE, xwin);
	    //ENABLES THE REVEAL BUTTON WHICH THE USER CAN SEE WHERE THEY PUT THEIR AVATARS 
	    display.setEnabled(true);
	}
	else if (winner== 'o')
	{
	    //OPENS OPTIONPANE THAT WILL TELL THE USER WHO WON 
	    ImageIcon owin = new ImageIcon (player2picmid);
	    JOptionPane.showMessageDialog (null, username2(name2)+" loses :(", "LOSER!!!", JOptionPane.INFORMATION_MESSAGE, owin);
	    //ENABLES THE REVEAL BUTTON WHICH THE USER CAN SEE WHERE THEY PUT THEIR AVATARS 
	    display.setEnabled(true);
	}
	else if (winner == 't')
	{
	    //OPENS OPTIONPANE THAT WILL TELL THE USER THE GAME IS A TIE 
	    JOptionPane.showMessageDialog (null, "NO WINNER OR LOSER! TIE GAME", "OOP IT'S A TIE", JOptionPane.INFORMATION_MESSAGE);
	    //ENABLES THE REVEAL BUTTON WHICH THE USER CAN SEE WHERE THEY PUT THEIR AVATARS 
	    display.setEnabled(true);
	}
    }

    //THIS METHOD WILL REVEAL
    public void reveal (int x, int y, JButton square)
    {
	//WILL CHECK IF THE COORDINATE ON THE BOARD IS ASSOCIATED WITH THE VALUE FOR PLAYER X
	if (board [x] [y] == 'x')
	    //WILL REPLACE THE PICTURE ON THE BOARD WITH THE AVATAR OF PLAYER 1
	    square.setIcon (createImageIcon (player1picbig));
	else if(board [x] [y] == 'o')
	    //WILL REPLACE THE PICTURE ON THE BOARD WITH THE AVATAR OF PLAYER 2
	    square.setIcon (createImageIcon (player2picbig));
	else 
	    square.setIcon (createImageIcon ("0.png"));
    }
    
    //RESETS THE VALUE OF THE BOARD TO EMPTY 
    public void emptyboard ()
    {
	board[0][0] = 'm';
	board[0][1] = 'm';
	board[0][2] = 'm';
	board[1][0] = 'm';
	board[1][1] = 'm';
	board[1][2] = 'm';
	board[2][0] = 'm';
	board[2][1] = 'm';
	board[2][2] = 'm';
    }

    //ACTIONPERFORMED METHOD FOR THE PLAYER 1 AVATAR SCREEN
    public void actionPerformed1 (ActionEvent e)
    {
	if (e.getActionCommand ().equals ("8"))
	{
	    avatar1.setEnabled (false);
	    avatar2.setEnabled (true);
	    avatar3.setEnabled (true);
	    avatar4.setEnabled (true);
	    avatar5.setEnabled (true);
	    avatar6.setEnabled (true);
	    player1avatar.setIcon (createImageIcon ("avatar7.png"));
	    confirm.setEnabled (true);
	    player1pic = "avatar13.png";
	    player1picbig = "bigavatar1.png";
	    player1picmid = "avatar7.png";
	    gamescreen (wallchoice4, player1pic, player2pic);
	    pickturn (wallchoice5, player1picmid, player2picmid);
	}

	else if (e.getActionCommand ().equals ("9"))
	{
	    avatar2.setEnabled (false);
	    avatar1.setEnabled (true);
	    avatar3.setEnabled (true);
	    avatar4.setEnabled (true);
	    avatar5.setEnabled (true);
	    avatar6.setEnabled (true);
	    player1avatar.setIcon (createImageIcon ("avatar8.png"));
	    confirm.setEnabled (true);
	    player1pic = "avatar14.png";
	    player1picbig = "bigavatar2.png";
	    player1picmid = "avatar8.png";
	    gamescreen (wallchoice4, player1pic, player2pic);
	    pickturn (wallchoice5, player1picmid, player2picmid);
	}

	else if (e.getActionCommand ().equals ("10"))
	{
	    avatar3.setEnabled (false);
	    avatar1.setEnabled (true);
	    avatar2.setEnabled (true);
	    avatar4.setEnabled (true);
	    avatar5.setEnabled (true);
	    avatar6.setEnabled (true);
	    player1avatar.setIcon (createImageIcon ("avatar9.png"));
	    confirm.setEnabled (true);
	    player1pic = "avatar15.png";
	    player1picbig = "bigavatar3.png";
	    player1picmid = "avatar9.png";
	    gamescreen (wallchoice4, player1pic, player2pic);
	    pickturn (wallchoice5, player1picmid, player2picmid);
	}

	else if (e.getActionCommand ().equals ("11"))
	{
	    avatar4.setEnabled (false);
	    avatar1.setEnabled (true);
	    avatar2.setEnabled (true);
	    avatar3.setEnabled (true);
	    avatar5.setEnabled (true);
	    avatar6.setEnabled (true);
	    player1avatar.setIcon (createImageIcon ("avatar10.png"));
	    confirm.setEnabled (true);
	    player1pic = "avatar16.png";
	    player1picbig = "bigavatar4.png";
	    player1picmid = "avatar10.png";
	    gamescreen (wallchoice4, player1pic, player2pic);
	    pickturn (wallchoice5, player1picmid, player2picmid);
	}

	else if (e.getActionCommand ().equals ("12"))
	{
	    avatar5.setEnabled (false);
	    avatar1.setEnabled (true);
	    avatar2.setEnabled (true);
	    avatar3.setEnabled (true);
	    avatar4.setEnabled (true);
	    avatar6.setEnabled (true);
	    player1avatar.setIcon (createImageIcon ("avatar11.png"));
	    confirm.setEnabled (true);
	    player1pic = "avatar17.png";
	    player1picbig = "bigavatar5.png";
	    player1picmid = "avatar11.png";
	    gamescreen (wallchoice4, player1pic, player2pic);
	    pickturn (wallchoice5, player1picmid, player2picmid);
	}

	else if (e.getActionCommand ().equals ("13"))
	{
	    avatar6.setEnabled (false);
	    avatar1.setEnabled (true);
	    avatar2.setEnabled (true);
	    avatar3.setEnabled (true);
	    avatar4.setEnabled (true);
	    avatar5.setEnabled (true);
	    player1avatar.setIcon (createImageIcon ("avatar12.png"));
	    confirm.setEnabled (true);
	    player1pic = "avatar18.png";
	    player1picbig = "bigavatar6.png";
	    player1picmid = "avatar12.png";
	    gamescreen (wallchoice4, player1pic, player2pic);
	    pickturn (wallchoice5, player1picmid, player2picmid);
	}
    }

    //ACTION PERFORMED FOR THE INSTRUCTONS, MUSIC AND RESET BUTTONS
    public void actionPerformed2 (ActionEvent e)
    {
	if (e.getActionCommand ().equals ("3"))
	{
	    JOptionPane.showMessageDialog (null, createImageIcon ("demo.png"), "Instructions", JOptionPane.PLAIN_MESSAGE);
	}

	else if (e.getActionCommand ().equals ("20"))
	{
	    music (musicpic);
	}
	else if (e.getActionCommand ().equals ("210"))
	{
	    soundEffect("confirm");
	    cdLayout.show (p_card, "1");
	    player1pic = "";
	    player2pic = "";
	    player1picbig = "";
	    player2picbig = "";
	    player1picmid = "";
	    player2picmid = "";
	    
	    avatar1.setEnabled (true);
	    avatar2.setEnabled (true);
	    avatar3.setEnabled (true);
	    avatar4.setEnabled (true);
	    avatar5.setEnabled (true);
	    avatar6.setEnabled (true);
	    confirm.setEnabled (false);
	    player1avatar.setIcon(createImageIcon("blank.png"));
	    
	    avatar7.setEnabled (true);
	    avatar8.setEnabled (true);
	    avatar9.setEnabled (true);
	    avatar10.setEnabled (true);
	    avatar11.setEnabled (true);
	    avatar12.setEnabled (true);
	    confirm1.setEnabled (false);
	    player2avatar.setIcon(createImageIcon("blank.png"));
	    
	    player1first.setEnabled (true);
	    player2first.setEnabled (true);
	    confirm2.setEnabled (true);
	    
	    emptyboard();
	    turn = 'x';
	}
    }

    //ACTIONPERFORMED FOR THE BACKGROUND SETTINGS SCREEN 
    public void actionPerformed3 (ActionEvent e)
    {
	if (e.getActionCommand ().equals ("4"))
	{
	    wallchoice4 = "wallpaper6.png";
	    gamescreen (wallchoice4, player1pic, player2pic);
	}


	else if (e.getActionCommand ().equals ("5"))
	{            
	    wallchoice4 = "wallpaper7.png";
	    gamescreen (wallchoice4, player1pic, player2pic);
	}


	else if (e.getActionCommand ().equals ("6"))
	{
	    wallchoice4 = "wallpaper8.png";
	    gamescreen (wallchoice4, player1pic, player2pic);
	}
    }

    //ACTIONPERFORMED FOR THE CONFIRM BUTTONS THE AVATAR1, AVATAR2, AND WHO GOES FIRST SCREEN 
    public void actionPerformed4 (ActionEvent e)
    {
	if (e.getActionCommand ().equals ("200"))
	{
	    player1first.setEnabled (false);
	    player2first.setEnabled (true);
	    confirm2.setEnabled (true);
	    turn = 'o';
	}

	else if (e.getActionCommand ().equals ("201"))
	{
	    player1first.setEnabled (true);
	    player2first.setEnabled (false);
	    confirm2.setEnabled (true);
	    turn = 'x';
	}

	else if (e.getActionCommand ().equals ("102"))
	{
	    soundEffect("confirm");
	    cdLayout.show (p_card, "5");
	    flipturn (player1pic, player2pic);
	}
    }

    //ACTIONPERFORMED METHOF FOR THE PLAYER 2 AVATAR SCREEN 
    public void actionPerformed5 (ActionEvent e)
    {
	if (e.getActionCommand ().equals ("14"))
	{
	    avatar7.setEnabled (false);
	    avatar8.setEnabled (true);
	    avatar9.setEnabled (true);
	    avatar10.setEnabled (true);
	    avatar11.setEnabled (true);
	    avatar12.setEnabled (true);
	    player2avatar.setIcon (createImageIcon ("avatar7.png"));
	    confirm1.setEnabled (true);
	    player2pic = "avatar13.png";
	    player2picbig = "bigavatar1.png";
	    player2picmid = "avatar7.png";
	    gamescreen (wallchoice4, player1pic, player2pic);
	    pickturn (wallchoice5, player1picmid, player2picmid);
	}


	else if (e.getActionCommand ().equals ("15"))
	{
	    avatar8.setEnabled (false);
	    avatar7.setEnabled (true);
	    avatar9.setEnabled (true);
	    avatar10.setEnabled (true);
	    avatar11.setEnabled (true);
	    avatar12.setEnabled (true);
	    player2avatar.setIcon (createImageIcon ("avatar8.png"));
	    confirm1.setEnabled (true);
	    player2pic = "avatar14.png";
	    player2picbig = "bigavatar2.png";
	    player2picmid = "avatar8.png";
	    gamescreen (wallchoice4, player1pic, player2pic);
	    pickturn (wallchoice5, player1picmid, player2picmid);
	}


	else if (e.getActionCommand ().equals ("16"))
	{
	    avatar9.setEnabled (false);
	    avatar7.setEnabled (true);
	    avatar8.setEnabled (true);
	    avatar10.setEnabled (true);
	    avatar11.setEnabled (true);
	    avatar12.setEnabled (true);
	    player2avatar.setIcon (createImageIcon ("avatar9.png"));
	    confirm1.setEnabled (true);
	    player2pic = "avatar15.png";
	    player2picbig = "bigavatar3.png";
	    player2picmid = "avatar9.png";
	    gamescreen (wallchoice4, player1pic, player2pic);
	    pickturn (wallchoice5, player1picmid, player2picmid);
	}


	else if (e.getActionCommand ().equals ("17"))
	{
	    avatar10.setEnabled (false);
	    avatar7.setEnabled (true);
	    avatar8.setEnabled (true);
	    avatar9.setEnabled (true);
	    avatar11.setEnabled (true);
	    avatar12.setEnabled (true);
	    player2avatar.setIcon (createImageIcon ("avatar10.png"));
	    confirm1.setEnabled (true);
	    player2pic = "avatar16.png";
	    player2picbig = "bigavatar4.png";
	    player2picmid = "avatar10.png";
	    gamescreen (wallchoice4, player1pic, player2pic);
	    pickturn (wallchoice5, player1picmid, player2picmid);
	}


	else if (e.getActionCommand ().equals ("18"))
	{
	    avatar11.setEnabled (false);
	    avatar7.setEnabled (true);
	    avatar8.setEnabled (true);
	    avatar9.setEnabled (true);
	    avatar10.setEnabled (true);
	    avatar12.setEnabled (true);
	    player2avatar.setIcon (createImageIcon ("avatar11.png"));
	    confirm1.setEnabled (true);
	    player2pic = "avatar17.png";
	    player2picbig = "bigavatar5.png";
	    player2picmid = "avatar11.png";
	    gamescreen (wallchoice4, player1pic, player2pic);
	    pickturn (wallchoice5, player1picmid, player2picmid);
	}


	else if (e.getActionCommand ().equals ("19"))
	{
	    avatar12.setEnabled (false);
	    avatar7.setEnabled (true);
	    avatar8.setEnabled (true);
	    avatar9.setEnabled (true);
	    avatar10.setEnabled (true);
	    avatar11.setEnabled (true);
	    player2avatar.setIcon (createImageIcon ("avatar12.png"));
	    confirm1.setEnabled (true);
	    player2pic = "avatar18.png";
	    player2picbig = "bigavatar6.png";
	    player2picmid = "avatar12.png";
	    gamescreen (wallchoice4, player1pic, player2pic);
	    pickturn (wallchoice5, player1picmid, player2picmid);
	}
    }

    //ACTIONPERFORMED METHOD FOR THE REVEAL BUTTON ON THE GAMESCREEN 
    public void actionPerformed6 (ActionEvent e)
    {
	if (e.getActionCommand ().equals ("reveal"))
	{
	    reveal (0, 0, a);
	    reveal (0, 1, b);
	    reveal (0, 2, c);
	    reveal (1, 0, d);
	    reveal (1, 1, ee);
	    reveal (1, 2, f);
	    reveal (2, 0, g);
	    reveal (2, 1, h);
	    reveal (2, 2, i);
	}
    }

    //MAIN ACTIONPREFORMED METHOD WHICH CALL ALL THE OTHER ACTIONPERFORMED METHODS 
    //MOSTLY CONSISTS OF FLIPPING BETWEEN SCREENS 
    public void actionPerformed (ActionEvent e)
    {
	actionPerformed1 (e);
	actionPerformed2 (e);
	actionPerformed3 (e);
	actionPerformed4 (e);
	actionPerformed5 (e);
	actionPerformed6 (e);

	if (e.getActionCommand ().equals ("1"))
	{
	    cdLayout.show (p_card, "3");
	}
	else if (e.getActionCommand ().equals ("2"))
	{
	    cdLayout.show (p_card, "2");
	}


	else if (e.getActionCommand ().equals ("7"))
	{
	    cdLayout.show (p_card, "1");
	}

	else if (e.getActionCommand ().equals ("0"))
	{
	    cdLayout.show (p_card, "1");
	}


	else if (e.getActionCommand ().equals ("100"))
	{
	    soundEffect("confirm");
	    cdLayout.show (p_card, "4");
	}


	else if (e.getActionCommand ().equals ("101"))
	{
	    soundEffect("confirm");
	    cdLayout.show (p_card, "6");
	}
	//ALLOWS THE BUTTONS TO BE PRESSED AND UPDATED IN GAME 
	else
	{
	    if (e.getActionCommand ().equals ("a"))
	    {
		upDateSquare (0, 0, a);
	    }
	    else if (e.getActionCommand ().equals ("b"))
	    {
		upDateSquare (0, 1, b);
	    }
	    else if (e.getActionCommand ().equals ("c"))
	    {

		upDateSquare (0, 2, c);
	    }
	    else if (e.getActionCommand ().equals ("d"))
	    {
		upDateSquare (1, 0, d);
	    }
	    else if (e.getActionCommand ().equals ("e"))
	    {
		upDateSquare (1, 1, ee);
	    }
	    else if (e.getActionCommand ().equals ("f"))
	    {
		upDateSquare (1, 2, f);
	    }
	    else if (e.getActionCommand ().equals ("g"))
	    {
		upDateSquare (2, 0, g);
	    }
	    else if (e.getActionCommand ().equals ("h"))
	    {
		upDateSquare (2, 1, h);
	    }
	    else if (e.getActionCommand ().equals ("i"))
	    {
		upDateSquare (2, 2, i);
	    }
	}
    }


    protected static ImageIcon createImageIcon (String path)
    {
	java.net.URL imgURL = TicTacToe_Notakto.class.getResource (path);
	if (imgURL != null)
	{
	    return new ImageIcon (imgURL);
	}


	else
	{
	    System.err.println ("Couldn't find file: " + path);
	    return null;
	}
    }
}




