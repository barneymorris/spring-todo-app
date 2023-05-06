package todoapp.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import todoapp.todo.dto.AuthenticationRequestDTO;
import todoapp.todo.dto.AuthenticationResponseDTO;
import todoapp.todo.dto.RegisterRequestDTO;
import todoapp.todo.dto.UserDTO;
import todoapp.todo.entity.Users;
import todoapp.todo.exeptions.EmailNotPresentedException;
import todoapp.todo.exeptions.PasswordIsNotPresentedException;
import todoapp.todo.exeptions.UserAlreadyExistException;
import todoapp.todo.exeptions.WrongUsernameException;
import todoapp.todo.repository.UsersRepository;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UsersRepository usersRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponseDTO register(RegisterRequestDTO request) throws UserAlreadyExistException, AddressException, EmailNotPresentedException, WrongUsernameException, PasswordIsNotPresentedException {

        if (request.getUserName().length() < 3) {
            throw new WrongUsernameException("Username must be at least 3 characters length");
        }

        if (request.getPassword().length() < 3) {
            throw new PasswordIsNotPresentedException("Password must be at least 3 characters length");
        }


        if (request.getEmail().length() == 0) {
            throw new EmailNotPresentedException("Email not presented");
        }

        InternetAddress emailAddr = new InternetAddress(request.getEmail());
        emailAddr.validate();

        Users user = Users.builder()
                .username(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .build();

        if (usersRepository.findByUsername(request.getUserName()).isPresent()) {
            throw new UserAlreadyExistException("User already exist");
        }

        usersRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .error("")
                .build();
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );

        Users user = usersRepository.findByUsername(request.getUserName()).orElseThrow();
        String jwt = jwtService.generateToken(user);

        return AuthenticationResponseDTO.builder()
                .token(jwt)
                .build();
    }

    public UserDTO getUserInfo() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return UserDTO.builder().userName("").id("").error("Not authenticated").build();
        }

        Users ctx = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        return UserDTO.builder()
                .userName(ctx.getUsername())
                .id(String.valueOf(ctx.getId()))
                .error("")
                .build();
    }
}
