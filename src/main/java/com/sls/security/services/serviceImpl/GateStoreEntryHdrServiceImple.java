package com.sls.security.services.serviceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sls.security.component.DepartmentDao;
import com.sls.security.component.GateStoreEntryDtlComponent;
import com.sls.security.component.GateStoreEntryHdrComponent;
import com.sls.security.component.IndentLineItemDao;
import com.sls.security.component.ItemGroupMasterDao;
import com.sls.security.component.ItemMasterDao;
import com.sls.security.component.POLineItemComponent;
import com.sls.security.component.SupplierMasterDao;
import com.sls.security.dto.DeleteDTO;
import com.sls.security.dto.GateStoreEntryRegHdrDTO;
import com.sls.security.dto.POHeaderDTO;
import com.sls.security.dto.POLineItemDtlDTO;
import com.sls.security.dto.POLineItemDtlsDTO;
import com.sls.security.dto.POSupplierDTO;
import com.sls.security.entity.Department;
import com.sls.security.entity.GateStoreEntryRegDtl;
import com.sls.security.entity.GateStoreEntryRegHdr;
import com.sls.security.entity.ItemMaster;
import com.sls.security.entity.POLineItem;
import com.sls.security.services.GateStoreEntryHdrService;

/*
 * Service Class for GateStoreEntryHdr
 */
@Service
public class GateStoreEntryHdrServiceImple implements GateStoreEntryHdrService {

	@Autowired
	GateStoreEntryHdrComponent storeentryhdrDao;

	@Autowired
	GateStoreEntryRegDtlServiceImpl storeentrydtlService;

	@Autowired
	GateStoreEntryDtlComponent gatestoredtlDao;

	@Autowired
	ItemMasterDao itemDao;

	@Autowired
	SupplierMasterDao supplierDao;

	@Autowired
	POHeaderServiceImpl pohdrService;

	@Autowired
	IndentLineItemDao indentlineDao;

	@Autowired
	ItemGroupMasterDao itemgroupDao;

	@Autowired
	DepartmentDao deptDao;

