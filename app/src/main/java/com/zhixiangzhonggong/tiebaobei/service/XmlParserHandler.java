package com.zhixiangzhonggong.tiebaobei.service;



import com.zhixiangzhonggong.tiebaobei.model.CarStateModel;
import com.zhixiangzhonggong.tiebaobei.model.CarUsingPurposeModel;
import com.zhixiangzhonggong.tiebaobei.model.CityModel;
import com.zhixiangzhonggong.tiebaobei.model.DistrictModel;
import com.zhixiangzhonggong.tiebaobei.model.ExcavatorBrandModel;
import com.zhixiangzhonggong.tiebaobei.model.ExcavatorModelModel;
import com.zhixiangzhonggong.tiebaobei.model.MonthModel;
import com.zhixiangzhonggong.tiebaobei.model.ProvinceModel;
import com.zhixiangzhonggong.tiebaobei.model.YearModel;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XmlParserHandler extends DefaultHandler {


	private List<ProvinceModel> provinceList = new ArrayList<ProvinceModel>();
	private List<ExcavatorBrandModel> excavatorBrandModelList=new ArrayList<>()	 ;
	private List<YearModel> yearModelList=new ArrayList<>()	 ;
	private List<CarStateModel> carStateModelList=new ArrayList<>();
	private List<CarUsingPurposeModel> carUsingPurposeModelList=new ArrayList<>();
	public XmlParserHandler() {
		
	}

	public List<CarStateModel> getCarStateModels() {
		return carStateModelList;
	}

	public List<CarUsingPurposeModel> getCarUsingPurposeModels() {
		return carUsingPurposeModelList;
	}

	public List<ExcavatorBrandModel> getExcavatorBrandModelList() {
		return excavatorBrandModelList;
	}

	public List<ProvinceModel> getDataList() {
		return provinceList;
	}

	public List<YearModel> getYearModelList() {
		return yearModelList;
	}

	@Override
	public void startDocument() throws SAXException {

	}
	ExcavatorBrandModel excavatorBrandModel= new ExcavatorBrandModel();
	ExcavatorModelModel excavatorModelModel=new ExcavatorModelModel();
	ProvinceModel provinceModel = new ProvinceModel();
	CityModel cityModel = new CityModel();
	DistrictModel districtModel = new DistrictModel();
	YearModel yearModel=new YearModel();
	MonthModel monthModel=new MonthModel();
	CarStateModel carStateModel=new CarStateModel();
	CarUsingPurposeModel carUsingPurposeModel=new CarUsingPurposeModel();

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (qName.equals("province")) {
			provinceModel = new ProvinceModel();
			provinceModel.setName(attributes.getValue(0));
			provinceModel.setCityList(new ArrayList<CityModel>());
		} else if (qName.equals("city")) {
			cityModel = new CityModel();
			cityModel.setName(attributes.getValue(0));
			cityModel.setDistrictList(new ArrayList<DistrictModel>());
		} else if (qName.equals("district")) {
			districtModel = new DistrictModel();
			districtModel.setName(attributes.getValue(0));
			districtModel.setZipcode(attributes.getValue(1));
		}
		else if (qName.equals("excavatorBrand")){
			excavatorBrandModel= new ExcavatorBrandModel();
			excavatorBrandModel.setName(attributes.getValue(0));
			excavatorBrandModel.setExcavatorModelList(new ArrayList<ExcavatorModelModel>());
		}
		else if (qName.equals("excavatorModel")){

			excavatorModelModel=new ExcavatorModelModel();
			excavatorModelModel.setName(attributes.getValue(0));
		}
		else if (qName.equals("year")){
			yearModel=new YearModel();
			yearModel.setName(attributes.getValue(0));
			yearModel.setMonthList(new ArrayList<MonthModel>());
		}
		else if (qName.equals("month")){
			monthModel=new MonthModel();
			monthModel.setName(attributes.getValue(0));
		}
		else if (qName.equals("carState")){
			carStateModel=new CarStateModel();
			carStateModel.setName(attributes.getValue(0));
		}
		else if (qName.equals("carPurpose")){
			carUsingPurposeModel=new CarUsingPurposeModel();
			carUsingPurposeModel.setName(attributes.getValue(0));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (qName.equals("district")) {
			cityModel.getDistrictList().add(districtModel);
        } else if (qName.equals("city")) {
        	provinceModel.getCityList().add(cityModel);
        } else if (qName.equals("province")) {
        	provinceList.add(provinceModel);
        }
		else if (qName.equals("excavatorBrand")){
			excavatorBrandModelList.add(excavatorBrandModel);
		}
		else if (qName.equals("excavatorModel")){
			excavatorBrandModel.getExcavatorModelList().add(excavatorModelModel);
		}
		else if (qName.equals("year")){
			yearModelList.add(yearModel);
		}
		else if (qName.equals("month")){
			yearModel.getMonthList().add(monthModel);
		}
		else if (qName.equals("carState")){
			carStateModelList.add(carStateModel);
		}
		else if (qName.equals("carPurpose")){
			carUsingPurposeModelList.add(carUsingPurposeModel);
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
	}

}
