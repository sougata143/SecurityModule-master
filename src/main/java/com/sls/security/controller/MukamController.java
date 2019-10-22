package com.sls.security.controller;

import static com.sls.security.constant.MasterManagementConstant.USER_HOST_SERVER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sls.security.dto.DeleteDTO;
import com.sls.security.dto.MukamDTO;
import com.sls.security.dto.MukamNameBySuppDTO;
import com.sls.security.services.serviceImpl.MukamServiceImpl;


@RestController
@RequestMapping("/mukam/")
@CrossOrigin(origins = USER_HOST_SERVER)
public class MukamController {

    @Autowired
    MukamServiceImpl mukamService;

    @PostMapping(value = "addMukam")
    public ResponseEntity<MukamDTO> persistMukam(@RequestBody MukamDTO mukamDTO) {

    	mukamService.persistMukam(mukamDTO);
    	
	return new ResponseEntity<MukamDTO>(mukamDTO,HttpStatus.CREATED);

    }

    @PostMapping(value = "updateMukam")
    public ResponseEntity<MukamDTO> updateMukam(@RequestBody MukamDTO mukamDTO) {

    	mukamService.updateMukam(mukamDTO);
    	
	return new ResponseEntity<MukamDTO>(mukamDTO, HttpStatus.CREATED);

    }

    @GetMapping(value = "getAllMukam")
    public List<MukamDTO> populateMukamList() {
	
    	return mukamService.populateMukamList();
    	
    }

    
    @GetMapping(value = "getMukamById/{Id}")
    public MukamDTO populateOneMukamDetails(@PathVariable("Id") Long Id) {
    
    	return mukamService.populateOneMukamDetails(Id);
    	
    }

    @DeleteMapping(value = "deleteMukam/{Id}")
    public DeleteDTO destroyMukam(@PathVariable("Id") Long Id) {

    	return mukamService.destroyMukam(Id);
	
    }

    @GetMapping("getmukamsbysuppcoed/{suppCode}")
    public List<MukamNameBySuppDTO> getAllMukamsBySuppCode(@PathVariable("suppCode") String suppCode){
    	return mukamService.getMukamNamesBySupp(suppCode);
    }
    

    
}
