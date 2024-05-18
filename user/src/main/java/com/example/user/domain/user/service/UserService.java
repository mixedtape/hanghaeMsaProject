package com.example.user.domain.user.service;


import com.example.user.domain.user.dto.ChangePasswordDTO;
import com.example.user.domain.user.dto.UpdateUserProfileRequestDTO;
import com.example.user.domain.user.dto.UserInfoResponseDTO;
import com.example.user.domain.user.dto.UserLoginRequestDTO;
import com.example.user.domain.user.dto.UserSignupRequestDTO;
import com.example.user.domain.user.entity.User;
import com.example.user.domain.user.repository.UserRepository;
import com.example.user.global.exception.user.AlreadyExistEmailException;
import com.example.user.global.exception.user.AlreadyExistUsernameException;
import com.example.user.global.exception.user.AuthenticationMismatchException;
import com.example.user.global.exception.user.InvalidPasswordException;
import com.example.user.global.exception.user.PasswordConfirmationException;
import com.example.user.global.exception.user.UserNotFoundException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(UserSignupRequestDTO requestDTO) {
        String username = requestDTO.getUsername();
        String password = requestDTO.getPassword();
        String checkPassword = requestDTO.getCheckPassword();
        String email = requestDTO.getEmail();
        String address = requestDTO.getAddress();
        String phoneNum = requestDTO.getPhoneNum();

        // 비밀번호 != 비밀번호 확인
        if (!Objects.equals(password, checkPassword)) {
            throw new PasswordConfirmationException();
        }

        String encodePassword = passwordEncoder.encode(password);

        // 유저네임 중복확인
        if (userRepository.findByUsername(username).isPresent()) {
            throw new AlreadyExistUsernameException();
        }

        // 이메일 중복확인
        if (userRepository.findByEmail(email).isPresent()) {
            throw new AlreadyExistEmailException();
        }

        User user = new User(username, encodePassword, email, address, phoneNum);
        userRepository.save(user);
    }

    public UserInfoResponseDTO getProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return new UserInfoResponseDTO(user);
    }


    @Transactional
    public void updateProfile(Long userId, UpdateUserProfileRequestDTO requestDTO, User loginUser) {

        User user = getUser(userId);

        if (!loginUser.getId().equals(user.getId())) {
            throw new AuthenticationMismatchException();
        }

        user.updateProfile(requestDTO);
    }

    public void passwordCheck(Long userId, ChangePasswordDTO changePasswordDTO, User loginUser) {
        User user = getUser(userId);
        if (!loginUser.getId().equals(user.getId())) {
            throw new AuthenticationMismatchException();
        }
        if (!passwordEncoder.matches(changePasswordDTO.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }
    }

    @Transactional
    public void changePassword(Long userId, ChangePasswordDTO changePasswordDTO, User loginUser) {
        User user = getUser(userId);
        if (!loginUser.getId().equals(user.getId())) {
            throw new AuthenticationMismatchException();
        }
        String encodePassword = passwordEncoder.encode(changePasswordDTO.getPassword());
        user.changePassword(encodePassword);
    }
    @Transactional
    public void deleteUser(Long userId) {
      User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
      user.deleteUser();
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
    public User getUserByName(String userName){
        return userRepository.findByUsername(userName)
                .orElseThrow(UserNotFoundException::new);
    }
}
