package com.example.LaptopShopClone.api;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.LaptopShopClone.DTO.SanPhamDTO;
import com.example.LaptopShopClone.Entity.DanhMuc;
import com.example.LaptopShopClone.Entity.HangSanXuat;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Entity.SanPham;
import com.example.LaptopShopClone.Entity.VaiTro;
import com.example.LaptopShopClone.ServiceInterface.ChiTietDonHangService;
import com.example.LaptopShopClone.ServiceInterface.DanhMucService;
import com.example.LaptopShopClone.ServiceInterface.DonHangService;
import com.example.LaptopShopClone.ServiceInterface.HangSanXuatService;
import com.example.LaptopShopClone.ServiceInterface.SanPhamService;
import com.example.LaptopShopClone.ServiceInterface.VaiTroService;
import com.example.LaptopShopClone.Utils.ErrorsContainer;
import com.example.LaptopShopClone.Utils.ResponseObject;
import com.example.LaptopShopClone.Utils.SearchSanPhamCriteria;
import com.example.LaptopShopClone.Utils.Validation;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/sanpham")
@SessionAttributes({"loggedUser","role_list"})
public class sanPhamAPI {
	@Autowired
	Validation validation;
	@Autowired
	VaiTroService vaiTroService;
	@Autowired
	DonHangService donHangService;
	@Autowired
	ChiTietDonHangService chiTietDonHangService;
	@Autowired
	SanPhamService sanPhamService;
	@Autowired
	DanhMucService danhMucService;
	@Autowired
	HangSanXuatService hangSanXuatService;
	@Autowired
	ErrorsContainer<String> errorsContainer;
	
	
	@ModelAttribute("loggedUser")
	public NguoiDung getLoggedUser(HttpServletRequest httpServletRequest) {
		return (NguoiDung)httpServletRequest.getSession().getAttribute("loggedUser");
		
	}
	
	@ModelAttribute("role_list")
	public List<VaiTro> getLoggedUserVaiTro(HttpServletRequest httpServletRequest) {
		NguoiDung ng=getLoggedUser(httpServletRequest);
		if(ng==null)
			return null;
					
		return getLoggedUser(httpServletRequest).getVaiTro();
	}
	
	@GetMapping("/test")
	public String test() {
		return "oke";
	}
//	
//	@RequestParam(name="danhMucId", defaultValue = "0", required = false) long danhMucId,
//	@RequestParam(name="hangSXId", defaultValue = "", required = false) long hangSXId,
//	@RequestParam(name="minValue", defaultValue = "0", required = false) int minValue,
//	@RequestParam(name="maxValue", defaultValue ="-1", required = false) int maxValue,
//	@RequestParam(name="sapXepTheo", defaultValue = "", required = false) String sapXepTheo,
//	@RequestParam(name="searchByName", defaultValue = "", required = false) String searchByName,
//	@RequestParam(name="searchById", defaultValue = "0", required = false) long searchById	
	
	@GetMapping("/searchSanPham")
	public ResponseEntity<ResponseObject> searchSanPham(
			@RequestParam(name="danhMucId", defaultValue = "0") long danhMucId,
			@RequestParam(name="hangSXId", defaultValue = "0") long hangSXId,
			@RequestParam(name="minValue", defaultValue = "0") int minValue,
			@RequestParam(name="maxValue", defaultValue ="-1") int maxValue,
			@RequestParam(name="sapXepTheo", defaultValue = "") String sapXepTheo,
			@RequestParam(name="searchByName", defaultValue = "") String searchByName,
			@RequestParam(name="searchById", defaultValue = "0") long searchById,
			@RequestParam(name="currentPage", defaultValue = "1") int currentPage,
			@RequestParam(name="sanPhamId", defaultValue = "0") long sanPhamId
			){
		
		
		int noResultPerPage=8;
		int noPage=5;
		
		if(searchById!=0) {
			ResponseObject ro=new ResponseObject();
			ro.setStatus("success");
			ArrayList<SanPham> content=new ArrayList<SanPham>();
			content.add(this.sanPhamService.getSanPhamByID(searchById));
			ro.setData(content);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(ro);
		}
		SearchSanPhamCriteria searchSanPhamCriteria=new SearchSanPhamCriteria();
		searchSanPhamCriteria.setDanhMuc(danhMucId);
		searchSanPhamCriteria.setHsx(hangSXId);
		searchSanPhamCriteria.setMinValue(minValue);
		searchSanPhamCriteria.setMaxValue(maxValue);
		searchSanPhamCriteria.setSort(sapXepTheo);
		searchSanPhamCriteria.setKeyWord(searchByName.split(" "));
		searchSanPhamCriteria.setSanPhamId(sanPhamId);
		List<SanPham> sanPhamList=this.sanPhamService.searchSanPham(searchSanPhamCriteria, currentPage, noResultPerPage);

		int totalPage=this.sanPhamService.getTotalResultCount(searchSanPhamCriteria);
		totalPage=(int)Math.ceil((double)totalPage/noResultPerPage);
		
		
		ResponseObject ro=new ResponseObject();
		ro.setStatus("success");
		ro.setData(sanPhamList);
		ro.setCurrentPage(currentPage);
		ro.setTotalPage(totalPage);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(ro);
	}
	
