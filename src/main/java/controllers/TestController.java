package controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tanat on 6/16/2016.
 */

/**
 * RestController tells that this controller serve data
 * @Controller is used to mark classes as Spring MVC Controller.
    @RestController is a convenience annotation that does nothing more than adding the @Controller and @ResponseBody annotations (see: Javadoc)
 */
@RestController
public class TestController {


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> saveAccount() {

        return (new ResponseEntity<>("test", null, HttpStatus.OK));
    }



}
