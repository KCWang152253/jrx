/*
 * Copyright 1999-$toady.year Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.config.server.service.query.enums;

/**
 * ResponseCode.
 *
 * @author Nacos
 */
public enum ResponseCode {
    /**
     * Request success.
     */
    SUCCESS(200, "Response ok"),
    
    /**
     * Request failed.
     */
    FAIL(500, "Response fail");
    
    int code;
    
    String desc;
    
    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public int getCode() {
        return code;
    }
    
    /**
     * Getter method for property <tt>desc</tt>.
     *
     * @return property value of desc
     */
    public String getDesc() {
        return desc;
    }
}