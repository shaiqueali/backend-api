package com.assignment.api.client.jps.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "JphCreatePostDataResponse", description = "JphCreatePostDataResponse contains response fields for creating post")
public class JphCreatePostDataResponse {

    @ApiModelProperty(position = 1, name = "id")
    String id;

    @ApiModelProperty(position = 2, name = "documentId")
    String documentId;

    @ApiModelProperty(position = 3, name = "body")
    String body;

}
