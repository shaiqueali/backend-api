package com.assignment.api.client.jps.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "JphCreatePostDataRequest", description = "JphCreatePostDataRequest contains request fields for creating post")
public class JphCreatePostDataRequest {

    @ApiModelProperty(position = 1, name = "documentId", required = true)
    @NotEmpty(message = "documentId can't be null or empty")
    String documentId;

    @ApiModelProperty(position = 2, name = "body", required = true)
    @NotEmpty(message = "body can't be null or empty")
    String body;
}
