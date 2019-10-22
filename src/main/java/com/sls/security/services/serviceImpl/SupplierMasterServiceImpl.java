package com.sls.security.services.serviceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sls.security.component.BrokerDao;
import com.sls.security.component.BrokerSuppMapDao;
import com.sls.security.component.MukamDao;
import com.sls.security.component.MukamSupplierMapDao;
import com.sls.security.component.SupplierMasterDao;
import com.sls.security.dto.DeleteDTO;
import com.sls.security.dto.MukamSuppMapDTO;
import com.sls.security.dto.SuppDTO;
import com.sls.security.dto.SupplierMasterBrokerDTO;
import com.sls.security.dto.SupplierMasterDTO;
import com.sls.security.entity.BrokerSuppMap;
import com.sls.security.entity.Mukam;
import com.sls.security.entity.MukamSupplier;
import com.sls.security.entity.SupplierMaster;
import com.sls.security.services.SupplierMasterService;


/*
 * Service implementation class for supplier_master table
 */
@Service
public class SupplierMasterServiceImpl implements SupplierMasterService {

    @Autowired
    SupplierMasterDao suppliermasterDao;

    @Autowired
    MukamDao mukamDao;
    
    @Autowired
    SupplierMasterDao supplierDao;
   
    @Autowired
    MukamSupplierMapDao mukammapDao;
    
    @Autowired
	BrokerDao brokerDao;
	
	@Autowired
	BrokerSuppMapDao brokersuppDao;
    
    private static final Logger LOGGER=LoggerFactory.getLogger(SupplierMasterServiceImpl.class);

