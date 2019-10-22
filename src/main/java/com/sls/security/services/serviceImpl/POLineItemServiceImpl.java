package com.sls.security.services.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sls.security.component.DepartmentDao;
import com.sls.security.component.IndentDao;
import com.sls.security.component.ItemMasterDao;
import com.sls.security.component.JuteQualityPriceMasterComponent;
import com.sls.security.component.POHeaderComponent;
import com.sls.security.component.POLineItemComponent;
import com.sls.security.component.VehicleComponent;
import com.sls.security.dto.DeleteDTO;
import com.sls.security.dto.GateStoreEntryRegDtlDTO;
import com.sls.security.dto.POLineItemDTO;
import com.sls.security.entity.Department;
import com.sls.security.entity.Indent;
import com.sls.security.entity.ItemMaster;
import com.sls.security.entity.JuteQualityPriceMaster;
import com.sls.security.entity.POHeader;
import com.sls.security.entity.POLineItem;
import com.sls.security.repository.IndentLineItemRepository;
import com.sls.security.services.POLineItemService;

@Service
public class POLineItemServiceImpl implements POLineItemService {
	
	@Autowired
	POLineItemComponent polineitemDao;
	
	@Autowired
	JuteQualityPriceMasterComponent priceDao;
	
	@Autowired
	ItemMasterDao itemDao;
	
	@Autowired
	POHeaderComponent hdrDao;
	
	@Autowired
	IndentDao indentDao;
	
	@Autowired
	IndentLineItemRepository indentlineDao;
	
	@Autowired
	DepartmentDao deptDao;
	
	@Autowired
	VehicleComponent vehicleDao;

