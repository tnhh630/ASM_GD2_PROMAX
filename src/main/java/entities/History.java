package entities;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the history database table.
 * 
 */
@Entity
@Table(name="history")
@NamedQuery(name="History.findAll", query="SELECT h FROM History h")
public class History implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private boolean isLiked;

	private Timestamp likedDate;
	
	@CreationTimestamp //entity đẩy vào database new 1 cái record để lấy thời gian hệ thống
	private Timestamp viewedDate;

	//bi-directional many-to-one association to User
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="userId", referencedColumnName = "id")
//	@JsonIgnoreProperties(value = {"application","hibernateLazyInitializer"})
	@JsonIgnore
	private User user;

	//bi-directional many-to-one association to Video
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="videoId", referencedColumnName = "id")
//	@JsonIgnoreProperties(value = {"application","hibernateLazyInitializer"})
	@JsonIgnore
	private Video video;

	public History() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getIsLiked() {
		return this.isLiked;
	}

	public void setIsLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	public Timestamp getlikedDate() {
		return this.likedDate;
	}

	public void setlikedDate(Timestamp likedDate) {
		this.likedDate = likedDate;
	}

	public Timestamp getViewedDate() {
		return this.viewedDate;
	}

	public void setViewedDate(Timestamp viewedDate) {
		this.viewedDate = viewedDate;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Video getVideo() {
		return this.video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

}