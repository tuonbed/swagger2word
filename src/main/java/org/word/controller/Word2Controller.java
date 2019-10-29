package org.word.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.word.model.Table;
import org.word.service.WordService2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

/**
 * @author tuonbed
 * @date 2019/10/29  10:18
 */
@Controller
public class Word2Controller {
    @Autowired
    private WordService2 wordService2;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${swagger.urls}")
    private String swaggerUrls;

    @Value("${swagger.name}")
    private String swaggerName;
    /**
     * 将 swagger 文档转换成 html 文档，可通过在网页上右键另存为 xxx.doc 的方式转换为 word 文档
     *
     * @param model
     * @param url   需要转换成 word 文档的资源地址
     * @return
     */
    @Deprecated
    @RequestMapping("/toWord2")
    public String getWord(Model model, @RequestParam(value = "url", required = false) String url
    ,@RequestParam(value = "name", required = false) String name) {
        String urls= Optional.ofNullable(url).orElse(swaggerUrls);
        String title= Optional.ofNullable(name).orElse(swaggerName);
        List apps = wordService2.apiMap(urls.split(";"));
        model.addAttribute("apps", apps);
        model.addAttribute("title", title);
        return "word2";
    }

    /**
     * 将 swagger 文档一键下载为 doc 文档
     *
     * @param url      需要转换成 word 文档的资源地址
     * @param request
     * @param response
     */
    @RequestMapping("/downloadWord2")
    public void word(@RequestParam(value = "url", required = false) String url,
            @RequestParam(value = "name", required = false) String name,HttpServletRequest request, HttpServletResponse response) {
        String path=request.getRequestURL().toString().replace(request.getRequestURI(),"");
        String urls= Optional.ofNullable(url).orElse(swaggerUrls);
        String title= Optional.ofNullable(name).orElse(swaggerName);
        ResponseEntity<String> forEntity = restTemplate.getForEntity(path+"/toWord2?url=" + urls, String.class);
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try (BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("toWord.doc", "utf-8"));
            byte[] bytes = forEntity.getBody().getBytes();
            bos.write(bytes, 0, bytes.length);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