	@Override
	public List<POLineItemDTO> getAllPOLineItem() {
		List<POLineItemDTO> polineitemDTO = new ArrayList<>();
		
		try {
			List<POLineItem> polineitemEntity = polineitemDao.getAllPOLineItem();	
			polineitemEntity.forEach(polineitem->{
				polineitemDTO.add(preparePOLineItemDTO(polineitem));
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return polineitemDTO;
	}

	private POLineItemDTO preparePOLineItemDTO(POLineItem polineitem) {
		POLineItemDTO polineitemDTO = new POLineItemDTO();
//		JuteQualityPriceMaster price = priceDao.getJuteQualityPriceMasterById(polineitem.getQualityCode());
		ItemMaster item = itemDao.findItemMasterById(polineitem.getItemId());
		
		polineitemDTO.setActualQuantity(polineitem.getActualQuantity());
		polineitemDTO.setAllowableMoisturePercentage(polineitem.getAllowableMoisturePercentage());
		polineitemDTO.setApproveFirstDate(polineitem.getApproveFirstDate());
		polineitemDTO.setApproverFirst(polineitem.getApproverFirst());
		polineitemDTO.setApproverSecond(polineitem.getApproverSecond());
		polineitemDTO.setApproveSecondDate(polineitem.getApproveSecondDate());
		polineitemDTO.setDiscount(polineitem.getDiscount());
		polineitemDTO.setId(polineitem.getId());
		polineitemDTO.setIndentId(polineitem.getIndentId());
		polineitemDTO.setItemGroupId(polineitem.getItemGroupId());
		polineitemDTO.setItemId(polineitem.getItemId());
		polineitemDTO.setMarka(polineitem.getMarka());
		polineitemDTO.setPoId(polineitem.getPoId());
		
		Indent indentline = indentlineDao.findByIndentHeaderIdAndItemId(polineitem.getIndentId(), item.getId());
		
		if(indentline!=null) {
			Department dept = new Department();
			if(indentline.getDeptId()!=0)
				dept = deptDao.findById(indentline.getDeptId());
			if(dept!=null) {
				polineitemDTO.setDept(String.valueOf(dept.getdepartmentName()));
			}
		}
			
		polineitemDTO.setQuantity(polineitem.getQuantity());
		polineitemDTO.setRate(polineitem.getRate());
		polineitemDTO.setStatus(polineitem.getStatus());
		polineitemDTO.setTax(polineitem.getTax());
		polineitemDTO.setType(polineitem.getType());
		polineitemDTO.setUnitId(polineitem.getUnitId());
		polineitemDTO.setValueWithoutTax(polineitem.getValueWithoutTax());
		polineitemDTO.setValueWithTax(polineitem.getValueWithTax());
		polineitemDTO.setItemDesc(item.getitemDsc());
		polineitemDTO.setReqQuantity(polineitem.getActualQuantity());
		
		return polineitemDTO;
	}

	@Override
	public POLineItemDTO getPOLineItemById(long id) {
		return preparePOLineItemDTO(polineitemDao.getPOLineItemById(id));
	}

	@Override
	public ResponseEntity<POLineItemDTO> savePOLineItem(POLineItemDTO polineitem) {
		POLineItem polineitemEntity = new POLineItem();
//		JuteQualityPriceMaster price = priceDao.getJuteQualityPriceMasterByJuteQuality(polineitem.getQualityCode());
//		ItemMaster item = itemDao.getItemMasterByItemdsc(polineitem.getItemDesc());
		JuteQualityPriceMaster price = priceDao
				.getJuteQualityPriceMasterByJuteQualityAndItemCode(polineitem.getQualityCode(),polineitem.getItemId());
		
		polineitemEntity.setActualQuantity(polineitem.getActualQuantity());
		polineitemEntity.setAllowableMoisturePercentage(polineitem.getAllowableMoisturePercentage());
		polineitemEntity.setApproveFirstDate(polineitem.getApproveFirstDate());
		polineitemEntity.setApproverFirst(polineitem.getApproverFirst());
		polineitemEntity.setApproverSecond(polineitem.getApproverSecond());
		polineitemEntity.setApproveSecondDate(polineitem.getApproveSecondDate());
		polineitemEntity.setDiscount(polineitem.getDiscount());
		polineitemEntity.setId(polineitem.getId());
		polineitemEntity.setIndentId(polineitem.getIndentId());
		polineitemEntity.setItemGroupId(polineitem.getItemGroupId());
		polineitemEntity.setItemId(polineitem.getItemId());
		polineitemEntity.setMarka(polineitem.getMarka());
		polineitemEntity.setPoId(polineitem.getPoId());
		polineitemEntity.setQualityCode(price.getId());
		polineitemEntity.setQuantity(polineitem.getQuantity());
		polineitemEntity.setRate(polineitem.getRate());
		polineitemEntity.setStatus(polineitem.getStatus());
		polineitemEntity.setTax(polineitem.getTax());
		polineitemEntity.setType(polineitem.getType());
		polineitemEntity.setUnitId(polineitem.getUnitId());
		polineitemEntity.setValueWithoutTax(polineitem.getValueWithoutTax());
		polineitemEntity.setValueWithTax(polineitem.getValueWithTax());
		polineitemEntity.setItemDesc(polineitem.getItemDesc());
		
		polineitemDao.savePoLineItem(polineitemEntity);
		
		return new ResponseEntity<POLineItemDTO>(polineitem, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<POLineItemDTO> updatePOLineItem(POLineItemDTO polineitem) {
		POLineItem polineitemEntity = polineitemDao.getPOLineItemById(polineitem.getId());
		JuteQualityPriceMaster price =
				priceDao.getJuteQualityPriceMasterByJuteQuality(polineitem.getQualityCode());
		
		polineitemEntity.setActualQuantity(polineitem.getActualQuantity());
		polineitemEntity.setAllowableMoisturePercentage(polineitem.getAllowableMoisturePercentage());
		polineitemEntity.setApproveFirstDate(polineitem.getApproveFirstDate());
		polineitemEntity.setApproverFirst(polineitem.getApproverFirst());
		polineitemEntity.setApproverSecond(polineitem.getApproverSecond());
		polineitemEntity.setApproveSecondDate(polineitem.getApproveSecondDate());
		polineitemEntity.setDiscount(polineitem.getDiscount());
		polineitemEntity.setId(polineitem.getId());
		polineitemEntity.setIndentId(polineitem.getIndentId());
		polineitemEntity.setItemGroupId(polineitem.getItemGroupId());
		polineitemEntity.setItemId(polineitem.getItemId());
		polineitemEntity.setMarka(polineitem.getMarka());
		polineitemEntity.setPoId(polineitem.getPoId());
		
		polineitemEntity.setQualityCode(price.getId());
		
		polineitemEntity.setQuantity(polineitem.getQuantity());
		polineitemEntity.setRate(polineitem.getRate());
		polineitemEntity.setStatus(polineitem.getStatus());
		polineitemEntity.setTax(polineitem.getTax());
		polineitemEntity.setType(polineitem.getType());
		polineitemEntity.setUnitId(polineitem.getUnitId());
		polineitemEntity.setValueWithoutTax(polineitem.getValueWithoutTax());
		polineitemEntity.setValueWithTax(polineitem.getValueWithTax());
		polineitemEntity.setItemDesc(polineitem.getItemDesc());
		
		polineitemDao.savePoLineItem(polineitemEntity);
		
		return new ResponseEntity<POLineItemDTO>(polineitem, HttpStatus.CREATED);

	}

	@Override
	public DeleteDTO deletePOLineItem(long id) {
		polineitemDao.deletePOLinteItem(id);
		DeleteDTO deleteDTO = new DeleteDTO();
    	deleteDTO.setstatus(1);
    	deleteDTO.setstatusCode(2000);
    	deleteDTO.setmsg("Deleted successfully.");
    	
    	return deleteDTO;

	}

	@Override
	public List<POLineItemDTO> getLineItemByPoNum(String poId) {
		List<POLineItem> lineitems = polineitemDao.getpolineitemByPoNum(poId);
		POLineItemDTO lineitemDTO = new POLineItemDTO();
		List<POLineItemDTO> lineitemDTOs = new ArrayList<>();
		lineitems.forEach(lineitem->{
			lineitemDTOs.add(preparePOLineItemDTO(lineitem));
		});
		return lineitemDTOs;
	}
	
	@Override
	public List<GateStoreEntryRegDtlDTO> getPOLineitemByPoNO(String pono) {
		List<GateStoreEntryRegDtlDTO> polineitems = new ArrayList<>();
		try {
			List<POLineItem> polineitemsEntity = polineitemDao.getpolineitemByPoNum(pono);
			polineitemsEntity.forEach(lineitem->{
				polineitems.add(prepareGateEntryDtlDTO(lineitem));
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return polineitems;
	}

	private GateStoreEntryRegDtlDTO prepareGateEntryDtlDTO(POLineItem lineitem) {
		GateStoreEntryRegDtlDTO entrydtl = new GateStoreEntryRegDtlDTO();
		
		JuteQualityPriceMaster price = new JuteQualityPriceMaster();
		if(lineitem.getQualityCode()!=0) {
			price = priceDao.getJuteQualityPriceMasterById(lineitem.getQualityCode());
		}
		ItemMaster item = itemDao.findItemMasterById(lineitem.getItemId());
		POHeader hdr = hdrDao.getPOHeaderById(lineitem.getPoId());
//		IndentHeader indent = indentDao.getIndentById(lineitem.getIndentId());
		/*Indent indentline = 
				indentlineDao.findByIndentHeaderIdAndItemIdAndQualityCode(lineitem.getIndentId(),
						item.getId(),String.valueOf(lineitem.getQualityCode()));*/
		
		entrydtl.setActualJuteType(item.getitemDsc());
		if(price!=null) {
			entrydtl.setActualQuality(price.getJuteQuality());
		}
//		entrydtl.setActualQuantity(lineitem.getActualQuantity());
		entrydtl.setAdvisedJuteType(item.getitemDsc());
		entrydtl.setAdvisedQuality(price.getJuteQuality());
//		entrydtl.setAdvisedQuantity(lineitem.getQuantity());
		
		
//		if(hdr.getVehicleTypeId()!=0 || hdr!=null)
//			entrydtl.setVehicleType(vehicleDao.getVehilceById(hdr.getVehicleTypeId()).getVehicleType());
		
		entrydtl.setVehicleType(hdr.getVehicleTypeId());
		
		double weight = 0.0f ; 
		if(lineitem.getBale() > 0) {
			entrydtl.setReceivedIn("BALE");
			entrydtl.setAdvisedQuantity(lineitem.getBale());
			entrydtl.setActualQuantity(lineitem.getBale());
			weight = lineitem.getBale() * 1.5;
		}else if(lineitem.getLoose() > 0){
			entrydtl.setReceivedIn("LOOSE");
			entrydtl.setAdvisedQuantity(lineitem.getLoose());
			entrydtl.setActualQuantity(lineitem.getLoose());
			weight = lineitem.getLoose();
		}else {
			entrydtl.setReceivedIn("LOOSE");
			entrydtl.setAdvisedQuantity(lineitem.getQuantity());
			entrydtl.setActualQuantity(lineitem.getActualQuantity());
		}
		
		/*if(indentline!=null)
			entrydtl.setDept(String.valueOf(indentline.getDeptId()));*/
		entrydtl.setRemarks(hdr.getRemarks());
		entrydtl.setUom(lineitem.getUnitId());
		entrydtl.setPoId(hdr.getId());
		
		/*float actualQnt = lineitem.getActualQuantity();
		
		if(lineitem.getBale()>0) {
			weight = actualQnt * 1.5;
		}else {
			weight = actualQnt;
		}*/
		entrydtl.setWeight(weight);
		
		return entrydtl;
	}

	@Override
	public List<POLineItemDTO> getStoreLineItemByPoNum(String poId) {
		/*List<GateStoreEntryRegDtlDTO> polineitems = new ArrayList<>();
		try {
			List<POLineItem> polineitemsEntity = polineitemDao.getStorePolineitemByPoNum(poId);
			polineitemsEntity.forEach(lineitem->{
				polineitems.add(prepareGateEntryDtlDTO(lineitem));
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return polineitems;*/
		
		
		List<POLineItem> lineitems = polineitemDao.getStorePolineitemByPoNum(poId);
		POLineItemDTO lineitemDTO = new POLineItemDTO();
		List<POLineItemDTO> lineitemDTOs = new ArrayList<>();
		lineitems.forEach(lineitem->{
			lineitemDTOs.add(preparePOLineItemDTO(lineitem));
		});
		return lineitemDTOs;
	}

	@Override
	public List<GateStoreEntryRegDtlDTO> getJuteLineItemByPoNum(String poId) {
		List<GateStoreEntryRegDtlDTO> polineitems = new ArrayList<>();
		try {
			List<POLineItem> polineitemsEntity = polineitemDao.getJutePolineitemByPoNum(poId, "J");
			polineitemsEntity.forEach(lineitem->{
				polineitems.add(prepareGateEntryDtlDTO(lineitem));
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return polineitems;
		
		
		
		
		/*List<POLineItem> lineitems = polineitemDao.getJutePolineitemByPoNum(poId, "J");
		POLineItemDTO lineitemDTO = new POLineItemDTO();
		List<POLineItemDTO> lineitemDTOs = new ArrayList<>();
		lineitems.forEach(lineitem->{
			lineitemDTOs.add(preparePOLineItemDTO(lineitem));
		});
		return lineitemDTOs;*/
	}

}
