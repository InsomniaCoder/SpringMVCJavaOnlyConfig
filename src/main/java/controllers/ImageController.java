package controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Tanat on 6/19/2016.
 */
@RestController
public class ImageController {

    final String imageDirectoryPath = "/webapp/pic";

    //    @RequestMapping(value = "/image", method = RequestMethod.GET, consumes = "application/json")
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public ResponseEntity<String> saveAccount() throws IOException {

        ImageInputStream is = ImageIO.createImageInputStream(new File(imageDirectoryPath + "/multipage.tiff"));

        if (is == null || is.length() == 0) {
            // handle error
            return (new ResponseEntity<>("", null, HttpStatus.INTERNAL_SERVER_ERROR));
        }

        Iterator<ImageReader> iterator = ImageIO.getImageReaders(is);

        if (iterator == null || !iterator.hasNext()) {
            throw new IOException("Image file format not supported by ImageIO: ");
        }

        // We are just looking for the first reader compatible:
        ImageReader reader = (ImageReader) iterator.next();
        iterator = null;

        reader.setInput(is);

        //get number of pages
        int nbPages = reader.getNumImages(true);
        //read
        //reader.read(numPage)

        return (new ResponseEntity<>("this tiff has :  "+nbPages, null, HttpStatus.OK));
    }
}