   /*
    * preparing supplier master DTO for displaying supplier master table details as well as mukams mapped with the
    * respective supplier
    */
    private SupplierMasterDTO prepareSupplierMasterDTO(SupplierMaster suppliermasterEntity, List<Mukam> muakms) throws Exception {
    	
    	SupplierMasterDTO suppliermasterDTO = new SupplierMasterDTO();
    	
    	//getting supplier master data
    	suppliermasterDTO.setsuppName(suppliermasterEntity.getsuppName().toUpperCase());
    	suppliermasterDTO.setaddress1(suppliermasterEntity.getaddress1());
    	suppliermasterDTO.setaddress2(suppliermasterEntity.getaddress2());
    	suppliermasterDTO.setaddress3(suppliermasterEntity.getaddress3());
//    	suppliermasterDTO.setsuppTyp(suppliermasterEntity.getsuppTyp());
    	
    	//setting supplier type with respect to database 
    	String type = suppliermasterEntity.getsuppTyp();
    	String suppType = null;
    	if(type.equalsIgnoreCase("J")) {
    		suppType="JUTE";
    	}else if(type.equalsIgnoreCase("S")) {
    		suppType="STORE";
    	}else if(type.equalsIgnoreCase("O")){
    		suppType="OTHERS";
    	}
    	
    	suppliermasterDTO.setsuppTyp(suppType);
    	
    	suppliermasterDTO.setphone1(suppliermasterEntity.getphone1());
    	suppliermasterDTO.setphone2(suppliermasterEntity.getphone2());
    	suppliermasterDTO.setfax(suppliermasterEntity.getfax());
    	suppliermasterDTO.setcellPhone(suppliermasterEntity.getcellPhone());
    	suppliermasterDTO.setemail(suppliermasterEntity.getemail());
    	suppliermasterDTO.setuser1(suppliermasterEntity.getuser1());
    	suppliermasterDTO.setdate1(suppliermasterEntity.getdate1());
    	suppliermasterDTO.setuser2(suppliermasterEntity.getuser2());
    	suppliermasterDTO.setdate2(suppliermasterEntity.getdate2());
    	suppliermasterDTO.setuser3(suppliermasterEntity.getuser3());
    	suppliermasterDTO.setdate3(suppliermasterEntity.getdate3());
    	suppliermasterDTO.setuser4(suppliermasterEntity.getuser4());
    	suppliermasterDTO.setdate4(suppliermasterEntity.getdate4());
    	suppliermasterDTO.setactiveFlag(suppliermasterEntity.getactiveFlag());
    	suppliermasterDTO.setsaleTaxNo(suppliermasterEntity.getsaleTaxNo());
    	suppliermasterDTO.setblackListedTag(suppliermasterEntity.getblackListedTag());
    	suppliermasterDTO.setblackListedDate(suppliermasterEntity.getblackListedDate());
    	suppliermasterDTO.setvatno(suppliermasterEntity.getvatno());
    	suppliermasterDTO.setcontactPerson(suppliermasterEntity.getcontactPerson());
    	suppliermasterDTO.setdesignation(suppliermasterEntity.getdesignation());
    	suppliermasterDTO.setcst(suppliermasterEntity.getcst());
    	suppliermasterDTO.setcin(suppliermasterEntity.getcin());
    	suppliermasterDTO.setgstNo(suppliermasterEntity.getgstNo());
    	suppliermasterDTO.setpanNo(suppliermasterEntity.getpanNo());
    	suppliermasterDTO.setmodby(suppliermasterEntity.getmodby());
    	suppliermasterDTO.setmodifiedOn(suppliermasterEntity.getmodifiedOn());
    	suppliermasterDTO.setdistrict(suppliermasterEntity.getdistrict());
    	suppliermasterDTO.setstate(suppliermasterEntity.getstate());
    	suppliermasterDTO.setcountry(suppliermasterEntity.getcountry());
    	suppliermasterDTO.setpincode(suppliermasterEntity.getpincode());
    	suppliermasterDTO.setSuppCode(suppliermasterEntity.getId());
    	
    	//getting mukam list only for supplier type jute
    	if(suppliermasterEntity.getsuppTyp().equalsIgnoreCase("J")) {
    		
        	List<MukamSuppMapDTO> mukamEmpList = new ArrayList<>();
        	MukamSuppMapDTO mukamEntityEmp = new MukamSuppMapDTO();
        	
        		//getting all the mukams and getting the size for the loop
//        		List<Mukam> mukamEmppList = mukamDao.findAll();
        		long size = muakms.size();
        		
        		//getting supplier mukam mapping entity filtering by supplier code
        		List<MukamSupplier> supps = mukammapDao.getBySuppCode(suppliermasterEntity.getId());
        		List<Long> mukamids = new ArrayList<>();
        		Long mukamid ;
        		//getting the mukam ids for all the supplier mukam map table
        		for(int k = 0 ; k < supps.size(); k++) {
        			mukamid = supps.get(k).getMukamId();
        			mukamids.add(mukamid);
        		}
        		
        		
        		
        	//checking whether the supplier is mapped with mukam or not
        	if(!supps.isEmpty()) {
        		
        		// loop for getting mukam  list
        		for(int i = 0 ; i < size ; i++) {
            		
            		String isMapped = "false";
            		if(mukamids.contains(muakms.get(i).getId())) {
            			isMapped = "true";
            		}else {
            			isMapped = "false";
            		}
            		
            		mukamEntityEmp.setIsMapped(isMapped);
            		mukamEntityEmp.setId(muakms.get(i).getId());
            		mukamEntityEmp.setMukamName(muakms.get(i).getmukamName());
            		
            		if(mukamEntityEmp.getIsMapped().equals("true"))
            			mukamEmpList.add(prepareMukamSuppDTO(mukamEntityEmp));
            		
            	}
        		suppliermasterDTO.setMukam(mukamEmpList);
        		
        	}
        	
    		
    	}
	
    	return suppliermasterDTO;
    }

    
    /*
     * preparing mukam supplier mapping table DTO
     */
    private MukamSuppMapDTO prepareMukamSuppDTO(MukamSuppMapDTO mukamEntityEmp) {
    	MukamSuppMapDTO mukam = new MukamSuppMapDTO();
    	
    	mukam.setId(mukamEntityEmp.getId());
    	mukam.setIsMapped(mukamEntityEmp.getIsMapped());
    	mukam.setMukamName(mukamEntityEmp.getMukamName());
    	
		return mukam;
	}

