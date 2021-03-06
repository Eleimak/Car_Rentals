package tam.group_bbv181.car_rentals.services.login.impls;

import com.mongodb.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tam.group_bbv181.car_rentals.model.*;
import tam.group_bbv181.car_rentals.repository.CustomerRepository;
import tam.group_bbv181.car_rentals.repository.LoginRepository;
import tam.group_bbv181.car_rentals.repository.PersonRepository;
import tam.group_bbv181.car_rentals.services.customer.impls.CustomerServiceImpl;
import tam.group_bbv181.car_rentals.services.login.interfaces.ILoginService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LoginServiceImpl implements UserDetailsService, ILoginService {
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PersonRepository personRepository;



    @PostConstruct
    void init(){
        customerRepository.deleteAll();
        personRepository.deleteAll();
        loginRepository.deleteAll();

        LoginUser baziL = new LoginUser("user",
                new ArrayList<>(Arrays.asList(Role.values())),
                new BCryptPasswordEncoder().encode("user"),
                true, true, true, true);
        LoginUser jaN = new LoginUser("zxcv",new ArrayList<>(Arrays.asList(Role.USER)),
                new BCryptPasswordEncoder().encode("zxcv"),
                true,true,true,true);
        LoginUser annA = new LoginUser("asdf",new ArrayList<>(Arrays.asList(Role.USER)),
                new BCryptPasswordEncoder().encode("asdf"),
                true,true,true,true);
        LoginUser qweR = new LoginUser("qwer",new ArrayList<>(Arrays.asList(Role.USER)),
                new BCryptPasswordEncoder().encode("qwer"),
                true,true,true,true);

        Person pash = new Person(this.create(baziL),"Bazil","Pash", "Anodov", true);
        Person desi = new Person(this.create(jaN),"Jan","Desi", "Emue", true);
        Person tester = new Person(this.create(annA),"Anna","Tester", "Oyen", false);
        Person pash1 = new Person(this.create(qweR),"Qwer","Rewq", "Ywer", true);

        List<Car> carList = new ArrayList<>();
        Customer bazil = new Customer(personRepository.save(pash),"qwrewt",
                    "45679689","qwer@gmail.com", 0,carList,false);
        Customer jan = new Customer(personRepository.save(desi),"zxcxczcz",
                    "436554","asdf@gmail.com",0,carList,false);
        Customer anna = new Customer(personRepository.save(tester),"wqqrrwer",
                    "323774","zvcx@mail.com",0,carList,false);
        Customer qwer1 = new Customer(personRepository.save(pash1),"qwerrttyy",
                "1234567890","qwert@mail.com",0,carList,false);

        anna.setBonusPoints(7);
        bazil.setBonusPoints(20);




        customerRepository.save(bazil);
        customerRepository.save(jan);
        customerRepository.save(anna);
        customerRepository.save(qwer1);
    }



    @Override
    public List<LoginUser> getAll() {
        return loginRepository.findAll();
    }

    @Override
    public LoginUser get(String id) {
        return loginRepository.findById(id).orElse(null);
    }

    @Override
    public LoginUser create(LoginUser loginUser) {
        return loginRepository.save(loginUser);
    }

    @Override
    public LoginUser update(LoginUser loginUser) {
        return loginRepository.save(loginUser);
    }

    @Override
    public LoginUser delete(String id) {
        LoginUser loginUser = this.get(id);
        loginRepository.deleteById(id);
        return loginUser;
    }

    @Override
    public boolean uniqueLogin(String login) {
        List<LoginUser> loginUserList = this.getAll();
        for (LoginUser item: loginUserList) {
            if(item.getUsername().equals(login)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isNotNull(LoginUser loginUser){
        if(loginUser.getUsername().equals("")||loginUser.getPassword().equals("")){
            return true;
        }
        return false;
    }

    public Optional<LoginUser> findByUsername(@NonNull String username){
        // return Optional.ofNullable(mongoTemplate.findOne(query(where("username").is(username)), User.class));
        return  Optional.ofNullable(loginRepository.findByUsername(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("user " + username + " was not found!"));
    }
}
