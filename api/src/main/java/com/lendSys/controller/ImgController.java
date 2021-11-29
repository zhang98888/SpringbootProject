package com.lendSys.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.lendSys.vo.ResultVo;
import io.swagger.annotations.Api;
import org.apache.coyote.OutputBuffer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/images")
@CrossOrigin
@Api(value = "Images Upload",tags = "Images Upload")
public class ImgController {

    @PostMapping("/upload")
    public ResultVo upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String flag = IdUtil.fastSimpleUUID();
        String rootFilePath = System.getProperty("user.dir") + "/api/src/main/resources/static/image/" + flag + "_" + originalFilename;
        FileUtil.writeBytes(file.getBytes(),rootFilePath);
        return new ResultVo(1000,"success",0,"http://8.208.82.237:9000/images/" + flag);

    }

    @GetMapping("/{flag}")
    public void getImg(@PathVariable String flag, HttpServletResponse response){
        OutputStream os;
        String basePath = System.getProperty("user.dir") + "/api/src/main/resources/static/image/";
        List<String> filenames = FileUtil.listFileNames(basePath);
        String filename = filenames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try{
            if(StrUtil.isNotEmpty(filename)){
                response.addHeader("content-Disposition","attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath+filename);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
