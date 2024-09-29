import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.io.FileNotFoundException;

public class RoyalFamily
{
    public static void main(String[] args)
    {
        //Define a variable to store the root node
        TNode<String> root = null;
        
        TNode<String> parent = null;
        TNode<String> child = null;
        File file = new File("data.txt");
        try{
            Scanner input = new Scanner(file);
            root = new TNode<String>(input.nextLine());
            int count = 0;
            
            while(input.hasNextLine()){
                
                String line = input.nextLine();
                String[] splitLine = line.split(">"); //Split into lines
                
                for(int j = 0; j < splitLine.length; j++){
                    splitLine[j] = splitLine[j].trim();
                }
                
                for(int i = 0; i < splitLine.length; i++)
                {
                    if(splitLine[i] == null)
                        break;
                        
                    if(count == 0){
                        parent = new TNode<String>(splitLine[i]);
                        if(find(root, splitLine[i]) != null)
                            parent = find(parent, splitLine[i]);
                        count++;
                    }
                    
                    else if(count == 1){
                        child = new TNode<String>(splitLine[i]);
                        if(find(root, splitLine[i]) != null)
                            child = find(parent, splitLine[i]);
                        count++;
                    }
                    
                    else
                        {
                            parent.addChild(child);
                            count = 0;
                        }
                }
            }
        }
        catch(FileNotFoundException e){
        }
    }
    
    /**
     *  @return node if its data matches name, or return a child node with data that matches name
     */
    public static TNode<String> find(TNode<String> node, String name)
    {
        if(node.getData().equals(name))
            return node;
        for(TNode<String> child: node.getChildren())
            {
              TNode<String> result = find(child, name);
              if(result!=null)
                  return result;
            }
        return null;
    }
    
    /**
     *  @return a String containing the path from the root node to the specified node, separeated by ->
     */
    public static String getPath(TNode<String> node)
    {
        String path = "";
        if(node.getParent() == null)
            return path += node.getData();
        else
            return getPath(node.getParent()) + (path += " -> " + node.getData());
    }
}