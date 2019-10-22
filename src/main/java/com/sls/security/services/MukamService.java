package com.sls.security.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sls.security.dto.DeleteDTO;
import com.sls.security.dto.MukamDTO;
import com.sls.security.dto.MukamNameBySuppDTO;

public interface MukamService {

    //public ResponseEntity<ActivityDTO> getUser(String username);

    public ResponseEntity<MukamDTO> persistMukam(MukamDTO mukam);

     public MukamDTO populateOneMukamDetails(Long userId);
    public List<MukamDTO> populateMukamList();

    public DeleteDTO destroyMukam(Long userId);

    ResponseEntity<MukamDTO> updateMukam(MukamDTO mukamDTO);

    List<MukamNameBySuppDTO> getMukamNamesBySupp(String suppCode);
    

}
