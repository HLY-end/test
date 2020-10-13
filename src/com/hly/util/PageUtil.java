package com.hly.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @program: hly_emall_ssm
 * Author HLY
 * @desc 分页工具类
 * @create: 2020-09-24 11:38
 **/
public class PageUtil {

    /**
     * 生成分页Html
     * @param request
     * @param total 总记录数
     * @param page 当前页数
     * @param size 每页数量
     * @return
     */
    public static String getPageHtml(HttpServletRequest request , long total , int page , int size){
        if(total <= 0){
            return null;
        }
        //计算页数
        int pages = (int) (total % size == 0 ? total/size : total/size+1);
        pages=  pages == 0 ? 1 : pages;
        // 请求地址
        String url = request.getRequestURL().toString();
        //请求参数,取出typeId作为请求参数
        StringBuilder paramBuilder = new StringBuilder();
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()){
            String param = params.nextElement();
            if(param.indexOf("page") > -1){
                continue;
            }
            paramBuilder.append("&").append(param).append("=").append(request.getParameter(param));
        }

        //分页字符串
        StringBuilder pageBuilder = new StringBuilder();
        pageBuilder.append("<div class = 'holder'>");
        //判断是否上一页，有则添加上一页、首页标签
        if(page <= 1){
//            pageBuilder.append("<a title='已是首页'>到首页了</a>");
        }else {
            pageBuilder.append("<a href=' ").append(url).append("?").append("page=1")
                    .append(paramBuilder).append("'>首页</a>");
            pageBuilder.append("<a href=' ").append(url).append("?").append("page=").append(page-1)
                    .append(paramBuilder).append("'>上页</a>"); //paramBuilder自动转换成String
        }
        //中间数字页码
        if (pages <= 7){ //总页数小于7，就显示全部页
            for(int i = 1; i <= pages; i++){
                pageBuilder.append(packPageItem(url , paramBuilder.toString(), page , i));
            }
        }else {
            if(page < 3 || page > pages-3){
                pageBuilder.append(packPageItem(url,paramBuilder.toString(),page,1));
                pageBuilder.append(packPageItem(url,paramBuilder.toString(),page,2));
                pageBuilder.append(packPageItem(url,paramBuilder.toString(),page,3));
                pageBuilder.append("...");
                pageBuilder.append(packPageItem(url,paramBuilder.toString(),page,pages-2));
                pageBuilder.append(packPageItem(url,paramBuilder.toString(),page,pages-1));
                pageBuilder.append(packPageItem(url,paramBuilder.toString(),page,pages));
            }else {
                pageBuilder.append(packPageItem(url,paramBuilder.toString(),page,1));
                pageBuilder.append(packPageItem(url,paramBuilder.toString(),page,2));
                pageBuilder.append("...");
                pageBuilder.append(packPageItem(url,paramBuilder.toString(),page,page-1));
                pageBuilder.append(packPageItem(url,paramBuilder.toString(),page,page));
                pageBuilder.append(packPageItem(url,paramBuilder.toString(),page,page+1));
                pageBuilder.append("...");
                pageBuilder.append(packPageItem(url,paramBuilder.toString(),page,pages-1));
                pageBuilder.append(packPageItem(url,paramBuilder.toString(),page,pages));
            }
        }
        //判断是否有下一页，有则添加下一页、尾页标签
        if(page >= pages){
//           pageBuilder.append("<a title='已是尾页'>到尾页了</a>");
        }else {
            pageBuilder.append("<a href=' ").append(url).append("?").append("page=").append(page+1)
                    .append(paramBuilder).append("'> 下页</a>");
            pageBuilder.append("<a href=' ").append(url).append("?").append("page=").append(pages)
                    .append(paramBuilder).append("'> 尾页</a>");
        }
        pageBuilder.append("</div>");
        return pageBuilder.toString();
    }

    /**
     * 封装分页项，数字
     * @param url http://localhost:8055/hly_emal_ssm_war_exploded/index/type
     * @param params typeId 参数
     * @param page 当前页
     * @param i 用来判断是否等于当前页的
     * @return
     */
    private static String packPageItem(String url, String params, int page, int i){
        StringBuilder pageBuilder = new StringBuilder();
        if(i == page){
            pageBuilder.append("<a class = 'jp-current'>").append(i).append("</a>"); //判断是否为当前页，设置高亮
        }else {
            pageBuilder.append("<a title = '第").append(i).append("页' href =' ").append(url).append("?").append("page=").append(i)
                   .append(params).append(" '>");pageBuilder.append(i).append("</a>");
        }
        return pageBuilder.toString();
    }
}
