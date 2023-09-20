/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.pig4cloud.pig.music.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pig4cloud.pig.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 *
 * @author pig code generator
 * @date 2023-07-14 02:57:48
 */
@Data
@TableName("music")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "")
public class Music extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description ="id")
    private Long id;

    /**
     * name
     */
    @Schema(description ="name")
    private String name;

    /**
     * uploader
     */
    @Schema(description ="uploader")
    private String uploader;

    /**
     * account
     */
    @Schema(description ="account")
    private String account;

    /**
     * views
     */
    @Schema(description ="views")
    private Integer views;

    /**
     * likes
     */
    @Schema(description ="likes")
    private Integer likes;

    /**
     * musicFiles
     */
    @Schema(description ="musicFiles")
    private String musicFiles;

    /**
     * comment
     */
    @Schema(description ="comment")
    private String comment;


}
