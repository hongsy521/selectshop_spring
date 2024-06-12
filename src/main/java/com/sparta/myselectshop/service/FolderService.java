package com.sparta.myselectshop.service;

import com.sparta.myselectshop.dto.FolderResponseDto;
import com.sparta.myselectshop.entity.Folder;
import com.sparta.myselectshop.entity.User;
import com.sparta.myselectshop.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {
    private final FolderRepository folderRepository;

    public void createFolder(List<String> folderNames, User user) {
        // 이미 만들어져 있는 폴더일 경우를 제외하기 위해 db에서 기존 데이터 가져와 비교
        List<Folder> existFolderList = folderRepository.findAllByUserAndNameIn(user,folderNames);

        List<Folder> folderList = new ArrayList<>();
        for (String foldername : folderNames) {
            if(!isExistFolderName(foldername,existFolderList)){
                // 폴더 저장
                Folder folder = new Folder(foldername,user);
                folderList.add(folder);
            }else {
                throw new IllegalArgumentException("중복된 폴더명입니다.");
            }
        }
        folderRepository.saveAll(folderList);
    }

    public List<FolderResponseDto> getFolders(User user) {
        List<Folder> folderList = folderRepository.findAllByUser(user);
        List<FolderResponseDto> folderResponseDtoList = new ArrayList<>();
        for (Folder folder : folderList) {
            folderResponseDtoList.add(new FolderResponseDto(folder));
        }
        return folderResponseDtoList;
    }

    private boolean isExistFolderName(String foldername, List<Folder> existFolderList) {
        for (Folder folder : existFolderList) {
            if(foldername.equals(folder.getName())){
                return true;
            }
        }
        return false;
    }
}

