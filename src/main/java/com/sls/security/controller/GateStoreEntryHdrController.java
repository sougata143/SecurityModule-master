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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sls.security.component.GateStoreEntryHdrComponent;
import com.sls.security.dto.DeleteDTO;
import com.sls.security.dto.GateStoreEntryRegHdrDTO;
import com.sls.security.entity.GateStoreEntryRegHdr;
import com.sls.security.services.serviceImpl.GateStoreEntryHdrServiceImple;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
@RequestMapping("gatestoreentryhdr")
@CrossOrigin(origins = USER_HOST_SERVER)
public class GateStoreEntryHdrController {
	
	@Autowired
	GateStoreEntryHdrServiceImple storeentryhdrService;
	
	@Autowired
	GateStoreEntryHdrComponent storeDao;
	
	
	@GetMapping("getAllStoreHdrByDate/{fromDate}/{toDate}")
	public List<GateStoreEntryRegHdrDTO> 
				getAllStoreEntryHdrByDate(@PathVariable("fromDate") Date fromDate, @PathVariable("toDate") Date toDate){
		return storeentryhdrService.getAllGateStoreEntryHdrByDate(fromDate, toDate);
	}
	
	
	@GetMapping("/getAllStoreEntryHdr")
	public List<GateStoreEntryRegHdrDTO> getAllStoreEntryHdr(){
		return storeentryhdrService.getAllGateStoreEntryRegHdr();
	}
	
	@GetMapping("/getAllStoreEntryHdrById/{id}")
	public GateStoreEntryRegHdrDTO getAllStoreEntryHdrById(@PathVariable("id") long id){
		return storeentryhdrService.getGateStoreEntryRegHdrById(id);
	}
	
	@PostMapping("/saveStoreEntryHdr")
	public ResponseEntity<GateStoreEntryRegHdrDTO> saveGateStoreEntryRegHdr(@RequestBody GateStoreEntryRegHdrDTO storeentryhdr) {
		/*GateStoreEntryRegHdr storeentity = 
				storeDao.getGateStoreEntryHdrByPoNoAndChallanNo(storeentryhdr.getPoNo(), storeentryhdr.getChallanNo());
		GateStoreEntryRegHdr store = 
				storeDao.getGateStoreEntryRegHdrByChallanNoAndSuppCode(storeentryhdr.getSuppCode(),
																		storeentryhdr.getChallanNo());
		System.out.println("store "+(store == null)+"store entity "+(storeentity == null));
		if(storeentity == null) {
			if( store == null) {*/
				storeentryhdrService.saveGateStoreEntryRegHdr(storeentryhdr);
				return new ResponseEntity<GateStoreEntryRegHdrDTO>(storeentryhdr, HttpStatus.CREATED);
			/*}else {
				return new ResponseEntity<GateStoreEntryRegHdrDTO>(storeentryhdr, HttpStatus.ALREADY_REPORTED);
			}
		}else {
			return new ResponseEntity<GateStoreEntryRegHdrDTO>(storeentryhdr, HttpStatus.ALREADY_REPORTED);
		}*/
		
	}
	
	@PostMapping("/updateStoreEntryHdr")
	public ResponseEntity<GateStoreEntryRegHdrDTO> updateGateStoreEntryRegHdr(@RequestBody GateStoreEntryRegHdrDTO storeentryhdr) {
		return storeentryhdrService.updateGateStoreEntryRegHdr(storeentryhdr);
	}
	
	@DeleteMapping("/deleteStoreEntryHdr/{id}")
	public DeleteDTO deleteStoreEntryHdr(@PathVariable("id") long id) {
		storeentryhdrService.deleteGateStoreEntryRegHdr(id);
		
		DeleteDTO deleteDTO = new DeleteDTO();
    	deleteDTO.setstatus(1);
    	deleteDTO.setstatusCode(2000);
    	deleteDTO.setmsg("Deleted successfully.");
    	
    	return deleteDTO;
	}
	
	@GetMapping("getStoreEntryHdrByChallanNo/{challanNo}/{suppCode}")
	public ResponseEntity<List<GateStoreEntryRegHdrDTO>> getGateStoreEntryRegHdrByChallanNo(@PathVariable("suppCode") String suppCode,
																			@PathVariable("challanNo") long challanNo) {
		List<GateStoreEntryRegHdrDTO> storeheader = 
				storeentryhdrService.getAllGateStoreEntryRegHdrBySuppCodeAndChallanNo(suppCode,challanNo);
		if(storeheader != null) {
			return new ResponseEntity<List<GateStoreEntryRegHdrDTO>>(storeheader, HttpStatus.CREATED);
			
		}else {
			return new ResponseEntity<List<GateStoreEntryRegHdrDTO>>(storeheader, HttpStatus.BAD_REQUEST);
		}
//		return storeentryhdrService.getAllGateStoreEntryRegHdrByChallanNo(challanNo);
	}
	
	@PostMapping("/updateStoreEntryHdrOut")
	public ResponseEntity<GateStoreEntryRegHdrDTO> updateGateStoreEntryRegHdrOut(@RequestBody GateStoreEntryRegHdrDTO storeentryhdr) {
		return storeentryhdrService.updateGateStoreEntryRegHdr(storeentryhdr);
	}
}
