package pl.edu.wszib.magazyn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.magazyn.services.IStorageService;
import pl.edu.wszib.magazyn.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class StorageController {

    @Autowired
    IStorageService storageService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/addToStorage/{id}", method = RequestMethod.GET)
    public String addToStorage(@PathVariable int id){
        if (!this.sessionObject.isLogged()){
            return "redirect:/login";
        }
        this.storageService.addProductByIdToStorage(id);
        return "redirect:/main";
    }

    @RequestMapping(value = "/deleteFromStorage/{id}", method = RequestMethod.GET)
    public String deleteFromStorage(@PathVariable int id){
        if (!this.sessionObject.isLogged()){
            return "redirect:/login";
        }
        this.storageService.deleteProductByIdFromStorage(id);
        return "redirect:/main";
    }
}
