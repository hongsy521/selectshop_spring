package com.sparta.myselectshop.repository;

import com.sparta.myselectshop.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRespository extends JpaRepository<Folder, Long> {
}