    /*
     * (non-Javadoc)
     * @see com.sls.security.services.SupplierMasterService#persistSupplierMaster(com.sls.security.dto.SupplierMasterDTO)
     * saving supplier master data to the suppliermaster table as well as mukams data to the mukam_supp_map table
     */
	@Override
    public ResponseEntity<SupplierMasterDTO> persistSupplierMaster(SupplierMasterDTO suppliermasterDTO) {

			/*
			 * for setting custom supplier code with is created by getting the first letter of t he supp_type and concata
			 * it with the sequence
			 */
    		String code = suppliermasterDTO.getsuppTyp().toUpperCase()+suppliermasterDao.seqNextVal();

        	SupplierMaster suppliermasterEntity = new SupplierMaster();
        	suppliermasterEntity.setId(code);
        	suppliermasterEntity.setsuppName(suppliermasterDTO.getsuppName().toUpperCase());
        	suppliermasterEntity.setaddress1(suppliermasterDTO.getaddress1());
        	suppliermasterEntity.setaddress2(suppliermasterDTO.getaddress2());
        	suppliermasterEntity.setaddress3(suppliermasterDTO.getaddress3());
        	suppliermasterEntity.setsuppTyp(suppliermasterDTO.getsuppTyp());
        	
        	suppliermasterEntity.setphone1(suppliermasterDTO.getphone1());
        	suppliermasterEntity.setphone2(suppliermasterDTO.getphone2());
        	suppliermasterEntity.setfax(suppliermasterDTO.getfax());
        	suppliermasterEntity.setcellPhone(suppliermasterDTO.getcellPhone());
        	suppliermasterEntity.setemail(suppliermasterDTO.getemail());
        	suppliermasterEntity.setuser1(suppliermasterDTO.getuser1());
        	suppliermasterEntity.setdate1(suppliermasterDTO.getdate1());
        	suppliermasterEntity.setuser2(suppliermasterDTO.getuser2());
        	suppliermasterEntity.setdate2(suppliermasterDTO.getdate2());
        	suppliermasterEntity.setuser3(suppliermasterDTO.getuser3());
        	suppliermasterEntity.setdate3(suppliermasterDTO.getdate3());
        	suppliermasterEntity.setuser4(suppliermasterDTO.getuser4());
        	suppliermasterEntity.setdate4(suppliermasterDTO.getdate4());
        	suppliermasterEntity.setactiveFlag(suppliermasterDTO.getactiveFlag());
        	suppliermasterEntity.setsaleTaxNo(suppliermasterDTO.getsaleTaxNo());
        	suppliermasterEntity.setblackListedTag(suppliermasterDTO.getblackListedTag());
        	suppliermasterEntity.setblackListedDate(suppliermasterDTO.getblackListedDate());
        	suppliermasterEntity.setvatno(suppliermasterDTO.getvatno());
        	suppliermasterEntity.setcontactPerson(suppliermasterDTO.getcontactPerson());
        	suppliermasterEntity.setdesignation(suppliermasterDTO.getdesignation());
        	suppliermasterEntity.setcst(suppliermasterDTO.getcst());
        	suppliermasterEntity.setcin(suppliermasterDTO.getcin());
        	suppliermasterEntity.setgstNo(suppliermasterDTO.getgstNo());
        	suppliermasterEntity.setpanNo(suppliermasterDTO.getpanNo());
        	suppliermasterEntity.setmodby(suppliermasterDTO.getmodby());
        	suppliermasterEntity.setmodifiedOn(Date.valueOf(LocalDate.now()));
        	suppliermasterEntity.setdistrict(suppliermasterDTO.getdistrict());
        	suppliermasterEntity.setstate(suppliermasterDTO.getstate());
        	suppliermasterEntity.setcountry(suppliermasterDTO.getcountry());
        	suppliermasterEntity.setpincode(suppliermasterDTO.getpincode());

        	suppliermasterDao.addSupplierMaster(suppliermasterEntity);
        	
        	List<MukamSuppMapDTO> mukamlist = suppliermasterDTO.getMukam();
        	System.out.println(mukamlist);
        	List<MukamSuppMapDTO> mukamsuppmap = new ArrayList<>(); 
        	MukamSuppMapDTO mukamsuppEntity = new MukamSuppMapDTO();
        	
        	//getting data of mukam supp map to save to the table mukam_supp_map table
        	char type = suppliermasterDTO.getsuppTyp().charAt(0);
        	System.out.println(type=='J');
        	if(type == 'J') {
    	    	for(int i = 0; i<=mukamlist.size()-1; i++) {
    	    		long seq = mukammapDao.getSeq();
    	    		mukamsuppEntity.setId(seq);
    	    		mukamsuppEntity.setMukamName(mukamlist.get(i).getMukamName());
    	    		mukamsuppEntity.setIsMapped("true");
    	    		mukamsuppmap.add(mukamsuppEntity);
    	    		mukammapDao.save(prepareMukamEntity(mukamsuppEntity, mukamlist.get(i).getId(), suppliermasterEntity.getId()));
    	    	}
        	}
        
    	
    	
	return new ResponseEntity<SupplierMasterDTO>(suppliermasterDTO, HttpStatus.CREATED);

    }

