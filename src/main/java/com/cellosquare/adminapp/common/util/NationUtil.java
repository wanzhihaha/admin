package main.java.com.cellosquare.adminapp.common.util;

import com.alibaba.excel.util.StringUtils;

public class NationUtil {

    /**
     * 根据国家二字码获取国家对应的中文(英文,code)
     *
     * @param countryCode
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2019年1月17日]
     * @see [类、类#方法、类#成员]
     */
    public static String getCountryNameCnEnCodeByCountryCode(String countryCode) {
        String countryName = null;
        switch (countryCode) {
            case "US":
                countryName = "美国(United State,US)";
                break;
            case "GB":
                countryName = "英国(United Kingdom,GB)";
                break;
            case "DE":
                countryName = "德国(Germany,DE)";
                break;
            case "CA":
                countryName = "加拿大(Canada,CA)";
                break;
            case "JP":
                countryName = "日本(Japan,JP)";
                break;
            case "ES":
                countryName = "西班牙(Spain,ES)";
                break;
            case "FR":
                countryName = "法国(France,FR)";
                break;
            case "IT":
                countryName = "意大利(Italy,IT)";
                break;
            case "CN":
                countryName = "中国(China,CN)";
                break;
            case "AT":
                countryName = "奥地利(Austria,AT)";
                break;
            case "AU":
                countryName = "澳大利亚(Australian,AU)";
                break;
            case "CH":
                countryName = "瑞士(Switzerland,CH)";
                break;
            case "EG":
                countryName = "埃及(Egypt,EG)";
                break;
            case "FI":
                countryName = "芬兰(Finland,FI)";
                break;
            case "IN":
                countryName = "印度(India,IN)";
                break;
            case "IE":
                countryName = "爱尔兰(Ireland,IE)";
                break;
            case "ID":
                countryName = "印度尼西亚(Indonesia,ID)";
                break;
            case "KH":
                countryName = "柬埔寨(Cambodia,KH)";
                break;
            case "KR":
                countryName = "韩国(Korea,KR)";
                break;
            case "MX":
                countryName = "墨西哥(Mexico,MX)";
                break;
            case "NL":
                countryName = "荷兰(Holland,NL)";
                break;
            case "NO":
                countryName = "挪威(Norway,NO)";
                break;
            case "MM":
                countryName = "缅甸(Burma,MM)";
                break;
            case "PH":
                countryName = "菲律宾(Philippines,PH)";
                break;
            case "BE":
                countryName = "比利时(Belgium,BE)";
                break;
            case "GY":
                countryName = "圭亚那(Guyana,GY)";
                break;
            case "SE":
                countryName = "瑞典(Sweden,SE)";
                break;
            case "PY":
                countryName = "巴拉圭(Paraguay,PY)";
                break;
            case "UA":
                countryName = "乌克兰(Ukraine,UA)";
                break;
            case "PE":
                countryName = "秘鲁(Peru,PE)";
                break;
            case "AE":
                countryName = "阿联酋(United Arab Emirates,AE)";
                break;
            case "VN":
                countryName = "越南(Vietnam,VN)";
                break;
            case "SO":
                countryName = "索马里(Somalia,SO)";
                break;
            case "LA":
                countryName = "老挝(Laos,LA)";
                break;
            case "KW":
                countryName = "科威特(Kuwait,KW)";
                break;
            case "MY":
                countryName = "马来西亚(Malaysia,MY)";
                break;
            case "DK":
                countryName = "丹麦(Denmark,DK)";
                break;
            case "PK":
                countryName = "巴基斯坦(Pakistan,PK)";
                break;
            case "TH":
                countryName = "泰国(Thailand,TH)";
                break;
            case "HU":
                countryName = "匈牙利(HUNGARY,HU)";
                break;
            case "LV":
                countryName = "拉脱维亚(Latvia,LV)";
                break;
            case "RU":
                countryName = "俄罗斯(Russia,RU)";
                break;
            case "CZ":
                countryName = "捷克(Czech Republic,CZ)";
                break;
            case "PL":
                countryName = "波兰(Poland,PL)";
                break;
            case "BY":
                countryName = "白俄罗斯(Belarus,BY)";
                break;
            case "LT":
                countryName = "立陶宛(Lithuania,LT)";
                break;
            case "LU":
                countryName = "卢森堡(Luxembourg,LU)";
                break;
            case "AD":
                countryName = "安道尔(Andorra,AD)";
                break;
            case "AG":
                countryName = "安提瓜(Antigua and Barbuda,AG)";
                break;
            case "RO":
                countryName = "罗马尼亚(Romania,RO)";
                break;
            case "AM":
                countryName = "亚美尼亚(Armenia,AM)";
                break;
            case "CV":
                countryName = "佛得角(Cape Verde,CV)";
                break;
            case "DZ":
                countryName = "阿尔及利亚(Algeria,DZ)";
                break;
            case "RT":
                countryName = "罗塔岛（北马里亚纳群岛）(Rota(Northern Mariana Islands),RT)";
                break;
            case "TL":
                countryName = "东帝汶(Timor-Leste,TL)";
                break;
            case "MU":
                countryName = "毛里求斯(Mauritius,MU)";
                break;
            case "ZM":
                countryName = "赞比亚(Zambia,ZM)";
                break;
            case "BL":
                countryName = "圣巴泰勒米(St. Barthelemy,BL)";
                break;
            case "SX":
                countryName = "圣马丁（瓜德罗普岛）(St. Maarten,St. Martin,SX)";
                break;
            case "BH":
                countryName = "巴林(Bahrain,BH)";
                break;
            case "GM":
                countryName = "冈比亚(Gambia,GM)";
                break;
            case "MV":
                countryName = "马尔代夫(Maldives,MV)";
                break;
            case "CK":
                countryName = "库克群岛(Cook Islands,CK)";
                break;
            case "RE":
                countryName = "留尼汪岛(Reunion Island,RE)";
                break;
            case "IQ":
                countryName = "伊拉克(Iraq,IQ)";
                break;
            case "GD":
                countryName = "格林纳达(Grenada,GD)";
                break;
            case "FM":
                countryName = "密克罗尼西亚(Micronesia,Federated States of,FM)";
                break;
            case "VE":
                countryName = "委内瑞拉(Venezuela,VE)";
                break;
            case "NC":
                countryName = "新喀里多尼亚(New Caledonia,NC)";
                break;
            case "NP":
                countryName = "尼泊尔(Nepal,NP)";
                break;
            case "MD":
                countryName = "摩尔多瓦共和国(Moldova,MD)";
                break;
            case "VA":
                countryName = "梵蒂冈(Vatican City(Italy),VA)";
                break;
            case "GP":
                countryName = "瓜德罗普岛(Guadeloupe,GP)";
                break;
            case "GU":
                countryName = "关岛(Guam,GU)";
                break;
            case "NA":
                countryName = "纳米比亚(Namibia,NA)";
                break;
            case "QA":
                countryName = "卡塔尔(Qatar,QA)";
                break;
            case "YT":
                countryName = "马约特岛岛(Mayotte,YT)";
                break;
            case "ME":
                countryName = "黑山(Montenegro,ME)";
                break;
            case "BT":
                countryName = "不丹(Bhutan,BT)";
                break;
            case "UZ":
                countryName = "乌兹别克斯坦(Uzbekistan,UZ)";
                break;
            case "WF":
                countryName = "瓦利斯群岛和富图纳群岛(Wallis & Futuna Islands,WF)";
                break;
            case "JO":
                countryName = "约旦(Jordan,JO)";
                break;
            case "MK":
                countryName = "马其顿(Macedonia(FYROM),MK)";
                break;
            case "BF":
                countryName = "布基纳法索(Burkina Faso,BF)";
                break;
            case "NE":
                countryName = "尼日尔(Niger,NE)";
                break;
            case "WL":
                countryName = "威尔士（英国）(Wales(United Kingdom),WL)";
                break;
            case "GE":
                countryName = "格鲁吉亚(Georgia,GE)";
                break;
            case "IS":
                countryName = "冰岛(Iceland,IS)";
                break;
            case "ER":
                countryName = "厄立特里亚(Eritrea,ER)";
                break;
            case "KM":
                countryName = "科摩罗(Comoros,KM)";
                break;
            case "SN":
                countryName = "塞内加尔(Senegal,SN)";
                break;
            case "EE":
                countryName = "爱沙尼亚(Estonia,EE)";
                break;
            case "MO":
                countryName = "澳门(Macau,MO)";
                break;
            case "SI":
                countryName = "斯洛文尼亚(Slovenia,SI)";
                break;
            case "UG":
                countryName = "乌干达(Uganda,UG)";
                break;
            case "ZW":
                countryName = "津巴布韦(Zimbabwe,ZW)";
                break;
            case "TD":
                countryName = "乍得(Chad,TD)";
                break;
            case "GL":
                countryName = "格陵兰岛(Greenland,GL)";
                break;
            case "AF":
                countryName = "阿富汗(Afghanistan,AF)";
                break;
            case "CD":
                countryName = "刚果人民共和国(Congo,Democratic Republic of,CD)";
                break;
            case "MN":
                countryName = "蒙古(Mongolia,MN)";
                break;
            case "MR":
                countryName = "毛里塔尼亚(Mauritania,MR)";
                break;
            case "CO":
                countryName = "哥伦比亚(Colombia,CO)";
                break;
            case "TT":
                countryName = "特立尼达和多巴哥(Trinidad & Tobago,TT)";
                break;
            case "SF":
                countryName = "苏格兰(Scotland(United Kingdom),SF)";
                break;
            case "CF":
                countryName = "中非共和国(Central African Republic,CF)";
                break;
            case "GI":
                countryName = "直布罗陀(Gibraltar,GI)";
                break;
            case "KY":
                countryName = "开曼群岛(Cayman Islands,KY)";
                break;
            case "NI":
                countryName = "尼加拉瓜(Nicaragua,NI)";
                break;
            case "TG":
                countryName = "多哥(Togo,TG)";
                break;
            case "GQ":
                countryName = "赤道几内亚(Equatorial Guinea,GQ)";
                break;
            case "KN":
                countryName = "圣基茨(St. Kitts and Nevis,KN)";
                break;
            case "ZZ":
                countryName = "托尔托拉岛（英属处女岛）(Tortola(British Virgin Islands),ZZ)";
                break;
            case "TZ":
                countryName = "坦桑尼亚共和国(Tanzania,United Republic of,TZ)";
                break;
            case "KE":
                countryName = "肯尼亚(Kenya,KE)";
                break;
            case "MH":
                countryName = "马绍尔群岛(Marshall Islands,MH)";
                break;
            case "KZ":
                countryName = "哈萨克斯坦(Kazakhstan,KZ)";
                break;
            case "SA":
                countryName = "沙特阿拉伯(Saudi Arabia,SA)";
                break;
            case "BN":
                countryName = "文莱(Brunei Darussalam,BN)";
                break;
            case "SY":
                countryName = "叙利亚(Syrian Arab Republic,SY)";
                break;
            case "HN":
                countryName = "洪都拉斯(Honduras,HN)";
                break;
            case "PG":
                countryName = "巴布亚新几内亚(Papua New Guinea,PG)";
                break;
            case "HT":
                countryName = "海地(Haiti,HT)";
                break;
            case "SM":
                countryName = "圣马力诺(San Marino,SM)";
                break;
            case "MW":
                countryName = "马拉维(Malawi,MW)";
                break;
            case "CM":
                countryName = "喀麦隆(Cameroon,CM)";
                break;
            case "MG":
                countryName = "马达加斯加(Madagascar,MG)";
                break;
            case "PT":
                countryName = "葡萄牙(Portugal,PT)";
                break;
            case "CR":
                countryName = "哥斯达黎加(Costa Rica,CR)";
                break;
            case "BM":
                countryName = "百慕大(Bermuda,BM)";
                break;
            case "SR":
                countryName = "苏里南(Suriname,SR)";
                break;
            case "SB":
                countryName = "所罗门群岛(Solomon Islands,SB)";
                break;
            case "TA":
                countryName = "大溪地(Tahiti(French Polynesia),TA)";
                break;
            case "CW":
                countryName = "库拉索(Curacao,CW)";
                break;
            case "TW":
                countryName = "中国台湾(Taiwan,TW)";
                break;
            case "SL":
                countryName = "塞拉里昂(Sierra Leone,SL)";
                break;
            case "TU":
                countryName = "特鲁克岛（密克罗尼西亚联邦）(Truk(Micronesia,Federated States of),TU)";
                break;
            case "UI":
                countryName = "联盟群岛（圣文森特和格林纳丁斯群岛）(Union Islands(St. Vincent & the Grenadines),UI)";
                break;
            case "VL":
                countryName = "圣托马斯（美属维尔京群岛）(St. Thomas(U.S. Virgin Islands),VL)";
                break;
            case "NF":
                countryName = "诺福克(Norfolk Island(Australia),NF)";
                break;
            case "GT":
                countryName = "危地马拉(Guatemala,GT)";
                break;
            case "BG":
                countryName = "保加利亚(Bulgaria,BG)";
                break;
            case "PO":
                countryName = "波纳佩岛（密克罗尼西亚联邦）(Ponape(Micronesia,Federated States of),PO)";
                break;
            case "AW":
                countryName = "阿鲁巴岛(Aruba,AW)";
                break;
            case "EC":
                countryName = "厄瓜多尔(Ecuador,EC)";
                break;
            case "RS":
                countryName = "塞尔维亚(Serbia,RS)";
                break;
            case "BS":
                countryName = "巴哈马(Bahamas,BS)";
                break;
            case "BO":
                countryName = "玻利维亚(Bolivia,BO)";
                break;
            case "PW":
                countryName = "帕劳(Palau,PW)";
                break;
            case "KG":
                countryName = "吉尔吉斯斯坦(Kirghizia(Kyrgyzstan),KG)";
                break;
            case "TC":
                countryName = "特克斯和凯科斯群岛(Turks & Caicos Islands,TC)";
                break;
            case "LC":
                countryName = "圣卢西亚(St. Lucia,LC)";
                break;
            case "AZ":
                countryName = "阿塞拜疆(Azerbaijan,AZ)";
                break;
            case "MT":
                countryName = "马耳他(Malta,MT)";
                break;
            case "BI":
                countryName = "布隆迪(Burundi,BI)";
                break;
            case "AO":
                countryName = "安哥拉(Angola,AO)";
                break;
            case "CY":
                countryName = "塞浦路斯(Cyprus,CY)";
                break;
            case "BR":
                countryName = "巴西(Brazil,BR)";
                break;
            case "WS":
                countryName = "西萨摩亚(Samoa Western,WS)";
                break;
            case "AX":
                countryName = "奥兰群岛(Aland Island(Finland),AX)";
                break;
            case "LR":
                countryName = "利比里亚(Liberia,LR)";
                break;
            case "FO":
                countryName = "法罗群岛(Faroe Islands,FO)";
                break;
            case "UV":
                countryName = "圣约翰（美属维尔京群岛）(St. John(U.S. Virgin Islands),UV)";
                break;
            case "SG":
                countryName = "新加坡(Singapore,SG)";
                break;
            case "NG":
                countryName = "尼日利亚(Nigeria,NG)";
                break;
            case "PA":
                countryName = "巴拿马(Panama,PA)";
                break;
            case "DJ":
                countryName = "吉布提(Djibouti,DJ)";
                break;
            case "SZ":
                countryName = "斯威士兰(Swaziland,SZ)";
                break;
            case "DO":
                countryName = "多米尼加共和国(Dominican Republic,DO)";
                break;
            case "KO":
                countryName = "摩斯雷（密克罗尼西亚联邦）(Kosrae(Micronesia,Federated States of),KO)";
                break;
            case "MZ":
                countryName = "莫桑比克(Mozambique,MZ)";
                break;
            case "AS":
                countryName = "美国萨摩亚群岛(American Samoa,AS)";
                break;
            case "UY":
                countryName = "乌拉圭(Uruguay,UY)";
                break;
            case "KT":
                countryName = "科特迪瓦共和国(Republic Of Ivory Coast,KT)";
                break;
            case "AL":
                countryName = "阿尔巴尼亚(Albania,AL)";
                break;
            case "CG":
                countryName = "刚果(Congo,CG)";
                break;
            case "AI":
                countryName = "安圭拉(Anguilla,AI)";
                break;
            case "RW":
                countryName = "卢旺达(Rwanda,RW)";
                break;
            case "GR":
                countryName = "希腊(Greece,GR)";
                break;
            case "BW":
                countryName = "博茨瓦纳(Botswana,BW)";
                break;
            case "HR":
                countryName = "克罗地亚(Croatia,HR)";
                break;
            case "SC":
                countryName = "塞舌尔(Seychelles,SC)";
                break;
            case "NZ":
                countryName = "新西兰(New Zealand,NZ)";
                break;
            case "PF":
                countryName = "法属波利尼西亚(French Polynesia,PF)";
                break;
            case "ML":
                countryName = "马里(Mali,ML)";
                break;
            case "VU":
                countryName = "瓦努阿图(Vanuatu,VU)";
                break;
            case "MP":
                countryName = "北马里亚纳群岛(Northern Mariana Islands,MP)";
                break;
            case "TR":
                countryName = "土耳其(Turkey,TR)";
                break;
            case "BA":
                countryName = "波斯尼亚黑塞哥维那(Bosnia and Herzegovina,BA)";
                break;
            case "LY":
                countryName = "利比亚(Libyan Arab Jamahiriya,LY)";
                break;
            case "SV":
                countryName = "萨尔瓦多(El Salvador,SV)";
                break;
            case "TN":
                countryName = "突尼斯(Tunisia,TN)";
                break;
            case "SW":
                countryName = "圣克里斯托佛岛及尼维斯岛(St. Christopher(St. Kitts),SW)";
                break;
            case "AR":
                countryName = "阿根廷(Argentina,AR)";
                break;
            case "YE":
                countryName = "也门(Yemen,Republic of,YE)";
                break;
            case "TJ":
                countryName = "塔吉克斯坦(Tajikistan,TJ)";
                break;
            case "GA":
                countryName = "加蓬(Gabon,GA)";
                break;
            case "FJ":
                countryName = "斐济(Fiji,FJ)";
                break;
            case "GN":
                countryName = "几内亚(Guinea,GN)";
                break;
            case "GH":
                countryName = "加纳(Ghana,GH)";
                break;
            case "BD":
                countryName = "孟加拉国(Bangladesh,BD)";
                break;
            case "TV":
                countryName = "图瓦卢(Tuvalu,TV)";
                break;
            case "YA":
                countryName = "雅浦（密克罗尼西亚联邦）(Yap(Micronesia,Federated States of),YA)";
                break;
            case "LI":
                countryName = "列支敦士登(Liechtenstein,LI)";
                break;
            case "SP":
                countryName = "塞班岛(Saipan(Northern Mariana Islands),SP)";
                break;
            case "LB":
                countryName = "黎巴嫩(Lebanon,LB)";
                break;
            case "MQ":
                countryName = "马提尼克岛(Martinique,MQ)";
                break;
            case "ZA":
                countryName = "南非(South Africa,ZA)";
                break;
            case "BJ":
                countryName = "贝宁(Benin,BJ)";
                break;
            case "OM":
                countryName = "阿曼(Oman,OM)";
                break;
            case "TO":
                countryName = "汤加(Tonga,TO)";
                break;
            case "SK":
                countryName = "斯洛伐克(Slovakia,SK)";
                break;
            case "BB":
                countryName = "巴巴多斯(Barbados,BB)";
                break;
            case "MA":
                countryName = "摩洛哥(Morocco,MA)";
                break;
            case "IL":
                countryName = "以色列(Israel,IL)";
                break;
            case "LK":
                countryName = "斯里兰卡(Sri Lanka,LK)";
                break;
            case "BQ":
                countryName = "博内尔(Bonaire,St. Eustatius,Saba,BQ)";
                break;
            case "PR":
                countryName = "波多黎各(Puerto Rico,PR)";
                break;
            case "MS":
                countryName = "蒙特塞拉特(Montserrat,MS)";
                break;
            case "TM":
                countryName = "土库曼斯坦(Turkmenistan,TM)";
                break;
            case "KI":
                countryName = "基里巴斯(Kiribati,KI)";
                break;
            case "GW":
                countryName = "几内亚比绍(Guinea-Bissau,GW)";
                break;
            case "IC":
                countryName = "加那利群岛(Canary Islands(Spain),IC)";
                break;
            case "GF":
                countryName = "法属圭亚那(French Guiana,GF)";
                break;
            case "LS":
                countryName = "莱索托(Lesotho,LS)";
                break;
            case "JE":
                countryName = "泽西岛(Jersey(Channel Islands),JE)";
                break;
            case "DM":
                countryName = "多米尼加(Dominica,DM)";
                break;
            case "ET":
                countryName = "埃塞俄比亚(Ethiopia,ET)";
                break;
            case "BZ":
                countryName = "伯利兹(Belize,BZ)";
                break;
            case "GG":
                countryName = "根西岛(Guernsey(Channel Islands),GG)";
                break;
            case "JM":
                countryName = "牙买加(Jamaica,JM)";
                break;
            case "NB":
                countryName = "北爱尔兰(Northern Ireland(United Kingdom),NB)";
                break;
            case "CL":
                countryName = "智利(Chile,CL)";
                break;
            case "SD":
                countryName = "苏丹(Sudan,SD)";
                break;
            case "CI":
                countryName = "科特迪瓦（象牙海岸）(ivory coast,CI)";
                break;
            case "AN":
                countryName = "荷属安的列斯群岛(Netherlands antilles,AN)";
                break;
            case "CC":
                countryName = "科科斯群岛(Cocos(Keeling) Islands,CC)";
                break;
            case "CU":
                countryName = "古巴(Cuba,CU)";
                break;
            case "MC":
                countryName = "摩纳哥(Monaco,MC)";
                break;
            case "VC":
                countryName = "圣文森特岛(St. Vincent & the Grenadines,VC)";
                break;
            case "HK":
                countryName = "香港(HongKong,HK)";
                break;
            default:
                countryName = countryCode;
                break;
        }
        return countryName;
    }

