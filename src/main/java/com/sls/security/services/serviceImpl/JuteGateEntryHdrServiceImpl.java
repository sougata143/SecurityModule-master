package com.sls.security.services.serviceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
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
import com.sls.security.dto.DeleteDTO;
import com.sls.security.dto.JuteEntryDtlLineItemDTO;
import com.sls.security.dto.JuteEntryDtlLineItemdDTO;
import com.sls.security.dto.JuteEntryDtlLineItemsDTO;
import com.sls.security.dto.JuteEntryHeaderDTO;
import com.sls.security.dto.JuteEntryHeaderdDTO;
import com.sls.security.entity.Broker;
import com.sls.security.entity.ItemMaster;
import com.sls.security.entity.JuteEntryHeader;
import com.sls.security.entity.JuteGateEntryDtl;
import com.sls.security.entity.JuteQualityPriceMaster;
import com.sls.security.entity.Mukam;
import com.sls.security.entity.MukamJute;
import com.sls.security.entity.POLineItem;
import com.sls.security.entity.SupplierMaster;
import com.sls.security.entity.Vehicle;
import com.sls.security.services.JuteGateEntryHdrService;

/*
 * Service class for JUTE GATE ENTRY REG HEADER
 */
@Service
public class JuteGateEntryHdrServiceImpl implements JuteGateEntryHdrService {

