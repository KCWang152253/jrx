package com.anytech.anytxn.biz.common.base;

import com.anytech.anytxn.biz.common.constant.AnyTxnCommonRespCode;
import com.anytech.anytxn.biz.common.enums.ParmAuditOperateTypeEnum;
import com.anytech.anytxn.biz.common.base.audit.ParmModificationRecord;
import com.anytech.anytxn.biz.common.exception.AnyTxnException;
import com.anytech.anytxn.biz.common.web.AnyTxnHttpResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Optional;

/**
 * @Author: sukang
 * @Date: 2021/4/16 15:39
 */
@Slf4j
public abstract class AbstractParameterService implements IParameterService{


    /**
     * 审核通过方法统一入口
     */
    public final Boolean approved(ParmModificationRecord parmModificationRecord) throws Exception{

        if (isUpdate(parmModificationRecord)){
            return updateDb(parmModificationRecord);
        }

        if (isDelete(parmModificationRecord)){
            return deleteDb(parmModificationRecord);
        }

        if (isInsert(parmModificationRecord)){
            return insertDb(parmModificationRecord);
        }

        return false;
    }

    /**
     * 更新参数审核通过，操作db
     * @param parmModificationRecord {@link ParmModificationRecord}
     * @return true 成功  false 失败
     */
    protected abstract boolean updateDb(ParmModificationRecord parmModificationRecord) throws Exception;


    /**
     *插入人参数审核通过，操作db
     * @param parmModificationRecord {@link ParmModificationRecord}
     * @return true 成功  false 失败
     */
    protected abstract  boolean insertDb(ParmModificationRecord parmModificationRecord) throws Exception;


    /**
     *删除参数审核通过 操作db
     * @param parmModificationRecord {@link ParmModificationRecord}
     * @return true 成功  false 失败
     */
    protected  abstract boolean deleteDb(ParmModificationRecord parmModificationRecord) throws Exception;



    private boolean isUpdate(ParmModificationRecord parmModificationRecord){
        return Objects.equals(ParmAuditOperateTypeEnum.UPDATE.getOperateType(), parmModificationRecord.getOperateType());
    }

    private boolean isDelete(ParmModificationRecord parmModificationRecord){
        return Objects.equals(ParmAuditOperateTypeEnum.DELETE.getOperateType(), parmModificationRecord.getOperateType());
    }


    private boolean isInsert(ParmModificationRecord parmModificationRecord){
        return Objects.equals(ParmAuditOperateTypeEnum.INSERT.getOperateType(), parmModificationRecord.getOperateType());
    }

    public AnyTxnHttpResponse<Object> buildExceptionInfo(Exception ex, Object data){
        AnyTxnHttpResponse<Object> response;
        if (ex instanceof AnyTxnException){
            AnyTxnException exception = (AnyTxnException) ex;
            response = AnyTxnHttpResponse.fail(exception.getErrCode(),
                    Optional.ofNullable(exception.getErrDetail()).orElse(exception.getErrMsg()));
        }else {
            log.error("data exception", ex);
            response = AnyTxnHttpResponse.fail(AnyTxnCommonRespCode.RESPONSE_UNKNOWN,"Data processing exception");
        }
        response.setData(data);
        return response;
    }


}
