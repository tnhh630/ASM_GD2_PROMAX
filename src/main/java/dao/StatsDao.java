package dao;

import java.util.List;

import dto.VideoLikedInfo;

public interface StatsDao {
	
	List<VideoLikedInfo> findVideoLikedInfo();
	
}
