package tam.group_bbv181.car_rental.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tam.group_bbv181.car_rental.forms.CustomerForm;
import tam.group_bbv181.car_rental.model.Customer;
import tam.group_bbv181.car_rental.model.LoginUser;
import tam.group_bbv181.car_rental.model.Person;
import tam.group_bbv181.car_rental.services.customers.impls.CustomerServiceImpl;
import tam.group_bbv181.car_rental.services.login.impls.LoginServiceImpl;
import tam.group_bbv181.car_rental.services.person.impls.PersonServiceImpl;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/CarRentals")
@CrossOrigin("*")
@Controller
public class LoginUserWebController {
    @Autowired
    CustomerServiceImpl customersService;

    @Autowired
    LoginServiceImpl loginService;

    @Autowired
    PersonServiceImpl personService;



    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String signIn(Model model){
        LoginUser loginUser = new LoginUser();
        model.addAttribute("LoginUser", loginUser);
        return "loginUser";
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String SignIn(Model model,@ModelAttribute("LoginUser")
            LoginUser loginUser){
        if(loginService.userAccount(loginUser.getLogin(),
                loginUser.getPassword()) != null) {
            model.addAttribute("LoginUser", loginUser);
            String redirectStr = "redirect:/CarRentals/userAccount/" +
                    loginService.userAccount(loginUser.getLogin(),
                            loginUser.getPassword()).getCustomer().getId();
            return redirectStr;
        }
        return "redirect:/CarRentals/signIn";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String addCustomer(Model model){
        CustomerForm customerForm = new CustomerForm();
        List manWoman = Arrays.asList(
                "man", "woman");
        model.addAttribute("manWoman", manWoman);
        model.addAttribute("CustomersForm", customerForm);
        return "customersAdd";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String create(Model model,@ModelAttribute("CustomersForm")
            CustomerForm customerForm){
        boolean gender;
        if(customerForm.getGender().equals("man")){
            gender = true;
        }else{
            gender = false;
        }
        Person newperson = new Person(customerForm.getFirstName(),
                customerForm.getMiddleName(), customerForm.getLastName(),
                gender);
        personService.create(newperson);
        Customer newCustomer = new Customer(newperson, customerForm.getAddress(),
                customerForm.getPhone(), customerForm.geteMail());
        customersService.create(newCustomer);
        LoginUser loginUser = new LoginUser(customerForm.getLogin(), customerForm.getPassword(),
                newCustomer);
        return "redirect:/CarRentals/userAccount/" + loginService.create(loginUser).getCustomer().getId();
    }
/*

    // @PostMapping("/update/{id}")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateWorker(Model model,  @PathVariable("id") String id){
        Worker workerToUpdate = workerService.get(id);
        WorkerForm workerForm = new WorkerForm();
        workerForm.setName(workerToUpdate.getName());
        workerForm.setOccupation(workerToUpdate.getOccupation());
        workerForm.setSalary(workerToUpdate.getSalary());
        workerForm.setSpeciality(workerToUpdate.getSpeciality().getName());
        Map<String, String> mavs = (Map<String, String>)
                specialityServices.getAll().stream()
                        .collect(Collectors.toMap(Speciality::getId,
                                Speciality::getName));

        model.addAttribute("mavs", mavs);
        model.addAttribute("WorkerForm", workerForm);
        return "workerupdate";
    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("WorkerForm")
                                 WorkerForm workerForm){
        Worker newWorker = new Worker(workerForm.getName(),
                workerForm.getOccupation(),workerForm.getSalary(),
                specialityServices.get(workerForm.getSpeciality()),
                LocalDateTime.parse(workerForm.getEmploymentDay(),
                        DateTimeFormatter.ofPattern("HH:mm MM/dd/yyyy")));
        newWorker.setId(workerForm.getId());
        workerService.update(newWorker);
        return "redirect:/worker/list";
    }
*/

}
