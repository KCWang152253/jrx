package com.anytech.anytxn.biz.common.param.poi;


import com.anytech.anytxn.biz.common.constant.AnyTxnCommonRespCode;
import com.anytech.anytxn.biz.common.web.AnyTxnHttpResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: sukang
 * @Date: 2021/8/30 11:37
 */
@Setter
@Getter
@ToString
public class ParamImportResultDTO {

    private String fileName;

    private String fileType;

    private byte[] bytes;

    private String paramsCode;

    private String totalNum;

    private String successNum;

    private String respCode;

    private String respDesc;

    private List<AnyTxnHttpResponse<?>> executeResult;

    public void buildErrorInfo(String respCode, String respDesc){
        this.respCode = respCode;
        this.respDesc = respDesc;
    }

    public ParamImportResultDTO buildSuccessInfo(){
        this.respCode = AnyTxnCommonRespCode.RESPONSE_SUCESS;
        this.respDesc = "success";
        return this;
    }

    public void addResult(AnyTxnHttpResponse<Object> anyTxnHttpResponse) {
        this.executeResult.add(anyTxnHttpResponse);
    }


    public static final class ParamImportResultDtoBuilder {
        private String fileName;
        private String fileType;
        private String paramsCode;
        private String totalNum;

        private byte[] bytes;

        private String successNum;

        private List<AnyTxnHttpResponse<?>> executeResult;

        private ParamImportResultDtoBuilder(List<AnyTxnHttpResponse<?>> executeResult) {
            this.executeResult = executeResult;
        }

        public static ParamImportResultDtoBuilder getBuilder() {
            return new ParamImportResultDtoBuilder(new ArrayList<>(8));
        }

        public ParamImportResultDtoBuilder withFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public ParamImportResultDtoBuilder withSuccessNum(String successNum) {
            this.successNum = successNum;
            return this;
        }



        public ParamImportResultDtoBuilder withFileType(String fileType) {
            this.fileType = fileType;
            return this;
        }

        public ParamImportResultDtoBuilder withParamsCode(String paramsCode) {
            this.paramsCode = paramsCode;
            return this;
        }


        public ParamImportResultDtoBuilder withBytes(byte[] bytes) {
            this.bytes = bytes;
            return this;
        }


        public ParamImportResultDTO build() {
            ParamImportResultDTO paramImportResultDTO = new ParamImportResultDTO();
            paramImportResultDTO.setFileName(fileName);
            paramImportResultDTO.setFileType(fileType);
            paramImportResultDTO.setParamsCode(paramsCode);
            paramImportResultDTO.setSuccessNum(successNum);
            paramImportResultDTO.setExecuteResult(executeResult);
            paramImportResultDTO.setBytes(bytes);
            return paramImportResultDTO;
        }
    }
}