	@Autowired
	PreparingJuteGateEntryDTOService preparingJuteGateEntryDTOService;

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
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sls.security.services.JuteGateEntryHdrService#getAllJuteGateEntryHeader()
	 * 
	 * Fetching All Jute Gate Entry Header from database
	 * 
	 */
	@Override
	public List<JuteEntryHeaderDTO> getAllJuteGateEntryHeader() {
		List<JuteEntryHeaderDTO> entryHdrDtoList = new ArrayList<>();
		try {
			List<JuteEntryHeader> entryHdrList = juteEntryDao.getAllJuteEntryHeader();
			entryHdrList.forEach(hdr -> {
				entryHdrDtoList.add(preparingJuteGateEntryDTOService.prepareJuteGateEntryDTO(hdr));
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entryHdrDtoList.stream().sorted(Comparator.comparing(JuteEntryHeaderDTO::getInTime).reversed())
				.collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sls.security.services.JuteGateEntryHdrService#saveJuteGateEntryHeader(com
	 * .sls.security.dto.JuteEntryHeaderDTO)
	 * 
	 * saving Jute Gate Entry Header object to the Jute Gate Entry Header table as
	 * well as Jute Entry Dtl object to the Jute Gate Entry Dtl table
	 * 
	 * @Param
	 * 
	 * JuteEntryHeaderDTO object
	 * 
	 */
	@Override
	public ResponseEntity<JuteEntryHeaderDTO> saveJuteGateEntryHeader(JuteEntryHeaderDTO entryHdr) {

		if (!Strings.isNullOrEmpty(entryHdr.getPoNo())) {
			System.out.println("inside with PO");
			JuteEntryHeader juteentryhdrEntity = new JuteEntryHeader();
			List<String> pos = new ArrayList<>();
			long chalanNo = 0;
			// for(int i = 0 ; i < entryHdr.getPoNo().size() ; i++) {
			Mukam mukams = new Mukam();
			if (entryHdr.getMukam() != null)
				mukams = mukamDao.findByMukamName(entryHdr.getMukam());
			juteentryhdrEntity.setChalanDate(Date.valueOf(entryHdr.getChalanDate().toLocalDate()));
			juteentryhdrEntity.setChalanNo(entryHdr.getChalanNo());
			juteentryhdrEntity.setDriverName(entryHdr.getDriverName());
			juteentryhdrEntity.setFinYear(entryHdr.getFinYear());
			juteentryhdrEntity.setGrossWeight(entryHdr.getGrossWeight());
			juteentryhdrEntity.setId(entryHdr.getId());

			// juteentryhdrEntity.setInDate(Date.valueOf(LocalDate.now()));
			juteentryhdrEntity.setInDate(Date.valueOf(entryHdr.getInDate().toLocalDate()));
			// juteentryhdrEntity.setInTime(Timestamp.valueOf(LocalDateTime.now()));
			// juteentryhdrEntity.setInTime(new Timestamp(System.currentTimeMillis()));
			/*
			 * String time = entryHdr.getInTime(); String t =
			 * String.valueOf(time.substring(5, 6)); System.out.println("time "+t); long hh
			 * = 0; if(!t.equalsIgnoreCase("A")) { hh = Long.valueOf(time.substring(0,
			 * 1))+12; }else { hh = Long.valueOf(time.substring(0, 1))+0; } long mm =
			 * Long.valueOf(time.substring(2,4)); System.out.println("mm "+mm); String
			 * intime = String.valueOf(hh)+":"+String.valueOf(mm)+":"+"10";
			 * System.out.println(intime); System.out.println("time1 "+hh);
			 */
			juteentryhdrEntity.setInTime(
					Timestamp.valueOf(String.valueOf(entryHdr.getInDate()) + " " + entryHdr.getInTime() + ".3654"));

			juteentryhdrEntity.setMrNo(juteentrydtlDao.getMRSeq()); // calling sequence to generate mr no

			if (mukams != null) {
				juteentryhdrEntity.setMukam(String.valueOf(mukams.getId()));
			}

			juteentryhdrEntity.setNetWeight(entryHdr.getTareWeight());
			juteentryhdrEntity.setOpenClose("2");
			juteentryhdrEntity.setOutDate(null);
			juteentryhdrEntity.setOutTime(null);

			/*
			 * List<String> ponos = new ArrayList<>(); if(entryHdr.getPoNo()!=null) {
			 * List<String> poids = entryHdr.getPoNo(); for(int k = 0 ; k < poids.size() ;
			 * k++) { String pono = poids.get(k); juteentryhdrEntity.setPoNo(pono);
			 * ponos.add(pono); } juteentryhdrEntity.setPoNo(ponos.get(i)); }
			 */
			juteentryhdrEntity.setPoNo(entryHdr.getPoNo());

			juteentryhdrEntity.setTransporter(entryHdr.getTransporter());
			juteentryhdrEntity.setUpdateBy(entryHdr.getUpdateBy());
			juteentryhdrEntity.setUpdateDateTime(null);
			juteentryhdrEntity.setVehicleNo(entryHdr.getVehicleNo());
			juteentryhdrEntity.setChallanWeight(entryHdr.getChallanWeight());
			// juteentryhdrEntity.setSuppCode(entryHdr.getSuppCode());

			/*
			 * SupplierMaster supplierByCode =
			 * supplierDao.findSupplierMasterById(entryHdr.getSuppCode()); SupplierMaster
			 * supplier = supplierDao.findBySuppName(entryHdr.getBrokerName());
			 * 
			 * juteentryhdrEntity.setSupplierName(supplier.getsuppName());
			 * juteentryhdrEntity.setBrokerName(entryHdr.getBrokerName()); //
			 * juteentryhdrEntity.setBrokerName(supplier.getsuppName()); //
			 * juteentryhdrEntity.setSupplierName(entryHdr.getSupplierName()); //
			 * juteentryhdrEntity.setBrokerAddress(entryHdr.getBrokerAddress());
			 * juteentryhdrEntity.setBrokerAddress(supplier.getaddress1());
			 * juteentryhdrEntity.setSuppCode(supplier.getId());
			 */

			if (entryHdr.getSuppCode().startsWith("J")) {
				SupplierMaster supplierByCode = supplierDao.findSupplierMasterById(entryHdr.getSuppCode());
				juteentryhdrEntity.setSupplierName(supplierByCode.getsuppName());
				juteentryhdrEntity.setSuppCode(supplierByCode.getId());
			} else {
				Broker broker = brokerDao.getBrokerById(Long.parseLong(entryHdr.getSuppCode()));
				juteentryhdrEntity.setBrokerName(broker.getBrokerName());
				juteentryhdrEntity.setBrokerAddress(broker.getAddress());
			}

			long actualWt = entryHdr.getGrossWeight() - entryHdr.getNetWeight();
			juteentryhdrEntity.setActualWeight(actualWt);

			juteEntryDao.save(juteentryhdrEntity);

			JuteGateEntryDtl entrydtlEntity = new JuteGateEntryDtl();
			List<JuteEntryDtlLineItemDTO> polineitems = entryHdr.getPolineitem();

			List<MukamJute> mukammap = new ArrayList<>();
			if (mukams != null) {
				mukammap = mukamjuteDao.findByMukamId(mukams.getId());
			}

			List<POLineItem> lineitem = new ArrayList<>();
			/*
			 * if(entryHdr.getPoNo() != null) { lineitem =
			 * polineitemDao.getpolineitemByPoNum(entryHdr.getPoNo()); }else { lineitem =
			 * null; }
			 */

			int size = polineitems.size();
			if (entryHdr.getPoNo() != null) {
				lineitem = polineitemDao.getpolineitemByPoNum(entryHdr.getPoNo());
			} else {

			}

			// loop for the Jute Gate Entry Dtl table
			for (int j = 0; j < size; j++) {
				List<POLineItem> poline = polineDao.getpolineitemByPoNum(entryHdr.getPoNo());
				/*
				 * JuteQualityPriceMaster price =
				 * pricemasterDao.getJuteQualityPriceMasterByJuteQuality(
				 * polineitems.get(j).getAdvisedQuality());
				 */
				System.out.println(" advisedjutetype " + polineitems.get(j).getAdvisedJuteType());
				System.out.println(" polineitem " + polineitems.get(j));
				System.out.println(" actualjutetype " + polineitems.get(j).getActualJuteType());

				/*ItemMaster items = itemDao.getItemMasterByItemdsc(polineitems.get(j).getAdvisedJuteType());
				JuteQualityPriceMaster price = pricemasterDao.getJuteQualityPriceMasterByJuteQualityAndItemCode(
						polineitems.get(j).getAdvisedQuality(), items.getId());

				String itemcode = price.getItemCode();*/

				/*
				 * String itemCode = null; List<JuteQualityPriceMaster> pricemaster =
				 * pricemasterDao.getPriceMasterByItemCode(itemcode); if(!pricemaster.isEmpty())
				 * { if(poline.size()>j) {
				 * if(pricemaster.get(j).getJuteQuality().equals(polineitems.get(j).
				 * getActualQuality())) { itemCode = itemcode; } } }
				 */

				ItemMaster item = new ItemMaster();
				if (polineitems.get(j).getAdvisedJuteType() != null) {
					item = itemDao.getItemMasterByItemdsc(polineitems.get(j).getAdvisedJuteType());
				}
				entrydtlEntity.setRecId(juteentrydtlDao.getSeq());
				
				entrydtlEntity.setActualJuteTyp(polineitems.get(j).getActualJuteType());
				entrydtlEntity.setAdvisedJuteTyp(polineitems.get(j).getAdvisedJuteType());
				
				entrydtlEntity.setActualQuality(polineitems.get(j).getActualQuality());
				entrydtlEntity.setAdvisedQuality(polineitems.get(j).getAdvisedQuality());

				entrydtlEntity.setActualQuantity(polineitems.get(j).getActualQuantity());
				entrydtlEntity.setAdvisedQuantity(String.valueOf(polineitems.get(j).getAdvisedQuantity()));

				entrydtlEntity.setHdrId(juteentryhdrEntity.getId());

				/*
				 * if(entryHdr.getPolineitem().get(i).getIsPOAmment()==true) {
				 * entrydtlEntity.setIsPoAmment("1"); }else { entrydtlEntity.setIsPoAmment("0");
				 * }
				 */

				entrydtlEntity.setItemCode(item.getId());
				entrydtlEntity.setOpenClose("2");
				// entrydtlEntity.setPoLineItemNum(polineitems.get(i).getPolineitemnum());
				entrydtlEntity.setRemarks(polineitems.get(j).getRemarks());
				// entrydtlEntity.setUom(polineitems.get(i).getUom());
				entrydtlEntity.setReceivedIn(polineitems.get(j).getReceivedIn());

				/*
				 * long lineitemsize = lineitem.size(); if(lineitem!=null ) { if(lineitemsize <=
				 * size) { entrydtlEntity.setPoLineItemNum(lineitem.get(j).getId()); }else {
				 * entrydtlEntity.setPoLineItemNum(0); } }else {
				 * entrydtlEntity.setPoLineItemNum(0); }
				 */

				if (lineitem.size() > j) {
					/*
					 * List<POLineItem> lineitem1 =
					 * polineitemDao.getpolineitemByPoNum(entryHdr.getPoNo()); for(int k = 0 ; k <
					 * lineitem1.size() ; k++) {
					 * entrydtlEntity.setPoLineItemNum(lineitem1.get(k).getId()); }
					 */
					entrydtlEntity.setPoLineItemNum(lineitem.get(j).getId());
				} else {
					entrydtlEntity.setPoLineItemNum(0);
				}

				// entrydtlEntity.setPoLineItemNum(polineitems.get(i).getPolineitemnum());
				entrydtlEntity.setQuantity(polineitems.get(j).getActualQuantity());
				JuteEntryHeader jutehdr = juteEntryDao.getJuteEntryHeaderById(juteentryhdrEntity.getId());

				jutehdr.setVehicleType(polineitems.get(j).getVehicleType());

				// jutehdr.setVehicleType(vehicle.getId());

				juteEntryDao.save(jutehdr);

				juteentrydtlDao.save(entrydtlEntity);

				// calling procedure for extra polineitem
				/*
				 * System.out.println("inside with po");
				 * 
				 * System.out.println("inside with po"); if(poline.size()<polineitems.size()) {
				 * System.out.println("inside with po");
				 * juteEntryDao.storedProcForExtraPOLineItem(juteentryhdrEntity.getId(),
				 * juteentryhdrEntity.getPoNo()); System.out.println("inside with po"); }
				 * System.out.println("inside with po");
				 */
			}
			// }
		}
		if (Strings.isNullOrEmpty(entryHdr.getPoNo())) {
			System.out.println("inside without PO");
			JuteEntryHeader juteentryhdrEntity = new JuteEntryHeader();
			List<String> pos = new ArrayList<>();
			long chalanNo = 0;
			Mukam mukams = new Mukam();
			if (entryHdr.getMukam() != null)
				mukams = mukamDao.findByMukamName(entryHdr.getMukam());
			juteentryhdrEntity.setChalanDate(Date.valueOf(entryHdr.getChalanDate().toLocalDate()));
			juteentryhdrEntity.setChalanNo(entryHdr.getChalanNo());
			juteentryhdrEntity.setDriverName(entryHdr.getDriverName());
			juteentryhdrEntity.setFinYear(entryHdr.getFinYear());
			juteentryhdrEntity.setGrossWeight(entryHdr.getGrossWeight());
			juteentryhdrEntity.setId(entryHdr.getId());
			// juteentryhdrEntity.setInDate(Date.valueOf(LocalDate.now()));
			juteentryhdrEntity.setInDate(Date.valueOf(entryHdr.getInDate().toLocalDate()));
			// juteentryhdrEntity.setInTime(Timestamp.valueOf(LocalDateTime.now()));
			// juteentryhdrEntity.setInTime(new Timestamp(System.currentTimeMillis()));
			/*
			 * String time = entryHdr.getInTime(); String t =
			 * String.valueOf(time.substring(5, 6)); System.out.println("time "+t); long hh
			 * = 0; if(!t.equalsIgnoreCase("A")) { hh = Long.valueOf(time.substring(0,
			 * 1))+12; }else { hh = Long.valueOf(time.substring(0, 1))+0; } long mm =
			 * Long.valueOf(time.substring(2,4)); System.out.println("mm "+mm); String
			 * intime = String.valueOf(hh)+":"+String.valueOf(mm)+":"+"10";
			 * System.out.println(intime); System.out.println("time1 "+hh);
			 */
			juteentryhdrEntity.setInTime(
					Timestamp.valueOf(String.valueOf(entryHdr.getInDate()) + " " + entryHdr.getInTime() + ".3654"));

			juteentryhdrEntity.setMrNo(juteentrydtlDao.getMRSeq()); // calling sequence to generate mr no

			if (mukams != null) {
				juteentryhdrEntity.setMukam(String.valueOf(mukams.getId()));
			}

			juteentryhdrEntity.setNetWeight(entryHdr.getTareWeight());
			juteentryhdrEntity.setOpenClose("2");
			juteentryhdrEntity.setOutDate(null);
			juteentryhdrEntity.setOutTime(null);

			/*
			 * List<String> ponos = new ArrayList<>(); if(entryHdr.getPoNo()!=null) {
			 * List<String> poids = entryHdr.getPoNo(); for(int k = 0 ; k < poids.size() ;
			 * k++) { String pono = poids.get(k); juteentryhdrEntity.setPoNo(pono);
			 * ponos.add(pono); } juteentryhdrEntity.setPoNo(ponos.get(i)); }
			 */
			// juteentryhdrEntity.setPoNo(entryHdr.getPoNo());

			juteentryhdrEntity.setTransporter(entryHdr.getTransporter());
			juteentryhdrEntity.setUpdateBy(entryHdr.getUpdateBy());
			juteentryhdrEntity.setUpdateDateTime(null);
			juteentryhdrEntity.setVehicleNo(entryHdr.getVehicleNo());
			juteentryhdrEntity.setChallanWeight(entryHdr.getChallanWeight());
			// juteentryhdrEntity.setSuppCode(entryHdr.getSuppCode());

			/*
			 * SupplierMaster supplierByCode =
			 * supplierDao.findSupplierMasterById(entryHdr.getSuppCode()); SupplierMaster
			 * supplier = supplierDao.findBySuppName(entryHdr.getBrokerName());
			 * 
			 * juteentryhdrEntity.setSupplierName(supplier.getsuppName());
			 * juteentryhdrEntity.setBrokerName(entryHdr.getBrokerName()); //
			 * juteentryhdrEntity.setBrokerName(supplier.getsuppName()); //
			 * juteentryhdrEntity.setSupplierName(entryHdr.getSupplierName()); //
			 * juteentryhdrEntity.setBrokerAddress(entryHdr.getBrokerAddress());
			 * juteentryhdrEntity.setBrokerAddress(supplier.getaddress1());
			 * juteentryhdrEntity.setSuppCode(supplier.getId());
			 */

			if (entryHdr.getSuppCode().startsWith("J")) {
				SupplierMaster supplierByCode = supplierDao.findSupplierMasterById(entryHdr.getSuppCode());
				juteentryhdrEntity.setSupplierName(supplierByCode.getsuppName());
				juteentryhdrEntity.setSuppCode(supplierByCode.getId());
			} else {
				Broker broker = brokerDao.getBrokerById(Long.parseLong(entryHdr.getSuppCode()));
				juteentryhdrEntity.setBrokerName(broker.getBrokerName());
				juteentryhdrEntity.setBrokerAddress(broker.getAddress());
			}

			long actualWt = entryHdr.getGrossWeight() - entryHdr.getNetWeight();
			juteentryhdrEntity.setActualWeight(actualWt);

			juteEntryDao.save(juteentryhdrEntity);

			JuteGateEntryDtl entrydtlEntity = new JuteGateEntryDtl();
			List<JuteEntryDtlLineItemDTO> polineitems = entryHdr.getPolineitem();

			List<MukamJute> mukammap = new ArrayList<>();
			if (mukams != null) {
				mukammap = mukamjuteDao.findByMukamId(mukams.getId());
			}

			List<POLineItem> lineitem = new ArrayList<>();
			/*
			 * if(entryHdr.getPoNo() != null) { lineitem =
			 * polineitemDao.getpolineitemByPoNum(entryHdr.getPoNo()); }else { lineitem =
			 * null; }
			 */

			int size = polineitems.size();

			// loop for the Jute Gate Entry Dtl table
			for (int j = 0; j < size; j++) {
				List<JuteEntryDtlLineItemDTO> polineitem = polineitems;
				/*
				 * JuteQualityPriceMaster price =
				 * pricemasterDao.getJuteQualityPriceMasterByJuteQuality(
				 * polineitem.get(j).getAdvisedQuality()).get(0);
				 */
				ItemMaster item = itemDao.getItemMasterByItemdsc(polineitem.get(j).getAdvisedJuteType());
				JuteQualityPriceMaster price = pricemasterDao.getJuteQualityPriceMasterByJuteQualityAndItemCode(
						polineitem.get(j).getAdvisedQuality(), item.getId());
				String itemcode = price.getItemCode();

				String itemCode = null;
				List<JuteQualityPriceMaster> pricemaster = pricemasterDao.getPriceMasterByItemCode(itemcode);
				if (!pricemaster.isEmpty()) {
					if (pricemaster.get(j).getJuteQuality().equals(polineitem.get(j).getActualQuality())) {
						itemCode = itemcode;
					}
				}

				ItemMaster item1 = itemDao.getItemMasterByItemdsc(polineitem.get(j).getAdvisedJuteType());
				entrydtlEntity.setRecId(juteentrydtlDao.getSeq());
				entrydtlEntity.setActualJuteTyp(item1.getitemDsc());
				entrydtlEntity.setAdvisedJuteTyp(item.getitemDsc());
				entrydtlEntity.setActualQuality(polineitem.get(j).getAdvisedQuality());
				entrydtlEntity.setAdvisedQuality(polineitem.get(j).getAdvisedQuality());

				// entrydtlEntity.setActualQuantity(polineitem.get(j).getActualQuantity());
				
				Float qnt = 0.0f;
				if(polineitem.get(j).getReceivedIn().equalsIgnoreCase("BALE")) {
					qnt = (float) (1*(polineitem.get(j).getAdvisedQuantity())/(1.5));
				}else {
					qnt = (float) polineitem.get(j).getAdvisedQuantity();
				}
				entrydtlEntity.setActualQuantity(qnt);
//				entrydtlEntity.setAdvisedQuantity(String.valueOf(qnt));
				entrydtlEntity.setQuantity(qnt);
				/*entrydtlEntity.setActualQuantity(polineitem.get(j).getAdvisedQuantity());
				entrydtlEntity.setAdvisedQuantity(String.valueOf(polineitem.get(j).getQuantity()));*/
				
				entrydtlEntity.setAdvisedJuteTyp(item.getitemDsc());

				entrydtlEntity.setHdrId(juteentryhdrEntity.getId());

				/*
				 * if(entryHdr.getPolineitem().get(i).getIsPOAmment()==true) {
				 * entrydtlEntity.setIsPoAmment("1"); }else { entrydtlEntity.setIsPoAmment("0");
				 * }
				 */

				entrydtlEntity.setItemCode(item.getId());
				entrydtlEntity.setOpenClose("2");
				// entrydtlEntity.setPoLineItemNum(polineitems.get(i).getPolineitemnum());
				entrydtlEntity.setRemarks(polineitem.get(j).getRemarks());
				// entrydtlEntity.setUom(polineitems.get(i).getUom());
				entrydtlEntity.setReceivedIn(polineitem.get(j).getReceivedIn());

				/*
				 * if(entryHdr.getPoNo() != null) { lineitem =
				 * polineitemDao.getpolineitemByPoNum(entryHdr.getPoNo().get(i));
				 * if(!lineitem.isEmpty())
				 * entrydtlEntity.setPoLineItemNum(lineitem.get(j).getId()); }else {
				 * entrydtlEntity.setPoLineItemNum(0); }
				 */

				// entrydtlEntity.setPoLineItemNum(polineitems.get(i).getPolineitemnum());
				
				entrydtlEntity.setAdvisedQuantity(String.valueOf(polineitem.get(j).getAdvisedQuantity()));
				JuteEntryHeader jutehdr = juteEntryDao.getJuteEntryHeaderById(juteentryhdrEntity.getId());
				jutehdr.setVehicleType(polineitems.get(j).getVehicleType());
				/*
				 * long vehicletypeid =
				 * vehicleDao.getVehilceByVehicleType(polineitems.get(j).getVehicleType()).getId
				 * (); jutehdr.setVehicleType(vehicletypeid);
				 */
				juteEntryDao.save(jutehdr);
				juteentrydtlDao.save(entrydtlEntity);
				System.out.println("inside without po");
			}
			System.out.println("inside without po");
			// calling procedure for Without PO
			juteEntryDao.storedProcForWithoutPO(juteentryhdrEntity.getId());
			System.out.println("inside without po");

		}
		// System.out.println(lineitem);
		return new ResponseEntity<JuteEntryHeaderDTO>(entryHdr, HttpStatus.CREATED);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sls.security.services.JuteGateEntryHdrService#updateJuteGateEntryHeader(
	 * com.sls.security.dto.JuteEntryHeaderDTO)
	 * 
	 * Updating Jute Gate Entry Header as Well as Respective Jute Gate Entry Dtl
	 * Tables
	 * 
	 * @Param
	 * 
	 * JuteEntryHeaderDTO object
	 * 
	 */
	@Override
	public ResponseEntity<JuteEntryHeaderDTO> updateJuteGateEntryHeader(JuteEntryHeaderDTO entryHdr) {
		JuteEntryHeader juteentryhdrEntity = juteEntryDao.getJuteEntryHeaderById(entryHdr.getId());
		if (juteentryhdrEntity.getOutDate() == null) {
			Mukam mukams = mukamDao.findByMukamName(entryHdr.getMukam());

			juteentryhdrEntity.setChalanDate(Date.valueOf(entryHdr.getChalanDate().toLocalDate()));
			juteentryhdrEntity.setChalanNo(entryHdr.getChalanNo());
			juteentryhdrEntity.setId(entryHdr.getId());
			juteentryhdrEntity.setMrNo(entryHdr.getMrNo());
			juteentryhdrEntity.setMukam(String.valueOf(mukams.getId()));
			juteentryhdrEntity.setPoNo(entryHdr.getPoNo());
			juteentryhdrEntity.setVehicleNo(entryHdr.getVehicleNo());
			juteentryhdrEntity.setUpdateDateTime(Timestamp.valueOf(LocalDateTime.now()));
			juteentryhdrEntity.setOutDate(Date.valueOf(LocalDate.now()));
			juteentryhdrEntity.setOutTime(Timestamp.valueOf(LocalDateTime.now()));
			juteentryhdrEntity.setDriverName(entryHdr.getDriverName());
			juteentryhdrEntity.setFinYear(entryHdr.getFinYear());
			juteentryhdrEntity.setGrossWeight(entryHdr.getGrossWeight());
			juteentryhdrEntity.setTransporter(entryHdr.getTransporter());
			juteentryhdrEntity.setNetWeight(entryHdr.getTareWeight());
			juteentryhdrEntity.setChallanWeight(entryHdr.getChallanWeight());

			// long actualWt = entryHdr.getGrossWeight() - entryHdr.getNetWeight();
			juteentryhdrEntity.setActualWeight(entryHdr.getActualWeight());

			/*
			 * SupplierMaster supplier =
			 * supplierDao.findSupplierMasterById(entryHdr.getSuppCode()); SupplierMaster
			 * supplier = supplierDao.findBySuppName(entryHdr.getBrokerName()); //
			 * juteentryhdrEntity.setSupplierName(supplier.getsuppName());
			 * juteentryhdrEntity.setBrokerName(supplier.getsuppName()); //
			 * juteentryhdrEntity.setSupplierName(entryHdr.getSupplierName());
			 * juteentryhdrEntity.setBrokerAddress(supplier.getaddress1());
			 * juteentryhdrEntity.setSuppCode(supplier.getId());
			 * juteentryhdrEntity.setBrokerAddress(entryHdr.getBrokerAddress());
			 */

			if (entryHdr.getSuppCode() != null) {
				SupplierMaster supplier = supplierDao.findSupplierMasterById(entryHdr.getSuppCode());
				juteentryhdrEntity.setSupplierName(supplier.getsuppName());
				juteentryhdrEntity.setSuppCode(supplier.getId());
			} else if (entryHdr.getBrokerName() != null) {
				Broker broker = brokerDao.getBrokerByBrokerName(entryHdr.getBrokerName());
				juteentryhdrEntity.setBrokerName(broker.getBrokerName());
				juteentryhdrEntity.setBrokerAddress(broker.getAddress());
			}

			juteEntryDao.save(juteentryhdrEntity);

			JuteGateEntryDtl entrydtlEntityEmp = new JuteGateEntryDtl();
			List<JuteGateEntryDtl> juteentrydtls = new ArrayList<>();
			List<JuteEntryDtlLineItemDTO> polineitems = entryHdr.getPolineitem();

			List<MukamJute> mukammap = new ArrayList<>();
			if (entryHdr.getMukam() != null) {
				mukammap = mukamjuteDao.findByMukamId(mukams.getId());
			}

			List<POLineItem> lineitem = new ArrayList<>();
			if (entryHdr.getPoNo() != null) {
				lineitem = polineitemDao.getpolineitemByPoNum(entryHdr.getPoNo());
			}

			int size = polineitems.size();
			// int size1 = lineitem.size();
			List<JuteGateEntryDtl> entrydtls = juteentrydtlDao.getJuteGateEntryDtlByHdrId(juteentryhdrEntity.getId());

			for (int i = 0; i < size; i++) {
				/*
				 * JuteGateEntryDtl entrydtlEntity =
				 * juteentrydtlDao.getJuteGateEntryDtlByPolineitem(polineitems.get(i).
				 * getPolineitemnum());
				 */
				if (i < entrydtls.size()) {
					JuteGateEntryDtl entrydtlEntity = entrydtls.get(i);
					System.out.println("polineitemnum " + polineitems.get(i).getPolineitemnum());
					System.out.println((entrydtlEntity != null));
					if (entrydtlEntity != null) {
						String itemcode = polineitems.get(0).getItemCode();
						String itemCode = null;
						List<JuteQualityPriceMaster> pricemaster = pricemasterDao.getPriceMasterByItemCode(itemcode);
						if (!pricemaster.isEmpty()) {
							if (pricemaster.get(i).getJuteQuality().equals(polineitems.get(i).getActualQuality())) {
								itemCode = itemcode;
							}

						}

						ItemMaster item = new ItemMaster();
						if (itemcode != null) {
							item = itemDao.findItemMasterById(itemcode);
						}
						// entrydtlEntity.setRecId(entryHdr.getPolineitem().get(i).getDtlId());
						entrydtlEntity.setActualJuteTyp(item.getitemDsc());
						entrydtlEntity.setActualQuality(polineitems.get(i).getActualQuality());
						entrydtlEntity.setActualQuantity(polineitems.get(i).getActualQuantity());
						entrydtlEntity.setAdvisedJuteTyp(item.getitemDsc());
						entrydtlEntity.setAdvisedQuality(polineitems.get(i).getAdvisedQuality());
						entrydtlEntity.setAdvisedQuantity(String.valueOf(polineitems.get(i).getAdvisedQuantity()));
						entrydtlEntity.setHdrId(juteentryhdrEntity.getId());

						if (entryHdr.getPolineitem().get(i).getIsPOAmment() == true) {
							entrydtlEntity.setIsPoAmment("1");
						} else {
							entrydtlEntity.setIsPoAmment("0");
						}

						entrydtlEntity.setItemCode(item.getId());
						entrydtlEntity.setOpenClose("2");
						// entrydtlEntity.setPoLineItemNum(polineitems.get(i).getPolineitemnum());
						entrydtlEntity.setRemarks(polineitems.get(i).getRemarks());
						entrydtlEntity.setUom(polineitems.get(i).getUom());
						entrydtlEntity.setReceivedIn(polineitems.get(i).getReceivedIn());

						if (lineitem != null) {
							if (i < lineitem.size()) {
								entrydtlEntity.setPoLineItemNum(lineitem.get(i).getId());
							}
						} else {
							if (i < lineitem.size()) {
								entrydtlEntity.setPoLineItemNum(polineitems.get(i).getPolineitemnum());
							}
						}

						entrydtlEntity.setQuantity(polineitems.get(i).getActualQuantity());

						JuteGateEntryDtl dtltl = juteentrydtlDao.save(entrydtlEntity);
						System.out.println("inside if");
						System.out.println(dtltl);
					} else {
						String itemcode = polineitems.get(0).getItemCode();
						String itemCode = null;
						List<JuteQualityPriceMaster> pricemaster = pricemasterDao.getPriceMasterByItemCode(itemcode);
						if (!pricemaster.isEmpty()) {
							if (pricemaster.get(i).getJuteQuality().equals(polineitems.get(i).getActualQuality())) {
								itemCode = itemcode;
							}

						}

						ItemMaster item = new ItemMaster();
						if (itemcode != null) {
							item = itemDao.findItemMasterById(itemcode);
						}
						entrydtlEntityEmp.setRecId(juteentrydtlDao.getSeq());
						entrydtlEntityEmp.setActualJuteTyp(item.getitemDsc());
						entrydtlEntityEmp.setActualQuality(polineitems.get(i).getActualQuality());
						entrydtlEntityEmp.setActualQuantity(polineitems.get(i).getActualQuantity());
						entrydtlEntityEmp.setAdvisedJuteTyp(item.getitemDsc());
						entrydtlEntityEmp.setAdvisedQuality(polineitems.get(i).getAdvisedQuality());
						entrydtlEntityEmp.setAdvisedQuantity(String.valueOf(polineitems.get(i).getAdvisedQuantity()));
						entrydtlEntityEmp.setHdrId(juteentryhdrEntity.getId());

						if (entryHdr.getPolineitem().get(i).getIsPOAmment() == true) {
							entrydtlEntity.setIsPoAmment("1");
						} else {
							entrydtlEntity.setIsPoAmment("0");
						}

						entrydtlEntityEmp.setItemCode(item.getId());
						entrydtlEntityEmp.setOpenClose("2");
						// entrydtlEntityEmp.setPoLineItemNum(polineitems.get(i).getPolineitemnum());
						entrydtlEntityEmp.setRemarks(polineitems.get(i).getRemarks());
						entrydtlEntityEmp.setUom(polineitems.get(i).getUom());
						entrydtlEntityEmp.setReceivedIn(polineitems.get(i).getReceivedIn());

						if (lineitem != null) {
							entrydtlEntityEmp.setPoLineItemNum(lineitem.get(i).getId());
						} else {
							entrydtlEntityEmp.setPoLineItemNum(polineitems.get(i).getPolineitemnum());
						}

						entrydtlEntityEmp.setQuantity(polineitems.get(i).getActualQuantity());

						juteentrydtlDao.save(entrydtlEntityEmp);
						System.out.println("inside else");
						System.out.println(entrydtlEntity);
					}
				} else {
					String itemcode = polineitems.get(0).getItemCode();
					String itemCode = null;
					List<JuteQualityPriceMaster> pricemaster = pricemasterDao.getPriceMasterByItemCode(itemcode);
					if (!pricemaster.isEmpty()) {
						if (pricemaster.get(i).getJuteQuality().equals(polineitems.get(i).getActualQuality())) {
							itemCode = itemcode;
						}

					}

					ItemMaster item = new ItemMaster();
					if (itemcode != null) {
						item = itemDao.findItemMasterById(itemcode);
					}
					entrydtlEntityEmp.setRecId(juteentrydtlDao.getSeq());
					entrydtlEntityEmp.setActualJuteTyp(item.getitemDsc());
					entrydtlEntityEmp.setActualQuality(polineitems.get(i).getActualQuality());
					entrydtlEntityEmp.setActualQuantity(polineitems.get(i).getActualQuantity());
					entrydtlEntityEmp.setAdvisedJuteTyp(item.getitemDsc());
					entrydtlEntityEmp.setAdvisedQuality(polineitems.get(i).getAdvisedQuality());
					entrydtlEntityEmp.setAdvisedQuantity(String.valueOf(polineitems.get(i).getAdvisedQuantity()));
					entrydtlEntityEmp.setHdrId(juteentryhdrEntity.getId());

					if (entryHdr.getPolineitem().get(i).getIsPOAmment() == true) {
						entrydtlEntityEmp.setIsPoAmment("1");
					} else {
						entrydtlEntityEmp.setIsPoAmment("0");
					}

					entrydtlEntityEmp.setItemCode(item.getId());
					entrydtlEntityEmp.setOpenClose("2");
					// entrydtlEntityEmp.setPoLineItemNum(polineitems.get(i).getPolineitemnum());
					entrydtlEntityEmp.setRemarks(polineitems.get(i).getRemarks());
					entrydtlEntityEmp.setUom(polineitems.get(i).getUom());
					entrydtlEntityEmp.setReceivedIn(polineitems.get(i).getReceivedIn());

					if (lineitem != null) {
						if (i < lineitem.size()) {
							entrydtlEntityEmp.setPoLineItemNum(lineitem.get(i).getId());
						}
					} else {
						if (i < lineitem.size()) {
							entrydtlEntityEmp.setPoLineItemNum(polineitems.get(i).getPolineitemnum());
						}
					}

					entrydtlEntityEmp.setQuantity(polineitems.get(i).getActualQuantity());

					juteentrydtlDao.save(entrydtlEntityEmp);
					System.out.println("inside else");

				}

			}

			return new ResponseEntity<JuteEntryHeaderDTO>(entryHdr, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<JuteEntryHeaderDTO>(entryHdr, HttpStatus.ALREADY_REPORTED);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sls.security.services.JuteGateEntryHdrService#deleteJuteEntryHeader(long)
	 * 
	 * deleting Jute Gate Entry Header as well as Jute Gate Entry Dtl
	 * 
	 * @Param
	 * 
	 * long id (hdrId)
	 * 
	 */
	@Override
	public DeleteDTO deleteJuteEntryHeader(long id) {
		juteEntryDao.delete(id);

		DeleteDTO deleteDTO = new DeleteDTO();
		deleteDTO.setstatus(1);
		deleteDTO.setstatusCode(2000);
		deleteDTO.setmsg("Deleted successfully.");

		return deleteDTO;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sls.security.services.JuteGateEntryHdrService#getJuteGateEntryHeaderById(
	 * long)
	 * 
	 * Fetching Jute Gate Entry Header as well as Respective Jute Gate Entry Dtl by
	 * HdrId
	 * 
	 * @Param
	 * 
	 * long hdrId
	 * 
	 */
	@Override
	public JuteEntryHeaderDTO getJuteGateEntryHeaderById(long id) {
		JuteEntryHeader juteentryhdrEntity = new JuteEntryHeader();
		try {
			juteentryhdrEntity = juteEntryDao.getJuteEntryHeaderById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return preparingJuteGateEntryDTOService.prepareJuteGateEntryDTO(juteentryhdrEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sls.security.services.JuteGateEntryHdrService#
	 * getAllJuteGateEntryHdrByDate(java.sql.Date, java.sql.Date)
	 * 
	 * Fetching all the Jute Gate Entry Header and respective Jute Gate Entry Dtl by
	 * inDate
	 * 
	 * @Params
	 * 
	 * Date fromDate
	 * 
	 * Date toDAte
	 * 
	 */
	@Override
	public List<JuteEntryHeaderDTO> getAllJuteGateEntryHdrByDate(Date startDate, Date toDate) {
		List<JuteEntryHeaderDTO> entryhdr = new ArrayList<>();

		try {
			List<JuteEntryHeader> entryhdrEntity = juteEntryDao.getAllJuteEntryHeaderByDate(startDate, toDate);
			entryhdrEntity.forEach(hdr -> {
				entryhdr.add(preparingJuteGateEntryDTOService.prepareJuteGateEntryDTO(hdr));
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entryhdr.stream().sorted(Comparator.comparing(JuteEntryHeaderDTO::getInTime).reversed())
				.collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sls.security.services.JuteGateEntryHdrService#
	 * getAllJuteEntryHeaderBySuppCodeAndChallanNo(java.lang.String, long)
	 * 
	 * fetching All Jute Gate Entry Header and Respective Jute Gate Entry Dtl By
	 * suppCode and challanNO
	 * 
	 */
	@Override
	public JuteEntryHeaderDTO getAllJuteEntryHeaderBySuppNameAndChallanNo(String suppName, long challanNo) {
		JuteEntryHeaderDTO juteentryheader = new JuteEntryHeaderDTO();
		SupplierMaster supplier = supplierDao.findBySuppName(suppName);

		juteentryheader = preparingJuteGateEntryDTOService.prepareJuteGateEntryDTO(
				juteEntryDao.getJuteEntryHeaderBySuppNameAndChallanNo(supplier.getsuppName(), challanNo));

		return juteentryheader;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sls.security.services.JuteGateEntryHdrService#
	 * updateJuteGateEntryHeaderOut(com.sls.security.dto.JuteEntryHeaderDTO)
	 * 
	 * For Out API which will update the outDate and outTime in the JuteGateEntryHdr
	 * table
	 * 
	 * @Param
	 * 
	 * JuteEntryHeaderDTO object
	 * 
	 */
	@Override
	public ResponseEntity<JuteEntryHeaderDTO> updateJuteGateEntryHeaderOut(JuteEntryHeaderDTO entryHdr) {
		JuteEntryHeader juteentryhdrEntity = juteEntryDao.getJuteEntryHeaderById(entryHdr.getId());
		if (juteentryhdrEntity.getOutDate() == null) {

			juteentryhdrEntity.setOutDate(Date.valueOf(LocalDate.now()));
			juteentryhdrEntity.setOutTime(Timestamp.valueOf(LocalDateTime.now()));

			juteEntryDao.save(juteentryhdrEntity);

			return new ResponseEntity<JuteEntryHeaderDTO>(entryHdr, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<JuteEntryHeaderDTO>(entryHdr, HttpStatus.ALREADY_REPORTED);
		}
	}

	@Override
	public long getChallanWeightByChalanNoAndSupp(String suppCode, long chalanNo) {
		long chalanWt = 0;

		JuteEntryHeader entryHdr = juteEntryDao.getJuteEntryHeaderBySuppNameAndChallanNo(suppCode, chalanNo);
		chalanWt = entryHdr.getChallanWeight();
		System.out.println(entryHdr);
		System.out.println(chalanWt);
		return chalanWt;
	}

	@Override
	public long getChallanWeightByChalanNoAndSuppCode(String suppCode, long chalanNo) {
		long chalanWt = 0;

		JuteEntryHeader entryHdr = juteEntryDao.getJuteEntryHeaderBySuppCodeAndChallanNo(suppCode, chalanNo);
		chalanWt = entryHdr.getChallanWeight();
		System.out.println(entryHdr);
		System.out.println(chalanWt);
		return chalanWt;
	}

	@Override
	public JuteEntryHeaderdDTO getJuteGateEntryHeaderById2(long id) {
		JuteEntryHeader juteentryhdrEntity = new JuteEntryHeader();
		try {
			juteentryhdrEntity = juteEntryDao.getJuteEntryHeaderById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return preparingJuteGateEntryDTOService.prepareJuteGateEntrydDTO(juteentryhdrEntity);
	}

}
