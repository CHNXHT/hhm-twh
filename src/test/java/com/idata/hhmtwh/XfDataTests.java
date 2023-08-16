package com.idata.hhmtwh;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.idata.hhmtwh.mapper.*;
import com.idata.hhmtwh.model.*;
import com.idata.hhmtwh.tool.IdCardUtils;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.util.Arrays.asList;

@SpringBootTest
public class XfDataTests {

@Autowired
private TMediationCaseTestMapper tMediationCaseTestMapper;
@Autowired
private TMediationCasePeopleTestMapper tMediationCasePeopleTestMapper;

@Autowired
private TMediationCaseLogTestMapper tMediationCaseLogTestMapper;

@Autowired
private TUserOrgsMapper tUserOrgsMapper;

@Autowired
private t_organizationMapper tOrganizationMapper;

@Autowired
private TMediationParticipantTestMapper tMediationParticipantTestMapper;


    /**
     *序号   hhm分类          信访分类
     *
     * 3	房产宅基地纠纷	    农村农业
     * 5	生产经营纠纷	    市场监督
     * 7	山林土地纠纷	    自然资源
     * 8	征地拆迁纠纷	    城乡建设
     * 9	环境污染纠纷	    生态环境
     * 10	劳动争议纠纷	    劳动和社会保障
     * 14	道路交通事故纠纷	交通运输
     * 13	医疗纠纷	        卫生健康
     * 15	物业管理纠纷	    房产物业
     * 12	消费纠纷	        经济管理
     * 11	旅游纠纷	        文体旅游
     * 99	其他纠纷	        党务政务
     * 		                组织人事
     * 		                其他
     * 		                科技与信息产业
     * 		                军队事务
     * 		                民政与应急
     * 		                教育
     * 		                政法
     * 		                纪检监察
     */
    @Test
    public void caseClean(){
        String excelFilePath = "C:\\Users\\chnxh\\Desktop\\合肥矛调\\信访\\信访件列表2023.xls";
        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = WorkbookFactory.create(inputStream)) {
            int numberOfSheets = workbook.getNumberOfSheets();
            List<TMediationCaseTest> tMediationCasesList = new ArrayList<>();
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                for (Row row : sheet) {
                    TMediationCaseTest tMediationCase = new TMediationCaseTest();
                    String create_time = "",update_time = "",case_num="",case_description="",case_type="",
                            place_code="",resource_id="",place_detail="",create_user_name = "";
                    create_time = DateUtil.now();
                    update_time = create_time;
                    tMediationCase.setCreateTime(create_time);
                    tMediationCase.setUpdateTime(update_time);
                    tMediationCase.setCaseSource(7);
                    tMediationCase.setMethod(2);
                    Long create_user_id = 10101L;
                    tMediationCase.setCreateUserId(create_user_id);
                    //case_num
                    if (row.getCell(3) != null) {
                        case_num = row.getCell(3).getStringCellValue();
                        tMediationCase.setCaseNum(case_num);
                    }
                    //case_description 18
                    if (row.getCell(18) != null) {
                        case_description = row.getCell(18).getStringCellValue();
                        tMediationCase.setCaseDescription(case_description);
                    }
                    //case_type 12 19种
                    if (row.getCell(12) != null) {
                        case_type = row.getCell(12).getStringCellValue();
                        String[] typeList = case_type.split("_");
                        String xf_case_type = typeList[0];
                        String hhm_case_type = "";
                        switch (xf_case_type) {
                            case "农村农业":
                                hhm_case_type = "3";
                                break;
                            case "市场监督":
                                hhm_case_type = "5";
                                break;
                            case "自然资源":
                                hhm_case_type = "7";
                                break;
                            case "城乡建设":
                                hhm_case_type = "8";
                                break;
                            case "生态环境":
                                hhm_case_type = "9";
                                break;
                            case "劳动和社会保障":
                                hhm_case_type = "10";
                                break;
                            case "交通运输":
                                hhm_case_type = "14";
                                break;
                            case "卫生健康":
                                hhm_case_type = "13";
                                break;
                            case "房产物业":
                                hhm_case_type = "15";
                                break;
                            case "经济管理":
                                hhm_case_type = "12";
                                break;
                            case "文体旅游":
                                hhm_case_type = "11";
                                break;
                            default:
                                hhm_case_type = "99";
                                break;
                        }
                        tMediationCase.setCaseType(hhm_case_type);
                    }
                    //place_code,resource_id,place_detail 7
                    if (row.getCell(7) != null) {
                        String detail = row.getCell(7).getStringCellValue();
                        resource_id = detail;
                        place_detail = detail;
                        place_code = addressResolution(resource_id);
                        tMediationCase.setResourceId(resource_id);
                        tMediationCase.setPlaceDetail(place_detail);
                        tMediationCase.setPlaceCode(place_code);
                    }
                    //status 24 办理状态
                    if (row.getCell(9) != null) {
                        int status = AnalyzeCaseStatus(row.getCell(9).getStringCellValue());
                        tMediationCase.setStatus(status);
                    }
                    //create_user_name 14
                    if (row.getCell(14) != null) {
                        create_user_name = row.getCell(14).getStringCellValue();
                        tMediationCase.setCreateUserName(create_user_name);
                    }
                    System.out.println(tMediationCase.toString());
                    tMediationCasesList.add(tMediationCase);
                    //这里有问题，剩余数据怎么处理
                    if(tMediationCasesList.size() == 200) {
                        tMediationCaseTestMapper.updateOrInsertCaseInfo(tMediationCasesList);
                        tMediationCasesList.clear();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析办理状态 案件状态：1 待受理、2 调解中、3 调解成功、4 调解结束、5 调解终止、6 待调解、7 司法确认、8 草稿
     * 已办结 ->调解成功3
     * 待审核 -> 调解中2
     * 已送达待处理 -> 待调解 1
     * 待签收 -> 待调解 1
     * 退回待办理 -> 待调解 1
     * 已存档 -> 调解成功4
     * 已签收待办理 -> 待调解 1
     * 待补充材料 -> 调解中2
     * 已受理待送达 -> 调解中2
     * 已处理待送达 -> 调解中2
     * 待办理 -> 待调解 1
     * 已延期申请 -> 调解中2
     * 待送达 -> 待调解1
     */
    public static int AnalyzeCaseStatus(String status){
        switch (status) {
            case "已办结":
                return 3;
            case "待审核":
            case "待补充材料":
            case "已受理待送达":
            case "已处理待送达":
                return 2;
            case "已送达待处理":
            case "待签收":
            case "退回待办理":
            case "已签收待办理":
            case "待办理":
            case "待送达":
                return 1;
            case "已存档":
                return 4;
            default:
                return 1;
        }
    }

    /**
     * 案件类型 case_type
     * 婚姻家庭纠纷	01
     * 邻里纠纷	    02
     * 房产宅基地纠纷	03
     * 合同纠纷	    04
     * 生产经营纠纷	05
     * 损害赔偿纠纷	06
     * 山林土地纠纷	07
     * 征地拆迁纠纷	08
     * 环境污染纠纷	09
     * 劳动争议纠纷	10
     * 道路交通事故纠纷	14
     * 医疗纠纷	    13
     * 物业管理纠纷	15
     * 消费纠纷	    12
     * 旅游纠纷	    11
     * 知识产权纠纷	16
     * 互联网纠纷	    17
     * 其他纠纷	    99
     */

    /**
     * 解析地址
     * @author xiehaotian
     * @param detailAddress
     * @return
     */
    public static String addressResolution(String detailAddress){
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
//            String province = address.getProvince() != null ? address.getProvince() : "";
//            String city = address.getCity() != null ? address.getCity() : "";
//            String district = address.getDistrict() != null ? address.getDistrict() : "";
//            String town = address.getTown() != null ? address.getTown() : "";
//            String village = address.getVillage() != null ? address.getVillage() : "";
//            List<String> list2 = asList(province, city, district,town,village);
//            String place_code1 = StringUtils.join(list2,',');
//            if (!province.isEmpty() && province.equals("安徽省") && !city.isEmpty() && city.equals("合肥市"))
//                System.out.println(detailAddress + "======>" + place_code1 + "=====>" + place_code);

        }
        return place_code;
    }



    //----------------------------------------------------------------

    /**
     * people表数据
     * create_time	创建时间	当前时间
     * update_time	修改时间	当前时间
     * class	    参与人类型：1 申请人、2 被申请人	1
     * type	        申请人、被申请人类型：1 自然人、2 法人、3 非法人组织	1
     * name	        申请人姓名/企业名称	登记人
     * identity_type	自然人证件类型：1 居民身份证、2 护照	1
     * identity_num	自然人证件号码	身份证号	6
     * gender	    自然人性别：1 男性、2 女性	身份证号第17位，奇数是男，偶数是女
     * phone	    联系电话	手机号码	5
     * place_code	地址-国家行政区代码	case表中place_code	7
     * place_detail	地址-详细地址	place_detail	7
     * credit_code	社会信用码	/
     * legal_representative	法定代表人	/
     * age	        申请人/被申请人年龄	身份证号	6
     * nation	    民族	身份证号	6
     * position	    职位/职务	/
     * case_id	    纠纷案件id	case id
     */
    @Test
    public void CleanPeople(){
        String excelFilePath = "C:\\Users\\chnxh\\Desktop\\合肥矛调\\信访\\信访件列表2023.xls";

        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            int numberOfSheets = workbook.getNumberOfSheets();
            List<TMediationCasePeopleTest> tMediationCasePeopleList = new ArrayList<>();
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                for (Row row : sheet) {
                    TMediationCasePeopleTest tMediationCasePeopleTest = new TMediationCasePeopleTest();
                    String create_time = "",update_time = "",case_num="", place_code="",resource_id="",place_detail="";
                    create_time = DateUtil.now();
                    update_time = create_time;
                    tMediationCasePeopleTest.setCreateTime(create_time);
                    tMediationCasePeopleTest.setUpdateTime(update_time);
                    tMediationCasePeopleTest.setType(1);
                    String reate_user_name = row.getCell(14).getStringCellValue();
                    tMediationCasePeopleTest.setName(reate_user_name);
                    tMediationCasePeopleTest.setIdentityType(1);
                    if (row.getCell(6)!=null){
                        String identityNum = row.getCell(6).getStringCellValue();
                        tMediationCasePeopleTest.setIdentityNum(identityNum);
                        tMediationCasePeopleTest.setGender(IdCardUtils.getGender(identityNum));
                        tMediationCasePeopleTest.setAge(IdCardUtils.getAge(identityNum));
                    }
                    if(row.getCell(5)!= null){
                        String phonenum = row.getCell(5).getStringCellValue();
                        tMediationCasePeopleTest.setPhone(phonenum);
                    }

                    if (row.getCell(7) != null) {
                        String detail = row.getCell(7).getStringCellValue();
                        resource_id = detail;
                        place_detail = detail;
                        place_code = addressResolution(resource_id);
                        tMediationCasePeopleTest.setPlaceDetail(place_detail);
                        tMediationCasePeopleTest.setPlaceCode(place_code);
                    }
                    if (row.getCell(3) != null) {
                        case_num = row.getCell(3).getStringCellValue();
                        QueryWrapper<TMediationCaseTest> wrapper = new QueryWrapper<>();
                        wrapper.eq("case_num",case_num);
                        List<TMediationCaseTest> tMediationCaseTests = tMediationCaseTestMapper.selectList(wrapper);
                        if (tMediationCaseTests.size()>0){
                            Long id = tMediationCaseTests.get(0).getId();
                            tMediationCasePeopleTest.setCaseId(id);
                        }
                    }
                    System.out.println(tMediationCasePeopleTest.toString());
                    tMediationCasePeopleList.add(tMediationCasePeopleTest);

                    if(tMediationCasePeopleList.size() == 200 ) {
                        System.out.println(tMediationCasePeopleList.size());
                        tMediationCasePeopleTestMapper.updateOrInsertCasePeopleInfo(tMediationCasePeopleList);
                        tMediationCasePeopleList.clear();
                    }
                }
            }
            // 处理不足 200 条的数据
            if (!tMediationCasePeopleList.isEmpty()) {
                tMediationCasePeopleTestMapper.updateOrInsertCasePeopleInfo(tMediationCasePeopleList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------
    /**
     * create_time	创建时间	当前时间
     * update_time	修改时间	当前时间
     * case_id	纠纷案件id	case id
     * log_description	日志描述	case_description
     */
    @Test
    public void CleanLog() {
        String excelFilePath = "C:\\Users\\chnxh\\Desktop\\合肥矛调\\信访\\信访件列表2023.xls";

        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream)) {

            int numberOfSheets = workbook.getNumberOfSheets();
            List<TMediationCaseLogTest> tMediationCaseLogList = new ArrayList<>();
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                for (Row row : sheet) {
                    TMediationCaseLogTest tMediationCaseLogTest = new TMediationCaseLogTest();
                    DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    try {
                        Date create_time = fmt.parse(DateUtil.now());
                        tMediationCaseLogTest.setCreateTime(create_time);
                        tMediationCaseLogTest.setUpdateTime(create_time);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    //case_num
                    if (row.getCell(3) != null) {
                        String case_num = row.getCell(3).getStringCellValue();
                        QueryWrapper<TMediationCaseTest> wrapper = new QueryWrapper();
                        wrapper.eq("case_num",case_num);
                        List<TMediationCaseTest> tMediationCaseTests = tMediationCaseTestMapper.selectList(wrapper);
                        if(tMediationCaseTests.size()>0){
                            tMediationCaseLogTest.setCaseId(tMediationCaseTests.get(0).getId());
                        }
//                        tMediationCaseLogTest.setCaseId(case_num);
                    }
                    //case_description 18
                    if (row.getCell(18) != null) {
                        String case_description = row.getCell(18).getStringCellValue();
                        tMediationCaseLogTest.setLogDescription(case_description);
                    }
                    tMediationCaseLogList.add(tMediationCaseLogTest);

                    if(tMediationCaseLogList.size() == 200 ) {
                        System.out.println(tMediationCaseLogList.size());
                        tMediationCaseLogTestMapper.updateOrInsertCaseLogInfo(tMediationCaseLogList);
                        tMediationCaseLogList.clear();
                    }

                }
            }
            // 处理不足 200 条的数据
            if (!tMediationCaseLogList.isEmpty()) {
                tMediationCaseLogTestMapper.updateOrInsertCaseLogInfo(tMediationCaseLogList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------
    /**
     * @des participant清洗
     *
     * id	主键id
     * create_time	创建时间
     * update_time	修改时间
     * user_id	案件流转参与者id
     * org_id	案件流转参与者所在调解机构id
     * case_id	纠纷案件id
     * flag	调解机构/调解员标识：1 调解机构、 2 调解员、3 协同调解员
     */
    @Test
    public void CleanPart() {
        //1、获取全部case数据
        QueryWrapper<TMediationCaseTest> wrapper = new QueryWrapper();
        wrapper.eq("case_source",7);
        IPage<Map<String, Object>> page=new Page<>(1,50,false);
        IPage<Map<String, Object>> mapIPage = tMediationCaseTestMapper.selectMapsPage(page, wrapper);
        List<Map<String, Object>> records = mapIPage.getRecords();
        List<TMediationParticipantTest> tMediationParticipantTestList = new ArrayList<>();
        for (Map<String, Object> record : records) {
            TMediationParticipantTest tMediationParticipantTest = new TMediationParticipantTest();
            String createTime = record.get("create_time").toString();
            tMediationParticipantTest.setCreateTime(createTime);
            String updateTime = DateUtil.now();
            tMediationParticipantTest.setUpdateTime(updateTime);
            Long caseNum = Long.parseLong(record.get("id").toString());
            tMediationParticipantTest.setCaseId(caseNum);
            tMediationParticipantTest.setFlag(1);
            if(record.get("place_code")!= null){
                String placeCode = record.get("place_code").toString();
                String[] split = placeCode.split(",");
                // 确保生成五个部分并保留空字符串
                String[] result = new String[5];
                for (int i = 0; i < 5; i++) {
                    if (i < split.length) {
                        result[i] = split[i];
                    } else {
                        result[i] = "";
                    }
                }
                // 将切割后的部分赋值给对应变量
                String province = result[0];
                String city = result[1];
                String county = result[2];
                String town = result[3];
                String village = result[4];
                QueryWrapper<t_organization> wrapper_org = new QueryWrapper<>();
                wrapper_org.eq("province",province);
                wrapper_org.eq("city",city);
                wrapper_org.eq("county",county);
                wrapper_org.eq("town",town);
                wrapper_org.eq("village",village);
                List<t_organization> tOrganizations = tOrganizationMapper.selectList(wrapper_org);
                if (tOrganizations.size()>0) {
                    Long org_id = tOrganizations.get(0).getId();
                    //去t_user_orgs表获取user_id
                    QueryWrapper<TUserOrgs> wrapper_orguesr_id = new QueryWrapper<>();
                    wrapper_orguesr_id.eq("org_id",org_id);
                    List<TUserOrgs> tUserOrgs = tUserOrgsMapper.selectList(wrapper_orguesr_id);
                    if(tUserOrgs.size()>0){
                        Long userId = tUserOrgs.get(0).getUserId();
                        Long orgId = tUserOrgs.get(0).getOrgId();
                        tMediationParticipantTest.setOrgId(orgId);
                        tMediationParticipantTest.setUserId(userId);
                        System.out.println(tMediationParticipantTest);
                        tMediationParticipantTestList.add(tMediationParticipantTest);
                        if(tMediationParticipantTestList.size() == 200 ) {
                            tMediationParticipantTestMapper.updateOrInsertCasePartInfo(tMediationParticipantTestList);
                            tMediationParticipantTestList.clear();
                        }
                    }
                }
            }
        }
        // 处理不足 200 条的数据
        if (!tMediationParticipantTestList.isEmpty()) {
            tMediationParticipantTestMapper.updateOrInsertCasePartInfo(tMediationParticipantTestList);
        }
    }

}