	@GetMapping("/getSoLuongSanPhamCriteria")
	public ResponseEntity<ResponseObject> getSoLuongSanPhamCriteria(
			@RequestParam(name="danhMucId", defaultValue = "0") long danhMucId,
			@RequestParam(name="hangSXId", defaultValue = "0") long hangSXId,
			@RequestParam(name="minValue", defaultValue = "0") int minValue,
			@RequestParam(name="maxValue", defaultValue ="-1") int maxValue,
			@RequestParam(name="sapXepTheo", defaultValue = "") String sapXepTheo,
			@RequestParam(name="searchByName", defaultValue = "") String searchByName,
			@RequestParam(name="searchById", defaultValue = "0") long searchById,
			@RequestParam(name="currentPage", defaultValue = "1") int currentPage
			){
		
		SearchSanPhamCriteria searchSanPhamCriteria=new SearchSanPhamCriteria();
		searchSanPhamCriteria.setDanhMuc(danhMucId);
		searchSanPhamCriteria.setHsx(hangSXId);
		searchSanPhamCriteria.setMinValue(minValue);
		searchSanPhamCriteria.setMaxValue(maxValue);
		searchSanPhamCriteria.setSort(sapXepTheo);
		searchSanPhamCriteria.setKeyWord(searchByName.split(" "));
		
		ResponseObject ro=new ResponseObject();
		ro.setStatus("success");
		ro.setData(this.sanPhamService.getTotalResultCount(searchSanPhamCriteria));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(ro);
	}
	
