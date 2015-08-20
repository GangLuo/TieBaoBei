package com.zhixiangzhonggong.tiebaobei.Activity;

import android.app.Activity;
import android.content.res.AssetManager;


import com.zhixiangzhonggong.tiebaobei.model.CarStateModel;
import com.zhixiangzhonggong.tiebaobei.model.CarUsingPurposeModel;
import com.zhixiangzhonggong.tiebaobei.model.CityModel;
import com.zhixiangzhonggong.tiebaobei.model.DistrictModel;
import com.zhixiangzhonggong.tiebaobei.model.ExcavatorBrandModel;
import com.zhixiangzhonggong.tiebaobei.model.ExcavatorModelModel;
import com.zhixiangzhonggong.tiebaobei.model.MonthModel;
import com.zhixiangzhonggong.tiebaobei.model.ProvinceModel;
import com.zhixiangzhonggong.tiebaobei.model.YearModel;
import com.zhixiangzhonggong.tiebaobei.service.XmlParserHandler;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class BaseActivity extends Activity {
	

	protected String[] mProvinceDatas;
	protected String[] mExcavatorDatas;
	protected String[] mYearDatas;
    protected String[] mCarStateDatas;
    protected String[] mCarUsingPurposeDatas;

	protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	protected Map<String, String[]> mExcavatorModelDatasMap = new HashMap<String, String[]>();
	protected Map<String, String[]> mYearDatasMAP=new HashMap<>();
	protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();
	protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();


	protected String mCurrentProviceName;
	protected String mCurentExcavatorName;
	protected String mCurrentExcavatorModelName;
	protected String mCurrentYearName;
	protected String mCurrentMonthName;
	protected String mCurrentCityName;
	protected String mCurrentDistrictName ="";
	protected String mCurrentZipCode ="";
    protected String mCurrentCarStateName;
    protected String mCurrentCarUsingPurposeName;
	
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

	protected void initYearDatas()
	{
		List<YearModel> yearModelList = null;
		AssetManager asset = getAssets();
		try {
			InputStream input = asset.open("province_data.xml");
			SAXParserFactory spf = SAXParserFactory.newInstance();

			SAXParser parser = spf.newSAXParser();
			XmlParserHandler handler = new XmlParserHandler();
			parser.parse(input, handler);
			input.close();

			yearModelList = handler.getYearModelList();

			if (yearModelList!= null && !yearModelList.isEmpty()) {
				mCurrentYearName = yearModelList.get(0).getName();
				List<MonthModel> monthModelList = yearModelList.get(0).getMonthList();
				if (monthModelList!= null && !monthModelList.isEmpty()) {
					mCurrentMonthName = monthModelList.get(0).getName();

				}

			}
			//*/
			mYearDatas = new String[yearModelList.size()];
			for (int i=0; i< yearModelList.size(); i++) {

				mYearDatas[i] = yearModelList.get(i).getName();
				List<MonthModel> monthModelList = yearModelList.get(i).getMonthList();
				String[] modelNames = new String[monthModelList.size()];
				for (int j = 0; j < monthModelList.size(); j++) {

					modelNames[j] = monthModelList.get(j).getName();


				}

				mYearDatasMAP.put(yearModelList.get(i).getName(), modelNames);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {

		}
}
    protected void initCarStateDatas()
    {
        List<CarStateModel> carStateModelList = null;
        AssetManager asset = getAssets();
        try {
            InputStream input = asset.open("province_data.xml");

            SAXParserFactory spf = SAXParserFactory.newInstance();

            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();

            carStateModelList = handler.getCarStateModels();

            if (carStateModelList!= null && !carStateModelList.isEmpty()) {
                mCurrentCarStateName = carStateModelList.get(0).getName();

            }
            //*/
            mCarStateDatas = new String[carStateModelList.size()];
            for (int i=0; i< carStateModelList.size(); i++) {

                mCarStateDatas[i] = carStateModelList.get(i).getName();

            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }

    protected void initCarUsingPurposeDatas()
    {
        List<CarUsingPurposeModel> carUsingPurposeModelList = null;
        AssetManager asset = getAssets();
        try {
            InputStream input = asset.open("province_data.xml");

            SAXParserFactory spf = SAXParserFactory.newInstance();

            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();

            carUsingPurposeModelList = handler.getCarUsingPurposeModels();

            if (carUsingPurposeModelList!= null && !carUsingPurposeModelList.isEmpty()) {
                mCurrentCarUsingPurposeName = carUsingPurposeModelList.get(0).getName();

            }
            //*/
            mCarUsingPurposeDatas = new String[carUsingPurposeModelList.size()];
            for (int i=0; i< carUsingPurposeModelList.size(); i++) {

                mCarUsingPurposeDatas[i] = carUsingPurposeModelList.get(i).getName();

            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }
}
