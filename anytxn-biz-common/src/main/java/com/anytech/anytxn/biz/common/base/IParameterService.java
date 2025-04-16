package com.anytech.anytxn.biz.common.base;

import com.anytech.anytxn.biz.common.dto.PageResultDTO;
import com.anytech.anytxn.biz.common.param.poi.ParamExportDTO;
import com.anytech.anytxn.biz.common.param.poi.ParamExportPageDTO;
import com.anytech.anytxn.biz.common.param.poi.ParamImportDTO;
import com.anytech.anytxn.biz.common.param.poi.ParamImportResultDTO;
import com.anytech.anytxn.biz.common.util.JacksonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.anytech.anytxn.biz.common.base.audit.ParmModificationRecord;

import java.io.IOException;

/**
 * @Author: sukang
 * @Date: 2021/4/23 15:10
 *
 * 查看本次审核的记录对应的参数详情页面
 *
 * 1. 删除 查看删除前的参数的详情页面
 *
 * 2. 更新 查看更新前参数的详情页面
 *
 * 3.新增 无
 *
 * 需要获取审核记录中的 ORIGINAL_ID 或者( PARM_TABLE_ID && ORGANIZATION_NUMBER)  调用原查看详情的service方法
 *
 *
 */
public interface IParameterService {

    /**
     * 通过审核记录中的 唯一标识 获取对应参数的详情信息;
     * @param parmModificationRecord {@link ParmModificationRecord}
     * @return  参数详情
     */
    default Object getParameterDetail(ParmModificationRecord parmModificationRecord){

        //默认方法返回一个空json
        return JacksonUtil.readValue("{}", JsonNode.class);
    }

    /**
     * 参数的导出
     * @return 导出的字节数组
     */
    default ParamImportDTO exportData(ParamExportDTO paramPoiExportDTO){
        return null;
    }

    /**
     * 参数导入
     * @param paramImportResultDTO {@link ParamImportResultDTO}
     * @return ParamImportResultDTO
     */
    default ParamImportResultDTO importData(ParamImportResultDTO paramImportResultDTO) throws IOException {
        return null;
    }

    /**
     * 参数导出选项
     */
    default PageResultDTO<ParamExportPageDTO> exPortPages(ParamExportDTO paramExportDTO){
        return new PageResultDTO<>();
    }

}
