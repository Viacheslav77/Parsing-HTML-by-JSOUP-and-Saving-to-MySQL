package parsinghtml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import parsinghtml.db.JavaToMySQL;
import parsinghtml.entity.Product;

public class ParsingProduct {
    
    // the fields by seting manual
    private static long startSection_id = 40;
    private static long startId = startSection_id;
    
    private static int category = 3;
    private static long startPriceId = startSection_id;
    private static int currency = 1;
    
    private static Document doc;
    
    public static void makeContents ( List<ResourcesRuEn> resources){
        
        long id = startId;
        long section_id = startSection_id;
        long priceId = startPriceId;
        
        for(ResourcesRuEn resource :resources){
             System.out.println("\nName Product = " + resource.getNameRu() + "\n" );
              makeContent(resource.urlRu, resource.urlEn, id++, section_id++, priceId++);
        }
    }
    
    public static void makeContent(String urlRu, String urlEn, long id, long section_id, long priceId){
       
       Product product = new Product();
       
       product.setSection_id(section_id);
       product.setId(id);
       
       product.setCategoryId(category);
       product.setCurrency_id(currency);
       product.setPriceId(priceId);
       
       Random random = new Random();
       
       product.setRating(random.nextInt(7));
       product.setVoteCount(random.nextInt(200));
       
        try {
            makeFromThisUrlEn(urlEn, product, id);
            makeFromThisUrlRu(urlRu, product);
        } catch (Exception e) {
           
            try {
                System.out.println("\nПопытка 2 = " + e);
                imitateLoading();
                
                makeFromThisUrlEn(urlEn, product, id);
                makeFromThisUrlRu(urlRu, product);  
            
            } catch (Exception ex) {
            
                try {
                    System.out.println("\nПопытка 3 = " + ex);
                    imitateLoading();
                    
                    makeFromThisUrlEn(urlEn, product, id);
                    makeFromThisUrlRu(urlRu, product);  
                
                } catch (Exception exz) {
                    System.out.println("\nПропускаю = " + exz + "\n");
                    
                }
            }             
        }

       System.out.println(product);
        
       JavaToMySQL.insertThisProductAllField(product); 
    }

    public static void makeFromThisUrlEn (String url, Product product, long id ){
             
        doc = null;
        
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException ex) {
            Logger.getLogger(ParsingHTML.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String forNameEn = doc.getElementsByClass("block-product-detail").get(0).getElementsByTag("h1").get(0).text();
        if(forNameEn.length()>20){
             if(forNameEn.contains("Forever")){
                  product.setNameEn(forNameEn.replace("Forever ", "")); 
             }
        }
        else {
             product.setNameEn(forNameEn);
        }
        
        product.setShortDescriptionEn(doc.getElementsByClass("block-product-detail").get(0).getElementsByTag("p").get(0).text() + doc.getElementsByClass("block-product-detail").get(0).getElementsByTag("p").get(1).text());        
        product.setDescriptionEn(doc.getElementsByClass("tab-content").get(0).getElementsByTag("div").get(0).toString().replaceAll("<div role=\"tabpanel\" class=\"tab-pane active\" id=\"overview-a1P0V000003buUgUAI\"> ", ""));       
        product.setLinkImage(doc.getElementsByClass("product-gallery hidden-xs").get(0).getElementsByTag("img").attr("src").toString());
       
        try {
     
            SaveImageFromUrl.saveImageToProduct(product);
       
        } catch (IOException ex) {
            Logger.getLogger(ParsingProduct.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
        public static void makeFromThisUrlRu (String url, Product product){
             
        doc = null;
        
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException ex) {
            Logger.getLogger(ParsingHTML.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        String s = new Element (doc.title()).toString();
        
        String [] forCategory = s.split(":");
        String  forNameRu = forCategory[1].split(">")[0];       
        
        if(forNameRu.length()>20){
             if(forNameRu.contains("Форевер")){
                 product.setNameRu(forNameRu.replace("Форевер ", "")); 
             }
        }
        else {
             product.setNameRu(forNameRu);
        }

        String forDescriptionRu = doc.getElementsByClass("product-description gk-product-tab").get(0).toString();
        String [] forVendorCode = doc.getElementsByClass("scu").get(0).text().split("№");        
        String [] forPrice = doc.getElementsByClass("product_price").get(0).text().split("грн.");       
        
        if(forPrice[0].contains(",")){
             product.setPrice(Double.valueOf(forPrice[0].replaceAll(",", "")));
        }
        else {
             product.setPrice(Double.valueOf(forPrice[0]));
        }
        
        product.setVendorCode(Integer.valueOf(forVendorCode[1]));
       
        product.setShortDescriptionRu(doc.getElementsByClass("short_desc").get(0).text());
        product.setDescriptionRu(forDescriptionRu.replaceAll("product-description gk-product-tab", "product-description"));
         
        }
    
    private void printContent() {
                
        List <Element> contents = new ArrayList<>();
       
        String [] nameClassOnPage = {"category_name","short_desc", "product-description gk-product-tab", "main-image", "additional-images"};
            for (String name: nameClassOnPage)
                doc.getElementsByClass(name).forEach (content-> contents.add(content));

            contents.forEach(content->  System.out.println(content));
    }
     private static void imitateLoading() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            
        }
    }
}
