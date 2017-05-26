import java.util.LinkedList;
import java.awt.Color;
import java.util.HashMap;
/**
 * This class is part of the "Draw Demo" application. 
 * Class DrawDemo - enable a user to draw on a canvas using a 'pen'.
 * 
 * This class holds an enumeration of all command words known to the application.
 * It is used to recognise commands as they are typed in.
 *
 * @author Thulani Sibanda
 * @version 2016.12.07
 */

public class CommandWords
{
    //holds the colous
    private HashMap<String, Color> colors;
    //holds the pens
    private HashMap<String, Pen> pens;
    //pen in use
    private Pen currentPen;
    //holds the help infomation
    private HashMap<String, String> help;
    // Source of user commands.
    private InputReader reader;
  
    /**
     * Constructor - creates the HashMaps and InputReader.
     */
    public CommandWords()
    {
      colors = new HashMap<>();
      pens = new HashMap<>();
      help = new HashMap<>();
      reader = new InputReader();
      
      //put colors into HashMap
      colors.put("blue", Color.BLUE);
      colors.put("green", Color.GREEN);
      colors.put("red", Color.RED);
      colors.put("magenta", Color.MAGENTA);
      colors.put("black", Color.BLACK);
      colors.put("yellow", Color.YELLOW);
      
              
      //put help into HashMap
      help.put("up", "up: Raise the pen off the canvas so that movements do not cause drawing. e.g. up");
      help.put("down", "down: Lower the pen onto the canvas so that movements cause drawing. e.g. down");        
      help.put("pen", "pen: Create a new Pen that will be referred to with the given name.\nThe name can only be one word. e.g. pen john.");
      help.put("select", "select: Select the pen with the given name as the one to which future commands will apply.\ne.g. select john.\nPen must have been created previously");
      help.put("delete", "delete: Delete the pen with the given name.\n e.g. delete john.\nPen must have been created previously");
      help.put("move", "move: Move the pen the specified distance in its direction of movement.\nDistance must be a single integer value. e.g. move 25");
      help.put("turn", "turn: Change the direction of movement by angle degrees.\nAngle must be a single integer e.g. turn 90");        
      help.put("moveto", "moveto: Move to co-ordinate position x,y on the canvas. \nBoth x and y must be sinlge integers. e.g. moveto 90 35");
      help.put("turnto", "turnto: Change the direction of movement to angle degrees.\nAngle must be a single integer e.g. turnto 180");

    }

    /**
     * Raise the pen off the canvas so that movements do not cause drawing.
     */
    public void up(LinkedList<String> command)
        {
        if(command.size() == 1) {
        currentPen.penUp();
        System.out.println("Pen is up");
       }
       else {
           printSpecificHelp(command.get(0));
        }
        } 
        
    /**
     * Lower the pen onto the canvas so that movements cause drawing.
     */        
        
    public void down(LinkedList<String> command)
        {
        if(command.size() == 1) {
        currentPen.penDown();
        System.out.println("Pen is down");
       }
       else {
         printSpecificHelp(command.get(0));
        }
        }           

    /**
     * Move the pen the specified distance in its direction of movement.
     */      
    public void move(LinkedList<String> command)
        {
                                       
         if(command.size() == 2 && reader.isAnInteger(command.get(1))) {
           currentPen.move(reader.convertToInteger(command.get(1))); 
         }
         else {
             printSpecificHelp(command.get(0));
         }
        }

    /**
     * Move to co-ordinate position x,y on the canvas.
     */        
        public void moveto(LinkedList<String> command) {
         if(command.size() == 3 && reader.isAnInteger(command.get(1)) && reader.isAnInteger(command.get(2))) {
          currentPen.moveTo(reader.convertToInteger(command.get(1)), reader.convertToInteger(command.get(2)));
          }
         else { 
             printSpecificHelp(command.get(0));
          }            
        }
        
