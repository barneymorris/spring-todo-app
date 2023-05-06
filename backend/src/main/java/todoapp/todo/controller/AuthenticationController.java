package todoapp.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import todoapp.todo.dto.AuthenticationRequestDTO;
import todoapp.todo.dto.AuthenticationResponseDTO;
import todoapp.todo.dto.RegisterRequestDTO;
import todoapp.todo.exeptions.EmailNotPresentedException;
import todoapp.todo.exeptions.PasswordIsNotPresentedException;
import todoapp.todo.exeptions.UserAlreadyExistException;
import todoapp.todo.exeptions.WrongUsernameException;
import todoapp.todo.service.AuthenticationService;

import javax.mail.internet.AddressException;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody RegisterRequestDTO request
    ) {
        try {
            return ResponseEntity.ok(authenticationService.register(request));
        } catch (UserAlreadyExistException | EmailNotPresentedException | WrongUsernameException |
                 PasswordIsNotPresentedException e) {
            var dto = AuthenticationResponseDTO.builder().token("").error(e.getMessage()).build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
        } catch (AddressException e) {
            var dto = AuthenticationResponseDTO.builder().token("").error("You provided wrong email address").build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
        }
    }

    @PostMapping("/api/auth/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody AuthenticationRequestDTO request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
