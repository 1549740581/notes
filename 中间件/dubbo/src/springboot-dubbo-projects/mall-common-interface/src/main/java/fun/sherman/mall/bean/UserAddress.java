package fun.sherman.mall.bean;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAddress implements Serializable {
    private Integer id;
    /**
     * 用户收货地址
     */
    private String userAddress;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 手机号码
     */
    private String phoneNum;
    /**
     * 是否为默认收货：true
     */
    private boolean isDefault;

}
