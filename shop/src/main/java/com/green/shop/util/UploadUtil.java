package com.green.shop.util;

import com.green.shop.item.vo.ImgVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//파일 첨부와 관련된 기능 모임집
public class UploadUtil {
    //파일의 확장자를 문자열로 리턴하는 메소드
    public static String getExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    //uuid를 통한 파일명 생성
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    //단일 파일 업로드 메소드
    public static ImgVO uploadFile(MultipartFile uploadFile){
        ImgVO imgVO = null;

        if(!uploadFile.isEmpty()){
            imgVO = new ImgVO();

            //확장자 추출
            String extension = getExtension(uploadFile.getOriginalFilename());

            //중복되지 않는 파일명 생성
            String fileName = getUUID() + extension;

            try{
                File file1 = new File(ConstantVariable.UPLOAD_PATH + fileName);
                uploadFile.transferTo(file1);

                imgVO.setAttachedFileName(fileName);
                imgVO.setOriginFileName(uploadFile.getOriginalFilename());
                imgVO.setIsMain("Y");
            }catch(Exception e){
                System.out.println("파일 첨부 중 예외 발생~");
                e.printStackTrace();
            }
        }
        return imgVO;
    }

    //다중 첨부 메소드
    public static List<ImgVO> multiUploadFile(MultipartFile[] uploadFiles){
        List<ImgVO> imgList = new ArrayList<>();

        for(MultipartFile uploadFile : uploadFiles){
            ImgVO vo = uploadFile(uploadFile);
            if(vo != null){
                vo.setIsMain("N");
                imgList.add(vo);
            }
        }
        return imgList;
    }


}