	/*
	 * preparing mukam supp mapping table data to saving the data to the table
	 */
    private MukamSupplier prepareMukamEntity(MukamSuppMapDTO mukamsuppEntity, long mukamid, String suppcode) {
		MukamSupplier mukamsupp = new MukamSupplier();
		
		mukamsupp.setIsMapped(mukamsuppEntity.getIsMapped());
		mukamsupp.setMukamId(mukamid);
		mukamsupp.setSupplierName(suppcode);
		
		return mukamsupp;
	}

    /*
     * (non-Javadoc)
     * @see com.sls.security.services.SupplierMasterService#updateSupplierMaster(com.sls.security.dto.SupplierMasterDTO)
     * updating suppliermaster table as well mukam_supp_map data 
     */
	@Override
    public ResponseEntity<SupplierMasterDTO> updateSupplierMaster(SupplierMasterDTO suppliermasterDTO) {

	if(suppliermasterDTO.getSuppCode() != null) {
    	SupplierMaster suppliermasterEntity = suppliermasterDao.findSupplierMasterById(suppliermasterDTO.getSuppCode());
    	
    	suppliermasterEntity.setsuppName(suppliermasterDTO.getsuppName());
    	suppliermasterEntity.setaddress1(suppliermasterDTO.getaddress1());
    	suppliermasterEntity.setaddress2(suppliermasterDTO.getaddress2());
    	suppliermasterEntity.setaddress3(suppliermasterDTO.getaddress3());
    	suppliermasterEntity.setphone1(suppliermasterDTO.getphone1());
    	suppliermasterEntity.setphone2(suppliermasterDTO.getphone2());
    	suppliermasterEntity.setfax(suppliermasterDTO.getfax());
    	suppliermasterEntity.setcellPhone(suppliermasterDTO.getcellPhone());
    	suppliermasterEntity.setemail(suppliermasterDTO.getemail());
    	suppliermasterEntity.setuser1(suppliermasterDTO.getuser1());
    	suppliermasterEntity.setdate1(suppliermasterDTO.getdate1());
    	suppliermasterEntity.setuser2(suppliermasterDTO.getuser2());
    	suppliermasterEntity.setdate2(suppliermasterDTO.getdate2());
    	suppliermasterEntity.setuser3(suppliermasterDTO.getuser3());
    	suppliermasterEntity.setdate3(suppliermasterDTO.getdate3());
    	suppliermasterEntity.setuser4(suppliermasterDTO.getuser4());
    	suppliermasterEntity.setdate4(suppliermasterDTO.getdate4());
    	suppliermasterEntity.setactiveFlag(suppliermasterDTO.getactiveFlag());
    	suppliermasterEntity.setsaleTaxNo(suppliermasterDTO.getsaleTaxNo());
    	suppliermasterEntity.setblackListedTag(suppliermasterDTO.getblackListedTag());
    	suppliermasterEntity.setblackListedDate(suppliermasterDTO.getblackListedDate());
    	suppliermasterEntity.setvatno(suppliermasterDTO.getvatno());
    	suppliermasterEntity.setcontactPerson(suppliermasterDTO.getcontactPerson());
    	suppliermasterEntity.setdesignation(suppliermasterDTO.getdesignation());
    	suppliermasterEntity.setcst(suppliermasterDTO.getcst());
    	suppliermasterEntity.setcin(suppliermasterDTO.getcin());
    	suppliermasterEntity.setgstNo(suppliermasterDTO.getgstNo());
    	suppliermasterEntity.setpanNo(suppliermasterDTO.getpanNo());
    	suppliermasterEntity.setmodby(suppliermasterDTO.getmodby());
    	suppliermasterEntity.setmodifiedOn(Date.valueOf(LocalDate.now()));
    	suppliermasterEntity.setdistrict(suppliermasterDTO.getdistrict());
    	suppliermasterEntity.setstate(suppliermasterDTO.getstate());
    	suppliermasterEntity.setcountry(suppliermasterDTO.getcountry());
    	suppliermasterEntity.setpincode(suppliermasterDTO.getpincode());
    	suppliermasterEntity.setId(suppliermasterDTO.getSuppCode());
	
	
	System.out.println("suppliermaster Entity to send to dao is " + suppliermasterEntity);
	suppliermasterDao.save(suppliermasterEntity);
	
	List<MukamSuppMapDTO> mukamlist = suppliermasterDTO.getMukam();
	List<MukamSupplier> mukamsuppmap = new ArrayList<>(); 
	
	char type = suppliermasterDTO.getsuppTyp().charAt(0);
	if(type == 'J') {
		for(int i = 0; i<=mukamlist.size()-1; i++) {
			MukamSupplier mukamsuppEntity = mukammapDao.getBySuppCodeAndMukamId(suppliermasterDTO.getSuppCode(),
    				suppliermasterDTO.getMukam().get(i).getId());
			//if the isMapped field from the DTO is true and the entity is not present in the table then add to the mapping table
			if(suppliermasterDTO.getMukam().get(i).getIsMapped().equalsIgnoreCase("true") && mukamsuppEntity==null) {
				MukamSupplier mukamsuppEmp = new MukamSupplier();
    			
    			mukamsuppEmp.setMukamId(suppliermasterDTO.getMukam().get(i).getId());
    			mukamsuppEmp.setSupplierName(suppliermasterDTO.getSuppCode());
    			mukamsuppEmp.setIsMapped(mukamlist.get(i).getIsMapped());
    			mukamsuppmap.add(mukammapDao.save(mukamsuppEmp));
			}else if(suppliermasterDTO.getMukam().get(i).getIsMapped().equalsIgnoreCase("true") && mukamsuppEntity!=null) {
				/*
				 * if the isMapped field from the DTO is true and the entity is present in the table then 
				 * update the mapping table
				 */
    			mukamsuppEntity.setIsMapped(mukamlist.get(i).getIsMapped());
    			mukamsuppEntity.setMukamId(mukamlist.get(i).getId());
    			mukamsuppEntity.setSupplierName(suppliermasterEntity.getId());
    			mukamsuppmap.add(mukammapDao.save(mukamsuppEntity));
    		}else if(suppliermasterDTO.getMukam().get(i).getIsMapped().equalsIgnoreCase("false") && mukamsuppEntity!=null) {
    			/*
    			 * if the ismapped field from the DTO is false then delete the entity
    			 */
    			mukammapDao.delete(mukamsuppEntity.getId());
    		}
		}
	}
	

	
	}
	
	return new ResponseEntity<SupplierMasterDTO>(suppliermasterDTO, HttpStatus.CREATED);

    }

