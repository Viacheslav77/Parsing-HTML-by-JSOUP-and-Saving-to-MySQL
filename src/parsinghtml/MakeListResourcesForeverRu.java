package parsinghtml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class MakeListResourcesForeverRu {
    
    private String url = "https://url";
    private String fileSaveName = "e://file.html";
    
    private Document doc;
    
    private Elements categories = new Elements();
    private Elements contents = new Elements();
    private Elements contentsCategory = new Elements();

    private Map <Element,  List <Element> > mapForevers = new HashMap <>();
    
    public void getDocForeverBySite() throws IOException{
       doc = Jsoup.connect(url).get();
       doc.toString();
        try (PrintWriter writer = new PrintWriter( fileSaveName , "UTF-8")) { // тут путь куда мы сохраняем наш html файл
            // тут путь куда мы сохраняем наш html файл
            writer.println(doc.toString());
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
        }

    }
    
    public void getDocForeverByFile() throws IOException{
        
        File input = new File(fileSaveName);
        doc = Jsoup.parse(input, "UTF-8", "");
    }
    
    public void buildMapResourcesForever (){
      
        doc.getElementsByClass("category   ").forEach (category-> categories.add(category));
        doc.getElementsByClass("third").forEach (content-> contents.add(content));
        
        int i=-1;
        
        for (Element content: contents  ){
            if( i == -1) {       
                i++;
                continue;           
            }
            if(content.getElementsByTag("a").text().isEmpty()) {               
                mapForevers.put(categories.get(i),new ArrayList<>(contentsCategory));         
                i++; 
                contentsCategory.clear();
            }
            else 
                contentsCategory.add(content);           
        };

    }
    
    public void printMapForevers (){
            
        try {
            categories.forEach((category) -> {

            System.out.println("\nCategory = " + category.getElementsByClass("cat_title openca").get(0).attr("title") + "\n");
          
            mapForevers.get(category).forEach((content) -> {
                System.out.println( "val = " + content.getElementsByTag("a").text() +
                        "  href = " + content.getElementsByTag("a").attr("href"));
            });
        });
        } catch (Exception e) {
        }
            
    }
    
    
    
   
        
       
}