    /**
     * Move the pen the specified distance in its direction of movement.
     */        
        public void turn(LinkedList<String> command) {
            if(command.size() == 2 && reader.isAnInteger(command.get(1))) {
             currentPen.turn(reader.convertToInteger(command.get(1)));  
             }
             else { 
                printSpecificHelp(command.get(0));
             }          
        }

    /**
     * Change the direction of movement by angle degrees.
     */        
        public void turnto(LinkedList<String> command) {
          if(command.size() == 2 && reader.isAnInteger(command.get(1))) {
           currentPen.turnTo(reader.convertToInteger(command.get(1)));  
             }
           else { 
               printSpecificHelp(command.get(0));
            }       
        }       

    /**
     * Change the drawing colour.
     */        
        public void colour(LinkedList<String> command) {
          if(command.size() == 2 && colors.containsKey(command.get(1))){
           currentPen.setColor(colors.get(command.get(1)));
           System.out.println("The colour is now " + command.get(1));
           } 
           else {
           helpColour();
           }       
        }

    /**
     * Create a new Pen that will be referred to with the given name.
     */        
        public void pen(LinkedList<String> command, Canvas canvas) {
          if(command.size() == 2 && !pens.containsKey(command.get(1))) {
           currentPen = new Pen(0, 0, canvas);
           pens.put(command.get(1), currentPen);
           currentPen.setColor(Color.BLACK);
           currentPen.penDown();
           System.out.println("new pen called " + command.get(1) + " created. \n Pen position: " + currentPen.getX() + "," + currentPen.getY());
           System.out.println("The pen is " + (currentPen.isPenDown() ? "down" : "up") + ".");
          }
          else if(command.size() == 2 && pens.containsKey(command.get(1))) {
           System.out.println("Pen name already in use. To select this pen type:\n select " + command.get(1));
           }
           else{
           printSpecificHelp(command.get(0));
                                
           }      
        } 
        
    /**
     * Select the pen with the given name as the one to which future commands will apply.
     */
        public void select(LinkedList<String> command) {
          if(command.size() == 2 && pens.containsKey(command.get(1))) {
           currentPen = pens.get(command.get(1));
           System.out.println(command.get(1) + " selected");
           }
           else{
           printSpecificHelp(command.get(0));
           }    
        }        

    /**
     * Delete the pen with the given name. The name will no longer be in use.
     */        
        public void delete(LinkedList<String> command) {
         if(command.size() == 2 && pens.containsKey(command.get(1))) {
          pens.remove(command.get(1));
          currentPen = null;
          System.out.println(command.get(1) + " has been removed");
           }
          else{
          printSpecificHelp(command.get(0));
           }
                            
       }
       
     
           /**
     * Prints a general help statement.
     */
    
        public void printGeneralHelp() 
       {
        System.out.println("Welcome to the Drawing Tool.\nTo begin please create a pen using the pen command.");
        System.out.println("The available commands are:");
        for (String key : help.keySet()) {
         System.out.print(key + " ");
        } 
        System.out.print("\n");
        System.out.println("For help with a command please type the command. e.g pen.\nType bye to exit.");
       }
       
    /**
     * Specific help for the colour method.
     */       
           public void helpColour()
    {
     System.out.println("Colour: Change the drawing colour to be colour-name. e.g. colour blue.\ncolour-name must be one of the following:");
     for (String key : colors.keySet()) {
                System.out.print(key + " ");
                                  }    
     System.out.println("");
           
        }
        
        
    /**
     * prints assistance with all available options seperately and in more detail
     */
    
        public void printSpecificHelp(String command) 
       {
           System.out.println(help.get(command));

       }

    /**
     * Checks whether the pen is a null value..
     */       
       public boolean isPenNull()
       {
           if(currentPen == null){
               return true;
            }
            else {
                return false;
            }
    }

    /**
     * Returns what the user has typed.
     */    
    public LinkedList<String> getInput()
    {
        LinkedList<String> command = reader.getInput();
        return command;
    }
    

}
 
