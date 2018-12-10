package parsinghtml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class MakeListResourcesForeverEn {
    
    private String url = "http://url";    
    private Map <String,String> urls = new HashMap <>() ;     
    private String fileSaveName = "e://file.html";   
    private Document doc;    
    private Elements contents = new Elements();

    private Map <String,  List <Element> > mapForevers = new HashMap <>();
    
    public void getDocForeverBySite() throws IOException{
       doc = Jsoup.connect(url).get();
       doc.toString();
        try (PrintWriter writer = new PrintWriter( fileSaveName , "UTF-8")) { // тут путь куда мы сохраняем наш html файл
            // тут путь куда мы сохраняем наш html файл
            writer.println(doc.toString());
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
        }

    }
    
    public void getDocForeverBySites() {
        urls.forEach((k,v) -> {
            try {
                doc = Jsoup.connect(v).get();
            } catch (IOException ex) {
                Logger.getLogger(MakeListResourcesForeverEn.class.getName()).log(Level.SEVERE, null, ex);
            }
            Elements contentsURL = new Elements();
             doc.getElementsByClass("block-product").forEach (content-> contentsURL.add(content));
            
            mapForevers.put(k, contentsURL);
           
        });
        mapForevers.forEach((k,v)-> {
            System.out.println("\nCategory = " + k + "\n");
            v.forEach((content) -> {
                System.out.println( "val = " + content.getElementsByTag("h2").text() +
                        "  href = " + content.getElementsByTag("a").attr("href"));
                
            });
        });
                    
    }
    
    public void getDocForeverByFile() throws IOException{
        
        File input = new File(fileSaveName);
        doc = Jsoup.parse(input, "UTF-8", "");
    }
    
    public void buildMapResourcesForever (){
        
              doc.getElementsByClass("block-product").forEach (content-> contents.add(content));
    }
    
    public void printMapForevers (){
            
        try {
    
            contents.forEach((content) -> {
                System.out.println( "val = " + content.getElementsByTag("h2").text() +
                        "  href = " + content.getElementsByTag("a").attr("href"));
            });
      
        } catch (Exception e) {
        }
    }
}