    /**
     * 根据国家二字码获取国家中文名
     *
     * @param countryCode
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2019年1月17日]
     * @see [类、类#方法、类#成员]
     */
    public static String getCountryNameCnByCountryCode(String countryCode) {
        String countryNameCn = null;
        if (StringUtils.isBlank(countryCode)) {
            return countryNameCn;
        }
        String countryNameCnEnCode = getCountryNameCnEnCodeByCountryCode(countryCode);
        if (StringUtils.isBlank(countryNameCnEnCode)) {
            return countryNameCn;
        }
        if (countryNameCnEnCode.indexOf("(") > -1) {
            countryNameCn = countryNameCnEnCode.substring(0, countryNameCnEnCode.indexOf("("));
        } else {
            countryNameCn = countryNameCnEnCode;
        }
        return countryNameCn;
    }

    /**
     * 获取国家英文名
     *
     * @param countryCode
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2019年1月17日]
     * @see [类、类#方法、类#成员]
     */
    public static String getCountryNameEnByCountryCode(String countryCode) {
        String countryNameEn = null;
        if (StringUtils.isBlank(countryCode)) {
            return countryNameEn;
        }
        String countryNameCnEnCode = getCountryNameCnEnCodeByCountryCode(countryCode);
        if (StringUtils.isBlank(countryNameCnEnCode)) {
            return countryNameEn;
        }
        /*
         * 截取括号里面的内容
         */
        if (countryNameCnEnCode.indexOf("(") > -1) {
            countryNameEn = countryNameCnEnCode.substring(countryNameCnEnCode.indexOf("(") + 1, countryNameCnEnCode.length() - 1);
            countryNameEn = countryNameEn.replace("," + countryCode, "");
        } else {
            countryNameEn = countryNameCnEnCode;
        }
        return countryNameEn;
    }

    public static void main(String[] args) {
        System.out.println(getCountryNameCnByCountryCode("CN"));
    }


}