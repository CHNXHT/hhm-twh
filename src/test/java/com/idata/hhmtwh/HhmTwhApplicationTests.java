package com.idata.hhmtwh;

import cn.hutool.core.io.file.FileWriter;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.idata.hhmtwh.mapper.T_SJKJ_RMTJ_AJBLMapper;
import com.idata.hhmtwh.mapper.t_organizationMapper;
import com.idata.hhmtwh.mapper.t_twh_code_copy1Mapper;
import com.idata.hhmtwh.model.T_SJKJ_RMTJ_AJBL;
import com.idata.hhmtwh.model.t_organization;
import com.idata.hhmtwh.model.t_twh_code_copy1;
import com.idata.hhmtwh.service.T_SJKJ_RMTJ_AJBLService;
import com.idata.hhmtwh.service.t_organizationService;
import com.idata.hhmtwh.service.t_twh_code_copy1Service;
import org.apache.tomcat.util.buf.StringUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;

@SpringBootTest
class HhmTwhApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(HhmTwhApplicationTests.class);
    private static final Logger MLOGGER = LoggerFactory.getLogger("GLMAPPER-TEST-LOG");

    @Autowired
    private T_SJKJ_RMTJ_AJBLService tSjkjRmtjAjblService;

    @Autowired
    private t_organizationService tOrganizationService;

    @Autowired
    private t_twh_code_copy1Service twhCodeCopy1Service;

    @Autowired
    private T_SJKJ_RMTJ_AJBLMapper tSjkjRmtjAjblMapper;

    @Autowired
    private t_organizationMapper torganizationMapper;
    @Autowired
    private t_twh_code_copy1Mapper twhCodeCopy1Mapper;

    @Test
    @DS("middle")
    void getall(){
        List<T_SJKJ_RMTJ_AJBL> list = tSjkjRmtjAjblService.list();

        QueryWrapper<T_SJKJ_RMTJ_AJBL> wrapper = new QueryWrapper();

        Long ajblCount = tSjkjRmtjAjblMapper.selectCount(wrapper);
        int totalPage = (int) (ajblCount / 1000);
        int count = 0;
        //分多少页
        for (int i = 1; i <= totalPage; i++) {
            Page<T_SJKJ_RMTJ_AJBL> page = new Page<>(i,200);
            Page<T_SJKJ_RMTJ_AJBL> ipage  = tSjkjRmtjAjblMapper.selectPage(page,wrapper);
            //分页
            for (T_SJKJ_RMTJ_AJBL tSjkjRmtjAjbl : ipage.getRecords()){
                String sldw = tSjkjRmtjAjbl.getSldw();
                //获取解析后地址
                String targetAddress = getTargetAddress(sldw);
                //根据解析地址获取行政区划
                List<t_organization> xzqh = getXzqh(targetAddress);
                List<t_twh_code_copy1> tTwhCodeCopy1List = new ArrayList<>();
                if (xzqh!=null && xzqh.size()==1){
                    String province = xzqh.get(0).getProvince();
                    String city = xzqh.get(0).getCity();
                    String county = xzqh.get(0).getCounty();
                    String town = xzqh.get(0).getTown();
                    String village = xzqh.get(0).getVillage();
                    //拼接5级
                    List<String> list1 = asList(province, city, county,town,village);

                    String result= StringUtils.join(list1,',');
                    count++;
                    if(count%1000==0){
                        System.out.println("当前已传："+count);
                    }
//                    System.out.println(result);
                    t_twh_code_copy1 tTwhCodeCopy1 = new t_twh_code_copy1();
                    tTwhCodeCopy1.setAddress(targetAddress);
                    tTwhCodeCopy1.setTwh(sldw);
                    tTwhCodeCopy1.setPlaceCode(result);
                    tTwhCodeCopy1List.add(tTwhCodeCopy1);
//                    twhCodeCopy1Mapper.insert(tTwhCodeCopy1);
                }
                twhCodeCopy1Mapper.updateOrInsertClientInfo(tTwhCodeCopy1List);
//                System.out.println("解析前："+sldw+"  =====  解析后："+targetAddress +"=====  匹配："+xzqh.toString());
//                if (xzqh.isEmpty()){
//                    System.out.println("解析前："+sldw+"  =====  解析后："+targetAddress +"=====  解析后："+xzqh.toString());
//                    FileWriter writer = new FileWriter("C:\\Users\\xht\\Desktop\\合肥矛调\\address5.csv");
//                    writer.append(sldw+","+targetAddress+"\n");
//                }

            }
        }
        System.out.println("ajbl总量："+count);
    }
    public List<t_organization> getXzqh(String address){
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
                wrapper1.eq("name", address+"委会");
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
//            if (organization.size() > 1) {
//                MLOGGER.info(organization.toString());
//            } else if (organization.size() == 1) {
//                String pc = organization.get(0).getProvince()+","+organization.get(0).getCity()+","+organization.get(0).getCounty()+","+organization.get(0).getTown()+","+organization.get(0).getVillage();
//                LOGGER.info("#####{}${}", address, pc);
//
//            } else {
////                LOGGER.error(String.format("#####%s$%s$%s$%s$%s", address, resourceId, province, city, county));
//            }
            return organization;
        }catch (Exception ex) {
//            System.err.println("address ->" + address);
            return null;
        }
    }
    /**
     * 从 resourceId 解析出四级机构
     * @param resourceId
     * @return
     */
    public static String getTargetAddress(String resourceId) {
        try {
            String address = resourceId.split("-", -1)[0];
            //调解委会驻派出所调解室
            if (address.endsWith("调解委会驻派出所调解室")) {
                address = address.substring(0, address.length() - "调解委会驻派出所调解室".length());
            }
            if(address.contains("司法所")){
                    address = address.replace("司法所","");
            }
            if(address.contains("杜集乡")){
                address = address.replace("杜集乡","杜集镇");
            }
            if(address.contains("司法局")){
                address = address.replace("司法局","");
            }
            if(address.contains("派出所")){
                address = address.replace("派出所","");
            }
            if(address.contains("安徽（长丰）")){
                address = address.replace("安徽（长丰）","");
            }
            //驻所调解室
            if (address.endsWith("驻所调解室")) {
                address = address.substring(0, address.length() - "驻所调解室".length());
            }
            if(address.contains("调解室")){
                address = address.replace("调解室","");
            }
            if (address.endsWith("社居委人民调解委员会")) {
                address = address.substring(0, address.length() - "社居委人民调解委员会".length());
//                address = address + "社区";
            }
            if(address.contains("县桥社居")){
                address = address.replace("县桥社居","");
            }
            if (address.endsWith("婚姻家庭纠纷人民调解委员会")) {
                address = address.substring(0, address.length() - "婚姻家庭纠纷人民调解委员会".length());
            }
            if (address.endsWith("社区人民调委员会")) {
                address = address.substring(0, address.length() - "社区人民调委员会".length());
            }
            //肥东县经济开发区警民联调人民调解委员会
            if (address.endsWith("经济开发区警民联调人民调解委员会")) {
                address = address.substring(0, address.length() - "经济开发区警民联调人民调解委员会".length());
            }
            //大孟社区人民调解委员会
            if (address.endsWith("大孟社区人民调解委员会")) {
                address = address.substring(0, address.length() - "大孟社区人民调解委员会".length());
            }
            //盛湾社区人民调解委员会
            if (address.endsWith("盛湾社区人民调解委员会")) {
                address = address.substring(0, address.length() - "盛湾社区人民调解委员会".length());
            }
            if (address.endsWith("中山路警民联合人民调解委员会")) {
                address = address.substring(0, address.length() - "中山路警民联合人民调解委员会".length());
            }
            if (address.endsWith("李荣工作站人民调解委员会")) {
                address = address.substring(0, address.length() - "李荣工作站人民调解委员会".length());
            }
            //社居委居民调解委员会
            if (address.endsWith("社居委居民调解委员会")) {
                address = address.substring(0, address.length() - "社居委居民调解委员会".length());
            }
            //巢湖闸社区人民调解委员会
            if (address.endsWith("巢湖闸社区人民调解委员会")) {
                address = address.substring(0, address.length() - "巢湖闸社区人民调解委员会".length());
            }
            //皖光社区人民调解委员会
            if (address.endsWith("皖光社区人民调解委员会")) {
                address = address.substring(0, address.length() - "皖光社区人民调解委员会".length());
            }

            if (address.endsWith("调解员委会")) {
                address = address.substring(0, address.length() - "调解员委会".length());
            }
            if (address.endsWith("名委员会")) {
                address = address.substring(0, address.length() - "名委员会".length());
            }
            if (address.endsWith("婚姻家庭纠纷人民调解委员会")) {
                address = address.substring(0, address.length() - "婚姻家庭纠纷人民调解委员会".length());
            }
            //医患纠纷人民调解委员会
            if (address.endsWith("医患纠纷人民调解委员会")) {
                address = address.substring(0, address.length() - "医患纠纷人民调解委员会".length());
            }
            //信访事项纠纷人民调解委员会
            if (address.endsWith("信访事项纠纷人民调解委员会")) {
                address = address.substring(0, address.length() - "信访事项纠纷人民调解委员会".length());
            }
            //总商会人民调解委员会
            if (address.endsWith("总商会人民调解委员会")) {
                address = address.substring(0, address.length() - "总商会人民调解委员会".length());
            }
            //驻派出所调解室
            if (address.endsWith("驻派出所调解室")) {
                address = address.substring(0, address.length() - "驻派出所调解室".length());
            }
            //警民联室
            if (address.endsWith("警民联室")) {
                address = address.substring(0, address.length() - "警民联室".length());
            }
            //劳动争议调委会
            if (address.endsWith("劳动争议调委会")) {
                address = address.substring(0, address.length() - "劳动争议调委会".length());
            }
            //人名调解委员会
            if (address.endsWith("人名调解委员会")) {
                address = address.substring(0, address.length() - "人名调解委员会".length());
            }

            else if (address.endsWith("警民联调室人民调解委员会")) {
                address = address.substring(0, address.length() - "警民联调室人民调解委员会".length());
            } else if (address.endsWith("警民联调室调解委员会")) {
                address = address.substring(0, address.length() - "警民联调室调解委员会".length());
            } else if (address.endsWith("警民联合人民调解委员会")) {
                address = address.substring(0, address.length() - "警民联合人民调解委员会".length());
            } else if (address.endsWith("警民联调室")) {
                address = address.substring(0, address.length() - "警民联调室".length());
            }
            else if (address.endsWith("社居委调解委员会")) {
                address = address.substring(0, address.length() - "社居委调解委员会".length());
                address = address + "社区";
            } else if (address.endsWith("社居委调委会")) {
                address = address.substring(0, address.length() - "社居委调委会".length());
                address = address + "社区";
            } else if (address.endsWith("社居调委会")) {
                address = address.substring(0, address.length() - "社居调委会".length());
                address = address + "社区";
            } else if (address.endsWith("社居人民调解委员会")) {
                address = address.substring(0, address.length() - "社居人民调解委员会".length());
                address = address + "社区";
            }else if (address.endsWith("社区居民调解委员会")) {
                address = address.substring(0, address.length() - "社区居民调解委员会".length());
                address = address + "社区";
            }
            else if (address.endsWith("居民调解委员会")) {
                address = address.substring(0, address.length() - "居民调解委员会".length());
                address = address + "社区";
            } else if (address.endsWith("中山路警民联合人民调解委员会")) {
                address = address.substring(0, address.length() - "中山路警民联合人民调解委员会".length());
            } else if (address.endsWith("物业纠纷人民调解委员会")) {
                address = address.substring(0, address.length() - "物业纠纷人民调解委员会".length());
            } else if (address.endsWith("居民委员会人民调解委员会")) {
                address = address.substring(0, address.length() - "居民委员会人民调解委员会".length());
            }else if (address.endsWith("居委会人民调解委员会")) {
                address = address.substring(0, address.length() - "居委会人民调解委员会".length());
            }
            else if (address.endsWith("经开区人民调解委员会")) {
                address = address.substring(0, address.length() - "经开区人民调解委员会".length());
            }
            else if (address.endsWith("人民调解委员会")) {
                address = address.substring(0, address.length() - "人民调解委员会".length());
            } else if (address.endsWith("人民调解委员")) {
                address = address.substring(0, address.length() - "人民调解委员".length());
            }
            else if (address.endsWith("人民调解调委会")) {
                address = address.substring(0, address.length() - "人民调解调委会".length());
            }
            else if (address.endsWith("调解委员会")) {
                address = address.substring(0, address.length() - "调解委员会".length());
            } else if (address.endsWith("调委会")) {
                address = address.substring(0, address.length() - "调委会".length());
            } else if (address.endsWith("工作站")) {
                address = address.substring(0, address.length() - "工作站".length());
            }else if (address.endsWith("管委会司法办")) {
                address = address.substring(0, address.length() - "管委会司法办".length());
            }else if (address.endsWith("调解室")) {
                address = address.substring(0, address.length() - "调解室".length());
            }else if (address.endsWith("行业纠纷调处中心")) {
                address = address.substring(0, address.length() - "行业纠纷调处中心".length());
            }
            //
            if(address.equals("店埠镇镇南社区")){
                address = "镇南社区";
            }
            if(address.contains("镇镇") && !address.endsWith("镇镇")){
                address = address.split("镇镇")[1];
            }
            if (address.contains("市") && !address.endsWith("市")) {
                address = address.split("市")[1];
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
            if (address.contains("七里站") && !address.endsWith("七里站")) {
                address = address.split("七里站")[1];
            }
            if (address.contains("三里街") && !address.endsWith("三里街") && !address.equals("三里街街道")) {
                address = address.split("三里街")[1];
            }
            if (address.contains("林店街道")) {
                address = address.replace("林店街道","林店社区");
            }
            if (address.contains("左店乡")) {
                address = address.replace("左店乡","左店镇");
            }
            if (address.contains("义井乡")) {
                address = address.replace("义井乡","义井镇");
            }
            if (address.contains("庙岗镇")) {
                address = address.replace("庙岗镇","庙岗乡");
            }
            if (address.equals("汲桥新村社区")) {
                address = "汲桥新村";
            }
            if (address.endsWith("村委会")) {
                address = address.replace("村委会","");
            }
            if (address.endsWith("风景区")) {
                address = address.replace("风景区","");
            }
            if (address.endsWith("开发区")) {
                address = address.replace("开发区","");
            }
            if (address.endsWith("工作站")) {
                address = address.replace("工作站","");
            }
            if (address.contains("蜀山区三里庵")) {
                address = address.replace("蜀山区三里庵","");
            }
            if (address.contains("新村社区")) {
                address = address.replace("新村社区","社区居委会");
            }
            if (address.equals("双凤经开区")) {
                address = address.replace("双凤经开区","双凤开发区");
            }
            if (address.equals("塘林社区")) {
                address = address.replace("塘林社区","塘林回族满族社区");
            }
            if (address.equals("牌坊乡")) {
                address = address.replace("牌坊乡","牌坊回族满族乡");
            }
            //循环经济示范园
            if (address.equals("循环经济示范园")) {
                address = address.replace("循环经济示范园","合肥循环经济示范园");
            }
            //潘付村
            //循环经济示范园
            if (address.equals("潘付村")) {
                address = address.replace("潘付村","潘傅村");
            }
            //铜陵社区居委会
            if (address.equals("铜陵社区居委会")) {
                address = address.replace("铜陵社区居委会","铜陵新村社区");
            }
            //朝阳天河街道
            if (address.equals("朝阳天河街道")) {
                address = address.replace("朝阳天河街道","天河");
            }
            return address;
        } catch (Exception ex) {
            System.err.println("address ->" + resourceId);
            return null;
        }
    }
}