	@Autowired
	POLineItemComponent polineDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sls.security.services.GateStoreEntryHdrService#
	 * getAllGateStoreEntryHdrByDate(java.sql.Date, java.sql.Date)
	 * 
	 * Fetching All GateStoreEntryHdr By inDate
	 * 
	 * @Params
	 * 
	 * Date fromDate, Date toDate
	 * 
	 */
	@Override
	public List<GateStoreEntryRegHdrDTO> getAllGateStoreEntryHdrByDate(Date fromDate, Date toDate) {
		List<GateStoreEntryRegHdrDTO> storeentryhdr = new ArrayList<>();

		try {
			List<GateStoreEntryRegHdr> entryhdr = storeentryhdrDao.getGateStoreEntryRegHdrByDate(fromDate, toDate);
			entryhdr.forEach(hdr -> {
				storeentryhdr.add(prepareStoreEntryHdrDTO(hdr));
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return storeentryhdr.stream().sorted(Comparator.comparing(GateStoreEntryRegHdrDTO::getInTime).reversed())
				.collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sls.security.services.GateStoreEntryHdrService#getAllGateStoreEntryRegHdr
	 * ()
	 * 
	 * fetching All GateStoreEntryHdr
	 */
	@Override
	public List<GateStoreEntryRegHdrDTO> getAllGateStoreEntryRegHdr() {
		List<GateStoreEntryRegHdrDTO> storeentryhdr = new ArrayList<>();
		try {
			List<GateStoreEntryRegHdr> storeentruhdrEntity = storeentryhdrDao.getAllGateStoreEntryRegHdr();
			storeentruhdrEntity.forEach(storehdr -> {
				storeentryhdr.add(prepareStoreEntryHdrDTO(storehdr));
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return storeentryhdr.stream().sorted(Comparator.comparing(GateStoreEntryRegHdrDTO::getInTime).reversed())
				.collect(Collectors.toList());
	}

	/*
	 * Preparing DTO for GateStoreEntryRegHdr Entity
	 */
	private GateStoreEntryRegHdrDTO prepareStoreEntryHdrDTO(GateStoreEntryRegHdr storehdr) {
		GateStoreEntryRegHdrDTO storeentryhdrDTO = new GateStoreEntryRegHdrDTO();
		List<GateStoreEntryRegHdr> entryhdrs = new ArrayList<>();
		/*
		 * If poNo is present then search for supplier and broker by poNo
		 */
		POSupplierDTO posupp = new POSupplierDTO();
		if (storehdr.getPoNo() != null) {
			posupp = pohdrService.getPoSupplier(storehdr.getPoNo());
		}

		List<GateStoreEntryRegDtl> entrydtl = new ArrayList<>();

		List<POLineItemDtlDTO> polineitems = new ArrayList<>();
		// POLineItemDtlDTO polineitem = new POLineItemDtlDTO();

		if (storehdr != null) {
			entrydtl = gatestoredtlDao.getEntryDtlByHdrId(storehdr.getHdrId());

			storeentryhdrDTO.setChallanDate(storehdr.getChallanDate());
			storeentryhdrDTO.setChallanNo(storehdr.getChallanNo());
			storeentryhdrDTO.setDriverName(storehdr.getDriverName());
			storeentryhdrDTO.setId(storehdr.getHdrId());
			/*
			 * storeentryhdrDTO.setInDate(storehdr.getInDate());
			 * storeentryhdrDTO.setInTime(storehdr.getInTime());
			 */
			storeentryhdrDTO.setInDate(storehdr.getInDate());
			storeentryhdrDTO.setInTime(String.valueOf(storehdr.getInTime()));
			storeentryhdrDTO.setOpenClose(storehdr.getOpenClose());
			storeentryhdrDTO.setOutDate(storehdr.getOutDate());
			String outtime = null;
			if(String.valueOf(storehdr.getOutTime()).equalsIgnoreCase("null")) {
				outtime = null;
			}else {
				outtime = String.valueOf(storehdr.getOutTime());
			}
			storeentryhdrDTO.setOutTime(outtime);

			entryhdrs = storeentryhdrDao.getGateStoreEntryRegHdrsByChallanNo(storehdr.getChallanNo());
			for (int m = 0; m < entryhdrs.size(); m++) {
				List<String> ponos = new ArrayList<>();
				for (int i = 0; i < entrydtl.size(); i++) {
					GateStoreEntryRegHdr entryhdr = storeentryhdrDao
							.getGateStoreEntryRegHdrById(entrydtl.get(i).getHdrId());
					String pono = entryhdr.getPoNo();
					// pono.setPoNo(entryhdr.getPoNo());
					ponos.add(pono);
				}
				storeentryhdrDTO.setPoNo(ponos.stream().distinct().collect(Collectors.toList()));
			}

			storeentryhdrDTO.setRemarks(storehdr.getRemarks());
			storeentryhdrDTO.setSuppCode(storehdr.getSuppCode());
			storeentryhdrDTO.setSupplierName(storehdr.getSupplierName());
			storeentryhdrDTO.setUpdateBy(storehdr.getUpdateBy());
			storeentryhdrDTO.setVehicleNo(storehdr.getVehicleNo());
			// storeentryhdrDTO.setSuppAddress(posupp.getAddress());
			// storeentryhdrDTO.setPolineitems(populateEntryDtlDTO(entrydtl));
			/*
			 * List<POLineItemDtlDTO> po = populateEntryDtlDTO(entrydtl); po.forEach(p->{
			 * System.out.println(p.getHdrIdDtl()); System.out.println(p.getItemId());
			 * System.out.println(p.getItemDesc()); });
			 */

			List<POLineItemDtlsDTO> lines = new ArrayList<>();
			for (int i = 0; i < entrydtl.size(); i++) {
				ItemMaster item = itemDao.getItemMasterByItemdsc(entrydtl.get(i).getItemDesc());
				Department dept = deptDao.findById(Long.parseLong(entrydtl.get(i).getDept()));
				POLineItemDtlDTO polineitem = new POLineItemDtlDTO();
				// System.out.println(item);
				polineitem.setDtlId(entrydtl.get(i).getDtlId());
				polineitem.setDept(dept.getdepartmentName());
				polineitem.setHdrIdDtl(entrydtl.get(i).getHdrId());
				polineitem.setItemId(item.getId());
				polineitem.setItemDesc(item.getitemDsc());
				/*
				 * polineitem.setItemId(entrydtl.get(i).getItemCode());
				 * polineitem.setItemDesc(entrydtl.get(i).getItemDesc());
				 */
				polineitem.setReqQuantity(entrydtl.get(i).getReqQuantity());
				polineitem.setUnitId(entrydtl.get(i).getUom());
				polineitem.setQuantity(entrydtl.get(i).getQuantity());
				polineitem.setPoId(storehdr.getPoNo());

				polineitems.add(polineitem);
			}
			POLineItemDtlsDTO line = new POLineItemDtlsDTO();
			line.setPolineitems(polineitems);
			lines.add(line);
			storeentryhdrDTO.setPolineitems(lines.stream().distinct().collect(Collectors.toList()));
			// List<POLineItemDtlDTO> b = storeentryhdrDTO.getPolineitems();
			/*
			 * b.forEach(a->{ System.out.println(a.getItemDesc()); });
			 */
		}
		System.out.println(entryhdrs);
		return storeentryhdrDTO;
	}

	/*
	 * Preparing DTO for GateStoreEntryRegDtl Entity
	 */
	private List<POLineItemDtlDTO> populateEntryDtlDTO(List<GateStoreEntryRegDtl> entrydtl) {
		List<POLineItemDtlDTO> polineitems = new ArrayList<>();
		POLineItemDtlDTO polineitem = new POLineItemDtlDTO();

		for (int i = 0; i < entrydtl.size(); i++) {
			ItemMaster item = itemDao.getItemMasterByItemdsc(entrydtl.get(i).getItemDesc());
			// System.out.println(entrydtl.get(i).getItemCode());
			polineitem.setDept(entrydtl.get(i).getDept());
			polineitem.setHdrIdDtl(entrydtl.get(i).getHdrId());
			polineitem.setItemId(entrydtl.get(i).getItemCode());
			// polineitem.setItemId(item.getId());
			// polineitem.setItemDesc(item.getitemDsc());
			polineitem.setItemDesc(entrydtl.get(i).getItemDesc());
			polineitem.setReqQuantity(entrydtl.get(i).getReqQuantity());
			polineitem.setUnitId(entrydtl.get(i).getUom());
			polineitem.setQuantity(entrydtl.get(i).getQuantity());
			polineitems.add(polineitem);
		}

		return polineitems;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sls.security.services.GateStoreEntryHdrService#
	 * getGateStoreEntryRegHdrById(long)
	 * 
	 * Fetching GateStoreEntryRegHdr by hdrId
	 * 
	 * @Param
	 * 
	 * Long id
	 * 
	 */
	@Override
	public GateStoreEntryRegHdrDTO getGateStoreEntryRegHdrById(long id) {
		return prepareStoreEntryHdrDTO(storeentryhdrDao.getGateStoreEntryRegHdrById(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sls.security.services.GateStoreEntryHdrService#saveGateStoreEntryRegHdr(
	 * com.sls.security.dto.GateStoreEntryRegHdrDTO)
	 * 
	 * Save Service For GateStoreEntryRegHdr Entity
	 * 
	 * @Param
	 * 
	 * GateStoreEntryRegHdrDTO
	 * 
	 */
	@Override
	public ResponseEntity<GateStoreEntryRegHdrDTO> saveGateStoreEntryRegHdr(GateStoreEntryRegHdrDTO storehdr) {

		List<String> pos = new ArrayList<>();
		// if(!storehdr.getPoNo().isEmpty() || storehdr.getPoNo()!=null) {
		// if(!storehdr.getPoNo().isEmpty() || storehdr.getPoNo()!=null) {
		if (storehdr.getPoNo() != null && !storehdr.getPoNo().isEmpty() && storehdr.getPoNo().size() > 0) {
			// if(storehdr.getPoNo().size()>0 ) {

			System.out.println("Inside with PO");
			long chalanNo = 0;
			for (int x = 0; x < storehdr.getPoNo().size(); x++) {
				// GateStoreEntryRegHdr storeentity =
				// storeentryhdrDao.getGateStoreEntryRegHdrByPoNo(storehdr.getPoNo());
				GateStoreEntryRegHdr storeentryhdrEntity = new GateStoreEntryRegHdr();

				chalanNo = storehdr.getChallanNo();
				storeentryhdrEntity.setChallanDate(Date.valueOf(storehdr.getChallanDate().toLocalDate()));
				storeentryhdrEntity.setChallanNo(storehdr.getChallanNo());
				storeentryhdrEntity.setDriverName(storehdr.getDriverName());
				// storeentryhdrEntity.setHdrId(storehdr.getHdrId());

				// storeentryhdrEntity.setInDate(Date.valueOf(LocalDate.now()));
				storeentryhdrEntity.setInDate(Date.valueOf(storehdr.getInDate().toLocalDate()));
				// storeentryhdrEntity.setInTime(Timestamp.valueOf(LocalDateTime.now()));
				storeentryhdrEntity.setInTime(
						Timestamp.valueOf(String.valueOf(storehdr.getInDate() + " " + storehdr.getInTime() + ".3654")));

				storeentryhdrEntity.setOpenClose("2");
				storeentryhdrEntity.setOutDate(null);
				storeentryhdrEntity.setOutTime(null);

				/*
				 * List<PONoDTO> ponos = new ArrayList<>(); List<PONoDTO> poid =
				 * storehdr.getPoNo(); for(int i = 0 ; i < poid.size() ; i++) { PONoDTO pono
				 * =new PONoDTO(); pono.setPoNo(poid.get(i).getPoNo()); //
				 * storeentryhdrEntity.setPoNo(pono); ponos.add(pono); }
				 */

				List<String> ponos = new ArrayList<>();
				if (storehdr.getPoNo() != null) {
					List<String> poid = storehdr.getPoNo();
					for (int i = 0; i < poid.size(); i++) {
						String pono = poid.get(i);
						storeentryhdrEntity.setPoNo(pono);
						ponos.add(pono);
					}
					storeentryhdrEntity.setPoNo(ponos.get(x));
				}

				storeentryhdrEntity.setRemarks(storehdr.getRemarks());
				storeentryhdrEntity.setSuppCode(storehdr.getSuppCode());
				storeentryhdrEntity.setSupplierName(storehdr.getSupplierName());
				storeentryhdrEntity.setUpdateBy(storehdr.getUpdateBy());
				storeentryhdrEntity.setVehicleNo(storehdr.getVehicleNo());

				storeentryhdrDao.saveGateStoreEntryRegHdr(storeentryhdrEntity);

				// GateStoreEntryRegHdr savehdr =
				// storeentryhdrDao.saveGateStoreEntryRegHdr(storeentryhdrEntity);
				// System.out.println(polineitems.size());
				// System.out.println(polineitems);
				// System.out.println(storehdr.getPoNo());
				// gatestoredtlDao.saveGateStoreEntryRegDtl(polineitemsEmp);
			}

			List<POLineItemDtlsDTO> lineitemslist = storehdr.getPolineitems();
			List<POLineItemDtlDTO> polineitems = new ArrayList<>();
			if (!lineitemslist.isEmpty()) {
				for (int n = 0; n < lineitemslist.size(); n++) {
					// GateStoreEntryRegDtl storeentrydtl = new GateStoreEntryRegDtl();
					polineitems = storehdr.getPolineitems().get(n).getPolineitems();
					// System.out.println(polineitems.size());
					List<GateStoreEntryRegDtl> polineitemsEmp = new ArrayList<>();

					if (polineitems != null) {
						for (int j = 0; j < polineitems.size(); j++) {
							GateStoreEntryRegDtl storeentrydtl = new GateStoreEntryRegDtl();
							// Department dept = deptDao.findByDeptName(polineitems.get(j).getDept());
							GateStoreEntryRegHdr entryhdr = new GateStoreEntryRegHdr();

							if (polineitems.get(j).getPoId() != null) {
								entryhdr = storeentryhdrDao.getGateStoreEntryHdrByPoNoAndChallanNoAndSuppCode(
										polineitems.get(j).getPoId(), chalanNo, storehdr.getSuppCode());
								storeentrydtl.setHdrId(entryhdr.getHdrId());
							}
							if (polineitems.get(j).getPoId() == null) {
								entryhdr = storeentryhdrDao.getGateStoreEntryHdrByPoNoAndChallanNoAndSuppCode(
										polineitems.get(0).getPoId(), chalanNo, storehdr.getSuppCode());
								storeentrydtl.setHdrId(entryhdr.getHdrId());
							}

							System.out.println(polineitems.get(j));

							storeentrydtl.setDtlId(gatestoredtlDao.getSeq());
							Department dept = deptDao.findByDeptName(polineitems.get(j).getDept());
							storeentrydtl.setDept(String.valueOf(dept.getId()));

							if (polineitems.get(j).getItemDesc() != null) {
								ItemMaster item = itemDao.getItemMasterByItemdsc(polineitems.get(j).getItemDesc());
								storeentrydtl.setItemCode(item.getId());
								storeentrydtl.setItemDesc(item.getitemDsc());
								storeentrydtl.setItem(item.getItemGroupMaster().getId());
							}

							/*
							 * if(polineitem.getItemId()!=null) { ItemMaster items =
							 * itemDao.findItemMasterById(polineitem.getItemId());
							 * storeentrydtl.setItemDesc(items.getitemDsc()); }else {
							 * storeentrydtl.setItemDesc(null); }
							 */

							storeentrydtl.setOpenClose("2");
							storeentrydtl.setQuantity(polineitems.get(j).getQuantity());
							storeentrydtl.setReqQuantity(polineitems.get(j).getReqQuantity());
							storeentrydtl.setUom(polineitems.get(j).getUnitId());

							polineitemsEmp.add(storeentrydtl);
							gatestoredtlDao.saveGateStoreEntryRegDtl(storeentrydtl);

							// calling procedure for extra polineitem
							List<POLineItem> poline = polineDao.getpolineitemByPoNum(polineitems.get(0).getPoId());
							System.out.println(poline.size() < polineitems.size());
							System.out.println("poline " + poline.size());
							System.out.println("storehdr.getPolineitems() " + polineitems.size());
							if (!poline.isEmpty()) {
								if (poline.size() < polineitems.size()) {
									gatestoredtlDao.storedProcForExtraPOLineItem(storeentrydtl.getHdrId(),
											polineitems.get(0).getPoId());
								}
								System.out.println(storeentrydtl.getHdrId());
								System.out.println("poid for last " + polineitems.get(0).getPoId());
							}
							if (poline.isEmpty()) {
								if (poline.size() < polineitems.size()) {
									gatestoredtlDao.storedProcForExtraPOLineItem(storeentrydtl.getHdrId(),
											polineitems.get(0).getPoId());
								}
								System.out.println(storeentrydtl.getHdrId());
								System.out.println("poid for last " + polineitems.get(0).getPoId());
							}
						}

					}

				}
			}

			// calling procedure for extra polineitem
			/*
			 * List<POLineItem> poline = polineDao.getpolineitemByPoNum(savehdr.getPoNo());
			 * if(poline.size()<polineitems.size()) {
			 * gatestoredtlDao.storedProcForExtraPOLineItem(savehdr.getHdrId(),
			 * savehdr.getPoNo()); }
			 */

		}

		// if(storehdr.getPoNo().isEmpty() || storehdr.getPoNo()==null){
		if (storehdr.getPoNo() == null || storehdr.getPoNo().isEmpty() || storehdr.getPoNo().size() == 0) {
			/*
			 * if(storehdr.getPoNo()==null && storehdr.getPoNo().isEmpty() &&
			 * storehdr.getPoNo().size()==0 &&
			 * Strings.isNullOrEmpty(storehdr.getPoNo().get(0))) {
			 */
			System.out.println("Inside without PO");
			// GateStoreEntryRegHdr storeentity =
			// storeentryhdrDao.getGateStoreEntryRegHdrByPoNo(storehdr.getPoNo());
			GateStoreEntryRegHdr storeentryhdrEntity = new GateStoreEntryRegHdr();

			storeentryhdrEntity.setChallanDate(Date.valueOf(storehdr.getChallanDate().toLocalDate()));
			storeentryhdrEntity.setChallanNo(storehdr.getChallanNo());
			storeentryhdrEntity.setDriverName(storehdr.getDriverName());
			// storeentryhdrEntity.setHdrId(storehdr.getHdrId());

			// storeentryhdrEntity.setInDate(Date.valueOf(LocalDate.now()));
			storeentryhdrEntity.setInDate(Date.valueOf(storehdr.getInDate().toLocalDate()));
			// storeentryhdrEntity.setInTime(Timestamp.valueOf(LocalDateTime.now()));
			storeentryhdrEntity.setInTime(
					Timestamp.valueOf(String.valueOf(storehdr.getInDate() + " " + storehdr.getInTime() + ".3654")));

			storeentryhdrEntity.setOpenClose("2");
			storeentryhdrEntity.setOutDate(null);
			storeentryhdrEntity.setOutTime(null);

			/*
			 * List<PONoDTO> ponos = new ArrayList<>(); List<PONoDTO> poid =
			 * storehdr.getPoNo(); for(int i = 0 ; i < poid.size() ; i++) { PONoDTO pono
			 * =new PONoDTO(); pono.setPoNo(poid.get(i).getPoNo()); //
			 * storeentryhdrEntity.setPoNo(pono); ponos.add(pono); }
			 */

			storeentryhdrEntity.setRemarks(storehdr.getRemarks());
			storeentryhdrEntity.setSuppCode(storehdr.getSuppCode());
			storeentryhdrEntity.setSupplierName(storehdr.getSupplierName());
			storeentryhdrEntity.setUpdateBy(storehdr.getUpdateBy());
			storeentryhdrEntity.setVehicleNo(storehdr.getVehicleNo());

			GateStoreEntryRegHdr savehdr = storeentryhdrDao.saveGateStoreEntryRegHdr(storeentryhdrEntity);
			// long seq = storeentryhdrDao.getseq();
			Long hdrid = savehdr.getHdrId();
			List<POLineItemDtlsDTO> lineitemslist = storehdr.getPolineitems();
			List<POLineItemDtlDTO> polineitems = new ArrayList<>();
			if (!lineitemslist.isEmpty()) {
				for (int n = 0; n < lineitemslist.size(); n++) {
					// GateStoreEntryRegDtl storeentrydtl = new GateStoreEntryRegDtl();

					polineitems = storehdr.getPolineitems().get(n).getPolineitems();
					// System.out.println(polineitems.size());

				}
				List<GateStoreEntryRegDtl> polineitemsEmp = new ArrayList<>();

				if (polineitems != null) {
					for (int j = 0; j < polineitems.size(); j++) {
						GateStoreEntryRegDtl storeentrydtl = new GateStoreEntryRegDtl();

						/*
						 * GateStoreEntryRegHdr entryhdr = new GateStoreEntryRegHdr();
						 * if(polineitems.get(n).getPoNo()!=null) { entryhdr =
						 * storeentryhdrDao.getGateStoreEntryRegHdrByPoNo(polineitems.get(n).getPoNo());
						 * storeentrydtl.setHdrId(entryhdr.getHdrId()); }
						 */
						storeentrydtl.setHdrId(savehdr.getHdrId());
						System.out.println(polineitems.get(j));
						storeentrydtl.setDtlId(gatestoredtlDao.getSeq());
						Department dept = deptDao.findById(Long.parseLong(polineitems.get(j).getDept()));
						storeentrydtl.setDept(String.valueOf(dept.getId()));

						if (polineitems.get(j).getItemDesc() != null) {
							ItemMaster item = itemDao.getItemMasterByItemdsc(polineitems.get(j).getItemDesc());
							storeentrydtl.setItemCode(item.getId());
							storeentrydtl.setItemDesc(item.getitemDsc());
							storeentrydtl.setItem(item.getItemGroupMaster().getId());
						}

						/*
						 * if(polineitem.getItemId()!=null) { ItemMaster items =
						 * itemDao.findItemMasterById(polineitem.getItemId());
						 * storeentrydtl.setItemDesc(items.getitemDsc()); }else {
						 * storeentrydtl.setItemDesc(null); }
						 */

						storeentrydtl.setOpenClose("2");
						storeentrydtl.setQuantity(polineitems.get(j).getReqQuantity());
						storeentrydtl.setReqQuantity(polineitems.get(j).getReqQuantity());
						storeentrydtl.setUom(polineitems.get(j).getUnitId());

						polineitemsEmp.add(storeentrydtl);
						// System.out.println(seq);
						System.out.println("header id " + savehdr.getHdrId());
						gatestoredtlDao.saveGateStoreEntryRegDtl(storeentrydtl);
					}

				}
			}
			// proc to be called

			// calling procedure for without po
			// System.out.println("seq "+seq);
			System.out.println("savehdr.getHdrId() " + savehdr.getHdrId());
			gatestoredtlDao.storedProcForWithoutPO(savehdr.getHdrId());

			// System.out.println(polineitems.size());
			// System.out.println(polineitems);
			// System.out.println(storehdr.getPoNo());
			// gatestoredtlDao.saveGateStoreEntryRegDtl(polineitemsEmp);
		}

		return new ResponseEntity<GateStoreEntryRegHdrDTO>(storehdr, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<GateStoreEntryRegHdrDTO> updateGateStoreEntryRegHdr(GateStoreEntryRegHdrDTO storehdr) {
		// GateStoreEntryRegHdr storeentryhdrEntity =
		// storeentryhdrDao.getGateStoreEntryRegHdrById(storehdr.getHdrId());
		GateStoreEntryRegHdr storeentryhdrEntity2 = storeentryhdrDao.getGateStoreEntryRegHdrById(storehdr.getId());
		if (storeentryhdrEntity2.getOutDate() == null) {
			for (int x = 0; x < storehdr.getPoNo().size(); x++) {
				// GateStoreEntryRegHdr storeentity =
				// storeentryhdrDao.getGateStoreEntryRegHdrByPoNo(storehdr.getPoNo());
				GateStoreEntryRegHdr storeentryhdrEntity = storeentryhdrDao
						.getGateStoreEntryRegHdrById(storehdr.getId());
				if (storeentryhdrEntity.getOutDate() == null) {

				}
				storeentryhdrEntity.setChallanDate(Date.valueOf(storehdr.getChallanDate().toLocalDate()));
				storeentryhdrEntity.setChallanNo(storehdr.getChallanNo());
				storeentryhdrEntity.setDriverName(storehdr.getDriverName());
				storeentryhdrEntity.setUpdateDateTime(Timestamp.valueOf(LocalDateTime.now()));
				storeentryhdrEntity.setOutDate(Date.valueOf(LocalDate.now()));
				storeentryhdrEntity.setOutTime(Timestamp.valueOf(LocalDateTime.now()));
				// storeentryhdrEntity.setHdrId(storehdr.getHdrId());

				// storeentryhdrEntity.setInDate(Date.valueOf(LocalDate.now()));
				// storeentryhdrEntity.setInDate(Date.valueOf(storehdr.getInDate().toLocalDate()));
				// storeentryhdrEntity.setInTime(Timestamp.valueOf(LocalDateTime.now()));
				// storeentryhdrEntity.setInTime(
				// Timestamp.valueOf(String.valueOf(storehdr.getInDate()+"
				// "+storehdr.getInTime()+".3654")));

				storeentryhdrEntity.setOpenClose("2");
				
				List<String> ponos = new ArrayList<>();
				List<String> poid = storehdr.getPoNo();
				for (int i = 0; i < poid.size(); i++) {
					String pono = poid.get(i);
					storeentryhdrEntity.setPoNo(pono);
					ponos.add(pono);
				}
				storeentryhdrEntity.setPoNo(ponos.get(x));

				storeentryhdrEntity.setRemarks(storehdr.getRemarks());
				storeentryhdrEntity.setSuppCode(storehdr.getSuppCode());
				storeentryhdrEntity.setSupplierName(storehdr.getSupplierName());
				storeentryhdrEntity.setUpdateBy(storehdr.getUpdateBy());
				storeentryhdrEntity.setVehicleNo(storehdr.getVehicleNo());

				storeentryhdrDao.saveGateStoreEntryRegHdr(storeentryhdrEntity);

				// System.out.println(polineitems.size());
				// System.out.println(polineitems);
				// System.out.println(storehdr.getPoNo());
				// gatestoredtlDao.saveGateStoreEntryRegDtl(polineitemsEmp);
			}

			List<POLineItemDtlsDTO> lineitemslist = storehdr.getPolineitems();
			List<POLineItemDtlDTO> polineitems = new ArrayList<>();
			if (!lineitemslist.isEmpty()) {
				for (int n = 0; n < lineitemslist.size(); n++) {
					// GateStoreEntryRegDtl storeentrydtl = new GateStoreEntryRegDtl();
					polineitems = storehdr.getPolineitems().get(n).getPolineitems();
					// System.out.println(polineitems.size());
					List<GateStoreEntryRegDtl> polineitemsEmp = new ArrayList<>();

					if (polineitems != null) {
						for (int j = 0; j < polineitems.size(); j++) {
							// GateStoreEntryRegDtl storeentrydtl = new GateStoreEntryRegDtl();
							GateStoreEntryRegDtl storeentrydtl = gatestoredtlDao
									.getGateStoreEntryRegDtlById(polineitems.get(j).getDtlId());

							System.out.println(polineitems.get(j));
							// storeentrydtl.setDept(polineitems.get(j).getDept());

							if (polineitems.get(j).getItemDesc() != null) {
								ItemMaster item = itemDao.getItemMasterByItemdsc(polineitems.get(j).getItemDesc());
								storeentrydtl.setItemCode(item.getId());
								storeentrydtl.setItemDesc(item.getitemDsc());
							}

							/*
							 * if(polineitem.getItemId()!=null) { ItemMaster items =
							 * itemDao.findItemMasterById(polineitem.getItemId());
							 * storeentrydtl.setItemDesc(items.getitemDsc()); }else {
							 * storeentrydtl.setItemDesc(null); }
							 */

							storeentrydtl.setOpenClose("2");
							storeentrydtl.setQuantity(polineitems.get(j).getQuantity());
							storeentrydtl.setReqQuantity(polineitems.get(j).getReqQuantity());
							storeentrydtl.setUom(polineitems.get(j).getUnitId());

							polineitemsEmp.add(storeentrydtl);
							gatestoredtlDao.saveGateStoreEntryRegDtl(storeentrydtl);
						}

					}

				}
			}
			return new ResponseEntity<GateStoreEntryRegHdrDTO>(storehdr, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<GateStoreEntryRegHdrDTO>(storehdr, HttpStatus.ALREADY_REPORTED);
		}
	}

	@Override
	public DeleteDTO deleteGateStoreEntryRegHdr(long id) {
		storeentryhdrDao.deleteGateStoreEntryRegHdr(id);

		DeleteDTO deleteDTO = new DeleteDTO();
		deleteDTO.setstatus(1);
		deleteDTO.setstatusCode(2000);
		deleteDTO.setmsg("Deleted successfully.");

		return deleteDTO;

	}

	@Override
	public List<GateStoreEntryRegHdrDTO> getAllGateStoreEntryRegHdrBySuppCodeAndChallanNo(String suppCode,
			long challanNo) {
		List<GateStoreEntryRegHdrDTO> hdrsDTO = new ArrayList<>();
		List<GateStoreEntryRegHdr> hdrs = storeentryhdrDao.getGateStoreEntryRegHdrByChallanNoAndSuppCode(suppCode,
				challanNo);
		hdrs.forEach(hdr -> {
			hdrsDTO.add(prepareStoreEntryHdrDTO(hdr));
		});
		return hdrsDTO.stream().sorted(Comparator.comparing(GateStoreEntryRegHdrDTO::getInTime).reversed())
				.collect(Collectors.toList());
	}

	@Override
	public ResponseEntity<GateStoreEntryRegHdrDTO> updateGateStoreEntryRegHdrOut(GateStoreEntryRegHdrDTO storehdr) {
		GateStoreEntryRegHdr storeentryhdrEntity2 = storeentryhdrDao.getGateStoreEntryRegHdrById(storehdr.getId());
		if (storeentryhdrEntity2.getOutDate() == null) {
			for (int x = 0; x < storehdr.getPoNo().size(); x++) {
				// GateStoreEntryRegHdr storeentity =
				// storeentryhdrDao.getGateStoreEntryRegHdrByPoNo(storehdr.getPoNo());
				GateStoreEntryRegHdr storeentryhdrEntity = storeentryhdrDao
						.getGateStoreEntryRegHdrById(storehdr.getId());

				/*
				 * storeentryhdrEntity.setChallanDate(storehdr.getChallanDate());
				 * storeentryhdrEntity.setChallanNo(storehdr.getChallanNo());
				 * storeentryhdrEntity.setDriverName(storehdr.getDriverName());
				 * storeentryhdrEntity.setUpdateDateTime(Timestamp.valueOf(LocalDateTime.now()))
				 * ;
				 */
				// storeentryhdrEntity.setHdrId(storehdr.getHdrId());

				// storeentryhdrEntity.setInDate(Date.valueOf(LocalDate.now()));
				// storeentryhdrEntity.setInDate(Date.valueOf(storehdr.getInDate().toLocalDate()));
				// storeentryhdrEntity.setInTime(Timestamp.valueOf(LocalDateTime.now()));
				// storeentryhdrEntity.setInTime(
				// Timestamp.valueOf(String.valueOf(storehdr.getInDate()+"
				// "+storehdr.getInTime()+".3654")));

				storeentryhdrEntity.setOpenClose("2");
				storeentryhdrEntity.setOutDate(Date.valueOf(LocalDate.now()));
				storeentryhdrEntity.setOutTime(Timestamp.valueOf(LocalDateTime.now()));

				/*
				 * List<String> ponos = new ArrayList<>(); List<String> poid =
				 * storehdr.getPoNo(); for(int i = 0 ; i < poid.size() ; i++) { String pono
				 * =poid.get(i); storeentryhdrEntity.setPoNo(pono); ponos.add(pono); }
				 * storeentryhdrEntity.setPoNo(ponos.get(x));
				 * 
				 * storeentryhdrEntity.setRemarks(storehdr.getRemarks());
				 * storeentryhdrEntity.setSuppCode(storehdr.getSuppCode());
				 * storeentryhdrEntity.setSupplierName(storehdr.getSupplierName());
				 * storeentryhdrEntity.setUpdateBy(storehdr.getUpdateBy());
				 * storeentryhdrEntity.setVehicleNo(storehdr.getVehicleNo());
				 */

				storeentryhdrDao.saveGateStoreEntryRegHdr(storeentryhdrEntity);

				// System.out.println(polineitems.size());
				// System.out.println(polineitems);
				// System.out.println(storehdr.getPoNo());
				// gatestoredtlDao.saveGateStoreEntryRegDtl(polineitemsEmp);
			}

			List<POLineItemDtlsDTO> lineitemslist = storehdr.getPolineitems();
			List<POLineItemDtlDTO> polineitems = new ArrayList<>();
			if (!lineitemslist.isEmpty()) {
				for (int n = 0; n < lineitemslist.size(); n++) {
					// GateStoreEntryRegDtl storeentrydtl = new GateStoreEntryRegDtl();
					polineitems = storehdr.getPolineitems().get(n).getPolineitems();
					// System.out.println(polineitems.size());
					List<GateStoreEntryRegDtl> polineitemsEmp = new ArrayList<>();

					if (polineitems != null) {
						for (int j = 0; j < polineitems.size(); j++) {
							// GateStoreEntryRegDtl storeentrydtl = new GateStoreEntryRegDtl();
							GateStoreEntryRegDtl storeentrydtl = gatestoredtlDao
									.getGateStoreEntryRegDtlById(polineitems.get(j).getDtlId());

							System.out.println(polineitems.get(j));
							/*
							 * storeentrydtl.setDept(polineitems.get(j).getDept());
							 * 
							 * if(polineitems.get(j).getItemDesc() != null) { ItemMaster item =
							 * itemDao.getItemMasterByItemdsc(polineitems.get(j).getItemDesc());
							 * storeentrydtl.setItemCode(item.getId());
							 * storeentrydtl.setItemDesc(item.getitemDsc()); }
							 */

							/*
							 * if(polineitem.getItemId()!=null) { ItemMaster items =
							 * itemDao.findItemMasterById(polineitem.getItemId());
							 * storeentrydtl.setItemDesc(items.getitemDsc()); }else {
							 * storeentrydtl.setItemDesc(null); }
							 */

							/*
							 * storeentrydtl.setOpenClose("2");
							 * storeentrydtl.setQuantity(polineitems.get(j).getQuantity());
							 * storeentrydtl.setReqQuantity(polineitems.get(j).getReqQuantity());
							 * storeentrydtl.setUom(polineitems.get(j).getUnitId());
							 */

							polineitemsEmp.add(storeentrydtl);
							gatestoredtlDao.saveGateStoreEntryRegDtl(storeentrydtl);
						}

					}
				}
			}

			return new ResponseEntity<GateStoreEntryRegHdrDTO>(storehdr, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<GateStoreEntryRegHdrDTO>(storehdr, HttpStatus.ALREADY_REPORTED);
		}

	}
}
