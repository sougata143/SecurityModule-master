package com.sls.security.services.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sls.security.component.BrokerDao;
import com.sls.security.component.ItemMasterDao;
import com.sls.security.component.JuteGateEntryDtlComponent;
import com.sls.security.component.JuteGateEntryHdrComponent;
import com.sls.security.component.JuteQualityPriceMasterComponent;
import com.sls.security.component.MukamDao;
import com.sls.security.component.MukamJuteComponent;
import com.sls.security.component.POLineItemComponent;
import com.sls.security.component.SupplierMasterDao;
import com.sls.security.component.VehicleComponent;
import com.sls.security.dto.JuteEntryDtlLineItemDTO;
import com.sls.security.dto.JuteEntryDtlLineItemdDTO;
import com.sls.security.dto.JuteEntryDtlLineItemsDTO;
import com.sls.security.dto.JuteEntryHeaderDTO;
import com.sls.security.dto.JuteEntryHeaderdDTO;
import com.sls.security.entity.JuteEntryHeader;
import com.sls.security.entity.JuteGateEntryDtl;
import com.sls.security.entity.Mukam;
import com.sls.security.entity.SupplierMaster;
import com.sls.security.entity.Vehicle;

@Component
public class PreparingJuteGateEntryDTOService {

	@Autowired
	JuteGateEntryHdrComponent juteEntryDao;

	@Autowired
	JuteGateEntryDtlComponent juteentrydtlDao;

	@Autowired
	POLineItemComponent polineitemDao;

	@Autowired
	MukamDao mukamDao;

	@Autowired
	MukamJuteComponent mukamjuteDao;

	@Autowired
	JuteQualityPriceMasterComponent pricemasterDao;

	@Autowired
	ItemMasterDao itemDao;

	@Autowired
	SupplierMasterDao supplierDao;

	@Autowired
	BrokerDao brokerDao;

	@Autowired
	POLineItemComponent polineDao;

	@Autowired
	VehicleComponent vehicleDao;
	
