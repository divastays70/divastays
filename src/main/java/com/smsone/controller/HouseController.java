package com.smsone.controller;

import java.io.IOException;
import java.util.List;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smsone.model.House;
import com.smsone.model.Owner;
import com.smsone.model.User;
import com.smsone.service.HouseService;

@Controller
public class HouseController {
	@Autowired
	private HouseService houseService;		
	@RequestMapping(value = {"/showLongTermSorting","/showShortTermSorting","/showMainFilterSorting","/showFacilitiesSorting"})
	public String applySorting(HttpSession session,Model model,Integer offset, Integer maxResults,@RequestParam("priceSort") String priceSort,HttpServletRequest request)
	{
		
		String accommodationType=(String) session.getAttribute("longTerm");
		String profession=(String) session.getAttribute("profession");
		String motherTongue=(String) session.getAttribute("motherTongue");
		String foodPreference=(String) session.getAttribute("foodPreference");
		Double rent=(Double) session.getAttribute("rent");		
		String locationArea=(String) session.getAttribute("locationArea");
		String[] facilities=(String[]) session.getAttribute("facilities");
		House house=new House();
		house.setAddress("pune");
		house.setAccommodationType(accommodationType);		
	    house.setRent(rent);
	    house.setLocationArea(locationArea);
		User user=new User();
		user.setProfession(profession);
		user.setMotherTongue(motherTongue);
		user.setFoodPreference(foodPreference);	
		
		if(request.getRequestURI().equals("/PGHOSTEL/showLongTermSorting"))
		{
			
			model.addAttribute("house",houseService.listHouseByAddressLongTerm(house, offset, maxResults, priceSort));
		}
		else if(request.getRequestURI().equals("/PGHOSTEL/showShortTermSorting"))
		{
			model.addAttribute("house",houseService.listHouseByAddressShortTerm(house, offset, maxResults,priceSort));		
		}
		else if(request.getRequestURI().equals("/PGHOSTEL/showMainFilterSorting"))
		{
			model.addAttribute("house",houseService.listHouseByMainFilter(house, user, offset, maxResults, priceSort));
		}
		else if(request.getRequestURI().equals("/PGHOSTEL/showFacilitiesSorting"))
		{
			model.addAttribute("house", houseService.listHouseByadvancedFilter(house, user, facilities, offset, maxResults,priceSort));
		}
		else
		{
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
	
		
		
		//String facilities[]=(String[]) session.getAttribute("facilities");
		
		//User user=new User();
		/*if(address.isEmpty())
		{
			
		}*/
		//house.setLocationArea(locationArea);
		
       
        	
		//model.addAttribute("house", houseService.applySorting(house,offset,maxResults,priceSort));
		return "filter";  
	}
	//show HouseInfo Home
	@RequestMapping(value = "/showHouseInfo/showHome")
	public String showHome1()
	{
		return "redirect:/showHome";  
	}
	@RequestMapping(value = "/editRoomDetails/{hId}")
	public String editRoomDetails(@PathVariable("hId") Long hId,RedirectAttributes ra)
	{
		ra.addAttribute("hId", hId);
		return "redirect:/editRoomDetails1";
     
	}
	@RequestMapping(value = "/editRoomDetails1")
	public String editRoomDetails1(@RequestParam("hId") Long hId,Model model)
	{
		House house=new House();
		house.sethId(hId);
	    model.addAttribute("room",houseService.getRooms(house));
		return "ownerRooms";  
	}
	@RequestMapping(value = "/showEditHouseDetails/{hId}")
	public String showEditHouseDetails(@PathVariable("hId") Long hId,Model model,HttpSession session,RedirectAttributes ra)
	{
        ra.addAttribute("hId",hId);
		return "redirect:/editHouseDetails";
     
	}
	@RequestMapping(value = "/editHouseDetails")
	public String editHouseDetails(@RequestParam("hId") Long hId,Model model,HttpSession session)
	{
		House house=new House();
		house.sethId(hId);
		model.addAttribute("house",houseService.getHouse(house));
		Long oId= (Long)session.getAttribute("oId");
		model.addAttribute("oId", oId);
		return "editHouseDetails";  
	}
	@RequestMapping(value = "/deleteHouse/{hId}")
	public String deleteHouse(@PathVariable("hId") Long hId,RedirectAttributes ra)
	{
        ra.addAttribute("hId",hId);
		return "redirect:/deleteHouse1";
	}
	@RequestMapping(value = "/deleteHouse1")
	public String deleteHouse1(@RequestParam("hId") Long hId,Model model,HttpSession session,RedirectAttributes ra)
	{
		House house=new House();
		house.sethId(hId);
		Long oId= (Long)session.getAttribute("oId");
		houseService.deleteHouse(house);		
		List<House> house2= houseService.remainingOwnerHouse(oId);
			ra.addAttribute("house", house2);
			return "ownerHomes";
                                                                                                                                                           
	}
	//show house reg page
	@RequestMapping(value = "/showHouseReg")
	public String showHouseRegistration(HttpSession session,Model model)
	{
			
		Owner owner1=(Owner)session.getAttribute("owner");
		if(owner1==null)
		{
			return "redirect:/showOwnerPage";
		}
		else
		{
			model.addAttribute("oId",owner1.getoId());
			return "houseRegistration";
		}			
	}
		//save house
	//save house
			@RequestMapping(value = "/saveHouse",method = RequestMethod.POST)
			public String saveHouse(@RequestParam("ownerId")Long oId,@RequestParam("pincode")Long pincode,@RequestParam("accommodationType")String accommodationType,@RequestParam("tenancyType")String tenancyType,@RequestParam("room")Integer room,@RequestParam("city")String city,@RequestParam("subcategory1")String state,@RequestParam("rent")Double rent,@RequestParam("area")Double area,@RequestParam("img1")MultipartFile img1,@RequestParam("img2")MultipartFile img2,@RequestParam("houseName")String houseName,@RequestParam("floorNumber")Integer floorNumber,
			@RequestParam("address")String address,@RequestParam("subcategory")String locationArea,@RequestParam("subcategory2")String country,@RequestParam("deposit")Double deposit,@RequestParam("foodPreference")String foodPreference,@RequestParam("latitude")Double latitude,@RequestParam("longitude")Double longitude,@RequestParam("img3")MultipartFile img3,Model model,HttpSession session) throws IOException,SerialException
			{
				House house=new House();
				house.setAddress(address);
				house.setArea(area);
				house.setPincode(pincode);
				house.setDeposit(deposit);
				house.setFloorNumber(floorNumber);
				house.setFoodPreference(foodPreference);
				house.setRent(rent);
				house.setRoom(room);
				house.setState(state);
				house.setCity(city);
				house.setCountry(country);
				house.setLocationArea(locationArea);
				house.setTenancyType(tenancyType);
				house.setAccommodationType(accommodationType);
				house.setLatitude(latitude);
				house.setLongitude(longitude);
				byte[] img11 = img1.getBytes();
				byte[] img13 = img2.getBytes();
				byte[] img12 = img3.getBytes();
				house.setImg1(img11);
				house.setImg2(img12);
				house.setImg3(img13);
				house.setHouseName(houseName);
				houseService.saveHouse(house,oId);
				model.addAttribute("hId",house.gethId());
				model.addAttribute("room",room);
				model.addAttribute("i",  new Integer(1));
				Owner owner=(Owner)session.getAttribute("owner");
				if(owner==null)
				{
					return "redirect:/showOwnerPage";
				}
				else
				{
					return "roomReg";
				}
			}	
		//save house
				@RequestMapping(value = "/saveEditedHouse",method = RequestMethod.POST)
				public String saveEditedHouse(@RequestParam("houseId")Long hId,@RequestParam("accommodationType")String accommodationType,@RequestParam("ownerId")Long oId,@RequestParam("tenancyType")String tenancyType,@RequestParam("room")Integer room,@RequestParam("pincode")Long pincode,@RequestParam("city")String city,@RequestParam("subcategory1")String state,@RequestParam("rent")Double rent,@RequestParam("area")Double area,@RequestParam("img1")MultipartFile img1,@RequestParam("img2")MultipartFile img2,@RequestParam("houseName")String houseName,@RequestParam("floorNumber")Integer floorNumber,
				@RequestParam("address")String address,@RequestParam("subcategory")String locationArea,@RequestParam("subcategory2")String country,@RequestParam("deposit")Double deposit,@RequestParam("foodPreference")String foodPreference,@RequestParam("img3")MultipartFile img3,Model model,HttpSession session,@RequestParam("latitude")Double latitude,@RequestParam("longitude")Double longitude) throws IOException,SerialException
				{
					House house=new House();
					Owner owner=new Owner();
					owner.setoId(oId);
					house.sethId(hId);
					house.setAccommodationType(accommodationType);
					house.setOwner(owner);	
					house.setAddress(address);
					house.setArea(area);
					house.setDeposit(deposit);
					house.setFloorNumber(floorNumber);
					house.setFoodPreference(foodPreference);
					house.setRent(rent);
					house.setRoom(room);
					house.setPincode(pincode);
					house.setState(state);
					house.setCity(city);
					house.setCountry(country);
					house.setLocationArea(locationArea);
					house.setTenancyType(tenancyType);
					byte[] img11 = img1.getBytes();
					byte[] img13 = img2.getBytes();
					byte[] img12 = img3.getBytes();
					house.setImg1(img11);
					house.setImg2(img12);
					house.setImg3(img13);
					house.setLatitude(latitude);
					house.setLongitude(longitude);
					house.setHouseName(houseName);
					houseService.updateHouse(house);			
						return "success";			
				}				
	//show house info
	@RequestMapping(value = "/showHouseInfo/{hId}")
	public String showHouseInfo(@PathVariable("hId") Long hId,Model model,HttpSession session)
	{
		User user=(User)session.getAttribute("user");
		if(user!=null)
		{
			House house=new House();
			house.sethId(hId);
			house=houseService.getHouse(house);
			model.addAttribute("house",house);
			return "houseInfo";
		}
		else
		{
			return "redirect:/showFilter";
		}		
	}	
			//filter page response with filter
			@RequestMapping(value="/showFilter3")
			public String listHouseByFilters(@RequestParam("food") String food,Model model, Integer offset, Integer maxResults,HttpSession session){
				House house1=new House();
				house1.setFoodPreference(food);
				model.addAttribute("house", houseService.listHouseByFilters(house1,offset, maxResults));
				model.addAttribute("count", houseService.count());
				model.addAttribute("offset", offset);
				model.addAttribute("url", "showFilter2");
				return "filter";
			}
			//sort record
			@RequestMapping(value="/sortPrice")
			public String Sortist(@RequestParam("priceSort")String priceSort,Model model, Integer offset, Integer maxResults){
				model.addAttribute("house", houseService.list(offset, maxResults));	
				model.addAttribute("count", houseService.count());
				model.addAttribute("offset", offset);
					return "filter";
			}	
			//show payment page
			@RequestMapping(value = "/showHouseInfo/showPaymentPage")
			public String showPaymentPage1()
			{
				return "redirect:/showPaymentPage";
				
			}
			@RequestMapping(value = "showPaymentPage")
			public String showPaymentPage()
			{
				return "payment";
				
			}	
			//show filter based on requirements
			@RequestMapping(value = "/showFilter111")
			public String showFilter(@RequestParam("profession") String profession,@RequestParam("language") String language,@RequestParam("address") String address,
			@RequestParam("accomodation") String accomodation,@RequestParam("food") String food,@RequestParam("price") String price[])
			{
				return "filter";
			}				
			//filter page response with only address
			@RequestMapping(value="/showFilterLongTerm")
			public String showFilterLongTerm(@RequestParam("address") String address,Model model, Integer offset, Integer maxResults,HttpSession session){
				House house=new House();
				house.setAccommodationType("longTerm");
				String priceSort="lowTohigh";
				session.setAttribute("address", address);
				if(address.isEmpty())
				{
					house.setAddress("pune");
				}
				else
				{
					house.setAddress(address);
				}
				model.addAttribute("sort", "showLongTermSorting");
				model.addAttribute("house", houseService.listHouseByAddressLongTerm(house,offset, maxResults,priceSort));
				//model.addAttribute("count", houseService.countHouseByAddressLongTerm(house));
				model.addAttribute("offset", offset);
				model.addAttribute("url", "showFilterLongTerm");
				model.addAttribute("address", address);
				
				return "filter";
			}
			@RequestMapping(value="/showFilterShortTerm")
			public String showFilterShortTerm(@RequestParam("address") String address,@RequestParam("checkIn")@DateTimeFormat(pattern="yyyy-MM-dd") Date checkIn,@RequestParam("checkOut")@DateTimeFormat(pattern="yyyy-MM-dd") Date checkOut,Model model, Integer offset, Integer maxResults,HttpSession session){
				System.out.println(checkIn);
				System.out.println(checkOut);
				House house=new House();
				house.setAccommodationType("shortTerm");
				String priceSort="lowTohigh";
				session.setAttribute("address", address);
				if(address.isEmpty())
				{
					house.setAddress("pune");
				}
				else
				{
					house.setAddress(address);
				}
				model.addAttribute("house", houseService.listHouseByAddressShortTerm(house, offset, maxResults,priceSort));
				model.addAttribute("count", houseService.countHouseByAddressLongTerm(house));
				model.addAttribute("offset", offset);
				model.addAttribute("url", "showFilterShortTerm");
				model.addAttribute("address", address);
				model.addAttribute("sort", "showShortTermSorting");
				return "filter";
			}		
			//show filter with results 
			@RequestMapping(value="/showFilter")
			public String list(Model model, Integer offset, Integer maxResults){ 
				System.out.println(offset);
				System.out.println(maxResults);
				model.addAttribute("house", houseService.list(offset, maxResults));
				model.addAttribute("count", houseService.count());
				model.addAttribute("offset", offset);
				model.addAttribute("url", "showFilter");
				return "filter";
			}
			//filter page response with filter
			@RequestMapping(value="/mainFilter")
			public String listHouseByMainFilter(@RequestParam("profession") String profession,@RequestParam("motherTongue") String motherTongue,@RequestParam("subcategory") String locationArea,@RequestParam("foodPreference") String foodPreference,@RequestParam("rent") Double rent,Model model, Integer offset, Integer maxResults,HttpSession session){
				session.setAttribute("profession", profession);
				session.setAttribute("motherTongue", motherTongue);
				session.setAttribute("locationArea", locationArea);
				session.setAttribute("foodPreference", foodPreference);
				session.setAttribute("rent", rent);	
				String priceSort="lowTohigh";
				House house=new House();
				User user=new User();
				user.setProfession(profession);
				user.setMotherTongue(motherTongue);
				user.setFoodPreference(foodPreference);
				house.setLocationArea(locationArea);
				house.setRent(rent);
				model.addAttribute("sort", "showMainFilterSorting");
				model.addAttribute("house", houseService.listHouseByMainFilter(house,user,offset, maxResults,priceSort));
				//model.addAttribute("count", houseService.listHouseByMainFilterCount(house,user));
				model.addAttribute("offset", offset);
				model.addAttribute("url", "mainFilter");
				
				return "filter";
			}
			//showFilterWithFacilities
			@RequestMapping(value="/showFilterWithFacilities")
			public String showFilterWithFacilities(@RequestParam("facilities") String[] facilities,Model model,Integer offset, Integer maxResults,HttpSession session)
			{
				String profession=(String)session.getAttribute("profession");
				String motherTongue=(String)session.getAttribute("motherTongue");
				String foodPreference=(String)session.getAttribute("foodPreference");
				String locationArea=(String)session.getAttribute("locationArea");
				Double rent=(Double)session.getAttribute("rent");			
				session.setAttribute("facilities", facilities);	
				String priceSort="lowTohigh";
				User user=new User();
				user.setProfession(profession);
				user.setMotherTongue(motherTongue);
				user.setFoodPreference(foodPreference);
				House house=new House();
				house.setRent(rent);
				house.setLocationArea(locationArea);
				model.addAttribute("sort", "showFacilitiesSorting");
				model.addAttribute("house", houseService.listHouseByadvancedFilter(house,user,facilities,maxResults,offset,priceSort));
				//model.addAttribute("count",houseService.listHouseByadvancedFilterCount(house,user,facilities));
				model.addAttribute("offset", offset);
				model.addAttribute("url", "showFilterWithFacilities");
				return "filter";
			}	
}
