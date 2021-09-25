package com.assignment.api.client.jps.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "JphCreateCommentDataRequest", description = "JphCreateCommentDataRequest contains request fields for creating comment")
public class JphCreateCommentDataRequest {

    @ApiModelProperty(position = 1, name = "postId", required = true)
    @NotEmpty(message = "postId can't be null or empty")
    String postId;

    @ApiModelProperty(position = 2, name = "name", required = true)
    @NotEmpty(message = "name can't be null or empty")
    String name;

    @ApiModelProperty(position = 3, name = "email", required = true)
    @Email(message = "email is not formatted")
    @NotEmpty(message = "postId can't be null or empty")
    String email;

    @ApiModelProperty(position = 4, name = "body", required = true)
    @NotEmpty(message = "body can't be null or empty")
    String body;
}
