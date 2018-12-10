package parsinghtml.entity;

import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Setter 
@Getter
public  class Product  {
  
    private Long id;
    private long section_id;
    
    private String nameRu;
    private String nameEn;
    
    private int vendorCode;  
    private  int categoryId;  
    
    private String shortDescriptionRu; 
    private String shortDescriptionEn;
    private String descriptionRu;  
    private String descriptionEn;  
    
    private String linkImage;
    private byte[] image;    
    
    private List <String> linkImageList;
    private List <byte[]> imagesList;   
    
    private Long priceId;
    private double price;
    
    private int rating;
    private int voteCount; 
    private Set votes; 
    
    private int currency_id;
    

    @Override
    public String toString() {
        return "Product{" + "id = " + id + ", nameRu = " + nameRu + ", nameEn = " + nameEn   
                + ", category_id = " + categoryId + " Price = " + price + ", vendorCode = " + vendorCode 
                +", shortDescriptionRu = " + !shortDescriptionRu.isEmpty()
                +", descriptionRu = " + !descriptionRu.isEmpty()
                +", shortDescriptionEn = " + !shortDescriptionEn.isEmpty()
                +", descriptionEn = " + !descriptionEn.isEmpty()
                + ", linkImage = " + !linkImage.isEmpty() +" }" ;
    }


    
    
}
