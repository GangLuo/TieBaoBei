package com.zhixiangzhonggong.tiebaobei.Activity;

import android.app.Activity;
import android.content.res.AssetManager;


import com.zhixiangzhonggong.tiebaobei.model.CityModel;
import com.zhixiangzhonggong.tiebaobei.model.DistrictModel;
import com.zhixiangzhonggong.tiebaobei.model.ExcavatorBrandModel;
import com.zhixiangzhonggong.tiebaobei.model.ExcavatorModelModel;
import com.zhixiangzhonggong.tiebaobei.model.ProvinceModel;
import com.zhixiangzhonggong.tiebaobei.service.XmlParserHandler;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class BaseActivity extends Activity {
	
	/**
	 * ����ʡ
	 */
	protected String[] mProvinceDatas;
	protected String[] mExcavatorDatas;
	/**
	 * key - ʡ value - ��
	 */
	protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();

	protected Map<String, String[]> mExcavatorModelDatasMap = new HashMap<String, String[]>();
	/**
	 * key - �� values - ��
	 */
	protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();
	
	/**
	 * key - �� values - �ʱ�
	 */
	protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();

	/**
	 * ��ǰʡ�����
	 */
	protected String mCurrentProviceName;
	/**
	 * ��ǰ�е����
	 */
	protected String mCurentExcavatorName;
	protected String mCurrentExcavatorModelName;
	protected String mCurrentCityName;

	/**
	 * ��ǰ������
	 */
	protected String mCurrentDistrictName ="";
	
	/**
	 * ��ǰ�����������
	 */
	protected String mCurrentZipCode ="";
	
	/**
	 * ����ʡ�����XML���
	 */
	
    protected void initProvinceDatas()
	{
		List<ProvinceModel> provinceList = null;
    	AssetManager asset = getAssets();
        try {
            InputStream input = asset.open("province_data.xml");

			SAXParserFactory spf = SAXParserFactory.newInstance();

			SAXParser parser = spf.newSAXParser();
			XmlParserHandler handler = new XmlParserHandler();
			parser.parse(input, handler);
			input.close();

			provinceList = handler.getDataList();

			if (provinceList!= null && !provinceList.isEmpty()) {
				mCurrentProviceName = provinceList.get(0).getName();
				List<CityModel> cityList = provinceList.get(0).getCityList();
				if (cityList!= null && !cityList.isEmpty()) {
					mCurrentCityName = cityList.get(0).getName();
					List<DistrictModel> districtList = cityList.get(0).getDistrictList();
					mCurrentDistrictName = districtList.get(0).getName();
					mCurrentZipCode = districtList.get(0).getZipcode();
				}
			}
			//*/
			mProvinceDatas = new String[provinceList.size()];
        	for (int i=0; i< provinceList.size(); i++) {

        		mProvinceDatas[i] = provinceList.get(i).getName();
        		List<CityModel> cityList = provinceList.get(i).getCityList();
        		String[] cityNames = new String[cityList.size()];
        		for (int j=0; j< cityList.size(); j++) {

        			cityNames[j] = cityList.get(j).getName();
        			List<DistrictModel> districtList = cityList.get(j).getDistrictList();
        			String[] distrinctNameArray = new String[districtList.size()];
        			DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
        			for (int k=0; k<districtList.size(); k++) {

        				DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());

        				mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
        				distrinctArray[k] = districtModel;
        				distrinctNameArray[k] = districtModel.getName();
        			}

        			mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
        		}

        		mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
        	}
        } catch (Throwable e) {
            e.printStackTrace();  
        } finally {
        	
        } 
	}

	protected void initExcavatorBrandDatas()
	{
		List<ExcavatorBrandModel> excavatorBrandModelList = null;
		AssetManager asset = getAssets();
		try {
			InputStream input = asset.open("province_data.xml");

			SAXParserFactory spf = SAXParserFactory.newInstance();

			SAXParser parser = spf.newSAXParser();
			XmlParserHandler handler = new XmlParserHandler();
			parser.parse(input, handler);
			input.close();

			excavatorBrandModelList = handler.getExcavatorBrandModelList();

			if (excavatorBrandModelList!= null && !excavatorBrandModelList.isEmpty()) {
				mCurentExcavatorName = excavatorBrandModelList.get(0).getName();
				List<ExcavatorModelModel> excavatorModelList = excavatorBrandModelList.get(0).getExcavatorModelList();
				if (excavatorModelList!= null && !excavatorModelList.isEmpty()) {
					mCurrentExcavatorModelName = excavatorModelList.get(0).getName();

				}

			}
			//*/
			mExcavatorDatas = new String[excavatorBrandModelList.size()];
			for (int i=0; i< excavatorBrandModelList.size(); i++) {

				mExcavatorDatas[i] = excavatorBrandModelList.get(i).getName();
				List<ExcavatorModelModel> excavatorModelList = excavatorBrandModelList.get(i).getExcavatorModelList();
				String[] modelNames = new String[excavatorModelList.size()];
				for (int j = 0; j < excavatorModelList.size(); j++) {

					modelNames[j] = excavatorModelList.get(j).getName();


				}

				mExcavatorModelDatasMap.put(excavatorBrandModelList.get(i).getName(), modelNames);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {

		}
	}
}
