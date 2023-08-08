package com.idata.hhmtwh;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idata.hhmtwh.mapper.T_SJKJ_RMTJ_AJBLMapper;
import com.idata.hhmtwh.mapper.t_organizationMapper;
import com.idata.hhmtwh.model.t_organization;
import org.apache.poi.ss.usermodel.*;
import org.apache.tomcat.util.buf.StringUtils;
import org.bitlap.geocoding.Geocoding;
import org.bitlap.geocoding.model.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;

@SpringBootTest
public class XfDataTests {
    @Autowired
    private T_SJKJ_RMTJ_AJBLMapper tSjkjRmtjAjblMapper;

    @Autowired
    private t_organizationMapper torganizationMapper;
    @Test
    void getAll(){
        String excelFilePath = "C:\\Users\\chnxh\\Desktop\\合肥矛调\\信访\\信访件列表2023.xls";

        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // 获取第一个工作表

            for (Row row : sheet) {
                String case_num="",case_description="",case_type="",place_code="",resource_id="",place_detail="",status="",create_user_name = "";
                //case_num
                if (row.getCell(3) != null) {
                    case_num = row.getCell(3).getStringCellValue();
                }
                //case_description 18
                if (row.getCell(18) != null) {
                    case_description = row.getCell(18).getStringCellValue();
                }
                //case_type 12
                if (row.getCell(12) != null) {
                    case_type = row.getCell(12).getStringCellValue();
                }
                //place_code,resource_id,place_detail 7
                if (row.getCell(7) != null) {
                    String detail = row.getCell(7).getStringCellValue();
                    String place_clean_detail = detailClean(detail);
                    List<t_organization> tOrganizations = codeClean(place_clean_detail);
                    if (tOrganizations!=null && tOrganizations.size() == 1) {
                        String province = tOrganizations.get(0).getProvince();
                        String city = tOrganizations.get(0).getCity();
                        String county = tOrganizations.get(0).getCounty();
                        String town = tOrganizations.get(0).getTown();
                        String village = tOrganizations.get(0).getVillage();
                        //拼接5级
                        List<String> list1 = asList(province, city, county,town,village);

                        place_code = StringUtils.join(list1,',');

                    };
                    resource_id = row.getCell(7).getStringCellValue();
                    place_detail = row.getCell(7).getStringCellValue();
                    addressResolution(resource_id);
//                    System.out.println(detail+"==>"+place_clean_detail+"==>"+place_code);
                }
                //status 9
                if (row.getCell(9) != null) {
                    status = row.getCell(9).getStringCellValue();
                }
                //create_user_name 14
                if (row.getCell(14) != null) {
                    create_user_name = row.getCell(14).getStringCellValue();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String detailClean(String address){
        if(address.contains("镇镇") && !address.endsWith("镇镇")){
            address = address.split("镇镇")[1];
        }
        if (address.contains("市") && !address.endsWith("市")) {
            address = address.split("市")[1];
        }
        if (address.contains("省") && !address.endsWith("省")) {
            address = address.split("省")[1];
        }
        if (address.contains("区") && !address.endsWith("区")) {
            address = address.split("区")[1];
        }
        if (address.contains("新区") && !address.endsWith("新区")) {
            address = address.split("新区")[1];
        }
        if (address.contains("开发区") && !address.endsWith("开发区")) {
            address = address.split("开发区")[1];
        }
        if (address.contains("县") && !address.endsWith("县") && !address.contains("县桥") ) {
            address = address.split("县")[1];
        }
        if (address.contains("镇") && !address.endsWith("镇") && !address.equals("镇南社区")) {
            address = address.split("镇")[1];
        }
        if (address.contains("街道") && !address.endsWith("街道")) {
            address = address.split("街道")[1];
        }
        if (address.contains("乡") && !address.endsWith("乡")) {
            address = address.split("乡")[1];
        }
        if (address.contains("村") && !address.endsWith("村")) {
            address = address.split("村")[0];
        }
        return address;
    }
    public List<t_organization> codeClean(String address){
        try {
            QueryWrapper<t_organization> wrapper = new QueryWrapper();
            wrapper.eq("name", address);
            wrapper.select("province", "city", "county", "town", "village", "name");
            List<t_organization> organization = torganizationMapper.selectList(wrapper);
            if (organization.size() == 0) {
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address+"居委会");
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }
            if (organization.size() == 0) {
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address+"管委会");
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }
            if (organization.size() == 0) {
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address+"社区居委会");
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }
            if (organization.size() == 0) {
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address+"管理委员会");
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }
            if (organization.size() == 0) {
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address+"委会");
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }
            if (organization.size() == 0) {
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address+"市");
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }
            if (organization.size() == 0) {
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address+"街道");
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }

            if (organization.size() == 0) {
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address+"社区");
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }

            if (organization.size() == 0) {
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address+"民委员会");
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }
            if (organization.size() == 0) {
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address+"村民委员会");
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }
            if (organization.size() == 0) {
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address+"村");
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }
            if (organization.size() == 0) {
                String address1 = address.replace("新村","社区居委会");
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address1);
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }
            if (organization.size() == 0) {
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address+"村委会");
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }
            if (organization.size() == 0) {
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address+"乡");
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }
            if (organization.size() == 0) {
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address+"镇");
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }
            if (organization.size() == 0) {
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address+"办事处");
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }
            if (organization.size() == 0) {
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address+"服务中心");
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }
            if (organization.size() == 0) {
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address+"街道办事处");
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }
            if (organization.size() == 0) {
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address+"开发区");
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
            }
            if (organization.size() == 0) {
                String address1 = address.replace("镇","街道办事处街道");
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address1);
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
                if(organization.size() == 0){
                    String address2 = address.replace("镇","居委会");
                    QueryWrapper<t_organization> wrapper2 = new QueryWrapper();
                    wrapper2.eq("name", address2);
                    wrapper2.select("province", "city", "county", "town", "village", "name");
                    organization = torganizationMapper.selectList(wrapper2);
                }
                if(organization.size() == 0){
                    String address2 = address.replace("镇","村委会");
                    QueryWrapper<t_organization> wrapper2 = new QueryWrapper();
                    wrapper2.eq("name", address2);
                    wrapper2.select("province", "city", "county", "town", "village", "name");
                    organization = torganizationMapper.selectList(wrapper2);
                }
            }
            if (organization.size() == 0 && address.endsWith("社区")) {
                String address1 = address.replace("社区","居委会");
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address1);
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
                if(organization.size() == 0){
                    String address2 = address.replace("社区","村委会");
                    QueryWrapper<t_organization> wrapper2 = new QueryWrapper();
                    wrapper2.eq("name", address2);
                    wrapper2.select("province", "city", "county", "town", "village", "name");
                    organization = torganizationMapper.selectList(wrapper2);
                }
                if(organization.size() == 0){
                    String address2 = address.replace("社区","社居委");
                    QueryWrapper<t_organization> wrapper2 = new QueryWrapper();
                    wrapper2.eq("name", address2);
                    wrapper2.select("province", "city", "county", "town", "village", "name");
                    organization = torganizationMapper.selectList(wrapper2);
                }
                if(organization.size() == 0){
                    String address2 = address.replace("社区","村");
                    QueryWrapper<t_organization> wrapper2 = new QueryWrapper();
                    wrapper2.eq("name", address2);
                    wrapper2.select("province", "city", "county", "town", "village", "name");
                    organization = torganizationMapper.selectList(wrapper2);
                }
            }

            if (organization.size() == 0 && address.endsWith("村")) {
                String address1 = address.replace("村","社区");
                QueryWrapper<t_organization> wrapper1 = new QueryWrapper();
                wrapper1.eq("name", address1);
                wrapper1.select("province", "city", "county", "town", "village", "name");
                organization = torganizationMapper.selectList(wrapper1);
                if(organization.size() == 0){
                    String address2 = address.replace("村","社区居民委员会");
                    QueryWrapper<t_organization> wrapper2 = new QueryWrapper();
                    wrapper2.eq("name", address2);
                    wrapper2.select("province", "city", "county", "town", "village", "name");
                    organization = torganizationMapper.selectList(wrapper2);
                }
            }
            return organization;
        }catch (Exception ex) {
            return null;
        }
    }
    /**
     * 解析地址
     * @author xiehaotian
     * @param detailAddress
     * @return
     */
    public String addressResolution(String detailAddress){
        String place_code = "";
        //拼接5级
        Address address = Geocoding.normalizing(detailAddress);
        if (address != null){
            String provinceId = address.getProvinceId() != null ? address.getProvinceId().toString() : "";
            String cityId = address.getCityId() != null ? address.getCityId().toString() : "";
            String districtId = address.getDistrictId() != null ? address.getDistrictId().toString() : "";
            String townId = address.getTownId() != null ? address.getTownId().toString() : "";
            String villageId = address.getVillageId() != null ? address.getVillageId().toString() : "";
            List<String> list1 = asList(provinceId, cityId, districtId,townId,villageId);
            place_code = StringUtils.join(list1,',');

            String province = address.getProvince() != null ? address.getProvince() : "";
            String city = address.getCity() != null ? address.getCity() : "";
            String district = address.getDistrict() != null ? address.getDistrict() : "";
            String town = address.getTown() != null ? address.getTown() : "";
            String village = address.getVillage() != null ? address.getVillage() : "";
            List<String> list2 = asList(province, city, district,town,village);
            String place_code1 = StringUtils.join(list2,',');
            if (!province.isEmpty() && province.equals("安徽省") && !city.isEmpty() && city.equals("合肥市"))
                System.out.println(detailAddress + "======>" + place_code1 + "=====>" + place_code);

        }
        return place_code;
    }
}



