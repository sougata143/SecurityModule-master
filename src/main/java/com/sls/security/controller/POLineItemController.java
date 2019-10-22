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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sls.security.dto.DeleteDTO;
import com.sls.security.dto.GateStoreEntryRegDtlDTO;
import com.sls.security.dto.POLineItemDTO;
import com.sls.security.services.serviceImpl.POLineItemServiceImpl;

@RestController
@RequestMapping("/polineitem")
@CrossOrigin(origins = USER_HOST_SERVER)
public class POLineItemController {
	
	@Autowired
	POLineItemServiceImpl polineitemService;
	
	@GetMapping("/getlineitembyponum/{poId}")
	public List<POLineItemDTO> getLineItemByPONum(@PathVariable("poId") String poId) {
		return polineitemService.getLineItemByPoNum(poId);
	}
	
	@GetMapping("/getalllineitem")
	public List<POLineItemDTO> getAllLineItem(){
		return polineitemService.getAllPOLineItem();
	}
	
	@PostMapping("/savelineitem")
	public ResponseEntity<POLineItemDTO> saveLineitem(@RequestBody POLineItemDTO polineitem) {
		polineitemService.savePOLineItem(polineitem);
		return new ResponseEntity<POLineItemDTO>(polineitem, HttpStatus.CREATED);
	}
	
	@PutMapping("/updatepolineitem")
	public ResponseEntity<POLineItemDTO> updatePoLineItem(@RequestBody POLineItemDTO polineitem) {
		polineitemService.updatePOLineItem(polineitem);
		return new ResponseEntity<POLineItemDTO>(polineitem, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletepolineitem/{id}")
	public DeleteDTO deletePOLineItem(@PathVariable("id") long id) {
		polineitemService.deletePOLineItem(id);
		
		DeleteDTO deleteDTO = new DeleteDTO();
    	deleteDTO.setstatus(1);
    	deleteDTO.setstatusCode(2000);
    	deleteDTO.setmsg("Deleted successfully.");
    	
    	return deleteDTO;
	}
	
	@GetMapping("/getjutepolineitembypono/{pono}")
	private List<GateStoreEntryRegDtlDTO> getJutePOLineitemByPoNO(@PathVariable("pono") String pono) {
		return polineitemService.getPOLineitemByPoNO(pono);
	}
	
	@GetMapping("/getjutelineitembyponum/{poId}")
	public List<GateStoreEntryRegDtlDTO> getJuteLineItemByPONum(@PathVariable("poId") String poId) {
		return polineitemService.getJuteLineItemByPoNum(poId);
	}
	
	@GetMapping("/getstorelineitembyponum/{poId}")
	public List<POLineItemDTO> getStoreLineItemByPONum(@PathVariable("poId") String poId) {
		return polineitemService.getStoreLineItemByPoNum(poId);
	}

}
