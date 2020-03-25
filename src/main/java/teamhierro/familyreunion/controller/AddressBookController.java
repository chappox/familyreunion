package teamhierro.familyreunion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import teamhierro.familyreunion.model.AddressBook;
import teamhierro.familyreunion.model.ErrorMessage;
import teamhierro.familyreunion.service.AddressBookService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class AddressBookController {

    @Autowired
    AddressBookService service;

    @GetMapping("/addressbook/single/add/")
    public String showAddForm(AddressBook addressBook, ErrorMessage errorMessage) {
        errorMessage.setErrorcode(0);
        errorMessage.setErrormessage("");
        return "addressbook/showAddForm";
    }

    @PostMapping("/addressbook/single/add/")
    public String validateAddForm(@Valid AddressBook addressBook, ErrorMessage errorMessage, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {

            if (!service.addAddress(addressBook.getFirstname().substring(0, 1).toUpperCase() + addressBook.getFirstname().substring(1)
                    , addressBook.getLastname().substring(0, 1).toUpperCase() + addressBook.getLastname().substring(1)
                    , addressBook.getStreetaddress()
                    , addressBook.getCity().substring(0, 1).toUpperCase() + addressBook.getCity().substring(1)
                    , addressBook.getState().substring(0, 1).toUpperCase() + addressBook.getState().substring(1)
                    , addressBook.getCountry().substring(0, 1).toUpperCase() + addressBook.getCountry().substring(1)
                    , addressBook.getZipcode())) {
                errorMessage.setErrorcode(1000);
                errorMessage.setErrormessage("It seems that person has already been added to the Address Book!");
                return "addressbook/showAddForm";
            } else {

                return "addressbook/showAddFormSuccess";
            }

        }
        return "addressbook/showAddForm";
    }

    @GetMapping("/addressbook/single/remove/")
    public String showRemoveForm(AddressBook addressBook, ErrorMessage errorMessage, Model model) {
        // pull records from address book
        List<AddressBook> dataPull = service.findAll();
        // add them to view, then display
        model.addAttribute("dataPull", dataPull);
        return "/addressbook/showRemoveForm";
    }

    @GetMapping("/addressbook/single/remove/{id}")
    public String validateRemoveForm(@PathVariable("id") Integer id, AddressBook addressBook, ErrorMessage errorMessage, Model model) {
        // clear error handling
        errorMessage.setErrorcode(0);
        errorMessage.setErrormessage("");
        // pull records from address book
        List<AddressBook> dataPull = service.findAll();
        // add them to view, then display
        model.addAttribute("dataPull", dataPull);

        // check if id is valid
        if (service.checkifIdExists(id)) {
            service.removeAddress(id);
            // pull records from address book
            dataPull = service.findAll();
            // add them to view, then display
            model.addAttribute("dataPull", dataPull);
            return "/addressbook/showRemoveForm";
        }
        errorMessage.setErrorcode(2000);
        errorMessage.setErrormessage("The record cannot be found in the database!");
        return "/addressbook/showRemoveForm";
    }

    @GetMapping("/addressbook/single/edit/")
    public String showEditForm(AddressBook addressBook, ErrorMessage errorMessage, Model model) {
        // pull records from address book
        List<AddressBook> dataPull = service.findAll();
        // add them to view, then display
        model.addAttribute("dataPull", dataPull);
        return "/addressbook/showEditForm";
    }

    @GetMapping("/addressbook/single/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, AddressBook addressBook, ErrorMessage errorMessage, Model model) {
        // clear error handling
        errorMessage.setErrorcode(0);
        errorMessage.setErrormessage("");

        // check if id is valid
        if (service.checkifIdExists(id)) {
            // pull records from address book
            Optional<AddressBook> dataPull = service.findById(id);
            // add them to view, then display
            model.addAttribute("dataPull", dataPull);

            return "/addressbook/showEditFormFull";
        }
        return "showEditForm";
    }

    @PostMapping("/addressbook/single/edit/{id}")
    public String validateEditForm(@PathVariable("id") Integer id, @Valid AddressBook addressBook, ErrorMessage errorMessage, Model model) {
        // clear error handling
        errorMessage.setErrorcode(0);
        errorMessage.setErrormessage("");

        // check if id is valid
        if (service.checkifIdExists(id)) {
            service.editAddress(addressBook.getId()
                    , addressBook.getFirstname()
                    , addressBook.getLastname()
                    , addressBook.getStreetaddress()
                    , addressBook.getCity()
                    , addressBook.getState()
                    , addressBook.getCountry()
                    , addressBook.getZipcode());
            // pull records from address book
            List<AddressBook> dataPull = service.findAll();
            // add them to view, then display
            model.addAttribute("dataPull", dataPull);
            return "/addressbook/showEditForm";
        }
        return "/addressbook/showEditForm";
    }

    @GetMapping("/addressbook/single/view/{id}")
    public String showSingleView(@PathVariable("id") Integer id, AddressBook addressBook, ErrorMessage errorMessage, Model model) {
        // check if id exists
        if(!service.checkifIdExists(id)) {
            errorMessage.setErrorcode(1000);
            errorMessage.setErrormessage("Record does not exist");
            return "/addressbook/showViewAll";
        }
        // if it exists, extract from list
        List<AddressBook> dataPull = service.findAll();
        dataPull.stream().filter(dataRecord -> dataRecord.getId() == id).findFirst().ifPresent(dataRecord -> model.addAttribute("dataPull", dataRecord));
        // add record to view
//        model.addAttribute("dataPull", singleRow);
        return "/addressbook/showViewSingle";
    }

}
