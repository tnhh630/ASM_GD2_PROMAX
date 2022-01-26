package dao.impl;

import java.util.List;

import dao.AbstractDao;
import dao.VideoDao;
import entities.Video;

public class VideoDaoImpl extends AbstractDao<Video> implements VideoDao {

	@Override
	public Video findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findbyId(Video.class, id);
	}

	@Override
	public Video findByHref(String href) {
		// TODO Auto-generated method stub
		String sql = "SELECT o FROM Video o WHERE o.href = ?0";
		return super.findOne(Video.class, sql, href);
	}

	@Override
	public List<Video> findAll() {
		// TODO Auto-generated method stub
		return super.findAll(Video.class, true);
	}

	@Override
	public List<Video> findAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return super.findAll(Video.class, true, pageNumber, pageSize);
	}
	
}
