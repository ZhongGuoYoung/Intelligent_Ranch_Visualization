package com.mlm.mlmserver.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.deepoove.poi.data.*;
import com.deepoove.poi.data.Pictures.PictureBuilder;
import com.deepoove.poi.data.Tables.TableBuilder;
import com.mlm.mlmserver.config.YmlConfig;
import com.mlm.mlmserver.service.WordService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class WordServiceImpl implements WordService {
    @Resource
    private YmlConfig yml;


    @Override
    public String create(JSONObject word) {
        Long timestamp = LocalDateTime.now().toInstant(ZoneOffset.of("+08")).toEpochMilli();
        String title = word.getString("title");
        String path = yml.getWordPath() + title + "-" + Long.toString(timestamp) + ".docx";
        JSONArray curParagraph = word.getJSONArray("paragraph");
        JSONArray newParagraph = new JSONArray();

        for (int i = 0; i < curParagraph.size(); i++) {
            JSONObject curPara = curParagraph.getJSONObject(i);
            String ptype = curPara.getString("type");
            String ptitle = curPara.getString("title");
            if ("text".equals(ptype)) {
                String pdata = curPara.getString("data");
                if (pdata != null && !pdata.isEmpty()) {
                    String[] arr = pdata.split("\\r?\\n");
                    for (int j = 0; j < arr.length; j++) {
                        JSONObject newPara = new JSONObject();
                        newPara.put("type", ptype);
                        newPara.put("title", j == 0 ? ptitle : null);
                        newPara.put("data", arr[j]);
                        newParagraph.add(newPara);
                    }
                } else {
                    newParagraph.add(curPara);
                }
            }
            if ("table".equals(ptype)) {
                JSONArray pdata = curPara.getJSONArray("data");
                JSONArray pcolumn = curPara.getJSONArray("column");
//				表头
                String[] th = pcolumn.stream().map(item -> JSONObject.from(item).getString("label")).toArray(String[]::new);
                RowRenderData trh = Rows.of(th).bgColor("F1F1F1").center().create();
                TableBuilder table = Tables.of(trh);
//				表主体
                for (int j = 0; j < pdata.size(); j++) {
                    JSONObject item = pdata.getJSONObject(j);
                    String[] td = pcolumn.stream().map(v -> item.getString(JSONObject.from(v).getString("prop"))).toArray(String[]::new);
                    RowRenderData trd = Rows.of(td).center().create();
                    table.addRow(trd);
                }
//				表格宽度
                int[] colWidthsPercent = pcolumn.stream().map(item -> JSONObject.from(item)).filter(item -> item.containsKey("width")).mapToInt(item -> item.getIntValue("width", 0)).toArray();
                if (colWidthsPercent.length == pcolumn.size()) {table.percentWidth("100%", colWidthsPercent);} else {
                    table.autoWidth();
                }
                JSONObject newPara = new JSONObject();
                newPara.put("type", ptype);
                newPara.put("title", ptitle);
                newPara.put("data", table.center().cellMargin(0.1, 0.2, 0.1, 0.2).create());
                newParagraph.add(newPara);
            }
            if ("image".equals(ptype) || "chart".equals(ptype)) {
                String pdata = curPara.getString("data");
                double width = curPara.getDoubleValue("width");
                double height = curPara.getDoubleValue("height");
                PictureBuilder pictures = Pictures.ofBase64(pdata, PictureType.PNG);
//				图片尺寸
                if (width != 0.0 && height != 0.0) {pictures.sizeInCm(width, height);} else {pictures.fitSize();}
                JSONObject newPara = new JSONObject();
                newPara.put("type", "image");
                newPara.put("title", ptitle);
                newPara.put("data", pictures.center().create());
                newParagraph.add(newPara);
            }
            word.put("paragraph", newParagraph);
        }

        try {
//			模板的绝对路径
            InputStream templatePath = new ClassPathResource("template/模板.docx").getInputStream();
//			编译模板、渲染数据
            ConfigureBuilder builder = Configure.builder();
            builder.useSpringEL();
            XWPFTemplate template = XWPFTemplate.compile(templatePath, builder.build()).render(word);
//			输出文档
            template.writeAndClose(new FileOutputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}