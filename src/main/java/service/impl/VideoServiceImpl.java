package service.impl;

import java.util.List;

import dao.VideoDao;
import dao.impl.VideoDaoImpl;
import entities.Video;
import service.VideoService;

public class VideoServiceImpl implements VideoService{

	private VideoDao dao;
	
	public VideoServiceImpl() {
		dao = new VideoDaoImpl();
	}
	@Override
	public Video findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public Video findByHref(String href) {
		// TODO Auto-generated method stub
		return dao.findByHref(href);
	}

	@Override
	public List<Video> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<Video> findAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return dao.findAll(pageNumber, pageSize);
	}

	@Override
	public Video create(Video entity) {
		// TODO Auto-generated method stub
		entity.setIsActive(Boolean.TRUE);
		entity.setViews(0);
		entity.setShares(0);
		return dao.create(entity);
	}

	@Override
	public Video update(Video entity) {
		// TODO Auto-generated method stub
		return dao.update(entity);
	}

	@Override
	public Video delete(String href) {
		// TODO Auto-generated method stub
		Video entity = findByHref(href);
		entity.setIsActive(false);
		return dao.update(entity);
	}

}
