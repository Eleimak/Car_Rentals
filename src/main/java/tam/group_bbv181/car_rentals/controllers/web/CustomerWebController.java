package tam.group_bbv181.car_rentals.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tam.group_bbv181.car_rentals.forms.CustomerForm;
import tam.group_bbv181.car_rentals.model.*;
import tam.group_bbv181.car_rentals.services.customer.impls.CustomerServiceImpl;
import tam.group_bbv181.car_rentals.services.login.impls.LoginServiceImpl;
import tam.group_bbv181.car_rentals.services.person.impls.PersonServiceImpl;
import tam.group_bbv181.car_rentals.services.rentcar.impls.RentCarServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/CarRentals")
@CrossOrigin("*")
@Controller
public class CustomerWebController {
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    RentCarServiceImpl rentCarService;

    @Autowired
    LoginServiceImpl loginService;

    @Autowired
    PersonServiceImpl personService;

    /*

    * LIST

     */

    @RequestMapping("/customer/list")
    public String showAll(Model model){

        List<Customer> list = customerService.getAll();

        boolean isAuthenticated;
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) isAuthenticated = true;
        else isAuthenticated = false;
        if(isAuthenticated){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            Person personLogin = personService.getPersonLoginUser(loginUser);
            Customer customerLogin = customerService.getCustomerPerson(personLogin);
            model.addAttribute("personLogin", customerLogin);
        }
        model.addAttribute("isAuthenticated", isAuthenticated);

        model.addAttribute("customers", list);

        return "/customer/customerList";
    }

    /*

    * CREATE GET

     */

    @RequestMapping(value = "/customer/create", method = RequestMethod.GET)
    public String add(Model model){

        CustomerForm customerForm = new CustomerForm();

        List gender = Arrays.asList(
                "man", "woman");

        model.addAttribute("Gender", gender);
        model.addAttribute("CustomerForm", customerForm);

        return "/customer/customerAdd";
    }

    /*

    * CREATE POST

     */
    @RequestMapping(value = "/customer/create", method = RequestMethod.POST)
    public String create(Model model,@ModelAttribute("CustomerForm")
            CustomerForm customerForm){

        boolean genderBool;
        if(customerForm.getGender().equals("man")){
            genderBool = true;
        }else{
            genderBool = false;
        }

        Person newPerson = new Person(null, customerForm.getFirstName(),
                customerForm.getLastName(), customerForm.getMiddleName(),
                genderBool);

        List<Car> carList = new ArrayList<>();
        Customer newCustomer = new Customer(personService.create(newPerson),
                customerForm.getAddress(), customerForm.getPhone(),
                customerForm.geteMail(),0,carList,false);

        if(personService.isNotEmptyFields(newPerson)
                || customerService.isNotEmptyFields(newCustomer)){
            String error = "All fields must be filled!";
            model.addAttribute("errorMessage", error);
            List gender = Arrays.asList(
                    "man", "woman");
            model.addAttribute("Gender", gender);
            personService.delete(newCustomer.getPerson().getId());
            return "/customer/customerAdd";
        }

        customerService.create(newCustomer);

        return "redirect:/CarRentals/customer/list";
    }

    /*

    * USER ACCOUNT

     */

    @RequestMapping("/userAccount/{id}")
    public String userAccount(Model model,@PathVariable(value="id")String id){
        Customer customer = customerService.get(id);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        boolean isAuthenticated;
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) isAuthenticated = true;
        else isAuthenticated = false;
        if(isAuthenticated){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            Person personLogin = personService.getPersonLoginUser(loginUser);
            Customer customerLogin = customerService.getCustomerPerson(personLogin);
            model.addAttribute("personLogin", customerLogin);
        }
        model.addAttribute("isAuthenticated", isAuthenticated);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        model.addAttribute("customer", customer);
        return "accountUser";
    }

    /*

    * UPDATE GET

     */

    // @PostMapping("/update/{id}")
    @RequestMapping(value = "/customer/update/{id}", method = RequestMethod.GET)
    public String updateCustomer(Model model,  @PathVariable("id") String id){
        Customer customerToUpdate = customerService.get(id);
        CustomerForm customerForm = new CustomerForm();
        customerForm.setId(customerToUpdate.getId());
        customerForm.setPerson(customerToUpdate.getPerson());
        customerForm.setIdLogin(customerForm.getPerson().getLoginUser().getId());
        customerForm.setFirstName(customerToUpdate.getPerson().getFirstName());
        customerForm.setLastName(customerToUpdate.getPerson().getLastName());
        customerForm.setMiddleName(customerToUpdate.getPerson().getMiddleName());
        List listGender = new ArrayList();
        if(customerToUpdate.getPerson().isGender()){
            listGender.add("man");
            listGender.add("woman");
        }
        else{
            listGender.add("woman");
            listGender.add("man");
        }
        model.addAttribute("ListGender", listGender);
        customerForm.setAddress(customerToUpdate.getAddress());
        customerForm.setPhone(customerToUpdate.getPhone());
        customerForm.seteMail(customerToUpdate.geteMail());
        customerForm.setBonusPoints(customerToUpdate.getBonusPoints());
        customerForm.setCarList(customerToUpdate.getCarList());
        List listCars = Arrays.asList(customerToUpdate.getCarList());
        model.addAttribute("ListCars", listCars);
        customerForm.setRent(customerToUpdate.isRent());
        model.addAttribute("CustomerForm", customerForm);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        boolean isAuthenticated;
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) isAuthenticated = true;
        else isAuthenticated = false;
        if(isAuthenticated){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            Person personLogin = personService.getPersonLoginUser(loginUser);
            Customer customerLogin = customerService.getCustomerPerson(personLogin);
            model.addAttribute("personLogin", customerLogin);
        }
        model.addAttribute("isAuthenticated", isAuthenticated);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        return "/customer/customerToUpdate";
    }

    /*

    * UPDATE POST

     */

    @RequestMapping(value = "/customer/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") String id,
                 @ModelAttribute("CustomerForm") CustomerForm customerForm){
        boolean gender;
        if(customerForm.getGender().equals("man")){
            gender = true;
        }else{
            gender = false;
        }

        Person newPerson = new Person(customerService.get(id).getPerson().getLoginUser(), customerForm.getFirstName(),
                customerForm.getLastName(), customerForm.getMiddleName(),
                gender);
        newPerson.setId(customerService.get(id).getPerson().getId());

        Customer newCustomer = new Customer(personService.update(newPerson), customerForm.getAddress(),
                customerForm.getPhone(), customerForm.geteMail(),
                customerForm.getBonusPoints(), customerForm.getCarList(),
                customerForm.isRent());
        newCustomer.setId(customerForm.getId());

        rentCarService.customerUpdate(customerService.update(newCustomer));
        return "redirect:/CarRentals/customer/list";
    }

    /*

    * DELETE

     */

    @RequestMapping("/customer/delete/{id}")
    public String delete(Model model,@PathVariable(value = "id")String id){
        customerService.delete(id);
        return "redirect:/CarRentals/customer/list";
    }
}
