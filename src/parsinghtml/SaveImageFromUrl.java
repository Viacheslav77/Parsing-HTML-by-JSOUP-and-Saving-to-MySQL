package parsinghtml;

import java.io.ByteArrayOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.net.URL;
import parsinghtml.entity.Product;

public class SaveImageFromUrl {

public static  void saveImageToProduct(Product product) throws IOException {
    URL url = new URL("http:"+product.getLinkImage());
    InputStream is = url.openStream();
    ByteArrayOutputStream bOut = new ByteArrayOutputStream();

    byte[] b = new byte[2048];

    int length;

    while ((length = is.read(b)) != -1) {
        bOut.write(b, 0, length);
    }
    product.setImage(bOut.toByteArray());
    
    is.close();
    bOut.close();
}

}
