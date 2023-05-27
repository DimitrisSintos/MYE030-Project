package mye030.countries.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="countries")
public class Country {
   

   @Column(name = "iso")
   private String iso; 
   @Column(name = "iso3")
   private String iso3; 
   @Id
   @Column(name = "iso_code")
   private int iso_code;
   @Column(name = "display_name")
   private String display_name; 
   @Column(name = "official_name")
   private String official_name; 
   @Column(name = "capital")
   private String capital; 
   @Column(name = "continent")
   private String continent; 
   @Column(name = "currencycode")
   private String currencycode; 
   @Column(name = "currencyname")
   private String currencyname; 
   @Column(name = "phone")
   private String phone; 
   @Column(name = "region_code")
   private String region_code; 
   @Column(name = "region_name")
   private String region_name; 
   @Column(name = "subregion_code")
   private String subregion_code; 
   @Column(name = "subregion_name")
   private String subregion_name; 
   @Column(name = "intermediate_region_code")
   private String intermediate_region_code; 
   @Column(name = "intermediate_region_name")
   private String intermediate_region_name; 
   @Column(name = "status")
   private String status; 
   @Column(name = "developed_or_developing")
   private String developed_or_developing; 
   @Column(name = "small_island_developing_states")
   private Boolean small_island_developing_states; 
   @Column(name = "land_locked_developing_countries")
   private Boolean land_locked_developing_countries; 
   @Column(name = "least_developed_countries")
   private Boolean least_developed_countries;
   @Column(name = "area_sqkm")
   private Double area_sqkm;
   @Column(name = "population")
   private Double population;
   public String getIso() {
      return iso;
   }
   public void setIso(String iso) {
      this.iso = iso;
   }
   public String getIso3() {
      return iso3;
   }
   public void setIso3(String iso3) {
      this.iso3 = iso3;
   }
   public int getIso_code() {
      return iso_code;
   }
   public void setIso_code(int iso_code) {
      this.iso_code = iso_code;
   }
   public String getDisplay_name() {
      return display_name;
   }
   public void setDisplay_name(String display_name) {
      this.display_name = display_name;
   }
   public String getOfficial_name() {
      return official_name;
   }
   public void setOfficial_name(String official_name) {
      this.official_name = official_name;
   }
   public String getCapital() {
      return capital;
   }
   public void setCapital(String capital) {
      this.capital = capital;
   }
   public String getContinent() {
      return continent;
   }
   public void setContinent(String continent) {
      this.continent = continent;
   }
   public String getCurrencycode() {
      return currencycode;
   }
   public void setCurrencycode(String currencycode) {
      this.currencycode = currencycode;
   }
   public String getCurrencyname() {
      return currencyname;
   }
   public void setCurrencyname(String currencyname) {
      this.currencyname = currencyname;
   }
   public String getPhone() {
      return phone;
   }
   public void setPhone(String phone) {
      this.phone = phone;
   }
   public String getRegion_code() {
      return region_code;
   }
   public void setRegion_code(String region_code) {
      this.region_code = region_code;
   }
   public String getRegion_name() {
      return region_name;
   }
   public void setRegion_name(String region_name) {
      this.region_name = region_name;
   }
   public String getSubregion_code() {
      return subregion_code;
   }
   public void setSubregion_code(String subregion_code) {
      this.subregion_code = subregion_code;
   }
   public String getSubregion_name() {
      return subregion_name;
   }
   public void setSubregion_name(String subregion_name) {
      this.subregion_name = subregion_name;
   }
   public String getIntermediate_region_code() {
      return intermediate_region_code;
   }
   public void setIntermediate_region_code(String intermediate_region_code) {
      this.intermediate_region_code = intermediate_region_code;
   }
   public String getIntermediate_region_name() {
      return intermediate_region_name;
   }
   public void setIntermediate_region_name(String intermediate_region_name) {
      this.intermediate_region_name = intermediate_region_name;
   }
   public String getStatus() {
      return status;
   }
   public void setStatus(String status) {
      this.status = status;
   }
   public String getDeveloped_or_developing() {
      return developed_or_developing;
   }
   public void setDeveloped_or_developing(String developed_or_developing) {
      this.developed_or_developing = developed_or_developing;
   }
   public Boolean getSmall_island_developing_states() {
      return small_island_developing_states;
   }
   public void setSmall_island_developing_states(Boolean small_island_developing_states) {
      this.small_island_developing_states = small_island_developing_states;
   }
   public Boolean getLand_locked_developing_countries() {
      return land_locked_developing_countries;
   }
   public void setLand_locked_developing_countries(Boolean land_locked_developing_countries) {
      this.land_locked_developing_countries = land_locked_developing_countries;
   }
   public Boolean getLeast_developed_countries() {
      return least_developed_countries;
   }
   public void setLeast_developed_countries(Boolean least_developed_countries) {
      this.least_developed_countries = least_developed_countries;
   }
   public Double getArea_sqkm() {
      return area_sqkm;
   }
   public void setArea_sqkm(Double area_sqkm) {
      this.area_sqkm = area_sqkm;
   }
   public Double getPopulation() {
      return population;
   }
   public void setPopulation(Double population) {
      this.population = population;
   }

   public String toString() {
      return this.display_name;

   }

}
