package com.smsone.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class House implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long hId;
	@Column(nullable=false,length=20)
	private Integer floorNumber;
	@Column(nullable=false,length=20)
	private Double rent;
	@Column(nullable=false,length=20)
	private Double area;
	@Column(nullable=false,length=20)
	private String tenancyType;
	@Column(nullable=false,length=20)
	private Integer room;
	@Column(nullable=false,length=20)
	private String foodPreference;
	@Column(nullable=false,length=20)
	private Double deposit;
	@Column(nullable=false,length=100)
	private String houseName;
	@Column(nullable=false,length=100)
	private String state;
	@Column(nullable=false,length=100)
	private String locationArea;
	@Column(nullable=false,length=100)
	private String country;
	@Column(nullable=false,length=100)
	private String city;
	@Column(nullable=false,length=20)
	private String address;
	@Column(name="img1",columnDefinition = "LONGBLOB")
	private byte[] img1;
	@Column(name="img2",columnDefinition = "LONGBLOB")
	private byte[] img2;
	@Column(name="img3",columnDefinition = "LONGBLOB")
	private byte[] img3;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="house")
	private Set<User> user;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="house")
	private Set<Room> rooms;
	@ManyToOne
    @JoinColumn(name = "oId")
	private Owner owner;
	public Long gethId() {
		return hId;
	}
	public void sethId(Long hId) {
		this.hId = hId;
	}
	public Integer getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(Integer floorNumber) {
		this.floorNumber = floorNumber;
	}
	public Double getRent() {
		return rent;
	}
	public void setRent(Double rent) {
		this.rent = rent;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public String getTenancyType() {
		return tenancyType;
	}
	public void setTenancyType(String tenancyType) {
		this.tenancyType = tenancyType;
	}
	public Integer getRoom() {
		return room;
	}
	public void setRoom(Integer room) {
		this.room = room;
	}
	public String getFoodPreference() {
		return foodPreference;
	}
	public void setFoodPreference(String foodPreference) {
		this.foodPreference = foodPreference;
	}
	public Double getDeposit() {
		return deposit;
	}
	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLocationArea() {
		return locationArea;
	}
	public void setLocationArea(String locationArea) {
		this.locationArea = locationArea;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public byte[] getImg1() {
		return img1;
	}
	public void setImg1(byte[] img1) {
		this.img1 = img1;
	}
	public byte[] getImg2() {
		return img2;
	}
	public void setImg2(byte[] img2) {
		this.img2 = img2;
	}
	public byte[] getImg3() {
		return img3;
	}
	public void setImg3(byte[] img3) {
		this.img3 = img3;
	}
	public Set<User> getUser() {
		return user;
	}
	public void setUser(Set<User> user) {
		this.user = user;
	}
	public Set<Room> getRooms() {
		return rooms;
	}
	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public House() {
		super();
	}
	public House(Long hId, Integer floorNumber, Double rent, Double area, String tenancyType, Integer room,
			String foodPreference, Double deposit, String houseName, String state, String locationArea, String country,
			String city, String address, byte[] img1, byte[] img2, byte[] img3, Set<User> user, Set<Room> rooms,
			Owner owner) {
		super();
		this.hId = hId;
		this.floorNumber = floorNumber;
		this.rent = rent;
		this.area = area;
		this.tenancyType = tenancyType;
		this.room = room;
		this.foodPreference = foodPreference;
		this.deposit = deposit;
		this.houseName = houseName;
		this.state = state;
		this.locationArea = locationArea;
		this.country = country;
		this.city = city;
		this.address = address;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.user = user;
		this.rooms = rooms;
		this.owner = owner;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((deposit == null) ? 0 : deposit.hashCode());
		result = prime * result + ((floorNumber == null) ? 0 : floorNumber.hashCode());
		result = prime * result + ((foodPreference == null) ? 0 : foodPreference.hashCode());
		result = prime * result + ((hId == null) ? 0 : hId.hashCode());
		result = prime * result + ((houseName == null) ? 0 : houseName.hashCode());
		result = prime * result + Arrays.hashCode(img1);
		result = prime * result + Arrays.hashCode(img2);
		result = prime * result + Arrays.hashCode(img3);
		result = prime * result + ((locationArea == null) ? 0 : locationArea.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((rent == null) ? 0 : rent.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + ((rooms == null) ? 0 : rooms.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((tenancyType == null) ? 0 : tenancyType.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		House other = (House) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (deposit == null) {
			if (other.deposit != null)
				return false;
		} else if (!deposit.equals(other.deposit))
			return false;
		if (floorNumber == null) {
			if (other.floorNumber != null)
				return false;
		} else if (!floorNumber.equals(other.floorNumber))
			return false;
		if (foodPreference == null) {
			if (other.foodPreference != null)
				return false;
		} else if (!foodPreference.equals(other.foodPreference))
			return false;
		if (hId == null) {
			if (other.hId != null)
				return false;
		} else if (!hId.equals(other.hId))
			return false;
		if (houseName == null) {
			if (other.houseName != null)
				return false;
		} else if (!houseName.equals(other.houseName))
			return false;
		if (!Arrays.equals(img1, other.img1))
			return false;
		if (!Arrays.equals(img2, other.img2))
			return false;
		if (!Arrays.equals(img3, other.img3))
			return false;
		if (locationArea == null) {
			if (other.locationArea != null)
				return false;
		} else if (!locationArea.equals(other.locationArea))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (rent == null) {
			if (other.rent != null)
				return false;
		} else if (!rent.equals(other.rent))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (rooms == null) {
			if (other.rooms != null)
				return false;
		} else if (!rooms.equals(other.rooms))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (tenancyType == null) {
			if (other.tenancyType != null)
				return false;
		} else if (!tenancyType.equals(other.tenancyType))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "House [hId=" + hId + ", floorNumber=" + floorNumber + ", rent=" + rent + ", area=" + area
				+ ", tenancyType=" + tenancyType + ", room=" + room + ", foodPreference=" + foodPreference
				+ ", deposit=" + deposit + ", houseName=" + houseName + ", state=" + state + ", locationArea="
				+ locationArea + ", country=" + country + ", city=" + city + ", address=" + address + ", img1="
				+ Arrays.toString(img1) + ", img2=" + Arrays.toString(img2) + ", img3=" + Arrays.toString(img3)
				+ ", user=" + user + ", rooms=" + rooms + ", owner=" + owner + "]";
	}
	
	
	
}