	/*
	 * Converting the DTO for Jute Gate Entry Header from Entity object of the same
	 */
	public JuteEntryHeaderDTO prepareJuteGateEntryDTO(JuteEntryHeader hdr) {

		JuteEntryHeaderDTO entryDTO = new JuteEntryHeaderDTO();
		if (hdr != null) {

			Mukam mukam = new Mukam();
			if (hdr.getMukam() != null) {
				mukam = mukamDao.findMukamById(Long.valueOf(hdr.getMukam()));
			}

			entryDTO.setActualWeight(hdr.getActualWeight());
			entryDTO.setBrokerAddress(hdr.getBrokerAddress());
			entryDTO.setBrokerName(hdr.getBrokerName());
			entryDTO.setChalanDate(hdr.getChalanDate());
			entryDTO.setChalanNo(hdr.getChalanNo());
			entryDTO.setDriverName(hdr.getDriverName());
			entryDTO.setFinYear(hdr.getFinYear());
			entryDTO.setGrossWeight(hdr.getGrossWeight());
			entryDTO.setId(hdr.getId());
			entryDTO.setInDate(hdr.getInDate());
			entryDTO.setInTime(String.valueOf(hdr.getInTime()));
			entryDTO.setMrNo(hdr.getMrNo());
			entryDTO.setMukam(mukam.getmukamName());
			entryDTO.setNetWeight(hdr.getNetWeight());
			entryDTO.setOpenClose(hdr.getOpenClose());
			entryDTO.setOutDate(hdr.getOutDate());
			
			String outtime = null;
			if(String.valueOf(hdr.getOutTime()).equalsIgnoreCase("null")) {
				outtime = null;
			}else {
				outtime = String.valueOf(hdr.getOutTime());
			}
			entryDTO.setOutTime(outtime);
			
			entryDTO.setTareWeight(hdr.getNetWeight());

			Vehicle vehicle = vehicleDao.getVehilceById(hdr.getVehicleType());
			entryDTO.setVehicleType(vehicle.getVehicleType());

			/*
			 * List<String> ponos = new ArrayList<>(); List<JuteEntryHeader> entryheaders =
			 * juteEntryDao.getJuteEntryHeaderByChallanNo(hdr.getChalanNo()); for(int p = 0
			 * ; p < entryheaders.size() ; p++) { String pono =
			 * entryheaders.get(p).getPoNo(); ponos.add(pono); }
			 * entryDTO.setPoNo(ponos.stream().distinct().collect(Collectors.toList()));
			 */
			entryDTO.setPoNo(hdr.getPoNo());

			entryDTO.setTransporter(hdr.getTransporter());
			entryDTO.setUpdateBy(hdr.getUpdateBy());
			entryDTO.setUpdateDateTime(hdr.getUpdateDateTime());
			entryDTO.setVehicleNo(hdr.getVehicleNo());

			SupplierMaster supplier = supplierDao.findSupplierMasterById(hdr.getSuppCode());
			entryDTO.setSuppCode(hdr.getSuppCode());
			entryDTO.setSupplierName(hdr.getSupplierName());
			entryDTO.setSuppAddress(supplier.getaddress1());

			entryDTO.setChallanWeight(hdr.getChallanWeight());

			List<JuteGateEntryDtl> polineitems = juteentrydtlDao.getJuteGateEntryDtlByHdrId(hdr.getId());

			List<JuteEntryDtlLineItemsDTO> emptyJuteEntryDtlDTOs = new ArrayList<>();
			List<JuteEntryDtlLineItemDTO> emptyJuteEntryDtlDTO = new ArrayList<>();
			for (int x = 0; x < polineitems.size(); x++) {
				JuteEntryDtlLineItemDTO lineitememp = new JuteEntryDtlLineItemDTO();
				// ItemMaster item =
				// itemDao.findItemMasterById(polineitems.get(x).getItemCode());

				lineitememp.setActualJuteType(polineitems.get(x).getActualJuteTyp());
				lineitememp.setActualQuality(polineitems.get(x).getActualQuality());
				lineitememp.setActualQuantity(polineitems.get(x).getActualQuantity());
				lineitememp.setAdvisedJuteType(polineitems.get(x).getAdvisedJuteTyp());
				lineitememp.setAdvisedQuality(polineitems.get(x).getAdvisedQuality());
				lineitememp.setAdvisedQuantity(Long.parseLong(polineitems.get(x).getAdvisedQuantity()));
				lineitememp.setDtlId(polineitems.get(x).getRecId());
				lineitememp.setHdrId(polineitems.get(x).getHdrId());
				// lineitememp.setIsPOAmment(polineitems.get(x).getIsPoAmment());
				lineitememp.setItemCode(polineitems.get(x).getItemCode());
				lineitememp.setKgs(polineitems.get(x).getKgs());
				lineitememp.setOpenClose(polineitems.get(x).getOpenClose());
				// lineitememp.setPoid(polineitems.get(x).getpo);
				lineitememp.setPolineitemnum(polineitems.get(x).getPoLineItemNum());
				lineitememp.setQuantity(polineitems.get(x).getQuantity());
				lineitememp.setReceivedIn(polineitems.get(x).getReceivedIn());
				lineitememp.setRemarks(polineitems.get(x).getRemarks());
				lineitememp.setUom(polineitems.get(x).getUom());

				emptyJuteEntryDtlDTO.add(lineitememp);
			}
			JuteEntryDtlLineItemsDTO emptyJuteEntryDtlDTOs2 = new JuteEntryDtlLineItemsDTO();
			emptyJuteEntryDtlDTOs2.setPolineitems(emptyJuteEntryDtlDTO);
			emptyJuteEntryDtlDTOs.add(emptyJuteEntryDtlDTOs2);
			entryDTO.setPolineitem(emptyJuteEntryDtlDTO);

		}
		return entryDTO;
	}

	/*
	 * converting the Entity of Jute Gate Entry Dtl to DTO
	 */
	private JuteEntryDtlLineItemDTO prepareEntryDtlDTO(JuteGateEntryDtl entrydtl) {
		JuteEntryDtlLineItemDTO juteentrydtl = new JuteEntryDtlLineItemDTO();

		juteentrydtl.setActualJuteType(entrydtl.getActualJuteTyp());
		juteentrydtl.setActualQuality(entrydtl.getActualQuality());
		juteentrydtl.setActualQuantity(entrydtl.getActualQuantity());
		juteentrydtl.setAdvisedJuteType(entrydtl.getAdvisedJuteTyp());
		juteentrydtl.setAdvisedQuality(entrydtl.getAdvisedQuality());
		juteentrydtl.setAdvisedQuantity(Long.parseLong(entrydtl.getAdvisedQuantity()));
		juteentrydtl.setHdrId(entrydtl.getHdrId());
		juteentrydtl.setItemCode(entrydtl.getItemCode());
		juteentrydtl.setKgs(entrydtl.getKgs());
		juteentrydtl.setOpenClose(entrydtl.getOpenClose());
		juteentrydtl.setRemarks(entrydtl.getRemarks());
		juteentrydtl.setReceivedIn(entrydtl.getReceivedIn());
		juteentrydtl.setRemarks(entrydtl.getRemarks());
		juteentrydtl.setUom(entrydtl.getUom());
		juteentrydtl.setPolineitemnum(entrydtl.getPoLineItemNum());
		juteentrydtl.setDtlId(entrydtl.getRecId());

		if (entrydtl.getIsPoAmment().equals("1")) {
			juteentrydtl.setIsPOAmment(true);
		} else {
			juteentrydtl.setIsPOAmment(false);
		}

		return juteentrydtl;
	}
	
