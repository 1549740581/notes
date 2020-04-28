package fun.sherman.springcloud.commonapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created on 2020/4/28
 *
 * @author sherman tang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("payment")
public class Payment implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("serial")
    private String serial;
}
