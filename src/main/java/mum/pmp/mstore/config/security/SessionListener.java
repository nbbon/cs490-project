package mum.pmp.mstore.config.security;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import mum.pmp.mstore.adapters.UserAdapter;
import mum.pmp.mstore.model.Person;
import mum.pmp.mstore.model.User;
import mum.pmp.mstore.service.security.RegistrationService;
import mum.pmp.mstore.service.security.UserService;

@Component("sessionListener")
public class SessionListener {

  @Autowired
  UserService userService;
  
  @Autowired 
  RegistrationService personService;
  
  @Autowired
  HttpSession httpSession;

  public Person getUser() {
    if (httpSession.getAttribute("loggedUser") != null) {
      return (Person) httpSession.getAttribute("loggedUser");
    }
   
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   	UserDetails userdetails = (UserDetails) auth.getPrincipal();
    Person person = personService.findByEmail(userdetails.getUsername());

    httpSession.setAttribute("loggedUser", person);
    return person;

  }

  

}


