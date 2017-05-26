
import java.util.LinkedList;
/**
 * Class DrawDemo - enable a user to draw on a canvas
 * using a 'pen'.
 *
 * @author Thulani Sibanda
 * @version 2016.12.07
 */

public class DrawingTool
{
    // Where to draw.
    private Canvas canvas;
    //holds all the valid commands words
    private CommandWords commandWords;


    /**
     * Prepare to draw on a canvas of default size.
     */
    public DrawingTool()
    {
        this(500, 400);
        
    }
    
    
    /**
     * Prepare to draw on a canvas of specified size.
     * The pen starts down at position (0, 0) and its
     * colour is black.
     * @param width The canvas width.
     * @param height The canvas height.
     */
    public DrawingTool(int width, int height)
    {
        canvas = new Canvas("Drawing Program", width, height);
        commandWords = new CommandWords();
        draw();
       
       
    }
    
  
    /**
     * Allow the user to draw on the canvas by typing commands.
     * Each command is in the help HashMap.
     */
    public void draw()
      {
        commandWords.printGeneralHelp();
        boolean finished = false;
        while(!finished) {
            LinkedList<String> command = commandWords.getInput();
            if(!command.isEmpty()) {
             String firstWord = command.get(0);
             if(!commandWords.isPenNull()){ 
               switch(firstWord) {
                   case "bye":
                   if(command.size() == 1){
                   finished = true;
                  }
                   break;
                    
                   case "help":
                    commandWords.printGeneralHelp();
                    break;
                    
                    case "pen":
                    commandWords.pen(command, canvas);
                    break;
                    
                    case "select":
                    commandWords.select(command);       
                    break; 

                    case "delete":
                    commandWords.delete(command);
                    break;    
                    
                    case "move":                          
                    commandWords.move(command);
                    break;
                     
                      case "moveto":
                      commandWords.moveto(command);         
                      break;
                    
                      case "turn":
                      commandWords.turn(command);
                      break;
                    
                      case "turnto":
                      commandWords.turnto(command);
                      break;
                    
                      case "colour":
                      commandWords.colour(command);
                      break;

                      case "down":
                      commandWords.down(command);
                      break;
                        
                      case "up":
                      commandWords.up(command);
                      break;
                    
                    
                    default:
                    System.out.println("Unrecognised command.\n Type help for assistance.");
                    break; 
                        
                    }  
                    
                    }
                    else {
                   switch(firstWord) {
                       
                     case "bye":
                     if(command.size() == 1){
                      finished = true;
                     }
                    break;
                    case "help":
                    commandWords.printGeneralHelp();
                    break;
                    
                    case "pen":
                    commandWords.pen(command, canvas);
                    break;
                    
                    case "select":
                    commandWords.select(command);       
                    break; 

                    case "delete":
                    commandWords.delete(command);
                    break; 
                    
                    default:
                    System.out.println("Please create or select a pen first.\nType help for assistance.");
                    break; 
                                            
                        }
                        }

                    }
                    else {
                        System.out.println("Empty command, Please type valid command");
                     }

          }
        
         System.out.println("Goodbye.");
    
      
   }

}

  
  
  
  




