package co.rcbike.desplazamientos.model.openweather;

import java.util.ArrayList;

/**
 * Created by rohit on 10/15/15.
 */
public class WeatherData {

	private Integer dt;
	private Main main;
	private java.util.List<Weather> weather = new ArrayList<Weather>();
	private Clouds clouds;
	private Wind wind;
	private Sys sys;
	private String base;
	private Long id;
	private String name;
	private Integer cod;

	public Integer getDt() {
		return dt;
	}

	public void setDt(Integer dt) {
		this.dt = dt;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public java.util.List<Weather> getWeather() {
		return weather;
	}

	public void setWeather(java.util.List<Weather> weather) {
		this.weather = weather;
	}

	public Clouds getClouds() {
		return clouds;
	}

	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

}