	public JuteEntryHeaderdDTO prepareJuteGateEntrydDTO(JuteEntryHeader hdr) {
		JuteEntryHeaderdDTO entryDTO = new JuteEntryHeaderdDTO();
		if (hdr != null) {

			Mukam mukam = new Mukam();
			if (hdr.getMukam() != null) {
				mukam = mukamDao.findMukamById(Long.valueOf(hdr.getMukam()));
			}

			entryDTO.setActualWeight(hdr.getActualWeight());
			entryDTO.setBrokerAddress(hdr.getBrokerAddress());
			entryDTO.setBrokerName(hdr.getBrokerName());
			entryDTO.setChalanDate(hdr.getChalanDate());
			entryDTO.setChalanNo(hdr.getChalanNo());
			entryDTO.setDriverName(hdr.getDriverName());
			entryDTO.setFinYear(hdr.getFinYear());
			entryDTO.setGrossWeight(hdr.getGrossWeight());
			entryDTO.setId(hdr.getId());
			entryDTO.setInDate(hdr.getInDate());
			entryDTO.setInTime(String.valueOf(hdr.getInTime()));
			entryDTO.setMrNo(hdr.getMrNo());
			entryDTO.setMukam(mukam.getmukamName());
			entryDTO.setNetWeight(hdr.getNetWeight());
			entryDTO.setOpenClose(hdr.getOpenClose());
			entryDTO.setOutDate(hdr.getOutDate());
			entryDTO.setOutTime(hdr.getOutTime());
			entryDTO.setTareWeight(hdr.getNetWeight());

			Vehicle vehicle = vehicleDao.getVehilceById(hdr.getVehicleType());
			entryDTO.setVehicleType(vehicle.getVehicleType());

			/*
			 * List<String> ponos = new ArrayList<>(); List<JuteEntryHeader> entryheaders =
			 * juteEntryDao.getJuteEntryHeaderByChallanNo(hdr.getChalanNo()); for(int p = 0
			 * ; p < entryheaders.size() ; p++) { String pono =
			 * entryheaders.get(p).getPoNo(); ponos.add(pono); }
			 * entryDTO.setPoNo(ponos.stream().distinct().collect(Collectors.toList()));
			 */
			entryDTO.setPoNo(hdr.getPoNo());

			entryDTO.setTransporter(hdr.getTransporter());
			entryDTO.setUpdateBy(hdr.getUpdateBy());
			entryDTO.setUpdateDateTime(hdr.getUpdateDateTime());
			entryDTO.setVehicleNo(hdr.getVehicleNo());

			SupplierMaster supplier = supplierDao.findSupplierMasterById(hdr.getSuppCode());
			entryDTO.setSuppCode(hdr.getSuppCode());
			entryDTO.setSupplierName(hdr.getSupplierName());
			entryDTO.setSuppAddress(supplier.getaddress1());

			entryDTO.setChallanWeight(hdr.getChallanWeight());

			List<JuteGateEntryDtl> polineitems = juteentrydtlDao.getJuteGateEntryDtlByHdrId(hdr.getId());

			List<JuteEntryDtlLineItemsDTO> emptyJuteEntryDtlDTOs = new ArrayList<>();
			List<JuteEntryDtlLineItemdDTO> emptyJuteEntryDtlDTO = new ArrayList<>();
			for (int x = 0; x < polineitems.size(); x++) {
				JuteEntryDtlLineItemdDTO lineitememp = new JuteEntryDtlLineItemdDTO();
				// ItemMaster item =
				// itemDao.findItemMasterById(polineitems.get(x).getItemCode());

				lineitememp.setActualJuteTyp(polineitems.get(x).getActualJuteTyp());
				lineitememp.setActualQuality(polineitems.get(x).getActualQuality());
				lineitememp.setActualQuantity(polineitems.get(x).getActualQuantity());
				lineitememp.setAdvisedJuteTyp(polineitems.get(x).getAdvisedJuteTyp());
				lineitememp.setAdvisedQuality(polineitems.get(x).getAdvisedQuality());
				lineitememp.setAdvisedQuantity(Long.parseLong(polineitems.get(x).getAdvisedQuantity()));
				lineitememp.setDtlId(polineitems.get(x).getRecId());
				lineitememp.setHdrId(polineitems.get(x).getHdrId());
				// lineitememp.setIsPOAmment(polineitems.get(x).getIsPoAmment());
				lineitememp.setItemCode(polineitems.get(x).getItemCode());
				lineitememp.setKgs(polineitems.get(x).getKgs());
				lineitememp.setOpenClose(polineitems.get(x).getOpenClose());
				// lineitememp.setPoid(polineitems.get(x).getpo);
				lineitememp.setPolineitemnum(polineitems.get(x).getPoLineItemNum());
				lineitememp.setQuantity(polineitems.get(x).getQuantity());
				lineitememp.setReceivedIn(polineitems.get(x).getReceivedIn());
				lineitememp.setRemarks(polineitems.get(x).getRemarks());
				lineitememp.setUom(polineitems.get(x).getUom());

				emptyJuteEntryDtlDTO.add(lineitememp);
			}
			JuteEntryDtlLineItemsDTO emptyJuteEntryDtlDTOs2 = new JuteEntryDtlLineItemsDTO();
			// emptyJuteEntryDtlDTOs2.setPolineitems(emptyJuteEntryDtlDTO);
			// emptyJuteEntryDtlDTOs.add(emptyJuteEntryDtlDTOs2);
			entryDTO.setPolineitem(emptyJuteEntryDtlDTO);

		}
		return entryDTO;
	}
	
