package parsinghtml;

import lombok.*;


@Data
public class ResourcesRuEn {
    
    String nameRu;
    String urlRu;
    String urlEn;  

    public ResourcesRuEn(String nameRu, String urlRu, String urlEn) {
        this.nameRu = nameRu;
        this.urlRu = urlRu;
        this.urlEn = urlEn;
    }
    
    
   
}
