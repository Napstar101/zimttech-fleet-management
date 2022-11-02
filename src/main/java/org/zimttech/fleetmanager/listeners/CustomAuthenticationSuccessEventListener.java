package org.zimttech.fleetmanager.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextListener;
import org.zimttech.fleetmanager.enums.Role;
import org.zimttech.fleetmanager.service.ifaces.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.OffsetDateTime;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationSuccessEventListener extends SavedRequestAwareAuthenticationSuccessHandler {
    private final RequestContextListener requestContextListener;
    private final UserService userService;
    private final HttpSession session;
    private final HttpServletRequest request;


    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event){
        User principal = (User) event.getAuthentication().getPrincipal();
        String username = principal.getUsername();
        log.info("User with username : {} just logged in at : {}", username, OffsetDateTime.now());

        // Find the logged in user account details
        Optional<org.zimttech.fleetmanager.domain.User> optionalUserAccount = userService.findByUsername(username);

        optionalUserAccount.map(account -> {

            if(account.isCredentialsNonExpired() && account.isEnabled()){
                log.info("User principle account not locked.");
                log.info("User principle account is enabled.");

                //Get User Personal Details
                String firstName = account.getFirstName();
                String surname = account.getSurname();
                Role role = account.getRole();
                Long userId = account.getId();
                log.info("Logged in ROLE: {}", role);


                //Set all the session attributes you want
                session.setAttribute("AUTHENTICATED", "AUTHENTICATED");
                session.setAttribute("role", role.name());
                session.setAttribute("firstName", firstName);
                session.setAttribute("surname", surname);
                session.setAttribute("username", username);
                session.setAttribute("userId", userId);

            }else if(!account.isEnabled()) {
                log.info("User principle account disabled.");
                new SecurityContextLogoutHandler().logout(request, null, null);
            }
            return account;
        }).orElseThrow(()->new UsernameNotFoundException("Username was not found"));
    }
}
