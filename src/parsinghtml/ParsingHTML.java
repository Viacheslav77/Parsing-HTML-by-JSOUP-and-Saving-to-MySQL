package parsinghtml;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ParsingHTML {
   
    public static void main(String[] args) throws IOException  {
          
        MakeListResourcesForeverRu resources = new MakeListResourcesForeverRu();
        
        resources.getDocForeverBySite();
        resources.getDocForeverByFile();
        resources.buildMapResourcesForever();
        resources.printMapForevers();
 
        List<ResourcesRuEn> resourcesRuEn = new ArrayList<>();
    
        ParsingProduct.makeContents(resourcesRuEn);
        

    }
      
       
    
}
