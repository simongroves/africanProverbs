/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.lwuit.*;
import com.sun.lwuit.animations.*;
import com.sun.lwuit.events.*;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.layouts.GridLayout;
import com.sun.lwuit.list.DefaultListModel;
import com.sun.lwuit.plaf.*;
import com.sun.lwuit.tree.Tree;
import com.sun.lwuit.tree.TreeModel;
import com.sun.lwuit.util.Resources;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.*;
import javax.microedition.pim.Contact;
import javax.microedition.pim.ContactList;
import javax.microedition.pim.PIM;
import javax.microedition.pim.PIMException;
import javax.microedition.rms.RecordStore;


/**
 * @author Simon Groves
 */
public class africanProverbMIDlet extends MIDlet implements ActionListener {
    Form frmPlaceHolder;
    Form frmHome;
    Form frmAbout;
    Form frmTellUs;
    Command cmdBack;
    Command cmdExit;
    Button btnAbout;
    Button btnPlaceHolder;
    Button btnTellUs;
    int intProverb;
    Random rand;
    int min, max, intRand;
    RecordStore rs=null;
    Label lblTicker;
    
    String[][] proverbs = {
            {"The axe forgets, the tree remembers", 
                "He who thinks he is leading and has no one following him is only taing a walk", 
                "Hunt in every forest for there is wisdom in them all",
                "A man who uses force is afraid of reasoning",
                "The African race is a rubber ball. The harder you dash it to the ground, the higher it will rise",
                "He who is destined for power does not have to fight for it",
                "A chick that will grow into a cock can be spotted the very day it hatches",
                "A child's fingers are not scalded by a piece of hot yam which his mother puts into his palm",
                "Do not look where you fell, but where you slipped",
                "It takes a whole village to raise a child",
                "The lizard that jumped from the high iroko tree to the ground said he would praise himself if no one else did",
                "The mouth which eats does not talk",
                "The sun will shine on those who stand before it shines on those who kneel under them",
                "Those whose palm-kernels were cracked for them by a benevolent spirit should not forget to be humble",
                "You must judge a man by the work of his hands",
                "By the time the fool has learned the game, the players have dispersed",
                "Do not call the forest that shelters you a jungle",
                "Even though the old man is strong and hearty, he will not live forever",
                "Fire and gunpowder do not sleep together",
                "Hunger is felt by a slave and hunger is felt by a king",
                "If you are in hiding, don't light a fire",
                "It is no shame at all to work for money",
                "It is the calm and silent water that drowns a man",
                "No one tests the depth of a river with both feet",
                "One falsehood spoils a thousand truths",
                "The moon moves slowly, but it crosses the town",
                "There is no medicine to cure hatred",
                "When a king has good counselors, his reign is peaceful",
                "When a man is wealthy, he may wear an old cloth",
                "When a woman is hungry, she says, 'Roast something for the children that they might eat.' ",
                "When the fool is told a proverb, its meaning has to be explained to him",
                "When you follow in the path of your father, you learn to walk like him",
                "A bird will always use another bird's feathers to feather its own nest",
                "He that digs a grave for his enemy, may be digging it for himself",
                "A person is a person because of other persons",
                "If you don't stand for something, you will fall for anything",
                "You overcame the rain, but what about the dew?",
                "As soon as you experience hunger, why do you always want to eat?",
                "Water follows the slope",
                "If the day of harvesting or eating yams is the same as the day of planting yams, even goats will not be allowed to eat the yam peels",
                "Big fish are caught with big fish hooks",
                "If the young are not initiated into the village, they will burn it down just to feel its warmth",
                "When deeds speak, words are nothing",
                "Never make the mistake of thinking it is easy to be capable. Overt Competency may be your greatest struggle in life",
                "To love someone who does not love you is like shaking a tree to make the dew drops fall",
                "If you can't resolve your problems in peace, you can't solve war",
                "I pointed out to you the stars and all you saw was my fingertip",
                "A beautiful woman is not without defects",
                "If you want to go fast, go alone. If you want to go far, go together",
                "Songs learnt from outside sources are not used at a dance for long",
                "Do not follow the path. Go where there is no path to begin the trail",
                "The habit of thinking is the habit of gaining strength",
                "Wealth, if you use it, comes to an end; learning, if you use it, increases",
                "The pillar of the world is hope",
                "Until lions write their own history, the tale of the hunt will always glorify the hunter",
                "Tomorrow belongs to the people who prepare for it today",
                "If the chicken attacks you in the morning, RUN first because it may have grown teeth overnight",
                "I always get confused when there’s a proverb sourced as an “African proverb”. There’s hundreds of different ethno-cultural groupings on the continent with varied languages and histories of interactions with each other and with non-African groups. It’s not like there’s some sort of single wise old person somewhere who spouts all the proverbs for all Africans",
                "He who learns, teaches",
                "If you close your eyes to facts, you will learn through accidents",
                "Every morning in Africa a gazelle awakens knowing it must today run faster than the fastest lion or it will be eaten. Every morning a lion awakens knowing it must outrun the slowest gazelle or it will starve. It matters not whether you are a gazelle or a lion, when the sun rises you had better be running",
                "The truth is like a baobab tree, you cannot embrace it with one pair of arms",
                "Moonlight does not enable one to see to gather firewood",
                "What suffices for a small project won't do for an enormous project",
                "The salt we have for seasoning the chicken is insufficient, and you go and kill a goat!",
                "The foolish little antelope cut firewood for the leopard",
                "The small spotted wild cat mistook the leopard for a relative!",
                "An antelope wouldn't be strong enough to carry the tusks an elephant bears",
                "One does not set out for a hunting camp with nothing but bananas",
                "As the ocimbamba seeks the low lying tree so friends gather to the friendly person",
                "What the guest would like is what the host is ashamed to offer as not being good enough and the guest is disappointed",
                "If heedless of warning it is you who will suffer, not I",
                "It makes a difference whose ox is gored",
                "Burning charcoal always turns to ashes",
                "People working on the slope of a mountain do not look at the buttocks of one another",
                "If two selfish young men sit next to a pot of water, the water spills out on the ground",
                "If an opportunity is not taken when it comes, it passes away",
                 "Little palm tree, stop crying, your child is the tall palm tree",
                 "If the young palm tree wants to stay alive, it grows next to the odum tree",
                "One palm nut cannot be peeled twice",
                "Even though the sound of the horn is not pleasant, it is still blown by a man's mouth",
                "You make a new arrow by comparing it to an old one",
                "The witch is going! The witch is going! but if you are not a witch you don't turn around to look"
                
            },
            {"Kenyan", 
                "Swahili", 
                "African",
                "Kenyan",
                "African",
                "Ugandan", 
                "African", 
                "African", 
                "African", 
                "African", 
                "African", 
                "African", 
                "African", 
                "African", 
                "African",
                "Ashanti",
                "Ashanti",
                "Ashanti",
                "Ashanti",
                "Ashanti",
                "Ashanti",
                "Ashanti",
                "Ashanti",
                "Ashanti",
                "Ashanti",
                "Ashanti",
                "Ashanti",
                "Ashanti",
                "Ashanti",
                "Ashanti",
                "Ashanti",
                "Ashanti",
                "Sesotho",
                "Sesotho",
                "Sesotho",
                "African",
                "Kenyan",
                "Kenyan",
                "Kenyan",
                "Ewe",
                "Kerewe",
                "African",
                "African",
                "African",
                "Congolese",
                "Somali",
                "Sukuma",
                "African",
                "African",
                "Swahili",
                "Ashanti",
                "African",
                "Swahili",
                "Nigerian",
                "African",
                "African",
                "Nonso",
                "African",
                "African",
                "African",
                "African",
                "African",
                "African",
                "Nkundo-Mongo",
                "Nkundo-Mongo",
                "Nkundo-Mongo",
                "Nkundo-Mongo",
                "Nkundo-Mongo",
                "Nkundo-Mongo",
                "Nkundo-Mongo",
                "Umbundu",
                "Umbundu",
                "Umbundu",
                "Kuria",
                "Fante",
                "Fante",
                "Fante",
                "Fante",
                "Fante",
                "Fante",
                "Fante",
                "Fante",
                "Fante"

            }
        };
    
     

