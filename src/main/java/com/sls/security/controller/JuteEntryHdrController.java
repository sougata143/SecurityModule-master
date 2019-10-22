package com.sls.security.controller;

import static com.sls.security.constant.MasterManagementConstant.USER_HOST_SERVER;

import java.sql.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.sls.security.component.JuteGateEntryHdrComponent;
import com.sls.security.dto.DeleteDTO;
import com.sls.security.dto.JuteEntryHeaderDTO;
import com.sls.security.dto.JuteEntryHeaderdDTO;
import com.sls.security.entity.JuteEntryHeader;
import com.sls.security.services.serviceImpl.JuteGateEntryHdrServiceImpl;

@RestController
@RequestMapping("juteentryhdr")
public class JuteEntryHdrController {
	
	@Autowired
	JuteGateEntryHdrServiceImpl juteentryhdrService;
	
	@Autowired
	JuteGateEntryHdrComponent juteEntryDao;
	
	@GetMapping("/getAllJuteEntryHdrByDate/{fromDate}/{toDate}")
	@CrossOrigin(origins = USER_HOST_SERVER)
	public List<JuteEntryHeaderDTO> getAllJuteEntryHeaderByDate(@PathVariable("fromDate") Date fromDate,
																@PathVariable("toDate") Date toDate){
		return juteentryhdrService.getAllJuteGateEntryHdrByDate(fromDate, toDate);
	}
	
	@GetMapping("/getAllJuteEntryHdr")
	@CrossOrigin(origins = USER_HOST_SERVER)
	public List<JuteEntryHeaderDTO> getAllJuteEntryHdr(){
		return juteentryhdrService.getAllJuteGateEntryHeader();
	}
	
	/*@GetMapping("/getAllJuteEntryHdrById/{id}")
	@CrossOrigin(origins = USER_HOST_SERVER)
	public JuteEntryHeaderDTO getAllJuteEntryHdrById(@PathVariable("id") long id){
		return juteentryhdrService.getJuteGateEntryHeaderById(id);
	}*/
	
	@GetMapping("/getAllJuteEntryHdrById/{id}")
	@CrossOrigin(origins = USER_HOST_SERVER)
	public JuteEntryHeaderdDTO getAllJuteEntryHdrById2(@PathVariable("id") long id){
		return juteentryhdrService.getJuteGateEntryHeaderById2(id);
	}
	
	@PostMapping("/saveJuteEntryHdr")
	@CrossOrigin(origins = USER_HOST_SERVER)
	public ResponseEntity<JuteEntryHeaderDTO> saveJuteEntryHdr(@RequestBody JuteEntryHeaderDTO juteentryhdr) {
		
		JuteEntryHeader entryheader = new JuteEntryHeader();
		if(juteentryhdr.getPoNo()!=null) {
			entryheader = 
					juteEntryDao.getJuteEntryHeaderByPoNoAndChallanNo(juteentryhdr.getPoNo(), 
																			juteentryhdr.getChalanNo());
		}
		
		JuteEntryHeader entry = 
				juteEntryDao.getJuteEntryHeaderBySuppNameAndChallanNo(juteentryhdr.getBrokerName(),
																			juteentryhdr.getChalanNo());
		
		if(juteentryhdr.getPoNo()!=null) {
			if(entryheader == null) {
				if(entry==null) {
					juteentryhdrService.saveJuteGateEntryHeader(juteentryhdr);
					return new ResponseEntity<JuteEntryHeaderDTO>(juteentryhdr, HttpStatus.CREATED);
				}else {
					return new ResponseEntity<JuteEntryHeaderDTO>(juteentryhdr, HttpStatus.ALREADY_REPORTED);
				}
			}else {
				return new ResponseEntity<JuteEntryHeaderDTO>(juteentryhdr, HttpStatus.ALREADY_REPORTED);
			}
		}else {
			if(entry==null) {
				juteentryhdrService.saveJuteGateEntryHeader(juteentryhdr);
				return new ResponseEntity<JuteEntryHeaderDTO>(juteentryhdr, HttpStatus.CREATED);
			}else {
				return new ResponseEntity<JuteEntryHeaderDTO>(juteentryhdr, HttpStatus.ALREADY_REPORTED);
			}
		}
		
		/*juteentryhdrService.saveJuteGateEntryHeader(juteentryhdr);
		
		return new ResponseEntity<JuteEntryHeaderDTO>(juteentryhdr, HttpStatus.OK);*/
	}
	
	@PostMapping("/updateJuteEntryHdr")
	@CrossOrigin(origins = USER_HOST_SERVER)
	public ResponseEntity<JuteEntryHeaderDTO> updateJuteEntryHdr(@RequestBody JuteEntryHeaderDTO juteentryhdr) {
		return juteentryhdrService.updateJuteGateEntryHeader(juteentryhdr);
	}
	
	@DeleteMapping("/deleteJuteEntryHdr/{id}")
	@CrossOrigin(origins = USER_HOST_SERVER)
	public DeleteDTO deleteJuteEntryHdr(@PathVariable("id") long id) {
		juteentryhdrService.deleteJuteEntryHeader(id);
		
		DeleteDTO deleteDTO = new DeleteDTO();
    	deleteDTO.setstatus(1);
    	deleteDTO.setstatusCode(2000);
    	deleteDTO.setmsg("Deleted successfully.");
    	
    	return deleteDTO;
	}
	
	@GetMapping("getAllJuteEntryHeaderByChallanNo/{challanNo}/{suppName}")
	@CrossOrigin(origins = USER_HOST_SERVER)
	public JuteEntryHeaderDTO getAllHuteEntryHeaderByChallanNo( @PathVariable("challanNo") long challanNo,
																@PathVariable("suppName")String suppName) {
		return juteentryhdrService.getAllJuteEntryHeaderBySuppNameAndChallanNo(suppName,challanNo);
	}
	
	@PostMapping("/updateJuteEntryHdrOut")
	@CrossOrigin(origins = USER_HOST_SERVER)
	public ResponseEntity<JuteEntryHeaderDTO> updateJuteEntryHdrOut(@RequestBody JuteEntryHeaderDTO juteentryhdr) {
		return juteentryhdrService.updateJuteGateEntryHeader(juteentryhdr);
	}

	@GetMapping("getChalanWeightBySuppNameAndChalanNo/{suppName}/{chalanNo}")
	public long getchalanWeightBySuppNameAndChalanNo(@PathVariable("suppName") String suppName, @PathVariable("chalanNo") long chalanNo) {
		return juteentryhdrService.getChallanWeightByChalanNoAndSupp(suppName, chalanNo);
	}
	
	@GetMapping("getChalanWeightBySuppCodeAndChalanNo/{suppCode}/{chalanNo}")
	public long getchalanWeightBySuppCodeAndChalanNo(@PathVariable("suppCode") String suppCode, @PathVariable("chalanNo") long chalanNo) {
		return juteentryhdrService.getChallanWeightByChalanNoAndSuppCode(suppCode, chalanNo);
	}
	
	
}