	/*
	 * private JuteEntryHeaderDTO prepareJuteGateEntryNewDTO(JuteEntryHeader hdr) {
	 * JuteEntryHeaderDTO entryDTO = new JuteEntryHeaderDTO(); if(hdr != null) {
	 * 
	 * Mukam mukam = new Mukam(); if(hdr.getMukam()!=null) { mukam =
	 * mukamDao.findMukamById(Long.valueOf(hdr.getMukam())); }
	 * 
	 * entryDTO.setActualWeight(hdr.getActualWeight());
	 * entryDTO.setChalanDate(hdr.getChalanDate());
	 * entryDTO.setChalanNo(hdr.getChalanNo());
	 * entryDTO.setDriverName(hdr.getDriverName());
	 * entryDTO.setFinYear(hdr.getFinYear());
	 * entryDTO.setGrossWeight(hdr.getGrossWeight()); entryDTO.setId(hdr.getId());
	 * entryDTO.setInDate(hdr.getInDate());
	 * entryDTO.setInTime(String.valueOf(hdr.getInTime()));
	 * entryDTO.setMrNo(hdr.getMrNo()); entryDTO.setMukam(mukam.getmukamName());
	 * entryDTO.setNetWeight(hdr.getNetWeight());
	 * entryDTO.setOpenClose(hdr.getOpenClose());
	 * entryDTO.setOutDate(hdr.getOutDate()); entryDTO.setOutTime(hdr.getOutTime());
	 * entryDTO.setPoNo(hdr.getPoNo());
	 * entryDTO.setTransporter(hdr.getTransporter());
	 * entryDTO.setUpdateBy(hdr.getUpdateBy());
	 * entryDTO.setUpdateDateTime(hdr.getUpdateDateTime());
	 * entryDTO.setVehicleNo(hdr.getVehicleNo());
	 * 
	 * if(hdr.getBrokerName()!=null) { Broker broker =
	 * brokerDao.getBrokerByBrokerName(hdr.getBrokerName());
	 * entryDTO.setBrokerAddress(hdr.getBrokerAddress());
	 * entryDTO.setBrokerName(hdr.getBrokerName());
	 * entryDTO.setSuppCode(String.valueOf(broker.getBrokerId()));
	 * entryDTO.setSuppAddress(broker.getAddress());
	 * entryDTO.setSupplierName(broker.getBrokerName()); }else
	 * if(hdr.getSuppCode()!=null) { SupplierMaster supplier =
	 * supplierDao.findSupplierMasterById(hdr.getSuppCode());
	 * entryDTO.setBrokerAddress(supplier.getaddress1());
	 * entryDTO.setBrokerName(supplier.getsuppName());
	 * entryDTO.setSuppCode(supplier.getId());
	 * entryDTO.setSuppAddress(supplier.getaddress1());
	 * entryDTO.setSupplierName(supplier.getsuppName()); }
	 * 
	 * entryDTO.setChallanWeight(hdr.getChallanWeight());
	 * 
	 * List<JuteGateEntryDtl> polineitems =
	 * juteentrydtlDao.getJuteGateEntryDtlByHdrId(hdr.getId());
	 * 
	 * List<JuteEntryDtlLineItemDTO> emptyJuteEntryDtlDTOs = new ArrayList<>();
	 * polineitems.forEach(lineitem->{
	 * emptyJuteEntryDtlDTOs.add(prepareEntryDtlDTO(lineitem)); });
	 * 
	 * entryDTO.setPolineitem(emptyJuteEntryDtlDTOs);
	 * 
	 * } return entryDTO; }
	 */
}