    public void startApp() {
      Display.init(this);
      installTheme();
      createUI();
      
      frmHome.show();
    }
    
        private void installTheme() {
            try {
                Resources r = Resources.open("/res/eyeOfAfrica.res");
                UIManager.getInstance().setThemeProps(r.getTheme("eyesOfAfrica"));
              } catch (IOException ioe) {
                System.out.println("Couldn't load theme.");
             }
        }

    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnAbout ) {
            frmAbout.show();
        }
        else if (ae.getSource() == btnPlaceHolder) {                      
            frmPlaceHolder.show();
        }
        else if (ae.getSource() == btnTellUs) {                      
            frmTellUs.show();
        }
        else if (ae.getSource() == cmdBack) {
            frmHome.show();
        }
        else if (ae.getCommand() == cmdExit) {
            notifyDestroyed();
        }
    }
    
    private void createUI(){      
      cmdBack = new Command("Back");      
      cmdExit = new Command("Exit");
      lblTicker = new Label("... this application is brought to you by the latter A and the letter P ...");
         
      
       min=0;
       max=proverbs[0].length-1;
      
      
      
      frmHome=createHome();
      frmPlaceHolder=createPlaceHolder();
      frmAbout=createAbout();
      frmTellUs=createTellUs();
    }
    
    public Form createPlaceHolder(){
        final Form f=new Form("... Place Holder ...");
        if (frmPlaceHolder==null){
         f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));   
         f.addComponent(new Label("Choose Back to return to the home screen."));
          
         f.addCommand(cmdBack);
         f.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.
         f.setTransitionInAnimator(Transition3D.createCube(300, false));
         f.setTransitionOutAnimator(Transition3D.createCube(300, true));  
        }
        return f;
    }
    
    public Form createTellUs(){
        final Form f=new Form("Let us know");
        if (frmTellUs==null){
         f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
         
         
         TextArea lblOne = new TextArea("Tell us a proverb that resonates with you");
         lblOne.setEditable(false);
         TextArea txtProverb = new TextArea("",3,20);
         TextArea lblTwo=new TextArea("Which part of Africa?");
         lblTwo.setEditable(false);
         TextArea txtLocation = new TextArea("");
         TextArea lblThree= new TextArea("If we like your contribution, it will be included in the next version of the application.");
         f.addComponent(lblOne);
         f.addComponent(txtProverb);
         f.addComponent(lblTwo);
         f.addComponent(txtLocation);
         f.addComponent(lblThree);
         
         f.addComponent(new Button("Send"));
          
         f.addCommand(cmdBack);
         f.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.
         f.setTransitionInAnimator(Transition3D.createCube(300, true));
         f.setTransitionOutAnimator(Transition3D.createCube(300, false));  
        }
        return f;
    }
    
    public Form createAbout(){
        final Form f=new Form("About This");
        if (frmAbout==null){
         f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));   
         
         
         /*lblTicker.setPropertyValue("BGColor", "#ffffff");
         lblTicker.getStyle();
         warningAlert = new Alert("Check", lblTicker.getStyle(), null, AlertType.CONFIRMATION);
         display.setCurrent(warningAlert, searchForm);
           */ 
         f.addComponent(lblTicker);
         lblTicker.startTicker(100, true);
         
         TextArea txtDisc= new TextArea("This collection of proverbs has been sourced from mamy places");
         txtDisc.setEditable(false);
         f.addComponent(txtDisc);
         
         TextArea txtTotMem=new TextArea("Phone Memory\n"+ Runtime.getRuntime().totalMemory());
         txtTotMem.setEditable(false);
         f.addComponent(txtTotMem);
         
         TextArea txtFreeMem=new TextArea("Free Memory(Phone)\n" + Runtime.getRuntime().freeMemory());
         txtFreeMem.setEditable(false);
         f.addComponent(txtFreeMem);
         
            try {
                  rs=RecordStore.openRecordStore("Space",true); 
            }
            catch(Exception e) {
            }
            try { 
                  TextArea txtRSFree=new TextArea("RMS Free Memory\n" + rs.getSizeAvailable());
                    txtRSFree.setEditable(false);
                    f.addComponent(txtRSFree);
                  
            }
            catch(Exception e) { 
            }
            
         f.addCommand(cmdBack);
         f.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.
         f.setTransitionInAnimator(Transition3D.createCube(300, false));
         f.setTransitionOutAnimator(Transition3D.createCube(300, true));  
        }
        return f;
    }
    
    public Form createHome(){
       rand=new Random();
        final int intRand = rand.nextInt(max - min + 1) + min;
        intProverb = intRand;
        String pText;
       
        final Form f=new Form("African Proverbs");
        if (frmHome==null){
         //f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));  
         f.setLayout(new BorderLayout());  
         //pText = proverbs[0][intRand];
         pText=Integer.toString(proverbs[0].length);
         f.addComponent(BorderLayout.NORTH, new Label( pText+ " proverbs available"));
         
        
         pText = proverbs[0][intRand];
         final TextArea provText=new TextArea(pText);
         provText.setEditable(false);
         pText = proverbs[1][intRand];
         final Label provOrigin=new Label(pText);
         
         final Container provBlock=new Container(new BoxLayout(BoxLayout.Y_AXIS));
         provBlock.addComponent(provText);
         provBlock.addComponent(provOrigin);
         f.addComponent(BorderLayout.CENTER,provBlock);
         
         
         final Button pushButton = new Button("Again!");
            pushButton.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ae) {
                
                  int intRand = rand.nextInt(max - min + 1) + min;
                pushButton.setText("Again!");
                
                  //provBlock.removeComponent(provOrigin);
                //provBlock.layoutContainer();
               intProverb=intRand;
                provText.setText(proverbs[0][intRand]);
                
                //provBlock.addComponent(provOrigin);
                provOrigin.setText(proverbs[1][intRand]);
                //provBlock.layoutContainer();
                //f.layoutContainer();
                f.revalidate();
              }
            });
            
          final Button nextButton = new Button(">>");
            nextButton.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ae) {
                  if (intProverb<=max-1){
                    intProverb++;
                    provText.setText(proverbs[0][intProverb]);
                    provOrigin.setText(proverbs[1][intProverb]); 
                    f.revalidate();
                  }
              }
            });
            final Button prevButton = new Button("<<");
            prevButton.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ae) {
                  if (intProverb>1){
                    intProverb--;
                    provText.setText(proverbs[0][intProverb]);
                    provOrigin.setText(proverbs[1][intProverb]); 
                    f.revalidate();
                  }
              }
            });
         
         
         Container btnBlock = new Container(new GridLayout(2,3));
         btnBlock.addComponent(prevButton);   
         btnBlock.addComponent(pushButton);
         btnBlock.addComponent(nextButton);   
         
        

         btnTellUs = new Button("Tell Us");
         btnTellUs.addActionListener(this);
         btnBlock.addComponent(btnTellUs); 
         
         btnAbout = new Button("About");
         btnAbout.addActionListener(this);
         btnBlock.addComponent(btnAbout);
         
         f.addComponent(BorderLayout.SOUTH, btnBlock);
          
         f.addCommand(cmdExit);
         f.addCommandListener(this); // Use setCommandListener() with LWUIT 1.3 or earlier.
         }
        return f;
    }
    

   
}