	/*
	 * (non-Javadoc)
	 * @see com.sls.security.services.SupplierMasterService#populateSupplierMasterList()
	 * fetching all the suppliers from supplier master data 
	 */
    @Override
    public List<SupplierMasterDTO> populateSupplierMasterList() {
	List<SupplierMasterDTO> suppliermasterDTOList=new ArrayList<SupplierMasterDTO>();
	try {
	    List<SupplierMaster> suppliermasterEntityList=suppliermasterDao.findAll()
	    												.stream()
	    												.sorted(Comparator.comparing(SupplierMaster::getsuppName))
	    												.collect(Collectors.toList());
	    List<Mukam> muakms = mukamDao.findAll();
	    suppliermasterEntityList.forEach(suppliermasterEntity->{
	    	try {
				suppliermasterDTOList.add(prepareSupplierMasterDTO(suppliermasterEntity,muakms));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    });
	    
	   
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return suppliermasterDTOList;
    }

    /*
     * (non-Javadoc)
     * @see com.sls.security.services.SupplierMasterService#populateOneSupplierMasterDetails(java.lang.String)
     * fetching a single supplier by supplier code
     */
    @Override
    public SupplierMasterDTO populateOneSupplierMasterDetails(String Id) {
    	SupplierMaster suppliermasterEntity = suppliermasterDao.findSupplierMasterById(Id);
    	List<Mukam> mukams = new ArrayList<>();
		try {
			mukams = mukamDao.findAll();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	SupplierMasterDTO suppmasterDTO = new SupplierMasterDTO();
	try {
		suppmasterDTO = prepareSupplierMasterDTO(suppliermasterEntity,mukams);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return suppmasterDTO;
	
    }

    /*
     * (non-Javadoc)
     * @see com.sls.security.services.SupplierMasterService#destroySupplierMaster(java.lang.String)
     * deleting data from supplier master
     */
    @Override
    public DeleteDTO destroySupplierMaster(String Id) {
    	
    	List<MukamSupplier> mukamsupp = mukammapDao.getBySuppCode(Id);
    	suppliermasterDao.deleteSupplierMaster(Id);
    	
		mukamsupp.forEach(mukam->{
    		mukammapDao.delete(mukam.getId());
    	});
		
    	DeleteDTO deleteDTO = new DeleteDTO();
    	deleteDTO.setstatus(1);
    	deleteDTO.setstatusCode(2000);
    	deleteDTO.setmsg("Deleted Successfully.");
    	
    	return deleteDTO;
    	
    	
    }


	@Override
	public SupplierMasterDTO getSupplierBySuppCode(String id) {
		SupplierMaster supplier = suppliermasterDao.findSupplierMasterById(id);
		return prepareSupplierDTOForSuppAndSuppType(supplier);
	}


	@Override
	public SupplierMasterDTO getSupplierBySuppName(String suppName) {
		SupplierMaster supplier = suppliermasterDao.findBySuppName(suppName);
		List<Mukam> mukams = new ArrayList<>();
		SupplierMasterDTO supplierDTO = new SupplierMasterDTO(); 
		try {
			 mukams = mukamDao.findAll();
			 supplierDTO = prepareSupplierMasterDTO(supplier, mukams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return supplierDTO;
	}


	private SupplierMasterDTO prepareSupplierDTOForSuppAndSuppType(SupplierMaster suppliermasterEntity) {
		SupplierMasterDTO suppliermasterDTO = new SupplierMasterDTO();
    	
    	//getting supplier master data
    	suppliermasterDTO.setsuppName(suppliermasterEntity.getsuppName().toUpperCase());
    	suppliermasterDTO.setaddress1(suppliermasterEntity.getaddress1());
    	suppliermasterDTO.setaddress2(suppliermasterEntity.getaddress2());
    	suppliermasterDTO.setaddress3(suppliermasterEntity.getaddress3());
//    	suppliermasterDTO.setsuppTyp(suppliermasterEntity.getsuppTyp());
    	
    	//setting supplier type with respect to database 
    	String type = suppliermasterEntity.getsuppTyp();
    	String suppType = null;
    	if(type.equalsIgnoreCase("J")) {
    		suppType="JUTE";
    	}else if(type.equalsIgnoreCase("S")) {
    		suppType="STORE";
    	}else if(type.equalsIgnoreCase("O")){
    		suppType="OTHERS";
    	}
    	
    	suppliermasterDTO.setsuppTyp(suppType);
    	
    	suppliermasterDTO.setphone1(suppliermasterEntity.getphone1());
    	suppliermasterDTO.setphone2(suppliermasterEntity.getphone2());
    	suppliermasterDTO.setfax(suppliermasterEntity.getfax());
    	suppliermasterDTO.setcellPhone(suppliermasterEntity.getcellPhone());
    	suppliermasterDTO.setemail(suppliermasterEntity.getemail());
    	suppliermasterDTO.setuser1(suppliermasterEntity.getuser1());
    	suppliermasterDTO.setdate1(suppliermasterEntity.getdate1());
    	suppliermasterDTO.setuser2(suppliermasterEntity.getuser2());
    	suppliermasterDTO.setdate2(suppliermasterEntity.getdate2());
    	suppliermasterDTO.setuser3(suppliermasterEntity.getuser3());
    	suppliermasterDTO.setdate3(suppliermasterEntity.getdate3());
    	suppliermasterDTO.setuser4(suppliermasterEntity.getuser4());
    	suppliermasterDTO.setdate4(suppliermasterEntity.getdate4());
    	suppliermasterDTO.setactiveFlag(suppliermasterEntity.getactiveFlag());
    	suppliermasterDTO.setsaleTaxNo(suppliermasterEntity.getsaleTaxNo());
    	suppliermasterDTO.setblackListedTag(suppliermasterEntity.getblackListedTag());
    	suppliermasterDTO.setblackListedDate(suppliermasterEntity.getblackListedDate());
    	suppliermasterDTO.setvatno(suppliermasterEntity.getvatno());
    	suppliermasterDTO.setcontactPerson(suppliermasterEntity.getcontactPerson());
    	suppliermasterDTO.setdesignation(suppliermasterEntity.getdesignation());
    	suppliermasterDTO.setcst(suppliermasterEntity.getcst());
    	suppliermasterDTO.setcin(suppliermasterEntity.getcin());
    	suppliermasterDTO.setgstNo(suppliermasterEntity.getgstNo());
    	suppliermasterDTO.setpanNo(suppliermasterEntity.getpanNo());
    	suppliermasterDTO.setmodby(suppliermasterEntity.getmodby());
    	suppliermasterDTO.setmodifiedOn(suppliermasterEntity.getmodifiedOn());
    	suppliermasterDTO.setdistrict(suppliermasterEntity.getdistrict());
    	suppliermasterDTO.setstate(suppliermasterEntity.getstate());
    	suppliermasterDTO.setcountry(suppliermasterEntity.getcountry());
    	suppliermasterDTO.setpincode(suppliermasterEntity.getpincode());
    	suppliermasterDTO.setSuppCode(suppliermasterEntity.getId());
		return suppliermasterDTO;
	}


	@Override
	public List<SupplierMasterDTO> getStoreSupplier() {
		List<SupplierMasterDTO> supplierDto = new ArrayList<>();
		try {
			List<SupplierMaster> supplier = supplierDao.findBySuppType("S")
														.stream()
														.sorted(Comparator.comparing(SupplierMaster::getsuppName))
														.collect(Collectors.toList());
			supplier.forEach(supp->{
				supplierDto.add(prepareSupplierDTOForSuppAndSuppType(supp));
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return supplierDto;
	}


	@Override
	public List<SupplierMasterDTO> getJuteSupplier() {
		List<SupplierMasterDTO> supplierDto = new ArrayList<>();
		try {
			List<SupplierMaster> supplier = supplierDao.findBySuppType("J")
														.stream()
														.sorted(Comparator.comparing(SupplierMaster::getsuppName))
														.collect(Collectors.toList());
			List<Mukam> mukams = mukamDao.findAll();
			supplier.forEach(supp->{
				try {
					supplierDto.add(prepareSupplierMasterDTO(supp,mukams));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return supplierDto;
	}


	@Override
	public SuppDTO getSupplierBySuppNameDTO(String suppName) {
		SuppDTO posupplier = new SuppDTO();
		SupplierMaster supplier = supplierDao.findBySuppName(suppName);
		List<MukamSupplier> mukammap = mukammapDao.getBySuppCode(supplier.getId());
		
		posupplier.setSuppCode(supplier.getId());
		posupplier.setSuppName(supplier.getsuppName());
		posupplier.setAddress1(supplier.getaddress1());
		
		
		List<Mukam> muakms = new ArrayList<>();
		for(int i = 0 ; i < mukammap.size() ; i++) {
			Mukam mukamEmp = new Mukam();
			mukamEmp.setId(mukammap.get(i).getMukamId());
			Mukam m = mukamDao.findMukamById(mukamEmp.getId());
			mukamEmp.setmukamName(m.getmukamName());
			muakms.add(mukamEmp);
		}
		
		posupplier.setMukams(muakms);
		
		return posupplier;
	}


	@Override
	public List<SupplierMasterBrokerDTO> getAllSupplierByBroker(long brokerId) {
		List<SupplierMasterBrokerDTO> supplierDTO = new ArrayList<>();
		try {
			List<BrokerSuppMap> brokersupp = brokersuppDao.getByBrokerId(brokerId);
			brokersupp.forEach(brokersuppmap->{
				supplierDTO.add(prepareSuppBrokerDTO(brokersuppmap));
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return supplierDTO;
	}


	private SupplierMasterBrokerDTO prepareSuppBrokerDTO(BrokerSuppMap brokersuppmap) {
		SupplierMasterBrokerDTO supplierDTO = new SupplierMasterBrokerDTO();
		SupplierMaster supplier = supplierDao.findSupplierMasterById(brokersuppmap.getSuppCode());
		
		supplierDTO.setSuppCode(supplier.getId());
		supplierDTO.setSuppName(supplier.getsuppName());
		
		return supplierDTO;
	}

   
    

    
}
