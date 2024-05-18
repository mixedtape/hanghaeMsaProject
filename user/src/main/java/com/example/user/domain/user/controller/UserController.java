package com.example.user.domain.user.controller;


import com.example.user.domain.user.UserDetailsImpl;
import com.example.user.domain.user.dto.ChangePasswordDTO;
import com.example.user.domain.user.dto.EmailCheckDto;
import com.example.user.domain.user.dto.EmailRequestDto;
import com.example.user.domain.user.dto.UpdateUserProfileRequestDTO;
import com.example.user.domain.user.dto.UserInfoResponseDTO;
import com.example.user.domain.user.dto.UserSignupRequestDTO;
import com.example.user.domain.user.entity.User;
import com.example.user.domain.user.service.EmailService;
import com.example.user.domain.user.service.UserService;
import com.example.user.global.common.CommonResponse;
import com.example.user.global.exception.user.InvalidEmailCodeException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final EmailService mailService;

    @PostMapping
    public ResponseEntity<CommonResponse<Void>> signup(
            @Valid @RequestBody UserSignupRequestDTO requestDTO) {

        userService.signup(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.of("회원가입 성공", null));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoResponseDTO> getProfile(
            @PathVariable Long userId) {

        UserInfoResponseDTO responseDTO = userService.getProfile(userId);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping
    public User getUserByName(
            @RequestParam("userName") String userName) {

        return userService.getUserByName(userName);
    }

    @PostMapping("/mail")
    public String mailSend(@RequestBody @Valid EmailRequestDto emailDto) {
        System.out.println("이메일 인증 이메일 :" + emailDto.getEmail());
        return mailService.joinEmail(emailDto.getEmail());
    }

    @PostMapping("/mailAuth")
    public String AuthCheck(@RequestBody @Valid EmailCheckDto emailCheckDto) {
        boolean Checked = mailService.CheckAuthNum(emailCheckDto.getEmail(),
                emailCheckDto.getAuthNum());
        if (Checked) {
            return "ok";
        } else {
            throw new InvalidEmailCodeException();
        }
    }

    @GetMapping("{userId}/password")
    public ResponseEntity<CommonResponse<Void>> checkPassword(
            @PathVariable Long userId,
            @RequestBody ChangePasswordDTO changePasswordDTO,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        userService.passwordCheck(userId,changePasswordDTO,userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.of("패스워드 확인 완료", null));
    }

    @PatchMapping("/{userId}/new-password")
    public ResponseEntity<CommonResponse<Void>> changePassword(
            @PathVariable Long userId,
            @RequestBody ChangePasswordDTO changePasswordDTO,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        userService.changePassword(userId,changePasswordDTO,userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.of("패스워드 변경 완료", null));
    }
    @PatchMapping("/{userId}")
    public ResponseEntity<CommonResponse<Void>> updateProfile(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateUserProfileRequestDTO requestDTO,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        User loginUser = userDetails.getUser();
        userService.updateProfile(userId, requestDTO, loginUser);

        return ResponseEntity.ok().body(CommonResponse.of("프로필 수정 성공", null));
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<CommonResponse<Void>> deleteUser(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        userService.deleteUser(userDetails.getUser().getId());
        return ResponseEntity.ok().body(CommonResponse.of("유저 삭제 성공",null));
    }

}
