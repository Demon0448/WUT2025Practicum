package com.oa7.pojo;

import lombok.Data;

@Data
public class SignDetailDTO {

    private String date;
    private Integer morningSignedCount;
    private Integer morningUnsignedCount;
    private Integer eveningSignedCount;
    private Integer eveningUnsignedCount;
    private Integer totalSignedCount;
    private Integer totalUnsignedCount;

}
