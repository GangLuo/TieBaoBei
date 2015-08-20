package com.zhixiangzhonggong.tiebaobei.service;



import com.zhixiangzhonggong.tiebaobei.model.CityModel;
import com.zhixiangzhonggong.tiebaobei.model.DistrictModel;
import com.zhixiangzhonggong.tiebaobei.model.ExcavatorBrandModel;
import com.zhixiangzhonggong.tiebaobei.model.ExcavatorModelModel;
import com.zhixiangzhonggong.tiebaobei.model.ProvinceModel;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XmlParserHandler extends DefaultHandler {


	private List<ProvinceModel> provinceList = new ArrayList<ProvinceModel>();
	private List<ExcavatorBrandModel> excavatorBrandModelList=new ArrayList<>()	 ;
	public XmlParserHandler() {
		
	}

	public List<ExcavatorBrandModel> getExcavatorBrandModelList() {
		return excavatorBrandModelList;
	}

	public List<ProvinceModel> getDataList() {
		return provinceList;
	}

	@Override
	public void startDocument() throws SAXException {

	}
	ExcavatorBrandModel excavatorBrandModel= new ExcavatorBrandModel();
	ExcavatorModelModel excavatorModelModel=new ExcavatorModelModel();
	ProvinceModel provinceModel = new ProvinceModel();
	CityModel cityModel = new CityModel();
	DistrictModel districtModel = new DistrictModel();
	
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
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
	}

}
