package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.FolderRequestDto;
import com.sparta.myselectshop.dto.FolderResponseDto;
import com.sparta.myselectshop.dto.FolderResponseDto;
import com.sparta.myselectshop.security.UserDetailsImpl;
import com.sparta.myselectshop.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;

    // 회원의 폴더 생성 --> 인증 처리 필요 , 토큰 검사 후 유저 정보 받음
    @PostMapping("/folders")
    public void createFolder(@RequestBody FolderRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        List<String> folderNames = requestDto.getFolderNames();
        folderService.createFolder(folderNames,userDetails.getUser());
    }

}
