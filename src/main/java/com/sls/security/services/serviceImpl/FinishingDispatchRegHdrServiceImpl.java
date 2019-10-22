package com.sls.security.services.serviceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sls.security.component.FinishDispatchEntryDtlComponent;
import com.sls.security.component.FinishingDispatchRegHdrComponent;
import com.sls.security.component.SelectioinQuallityMstComponent;
import com.sls.security.dto.FinishDispatchEntryDtlDTO;
import com.sls.security.dto.FinishingDispatchRegHdrDTO;
import com.sls.security.dto.FinishingDispatchRegHdrDTOSave;
import com.sls.security.entity.FinishingDispatchEntryDtl;
import com.sls.security.entity.FinishingDispatchRegHdr;
import com.sls.security.entity.SelectioinQuallityMst;
import com.sls.security.services.FinishingDispatchRegHdrService;

/*
 * Service Class for Finishing Dispatch Register
 */
@Service
public class FinishingDispatchRegHdrServiceImpl implements FinishingDispatchRegHdrService {
	
	@Autowired
	FinishingDispatchRegHdrComponent finishingDao;
	
	@Autowired
	FinishDispatchEntryDtlComponent finishdtlDao;
	
	@Autowired
	SelectioinQuallityMstComponent priceDao;

	/*
	 * (non-Javadoc)
	 * @see com.sls.security.services.FinishingDispatchRegHdrService#getAllFinishingDispatchRegHdr()
	 * fetching all Finishing Dispatch Register datas from database 
	 */
	@Override
	public List<FinishingDispatchRegHdrDTO> getAllFinishingDispatchRegHdr() {
		List<FinishingDispatchRegHdrDTO> finishingDTOs = new ArrayList<>();
		try {
			List<FinishingDispatchRegHdr> finishingdispatch = finishingDao.getAllFinishingDispatchRegHdr();
			finishingdispatch.forEach(finishhdr->{
				List<FinishingDispatchEntryDtl> finishdtls = 
												finishdtlDao.getFinishingDispatchEntryDtlByHdrId(finishhdr.getHdrId());
				finishingDTOs.add(prepareFinishingDispatchDTO(finishhdr, finishdtls));
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return finishingDTOs;
	}

	
	/*
	 * preparing Finishing Dispatch Register DTO 
	 * 
	 * @Params 
 	 * 
 	 * FinishingDispatchRegHdr and List<FinishingDispatchEntryDtl>
	 */
	private FinishingDispatchRegHdrDTO prepareFinishingDispatchDTO(FinishingDispatchRegHdr finish,
																List<FinishingDispatchEntryDtl> finishdtls) {
		FinishingDispatchRegHdrDTO finishingDTO = new FinishingDispatchRegHdrDTO();
		
		finishingDTO.setActualWeight(finish.getActualWeight());
		finishingDTO.setChallanDate(finish.getChallanDate());
		finishingDTO.setChallanNo(finish.getChallanNo());
		finishingDTO.setDriverName(finish.getDriverName());
		finishingDTO.setGrossWeight(finish.getGrossWeight());
		finishingDTO.setHdrId(finish.getHdrId());
		finishingDTO.setInDate(finish.getInDate());
		finishingDTO.setInTime(finish.getInTime());
		finishingDTO.setNetWeight(finish.getNetWeight());
		finishingDTO.setOpenClose(finish.getOpenClose());
		finishingDTO.setOrderNo(finish.getOrderNo());
		finishingDTO.setOutDate(finish.getOutDate());
		finishingDTO.setOutTime(finish.getOutTime());
		finishingDTO.setTransporter(finish.getTransporter());
		finishingDTO.setUpdateBy(finish.getUpdateBy());
		finishingDTO.setUpdateDateTime(finish.getUpdateDateTime());
		finishingDTO.setVehicleNo(finish.getVehicleNo());
		finishingDTO.setVeighclePassNO(finish.getVeighclePassNO());
		finishingDTO.setWhomToDispatch(finish.getWhomToDispatch());
		
		List<FinishingDispatchEntryDtl> finishdtlDTOs = new ArrayList<>();
//		FinishingDispatchEntryDtl finishdtl = new FinishingDispatchEntryDtl();
		
		for(int i = 0 ; i < finishdtls.size() ; i++) {
			FinishingDispatchEntryDtl finishdtl = new FinishingDispatchEntryDtl();
			finishdtl.setDtlId(finishdtls.get(i).getDtlId());
			finishdtl.setHdrId(finishdtls.get(i).getHdrId());
			finishdtl.setOpenClose(finishdtls.get(i).getOpenClose());
			finishdtl.setQuality(finishdtls.get(i).getQuality());
			finishdtl.setQualityDesc(finishdtls.get(i).getQualityDesc());
			finishdtl.setQuantity(finishdtls.get(i).getQuantity());
			finishdtl.setSrlNo(finishdtls.get(i).getSrlNo());
			finishdtl.setUnitId(finishdtls.get(i).getUnitId());
			finishdtlDTOs.add(finishdtl);
		}
		/*finishdtls.forEach(dtl->{
			finishdtl.setDtlId(dtl.getDtlId());
			finishdtl.setHdrId(dtl.getHdrId());
			finishdtl.setOpenClose(dtl.getOpenClose());
			finishdtl.setQuality(dtl.getQuality());
			finishdtl.setQualityDesc(dtl.getQualityDesc());
			finishdtl.setQuantity(dtl.getQuantity());
			finishdtl.setSrlNo(dtl.getSrlNo());
			finishdtl.setUnitId(dtl.getUnitId());
			finishdtlDTOs.add(finishdtl);
		});*/
		finishingDTO.setFinishdtls(prepareFinishDtlDTO(finishdtls));
		
		return finishingDTO;
		
	}

	
	/*
	 * Preparing Finish Dispatch Entry Dtl DTO
	 * 
	 * @Params
	 * 
	 * List<FinishingDispatchEntryDtl
	 */
	private List<FinishDispatchEntryDtlDTO> prepareFinishDtlDTO(List<FinishingDispatchEntryDtl> finishdtls) {
		List<FinishDispatchEntryDtlDTO> finishdtlDTOs = new ArrayList<>();
		
		
		for(int i = 0 ; i < finishdtls.size() ; i++) {
			FinishDispatchEntryDtlDTO finishdtl = new FinishDispatchEntryDtlDTO();
			finishdtl.setDtlId(finishdtls.get(i).getDtlId());
			finishdtl.setHdrId(finishdtls.get(i).getHdrId());
			finishdtl.setOpenClose(finishdtls.get(i).getOpenClose());
			finishdtl.setQuality(finishdtls.get(i).getQuality());
			finishdtl.setQualityDesc(finishdtls.get(i).getQualityDesc());
			finishdtl.setQuantity(finishdtls.get(i).getQuantity());
			finishdtl.setSrlNo(finishdtls.get(i).getSrlNo());
			finishdtl.setUnitId(finishdtls.get(i).getUnitId());
			finishdtlDTOs.add(finishdtl);
		}
		
		/*finishdtls.forEach(dtl->{
			finishdtl.setDtlId(dtl.getDtlId());
			finishdtl.setHdrId(dtl.getHdrId());
			finishdtl.setOpenClose(dtl.getOpenClose());
			finishdtl.setQuality(dtl.getQuality());
			finishdtl.setQualityDesc(dtl.getQualityDesc());
			finishdtl.setQuantity(dtl.getQuantity());
			finishdtl.setSrlNo(dtl.getSrlNo());
			finishdtl.setUnitId(dtl.getUnitId());
			finishdtlDTOs.add(finishdtl);
		});*/
		return finishdtlDTOs;
	}

	
	/*
	 * preparing Finishing Dispatch Reg Hdr DTO
	 * 
	 * @Params
	 * 
	 * FinishingDispatchRegHdr
	 */
	private FinishingDispatchRegHdrDTO prepareFinishingDispatchDTO(FinishingDispatchRegHdr finish) {
		FinishingDispatchRegHdrDTO finishingDTO = new FinishingDispatchRegHdrDTO();
		List<FinishingDispatchEntryDtl> finishingdtls = 
						finishdtlDao.getFinishingDispatchEntryDtlByHdrId(finish.getHdrId());
		
		finishingDTO.setActualWeight(finish.getActualWeight());
		finishingDTO.setChallanDate(finish.getChallanDate());
		finishingDTO.setChallanNo(finish.getChallanNo());
		finishingDTO.setDriverName(finish.getDriverName());
		finishingDTO.setGrossWeight(finish.getGrossWeight());
		finishingDTO.setHdrId(finish.getHdrId());
		finishingDTO.setInDate(finish.getInDate());
		finishingDTO.setInTime(finish.getInTime());
		finishingDTO.setNetWeight(finish.getNetWeight());
		finishingDTO.setOpenClose(finish.getOpenClose());
		finishingDTO.setOrderNo(finish.getOrderNo());
		finishingDTO.setOutDate(finish.getOutDate());
		finishingDTO.setOutTime(finish.getOutTime());
		finishingDTO.setTransporter(finish.getTransporter());
		finishingDTO.setUpdateBy(finish.getUpdateBy());
		finishingDTO.setUpdateDateTime(finish.getUpdateDateTime());
		finishingDTO.setVehicleNo(finish.getVehicleNo());
		finishingDTO.setVeighclePassNO(finish.getVeighclePassNO());
		finishingDTO.setWhomToDispatch(finish.getWhomToDispatch());
		
		List<FinishDispatchEntryDtlDTO> finishdtlsDTO = new ArrayList<>();
		FinishDispatchEntryDtlDTO finishdtl = new FinishDispatchEntryDtlDTO();
		finishingdtls.forEach(dtls->{
			
			finishdtl.setDtlId(dtls.getDtlId());
			finishdtl.setHdrId(dtls.getHdrId());
			finishdtl.setOpenClose(dtls.getOpenClose());
			finishdtl.setQuality(dtls.getQuality());
			finishdtl.setQualityDesc(dtls.getQualityDesc());
			finishdtl.setQuantity(dtls.getQuantity());
			finishdtl.setSrlNo(dtls.getSrlNo());
			finishdtl.setUnitId(dtls.getUnitId());
			
			finishdtlsDTO.add(finishdtl);
		});
		
		finishingDTO.setFinishdtls(finishdtlsDTO);
		
		return finishingDTO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sls.security.services.FinishingDispatchRegHdrService#getFinishingDispatchRegHdrById(long)
	 * Fetching Finishing Dispatch Register Header by hdrId
	 * 
	 * @Params
	 * 
	 * long hdrId
	 */
	@Override
	public FinishingDispatchRegHdrDTO getFinishingDispatchRegHdrById(long id) {
		FinishingDispatchRegHdr finishhdr =  finishingDao.getFinishingDispatchRegHdrById(id);
		
		List<FinishingDispatchEntryDtl> finishdtls = 
				finishdtlDao.getFinishingDispatchEntryDtlByHdrId(finishhdr.getHdrId());
									
		return prepareFinishingDispatchDTO(finishhdr, finishdtls);
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.sls.security.services.FinishingDispatchRegHdrService#saveFinishingDispatchRegHdr(com.sls.security.dto.FinishingDispatchRegHdrDTOSave)
	 * 
	 * Saving Data to the Database
	 * 
	 * @Params
	 * 
	 * FinishingDispatchRegHdrDTOSave
	 * 
	 */
	@Override
	public ResponseEntity<FinishingDispatchRegHdrDTOSave> saveFinishingDispatchRegHdr(FinishingDispatchRegHdrDTOSave finish) {
		FinishingDispatchRegHdr finishingEntitiy = new FinishingDispatchRegHdr();
		
		finishingEntitiy.setActualWeight(finish.getActualWeight());
		finishingEntitiy.setChallanDate(finish.getChallanDate());
		finishingEntitiy.setChallanNo(finish.getChallanNo());
		finishingEntitiy.setDriverName(finish.getDriverName());
		finishingEntitiy.setGrossWeight(finish.getGrossWeight());
		finishingEntitiy.setHdrId(finish.getHdrId());
		finishingEntitiy.setInDate(Date.valueOf(LocalDate.now()));
		finishingEntitiy.setInTime(Timestamp.valueOf(LocalDateTime.now()));
		finishingEntitiy.setNetWeight(finish.getNetWeight());
		finishingEntitiy.setOpenClose(finish.getOpenClose());
		finishingEntitiy.setOrderNo(finish.getOrderNo());
		finishingEntitiy.setOutDate(finish.getOutDate());
		finishingEntitiy.setOutTime(finish.getOutTime());
		finishingEntitiy.setTransporter(finish.getTransporter());
		finishingEntitiy.setUpdateBy(finish.getUpdateBy());
		finishingEntitiy.setUpdateDateTime(finish.getUpdateDateTime());
		finishingEntitiy.setVehicleNo(finish.getVehicleNo());
		finishingEntitiy.setVeighclePassNO(finish.getVeighclePassNO());
		finishingEntitiy.setWhomToDispatch(finish.getWhomToDispatch());
		finishingEntitiy.setOpenClose("2");
		
		FinishingDispatchRegHdr savedhdr = finishingDao.saveFinishingDispatchRegHdr(finishingEntitiy);
		
		/*List<FinishDispatchEntryDtlDTO> finishdtls = finish.getFinishdtls();
		List<FinishDispatchEntryDtlDTO> empFinsihdtls = new ArrayList<>();
		FinishDispatchEntryDtlDTO finishdtl = new FinishDispatchEntryDtlDTO();
		
		finishdtls.forEach(finishs->{
			finishdtl.setHdrId(savedhdr.getHdrId());
			finishdtl.setOpenClose(finish.getOpenClose());
			finishdtl.setQuality(finishs.getQuality());
			
			SelectioinQuallityMst price = priceDao.getAllSelectioinQuallityMstByQuality(finishs.getQuality());
			finishdtl.setQualityDesc(price.getQuality());
			
			finishdtl.setQuantity(finishs.getQuantity());
			finishdtl.setSrlNo(finishs.getSrlNo());
			finishdtl.setUnitId(finishs.getUnitId());
			
			finishdtlDao.saveFinishingDispatchEntryDtl(prepareFinishingDtlDTO(finishdtl));
		});
		*/
		return new ResponseEntity<FinishingDispatchRegHdrDTOSave>(finish, HttpStatus.CREATED);

	}

	
	/*
	 * preparing Finishing Reg Dtl DTO
	 * 
	 * @Param
	 * 
	 * FinishDispatchEntryDtlDTO
	 * 
	 */
	private FinishingDispatchEntryDtl prepareFinishingDtlDTO(FinishDispatchEntryDtlDTO finishdtl) {
		FinishingDispatchEntryDtl finishEntity = new FinishingDispatchEntryDtl();
		
		finishEntity.setHdrId(finishdtl.getHdrId());
		finishEntity.setOpenClose(finishdtl.getOpenClose());
		finishEntity.setQuality(finishdtl.getQuality());
		finishEntity.setQualityDesc(finishdtl.getQualityDesc());
		finishEntity.setQuantity(finishdtl.getQuantity());
		finishEntity.setSrlNo(finishdtl.getSrlNo());
		finishEntity.setUnitId(finishdtl.getUnitId());
		
		return finishEntity;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.sls.security.services.FinishingDispatchRegHdrService#updateFinishingDispatchRegHdr(com.sls.security.dto.FinishingDispatchRegHdrDTO)
	 * 
	 * updating finishing dispatch reg hdr
	 * 
	 * @Param
	 * 
	 * FinishingDispatchRegHdrDTO
	 * 
	 */
	@Override
	public ResponseEntity<FinishingDispatchRegHdrDTO> updateFinishingDispatchRegHdr(FinishingDispatchRegHdrDTO finish) {
		
		FinishingDispatchRegHdr finishingEntitiy = finishingDao.getFinishingDispatchRegHdrById(finish.getHdrId());
		
//		finishingEntitiy.setActualWeight(finish.getActualWeight());
		finishingEntitiy.setChallanDate(finish.getChallanDate());
		finishingEntitiy.setChallanNo(finish.getChallanNo());
		finishingEntitiy.setDriverName(finish.getDriverName());
		finishingEntitiy.setGrossWeight(finish.getGrossWeight());
		finishingEntitiy.setHdrId(finish.getHdrId());
		finishingEntitiy.setInDate(finish.getInDate());
		finishingEntitiy.setInTime(finish.getInTime());
		finishingEntitiy.setNetWeight(finish.getNetWeight());
		finishingEntitiy.setOpenClose(finish.getOpenClose());
		finishingEntitiy.setOrderNo(finish.getOrderNo());
		finishingEntitiy.setOutDate(finish.getOutDate());
		finishingEntitiy.setOutTime(finish.getOutTime());
		finishingEntitiy.setTransporter(finish.getTransporter());
		finishingEntitiy.setUpdateBy(finish.getUpdateBy());
		finishingEntitiy.setUpdateDateTime(finish.getUpdateDateTime());
		finishingEntitiy.setVehicleNo(finish.getVehicleNo());
		finishingEntitiy.setVeighclePassNO(finish.getVeighclePassNO());
		finishingEntitiy.setWhomToDispatch(finish.getWhomToDispatch());
		finishingEntitiy.setOutDate(Date.valueOf(LocalDate.now()));
		finishingEntitiy.setOutTime(Timestamp.valueOf(LocalDateTime.now()));
		long actualWt = finishingEntitiy.getGrossWeight() - finish.getNetWeight();
		finishingEntitiy.setActualWeight(actualWt);
		
		finishingDao.saveFinishingDispatchRegHdr(finishingEntitiy);
		
		List<FinishDispatchEntryDtlDTO> finishdtls = finish.getFinishdtls();
		List<FinishDispatchEntryDtlDTO> empFinsihdtls = new ArrayList<>();
//		FinishDispatchEntryDtlDTO finishdtl = new FinishDispatchEntryDtlDTO();
		
		for(int i = 0 ; i < finishdtls.size() ; i++) {
			
			List<FinishingDispatchEntryDtl> entrydtls = 
					finishdtlDao.getFinishingDispatchEntryDtlByHdrId(finishdtls.get(i).getHdrId());
			
			if(entrydtls.isEmpty()) {
				FinishDispatchEntryDtlDTO finishdtl = new FinishDispatchEntryDtlDTO();
				finishdtl.setHdrId(finishingEntitiy.getHdrId());
				finishdtl.setOpenClose("2");
				finishdtl.setQuality(finishdtls.get(i).getQuality());
				
				SelectioinQuallityMst price = 
								priceDao.getSelectioinQuallityMstById(Long.parseLong(finishdtls.get(i).getQuality()));
				finishdtl.setQualityDesc(price.getQuality());
				
				finishdtl.setQuantity(finishdtls.get(i).getQuantity());
				finishdtl.setSrlNo(finishdtls.get(i).getSrlNo());
				finishdtl.setUnitId(finishdtls.get(i).getUnitId());
				
				finishdtlDao.saveFinishingDispatchEntryDtl(prepareFinishingDtlDTO(finishdtl));
			}else {
				FinishingDispatchEntryDtl entrydtl = new FinishingDispatchEntryDtl(); 
				if(i < entrydtls.size()) {
					 entrydtl = entrydtls.get(i);
				}
				entrydtl.setHdrId(finishingEntitiy.getHdrId());
				entrydtl.setOpenClose("2");
				entrydtl.setQuality(finishdtls.get(i).getQuality());
				
				SelectioinQuallityMst price = 
								priceDao.getSelectioinQuallityMstById(Long.parseLong(finishdtls.get(i).getQuality()));
				entrydtl.setQualityDesc(price.getQuality());
				
				entrydtl.setQuantity(finishdtls.get(i).getQuantity());
//				entrydtl.setQuantity(3);
				entrydtl.setSrlNo(finishdtls.get(i).getSrlNo());
				entrydtl.setUnitId(finishdtls.get(i).getUnitId());
				
				finishdtlDao.saveFinishingDispatchEntryDtl(entrydtl);
				
			}
		}
		
		
		
		
		return new ResponseEntity<FinishingDispatchRegHdrDTO>(finish, HttpStatus.CREATED);

	}

	/*
	 * (non-Javadoc)
	 * @see com.sls.security.services.FinishingDispatchRegHdrService#deleteFinishingDispatchRegHdr(long)
	 * 
	 * deleting finishing Dispatch reg hdr data
	 * 
	 * @Param
	 * 
	 * long id
	 */
	@Override
	public void deleteFinishingDispatchRegHdr(long id) {
		finishingDao.deleteFinishingDispatchRegHdr(id);

	}

	
	/*
	 * (non-Javadoc)
	 * @see com.sls.security.services.FinishingDispatchRegHdrService#getAllFinishingDispatchByDate(java.sql.Date, java.sql.Date)
	 * 
	 * fetching finishing dispatch reg hdr by indate
	 * 
	 *  @Params
	 *  
	 *  Date fromDate, Date toDate
	 *  
	 */
	@Override
	public List<FinishingDispatchRegHdrDTO> getAllFinishingDispatchByDate(Date fromDate, Date toDate) {
		List<FinishingDispatchRegHdrDTO> finishingDispatchDTOs = new ArrayList<>();
		
		try {
			List<FinishingDispatchRegHdr> finishingdiapatch = 
									finishingDao.getAllFinishingDispatchRegHdrByDate(fromDate, toDate);
			finishingdiapatch.forEach(finishhdr->{
				List<FinishingDispatchEntryDtl> finishdtls = 
						finishdtlDao.getFinishingDispatchEntryDtlByHdrId(finishhdr.getHdrId());
				finishingDispatchDTOs.add(prepareFinishingDispatchDTO(finishhdr, finishdtls));
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return finishingDispatchDTOs;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.sls.security.services.FinishingDispatchRegHdrService#getAllFinishingDispatchRegHdrByChallanNo(java.lang.String)
	 * 
	 * Fetching Finishing Dispatch reg hdr by challanNo
	 * 
	 * @Param
	 * 
	 * String challanNo
	 */
	@Override
	public FinishingDispatchRegHdrDTO getAllFinishingDispatchRegHdrByChallanNo(String challanNo) {
		/*List<FinishingDispatchRegHdrDTO> finishingDTOs = new ArrayList<>();
		try {
			List<FinishingDispatchRegHdr> finishingEntity = finishingDao.getFinishingDispatchByChallanNo(challanNo);
			finishingEntity.forEach(finish->{
				finishingDTOs.add(prepareFinishingDispatchDTO(finish));
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		*/
		return prepareFinishingDispatchDTO(finishingDao.getFinishingDispatchByChallanNo(challanNo));
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.sls.security.services.FinishingDispatchRegHdrService#updateFinishingDispatchRegHdrOut(com.sls.security.dto.FinishingDispatchRegHdrDTO)
	 * 
	 * updating Finishing Dispatch Reg Hdr
	 * 
	 * @Param
	 * 
	 * FinishingDispatchRegHdrDTO finish
	 * 
	 */
	@Override
	public ResponseEntity<FinishingDispatchRegHdrDTO> updateFinishingDispatchRegHdrOut(
			FinishingDispatchRegHdrDTO finish) {
FinishingDispatchRegHdr finishingEntitiy = finishingDao.getFinishingDispatchRegHdrById(finish.getHdrId());
		
		finishingEntitiy.setActualWeight(finish.getActualWeight());
		finishingEntitiy.setChallanDate(finish.getChallanDate());
		finishingEntitiy.setChallanNo(finish.getChallanNo());
		finishingEntitiy.setDriverName(finish.getDriverName());
		finishingEntitiy.setGrossWeight(finish.getGrossWeight());
		finishingEntitiy.setHdrId(finish.getHdrId());
		finishingEntitiy.setInDate(finish.getInDate());
		finishingEntitiy.setInTime(finish.getInTime());
		finishingEntitiy.setNetWeight(finish.getNetWeight());
		finishingEntitiy.setOpenClose(finish.getOpenClose());
		finishingEntitiy.setOrderNo(finish.getOrderNo());
		finishingEntitiy.setOutDate(finish.getOutDate());
		finishingEntitiy.setOutTime(finish.getOutTime());
		finishingEntitiy.setTransporter(finish.getTransporter());
		finishingEntitiy.setUpdateBy(finish.getUpdateBy());
		finishingEntitiy.setUpdateDateTime(finish.getUpdateDateTime());
		finishingEntitiy.setVehicleNo(finish.getVehicleNo());
		finishingEntitiy.setVeighclePassNO(finish.getVeighclePassNO());
		finishingEntitiy.setWhomToDispatch(finish.getWhomToDispatch());
		finishingEntitiy.setOutDate(Date.valueOf(LocalDate.now()));
		finishingEntitiy.setOutTime(Timestamp.valueOf(LocalDateTime.now()));
		
		finishingDao.saveFinishingDispatchRegHdr(finishingEntitiy);
		
		return new ResponseEntity<FinishingDispatchRegHdrDTO>(finish, HttpStatus.CREATED);
	}

}
