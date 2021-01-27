package com.chinasoft.ssm.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

public class BaseController {
    public List<String> getDelData(String ids){
        List<String> list=new ArrayList<String>();
        StringBuffer buff=new StringBuffer(ids);
        buff.delete(ids.length()-1,ids.length());
        //System.out.println(buff.toString());
        String temp[]=ids.split(",");
        for (int i=0;i<temp.length;i++){
            list.add(temp[i]);
        }
        return list;
    }

    public Map<String,Object> getParamData(HttpServletRequest request){
        Map<String,String[]> map=request.getParameterMap();
        Map<String,Object> mapAll=new HashMap<String,Object>();//最后组装成的map
        Set<String> set=map.keySet();
        Iterator<String> it=set.iterator();
        while(it.hasNext()){
            String key=it.next();//String 类型的key
            String[] value= map.get(key);
            // System.out.println(value[0]);
            Object value1=value[0];
            mapAll.put(key,value1);
        }
        return mapAll;
    }
    //组装分页
    public Map<String,Object> getPageData(Map<String,Object> map,int totalCount){
        int pageIndex=0;
        int pageSize=0;
        int totalPage=0;
        int start=0;
        if (map.get("pageIndex")!=null&&!map.get("pageSize").toString().equals("")&&map.get("pageSize")!=null&&!map.get("pageSize").toString().equals("")){
            //分页,得到分页的总数
            pageIndex=Integer.parseInt(map.get("pageIndex").toString());
            pageSize=Integer.parseInt(map.get("pageSize").toString());
            totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
            start=(pageIndex-1)*pageSize;//得到索引号
            map.put("pageIndex",pageIndex);
            map.put("pageSize",pageSize);
            map.put("totlaPage",totalPage);
            map.put("start",start);
            map.put("totlaCount",totalCount);
        }
        return map;
    }

    public String getRandomUUId(){
        return UUID.randomUUID().toString();
    }

    public String upload(@RequestParam("attr") MultipartFile file) throws Exception{
        String filename=file.getOriginalFilename();
//        System.out.println(filename);
        String suffixName=filename.substring(filename.lastIndexOf("."));
//        String newFilename="/img/"+UUID.randomUUID().toString()+suffixName;
//        String filePath = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+newFilename;
        String newFilename="/img/"+UUID.randomUUID().toString()+suffixName;
        String filePath = "D:"+newFilename;
        System.out.println(filePath);
        FileOutputStream out = new FileOutputStream(filePath);
        InputStream in = file.getInputStream();//文件输入流
        IOUtils.copy(in,out);
        in.close();
        out.close();
        return newFilename;
    }

//    public static void main(String[] args) {
//        String str="1.2.3.4.5.6.7.8.9.0";
//        String s[]=str.split("\\.");
//        for ( int i=0;i<s.length;i++){
//            System.out.println(s[i]);
//        }
//    }
}
