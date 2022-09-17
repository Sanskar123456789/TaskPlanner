package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.History;


@Service
public class HDAO {
	
	@Autowired HistoryRepo obj;
	
	public History update(History h) {
		History uh = new History();
		uh.setId(h.getId());
		uh.setCreatedAt(h.getCreatedAt());
		uh.setLastUpdated(h.getLastUpdated());
		uh.setTaskHistory(h.getTaskHistory());
		uh.setTaskHistory(h.getTaskHistory());
		uh.setTaskid(h.getTaskid());
		System.out.println("HELlo");
		obj.delete(uh);
		return obj.save(uh);
	}
}
