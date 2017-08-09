package co.kr.umbbalook.util;

import co.kr.umbbalook.vo.FileVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by whydda on 2017-07-28.
 */
public class FileUtils {

    @Autowired
    private ResourceLoader resourceLoader;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 파일 업로드
     * @param defaultParams
     * @throws Exception
     */
    public void fileUploadFiles(Map<String, Object> defaultParams) throws Exception{
        //업로드될 절대경로를 가지고 온다.
        String filePath = resourceLoader.getResource("/").getFile().getAbsolutePath() + File.separator +"resources" + File.separator + String.valueOf(defaultParams.get("filePath"));
        defaultParams.put("filePath", filePath);

        List<Map<String, Object>> uploadFiles = (List<Map<String, Object>>) defaultParams.get("uploadFileMap");

        //ATTACH 객체가 1나 라도 있으면 삭제파일이 있는지 확인하고 삭제한다.
        if(defaultParams.get("ATTACH") != null){
            if(((List<Map<String, Object>>) defaultParams.get("ATTACH")).size() > 0) {
                //삭제 메서드 호출
                deleteFile(defaultParams);
            }

            if (null != uploadFiles && 0 < uploadFiles.size()) {
                //업로드 메서드 호출
                uploadFile(defaultParams);
            }
        }
    }

    /**
     * 엑셀 파일 업로드
     * @param defaultParams
     * @throws Exception
     */
//    public static List<Map<String, String>> excelFileUploadFile(Map<String, Object> defaultParams) throws Exception{
//        //업로드될 절대경로를 가지고 온다.
//        Map<String, Object> map = ((List<Map<String, Object>>) defaultParams.get("uploadFileMap")).get(0);
//        MultipartFile multipartFile = ((List<MultipartFile>) map.get("files")).get(0);
//        ExcelReadOption excelReadOption = new ExcelReadOption();
//        //컬럼은 직접 입력한다. 이 부분이 MAP에 키가 된다.
//        excelReadOption.setOutputColumns("A","B","C","D","E","F","G");
//        excelReadOption.setStartRow(2); //시작 row 를 나타 낸다.
//        Workbook wb = ExcelUtils.getWorkbook(multipartFile.getInputStream());
//        return ExcelUtils.excelDataUpload(wb, excelReadOption);
//    }

    /**
     * 파일 삭제
     * @param paramMap
     * @throws Exception
     */
    public void deleteFile(Map<String, Object> paramMap) throws Exception{
        for (Map<String, Object> map : (List<Map<String, Object>>) paramMap.get("ATTACH")) {
            if ("D".equals(map.get("CRUD"))) {
                //파일 삭제처리
                //fileService.deleteFile(downPath, String.valueOf(map.get("IMAGE_SEQ_NO")), Integer.parseInt(String.valueOf(map.get("IMAGE_SEQ_NO"))));
            }else{
                if(!String.valueOf(map.get("IMAGE_SEQ_NO")).equals("null")){
                    paramMap.put("IMAGE_SEQ_NO", String.valueOf(map.get("IMAGE_SEQ_NO")));
                }
            }
        }
    }

    /**
     * 사진 업로드
     * @param defaultParams
     * @throws Exception
     */
    public void uploadFile(Map<String, Object> defaultParams) throws Exception{
        for (Map<String, Object> map : (List<Map<String, Object>>) defaultParams.get("uploadFileMap")) {
            if (String.valueOf(map.get("key")).equals(String.valueOf(defaultParams.get("RN")))) {
                for (MultipartFile multipartFile : (List<MultipartFile>) map.get("files")) {
                    defaultParams.put("IMAGE_SEQ_NO", fileUploadPros(defaultParams, multipartFile));
                }
            }
        }
    }


    /**
     * 파일 저장 로직
     * @param defaultParams
     * @param multipartFile
     * @return
     */
    public static String fileUploadPros(Map<String, Object> defaultParams, MultipartFile multipartFile) {
        String subDir = String.valueOf(defaultParams.get("filePath"));

        createDirectory(Paths.get(subDir));

        List<FileVO> fileLists = new ArrayList<>();
        String imageSeqNo = "";

        try {
            String key = String.valueOf(defaultParams.get("IMAGE_SEQ_NO"));

//            if (fileService.retrieveAtchFileNoChk(key)) {
//                atchFileNo = key;
//                int atchFileSeq = fileService.retrieveFileSeq(key);
//                for (int i = 0; i < uploadFileInfos.size(); i++) {
//                    FileVO fileInfo = new FileVO();
//                    fileLists.add(fileInfo);
//                }
//
//            } else {
//                atchFileNo = fileService.getAtchFileNo();
//                for (int i = 0; i < uploadFileInfos.size(); i++) {
//                    FileVO fileInfo = new FileVO();
//                    fileLists.add(fileInfo);
//                }
//            }
//
//            fileService.insertAtchFile(fileLists);

        } catch (Exception e) {
            //물리적 삭제
            //fileUpload.cleanupTransferedFile(uploadFileInfos);
        }

        return imageSeqNo;
    }

    /**
     * 디렉터리 생성
     * @param path
     * @return
     */
    public static void createDirectory(Path path) {
        if (!Files.exists(path) || Files.isDirectory(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e, e.getStackTrace());
            }
        }
    }
}
