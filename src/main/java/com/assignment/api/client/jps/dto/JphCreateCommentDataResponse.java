package com.assignment.api.client.jps.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "JphCreateCommentDataResponse", description = "JphCreateCommentDataResponse contains response fields for creating comment")
public class JphCreateCommentDataResponse {

    @ApiModelProperty(position = 1, name = "id", required = true)
    String id;

    @ApiModelProperty(position = 2, name = "postId", required = true)
    String postId;

    @ApiModelProperty(position = 3, name = "name", required = true)
    String name;

    @ApiModelProperty(position = 4, name = "email", required = true)
    String email;

    @ApiModelProperty(position = 5, name = "body", required = true)
    String body;

}