	@PostMapping("addSanPham")
	public ResponseEntity<ResponseObject> addSanPham(
			SanPhamDTO sanPhamDTO,
			HttpServletRequest httpServletRequest
			
			){
		
		System.out.println("addSanPham is called ");
		
		if(!this.validation.isAdmin(this.getLoggedUser(httpServletRequest), this.getLoggedUserVaiTro(httpServletRequest))) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
//		ErrorsContainer<String> errorsContainer=new ErrorsContainer<String>();
		
//		Map<String, List<String>> errors=new HashMap<String, List<String>>();
//		errors.put("tenSanPham", new LinkedList<String>());
//		errors.put("donGia", new LinkedList<String>());
//		errors.put("donViKho", new LinkedList<String>());
//		errors.put("donViBan", new LinkedList<String>());
//		errors.put("thongTinBaoHanh", new LinkedList<String>());
//		errors.put("thongTinChung", new LinkedList<String>());
//		errors.put("manHinh", new LinkedList<String>());
//		errors.put("cpu", new LinkedList<String>());
//		errors.put("ram", new LinkedList<String>());
//		errors.put("thietKe", new LinkedList<String>());
//		errors.put("dungLuongPin", new LinkedList<String>());
//		errors.put("hinhAnh", new LinkedList<String>());
//		errors.put("danhMuc", new LinkedList<String>());
//		errors.put("hangSanXuat", new LinkedList<String>());
		
//		check empty field value
//		if(this.validation.isStringNullOrEmptyString(sanPhamDTO.getTenSanPham()))
//			errors.get("tenSanPham").add("Không được để trống");
//		if(this.validation.isStringNullOrEmptyString(sanPhamDTO.getDonGia()))
//			errors.get("donGia").add("Không được để trống");
//		if(this.validation.isStringNullOrEmptyString(sanPhamDTO.getDonViKho()))
//			errors.get("donViKho").add("Không được để trống");
//		if(this.validation.isStringNullOrEmptyString(sanPhamDTO.getDonViBan()))
//			errors.get("donViBan").add("Không được để trống");
//		if(this.validation.isStringNullOrEmptyString(sanPhamDTO.getThongTinBaoHanh()))
//			errors.get("thongTinBaoHanh").add("Không được để trống");
//		if(this.validation.isStringNullOrEmptyString(sanPhamDTO.getThongTinChung()))
//			errors.get("thongTinChung").add("Không được để trống");
//		if(this.validation.isStringNullOrEmptyString(sanPhamDTO.getManHinh()))
//			errors.get("manHinh").add("Không được để trống");
//		if(this.validation.isStringNullOrEmptyString(sanPhamDTO.getCpu()))
//			errors.get("cpu").add("Không được để trống");
//		if(this.validation.isStringNullOrEmptyString(sanPhamDTO.getRam()))
//			errors.get("ram").add("Không được để trống");
//		if(this.validation.isStringNullOrEmptyString(sanPhamDTO.getThietKe()))
//			errors.get("thietKe").add("Không được để trống");
//		if(this.validation.isStringNullOrEmptyString(sanPhamDTO.getDungLuongPin()))
//			errors.get("dungLuongPin").add("Không được để trống");
//		if(this.validation.isStringNullOrEmptyString(sanPhamDTO.getHinhAnh()))
//			errors.get("hinhAnh").add("Không được để trống");
//		if(this.validation.isStringNullOrEmptyString(sanPhamDTO.getDanhMuc()))
//			errors.get("danhMuc").add("Không được để trống");
//		if(this.validation.isStringNullOrEmptyString(sanPhamDTO.getHangSanXuat()))
//			errors.get("hangSanXuat").add("Không được để trống");
		errorsContainer.isEmptyOrNullString(sanPhamDTO.getTenSanPham(), "tenSanPham");
		errorsContainer.isEmptyOrNullString(sanPhamDTO.getDonGia(), "donGia");
		errorsContainer.isEmptyOrNullString(sanPhamDTO.getDonViKho(), "donViKho");
		errorsContainer.isEmptyOrNullString(sanPhamDTO.getThongTinBaoHanh(), "thongTinBaoHanh");
		errorsContainer.isEmptyOrNullString(sanPhamDTO.getThongTinChung(), "thongTinChung");
		errorsContainer.isEmptyOrNullString(sanPhamDTO.getManHinh(), "manHinh");
		errorsContainer.isEmptyOrNullString(sanPhamDTO.getCpu(), "cpu");
		errorsContainer.isEmptyOrNullString(sanPhamDTO.getRam(), "ram");
		errorsContainer.isEmptyOrNullString(sanPhamDTO.getThietKe(), "thietKe");
		errorsContainer.isEmptyOrNullString(sanPhamDTO.getDungLuongPin(), "dungLuongPin");
		errorsContainer.isMultipartFileEmpty(sanPhamDTO.getHinhAnh(), "hinhAnh");
		errorsContainer.isEmptyOrNullString(sanPhamDTO.getDanhMuc(), "danhMuc");
		errorsContainer.isEmptyOrNullString(sanPhamDTO.getHangSanXuat(), "hangSanXuat");

	
		
		
		//check if number is in number format and number is positive
//		if(this.validation.isNumberic(sanPhamDTO.getDonGia()))
//			errors.get("donGia").add("Sai format");
//		else if(Integer.parseInt(sanPhamDTO.getDonGia())<=0)
//			errors.get("donGia").add("Phải là số dương");
//		
//		if(this.validation.isNumberic(sanPhamDTO.getDonViKho()))
//			errors.get("donViKho").add("Sai format");
//		else if(Integer.parseInt(sanPhamDTO.getDonViKho())<=0)
//			errors.get("donViKho").add("Phải là số dương");
//		
//		if(this.validation.isNumberic(sanPhamDTO.getDonViBan()))
//			errors.get("donViBan").add("Sai format");
//		else if(Integer.parseInt(sanPhamDTO.getDonViBan())<=0)
//			errors.get("donViBan").add("Phải là số dương");
		errorsContainer.isInteger(sanPhamDTO.getDonGia(), "donGia");
		errorsContainer.isPositiveNumber(sanPhamDTO.getDonGia(), "donGia");
		
		errorsContainer.isInteger(sanPhamDTO.getDonViKho(), "donViKho");
		errorsContainer.isPositiveNumber(sanPhamDTO.getDonViKho(), "donViKho");
		
//		errorsContainer.isInteger(sanPhamDTO.getDonViBan(), "donViBan");
//		errorsContainer.isPositiveNumber(sanPhamDTO.getDonViBan(), "donViBan");
		
		
		//check if danh muc is valid
//		DanhMuc danhmuc=this.danhMucService.getDanhMucById(Long.parseLong(sanPhamDTO.getDanhMuc()));
//		if(danhmuc==null)
//			errors.get("danhMuc").add("Danh mục không tồn tại");
		errorsContainer.isDanhMucValid(sanPhamDTO.getDanhMuc(), "danhMuc");
		
		//check if hang san xuat is valid
//		HangSanXuat hsx=this.hangSanXuatService.getHSXById(Long.parseLong(sanPhamDTO.getHangSanXuat()));
//		if(hsx==null)
//			errors.get("hangSanXuat").add("Hãng sản xuất không tồn tại");
		errorsContainer.isHSXValid(sanPhamDTO.getHangSanXuat(), "hangSanXuat");
		
		if(errorsContainer.isHasError()) {
			ResponseObject ro=new ResponseObject();
			ro.setStatus("fail");
			ro.setData(errorsContainer.getErrors());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(ro);
		}
		
		//Luu san pham truoc khi luu hinh anh
		SanPham sanPham=new SanPham();
		sanPhamDTO.convertToEntity(sanPham);
		sanPham.setDanhMuc(this.danhMucService.getDanhMucById(Long.parseLong(sanPhamDTO.getDanhMuc())));
		sanPham.setHangSanXuat(this.hangSanXuatService.getHSXById(Long.parseLong(sanPhamDTO.getHangSanXuat())));
		this.sanPhamService.SaveOrUpdate(sanPham);
		System.out.println(sanPham.toString());
		
		//Luu file hinh anh san pham co then la id cua san pham
		Path staticServingDir=Paths.get("src/main/resources/static");
		Path hinhAnhSanPhamDir=Paths.get("src/main/resources/static/HinhAnhSP");
		if(!Files.exists(hinhAnhSanPhamDir)) {
			try {
				hinhAnhSanPhamDir=Files.createDirectory(hinhAnhSanPhamDir);
				System.out.println("hinhAnhSanPhamDir:");
				System.out.println(hinhAnhSanPhamDir.toAbsolutePath());
				System.out.println(hinhAnhSanPhamDir.toString());
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		Path fileDir=hinhAnhSanPhamDir.resolve(String.valueOf(sanPham.getId())+".png");
		System.out.println("fileDir:");
		System.out.println(fileDir.toAbsolutePath());
		try {
//			Files.copy(sanPhamDTO.getHinhAnh().getInputStream(), hinhAnhSanPhamDir,StandardCopyOption.REPLACE_EXISTING);
			Files.copy(sanPhamDTO.getHinhAnh().getInputStream(), fileDir);
//			sanPham.setHinhAnh(fileDir.toString());
			sanPham.setHinhAnh(staticServingDir.relativize(fileDir).toString());
			
			this.sanPhamService.SaveOrUpdate(sanPham);
		} catch (Exception e) {
			System.out.println(e);
		}

		ResponseObject ro=new ResponseObject();
		ro.setStatus("success");
		
		
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(ro);
	}
	
	
}
