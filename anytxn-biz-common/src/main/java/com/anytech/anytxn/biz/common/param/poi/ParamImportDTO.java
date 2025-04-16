package com.anytech.anytxn.biz.common.param.poi;

import com.anytech.anytxn.biz.common.constant.AnyTxnCommonRespCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.InputStream;

/**
 * @Author: sukang
 * @Date: 2021/8/27 10:53
 */
@Setter
@Getter
@ToString
public class ParamImportDTO {


    private String fileName;

    private String fileType;

    private String paramsCode;

    private String totalNum;

    private byte[] bytes;

    private String respCode;

    private String respDesc;

    private InputStream inputStream;

    public ParamImportDTO() {
    }

    public ParamImportDTO(String paramsCode,String fileType) {
        this.paramsCode = paramsCode;
        this.fileType = fileType;
    }


    public void buildErrorInfo(String respCode, String respDesc){
        this.respCode = respCode;
        this.respDesc = respDesc;
    }

    public void buildSuccessInfo(){
        this.respCode = AnyTxnCommonRespCode.RESPONSE_SUCESS;
        this.respDesc = "success";
    }


    public static final class ParamImportDTOBuilder {
        private String fileName;
        private String fileType;
        private String paramsCode;
        private String totalNum;
        private byte[] bytes;
        private String respCode;
        private String respDesc;

        private ParamImportDTOBuilder() {
        }

        public static ParamImportDTOBuilder aParamImportDTO() {
            return new ParamImportDTOBuilder();
        }

        public ParamImportDTOBuilder withFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public ParamImportDTOBuilder withFileType(String fileType) {
            this.fileType = fileType;
            return this;
        }

        public ParamImportDTOBuilder withParamsCode(String paramsCode) {
            this.paramsCode = paramsCode;
            return this;
        }

        public ParamImportDTOBuilder withTotalNum(String totalNum) {
            this.totalNum = totalNum;
            return this;
        }

        public ParamImportDTOBuilder withBytes(byte[] bytes) {
            this.bytes = bytes;
            return this;
        }

        public ParamImportDTOBuilder withRespCode(String respCode) {
            this.respCode = respCode;
            return this;
        }

        public ParamImportDTOBuilder withRespDesc(String respDesc) {
            this.respDesc = respDesc;
            return this;
        }

        public ParamImportDTO build() {
            ParamImportDTO paramImportDTO = new ParamImportDTO();
            paramImportDTO.setFileName(fileName);
            paramImportDTO.setFileType(fileType);
            paramImportDTO.setParamsCode(paramsCode);
            paramImportDTO.setTotalNum(totalNum);
            paramImportDTO.setBytes(bytes);
            paramImportDTO.setRespCode(respCode);
            paramImportDTO.setRespDesc(respDesc);
            return paramImportDTO;
        }
    }
}
