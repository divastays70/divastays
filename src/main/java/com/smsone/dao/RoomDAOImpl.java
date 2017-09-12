package com.smsone.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smsone.model.Beds;
import com.smsone.model.House;
import com.smsone.model.Room;
import com.smsone.model.User;
@Repository
public class RoomDAOImpl implements RoomDAO {
	@Autowired
	private SessionFactory sessionFactory;
	//save room
	public void saveRoom(Room room,Long hId) {
		Session session=sessionFactory.openSession();
		House house=(House)session.load(House.class,hId);
		room.setHouse(house);
		session.save(room);
		for(int i=1;i<=room.getNumberOfBed();i++)
		{
			Beds beds=new Beds();
			beds.setBedId(new Long(i));
			Room room1=(Room)session.load(Room.class,room.getrId());
			beds.setRoom(room1);
			session.save(beds);
		}
		session.close();
	}
	public void assignBed(User user, Beds beds) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		User user1=(User)session.get(User.class,user.getuId());
		Beds beds1=(Beds)session.get(Beds.class,beds.getbId());
		Room room=beds1.getRoom();
		House house=room.getHouse();
		user1.setHouse(house);
		session.save(user1);
		beds1.setUser(user1);
		session.save(beds1);
		tx.commit();
		session.close();
	}
	@SuppressWarnings("unchecked")
	public List<Beds> getAllBedDetails(Long rId) {
		Session session=sessionFactory.openSession();	
		//Query query=session.createQuery("select beds.bId from Beds beds where beds.room=:rId"); 
		//query.setLong("rId", rId);
		Criteria crit = session.createCriteria(Beds.class);
	    ProjectionList projList = Projections.projectionList();
	    projList.add(Projections.property("Id"));
	    projList.add(Projections.property("name"));
	    crit.setProjection(projList);
		List<Object[]> beds = crit.list();
		
		return null;
	}
	public List<Room> getAllRoomDetails(Long hId) {
		Session session=sessionFactory.openSession();
		House house=(House)session.load(House.class,hId);
		List<Room> room=house.getRooms();
		if(room!=null)
		{
		return room;
		}
		else
		{
			return null;
		}
	}
	
	public Long countRooms(Long hId) {
		Session session=sessionFactory.openSession();
		House house=(House)session.load(House.class, hId);
		List<Room> room=house.getRooms();
		int count=room.size();
		Long c=new Long(count);
		session.close();
		return c;
	}
	public Long countBeds(Long rId) {
		Session session=sessionFactory.openSession();
		Room room=(Room)session.load(Room.class, rId);
		List<Beds> beds=room.getBeds();
		int count=beds.size();
		Long bedCount=new Long(count);
		session.close();
		return bedCount;
	}
	@SuppressWarnings("unchecked")
	public List<User> getUsers(List<Beds> beds) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Beds beds where beds in(:beds)"); 
		query.setParameterList("beds",beds);
		List<Beds> beds1=query.list();
		System.out.println(beds1);
		return null;
	}
	public Room getRoom(Long rId) {
		Session session=sessionFactory.openSession();
		Room room=(Room)session.load(Room.class,rId);
		return room;
	}	
}
