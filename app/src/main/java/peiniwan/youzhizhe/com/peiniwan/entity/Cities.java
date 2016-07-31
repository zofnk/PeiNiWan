package peiniwan.youzhizhe.com.peiniwan.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Cities {

	@Expose
	private List<City> hotcity = new ArrayList<City>();
	@Expose
	private List<City> allcity = new ArrayList<City>();

	/**
	 * 
	 * @return The hotcity
	 */
	public List<City> getHotcity() {
		return hotcity;
	}

	/**
	 * 
	 * @param hotcity
	 *            The hotcity
	 */
	public void setHotcity(List<City> hotcity) {
		this.hotcity = hotcity;
	}

	/**
	 * 
	 * @return The allcity
	 */
	public List<City> getAllcity() {
		return allcity;
	}

	/**
	 * 
	 * @param allcity
	 *            The allcity
	 */
	public void setAllcity(List<City> allcity) {
		this.allcity = allcity;
	}

}
